package barrier.world;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.event.player.PlayerAdvancementDoneEvent;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
// import org.bukkit.entity.Player;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.jetbrains.annotations.NotNull;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.Bukkit;

import java.util.List;
//import java.util.Objects;
//import java.util.Objects;
import java.util.logging.Logger;
//import org.bukkit.advancement.Advancement;
import org.bukkit.command.PluginCommand;



public final class CreateBorder extends JavaPlugin {
    public Server server;
    public PluginManager pm;
    public Config config;
    public static Double brd_size;
    public List<World> allWorld;
    public Logger logger = getLogger();
    public World world;

    public static void set_new_brd_size(Double brd_size){
        CreateBorder.brd_size = brd_size;
    }


    @Override
    public void onEnable() {
        config = new Config();
        config.create_config(this);
        server = this.getServer();
        pm = server.getPluginManager();
        allWorld = server.getWorlds();

        for (World w : allWorld) {
            world = w;
            WorldBorder border = w.getWorldBorder();

            brd_size = border.getSize();
            set_new_brd_size(brd_size);
            break;
        }

        pm.registerEvents(new Border(logger, config, allWorld), this);

        PluginCommand bordercommand = getCommand("border");
        bordercommand.setExecutor(new BorderOverworld(this, config, logger));
        bordercommand.setTabCompleter(new BorderOverworld(this, config, logger));


        bordercommand.setDescription("<radius> [times (in seconds)]");

        logger.info(ColorC.cyan ("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));
        logger.info(ColorC.blue ("  Запуск плагина на барьеры  "));
        logger.info(ColorC.blue ("  Название мира: '") + ColorC.cyan(world.getName()) + ColorC.blue ("'"));
        logger.info(ColorC.blue ("  Текущий размер барьера: ") + ColorC.cyan(brd_size.toString()) + ColorC.blue (" блоков"));
        logger.info(ColorC.cyan ("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));
    }

    @Override
    public void onDisable() {
        logger.info(ColorC.red       ("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));
        logger.info(ColorC.yellow    ("  Выключение плагина на барьеры  "));
        logger.info(ColorC.yellow ("  Текущий размер барьера: ") + ColorC.red (brd_size.toString()) + ColorC.yellow (" блоков"));
        logger.info(ColorC.red       ("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));

    }
}
