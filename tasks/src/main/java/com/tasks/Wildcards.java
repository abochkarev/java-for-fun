package com.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Fruit {
}

class Apple extends Fruit {
}

class Jonathan extends Apple {
}

class Orange extends Fruit {
}

public class Wildcards {

    public static void main(String[] args) {
        List<? extends Fruit> fruits = Arrays.asList(new Fruit());
        // impossible to add a Fruit to fruits
        // fruits.add(new Fruit());

        //but possible to get Fruit w/o casting
        Fruit fruit = fruits.get(0);

        List<Fruit> fruits1 = new ArrayList<>();
        fruits1.add(new Fruit());
        fruits1.add(new Apple());
        fruits1.add(new Jonathan());
        fruits1.add(new Orange());
        System.out.println(fruits1);

        List<? super Apple> apples = new ArrayList<>();
        // impossible to add a Fruit because it is not in bound
        //apples.add(new Fruit());

        apples.add(new Apple());
        apples.add(new Jonathan());
        System.out.println(apples);
    }

}
