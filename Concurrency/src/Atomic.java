import javax.annotation.processing.Processor;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.*;

public class Atomic {

    /**
     * <h3>Các nhiệm vụ chung của gói java.util.concurrent.atomic bao gồm:</h3>
     *
     * <p>Đảm Bảo Atomicity (Tính Nguyên Tố): Các phương thức và lớp trong gói này được thiết kế để đảm bảo rằng các hoạt động trên biến nguyên tố sẽ hoàn thành một cách nguyên tố, tức là không thể bị gián đoạn bởi các luồng khác trong khi đang thực thi.</p>
     * <p>Đảm Bảo Nhất Quán (Consistency): Các phương thức và lớp trong gói này cung cấp các cơ chế để đảm bảo tính nhất quán khi thực hiện các thao tác trên các biến nguyên tố trong môi trường đồng thời.</p>
     * <p>Không Cần Sử Dụng Locks (Lock-Free): Sử dụng các lớp và giao diện trong gói này giúp tránh việc sử dụng các cơ chế đồng bộ hóa như locks, giúp giảm thiểu sự chậm trễ và tăng hiệu suất trong các ứng dụng đồng thời.</p>
     */
    public static void main(String[] args) {
        // Các loại Atomic cho Integer, Long, Array,...
        // AtomicReference là flexible nhất rồi :))
        // Ví dụ dưới đây sử dụng AtomicString
        AtomicReference<String> atomicString = new AtomicReference<>("Hello");
        atomicInThread(atomicString);

        System.out.println("Final value of atomicString: " + atomicString.get());

    }

    private static void atomicInThread(AtomicReference<String> atomicString) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicString.updateAndGet(s -> s + " + 1");
            }

        });

        Thread thread2 = new Thread(() -> {
            atomicString.updateAndGet(s -> s + " + 2");

        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}