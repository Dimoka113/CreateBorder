package barrier.world;


import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static FileConfiguration cnf;


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
        cnf.addDefault("advancement", true);
        //cnf.addDefault("", "#on/off expansion of the barrier for achievement");
        //cnf.addDefault("", "#вкл/выкл расширения барьера для ачивки");

        cnf.addDefault("BorderSpeed", 1);
        cnf.addDefault("BorderCoff", 1);
        cnf.addDefault("BlockBolder_common", 2);
        cnf.addDefault("BlockBolder_rare", 8);
        cnf.addDefault("BlockBolder_epic", 16);
        cnf.addDefault("World_Border_Overworld", 1);
        cnf.addDefault("World_Border_Nether", 8);
        cnf.addDefault("World_Border_End", 24);


        cnf.addDefault("ActionBar.enable", true);
        cnf.addDefault("ActionBar.Text","test message {player} {size} {plus}");
        limit:
        cnf.addDefault("limit.actionbarmessage", "§4Достигнут лимит размера барьера...§r");
        cnf.addDefault("limit.radius", 10000);


        cnf.addDefault("ActionBar.ColorPlayerOverworld","");
        cnf.addDefault("ActionBar.ColorPlayerNether","");
        cnf.addDefault("ActionBar.ColorPlayerTheEnd","");

        cnf.addDefault("ActionBar.ColorOverworld","");
        cnf.addDefault("ActionBar.ColorNether","");
        cnf.addDefault("ActionBar.ColorTheEnd","");

        cnf.addDefault("ActionBar.ColorAddOverworld","");
        cnf.addDefault("ActionBar.ColorAddNether","");
        cnf.addDefault("ActionBar.ColorAddTheEnd","");

        cnf.addDefault("ActionBar.size","{size}");
        cnf.addDefault("ActionBar.plus","{plus}");





        cnf.options().copyDefaults(true);
        plugin.saveConfig();
    }



}
