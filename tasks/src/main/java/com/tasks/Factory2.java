package com.tasks;

interface Service {
    String method1();

    String method2();
}

interface ServiceFactory {
    Service getService();
}

class Impl1 implements Service {
    public static ServiceFactory factory = Impl1::new;

    private Impl1() {
    }

    public String method1() {
        return "Impl1 method 1";
    }

    public String method2() {
        return "Impl1 method 2";
    }
}

class Impl2 implements Service {
    public static ServiceFactory factory = Impl2::new;

    private Impl2() {
    }

    public String method1() {
        return "Impl2 method 1";
    }

    public String method2() {
        return "Impl2 method 2";
    }
}
