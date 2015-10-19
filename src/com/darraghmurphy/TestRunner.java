package com.darraghmurphy;

import java.util.Random;

public class TestRunner {

    static final int ITERATIONS = 4000;

    static int THREAD_COUNT = 4;

    static final int MIN = 10000;
    static final int MAX = 10100;

    public static void main(String[] args) throws Exception {

        test("Fibonacci with cache and synchronized method", new FibonacciCache1());
        test("Fibonacci with cache and synchronized block", new FibonacciCache2());
    }

    private static void test(String testName, FibonacciInterface fib) throws InterruptedException {

        ThreadTest threads[] = new ThreadTest[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new ThreadTest("Thread" + i, fib);
        }

        /** Avoid JVM warmup penalty */
        Thread.sleep(1000);

        for (Thread t : threads)
            t.start();

        for (Thread t : threads)
            t.join();

        int count = 0;
        long[] timings = new long[THREAD_COUNT * ITERATIONS];
        for (int j = 0; j < THREAD_COUNT; j++) {
            for (int i = 0; i < ITERATIONS; i++) {

                timings[count++] = threads[j].timings[i];

            }
        }

        System.out.println("TestRunner : " + testName + " . Avg time : " + MathUtil.arithmeticMean(timings) + " " +
                "nanoseconds");

        /** Avoid JVM warmup penalty */
        Thread.sleep(1000);
    }

}

class ThreadTest extends Thread {

    Random random = new Random();

    FibonacciInterface instance;

    String threadName;

    long[] timings = new long[TestRunner.ITERATIONS];

    public ThreadTest(String threadName, FibonacciInterface instance) {
        super(threadName);
        this.threadName = threadName;
        this.instance = instance;
    }

    public void run() {

        for (int i = 0; i < TestRunner.ITERATIONS; i++) {

            long s = System.nanoTime();
            int randomNum = random.nextInt((TestRunner.MAX - TestRunner.MIN) + 1) + TestRunner.MIN;

            /** Calculate FibonacciInterface for N */
            instance.getNumber(randomNum);

            timings[i] = (System.nanoTime() - s);
        }

    }

}