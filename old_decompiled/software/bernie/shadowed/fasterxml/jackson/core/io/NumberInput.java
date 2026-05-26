/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.io;

import java.math.BigDecimal;

public final class NumberInput {
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final long L_BILLION = 1000000000L;
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    public static int parseInt(char[] ch2, int off, int len) {
        int num = ch2[off] - 48;
        if (len > 4) {
            num = num * 10 + (ch2[++off] - 48);
            num = num * 10 + (ch2[++off] - 48);
            num = num * 10 + (ch2[++off] - 48);
            num = num * 10 + (ch2[++off] - 48);
            if ((len -= 4) > 4) {
                num = num * 10 + (ch2[++off] - 48);
                num = num * 10 + (ch2[++off] - 48);
                num = num * 10 + (ch2[++off] - 48);
                num = num * 10 + (ch2[++off] - 48);
                return num;
            }
        }
        if (len > 1) {
            num = num * 10 + (ch2[++off] - 48);
            if (len > 2) {
                num = num * 10 + (ch2[++off] - 48);
                if (len > 3) {
                    num = num * 10 + (ch2[++off] - 48);
                }
            }
        }
        return num;
    }

    public static int parseInt(String s2) {
        char c10 = s2.charAt(0);
        int len = s2.length();
        boolean neg = c10 == '-';
        int offset = 1;
        if (neg) {
            if (len == 1 || len > 10) {
                return Integer.parseInt(s2);
            }
            c10 = s2.charAt(offset++);
        } else if (len > 9) {
            return Integer.parseInt(s2);
        }
        if (c10 > '9' || c10 < '0') {
            return Integer.parseInt(s2);
        }
        int num = c10 - 48;
        if (offset < len) {
            if ((c10 = s2.charAt(offset++)) > '9' || c10 < '0') {
                return Integer.parseInt(s2);
            }
            num = num * 10 + (c10 - 48);
            if (offset < len) {
                if ((c10 = s2.charAt(offset++)) > '9' || c10 < '0') {
                    return Integer.parseInt(s2);
                }
                num = num * 10 + (c10 - 48);
                if (offset < len) {
                    do {
                        if ((c10 = s2.charAt(offset++)) > '9' || c10 < '0') {
                            return Integer.parseInt(s2);
                        }
                        num = num * 10 + (c10 - 48);
                    } while (offset < len);
                }
            }
        }
        return neg ? -num : num;
    }

    public static long parseLong(char[] ch2, int off, int len) {
        int len1 = len - 9;
        long val = (long)NumberInput.parseInt(ch2, off, len1) * 1000000000L;
        return val + (long)NumberInput.parseInt(ch2, off + len1, 9);
    }

    public static long parseLong(String s2) {
        int length = s2.length();
        if (length <= 9) {
            return NumberInput.parseInt(s2);
        }
        return Long.parseLong(s2);
    }

    public static boolean inLongRange(char[] ch2, int off, int len, boolean negative) {
        String cmpStr = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int cmpLen = cmpStr.length();
        if (len < cmpLen) {
            return true;
        }
        if (len > cmpLen) {
            return false;
        }
        for (int i2 = 0; i2 < cmpLen; ++i2) {
            int diff = ch2[off + i2] - cmpStr.charAt(i2);
            if (diff == 0) continue;
            return diff < 0;
        }
        return true;
    }

    public static boolean inLongRange(String s2, boolean negative) {
        String cmp = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int cmpLen = cmp.length();
        int alen = s2.length();
        if (alen < cmpLen) {
            return true;
        }
        if (alen > cmpLen) {
            return false;
        }
        for (int i2 = 0; i2 < cmpLen; ++i2) {
            int diff = s2.charAt(i2) - cmp.charAt(i2);
            if (diff == 0) continue;
            return diff < 0;
        }
        return true;
    }

    public static int parseAsInt(String s2, int def) {
        char c10;
        if (s2 == null) {
            return def;
        }
        int len = (s2 = s2.trim()).length();
        if (len == 0) {
            return def;
        }
        int i2 = 0;
        if (i2 < len) {
            c10 = s2.charAt(0);
            if (c10 == '+') {
                s2 = s2.substring(1);
                len = s2.length();
            } else if (c10 == '-') {
                ++i2;
            }
        }
        while (i2 < len) {
            c10 = s2.charAt(i2);
            if (c10 > '9' || c10 < '0') {
                try {
                    return (int)NumberInput.parseDouble(s2);
                }
                catch (NumberFormatException e10) {
                    return def;
                }
            }
            ++i2;
        }
        try {
            return Integer.parseInt(s2);
        }
        catch (NumberFormatException e11) {
            return def;
        }
    }

    public static long parseAsLong(String s2, long def) {
        char c10;
        if (s2 == null) {
            return def;
        }
        int len = (s2 = s2.trim()).length();
        if (len == 0) {
            return def;
        }
        int i2 = 0;
        if (i2 < len) {
            c10 = s2.charAt(0);
            if (c10 == '+') {
                s2 = s2.substring(1);
                len = s2.length();
            } else if (c10 == '-') {
                ++i2;
            }
        }
        while (i2 < len) {
            c10 = s2.charAt(i2);
            if (c10 > '9' || c10 < '0') {
                try {
                    return (long)NumberInput.parseDouble(s2);
                }
                catch (NumberFormatException e10) {
                    return def;
                }
            }
            ++i2;
        }
        try {
            return Long.parseLong(s2);
        }
        catch (NumberFormatException e11) {
            return def;
        }
    }

    public static double parseAsDouble(String s2, double def) {
        if (s2 == null) {
            return def;
        }
        int len = (s2 = s2.trim()).length();
        if (len == 0) {
            return def;
        }
        try {
            return NumberInput.parseDouble(s2);
        }
        catch (NumberFormatException e10) {
            return def;
        }
    }

    public static double parseDouble(String s2) throws NumberFormatException {
        if (NASTY_SMALL_DOUBLE.equals(s2)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(s2);
    }

    public static BigDecimal parseBigDecimal(String s2) throws NumberFormatException {
        try {
            return new BigDecimal(s2);
        }
        catch (NumberFormatException e10) {
            throw NumberInput._badBD(s2);
        }
    }

    public static BigDecimal parseBigDecimal(char[] b10) throws NumberFormatException {
        return NumberInput.parseBigDecimal(b10, 0, b10.length);
    }

    public static BigDecimal parseBigDecimal(char[] b10, int off, int len) throws NumberFormatException {
        try {
            return new BigDecimal(b10, off, len);
        }
        catch (NumberFormatException e10) {
            throw NumberInput._badBD(new String(b10, off, len));
        }
    }

    private static NumberFormatException _badBD(String s2) {
        return new NumberFormatException("Value \"" + s2 + "\" can not be represented as BigDecimal");
    }
}

