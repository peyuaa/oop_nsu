package prime;

import java.lang.Math;

public class SerialSolution {
    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean containsNonPrime(int[] arr) {
        for (int num : arr) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }
}
