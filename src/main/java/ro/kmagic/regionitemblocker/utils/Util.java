package ro.kmagic.regionitemblocker.utils;

import net.md_5.bungee.api.ChatColor;
import ro.kmagic.regionitemblocker.RegionItemBlocker;

import java.util.logging.Level;

public class Util {

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void log(Level level, String str) {
        RegionItemBlocker.getInstance().getLogger().log(level, str);
    }

    public static void info(String str) {
        log(Level.INFO, str);
    }

    public static void error(String str) {
        log(Level.SEVERE, str);
    }

}
