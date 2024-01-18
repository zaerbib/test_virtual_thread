package presentation;

import java.util.concurrent.atomic.AtomicLong;

public class HowManyVirtualThreads {

    private static final int NUMBER_OF_VIRTUAL_THREADS = 100_000_000;
    private static final int PRINT_STEP = Math.min(NUMBER_OF_VIRTUAL_THREADS / 10, 100_000);

    public static void main(String[] args) throws InterruptedException {
        AtomicLong runingThreadsCounter = new AtomicLong();
    }
}
