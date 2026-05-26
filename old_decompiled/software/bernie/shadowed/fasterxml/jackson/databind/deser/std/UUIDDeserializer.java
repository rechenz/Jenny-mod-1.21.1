/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variants;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.exc.InvalidFormatException;

public class UUIDDeserializer
extends FromStringDeserializer<UUID> {
    private static final long serialVersionUID = 1L;
    static final int[] HEX_DIGITS;

    public UUIDDeserializer() {
        super(UUID.class);
    }

    @Override
    protected UUID _deserialize(String id, DeserializationContext ctxt) throws IOException {
        if (id.length() != 36) {
            if (id.length() == 24) {
                byte[] stuff = Base64Variants.getDefaultVariant().decode(id);
                return this._fromBytes(stuff, ctxt);
            }
            return this._badFormat(id, ctxt);
        }
        if (id.charAt(8) != '-' || id.charAt(13) != '-' || id.charAt(18) != '-' || id.charAt(23) != '-') {
            this._badFormat(id, ctxt);
        }
        long l1 = this.intFromChars(id, 0, ctxt);
        long l2 = (long)this.shortFromChars(id, 9, ctxt) << 16;
        long hi = (l1 <<= 32) + (l2 |= (long)this.shortFromChars(id, 14, ctxt));
        int i1 = this.shortFromChars(id, 19, ctxt) << 16 | this.shortFromChars(id, 24, ctxt);
        l1 = i1;
        l2 = this.intFromChars(id, 28, ctxt);
        l2 = l2 << 32 >>> 32;
        long lo = (l1 <<= 32) | l2;
        return new UUID(hi, lo);
    }

    @Override
    protected UUID _deserializeEmbedded(Object ob, DeserializationContext ctxt) throws IOException {
        if (ob instanceof byte[]) {
            return this._fromBytes((byte[])ob, ctxt);
        }
        super._deserializeEmbedded(ob, ctxt);
        return null;
    }

    private UUID _badFormat(String uuidStr, DeserializationContext ctxt) throws IOException {
        return (UUID)ctxt.handleWeirdStringValue(this.handledType(), uuidStr, "UUID has to be represented by standard 36-char representation", new Object[0]);
    }

    int intFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
        return (this.byteFromChars(str, index, ctxt) << 24) + (this.byteFromChars(str, index + 2, ctxt) << 16) + (this.byteFromChars(str, index + 4, ctxt) << 8) + this.byteFromChars(str, index + 6, ctxt);
    }

    int shortFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
        return (this.byteFromChars(str, index, ctxt) << 8) + this.byteFromChars(str, index + 2, ctxt);
    }

    int byteFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
        int hex;
        char c12 = str.charAt(index);
        char c22 = str.charAt(index + 1);
        if (c12 <= '\u007f' && c22 <= '\u007f' && (hex = HEX_DIGITS[c12] << 4 | HEX_DIGITS[c22]) >= 0) {
            return hex;
        }
        if (c12 > '\u007f' || HEX_DIGITS[c12] < 0) {
            return this._badChar(str, index, ctxt, c12);
        }
        return this._badChar(str, index + 1, ctxt, c22);
    }

    int _badChar(String uuidStr, int index, DeserializationContext ctxt, char c10) throws JsonMappingException {
        throw ctxt.weirdStringException(uuidStr, this.handledType(), String.format("Non-hex character '%c' (value 0x%s), not valid for UUID String", Character.valueOf(c10), Integer.toHexString(c10)));
    }

    private UUID _fromBytes(byte[] bytes, DeserializationContext ctxt) throws JsonMappingException {
        if (bytes.length != 16) {
            throw InvalidFormatException.from(ctxt.getParser(), "Can only construct UUIDs from byte[16]; got " + bytes.length + " bytes", bytes, this.handledType());
        }
        return new UUID(UUIDDeserializer._long(bytes, 0), UUIDDeserializer._long(bytes, 8));
    }

    private static long _long(byte[] b10, int offset) {
        long l1 = (long)UUIDDeserializer._int(b10, offset) << 32;
        long l2 = UUIDDeserializer._int(b10, offset + 4);
        l2 = l2 << 32 >>> 32;
        return l1 | l2;
    }

    private static int _int(byte[] b10, int offset) {
        return b10[offset] << 24 | (b10[offset + 1] & 0xFF) << 16 | (b10[offset + 2] & 0xFF) << 8 | b10[offset + 3] & 0xFF;
    }

    static {
        int i2;
        HEX_DIGITS = new int[127];
        Arrays.fill(HEX_DIGITS, -1);
        for (i2 = 0; i2 < 10; ++i2) {
            UUIDDeserializer.HEX_DIGITS[48 + i2] = i2;
        }
        for (i2 = 0; i2 < 6; ++i2) {
            UUIDDeserializer.HEX_DIGITS[97 + i2] = 10 + i2;
            UUIDDeserializer.HEX_DIGITS[65 + i2] = 10 + i2;
        }
    }
}

