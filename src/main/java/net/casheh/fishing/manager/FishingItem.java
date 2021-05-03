package net.casheh.fishing.manager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class FishingItem {

    private Material type;

    private String displayName;

    private boolean glowing;

    private List<String> lore;

    private double chance;

    public FishingItem(Material type, String displayName, boolean glowing, List<String> lore, double chance) {
        this.type = type;
        this.displayName = displayName;
        this.glowing = glowing;
        this.lore = lore;
        this.chance = chance;
    }

    public Material getType() {
        return this.type;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean isGlowing() {
        return this.glowing;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public double getChance() {
        return this.chance;
    }

    public ItemStack buildItemStack() {
        ItemStack itemStack = new ItemStack(this.type);
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(this.displayName);
            meta.setLore(this.lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        itemStack.setItemMeta(meta);
        if (this.glowing) {
            itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 999);
        }
        return itemStack;
    }

}
