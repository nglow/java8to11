package me.nglow.java8to11.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        // 한개
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });

        executorService.shutdown(); // Graceful shutdown - 현재 진행중인 작업은 끝까지 마치고 죽음
//        executorService.shutdownNow(); // 그냥 죽음

        // 여러개
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.submit(getRunnable("hello1: "));
        executorService1.submit(getRunnable("hello2: "));
        executorService1.submit(getRunnable("hello3: "));
        executorService1.submit(getRunnable("hello4: "));
        executorService1.submit(getRunnable("hello5: "));

        executorService1.shutdown();

        // 한개 - 지연후 실행
        var scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(getRunnable("Scheduled: "), 3, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();

        // 한개 - 반복실행
        var scheduledExecutorService2 = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService2.scheduleAtFixedRate(getRunnable("Scheduled: "), 1, 2, TimeUnit.SECONDS);
    }

    // Runnable 은 void이기 때문에 Return값이 없음. return값을 받고싶으면 Callable을 사용해야함.
    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
