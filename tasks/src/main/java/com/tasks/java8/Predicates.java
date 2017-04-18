package com.tasks.java8;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Predicates {

    private static <T> List<T> filter(List<T> lst, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : lst) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> lst = Arrays.asList("1", "2", "33", "", null);
        System.out.println(filter(lst, (String s) -> !(s == null || s.isEmpty())));

    }
}
