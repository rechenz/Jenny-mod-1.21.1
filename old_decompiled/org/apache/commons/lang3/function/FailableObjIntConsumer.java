/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableObjIntConsumer<T, E extends Throwable> {
    public static final FailableObjIntConsumer NOP = (t2, u2) -> {};

    public static <T, E extends Throwable> FailableObjIntConsumer<T, E> nop() {
        return NOP;
    }

    public void accept(T var1, int var2) throws E;
}

