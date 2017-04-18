package com.tasks.java8;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Type {MEAT, FISH, OTHER}
}


public class Streams {
    private static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    private static void print() {
        Arrays.asList("*************\n".toCharArray()).stream().forEach(System.out::print);
    }

    public static void main(String[] args) {
        List<String> threeHighCaloricDishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        System.out.println(threeHighCaloricDishes);
        print();

        List<String> names = menu.stream().filter(d -> {
            System.out.println("filtering " + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("mapping " + d.getName());
            return d.getName();
        }).limit(3).collect(toList());
        System.out.println(names);
        print();

        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> stream = title.stream();
        stream.forEach(System.out::println);
        try {
            stream.forEach(System.out::println);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        stream = title.stream();
        stream.forEach(System.out::println);
        print();

        List<Dish> vegeterianDishes = menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println(vegeterianDishes);
        print();

        //distinct - removing duplicates
        List<Integer> ints = Arrays.asList(1, 2, 4, 1, 3, 5, 1, 4, 7, 8, 3, 2, 5, 7, 8);
        ints.stream().filter(i -> i % 2 != 0).distinct().forEach(System.out::println);
        print();

        //skip two first dishes
        menu.stream().filter(d -> d.getCalories() > 300).skip(2).limit(2).forEach(System.out::println);
        print();

        //select two meat dishes
        menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).limit(2).forEach(System.out::println);
        print();

        //there's a list of words - return a list of lengths
        Arrays.asList("1", "22", "333").stream().map(String::length).forEach(System.out::println);
        print();
        //return a list of unique characters - doesn't work
        List<String> lst = Arrays.asList("Hello", "World");
        List<String[]> result = lst.stream().map(word -> word.split("")).distinct().collect(toList());
        System.out.println(result);
        print();

        //return a list of unique characters - doesn't work
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        result = streamOfWords.map(word -> word.split("")).distinct().collect(toList());
        System.out.println(result);
        print();

        //return a list of unique characters - works!
        List<String> finalResult = lst.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        finalResult.stream().forEach(System.out::print);
        print();

        //return a list of the square of each number
        int[] nums = {1, 2, 3, 4, 5};
        Arrays.stream(nums).map(n -> n * n).forEach(System.out::println);
        print();

        //return all pairs of numbers
        List<Integer> lst1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> lst2 = Arrays.asList(8, 9, 10);
        List<List<Integer>> pairs = lst1.stream().flatMap(i -> lst2.stream().map(j -> Arrays.asList(i, j))).collect(toList());
        pairs.stream().forEach(System.out::println);
        print();


        boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println(isHealthy);
        print();

        isHealthy = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealthy);
        print();

        //find any vegetarian dish and print if exists
        Optional<Dish> opt = menu.stream().filter(Dish::isVegetarian).findAny();
        opt.ifPresent(System.out::println);
        print();

        Dish dish = menu.stream().findFirst().get();
        System.out.println(dish);
        print();
    }

}
