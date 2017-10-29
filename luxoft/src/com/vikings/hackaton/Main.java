package com.vikings.hackaton;

public class Main {

    public static void main(String[] args) {
        long i = 1;
        long maxSteps = 0;
        while (true) {
            long steps = 0;
            long n = i;
            while (n != 1) {
                if (n % 2 == 0) {
                    n = n >> 1;
                } else {
                    n = 3 * n + 1;
                }
                steps++;
            }
            if (steps > maxSteps) {
                maxSteps = steps;
                System.out.println("Current highest number of steps: " + steps + " for a number: " + i);
            }
            i++;
        }
    }
}
