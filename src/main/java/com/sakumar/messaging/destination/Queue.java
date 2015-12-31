package com.sakumar.messaging.destination;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.sakumar.messaging.manager.ClientManager;

public class Queue extends AbstractDestination {
	private static class AtomicCounter {
		private final AtomicInteger number;
		private AtomicCounter() {
			this.number = new AtomicInteger(0);
		}
		public int getNext() {
			return number.updateAndGet(n -> (n < 0) ? 0 : n+1);
		}
	}
	private final AtomicCounter atomicCounter;
	public Queue(String destination,int listenerCount,int bufSize) {
		super(destination,listenerCount,bufSize);
		atomicCounter = new AtomicCounter();
	}
	@Override
	public void publish(String message) {
		Set<String> clientIds = ClientManager.getClients(destination);
		if(null != clientIds){
			int index = atomicCounter.getNext()%(clientIds.size());
			int i = 0;
			String clientId = null;
			for(String id : clientIds){
			 if(++i > index){
				 clientId = id;
				 break;
			 }
			}
			if(null != clientId){
			   disruptorHandler.publish(message,clientId);
			}
		}
	}
	
}
