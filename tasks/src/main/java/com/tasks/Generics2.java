package com.tasks;

class Generator<T> {
    protected final Class<T> type;

    protected Generator(Class<T> type) {
        this.type = type;
    }

    protected static <T> T create(Class<T> t) {
        return new Generator<>(t).generate();
    }

    protected T generate() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class NumbGenerator<T extends Number> extends Generator<T> {

    protected NumbGenerator(Class<T> type) {
        super(type);
    }

    @SuppressWarnings("unchecked")
    protected static <T> T create(Class<T> t) {
        return (T) new NumbGenerator(t).generate();
    }

    @Override
    protected T generate() {
        try {
            return super.type.getDeclaredConstructor(String.class).newInstance("0");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


public class Generics2 {

    public static void main(String[] args) {
        String st = Generator.create(String.class);
        System.out.println(st);
        Object ob = Generator.create(Object.class);
        System.out.println(ob);
        Integer in = NumbGenerator.create(Integer.class);
        System.out.println(in);
        Double dou = NumbGenerator.create(Double.class);
        System.out.println(dou);
        Long lo = NumbGenerator.create(Long.class);
        System.out.println(lo);
    }

}
