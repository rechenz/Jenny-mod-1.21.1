/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

@JacksonStdImpl
public class SqlDateSerializer
extends DateTimeSerializerBase<Date> {
    public SqlDateSerializer() {
        this(null, null);
    }

    protected SqlDateSerializer(Boolean useTimestamp, DateFormat customFormat) {
        super(Date.class, useTimestamp, customFormat);
    }

    public SqlDateSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
        return new SqlDateSerializer(timestamp, customFormat);
    }

    @Override
    protected long _timestamp(Date value) {
        return value == null ? 0L : value.getTime();
    }

    @Override
    public void serialize(Date value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        if (this._asTimestamp(provider)) {
            g10.writeNumber(this._timestamp(value));
            return;
        }
        if (this._customFormat == null) {
            g10.writeString(value.toString());
            return;
        }
        this._serializeAsString(value, g10, provider);
    }
}

