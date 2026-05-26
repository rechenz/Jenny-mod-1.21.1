/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.reflect;

import java.lang.reflect.Type;

@FunctionalInterface
public interface Typed<T> {
    public Type getType();
}

