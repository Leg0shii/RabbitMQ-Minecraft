package de.legoshi.rabbitmqtestmaven.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class RabbitMQServer {

    private Connection connection;
    private HashMap<String, Channel> channelHashMap;

    public RabbitMQServer() {
        this.channelHashMap = new HashMap<>();
        startConnection();
    }

    private void startConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnection()) {

            this.connection = connection;
            loadChannels();

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void loadChannels() throws IOException {
        ArrayList<String> channels = new ArrayList<String>() {{ add("channel1"); add("channel2"); }};
        for(String ch : channels) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(ch, false, false, false, null);
            channelHashMap.put(ch, channel);
        }
    }

    public HashMap<String, Channel> getChannelHashMap() {
        return channelHashMap;
    }
}
