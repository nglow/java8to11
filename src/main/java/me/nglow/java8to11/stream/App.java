package me.nglow.java8to11.stream;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        var names = new ArrayList<String>();
        names.add("taekhyeon");
        names.add("nglow");
        names.add("nglow90");
        names.add("foo");

        /**
         * stream은 데이터를 변경하지 않음
         */
        var stringStream1 = names.stream().map(String::toUpperCase);
        names.forEach(x -> System.out.println(x));

        /**
         * stream은 lazy함 ->아래가 출려되지 않음.
         * Terminal operator가 사용되지 않으면 실행되지 않면.
         */
        var stringStream2 = names.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        /**
         * parallelStream을 사용하여 병렬처리가 가능함
         * Multi Thread라고 해서 모두 빠른게 아님
         * 쓰레드를 만들고 병렬처리를 돌리는데 비용이 발생함
         * 한 스레드에서 처리하는게 빠를때
         * 데이터가 엄청나게 큰경우가 아니면 parallelSteam()이 효과가 없을수 있다.
         */
        var collect = names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
