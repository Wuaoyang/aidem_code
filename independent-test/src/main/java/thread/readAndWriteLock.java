package thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readAndWriteLock {
    static Lock lock = new ReentrantLock(true);
    private static int value;
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try {
            lock.lock();
//            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "read over!");
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable readR = () -> read(lock);
        //Runnable readR=()->read(readLock);
        //Runnable writeR=()->write(writeLock,new Random().nextInt());
        Runnable writeR = () -> write(lock, new Random().nextInt());
        for (int i = 0; i < 1000; i++) {

            new Thread(readR, "thread" + i).start();
        }
        for (int i = 0; i < 2; i++) new Thread(writeR, "thread" + i).start();
    }
}
