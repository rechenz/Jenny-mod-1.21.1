/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key.Jsr310KeyDeserializer;

public class MonthDayKeyDeserializer
extends Jsr310KeyDeserializer {
    public static final MonthDayKeyDeserializer INSTANCE = new MonthDayKeyDeserializer();
    private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder().appendLiteral("--").appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).toFormatter();

    private MonthDayKeyDeserializer() {
    }

    @Override
    protected MonthDay deserialize(String key, DeserializationContext ctxt) throws IOException {
        try {
            return MonthDay.parse(key, PARSER);
        }
        catch (DateTimeException e10) {
            return (MonthDay)this._rethrowDateTimeException(ctxt, MonthDay.class, e10, key);
        }
    }
}

