package com.ddamiani.codejam.lawn;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Class for checking if a specific lawn configuration can be cut.
 */
public final class LawnMower extends CodeJamFileHandler {
    public enum Result {
        YES,
        NO;

        public static Result getResult(boolean val) {
            if (val) {
                return YES;
            } else {
                return NO;
            }
        }
    }

    protected final List<Result> results;

    public LawnMower(String inputFile, String outputFile) throws FileNotFoundException {
        this(inputFile, outputFile, false);
    }

    public LawnMower(String inputFile, String outputFile, boolean testing) throws FileNotFoundException {
        super(inputFile, outputFile, testing, 100); // Pick 100 since that is the largest possible size - it does vary.
        results = new ArrayList<>();
    }

    public final void operateImpl() throws IOException {
        final Pattern splitter = Pattern.compile(" ");

        String currentLine;
        while ((currentLine = readInputLine()) != null) {
            String[] numbers = splitter.split(currentLine);

            if (numbers.length != 2) {
                throw new IllegalArgumentException("Invalid number of input parameters : " + numbers.length);
            }

            final int height = Integer.parseInt(numbers[0]);
            final int width = Integer.parseInt(numbers[1]);

            final Result result = Result.getResult(validateLawn(readInLawn(height, width)));

            if (testMode) {
                results.add(result);
            }

            emitOutputLine(result.toString());
        }
    }

    private boolean validateLawn(Lawn lawn) {
        for (int x = 0; x < lawn.getHeight(); x++) {
            for (int y = 0; y < lawn.getWidth(); y++) {
                final int currentVal = lawn.get(x, y);
                if (checkPathBlocked(lawn, currentVal, 0, lawn.getHeight(), y, false)
                        && checkPathBlocked(lawn, currentVal, 0, lawn.getWidth(), x, true)) {
                    return false;
                }
            }
        }

        return true;
    }

    private Lawn readInLawn(int height, int width) throws IOException {
        Lawn lawn = new Lawn(height, width);
        for (int i = 0; i < height; i++) {
            lawn.addRow(i, readInputLine());
        }

        return lawn;
    }

    private boolean checkPathBlocked(Lawn lawn, int comp, int start, int end, int fixed, boolean horizontal) {
        for (int i = start; i < end; i++) {
            if ((horizontal ? lawn.get(fixed, i) : lawn.get(i, fixed)) > comp) return true;
        }

        return false;
    }
}
