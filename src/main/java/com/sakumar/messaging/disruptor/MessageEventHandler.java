package com.sakumar.messaging.disruptor;

import java.util.concurrent.ExecutorService;

import com.lmax.disruptor.EventHandler;
import com.sakumar.messaging.AsyncMessageListener;
import com.sakumar.messaging.MessageListener;
import com.sakumar.messaging.destination.MessageDeligator;
import com.sakumar.messaging.event.MessageEvent;
import com.sakumar.messaging.manager.ClientManager;

public class MessageEventHandler implements EventHandler<MessageEvent> {
	@Override
	public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception {
		String clientId = event.getClientId();
		if(null != clientId){
			MessageListener messageListener = ClientManager.getMessageListener(clientId);
			if(null != messageListener){
				if(messageListener instanceof AsyncMessageListener){
					AsyncMessageListener asyncMessageListener = (AsyncMessageListener) messageListener;
					ExecutorService executorService = asyncMessageListener.getExecutorService();
					if(null != executorService){
						executorService.execute(new MessageDeligator(event, asyncMessageListener));
					}
				}
				else
				{	
					messageListener.onMessage(event.getMessage());
				}
			}
		}

	}

}
