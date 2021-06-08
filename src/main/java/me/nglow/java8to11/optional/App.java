package me.nglow.java8to11.optional;


import java.util.ArrayList;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        var springClasses = new ArrayList<OnlineClass>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring security", false));

        var spring1 = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        spring1.ifPresent(oc -> System.out.println(oc.getTitle()));

        // 파라미터가 인스턴스
        var onlineClass1 = spring1
                .orElse(createNewClass());
        System.out.println(onlineClass1.getTitle());

        // 파라미터가 함수형 인터페이스
//        var orElseGet = spring1.orElseGet(() -> createNewClass());
        var orElseGet1 = spring1
                .orElseGet(App::createNewClass);
        System.out.println(orElseGet1.getTitle());

        var orElseThrow = spring1
                .orElseThrow(() -> new IllegalArgumentException("없어!"));

        var filter1 = spring1.filter(oc -> !oc.isClosed());

        var map = spring1.map(OnlineClass::getProgress);

        var flatMap = spring1.flatMap(OnlineClass::getProgress);

        var spring2 = springClasses
                .stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        spring2.ifPresent(oc -> System.out.println(oc.getTitle()));

        var onlineClass2 = spring2.orElse(createNewClass());
        System.out.println(onlineClass2.getTitle());

        var orElseGet2 = spring2.orElseGet(App::createNewClass);
        System.out.println(orElseGet2.getTitle());

        var orElseThrow2 = spring2
                .orElseThrow(() -> new IllegalArgumentException("없어!"));

        var filter2 = spring2.filter(oc -> !oc.isClosed());

        var map2 = spring2.map(OnlineClass::getProgress);

        var flatMap2 = spring2.flatMap(OnlineClass::getProgress);

    }

    private static OnlineClass createNewClass() {
        return new OnlineClass(10, "New class", false);
    }
}
