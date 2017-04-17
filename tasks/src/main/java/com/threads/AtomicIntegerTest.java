package com.threads;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000);
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ai = new AtomicIntegerTest();
        exec.submit(ai);
        while (true) {
            int val = ai.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

    public int getValue() {
        return atomicInteger.get();
    }

    private void increment() {
        atomicInteger.addAndGet(2);
    }

    @Override
    public void run() {
        while (true) {
            increment();
        }
    }
}
