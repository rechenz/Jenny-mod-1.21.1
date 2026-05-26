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
import software.bernie.shadowed.fasterxml.jackson.core.io.NumberOutput;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NumericNode;

public class IntNode
extends NumericNode {
    static final int MIN_CANONICAL = -1;
    static final int MAX_CANONICAL = 10;
    private static final IntNode[] CANONICALS;
    protected final int _value;

    public IntNode(int v2) {
        this._value = v2;
    }

    public static IntNode valueOf(int i2) {
        if (i2 > 10 || i2 < -1) {
            return new IntNode(i2);
        }
        return CANONICALS[i2 - -1];
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override
    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.INT;
    }

    @Override
    public boolean isIntegralNumber() {
        return true;
    }

    @Override
    public boolean isInt() {
        return true;
    }

    @Override
    public boolean canConvertToInt() {
        return true;
    }

    @Override
    public boolean canConvertToLong() {
        return true;
    }

    @Override
    public Number numberValue() {
        return this._value;
    }

    @Override
    public short shortValue() {
        return (short)this._value;
    }

    @Override
    public int intValue() {
        return this._value;
    }

    @Override
    public long longValue() {
        return this._value;
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
        return BigInteger.valueOf(this._value);
    }

    @Override
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return this._value != 0;
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
        if (o2 instanceof IntNode) {
            return ((IntNode)o2)._value == this._value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this._value;
    }

    static {
        int count = 12;
        CANONICALS = new IntNode[count];
        for (int i2 = 0; i2 < count; ++i2) {
            IntNode.CANONICALS[i2] = new IntNode(-1 + i2);
        }
    }
}

