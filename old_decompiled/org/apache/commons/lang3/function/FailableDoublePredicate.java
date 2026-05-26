/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableDoublePredicate<E extends Throwable> {
    public static final FailableDoublePredicate FALSE = t2 -> false;
    public static final FailableDoublePredicate TRUE = t2 -> true;

    public static <E extends Throwable> FailableDoublePredicate<E> falsePredicate() {
        return FALSE;
    }

    public static <E extends Throwable> FailableDoublePredicate<E> truePredicate() {
        return TRUE;
    }

    default public FailableDoublePredicate<E> and(FailableDoublePredicate<E> other) {
        Objects.requireNonNull(other);
        return t2 -> this.test(t2) && other.test(t2);
    }

    default public FailableDoublePredicate<E> negate() {
        return t2 -> !this.test(t2);
    }

    default public FailableDoublePredicate<E> or(FailableDoublePredicate<E> other) {
        Objects.requireNonNull(other);
        return t2 -> this.test(t2) || other.test(t2);
    }

    public boolean test(double var1) throws E;
}

