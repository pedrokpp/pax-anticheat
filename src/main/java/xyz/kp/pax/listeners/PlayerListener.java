package xyz.kp.pax.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.kp.pax.cache.PlayerDataManager;

public class PlayerListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (PlayerDataManager.get(event.getPlayer()) == null)
            PlayerDataManager.bind(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (PlayerDataManager.get(event.getPlayer()) != null)
            PlayerDataManager.remove(event.getPlayer());
    }

}
