package com.ddamiani.codejam.tongues;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import java.io.*;
import java.util.*;

/**
 * Project A of google code jam 2012
 */
public final class Tongues extends CodeJamFileHandler {
    protected final List<String> lines;
    private final Translation trans;

    public Tongues(String inputFile, String outputFile) throws FileNotFoundException {
        this(inputFile, outputFile, false);
    }

    public Tongues(String inputFile, String outputFile, boolean testing) throws FileNotFoundException {
        super(inputFile, outputFile, testing);
        lines = new ArrayList<>();
        trans = new Translation(Tongues.class.getResourceAsStream("/normal_letters.txt"),
                Tongues.class.getResourceAsStream("/mutated_letters.txt"));
    }

    public final void operateImpl() throws IOException {
        String currentLine;
        while ((currentLine = readInputLine()) != null) {
            StringBuilder translatedStr = new StringBuilder(currentLine.length());
            for (char letter : currentLine.toCharArray()) {
                translatedStr.append(trans.getNormalVersion(letter));
            }

            if (testMode) {
                lines.add(translatedStr.toString());
            }

            emitOutputLine(translatedStr.toString());
        }
    }
}
