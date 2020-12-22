package me.xemu.xemchatprotection;

import me.xemu.xemchatprotection.commands.ReloadConfigCommand;
import me.xemu.xemchatprotection.events.PlayerChatEvent;
import me.xemu.xemchatprotection.utils.metrics.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class XemChatProtection extends JavaPlugin
{

    private static XemChatProtection plugin;

    @Override public void onEnable()
    {
        plugin = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        getCommand("xemchatprotection").setExecutor(new ReloadConfigCommand());


        // Metrics
        final int pluginID = 9675;
        MetricsLite metrics = new MetricsLite(this, pluginID);
        getLogger().info("Hooked into bStats");

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);

        getLogger().info("Plugin Enabled");
    };

    @Override public void onDisable()
    {
        plugin = null;

        getLogger().info("Plugin Disabled");
    };

    public static XemChatProtection getPlugin()
    {
        return plugin;
    };

};
