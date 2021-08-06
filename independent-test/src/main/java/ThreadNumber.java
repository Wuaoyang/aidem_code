public class ThreadNumber {

    private static Object obj = new Object() ;

    private static int i = 1 ;

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    while(i < 100){
                        if(i % 3 == 1){
                            System.out.println("线程1被打印---" + i);
                            i++;
                            obj.notify();
                        }else{
                            try {
                                System.out.println("线程1被阻塞---" + i);
                                obj.wait();
                                System.out.println("线程1被唤醒---" + i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }) ;


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    while(i < 100){
                        if(i % 3 == 2){
                            System.out.println("线程2被打印---" + i);
                            i++;
                            obj.notify();
                        }else{
                            try {
                                System.out.println("线程2被阻塞---" + i);
                                obj.wait();
                                System.out.println("线程2被唤醒---" + i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

            }
        }) ;

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    while(i<100){

                        if(i % 3 == 0){
                            System.out.println("线程3被打印---" + i);
                            i++;
                            obj.notify();
                        }else{
                            try {
                                System.out.println("线程3被阻塞---" + i);
                                obj.wait();
                                System.out.println("线程3被唤醒---" + i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

            }
        }) ;
        thread.start();
        thread1.start();
        thread2.start();


    }

}
