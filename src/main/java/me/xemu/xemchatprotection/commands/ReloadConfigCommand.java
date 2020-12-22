/*
Plugin Developed & Maintained by Xemu
 */
package me.xemu.xemchatprotection.commands;

import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfigCommand implements CommandExecutor
{

    @Override public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args)
    {
        if(sender instanceof Player)
        {
            final Player player = (Player) sender;

            if(args.length == 0)
            {
                for (String h : XemChatProtection.getPlugin().getConfig().getStringList("Help"))
                {
                    player.sendMessage(Utils.chat(h).replaceAll("<version>", XemChatProtection.getPlugin().getDescription().getVersion()));
                };
            } else if (args.length == 1) {
                if(args[0].toLowerCase().equalsIgnoreCase("reload"))
                {
                    if(player.hasPermission(XemChatProtection.getPlugin().getConfig().getString("ReloadCommand.Permission")))
                    {
                        XemChatProtection.getPlugin().reloadConfig();
                        player.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("ReloadCommand.Message")));
                    } else {
                        player.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("NoPermission")));
                    };
                };
            };


        };
        return true;
    };

};