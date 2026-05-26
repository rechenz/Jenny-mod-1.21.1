/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntFunction<R, E extends Throwable> {
    public static final FailableIntFunction NOP = t2 -> null;

    public static <R, E extends Throwable> FailableIntFunction<R, E> nop() {
        return NOP;
    }

    public R apply(int var1) throws E;
}

