package com.tasks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFactory2 {

    @Test
    public void test() {
        assertEquals("Impl1 method 1", Impl1.factory.getService().method1());
        assertEquals("Impl1 method 2", Impl1.factory.getService().method2());
        assertEquals("Impl2 method 1", Impl2.factory.getService().method1());
        assertEquals("Impl2 method 2", Impl2.factory.getService().method2());
    }
}
