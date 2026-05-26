/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.datatype.jsr310.deser;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeDeserializer;

abstract class JSR310DeserializerBase<T>
extends StdScalarDeserializer<T> {
    private static final long serialVersionUID = 1L;

    protected JSR310DeserializerBase(Class<T> supportedType) {
        super(supportedType);
    }

    @Override
    public Object deserializeWithType(JsonParser parser, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(parser, context);
    }

    protected <BOGUS> BOGUS _reportWrongToken(DeserializationContext context, JsonToken exp, String unit) throws IOException {
        context.reportWrongTokenException(this, exp, "Expected %s for '%s' of %s value", exp.name(), unit, this.handledType().getName());
        return null;
    }

    protected <BOGUS> BOGUS _reportWrongToken(JsonParser parser, DeserializationContext context, JsonToken ... expTypes) throws IOException {
        return (BOGUS)context.reportInputMismatch(this.handledType(), "Unexpected token (%s), expected one of %s for %s value", new Object[]{parser.getCurrentToken(), Arrays.asList(expTypes).toString(), this.handledType().getName()});
    }

    protected <BOGUS> BOGUS _rethrowDateTimeException(JsonParser p2, DeserializationContext context, DateTimeException e02, String value) throws JsonMappingException {
        String msg;
        if (e02 instanceof DateTimeParseException) {
            JsonMappingException e10 = context.weirdStringException(value, this.handledType(), e02.getMessage());
            e10.initCause(e02);
            throw e10;
        }
        if (e02 instanceof DateTimeException && (msg = e02.getMessage()).contains("invalid format")) {
            JsonMappingException e11 = context.weirdStringException(value, this.handledType(), e02.getMessage());
            e11.initCause(e02);
            throw e11;
        }
        return (BOGUS)context.reportInputMismatch(this.handledType(), "Failed to deserialize %s: (%s) %s", this.handledType().getName(), e02.getClass().getName(), e02.getMessage());
    }

    protected DateTimeException _peelDTE(DateTimeException e10) {
        Throwable t2;
        while ((t2 = e10.getCause()) != null && t2 instanceof DateTimeException) {
            e10 = (DateTimeException)t2;
        }
        return e10;
    }
}

