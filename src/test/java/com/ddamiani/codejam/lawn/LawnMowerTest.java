package com.ddamiani.codejam.lawn;

import com.ddamiani.codejam.lawn.LawnMower.Result;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit tests for the Lawnmower class.
 */
public final class LawnMowerTest {
    private LawnMower tester;

    @Before
    public final void setUp() throws FileNotFoundException {
        tester = new LawnMower(this.getClass().getResource("/test_lawn_input.txt").getFile(), null, true);
    }

    @Test
    public final void testTestMode() throws FileNotFoundException {
        LawnMower nonTestLawnMower = new LawnMower(this.getClass().getResource("/test_lawn_input.txt").getFile(), null);
        assertFalse(nonTestLawnMower.isTestMode());
    }

    @Test
    public final void testLineNum() throws IOException {
        assertEquals(0, tester.getNumCases());
        tester.operate();
        assertEquals(3, tester.getNumCases());
        assertEquals(tester.getNumCases(), tester.getNumEmittedLines());
        assertEquals(12, tester.getNumConsumedLines());
    }

    @Test
    public final void testOutput() throws IOException {
        tester.operate();
        assertEquals(3, tester.results.size());
        List<Result> expectedResults = Arrays.asList(Result.YES,
                Result.NO,
                Result.YES);
        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals("Test of board " + (i + 1), expectedResults.get(i), tester.results.get(i));
        }
    }
}
