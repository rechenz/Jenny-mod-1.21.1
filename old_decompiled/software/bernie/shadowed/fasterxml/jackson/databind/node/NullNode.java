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

public final class NullNode
extends ValueNode {
    public static final NullNode instance = new NullNode();

    private NullNode() {
    }

    public static NullNode getInstance() {
        return instance;
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.NULL;
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.VALUE_NULL;
    }

    @Override
    public String asText(String defaultValue) {
        return defaultValue;
    }

    @Override
    public String asText() {
        return "null";
    }

    @Override
    public final void serialize(JsonGenerator g10, SerializerProvider provider) throws IOException {
        provider.defaultSerializeNull(g10);
    }

    @Override
    public boolean equals(Object o2) {
        return o2 == this;
    }

    @Override
    public int hashCode() {
        return JsonNodeType.NULL.ordinal();
    }
}

