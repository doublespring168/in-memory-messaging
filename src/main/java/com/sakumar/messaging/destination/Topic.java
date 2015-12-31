package com.sakumar.messaging.destination;

import java.util.Set;

import com.sakumar.messaging.manager.ClientManager;

public class Topic extends AbstractDestination {

	public Topic(String destination,int listenerCount,int bufSize) {
		super(destination,listenerCount,bufSize);
	}

	@Override
	public void publish(String message) {
		Set<String> clientIds = ClientManager.getClients(destination);
		for(String clientId : clientIds){
			if(null != clientId){
				   disruptorHandler.publish(message,clientId);
			}
		}

	}

}
