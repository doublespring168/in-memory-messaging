package com.sakumar.messaging.client;

import com.sakumar.messaging.AsyncMessageListener;

import java.util.concurrent.ExecutorService;

public class AsyncMessageListenerImpl extends MessageListenerImpl implements AsyncMessageListener {
    private final ExecutorService executorService;

    public AsyncMessageListenerImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public ExecutorService getExecutorService() {
        // TODO Auto-generated method stub
        return executorService;
    }

}
