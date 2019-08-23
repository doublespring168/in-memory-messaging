package com.sakumar.messaging.event;

import com.lmax.disruptor.EventFactory;

import java.io.Serializable;

public class MessageEvent implements Serializable {
    public final static EventFactory<MessageEvent> EVENT_FACTORY = new EventFactory<MessageEvent>() {
        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    };
    /**
     *
     */
    private static final long serialVersionUID = 8402647408778527924L;
    private String clientId;
    private String message;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
