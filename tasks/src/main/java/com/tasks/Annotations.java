package com.tasks;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface TypeAnnotation {

}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldAnnotation {

}


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {

}

@TypeAnnotation
public class Annotations {

    @FieldAnnotation
    public int field;

    @MethodAnnotation
    public static void main(String[] args) {
        //find type annotations
        System.out.println(Arrays.toString(Annotations.class.getAnnotations()));

        //find inherited annotations
        System.out.println(Arrays.toString(Annotations2.class.getAnnotations()));

        Field[] fields = Annotations.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(Arrays.toString(field.getDeclaredAnnotations()));
        }

        for (Method method : Annotations.class.getDeclaredMethods()) {
            System.out.println(Arrays.toString(method.getDeclaredAnnotations()));
        }
    }

}

class Annotations2 extends Annotations {

}
