package me.nglow.java8to11.completable_future;


import java.util.Locale;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 기존
         * get() 이후에 future에 있는 데이터를 활용할 수 있음
         */
        var executorService = Executors.newFixedThreadPool(4);
        var future = executorService.submit(() -> "hello");
        System.out.println(future.get());
        executorService.shutdown();

        /**
         * Completable future
         * ForkJoinPool덕분에 ThreadPool을 생성하지않고 동작할 수 있음
         * 아무설정이 없으면 ForkJoinPool에 있는 commonPool을 사용할 수 있음.
         */
//        var completableFuture = new CompletableFuture<String>();
//        completableFuture.complete("taekhyeon");
        var completableFuture = CompletableFuture.completedFuture("taekhyeon");
        System.out.println(completableFuture.get());

        /**
         * 리턴값이 없는 경우
         */
        var runAsync = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        System.out.println(runAsync.get());

        /**
         * 리턴값이 있는경우
         */
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(supplyAsync.get());

        /**
         * 이어오는 콜백함수에 리턴값이 없는 경우
         */
        CompletableFuture<Void> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            s.toUpperCase();
        });
        System.out.println(supplyAsync2.get());

        /**
         * 이어오는 콜백함수에 결과값이 필요하지 않은 경우
         */
        CompletableFuture<Void> supplyAsync3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(supplyAsync3.get());

        /**
         * 별도의 쓰레드풀을 사용할 경우
         */
        var executorService1 = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> supplyAsync4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService1).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, executorService1);
        System.out.println(supplyAsync4.get());
        executorService1.shutdown();

        /**
         * 연관관계가 있는 경우
         */
        var hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        var completableFuture1 = hello.thenCompose(App::getWorld);

        System.out.println(completableFuture1.get());

        /**
         * 연관관계가 없는 경우
         */
        var hello2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        var world2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        var completableFuture2 = hello2.thenCombine(world2, (h, w) -> {
            return h + " " + w;
        });
        System.out.println(completableFuture2.get());

        /**
         * 예외처리
         */
        boolean throwError = true;

        CompletableFuture<String> hello3 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
//        }).exceptionally(ex -> {
//            System.out.println(ex);
//            return "Error!";
//        });
        }).handle((result, ex) -> {
           if (ex != null) {
               System.out.println(ex);
               return "Error!";
           }
           return result;
        });
        System.out.println(hello3.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }

    // 추가적으로 Flow라는 API 공부해보기
}
