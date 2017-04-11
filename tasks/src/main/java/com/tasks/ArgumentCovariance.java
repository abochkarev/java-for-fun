package com.tasks;

class A1 {
    private String s = "a1";

    @Override
    public String toString() {
        return "A1{" +
                "s='" + s + '\'' +
                '}';
    }
}

class B1 extends A1 {
    private String s = "a2";

    @Override
    public String toString() {
        return "B1{" +
                "s='" + s + '\'' +
                "} " + super.toString();
    }
}

class C1 extends B1 {
    private String s = "a3";

    @Override
    public String toString() {
        return "C1{" +
                "s='" + s + '\'' +
                "} " + super.toString();
    }
}

class AA {

    public AA() {
    }

    A1 getInstance() {
        return new A1();
    }
}

class BB extends AA {

    public BB() {
        super();
    }

    B1 getInstance() {
        return new B1();
    }
}

class CC extends BB {

    public CC() {
        super();
    }

    C1 getInstance() {
        return new C1();
    }
}


public class ArgumentCovariance {

    public static void main(String[] args) {
        AA[] array = {new AA(), new BB(), new CC()};
        for (AA aa : array) {
            System.out.println(aa.getInstance());
        }
    }
}
