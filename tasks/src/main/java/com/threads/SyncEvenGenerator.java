package com.threads;


public class SyncEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public static void main(String[] args) {
        EvenChecker.test(new SyncEvenGenerator());
    }

    @Override
    synchronized int nextInt() {
        System.out.println(currentEvenValue);
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
}
