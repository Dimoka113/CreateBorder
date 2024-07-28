package barrier.world;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.bukkit.entity.Player;

public class Border implements Listener {
    double block;


    public Logger logger;
    public Config config;
    public List<World> allWorld;

    public Server server;


    public Border(Logger logger, Config config, List<World> allWorld) {
        this.logger = logger;
        this.config = config;
        this.allWorld = allWorld;
        this.server = Bukkit.getServer();

    }


    @EventHandler
    public void onAdvancementDoneEvent(PlayerAdvancementDoneEvent e) {
        Player pla = e.getPlayer();
        String plname = pla.getName();

        long time = config.getLong("BorderSpeed");
        double coffees = config.getDouble("BorderCoff");
        boolean advancementss = config.getBoolean("advancement");
        Advancement adv = e.getAdvancement();
        if (advancementss) {


            try {
                String frame = Objects.requireNonNull(adv.getDisplay()).frame().toString();

                switch (frame) {
                    case "TASK" -> block = config.getDouble("BlockBolder_common");
                    case "GOAL" -> block = config.getDouble("BlockBolder_rare");
                    default -> block = config.getDouble("BlockBolder_epic");
                }
                String name = config.getString("World_name");


                if (CreateBorder.brd_size >= config.getDouble("limit.blocks")) {
                    pla.sendActionBar(config.getString("limit.actionbarmessage"));
                    return;
                }
                CreateBorder.brd_size = CreateBorder.brd_size + (block * coffees);

                for (World w : allWorld) {
                    double w_add;

                    if (w.getName().equals(name)) {
                        w_add = config.getDouble("World_Border_Overworld");


                    } else if (w.getName().equals(name + "_nether")) {
                        w_add = config.getDouble("World_Border_Nether");
                    } else if (w.getName().equals(name + "_the_end")) {
                        w_add = config.getDouble("World_Border_End");
                    } else {
                        w_add = 1;
                        logger.info("Такого мира не существует!");
                    }


                    WorldBorder border = w.getWorldBorder();
                    border.setSize(((CreateBorder.brd_size * 2.0) * w_add) + 1.0, time);
                }


                String plnamecr;
                String colorsize;
                String colorplus;

                for (Player pl : server.getOnlinePlayers()) {
                    double ww_add;

                    World w = pl.getWorld();

                    if (w.getName().equals(name)) {
                        plnamecr = config.getString("ActionBar.ColorPlayerOverworld");
                        colorsize = config.getString("ActionBar.ColorOverworld");
                        colorplus = config.getString("ActionBar.ColorAddOverworld");
                        ww_add = config.getDouble("World_Border_Overworld");
                    } else if (w.getName().equals(name + "_nether")) {
                        plnamecr = config.getString("ActionBar.ColorPlayerNether");
                        colorsize = config.getString("ActionBar.ColorNether");
                        colorplus = config.getString("ActionBar.ColorAddNether");
                        ww_add = config.getDouble("World_Border_Nether");
                    } else if (w.getName().equals(name + "_the_end")) {
                        plnamecr = config.getString("ActionBar.ColorPlayerTheEnd");
                        colorsize = config.getString("ActionBar.ColorTheEnd");
                        colorplus = config.getString("ActionBar.ColorAddTheEnd");
                        ww_add = config.getDouble("World_Border_End");
                    } else {
                        plnamecr = "";
                        colorsize = "";
                        colorplus = "";
                        ww_add = 1;
                        logger.info("Название мира неправильно настроено!");
                    }

                    if (config.getBoolean("ActionBar.enable")) {
                        pl.sendActionBar(Component.text(config.getString("ActionBar.Text")
                                .replace("{player}", config.getString("ActionBar.player")
                                        .replace("{colorPlayer}", plnamecr)
                                        .replace("{player}", plname)
                                )

                                .replace("{size}", config.getString("ActionBar.size")
                                        .replace("{colorWorld}", colorsize))
                                .replace("{size}", Double.toString(((ww_add * coffees) * CreateBorder.brd_size))
                                )

                                .replace("{plus}", config.getString("ActionBar.plus")
                                        .replace("{colorAddWorld}", colorplus)
                                        .replace("{plus}", Double.toString(((ww_add * coffees) * block))
                                        ))));
                    }


                }


            } catch (NullPointerException ignored) {
            }

        }
    }
}
