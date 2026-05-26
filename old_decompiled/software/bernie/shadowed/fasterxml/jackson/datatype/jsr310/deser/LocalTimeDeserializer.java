/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonDeserializer;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.JSR310DateTimeDeserializerBase;

public class LocalTimeDeserializer
extends JSR310DateTimeDeserializerBase<LocalTime> {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
    public static final LocalTimeDeserializer INSTANCE = new LocalTimeDeserializer();

    private LocalTimeDeserializer() {
        this(DEFAULT_FORMATTER);
    }

    public LocalTimeDeserializer(DateTimeFormatter formatter) {
        super(LocalTime.class, formatter);
    }

    @Override
    protected JsonDeserializer<LocalTime> withDateFormat(DateTimeFormatter formatter) {
        return new LocalTimeDeserializer(formatter);
    }

    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (parser.hasToken(JsonToken.VALUE_STRING)) {
            String string = parser.getText().trim();
            if (string.length() == 0) {
                return null;
            }
            DateTimeFormatter format = this._formatter;
            try {
                if (format == DEFAULT_FORMATTER && string.contains("T")) {
                    return LocalTime.parse(string, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                return LocalTime.parse(string, format);
            }
            catch (DateTimeException e10) {
                this._rethrowDateTimeException(parser, context, e10, string);
            }
        }
        if (parser.isExpectedStartArrayToken()) {
            JsonToken t2 = parser.nextToken();
            if (t2 == JsonToken.END_ARRAY) {
                return null;
            }
            if (context.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS) && (t2 == JsonToken.VALUE_STRING || t2 == JsonToken.VALUE_EMBEDDED_OBJECT)) {
                LocalTime parsed = this.deserialize(parser, context);
                if (parser.nextToken() != JsonToken.END_ARRAY) {
                    this.handleMissingEndArrayForSingle(parser, context);
                }
                return parsed;
            }
            if (t2 == JsonToken.VALUE_NUMBER_INT) {
                LocalTime result;
                int hour = parser.getIntValue();
                parser.nextToken();
                int minute = parser.getIntValue();
                t2 = parser.nextToken();
                if (t2 == JsonToken.END_ARRAY) {
                    result = LocalTime.of(hour, minute);
                } else {
                    int second = parser.getIntValue();
                    t2 = parser.nextToken();
                    if (t2 == JsonToken.END_ARRAY) {
                        result = LocalTime.of(hour, minute, second);
                    } else {
                        int partialSecond = parser.getIntValue();
                        if (partialSecond < 1000 && !context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)) {
                            partialSecond *= 1000000;
                        }
                        if ((t2 = parser.nextToken()) != JsonToken.END_ARRAY) {
                            throw context.wrongTokenException(parser, this.handledType(), JsonToken.END_ARRAY, "Expected array to end");
                        }
                        result = LocalTime.of(hour, minute, second, partialSecond);
                    }
                }
                return result;
            }
            context.reportInputMismatch(this.handledType(), "Unexpected token (%s) within Array, expected VALUE_NUMBER_INT", new Object[]{t2});
        }
        if (parser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            return (LocalTime)parser.getEmbeddedObject();
        }
        throw context.wrongTokenException(parser, this.handledType(), JsonToken.START_ARRAY, "Expected array or string.");
    }
}

