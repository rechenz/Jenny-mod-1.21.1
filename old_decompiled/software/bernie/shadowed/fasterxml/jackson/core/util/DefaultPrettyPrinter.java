/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.util;

import java.io.IOException;
import java.io.Serializable;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.PrettyPrinter;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.core.io.SerializedString;
import software.bernie.shadowed.fasterxml.jackson.core.util.DefaultIndenter;
import software.bernie.shadowed.fasterxml.jackson.core.util.Instantiatable;
import software.bernie.shadowed.fasterxml.jackson.core.util.Separators;

public class DefaultPrettyPrinter
implements PrettyPrinter,
Instantiatable<DefaultPrettyPrinter>,
Serializable {
    private static final long serialVersionUID = 1L;
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
    protected Indenter _arrayIndenter = FixedSpaceIndenter.instance;
    protected Indenter _objectIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
    protected final SerializableString _rootSeparator;
    protected boolean _spacesInObjectEntries = true;
    protected transient int _nesting;
    protected Separators _separators;
    protected String _objectFieldValueSeparatorWithSpaces;

    public DefaultPrettyPrinter() {
        this(DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public DefaultPrettyPrinter(String rootSeparator) {
        this(rootSeparator == null ? null : new SerializedString(rootSeparator));
    }

    public DefaultPrettyPrinter(SerializableString rootSeparator) {
        this._rootSeparator = rootSeparator;
        this.withSeparators(DEFAULT_SEPARATORS);
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter base) {
        this(base, base._rootSeparator);
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter base, SerializableString rootSeparator) {
        this._arrayIndenter = base._arrayIndenter;
        this._objectIndenter = base._objectIndenter;
        this._spacesInObjectEntries = base._spacesInObjectEntries;
        this._nesting = base._nesting;
        this._separators = base._separators;
        this._objectFieldValueSeparatorWithSpaces = base._objectFieldValueSeparatorWithSpaces;
        this._rootSeparator = rootSeparator;
    }

    public DefaultPrettyPrinter withRootSeparator(SerializableString rootSeparator) {
        if (this._rootSeparator == rootSeparator || rootSeparator != null && rootSeparator.equals(this._rootSeparator)) {
            return this;
        }
        return new DefaultPrettyPrinter(this, rootSeparator);
    }

    public DefaultPrettyPrinter withRootSeparator(String rootSeparator) {
        return this.withRootSeparator(rootSeparator == null ? null : new SerializedString(rootSeparator));
    }

    public void indentArraysWith(Indenter i2) {
        this._arrayIndenter = i2 == null ? NopIndenter.instance : i2;
    }

    public void indentObjectsWith(Indenter i2) {
        this._objectIndenter = i2 == null ? NopIndenter.instance : i2;
    }

    public DefaultPrettyPrinter withArrayIndenter(Indenter i2) {
        if (i2 == null) {
            i2 = NopIndenter.instance;
        }
        if (this._arrayIndenter == i2) {
            return this;
        }
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter(this);
        pp._arrayIndenter = i2;
        return pp;
    }

    public DefaultPrettyPrinter withObjectIndenter(Indenter i2) {
        if (i2 == null) {
            i2 = NopIndenter.instance;
        }
        if (this._objectIndenter == i2) {
            return this;
        }
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter(this);
        pp._objectIndenter = i2;
        return pp;
    }

    public DefaultPrettyPrinter withSpacesInObjectEntries() {
        return this._withSpaces(true);
    }

    public DefaultPrettyPrinter withoutSpacesInObjectEntries() {
        return this._withSpaces(false);
    }

    protected DefaultPrettyPrinter _withSpaces(boolean state) {
        if (this._spacesInObjectEntries == state) {
            return this;
        }
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter(this);
        pp._spacesInObjectEntries = state;
        return pp;
    }

    public DefaultPrettyPrinter withSeparators(Separators separators) {
        this._separators = separators;
        this._objectFieldValueSeparatorWithSpaces = " " + separators.getObjectFieldValueSeparator() + " ";
        return this;
    }

    @Override
    public DefaultPrettyPrinter createInstance() {
        return new DefaultPrettyPrinter(this);
    }

    @Override
    public void writeRootValueSeparator(JsonGenerator g10) throws IOException {
        if (this._rootSeparator != null) {
            g10.writeRaw(this._rootSeparator);
        }
    }

    @Override
    public void writeStartObject(JsonGenerator g10) throws IOException {
        g10.writeRaw('{');
        if (!this._objectIndenter.isInline()) {
            ++this._nesting;
        }
    }

    @Override
    public void beforeObjectEntries(JsonGenerator g10) throws IOException {
        this._objectIndenter.writeIndentation(g10, this._nesting);
    }

    @Override
    public void writeObjectFieldValueSeparator(JsonGenerator g10) throws IOException {
        if (this._spacesInObjectEntries) {
            g10.writeRaw(this._objectFieldValueSeparatorWithSpaces);
        } else {
            g10.writeRaw(this._separators.getObjectFieldValueSeparator());
        }
    }

    @Override
    public void writeObjectEntrySeparator(JsonGenerator g10) throws IOException {
        g10.writeRaw(this._separators.getObjectEntrySeparator());
        this._objectIndenter.writeIndentation(g10, this._nesting);
    }

    @Override
    public void writeEndObject(JsonGenerator g10, int nrOfEntries) throws IOException {
        if (!this._objectIndenter.isInline()) {
            --this._nesting;
        }
        if (nrOfEntries > 0) {
            this._objectIndenter.writeIndentation(g10, this._nesting);
        } else {
            g10.writeRaw(' ');
        }
        g10.writeRaw('}');
    }

    @Override
    public void writeStartArray(JsonGenerator g10) throws IOException {
        if (!this._arrayIndenter.isInline()) {
            ++this._nesting;
        }
        g10.writeRaw('[');
    }

    @Override
    public void beforeArrayValues(JsonGenerator g10) throws IOException {
        this._arrayIndenter.writeIndentation(g10, this._nesting);
    }

    @Override
    public void writeArrayValueSeparator(JsonGenerator g10) throws IOException {
        g10.writeRaw(this._separators.getArrayValueSeparator());
        this._arrayIndenter.writeIndentation(g10, this._nesting);
    }

    @Override
    public void writeEndArray(JsonGenerator g10, int nrOfValues) throws IOException {
        if (!this._arrayIndenter.isInline()) {
            --this._nesting;
        }
        if (nrOfValues > 0) {
            this._arrayIndenter.writeIndentation(g10, this._nesting);
        } else {
            g10.writeRaw(' ');
        }
        g10.writeRaw(']');
    }

    public static class FixedSpaceIndenter
    extends NopIndenter {
        public static final FixedSpaceIndenter instance = new FixedSpaceIndenter();

        @Override
        public void writeIndentation(JsonGenerator g10, int level) throws IOException {
            g10.writeRaw(' ');
        }

        @Override
        public boolean isInline() {
            return true;
        }
    }

    public static class NopIndenter
    implements Indenter,
    Serializable {
        public static final NopIndenter instance = new NopIndenter();

        @Override
        public void writeIndentation(JsonGenerator g10, int level) throws IOException {
        }

        @Override
        public boolean isInline() {
            return true;
        }
    }

    public static interface Indenter {
        public void writeIndentation(JsonGenerator var1, int var2) throws IOException;

        public boolean isInline();
    }
}

