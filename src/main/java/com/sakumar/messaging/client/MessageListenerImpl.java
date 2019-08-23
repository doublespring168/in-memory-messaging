package com.sakumar.messaging.client;

import com.sakumar.messaging.MessageListener;

public class MessageListenerImpl implements MessageListener {

    @Override
    public void onMessage(String message) {
        System.out.println("Hey " + this + " Received " + message + " From " + Thread.currentThread().getName() + "!!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
