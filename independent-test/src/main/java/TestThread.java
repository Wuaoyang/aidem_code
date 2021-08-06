import java.util.concurrent.ConcurrentHashMap;

class TestThread extends Thread {
    private static ConcurrentHashMap map = new ConcurrentHashMap();

    @Override
    public void run() {
        synchronized (map) {
            for (int i = 0; i < 10000; i++)
                System.out.println("i:" + i + ", thread:" + Thread.currentThread().getName());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        TestThread t = new TestThread();

        Thread t1 = new Thread(t, "A");

        Thread t2 = new Thread(t, "B");

        t1.start();
        t2.start();

    }

}
