import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        CustomThread customThread = new CustomThread();
        customThread.start();
        CustomRunnable customRunnable = new CustomRunnable();
        customRunnable.run();
        System.out.println("Hello world!");

        AtomicReference<String> string = new AtomicReference<>("");
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i < 6; i++) {
            int finalI = i;
            executor.execute(() -> {
                string.set(string + Character.toString(finalI));
                System.out.println(string+ "\n");
            });
        }

//        executor.submit(() -> {
//            string.set(string + "a ");
//            System.out.println(string + "\n");
//        });
        executor.shutdown(); // Dừng ExecutorService sau khi tất cả các công việc được thực thi
    }

}