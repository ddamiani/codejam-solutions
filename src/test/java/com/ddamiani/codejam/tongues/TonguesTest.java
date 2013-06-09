package com.ddamiani.codejam.tongues;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public final class TonguesTest {
    private Tongues tester;

    @Before
    public final void setUp() throws FileNotFoundException {
        tester = new Tongues(this.getClass().getResource("/test_input.txt").getFile(), null, true);
    }

    @Test
    public final void testTestMode() throws FileNotFoundException {
        Tongues nonTestTongues = new Tongues(this.getClass().getResource("/test_input.txt").getFile(), null);
        assertFalse(nonTestTongues.isTestMode());
    }

    @Test
    public final void testLineNum() throws IOException {
        assertEquals(0, tester.getNumCases());
        tester.operate();
        assertEquals(3, tester.getNumCases());
        assertEquals(tester.getNumCases(), tester.getNumEmittedLines());
        assertEquals(tester.getNumCases() * tester.getNumLinesPerCase(), tester.getNumConsumedLines());
    }

    @Test
    public final void testOutput() throws IOException {
        tester.operate();
        assertEquals(3, tester.lines.size());
        List<String> results = Arrays.asList("our language is impossible to understand",
                "there are twenty six factorial possibilities",
                "so it is okay if you want to just give up");
        for (int i = 0; i < results.size(); i++) {
            assertEquals(results.get(i), tester.lines.get(i));
        }
    }
}
