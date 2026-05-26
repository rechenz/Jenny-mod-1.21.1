/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.JSR310DateTimeDeserializerBase;

public class MonthDayDeserializer
extends JSR310DateTimeDeserializerBase<MonthDay> {
    private static final long serialVersionUID = 1L;
    public static final MonthDayDeserializer INSTANCE = new MonthDayDeserializer(null);

    public MonthDayDeserializer(DateTimeFormatter formatter) {
        super(MonthDay.class, formatter);
    }

    @Override
    protected JsonDeserializer<MonthDay> withDateFormat(DateTimeFormatter dtf) {
        return new MonthDayDeserializer(dtf);
    }

    @Override
    public MonthDay deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (parser.hasToken(JsonToken.VALUE_STRING)) {
            String string = parser.getValueAsString().trim();
            try {
                if (this._formatter == null) {
                    return MonthDay.parse(string);
                }
                return MonthDay.parse(string, this._formatter);
            }
            catch (DateTimeException e10) {
                this._rethrowDateTimeException(parser, context, e10, string);
            }
        }
        if (parser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            return (MonthDay)parser.getEmbeddedObject();
        }
        if (parser.hasToken(JsonToken.START_ARRAY)) {
            return (MonthDay)this._deserializeFromArray(parser, context);
        }
        return (MonthDay)this._reportWrongToken(parser, context, JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT);
    }
}

