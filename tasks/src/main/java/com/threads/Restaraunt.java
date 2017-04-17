package com.threads;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Meal {
    private final int orderedId;

    public Meal(int orderedId) {
        this.orderedId = orderedId;
    }

    @Override
    public String toString() {
        return "# " + orderedId;
    }
}

class Waiter implements Runnable {

    private final Restaraunt restaraunt;

    public Waiter(Restaraunt restaraunt) {
        this.restaraunt = restaraunt;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaraunt.meal == null) {
                        wait();
                    }
                }
                System.out.println("Waitperson got " + restaraunt.meal);
                synchronized (restaraunt.chef) {
                    restaraunt.meal = null;
                    restaraunt.chef.notifyAll();
                }

            }
        } catch (InterruptedException e) {
            System.out.println("Waiter interrupted");
        }

    }
}

class Chef implements Runnable {

    private final Restaraunt restaraunt;

    public Chef(Restaraunt restaraunt) {
        this.restaraunt = restaraunt;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaraunt.meal != null) {
                        wait();
                    }
                }
                if (i++ > 9) {
                    restaraunt.exec.shutdownNow();
                    System.out.println("The restaraunt is closed.");
                }
                synchronized (restaraunt.waiter) {
                    restaraunt.meal = new Meal(i);
                    restaraunt.waiter.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}

public class Restaraunt {

    final public ExecutorService exec = Executors.newCachedThreadPool();
    final public Chef chef = new Chef(this);
    final public Waiter waiter = new Waiter(this);
    public Meal meal;


    public Restaraunt() {
        exec.execute(chef);
        exec.execute(waiter);
    }

    public static void main(String[] args) {
        new Restaraunt();
    }
}
