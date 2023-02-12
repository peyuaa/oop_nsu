/**
 * The class {@code SerialSolution} contains a single public method {@link #containsNonPrime(int[])}
 * which takes in an integer array and returns a boolean indicating if the array contains a non-prime number.
 * <p>
 * The method first finds the maximum element in the array and creates a boolean array to store the prime numbers
 * up to the maximum element. The boolean array is initialized with all elements set to true and the first two
 * elements, 0 and 1, set to false since they are not primes.
 * <p>
 * The method then uses the Sieve of Eratosthenes algorithm to fill the boolean array with the prime numbers.
 * It iterates through the numbers from 2 to the square root of the maximum element, filtering out the numbers
 * that are not prime. For each prime number, the method sets all its multiples as non-prime in the boolean array.
 * <p>
 * Finally, the method returns true if the array contains any number that is not marked as prime in the boolean array.
 */
package prime;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SerialSolution {

    /**
     * ContainsNonPrime checks if the given array contains any non-prime numbers.
     *
     * @param arr The array to check for non-prime numbers
     * @return Returns true if the array contains any non-prime numbers, false otherwise.
     */
    public boolean containsNonPrime(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        if (max < 2) {
            return true;
        }

        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        IntStream.rangeClosed(2, (int) Math.sqrt(max))
                .filter(x -> isPrime[x])
                .forEach(x -> {
                    int start = x * x;
                    while (start <= max) {
                        isPrime[start] = false;
                        start += x;
                    }
                });

        return Arrays.stream(arr).anyMatch(x -> !isPrime[x]);
    }

    /**
     * Constructs a new instance of the {@code SerialSolution} class.
     */
    public SerialSolution() {

    }
}
