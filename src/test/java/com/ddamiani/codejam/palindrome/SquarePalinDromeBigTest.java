package com.ddamiani.codejam.palindrome;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the big version of the square palindrome class.
 */
public final class SquarePalindromeBigTest {
    private SquarePalindromeBig tester;

    @Before
    public final void setUp() throws FileNotFoundException {
        tester = new SquarePalindromeBig(this.getClass().getResource("/test_palindrome_input.txt").getFile(), null, true);
    }

    @Test
    public final void testTestMode() throws FileNotFoundException {
        SquarePalindromeBig nonTestSquarePalindromeBig = new SquarePalindromeBig(this.getClass().getResource("/test_palindrome_input.txt").getFile(), null);
        assertFalse(nonTestSquarePalindromeBig.isTestMode());
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
        final List<BigInteger> palindromes = Arrays.asList(BigInteger.valueOf(1l),
                BigInteger.valueOf(3l),
                BigInteger.valueOf(6l),
                BigInteger.valueOf(11l),
                BigInteger.valueOf(22l),
                BigInteger.valueOf(8778l),
                BigInteger.valueOf(98789l));
        final List<BigInteger> notPalindromes = Arrays.asList(BigInteger.valueOf(12l),
                BigInteger.valueOf(122l),
                BigInteger.valueOf(87781l),
                BigInteger.valueOf(98798l));

        for(BigInteger number : palindromes) {
            assertTrue("The number " + number + " should be a palindrome.", SquarePalindromeBig.isAPalindrome(number));
        }

        for(BigInteger number : notPalindromes) {
            assertFalse("The number " + number + " should not be a palindrome.", SquarePalindromeBig.isAPalindrome(number));
        }
    }

    @Test
    public final void testSquareRoot() {
        assertEquals(BigInteger.valueOf(313), SquarePalindromeBig.squareRoot(BigInteger.valueOf(97948l), false));
        assertEquals(BigInteger.valueOf(32l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(1000l), false));
        assertEquals(BigInteger.valueOf(10l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(82l), false));
        assertEquals(BigInteger.valueOf(9l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(80l), false));
        assertEquals(BigInteger.valueOf(1l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(1l), false));
        assertEquals(BigInteger.valueOf(0l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(0l), false));
        assertEquals(BigInteger.valueOf(9l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(81l), false));

        assertEquals(BigInteger.valueOf(9l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(82l), true));
        assertEquals(BigInteger.valueOf(8l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(80l), true));
        assertEquals(BigInteger.valueOf(1l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(1l), true));
        assertEquals(BigInteger.valueOf(0l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(0l), true));
        assertEquals(BigInteger.valueOf(9l), SquarePalindromeBig.squareRoot(BigInteger.valueOf(81l), true));
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
