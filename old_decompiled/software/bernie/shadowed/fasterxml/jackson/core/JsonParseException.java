/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core;

import software.bernie.shadowed.fasterxml.jackson.core.JsonLocation;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;
import software.bernie.shadowed.fasterxml.jackson.core.util.RequestPayload;

public class JsonParseException
extends JsonProcessingException {
    private static final long serialVersionUID = 2L;
    protected transient JsonParser _processor;
    protected RequestPayload _requestPayload;

    @Deprecated
    public JsonParseException(String msg, JsonLocation loc) {
        super(msg, loc);
    }

    @Deprecated
    public JsonParseException(String msg, JsonLocation loc, Throwable root) {
        super(msg, loc, root);
    }

    public JsonParseException(JsonParser p2, String msg) {
        super(msg, p2 == null ? null : p2.getCurrentLocation());
        this._processor = p2;
    }

    public JsonParseException(JsonParser p2, String msg, Throwable root) {
        super(msg, p2 == null ? null : p2.getCurrentLocation(), root);
        this._processor = p2;
    }

    public JsonParseException(JsonParser p2, String msg, JsonLocation loc) {
        super(msg, loc);
        this._processor = p2;
    }

    public JsonParseException(JsonParser p2, String msg, JsonLocation loc, Throwable root) {
        super(msg, loc, root);
        this._processor = p2;
    }

    public JsonParseException withParser(JsonParser p2) {
        this._processor = p2;
        return this;
    }

    public JsonParseException withRequestPayload(RequestPayload p2) {
        this._requestPayload = p2;
        return this;
    }

    @Override
    public JsonParser getProcessor() {
        return this._processor;
    }

    public RequestPayload getRequestPayload() {
        return this._requestPayload;
    }

    public String getRequestPayloadAsString() {
        return this._requestPayload != null ? this._requestPayload.toString() : null;
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (this._requestPayload != null) {
            msg = msg + "\nRequest payload : " + this._requestPayload.toString();
        }
        return msg;
    }
}

