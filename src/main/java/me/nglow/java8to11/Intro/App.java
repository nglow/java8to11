package me.nglow.java8to11.Intro;


public class App {

    public static void main(String[] args) {
        // 익명 내부 클래스(anonymous inner class)
        RunSomething anonymousInnerClass = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
                System.out.println("2줄은 람다표현식으로 변환이 안됨");
            }
        };

        // Lambda expression
        RunSomething lambdaExpression  = () -> System.out.println("lambda");

        // Pure function
        PureFunction pureFunction = (number) -> number + 10;

        // 같은값이 들어가면 같은값이 나와야한다 -> 상태를 가지지 않음(Statless) -> 함수형 프로그래밍 (Functional Programming)
        System.out.println(1);
        System.out.println(1);
        System.out.println(1);

        System.out.println(2);
        System.out.println(2);
        System.out.println(2);
    }
}
