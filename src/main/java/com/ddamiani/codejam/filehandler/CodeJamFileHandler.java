package com.ddamiani.codejam.filehandler;

import java.io.*;

/**
 * Class for handling input and output for google code jams
 */
public abstract class CodeJamFileHandler {
    private int num_line = 0;
    private int lines_consumed = 0;
    private int lines_emitted = 0;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public CodeJamFileHandler(String inputFileName, String outputFileName) throws FileNotFoundException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        if (outputFileName == null) {
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
        } else {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));
        }
    }

    public final void operate() throws IOException {
        num_line = Integer.parseInt(reader.readLine());
        operateImpl();
    }

    public abstract void operateImpl() throws IOException;

    public final int getNumLines() {
        return num_line;
    }

    public final int getNumEmittedLines() {
        return lines_emitted;
    }

    public final int getNumConsumedLines() {
        return lines_consumed;
    }

    public final void close() throws IOException {
        reader.close();
        writer.close();
    }

    public final String readInputLine() throws IOException {
        if (lines_consumed > num_line) {
            System.err.println("All intended input lines (" + num_line + ") have already been consumed!");
        }

        String currentLine = reader.readLine();
        lines_consumed++;

        return currentLine;
    }

    public final void emitOutputLine(String output) throws IOException {
        final String templateStr = "Case #%d: %s\n";

        writer.write(String.format(templateStr, lines_emitted + 1, output));
        lines_emitted++;
    }
}
