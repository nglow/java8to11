package me.nglow.java8to11.lambda_expression;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) {
        Supplier<Integer> get10 = () -> 10;

        BinaryOperator<Integer> sum = (a, b) -> a + b;

        App app = new App();
        app.run();
    }

    private void run() {
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11
            }
        };

        // 람다
        IntConsumer printInt = (i) -> System.out.println(i + baseNumber);
        // run과 같은 scope이기 때문에 shadowing을 시도할 수 없음
        // effective final이거나 final인 변수만 참조가능

        /**
         * 공통점: 위의 baseNumber 참조가능
         * 람다와 나머지의 차이점: Shadowing -> 람다는 shadowing이 일어나지 않음
         */
    }
}
