package com.tasks.java8;


interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}

interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}

interface AA {
    default void hello() {
        System.out.println("Hello from AA");
    }
}

interface BB extends AA {
    default void hello() {
        System.out.println("Hello from BB");
    }
}

interface AAA {
    default void hello() {
        System.out.println("Hello from AAA");
    }
}

interface BBB {
    default void hello() {
        System.out.println("Hello from BBB");
    }
}

interface Root {
    default void hello() {
        System.out.println("Hello from Root");
    }
}


interface A1 extends Root {

}

interface A2 extends Root {

}

class C implements A, B {


}

class CC extends DD implements AA, BB {
}

class DD implements AA {

}

class CCC implements AAA, BBB {

    //method hello must be overriden
    @Override
    public void hello() {
        //explicitly choose to call the method from AAA
        AAA.super.hello();
        //explicitly choose to call the method from BBB
        BBB.super.hello();
        System.out.println("Hello from CCC");
    }
}

class C1 implements A1, A2 {

}

public class DefaultMethods {


    public static void main(String[] args) {
        // will be printer "Hello from B" because B is more specific
        new C().hello();

        //will be printed "Hello from BB" because the class DD doesn't override hello and BB is more specific
        new CC().hello();

        new CCC().hello();

        //will be printed "Hello from Root" because there is actually only one method
        new C1().hello();

    }
}
