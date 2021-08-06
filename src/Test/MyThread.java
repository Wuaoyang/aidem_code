package Test;

public class MyThread extends Thread {
    static int i = 0;

    @Override
    public void run() {
        synchronized (MyThread.class) {
            i++;
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new MyThread().start();
        }
    }
}
