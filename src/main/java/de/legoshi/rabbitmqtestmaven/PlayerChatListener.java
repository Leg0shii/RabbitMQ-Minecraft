package de.legoshi.rabbitmqtestmaven;

import de.legoshi.rabbitmqtest.rabbit.MessageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private final MessageManager manager;

    public PlayerChatListener(MessageManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        manager.sendMessage("channel1", message);
        event.setCancelled(true);
    }

}
