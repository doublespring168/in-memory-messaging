package com.sakumar.messaging.util;

public class MessagePublisher implements Runnable {
    private final String destination;
	public MessagePublisher(String destination) {
		this.destination = destination;
	}
	@Override
	public void run() {			
		for(int i=0;i<1000;i++){
			MessagingUtil.post(destination, "Hello-" + i);
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Interrupted!!");
			}
		}

	}

}
