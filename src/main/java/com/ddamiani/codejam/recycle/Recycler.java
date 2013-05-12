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
            int count = 0;
            String[] nums = splitter.split(currentLine);

            if (nums.length != 2) {
                System.err.println("Invalid number of input parameters : " + nums.length);
                continue;
            }

            try {
                int paramOne = Integer.parseInt(nums[0]);
                int paramTwo = Integer.parseInt(nums[1]);

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
            } catch (NumberFormatException e) {
                System.err.println("Input parameter is not an integer: " + e.getMessage());
            }

            if (testMode) {
                results.add(count);
            }

            emitOutputLine(Integer.toString(count));
        }
    }

    protected static String reverse(String value, int numPlaces) {
        return value.substring(numPlaces) + value.substring(0, numPlaces);
    }
}
