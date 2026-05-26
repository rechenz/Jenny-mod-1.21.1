/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.net.InetAddress;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonFormat;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContextualSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

public class InetAddressSerializer
extends StdScalarSerializer<InetAddress>
implements ContextualSerializer {
    protected final boolean _asNumeric;

    public InetAddressSerializer() {
        this(false);
    }

    public InetAddressSerializer(boolean asNumeric) {
        super(InetAddress.class);
        this._asNumeric = asNumeric;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        JsonFormat.Shape shape;
        JsonFormat.Value format = this.findFormatOverrides(serializers, property, this.handledType());
        boolean asNumeric = false;
        if (format != null && ((shape = format.getShape()).isNumeric() || shape == JsonFormat.Shape.ARRAY)) {
            asNumeric = true;
        }
        if (asNumeric != this._asNumeric) {
            return new InetAddressSerializer(asNumeric);
        }
        return this;
    }

    @Override
    public void serialize(InetAddress value, JsonGenerator g10, SerializerProvider provider) throws IOException {
        String str;
        if (this._asNumeric) {
            str = value.getHostAddress();
        } else {
            str = value.toString().trim();
            int ix = str.indexOf(47);
            if (ix >= 0) {
                str = ix == 0 ? str.substring(1) : str.substring(0, ix);
            }
        }
        g10.writeString(str);
    }

    @Override
    public void serializeWithType(InetAddress value, JsonGenerator g10, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g10, typeSer.typeId((Object)value, InetAddress.class, JsonToken.VALUE_STRING));
        this.serialize(value, g10, provider);
        typeSer.writeTypeSuffix(g10, typeIdDef);
    }
}

