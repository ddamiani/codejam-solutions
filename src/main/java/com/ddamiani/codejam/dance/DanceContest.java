package com.ddamiani.codejam.dance;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * A dancing competition. Qualification Round 2012: Problem B.
 */
public final class DanceContest extends CodeJamFileHandler {
    private static final int MAX_SCORE = 10;
    protected final List<Integer> results;

    public DanceContest(String inputFile, String outputFile) throws FileNotFoundException {
        this(inputFile, outputFile, false);
    }

    public DanceContest(String inputFile, String outputFile, boolean testing) throws FileNotFoundException {
        super(inputFile, outputFile, testing);
        results = new ArrayList<>();
    }

    public final void operateImpl() throws IOException {
        final Pattern splitter = Pattern.compile(" ");

        String currentLine;
        while ((currentLine = readInputLine()) != null) {
            String[] args = splitter.split(currentLine);

            if (args.length < 3) {
                System.out.println("Invalid number of input parameters : " + args.length);
            }

            try {
                final int numGooglers = Integer.parseInt(args[0]);
                final int numExceptions = Integer.parseInt(args[1]);
                final int topScore = Integer.parseInt(args[2]);
                List<Dancer> dancers = parseTotalPoints(Arrays.copyOfRange(args, 3, args.length));

                if (dancers.size() != numGooglers) {
                    System.out.println("The number of googlers and total points don't match");
                    continue;
                }

                int count = 0;
                int exceptions = 0;

                for (Dancer dancer : dancers) {
                    for (int current = topScore; current <= MAX_SCORE; current++) {
                        if (dancer.isValidTopScore(current)) {
                            count++;
                            break;
                        } else if (exceptions < numExceptions && dancer.isValidExceptionalTopScore(current)) {
                            count++;
                            exceptions++;
                            break;
                        }
                    }
                }

                if (testMode) {
                    results.add(count);
                }

                emitOutputLine(Integer.toString(count));
            } catch (NumberFormatException e) {
                System.err.println("Input parameter is not an integer: " + e.getMessage());
            }
        }
    }

    private static List<Dancer> parseTotalPoints(String[] args) {
        List<Dancer> results = new ArrayList<>();
        for (String arg : args) {
            results.add(new Dancer(Integer.parseInt(arg)));
        }

        return results;
    }
}
