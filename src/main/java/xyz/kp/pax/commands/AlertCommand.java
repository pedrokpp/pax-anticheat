package xyz.kp.pax.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.kp.pax.cache.PlayerData;
import xyz.kp.pax.cache.PlayerDataManager;

public class AlertCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas players podem executar esse comando.");
            return true;
        }
        Player player = (Player) sender;
        PlayerData playerData = PlayerDataManager.get(player);
        if (playerData == null) {
            return true;
        }
        if (command.getName().equalsIgnoreCase("alerts")) {
            if (!player.hasPermission("pax.alerts")) {
                player.sendMessage("§5§lPAX: §fVocê §cnão §ftem permissão para executar esse comando.");
            } else {
                player.sendMessage("§5§lPAX: §fVocê " + (playerData.alerts ? "§cdesativou" : "§aativou") +
                        " §fos alertas.");
                playerData.alerts = !playerData.alerts;
            }
        }
        return false;
    }

}
