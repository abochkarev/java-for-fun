package com.threads;/**
 * Created by abochkarev on 13.04.17.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {

    private final int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {

    private static final int nThreads = 100;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        List<Future<String>> futures = new ArrayList<>(nThreads);
        for (int i = 0; i < nThreads; i++) {
            futures.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }

}
