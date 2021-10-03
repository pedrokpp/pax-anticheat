package xyz.kp.pax.checks.impl;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import xyz.kp.pax.checks.Flags;

public class Velocity extends PacketListenerAbstract {

    public Velocity() {
        super(PacketListenerPriority.LOW);
    }



}
