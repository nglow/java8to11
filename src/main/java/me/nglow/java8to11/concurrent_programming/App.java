package me.nglow.java8to11.concurrent_programming;


public class App {

    public static void main(String[] args) throws InterruptedException {
        // Main thread
        System.out.println(Thread.currentThread().getName());

        // Make thread 1
        // Thread는 순서를 보장하지 못한다
        var myThead = new MyThread();
        myThead.start();

        System.out.println("hello");

        // Make thread 2 & Interrupt
        var myThread2 = new Thread(() -> {
            while (true) {
                System.out.println("Thread2: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    return;
                }
            }
        });
        myThread2.start();

        System.out.println("hello2: " + Thread.currentThread().getName());
        Thread.sleep(3000L);

        myThread2.interrupt();;

        // Join - thread가 끝낫을 때까지 기다림 -> 사실상 Programmer가 수많은 쓰레드를 직접 관리하기는 어려움 -> Executor등장
        var myThread3 = new Thread(() -> {
            System.out.println("Thread2: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                System.out.println("exit!");
                return;
            }
        });
        myThread3.start();

        System.out.println("Hello3: " + Thread.currentThread().getName());
        myThread3.join();
        System.out.println(myThread3 + " is finished");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
