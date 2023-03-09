package com.kdazz.comment.utils;

import java.util.concurrent.LinkedBlockingQueue;

public class CustomBlockingQueue<E> extends LinkedBlockingQueue<E> {

    public CustomBlockingQueue(int capacity) {
        super(capacity);
    }

    @Override
    public boolean offer(E e) {
        try {
            put(e);
            return true;
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        return false;
    }
}
