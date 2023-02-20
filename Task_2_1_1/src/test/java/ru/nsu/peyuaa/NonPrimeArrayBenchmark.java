package ru.nsu.peyuaa;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * The NonPrimeArrayBenchmark class benchmarks three different solutions
 * to find non-prime numbers in an integer array.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class NonPrimeArrayBenchmark {
    private static final int ARRAY_SIZE = 10_000_000;
    private static final int THREAD_COUNT = 4;
    private static int[] array;

    /**
     * Initializes the static integer array with random integer values between 1 and ARRAY_SIZE.
     * The method is annotated with @BeforeAll to run before all benchmark tests in the class.
     * It creates a new integer array of size ARRAY_SIZE, initializes a new Random object,
     * and uses a for loop to fill the array with random integer values between 1 and ARRAY_SIZE
     * using the Random object's nextInt() method.
     */
    @BeforeAll
    public static void init() {
        array = new int[ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(ARRAY_SIZE) + 1;
        }
    }

    @Benchmark
    @DisplayName("Single threaded solution")
    public void testSingleThreaded() {
        SerialSolution serialSolution = new SerialSolution();
        serialSolution.containsNonPrime(array);
    }

    @Benchmark
    @DisplayName("Multi threaded solution")
    public void testMultiThreaded() throws InterruptedException {
        ParallelSolution parallelSolution = new ParallelSolution(THREAD_COUNT);
        parallelSolution.containsNonPrime(array);
    }

    @Benchmark
    @DisplayName("Parallel stream solution")
    public void testParallelStream() {
        ParallelStreamSolution parallelStreamSolution = new ParallelStreamSolution();
        parallelStreamSolution.containsNonPrime(array);
    }

    /**
     * Runs the benchmark tests for the NonPrimeArrayBenchmark class using the JMH Runner class.
     * The method creates a new Options object with the class name as the include pattern and uses it to build a
     * new OptionsBuilder object. It then creates a new Runner object with the OptionsBuilder object
     * and runs the benchmark tests. The method throws a RunnerException if the benchmark tests fail to run.
     */
    public void runBenchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(NonPrimeArrayBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
