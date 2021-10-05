package xyz.kp.pax.cache;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private final Player player;
    private final UUID uuid;

    private boolean sneaking = false;
    private boolean sprinting = false;

    public boolean alerts = true;

    public int ac = 0;
    public int acT = 0;
    public int acVL = 0;

    public int fp = 0;
    public int fpT = 0;
    public int fpVL = 0;

    public long timerLT = 0L;
    public double timerBL = 0L;
    public int timerVL = 0;

    public PlayerData(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public boolean isSneaking() {
        return sneaking;
    }

    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;
    }

    public boolean isSprinting() {
        return sprinting;
    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }
}
