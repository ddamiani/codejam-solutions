package com.ddamiani.codejam.dance;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit tests for the dance contest
 */
public final class DanceContestTest {
    private DanceContest tester;

    @Before
    public final void setUp() throws FileNotFoundException {
        tester = new DanceContest(this.getClass().getResource("/test_dance_input.txt").getFile(), null, true);
    }

    @Test
    public final void testTestMode() throws FileNotFoundException {
        DanceContest nonTestDanceContest = new DanceContest(this.getClass().getResource("/test_dance_input.txt").getFile(), null);
        assertFalse(nonTestDanceContest.isTestMode());
    }

    @Test
    public final void testLineNum() throws IOException {
        assertEquals(0, tester.getNumCases());
        tester.operate();
        assertEquals(4, tester.getNumCases());
        assertEquals(4, tester.getNumEmittedLines());
        assertEquals(4, tester.getNumConsumedLines());
    }

    @Test
    public final void testOutput() throws IOException {
        tester.operate();
        assertEquals(4, tester.results.size());
        List<Integer> expectedResults = Arrays.asList(3, 2, 1, 3);
        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals("Test result of case " + (i + 1), expectedResults.get(i), tester.results.get(i));
        }
    }
}
