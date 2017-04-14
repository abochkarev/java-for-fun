package com.tasks;

import java.util.Arrays;

enum Shrubbery {
    GROUND, CRAWLING, HANGING;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

enum ExtEnum {
    ONE("One description"), TWO("Two description"), THREE("Three description");
    private String description;

    ExtEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


enum HeroStates {
    HALK(States.HALK_STATES.class), TOR(States.TOR_STATES.class), FLASH(States.FLASH_STATES.class);

    States[] values;

    HeroStates(Class<? extends States> clazz) {
        values = clazz.getEnumConstants();
    }

    interface States {
        enum HALK_STATES implements States {
            RUN, CRUSH, SMASH
        }

        enum TOR_STATES implements States {
            RUN, FLY
        }

        enum FLASH_STATES implements States {
            RUN
        }
    }
}

interface Food {

    enum Beverage implements Food {
        COFEE, TEA, MILK
    }

    enum Dessert implements Food {
        TIRAMISU, FRUIT, ICE_CREAM
    }

}

public class EnumClass {
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            System.out.println(s.ordinal());
            System.out.println(s.name());
            System.out.println(s);
            System.out.println(s.compareTo(Shrubbery.CRAWLING));
            System.out.println(s.equals(Shrubbery.CRAWLING));
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println("-------------------------------");
        }

        for (ExtEnum extEnum : ExtEnum.values()) {
            System.out.println(extEnum.getDescription());
        }

        System.out.println("-------------------------------");

        Arrays.asList(Food.Dessert.values()).forEach(System.out::println);
        Arrays.asList(Food.Beverage.values()).forEach(System.out::println);

        System.out.println("-------------------------------");

        for (HeroStates state : HeroStates.values()) {
            System.out.println(state + ": " + Arrays.toString(state.values));
        }
    }
}
