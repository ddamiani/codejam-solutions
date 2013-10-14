package com.ddamiani.codejam;

import java.io.*;
import java.net.URL;

/**
 * A helper class for listing all classes in a package
 */
public final class ClassLister<T> {
    private final Class<T> baseClass;

    public ClassLister(final Class<T> baseClass) {
        this.baseClass = baseClass;
    }

    public Class<? extends T> getFirstSubClassInPackage(final String packageName) {
        String packageDirName;
        if ((packageDirName = getPackageDirName(packageName)) != null) {
            // Open a file object pointing to the package dir in the Jar
            File packageDir = new File(packageDirName);
            if (packageDir.exists()) {
                for (String file : packageDir.list()) {
                    final Class<? extends T> foundClass = getAndTestClass(packageName, file);
                    if (foundClass != null) {
                        return foundClass;
                    }
                }
            }
        }

        return null;
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

    private static String getPackageDirName(String packageName) {
        URL packageDirUrl = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
        return packageDirUrl != null ? packageDirUrl.getFile() : null;
    }
}
