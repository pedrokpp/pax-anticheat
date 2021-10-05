package xyz.kp.pax.checks;

import lombok.Getter;

@Getter
public enum Flags {
    AUTOCLICK("AutoClick", 80, 3, false, false, true),
    FASTPLACE("FastPlace", 50, 2, false, false, true),
    TIMER("Timer", 30, 5
            , true, false, true),
    ;

    private final String name;
    private final int maxAlerts;
    private final int threshold;
    private final boolean testing;
    private final boolean autoban;
    private final boolean enabled;
    Flags(String name, int maxAlerts, int threshold, boolean testing, boolean autoban, boolean enabled) {
        this.name = name;
        this.maxAlerts = maxAlerts;
        this.threshold = threshold;
        this.testing = testing;
        this.autoban = autoban;
        this.enabled = enabled;
    }

}
