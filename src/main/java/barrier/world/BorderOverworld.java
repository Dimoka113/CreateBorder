package barrier.world;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.Server;
import java.util.List;



public class BorderOverworld implements CommandExecutor, TabCompleter {
    CreateBorder plugin;
    Config config;

    Logger logger;
    Server server;
    List<World> allWorld;


    public BorderOverworld(CreateBorder plugin, Config config, Logger logger) {
        this.plugin = plugin;
        this.config = config;
        this.logger = logger;
        this.server = plugin.getServer();
    }


    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("set");
            completions.add("add");
            completions.add("get");

        } else if (args.length == 2) {
            switch (args[1]) {
                case "set", "add" -> completions.add("<radius (blocks)>");
                case "get" -> completions.add("");
            }
        } else if (args.length == 3) {
            switch (args[1]) {
                case "set", "add" -> completions.add("<time (seconds)>");
                case "get" -> completions.add("");
            }
        }

        return completions;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        try {
            CreateBorder.set_new_brd_size(Double.parseDouble(args[0]));

            String name = config.getString("World_name");

            allWorld = server.getWorlds();

            for (World w : allWorld) {
                double w_add;

                if (w.getName().equals(name)) {
                    w_add = config.getDouble("World_Border_Overworld");


                }
                else if (w.getName().equals(name+"_nether")) {
                    w_add = config.getDouble("World_Border_Nether");
                }
                else if (w.getName().equals(name+"_the_end")) {
                    w_add = config.getDouble("World_Border_End");
                }

                else {
                    w_add = 1;
                    logger.info("Такого мира не существует");
                }


                WorldBorder border = w.getWorldBorder();
                // logger.info(String.valueOf(size));

                border.setSize(((Double.parseDouble(args[0])*2.0)*w_add)+1.0, Long.parseLong(args[1]));

            }

            sender.sendMessage(
                    ColorG.aqua("Размер мира будет изменён на ")
                            .append(ColorG.white(args[0]))
                            .append(ColorG.aqua(" бл. через "))
                            .append(ColorG.white(args[1]))
                            .append(ColorG.aqua(" сек."))
            );
        } catch (NullPointerException ignored) {}
        catch (Exception err) {
            logger.info(err.toString());
            sender.sendMessage(ColorG.red("Неправильное использование команды!"));
            sender.sendMessage(ColorG.yellow("Используйте '/border <float> (Размер барьера в радиусе) <long> (Время в секундах)'"));
        }



        return true;


    }
}
