public class CustomThread extends Thread{
    @Override
    public synchronized void start() {
        ThreadLocal<CustomThread> threadLocal = new ThreadLocal<>();
        System.out.println("Do something in CustomThread");
    }
}
