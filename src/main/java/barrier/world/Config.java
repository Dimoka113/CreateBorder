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
// �������� ���������
        cnf.addDefault("advancement", true);
        cnf.setComments("advancement", Arrays.asList(
                "# on/off expansion of the barrier for achievements",
                "# ���/���� ���������� ������� �� ������"
        ));

        cnf.addDefault("BorderSpeed", 1);
        cnf.setComments("BorderSpeed", Arrays.asList(
                "# The speed at which the barrier will expand for completing one achievement",
                "# C������� � ������� ������ ����� ����������� �� ���������� ����� ������"
        ));

        cnf.addDefault("BorderCoff", 2.0);
        cnf.setComments("BorderCoff", Arrays.asList(
                "# Barrier increase size factor (default 2 (1 block))",
                "# ����������� ������� ���������� ������� (�� ��������� 2 (1 ����))"
        ));

        cnf.addDefault("BlockBolder_common", 1.0);
        cnf.setComments("BlockBolder_common", Arrays.asList(
                "# Size by how much the barrier will increase for completing one �achievement�",
                "# ������ �� ������� ���������� ������ �� ���������� ������ \"����������\""
        ));

        cnf.addDefault("BlockBolder_rare", 4.0);
        cnf.setComments("BlockBolder_rare", Arrays.asList(
                "# The size of how much the barrier to completing one �goal� increases",
                "# ������ �� ������� ���������� ������ �� ���������� ����� \"����\""
        ));

        cnf.addDefault("BlockBolder_epic", 16.0);
        cnf.setComments("BlockBolder_epic", Arrays.asList(
                "# Size by how much the barrier will increase for completing one �trial�",
                "# ������ �� ������� ���������� ������ �� ���������� ������ \"���������\""
        ));

        cnf.addDefault("World_name", "world");
        cnf.setComments("World_name", Arrays.asList(
                "# Names of your world with a barrier (needed for multiworld)",
                "# �������� ������ ���� � �������� (����� ��� ������������)"
        ));

        cnf.addDefault("World_Border_Overworld", 1.0);
        cnf.setComments("World_Border_Overworld", Arrays.asList(
                "# Size by how much the barrier will increase in overworld",
                "# ������ �� ������� ���������� ������ � ������� ����"
        ));

        cnf.addDefault("World_Border_Nether", 0.125);
        cnf.setComments("World_Border_Nether", Arrays.asList(
                "# Size by how much the barrier will increase in the_nether",
                "# ������ �� ������� ���������� ������ � ���"
        ));

        cnf.addDefault("World_Border_End", 3.0);
        cnf.setComments("World_Border_End", Arrays.asList(
                "# Size by how much the barrier will increase in the_end",
                "# ������ �� ������� ���������� ������ � ����"
        ));

        cnf.addDefault("Wolrd_Border_Other", 1.0);
        cnf.setComments("Wolrd_Border_Other", Arrays.asList(
                "# Size by how much the barrier will increase in other worlds (only if there is a multi-world)",
                "# ������ �� ������� ���������� ������ � ��������� ����� (������ ���� ���� ������ �����)"
        ));

// ��������� ������
        cnf.addDefault("limit.enable", true);
        cnf.setComments("limit.enable", Arrays.asList(
                "# Enable limit on barrier expansion",
                "# �������� ����������� �� ���������� �������"
        ));

        cnf.addDefault("limit.blocks", 10000);
        cnf.setComments("limit.blocks", Arrays.asList(
                "# Number of blocks until the barrier will expand (radius). Upon reaching this distance it will display \"actionbarmessage\"",
                "# ���-�� ������ �� ������ ������� ������ ����� ����������� (������). �� ���������� ����� ���������� ����� �������� \"actionbarmessage\""
        ));

        cnf.addDefault("limit.actionbarmessage", "�4��������� ����� ������� �������...�r");
        cnf.setComments("limit.actionbarmessage", Arrays.asList(
                "# Messages that the player will see if the maximum barrier size has been reached",
                "# ���������, ������� ������ �����, ���� ������������ ������ ������� ���������"
        ));

// ��������� ActionBar
        cnf.addDefault("ActionBar.enable", true);
        cnf.setComments("ActionBar.enable", Arrays.asList(
                "# Enable displays above players' inventory if someone has completed an achievement and increased the barrier",
                "# �������� ����������� ��� ��������� �������, ���� ���-�� �������� ������ � �������� ������"
        ));

        cnf.addDefault("ActionBar.Text", "��� �������� {player}: +{plus} (������ ���� {size})");
        cnf.setComments("ActionBar.Text", Arrays.asList(
                "# Message that will be displayed to players There is a {player} {size} {plus} to display",
                "# ���������, ������� ����� ���������� �������: ���� {player} {size} {plus}, ��� �����������"
        ));

        cnf.addDefault("ActionBar.ColorPlayerOverworld", "�b");
        cnf.addDefault("ActionBar.ColorPlayerNether", "�b");
        cnf.addDefault("ActionBar.ColorPlayerTheEnd", "�b");
        cnf.addDefault("ActionBar.ColorPlayerOther", "");
        cnf.setComments("ActionBar.ColorPlayerOverworld", Arrays.asList(
                "# Color settings for each world (player nickname)",
                "# ��������� ����� ��� ������� ���� (��� ������)"
        ));

        cnf.addDefault("ActionBar.ColorOverworld", "�2");
        cnf.addDefault("ActionBar.ColorNether", "�4");
        cnf.addDefault("ActionBar.ColorTheEnd", "�5");
        cnf.addDefault("ActionBar.ColorOther", "");
        cnf.setComments("ActionBar.ColorOverworld", Arrays.asList(
                "# Color setting for each world (barrier size)",
                "# ��������� ����� ��� ������� ���� (������ �������)"
        ));

        cnf.addDefault("ActionBar.ColorAddOverworld", "�a");
        cnf.addDefault("ActionBar.ColorAddNether", "�c");
        cnf.addDefault("ActionBar.ColorAddTheEnd", "�d");
        cnf.addDefault("ActionBar.ColorAddOther", "");
        cnf.setComments("ActionBar.ColorAddOverworld", Arrays.asList(
                "# Color setting for each world (by how much the barrier is increased)",
                "# ��������� ����� ��� ������� ���� (�� ������� �������� ������)"
        ));

        cnf.addDefault("ActionBar.player", "{colorPlayer}{player}�r");
        cnf.setComments("ActionBar.player", Arrays.asList(
                "# Player display template",
                "# ������ ����������� ������"
        ));

        cnf.addDefault("ActionBar.size", "{colorWorld}{size}�r");
        cnf.setComments("ActionBar.size", Arrays.asList(
                "# World size display template",
                "# ������ ����������� ������� ����"
        ));

        cnf.addDefault("ActionBar.plus", "{colorAddWorld}{plus}�r");
        cnf.setComments("ActionBar.plus", Arrays.asList(
                "# Template for displaying the number of blocks to increase",
                "# ������ ����������� ���-�� ������ ��� ����������"
        ));

        cnf.addDefault("ActionBar.Colors", "�");
        // �������������� �����
        cnf.setComments("ActionBar.Colors", Arrays.asList(
                "# currently supported colors:",
                "# ����� ������� �������������� � ������ ������:",
                "#Colors:",
                "#  black: �0",
                "#  dark_green: �2",
                "#  dark_red: �4",
                "#  gold: �6",
                "#  dark_gray: �8",
                "#  green: �a",
                "#  red: �c",
                "#  yellow: �e",
                "#  dark_blue: �1",
                "#  dark_aqua: �3",
                "#  dark_purple: �5",
                "#  gray: �7",
                "#  blue: �9",
                "#  aqua: �b",
                "#  purple: �d",
                "#  white: �f",
                "#  clear: �r",
                "https://www.reddit.com/media?url=https%3A%2F%2Fpreview.redd.it%2Fyldfhnb4xp411.png%3Fauto%3Dwebp%26s%3D747bcfe3d9db9aba2b0bfecacf8299619f8eca38"
        ));


        cnf.options().copyDefaults(true);
        plugin.saveDefaultConfig();
        plugin.saveConfig();


    }



}