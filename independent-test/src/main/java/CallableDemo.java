import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableDemo implements Callable<String> {

    private int id;

    public CallableDemo(int id) {
        this.id = id;
    }

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            results.add(executorService.submit(new CallableDemo(i)));
        }
        for (Future<String> future : results) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }

    @Override
    public String call() throws Exception {
        // TODO Auto-generated method stub
        return "Result of CallableDemo is " + id;
    }
}
