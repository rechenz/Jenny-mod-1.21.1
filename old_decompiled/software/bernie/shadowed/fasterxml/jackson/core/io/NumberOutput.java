/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.io;

public final class NumberOutput {
    private static int MILLION = 1000000;
    private static int BILLION = 1000000000;
    private static long BILLION_L = 1000000000L;
    private static long MIN_INT_AS_LONG = Integer.MIN_VALUE;
    private static long MAX_INT_AS_LONG = Integer.MAX_VALUE;
    static final String SMALLEST_INT = String.valueOf(Integer.MIN_VALUE);
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static final int[] TRIPLET_TO_CHARS = new int[1000];
    private static final String[] sSmallIntStrs;
    private static final String[] sSmallIntStrs2;

    public static int outputInt(int v2, char[] b10, int off) {
        if (v2 < 0) {
            if (v2 == Integer.MIN_VALUE) {
                return NumberOutput._outputSmallestI(b10, off);
            }
            b10[off++] = 45;
            v2 = -v2;
        }
        if (v2 < MILLION) {
            if (v2 < 1000) {
                if (v2 < 10) {
                    b10[off] = (char)(48 + v2);
                    return off + 1;
                }
                return NumberOutput._leading3(v2, b10, off);
            }
            int thousands = v2 / 1000;
            off = NumberOutput._leading3(thousands, b10, off);
            off = NumberOutput._full3(v2 -= thousands * 1000, b10, off);
            return off;
        }
        if (v2 >= BILLION) {
            if ((v2 -= BILLION) >= BILLION) {
                v2 -= BILLION;
                b10[off++] = 50;
            } else {
                b10[off++] = 49;
            }
            return NumberOutput._outputFullBillion(v2, b10, off);
        }
        int newValue = v2 / 1000;
        int ones = v2 - newValue * 1000;
        v2 = newValue;
        int thousands = v2 - (newValue /= 1000) * 1000;
        off = NumberOutput._leading3(newValue, b10, off);
        off = NumberOutput._full3(thousands, b10, off);
        return NumberOutput._full3(ones, b10, off);
    }

    public static int outputInt(int v2, byte[] b10, int off) {
        if (v2 < 0) {
            if (v2 == Integer.MIN_VALUE) {
                return NumberOutput._outputSmallestI(b10, off);
            }
            b10[off++] = 45;
            v2 = -v2;
        }
        if (v2 < MILLION) {
            if (v2 < 1000) {
                if (v2 < 10) {
                    b10[off++] = (byte)(48 + v2);
                } else {
                    off = NumberOutput._leading3(v2, b10, off);
                }
            } else {
                int thousands = v2 / 1000;
                off = NumberOutput._leading3(thousands, b10, off);
                off = NumberOutput._full3(v2 -= thousands * 1000, b10, off);
            }
            return off;
        }
        if (v2 >= BILLION) {
            if ((v2 -= BILLION) >= BILLION) {
                v2 -= BILLION;
                b10[off++] = 50;
            } else {
                b10[off++] = 49;
            }
            return NumberOutput._outputFullBillion(v2, b10, off);
        }
        int newValue = v2 / 1000;
        int ones = v2 - newValue * 1000;
        v2 = newValue;
        int thousands = v2 - (newValue /= 1000) * 1000;
        off = NumberOutput._leading3(newValue, b10, off);
        off = NumberOutput._full3(thousands, b10, off);
        return NumberOutput._full3(ones, b10, off);
    }

    public static int outputLong(long v2, char[] b10, int off) {
        if (v2 < 0L) {
            if (v2 > MIN_INT_AS_LONG) {
                return NumberOutput.outputInt((int)v2, b10, off);
            }
            if (v2 == Long.MIN_VALUE) {
                return NumberOutput._outputSmallestL(b10, off);
            }
            b10[off++] = 45;
            v2 = -v2;
        } else if (v2 <= MAX_INT_AS_LONG) {
            return NumberOutput.outputInt((int)v2, b10, off);
        }
        long upper = v2 / BILLION_L;
        v2 -= upper * BILLION_L;
        if (upper < BILLION_L) {
            off = NumberOutput._outputUptoBillion((int)upper, b10, off);
        } else {
            long hi = upper / BILLION_L;
            off = NumberOutput._leading3((int)hi, b10, off);
            off = NumberOutput._outputFullBillion((int)(upper -= hi * BILLION_L), b10, off);
        }
        return NumberOutput._outputFullBillion((int)v2, b10, off);
    }

    public static int outputLong(long v2, byte[] b10, int off) {
        if (v2 < 0L) {
            if (v2 > MIN_INT_AS_LONG) {
                return NumberOutput.outputInt((int)v2, b10, off);
            }
            if (v2 == Long.MIN_VALUE) {
                return NumberOutput._outputSmallestL(b10, off);
            }
            b10[off++] = 45;
            v2 = -v2;
        } else if (v2 <= MAX_INT_AS_LONG) {
            return NumberOutput.outputInt((int)v2, b10, off);
        }
        long upper = v2 / BILLION_L;
        v2 -= upper * BILLION_L;
        if (upper < BILLION_L) {
            off = NumberOutput._outputUptoBillion((int)upper, b10, off);
        } else {
            long hi = upper / BILLION_L;
            off = NumberOutput._leading3((int)hi, b10, off);
            off = NumberOutput._outputFullBillion((int)(upper -= hi * BILLION_L), b10, off);
        }
        return NumberOutput._outputFullBillion((int)v2, b10, off);
    }

    public static String toString(int v2) {
        if (v2 < sSmallIntStrs.length) {
            if (v2 >= 0) {
                return sSmallIntStrs[v2];
            }
            int v22 = -v2 - 1;
            if (v22 < sSmallIntStrs2.length) {
                return sSmallIntStrs2[v22];
            }
        }
        return Integer.toString(v2);
    }

    public static String toString(long v2) {
        if (v2 <= Integer.MAX_VALUE && v2 >= Integer.MIN_VALUE) {
            return NumberOutput.toString((int)v2);
        }
        return Long.toString(v2);
    }

    public static String toString(double v2) {
        return Double.toString(v2);
    }

    public static String toString(float v2) {
        return Float.toString(v2);
    }

    private static int _outputUptoBillion(int v2, char[] b10, int off) {
        if (v2 < MILLION) {
            if (v2 < 1000) {
                return NumberOutput._leading3(v2, b10, off);
            }
            int thousands = v2 / 1000;
            int ones = v2 - thousands * 1000;
            return NumberOutput._outputUptoMillion(b10, off, thousands, ones);
        }
        int thousands = v2 / 1000;
        int ones = v2 - thousands * 1000;
        int millions = thousands / 1000;
        off = NumberOutput._leading3(millions, b10, off);
        int enc = TRIPLET_TO_CHARS[thousands -= millions * 1000];
        b10[off++] = (char)(enc >> 16);
        b10[off++] = (char)(enc >> 8 & 0x7F);
        b10[off++] = (char)(enc & 0x7F);
        enc = TRIPLET_TO_CHARS[ones];
        b10[off++] = (char)(enc >> 16);
        b10[off++] = (char)(enc >> 8 & 0x7F);
        b10[off++] = (char)(enc & 0x7F);
        return off;
    }

    private static int _outputFullBillion(int v2, char[] b10, int off) {
        int thousands = v2 / 1000;
        int ones = v2 - thousands * 1000;
        int millions = thousands / 1000;
        int enc = TRIPLET_TO_CHARS[millions];
        b10[off++] = (char)(enc >> 16);
        b10[off++] = (char)(enc >> 8 & 0x7F);
        b10[off++] = (char)(enc & 0x7F);
        enc = TRIPLET_TO_CHARS[thousands -= millions * 1000];
        b10[off++] = (char)(enc >> 16);
        b10[off++] = (char)(enc >> 8 & 0x7F);
        b10[off++] = (char)(enc & 0x7F);
        enc = TRIPLET_TO_CHARS[ones];
        b10[off++] = (char)(enc >> 16);
        b10[off++] = (char)(enc >> 8 & 0x7F);
        b10[off++] = (char)(enc & 0x7F);
        return off;
    }

    private static int _outputUptoBillion(int v2, byte[] b10, int off) {
        if (v2 < MILLION) {
            if (v2 < 1000) {
                return NumberOutput._leading3(v2, b10, off);
            }
            int thousands = v2 / 1000;
            int ones = v2 - thousands * 1000;
            return NumberOutput._outputUptoMillion(b10, off, thousands, ones);
        }
        int thousands = v2 / 1000;
        int ones = v2 - thousands * 1000;
        int millions = thousands / 1000;
        off = NumberOutput._leading3(millions, b10, off);
        int enc = TRIPLET_TO_CHARS[thousands -= millions * 1000];
        b10[off++] = (byte)(enc >> 16);
        b10[off++] = (byte)(enc >> 8);
        b10[off++] = (byte)enc;
        enc = TRIPLET_TO_CHARS[ones];
        b10[off++] = (byte)(enc >> 16);
        b10[off++] = (byte)(enc >> 8);
        b10[off++] = (byte)enc;
        return off;
    }

    private static int _outputFullBillion(int v2, byte[] b10, int off) {
        int thousands = v2 / 1000;
        int ones = v2 - thousands * 1000;
        int millions = thousands / 1000;
        int enc = TRIPLET_TO_CHARS[millions];
        b10[off++] = (byte)(enc >> 16);
        b10[off++] = (byte)(enc >> 8);
        b10[off++] = (byte)enc;
        enc = TRIPLET_TO_CHARS[thousands -= millions * 1000];
        b10[off++] = (byte)(enc >> 16);
        b10[off++] = (byte)(enc >> 8);
        b10[off++] = (byte)enc;
        enc = TRIPLET_TO_CHARS[ones];
        b10[off++] = (byte)(enc >> 16);
        b10[off++] = (byte)(enc >> 8);
        b10[off++] = (byte)enc;
        return off;
    }

    private static int _outputUptoMillion(char[] b10, int off, int thousands, int ones) {
        int enc = TRIPLET_TO_CHARS[thousands];
        if (thousands > 9) {
            if (thousands > 99) {
                b10[off++] = (char)(enc >> 16);
            }
            b10[off++] = (char)(enc >> 8 & 0x7F);
        }
        b10[off++] = (char)(enc & 0x7F);
        enc = TRIPLET_TO_CHARS[ones];
        b10[off++] = (char)(enc >> 16);
        b10[off++] = (char)(enc >> 8 & 0x7F);
        b10[off++] = (char)(enc & 0x7F);
        return off;
    }

    private static int _outputUptoMillion(byte[] b10, int off, int thousands, int ones) {
        int enc = TRIPLET_TO_CHARS[thousands];
        if (thousands > 9) {
            if (thousands > 99) {
                b10[off++] = (byte)(enc >> 16);
            }
            b10[off++] = (byte)(enc >> 8);
        }
        b10[off++] = (byte)enc;
        enc = TRIPLET_TO_CHARS[ones];
        b10[off++] = (byte)(enc >> 16);
        b10[off++] = (byte)(enc >> 8);
        b10[off++] = (byte)enc;
        return off;
    }

    private static int _leading3(int t2, char[] b10, int off) {
        int enc = TRIPLET_TO_CHARS[t2];
        if (t2 > 9) {
            if (t2 > 99) {
                b10[off++] = (char)(enc >> 16);
            }
            b10[off++] = (char)(enc >> 8 & 0x7F);
        }
        b10[off++] = (char)(enc & 0x7F);
        return off;
    }

    private static int _leading3(int t2, byte[] b10, int off) {
        int enc = TRIPLET_TO_CHARS[t2];
        if (t2 > 9) {
            if (t2 > 99) {
                b10[off++] = (byte)(enc >> 16);
            }
            b10[off++] = (byte)(enc >> 8);
        }
        b10[off++] = (byte)enc;
        return off;
    }

    private static int _full3(int t2, char[] b10, int off) {
        int enc = TRIPLET_TO_CHARS[t2];
        b10[off++] = (char)(enc >> 16);
        b10[off++] = (char)(enc >> 8 & 0x7F);
        b10[off++] = (char)(enc & 0x7F);
        return off;
    }

    private static int _full3(int t2, byte[] b10, int off) {
        int enc = TRIPLET_TO_CHARS[t2];
        b10[off++] = (byte)(enc >> 16);
        b10[off++] = (byte)(enc >> 8);
        b10[off++] = (byte)enc;
        return off;
    }

    private static int _outputSmallestL(char[] b10, int off) {
        int len = SMALLEST_LONG.length();
        SMALLEST_LONG.getChars(0, len, b10, off);
        return off + len;
    }

    private static int _outputSmallestL(byte[] b10, int off) {
        int len = SMALLEST_LONG.length();
        for (int i2 = 0; i2 < len; ++i2) {
            b10[off++] = (byte)SMALLEST_LONG.charAt(i2);
        }
        return off;
    }

    private static int _outputSmallestI(char[] b10, int off) {
        int len = SMALLEST_INT.length();
        SMALLEST_INT.getChars(0, len, b10, off);
        return off + len;
    }

    private static int _outputSmallestI(byte[] b10, int off) {
        int len = SMALLEST_INT.length();
        for (int i2 = 0; i2 < len; ++i2) {
            b10[off++] = (byte)SMALLEST_INT.charAt(i2);
        }
        return off;
    }

    static {
        int fullIx = 0;
        for (int i1 = 0; i1 < 10; ++i1) {
            for (int i2 = 0; i2 < 10; ++i2) {
                for (int i3 = 0; i3 < 10; ++i3) {
                    int enc = i1 + 48 << 16 | i2 + 48 << 8 | i3 + 48;
                    NumberOutput.TRIPLET_TO_CHARS[fullIx++] = enc;
                }
            }
        }
        sSmallIntStrs = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        sSmallIntStrs2 = new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    }
}

