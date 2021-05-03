package net.casheh.fishing.manager;

import net.casheh.fishing.nbt.NBTEditor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class FishingItem {

    private final Material type;

    private final String displayName;

    private final boolean glowing;

    private final List<String> lore;

    private final double chance;

    private final double sellPrice;

    public FishingItem(Material type, String displayName, boolean glowing, List<String> lore, double chance, double sellPrice) {
        this.type = type;
        this.displayName = displayName;
        this.glowing = glowing;
        this.lore = lore;
        this.chance = chance;
        this.sellPrice = sellPrice;
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

    public double getSellPrice() {
        return this.sellPrice;
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

        itemStack = NBTEditor.set(itemStack, this.sellPrice, "FishingPrice");

        return itemStack;
    }

}
