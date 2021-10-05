package xyz.kp.pax.checks.impl;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import org.bukkit.entity.Player;
import xyz.kp.pax.cache.PlayerData;
import xyz.kp.pax.cache.PlayerDataManager;
import xyz.kp.pax.checks.Core;
import xyz.kp.pax.checks.Flags;

public class AutoClick extends PacketListenerAbstract {

    public AutoClick() {
        super(PacketListenerPriority.LOW);
    }

    private final Flags flag = Flags.AUTOCLICK;

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player);
        if (playerData == null) {
            return;
        }

        if (event.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            WrappedPacketInUseEntity wrappedPacketInUseEntity = new WrappedPacketInUseEntity(event.getNMSPacket());
            WrappedPacketInUseEntity.EntityUseAction action = wrappedPacketInUseEntity.getAction();
            if (action == WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                if (playerData.ac < 15) {
                    playerData.ac++;
                } else {
                    double delay = (playerData.acT * 49.5) / 1000;
                    float cps = (float) (15.0 / delay);

                    if (cps >= 15) {
                        playerData.acVL++;

                        if ((playerData.acVL % flag.getThreshold() == 0) &&
                                playerData.acVL >= flag.getThreshold()) {
                            Core.alert(player, flag, playerData.acVL / flag.getThreshold(),
                                    String.format("%.2f", cps) + " CPS");
                        }
                    }

                    playerData.ac = 0;
                    playerData.acT = 0;
                }
            }
        } else if (PacketType.Play.Client.Util.isInstanceOfFlying(event.getPacketId())) {
            playerData.acT++;
        }
    }

}
