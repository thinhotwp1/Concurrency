public class CustomRunnable implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
            System.out.println("Do something in CustomRunnable");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
