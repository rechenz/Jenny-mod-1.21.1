/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310;

import java.math.BigDecimal;

public final class DecimalUtils {
    private static final BigDecimal ONE_BILLION = new BigDecimal(1000000000L);

    private DecimalUtils() {
        throw new RuntimeException("DecimalUtils cannot be instantiated.");
    }

    public static String toDecimal(long seconds, int nanoseconds) {
        StringBuilder sb = new StringBuilder(20).append(seconds).append('.');
        if ((long)nanoseconds == 0L) {
            if (seconds == 0L) {
                return "0.0";
            }
            sb.append("000000000");
        } else {
            StringBuilder nanoSB = new StringBuilder(9);
            nanoSB.append(nanoseconds);
            int nanosLen = nanoSB.length();
            for (int prepZeroes = 9 - nanosLen; prepZeroes > 0; --prepZeroes) {
                sb.append('0');
            }
            sb.append((CharSequence)nanoSB);
        }
        return sb.toString();
    }

    public static BigDecimal toBigDecimal(long seconds, int nanoseconds) {
        if ((long)nanoseconds == 0L) {
            if (seconds == 0L) {
                return BigDecimal.ZERO.setScale(1);
            }
            return BigDecimal.valueOf(seconds).setScale(9);
        }
        return new BigDecimal(DecimalUtils.toDecimal(seconds, nanoseconds));
    }

    public static int extractNanosecondDecimal(BigDecimal value, long integer) {
        return value.subtract(new BigDecimal(integer)).multiply(ONE_BILLION).intValue();
    }
}

