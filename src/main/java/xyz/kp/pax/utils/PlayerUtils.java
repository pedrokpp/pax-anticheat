package xyz.kp.pax.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static int getPlayerPing(Player player) {
        return ((CraftPlayer)player).getHandle().ping;
    }

}
