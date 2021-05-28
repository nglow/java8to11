package me.nglow.java8to11.functional_interface;

import java.util.function.Function;

public class App {

    public static void main(String[] args) {
        var plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(multiply2.apply(2));

        /**
         * Procedure
         * 1. Multiply
         * 2. Plus
         */
        var compose = plus10.compose(multiply2);
        System.out.println(compose.apply(2));

        /**
         * Procedure
         * 1. Plus
         * 2. Multiply
         */
        var andThen  = plus10.andThen(multiply2);
        System.out.println(andThen.apply(2));
    }
}
