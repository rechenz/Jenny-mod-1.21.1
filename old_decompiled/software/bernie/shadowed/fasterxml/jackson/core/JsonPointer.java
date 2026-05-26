/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core;

import software.bernie.shadowed.fasterxml.jackson.core.JsonStreamContext;
import software.bernie.shadowed.fasterxml.jackson.core.io.NumberInput;

public class JsonPointer {
    public static final char SEPARATOR = '/';
    protected static final JsonPointer EMPTY = new JsonPointer();
    protected final JsonPointer _nextSegment;
    protected volatile JsonPointer _head;
    protected final String _asString;
    protected final String _matchingPropertyName;
    protected final int _matchingElementIndex;

    protected JsonPointer() {
        this._nextSegment = null;
        this._matchingPropertyName = "";
        this._matchingElementIndex = -1;
        this._asString = "";
    }

    protected JsonPointer(String fullString, String segment, JsonPointer next) {
        this._asString = fullString;
        this._nextSegment = next;
        this._matchingPropertyName = segment;
        this._matchingElementIndex = JsonPointer._parseIndex(segment);
    }

    protected JsonPointer(String fullString, String segment, int matchIndex, JsonPointer next) {
        this._asString = fullString;
        this._nextSegment = next;
        this._matchingPropertyName = segment;
        this._matchingElementIndex = matchIndex;
    }

    public static JsonPointer compile(String input) throws IllegalArgumentException {
        if (input == null || input.length() == 0) {
            return EMPTY;
        }
        if (input.charAt(0) != '/') {
            throw new IllegalArgumentException("Invalid input: JSON Pointer expression must start with '/': \"" + input + "\"");
        }
        return JsonPointer._parseTail(input);
    }

    public static JsonPointer valueOf(String input) {
        return JsonPointer.compile(input);
    }

    public static JsonPointer forPath(JsonStreamContext context, boolean includeRoot) {
        if (context == null) {
            return EMPTY;
        }
        if (!(context.hasPathSegment() || includeRoot && context.inRoot() && context.hasCurrentIndex())) {
            context = context.getParent();
        }
        JsonPointer tail = null;
        while (context != null) {
            if (context.inObject()) {
                String seg = context.getCurrentName();
                if (seg == null) {
                    seg = "";
                }
                tail = new JsonPointer(JsonPointer._fullPath(tail, seg), seg, tail);
            } else if (context.inArray() || includeRoot) {
                int ix = context.getCurrentIndex();
                String ixStr = String.valueOf(ix);
                tail = new JsonPointer(JsonPointer._fullPath(tail, ixStr), ixStr, ix, tail);
            }
            context = context.getParent();
        }
        if (tail == null) {
            return EMPTY;
        }
        return tail;
    }

    private static String _fullPath(JsonPointer tail, String segment) {
        if (tail == null) {
            StringBuilder sb = new StringBuilder(segment.length() + 1);
            sb.append('/');
            JsonPointer._appendEscaped(sb, segment);
            return sb.toString();
        }
        String tailDesc = tail._asString;
        StringBuilder sb = new StringBuilder(segment.length() + 1 + tailDesc.length());
        sb.append('/');
        JsonPointer._appendEscaped(sb, segment);
        sb.append(tailDesc);
        return sb.toString();
    }

    private static void _appendEscaped(StringBuilder sb, String segment) {
        int end = segment.length();
        for (int i2 = 0; i2 < end; ++i2) {
            char c10 = segment.charAt(i2);
            if (c10 == '/') {
                sb.append("~1");
                continue;
            }
            if (c10 == '~') {
                sb.append("~0");
                continue;
            }
            sb.append(c10);
        }
    }

    public boolean matches() {
        return this._nextSegment == null;
    }

    public String getMatchingProperty() {
        return this._matchingPropertyName;
    }

    public int getMatchingIndex() {
        return this._matchingElementIndex;
    }

    public boolean mayMatchProperty() {
        return this._matchingPropertyName != null;
    }

    public boolean mayMatchElement() {
        return this._matchingElementIndex >= 0;
    }

    public JsonPointer last() {
        JsonPointer next;
        JsonPointer current = this;
        if (current == EMPTY) {
            return null;
        }
        while ((next = current._nextSegment) != EMPTY) {
            current = next;
        }
        return current;
    }

    public JsonPointer append(JsonPointer tail) {
        if (this == EMPTY) {
            return tail;
        }
        if (tail == EMPTY) {
            return this;
        }
        String currentJsonPointer = this._asString;
        if (currentJsonPointer.endsWith("/")) {
            currentJsonPointer = currentJsonPointer.substring(0, currentJsonPointer.length() - 1);
        }
        return JsonPointer.compile(currentJsonPointer + tail._asString);
    }

    public boolean matchesProperty(String name) {
        return this._nextSegment != null && this._matchingPropertyName.equals(name);
    }

    public JsonPointer matchProperty(String name) {
        if (this._nextSegment != null && this._matchingPropertyName.equals(name)) {
            return this._nextSegment;
        }
        return null;
    }

    public boolean matchesElement(int index) {
        return index == this._matchingElementIndex && index >= 0;
    }

    public JsonPointer matchElement(int index) {
        if (index != this._matchingElementIndex || index < 0) {
            return null;
        }
        return this._nextSegment;
    }

    public JsonPointer tail() {
        return this._nextSegment;
    }

    public JsonPointer head() {
        JsonPointer h2 = this._head;
        if (h2 == null) {
            if (this != EMPTY) {
                h2 = this._constructHead();
            }
            this._head = h2;
        }
        return h2;
    }

    public String toString() {
        return this._asString;
    }

    public int hashCode() {
        return this._asString.hashCode();
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (!(o2 instanceof JsonPointer)) {
            return false;
        }
        return this._asString.equals(((JsonPointer)o2)._asString);
    }

    private static final int _parseIndex(String str) {
        long l2;
        int len = str.length();
        if (len == 0 || len > 10) {
            return -1;
        }
        char c10 = str.charAt(0);
        if (c10 <= '0') {
            return len == 1 && c10 == '0' ? 0 : -1;
        }
        if (c10 > '9') {
            return -1;
        }
        for (int i2 = 1; i2 < len; ++i2) {
            c10 = str.charAt(i2);
            if (c10 <= '9' && c10 >= '0') continue;
            return -1;
        }
        if (len == 10 && (l2 = NumberInput.parseLong(str)) > Integer.MAX_VALUE) {
            return -1;
        }
        return NumberInput.parseInt(str);
    }

    protected static JsonPointer _parseTail(String input) {
        int end = input.length();
        int i2 = 1;
        while (i2 < end) {
            char c10 = input.charAt(i2);
            if (c10 == '/') {
                return new JsonPointer(input, input.substring(1, i2), JsonPointer._parseTail(input.substring(i2)));
            }
            if (c10 != '~' || ++i2 >= end) continue;
            return JsonPointer._parseQuotedTail(input, i2);
        }
        return new JsonPointer(input, input.substring(1), EMPTY);
    }

    protected static JsonPointer _parseQuotedTail(String input, int i2) {
        int end = input.length();
        StringBuilder sb = new StringBuilder(Math.max(16, end));
        if (i2 > 2) {
            sb.append(input, 1, i2 - 1);
        }
        JsonPointer._appendEscape(sb, input.charAt(i2++));
        while (i2 < end) {
            char c10 = input.charAt(i2);
            if (c10 == '/') {
                return new JsonPointer(input, sb.toString(), JsonPointer._parseTail(input.substring(i2)));
            }
            if (c10 == '~' && ++i2 < end) {
                JsonPointer._appendEscape(sb, input.charAt(i2++));
                continue;
            }
            sb.append(c10);
        }
        return new JsonPointer(input, sb.toString(), EMPTY);
    }

    protected JsonPointer _constructHead() {
        JsonPointer last = this.last();
        if (last == this) {
            return EMPTY;
        }
        int suffixLength = last._asString.length();
        JsonPointer next = this._nextSegment;
        return new JsonPointer(this._asString.substring(0, this._asString.length() - suffixLength), this._matchingPropertyName, this._matchingElementIndex, next._constructHead(suffixLength, last));
    }

    protected JsonPointer _constructHead(int suffixLength, JsonPointer last) {
        if (this == last) {
            return EMPTY;
        }
        JsonPointer next = this._nextSegment;
        String str = this._asString;
        return new JsonPointer(str.substring(0, str.length() - suffixLength), this._matchingPropertyName, this._matchingElementIndex, next._constructHead(suffixLength, last));
    }

    private static void _appendEscape(StringBuilder sb, char c10) {
        if (c10 == '0') {
            c10 = (char)126;
        } else if (c10 == '1') {
            c10 = (char)47;
        } else {
            sb.append('~');
        }
        sb.append(c10);
    }
}

