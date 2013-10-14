package com.ddamiani.codejam;

import org.reflections.Reflections;

import java.util.*;

/**
 * A helper class for listing all classes in a package
 */
public final class ClassLister<T> {
    private final Class<T> baseClass;

    public ClassLister(final Class<T> baseClass) {
        this.baseClass = baseClass;
    }

    public final Class<? extends T> getClassInPackage(final String packageName, final String className) {
        for (Class<? extends T> currentClass : getClassSetInPackage(packageName)) {
            if (currentClass.getSimpleName().equals(className)) {
                return currentClass;
            }
        }
        return null;
    }

    public final Set<Class<? extends T>> getClassSetInPackage(final String packageName) {
        final Reflections reflections = new Reflections(packageName);
        return reflections.getSubTypesOf(baseClass);
    }
}
