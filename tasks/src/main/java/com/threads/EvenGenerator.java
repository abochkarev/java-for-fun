package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class IntGenerator {
    private volatile boolean cancelled = false;

    abstract int nextInt();

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}


class EvenChecker implements Runnable {
    private final IntGenerator intGenerator;
    private final int id;

    public EvenChecker(IntGenerator intGenerator, int id) {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    public static void test(IntGenerator ig, int count) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(ig, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator ig) {
        test(ig, 10);
    }

    @Override
    public void run() {
        while (!intGenerator.isCancelled()) {
            int val = intGenerator.nextInt();
            if (val % 2 != 0) {
                System.out.println("Thread id = " + id + ", " + val + " not even!");
                intGenerator.cancel();
            }
        }
    }
}


public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }

    @Override
    int nextInt() {
        System.out.println(currentEvenValue);
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

}
