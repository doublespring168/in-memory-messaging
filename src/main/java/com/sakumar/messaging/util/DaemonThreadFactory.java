package com.sakumar.messaging.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DaemonThreadFactory implements ThreadFactory {

    private final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);
    private final String threadNamePrefix;

    public DaemonThreadFactory(final String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;

    }

    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable, threadNamePrefix + "-"
                + THREAD_NUMBER.getAndIncrement());
        if (!thread.isDaemon()) {
            thread.setDaemon(true);
        }
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }

}