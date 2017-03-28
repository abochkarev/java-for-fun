package com.tasks;

import org.junit.Test;

import static com.tasks.Sort.bubble;
import static org.junit.Assert.assertArrayEquals;

public class TestSort {

    @Test
    public void testStupidBubble() {
        int[] actual = {8, 6, 1, 5, 7, 4, 9, 2, 3};
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, bubble(actual));
    }
}
