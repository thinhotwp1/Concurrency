import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Synchronized {
    /**
     * Ở ví dụ dưới đây, khi không sử dụng Synchronized thì giá trị của object Count trong môi trường đa luồng không được chính xác
     * Khi sử dụng Synchronized, Java sẽ tạo ra một khối riêng cho Object đó, các thread muốn sử dụng phải chờ cho khối Synchronized chạy xong mới được thực hiện -> độ chính xác được đảm bảo
     */
    public static void main(String[] args) {
        Count count = new Count();
        Count count1 = new Count();
        ConcurrentHashMap<Integer,String> concurrentHashMap = new ConcurrentHashMap<>();

        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                count.increment();
            }

            synchronized (count1) {
                for (int i = 0; i < 1000; i++) {
                    count1.increment();
                }
            }
        });

        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                count.increment();
            }

            synchronized (count1) {
                for (int i = 0; i < 1000; i++) {
                    count1.increment();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + count.getCount());
        System.out.println("Final count synchronized : " + count1.getCount());
    }
}