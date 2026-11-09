package service;

import java.math.BigDecimal;

/**
 * All the maths of the game. Every answer is calculated here by real Java code -
 * nothing is stored or hard-coded.
 */
public final class MathService {

    /** Decimal answers are accepted if they are within this distance of the real value. */
    public static final double TOLERANCE = 0.01;

    private static final double EPSILON = 1e-9;

    private MathService() {}

    /** Returns 1 if the number is prime, otherwise 0. Numbers below 2 are not prime. */
    public static int isPrime(int number) {
        if (number < 2) {
            return 0;
        }
        if (number < 4) {
            return 1;               // 2 and 3
        }
        if (number % 2 == 0) {
            return 0;
        }
        for (long i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return 0;
            }
        }
        return 1;
    }

    /** Returns the remainder of a / b using %. The divisor must not be zero. */
    public static int calculateMod(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor must not be zero.");
        }
        return a % b;
    }

    public static double calculateSin(double degree) {
        return Math.sin(Math.toRadians(degree));
    }

    public static double calculateCos(double degree) {
        return Math.cos(Math.toRadians(degree));
    }

    /** tan is undefined at 90, 270, ... degrees (cos = 0). */
    public static double calculateTan(double degree) {
        if (isTanUndefined(degree)) {
            throw new ArithmeticException(
                    "tan(" + plain(degree) + "°) is undefined. Please choose a different angle.");
        }
        return Math.tan(Math.toRadians(degree));
    }

    public static boolean isTanUndefined(double degree) {
        double reduced = ((degree % 180) + 180) % 180;
        return Math.abs(reduced - 90) < EPSILON;
    }

    /** Calculates sin, cos or tan of an angle given in degrees. */
    public static double calculateTrig(String ratio, double degree) {
        switch (ratio == null ? "" : ratio.toLowerCase()) {
            case "sin":
                return calculateSin(degree);
            case "cos":
                return calculateCos(degree);
            case "tan":
                return calculateTan(degree);
            default:
                throw new IllegalArgumentException("Ratio must be sin, cos or tan.");
        }
    }

    /** Compares two decimal numbers using the tolerance. */
    public static boolean isClose(double expected, double actual) {
        return Math.abs(expected - actual) <= TOLERANCE + 1e-12;
    }

    /** 30.0 -> "30", 0.5 -> "0.5" (for showing numbers to the player). */
    public static String plain(double value) {
        return BigDecimal.valueOf(value).stripTrailingZeros().toPlainString();
    }
}
