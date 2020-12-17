package me.xemu.xemchatprotection.events;

import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener
{

    @EventHandler protected void onPlayerChat(AsyncPlayerChatEvent event)
    {

        final String message = event.getMessage().toLowerCase();
        final Player messageAuthor = event.getPlayer();

        for(String blocked : XemChatProtection.getPlugin().getConfig().getStringList("Words"))
        {
            if(message.contains(blocked.toLowerCase()))
            {
                event.setCancelled(true);
                messageAuthor.sendMessage(Utils.chat(XemChatProtection.getPlugin().getConfig().getString("MessageBlocked").replaceAll("<message>", message).replaceAll("<player>", messageAuthor.getName())));
                break;
            }
        }

    };

};