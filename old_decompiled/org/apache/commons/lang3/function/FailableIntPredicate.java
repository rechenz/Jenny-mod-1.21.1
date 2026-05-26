/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableIntPredicate<E extends Throwable> {
    public static final FailableIntPredicate FALSE = t2 -> false;
    public static final FailableIntPredicate TRUE = t2 -> true;

    public static <E extends Throwable> FailableIntPredicate<E> falsePredicate() {
        return FALSE;
    }

    public static <E extends Throwable> FailableIntPredicate<E> truePredicate() {
        return TRUE;
    }

    default public FailableIntPredicate<E> and(FailableIntPredicate<E> other) {
        Objects.requireNonNull(other);
        return t2 -> this.test(t2) && other.test(t2);
    }

    default public FailableIntPredicate<E> negate() {
        return t2 -> !this.test(t2);
    }

    default public FailableIntPredicate<E> or(FailableIntPredicate<E> other) {
        Objects.requireNonNull(other);
        return t2 -> this.test(t2) || other.test(t2);
    }

    public boolean test(int var1) throws E;
}

