package com.tasks;

import org.junit.Test;

import static com.tasks.Calc.factorial;
import static org.junit.Assert.assertEquals;

public class TestCalc {

    @Test
    public void testFactorial() {
        assertEquals(120, factorial(5));
    }

}
