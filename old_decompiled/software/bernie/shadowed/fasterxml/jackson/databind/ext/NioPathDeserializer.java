/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.ext;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.databind.DeserializationContext;
import software.bernie.shadowed.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class NioPathDeserializer
extends StdScalarDeserializer<Path> {
    private static final long serialVersionUID = 1L;

    public NioPathDeserializer() {
        super(Path.class);
    }

    @Override
    public Path deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (!p2.hasToken(JsonToken.VALUE_STRING)) {
            return (Path)ctxt.handleUnexpectedToken(Path.class, p2);
        }
        String value = p2.getText();
        if (value.indexOf(58) < 0) {
            return Paths.get(value, new String[0]);
        }
        try {
            URI uri = new URI(value);
            return Paths.get(uri);
        }
        catch (URISyntaxException e10) {
            return (Path)ctxt.handleInstantiationProblem(this.handledType(), value, e10);
        }
    }
}

