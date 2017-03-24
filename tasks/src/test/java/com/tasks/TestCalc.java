package com.tasks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalc {

    @Test
    public void testFactorial() {
        Calc calc = new Calc();
        assertEquals(120, calc.factorial(5));
    }

}
