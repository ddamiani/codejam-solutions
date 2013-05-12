package com.ddamiani.codejam.dance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Dancer class
 */
public final class DancerTest {
    private static final int SCORE = 25;
    private static final int SCORE_ALT = 26;
    private Dancer dancer;
    private Dancer dancerAlt;

    @Before
    public final void setUp() {
        dancer = new Dancer(SCORE);
        dancerAlt = new Dancer(SCORE_ALT);
    }

    @Test
    public final void testGetScore() {
        assertEquals(SCORE, dancer.getScore());
    }

    @Test
    public final void testValidTopScore() {
        assertTrue("Nine should be a valid top score", dancer.isValidTopScore(9));
        assertFalse("Eight should not be a valid top score", dancer.isValidTopScore(8));
    }

    @Test
    public final void testExceptionalValidTopScore() {
        assertTrue("Ten should be a valid top score", dancerAlt.isValidExceptionalTopScore(10));
        assertFalse("Eleven should not be a valid top score", dancerAlt.isValidExceptionalTopScore(11));
    }
}
