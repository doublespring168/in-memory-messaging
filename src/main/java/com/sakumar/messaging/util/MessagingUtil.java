package com.sakumar.messaging.util;

import com.sakumar.messaging.destination.AbstractDestination;
import com.sakumar.messaging.manager.DestinationManager;

public class MessagingUtil {
    public static void post(String destination, String message) {
        AbstractDestination abstractDestination = DestinationManager.get(destination);
        if (null != abstractDestination) {
            abstractDestination.publish(message);
        } else {
            throw new RuntimeException("Destination " + destination + " not available");
        }
    }
}
