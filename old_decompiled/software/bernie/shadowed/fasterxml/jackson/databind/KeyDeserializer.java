/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;

public abstract class KeyDeserializer {
    public abstract Object deserializeKey(String var1, DeserializationContext var2) throws IOException;

    public static abstract class None
    extends KeyDeserializer {
    }
}

