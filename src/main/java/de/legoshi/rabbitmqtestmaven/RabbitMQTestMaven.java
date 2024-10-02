package de.legoshi.rabbitmqtestmaven;

import de.legoshi.rabbitmqtestmaven.rabbit.MessageManager;
import de.legoshi.rabbitmqtestmaven.rabbit.RabbitMQServer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RabbitMQTestMaven extends JavaPlugin {

    private RabbitMQServer rabbitMQServer;
    private MessageManager manager;

    @Override
    public void onEnable() {
        rabbitMQServer = new RabbitMQServer();
        manager = new MessageManager(rabbitMQServer);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            manager.receiveMessage("channel1");
        }, 1000, 10);

        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerChatListener(manager), this);
    }

}