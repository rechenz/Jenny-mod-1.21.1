/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ArrayNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.BaseJsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.BinaryNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.BooleanNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeCreator;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NullNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NumericNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.TextNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ValueNode;
import software.bernie.shadowed.fasterxml.jackson.databind.util.RawValue;

public abstract class ContainerNode<T extends ContainerNode<T>>
extends BaseJsonNode
implements JsonNodeCreator {
    protected final JsonNodeFactory _nodeFactory;

    protected ContainerNode(JsonNodeFactory nc) {
        this._nodeFactory = nc;
    }

    @Override
    public abstract JsonToken asToken();

    @Override
    public String asText() {
        return "";
    }

    @Override
    public abstract int size();

    @Override
    public abstract JsonNode get(int var1);

    @Override
    public abstract JsonNode get(String var1);

    @Override
    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    @Override
    public final ArrayNode arrayNode(int capacity) {
        return this._nodeFactory.arrayNode(capacity);
    }

    @Override
    public final ObjectNode objectNode() {
        return this._nodeFactory.objectNode();
    }

    @Override
    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }

    @Override
    public final BooleanNode booleanNode(boolean v2) {
        return this._nodeFactory.booleanNode(v2);
    }

    @Override
    public final NumericNode numberNode(byte v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(short v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(int v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(long v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(float v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(double v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(BigInteger v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(BigDecimal v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Byte v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Short v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Integer v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Long v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Float v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Double v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final TextNode textNode(String text) {
        return this._nodeFactory.textNode(text);
    }

    @Override
    public final BinaryNode binaryNode(byte[] data) {
        return this._nodeFactory.binaryNode(data);
    }

    @Override
    public final BinaryNode binaryNode(byte[] data, int offset, int length) {
        return this._nodeFactory.binaryNode(data, offset, length);
    }

    @Override
    public final ValueNode pojoNode(Object pojo) {
        return this._nodeFactory.pojoNode(pojo);
    }

    @Override
    public final ValueNode rawValueNode(RawValue value) {
        return this._nodeFactory.rawValueNode(value);
    }

    public abstract T removeAll();
}

