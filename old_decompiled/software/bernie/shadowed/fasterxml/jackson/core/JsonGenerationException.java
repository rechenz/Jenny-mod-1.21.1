/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core;

import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonLocation;
import software.bernie.shadowed.fasterxml.jackson.core.JsonProcessingException;

public class JsonGenerationException
extends JsonProcessingException {
    private static final long serialVersionUID = 123L;
    protected transient JsonGenerator _processor;

    @Deprecated
    public JsonGenerationException(Throwable rootCause) {
        super(rootCause);
    }

    @Deprecated
    public JsonGenerationException(String msg) {
        super(msg, (JsonLocation)null);
    }

    @Deprecated
    public JsonGenerationException(String msg, Throwable rootCause) {
        super(msg, null, rootCause);
    }

    public JsonGenerationException(Throwable rootCause, JsonGenerator g10) {
        super(rootCause);
        this._processor = g10;
    }

    public JsonGenerationException(String msg, JsonGenerator g10) {
        super(msg, (JsonLocation)null);
        this._processor = g10;
    }

    public JsonGenerationException(String msg, Throwable rootCause, JsonGenerator g10) {
        super(msg, null, rootCause);
        this._processor = g10;
    }

    public JsonGenerationException withGenerator(JsonGenerator g10) {
        this._processor = g10;
        return this;
    }

    @Override
    public JsonGenerator getProcessor() {
        return this._processor;
    }
}

