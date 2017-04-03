package com.tasks;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestStrategy {

    @Test
    public void test() throws Exception {
        String input = "HeLlO!";
        Map<String, Processor> inputMap = new HashMap<String, Processor>() {{
            put("HELLO!", new UpperPrinter(input));
            put("hello!", new LowerPrinter(input));
        }};

        for (Map.Entry<String, Processor> entry : inputMap.entrySet()) {
            assertEquals(entry.getKey(), entry.getValue().process());
        }
    }
}
