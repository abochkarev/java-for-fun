package com.tasks;

interface Interface {
    void doSomething();
    void doSomethingElse(String arg);
}

class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("Real object: do something");
    }

    @Override
    public void doSomethingElse(String arg) {
        System.out.println("Real object: do something else");
    }
}

class SimpleProxy implements Interface {

    Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        proxied.doSomething();
        System.out.println("Simple proxy: do something");
    }

    @Override
    public void doSomethingElse(String arg) {
        proxied.doSomethingElse(arg);
        System.out.println("Simple proxy: do something else");

    }
}
public class Proxy {

    public static void consumer(Interface i) {
        i.doSomething();
        i.doSomethingElse("foo/bar");
    }

    public static void main(String []args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }

}
