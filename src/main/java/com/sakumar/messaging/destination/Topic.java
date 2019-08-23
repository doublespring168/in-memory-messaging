package com.sakumar.messaging.destination;

import com.sakumar.messaging.manager.ClientManager;

import java.util.Set;

public class Topic extends AbstractDestination {

    public Topic(String destination, int listenerCount, int bufSize) {
        super(destination, listenerCount, bufSize);
    }

    @Override
    public void publish(String message) {
        Set<String> clientIds = ClientManager.getClients(destination);
        for (String clientId : clientIds) {
            if (null != clientId) {
                disruptorHandler.publish(message, clientId);
            }
        }

    }

}
