package com.tasks;

interface Cycle {
    String getName();
}

interface CycleFactory {
    Cycle getCycle();
}

class Unicycle implements Cycle {
    public String getName() {
        return "Unicycle";
    }
}

class Bicycle implements Cycle {
    public String getName() {
        return "Bicycle";
    }
}

class Tricycle implements Cycle {
    public String getName() {
        return "Tricycle";
    }
}

class UnicycleFactory implements CycleFactory {
    public Cycle getCycle() {
        return new Unicycle();
    }
}

class BicycleFactory implements CycleFactory {
    public Cycle getCycle() {
        return new Bicycle();
    }
}

class TricycleFactory implements CycleFactory {
    public Cycle getCycle() {
        return new Tricycle();
    }
}

