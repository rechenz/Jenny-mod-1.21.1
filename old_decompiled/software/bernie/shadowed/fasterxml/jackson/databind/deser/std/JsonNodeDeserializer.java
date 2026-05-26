/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ArrayNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.NullNode;
import software.bernie.shadowed.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeDeserializer
extends BaseNodeDeserializer<JsonNode> {
    private static final JsonNodeDeserializer instance = new JsonNodeDeserializer();

    protected JsonNodeDeserializer() {
        super(JsonNode.class, null);
    }

    public static JsonDeserializer<? extends JsonNode> getDeserializer(Class<?> nodeClass) {
        if (nodeClass == ObjectNode.class) {
            return ObjectDeserializer.getInstance();
        }
        if (nodeClass == ArrayNode.class) {
            return ArrayDeserializer.getInstance();
        }
        return instance;
    }

    @Override
    public JsonNode getNullValue(DeserializationContext ctxt) {
        return NullNode.getInstance();
    }

    @Override
    public JsonNode deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        switch (p2.getCurrentTokenId()) {
            case 1: {
                return this.deserializeObject(p2, ctxt, ctxt.getNodeFactory());
            }
            case 3: {
                return this.deserializeArray(p2, ctxt, ctxt.getNodeFactory());
            }
        }
        return this.deserializeAny(p2, ctxt, ctxt.getNodeFactory());
    }

    static final class ArrayDeserializer
    extends BaseNodeDeserializer<ArrayNode> {
        private static final long serialVersionUID = 1L;
        protected static final ArrayDeserializer _instance = new ArrayDeserializer();

        protected ArrayDeserializer() {
            super(ArrayNode.class, true);
        }

        public static ArrayDeserializer getInstance() {
            return _instance;
        }

        @Override
        public ArrayNode deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (p2.isExpectedStartArrayToken()) {
                return this.deserializeArray(p2, ctxt, ctxt.getNodeFactory());
            }
            return (ArrayNode)ctxt.handleUnexpectedToken(ArrayNode.class, p2);
        }

        @Override
        public ArrayNode deserialize(JsonParser p2, DeserializationContext ctxt, ArrayNode node) throws IOException {
            if (p2.isExpectedStartArrayToken()) {
                return (ArrayNode)this.updateArray(p2, ctxt, node);
            }
            return (ArrayNode)ctxt.handleUnexpectedToken(ArrayNode.class, p2);
        }
    }

    static final class ObjectDeserializer
    extends BaseNodeDeserializer<ObjectNode> {
        private static final long serialVersionUID = 1L;
        protected static final ObjectDeserializer _instance = new ObjectDeserializer();

        protected ObjectDeserializer() {
            super(ObjectNode.class, true);
        }

        public static ObjectDeserializer getInstance() {
            return _instance;
        }

        @Override
        public ObjectNode deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (p2.isExpectedStartObjectToken()) {
                return this.deserializeObject(p2, ctxt, ctxt.getNodeFactory());
            }
            if (p2.hasToken(JsonToken.FIELD_NAME)) {
                return this.deserializeObjectAtName(p2, ctxt, ctxt.getNodeFactory());
            }
            if (p2.hasToken(JsonToken.END_OBJECT)) {
                return ctxt.getNodeFactory().objectNode();
            }
            return (ObjectNode)ctxt.handleUnexpectedToken(ObjectNode.class, p2);
        }

        @Override
        public ObjectNode deserialize(JsonParser p2, DeserializationContext ctxt, ObjectNode node) throws IOException {
            if (p2.isExpectedStartObjectToken() || p2.hasToken(JsonToken.FIELD_NAME)) {
                return (ObjectNode)this.updateObject(p2, ctxt, node);
            }
            return (ObjectNode)ctxt.handleUnexpectedToken(ObjectNode.class, p2);
        }
    }
}

