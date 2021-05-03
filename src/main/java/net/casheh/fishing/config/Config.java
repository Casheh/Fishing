package net.casheh.fishing.config;

import net.casheh.fishing.Fishing;
import net.casheh.fishing.Util.Util;
import net.casheh.fishing.manager.FishingItem;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private Fishing plugin;

    public Config(Fishing plugin) {
        this.plugin = plugin;
    }

    public void assign() {
        this.plugin.reloadConfig();

    }

    public List<FishingItem> getItemChances() {
        List<FishingItem> items = new ArrayList<>();
        ConfigurationSection section = this.plugin.getConfig().getConfigurationSection("Rewards.items");
        if (section != null) {
            for (String item : section.getKeys(false)) {
                FishingItem fishingItem = new FishingItem(
                        Material.valueOf(section.getString(item + ".type")),
                        Util.color(section.getString(item + ".display-name")),
                        section.getBoolean(item + ".glowing"),
                        Util.color(section.getStringList(item + ".lore")),
                        section.getDouble(item + ".chance"));
                items.add(fishingItem);
            }
        }
        return items;
    }



}
