/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.ser;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.ser.InstantSerializerBase;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.ser.JSR310FormattedSerializerBase;

public class OffsetDateTimeSerializer
extends InstantSerializerBase<OffsetDateTime> {
    private static final long serialVersionUID = 1L;
    public static final OffsetDateTimeSerializer INSTANCE = new OffsetDateTimeSerializer();

    protected OffsetDateTimeSerializer() {
        super(OffsetDateTime.class, dt2 -> dt2.toInstant().toEpochMilli(), OffsetDateTime::toEpochSecond, OffsetDateTime::getNano, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    protected OffsetDateTimeSerializer(OffsetDateTimeSerializer base, Boolean useTimestamp, DateTimeFormatter formatter) {
        super(base, useTimestamp, formatter);
    }

    @Override
    protected JSR310FormattedSerializerBase<?> withFormat(Boolean useTimestamp, DateTimeFormatter formatter, JsonFormat.Shape shape) {
        return new OffsetDateTimeSerializer(this, useTimestamp, formatter);
    }
}

