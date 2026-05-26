/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.JSR310DeserializerBase;

public class YearDeserializer
extends JSR310DeserializerBase<Year> {
    private static final long serialVersionUID = 1L;
    public static final YearDeserializer INSTANCE = new YearDeserializer();
    private final DateTimeFormatter _formatter;

    private YearDeserializer() {
        this((DateTimeFormatter)null);
    }

    public YearDeserializer(DateTimeFormatter formatter) {
        super(Year.class);
        this._formatter = formatter;
    }

    @Override
    public Year deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonToken t2 = parser.getCurrentToken();
        if (t2 == JsonToken.VALUE_STRING) {
            String string = parser.getValueAsString().trim();
            try {
                if (this._formatter == null) {
                    return Year.parse(string);
                }
                return Year.parse(string, this._formatter);
            }
            catch (DateTimeException e10) {
                this._rethrowDateTimeException(parser, context, e10, string);
            }
        }
        if (t2 == JsonToken.VALUE_NUMBER_INT) {
            return Year.of(parser.getIntValue());
        }
        if (t2 == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return (Year)parser.getEmbeddedObject();
        }
        if (parser.hasToken(JsonToken.START_ARRAY)) {
            return (Year)this._deserializeFromArray(parser, context);
        }
        return (Year)this._reportWrongToken(parser, context, JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT);
    }
}

