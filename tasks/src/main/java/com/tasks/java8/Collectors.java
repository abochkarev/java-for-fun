package com.tasks.java8;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Collectors {
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

    public static void main(String[] args) {
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        menu.stream().collect(maxBy(dishCaloriesComparator)).ifPresent(System.out::println);

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double averageCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(averageCalories);

        IntSummaryStatistics statistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(statistics);

        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);

        shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu);

        Map<Dish.Type, List<Dish>> dishMap = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishMap);

        Map<CaloricLevel, List<Dish>> caloricLevelListMap = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));
        System.out.println(caloricLevelListMap);


        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishTypesMap = menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        })));
        System.out.println(dishTypesMap);


        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Boolean, List<Dish>> partitionedMap = menu.stream().collect(groupingBy(Dish::isVegetarian));
        System.out.println(partitionedMap.get(true));

    }

    public enum CaloricLevel {DIET, NORMAL, FAT}
}
