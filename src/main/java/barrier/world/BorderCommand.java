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

import static barrier.world.CreateBorder.*;


public class BorderCommand implements CommandExecutor, TabCompleter {
    CreateBorder plugin;
    Config config;

    Logger logger;
    Server server;
    List<World> allWorld;


    public BorderCommand(CreateBorder plugin, Config config, Logger logger) {
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
            completions.add("help");

        } else if (args.length == 2) {
            switch (args[0]) {
                case "set", "add" -> completions.add("<radius (blocks)>");
                case "get" -> completions.add("");
            }
        } else if (args.length == 3) {
            switch (args[0]) {
                case "set", "add" -> completions.add("<time (seconds)>");
                case "get" -> completions.add("");
            }
        }

        return completions;
    }

    public void helpMessage (@NotNull CommandSender sender) {
        sender.sendMessage(ColorG.yellow("����������� ��� ������� ����� �������:"));
        sender.sendMessage(ColorG.yellow("/border set <float> (������ ������� � �������) <long> (����� � ��������)"));
        sender.sendMessage(ColorG.orange("����� ���������� ������ �� �������� ���� �����������."));
        sender.sendMessage(ColorG.yellow("/border add <float> (������ ������� � �������) <long> (����� � ��������)"));
        sender.sendMessage(ColorG.orange("����� ��������� ������ �������"));
        sender.sendMessage(ColorG.yellow("/border get"));
        sender.sendMessage(ColorG.orange("����� ���������� ������� ������ �������"));
        sender.sendMessage(ColorG.yellow("/border help"));
        sender.sendMessage(ColorG.orange("������� ��� ���������."));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try { if (args.length == 0) {helpMessage(sender);return true;}} catch (Exception ignore) {helpMessage(sender);return true;}

        if (args.length == 1 && args[0].equals("get")) {
                List<World> allWorld = sender.getServer().getWorlds();

                for (World w : allWorld) {
                    double size = w.getWorldBorder().getSize();
                    sender.sendMessage(ColorG.blue("������� ������� �����:    "));
                    sender.sendMessage(ColorG.blue("���� ").append(
                    ColorG.white(String.valueOf(NewRadius_Size_Border(size,config.getDouble("World_Border_Overworld")))))
                            .append(ColorG.blue(" ����(��)")));
                    sender.sendMessage(ColorG.blue("�� ").append(
                    ColorG.white(String.valueOf(NewRadius_Size_Border(size,config.getDouble("World_Border_Nether")))))
                            .append(ColorG.blue(" ����(��)")));
                    sender.sendMessage(ColorG.blue("��� ").append(
                    ColorG.white(String.valueOf(NewRadius_Size_Border(size,config.getDouble("World_Border_End")))))
                            .append(ColorG.blue(" ����(��)")));



                    return true;

                }

        }

        else if (args.length == 1 && args[0].equals("help"))  { helpMessage(sender); return true;}


        else if (args.length == 2) { helpMessage(sender); return true;}
        else {
            if (args[0].equals("set")) {
                try {
                    String name = config.getString("World_name");
                    allWorld = server.getWorlds();
                    for (World w : allWorld) {
                        double w_add;
                        if (w.getName().equals(name)) {w_add = config.getDouble("World_Border_Overworld");}
                        else if (w.getName().equals(name + "_nether")) {w_add = config.getDouble("World_Border_Nether");}
                        else if (w.getName().equals(name + "_the_end")) {w_add = config.getDouble("World_Border_End");}
                        else { w_add = config.getDouble("Wolrd_Border_Other"); }
                        WorldBorder border = w.getWorldBorder();
                        border.setSize(NewDiametr_Size_Border(Double.parseDouble(args[1]), w_add), Long.parseLong(args[2]));
                    }
                    sender.sendMessage(ColorG.aqua("������ ���� ����� ������ �� ")
                                    .append(ColorG.white(args[1]))
                                    .append(ColorG.aqua(" ��. ����� "))
                                    .append(ColorG.white(args[2]))
                                    .append(ColorG.aqua(" ���.")));
                } catch (NullPointerException ignored) {} catch (Exception ignored) { helpMessage(sender); return true;}
            } else if (args[0].equals("add")) {
                try {
                    String name = config.getString("World_name");
                    allWorld = server.getWorlds();
                    for (World w : allWorld) {
                        double w_add;
                        if (w.getName().equals(name)) {w_add = config.getDouble("World_Border_Overworld");}
                        else if (w.getName().equals(name + "_nether")) {w_add = config.getDouble("World_Border_Nether");}
                        else if (w.getName().equals(name + "_the_end")) {w_add = config.getDouble("World_Border_End");}
                        else { w_add = config.getDouble("Wolrd_Border_Other"); }
                        WorldBorder border = w.getWorldBorder();
                        double size = border.getSize();
                        border.setSize(size + (Double.parseDouble(args[1]) * 2.0 * w_add), Long.parseLong(args[2]));


                    }
                    sender.sendMessage(ColorG.aqua("������ ���� ����� �������� �� ")
                            .append(ColorG.white(args[1]))
                            .append(ColorG.aqua(" ��. ����� "))
                            .append(ColorG.white(args[2]))
                            .append(ColorG.aqua(" ���.")));
                } catch (NullPointerException ignored) {} catch (Exception ignored) { helpMessage(sender); return true;}
            }
            return true;
        }
        return true;
    }
}
