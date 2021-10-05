package xyz.kp.pax.checks.impl;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.out.entityteleport.WrappedPacketOutEntityTeleport;
import org.bukkit.entity.Player;
import xyz.kp.pax.cache.PlayerData;
import xyz.kp.pax.cache.PlayerDataManager;
import xyz.kp.pax.checks.Core;
import xyz.kp.pax.checks.Flags;

public class Timer extends PacketListenerAbstract {

    public Timer() {
        super(PacketListenerPriority.LOW);
    }

    private final Flags flag = Flags.TIMER;

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player);
        if (playerData == null)
            return;

        if (PacketType.Play.Client.Util.isInstanceOfFlying(event.getPacketId())) {
            long time = System.currentTimeMillis();
            long lastTime = playerData.timerLT != 0 ? playerData.timerLT : time - 50;
            playerData.timerLT = time;

            long rate = time - lastTime;

            playerData.timerBL += 50.0;
            playerData.timerBL -= rate;

            if (playerData.timerBL >= 10.0) {
                double bal = playerData.timerBL;
                playerData.timerBL = 0.0;
                playerData.timerVL++;

                if ((playerData.timerVL % flag.getThreshold() == 0) &&
                        playerData.timerVL >= flag.getThreshold()) {
                    Core.alert(player, flag, playerData.timerVL / flag.getThreshold(),
                            bal + " offset");
                }

            }
        }
    }

    @Override
    public void onPacketPlaySend(PacketPlaySendEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player);
        if (playerData == null)
            return;

        if (event.getPacketId() == PacketType.Play.Server.POSITION) {
            playerData.timerBL -= 50.0;
        }
    }
}
