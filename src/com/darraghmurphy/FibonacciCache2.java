package com.darraghmurphy;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FibonacciCache2 implements FibonacciInterface {

    private Map<Long, BigInteger> cache = new ConcurrentHashMap<>();

    public FibonacciCache2() {

        // The base case for the FibonacciCache1 Sequence
        cache.put(0L, BigInteger.ONE);
        cache.put(1L, BigInteger.ONE);
    }

    public BigInteger getNumber(long index) {

        // Check if value is in cache
        if (!cache.containsKey(index)) {
            synchronized (this) {
                if (!cache.containsKey(index)) {
                    BigInteger value = MathUtil.fibonacci(index);
                    cache.put(index, value);
                    return value;
                }
            }
        }

        return cache.get(index);

    }

}