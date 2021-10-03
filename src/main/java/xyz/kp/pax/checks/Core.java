package xyz.kp.pax.checks;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.kp.pax.cache.PlayerData;
import xyz.kp.pax.cache.PlayerDataManager;
import xyz.kp.pax.utils.PlayerUtils;

public class Core {

    public static void alert(Player p, Flags flag, int alertCount, String lastFlag) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            PlayerData playerData = PlayerDataManager.get(player);
            if (playerData != null) {
                if (player.hasPermission("pax.alerts") && playerData.alerts) {
                    TextComponent component = new TextComponent("§5§lPAX: §e" + p.getName() + " §7[" + PlayerUtils.getPlayerPing(player) +
                            "ms] §ffalhou no teste de §e" +
                            flag.getName() + " §7[" + alertCount + "x]");
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eMais informações:\n" +
                            "\n§7Pode tomar banimento automático? " + (flag.isAutoban() ? "§aSim" : "§cNão") +
                            "\n§7Status do check: " + (flag.isTesting() ? "§cTESTES" : "§aNORMAL") +
                            "\n§7Flag: §c" + lastFlag.replace(",", "."))
                            .create()));
                    player.sendMessage(component);
                }
            }
        });
    }

}
