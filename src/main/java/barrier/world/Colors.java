package barrier.world;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;

class ColorC {

    public static String black (String text) {return "\033[0;30m" + text + "\033[0m";}
    public static String red (String text) {return "\033[0;31m" + text + "\033[0m";}
    public static String green (String text) {return "\033[0;32m" + text + "\033[0m";}
    public static String yellow (String text) {return "\033[0;33m" + text + "\033[0m";}
    public static String blue (String text) {return "\033[0;34m" + text + "\033[0m";}
    public static String puple (String text) {return "\033[0;35m" + text + "\033[0m";}
    public static String cyan (String text) {return "\033[0;36m" + text + "\033[0m";}
    public static String white (String text) {return "\033[0;37m" + text + "\033[0m";}

}


class ColorG {

    public static TextComponent aqua (String text) {
        return Component.text(text).color(TextColor.color(48 , 216, 224));
    }

    public static TextComponent blue (String text) {
        return Component.text(text).color(TextColor.color(49 , 34, 238));
    }

    public static TextComponent yellow (String text) {
        return Component.text(text).color(TextColor.color(255 , 255, 0));
    }

    public static TextComponent orange (String text) {
        return Component.text(text).color(TextColor.color(255, 165, 0));
    }

    public static TextComponent red (String text) {
        return Component.text(text).color(TextColor.color(200 , 0, 0));
    }

    public static TextComponent white (String text) {
        return Component.text(text).color(TextColor.color(255 , 255, 255));
    }


}