package com.tasks;

class TupleOne<A> {
    final A value;

    public TupleOne(A value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TupleOne{" +
                "value=" + value +
                '}';
    }
}

class TupleTwo<A, B> extends TupleOne<A> {
    final B value2;

    public TupleTwo(A value1, B value2) {
        super(value1);
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "TupleTwo{" +
                "value2=" + value2 +
                "} " + super.toString();
    }
}

public class Tuple {

    public static void main(String[] args) {
        TupleOne<Integer> t1 = new TupleOne<>(1);
        TupleTwo<Integer, String> t2 = new TupleTwo<>(1, "a");
        System.out.println(t1);
        System.out.println(t2);
    }
}
