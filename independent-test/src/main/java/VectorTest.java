import java.util.Vector;

public class VectorTest {

    private static final Vector<Integer> vector = new Vector<>(16);

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //修改次值可调整线程2执行次数
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (vector) {
                    System.out.println("Thread1拿到vector锁");
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread1:" + i);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("准备执行 vector.add(..)");
                    vector.add(i, i);
                    System.out.println("thread2:" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
