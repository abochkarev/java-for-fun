package com.tasks.java8;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BehaviourParametrization {
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter predicate) {
        for (Apple apple : inventory) {
            String output = predicate.accept(apple);
            System.out.println(output);
        }
    }

    public static List<Apple> findApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("red", 150), new Apple("green", 100), new Apple("yellow", 120));
        list.sort((Apple apple1, Apple apple2) -> apple1.getWeight().compareTo(apple2.getWeight()));
        prettyPrintApple(list, Apple::toString);
        prettyPrintApple(list, new AppleColorFormatter());
        prettyPrintApple(list, new AppleWeightFormatter());
        prettyPrintApple(list, new AppleFormatter() {

            @Override
            public String accept(Apple apple) {
                return "The apple weight is " + apple.getWeight() + " and color is " + apple.getColor();
            }
        });
        list = findApples(list, (Apple apple) -> "red".equals(apple.getColor()));
        prettyPrintApple(list, Apple::toString);
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(() -> System.out.println("Hello world!"));
        exec.shutdown();
    }

    @FunctionalInterface
    interface AppleFormatter {
        String accept(Apple apple);
    }


    interface ApplePredicate {
        boolean test(Apple apple);
    }

    static class Apple {
        private String color;
        private Integer weight;

        public Apple(String color, Integer weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public Integer getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class AppleColorFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            return apple.getColor();
        }
    }

    static class AppleWeightFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            return apple.getWeight().toString();
        }
    }
}
