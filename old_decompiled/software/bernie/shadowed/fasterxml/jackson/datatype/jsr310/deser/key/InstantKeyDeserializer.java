/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key.Jsr310KeyDeserializer;

public class InstantKeyDeserializer
extends Jsr310KeyDeserializer {
    public static final InstantKeyDeserializer INSTANCE = new InstantKeyDeserializer();

    private InstantKeyDeserializer() {
    }

    @Override
    protected Instant deserialize(String key, DeserializationContext ctxt) throws IOException {
        try {
            return DateTimeFormatter.ISO_INSTANT.parse((CharSequence)key, Instant::from);
        }
        catch (DateTimeException e10) {
            return (Instant)this._rethrowDateTimeException(ctxt, Instant.class, e10, key);
        }
    }
}

