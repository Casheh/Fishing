package net.casheh.fishing.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static String color(String raw) {
        return ChatColor.translateAlternateColorCodes('&', raw);
    }

    public static List<String> color(List<String> strList) {
        List<String> colourized = new ArrayList<>();
        strList.forEach(el -> colourized.add(Util.color(el)));
        return colourized;
    }


}
