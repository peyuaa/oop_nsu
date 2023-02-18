package prime;

import java.util.Arrays;

public class ParallelStreamSolution {

    public boolean isNonPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean containsNonPrime(int[] arr) {
        if (arr == null) {
            return false;
        }

        return Arrays.stream(arr)
                .parallel()
                .anyMatch(this::isNonPrime);
    }
}

