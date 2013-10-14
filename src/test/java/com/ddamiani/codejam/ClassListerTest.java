package com.ddamiani.codejam;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests of the ClassLister.
 */
public final class ClassListerTest {
    private final class FakeFileHandler extends CodeJamFileHandler {
        public FakeFileHandler() throws FileNotFoundException {
            super(null, null, false);
        }

        public void operateImpl() {
            // No op
        }
    }

    private final class SuperFakeFileHandler extends CodeJamFileHandler {
        public SuperFakeFileHandler() throws FileNotFoundException {
            super(null, null, false);
        }

        public void operateImpl() {
            // No op
        }
    }

    private static final String packageBaseName = ClassListerTest.class.getPackage().getName();
    private ClassLister<CodeJamFileHandler> lister;

    @Before
    public final void setUp() {
        lister = new ClassLister<>(CodeJamFileHandler.class);
    }

    @Test
    public final void testFindClass() {
        final Class<? extends CodeJamFileHandler> test = lister.getFirstSubClassInPackage(packageBaseName);
        assertNotNull("Check that a class was returned", test);
        assertEquals("Check that the returned class is correct", FakeFileHandler.class, test);
    }

    @Test
    public final void testGetClassList() {
        final List<Class<? extends CodeJamFileHandler>> classList = lister.getClassListInPackage(packageBaseName);
        final List<Class<? extends CodeJamFileHandler>> expectedList = new ArrayList<>();
        expectedList.add(FakeFileHandler.class);
        expectedList.add(SuperFakeFileHandler.class);

        assertEquals("Test the class list size", 2, classList.size());
        assertEquals("Test that the list contains the expected entries", expectedList, classList);
    }

    @Test
    public final void testGetSubpackagesWithSubClass() {
        final List<String> packageList = lister.getSubpackagesWithSubClass(packageBaseName);
        assertEquals("Test that the list is the right size", 0, packageList.size());
    }
}
