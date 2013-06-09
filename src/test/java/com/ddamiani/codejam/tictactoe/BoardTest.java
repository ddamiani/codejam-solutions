package com.ddamiani.codejam.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit tests for the Board class
 */
public final class BoardTest {
    @Test
    public final void testInitSize() {
        final int dim = 4;
        final Board board = new Board(4);

        final String testRow = "XO.O";
        for (int i = 0; i < dim; i++) {
            board.addRow(testRow);
        }

        try {
            board.addRow(testRow);
            fail("Too many rows were added to board");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public final void testInvalidEntry() {
        final Board board = new Board();

        try {
            board.addRow("XOOY");
            fail("There is an illegal char in the added row.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            board.addRow("XXXXX");
            fail("There are too many chars in this row");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            board.addRow("XXX");
            fail("There are too few chars in this row");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public final void testNumTs() {
        final Board board = new Board();

        try {
            board.addRow("TTOO");
            fail("Too many Ts in one row");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        board.addRow("TXOO");

        try {
            board.addRow("TXOO");
            fail("Too many Ts in the board");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public final void testEntries() {
        final String rows[] = {"OO..", "OOX.", "XXXX", "XOTO"};
        final Board board = new Board();

        try {
            board.addRow("XXXP");
            fail("Row entry should have failed");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        // Add entries to the board
        for (String row : rows) {
            board.addRow(row);
        }

        // Test the board
        int rowCount = 0;
        for (String row : rows) {
            int colCount = 0;
            for (char value : row.toCharArray()) {
                Board.Entry expected = Board.Entry.getEntry(value);
                assertEquals(expected, board.get(rowCount, colCount));
                colCount++;
            }
            rowCount++;
        }
    }

    @Test
    public final void testGetDimension() {
        Board board = new Board();
        assertEquals(4, board.getDimension());

        final int dim = 8;
        board = new Board(dim);
        assertEquals(dim, board.getDimension());
    }

    @Test
    public final void voidTestEmpty() {
        final String rows[] = {"OO..", "OOX.", "XXXX", "XOTO"};
        final Board board = new Board();

        // Add entries to the board
        for (String row : rows) {
            board.addRow(row);
        }

        assertTrue(board.isUnfilled());
    }

    @Test
    public final void voidTestNotEmpty() {
        final String rows[] = {"OOXX", "OOXO", "XXXX", "XOTO"};
        final Board board = new Board();

        // Add entries to the board
        for (String row : rows) {
            board.addRow(row);
        }

        assertFalse(board.isUnfilled());
    }
}
