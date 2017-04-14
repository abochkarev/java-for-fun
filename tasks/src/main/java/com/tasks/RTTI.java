package com.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

interface A {
}

interface B {
}

interface C {
}

class Candy implements Cloneable {
    static {
        System.out.println("Loading Candy");
    }

    Candy() {
        System.out.println("Creating Candy");
    }

    Candy(int i) {
    }
}

class Gum extends Candy implements A, B, C {
    static {
        System.out.println("Loading Gum");
    }

    Gum(int i) {
        //super(i);
    }
}

class Cookie {
    static {
        System.out.println("Loading Cookie");
    }
}

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = RTTI.rand.nextInt(1000);

    static {
        System.out.println("Initializing Initable");
    }

}

class Initable2 {
    static int staticNonFinal = 147;

    static {
        System.out.println("Initializing Initable2");
    }

}

class Initable3 {
    static int staticNonFinal = 74;

    static {
        System.out.println("Initializing Initable3");
    }
}


public class RTTI {
    public static Random rand = new Random(47);

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("inside main");
        new Candy(0);
        new Gum(0);
        new Candy(0);
        new Gum(0);
        System.out.println("After creating Candy");
        try {
            Class.forName("com.tasks.Gum");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn’t find Gum");
        }
        System.out.println("After Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");

        Class c = null;
        try {
            c = Class.forName("com.tasks.Gum");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't find Gum");
            System.exit(1);
        }
        System.out.println(c);
        for (Class face : c.getInterfaces()) {
            System.out.println(face);
        }
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        } catch (InstantiationException ie) {
            System.out.println("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException ie) {
            System.out.println("Cannot access");
            System.exit(1);

        }
        System.out.println(obj.getClass());

        Map<Class, Class> map = new HashMap<Class, Class>() {{
            put(boolean.class, Boolean.TYPE);
            put(char.class, Character.TYPE);
            put(short.class, Short.TYPE);
            put(int.class, Integer.TYPE);
        }};
        for (Map.Entry<Class, Class> entry : map.entrySet()) {
            System.out.println(entry.getKey() == entry.getValue());
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        System.out.println(Initable.staticFinal);
        System.out.println(Initable.staticFinal2);
        System.out.println(Initable2.staticNonFinal);
        Class initable3 = Class.forName("com.tasks.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);

        Class<? extends Number> a = int.class;
        a = double.class;
        System.out.println(a);

    }

}
