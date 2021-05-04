package net.casheh.fishing.commands;

import net.casheh.fishing.Fishing;
import net.casheh.fishing.nbt.NBTEditor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class FishCommand implements CommandExecutor {

    private final Fishing plugin;

    public FishCommand(Fishing plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender)
            return false;

        Player p = (Player) sender;
        PlayerInventory inv = p.getInventory();

        double sellPrice = 0;
        int totalItems = 0;

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item == null || item.getType() == Material.AIR)
                continue;
            if (NBTEditor.contains(item, "FishingPrice")) {
                sellPrice += item.getAmount() * NBTEditor.getDouble(item, "FishingPrice");
                totalItems += item.getAmount();
                inv.setItem(i, new ItemStack(Material.AIR));
            }
        }

        if (totalItems == 0) {
            p.sendMessage(plugin.getMessages().getNoItems());
            return false;
        }

        plugin.getEconomy().depositPlayer(p, sellPrice);
        p.sendMessage(plugin.getMessages().getSellMessage(totalItems, sellPrice));

        return true;
    }
}
