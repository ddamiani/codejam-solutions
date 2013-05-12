package com.ddamiani.codejam.recycle;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit tests for the Recycler class
 */
public final class RecyclerTest {
    private Recycler tester;

    @Before
    public final void setUp() throws FileNotFoundException {
        tester = new Recycler(this.getClass().getResource("/test_recycle_input.txt").getFile(), null, true);
    }

    @Test
    public final void testLineNum() throws IOException {
        assertEquals(0, tester.getNumLines());
        tester.operate();
        assertEquals(4, tester.getNumLines());
        assertEquals(4, tester.getNumEmittedLines());
        assertEquals(4, tester.getNumConsumedLines());
    }

    @Test
    public final void testOutput() throws IOException {
        tester.operate();
        assertEquals(4, tester.results.size());
        List<Integer> expectedResults = Arrays.asList(0, 3, 156, 287);
        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals(expectedResults.get(i), tester.results.get(i));
        }
    }

    @Test
    public final void testReverse() {
        assertEquals("45123", Recycler.reverse("12345", 3));
        assertEquals("12345", Recycler.reverse("12345", 0));
        assertEquals("12345", Recycler.reverse("12345", 5));
        try {
            assertEquals("12345", Recycler.reverse("12345", 6));
            fail("An string index out of bounds should have been thrown");
        } catch (StringIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }
}
