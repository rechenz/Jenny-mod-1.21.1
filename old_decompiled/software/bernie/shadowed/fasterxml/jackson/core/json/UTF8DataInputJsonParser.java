/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.json;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.JsonLocation;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParseException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;
import software.bernie.shadowed.fasterxml.jackson.core.base.ParserBase;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharTypes;
import software.bernie.shadowed.fasterxml.jackson.core.io.IOContext;
import software.bernie.shadowed.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import software.bernie.shadowed.fasterxml.jackson.core.util.ByteArrayBuilder;

public class UTF8DataInputJsonParser
extends ParserBase {
    static final byte BYTE_LF = 10;
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    protected ObjectCodec _objectCodec;
    protected final ByteQuadsCanonicalizer _symbols;
    protected int[] _quadBuffer = new int[16];
    protected boolean _tokenIncomplete;
    private int _quad1;
    protected DataInput _inputData;
    protected int _nextByte = -1;

    public UTF8DataInputJsonParser(IOContext ctxt, int features, DataInput inputData, ObjectCodec codec, ByteQuadsCanonicalizer sym, int firstByte) {
        super(ctxt, features);
        this._objectCodec = codec;
        this._symbols = sym;
        this._inputData = inputData;
        this._nextByte = firstByte;
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
    public int releaseBuffered(OutputStream out) throws IOException {
        return 0;
    }

    @Override
    public Object getInputSource() {
        return this._inputData;
    }

    @Override
    protected void _closeInput() throws IOException {
    }

    @Override
    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        this._symbols.release();
    }

    @Override
    public String getText() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return this._finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        return this._getText2(this._currToken);
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
    public String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return this._finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        return super.getValueAsString(null);
    }

    @Override
    public String getValueAsString(String defValue) throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return this._finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        return super.getValueAsString(defValue);
    }

    @Override
    public int getValueAsInt() throws IOException {
        JsonToken t2 = this._currToken;
        if (t2 == JsonToken.VALUE_NUMBER_INT || t2 == JsonToken.VALUE_NUMBER_FLOAT) {
            if ((this._numTypesValid & 1) == 0) {
                if (this._numTypesValid == 0) {
                    return this._parseIntValue();
                }
                if ((this._numTypesValid & 1) == 0) {
                    this.convertNumberToInt();
                }
            }
            return this._numberInt;
        }
        return super.getValueAsInt(0);
    }

    @Override
    public int getValueAsInt(int defValue) throws IOException {
        JsonToken t2 = this._currToken;
        if (t2 == JsonToken.VALUE_NUMBER_INT || t2 == JsonToken.VALUE_NUMBER_FLOAT) {
            if ((this._numTypesValid & 1) == 0) {
                if (this._numTypesValid == 0) {
                    return this._parseIntValue();
                }
                if ((this._numTypesValid & 1) == 0) {
                    this.convertNumberToInt();
                }
            }
            return this._numberInt;
        }
        return super.getValueAsInt(defValue);
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
    public char[] getTextCharacters() throws IOException {
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
    public int getTextLength() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                this._finishString();
            }
            return this._textBuffer.size();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName().length();
        }
        if (this._currToken != null) {
            if (this._currToken.isNumeric()) {
                return this._textBuffer.size();
            }
            return this._currToken.asCharArray().length;
        }
        return 0;
    }

    @Override
    public int getTextOffset() throws IOException {
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
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
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
            int ch2;
            if ((ch2 = this._inputData.readUnsignedByte()) <= 32) {
                continue;
            }
            int bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                if (ch2 == 34) break;
                bits = this._decodeBase64Escape(b64variant, ch2, 0);
                if (bits < 0) continue;
            }
            if (outputPtr > outputEnd) {
                outputCount += outputPtr;
                out.write(buffer, 0, outputPtr);
                outputPtr = 0;
            }
            int decodedData = bits;
            ch2 = this._inputData.readUnsignedByte();
            bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                bits = this._decodeBase64Escape(b64variant, ch2, 1);
            }
            decodedData = decodedData << 6 | bits;
            ch2 = this._inputData.readUnsignedByte();
            bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                if (bits != -2) {
                    if (ch2 == 34 && !b64variant.usesPadding()) {
                        buffer[outputPtr++] = (byte)(decodedData >>= 4);
                        break;
                    }
                    bits = this._decodeBase64Escape(b64variant, ch2, 2);
                }
                if (bits == -2) {
                    ch2 = this._inputData.readUnsignedByte();
                    if (!b64variant.usesPaddingChar(ch2)) {
                        throw this.reportInvalidBase64Char(b64variant, ch2, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                    }
                    buffer[outputPtr++] = (byte)(decodedData >>= 4);
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits;
            ch2 = this._inputData.readUnsignedByte();
            bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                if (bits != -2) {
                    if (ch2 == 34 && !b64variant.usesPadding()) {
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
    public JsonToken nextToken() throws IOException {
        JsonToken t2;
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
        this._tokenInputRow = this._currInputRow;
        if (i2 == 93 || i2 == 125) {
            this._closeScope(i2);
            return this._currToken;
        }
        if (this._parsingContext.expectComma()) {
            if (i2 != 44) {
                this._reportUnexpectedChar(i2, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            i2 = this._skipWS();
            if (JsonParser.Feature.ALLOW_TRAILING_COMMA.enabledIn(this._features) && (i2 == 93 || i2 == 125)) {
                this._closeScope(i2);
                return this._currToken;
            }
        }
        if (!this._parsingContext.inObject()) {
            return this._nextTokenNotInObject(i2);
        }
        String n2 = this._parseName(i2);
        this._parsingContext.setCurrentName(n2);
        this._currToken = JsonToken.FIELD_NAME;
        i2 = this._skipColon();
        if (i2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return this._currToken;
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
                this._matchToken("false", 1);
                t2 = JsonToken.VALUE_FALSE;
                break;
            }
            case 110: {
                this._matchToken("null", 1);
                t2 = JsonToken.VALUE_NULL;
                break;
            }
            case 116: {
                this._matchToken("true", 1);
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
                t2 = this._handleUnexpectedValue(i2);
            }
        }
        this._nextToken = t2;
        return this._currToken;
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
        }
        this._currToken = this._handleUnexpectedValue(i2);
        return this._currToken;
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
    public String nextFieldName() throws IOException {
        JsonToken t2;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nextAfterName();
            return null;
        }
        if (this._tokenIncomplete) {
            this._skipString();
        }
        int i2 = this._skipWS();
        this._binaryValue = null;
        this._tokenInputRow = this._currInputRow;
        if (i2 == 93 || i2 == 125) {
            this._closeScope(i2);
            return null;
        }
        if (this._parsingContext.expectComma()) {
            if (i2 != 44) {
                this._reportUnexpectedChar(i2, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            i2 = this._skipWS();
            if (JsonParser.Feature.ALLOW_TRAILING_COMMA.enabledIn(this._features) && (i2 == 93 || i2 == 125)) {
                this._closeScope(i2);
                return null;
            }
        }
        if (!this._parsingContext.inObject()) {
            this._nextTokenNotInObject(i2);
            return null;
        }
        String nameStr = this._parseName(i2);
        this._parsingContext.setCurrentName(nameStr);
        this._currToken = JsonToken.FIELD_NAME;
        i2 = this._skipColon();
        if (i2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return nameStr;
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
                this._matchToken("false", 1);
                t2 = JsonToken.VALUE_FALSE;
                break;
            }
            case 110: {
                this._matchToken("null", 1);
                t2 = JsonToken.VALUE_NULL;
                break;
            }
            case 116: {
                this._matchToken("true", 1);
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
                t2 = this._handleUnexpectedValue(i2);
            }
        }
        this._nextToken = t2;
        return nameStr;
    }

    @Override
    public String nextTextValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t2 = this._nextToken;
            this._nextToken = null;
            this._currToken = t2;
            if (t2 == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    return this._finishAndReturnString();
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
    public int nextIntValue(int defaultValue) throws IOException {
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
    public long nextLongValue(long defaultValue) throws IOException {
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
    public Boolean nextBooleanValue() throws IOException {
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
        if (t3 == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (t3 == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        return null;
    }

    protected JsonToken _parsePosNumber(int c10) throws IOException {
        int outPtr;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        if (c10 == 48) {
            c10 = this._handleLeadingZeroes();
            if (c10 <= 57 && c10 >= 48) {
                outPtr = 0;
            } else {
                outBuf[0] = 48;
                outPtr = 1;
            }
        } else {
            outBuf[0] = (char)c10;
            c10 = this._inputData.readUnsignedByte();
            outPtr = 1;
        }
        int intLen = outPtr;
        while (c10 <= 57 && c10 >= 48) {
            ++intLen;
            outBuf[outPtr++] = (char)c10;
            c10 = this._inputData.readUnsignedByte();
        }
        if (c10 == 46 || c10 == 101 || c10 == 69) {
            return this._parseFloat(outBuf, outPtr, c10, false, intLen);
        }
        this._textBuffer.setCurrentLength(outPtr);
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace();
        } else {
            this._nextByte = c10;
        }
        return this.resetInt(false, intLen);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected JsonToken _parseNegNumber() throws IOException {
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int outPtr = 0;
        outBuf[outPtr++] = 45;
        int c10 = this._inputData.readUnsignedByte();
        outBuf[outPtr++] = (char)c10;
        if (c10 <= 48) {
            if (c10 != 48) return this._handleInvalidNumberStart(c10, true);
            c10 = this._handleLeadingZeroes();
        } else {
            if (c10 > 57) {
                return this._handleInvalidNumberStart(c10, true);
            }
            c10 = this._inputData.readUnsignedByte();
        }
        int intLen = 1;
        while (c10 <= 57 && c10 >= 48) {
            ++intLen;
            outBuf[outPtr++] = (char)c10;
            c10 = this._inputData.readUnsignedByte();
        }
        if (c10 == 46 || c10 == 101 || c10 == 69) {
            return this._parseFloat(outBuf, outPtr, c10, true, intLen);
        }
        this._textBuffer.setCurrentLength(outPtr);
        this._nextByte = c10;
        if (!this._parsingContext.inRoot()) return this.resetInt(true, intLen);
        this._verifyRootSpace();
        return this.resetInt(true, intLen);
    }

    private final int _handleLeadingZeroes() throws IOException {
        int ch2 = this._inputData.readUnsignedByte();
        if (ch2 < 48 || ch2 > 57) {
            return ch2;
        }
        if (!this.isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            this.reportInvalidNumber("Leading zeroes not allowed");
        }
        while (ch2 == 48) {
            ch2 = this._inputData.readUnsignedByte();
        }
        return ch2;
    }

    private final JsonToken _parseFloat(char[] outBuf, int outPtr, int c10, boolean negative, int integerPartLength) throws IOException {
        int fractLen = 0;
        if (c10 == 46) {
            outBuf[outPtr++] = (char)c10;
            while ((c10 = this._inputData.readUnsignedByte()) >= 48 && c10 <= 57) {
                ++fractLen;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = (char)c10;
            }
            if (fractLen == 0) {
                this.reportUnexpectedNumberChar(c10, "Decimal point not followed by a digit");
            }
        }
        int expLen = 0;
        if (c10 == 101 || c10 == 69) {
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = (char)c10;
            c10 = this._inputData.readUnsignedByte();
            if (c10 == 45 || c10 == 43) {
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = (char)c10;
                c10 = this._inputData.readUnsignedByte();
            }
            while (c10 <= 57 && c10 >= 48) {
                ++expLen;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = (char)c10;
                c10 = this._inputData.readUnsignedByte();
            }
            if (expLen == 0) {
                this.reportUnexpectedNumberChar(c10, "Exponent indicator not followed by a digit");
            }
        }
        this._nextByte = c10;
        if (this._parsingContext.inRoot()) {
            this._verifyRootSpace();
        }
        this._textBuffer.setCurrentLength(outPtr);
        return this.resetFloat(negative, integerPartLength, fractLen, expLen);
    }

    private final void _verifyRootSpace() throws IOException {
        int ch2 = this._nextByte;
        if (ch2 <= 32) {
            this._nextByte = -1;
            if (ch2 == 13 || ch2 == 10) {
                ++this._currInputRow;
            }
            return;
        }
        this._reportMissingRootWS(ch2);
    }

    protected final String _parseName(int i2) throws IOException {
        if (i2 != 34) {
            return this._handleOddName(i2);
        }
        int[] codes = _icLatin1;
        int q2 = this._inputData.readUnsignedByte();
        if (codes[q2] == 0) {
            i2 = this._inputData.readUnsignedByte();
            if (codes[i2] == 0) {
                q2 = q2 << 8 | i2;
                i2 = this._inputData.readUnsignedByte();
                if (codes[i2] == 0) {
                    q2 = q2 << 8 | i2;
                    i2 = this._inputData.readUnsignedByte();
                    if (codes[i2] == 0) {
                        q2 = q2 << 8 | i2;
                        i2 = this._inputData.readUnsignedByte();
                        if (codes[i2] == 0) {
                            this._quad1 = q2;
                            return this._parseMediumName(i2);
                        }
                        if (i2 == 34) {
                            return this.findName(q2, 4);
                        }
                        return this.parseName(q2, i2, 4);
                    }
                    if (i2 == 34) {
                        return this.findName(q2, 3);
                    }
                    return this.parseName(q2, i2, 3);
                }
                if (i2 == 34) {
                    return this.findName(q2, 2);
                }
                return this.parseName(q2, i2, 2);
            }
            if (i2 == 34) {
                return this.findName(q2, 1);
            }
            return this.parseName(q2, i2, 1);
        }
        if (q2 == 34) {
            return "";
        }
        return this.parseName(0, q2, 0);
    }

    private final String _parseMediumName(int q2) throws IOException {
        int[] codes = _icLatin1;
        int i2 = this._inputData.readUnsignedByte();
        if (codes[i2] != 0) {
            if (i2 == 34) {
                return this.findName(this._quad1, q2, 1);
            }
            return this.parseName(this._quad1, q2, i2, 1);
        }
        q2 = q2 << 8 | i2;
        i2 = this._inputData.readUnsignedByte();
        if (codes[i2] != 0) {
            if (i2 == 34) {
                return this.findName(this._quad1, q2, 2);
            }
            return this.parseName(this._quad1, q2, i2, 2);
        }
        q2 = q2 << 8 | i2;
        i2 = this._inputData.readUnsignedByte();
        if (codes[i2] != 0) {
            if (i2 == 34) {
                return this.findName(this._quad1, q2, 3);
            }
            return this.parseName(this._quad1, q2, i2, 3);
        }
        q2 = q2 << 8 | i2;
        i2 = this._inputData.readUnsignedByte();
        if (codes[i2] != 0) {
            if (i2 == 34) {
                return this.findName(this._quad1, q2, 4);
            }
            return this.parseName(this._quad1, q2, i2, 4);
        }
        return this._parseMediumName2(i2, q2);
    }

    private final String _parseMediumName2(int q3, int q2) throws IOException {
        int[] codes = _icLatin1;
        int i2 = this._inputData.readUnsignedByte();
        if (codes[i2] != 0) {
            if (i2 == 34) {
                return this.findName(this._quad1, q2, q3, 1);
            }
            return this.parseName(this._quad1, q2, q3, i2, 1);
        }
        q3 = q3 << 8 | i2;
        i2 = this._inputData.readUnsignedByte();
        if (codes[i2] != 0) {
            if (i2 == 34) {
                return this.findName(this._quad1, q2, q3, 2);
            }
            return this.parseName(this._quad1, q2, q3, i2, 2);
        }
        q3 = q3 << 8 | i2;
        i2 = this._inputData.readUnsignedByte();
        if (codes[i2] != 0) {
            if (i2 == 34) {
                return this.findName(this._quad1, q2, q3, 3);
            }
            return this.parseName(this._quad1, q2, q3, i2, 3);
        }
        q3 = q3 << 8 | i2;
        i2 = this._inputData.readUnsignedByte();
        if (codes[i2] != 0) {
            if (i2 == 34) {
                return this.findName(this._quad1, q2, q3, 4);
            }
            return this.parseName(this._quad1, q2, q3, i2, 4);
        }
        return this._parseLongName(i2, q2, q3);
    }

    private final String _parseLongName(int q2, int q22, int q3) throws IOException {
        this._quadBuffer[0] = this._quad1;
        this._quadBuffer[1] = q22;
        this._quadBuffer[2] = q3;
        int[] codes = _icLatin1;
        int qlen = 3;
        while (true) {
            int i2;
            if (codes[i2 = this._inputData.readUnsignedByte()] != 0) {
                if (i2 == 34) {
                    return this.findName(this._quadBuffer, qlen, q2, 1);
                }
                return this.parseEscapedName(this._quadBuffer, qlen, q2, i2, 1);
            }
            q2 = q2 << 8 | i2;
            i2 = this._inputData.readUnsignedByte();
            if (codes[i2] != 0) {
                if (i2 == 34) {
                    return this.findName(this._quadBuffer, qlen, q2, 2);
                }
                return this.parseEscapedName(this._quadBuffer, qlen, q2, i2, 2);
            }
            q2 = q2 << 8 | i2;
            i2 = this._inputData.readUnsignedByte();
            if (codes[i2] != 0) {
                if (i2 == 34) {
                    return this.findName(this._quadBuffer, qlen, q2, 3);
                }
                return this.parseEscapedName(this._quadBuffer, qlen, q2, i2, 3);
            }
            q2 = q2 << 8 | i2;
            i2 = this._inputData.readUnsignedByte();
            if (codes[i2] != 0) {
                if (i2 == 34) {
                    return this.findName(this._quadBuffer, qlen, q2, 4);
                }
                return this.parseEscapedName(this._quadBuffer, qlen, q2, i2, 4);
            }
            if (qlen >= this._quadBuffer.length) {
                this._quadBuffer = UTF8DataInputJsonParser._growArrayBy(this._quadBuffer, qlen);
            }
            this._quadBuffer[qlen++] = q2;
            q2 = i2;
        }
    }

    private final String parseName(int q1, int ch2, int lastQuadBytes) throws IOException {
        return this.parseEscapedName(this._quadBuffer, 0, q1, ch2, lastQuadBytes);
    }

    private final String parseName(int q1, int q2, int ch2, int lastQuadBytes) throws IOException {
        this._quadBuffer[0] = q1;
        return this.parseEscapedName(this._quadBuffer, 1, q2, ch2, lastQuadBytes);
    }

    private final String parseName(int q1, int q2, int q3, int ch2, int lastQuadBytes) throws IOException {
        this._quadBuffer[0] = q1;
        this._quadBuffer[1] = q2;
        return this.parseEscapedName(this._quadBuffer, 2, q3, ch2, lastQuadBytes);
    }

    protected final String parseEscapedName(int[] quads, int qlen, int currQuad, int ch2, int currQuadBytes) throws IOException {
        String name;
        int[] codes = _icLatin1;
        while (true) {
            if (codes[ch2] != 0) {
                if (ch2 == 34) break;
                if (ch2 != 92) {
                    this._throwUnquotedSpace(ch2, "name");
                } else {
                    ch2 = this._decodeEscaped();
                }
                if (ch2 > 127) {
                    if (currQuadBytes >= 4) {
                        if (qlen >= quads.length) {
                            quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
                            this._quadBuffer = quads;
                        }
                        quads[qlen++] = currQuad;
                        currQuad = 0;
                        currQuadBytes = 0;
                    }
                    if (ch2 < 2048) {
                        currQuad = currQuad << 8 | (0xC0 | ch2 >> 6);
                        ++currQuadBytes;
                    } else {
                        currQuad = currQuad << 8 | (0xE0 | ch2 >> 12);
                        if (++currQuadBytes >= 4) {
                            if (qlen >= quads.length) {
                                quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
                                this._quadBuffer = quads;
                            }
                            quads[qlen++] = currQuad;
                            currQuad = 0;
                            currQuadBytes = 0;
                        }
                        currQuad = currQuad << 8 | (0x80 | ch2 >> 6 & 0x3F);
                        ++currQuadBytes;
                    }
                    ch2 = 0x80 | ch2 & 0x3F;
                }
            }
            if (currQuadBytes < 4) {
                ++currQuadBytes;
                currQuad = currQuad << 8 | ch2;
            } else {
                if (qlen >= quads.length) {
                    quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
                    this._quadBuffer = quads;
                }
                quads[qlen++] = currQuad;
                currQuad = ch2;
                currQuadBytes = 1;
            }
            ch2 = this._inputData.readUnsignedByte();
        }
        if (currQuadBytes > 0) {
            if (qlen >= quads.length) {
                quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
                this._quadBuffer = quads;
            }
            quads[qlen++] = UTF8DataInputJsonParser.pad(currQuad, currQuadBytes);
        }
        if ((name = this._symbols.findName(quads, qlen)) == null) {
            name = this.addName(quads, qlen, currQuadBytes);
        }
        return name;
    }

    protected String _handleOddName(int ch2) throws IOException {
        String name;
        int[] codes;
        if (ch2 == 39 && this.isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return this._parseAposName();
        }
        if (!this.isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            char c10 = (char)this._decodeCharForError(ch2);
            this._reportUnexpectedChar(c10, "was expecting double-quote to start field name");
        }
        if ((codes = CharTypes.getInputCodeUtf8JsNames())[ch2] != 0) {
            this._reportUnexpectedChar(ch2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] quads = this._quadBuffer;
        int qlen = 0;
        int currQuad = 0;
        int currQuadBytes = 0;
        do {
            if (currQuadBytes < 4) {
                ++currQuadBytes;
                currQuad = currQuad << 8 | ch2;
                continue;
            }
            if (qlen >= quads.length) {
                this._quadBuffer = quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
            }
            quads[qlen++] = currQuad;
            currQuad = ch2;
            currQuadBytes = 1;
        } while (codes[ch2 = this._inputData.readUnsignedByte()] == 0);
        this._nextByte = ch2;
        if (currQuadBytes > 0) {
            if (qlen >= quads.length) {
                this._quadBuffer = quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
            }
            quads[qlen++] = currQuad;
        }
        if ((name = this._symbols.findName(quads, qlen)) == null) {
            name = this.addName(quads, qlen, currQuadBytes);
        }
        return name;
    }

    protected String _parseAposName() throws IOException {
        String name;
        int ch2 = this._inputData.readUnsignedByte();
        if (ch2 == 39) {
            return "";
        }
        int[] quads = this._quadBuffer;
        int qlen = 0;
        int currQuad = 0;
        int currQuadBytes = 0;
        int[] codes = _icLatin1;
        while (ch2 != 39) {
            if (ch2 != 34 && codes[ch2] != 0) {
                if (ch2 != 92) {
                    this._throwUnquotedSpace(ch2, "name");
                } else {
                    ch2 = this._decodeEscaped();
                }
                if (ch2 > 127) {
                    if (currQuadBytes >= 4) {
                        if (qlen >= quads.length) {
                            this._quadBuffer = quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
                        }
                        quads[qlen++] = currQuad;
                        currQuad = 0;
                        currQuadBytes = 0;
                    }
                    if (ch2 < 2048) {
                        currQuad = currQuad << 8 | (0xC0 | ch2 >> 6);
                        ++currQuadBytes;
                    } else {
                        currQuad = currQuad << 8 | (0xE0 | ch2 >> 12);
                        if (++currQuadBytes >= 4) {
                            if (qlen >= quads.length) {
                                this._quadBuffer = quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
                            }
                            quads[qlen++] = currQuad;
                            currQuad = 0;
                            currQuadBytes = 0;
                        }
                        currQuad = currQuad << 8 | (0x80 | ch2 >> 6 & 0x3F);
                        ++currQuadBytes;
                    }
                    ch2 = 0x80 | ch2 & 0x3F;
                }
            }
            if (currQuadBytes < 4) {
                ++currQuadBytes;
                currQuad = currQuad << 8 | ch2;
            } else {
                if (qlen >= quads.length) {
                    this._quadBuffer = quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
                }
                quads[qlen++] = currQuad;
                currQuad = ch2;
                currQuadBytes = 1;
            }
            ch2 = this._inputData.readUnsignedByte();
        }
        if (currQuadBytes > 0) {
            if (qlen >= quads.length) {
                this._quadBuffer = quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
            }
            quads[qlen++] = UTF8DataInputJsonParser.pad(currQuad, currQuadBytes);
        }
        if ((name = this._symbols.findName(quads, qlen)) == null) {
            name = this.addName(quads, qlen, currQuadBytes);
        }
        return name;
    }

    private final String findName(int q1, int lastQuadBytes) throws JsonParseException {
        String name = this._symbols.findName(q1 = UTF8DataInputJsonParser.pad(q1, lastQuadBytes));
        if (name != null) {
            return name;
        }
        this._quadBuffer[0] = q1;
        return this.addName(this._quadBuffer, 1, lastQuadBytes);
    }

    private final String findName(int q1, int q2, int lastQuadBytes) throws JsonParseException {
        String name = this._symbols.findName(q1, q2 = UTF8DataInputJsonParser.pad(q2, lastQuadBytes));
        if (name != null) {
            return name;
        }
        this._quadBuffer[0] = q1;
        this._quadBuffer[1] = q2;
        return this.addName(this._quadBuffer, 2, lastQuadBytes);
    }

    private final String findName(int q1, int q2, int q3, int lastQuadBytes) throws JsonParseException {
        String name = this._symbols.findName(q1, q2, q3 = UTF8DataInputJsonParser.pad(q3, lastQuadBytes));
        if (name != null) {
            return name;
        }
        int[] quads = this._quadBuffer;
        quads[0] = q1;
        quads[1] = q2;
        quads[2] = UTF8DataInputJsonParser.pad(q3, lastQuadBytes);
        return this.addName(quads, 3, lastQuadBytes);
    }

    private final String findName(int[] quads, int qlen, int lastQuad, int lastQuadBytes) throws JsonParseException {
        if (qlen >= quads.length) {
            quads = UTF8DataInputJsonParser._growArrayBy(quads, quads.length);
            this._quadBuffer = quads;
        }
        quads[qlen++] = UTF8DataInputJsonParser.pad(lastQuad, lastQuadBytes);
        String name = this._symbols.findName(quads, qlen);
        if (name == null) {
            return this.addName(quads, qlen, lastQuadBytes);
        }
        return name;
    }

    private final String addName(int[] quads, int qlen, int lastQuadBytes) throws JsonParseException {
        int lastQuad;
        int byteLen = (qlen << 2) - 4 + lastQuadBytes;
        if (lastQuadBytes < 4) {
            lastQuad = quads[qlen - 1];
            quads[qlen - 1] = lastQuad << (4 - lastQuadBytes << 3);
        } else {
            lastQuad = 0;
        }
        char[] cbuf = this._textBuffer.emptyAndGetCurrentSegment();
        int cix = 0;
        int ix = 0;
        while (ix < byteLen) {
            int ch2 = quads[ix >> 2];
            int byteIx = ix & 3;
            ch2 = ch2 >> (3 - byteIx << 3) & 0xFF;
            ++ix;
            if (ch2 > 127) {
                int needed;
                if ((ch2 & 0xE0) == 192) {
                    ch2 &= 0x1F;
                    needed = 1;
                } else if ((ch2 & 0xF0) == 224) {
                    ch2 &= 0xF;
                    needed = 2;
                } else if ((ch2 & 0xF8) == 240) {
                    ch2 &= 7;
                    needed = 3;
                } else {
                    this._reportInvalidInitial(ch2);
                    ch2 = 1;
                    needed = 1;
                }
                if (ix + needed > byteLen) {
                    this._reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                }
                int ch22 = quads[ix >> 2];
                byteIx = ix & 3;
                ++ix;
                if (((ch22 >>= 3 - byteIx << 3) & 0xC0) != 128) {
                    this._reportInvalidOther(ch22);
                }
                ch2 = ch2 << 6 | ch22 & 0x3F;
                if (needed > 1) {
                    ch22 = quads[ix >> 2];
                    byteIx = ix & 3;
                    ++ix;
                    if (((ch22 >>= 3 - byteIx << 3) & 0xC0) != 128) {
                        this._reportInvalidOther(ch22);
                    }
                    ch2 = ch2 << 6 | ch22 & 0x3F;
                    if (needed > 2) {
                        ch22 = quads[ix >> 2];
                        byteIx = ix & 3;
                        ++ix;
                        if (((ch22 >>= 3 - byteIx << 3) & 0xC0) != 128) {
                            this._reportInvalidOther(ch22 & 0xFF);
                        }
                        ch2 = ch2 << 6 | ch22 & 0x3F;
                    }
                }
                if (needed > 2) {
                    ch2 -= 65536;
                    if (cix >= cbuf.length) {
                        cbuf = this._textBuffer.expandCurrentSegment();
                    }
                    cbuf[cix++] = (char)(55296 + (ch2 >> 10));
                    ch2 = 0xDC00 | ch2 & 0x3FF;
                }
            }
            if (cix >= cbuf.length) {
                cbuf = this._textBuffer.expandCurrentSegment();
            }
            cbuf[cix++] = (char)ch2;
        }
        String baseName = new String(cbuf, 0, cix);
        if (lastQuadBytes < 4) {
            quads[qlen - 1] = lastQuad;
        }
        return this._symbols.addName(baseName, quads, qlen);
    }

    @Override
    protected void _finishString() throws IOException {
        int outPtr = 0;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int[] codes = _icUTF8;
        int outEnd = outBuf.length;
        do {
            int c10;
            if (codes[c10 = this._inputData.readUnsignedByte()] != 0) {
                if (c10 == 34) {
                    this._textBuffer.setCurrentLength(outPtr);
                    return;
                }
                this._finishString2(outBuf, outPtr, c10);
                return;
            }
            outBuf[outPtr++] = (char)c10;
        } while (outPtr < outEnd);
        this._finishString2(outBuf, outPtr, this._inputData.readUnsignedByte());
    }

    private String _finishAndReturnString() throws IOException {
        int outPtr = 0;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int[] codes = _icUTF8;
        int outEnd = outBuf.length;
        do {
            int c10;
            if (codes[c10 = this._inputData.readUnsignedByte()] != 0) {
                if (c10 == 34) {
                    return this._textBuffer.setCurrentAndReturn(outPtr);
                }
                this._finishString2(outBuf, outPtr, c10);
                return this._textBuffer.contentsAsString();
            }
            outBuf[outPtr++] = (char)c10;
        } while (outPtr < outEnd);
        this._finishString2(outBuf, outPtr, this._inputData.readUnsignedByte());
        return this._textBuffer.contentsAsString();
    }

    private final void _finishString2(char[] outBuf, int outPtr, int c10) throws IOException {
        int[] codes = _icUTF8;
        int outEnd = outBuf.length;
        while (true) {
            if (codes[c10] == 0) {
                if (outPtr >= outEnd) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                    outEnd = outBuf.length;
                }
                outBuf[outPtr++] = (char)c10;
                c10 = this._inputData.readUnsignedByte();
                continue;
            }
            if (c10 == 34) break;
            switch (codes[c10]) {
                case 1: {
                    c10 = this._decodeEscaped();
                    break;
                }
                case 2: {
                    c10 = this._decodeUtf8_2(c10);
                    break;
                }
                case 3: {
                    c10 = this._decodeUtf8_3(c10);
                    break;
                }
                case 4: {
                    c10 = this._decodeUtf8_4(c10);
                    outBuf[outPtr++] = (char)(0xD800 | c10 >> 10);
                    if (outPtr >= outBuf.length) {
                        outBuf = this._textBuffer.finishCurrentSegment();
                        outPtr = 0;
                        outEnd = outBuf.length;
                    }
                    c10 = 0xDC00 | c10 & 0x3FF;
                    break;
                }
                default: {
                    if (c10 < 32) {
                        this._throwUnquotedSpace(c10, "string value");
                        break;
                    }
                    this._reportInvalidChar(c10);
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
                outEnd = outBuf.length;
            }
            outBuf[outPtr++] = (char)c10;
            c10 = this._inputData.readUnsignedByte();
        }
        this._textBuffer.setCurrentLength(outPtr);
    }

    protected void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int[] codes = _icUTF8;
        block6: while (true) {
            int c10;
            if (codes[c10 = this._inputData.readUnsignedByte()] == 0) {
                continue;
            }
            if (c10 == 34) break;
            switch (codes[c10]) {
                case 1: {
                    this._decodeEscaped();
                    continue block6;
                }
                case 2: {
                    this._skipUtf8_2();
                    continue block6;
                }
                case 3: {
                    this._skipUtf8_3();
                    continue block6;
                }
                case 4: {
                    this._skipUtf8_4();
                    continue block6;
                }
            }
            if (c10 < 32) {
                this._throwUnquotedSpace(c10, "string value");
                continue;
            }
            this._reportInvalidChar(c10);
        }
    }

    protected JsonToken _handleUnexpectedValue(int c10) throws IOException {
        switch (c10) {
            case 93: {
                if (!this._parsingContext.inArray()) break;
            }
            case 44: {
                if (this.isEnabled(JsonParser.Feature.ALLOW_MISSING_VALUES)) {
                    this._nextByte = c10;
                    return JsonToken.VALUE_NULL;
                }
            }
            case 125: {
                this._reportUnexpectedChar(c10, "expected a value");
            }
            case 39: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) break;
                return this._handleApos();
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
                return this._handleInvalidNumberStart(this._inputData.readUnsignedByte(), false);
            }
        }
        if (Character.isJavaIdentifierStart(c10)) {
            this._reportInvalidToken(c10, "" + (char)c10, "('true', 'false' or 'null')");
        }
        this._reportUnexpectedChar(c10, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected JsonToken _handleApos() throws IOException {
        int c10 = 0;
        int outPtr = 0;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int[] codes = _icUTF8;
        block6: while (true) {
            int outEnd = outBuf.length;
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
                outEnd = outBuf.length;
            }
            while ((c10 = this._inputData.readUnsignedByte()) != 39) {
                if (codes[c10] == 0) {
                    outBuf[outPtr++] = (char)c10;
                    if (outPtr < outEnd) continue;
                    continue block6;
                }
                switch (codes[c10]) {
                    case 1: {
                        c10 = this._decodeEscaped();
                        break;
                    }
                    case 2: {
                        c10 = this._decodeUtf8_2(c10);
                        break;
                    }
                    case 3: {
                        c10 = this._decodeUtf8_3(c10);
                        break;
                    }
                    case 4: {
                        c10 = this._decodeUtf8_4(c10);
                        outBuf[outPtr++] = (char)(0xD800 | c10 >> 10);
                        if (outPtr >= outBuf.length) {
                            outBuf = this._textBuffer.finishCurrentSegment();
                            outPtr = 0;
                        }
                        c10 = 0xDC00 | c10 & 0x3FF;
                        break;
                    }
                    default: {
                        if (c10 < 32) {
                            this._throwUnquotedSpace(c10, "string value");
                        }
                        this._reportInvalidChar(c10);
                    }
                }
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outBuf[outPtr++] = (char)c10;
                continue block6;
            }
            break;
        }
        this._textBuffer.setCurrentLength(outPtr);
        return JsonToken.VALUE_STRING;
    }

    protected JsonToken _handleInvalidNumberStart(int ch2, boolean neg) throws IOException {
        while (ch2 == 73) {
            String match;
            ch2 = this._inputData.readUnsignedByte();
            if (ch2 == 78) {
                match = neg ? "-INF" : "+INF";
            } else {
                if (ch2 != 110) break;
                match = neg ? "-Infinity" : "+Infinity";
            }
            this._matchToken(match, 3);
            if (this.isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                return this.resetAsNaN(match, neg ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            this._reportError("Non-standard token '" + match + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
        this.reportUnexpectedNumberChar(ch2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected final void _matchToken(String matchStr, int i2) throws IOException {
        int ch2;
        int len = matchStr.length();
        do {
            if ((ch2 = this._inputData.readUnsignedByte()) == matchStr.charAt(i2)) continue;
            this._reportInvalidToken(ch2, matchStr.substring(0, i2));
        } while (++i2 < len);
        ch2 = this._inputData.readUnsignedByte();
        if (ch2 >= 48 && ch2 != 93 && ch2 != 125) {
            this._checkMatchEnd(matchStr, i2, ch2);
        }
        this._nextByte = ch2;
    }

    private final void _checkMatchEnd(String matchStr, int i2, int ch2) throws IOException {
        char c10 = (char)this._decodeCharForError(ch2);
        if (Character.isJavaIdentifierPart(c10)) {
            this._reportInvalidToken(c10, matchStr.substring(0, i2));
        }
    }

    private final int _skipWS() throws IOException {
        int i2 = this._nextByte;
        if (i2 < 0) {
            i2 = this._inputData.readUnsignedByte();
        } else {
            this._nextByte = -1;
        }
        while (true) {
            if (i2 > 32) {
                if (i2 == 47 || i2 == 35) {
                    return this._skipWSComment(i2);
                }
                return i2;
            }
            if (i2 == 13 || i2 == 10) {
                ++this._currInputRow;
            }
            i2 = this._inputData.readUnsignedByte();
        }
    }

    private final int _skipWSOrEnd() throws IOException {
        int i2 = this._nextByte;
        if (i2 < 0) {
            try {
                i2 = this._inputData.readUnsignedByte();
            }
            catch (EOFException e10) {
                return this._eofAsNextChar();
            }
        } else {
            this._nextByte = -1;
        }
        while (true) {
            if (i2 > 32) {
                if (i2 == 47 || i2 == 35) {
                    return this._skipWSComment(i2);
                }
                return i2;
            }
            if (i2 == 13 || i2 == 10) {
                ++this._currInputRow;
            }
            try {
                i2 = this._inputData.readUnsignedByte();
            }
            catch (EOFException e11) {
                return this._eofAsNextChar();
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final int _skipWSComment(int i2) throws IOException {
        while (true) {
            if (i2 > 32) {
                if (i2 == 47) {
                    this._skipComment();
                } else {
                    if (i2 != 35) return i2;
                    if (!this._skipYAMLComment()) {
                        return i2;
                    }
                }
            } else if (i2 == 13 || i2 == 10) {
                ++this._currInputRow;
            }
            i2 = this._inputData.readUnsignedByte();
        }
    }

    private final int _skipColon() throws IOException {
        int i2 = this._nextByte;
        if (i2 < 0) {
            i2 = this._inputData.readUnsignedByte();
        } else {
            this._nextByte = -1;
        }
        if (i2 == 58) {
            i2 = this._inputData.readUnsignedByte();
            if (i2 > 32) {
                if (i2 == 47 || i2 == 35) {
                    return this._skipColon2(i2, true);
                }
                return i2;
            }
            if ((i2 == 32 || i2 == 9) && (i2 = this._inputData.readUnsignedByte()) > 32) {
                if (i2 == 47 || i2 == 35) {
                    return this._skipColon2(i2, true);
                }
                return i2;
            }
            return this._skipColon2(i2, true);
        }
        if (i2 == 32 || i2 == 9) {
            i2 = this._inputData.readUnsignedByte();
        }
        if (i2 == 58) {
            i2 = this._inputData.readUnsignedByte();
            if (i2 > 32) {
                if (i2 == 47 || i2 == 35) {
                    return this._skipColon2(i2, true);
                }
                return i2;
            }
            if ((i2 == 32 || i2 == 9) && (i2 = this._inputData.readUnsignedByte()) > 32) {
                if (i2 == 47 || i2 == 35) {
                    return this._skipColon2(i2, true);
                }
                return i2;
            }
            return this._skipColon2(i2, true);
        }
        return this._skipColon2(i2, false);
    }

    private final int _skipColon2(int i2, boolean gotColon) throws IOException {
        while (true) {
            if (i2 > 32) {
                if (i2 == 47) {
                    this._skipComment();
                } else if (i2 != 35 || !this._skipYAMLComment()) {
                    if (gotColon) {
                        return i2;
                    }
                    if (i2 != 58) {
                        this._reportUnexpectedChar(i2, "was expecting a colon to separate field name and value");
                    }
                    gotColon = true;
                }
            } else if (i2 == 13 || i2 == 10) {
                ++this._currInputRow;
            }
            i2 = this._inputData.readUnsignedByte();
        }
    }

    private final void _skipComment() throws IOException {
        int c10;
        if (!this.isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if ((c10 = this._inputData.readUnsignedByte()) == 47) {
            this._skipLine();
        } else if (c10 == 42) {
            this._skipCComment();
        } else {
            this._reportUnexpectedChar(c10, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCComment() throws IOException {
        int[] codes = CharTypes.getInputCodeComment();
        int i2 = this._inputData.readUnsignedByte();
        block7: while (true) {
            int code;
            if ((code = codes[i2]) != 0) {
                switch (code) {
                    case 42: {
                        i2 = this._inputData.readUnsignedByte();
                        if (i2 != 47) continue block7;
                        return;
                    }
                    case 10: 
                    case 13: {
                        ++this._currInputRow;
                        break;
                    }
                    case 2: {
                        this._skipUtf8_2();
                        break;
                    }
                    case 3: {
                        this._skipUtf8_3();
                        break;
                    }
                    case 4: {
                        this._skipUtf8_4();
                        break;
                    }
                    default: {
                        this._reportInvalidChar(i2);
                    }
                }
            }
            i2 = this._inputData.readUnsignedByte();
        }
    }

    private final boolean _skipYAMLComment() throws IOException {
        if (!this.isEnabled(JsonParser.Feature.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        this._skipLine();
        return true;
    }

    private final void _skipLine() throws IOException {
        int[] codes = CharTypes.getInputCodeComment();
        block7: while (true) {
            int i2;
            int code;
            if ((code = codes[i2 = this._inputData.readUnsignedByte()]) == 0) {
                continue;
            }
            switch (code) {
                case 10: 
                case 13: {
                    ++this._currInputRow;
                    return;
                }
                case 42: {
                    continue block7;
                }
                case 2: {
                    this._skipUtf8_2();
                    continue block7;
                }
                case 3: {
                    this._skipUtf8_3();
                    continue block7;
                }
                case 4: {
                    this._skipUtf8_4();
                    continue block7;
                }
            }
            if (code >= 0) continue;
            this._reportInvalidChar(i2);
        }
    }

    @Override
    protected char _decodeEscaped() throws IOException {
        int c10 = this._inputData.readUnsignedByte();
        switch (c10) {
            case 98: {
                return '\b';
            }
            case 116: {
                return '\t';
            }
            case 110: {
                return '\n';
            }
            case 102: {
                return '\f';
            }
            case 114: {
                return '\r';
            }
            case 34: 
            case 47: 
            case 92: {
                return (char)c10;
            }
            case 117: {
                break;
            }
            default: {
                return this._handleUnrecognizedCharacterEscape((char)this._decodeCharForError(c10));
            }
        }
        int value = 0;
        for (int i2 = 0; i2 < 4; ++i2) {
            int ch2 = this._inputData.readUnsignedByte();
            int digit = CharTypes.charToHex(ch2);
            if (digit < 0) {
                this._reportUnexpectedChar(ch2, "expected a hex-digit for character escape sequence");
            }
            value = value << 4 | digit;
        }
        return (char)value;
    }

    protected int _decodeCharForError(int firstByte) throws IOException {
        int c10 = firstByte & 0xFF;
        if (c10 > 127) {
            int needed;
            if ((c10 & 0xE0) == 192) {
                c10 &= 0x1F;
                needed = 1;
            } else if ((c10 & 0xF0) == 224) {
                c10 &= 0xF;
                needed = 2;
            } else if ((c10 & 0xF8) == 240) {
                c10 &= 7;
                needed = 3;
            } else {
                this._reportInvalidInitial(c10 & 0xFF);
                needed = 1;
            }
            int d10 = this._inputData.readUnsignedByte();
            if ((d10 & 0xC0) != 128) {
                this._reportInvalidOther(d10 & 0xFF);
            }
            c10 = c10 << 6 | d10 & 0x3F;
            if (needed > 1) {
                d10 = this._inputData.readUnsignedByte();
                if ((d10 & 0xC0) != 128) {
                    this._reportInvalidOther(d10 & 0xFF);
                }
                c10 = c10 << 6 | d10 & 0x3F;
                if (needed > 2) {
                    d10 = this._inputData.readUnsignedByte();
                    if ((d10 & 0xC0) != 128) {
                        this._reportInvalidOther(d10 & 0xFF);
                    }
                    c10 = c10 << 6 | d10 & 0x3F;
                }
            }
        }
        return c10;
    }

    private final int _decodeUtf8_2(int c10) throws IOException {
        int d10 = this._inputData.readUnsignedByte();
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
        return (c10 & 0x1F) << 6 | d10 & 0x3F;
    }

    private final int _decodeUtf8_3(int c12) throws IOException {
        c12 &= 0xF;
        int d10 = this._inputData.readUnsignedByte();
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
        int c10 = c12 << 6 | d10 & 0x3F;
        d10 = this._inputData.readUnsignedByte();
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
        c10 = c10 << 6 | d10 & 0x3F;
        return c10;
    }

    private final int _decodeUtf8_4(int c10) throws IOException {
        int d10 = this._inputData.readUnsignedByte();
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
        c10 = (c10 & 7) << 6 | d10 & 0x3F;
        d10 = this._inputData.readUnsignedByte();
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
        c10 = c10 << 6 | d10 & 0x3F;
        d10 = this._inputData.readUnsignedByte();
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
        return (c10 << 6 | d10 & 0x3F) - 65536;
    }

    private final void _skipUtf8_2() throws IOException {
        int c10 = this._inputData.readUnsignedByte();
        if ((c10 & 0xC0) != 128) {
            this._reportInvalidOther(c10 & 0xFF);
        }
    }

    private final void _skipUtf8_3() throws IOException {
        int c10 = this._inputData.readUnsignedByte();
        if ((c10 & 0xC0) != 128) {
            this._reportInvalidOther(c10 & 0xFF);
        }
        if (((c10 = this._inputData.readUnsignedByte()) & 0xC0) != 128) {
            this._reportInvalidOther(c10 & 0xFF);
        }
    }

    private final void _skipUtf8_4() throws IOException {
        int d10 = this._inputData.readUnsignedByte();
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
        if (((d10 = this._inputData.readUnsignedByte()) & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
        if (((d10 = this._inputData.readUnsignedByte()) & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF);
        }
    }

    protected void _reportInvalidToken(int ch2, String matchedPart) throws IOException {
        this._reportInvalidToken(ch2, matchedPart, "'null', 'true', 'false' or NaN");
    }

    protected void _reportInvalidToken(int ch2, String matchedPart, String msg) throws IOException {
        char c10;
        StringBuilder sb = new StringBuilder(matchedPart);
        while (Character.isJavaIdentifierPart(c10 = (char)this._decodeCharForError(ch2))) {
            sb.append(c10);
            ch2 = this._inputData.readUnsignedByte();
        }
        this._reportError("Unrecognized token '" + sb.toString() + "': was expecting " + msg);
    }

    protected void _reportInvalidChar(int c10) throws JsonParseException {
        if (c10 < 32) {
            this._throwInvalidSpace(c10);
        }
        this._reportInvalidInitial(c10);
    }

    protected void _reportInvalidInitial(int mask) throws JsonParseException {
        this._reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(mask));
    }

    private void _reportInvalidOther(int mask) throws JsonParseException {
        this._reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(mask));
    }

    private static int[] _growArrayBy(int[] arr, int more) {
        if (arr == null) {
            return new int[more];
        }
        return Arrays.copyOf(arr, arr.length + more);
    }

    protected final byte[] _decodeBase64(Base64Variant b64variant) throws IOException {
        ByteArrayBuilder builder = this._getByteArrayBuilder();
        while (true) {
            int ch2;
            if ((ch2 = this._inputData.readUnsignedByte()) <= 32) {
                continue;
            }
            int bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                if (ch2 == 34) {
                    return builder.toByteArray();
                }
                bits = this._decodeBase64Escape(b64variant, ch2, 0);
                if (bits < 0) continue;
            }
            int decodedData = bits;
            ch2 = this._inputData.readUnsignedByte();
            bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                bits = this._decodeBase64Escape(b64variant, ch2, 1);
            }
            decodedData = decodedData << 6 | bits;
            ch2 = this._inputData.readUnsignedByte();
            bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                if (bits != -2) {
                    if (ch2 == 34 && !b64variant.usesPadding()) {
                        builder.append(decodedData >>= 4);
                        return builder.toByteArray();
                    }
                    bits = this._decodeBase64Escape(b64variant, ch2, 2);
                }
                if (bits == -2) {
                    ch2 = this._inputData.readUnsignedByte();
                    if (!b64variant.usesPaddingChar(ch2)) {
                        throw this.reportInvalidBase64Char(b64variant, ch2, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                    }
                    builder.append(decodedData >>= 4);
                    continue;
                }
            }
            decodedData = decodedData << 6 | bits;
            ch2 = this._inputData.readUnsignedByte();
            bits = b64variant.decodeBase64Char(ch2);
            if (bits < 0) {
                if (bits != -2) {
                    if (ch2 == 34 && !b64variant.usesPadding()) {
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
        return new JsonLocation(this._getSourceReference(), -1L, -1L, this._tokenInputRow, -1);
    }

    @Override
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._getSourceReference(), -1L, -1L, this._currInputRow, -1);
    }

    private void _closeScope(int i2) throws JsonParseException {
        if (i2 == 93) {
            if (!this._parsingContext.inArray()) {
                this._reportMismatchedEndMarker(i2, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
        }
        if (i2 == 125) {
            if (!this._parsingContext.inObject()) {
                this._reportMismatchedEndMarker(i2, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
        }
    }

    private static final int pad(int q2, int bytes) {
        return bytes == 4 ? q2 : q2 | -1 << (bytes << 3);
    }
}

