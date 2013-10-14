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

    public final List<String> getSubpackagesWithSubClass(final String packageName) {
        List<String> packageList = new ArrayList<>();

        for (String subPackageName : getPackageFileList(packageName, true)) {
            Class<? extends T> foundClass = getFirstSubClassInPackage(packageName + "." + subPackageName);
            if (foundClass != null && foundClass != baseClass) {
                packageList.add(subPackageName);
            }
        }

        return packageList;
    }

    public final Class<? extends T> getFirstSubClassInPackage(final String packageName) {
        for (String filename : getPackageFileList(packageName, false)) {
            final Class<? extends T> foundClass = getAndTestClass(packageName, filename);
            if (foundClass != null) {
                return foundClass;
            }
        }

        return null;
    }

    public final List<Class<? extends T>> getClassListInPackage(final String packageName) {
        List<Class<? extends T>> classList = new ArrayList<>();
        for (String filename : getPackageFileList(packageName, false)) {
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

    private static String[] getPackageFileList(String packageName, boolean filter) {
        URL packageDirUrl = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
        if (packageDirUrl != null) {
            File packageDir = new File(packageDirUrl.getFile());
            if (packageDir.isDirectory()) {
                if (filter) {
                    return packageDir.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            File candidate = new File(dir, name);
                            return candidate.isDirectory();
                        }
                    });
                } else {
                    return packageDir.list();
                }
            }
        }
        return new String[0];
    }
}
