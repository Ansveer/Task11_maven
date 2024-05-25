package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main
{
    public static final String RESET_COLOR = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static void main( String[] args ) {
        print(new Messages());
    }
    private static void print(Messages msgs) {
        for (Method method: msgs.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Print.class)) {
                Object obj = method.getDeclaredAnnotation(Print.class);
                String clr = obj.toString().substring(26, 36);
                String s1 = obj.toString().substring(46, 47);
                String tmp = "\\";
                if (clr.equals(tmp + "u001b[31m")) {
                    clr = RED;
                } else if (clr.equals(tmp + "u001b[32m")) {
                    clr = GREEN;
                } else if (clr.equals(tmp + "u001b[33m")) {
                    clr = YELLOW;
                } else if (clr.equals(tmp + "u001b[34m")) {
                    clr = BLUE;
                }
                try {
                    String s = clr + s1+s1+s1 + " " + method.invoke(msgs) + " " +s1+s1+s1 + Main.RESET_COLOR;
                    System.out.println(s);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Print {
    String style() default "*";
    String color() default Main.RED;
}
