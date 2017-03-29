package com.tasks;

interface Processor {
    String process();
}

class UpperPrinter implements Processor {
    private String input;

    UpperPrinter(String input) {
        this.input = input;
    }

    @Override
    public String process() {
        return input.toUpperCase();
    }
}

class LowerPrinter implements Processor {
    private String input;

    LowerPrinter(String input) {
        this.input = input;
    }

    @Override
    public String process() {
        return input.toLowerCase();
    }
}

