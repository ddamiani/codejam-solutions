package com.ddamiani.codejam.mirror;

import java.util.*;

/**
 * Class representing a mirror grid.
 */
public final class MirrorRoom {
    public enum MirrorType {
        EMPTY('.'),
        PERSON('X'),
        MIRROR('#');

        private final char symbol;

        private MirrorType(char symbol) {
            this.symbol = symbol;
        }

        public static MirrorType findType(char symbol) {
            for (MirrorType type : MirrorType.values()) {
                if (type.symbol == symbol) return type;
            }

            throw new IllegalArgumentException("The symbol " + symbol + " does not correspond to a valid MirrorType");
        }
    }

    private final List<List<MirrorType>> rows;

    public MirrorRoom(int numRows, int numColumns, String... args) {
        rows = new ArrayList<List<MirrorType>>(numRows);
        init(numRows, numColumns, args);
    }

    public MirrorType getTypeAt(int row, int column) {
        return rows.get(row).get(column);
    }

    private void init(int numRows, int numColumns, String... args) {
        if (args.length != numRows) {
            throw new IllegalArgumentException("Row number mismatch: " + args.length + " vs. " + numRows);
        }

        for (int i = 0; i < numRows; i++) {
            final String currentLine = args[i];

            if (currentLine.length() != numColumns) {
                throw new IllegalArgumentException("Column number mismatch: " + currentLine.length() + " vs. " + numColumns);
            }

            List<MirrorType> currentRow = new ArrayList<MirrorType>(numColumns);
            for (char symbol : currentLine.toCharArray()) {
                currentRow.add(MirrorType.findType(symbol));
            }

            rows.add(currentRow);
        }
    }
}
