/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NumericNode;

public class BigIntegerNode
extends NumericNode {
    private static final BigInteger MIN_INTEGER = BigInteger.valueOf(Integer.MIN_VALUE);
    private static final BigInteger MAX_INTEGER = BigInteger.valueOf(Integer.MAX_VALUE);
    private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    protected final BigInteger _value;

    public BigIntegerNode(BigInteger v2) {
        this._value = v2;
    }

    public static BigIntegerNode valueOf(BigInteger v2) {
        return new BigIntegerNode(v2);
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override
    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.BIG_INTEGER;
    }

    @Override
    public boolean isIntegralNumber() {
        return true;
    }

    @Override
    public boolean isBigInteger() {
        return true;
    }

    @Override
    public boolean canConvertToInt() {
        return this._value.compareTo(MIN_INTEGER) >= 0 && this._value.compareTo(MAX_INTEGER) <= 0;
    }

    @Override
    public boolean canConvertToLong() {
        return this._value.compareTo(MIN_LONG) >= 0 && this._value.compareTo(MAX_LONG) <= 0;
    }

    @Override
    public Number numberValue() {
        return this._value;
    }

    @Override
    public short shortValue() {
        return this._value.shortValue();
    }

    @Override
    public int intValue() {
        return this._value.intValue();
    }

    @Override
    public long longValue() {
        return this._value.longValue();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return this._value;
    }

    @Override
    public float floatValue() {
        return this._value.floatValue();
    }

    @Override
    public double doubleValue() {
        return this._value.doubleValue();
    }

    @Override
    public BigDecimal decimalValue() {
        return new BigDecimal(this._value);
    }

    @Override
    public String asText() {
        return this._value.toString();
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return !BigInteger.ZERO.equals(this._value);
    }

    @Override
    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeNumber(this._value);
    }

    @Override
    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (!(o2 instanceof BigIntegerNode)) {
            return false;
        }
        return ((BigIntegerNode)o2)._value.equals(this._value);
    }

    @Override
    public int hashCode() {
        return this._value.hashCode();
    }
}

