/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

@Deprecated
public abstract class NonTypedScalarSerializerBase<T>
extends StdScalarSerializer<T> {
    protected NonTypedScalarSerializerBase(Class<T> t2) {
        super(t2);
    }

    protected NonTypedScalarSerializerBase(Class<?> t2, boolean bogus) {
        super(t2, bogus);
    }

    @Override
    public final void serializeWithType(T value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, provider);
    }
}

