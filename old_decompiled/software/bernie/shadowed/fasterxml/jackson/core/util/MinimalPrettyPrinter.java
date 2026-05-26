/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.util;

import java.io.IOException;
import java.io.Serializable;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.PrettyPrinter;
import software.bernie.shadowed.fasterxml.jackson.core.util.Separators;

public class MinimalPrettyPrinter
implements PrettyPrinter,
Serializable {
    private static final long serialVersionUID = 1L;
    protected String _rootValueSeparator;
    protected Separators _separators;

    public MinimalPrettyPrinter() {
        this(DEFAULT_ROOT_VALUE_SEPARATOR.toString());
    }

    public MinimalPrettyPrinter(String rootValueSeparator) {
        this._rootValueSeparator = rootValueSeparator;
        this._separators = DEFAULT_SEPARATORS;
    }

    public void setRootValueSeparator(String sep) {
        this._rootValueSeparator = sep;
    }

    public MinimalPrettyPrinter setSeparators(Separators separators) {
        this._separators = separators;
        return this;
    }

    @Override
    public void writeRootValueSeparator(JsonGenerator g10) throws IOException {
        if (this._rootValueSeparator != null) {
            g10.writeRaw(this._rootValueSeparator);
        }
    }

    @Override
    public void writeStartObject(JsonGenerator g10) throws IOException {
        g10.writeRaw('{');
    }

    @Override
    public void beforeObjectEntries(JsonGenerator g10) throws IOException {
    }

    @Override
    public void writeObjectFieldValueSeparator(JsonGenerator g10) throws IOException {
        g10.writeRaw(this._separators.getObjectFieldValueSeparator());
    }

    @Override
    public void writeObjectEntrySeparator(JsonGenerator g10) throws IOException {
        g10.writeRaw(this._separators.getObjectEntrySeparator());
    }

    @Override
    public void writeEndObject(JsonGenerator g10, int nrOfEntries) throws IOException {
        g10.writeRaw('}');
    }

    @Override
    public void writeStartArray(JsonGenerator g10) throws IOException {
        g10.writeRaw('[');
    }

    @Override
    public void beforeArrayValues(JsonGenerator g10) throws IOException {
    }

    @Override
    public void writeArrayValueSeparator(JsonGenerator g10) throws IOException {
        g10.writeRaw(this._separators.getArrayValueSeparator());
    }

    @Override
    public void writeEndArray(JsonGenerator g10, int nrOfValues) throws IOException {
        g10.writeRaw(']');
    }
}

