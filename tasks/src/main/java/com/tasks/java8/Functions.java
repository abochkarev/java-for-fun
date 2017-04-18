package com.tasks.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Functions {

    private static <T, R> List<R> apply(List<T> lst, Function<T, R> func) {
        List<R> result = new ArrayList<>();
        for (T item : lst) {
            result.add(func.apply(item));
        }
        return result;
    }


    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Some", "Simple", "Stings");
        System.out.println(apply(strings, String::toLowerCase));
        System.out.println(apply(strings, String::toUpperCase));
        System.out.println(apply(strings, String::length));
    }
}
