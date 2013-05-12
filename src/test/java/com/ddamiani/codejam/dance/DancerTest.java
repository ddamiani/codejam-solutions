package com.ddamiani.codejam.dance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Dancer class
 */
public final class DancerTest {
    private static final int SCORE = 25;
    private Dancer dancer;

    @Before
    public final void setUp() {
        dancer = new Dancer(SCORE);
    }

    @Test
    public final void testGetScore() {
        assertEquals(SCORE, dancer.getScore());
    }
}
