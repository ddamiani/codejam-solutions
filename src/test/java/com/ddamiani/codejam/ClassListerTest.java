package com.ddamiani.codejam;

import com.ddamiani.codejam.dance.DanceContest;
import com.ddamiani.codejam.filehandler.CodeJamFileHandler;
import com.ddamiani.codejam.lawn.LawnMower;
import com.ddamiani.codejam.palindrome.SquarePalindrome;
import com.ddamiani.codejam.palindrome.SquarePalindromeBig;
import com.ddamiani.codejam.recycle.Recycler;
import com.ddamiani.codejam.tictactoe.TicTacToe;
import com.ddamiani.codejam.tongues.Tongues;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Unit tests of the ClassLister.
 */
public final class ClassListerTest {
    private static final String packageBaseName = ClassListerTest.class.getPackage().getName();
    private ClassLister<CodeJamFileHandler> lister;

    @Before
    public final void setUp() {
        lister = new ClassLister<>(CodeJamFileHandler.class);
    }

    @Test
    public final void testFindClass() {
        final Class<? extends CodeJamFileHandler> test = lister.getClassInPackage(packageBaseName, "Tongues");
        assertNotNull("Check that a class was returned", test);
        assertEquals("Check that the returned class is correct", Tongues.class, test);

        final Class<? extends CodeJamFileHandler> testBad = lister.getClassInPackage(packageBaseName, "Tongue");
        assertNull("Check that a class was returned", testBad);
    }

    @Test
    public final void testGetClassList() {
        final Set<Class<? extends CodeJamFileHandler>> classSet = lister.getClassSetInPackage(packageBaseName);
        final Set<Class<? extends CodeJamFileHandler>> expectedSet = new HashSet<>();
        expectedSet.add(DanceContest.class);
        expectedSet.add(SquarePalindromeBig.class);
        expectedSet.add(LawnMower.class);
        expectedSet.add(SquarePalindrome.class);
        expectedSet.add(Recycler.class);
        expectedSet.add(TicTacToe.class);
        expectedSet.add(Tongues.class);


        assertEquals("Test the class list size", 7, classSet.size());
        assertEquals("Test that the list contains the expected entries", expectedSet, classSet);
    }
}
