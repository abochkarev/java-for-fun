package com.tasks;

import java.util.*;

public class GenericMethod {

    public static <K,V> Map<K,V> map() {
        return new HashMap<>();
    }

    public static <T> List<T> list() {
        return new ArrayList<>();
    }

    public static <T> LinkedList<T> lList() {
        return new LinkedList<>();
    }

    public static <T> Set<T> set() {
        return new HashSet<>();
    }

    public static <T1, T2, T3> void f(T1 t1, T2 t2, T3 t3) {
        System.out.println(t1.getClass().getName());
        System.out.println(t2.getClass().getName());
        System.out.println(t3.getClass().getName());
    }

    public static void mapMethod(Map<String, Integer> map) {
        System.out.println(map);
    }

    public static void listMethod(List<Long> l) {
        System.out.println(l);
    }

    public static void linkedListMethod(LinkedList<Double> ll) {
        System.out.println(ll);
    }

    public static void setMethod(Set<Map> set) {
        System.out.println(set);
    }

    public static void main(String[] args) {
        //method with generics
        f(1, 'c', "S");

        //creating diff types of containers using by generic methods
        Map<String, Integer> m = map();
        List<Long> l = list();
        LinkedList<Double> ll = lList();
        Set<List> s = set();

        //passing results of generic methods
        mapMethod(map());
        listMethod(list());
        linkedListMethod(lList());
        setMethod(set());
    }
}
