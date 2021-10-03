package xyz.kp.pax.listeners;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;
import org.bukkit.entity.Player;
import xyz.kp.pax.cache.PlayerData;
import xyz.kp.pax.cache.PlayerDataManager;

public class UpdateListener extends PacketListenerAbstract {

    public UpdateListener() {
        super(PacketListenerPriority.HIGH);
    }

//    private int counter = 0;
//    private long lastMS = System.currentTimeMillis();

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player);
        if (playerData == null) {
            return;
        }

//        if (PacketType.Play.Client.Util.isInstanceOfFlying(event.getPacketId())) {
//            ++counter;
//            long nowMS = System.currentTimeMillis();
//            Bukkit.getLogger().info("timing packet!! (" + (nowMS - lastMS) + " ms)  #" + counter);
//            lastMS = nowMS;
//        }

        if (event.getPacketId() == PacketType.Play.Client.ENTITY_ACTION) {
            WrappedPacketInEntityAction packet = new WrappedPacketInEntityAction(event.getNMSPacket());
            switch (packet.getAction()) {
                case START_SPRINTING:
                    playerData.setSprinting(true);
                    break;
                case STOP_SPRINTING:
                    playerData.setSprinting(false);
                    break;
                case START_SNEAKING:
                    playerData.setSneaking(true);
                    break;
                case STOP_SNEAKING:
                    playerData.setSneaking(false);
                    break;
            }
        }
    }
}
