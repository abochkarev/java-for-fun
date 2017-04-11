package com.tasks;

import java.util.Date;

interface Base {
    String get();
}

class BaseImpl implements Base {

    private final String s;

    BaseImpl(String s) {
        this.s = s;
    }

    @Override
    public String get() {
        return s;
    }
}

interface TimeStamp {
    long getTimeStamp();
}

class TimeStampImpl implements TimeStamp {

    private final Date date;

    TimeStampImpl() {
        this.date = new Date();
    }

    @Override
    public long getTimeStamp() {
        return date.getTime();
    }
}

interface SerialVersion {
    long getSerialVersion();
}

class SerialVersionImpl implements SerialVersion {
    private static long version = 1;
    private final long serialVersion = version++;

    @Override
    public long getSerialVersion() {
        return serialVersion;
    }
}

public class Mixins implements Base, TimeStamp, SerialVersion {
    Base base = new BaseImpl("Some string");
    TimeStamp timeStamp = new TimeStampImpl();
    SerialVersion serialVersion = new SerialVersionImpl();

    @Override
    public String get() {
        return base.get();
    }

    @Override
    public long getSerialVersion() {
        return serialVersion.getSerialVersion();
    }

    @Override
    public long getTimeStamp() {
        return timeStamp.getTimeStamp();
    }

    @Override
    public String toString() {
        return "Mixins{" +
                "base=" + base.get() +
                ", timeStamp=" + timeStamp.getTimeStamp() +
                ", serialVersion=" + serialVersion.getSerialVersion() +
                '}';
    }

    public static void main(String[] args) {
        Mixins mix1 = new Mixins();
        System.out.println(mix1);
        Mixins mix2 = new Mixins();
        System.out.println(mix2);
        Mixins mix3 = new Mixins();
        System.out.println(mix3);
    }
}
