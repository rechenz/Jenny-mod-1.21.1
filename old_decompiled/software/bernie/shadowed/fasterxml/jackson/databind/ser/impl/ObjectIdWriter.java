/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.impl;

import software.bernie.shadowed.fasterxml.jackson.annotation.ObjectIdGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.core.io.SerializedString;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.PropertyName;

public final class ObjectIdWriter {
    public final JavaType idType;
    public final SerializableString propertyName;
    public final ObjectIdGenerator<?> generator;
    public final JsonSerializer<Object> serializer;
    public final boolean alwaysAsId;

    protected ObjectIdWriter(JavaType t2, SerializableString propName, ObjectIdGenerator<?> gen, JsonSerializer<?> ser, boolean alwaysAsId) {
        this.idType = t2;
        this.propertyName = propName;
        this.generator = gen;
        this.serializer = ser;
        this.alwaysAsId = alwaysAsId;
    }

    public static ObjectIdWriter construct(JavaType idType, PropertyName propName, ObjectIdGenerator<?> generator, boolean alwaysAsId) {
        String simpleName = propName == null ? null : propName.getSimpleName();
        SerializedString serName = simpleName == null ? null : new SerializedString(simpleName);
        return new ObjectIdWriter(idType, serName, generator, null, alwaysAsId);
    }

    public ObjectIdWriter withSerializer(JsonSerializer<?> ser) {
        return new ObjectIdWriter(this.idType, this.propertyName, this.generator, ser, this.alwaysAsId);
    }

    public ObjectIdWriter withAlwaysAsId(boolean newState) {
        if (newState == this.alwaysAsId) {
            return this;
        }
        return new ObjectIdWriter(this.idType, this.propertyName, this.generator, this.serializer, newState);
    }
}

