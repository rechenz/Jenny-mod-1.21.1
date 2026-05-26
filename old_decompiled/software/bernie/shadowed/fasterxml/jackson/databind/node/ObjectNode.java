/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonPointer;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ArrayNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.BaseJsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ContainerNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeFactory;
import software.bernie.shadowed.fasterxml.jackson.databind.node.JsonNodeType;
import software.bernie.shadowed.fasterxml.jackson.databind.node.MissingNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.TextNode;
import software.bernie.shadowed.fasterxml.jackson.databind.util.RawValue;

public class ObjectNode
extends ContainerNode<ObjectNode> {
    protected final Map<String, JsonNode> _children;

    public ObjectNode(JsonNodeFactory nc) {
        super(nc);
        this._children = new LinkedHashMap<String, JsonNode>();
    }

    public ObjectNode(JsonNodeFactory nc, Map<String, JsonNode> kids) {
        super(nc);
        this._children = kids;
    }

    @Override
    protected JsonNode _at(JsonPointer ptr) {
        return this.get(ptr.getMatchingProperty());
    }

    public ObjectNode deepCopy() {
        ObjectNode ret = new ObjectNode(this._nodeFactory);
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            ret._children.put(entry.getKey(), (JsonNode)entry.getValue().deepCopy());
        }
        return ret;
    }

    @Override
    public boolean isEmpty(SerializerProvider serializers) {
        return this._children.isEmpty();
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.OBJECT;
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    @Override
    public int size() {
        return this._children.size();
    }

    @Override
    public Iterator<JsonNode> elements() {
        return this._children.values().iterator();
    }

    @Override
    public JsonNode get(int index) {
        return null;
    }

    @Override
    public JsonNode get(String fieldName) {
        return this._children.get(fieldName);
    }

    @Override
    public Iterator<String> fieldNames() {
        return this._children.keySet().iterator();
    }

    @Override
    public JsonNode path(int index) {
        return MissingNode.getInstance();
    }

    @Override
    public JsonNode path(String fieldName) {
        JsonNode n2 = this._children.get(fieldName);
        if (n2 != null) {
            return n2;
        }
        return MissingNode.getInstance();
    }

    @Override
    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return this._children.entrySet().iterator();
    }

    @Override
    public ObjectNode with(String propertyName) {
        JsonNode n2 = this._children.get(propertyName);
        if (n2 != null) {
            if (n2 instanceof ObjectNode) {
                return (ObjectNode)n2;
            }
            throw new UnsupportedOperationException("Property '" + propertyName + "' has value that is not of type ObjectNode (but " + n2.getClass().getName() + ")");
        }
        ObjectNode result = this.objectNode();
        this._children.put(propertyName, result);
        return result;
    }

    @Override
    public ArrayNode withArray(String propertyName) {
        JsonNode n2 = this._children.get(propertyName);
        if (n2 != null) {
            if (n2 instanceof ArrayNode) {
                return (ArrayNode)n2;
            }
            throw new UnsupportedOperationException("Property '" + propertyName + "' has value that is not of type ArrayNode (but " + n2.getClass().getName() + ")");
        }
        ArrayNode result = this.arrayNode();
        this._children.put(propertyName, result);
        return result;
    }

    @Override
    public boolean equals(Comparator<JsonNode> comparator, JsonNode o2) {
        if (!(o2 instanceof ObjectNode)) {
            return false;
        }
        ObjectNode other = (ObjectNode)o2;
        Map<String, JsonNode> m1 = this._children;
        Map<String, JsonNode> m2 = other._children;
        int len = m1.size();
        if (m2.size() != len) {
            return false;
        }
        for (Map.Entry<String, JsonNode> entry : m1.entrySet()) {
            JsonNode v2 = m2.get(entry.getKey());
            if (v2 != null && entry.getValue().equals(comparator, v2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public JsonNode findValue(String fieldName) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (fieldName.equals(entry.getKey())) {
                return entry.getValue();
            }
            JsonNode value = entry.getValue().findValue(fieldName);
            if (value == null) continue;
            return value;
        }
        return null;
    }

    @Override
    public List<JsonNode> findValues(String fieldName, List<JsonNode> foundSoFar) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (fieldName.equals(entry.getKey())) {
                if (foundSoFar == null) {
                    foundSoFar = new ArrayList<JsonNode>();
                }
                foundSoFar.add(entry.getValue());
                continue;
            }
            foundSoFar = entry.getValue().findValues(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    @Override
    public List<String> findValuesAsText(String fieldName, List<String> foundSoFar) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (fieldName.equals(entry.getKey())) {
                if (foundSoFar == null) {
                    foundSoFar = new ArrayList<String>();
                }
                foundSoFar.add(entry.getValue().asText());
                continue;
            }
            foundSoFar = entry.getValue().findValuesAsText(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    @Override
    public ObjectNode findParent(String fieldName) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (fieldName.equals(entry.getKey())) {
                return this;
            }
            JsonNode value = entry.getValue().findParent(fieldName);
            if (value == null) continue;
            return (ObjectNode)value;
        }
        return null;
    }

    @Override
    public List<JsonNode> findParents(String fieldName, List<JsonNode> foundSoFar) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (fieldName.equals(entry.getKey())) {
                if (foundSoFar == null) {
                    foundSoFar = new ArrayList<JsonNode>();
                }
                foundSoFar.add(this);
                continue;
            }
            foundSoFar = entry.getValue().findParents(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    @Override
    public void serialize(JsonGenerator g10, SerializerProvider provider) throws IOException {
        boolean trimEmptyArray = provider != null && !provider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        g10.writeStartObject(this);
        for (Map.Entry<String, JsonNode> en2 : this._children.entrySet()) {
            BaseJsonNode value = (BaseJsonNode)en2.getValue();
            if (trimEmptyArray && value.isArray() && value.isEmpty(provider)) continue;
            g10.writeFieldName(en2.getKey());
            value.serialize(g10, provider);
        }
        g10.writeEndObject();
    }

    @Override
    public void serializeWithType(JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        boolean trimEmptyArray = provider != null && !provider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId(this, JsonToken.START_OBJECT));
        for (Map.Entry<String, JsonNode> en2 : this._children.entrySet()) {
            BaseJsonNode value = (BaseJsonNode)en2.getValue();
            if (trimEmptyArray && value.isArray() && value.isEmpty(provider)) continue;
            g10.writeFieldName(en2.getKey());
            value.serialize(g10, provider);
        }
        typeSer.writeTypeSuffix(g10, typeIdDef);
    }

    public JsonNode set(String fieldName, JsonNode value) {
        if (value == null) {
            value = this.nullNode();
        }
        this._children.put(fieldName, value);
        return this;
    }

    public JsonNode setAll(Map<String, ? extends JsonNode> properties) {
        for (Map.Entry<String, ? extends JsonNode> en2 : properties.entrySet()) {
            JsonNode n2 = en2.getValue();
            if (n2 == null) {
                n2 = this.nullNode();
            }
            this._children.put(en2.getKey(), n2);
        }
        return this;
    }

    public JsonNode setAll(ObjectNode other) {
        this._children.putAll(other._children);
        return this;
    }

    public JsonNode replace(String fieldName, JsonNode value) {
        if (value == null) {
            value = this.nullNode();
        }
        return this._children.put(fieldName, value);
    }

    public JsonNode without(String fieldName) {
        this._children.remove(fieldName);
        return this;
    }

    public ObjectNode without(Collection<String> fieldNames) {
        this._children.keySet().removeAll(fieldNames);
        return this;
    }

    @Deprecated
    public JsonNode put(String fieldName, JsonNode value) {
        if (value == null) {
            value = this.nullNode();
        }
        return this._children.put(fieldName, value);
    }

    public JsonNode remove(String fieldName) {
        return this._children.remove(fieldName);
    }

    public ObjectNode remove(Collection<String> fieldNames) {
        this._children.keySet().removeAll(fieldNames);
        return this;
    }

    @Override
    public ObjectNode removeAll() {
        this._children.clear();
        return this;
    }

    @Deprecated
    public JsonNode putAll(Map<String, ? extends JsonNode> properties) {
        return this.setAll(properties);
    }

    @Deprecated
    public JsonNode putAll(ObjectNode other) {
        return this.setAll(other);
    }

    public ObjectNode retain(Collection<String> fieldNames) {
        this._children.keySet().retainAll(fieldNames);
        return this;
    }

    public ObjectNode retain(String ... fieldNames) {
        return this.retain(Arrays.asList(fieldNames));
    }

    public ArrayNode putArray(String fieldName) {
        ArrayNode n2 = this.arrayNode();
        this._put(fieldName, n2);
        return n2;
    }

    public ObjectNode putObject(String fieldName) {
        ObjectNode n2 = this.objectNode();
        this._put(fieldName, n2);
        return n2;
    }

    public ObjectNode putPOJO(String fieldName, Object pojo) {
        return this._put(fieldName, this.pojoNode(pojo));
    }

    public ObjectNode putRawValue(String fieldName, RawValue raw) {
        return this._put(fieldName, this.rawValueNode(raw));
    }

    public ObjectNode putNull(String fieldName) {
        this._children.put(fieldName, this.nullNode());
        return this;
    }

    public ObjectNode put(String fieldName, short v2) {
        return this._put(fieldName, this.numberNode(v2));
    }

    public ObjectNode put(String fieldName, Short v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.numberNode((short)v2));
    }

    public ObjectNode put(String fieldName, int v2) {
        return this._put(fieldName, this.numberNode(v2));
    }

    public ObjectNode put(String fieldName, Integer v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.numberNode((int)v2));
    }

    public ObjectNode put(String fieldName, long v2) {
        return this._put(fieldName, this.numberNode(v2));
    }

    public ObjectNode put(String fieldName, Long v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.numberNode((long)v2));
    }

    public ObjectNode put(String fieldName, float v2) {
        return this._put(fieldName, this.numberNode(v2));
    }

    public ObjectNode put(String fieldName, Float v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.numberNode(v2.floatValue()));
    }

    public ObjectNode put(String fieldName, double v2) {
        return this._put(fieldName, this.numberNode(v2));
    }

    public ObjectNode put(String fieldName, Double v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.numberNode((double)v2));
    }

    public ObjectNode put(String fieldName, BigDecimal v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.numberNode(v2));
    }

    public ObjectNode put(String fieldName, BigInteger v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.numberNode(v2));
    }

    public ObjectNode put(String fieldName, String v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.textNode(v2));
    }

    public ObjectNode put(String fieldName, boolean v2) {
        return this._put(fieldName, this.booleanNode(v2));
    }

    public ObjectNode put(String fieldName, Boolean v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.booleanNode(v2));
    }

    public ObjectNode put(String fieldName, byte[] v2) {
        return this._put(fieldName, v2 == null ? this.nullNode() : this.binaryNode(v2));
    }

    @Override
    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (o2 instanceof ObjectNode) {
            return this._childrenEqual((ObjectNode)o2);
        }
        return false;
    }

    protected boolean _childrenEqual(ObjectNode other) {
        return this._children.equals(other._children);
    }

    @Override
    public int hashCode() {
        return this._children.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(32 + (this.size() << 4));
        sb.append("{");
        int count = 0;
        for (Map.Entry<String, JsonNode> en2 : this._children.entrySet()) {
            if (count > 0) {
                sb.append(",");
            }
            ++count;
            TextNode.appendQuoted(sb, en2.getKey());
            sb.append(':');
            sb.append(en2.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }

    protected ObjectNode _put(String fieldName, JsonNode value) {
        this._children.put(fieldName, value);
        return this;
    }
}

