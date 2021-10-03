package xyz.kp.pax.checks;

import lombok.Getter;

@Getter
public enum Flags {
    AUTOCLICK("AutoClick", 80, 3, false, false),
    FASTPLACE("FastPlace", 50, 2, false, false),
    ;

    private final String name;
    private final int maxAlerts;
    private final int threshold;
    private final boolean testing;
    private final boolean autoban;
    Flags(String name, int maxAlerts, int threshold, boolean testing, boolean autoban) {
        this.name = name;
        this.maxAlerts = maxAlerts;
        this.threshold = threshold;
        this.testing = testing;
        this.autoban = autoban;
    }

}
