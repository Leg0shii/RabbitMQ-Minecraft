package de.legoshi.rabbitmqtestmaven.rabbit;

import com.rabbitmq.client.Channel;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MessageManager {

    private final RabbitMQServer rabbitMQServer;

    public MessageManager(RabbitMQServer rabbitMQServer) {
        this.rabbitMQServer = rabbitMQServer;
    }

    public void sendMessage(String channelName, String message) {
        Channel channel = rabbitMQServer.getChannelHashMap().get(channelName);
        try {
            channel.basicPublish("", channelName, false, null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage(String channelName) {
        Channel channel = rabbitMQServer.getChannelHashMap().get(channelName);
        try {
            channel.basicConsume(channelName, true, (consumerTag, message) -> {
                        String m = new String(message.getBody(), StandardCharsets.UTF_8);
                        Bukkit.broadcastMessage(m);
                    },
                    consumerTag -> {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
