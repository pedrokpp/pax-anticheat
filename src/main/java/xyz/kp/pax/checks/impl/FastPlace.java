package xyz.kp.pax.checks.impl;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import org.bukkit.entity.Player;
import xyz.kp.pax.cache.PlayerData;
import xyz.kp.pax.cache.PlayerDataManager;
import xyz.kp.pax.checks.Core;
import xyz.kp.pax.checks.Flags;

public class FastPlace extends PacketListenerAbstract {

    public FastPlace() {
        super(PacketListenerPriority.LOW);
    }

    private final Flags flag = Flags.FASTPLACE;

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player);
        if (playerData == null) {
            return;
        }

        if (event.getPacketId() == PacketType.Play.Client.BLOCK_PLACE) {
            if (playerData.fp < 15) {
                playerData.fp++;
            } else {
                double delay = (playerData.fpT * 49.5) / 1000;
                float bps = (float) (15.0 / delay);

                if (bps >= 15) {
                    playerData.fpF++;

                    if ((playerData.fpF % flag.getThreshold() == 0) &&
                            playerData.fpF >= flag.getThreshold()) {
                        Core.alert(player, flag, playerData.fpF / flag.getThreshold(),
                                String.format("%.2f", bps) + " BPS");
                    }
                }

                playerData.fp = 0;
                playerData.fpT = 0;
            }

        } else if (PacketType.Play.Client.Util.isInstanceOfFlying(event.getPacketId())) {
            playerData.fpT++;
        }
    }

}
