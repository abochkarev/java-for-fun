package com.tasks;

import java.text.DateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import static com.tasks.Enums.AlarmPoints.*;

public class Enums {

    interface Command {
        void action();
    }

    enum AlarmPoints {
        STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3,
        OFFICE4, BATHROOM, UTILITY, KITCHEN
    }

    enum ConstantSpecificMethods {
        DATE_TIME {
            @Override
            String getInfo() {
                return DateFormat.getDateInstance().format(new Date());
            }
        },
        CLASSPATH {
            @Override
            String getInfo() {
                return System.getenv("CLASSPATH");
            }
        },
        VERSION {
            @Override
            String getInfo() {
                return System.getProperty("java.version");
            }
        };

        abstract String getInfo();
    }

    public static void main(String []args) {
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
        points.forEach(System.out::println);
        System.out.println("-------------------");
        points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        points.forEach(System.out::println);
        System.out.println("-------------------");
        points = EnumSet.allOf(AlarmPoints.class);
        points.forEach(System.out::println);
        System.out.println("-------------------");
        points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
        points.forEach(System.out::println);
        System.out.println("-------------------");
        points = EnumSet.complementOf(points);
        points.forEach(System.out::println);

        EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
        em.put(KITCHEN, () -> System.out.println("Kitchen fire!"));
        em.put(BATHROOM, () -> System.out.println("Bathroom alert!"));
        em.put(LOBBY, () -> System.out.println("Lobby fire!"));

        for (Map.Entry<AlarmPoints, Command> me:  em.entrySet()) {
                me.getValue().action();
        }

        try {
            em.get(UTILITY).action();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (ConstantSpecificMethods csm : ConstantSpecificMethods.values()) {
            System.out.println(csm.getInfo());
        }
    }
}
