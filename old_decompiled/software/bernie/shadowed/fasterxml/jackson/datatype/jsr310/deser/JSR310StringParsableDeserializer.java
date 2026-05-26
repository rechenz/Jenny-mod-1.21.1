/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.JSR310DeserializerBase;

public class JSR310StringParsableDeserializer
extends JSR310DeserializerBase<Object> {
    private static final long serialVersionUID = 1L;
    protected static final int TYPE_PERIOD = 1;
    protected static final int TYPE_ZONE_ID = 2;
    protected static final int TYPE_ZONE_OFFSET = 3;
    public static final JsonDeserializer<Period> PERIOD = JSR310StringParsableDeserializer.createDeserializer(Period.class, 1);
    public static final JsonDeserializer<ZoneId> ZONE_ID = JSR310StringParsableDeserializer.createDeserializer(ZoneId.class, 2);
    public static final JsonDeserializer<ZoneOffset> ZONE_OFFSET = JSR310StringParsableDeserializer.createDeserializer(ZoneOffset.class, 3);
    protected final int _valueType;

    protected JSR310StringParsableDeserializer(Class<?> supportedType, int valueId) {
        super(supportedType);
        this._valueType = valueId;
    }

    protected static <T> JsonDeserializer<T> createDeserializer(Class<T> type, int typeId) {
        return new JSR310StringParsableDeserializer(type, typeId);
    }

    @Override
    public Object deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (parser.hasToken(JsonToken.VALUE_STRING)) {
            String string = parser.getText().trim();
            if (string.length() == 0) {
                return null;
            }
            try {
                switch (this._valueType) {
                    case 1: {
                        return Period.parse(string);
                    }
                    case 2: {
                        return ZoneId.of(string);
                    }
                    case 3: {
                        return ZoneOffset.of(string);
                    }
                }
            }
            catch (DateTimeException e10) {
                this._rethrowDateTimeException(parser, context, e10, string);
            }
        }
        if (parser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            return parser.getEmbeddedObject();
        }
        if (parser.hasToken(JsonToken.START_ARRAY)) {
            return this._deserializeFromArray(parser, context);
        }
        throw context.wrongTokenException(parser, this.handledType(), JsonToken.VALUE_STRING, null);
    }

    @Override
    public Object deserializeWithType(JsonParser parser, DeserializationContext context, TypeDeserializer deserializer) throws IOException {
        JsonToken t2 = parser.getCurrentToken();
        if (t2 != null && t2.isScalarValue()) {
            return this.deserialize(parser, context);
        }
        return deserializer.deserializeTypedFromAny(parser, context);
    }
}

