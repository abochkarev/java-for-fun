package com.tasks;

import org.junit.Test;

import static com.tasks.Search.*;
import static org.junit.Assert.assertArrayEquals;

public class TestSearch {

    @Test
    public void testGetTwoMin() throws Exception {
        int args[] = {3, 4, -1, 7, 1, 6, 8, 9, 0, 0};
        assertArrayEquals(new int[]{-1, 0}, getTwoMin(args));
    }

    @Test
    public void testGetTwoMax() throws Exception {
        int args[] = {3, 4, -1, 7, 1, 6, 8, 9, 0, 0};
        assertArrayEquals(new int[]{8, 9}, getTwoMax(args));
    }

    @Test
    public void testDivIntoThree() throws Exception {
        int args[] = {1, 3, 6, 8, 9, 2, 4, 7, 1, 3, 6, 8, 9, 90};
        assertArrayEquals(new Integer[]{3, 6, 9, 3, 6, 9, 90}, divIntoThree(args));
    }

}
