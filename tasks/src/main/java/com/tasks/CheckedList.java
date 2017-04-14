package com.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Dog {
}

class Cat {
}

public class CheckedList {

    private static void insertCat(List probablyDogs, Cat cat) {
        probablyDogs.add(cat);
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        insertCat(dogs, new Cat());

        List<Dog> dogs1 = Collections.checkedList(new ArrayList<>(), Dog.class);
        try {
            insertCat(dogs1, new Cat());
        } catch (ClassCastException e) {
            System.out.println(String.format("Unable to insert a Cat object to a Dogs list: %s", e));
        }
    }
}
