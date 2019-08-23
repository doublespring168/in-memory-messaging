package com.sakumar.messaging.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.sakumar.messaging.event.MessageEvent;
import com.sakumar.messaging.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorHandler {
    private static final int RINGBUFFER_MIN_SIZE = 128;
    private static final int RINGBUFFER_DEFAULT_SIZE = 128;
    private final Disruptor<MessageEvent> disruptor;
    private final ExecutorService executor;

    @SuppressWarnings("unchecked")
    public DisruptorHandler(String destination, int nThreads, int bufSize) {
        this.executor = Executors.newFixedThreadPool(nThreads, new DaemonThreadFactory(destination));
        this.disruptor = new Disruptor<MessageEvent>(MessageEvent.EVENT_FACTORY, calculateRingBufferSize(bufSize), executor);
        this.disruptor.handleEventsWith(new MessageEventHandler());
        this.disruptor.start();
    }

    private static int calculateRingBufferSize(int bufSize) {
        int ringBufferSize = RINGBUFFER_DEFAULT_SIZE;
        if (bufSize < RINGBUFFER_MIN_SIZE) {
            bufSize = RINGBUFFER_MIN_SIZE;
        }
        ringBufferSize = bufSize;
        return 1 << (32 - Integer.numberOfLeadingZeros(ringBufferSize - 1));
    }

    public void shutdown() {
        disruptor.shutdown();
        executor.shutdown();
    }

    public void publish(String message, String clientId) {
        RingBuffer<MessageEvent> ringBuffer = disruptor.getRingBuffer();
        long seq = ringBuffer.next();
        MessageEvent event = ringBuffer.get(seq);
        event.setMessage(message);
        event.setClientId(clientId);
        ringBuffer.publish(seq);
    }
}
