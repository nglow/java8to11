package me.nglow.java8to11.Intro;


@FunctionalInterface
public interface RunSomething {

    void doIt();

    static void printName() {
        System.out.println("Taekhyeon");
    }

    default void printAge() {
        System.out.println("40");
    }
}
