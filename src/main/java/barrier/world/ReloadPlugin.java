package barrier.world;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static barrier.world.Config.reload_config;

public class ReloadPlugin implements CommandExecutor, TabCompleter {

    public CreateBorder plugin;

    public ReloadPlugin (CreateBorder plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        completions.add("");
        return completions;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cЭту команду могут использовать только игроки.");
            return true;
        }

        if (!player.hasPermission("createborder.command.reloadborder")) {
            player.sendMessage("§cУ вас нет прав на использование этой команды.");
            return true;
        }

        player.sendMessage(ColorG.red("Перезагрузка плагина таким образом не рекомендуется. Рекомендуется перезапустить сервер."));



        PluginManager pluginManager = Bukkit.getPluginManager();
        Plugin pluginget = pluginManager.getPlugin("CreateBorder");
        if (pluginget == null) {
            player.sendMessage("§cПлагин не найден.");
            return true;
        }
        pluginManager.disablePlugin(pluginget);

        JavaPlugin javaPlugin = (JavaPlugin) pluginget;
        javaPlugin.reloadConfig();
        reload_config(plugin);

        pluginManager.enablePlugin(pluginget);



        player.sendMessage("§aПлагин успешно перезагружен.");
        return true;
    }
}


