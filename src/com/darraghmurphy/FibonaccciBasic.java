package com.darraghmurphy;

import java.math.BigInteger;

public class FibonaccciBasic implements FibonacciInterface {

    public BigInteger getNumber(long index) {

        return MathUtil.fibonacci(index);
    }

}
