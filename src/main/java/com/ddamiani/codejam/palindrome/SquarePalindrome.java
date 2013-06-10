package com.ddamiani.codejam.palindrome;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * A class for testing fair and square palindromes.
 * <p/>
 * Doesn't use big integers so it can't solve the second large input.
 */
public final class SquarePalindrome extends CodeJamFileHandler {
    protected final List<Long> results;

    public SquarePalindrome(String inputFile, String outputFile) throws FileNotFoundException {
        this(inputFile, outputFile, false);
    }

    public SquarePalindrome(String inputFile, String outputFile, boolean testing) throws FileNotFoundException {
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

            final long start = (long) Math.ceil(Math.sqrt(Double.parseDouble(numbers[0])));
            final long end = (long) Math.floor(Math.sqrt(Double.parseDouble(numbers[1])));
            long count = 0;

            for (long i = start; i <= end; i++) {
                if (!isAPalindrome(i)) continue;

                if (isAPalindrome(i * i)) count++;
            }

            if (testMode) {
                results.add(count);
            }

            emitOutputLine(Long.toString(count));
        }
    }

    public static boolean isAPalindrome(long number) {
        String numberStr = Long.toString(number);
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
}
