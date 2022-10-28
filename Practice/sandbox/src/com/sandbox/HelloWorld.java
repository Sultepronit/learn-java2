package com.sandbox;

import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, IDEA");
        System.out.println(args[0]);
        List<String> items = List.of("1", "a", "2", "a", "3", "a");
        items.forEach(item -> {
            if(item.equals("a")) {
                System.out.println("A");
            } else {
                System.out.println("Not A");
            }
        });

        System.out.println("PI = " + PI);
        System.out.println( sqrt(5.0) );
    }
}
