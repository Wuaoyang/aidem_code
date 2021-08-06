package thread;

import java.text.ParseException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 现在两个线程，轮流操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，交替进行。
 * 也就是一直 1、0、1、0、1、0、1、0 .....1、0
 * Condition（await、signal、signalAll） 实现
 */
public class ShareDate {
    public static void main(String[] args) throws ParseException {
        Data data = new Data();
        new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    data.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    data.decrease();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

}

class Data {

    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (count != 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "：" + ++count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decrease() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "：" + --count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
