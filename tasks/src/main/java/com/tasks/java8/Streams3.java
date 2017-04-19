package com.tasks.java8;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Streams3 {

    public static void main(String[] args) {
        //create a stream
        Stream<String> stream = Stream.of("a", "b", "c");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // sum stream numbers
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        // read lines and count unique words
        try (Stream<String> lines = Files.lines(Paths.get("aws-config.xml"), Charset.defaultCharset())) {
            long uniqueWords = lines.map(w -> w.split("")).distinct().count();
            System.out.println(uniqueWords);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // iteration n + 2
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        // fibonacci
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        // generate 5 random numbers
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
