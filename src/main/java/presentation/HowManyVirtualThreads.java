package presentation;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

public class HowManyVirtualThreads {

    private static final int NUMBER_OF_VIRTUAL_THREADS = 1_000_000;
    private static final int PRINT_STEP = Math.min(NUMBER_OF_VIRTUAL_THREADS / 10, 100_000);

    public static void main(String[] args) throws InterruptedException {
        AtomicLong runningThreadsCounter = new AtomicLong();

        long startTime = System.currentTimeMillis();

        for(int i = 0; i <= NUMBER_OF_VIRTUAL_THREADS; i++) {
            Thread.ofVirtual()
                    .start(
                            () -> {
                                runningThreadsCounter.incrementAndGet();
                                try {
                                    Thread.sleep(Duration.ofDays(100));
                                } catch(InterruptedException e) {

                                }
                                System.out.println("I died");
                            });

            if(i % PRINT_STEP == 0) {
                long runningThreads = runningThreadsCounter.get();
                long time = System.currentTimeMillis() -startTime;

                System.out.printf("%,d virtual threads started, %,d virtual threads running after %,d ms%n",
                        i, runningThreads, time);
                if(i - runningThreads > 100_000) {
                    HowManyThreadHelper.waitForVirtualThreadsToCatchup(i, runningThreadsCounter, startTime);
                }
            }
        }

        HowManyThreadHelper.waitForVirtualThreadsToCatchup(NUMBER_OF_VIRTUAL_THREADS, runningThreadsCounter, startTime);

        Thread.sleep(Duration.ofHours(1));
    }
}
