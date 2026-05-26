/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key.Jsr310KeyDeserializer;

public class OffsetTimeKeyDeserializer
extends Jsr310KeyDeserializer {
    public static final OffsetTimeKeyDeserializer INSTANCE = new OffsetTimeKeyDeserializer();

    private OffsetTimeKeyDeserializer() {
    }

    @Override
    protected OffsetTime deserialize(String key, DeserializationContext ctxt) throws IOException {
        try {
            return OffsetTime.parse(key, DateTimeFormatter.ISO_OFFSET_TIME);
        }
        catch (DateTimeException e10) {
            return (OffsetTime)this._rethrowDateTimeException(ctxt, OffsetTime.class, e10, key);
        }
    }
}

