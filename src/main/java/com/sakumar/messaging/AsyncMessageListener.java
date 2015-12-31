package com.sakumar.messaging;

import java.util.concurrent.ExecutorService;

public interface AsyncMessageListener extends MessageListener {
   public ExecutorService getExecutorService();
}
