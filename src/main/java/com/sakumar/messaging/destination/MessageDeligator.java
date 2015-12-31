package com.sakumar.messaging.destination;

import com.sakumar.messaging.MessageListener;
import com.sakumar.messaging.event.MessageEvent;

public class MessageDeligator implements Runnable {
    private final MessageEvent message;
    private final MessageListener messageListener;
	
    public MessageDeligator(MessageEvent message,MessageListener messageListener){
		this.message = message;
		this.messageListener = messageListener;
	}
	@Override
	public void run() {
		try
		{
			messageListener.onMessage(message.getMessage());
		}
		catch(Exception ex){
			System.err.println("Internal Error Occured in Consumer in " + message.getClientId() + " Message :" + ex.getMessage());
		}

	}

}
