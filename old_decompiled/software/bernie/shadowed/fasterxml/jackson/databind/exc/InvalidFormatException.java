/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.exc;

import software.bernie.shadowed.fasterxml.jackson.core.JsonLocation;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.MismatchedInputException;

public class InvalidFormatException
extends MismatchedInputException {
    private static final long serialVersionUID = 1L;
    protected final Object _value;

    @Deprecated
    public InvalidFormatException(String msg, Object value, Class<?> targetType) {
        super(null, msg);
        this._value = value;
        this._targetType = targetType;
    }

    @Deprecated
    public InvalidFormatException(String msg, JsonLocation loc, Object value, Class<?> targetType) {
        super(null, msg, loc);
        this._value = value;
        this._targetType = targetType;
    }

    public InvalidFormatException(JsonParser p2, String msg, Object value, Class<?> targetType) {
        super(p2, msg, targetType);
        this._value = value;
    }

    public static InvalidFormatException from(JsonParser p2, String msg, Object value, Class<?> targetType) {
        return new InvalidFormatException(p2, msg, value, targetType);
    }

    public Object getValue() {
        return this._value;
    }
}

