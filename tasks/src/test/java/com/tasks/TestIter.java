package com.tasks;

import org.junit.Test;

import java.util.*;

public class TestIter {

    public static final Set<String> set = Collections.unmodifiableSet(new HashSet<String>() {{
        add("a");
        add("b");
        add("c");
    }});

    public static final List<Integer> list = Collections.unmodifiableList(new ArrayList<Integer>() {{
        add(1);
        add(2);
        add(3);
    }});


    public static final List<Dummy> dummies = Collections.unmodifiableList(new ArrayList<Dummy>() {{
        add(new Dummy("1", "2"));
        add(new Dummy("3", "4"));
    }});

    @Test
    public void test() throws Exception {
        set.iterator().forEachRemaining(System.out::println);
        list.iterator().forEachRemaining(System.out::println);
        dummies.iterator().forEachRemaining(System.out::println);
    }

    private static class Dummy {
        private final String a;
        private final String b;

        public Dummy(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Dummy{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    '}';
        }
    }
}
