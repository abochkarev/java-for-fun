package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaptureUncaughtException implements Runnable {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new CaptureUncaughtException());
        exec.shutdown();
    }

    @Override
    public void run() {
        throw new RuntimeException("This exception should be caught.");
    }

    static class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + " - " + e.getMessage());
        }
    }
}
