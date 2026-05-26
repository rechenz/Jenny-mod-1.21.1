/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.exc;

import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.MismatchedInputException;

public class InvalidTypeIdException
extends MismatchedInputException {
    private static final long serialVersionUID = 1L;
    protected final JavaType _baseType;
    protected final String _typeId;

    public InvalidTypeIdException(JsonParser p2, String msg, JavaType baseType, String typeId) {
        super(p2, msg);
        this._baseType = baseType;
        this._typeId = typeId;
    }

    public static InvalidTypeIdException from(JsonParser p2, String msg, JavaType baseType, String typeId) {
        return new InvalidTypeIdException(p2, msg, baseType, typeId);
    }

    public JavaType getBaseType() {
        return this._baseType;
    }

    public String getTypeId() {
        return this._typeId;
    }
}

