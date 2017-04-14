package com.tasks;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFast {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(Arrays.asList("Hello ", "World "));
        Iterator<String> iterator = strings.iterator();
        strings.add("!");
        try {
            while (iterator.hasNext()) {
                iterator.next();
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Unable to read a value from the list");
        }

        strings = new CopyOnWriteArrayList<>(Arrays.asList("World ", "Hello "));
        iterator = strings.iterator();
        strings.add("!");
        try {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Unable to read a value from the list");
        }

    }
}
