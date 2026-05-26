/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Duration;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationFeature;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.JSR310DeserializerBase;

public class DurationDeserializer
extends JSR310DeserializerBase<Duration> {
    private static final long serialVersionUID = 1L;
    public static final DurationDeserializer INSTANCE = new DurationDeserializer();

    private DurationDeserializer() {
        super(Duration.class);
    }

    @Override
    public Duration deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        switch (parser.getCurrentTokenId()) {
            case 8: {
                BigDecimal value = parser.getDecimalValue();
                long seconds = value.longValue();
                int nanoseconds = DecimalUtils.extractNanosecondDecimal(value, seconds);
                return Duration.ofSeconds(seconds, nanoseconds);
            }
            case 7: {
                if (context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)) {
                    return Duration.ofSeconds(parser.getLongValue());
                }
                return Duration.ofMillis(parser.getLongValue());
            }
            case 6: {
                String string = parser.getText().trim();
                if (string.length() == 0) {
                    return null;
                }
                try {
                    return Duration.parse(string);
                }
                catch (DateTimeException e10) {
                    return (Duration)this._rethrowDateTimeException(parser, context, e10, string);
                }
            }
            case 12: {
                return (Duration)parser.getEmbeddedObject();
            }
            case 3: {
                return (Duration)this._deserializeFromArray(parser, context);
            }
        }
        return (Duration)this._reportWrongToken(parser, context, JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT, JsonToken.VALUE_NUMBER_FLOAT);
    }
}

