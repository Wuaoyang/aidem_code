package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 两个线程，一个线程打印1-52，另一个打印字母A-Z，打印顺序为12A34B...5152Z,要求用线程间的通信。
 */
public class ThreadOne {


    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("集齐了 7 颗龙珠，召唤出了猿兄！实现了一个愿望：所有程序员加薪不加班！");
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println("找到了 " + Thread.currentThread().getName() + " 号龙珠。");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "号龙珠消散了。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

//        for (Integer integer : list) {
//            list.remove(integer);
//        }
//        ThreadOneData data = new ThreadOneData();
//        new Thread(() -> {
//            try {
//                data.printNum();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "A").start();
//        new Thread(() -> {
//            try {
//                data.printWord();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "B").start();
    }

}

class ThreadOneData {

    private int num = 1;
    private char word = 'A';

    public synchronized void printNum() throws InterruptedException {
        while (num > 52) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + ":" + num);
        System.out.println(Thread.currentThread().getName() + ":" + ++num);
        ++num;
        this.notifyAll();
    }

    public synchronized void printWord() throws InterruptedException {
        while (num == 1) {
            this.wait();
        }
        while (word > 'Z') {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + ":" + word);
        word++;
        this.notifyAll();
    }


}
