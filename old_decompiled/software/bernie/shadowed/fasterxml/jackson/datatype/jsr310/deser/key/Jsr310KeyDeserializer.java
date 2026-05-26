/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser.key;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.KeyDeserializer;

abstract class Jsr310KeyDeserializer
extends KeyDeserializer {
    Jsr310KeyDeserializer() {
    }

    @Override
    public final Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        if ("".equals(key)) {
            return null;
        }
        return this.deserialize(key, ctxt);
    }

    protected abstract Object deserialize(String var1, DeserializationContext var2) throws IOException;

    protected <T> T _rethrowDateTimeException(DeserializationContext ctxt, Class<?> type, DateTimeException e02, String value) throws IOException {
        JsonMappingException e10;
        if (e02 instanceof DateTimeParseException) {
            e10 = ctxt.weirdStringException(value, type, e02.getMessage());
            e10.initCause(e02);
        } else {
            e10 = JsonMappingException.from(ctxt, String.format("Failed to deserialize %s: (%s) %s", type.getName(), e02.getClass().getName(), e02.getMessage()), (Throwable)e02);
        }
        throw e10;
    }
}

