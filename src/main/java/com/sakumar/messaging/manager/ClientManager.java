package com.sakumar.messaging.manager;

import com.sakumar.messaging.AsyncMessageListener;
import com.sakumar.messaging.MessageListener;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;


public class ClientManager {

    private static Map<String, MessageListener> clients = new ConcurrentHashMap<>();
    private static Map<String, Set<String>> destMap = new ConcurrentHashMap<>();

    public static String register(MessageListener messageListener, String destination) {
        String clientId = UUID.randomUUID().toString();
        register(clientId, messageListener, destination);
        return clientId;
    }

    public static void register(String clientId, MessageListener messageListener, String destination) {
        clients.put(clientId, messageListener);
        Set<String> clientIds = destMap.get(destination);
        if (null == clientIds) {
            clientIds = Collections.newSetFromMap(
                    new ConcurrentHashMap<String, Boolean>());
            ;
            destMap.put(destination, clientIds);
        }
        clientIds.add(clientId);
    }

    public static void unRegister(String clientId) {
        MessageListener messageListener = clients.remove(clientId);
        shutdownExecutorService(messageListener);
        for (Map.Entry<String, Set<String>> entry : destMap.entrySet()) {
            Set<String> clientIds = entry.getValue();
            clientIds.remove(clientId);
        }
    }

    private static void shutdownExecutorService(MessageListener messageListener) {
        if (messageListener instanceof AsyncMessageListener) {
            messageListener = (AsyncMessageListener) messageListener;
            ExecutorService executorService = ((AsyncMessageListener) messageListener).getExecutorService();
            if (null != executorService) {
                executorService.shutdown();
            }
        }
    }

    public static Set<String> getClients(String destination) {
        Set<String> clientIds = destMap.get(destination);
        if (null != clientIds) {
            clientIds = Collections.unmodifiableSet(clientIds);
        }
        return clientIds;
    }

    public static MessageListener getMessageListener(String clientId) {
        return clients.get(clientId);
    }

    public static void shutdown() {
        for (Map.Entry<String, MessageListener> entry : clients.entrySet()) {
            MessageListener messageListener = entry.getValue();
            shutdownExecutorService(messageListener);
        }
    }

}
