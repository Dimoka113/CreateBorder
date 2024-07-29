package barrier.world;


import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;

public class Config {
    public static FileConfiguration cnf;

    public static void reload_config(CreateBorder plugin){
        plugin.reloadConfig();
        cnf = plugin.getConfig();

    }
    public Integer getInt(String text) {
        return cnf.getInt(text);
    }

    public Boolean getBoolean(String text) {
        return cnf.getBoolean(text);
    }

    public double getDouble(String text) {
        return cnf.getDouble(text);
    }

    public long getLong(String text) {
        return cnf.getLong(text);
    }

    public String getString(String text) {
        return cnf.getString(text);
    }


    public void create_config(CreateBorder plugin) {
        cnf = plugin.getConfig();
// Основные настройки
        cnf.addDefault("advancement", true);
        cnf.setComments("advancement", Arrays.asList(
                "# on/off expansion of the barrier for achievements",
                "# вкл/выкл расширения барьера за ачивки"
        ));

        cnf.addDefault("BorderSpeed", 1);
        cnf.setComments("BorderSpeed", Arrays.asList(
                "# The speed at which the barrier will expand for completing one achievement",
                "# Cкорость с которой барьер будет расширяться за выполнение одной ачивки"
        ));

        cnf.addDefault("BorderCoff", 2.0);
        cnf.setComments("BorderCoff", Arrays.asList(
                "# Barrier increase size factor (default 2 (1 block))",
                "# Коэффициент размера увеличения барьера (по умолчанию 2 (1 блок))"
        ));

        cnf.addDefault("BlockBolder_common", 1.0);
        cnf.setComments("BlockBolder_common", Arrays.asList(
                "# Size by how much the barrier will increase for completing one “achievement”",
                "# Размер на сколько увеличится барьер за выполнение одного \"достижения\""
        ));

        cnf.addDefault("BlockBolder_rare", 4.0);
        cnf.setComments("BlockBolder_rare", Arrays.asList(
                "# The size of how much the barrier to completing one “goal” increases",
                "# Размер на сколько увеличится барьер за выполнение одной \"цели\""
        ));

        cnf.addDefault("BlockBolder_epic", 16.0);
        cnf.setComments("BlockBolder_epic", Arrays.asList(
                "# Size by how much the barrier will increase for completing one “trial”",
                "# Размер на сколько увеличится барьер за выполнение одного \"испытания\""
        ));

        cnf.addDefault("World_name", "world");
        cnf.setComments("World_name", Arrays.asList(
                "# Names of your world with a barrier (needed for multiworld)",
                "# Названия вашего мира с барьером (нужно для мультиворлда)"
        ));

        cnf.addDefault("World_Border_Overworld", 1.0);
        cnf.setComments("World_Border_Overworld", Arrays.asList(
                "# Size by how much the barrier will increase in overworld",
                "# Размер на сколько увеличится барьер в обычном мире"
        ));

        cnf.addDefault("World_Border_Nether", 0.125);
        cnf.setComments("World_Border_Nether", Arrays.asList(
                "# Size by how much the barrier will increase in the_nether",
                "# Размер на сколько увеличится барьер в аду"
        ));

        cnf.addDefault("World_Border_End", 3.0);
        cnf.setComments("World_Border_End", Arrays.asList(
                "# Size by how much the barrier will increase in the_end",
                "# Размер на сколько увеличится барьер в энде"
        ));

        cnf.addDefault("Wolrd_Border_Other", 1.0);
        cnf.setComments("Wolrd_Border_Other", Arrays.asList(
                "# Size by how much the barrier will increase in other worlds (only if there is a multi-world)",
                "# Размер на сколько увеличится барьер в остальных мирах (только если есть мульти ворлд)"
        ));

// Настройки лимита
        cnf.addDefault("limit.enable", true);
        cnf.setComments("limit.enable", Arrays.asList(
                "# Enable limit on barrier expansion",
                "# Включить ограничение на расширение барьера"
        ));

        cnf.addDefault("limit.blocks", 10000);
        cnf.setComments("limit.blocks", Arrays.asList(
                "# Number of blocks until the barrier will expand (radius). Upon reaching this distance it will display \"actionbarmessage\"",
                "# Кол-во блоков до какого момента барьер будет расширяться (радиус). По достижению этого расстояния будет выведено \"actionbarmessage\""
        ));

        cnf.addDefault("limit.actionbarmessage", "§4Достигнут лимит размера барьера...§r");
        cnf.setComments("limit.actionbarmessage", Arrays.asList(
                "# Messages that the player will see if the maximum barrier size has been reached",
                "# Сообщения, которое увидит игрок, если максимальный размер барьера достигнут"
        ));

// Настройки ActionBar
        cnf.addDefault("ActionBar.enable", true);
        cnf.setComments("ActionBar.enable", Arrays.asList(
                "# Enable displays above players' inventory if someone has completed an achievement and increased the barrier",
                "# Включить отображение над инвентарём игроков, если кто-то выполнил ачивку и увеличил барьер"
        ));

        cnf.addDefault("ActionBar.Text", "Мир увеличен {player}: +{plus} (Размер мира {size})");
        cnf.setComments("ActionBar.Text", Arrays.asList(
                "# Message that will be displayed to players There is a {player} {size} {plus} to display",
                "# Сообщение, которое будет выводиться игрокам: Есть {player} {size} {plus}, для отображения"
        ));

        cnf.addDefault("ActionBar.ColorPlayerOverworld", "§b");
        cnf.addDefault("ActionBar.ColorPlayerNether", "§b");
        cnf.addDefault("ActionBar.ColorPlayerTheEnd", "§b");
        cnf.addDefault("ActionBar.ColorPlayerOther", "");
        cnf.setComments("ActionBar.ColorPlayerOverworld", Arrays.asList(
                "# Color settings for each world (player nickname)",
                "# Настройка цвета для каждого мира (ник игрока)"
        ));

        cnf.addDefault("ActionBar.ColorOverworld", "§2");
        cnf.addDefault("ActionBar.ColorNether", "§4");
        cnf.addDefault("ActionBar.ColorTheEnd", "§5");
        cnf.addDefault("ActionBar.ColorOther", "");
        cnf.setComments("ActionBar.ColorOverworld", Arrays.asList(
                "# Color setting for each world (barrier size)",
                "# Настройка цвета для каждого мира (размер барьера)"
        ));

        cnf.addDefault("ActionBar.ColorAddOverworld", "§a");
        cnf.addDefault("ActionBar.ColorAddNether", "§c");
        cnf.addDefault("ActionBar.ColorAddTheEnd", "§d");
        cnf.addDefault("ActionBar.ColorAddOther", "");
        cnf.setComments("ActionBar.ColorAddOverworld", Arrays.asList(
                "# Color setting for each world (by how much the barrier is increased)",
                "# Настройка цвета для каждого мира (на сколько увеличен барьер)"
        ));

        cnf.addDefault("ActionBar.player", "{colorPlayer}{player}§r");
        cnf.setComments("ActionBar.player", Arrays.asList(
                "# Player display template",
                "# Шаблон отображения игрока"
        ));

        cnf.addDefault("ActionBar.size", "{colorWorld}{size}§r");
        cnf.setComments("ActionBar.size", Arrays.asList(
                "# World size display template",
                "# Шаблон отображения размера мира"
        ));

        cnf.addDefault("ActionBar.plus", "{colorAddWorld}{plus}§r");
        cnf.setComments("ActionBar.plus", Arrays.asList(
                "# Template for displaying the number of blocks to increase",
                "# Шаблон отображения кол-во блоков для увеличения"
        ));

        cnf.addDefault("ActionBar.Colors", "§");
        // Поддерживаемые цвета
        cnf.setComments("ActionBar.Colors", Arrays.asList(
                "# currently supported colors:",
                "# цвета которые поддерживаются в данный момент:",
                "#Colors:",
                "#  black: §0",
                "#  dark_green: §2",
                "#  dark_red: §4",
                "#  gold: §6",
                "#  dark_gray: §8",
                "#  green: §a",
                "#  red: §c",
                "#  yellow: §e",
                "#  dark_blue: §1",
                "#  dark_aqua: §3",
                "#  dark_purple: §5",
                "#  gray: §7",
                "#  blue: §9",
                "#  aqua: §b",
                "#  purple: §d",
                "#  white: §f",
                "#  clear: §r",
                "https://www.reddit.com/media?url=https%3A%2F%2Fpreview.redd.it%2Fyldfhnb4xp411.png%3Fauto%3Dwebp%26s%3D747bcfe3d9db9aba2b0bfecacf8299619f8eca38"
        ));


        cnf.options().copyDefaults(true);
        plugin.saveDefaultConfig();
        plugin.saveConfig();


    }



}