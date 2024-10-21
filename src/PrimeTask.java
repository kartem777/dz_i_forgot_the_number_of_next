import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class PrimeTask extends RecursiveTask<List<Integer>> {
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10;

    public PrimeTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<Integer> compute() {
        List<Integer> primes = new ArrayList<>();

        if (end - start <= THRESHOLD) {
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
        } else {
            int mid = (start + end) / 2;
            PrimeTask leftTask = new PrimeTask(start, mid);
            PrimeTask rightTask = new PrimeTask(mid + 1, end);

            leftTask.fork();
            rightTask.fork();

            List<Integer> leftResult = leftTask.join();
            List<Integer> rightResult = rightTask.join();

            primes.addAll(leftResult);
            primes.addAll(rightResult);
        }

        return primes;
    }

    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
