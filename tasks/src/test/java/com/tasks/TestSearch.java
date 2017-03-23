package com.tasks;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class TestSearch {

    @Test
    public void testGetTwoMin() {
        Search search = new Search();

        int args[] = {3, 4, -1, 7, 1, 6, 8, 9, 0, 0};
        assertArrayEquals(new int[]{-1, 0}, search.getTwoMin(args));
    }

    @Test
    public void testGetTwoMax() {
        Search search = new Search();

        int args[] = {3, 4, -1, 7, 1, 6, 8, 9, 0, 0};
        assertArrayEquals(new int[]{8, 9}, search.getTwoMax(args));
    }
}
