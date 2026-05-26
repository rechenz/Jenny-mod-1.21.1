/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ext;

import java.io.IOException;
import java.nio.file.Path;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

public class NioPathSerializer
extends StdScalarSerializer<Path> {
    private static final long serialVersionUID = 1L;

    public NioPathSerializer() {
        super(Path.class);
    }

    @Override
    public void serialize(Path value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.toUri().toString());
    }

    @Override
    public void serializeWithType(Path value, JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId((Object)value, Path.class, JsonToken.VALUE_STRING));
        this.serialize(value, g10, provider);
        typeSer.writeTypeSuffix(g10, typeIdDef);
    }
}

