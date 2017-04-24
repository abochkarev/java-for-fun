package com.tasks.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Optionals {

    public static void main(String []args) {
        Map<String, String> map = new HashMap<String, String>(){{
            put("val", "val");
        }};
        String string = Optional.ofNullable(map.get("val")).orElse("");
        System.out.println("Returned " + string);

        string = Optional.ofNullable(map.get("val1")).orElse("");
        System.out.println("Returned " + string);
    }
}
