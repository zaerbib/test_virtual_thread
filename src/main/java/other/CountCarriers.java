package other;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountCarriers {

    public static void main(String[] args) {
        Set<String> carrierNames = ConcurrentHashMap.newKeySet();

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 200_000; i++) {
                executorService.submit(
                        () -> {
                            for (int j = 0; j < 20; j++) {
                                String carrier = getCarrier();
                                boolean added = carrierNames.add(carrier);

                                if (added) {
                                    System.out.println(carrier);
                                }

                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {

                                }
                            }
                        }
                );
            }
        }
        System.out.println("Number of carriers: " + carrierNames.size());
    }

    private static String getCarrier() {
        String s = Thread.currentThread().toString();
        int atPos = s.indexOf('@');

        if (atPos == -1) {
            throw new IllegalArgumentException(s);
        }
        return s.substring(atPos + 1);
    }
}
