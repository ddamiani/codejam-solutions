package com.ddamiani.codejam.tictactoe;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;
import com.ddamiani.codejam.tictactoe.Board.Entry;

import java.io.*;
import java.util.*;

/**
 * Main class for the Tic Tac Toe challenge.
 */
public final class TicTacToe extends CodeJamFileHandler {
    public enum Result {
        INCOMPLETE("Game has not completed"),
        DRAW("Draw"),
        WIN_O("O won"),
        WIN_X("X won");

        private final String rep;

        private Result(String rep) {
            this.rep = rep;
        }

        @Override
        public String toString() {
            return rep;
        }
    }

    protected final List<Result> results;

    public TicTacToe(String inputFile, String outputFile) throws FileNotFoundException {
        this(inputFile, outputFile, false);
    }

    public TicTacToe(String inputFile, String outputFile, boolean testing) throws FileNotFoundException {
        super(inputFile, outputFile, testing, 5);
        results = new ArrayList<>();
    }

    public final void operateImpl() throws IOException {
        Board currentBoard;
        while ((currentBoard = loadBoard()) != null) {
            Result result = solveBoard(currentBoard);

            if (testMode) {
                results.add(result);
            }

            emitOutputLine(result.toString());
        }
    }

    protected final Board loadBoard() throws IOException {
        final Board board = new Board();
        int numAdded = 0;
        String currentLine;
        while ((currentLine = readInputLine()) != null) {
            if (currentLine.isEmpty()) {
                break;
            }

            board.addRow(currentLine);
            numAdded++;
        }

        if (board.getDimension() != numAdded) return null;

        return board;
    }

    protected final Result solveBoard(Board board) {
        Result result;
        if ((result = solveDiag(board, true)) != null) {
            return result;
        }

        if ((result = solveDiag(board, false)) != null) {
            return result;
        }

        if ((result = solveNonDiag(board, true)) != null) {
            return result;
        }

        if ((result = solveNonDiag(board, false)) != null) {
            return result;
        }

        if (board.isUnfilled()) {
            return Result.INCOMPLETE;
        }

        return Result.DRAW;
    }

    private Result solveNonDiag(Board board, boolean isHorizontal) {
        final int dim = board.getDimension();
        for (int i = 0; i < dim; i++) {
            int numMatch = 0;

            Entry first = null;

            for (int j = 0; j < dim; j++) {
                final Entry current = isHorizontal ? board.get(i, j) : board.get(j, i);

                if (current == Entry.EMPTY || first != null && current != Entry.T && current != first) {
                    break;
                }

                if (current != Entry.T) {
                    first = current;
                }

                numMatch++;
            }

            if (numMatch == dim) return entryToResult(first);
        }

        return null;
    }

    private Result solveDiag(Board board, boolean isLeft) {
        final int dim = board.getDimension();
        Entry first = null;
        int numMatch = 0;
        for (int i = 0; i < dim; i++) {
            final int j = isLeft ? i : dim - 1 - i;
            final Entry current = board.get(i, j);

            if (current == Entry.EMPTY || first != null && current != Entry.T && current != first) {
                break;
            }

            if (current != Entry.T) {
                first = current;
            }

            numMatch++;
        }

        if (numMatch != dim) return null;

        return entryToResult(first);
    }

    private Result entryToResult(Entry entry) {
        switch (entry) {
            case X:
                return Result.WIN_X;
            case O:
                return Result.WIN_O;
            default:
                return null;
        }
    }
}
