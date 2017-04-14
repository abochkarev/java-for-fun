package com.tasks;

import java.util.List;

import static java.util.Collections.singletonList;

class Holder<T> {
    T o1, o2, o3;

    Holder(T o1, T o2, T o3) {
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
    }

    public T getO1() {
        return o1;
    }

    public void setO1(T o1) {
        this.o1 = o1;
    }

    public T getO2() {
        return o2;
    }

    public void setO2(T o2) {
        this.o2 = o2;
    }

    public T getO3() {
        return o3;
    }

    public void setO3(T o3) {
        this.o3 = o3;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "o1=" + o1 +
                ", o2=" + o2 +
                ", o3=" + o3 +
                '}';
    }
}

public class Generics {

    public static void main(String[] args) {
        Holder<String> stringHolder = new Holder<>("a", "b", "c");
        Holder<Integer> integerHolder = new Holder<>(1, 2, 3);
        Holder<List> listHolder = new Holder<>(singletonList(1), singletonList(2), singletonList(3));
        System.out.println(stringHolder);
        System.out.println(integerHolder);
        System.out.println(listHolder);
    }
}
