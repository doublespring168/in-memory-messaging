package com.sakumar.messaging;

import java.io.File;
import java.util.concurrent.Executors;

import com.sakumar.messaging.client.AsyncMessageListenerImpl;
import com.sakumar.messaging.client.MessageListenerImpl;
import com.sakumar.messaging.destination.AbstractDestination;
import com.sakumar.messaging.manager.ClientManager;
import com.sakumar.messaging.util.DaemonThreadFactory;
import com.sakumar.messaging.util.MessagePublisher;

/**
 * Test Utility for Topic and Queue
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       String destConfigLocation= "src" + File.separator +"main" + File.separator + "resources" + File.separator + "Destinations.xml";
       AbstractDestination.init(destConfigLocation);
       MessageListener messageListener = new MessageListenerImpl();
       ClientManager.register(messageListener, "Queue-1");
       messageListener = new MessageListenerImpl();
       ClientManager.register(messageListener, "Queue-1");	
       messageListener = new AsyncMessageListenerImpl(Executors.newSingleThreadExecutor(new DaemonThreadFactory("AsyncQueueMessage")));
       ClientManager.register(messageListener, "Queue-1");
       messageListener = new MessageListenerImpl();
       ClientManager.register(messageListener, "Topic-1");
       messageListener = new MessageListenerImpl();
       ClientManager.register(messageListener, "Topic-1");
       messageListener = new AsyncMessageListenerImpl(Executors.newSingleThreadExecutor(new DaemonThreadFactory("AsyncTopicMessage-1")));
       ClientManager.register(messageListener, "Topic-1");		
       new Thread(new MessagePublisher("Topic-1")).start();
       new Thread(new MessagePublisher("Queue-1")).start();
       messageListener = new AsyncMessageListenerImpl(Executors.newSingleThreadExecutor(new DaemonThreadFactory("AsyncTopicMessage-2")));
       ClientManager.register(messageListener, "Topic-1");	
    } 
}
