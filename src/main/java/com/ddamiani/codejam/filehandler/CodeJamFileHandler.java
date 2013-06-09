package com.ddamiani.codejam.filehandler;

import java.io.*;

/**
 * Class for handling input and output for google code jams
 */
public abstract class CodeJamFileHandler {
    private int numCases = 0;
    private int linesConsumed = 0;
    private int linesEmitted = 0;
    private final int linesPerCase;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    protected final boolean testMode;

    public CodeJamFileHandler(String inputFileName, String outputFileName, boolean testMode) throws FileNotFoundException {
        this(inputFileName, outputFileName, testMode, 1);
    }

    public CodeJamFileHandler(String inputFileName, String outputFileName, boolean testMode, int linesPerCase) throws FileNotFoundException {
        this.linesPerCase = linesPerCase;
        this.testMode = testMode;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        if (outputFileName == null) {
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
        } else {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));
        }
    }

    public final void operate() throws IOException {
        numCases = Integer.parseInt(reader.readLine());
        operateImpl();
    }

    public abstract void operateImpl() throws IOException;

    public final boolean isTestMode() {
        return testMode;
    }

    public final int getNumCases() {
        return numCases;
    }

    public final int getNumEmittedLines() {
        return linesEmitted;
    }

    public final int getNumConsumedLines() {
        return linesConsumed;
    }

    public final int getNumLinesPerCase() {
        return linesPerCase;
    }

    public final void close() throws IOException {
        reader.close();
        writer.close();
    }

    public final String readInputLine() throws IOException {
        if (linesConsumed > numCases * linesPerCase) {
            System.err.println("All intended input lines (" + numCases * linesPerCase + ") have already been consumed!");
        }

        String currentLine = reader.readLine();
        if (currentLine != null) linesConsumed++;

        return currentLine;
    }

    public final void emitOutputLine(String output) throws IOException {
        final String templateStr = "Case #%d: %s\n";

        writer.write(String.format(templateStr, linesEmitted + 1, output));
        linesEmitted++;
    }
}
