package com.ddamiani.codejam.palindrome;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the square palindrome class.
 */
public final class SquarePalindromeTest {
    private SquarePalindrome tester;

    @Before
    public final void setUp() throws FileNotFoundException {
        tester = new SquarePalindrome(this.getClass().getResource("/test_palindrome_input.txt").getFile(), null, true);
    }

    @Test
    public final void testTestMode() throws FileNotFoundException {
        SquarePalindrome nonTestSquarePalindrome = new SquarePalindrome(this.getClass().getResource("/test_palindrome_input.txt").getFile(), null);
        assertFalse(nonTestSquarePalindrome.isTestMode());
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
    public final void testIsAPalindrome() {
        final List<Long> palindromes = Arrays.asList(1l, 3l, 6l, 11l, 22l, 8778l, 98789l);
        final List<Long> notPalindromes = Arrays.asList(12l, 122l, 87781l, 98798l);

        for(long number : palindromes) {
            assertTrue("The number " + number + " should be a palindrome.", SquarePalindrome.isAPalindrome(number));
        }

        for(long number : notPalindromes) {
            assertFalse("The number " + number + " should not be a palindrome.", SquarePalindrome.isAPalindrome(number));
        }
    }

    @Test
    public final void testOutput() throws IOException {
        tester.operate();
        assertEquals(3, tester.results.size());
        List<Long> expectedResults = Arrays.asList(2l, 0l, 2l);
        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals("Test of palindrome set " + (i + 1), expectedResults.get(i), tester.results.get(i));
        }
    }
}
