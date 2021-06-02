package me.nglow.java8to11.callable_future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        var helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone());
        System.out.println("Started!");

        // Blocking Call
        System.out.println(helloFuture.get());
//        helloFuture.cancel(false);

        System.out.println(helloFuture.isDone());
        System.out.println("End!!");
        executorService.shutdown();

        // 여러개 모두
        var executorService2 = Executors.newSingleThreadExecutor();
        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> spring = () -> {
            Thread.sleep(1000L);
            return "Spring";
        };
        // 모두가 끝날때까지 기다리기 때문에 제일 긴 시간이 걸리는 Java가 끝나야 비로소 출력이 됨
        var futures = executorService2.invokeAll(Arrays.asList(hello, java, spring));
        for (Future<String> x : futures) {
            System.out.println(x.get());
        }

        executorService2.shutdown();

        // 여러개중 한가지라도 된다면 그걸
        // 여러개의 서버에 백업된 같은 자료를 요청했을때 먼저온것만 받으면 되는 경우
        var executorService3 = Executors.newFixedThreadPool(4);
        var futures2 = executorService3.invokeAny(Arrays.asList(hello, java, spring));
        System.out.println(futures2);
        executorService3.shutdown();

    }
}
