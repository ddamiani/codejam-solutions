package com.ddamiani.codejam.tictactoe;

/**
 * Class representing a Tic Tac Toe board.
 */
public final class Board {
    public enum Entry {
        EMPTY("."),
        X("X"),
        O("O"),
        T("T");

        public static Entry getEntry(char value) {
            switch (value) {
                case 'X':
                    return X;
                case 'O':
                    return O;
                case 'T':
                    return T;
                case '.':
                    return EMPTY;
                default:
                    throw new IllegalArgumentException("Invalid entry value: " + value);
            }
        }

        private final String rep;

        private Entry(String rep) {
            this.rep = rep;
        }

        @Override
        public String toString() {
            return rep;
        }
    }

    private static final int MAX_T_COUNT = 1;
    private final int dimension;
    private int rowCount;
    private int tCount;
    private boolean hasEmpty;
    private Entry[][] store;

    public Board() {
        this(4);
    }

    public Board(int dimension) {
        this.dimension = dimension;
        rowCount = 0;
        tCount = 0;
        hasEmpty = false;
        store = new Entry[dimension][dimension];
    }

    public final void addRow(String row) {
        if (rowCount < dimension && row.length() == dimension) {
            char[] values = row.toCharArray();
            Entry[] tempRow = new Entry[dimension];
            int tCountTemp = tCount;
            boolean containsEmpty = false;
            for (int i = 0; i < dimension; i++) {
                Entry current = Entry.getEntry(values[i]);
                if (current == Entry.T) {
                    tCountTemp++;
                    if (tCountTemp > MAX_T_COUNT) {
                        throw new IllegalArgumentException("Number of T's greater than allowed max: " + MAX_T_COUNT);
                    }
                } else if (current == Entry.EMPTY) {
                    containsEmpty = true;
                }

                tempRow[i] = current;
            }
            store[rowCount] = tempRow;
            rowCount++;
            tCount = tCountTemp;
            hasEmpty = hasEmpty || containsEmpty;
        } else {
            throw new IllegalArgumentException("Requested row cannot fit in a board of dimension " + dimension);
        }
    }

    public final int getDimension() {
        return dimension;
    }

    public final Entry get(int rowIndex, int columnIndex) {
        return store[rowIndex][columnIndex];
    }

    public final boolean isUnfilled() {
        return hasEmpty;
    }
}
