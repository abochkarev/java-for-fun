package com.tasks;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestFactory {
    @Test
    public void test() throws Exception {
        Map<String, CycleFactory> expected = new HashMap<String, CycleFactory>(3) {{
            put("Unicycle", new UnicycleFactory());
            put("Bicycle", new BicycleFactory());
            put("Tricycle", new TricycleFactory());
        }};
        for (Map.Entry<String, CycleFactory> entry : expected.entrySet()) {
            assertEquals(entry.getKey(), entry.getValue().getCycle().getName());
        }

    }
}
