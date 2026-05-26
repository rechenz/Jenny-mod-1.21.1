/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.ser;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.ser.InstantSerializerBase;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.ser.JSR310FormattedSerializerBase;

public final class InstantSerializer
extends InstantSerializerBase<Instant> {
    private static final long serialVersionUID = 1L;
    public static final InstantSerializer INSTANCE = new InstantSerializer();

    protected InstantSerializer() {
        super(Instant.class, Instant::toEpochMilli, Instant::getEpochSecond, Instant::getNano, null);
    }

    protected InstantSerializer(InstantSerializer base, Boolean useTimestamp, DateTimeFormatter formatter) {
        super(base, useTimestamp, formatter);
    }

    @Override
    protected JSR310FormattedSerializerBase<Instant> withFormat(Boolean useTimestamp, DateTimeFormatter formatter, JsonFormat.Shape shape) {
        return new InstantSerializer(this, useTimestamp, formatter);
    }
}

