package sandbox;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadDemo {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                executorService.submit(() -> {
                    for (int j = 0; j < 10; j++) {
                        System.out.println("Hello, I am " + Thread.currentThread());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                });
            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println("time = " + time + " ms");
    }
}
