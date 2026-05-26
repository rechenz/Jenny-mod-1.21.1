/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.TokenBuffer;

@JacksonStdImpl
public class TokenBufferDeserializer
extends StdScalarDeserializer<TokenBuffer> {
    private static final long serialVersionUID = 1L;

    public TokenBufferDeserializer() {
        super(TokenBuffer.class);
    }

    @Override
    public TokenBuffer deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        return this.createBufferInstance(p2).deserialize(p2, ctxt);
    }

    protected TokenBuffer createBufferInstance(JsonParser p2) {
        return new TokenBuffer(p2);
    }
}

