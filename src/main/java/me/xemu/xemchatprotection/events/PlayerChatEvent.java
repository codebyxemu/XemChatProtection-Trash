package me.xemu.xemchatprotection.events;

import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerChatEvent implements Listener
{
    private boolean checkMessage(String string)
    {
        String chat = string;
        boolean found = false;
        Pattern pattern = Pattern.compile("^[\\u0000-\\u007F  \\p{Sc}]*$");
        Matcher matcher = pattern.matcher(chat);

        if(matcher.find())
        {
            found = true;
        };

        return found;
    };

    @EventHandler protected void onPlayerChat(AsyncPlayerChatEvent event)
    {

        final String message = event.getMessage().toLowerCase();
        final Player messageAuthor = event.getPlayer();

        if(checkMessage(message))
        {
            if(XemChatProtection.bypassEnabled == true && !messageAuthor.hasPermission(XemChatProtection.bypassPermission))
            {
                event.setCancelled(true);
            } else {
                event.setCancelled(false);
            };
            messageAuthor.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("MessageBlocked").replaceAll("<message>", message).replaceAll("<player>", messageAuthor.getName())));

            for (Player staff : Bukkit.getOnlinePlayers())
            {
                if(staff.hasPermission(XemChatProtection.getPlugin().getConfig().getString("NotifyStaff.Permission")))
                {
                    staff.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("NotifyStaff.Message").replaceAll("<message>", message).replaceAll("<player>", messageAuthor.getName())));
                };
            };
        };

        for(String blocked : XemChatProtection.getPlugin().getConfig().getStringList("Words"))
        {
            if(XemChatProtection.getPlugin().getConfig().getBoolean("AdvertisementBlocker"))
            {
                if(message.contains("www.") || message.endsWith(".net") || message.endsWith(".com") || message.contains(".com") || message.contains(".net"))
                {
                    if(XemChatProtection.bypassEnabled == true && !messageAuthor.hasPermission(XemChatProtection.bypassPermission))
                    {
                        event.setCancelled(true);
                    } else {
                        event.setCancelled(false);
                    };                    messageAuthor.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("MessageBlocked").replaceAll("<message>", message).replaceAll("<player>", messageAuthor.getName())));

                    if(XemChatProtection.getPlugin().getConfig().getBoolean("NotifyStaff.Enabled"))
                    {
                        for (Player staff : Bukkit.getServer().getOnlinePlayers())
                        {
                            if(staff.hasPermission(XemChatProtection.getPlugin().getConfig().getString("NotifyStaff.Permission")))
                            {
                                staff.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("NotifyStaff.Message").replaceAll("<message>", message).replaceAll("<player>", messageAuthor.getName())));
                            }
                        };
                    };


                    break;
                };
            };

            if(message.contains(blocked.toLowerCase()))
            {
                if(XemChatProtection.bypassEnabled == true && !messageAuthor.hasPermission(XemChatProtection.bypassPermission))
                {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                };                messageAuthor.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("MessageBlocked").replaceAll("<message>", message).replaceAll("<player>", messageAuthor.getName())));

                if(XemChatProtection.getPlugin().getConfig().getBoolean("NotifyStaff.Enabled"))
                {
                    for (Player staff : Bukkit.getServer().getOnlinePlayers())
                    {
                        if(staff.hasPermission(XemChatProtection.getPlugin().getConfig().getString("NotifyStaff.Permission")))
                        {
                            staff.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("NotifyStaff.Message").replaceAll("<message>", message).replaceAll("<player>", messageAuthor.getName())));
                        }
                    };
                };

                break;
            }
        }

    };

};