/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.io;

import software.bernie.shadowed.fasterxml.jackson.core.JsonParseException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;

public class JsonEOFException
extends JsonParseException {
    private static final long serialVersionUID = 1L;
    protected final JsonToken _token;

    public JsonEOFException(JsonParser p2, JsonToken token, String msg) {
        super(p2, msg);
        this._token = token;
    }

    public JsonToken getTokenBeingDecoded() {
        return this._token;
    }
}

