package com.tasks;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;


public class TestStack {

    @Test
    public void testStack() throws Exception {
        Stack<Integer> stack = new Stack<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
        }};

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }

        assertEquals(0, stack.size());
    }
}
