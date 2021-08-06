
public class RunnableTest implements Runnable {

    private String taskName;

    public RunnableTest(final String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Inside "+taskName);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        throw new RuntimeException("RuntimeException from inside " + taskName);
    }

}
