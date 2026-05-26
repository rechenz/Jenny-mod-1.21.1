/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.compare;

import java.util.function.Predicate;

public class ComparableUtils {
    public static <A extends Comparable<A>> Predicate<A> between(A b10, A c10) {
        return a10 -> ComparableUtils.is(a10).between(b10, c10);
    }

    public static <A extends Comparable<A>> Predicate<A> betweenExclusive(A b10, A c10) {
        return a10 -> ComparableUtils.is(a10).betweenExclusive(b10, c10);
    }

    public static <A extends Comparable<A>> Predicate<A> ge(A b10) {
        return a10 -> ComparableUtils.is(a10).greaterThanOrEqualTo(b10);
    }

    public static <A extends Comparable<A>> Predicate<A> gt(A b10) {
        return a10 -> ComparableUtils.is(a10).greaterThan(b10);
    }

    public static <A extends Comparable<A>> ComparableCheckBuilder<A> is(A a10) {
        return new ComparableCheckBuilder(a10, null);
    }

    public static <A extends Comparable<A>> Predicate<A> le(A b10) {
        return a10 -> ComparableUtils.is(a10).lessThanOrEqualTo(b10);
    }

    public static <A extends Comparable<A>> Predicate<A> lt(A b10) {
        return a10 -> ComparableUtils.is(a10).lessThan(b10);
    }

    private ComparableUtils() {
    }

    public static class ComparableCheckBuilder<A extends Comparable<A>> {
        private final A a;

        private ComparableCheckBuilder(A a10) {
            this.a = a10;
        }

        public boolean between(A b10, A c10) {
            return this.betweenOrdered(b10, c10) || this.betweenOrdered(c10, b10);
        }

        public boolean betweenExclusive(A b10, A c10) {
            return this.betweenOrderedExclusive(b10, c10) || this.betweenOrderedExclusive(c10, b10);
        }

        private boolean betweenOrdered(A b10, A c10) {
            return this.greaterThanOrEqualTo(b10) && this.lessThanOrEqualTo(c10);
        }

        private boolean betweenOrderedExclusive(A b10, A c10) {
            return this.greaterThan(b10) && this.lessThan(c10);
        }

        public boolean equalTo(A b10) {
            return this.a.compareTo(b10) == 0;
        }

        public boolean greaterThan(A b10) {
            return this.a.compareTo(b10) > 0;
        }

        public boolean greaterThanOrEqualTo(A b10) {
            return this.a.compareTo(b10) >= 0;
        }

        public boolean lessThan(A b10) {
            return this.a.compareTo(b10) < 0;
        }

        public boolean lessThanOrEqualTo(A b10) {
            return this.a.compareTo(b10) <= 0;
        }

        /* synthetic */ ComparableCheckBuilder(Comparable x0, 1 x1) {
            this(x0);
        }
    }
}

