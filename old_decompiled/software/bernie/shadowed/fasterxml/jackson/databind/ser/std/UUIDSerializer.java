/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.util.UUID;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

public class UUIDSerializer
extends StdScalarSerializer<UUID> {
    static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public UUIDSerializer() {
        super(UUID.class);
    }

    @Override
    public boolean isEmpty(SerializerProvider prov, UUID value) {
        return value.getLeastSignificantBits() == 0L && value.getMostSignificantBits() == 0L;
    }

    @Override
    public void serialize(UUID value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (gen.canWriteBinaryNatively() && !(gen instanceof TokenBuffer)) {
            gen.writeBinary(UUIDSerializer._asBytes(value));
            return;
        }
        char[] ch2 = new char[36];
        long msb = value.getMostSignificantBits();
        UUIDSerializer._appendInt((int)(msb >> 32), ch2, 0);
        ch2[8] = 45;
        int i2 = (int)msb;
        UUIDSerializer._appendShort(i2 >>> 16, ch2, 9);
        ch2[13] = 45;
        UUIDSerializer._appendShort(i2, ch2, 14);
        ch2[18] = 45;
        long lsb = value.getLeastSignificantBits();
        UUIDSerializer._appendShort((int)(lsb >>> 48), ch2, 19);
        ch2[23] = 45;
        UUIDSerializer._appendShort((int)(lsb >>> 32), ch2, 24);
        UUIDSerializer._appendInt((int)lsb, ch2, 28);
        gen.writeString(ch2, 0, 36);
    }

    private static void _appendInt(int bits, char[] ch2, int offset) {
        UUIDSerializer._appendShort(bits >> 16, ch2, offset);
        UUIDSerializer._appendShort(bits, ch2, offset + 4);
    }

    private static void _appendShort(int bits, char[] ch2, int offset) {
        ch2[offset] = HEX_CHARS[bits >> 12 & 0xF];
        ch2[++offset] = HEX_CHARS[bits >> 8 & 0xF];
        ch2[++offset] = HEX_CHARS[bits >> 4 & 0xF];
        ch2[++offset] = HEX_CHARS[bits & 0xF];
    }

    private static final byte[] _asBytes(UUID uuid) {
        byte[] buffer = new byte[16];
        long hi = uuid.getMostSignificantBits();
        long lo = uuid.getLeastSignificantBits();
        UUIDSerializer._appendInt((int)(hi >> 32), buffer, 0);
        UUIDSerializer._appendInt((int)hi, buffer, 4);
        UUIDSerializer._appendInt((int)(lo >> 32), buffer, 8);
        UUIDSerializer._appendInt((int)lo, buffer, 12);
        return buffer;
    }

    private static final void _appendInt(int value, byte[] buffer, int offset) {
        buffer[offset] = (byte)(value >> 24);
        buffer[++offset] = (byte)(value >> 16);
        buffer[++offset] = (byte)(value >> 8);
        buffer[++offset] = (byte)value;
    }
}

