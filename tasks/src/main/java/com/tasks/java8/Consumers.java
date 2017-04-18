package com.tasks.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Consumers {

    private static <T> void forEach(List<T> lst, Consumer<T> consumer) {
        for (T t : lst) {
            consumer.accept(t);
        }
    }

    public static void main(String[] args) {
        forEach(Arrays.asList(1, 2, 3, 4, 5, 6), (Integer i) -> System.out.println(i));
    }
}
