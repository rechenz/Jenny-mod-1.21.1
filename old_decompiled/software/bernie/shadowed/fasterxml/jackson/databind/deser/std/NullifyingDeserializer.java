/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationConfig;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public class NullifyingDeserializer
extends StdDeserializer<Object> {
    private static final long serialVersionUID = 1L;
    public static final NullifyingDeserializer instance = new NullifyingDeserializer();

    public NullifyingDeserializer() {
        super(Object.class);
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.FALSE;
    }

    @Override
    public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.FIELD_NAME)) {
            JsonToken t2;
            while ((t2 = p2.nextToken()) != null && t2 != JsonToken.END_OBJECT) {
                p2.skipChildren();
            }
        } else {
            p2.skipChildren();
        }
        return null;
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        switch (p2.getCurrentTokenId()) {
            case 1: 
            case 3: 
            case 5: {
                return typeDeserializer.deserializeTypedFromAny(p2, ctxt);
            }
        }
        return null;
    }
}

