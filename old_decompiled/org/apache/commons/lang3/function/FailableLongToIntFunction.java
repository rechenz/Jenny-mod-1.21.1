/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongToIntFunction<E extends Throwable> {
    public static final FailableLongToIntFunction NOP = t2 -> 0;

    public static <E extends Throwable> FailableLongToIntFunction<E> nop() {
        return NOP;
    }

    public int applyAsInt(long var1) throws E;
}

