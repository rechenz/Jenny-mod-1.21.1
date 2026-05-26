/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeType;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ValueNode;

public class BooleanNode
extends ValueNode {
    public static final BooleanNode TRUE = new BooleanNode(true);
    public static final BooleanNode FALSE = new BooleanNode(false);
    private final boolean _value;

    private BooleanNode(boolean v2) {
        this._value = v2;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode valueOf(boolean b10) {
        return b10 ? TRUE : FALSE;
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.BOOLEAN;
    }

    @Override
    public JsonToken asToken() {
        return this._value ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    @Override
    public boolean booleanValue() {
        return this._value;
    }

    @Override
    public String asText() {
        return this._value ? "true" : "false";
    }

    @Override
    public boolean asBoolean() {
        return this._value;
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return this._value;
    }

    @Override
    public int asInt(int defaultValue) {
        return this._value ? 1 : 0;
    }

    @Override
    public long asLong(long defaultValue) {
        return this._value ? 1L : 0L;
    }

    @Override
    public double asDouble(double defaultValue) {
        return this._value ? 1.0 : 0.0;
    }

    @Override
    public final void serialize(JsonGenerator g10, SerializerProvider provider) throws IOException {
        g10.writeBoolean(this._value);
    }

    @Override
    public int hashCode() {
        return this._value ? 3 : 1;
    }

    @Override
    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (!(o2 instanceof BooleanNode)) {
            return false;
        }
        return this._value == ((BooleanNode)o2)._value;
    }
}

