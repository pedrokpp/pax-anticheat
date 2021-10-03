package xyz.kp.pax.cache;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {

    private static final HashMap<UUID, PlayerData> pDataMap = new HashMap<>();

    public static void bind(Player player) {
        if (!pDataMap.containsKey(player.getUniqueId()))
            pDataMap.put(player.getUniqueId(), new PlayerData(player));
    }

    public static void remove(Player player) {
        pDataMap.remove(player.getUniqueId());
    }

    public static PlayerData get(Player player) {
        return pDataMap.get(player.getUniqueId());
    }

    public static PlayerData get(UUID uuid) {
        return pDataMap.get(uuid);
    }

}
