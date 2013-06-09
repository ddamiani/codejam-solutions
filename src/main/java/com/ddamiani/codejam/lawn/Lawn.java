package com.ddamiani.codejam.lawn;

import java.util.regex.*;

/**
 * Class representing a cut lawn.
 */
public final class Lawn {
    private final int height;
    private final int width;
    private int[] lawn;
    private final Pattern splitter = Pattern.compile(" ");

    public Lawn(int height, int width) {
        this.height = height;
        this.width = width;
        lawn = new int[height * width];
    }

    public final int getHeight() {
        return height;
    }

    public final int getWidth() {
        return width;
    }

    public final void addRow(int rowNum, String rowValues) {
        if (rowNum < 0 || rowNum >= height) {
            throw new IllegalArgumentException("Row number outside allowed range for lawn: " + rowNum);
        }

        final int[] newRow = parseIntegers(rowValues);
        if (newRow.length != width) throw new IllegalArgumentException("Row has the wrong width: " + newRow.length);

        System.arraycopy(newRow, 0, lawn, rowNum * width, newRow.length);
    }

    public final int get(int hPos, int wPos) {
        return lawn[hPos * width + wPos];
    }

    private int[] parseIntegers(String value) {
        final String[] splitValues = splitter.split(value);
        int[] numbers = new int[splitValues.length];
        for (int i = 0; i < splitValues.length; i++) {
            numbers[i] = Integer.parseInt(splitValues[i]);
        }

        return numbers;
    }
}
