package com.tasks;

import java.util.ArrayList;
import java.util.List;

//Marker-interfac
interface Marker {
}

class CountedInteger implements Marker {
    private static int counter;
    private final int id = counter++;

    public String toString() {
        return Integer.toString(id);
    }
}

class CountedLong implements Marker {
    private static long counter;
    private final long id = counter++;

    public String toString() {
        return Long.toString(id);
    }
}

class FilledList<T> {
    private Class<T> clazz;

    public FilledList(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> create(int nElements) {
        List<T> result = new ArrayList<T>();
        try {
            for (int i = 0; i < nElements; i++) {
                result.add(clazz.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

// Generic class references
public class GCR {

    public static void main(String[] args) {
        FilledList<CountedInteger> fl = new FilledList<>(CountedInteger.class);
        System.out.print(fl.create(10));

        FilledList<CountedLong> fl1 = new FilledList<>(CountedLong.class);
        System.out.print(fl1.create(10));

    }


}
