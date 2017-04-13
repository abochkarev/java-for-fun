package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {

    private int countDown = 5;
    private volatile double d;
    private final int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ":" + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 1; i < 1000000000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }

            }
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String []args) {
        ExecutorService e = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            e.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        e.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        e.shutdown();
    }
}
