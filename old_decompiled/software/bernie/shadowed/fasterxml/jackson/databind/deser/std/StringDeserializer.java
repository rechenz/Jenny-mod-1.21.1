/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;

@JacksonStdImpl
public class StringDeserializer
extends StdScalarDeserializer<String> {
    private static final long serialVersionUID = 1L;
    public static final StringDeserializer instance = new StringDeserializer();

    public StringDeserializer() {
        super(String.class);
    }

    @Override
    public boolean isCachable() {
        return true;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return "";
    }

    @Override
    public String deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.VALUE_STRING)) {
            return p2.getText();
        }
        JsonToken t2 = p2.getCurrentToken();
        if (t2 == JsonToken.START_ARRAY) {
            return (String)this._deserializeFromArray(p2, ctxt);
        }
        if (t2 == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object ob = p2.getEmbeddedObject();
            if (ob == null) {
                return null;
            }
            if (ob instanceof byte[]) {
                return ctxt.getBase64Variant().encode((byte[])ob, false);
            }
            return ob.toString();
        }
        String text = p2.getValueAsString();
        if (text != null) {
            return text;
        }
        return (String)ctxt.handleUnexpectedToken(this._valueClass, p2);
    }

    @Override
    public String deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return this.deserialize(p2, ctxt);
    }
}

