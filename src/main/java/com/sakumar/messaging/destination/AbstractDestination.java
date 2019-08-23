package com.sakumar.messaging.destination;

import com.sakumar.messaging.constant.Type;
import com.sakumar.messaging.disruptor.DisruptorHandler;
import com.sakumar.messaging.manager.DestinationManager;
import com.sakumar.messaging.model.Destination;
import com.sakumar.messaging.model.Destinations;
import com.sakumar.messaging.util.DestinationModelParserUtil;

import java.util.List;

public abstract class AbstractDestination {
    public static boolean initialized = false;
    protected final String destination;
    protected final DisruptorHandler disruptorHandler;

    protected AbstractDestination(String destination, int listenerCount, int bufSize) {
        this.destination = destination;
        this.disruptorHandler = new DisruptorHandler(destination, listenerCount, bufSize);
    }

    public static synchronized void init(String destConfigLocation) {
        if (false == initialized) {
            try {
                Destinations destination = DestinationModelParserUtil.getDestinations(destConfigLocation);
                if (null != destination) {
                    List<Destination> destinations = destination.getDestinations();
                    if (null != destinations) {
                        for (Destination destin : destinations) {
                            Type type = destin.getType();
                            String name = destin.getName();
                            switch (type) {
                                case queue:
                                    DestinationManager.put(name, new Queue(name, destin.getListenerCount(), destin.getCapacity()));
                                    break;
                                case topic:
                                    DestinationManager.put(name, new Topic(name, destin.getListenerCount(), destin.getCapacity()));
                            }
                        }

                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException("Unable To Parse Configuration", ex);
            }
            initialized = true;
        }
    }

    public abstract void publish(String message);
}
