/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.ser;

import java.io.IOException;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializationFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.ser.JSR310FormattedSerializerBase;

public class OffsetTimeSerializer
extends JSR310FormattedSerializerBase<OffsetTime> {
    private static final long serialVersionUID = 1L;
    public static final OffsetTimeSerializer INSTANCE = new OffsetTimeSerializer();

    protected OffsetTimeSerializer() {
        super(OffsetTime.class);
    }

    protected OffsetTimeSerializer(OffsetTimeSerializer base, Boolean useTimestamp, DateTimeFormatter dtf) {
        super(base, useTimestamp, dtf, null);
    }

    protected OffsetTimeSerializer withFormat(Boolean useTimestamp, DateTimeFormatter dtf, JsonFormat.Shape shape) {
        return new OffsetTimeSerializer(this, useTimestamp, dtf);
    }

    @Override
    public void serialize(OffsetTime time, JsonGenerator g10, SerializerProvider provider) throws IOException {
        if (this.useTimestamp(provider)) {
            g10.writeStartArray();
            this._serializeAsArrayContents(time, g10, provider);
            g10.writeEndArray();
        } else {
            String str = this._formatter == null ? time.toString() : time.format(this._formatter);
            g10.writeString(str);
        }
    }

    @Override
    public void serializeWithType(OffsetTime value, JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId(value, this.serializationShape(provider)));
        if (typeIdDef.valueShape == JsonToken.START_ARRAY) {
            this._serializeAsArrayContents(value, g10, provider);
        } else {
            String str = this._formatter == null ? value.toString() : value.format(this._formatter);
            g10.writeString(str);
        }
        typeSer.writeTypeSuffix(g10, typeIdDef);
    }

    private final void _serializeAsArrayContents(OffsetTime value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        g10.writeNumber(value.getHour());
        g10.writeNumber(value.getMinute());
        int secs = value.getSecond();
        int nanos = value.getNano();
        if (secs > 0 || nanos > 0) {
            g10.writeNumber(secs);
            if (nanos > 0) {
                if (provider.isEnabled(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)) {
                    g10.writeNumber(nanos);
                } else {
                    g10.writeNumber(value.get(ChronoField.MILLI_OF_SECOND));
                }
            }
        }
        g10.writeString(value.getOffset().toString());
    }

    @Override
    protected JsonToken serializationShape(SerializerProvider provider) {
        return this.useTimestamp(provider) ? JsonToken.START_ARRAY : JsonToken.VALUE_STRING;
    }
}

