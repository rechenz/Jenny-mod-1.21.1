/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class CharSequenceUtils {
    private static final int NOT_FOUND = -1;
    static final int TO_STRING_LIMIT = 16;

    public static CharSequence subSequence(CharSequence cs2, int start) {
        return cs2 == null ? null : cs2.subSequence(start, cs2.length());
    }

    static int indexOf(CharSequence cs2, int searchChar, int start) {
        if (cs2 instanceof String) {
            return ((String)cs2).indexOf(searchChar, start);
        }
        int sz = cs2.length();
        if (start < 0) {
            start = 0;
        }
        if (searchChar < 65536) {
            for (int i2 = start; i2 < sz; ++i2) {
                if (cs2.charAt(i2) != searchChar) continue;
                return i2;
            }
        }
        if (searchChar <= 0x10FFFF) {
            char[] chars = Character.toChars(searchChar);
            for (int i3 = start; i3 < sz - 1; ++i3) {
                char high = cs2.charAt(i3);
                char low = cs2.charAt(i3 + 1);
                if (high != chars[0] || low != chars[1]) continue;
                return i3;
            }
        }
        return -1;
    }

    static int indexOf(CharSequence cs2, CharSequence searchChar, int start) {
        if (cs2 instanceof String) {
            return ((String)cs2).indexOf(searchChar.toString(), start);
        }
        if (cs2 instanceof StringBuilder) {
            return ((StringBuilder)cs2).indexOf(searchChar.toString(), start);
        }
        if (cs2 instanceof StringBuffer) {
            return ((StringBuffer)cs2).indexOf(searchChar.toString(), start);
        }
        return cs2.toString().indexOf(searchChar.toString(), start);
    }

    static int lastIndexOf(CharSequence cs2, int searchChar, int start) {
        if (cs2 instanceof String) {
            return ((String)cs2).lastIndexOf(searchChar, start);
        }
        int sz = cs2.length();
        if (start < 0) {
            return -1;
        }
        if (start >= sz) {
            start = sz - 1;
        }
        if (searchChar < 65536) {
            for (int i2 = start; i2 >= 0; --i2) {
                if (cs2.charAt(i2) != searchChar) continue;
                return i2;
            }
        }
        if (searchChar <= 0x10FFFF) {
            char[] chars = Character.toChars(searchChar);
            if (start == sz - 1) {
                return -1;
            }
            for (int i3 = start; i3 >= 0; --i3) {
                char high = cs2.charAt(i3);
                char low = cs2.charAt(i3 + 1);
                if (chars[0] != high || chars[1] != low) continue;
                return i3;
            }
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    static int lastIndexOf(CharSequence cs, CharSequence searchChar, int start) {
        if (searchChar instanceof String) {
            if (cs instanceof String) {
                return ((String)cs).lastIndexOf((String)searchChar, start);
            }
            if (cs instanceof StringBuilder) {
                return ((StringBuilder)cs).lastIndexOf((String)searchChar, start);
            }
            if (cs instanceof StringBuffer) {
                return ((StringBuffer)cs).lastIndexOf((String)searchChar, start);
            }
        }
        len1 = cs.length();
        len2 = searchChar.length();
        if (start > len1) {
            start = len1;
        }
        if (start < 0 || len2 < 0 || len2 > len1) {
            return -1;
        }
        if (len2 == 0) {
            return start;
        }
        if (len2 <= 16) {
            if (cs instanceof String) {
                return ((String)cs).lastIndexOf(searchChar.toString(), start);
            }
            if (cs instanceof StringBuilder) {
                return ((StringBuilder)cs).lastIndexOf(searchChar.toString(), start);
            }
            if (cs instanceof StringBuffer) {
                return ((StringBuffer)cs).lastIndexOf(searchChar.toString(), start);
            }
        }
        if (start + len2 > len1) {
            start = len1 - len2;
        }
        char0 = searchChar.charAt(0);
        i = start;
        do lbl-1000:
        // 3 sources

        {
            block13: {
                if (cs.charAt(i) == char0) break block13;
                if (--i >= 0) ** GOTO lbl-1000
                return -1;
            }
            if (!CharSequenceUtils.checkLaterThan1(cs, searchChar, len2, i)) continue;
            return i;
        } while (--i >= 0);
        return -1;
    }

    private static boolean checkLaterThan1(CharSequence cs2, CharSequence searchChar, int len2, int start1) {
        int i2 = 1;
        for (int j2 = len2 - 1; i2 <= j2; ++i2, --j2) {
            if (cs2.charAt(start1 + i2) == searchChar.charAt(i2) && cs2.charAt(start1 + j2) == searchChar.charAt(j2)) continue;
            return false;
        }
        return true;
    }

    public static char[] toCharArray(CharSequence source) {
        int len = StringUtils.length(source);
        if (len == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        if (source instanceof String) {
            return ((String)source).toCharArray();
        }
        char[] array = new char[len];
        for (int i2 = 0; i2 < len; ++i2) {
            array[i2] = source.charAt(i2);
        }
        return array;
    }

    static boolean regionMatches(CharSequence cs2, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {
        if (cs2 instanceof String && substring instanceof String) {
            return ((String)cs2).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
        }
        int index1 = thisStart;
        int index2 = start;
        int tmpLen = length;
        int srcLen = cs2.length() - thisStart;
        int otherLen = substring.length() - start;
        if (thisStart < 0 || start < 0 || length < 0) {
            return false;
        }
        if (srcLen < length || otherLen < length) {
            return false;
        }
        while (tmpLen-- > 0) {
            char u2;
            char c22;
            char c12;
            if ((c12 = cs2.charAt(index1++)) == (c22 = substring.charAt(index2++))) continue;
            if (!ignoreCase) {
                return false;
            }
            char u1 = Character.toUpperCase(c12);
            if (u1 == (u2 = Character.toUpperCase(c22)) || Character.toLowerCase(u1) == Character.toLowerCase(u2)) continue;
            return false;
        }
        return true;
    }
}

