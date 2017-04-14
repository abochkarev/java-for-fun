package com.threads;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {

    private static final Random RANDOMIZER = new Random();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new SleepingTask());
        }
        executor.shutdown();
    }

    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.println(status());
                int sleepTime = RANDOMIZER.nextInt(10);
                System.out.println(String.format("Sleep time = %s", sleepTime));
                TimeUnit.SECONDS.sleep(sleepTime);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
