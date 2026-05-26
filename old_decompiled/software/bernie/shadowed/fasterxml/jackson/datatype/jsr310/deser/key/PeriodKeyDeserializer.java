/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Period;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key.Jsr310KeyDeserializer;

public class PeriodKeyDeserializer
extends Jsr310KeyDeserializer {
    public static final PeriodKeyDeserializer INSTANCE = new PeriodKeyDeserializer();

    private PeriodKeyDeserializer() {
    }

    @Override
    protected Period deserialize(String key, DeserializationContext ctxt) throws IOException {
        try {
            return Period.parse(key);
        }
        catch (DateTimeException e10) {
            return (Period)this._rethrowDateTimeException(ctxt, Period.class, e10, key);
        }
    }
}

