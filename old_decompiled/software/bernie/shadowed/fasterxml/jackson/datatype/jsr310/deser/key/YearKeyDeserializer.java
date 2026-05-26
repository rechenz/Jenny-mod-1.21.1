/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key.Jsr310KeyDeserializer;

public class YearKeyDeserializer
extends Jsr310KeyDeserializer {
    public static final YearKeyDeserializer INSTANCE = new YearKeyDeserializer();
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).toFormatter();

    private YearKeyDeserializer() {
    }

    @Override
    protected Year deserialize(String key, DeserializationContext ctxt) throws IOException {
        try {
            return Year.parse(key, FORMATTER);
        }
        catch (DateTimeException e10) {
            return (Year)this._rethrowDateTimeException(ctxt, Year.class, e10, key);
        }
    }
}

