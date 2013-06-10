package com.ddamiani.codejam.palindrome;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;

/**
 * A class for testing fair and square palindromes. Using Java's big int.
 */
public final class SquarePalindromeBig extends CodeJamFileHandler {
    private static final BigInteger DIGIT_LIMIT = BigInteger.valueOf(3l);
    private static final Pattern DEC_SPLITTER = Pattern.compile("\\.");
    protected final List<Long> results;

    public SquarePalindromeBig(String inputFile, String outputFile) throws FileNotFoundException {
        this(inputFile, outputFile, false);
    }

    public SquarePalindromeBig(String inputFile, String outputFile, boolean testing) throws FileNotFoundException {
        super(inputFile, outputFile, testing); // Pick 100 since that is the largest possible size - it does vary.
        results = new ArrayList<>();
    }

    public final void operateImpl() throws IOException {
        final Pattern splitter = Pattern.compile(" ");

        String currentLine;
        while ((currentLine = readInputLine()) != null) {
            final String[] numbers = splitter.split(currentLine);

            if (numbers.length != 2) {
                throw new IllegalArgumentException("Invalid number of input parameters : " + numbers.length);
            }

            final BigInteger start = squareRoot(new BigInteger(numbers[0]), false);
            final BigInteger end = squareRoot(new BigInteger(numbers[1]), true);
            long count = 0;

            for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(BigInteger.ONE)) {
                if (hasHighDigits(i) || !isAPalindrome(i)) continue;

                if (isAPalindrome(i.pow(2))) count++;
            }

            if (testMode) {
                results.add(count);
            }

            emitOutputLine(Long.toString(count));
        }
    }

    public static boolean isAPalindrome(final BigInteger number) {
        String numberStr = number.toString();
        int counterOne = 0;
        int counterTwo = numberStr.length() - 1;
        while (counterOne < counterTwo) {
            if (numberStr.charAt(counterOne) != numberStr.charAt(counterTwo)) {
                return false;
            }
            counterOne++;
            counterTwo--;
        }

        return true;
    }

    public static BigInteger squareRoot(final BigInteger input, boolean floor) {
        BigInteger result = new BigInteger(DEC_SPLITTER.split(Double.toString(Math.ceil(Math.sqrt(input.doubleValue()))))[0]);
        if (floor && result.pow(2).compareTo(input) != 0) result = result.subtract(BigInteger.ONE);
        return result;
    }

    private static boolean hasHighDigits(BigInteger input) {
        while (input.compareTo(BigInteger.ZERO) > 0) {
            final BigInteger[] divResult = input.divideAndRemainder(BigInteger.TEN);
            if (divResult[1].compareTo(DIGIT_LIMIT) > 0) return true;
            input = divResult[0];
        }

        return false;
    }
}
