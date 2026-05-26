/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3;

import java.net.URLClassLoader;
import java.util.Arrays;

public class ClassLoaderUtils {
    public static String toString(ClassLoader classLoader) {
        if (classLoader instanceof URLClassLoader) {
            return ClassLoaderUtils.toString((URLClassLoader)classLoader);
        }
        return classLoader.toString();
    }

    public static String toString(URLClassLoader classLoader) {
        return classLoader + Arrays.toString(classLoader.getURLs());
    }
}

