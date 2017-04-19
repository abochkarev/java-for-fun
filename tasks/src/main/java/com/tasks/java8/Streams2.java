package com.tasks.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}

class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}

public class Streams2 {
    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Cambridge");
    private static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


    public static void main(String[] args) {
        // sum all numbers
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println(Arrays.stream(nums).reduce(0, (a, b) -> a + b));

        System.out.println("***************************************************");

        //1. Find all transactions in the year 2011 and sort them by value (small to high).
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println("***************************************************");

        //2. What are all the unique cities where the traders work?
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println("***************************************************");

        //3. Find all traders from Cambridge and sort them by name.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("***************************************************");

        //4. Return a string of all traders’ names sorted alphabetically.
        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2));
        System.out.println("***************************************************");

        //5. Are any traders based in Milan?
        boolean milanBased = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);
        System.out.println("***************************************************");

        //6. Print all transactions’ values from the traders living in Cambridge.
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .sorted()
                .forEach(System.out::println);
        System.out.println("***************************************************");

        //7. What’s the highest value of all the transactions?
        transactions.stream().map(Transaction::getValue).reduce(Integer::max).ifPresent(System.out::println);
        transactions.stream().max(Comparator.comparing(Transaction::getValue)).ifPresent(System.out::println);
        transactions.stream().mapToInt(Transaction::getValue).max().ifPresent(System.out::println);
        System.out.println("***************************************************");

        //8. Find the transaction with the smallest value.
        transactions.stream().map(Transaction::getValue).reduce(Integer::min).ifPresent(System.out::println);
        transactions.stream().min(Comparator.comparing(Transaction::getValue)).ifPresent(System.out::println);
        System.out.println("***************************************************");
    }
}
