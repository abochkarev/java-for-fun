package com.tasks;

public class Calc {

    public int factorial(int i) {
        return (i == 1) ? 1 : factorial(i-1)*i;
    }

}
