package com.vikings.hackaton;

import java.math.BigInteger;

public class Main {


    public static void main(String[] args) {
        BigInteger max = BigInteger.valueOf(12235060455L);
        long i = max.longValue();
        long prevMaxSteps = 1153;
        long maxSteps = 1184;
        while (true) {
            long steps = 0;
            BigInteger n = BigInteger.valueOf(i);
            while (!n.equals(BigInteger.ONE)) {
                if (n.divideAndRemainder(BigInteger.valueOf(2))[1].longValue() == 0) {
                    n = n.divide(BigInteger.valueOf(2));
                } else {
                    n = n.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE).divide(BigInteger.valueOf(2));
                }
                steps++;
                if (n.compareTo(max) < 0 && steps + prevMaxSteps < maxSteps) {
                    break;
                }
            }
            if (steps > maxSteps) {
                prevMaxSteps = maxSteps;
                maxSteps = steps;
                max = n;
                //highest number of steps i got was 1333 for number 674_190_078_379
                //highest known number of steps calculated is 2620 for number 2_682_561_764_939_817_403
                System.out.println("Current highest number of steps: " + steps + " for a number: " + i);
            }
            i++;
        }
    }
}
