/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.util.JsonParserDelegate;

public class JsonParserSequence
extends JsonParserDelegate {
    protected final JsonParser[] _parsers;
    protected final boolean _checkForExistingToken;
    protected int _nextParserIndex;
    protected boolean _hasToken;

    @Deprecated
    protected JsonParserSequence(JsonParser[] parsers) {
        this(false, parsers);
    }

    protected JsonParserSequence(boolean checkForExistingToken, JsonParser[] parsers) {
        super(parsers[0]);
        this._checkForExistingToken = checkForExistingToken;
        this._hasToken = checkForExistingToken && this.delegate.hasCurrentToken();
        this._parsers = parsers;
        this._nextParserIndex = 1;
    }

    public static JsonParserSequence createFlattened(boolean checkForExistingToken, JsonParser first, JsonParser second) {
        if (!(first instanceof JsonParserSequence) && !(second instanceof JsonParserSequence)) {
            return new JsonParserSequence(checkForExistingToken, new JsonParser[]{first, second});
        }
        ArrayList<JsonParser> p2 = new ArrayList<JsonParser>();
        if (first instanceof JsonParserSequence) {
            ((JsonParserSequence)first).addFlattenedActiveParsers(p2);
        } else {
            p2.add(first);
        }
        if (second instanceof JsonParserSequence) {
            ((JsonParserSequence)second).addFlattenedActiveParsers(p2);
        } else {
            p2.add(second);
        }
        return new JsonParserSequence(checkForExistingToken, p2.toArray(new JsonParser[p2.size()]));
    }

    @Deprecated
    public static JsonParserSequence createFlattened(JsonParser first, JsonParser second) {
        return JsonParserSequence.createFlattened(false, first, second);
    }

    protected void addFlattenedActiveParsers(List<JsonParser> listToAddIn) {
        int len = this._parsers.length;
        for (int i2 = this._nextParserIndex - 1; i2 < len; ++i2) {
            JsonParser p2 = this._parsers[i2];
            if (p2 instanceof JsonParserSequence) {
                ((JsonParserSequence)p2).addFlattenedActiveParsers(listToAddIn);
                continue;
            }
            listToAddIn.add(p2);
        }
    }

    @Override
    public void close() throws IOException {
        do {
            this.delegate.close();
        } while (this.switchToNext());
    }

    @Override
    public JsonToken nextToken() throws IOException {
        if (this.delegate == null) {
            return null;
        }
        if (this._hasToken) {
            this._hasToken = false;
            return this.delegate.currentToken();
        }
        JsonToken t2 = this.delegate.nextToken();
        if (t2 == null) {
            return this.switchAndReturnNext();
        }
        return t2;
    }

    @Override
    public JsonParser skipChildren() throws IOException {
        if (this.delegate.currentToken() != JsonToken.START_OBJECT && this.delegate.currentToken() != JsonToken.START_ARRAY) {
            return this;
        }
        int open = 1;
        while (true) {
            JsonToken t2;
            if ((t2 = this.nextToken()) == null) {
                return this;
            }
            if (t2.isStructStart()) {
                ++open;
                continue;
            }
            if (t2.isStructEnd() && --open == 0) break;
        }
        return this;
    }

    public int containedParsersCount() {
        return this._parsers.length;
    }

    protected boolean switchToNext() {
        if (this._nextParserIndex < this._parsers.length) {
            this.delegate = this._parsers[this._nextParserIndex++];
            return true;
        }
        return false;
    }

    protected JsonToken switchAndReturnNext() throws IOException {
        while (this._nextParserIndex < this._parsers.length) {
            this.delegate = this._parsers[this._nextParserIndex++];
            if (this._checkForExistingToken && this.delegate.hasCurrentToken()) {
                return this.delegate.getCurrentToken();
            }
            JsonToken t2 = this.delegate.nextToken();
            if (t2 == null) continue;
            return t2;
        }
        return null;
    }
}

