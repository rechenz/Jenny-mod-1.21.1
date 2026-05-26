/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

@JacksonStdImpl
public class NumberSerializer
extends StdScalarSerializer<Number> {
    public static final NumberSerializer instance = new NumberSerializer((Class<? extends Number>)Number.class);
    protected final boolean _isInt;

    public NumberSerializer(Class<? extends Number> rawType) {
        super(rawType, false);
        this._isInt = rawType == BigInteger.class;
    }

    @Override
    public void serialize(Number value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        if (value instanceof BigDecimal) {
            g10.writeNumber((BigDecimal)value);
        } else if (value instanceof BigInteger) {
            g10.writeNumber((BigInteger)value);
        } else if (value instanceof Long) {
            g10.writeNumber(value.longValue());
        } else if (value instanceof Double) {
            g10.writeNumber(value.doubleValue());
        } else if (value instanceof Float) {
            g10.writeNumber(value.floatValue());
        } else if (value instanceof Integer || value instanceof Byte || value instanceof Short) {
            g10.writeNumber(value.intValue());
        } else {
            g10.writeNumber(value.toString());
        }
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return this.createSchemaNode(this._isInt ? "integer" : "number", true);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        if (this._isInt) {
            this.visitIntFormat(visitor, typeHint, JsonParser.NumberType.BIG_INTEGER);
        } else {
            Class h2 = this.handledType();
            if (h2 == BigDecimal.class) {
                this.visitFloatFormat(visitor, typeHint, JsonParser.NumberType.BIG_DECIMAL);
            } else {
                visitor.expectNumberFormat(typeHint);
            }
        }
    }
}

