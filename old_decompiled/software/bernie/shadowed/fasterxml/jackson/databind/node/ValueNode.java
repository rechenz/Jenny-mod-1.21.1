/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.io.IOException;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonPointer;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.node.BaseJsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.MissingNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;

public abstract class ValueNode
extends BaseJsonNode {
    protected ValueNode() {
    }

    @Override
    protected JsonNode _at(JsonPointer ptr) {
        return MissingNode.getInstance();
    }

    @Override
    public <T extends JsonNode> T deepCopy() {
        return (T)this;
    }

    @Override
    public abstract JsonToken asToken();

    @Override
    public void serializeWithType(JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId(this, this.asToken()));
        this.serialize(g10, provider);
        typeSer.writeTypeSuffix(g10, typeIdDef);
    }

    @Override
    public String toString() {
        return this.asText();
    }

    @Override
    public final JsonNode get(int index) {
        return null;
    }

    @Override
    public final JsonNode path(int index) {
        return MissingNode.getInstance();
    }

    @Override
    public final boolean has(int index) {
        return false;
    }

    @Override
    public final boolean hasNonNull(int index) {
        return false;
    }

    @Override
    public final JsonNode get(String fieldName) {
        return null;
    }

    @Override
    public final JsonNode path(String fieldName) {
        return MissingNode.getInstance();
    }

    @Override
    public final boolean has(String fieldName) {
        return false;
    }

    @Override
    public final boolean hasNonNull(String fieldName) {
        return false;
    }

    @Override
    public final JsonNode findValue(String fieldName) {
        return null;
    }

    @Override
    public final ObjectNode findParent(String fieldName) {
        return null;
    }

    @Override
    public final List<JsonNode> findValues(String fieldName, List<JsonNode> foundSoFar) {
        return foundSoFar;
    }

    @Override
    public final List<String> findValuesAsText(String fieldName, List<String> foundSoFar) {
        return foundSoFar;
    }

    @Override
    public final List<JsonNode> findParents(String fieldName, List<JsonNode> foundSoFar) {
        return foundSoFar;
    }
}

