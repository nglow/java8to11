package me.nglow.java8to11.method;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class App {

    public static void main(String[] args) {

        Foo foo = new DefaultFoo("Name");
        foo.printName();
        foo.printNameUpperCase();
        Foo.printAnything();
        System.out.println("=========================");

        List<String> names = new ArrayList<>();
        names.add("taekhyeon");
        names.add("nglow");
        names.add("nglow90");
        names.add("foo");

        names.forEach(name -> System.out.println(name));
        names.forEach(System.out::println);
        System.out.println("=========================");

        var spliterator1 = names.spliterator();
        var spliterator2 = spliterator1.trySplit();
        while (spliterator1.tryAdvance(System.out::println)); // 다음 인자가 없으면 false 리턴
        System.out.println("=========================");
        while (spliterator2.tryAdvance(System.out::println));

        System.out.println("=========================");
        names.removeIf(s -> s.startsWith("t"));
        names.forEach(System.out::println);
        System.out.println("=========================");

        names.sort(String::compareToIgnoreCase);
        names.forEach(System.out::println);
        System.out.println("=========================");

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());
//        names.sort(compareToIgnoreCase.reversed().thenComparing()); // 추가정렬 예제
        names.forEach(System.out::println);
    }
}
