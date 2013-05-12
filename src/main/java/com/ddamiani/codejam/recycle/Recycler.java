package com.ddamiani.codejam.recycle;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Main class for the recycler challenge
 */
public final class Recycler extends CodeJamFileHandler {
    protected final List<Integer> results;

    public Recycler(String inputFile, String outputFile) throws FileNotFoundException {
        this(inputFile, outputFile, false);
    }

    public Recycler(String inputFile, String outputFile, boolean testing) throws FileNotFoundException {
        super(inputFile, outputFile, testing);
        results = new ArrayList<Integer>();
    }

    public final void operateImpl() throws IOException {
        final Pattern splitter = Pattern.compile(" ");

        String currentLine;
        while ((currentLine = readInputLine()) != null) {
            String[] numbers = splitter.split(currentLine);

            if (numbers.length != 2) {
                System.err.println("Invalid number of input parameters : " + numbers.length);
                continue;
            }

            try {
                int count = getNumRecyclable(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));

                if (testMode) {
                    results.add(count);
                }

                emitOutputLine(Integer.toString(count));
            } catch (NumberFormatException e) {
                System.err.println("Input parameter is not an integer: " + e.getMessage());
            }
        }
    }

    protected static String reverse(String value, int numPlaces) {
        return value.substring(numPlaces) + value.substring(0, numPlaces);
    }

    protected static int getNumRecyclable(int paramOne, int paramTwo) {
        int count = 0;

        for (int forward = paramOne; forward <= paramTwo; forward++) {
            Set<Integer> already = new HashSet<Integer>();
            String strForward = Integer.toString(forward);
            for (int index = 1; index < strForward.length(); index++) {
                String strReverse = reverse(strForward, index);

                int reverse = Integer.parseInt(strReverse);

                if (reverse > forward && reverse >= paramOne &&
                        reverse <= paramTwo && !already.contains(reverse)) {
                    already.add(reverse);
                    count++;
                }
            }

        }

        return count;
    }
}
