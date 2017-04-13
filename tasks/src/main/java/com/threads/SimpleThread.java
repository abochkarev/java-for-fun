package com.threads;


public class SimpleThread extends Thread {

    private int countDown = 5;


    public SimpleThread() {
        start();
    }

    public String toString() {
        return String.format("# %s + (%s)", getName(), countDown);
    }


    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(this);
            Thread.yield();
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
