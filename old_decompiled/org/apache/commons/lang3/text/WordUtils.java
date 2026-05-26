/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.lang3.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

@Deprecated
public class WordUtils {
    public static String wrap(String str, int wrapLength) {
        return WordUtils.wrap(str, wrapLength, null, false);
    }

    public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords) {
        return WordUtils.wrap(str, wrapLength, newLineStr, wrapLongWords, " ");
    }

    public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords, String wrapOn) {
        if (str == null) {
            return null;
        }
        if (newLineStr == null) {
            newLineStr = System.lineSeparator();
        }
        if (wrapLength < 1) {
            wrapLength = 1;
        }
        if (StringUtils.isBlank(wrapOn)) {
            wrapOn = " ";
        }
        Pattern patternToWrapOn = Pattern.compile(wrapOn);
        int inputLineLength = str.length();
        int offset = 0;
        StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
        while (offset < inputLineLength) {
            int spaceToWrapAt = -1;
            Matcher matcher = patternToWrapOn.matcher(str.substring(offset, Math.min((int)Math.min(Integer.MAX_VALUE, (long)(offset + wrapLength) + 1L), inputLineLength)));
            if (matcher.find()) {
                if (matcher.start() == 0) {
                    offset += matcher.end();
                    continue;
                }
                spaceToWrapAt = matcher.start() + offset;
            }
            if (inputLineLength - offset <= wrapLength) break;
            while (matcher.find()) {
                spaceToWrapAt = matcher.start() + offset;
            }
            if (spaceToWrapAt >= offset) {
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;
                continue;
            }
            if (wrapLongWords) {
                wrappedLine.append(str, offset, wrapLength + offset);
                wrappedLine.append(newLineStr);
                offset += wrapLength;
                continue;
            }
            matcher = patternToWrapOn.matcher(str.substring(offset + wrapLength));
            if (matcher.find()) {
                spaceToWrapAt = matcher.start() + offset + wrapLength;
            }
            if (spaceToWrapAt >= 0) {
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;
                continue;
            }
            wrappedLine.append(str, offset, str.length());
            offset = inputLineLength;
        }
        wrappedLine.append(str, offset, str.length());
        return wrappedLine.toString();
    }

    public static String capitalize(String str) {
        return WordUtils.capitalize(str, null);
    }

    public static String capitalize(String str, char ... delimiters) {
        int delimLen;
        int n2 = delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        char[] buffer = str.toCharArray();
        boolean capitalizeNext = true;
        for (int i2 = 0; i2 < buffer.length; ++i2) {
            char ch2 = buffer[i2];
            if (WordUtils.isDelimiter(ch2, delimiters)) {
                capitalizeNext = true;
                continue;
            }
            if (!capitalizeNext) continue;
            buffer[i2] = Character.toTitleCase(ch2);
            capitalizeNext = false;
        }
        return new String(buffer);
    }

    public static String capitalizeFully(String str) {
        return WordUtils.capitalizeFully(str, null);
    }

    public static String capitalizeFully(String str, char ... delimiters) {
        int delimLen;
        int n2 = delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        str = str.toLowerCase();
        return WordUtils.capitalize(str, delimiters);
    }

    public static String uncapitalize(String str) {
        return WordUtils.uncapitalize(str, null);
    }

    public static String uncapitalize(String str, char ... delimiters) {
        int delimLen;
        int n2 = delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        char[] buffer = str.toCharArray();
        boolean uncapitalizeNext = true;
        for (int i2 = 0; i2 < buffer.length; ++i2) {
            char ch2 = buffer[i2];
            if (WordUtils.isDelimiter(ch2, delimiters)) {
                uncapitalizeNext = true;
                continue;
            }
            if (!uncapitalizeNext) continue;
            buffer[i2] = Character.toLowerCase(ch2);
            uncapitalizeNext = false;
        }
        return new String(buffer);
    }

    public static String swapCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        char[] buffer = str.toCharArray();
        boolean whitespace = true;
        for (int i2 = 0; i2 < buffer.length; ++i2) {
            char ch2 = buffer[i2];
            if (Character.isUpperCase(ch2) || Character.isTitleCase(ch2)) {
                buffer[i2] = Character.toLowerCase(ch2);
                whitespace = false;
                continue;
            }
            if (Character.isLowerCase(ch2)) {
                if (whitespace) {
                    buffer[i2] = Character.toTitleCase(ch2);
                    whitespace = false;
                    continue;
                }
                buffer[i2] = Character.toUpperCase(ch2);
                continue;
            }
            whitespace = Character.isWhitespace(ch2);
        }
        return new String(buffer);
    }

    public static String initials(String str) {
        return WordUtils.initials(str, null);
    }

    public static String initials(String str, char ... delimiters) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (delimiters != null && delimiters.length == 0) {
            return "";
        }
        int strLen = str.length();
        char[] buf = new char[strLen / 2 + 1];
        int count = 0;
        boolean lastWasGap = true;
        for (int i2 = 0; i2 < strLen; ++i2) {
            char ch2 = str.charAt(i2);
            if (WordUtils.isDelimiter(ch2, delimiters)) {
                lastWasGap = true;
                continue;
            }
            if (!lastWasGap) continue;
            buf[count++] = ch2;
            lastWasGap = false;
        }
        return new String(buf, 0, count);
    }

    public static boolean containsAllWords(CharSequence word, CharSequence ... words) {
        if (StringUtils.isEmpty(word) || ArrayUtils.isEmpty(words)) {
            return false;
        }
        for (CharSequence w2 : words) {
            if (StringUtils.isBlank(w2)) {
                return false;
            }
            Pattern p2 = Pattern.compile(".*\\b" + w2 + "\\b.*");
            if (p2.matcher(word).matches()) continue;
            return false;
        }
        return true;
    }

    private static boolean isDelimiter(char ch2, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch2);
        }
        for (char delimiter : delimiters) {
            if (ch2 != delimiter) continue;
            return true;
        }
        return false;
    }
}

