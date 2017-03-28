package com.tasks;

public class Sort {

    public static int[] bubble(int[] input) {
        while (true) {
            boolean sorted = true;
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] > input[i + 1]) {
                    int tmp = input[i];
                    input[i] = input[i + 1];
                    input[i + 1] = tmp;
                    sorted = false;
                }
            }
            if (sorted) {
                return input;
            }
        }
    }
}
