import java.util.ArrayList;
import java.util.Collections;

/**
 * @author aidem
 * @date 2021-04-10
 * @description 测试静态变量上锁
 */
public class TestStaticVariable {

    static Person person1 = new Person("person1");
    static Person person2 = new Person("person2");

    public static void main(String[] args) {
        // 线程1
        new Thread(() -> {
            System.out.println("Thread 1");
            person1.doSomeThing1();
        }).start();
        // 间隔
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程2
        new Thread(() -> {
            System.out.println("Thread 2");
            person2.doSomeThing2();
        }).start();
    }
}

/**
 * 资源类
 */
class Person {

    private static Integer i = 999;
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void doSomeThing1() {

        Collections.synchronizedList(new ArrayList<>());
        synchronized (i) {
            System.out.println(name + " ==== doSomeThing1");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doSomeThing2() {
        synchronized (i) {
            System.out.println(name + " ==== doSomeThing2");
        }
    }


}
