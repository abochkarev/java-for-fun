package com.threads;

public class SingleSync {
    private final Object syncObject = new Object();

    public static void main(String[] args) {
        SingleSync ss = new SingleSync();
        new Thread(ss::a).start();
        new Thread(ss::b).start();
        new Thread(ss::c).start();
    }

    public void a() {
        synchronized (syncObject) {
            System.out.println("Method a, thread =  " + Thread.currentThread().getName());
            Thread.yield();
        }
    }

    public void b() {
        synchronized (syncObject) {
            System.out.println("Method b, thread =  " + Thread.currentThread().getName());
            Thread.yield();
        }
    }

    public void c() {
        synchronized (syncObject) {
            System.out.println("Method c, thread =  " + Thread.currentThread().getName());
            Thread.yield();
        }
    }
}
