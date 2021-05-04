package net.casheh.fishing.config;

import net.casheh.fishing.Fishing;
import net.casheh.fishing.util.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Messages {

    private final Fishing plugin;

    private FileConfiguration msgConfig;

    private File msgFile;

    public Messages(Fishing plugin) {
        this.plugin = plugin;
    }

    public void init() {
        msgFile = new File(plugin.getDataFolder(), "messages.yml");

        if (!msgFile.exists()) {

            try {
                msgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        msgConfig = YamlConfiguration.loadConfiguration(msgFile);
        msgConfig.addDefault("prefix", "&c&lL&7&lP &8&l» &r");
        msgConfig.addDefault("fish-caught", "&aYou caught a fish! Your reward is: &b%reward%!");
        msgConfig.addDefault("sell-message", "&aYou sold %amount% items for %price%!");
        msgConfig.addDefault("no-items", "&cYou do not have any sellable items!");
        msgConfig.options().copyDefaults(true);
        save();
    }

    public FileConfiguration getMessages() {
        return this.msgConfig;
    }

    public void save() {

        try {
            msgConfig.save(msgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void reload() {
        msgConfig = YamlConfiguration.loadConfiguration(msgFile);
    }

    public String getFishCaught(String reward) {
        return Util.color(msgConfig.getString("prefix") + msgConfig.getString("fish-caught").replace("%reward%", reward));
    }

    public String getSellMessage(int amount, double price) {
        return Util.color(msgConfig.getString("prefix") + msgConfig.getString("sell-message")
                .replace("%amount%", amount + "")
                .replace("%price%", price + ""));
    }

    public String getNoItems() {
        return Util.color(msgConfig.getString("prefix") + msgConfig.getString("no-items"));
    }


}
