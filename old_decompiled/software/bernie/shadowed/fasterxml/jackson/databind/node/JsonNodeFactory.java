/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ArrayNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.BigIntegerNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.BinaryNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.BooleanNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.DecimalNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.DoubleNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.FloatNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.IntNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.node.LongNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NullNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NumericNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.POJONode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ShortNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.TextNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ValueNode;
import software.bernie.shadowed.fasterxml.jackson.databind.util.RawValue;

public class JsonNodeFactory
implements Serializable,
JsonNodeCreator {
    private static final long serialVersionUID = 1L;
    private final boolean _cfgBigDecimalExact;
    private static final JsonNodeFactory decimalsNormalized = new JsonNodeFactory(false);
    private static final JsonNodeFactory decimalsAsIs = new JsonNodeFactory(true);
    public static final JsonNodeFactory instance = decimalsNormalized;

    public JsonNodeFactory(boolean bigDecimalExact) {
        this._cfgBigDecimalExact = bigDecimalExact;
    }

    protected JsonNodeFactory() {
        this(false);
    }

    public static JsonNodeFactory withExactBigDecimals(boolean bigDecimalExact) {
        return bigDecimalExact ? decimalsAsIs : decimalsNormalized;
    }

    @Override
    public BooleanNode booleanNode(boolean v2) {
        return v2 ? BooleanNode.getTrue() : BooleanNode.getFalse();
    }

    @Override
    public NullNode nullNode() {
        return NullNode.getInstance();
    }

    @Override
    public NumericNode numberNode(byte v2) {
        return IntNode.valueOf(v2);
    }

    @Override
    public ValueNode numberNode(Byte value) {
        return value == null ? this.nullNode() : IntNode.valueOf(value.intValue());
    }

    @Override
    public NumericNode numberNode(short v2) {
        return ShortNode.valueOf(v2);
    }

    @Override
    public ValueNode numberNode(Short value) {
        return value == null ? this.nullNode() : ShortNode.valueOf(value);
    }

    @Override
    public NumericNode numberNode(int v2) {
        return IntNode.valueOf(v2);
    }

    @Override
    public ValueNode numberNode(Integer value) {
        return value == null ? this.nullNode() : IntNode.valueOf(value);
    }

    @Override
    public NumericNode numberNode(long v2) {
        return LongNode.valueOf(v2);
    }

    @Override
    public ValueNode numberNode(Long v2) {
        if (v2 == null) {
            return this.nullNode();
        }
        return LongNode.valueOf(v2);
    }

    @Override
    public ValueNode numberNode(BigInteger v2) {
        if (v2 == null) {
            return this.nullNode();
        }
        return BigIntegerNode.valueOf(v2);
    }

    @Override
    public NumericNode numberNode(float v2) {
        return FloatNode.valueOf(v2);
    }

    @Override
    public ValueNode numberNode(Float value) {
        return value == null ? this.nullNode() : FloatNode.valueOf(value.floatValue());
    }

    @Override
    public NumericNode numberNode(double v2) {
        return DoubleNode.valueOf(v2);
    }

    @Override
    public ValueNode numberNode(Double value) {
        return value == null ? this.nullNode() : DoubleNode.valueOf(value);
    }

    @Override
    public ValueNode numberNode(BigDecimal v2) {
        if (v2 == null) {
            return this.nullNode();
        }
        if (this._cfgBigDecimalExact) {
            return DecimalNode.valueOf(v2);
        }
        return v2.compareTo(BigDecimal.ZERO) == 0 ? DecimalNode.ZERO : DecimalNode.valueOf(v2.stripTrailingZeros());
    }

    @Override
    public TextNode textNode(String text) {
        return TextNode.valueOf(text);
    }

    @Override
    public BinaryNode binaryNode(byte[] data) {
        return BinaryNode.valueOf(data);
    }

    @Override
    public BinaryNode binaryNode(byte[] data, int offset, int length) {
        return BinaryNode.valueOf(data, offset, length);
    }

    @Override
    public ArrayNode arrayNode() {
        return new ArrayNode(this);
    }

    @Override
    public ArrayNode arrayNode(int capacity) {
        return new ArrayNode(this, capacity);
    }

    @Override
    public ObjectNode objectNode() {
        return new ObjectNode(this);
    }

    @Override
    public ValueNode pojoNode(Object pojo) {
        return new POJONode(pojo);
    }

    @Override
    public ValueNode rawValueNode(RawValue value) {
        return new POJONode(value);
    }

    protected boolean _inIntRange(long l2) {
        int i2 = (int)l2;
        long l22 = i2;
        return l22 == l2;
    }
}

