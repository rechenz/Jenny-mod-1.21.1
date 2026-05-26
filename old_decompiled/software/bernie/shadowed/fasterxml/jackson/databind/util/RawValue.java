/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializable;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

public class RawValue
implements JsonSerializable {
    protected Object _value;

    public RawValue(String v2) {
        this._value = v2;
    }

    public RawValue(SerializableString v2) {
        this._value = v2;
    }

    public RawValue(JsonSerializable v2) {
        this._value = v2;
    }

    protected RawValue(Object value, boolean bogus) {
        this._value = value;
    }

    public Object rawValue() {
        return this._value;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (this._value instanceof JsonSerializable) {
            ((JsonSerializable)this._value).serialize(gen, serializers);
        } else {
            this._serialize(gen);
        }
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        if (this._value instanceof JsonSerializable) {
            ((JsonSerializable)this._value).serializeWithType(gen, serializers, typeSer);
        } else if (this._value instanceof SerializableString) {
            this.serialize(gen, serializers);
        }
    }

    public void serialize(JsonGenerator gen) throws IOException {
        if (this._value instanceof JsonSerializable) {
            gen.writeObject(this._value);
        } else {
            this._serialize(gen);
        }
    }

    protected void _serialize(JsonGenerator gen) throws IOException {
        if (this._value instanceof SerializableString) {
            gen.writeRawValue((SerializableString)this._value);
        } else {
            gen.writeRawValue(String.valueOf(this._value));
        }
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof RawValue)) {
            return false;
        }
        RawValue other = (RawValue)o2;
        if (this._value == other._value) {
            return true;
        }
        return this._value != null && this._value.equals(other._value);
    }

    public int hashCode() {
        return this._value == null ? 0 : this._value.hashCode();
    }

    public String toString() {
        return String.format("[RawValue of type %s]", ClassUtil.classNameOf(this._value));
    }
}

