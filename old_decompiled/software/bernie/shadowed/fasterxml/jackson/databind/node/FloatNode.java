/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.io.NumberOutput;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NumericNode;

public class FloatNode
extends NumericNode {
    protected final float _value;

    public FloatNode(float v2) {
        this._value = v2;
    }

    public static FloatNode valueOf(float v2) {
        return new FloatNode(v2);
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    @Override
    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.FLOAT;
    }

    @Override
    public boolean isFloatingPointNumber() {
        return true;
    }

    @Override
    public boolean isFloat() {
        return true;
    }

    @Override
    public boolean canConvertToInt() {
        return this._value >= -2.1474836E9f && this._value <= 2.1474836E9f;
    }

    @Override
    public boolean canConvertToLong() {
        return this._value >= -9.223372E18f && this._value <= 9.223372E18f;
    }

    @Override
    public Number numberValue() {
        return Float.valueOf(this._value);
    }

    @Override
    public short shortValue() {
        return (short)this._value;
    }

    @Override
    public int intValue() {
        return (int)this._value;
    }

    @Override
    public long longValue() {
        return (long)this._value;
    }

    @Override
    public float floatValue() {
        return this._value;
    }

    @Override
    public double doubleValue() {
        return this._value;
    }

    @Override
    public BigDecimal decimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    @Override
    public BigInteger bigIntegerValue() {
        return this.decimalValue().toBigInteger();
    }

    @Override
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override
    public boolean isNaN() {
        return Float.isNaN(this._value) || Float.isInfinite(this._value);
    }

    @Override
    public final void serialize(JsonGenerator g10, SerializerProvider provider) throws IOException {
        g10.writeNumber(this._value);
    }

    @Override
    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (o2 instanceof FloatNode) {
            float otherValue = ((FloatNode)o2)._value;
            return Float.compare(this._value, otherValue) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Float.floatToIntBits(this._value);
    }
}

