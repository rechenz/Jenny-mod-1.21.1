/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeType;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ValueNode;

public abstract class NumericNode
extends ValueNode {
    protected NumericNode() {
    }

    @Override
    public final JsonNodeType getNodeType() {
        return JsonNodeType.NUMBER;
    }

    @Override
    public abstract JsonParser.NumberType numberType();

    @Override
    public abstract Number numberValue();

    @Override
    public abstract int intValue();

    @Override
    public abstract long longValue();

    @Override
    public abstract double doubleValue();

    @Override
    public abstract BigDecimal decimalValue();

    @Override
    public abstract BigInteger bigIntegerValue();

    @Override
    public abstract boolean canConvertToInt();

    @Override
    public abstract boolean canConvertToLong();

    @Override
    public abstract String asText();

    @Override
    public final int asInt() {
        return this.intValue();
    }

    @Override
    public final int asInt(int defaultValue) {
        return this.intValue();
    }

    @Override
    public final long asLong() {
        return this.longValue();
    }

    @Override
    public final long asLong(long defaultValue) {
        return this.longValue();
    }

    @Override
    public final double asDouble() {
        return this.doubleValue();
    }

    @Override
    public final double asDouble(double defaultValue) {
        return this.doubleValue();
    }

    public boolean isNaN() {
        return false;
    }
}

