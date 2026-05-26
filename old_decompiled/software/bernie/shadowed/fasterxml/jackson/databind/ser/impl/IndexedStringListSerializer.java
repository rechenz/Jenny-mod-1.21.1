/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;

@JacksonStdImpl
public final class IndexedStringListSerializer
extends StaticListSerializerBase<List<String>> {
    private static final long serialVersionUID = 1L;
    public static final IndexedStringListSerializer instance = new IndexedStringListSerializer();

    protected IndexedStringListSerializer() {
        super(List.class);
    }

    public IndexedStringListSerializer(IndexedStringListSerializer src, Boolean unwrapSingle) {
        super(src, unwrapSingle);
    }

    @Override
    public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
        return new IndexedStringListSerializer(this, unwrapSingle);
    }

    @Override
    protected JsonNode contentSchema() {
        return this.createSchemaNode("string", true);
    }

    @Override
    protected void acceptContentVisitor(JsonArrayFormatVisitor visitor) throws JsonMappingException {
        visitor.itemsFormat(JsonFormatTypes.STRING);
    }

    @Override
    public void serialize(List<String> value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        int len = value.size();
        if (len == 1 && (this._unwrapSingle == null && provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || this._unwrapSingle == Boolean.TRUE)) {
            this.serializeContents(value, g10, provider, 1);
            return;
        }
        g10.writeStartArray(len);
        this.serializeContents(value, g10, provider, len);
        g10.writeEndArray();
    }

    @Override
    public void serializeWithType(List<String> value, JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId(value, JsonToken.START_ARRAY));
        this.serializeContents(value, g10, provider, value.size());
        typeSer.writeTypeSuffix(g10, typeIdDef);
    }

    private final void serializeContents(List<String> value, JsonGenerator g10, SerializerProvider provider, int len) throws IOException {
        int i2;
        g10.setCurrentValue(value);
        try {
            for (i2 = 0; i2 < len; ++i2) {
                String str = value.get(i2);
                if (str == null) {
                    provider.defaultSerializeNull(g10);
                    continue;
                }
                g10.writeString(str);
            }
        }
        catch (Exception e10) {
            this.wrapAndThrow(provider, (Throwable)e10, value, i2);
        }
    }
}

