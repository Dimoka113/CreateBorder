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
import java.math.BigDecimal;
import java.math.RoundingMode;


import static barrier.world.CreateBorder.Radius_Size_Border;
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
        boolean advancementss = config.getBoolean("advancement");
        Advancement adv = e.getAdvancement();

        if (advancementss) {
            double brd_size = 0.0;
            Player pla = e.getPlayer();
            String plname = pla.getName();
            List<World> allWorld = pla.getServer().getWorlds();
            long time = config.getLong("BorderSpeed");
            double coffees = config.getDouble("BorderCoff");

            for (World w : allWorld) {
                WorldBorder border = w.getWorldBorder();
                brd_size = Radius_Size_Border(border.getSize());
                break;
            }



            try {
                String frame = Objects.requireNonNull(adv.getDisplay()).frame().toString();

                switch (frame) {
                    case "TASK" -> block = config.getDouble("BlockBolder_common");
                    case "GOAL" -> block = config.getDouble("BlockBolder_rare");
                    default -> block = config.getDouble("BlockBolder_epic");
                }
                String name = config.getString("World_name");

                if (config.getBoolean("limit.enable")) {
                    if (brd_size >= config.getDouble("limit.blocks")) {
                        pla.sendActionBar(config.getString("limit.actionbarmessage"));
                        return;
                    }
                }
                for (World w : allWorld) {
                    double w_add;

                    if (w.getName().equals(name)) {
                        w_add = config.getDouble("World_Border_Overworld");

                    } else if (w.getName().equals(name + "_nether")) {
                        w_add = config.getDouble("World_Border_Nether");
                    } else if (w.getName().equals(name + "_the_end")) {
                        w_add = config.getDouble("World_Border_End");
                    } else {
                        w_add = config.getDouble("Wolrd_Border_Other");
                    }


                    WorldBorder border = w.getWorldBorder();
                    brd_size = border.getSize();
                    double newSize = brd_size + (w_add * coffees * block);
                    newSize = round(newSize, config.getInt("shortening"));
                    border.setSize(newSize, time);
                }


                String plnamecr;
                String colorsize;
                String colorplus;

                for (Player pl : server.getOnlinePlayers()) {
                    double ww_add;

                    World w = pl.getWorld();
                    WorldBorder border = w.getWorldBorder();
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
                        plnamecr = config.getString("ActionBar.ColorPlayerOther");
                        colorsize = config.getString("ActionBar.ColorOther");
                        colorplus = config.getString("ActionBar.ColorAddOther");
                        ww_add = config.getDouble("Wolrd_Border_Other");
                    }

                    if (config.getBoolean("ActionBar.enable")) {
                        pl.sendActionBar(Component.text(config.getString("ActionBar.Text")
                                .replace("{player}", config.getString("ActionBar.player")
                                        .replace("{colorPlayer}", plnamecr)
                                        .replace("{player}", plname)
                                )

                                .replace("{size}", config.getString("ActionBar.size")
                                        .replace("{colorWorld}", colorsize))
                                .replace("{size}", Double.toString(Radius_Size_Border(border.getSize()))
                                )

                                .replace("{plus}", config.getString("ActionBar.plus")
                                        .replace("{colorAddWorld}", colorplus)
                                        .replace("{plus}", Double.toString(ww_add * coffees * block)
                                        ))));
                    }


                }


            } catch (NullPointerException ignored) {
            }

        }
    }
    // Метод для округления числа до указанного количества знаков после запятой
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
