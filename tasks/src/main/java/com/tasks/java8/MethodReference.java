package com.tasks.java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {

    private static Map<String, Function<Integer, Entity>> map = new HashMap<>();

    static {
        map.put("entity1", Entity::new);
        map.put("entity2", Entity::new);
    }

    private static Entity giveMeEntity(String str, Integer integer) {
        return map.get(str)
                .apply(integer);
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("c", "B", "aa", "BB", "CC");
        strings.sort(String::compareTo);
        System.out.println(strings);

        strings = Arrays.asList("11111", "-11", "8222", "433333333", "2111111111");
        strings.sort(Comparator.comparing(String::length));
        System.out.println(strings);

        strings = Arrays.asList("a", "b", "A", "B");
        strings.sort(String::compareToIgnoreCase);

        Supplier<String> supplier = String::new;
        String s = supplier.get();

        Supplier<String> supplier1 = () -> "123";
        String s1 = supplier1.get();

        Entity entity1 = giveMeEntity("entity1", 1);
        Entity entity2 = giveMeEntity("entity2", 2);

        System.out.println(entity1);
        System.out.println(entity2);


    }

    static class Entity {
        private final int a;

        public Entity(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "a=" + a +
                    '}';
        }
    }
}
