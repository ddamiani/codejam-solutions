package com.ddamiani.codejam.lawn;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit tests for the Lawn class.
 */
public final class LawnTest {
    private static final int height = 2;
    private static final int width = 3;
    private Lawn testLawn;

    @Before
    public final void init() {
        testLawn = new Lawn(height, width);
    }

    @Test
    public final void testFillSizes() {
        testLawn.addRow(0, "5 6 7");
        testLawn.addRow(1, "1 2 3");

        try {
            testLawn.addRow(2, "5 6 7");
            fail("Row insertion should have failed. Row num to large.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            testLawn.addRow(-1, "5 6 7");
            fail("Row insertion should have failed. Row num less than 0.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            testLawn.addRow(0, "5 6");
            fail("Row insertion should have failed. Row is too short.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            testLawn.addRow(0, "5 6 7 8");
            fail("Row insertion should have failed. Row is too long.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public final void testFillNonNumber() {
        try {
            testLawn.addRow(0, "5 6 A");
            fail("Row insertion should have failed. A non-number is present.");
        } catch (NumberFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public final void testGet() {
        final int[][] expected = {{1, 6, 7}, {5, 9, 10}};
        final String[] input = {"1 6 7", "5 9 10"};

        for (int i = 0; i < testLawn.getHeight(); i++) {
            testLawn.addRow(i, input[i]);
        }

        for (int x = 0; x < testLawn.getHeight(); x++) {
            for (int y = 0; y < testLawn.getWidth(); y++) {
                assertEquals("Test entry: " + x + ", " + y, expected[x][y], testLawn.get(x, y));
            }
        }
    }

    @Test
    public final void testGetDim() {
        assertEquals(height, testLawn.getHeight());
        assertEquals(width, testLawn.getWidth());
    }
}
