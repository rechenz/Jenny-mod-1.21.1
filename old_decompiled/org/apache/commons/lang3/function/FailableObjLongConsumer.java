/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableObjLongConsumer<T, E extends Throwable> {
    public static final FailableObjLongConsumer NOP = (t2, u2) -> {};

    public static <T, E extends Throwable> FailableObjLongConsumer<T, E> nop() {
        return NOP;
    }

    public void accept(T var1, long var2) throws E;
}

