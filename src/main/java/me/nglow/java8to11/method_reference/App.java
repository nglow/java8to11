package me.nglow.java8to11.method_reference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

    public static void main(String[] args) {
        // Method reference using static method
        UnaryOperator<String> hi = (s) -> "hi" + s;
        UnaryOperator<String> hi2 = Greeting::hi;

        // Method reference using instance method
        var greeting = new Greeting();
        UnaryOperator<String> hello = (s) -> "hello" + s;
        UnaryOperator<String> hello2 = greeting::hello;

        // Method reference using constructor with argument
        Function<String, Greeting> newGreeting = (s) -> new Greeting(s);
        Function<String, Greeting> newGreeting2 = Greeting::new;
        var instance = newGreeting.apply("name");
        var instance2 = newGreeting2.apply("name");

        // Method reference using constructor without argument
        Supplier<Greeting> newGreeting3 = () -> new Greeting();
        Supplier<Greeting> newGreeting4 = Greeting::new;
        var instance3 = newGreeting3.get();
        var instance4 = newGreeting4.get();

        // Method reference using arbitrary reference
        String[] names = {"nam", "taekhyeon", "othername"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }
}
