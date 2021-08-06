import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.CountDownLatch;

/**
 * @author aidem
 * @date 2021-06-09
 * @description code
 */
public class test00001 {

    public static void main(String[] args) throws ParseException, IOException {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        //蛋糕店老板
        new Thread(() -> {
            try {                //这个线程会被阻塞，直到计数器的值减为 0 。
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "：卖完了每天限量的8个蛋糕，关门休息了。");
        }, "蛋糕店老板").start();
        for (int i = 1; i <= 8; i++) {            //顾客线程
            new Thread(() -> {
                System.out.println("第 " + Thread.currentThread().getName() + "个蛋糕卖掉了");
                //使计数器的值减 1 。
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
    }
}
