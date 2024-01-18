package presentation;

import java.util.concurrent.atomic.AtomicLong;

public class HowManyThreadHelper {

    private static final long SLEEP_MILLIS = 100;

    public static void doSomeThing() {
        long millis = 0;
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(SLEEP_MILLIS);
                millis += SLEEP_MILLIS;
            } catch(InterruptedException e) {
                Thread.currentThread().isInterrupted();
            }
        }
        System.out.println("I died after " + millis + " milliseconds");
    }

    public static void waitForVirtualThreadsToCatchup
            (int startedThreads, AtomicLong runningThreadCounter, long startTime) throws InterruptedException {
        long runningThreads;
        while(startedThreads > (runningThreads = runningThreadCounter.get())) {
            long time = System.currentTimeMillis() - startTime;
            System.out.printf("Waiting for virtual threads to catch up : (%,d running after %,d ms) ...%n",
                    runningThreads, time);
            Thread.sleep(100);
        }

        long time = System.currentTimeMillis() - startTime;
        System.out.printf("Virtual threads caught up : %,d running after %,d", runningThreads, time);
    }
}
