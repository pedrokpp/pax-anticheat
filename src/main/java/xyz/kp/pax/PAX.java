package xyz.kp.pax;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.settings.PacketEventsSettings;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.kp.pax.checks.impl.AutoClick;
import xyz.kp.pax.checks.impl.FastPlace;
import xyz.kp.pax.commands.AlertCommand;
import xyz.kp.pax.listeners.PlayerListener;
import xyz.kp.pax.listeners.UpdateListener;

public final class PAX extends JavaPlugin {

    @Override
    public void onLoad() {
        PacketEvents.create(this);
        PacketEventsSettings settings = PacketEvents.get().getSettings();
        settings
                .fallbackServerVersion(ServerVersion.v_1_8_8)
                .compatInjector(false)
                .checkForUpdates(false)
                .bStats(true);
        PacketEvents.get().loadAsyncNewThread();
    }

    @Override
    public void onEnable() {
        registerCommands();
        registerPacketListeners();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getConsoleSender().sendMessage("§a[PAX] AntiCheat habilitado.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§c[PAX] AntiCheat desabilitado.");
        PacketEvents.get().terminate();
    }

    private void registerCommands() {
        getCommand("alerts").setExecutor(new AlertCommand());
    }

    private void registerPacketListeners() {
        PacketEvents.get().registerListener(new UpdateListener());
        PacketEvents.get().registerListener(new AutoClick());
        PacketEvents.get().registerListener(new FastPlace());
        PacketEvents.get().init();
    }

}
