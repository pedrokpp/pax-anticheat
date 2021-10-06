package xyz.kp.pax.utils;

import org.bukkit.Location;
import org.bukkit.Material;

public class CheckUtils {

    public static boolean isNearGround(Location location) {
        double expand = 0.3;
        for (double x = -expand; x <= expand; x += expand) {
            for (double z = -expand; z <= expand; z += expand) {
                if (location.clone().add(x, -0.5001, z).getBlock().getType() != Material.AIR)
                    return true;
            }
        }
        return false;
    }

}
