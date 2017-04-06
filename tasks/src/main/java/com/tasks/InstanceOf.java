package com.tasks;

class Building {
}

class House extends Building {
    @Override
    public String toString() {
        return "House";
    }
}

class Garage extends Building {
    @Override
    public String toString() {
        return "Garage";
    }
}

class OutputGenerator {
    void print(House house) {
        System.out.println(house.toString());
    }

    void print(Garage garage) {
        System.out.println(garage.toString());
    }
}

public class InstanceOf {
    public static void main(String[] args) throws Exception {
        String[] types = {"com.tasks.House", "com.tasks.Garage"};
        OutputGenerator outputGenerator = new OutputGenerator();
        for (String type : types) {
            Building b = (Building) Class.forName(type).newInstance();
            if (b instanceof House) {
                outputGenerator.print((House) b);
            }

            if (b instanceof Garage) {
                outputGenerator.print((Garage) b);
            }
        }
    }
}
