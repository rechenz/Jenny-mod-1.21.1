/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.JsonLocation;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParseException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.core.base.ParserBase;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharTypes;
import software.bernie.shadowed.fasterxml.jackson.core.io.IOContext;
import software.bernie.shadowed.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import software.bernie.shadowed.fasterxml.jackson.core.util.ByteArrayBuilder;
import software.bernie.shadowed.fasterxml.jackson.core.util.TextBuffer;

public class ReaderBasedJsonParser
extends ParserBase {
    protected static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    protected Reader _reader;
    protected char[] _inputBuffer;
    protected boolean _bufferRecyclable;
    protected ObjectCodec _objectCodec;
    protected final CharsToNameCanonicalizer _symbols;
    protected final int _hashSeed;
    protected boolean _tokenIncomplete;
    protected long _nameStartOffset;
    protected int _nameStartRow;
    protected int _nameStartCol;

    public ReaderBasedJsonParser(IOContext ctxt, int features, Reader r2, ObjectCodec codec, CharsToNameCanonicalizer st, char[] inputBuffer, int start, int end, boolean bufferRecyclable) {
        super(ctxt, features);
        this._reader = r2;
        this._inputBuffer = inputBuffer;
        this._inputPtr = start;
        this._inputEnd = end;
        this._objectCodec = codec;
        this._symbols = st;
        this._hashSeed = st.hashSeed();
        this._bufferRecyclable = bufferRecyclable;
    }

    public ReaderBasedJsonParser(IOContext ctxt, int features, Reader r2, ObjectCodec codec, CharsToNameCanonicalizer st) {
        super(ctxt, features);
        this._reader = r2;
        this._inputBuffer = ctxt.allocTokenBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._objectCodec = codec;
        this._symbols = st;
        this._hashSeed = st.hashSeed();
        this._bufferRecyclable = true;
    }

    @Override
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override
    public void setCodec(ObjectCodec c10) {
        this._objectCodec = c10;
    }

    @Override
    public int releaseBuffered(Writer w2) throws IOException {
        int count = this._inputEnd - this._inputPtr;
        if (count < 1) {
            return 0;
        }
        int origPtr = this._inputPtr;
        w2.write(this._inputBuffer, origPtr, count);
        return count;
    }

    @Override
    public Object getInputSource() {
        return this._reader;
    }

    @Deprecated
    protected char getNextChar(String eofMsg) throws IOException {
        return this.getNextChar(eofMsg, null);
    }

    protected char getNextChar(String eofMsg, JsonToken forToken) throws IOException {
        if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(eofMsg, forToken);
        }
        return this._inputBuffer[this._inputPtr++];
    }

    @Override
    protected void _closeInput() throws IOException {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || this.isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    @Override
    protected void _releaseBuffers() throws IOException {
        char[] buf;
        super._releaseBuffers();
        this._symbols.release();
        if (this._bufferRecyclable && (buf = this._inputBuffer) != null) {
            this._inputBuffer = null;
            this._ioContext.releaseTokenBuffer(buf);
        }
    }

    protected void _loadMoreGuaranteed() throws IOException {
        if (!this._loadMore()) {
            this._reportInvalidEOF();
        }
    }

    protected boolean _loadMore() throws IOException {
        int bufSize = this._inputEnd;
        this._currInputProcessed += (long)bufSize;
        this._currInputRowStart -= bufSize;
        this._nameStartOffset -= (long)bufSize;
        if (this._reader != null) {
            int count = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (count > 0) {
                this._inputPtr = 0;
                this._inputEnd = count;
                return true;
            }
            this._closeInput();
            if (count == 0) {
                throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
            }
        }
        return false;
    }

    @Override
    public final String getText() throws IOException {
        JsonToken t2 = this._currToken;
        if (t2 == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return this._getText2(t2);
    }

    @Override
    public int getText(Writer writer) throws IOException {
        JsonToken t2 = this._currToken;
        if (t2 == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsToWriter(writer);
        }
        if (t2 == JsonToken.FIELD_NAME) {
            String n2 = this._parsingContext.getCurrentName();
            writer.write(n2);
            return n2.length();
        }
        if (t2 != null) {
            if (t2.isNumeric()) {
                return this._textBuffer.contentsToWriter(writer);
            }
            char[] ch2 = t2.asCharArray();
            writer.write(ch2);
            return ch2.length;
        }
        return 0;
    }

    @Override
    public final String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        return super.getValueAsString(null);
    }

    @Override
    public final String getValueAsString(String defValue) throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        return super.getValueAsString(defValue);
    }

    protected final String _getText2(JsonToken t2) {
        if (t2 == null) {
            return null;
        }
        switch (t2.id()) {
            case 5: {
                return this._parsingContext.getCurrentName();
            }
            case 6: 
            case 7: 
            case 8: {
                return this._textBuffer.contentsAsString();
            }
        }
        return t2.asString();
    }

    @Override
    public final char[] getTextCharacters() throws IOException {
        if (this._currToken != null) {
            switch (this._currToken.id()) {
                case 5: {
                    if (!this._nameCopied) {
                        String name = this._parsingContext.getCurrentName();
                        int nameLen = name.length();
                        if (this._nameCopyBuffer == null) {
                            this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(nameLen);
                        } else if (this._nameCopyBuffer.length < nameLen) {
                            this._nameCopyBuffer = new char[nameLen];
                        }
                        name.getChars(0, nameLen, this._nameCopyBuffer, 0);
                        this._nameCopied = true;
                    }
                    return this._nameCopyBuffer;
                }
                case 6: {
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        this._finishString();
                    }
                }
                case 7: 
                case 8: {
                    return this._textBuffer.getTextBuffer();
                }
            }
            return this._currToken.asCharArray();
        }
        return null;
    }

    @Override
    public final int getTextLength() throws IOException {
        if (this._currToken != null) {
            switch (this._currToken.id()) {
                case 5: {
                    return this._parsingContext.getCurrentName().length();
                }
                case 6: {
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        this._finishString();
                    }
                }
                case 7: 
                case 8: {
                    return this._textBuffer.size();
                }
            }
            return this._currToken.asCharArray().length;
        }
        return 0;
    }

    @Override
    public final int getTextOffset() throws IOException {
        if (this._currToken != null) {
            switch (this._currToken.id()) {
                case 5: {
                    return 0;
                }
                case 6: {
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        this._finishString();
                    }
                }
                case 7: 
                case 8: {
                    return this._textBuffer.getTextOffset();
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT && this._binaryValue != null) {
            return this._binaryValue;
        }
        if (this._currToken != JsonToken.VALUE_STRING) {
            this._reportError("Current token (" + (Object)((Object)this._currToken) + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = this._decodeBase64(b64variant);
            }
            catch (IllegalArgumentException iae) {
                throw this._constructError("Failed to decode VALUE_STRING as base64 (" + b64variant + "): " + iae.getMessage());
            }
            this._tokenIncomplete = false;
        } else if (this._binaryValue == null) {
            ByteArrayBuilder builder = this._getByteArrayBuilder();
            this._decodeBase64(this.getText(), builder, b64variant);
            this._binaryValue = builder.toByteArray();
        }
        return this._binaryValue;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] b10 = this.getBinaryValue(b64variant);
            out.write(b10);
            return b10.length;
        }
        byte[] buf = this._ioContext.allocBase64Buffer();
        try {
            int n2 = this._readBinary(b64variant, out, buf);
            return n2;
        }
        finally {
            this._ioContext.releaseBase64Buffer(buf);
        }
    }

    protected int _readBinary(Base64Variant b64variant, OutputStream out, byte[] buffer) throws IOException {
        int outputPtr = 0;
        int outputEnd = buffer.length - 3;
        int outputCount = 0;
        while (true) {
            char ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._loadMoreGuaranteed();
            }
            if ((ch2 = this._inputBuffer[this._inputPtr++]) <= ' ') continue;
            int bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                if (ch2 == '\"') break;
                bits = this._decodeBase64Escape(b64variant, ch2, 0);
                if (bits < 0) continue;
            }
            if (outputPtr > outputEnd) {
                outputCount += outputPtr;
                out.write(buffer, 0, outputPtr);
                outputPtr = 0;
            }
            int decodedData = bits;
            if (this._inputPtr >= this._inputEnd) {
                this._loadMoreGuaranteed();
            }
            if ((bits = b64variant.decodeBase64Char(ch2 = this._inputBuffer[this._inputPtr++])) < 0) {
                bits = this._decodeBase64Escape(b64variant, ch2, 1);
            }
            decodedData = decodedData << 6 | bits;
            if (this._inputPtr >= this._inputEnd) {
                this._loadMoreGuaranteed();
            }
            if ((bits = b64variant.decodeBase64Char(ch2 = this._inputBuffer[this._inputPtr++])) < 0) {
                if (bits != -2) {
                    if (ch2 == '\"' && !b64variant.usesPadding()) {
                        buffer[outputPtr++] = (byte)(decodedData >>= 4);
                        break;
                    }
                    bits = this._decodeBase64Escape(b64variant, ch2, 2);
                }
                if (bits == -2) {
                    if (this._inputPtr >= this._inputEnd) {
                        this._loadMoreGuaranteed();
                    }
                    if (!b64variant.usesPaddingChar(ch2 = this._inputBuffer[this._inputPtr++])) {
                        throw this.reportInvalidBase64Char(b64variant, ch2, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                    }
                    buffer[outputPtr++] = (byte)(decodedData >>= 4);
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits;
            if (this._inputPtr >= this._inputEnd) {
                this._loadMoreGuaranteed();
            }
            if ((bits = b64variant.decodeBase64Char(ch2 = this._inputBuffer[this._inputPtr++])) < 0) {
                if (bits != -2) {
                    if (ch2 == '\"' && !b64variant.usesPadding()) {
                        buffer[outputPtr++] = (byte)((decodedData >>= 2) >> 8);
                        buffer[outputPtr++] = (byte)decodedData;
                        break;
                    }
                    bits = this._decodeBase64Escape(b64variant, ch2, 3);
                }
                if (bits == -2) {
                    buffer[outputPtr++] = (byte)((decodedData >>= 2) >> 8);
                    buffer[outputPtr++] = (byte)decodedData;
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits;
            buffer[outputPtr++] = (byte)(decodedData >> 16);
            buffer[outputPtr++] = (byte)(decodedData >> 8);
            buffer[outputPtr++] = (byte)decodedData;
        }
        this._tokenIncomplete = false;
        if (outputPtr > 0) {
            outputCount += outputPtr;
            out.write(buffer, 0, outputPtr);
        }
        return outputCount;
    }

    @Override
    public final JsonToken nextToken() throws IOException {
        JsonToken t2;
        boolean inObject;
        int i2;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nextAfterName();
        }
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            this._skipString();
        }
        if ((i2 = this._skipWSOrEnd()) < 0) {
            this.close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (i2 == 93 || i2 == 125) {
            this._closeScope(i2);
            return this._currToken;
        }
        if (this._parsingContext.expectComma()) {
            i2 = this._skipComma(i2);
            if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (i2 == 93 || i2 == 125)) {
                this._closeScope(i2);
                return this._currToken;
            }
        }
        if (inObject = this._parsingContext.inObject()) {
            this._updateNameLocation();
            String name = i2 == 34 ? this._parseName() : this._handleOddName(i2);
            this._parsingContext.setCurrentName(name);
            this._currToken = JsonToken.FIELD_NAME;
            i2 = this._skipColon();
        }
        this._updateLocation();
        switch (i2) {
            case 34: {
                this._tokenIncomplete = true;
                t2 = JsonToken.VALUE_STRING;
                break;
            }
            case 91: {
                if (!inObject) {
                    this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                }
                t2 = JsonToken.START_ARRAY;
                break;
            }
            case 123: {
                if (!inObject) {
                    this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                }
                t2 = JsonToken.START_OBJECT;
                break;
            }
            case 125: {
                this._reportUnexpectedChar(i2, "expected a value");
            }
            case 116: {
                this._matchTrue();
                t2 = JsonToken.VALUE_TRUE;
                break;
            }
            case 102: {
                this._matchFalse();
                t2 = JsonToken.VALUE_FALSE;
                break;
            }
            case 110: {
                this._matchNull();
                t2 = JsonToken.VALUE_NULL;
                break;
            }
            case 45: {
                t2 = this._parseNegNumber();
                break;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                t2 = this._parsePosNumber(i2);
                break;
            }
            default: {
                t2 = this._handleOddValue(i2);
            }
        }
        if (inObject) {
            this._nextToken = t2;
            return this._currToken;
        }
        this._currToken = t2;
        return t2;
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken t2 = this._nextToken;
        this._nextToken = null;
        if (t2 == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (t2 == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = t2;
        return this._currToken;
    }

    @Override
    public void finishToken() throws IOException {
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            this._finishString();
        }
    }

    @Override
    public boolean nextFieldName(SerializableString sstr) throws IOException {
        int end;
        char[] nameChars;
        int len;
        int i2;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            this._skipString();
        }
        if ((i2 = this._skipWSOrEnd()) < 0) {
            this.close();
            this._currToken = null;
            return false;
        }
        this._binaryValue = null;
        if (i2 == 93 || i2 == 125) {
            this._closeScope(i2);
            return false;
        }
        if (this._parsingContext.expectComma()) {
            i2 = this._skipComma(i2);
            if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (i2 == 93 || i2 == 125)) {
                this._closeScope(i2);
                return false;
            }
        }
        if (!this._parsingContext.inObject()) {
            this._updateLocation();
            this._nextTokenNotInObject(i2);
            return false;
        }
        this._updateNameLocation();
        if (i2 == 34 && this._inputPtr + (len = (nameChars = sstr.asQuotedChars()).length) + 4 < this._inputEnd && this._inputBuffer[end = this._inputPtr + len] == '\"') {
            int offset = 0;
            int ptr = this._inputPtr;
            while (true) {
                if (ptr == end) {
                    this._parsingContext.setCurrentName(sstr.getValue());
                    this._isNextTokenNameYes(this._skipColonFast(ptr + 1));
                    return true;
                }
                if (nameChars[offset] != this._inputBuffer[ptr]) break;
                ++offset;
                ++ptr;
            }
        }
        return this._isNextTokenNameMaybe(i2, sstr.getValue());
    }

    @Override
    public String nextFieldName() throws IOException {
        JsonToken t2;
        int i2;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nextAfterName();
            return null;
        }
        if (this._tokenIncomplete) {
            this._skipString();
        }
        if ((i2 = this._skipWSOrEnd()) < 0) {
            this.close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (i2 == 93 || i2 == 125) {
            this._closeScope(i2);
            return null;
        }
        if (this._parsingContext.expectComma()) {
            i2 = this._skipComma(i2);
            if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (i2 == 93 || i2 == 125)) {
                this._closeScope(i2);
                return null;
            }
        }
        if (!this._parsingContext.inObject()) {
            this._updateLocation();
            this._nextTokenNotInObject(i2);
            return null;
        }
        this._updateNameLocation();
        String name = i2 == 34 ? this._parseName() : this._handleOddName(i2);
        this._parsingContext.setCurrentName(name);
        this._currToken = JsonToken.FIELD_NAME;
        i2 = this._skipColon();
        this._updateLocation();
        if (i2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return name;
        }
        switch (i2) {
            case 45: {
                t2 = this._parseNegNumber();
                break;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                t2 = this._parsePosNumber(i2);
                break;
            }
            case 102: {
                this._matchFalse();
                t2 = JsonToken.VALUE_FALSE;
                break;
            }
            case 110: {
                this._matchNull();
                t2 = JsonToken.VALUE_NULL;
                break;
            }
            case 116: {
                this._matchTrue();
                t2 = JsonToken.VALUE_TRUE;
                break;
            }
            case 91: {
                t2 = JsonToken.START_ARRAY;
                break;
            }
            case 123: {
                t2 = JsonToken.START_OBJECT;
                break;
            }
            default: {
                t2 = this._handleOddValue(i2);
            }
        }
        this._nextToken = t2;
        return name;
    }

    private final void _isNextTokenNameYes(int i2) throws IOException {
        this._currToken = JsonToken.FIELD_NAME;
        this._updateLocation();
        switch (i2) {
            case 34: {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return;
            }
            case 91: {
                this._nextToken = JsonToken.START_ARRAY;
                return;
            }
            case 123: {
                this._nextToken = JsonToken.START_OBJECT;
                return;
            }
            case 116: {
                this._matchToken("true", 1);
                this._nextToken = JsonToken.VALUE_TRUE;
                return;
            }
            case 102: {
                this._matchToken("false", 1);
                this._nextToken = JsonToken.VALUE_FALSE;
                return;
            }
            case 110: {
                this._matchToken("null", 1);
                this._nextToken = JsonToken.VALUE_NULL;
                return;
            }
            case 45: {
                this._nextToken = this._parseNegNumber();
                return;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                this._nextToken = this._parsePosNumber(i2);
                return;
            }
        }
        this._nextToken = this._handleOddValue(i2);
    }

    protected boolean _isNextTokenNameMaybe(int i2, String nameToMatch) throws IOException {
        JsonToken t2;
        String name = i2 == 34 ? this._parseName() : this._handleOddName(i2);
        this._parsingContext.setCurrentName(name);
        this._currToken = JsonToken.FIELD_NAME;
        i2 = this._skipColon();
        this._updateLocation();
        if (i2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return nameToMatch.equals(name);
        }
        switch (i2) {
            case 45: {
                t2 = this._parseNegNumber();
                break;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                t2 = this._parsePosNumber(i2);
                break;
            }
            case 102: {
                this._matchFalse();
                t2 = JsonToken.VALUE_FALSE;
                break;
            }
            case 110: {
                this._matchNull();
                t2 = JsonToken.VALUE_NULL;
                break;
            }
            case 116: {
                this._matchTrue();
                t2 = JsonToken.VALUE_TRUE;
                break;
            }
            case 91: {
                t2 = JsonToken.START_ARRAY;
                break;
            }
            case 123: {
                t2 = JsonToken.START_OBJECT;
                break;
            }
            default: {
                t2 = this._handleOddValue(i2);
            }
        }
        this._nextToken = t2;
        return nameToMatch.equals(name);
    }

    private final JsonToken _nextTokenNotInObject(int i2) throws IOException {
        if (i2 == 34) {
            this._tokenIncomplete = true;
            this._currToken = JsonToken.VALUE_STRING;
            return this._currToken;
        }
        switch (i2) {
            case 91: {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                this._currToken = JsonToken.START_ARRAY;
                return this._currToken;
            }
            case 123: {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                this._currToken = JsonToken.START_OBJECT;
                return this._currToken;
            }
            case 116: {
                this._matchToken("true", 1);
                this._currToken = JsonToken.VALUE_TRUE;
                return this._currToken;
            }
            case 102: {
                this._matchToken("false", 1);
                this._currToken = JsonToken.VALUE_FALSE;
                return this._currToken;
            }
            case 110: {
                this._matchToken("null", 1);
                this._currToken = JsonToken.VALUE_NULL;
                return this._currToken;
            }
            case 45: {
                this._currToken = this._parseNegNumber();
                return this._currToken;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                this._currToken = this._parsePosNumber(i2);
                return this._currToken;
            }
            case 44: 
            case 93: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_MISSING_VALUES)) break;
                --this._inputPtr;
                this._currToken = JsonToken.VALUE_NULL;
                return this._currToken;
            }
        }
        this._currToken = this._handleOddValue(i2);
        return this._currToken;
    }

    @Override
    public final String nextTextValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t2 = this._nextToken;
            this._nextToken = null;
            this._currToken = t2;
            if (t2 == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    this._finishString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (t2 == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (t2 == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        return this.nextToken() == JsonToken.VALUE_STRING ? this.getText() : null;
    }

    @Override
    public final int nextIntValue(int defaultValue) throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t2 = this._nextToken;
            this._nextToken = null;
            this._currToken = t2;
            if (t2 == JsonToken.VALUE_NUMBER_INT) {
                return this.getIntValue();
            }
            if (t2 == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (t2 == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return defaultValue;
        }
        return this.nextToken() == JsonToken.VALUE_NUMBER_INT ? this.getIntValue() : defaultValue;
    }

    @Override
    public final long nextLongValue(long defaultValue) throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t2 = this._nextToken;
            this._nextToken = null;
            this._currToken = t2;
            if (t2 == JsonToken.VALUE_NUMBER_INT) {
                return this.getLongValue();
            }
            if (t2 == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (t2 == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return defaultValue;
        }
        return this.nextToken() == JsonToken.VALUE_NUMBER_INT ? this.getLongValue() : defaultValue;
    }

    @Override
    public final Boolean nextBooleanValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t2 = this._nextToken;
            this._nextToken = null;
            this._currToken = t2;
            if (t2 == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (t2 == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (t2 == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (t2 == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        JsonToken t3 = this.nextToken();
        if (t3 != null) {
            int id = t3.id();
            if (id == 9) {
                return Boolean.TRUE;
            }
            if (id == 10) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    protected final JsonToken _parsePosNumber(int ch2) throws IOException {
        int ptr = this._inputPtr;
        int startPtr = ptr - 1;
        int inputLen = this._inputEnd;
        if (ch2 == 48) {
            return this._parseNumber2(false, startPtr);
        }
        int intLen = 1;
        while (true) {
            if (ptr >= inputLen) {
                this._inputPtr = startPtr;
                return this._parseNumber2(false, startPtr);
            }
            if ((ch2 = this._inputBuffer[ptr++]) < 48 || ch2 > 57) break;
            ++intLen;
        }
        if (ch2 == 46 || ch2 == 101 || ch2 == 69) {
            this._inputPtr = ptr;
            return this._parseFloat(ch2, startPtr, ptr, false, intLen);
        }
        this._inputPtr = --ptr;
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace(ch2);
        }
        int len = ptr - startPtr;
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, len);
        return this.resetInt(false, intLen);
    }

    private final JsonToken _parseFloat(int ch2, int startPtr, int ptr, boolean neg, int intLen) throws IOException {
        int inputLen = this._inputEnd;
        int fractLen = 0;
        if (ch2 == 46) {
            while (true) {
                if (ptr >= inputLen) {
                    return this._parseNumber2(neg, startPtr);
                }
                if ((ch2 = this._inputBuffer[ptr++]) < 48 || ch2 > 57) break;
                ++fractLen;
            }
            if (fractLen == 0) {
                this.reportUnexpectedNumberChar(ch2, "Decimal point not followed by a digit");
            }
        }
        int expLen = 0;
        if (ch2 == 101 || ch2 == 69) {
            if (ptr >= inputLen) {
                this._inputPtr = startPtr;
                return this._parseNumber2(neg, startPtr);
            }
            if ((ch2 = this._inputBuffer[ptr++]) == 45 || ch2 == 43) {
                if (ptr >= inputLen) {
                    this._inputPtr = startPtr;
                    return this._parseNumber2(neg, startPtr);
                }
                ch2 = this._inputBuffer[ptr++];
            }
            while (ch2 <= 57 && ch2 >= 48) {
                ++expLen;
                if (ptr >= inputLen) {
                    this._inputPtr = startPtr;
                    return this._parseNumber2(neg, startPtr);
                }
                ch2 = this._inputBuffer[ptr++];
            }
            if (expLen == 0) {
                this.reportUnexpectedNumberChar(ch2, "Exponent indicator not followed by a digit");
            }
        }
        this._inputPtr = --ptr;
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace(ch2);
        }
        int len = ptr - startPtr;
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, len);
        return this.resetFloat(neg, intLen, fractLen, expLen);
    }

    protected final JsonToken _parseNegNumber() throws IOException {
        char ch2;
        int ptr = this._inputPtr;
        int startPtr = ptr - 1;
        int inputLen = this._inputEnd;
        if (ptr >= inputLen) {
            return this._parseNumber2(true, startPtr);
        }
        if ((ch2 = this._inputBuffer[ptr++]) > '9' || ch2 < '0') {
            this._inputPtr = ptr;
            return this._handleInvalidNumberStart(ch2, true);
        }
        if (ch2 == '0') {
            return this._parseNumber2(true, startPtr);
        }
        int intLen = 1;
        while (true) {
            if (ptr >= inputLen) {
                return this._parseNumber2(true, startPtr);
            }
            if ((ch2 = this._inputBuffer[ptr++]) < '0' || ch2 > '9') break;
            ++intLen;
        }
        if (ch2 == '.' || ch2 == 'e' || ch2 == 'E') {
            this._inputPtr = ptr;
            return this._parseFloat(ch2, startPtr, ptr, true, intLen);
        }
        this._inputPtr = --ptr;
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace(ch2);
        }
        int len = ptr - startPtr;
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, len);
        return this.resetInt(true, intLen);
    }

    private final JsonToken _parseNumber2(boolean neg, int startPtr) throws IOException {
        char c10;
        this._inputPtr = neg ? startPtr + 1 : startPtr;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int outPtr = 0;
        if (neg) {
            outBuf[outPtr++] = 45;
        }
        int intLen = 0;
        char c11 = c10 = this._inputPtr < this._inputEnd ? this._inputBuffer[this._inputPtr++] : this.getNextChar("No digit following minus sign", JsonToken.VALUE_NUMBER_INT);
        if (c10 == '0') {
            c10 = this._verifyNoLeadingZeroes();
        }
        boolean eof = false;
        while (c10 >= '0' && c10 <= '9') {
            ++intLen;
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c10;
            if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                c10 = '\u0000';
                eof = true;
                break;
            }
            c10 = this._inputBuffer[this._inputPtr++];
        }
        if (intLen == 0) {
            return this._handleInvalidNumberStart(c10, neg);
        }
        int fractLen = 0;
        if (c10 == '.') {
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c10;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                    eof = true;
                    break;
                }
                if ((c10 = this._inputBuffer[this._inputPtr++]) < '0' || c10 > '9') break;
                ++fractLen;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = c10;
            }
            if (fractLen == 0) {
                this.reportUnexpectedNumberChar(c10, "Decimal point not followed by a digit");
            }
        }
        int expLen = 0;
        if (c10 == 'e' || c10 == 'E') {
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c10;
            char c12 = c10 = this._inputPtr < this._inputEnd ? this._inputBuffer[this._inputPtr++] : this.getNextChar("expected a digit for number exponent");
            if (c10 == '-' || c10 == '+') {
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = c10;
                char c13 = c10 = this._inputPtr < this._inputEnd ? this._inputBuffer[this._inputPtr++] : this.getNextChar("expected a digit for number exponent");
            }
            while (c10 <= '9' && c10 >= '0') {
                ++expLen;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = c10;
                if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                    eof = true;
                    break;
                }
                c10 = this._inputBuffer[this._inputPtr++];
            }
            if (expLen == 0) {
                this.reportUnexpectedNumberChar(c10, "Exponent indicator not followed by a digit");
            }
        }
        if (!eof) {
            --this._inputPtr;
            if (this._parsingContext.inRoot()) {
                this._verifyRootSpace(c10);
            }
        }
        this._textBuffer.setCurrentLength(outPtr);
        return this.reset(neg, intLen, fractLen, expLen);
    }

    private final char _verifyNoLeadingZeroes() throws IOException {
        char ch2;
        if (this._inputPtr < this._inputEnd && ((ch2 = this._inputBuffer[this._inputPtr]) < '0' || ch2 > '9')) {
            return '0';
        }
        return this._verifyNLZ2();
    }

    private char _verifyNLZ2() throws IOException {
        if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
            return '0';
        }
        char ch2 = this._inputBuffer[this._inputPtr];
        if (ch2 < '0' || ch2 > '9') {
            return '0';
        }
        if (!this.isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            this.reportInvalidNumber("Leading zeroes not allowed");
        }
        ++this._inputPtr;
        if (ch2 == '0') {
            while (this._inputPtr < this._inputEnd || this._loadMore()) {
                ch2 = this._inputBuffer[this._inputPtr];
                if (ch2 < '0' || ch2 > '9') {
                    return '0';
                }
                ++this._inputPtr;
                if (ch2 == '0') continue;
                break;
            }
        }
        return ch2;
    }

    protected JsonToken _handleInvalidNumberStart(int ch2, boolean negative) throws IOException {
        if (ch2 == 73) {
            if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                this._reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_INT);
            }
            if ((ch2 = this._inputBuffer[this._inputPtr++]) == 78) {
                String match = negative ? "-INF" : "+INF";
                this._matchToken(match, 3);
                if (this.isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN(match, negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                this._reportError("Non-standard token '" + match + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (ch2 == 110) {
                String match = negative ? "-Infinity" : "+Infinity";
                this._matchToken(match, 3);
                if (this.isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN(match, negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                this._reportError("Non-standard token '" + match + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        }
        this.reportUnexpectedNumberChar(ch2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    private final void _verifyRootSpace(int ch2) throws IOException {
        ++this._inputPtr;
        switch (ch2) {
            case 9: 
            case 32: {
                return;
            }
            case 13: {
                this._skipCR();
                return;
            }
            case 10: {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                return;
            }
        }
        this._reportMissingRootWS(ch2);
    }

    protected final String _parseName() throws IOException {
        int ptr;
        int hash = this._hashSeed;
        int[] codes = _icLatin1;
        for (ptr = this._inputPtr; ptr < this._inputEnd; ++ptr) {
            char ch2 = this._inputBuffer[ptr];
            if (ch2 < codes.length && codes[ch2] != 0) {
                if (ch2 != '\"') break;
                int start = this._inputPtr;
                this._inputPtr = ptr + 1;
                return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
            }
            hash = hash * 33 + ch2;
        }
        int start = this._inputPtr;
        this._inputPtr = ptr;
        return this._parseName2(start, hash, 34);
    }

    private String _parseName2(int startPtr, int hash, int endChar) throws IOException {
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, this._inputPtr - startPtr);
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            char c10;
            char i2;
            if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            if ((i2 = (c10 = this._inputBuffer[this._inputPtr++])) <= '\\') {
                if (i2 == '\\') {
                    c10 = this._decodeEscaped();
                } else if (i2 <= endChar) {
                    if (i2 == endChar) break;
                    if (i2 < ' ') {
                        this._throwUnquotedSpace(i2, "name");
                    }
                }
            }
            hash = hash * 33 + c10;
            outBuf[outPtr++] = c10;
            if (outPtr < outBuf.length) continue;
            outBuf = this._textBuffer.finishCurrentSegment();
            outPtr = 0;
        }
        this._textBuffer.setCurrentLength(outPtr);
        TextBuffer tb = this._textBuffer;
        char[] buf = tb.getTextBuffer();
        int start = tb.getTextOffset();
        int len = tb.size();
        return this._symbols.findSymbol(buf, start, len, hash);
    }

    protected String _handleOddName(int i2) throws IOException {
        int[] codes;
        int maxCode;
        boolean firstOk;
        if (i2 == 39 && this.isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return this._parseAposName();
        }
        if (!this.isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            this._reportUnexpectedChar(i2, "was expecting double-quote to start field name");
        }
        if (!(firstOk = i2 < (maxCode = (codes = CharTypes.getInputCodeLatin1JsNames()).length) ? codes[i2] == 0 : Character.isJavaIdentifierPart((char)i2))) {
            this._reportUnexpectedChar(i2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int ptr = this._inputPtr;
        int hash = this._hashSeed;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            do {
                char ch2;
                if ((ch2 = this._inputBuffer[ptr]) < maxCode) {
                    if (codes[ch2] != 0) {
                        int start = this._inputPtr - 1;
                        this._inputPtr = ptr;
                        return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                    }
                } else if (!Character.isJavaIdentifierPart(ch2)) {
                    int start = this._inputPtr - 1;
                    this._inputPtr = ptr;
                    return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                }
                hash = hash * 33 + ch2;
            } while (++ptr < inputLen);
        }
        int start = this._inputPtr - 1;
        this._inputPtr = ptr;
        return this._handleOddName2(start, hash, codes);
    }

    protected String _parseAposName() throws IOException {
        int ptr = this._inputPtr;
        int hash = this._hashSeed;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            int[] codes = _icLatin1;
            int maxCode = codes.length;
            do {
                char ch2;
                if ((ch2 = this._inputBuffer[ptr]) == '\'') {
                    int start = this._inputPtr;
                    this._inputPtr = ptr + 1;
                    return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                }
                if (ch2 < maxCode && codes[ch2] != 0) break;
                hash = hash * 33 + ch2;
            } while (++ptr < inputLen);
        }
        int start = this._inputPtr;
        this._inputPtr = ptr;
        return this._parseName2(start, hash, 39);
    }

    protected JsonToken _handleOddValue(int i2) throws IOException {
        switch (i2) {
            case 39: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) break;
                return this._handleApos();
            }
            case 93: {
                if (!this._parsingContext.inArray()) break;
            }
            case 44: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_MISSING_VALUES)) break;
                --this._inputPtr;
                return JsonToken.VALUE_NULL;
            }
            case 78: {
                this._matchToken("NaN", 1);
                if (this.isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN("NaN", Double.NaN);
                }
                this._reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                break;
            }
            case 73: {
                this._matchToken("Infinity", 1);
                if (this.isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return this.resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
                }
                this._reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                break;
            }
            case 43: {
                if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                    this._reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_INT);
                }
                return this._handleInvalidNumberStart(this._inputBuffer[this._inputPtr++], false);
            }
        }
        if (Character.isJavaIdentifierStart(i2)) {
            this._reportInvalidToken("" + (char)i2, "('true', 'false' or 'null')");
        }
        this._reportUnexpectedChar(i2, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected JsonToken _handleApos() throws IOException {
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            char c10;
            char i2;
            if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
            }
            if ((i2 = (c10 = this._inputBuffer[this._inputPtr++])) <= '\\') {
                if (i2 == '\\') {
                    c10 = this._decodeEscaped();
                } else if (i2 <= '\'') {
                    if (i2 == '\'') break;
                    if (i2 < ' ') {
                        this._throwUnquotedSpace(i2, "string value");
                    }
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c10;
        }
        this._textBuffer.setCurrentLength(outPtr);
        return JsonToken.VALUE_STRING;
    }

    private String _handleOddName2(int startPtr, int hash, int[] codes) throws IOException {
        char c10;
        char i2;
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, this._inputPtr - startPtr);
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        int maxCode = codes.length;
        while ((this._inputPtr < this._inputEnd || this._loadMore()) && !((i2 = (c10 = this._inputBuffer[this._inputPtr])) <= maxCode ? codes[i2] != 0 : !Character.isJavaIdentifierPart(c10))) {
            ++this._inputPtr;
            hash = hash * 33 + i2;
            outBuf[outPtr++] = c10;
            if (outPtr < outBuf.length) continue;
            outBuf = this._textBuffer.finishCurrentSegment();
            outPtr = 0;
        }
        this._textBuffer.setCurrentLength(outPtr);
        TextBuffer tb = this._textBuffer;
        char[] buf = tb.getTextBuffer();
        int start = tb.getTextOffset();
        int len = tb.size();
        return this._symbols.findSymbol(buf, start, len, hash);
    }

    @Override
    protected final void _finishString() throws IOException {
        int ptr = this._inputPtr;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            int[] codes = _icLatin1;
            int maxCode = codes.length;
            do {
                char ch2;
                if ((ch2 = this._inputBuffer[ptr]) >= maxCode || codes[ch2] == 0) continue;
                if (ch2 != '\"') break;
                this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, ptr - this._inputPtr);
                this._inputPtr = ptr + 1;
                return;
            } while (++ptr < inputLen);
        }
        this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, ptr - this._inputPtr);
        this._inputPtr = ptr;
        this._finishString2();
    }

    protected void _finishString2() throws IOException {
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        int[] codes = _icLatin1;
        int maxCode = codes.length;
        while (true) {
            char c10;
            char i2;
            if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
            }
            if ((i2 = (c10 = this._inputBuffer[this._inputPtr++])) < maxCode && codes[i2] != 0) {
                if (i2 == '\"') break;
                if (i2 == '\\') {
                    c10 = this._decodeEscaped();
                } else if (i2 < ' ') {
                    this._throwUnquotedSpace(i2, "string value");
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = c10;
        }
        this._textBuffer.setCurrentLength(outPtr);
    }

    protected final void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int inPtr = this._inputPtr;
        int inLen = this._inputEnd;
        char[] inBuf = this._inputBuffer;
        while (true) {
            char c10;
            char i2;
            if (inPtr >= inLen) {
                this._inputPtr = inPtr;
                if (!this._loadMore()) {
                    this._reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
                }
                inPtr = this._inputPtr;
                inLen = this._inputEnd;
            }
            if ((i2 = (c10 = inBuf[inPtr++])) > '\\') continue;
            if (i2 == '\\') {
                this._inputPtr = inPtr;
                this._decodeEscaped();
                inPtr = this._inputPtr;
                inLen = this._inputEnd;
                continue;
            }
            if (i2 > '\"') continue;
            if (i2 == '\"') break;
            if (i2 >= ' ') continue;
            this._inputPtr = inPtr;
            this._throwUnquotedSpace(i2, "string value");
        }
        this._inputPtr = inPtr;
    }

    protected final void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || this._loadMore()) && this._inputBuffer[this._inputPtr] == '\n') {
            ++this._inputPtr;
        }
        ++this._currInputRow;
        this._currInputRowStart = this._inputPtr;
    }

    private final int _skipColon() throws IOException {
        if (this._inputPtr + 4 >= this._inputEnd) {
            return this._skipColon2(false);
        }
        char c10 = this._inputBuffer[this._inputPtr];
        if (c10 == ':') {
            char i2;
            if ((i2 = this._inputBuffer[++this._inputPtr]) > ' ') {
                if (i2 == '/' || i2 == '#') {
                    return this._skipColon2(true);
                }
                ++this._inputPtr;
                return i2;
            }
            if ((i2 == ' ' || i2 == '\t') && (i2 = this._inputBuffer[++this._inputPtr]) > ' ') {
                if (i2 == '/' || i2 == '#') {
                    return this._skipColon2(true);
                }
                ++this._inputPtr;
                return i2;
            }
            return this._skipColon2(true);
        }
        if (c10 == ' ' || c10 == '\t') {
            c10 = this._inputBuffer[++this._inputPtr];
        }
        if (c10 == ':') {
            char i3;
            if ((i3 = this._inputBuffer[++this._inputPtr]) > ' ') {
                if (i3 == '/' || i3 == '#') {
                    return this._skipColon2(true);
                }
                ++this._inputPtr;
                return i3;
            }
            if ((i3 == ' ' || i3 == '\t') && (i3 = this._inputBuffer[++this._inputPtr]) > ' ') {
                if (i3 == '/' || i3 == '#') {
                    return this._skipColon2(true);
                }
                ++this._inputPtr;
                return i3;
            }
            return this._skipColon2(true);
        }
        return this._skipColon2(false);
    }

    private final int _skipColon2(boolean gotColon) throws IOException {
        while (this._inputPtr < this._inputEnd || this._loadMore()) {
            char i2;
            if ((i2 = this._inputBuffer[this._inputPtr++]) > ' ') {
                if (i2 == '/') {
                    this._skipComment();
                    continue;
                }
                if (i2 == '#' && this._skipYAMLComment()) continue;
                if (gotColon) {
                    return i2;
                }
                if (i2 != ':') {
                    this._reportUnexpectedChar(i2, "was expecting a colon to separate field name and value");
                }
                gotColon = true;
                continue;
            }
            if (i2 >= ' ') continue;
            if (i2 == '\n') {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                continue;
            }
            if (i2 == '\r') {
                this._skipCR();
                continue;
            }
            if (i2 == '\t') continue;
            this._throwInvalidSpace(i2);
        }
        this._reportInvalidEOF(" within/between " + this._parsingContext.typeDesc() + " entries", null);
        return -1;
    }

    private final int _skipColonFast(int ptr) throws IOException {
        boolean gotColon;
        char i2;
        if ((i2 = this._inputBuffer[ptr++]) == ':') {
            if ((i2 = this._inputBuffer[ptr++]) > ' ') {
                if (i2 != '/' && i2 != '#') {
                    this._inputPtr = ptr;
                    return i2;
                }
            } else if ((i2 == ' ' || i2 == '\t') && (i2 = this._inputBuffer[ptr++]) > ' ' && i2 != '/' && i2 != '#') {
                this._inputPtr = ptr;
                return i2;
            }
            this._inputPtr = ptr - 1;
            return this._skipColon2(true);
        }
        if (i2 == ' ' || i2 == '\t') {
            i2 = this._inputBuffer[ptr++];
        }
        boolean bl2 = gotColon = i2 == ':';
        if (gotColon) {
            if ((i2 = this._inputBuffer[ptr++]) > ' ') {
                if (i2 != '/' && i2 != '#') {
                    this._inputPtr = ptr;
                    return i2;
                }
            } else if ((i2 == ' ' || i2 == '\t') && (i2 = this._inputBuffer[ptr++]) > ' ' && i2 != '/' && i2 != '#') {
                this._inputPtr = ptr;
                return i2;
            }
        }
        this._inputPtr = ptr - 1;
        return this._skipColon2(gotColon);
    }

    private final int _skipComma(int i2) throws IOException {
        if (i2 != 44) {
            this._reportUnexpectedChar(i2, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
        }
        while (this._inputPtr < this._inputEnd) {
            if ((i2 = this._inputBuffer[this._inputPtr++]) > 32) {
                if (i2 == 47 || i2 == 35) {
                    --this._inputPtr;
                    return this._skipAfterComma2();
                }
                return i2;
            }
            if (i2 >= 32) continue;
            if (i2 == 10) {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                continue;
            }
            if (i2 == 13) {
                this._skipCR();
                continue;
            }
            if (i2 == 9) continue;
            this._throwInvalidSpace(i2);
        }
        return this._skipAfterComma2();
    }

    private final int _skipAfterComma2() throws IOException {
        while (this._inputPtr < this._inputEnd || this._loadMore()) {
            char i2;
            if ((i2 = this._inputBuffer[this._inputPtr++]) > ' ') {
                if (i2 == '/') {
                    this._skipComment();
                    continue;
                }
                if (i2 == '#' && this._skipYAMLComment()) continue;
                return i2;
            }
            if (i2 >= ' ') continue;
            if (i2 == '\n') {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                continue;
            }
            if (i2 == '\r') {
                this._skipCR();
                continue;
            }
            if (i2 == '\t') continue;
            this._throwInvalidSpace(i2);
        }
        throw this._constructError("Unexpected end-of-input within/between " + this._parsingContext.typeDesc() + " entries");
    }

    private final int _skipWSOrEnd() throws IOException {
        char i2;
        if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
            return this._eofAsNextChar();
        }
        if ((i2 = this._inputBuffer[this._inputPtr++]) > ' ') {
            if (i2 == '/' || i2 == '#') {
                --this._inputPtr;
                return this._skipWSOrEnd2();
            }
            return i2;
        }
        if (i2 != ' ') {
            if (i2 == '\n') {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
            } else if (i2 == '\r') {
                this._skipCR();
            } else if (i2 != '\t') {
                this._throwInvalidSpace(i2);
            }
        }
        while (this._inputPtr < this._inputEnd) {
            if ((i2 = this._inputBuffer[this._inputPtr++]) > ' ') {
                if (i2 == '/' || i2 == '#') {
                    --this._inputPtr;
                    return this._skipWSOrEnd2();
                }
                return i2;
            }
            if (i2 == ' ') continue;
            if (i2 == '\n') {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                continue;
            }
            if (i2 == '\r') {
                this._skipCR();
                continue;
            }
            if (i2 == '\t') continue;
            this._throwInvalidSpace(i2);
        }
        return this._skipWSOrEnd2();
    }

    private int _skipWSOrEnd2() throws IOException {
        while (this._inputPtr < this._inputEnd || this._loadMore()) {
            char i2;
            if ((i2 = this._inputBuffer[this._inputPtr++]) > ' ') {
                if (i2 == '/') {
                    this._skipComment();
                    continue;
                }
                if (i2 == '#' && this._skipYAMLComment()) continue;
                return i2;
            }
            if (i2 == ' ') continue;
            if (i2 == '\n') {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                continue;
            }
            if (i2 == '\r') {
                this._skipCR();
                continue;
            }
            if (i2 == '\t') continue;
            this._throwInvalidSpace(i2);
        }
        return this._eofAsNextChar();
    }

    private void _skipComment() throws IOException {
        char c10;
        if (!this.isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(" in a comment", null);
        }
        if ((c10 = this._inputBuffer[this._inputPtr++]) == '/') {
            this._skipLine();
        } else if (c10 == '*') {
            this._skipCComment();
        } else {
            this._reportUnexpectedChar(c10, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() throws IOException {
        while (this._inputPtr < this._inputEnd || this._loadMore()) {
            char i2;
            if ((i2 = this._inputBuffer[this._inputPtr++]) > '*') continue;
            if (i2 == '*') {
                if (this._inputPtr >= this._inputEnd && !this._loadMore()) break;
                if (this._inputBuffer[this._inputPtr] != '/') continue;
                ++this._inputPtr;
                return;
            }
            if (i2 >= ' ') continue;
            if (i2 == '\n') {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                continue;
            }
            if (i2 == '\r') {
                this._skipCR();
                continue;
            }
            if (i2 == '\t') continue;
            this._throwInvalidSpace(i2);
        }
        this._reportInvalidEOF(" in a comment", null);
    }

    private boolean _skipYAMLComment() throws IOException {
        if (!this.isEnabled(JsonParser.Feature.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        this._skipLine();
        return true;
    }

    private void _skipLine() throws IOException {
        while (this._inputPtr < this._inputEnd || this._loadMore()) {
            char i2;
            if ((i2 = this._inputBuffer[this._inputPtr++]) >= ' ') continue;
            if (i2 == '\n') {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                break;
            }
            if (i2 == '\r') {
                this._skipCR();
                break;
            }
            if (i2 == '\t') continue;
            this._throwInvalidSpace(i2);
        }
    }

    @Override
    protected char _decodeEscaped() throws IOException {
        if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        char c10 = this._inputBuffer[this._inputPtr++];
        switch (c10) {
            case 'b': {
                return '\b';
            }
            case 't': {
                return '\t';
            }
            case 'n': {
                return '\n';
            }
            case 'f': {
                return '\f';
            }
            case 'r': {
                return '\r';
            }
            case '\"': 
            case '/': 
            case '\\': {
                return c10;
            }
            case 'u': {
                break;
            }
            default: {
                return this._handleUnrecognizedCharacterEscape(c10);
            }
        }
        int value = 0;
        for (int i2 = 0; i2 < 4; ++i2) {
            char ch2;
            int digit;
            if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
            }
            if ((digit = CharTypes.charToHex(ch2 = this._inputBuffer[this._inputPtr++])) < 0) {
                this._reportUnexpectedChar(ch2, "expected a hex-digit for character escape sequence");
            }
            value = value << 4 | digit;
        }
        return (char)value;
    }

    private final void _matchTrue() throws IOException {
        char c10;
        char[] b10;
        int ptr = this._inputPtr;
        if (ptr + 3 < this._inputEnd && (b10 = this._inputBuffer)[ptr] == 'r' && b10[++ptr] == 'u' && b10[++ptr] == 'e' && ((c10 = b10[++ptr]) < '0' || c10 == ']' || c10 == '}')) {
            this._inputPtr = ptr;
            return;
        }
        this._matchToken("true", 1);
    }

    private final void _matchFalse() throws IOException {
        char c10;
        char[] b10;
        int ptr = this._inputPtr;
        if (ptr + 4 < this._inputEnd && (b10 = this._inputBuffer)[ptr] == 'a' && b10[++ptr] == 'l' && b10[++ptr] == 's' && b10[++ptr] == 'e' && ((c10 = b10[++ptr]) < '0' || c10 == ']' || c10 == '}')) {
            this._inputPtr = ptr;
            return;
        }
        this._matchToken("false", 1);
    }

    private final void _matchNull() throws IOException {
        char c10;
        char[] b10;
        int ptr = this._inputPtr;
        if (ptr + 3 < this._inputEnd && (b10 = this._inputBuffer)[ptr] == 'u' && b10[++ptr] == 'l' && b10[++ptr] == 'l' && ((c10 = b10[++ptr]) < '0' || c10 == ']' || c10 == '}')) {
            this._inputPtr = ptr;
            return;
        }
        this._matchToken("null", 1);
    }

    protected final void _matchToken(String matchStr, int i2) throws IOException {
        int len = matchStr.length();
        if (this._inputPtr + len >= this._inputEnd) {
            this._matchToken2(matchStr, i2);
            return;
        }
        do {
            if (this._inputBuffer[this._inputPtr] != matchStr.charAt(i2)) {
                this._reportInvalidToken(matchStr.substring(0, i2));
            }
            ++this._inputPtr;
        } while (++i2 < len);
        char ch2 = this._inputBuffer[this._inputPtr];
        if (ch2 >= '0' && ch2 != ']' && ch2 != '}') {
            this._checkMatchEnd(matchStr, i2, ch2);
        }
    }

    private final void _matchToken2(String matchStr, int i2) throws IOException {
        int len = matchStr.length();
        do {
            if (this._inputPtr >= this._inputEnd && !this._loadMore() || this._inputBuffer[this._inputPtr] != matchStr.charAt(i2)) {
                this._reportInvalidToken(matchStr.substring(0, i2));
            }
            ++this._inputPtr;
        } while (++i2 < len);
        if (this._inputPtr >= this._inputEnd && !this._loadMore()) {
            return;
        }
        char ch2 = this._inputBuffer[this._inputPtr];
        if (ch2 >= '0' && ch2 != ']' && ch2 != '}') {
            this._checkMatchEnd(matchStr, i2, ch2);
        }
    }

    private final void _checkMatchEnd(String matchStr, int i2, int c10) throws IOException {
        char ch2 = (char)c10;
        if (Character.isJavaIdentifierPart(ch2)) {
            this._reportInvalidToken(matchStr.substring(0, i2));
        }
    }

    protected byte[] _decodeBase64(Base64Variant b64variant) throws IOException {
        ByteArrayBuilder builder = this._getByteArrayBuilder();
        while (true) {
            char ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._loadMoreGuaranteed();
            }
            if ((ch2 = this._inputBuffer[this._inputPtr++]) <= ' ') continue;
            int bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                if (ch2 == '\"') {
                    return builder.toByteArray();
                }
                bits = this._decodeBase64Escape(b64variant, ch2, 0);
                if (bits < 0) continue;
            }
            int decodedData = bits;
            if (this._inputPtr >= this._inputEnd) {
                this._loadMoreGuaranteed();
            }
            if ((bits = b64variant.decodeBase64Char(ch2 = this._inputBuffer[this._inputPtr++])) < 0) {
                bits = this._decodeBase64Escape(b64variant, ch2, 1);
            }
            decodedData = decodedData << 6 | bits;
            if (this._inputPtr >= this._inputEnd) {
                this._loadMoreGuaranteed();
            }
            if ((bits = b64variant.decodeBase64Char(ch2 = this._inputBuffer[this._inputPtr++])) < 0) {
                if (bits != -2) {
                    if (ch2 == '\"' && !b64variant.usesPadding()) {
                        builder.append(decodedData >>= 4);
                        return builder.toByteArray();
                    }
                    bits = this._decodeBase64Escape(b64variant, ch2, 2);
                }
                if (bits == -2) {
                    if (this._inputPtr >= this._inputEnd) {
                        this._loadMoreGuaranteed();
                    }
                    if (!b64variant.usesPaddingChar(ch2 = this._inputBuffer[this._inputPtr++])) {
                        throw this.reportInvalidBase64Char(b64variant, ch2, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                    }
                    builder.append(decodedData >>= 4);
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits;
            if (this._inputPtr >= this._inputEnd) {
                this._loadMoreGuaranteed();
            }
            if ((bits = b64variant.decodeBase64Char(ch2 = this._inputBuffer[this._inputPtr++])) < 0) {
                if (bits != -2) {
                    if (ch2 == '\"' && !b64variant.usesPadding()) {
                        builder.appendTwoBytes(decodedData >>= 2);
                        return builder.toByteArray();
                    }
                    bits = this._decodeBase64Escape(b64variant, ch2, 3);
                }
                if (bits == -2) {
                    builder.appendTwoBytes(decodedData >>= 2);
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits;
            builder.appendThreeBytes(decodedData);
        }
    }

    @Override
    public JsonLocation getTokenLocation() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            long total = this._currInputProcessed + (this._nameStartOffset - 1L);
            return new JsonLocation(this._getSourceReference(), -1L, total, this._nameStartRow, this._nameStartCol);
        }
        return new JsonLocation(this._getSourceReference(), -1L, this._tokenInputTotal - 1L, this._tokenInputRow, this._tokenInputCol);
    }

    @Override
    public JsonLocation getCurrentLocation() {
        int col = this._inputPtr - this._currInputRowStart + 1;
        return new JsonLocation(this._getSourceReference(), -1L, this._currInputProcessed + (long)this._inputPtr, this._currInputRow, col);
    }

    private final void _updateLocation() {
        int ptr = this._inputPtr;
        this._tokenInputTotal = this._currInputProcessed + (long)ptr;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = ptr - this._currInputRowStart;
    }

    private final void _updateNameLocation() {
        int ptr = this._inputPtr;
        this._nameStartOffset = ptr;
        this._nameStartRow = this._currInputRow;
        this._nameStartCol = ptr - this._currInputRowStart;
    }

    protected void _reportInvalidToken(String matchedPart) throws IOException {
        this._reportInvalidToken(matchedPart, "'null', 'true', 'false' or NaN");
    }

    protected void _reportInvalidToken(String matchedPart, String msg) throws IOException {
        char c10;
        StringBuilder sb = new StringBuilder(matchedPart);
        while ((this._inputPtr < this._inputEnd || this._loadMore()) && Character.isJavaIdentifierPart(c10 = this._inputBuffer[this._inputPtr])) {
            ++this._inputPtr;
            sb.append(c10);
            if (sb.length() < 256) continue;
            sb.append("...");
            break;
        }
        this._reportError("Unrecognized token '%s': was expecting %s", sb, msg);
    }

    private void _closeScope(int i2) throws JsonParseException {
        if (i2 == 93) {
            this._updateLocation();
            if (!this._parsingContext.inArray()) {
                this._reportMismatchedEndMarker(i2, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
        }
        if (i2 == 125) {
            this._updateLocation();
            if (!this._parsingContext.inObject()) {
                this._reportMismatchedEndMarker(i2, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
        }
    }
}

