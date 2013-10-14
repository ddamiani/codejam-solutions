package com.ddamiani.codejam;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * A helper class for listing all classes in a package
 */
public final class ClassLister<T> {
    private final Class<T> baseClass;

    public ClassLister(final Class<T> baseClass) {
        this.baseClass = baseClass;
    }

    public final Class<? extends T> getFirstSubClassInPackage(final String packageName) {
        for (String filename : getPackageFileList(packageName)) {
            final Class<? extends T> foundClass = getAndTestClass(packageName, filename);
            if (foundClass != null) {
                return foundClass;
            }
        }

        return null;
    }

    public final List<Class<? extends T>> getClassListInPackage(final String packageName) {
        List<Class<? extends T>> classList = new ArrayList<>();
        for (String filename : getPackageFileList(packageName)) {
            final Class<? extends T> foundClass = getAndTestClass(packageName, filename);
            if (foundClass != null) {
                classList.add(foundClass);
            }
        }
        return classList;
    }

    private Class<? extends T> getAndTestClass(final String packageName, final String fileName) {
        final String extension = ".class";
        if (fileName.endsWith(extension)) {
            try {
                return Class.forName(packageName + "." + fileName.substring(0, fileName.lastIndexOf(extension)))
                        .asSubclass(baseClass);
            } catch (ClassNotFoundException | ClassCastException e) {
                return null;
            }
        }

        return null;
    }

    private static String[] getPackageFileList(String packageName) {
        URL packageDirUrl = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
        if (packageDirUrl != null) {
            File packageDir = new File(packageDirUrl.getFile());
            if (packageDir.isDirectory()) {
                return packageDir.list();
            }
        }
        return new String[0];
    }
}
