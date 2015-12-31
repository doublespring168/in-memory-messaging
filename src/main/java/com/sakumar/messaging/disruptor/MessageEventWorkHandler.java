package com.sakumar.messaging.disruptor;

import com.lmax.disruptor.WorkHandler;
import com.sakumar.messaging.MessageListener;
import com.sakumar.messaging.event.MessageEvent;
import com.sakumar.messaging.manager.ClientManager;

public class MessageEventWorkHandler implements WorkHandler<MessageEvent> {

	@Override
	public void onEvent(MessageEvent event) throws Exception {
		String clientId = event.getClientId();
		if(null != clientId){
			MessageListener messageListener = ClientManager.getMessageListener(clientId);
			if(null != messageListener){
				messageListener.onMessage(event.getMessage());
			}
		}
		
	}

}
