package prime;

import org.junit.jupiter.api.Test;

class ParallelSieveOfEratosthenesTest {

    @Test
    void testGeneratePrimesWithThreadsLarge() throws InterruptedException {
        var parallelSieveOfEratosthenes = new ParallelSieveOfEratosthenes();
        boolean[] got = parallelSieveOfEratosthenes.findPrimes(40, 2);

        for (int i = 0; i < got.length; i++) {
            System.out.println(i + " " + got[i]);
        }
    }
}
