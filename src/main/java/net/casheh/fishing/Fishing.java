package net.casheh.fishing;

import net.casheh.fishing.commands.FishCommand;
import net.casheh.fishing.config.Config;
import net.casheh.fishing.config.Messages;
import net.casheh.fishing.events.FishListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fishing extends JavaPlugin {

    public static Fishing inst;

    private Config config;

    private Messages messages;

    private Economy economy;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        inst = this;

        config = new Config(this);
        config.assign();

        messages = new Messages(this);
        messages.init();

        setupEconomy();

        this.getServer().getPluginManager().registerEvents(new FishListener(this), this);
        getCommand("fish").setExecutor(new FishCommand(this));

    }

    private void setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null)
            return;
        RegisteredServiceProvider<Economy> provider = getServer().getServicesManager().getRegistration(Economy.class);
        if (provider == null)
            return;
        this.economy = provider.getProvider();
    }

    public Config config() {
        return this.config;
    }

    public Messages getMessages() {
        return this.messages;
    }

    public Economy getEconomy() {
        return this.economy;
    }
}
