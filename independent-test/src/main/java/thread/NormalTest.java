package thread;

public class NormalTest {

    /**
     * 现在两个线程，轮流操作初始值为零的一个变量，
     * 实现一个线程对该变量加1，一个线程对该变量减1，交替进行。
     * 也就是一直 1、0、1、0、1、0、1、0 .....1、0
     */
    public static void main(String[] args) throws InterruptedException {
        NewThread newThread = new NewThread();
        new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    newThread.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    newThread.decrease();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

}

class NewThread {

    private int count = 0;

    public synchronized void add() throws InterruptedException {
        while (count != 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "：" +  ++count);
        this.notifyAll();
    }

    public synchronized void decrease() throws InterruptedException {
        while (count == 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "：" +  --count);
        this.notifyAll();
    }
}
