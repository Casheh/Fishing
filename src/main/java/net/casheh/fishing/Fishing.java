package net.casheh.fishing;

import net.casheh.fishing.config.Config;
import net.casheh.fishing.config.Messages;
import net.casheh.fishing.events.FishListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fishing extends JavaPlugin {

    public static Fishing inst;

    private Config config;

    private Messages messages;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        inst = this;

        config = new Config(this);
        config.assign();

        messages = new Messages(this);
        messages.init();

        this.getServer().getPluginManager().registerEvents(new FishListener(this), this);

    }

    public Config config() {
        return this.config;
    }

    public Messages getMessages() {
        return this.messages;
    }
}
