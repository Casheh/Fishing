package net.casheh.fishing.events;

import net.casheh.fishing.Fishing;
import net.casheh.fishing.manager.FishingItem;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Random;

public class FishListener implements Listener {

    private Fishing plugin;

    public FishListener(Fishing plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        e.setExpToDrop(0);

        if (e.getCaught() instanceof Item) {
            Item item = (Item) e.getCaught();
            Random rand = new Random();
            double totalChance = plugin.config().getItemChances().stream().mapToDouble(FishingItem::getChance).sum();
            double num = 0 + (totalChance - 0) * rand.nextDouble();

            for (FishingItem fishingItem : plugin.config().getItemChances()) {
                e.getPlayer().sendMessage(" ");
                e.getPlayer().sendMessage(num + "");
                e.getPlayer().sendMessage(fishingItem.getChance() + "");
                if (num < fishingItem.getChance()) {
                    item.setItemStack(fishingItem.buildItemStack());
                    e.getPlayer().sendMessage(plugin.getMessages().getFishCaught(item.getName()));
                    break;
                }

                num -= fishingItem.getChance();
            }
        }
    }
}
