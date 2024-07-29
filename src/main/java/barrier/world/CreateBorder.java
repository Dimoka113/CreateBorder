package barrier.world;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.command.PluginCommand;


public final class CreateBorder extends JavaPlugin {
    public Server server;
    public PluginManager pm;
    public Config config;
    public List<World> allWorld;
    public Logger logger = getLogger();
    public World world;
    
    public static Double Radius_Size_Border(Double rr) {
        return ( ( rr - 1 ) / 2 ) ;
    }

    public static Double Diametr_Size_Border(Double rr) {
        return 1 + ( 2 * rr ) ;
    }

    public static Double NewRadius_Size_Border(Double rr, Double rw) {
        return (rr-1) / 2 * rw;
    }
    public static Double NewDiametr_Size_Border(Double rr, Double rw) {
        return 1 + ( 2 * rr * rw) ;
    }


    @Override
    public void onEnable() {
        Double brd_size = 0.0;
        config = new Config();
        config.create_config(this);
        server = this.getServer();
        pm = server.getPluginManager();
        allWorld = server.getWorlds();

        for (World w : allWorld) {
            world = w;
            WorldBorder border = w.getWorldBorder();
            brd_size = Radius_Size_Border(border.getSize());
            break;
        }

        pm.registerEvents(new Border(logger, config, allWorld), this);

        PluginCommand bordercmd = getCommand("border");
        bordercmd.setExecutor(new BorderCommand(this, config, logger));
        bordercmd.setTabCompleter(new BorderCommand(this, config, logger));

        PluginCommand reloadcmd = getCommand("reloadborder");
        reloadcmd.setExecutor(new ReloadPlugin(this));
        reloadcmd.setTabCompleter(new ReloadPlugin(this));

        logger.info(ColorC.cyan ("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));
        logger.info(ColorC.blue ("  Запуск плагина на барьеры  "));
        logger.info(ColorC.blue ("  Название мира: '") +
                ColorC.cyan(world.getName()) + ColorC.blue ("'"));
        logger.info(ColorC.blue ("  Текущий размер барьера: ") +
                ColorC.cyan(String.valueOf(brd_size)) +
                ColorC.blue (" блоков"));
        logger.info(ColorC.cyan ("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));
    }

    @Override
    public void onDisable() {
        Double brd_size = 0.0;
        for (World w : allWorld) {
            world = w;
            WorldBorder border = w.getWorldBorder();
            brd_size = Radius_Size_Border(border.getSize());
            break;
        }

        logger.info(ColorC.red       ("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));
        logger.info(ColorC.yellow    ("  Выключение плагина на барьеры  "));
        logger.info(
           ColorC.yellow ("  Текущий размер барьера: ") +
                ColorC.red (String.valueOf(brd_size)) +
                ColorC.yellow (" блоков"));
        logger.info(ColorC.red       ("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));

    }
}
