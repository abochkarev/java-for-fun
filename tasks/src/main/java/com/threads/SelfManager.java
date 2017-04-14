package com.threads;

public class SelfManager implements Runnable {
    private final Thread thread = new Thread(this);
    private int countDown = 5;

    public SelfManager() {
        thread.start();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new SelfManager();
        }
        new Thread(new Runnable() {
            private int countDown;

            {
                countDown = 50;
            }

            @Override
            public String toString() {
                return Thread.currentThread().getName() + "(" + countDown + "), ";
            }

            @Override
            public void run() {
                while (countDown-- > 0) {
                    System.out.println(this);
                }
            }
        }).start();
    }

    public String toString() {
        return Thread.currentThread().getName() + "(" + countDown + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(this);
            Thread.yield();
        }

    }
}
