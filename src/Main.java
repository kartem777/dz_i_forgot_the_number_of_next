import java.util.List;
import java.util.concurrent.ForkJoinPool;
public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        PrimeTask task = new PrimeTask(2, 50);

        List<Integer> primes = pool.invoke(task);

        System.out.println("Prime from 2 to 50: " + primes);
    }
}