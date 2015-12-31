package com.sakumar.messaging.client;

import java.util.concurrent.ExecutorService;

import com.sakumar.messaging.AsyncMessageListener;

public class AsyncMessageListenerImpl extends MessageListenerImpl implements AsyncMessageListener {
    private final ExecutorService executorService;	
	
    public AsyncMessageListenerImpl(ExecutorService executorService){
		this.executorService = executorService;
	}

	@Override
	public ExecutorService getExecutorService() {
		// TODO Auto-generated method stub
		return executorService;
	}

}
