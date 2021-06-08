package me.nglow.java8to11.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App2 {

    public static void main(String[] args) {
        var springClasses = new ArrayList<OnlineClass>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring security", false));

        System.out.println("spring으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getTitle()));

        System.out.println("\nclose 되지 않은 수업");
        springClasses.stream()
//                .filter(oc -> !oc.isClosed())
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getTitle()));

        System.out.println("\n수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(oc -> oc.getTitle())
//                .forEach(s -> System.out.println(s));
                .forEach(System.out::println);

        var javaClasses = new ArrayList<OnlineClass>();
        javaClasses.add(new OnlineClass(6, "The Java Test", true));
        javaClasses.add(new OnlineClass(7, "The java, Code Manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java 8 to 11", false));

        var nglowEvents = new ArrayList<List<OnlineClass>>();
        nglowEvents.add(springClasses);
        nglowEvents.add(javaClasses);

        System.out.println("\n두 수업 목록에 들어가 있는 모든 수업 아이디 출력");
        nglowEvents.stream()
                .flatMap(Collection::stream) // Flatten
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("\n10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        var iterate = Stream.iterate(10, i -> i + 1);
        iterate
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\n자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        var test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("\n스프링 수업 중에 제목에 spring이 들어간 제목만 것만 모아서 List로 만들기");
        var springs = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());

        springs.forEach(System.out::println);
    }
}
