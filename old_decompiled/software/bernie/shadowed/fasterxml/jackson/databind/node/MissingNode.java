/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeType;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ValueNode;

public final class MissingNode
extends ValueNode {
    private static final MissingNode instance = new MissingNode();

    private MissingNode() {
    }

    @Override
    public <T extends JsonNode> T deepCopy() {
        return (T)this;
    }

    public static MissingNode getInstance() {
        return instance;
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.MISSING;
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.NOT_AVAILABLE;
    }

    @Override
    public String asText() {
        return "";
    }

    @Override
    public String asText(String defaultValue) {
        return defaultValue;
    }

    @Override
    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeNull();
    }

    @Override
    public void serializeWithType(JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException, JsonProcessingException {
        g10.writeNull();
    }

    @Override
    public boolean equals(Object o2) {
        return o2 == this;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return JsonNodeType.MISSING.ordinal();
    }
}

