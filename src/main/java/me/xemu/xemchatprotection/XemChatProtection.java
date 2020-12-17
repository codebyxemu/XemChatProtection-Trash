package me.xemu.xemchatprotection;

import org.bukkit.plugin.java.JavaPlugin;

public final class XemChatProtection extends JavaPlugin
{

    private static XemChatProtection plugin;

    @Override public void onEnable()
    {
        plugin = this;
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
