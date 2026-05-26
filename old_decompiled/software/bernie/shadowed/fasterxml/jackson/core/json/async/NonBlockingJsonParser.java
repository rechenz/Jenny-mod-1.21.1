/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.json.async;

import java.io.IOException;
import java.io.OutputStream;
import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.async.ByteArrayFeeder;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharTypes;
import software.bernie.shadowed.fasterxml.jackson.core.io.IOContext;
import software.bernie.shadowed.fasterxml.jackson.core.json.async.NonBlockingJsonParserBase;
import software.bernie.shadowed.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import software.bernie.shadowed.fasterxml.jackson.core.util.VersionUtil;

public class NonBlockingJsonParser
extends NonBlockingJsonParserBase
implements ByteArrayFeeder {
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    protected byte[] _inputBuffer = NO_BYTES;
    protected int _origBufferLen;

    public NonBlockingJsonParser(IOContext ctxt, int parserFeatures, ByteQuadsCanonicalizer sym) {
        super(ctxt, parserFeatures, sym);
    }

    @Override
    public ByteArrayFeeder getNonBlockingInputFeeder() {
        return this;
    }

    @Override
    public final boolean needMoreInput() {
        return this._inputPtr >= this._inputEnd && !this._endOfInput;
    }

    @Override
    public void feedInput(byte[] buf, int start, int end) throws IOException {
        if (this._inputPtr < this._inputEnd) {
            this._reportError("Still have %d undecoded bytes, should not call 'feedInput'", this._inputEnd - this._inputPtr);
        }
        if (end < start) {
            this._reportError("Input end (%d) may not be before start (%d)", end, start);
        }
        if (this._endOfInput) {
            this._reportError("Already closed, can not feed more input");
        }
        this._currInputProcessed += (long)this._origBufferLen;
        this._currInputRowStart = start - (this._inputEnd - this._currInputRowStart);
        this._inputBuffer = buf;
        this._inputPtr = start;
        this._inputEnd = end;
        this._origBufferLen = end - start;
    }

    @Override
    public void endOfInput() {
        this._endOfInput = true;
    }

    @Override
    public int releaseBuffered(OutputStream out) throws IOException {
        int avail = this._inputEnd - this._inputPtr;
        if (avail > 0) {
            out.write(this._inputBuffer, this._inputPtr, avail);
        }
        return avail;
    }

    @Override
    protected char _decodeEscaped() throws IOException {
        VersionUtil.throwInternal();
        return ' ';
    }

    @Override
    public JsonToken nextToken() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            if (this._closed) {
                return null;
            }
            if (this._endOfInput) {
                if (this._currToken == JsonToken.NOT_AVAILABLE) {
                    return this._finishTokenWithEOF();
                }
                return this._eofAsNextToken();
            }
            return JsonToken.NOT_AVAILABLE;
        }
        if (this._currToken == JsonToken.NOT_AVAILABLE) {
            return this._finishToken();
        }
        this._numTypesValid = 0;
        this._tokenInputTotal = this._currInputProcessed + (long)this._inputPtr;
        this._binaryValue = null;
        int ch2 = this._inputBuffer[this._inputPtr++] & 0xFF;
        switch (this._majorState) {
            case 0: {
                return this._startDocument(ch2);
            }
            case 1: {
                return this._startValue(ch2);
            }
            case 2: {
                return this._startFieldName(ch2);
            }
            case 3: {
                return this._startFieldNameAfterComma(ch2);
            }
            case 4: {
                return this._startValueExpectColon(ch2);
            }
            case 5: {
                return this._startValue(ch2);
            }
            case 6: {
                return this._startValueExpectComma(ch2);
            }
        }
        VersionUtil.throwInternal();
        return null;
    }

    protected final JsonToken _finishToken() throws IOException {
        switch (this._minorState) {
            case 1: {
                return this._finishBOM(this._pending32);
            }
            case 4: {
                return this._startFieldName(this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 5: {
                return this._startFieldNameAfterComma(this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 7: {
                return this._parseEscapedName(this._quadLength, this._pending32, this._pendingBytes);
            }
            case 8: {
                return this._finishFieldWithEscape();
            }
            case 9: {
                return this._finishAposName(this._quadLength, this._pending32, this._pendingBytes);
            }
            case 10: {
                return this._finishUnquotedName(this._quadLength, this._pending32, this._pendingBytes);
            }
            case 12: {
                return this._startValue(this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 15: {
                return this._startValueAfterComma(this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 13: {
                return this._startValueExpectComma(this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 14: {
                return this._startValueExpectColon(this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 16: {
                return this._finishKeywordToken("null", this._pending32, JsonToken.VALUE_NULL);
            }
            case 17: {
                return this._finishKeywordToken("true", this._pending32, JsonToken.VALUE_TRUE);
            }
            case 18: {
                return this._finishKeywordToken("false", this._pending32, JsonToken.VALUE_FALSE);
            }
            case 19: {
                return this._finishNonStdToken(this._nonStdTokenType, this._pending32);
            }
            case 23: {
                return this._finishNumberMinus(this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 24: {
                return this._finishNumberLeadingZeroes();
            }
            case 25: {
                return this._finishNumberLeadingNegZeroes();
            }
            case 26: {
                return this._finishNumberIntegralPart(this._textBuffer.getBufferWithoutReset(), this._textBuffer.getCurrentSegmentSize());
            }
            case 30: {
                return this._finishFloatFraction();
            }
            case 31: {
                return this._finishFloatExponent(true, this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 32: {
                return this._finishFloatExponent(false, this._inputBuffer[this._inputPtr++] & 0xFF);
            }
            case 40: {
                return this._finishRegularString();
            }
            case 42: {
                this._textBuffer.append((char)this._decodeUTF8_2(this._pending32, this._inputBuffer[this._inputPtr++]));
                if (this._minorStateAfterSplit == 45) {
                    return this._finishAposString();
                }
                return this._finishRegularString();
            }
            case 43: {
                if (!this._decodeSplitUTF8_3(this._pending32, this._pendingBytes, this._inputBuffer[this._inputPtr++])) {
                    return JsonToken.NOT_AVAILABLE;
                }
                if (this._minorStateAfterSplit == 45) {
                    return this._finishAposString();
                }
                return this._finishRegularString();
            }
            case 44: {
                if (!this._decodeSplitUTF8_4(this._pending32, this._pendingBytes, this._inputBuffer[this._inputPtr++])) {
                    return JsonToken.NOT_AVAILABLE;
                }
                if (this._minorStateAfterSplit == 45) {
                    return this._finishAposString();
                }
                return this._finishRegularString();
            }
            case 41: {
                int c10 = this._decodeSplitEscaped(this._quoted32, this._quotedDigits);
                if (c10 < 0) {
                    return JsonToken.NOT_AVAILABLE;
                }
                this._textBuffer.append((char)c10);
                if (this._minorStateAfterSplit == 45) {
                    return this._finishAposString();
                }
                return this._finishRegularString();
            }
            case 45: {
                return this._finishAposString();
            }
            case 50: {
                return this._finishErrorToken();
            }
            case 51: {
                return this._startSlashComment(this._pending32);
            }
            case 52: {
                return this._finishCComment(this._pending32, true);
            }
            case 53: {
                return this._finishCComment(this._pending32, false);
            }
            case 54: {
                return this._finishCppComment(this._pending32);
            }
            case 55: {
                return this._finishHashComment(this._pending32);
            }
        }
        VersionUtil.throwInternal();
        return null;
    }

    protected final JsonToken _finishTokenWithEOF() throws IOException {
        JsonToken t2 = this._currToken;
        switch (this._minorState) {
            case 3: {
                return this._eofAsNextToken();
            }
            case 12: {
                return this._eofAsNextToken();
            }
            case 16: {
                return this._finishKeywordTokenWithEOF("null", this._pending32, JsonToken.VALUE_NULL);
            }
            case 17: {
                return this._finishKeywordTokenWithEOF("true", this._pending32, JsonToken.VALUE_TRUE);
            }
            case 18: {
                return this._finishKeywordTokenWithEOF("false", this._pending32, JsonToken.VALUE_FALSE);
            }
            case 19: {
                return this._finishNonStdTokenWithEOF(this._nonStdTokenType, this._pending32);
            }
            case 50: {
                return this._finishErrorTokenWithEOF();
            }
            case 24: 
            case 25: {
                return this._valueCompleteInt(0, "0");
            }
            case 26: {
                int len = this._textBuffer.getCurrentSegmentSize();
                if (this._numberNegative) {
                    --len;
                }
                this._intLength = len;
                return this._valueComplete(JsonToken.VALUE_NUMBER_INT);
            }
            case 30: {
                this._expLength = 0;
            }
            case 32: {
                return this._valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
            }
            case 31: {
                this._reportInvalidEOF(": was expecting fraction after exponent marker", JsonToken.VALUE_NUMBER_FLOAT);
            }
            case 52: 
            case 53: {
                this._reportInvalidEOF(": was expecting closing '*/' for comment", JsonToken.NOT_AVAILABLE);
            }
            case 54: 
            case 55: {
                return this._eofAsNextToken();
            }
        }
        this._reportInvalidEOF(": was expecting rest of token (internal state: " + this._minorState + ")", this._currToken);
        return t2;
    }

    private final JsonToken _startDocument(int ch2) throws IOException {
        if ((ch2 &= 0xFF) == 239 && this._minorState != 1) {
            return this._finishBOM(1);
        }
        while (ch2 <= 32) {
            if (ch2 != 32) {
                if (ch2 == 10) {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                } else if (ch2 == 13) {
                    ++this._currInputRowAlt;
                    this._currInputRowStart = this._inputPtr;
                } else if (ch2 != 9) {
                    this._throwInvalidSpace(ch2);
                }
            }
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 3;
                if (this._closed) {
                    return null;
                }
                if (this._endOfInput) {
                    return this._eofAsNextToken();
                }
                return JsonToken.NOT_AVAILABLE;
            }
            ch2 = this._inputBuffer[this._inputPtr++] & 0xFF;
        }
        return this._startValue(ch2);
    }

    private final JsonToken _finishBOM(int bytesHandled) throws IOException {
        while (this._inputPtr < this._inputEnd) {
            int ch2 = this._inputBuffer[this._inputPtr++] & 0xFF;
            switch (bytesHandled) {
                case 3: {
                    this._currInputProcessed -= 3L;
                    return this._startDocument(ch2);
                }
                case 2: {
                    if (ch2 == 191) break;
                    this._reportError("Unexpected byte 0x%02x following 0xEF 0xBB; should get 0xBF as third byte of UTF-8 BOM", ch2);
                    break;
                }
                case 1: {
                    if (ch2 == 187) break;
                    this._reportError("Unexpected byte 0x%02x following 0xEF; should get 0xBB as second byte UTF-8 BOM", ch2);
                }
            }
            ++bytesHandled;
        }
        this._pending32 = bytesHandled;
        this._minorState = 1;
        this._currToken = JsonToken.NOT_AVAILABLE;
        return this._currToken;
    }

    private final JsonToken _startFieldName(int ch2) throws IOException {
        String n2;
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 4;
            return this._currToken;
        }
        this._updateTokenLocation();
        if (ch2 != 34) {
            if (ch2 == 125) {
                return this._closeObjectScope();
            }
            return this._handleOddName(ch2);
        }
        if (this._inputPtr + 13 <= this._inputEnd && (n2 = this._fastParseName()) != null) {
            return this._fieldComplete(n2);
        }
        return this._parseEscapedName(0, 0, 0);
    }

    private final JsonToken _startFieldNameAfterComma(int ch2) throws IOException {
        String n2;
        int ptr;
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 5;
            return this._currToken;
        }
        if (ch2 != 44) {
            if (ch2 == 125) {
                return this._closeObjectScope();
            }
            if (ch2 == 35) {
                return this._finishHashComment(5);
            }
            if (ch2 == 47) {
                return this._startSlashComment(5);
            }
            this._reportUnexpectedChar(ch2, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
        }
        if ((ptr = this._inputPtr) >= this._inputEnd) {
            this._minorState = 4;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        ch2 = this._inputBuffer[ptr];
        this._inputPtr = ptr + 1;
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 4;
            return this._currToken;
        }
        this._updateTokenLocation();
        if (ch2 != 34) {
            if (ch2 == 125 && JsonParser.Feature.ALLOW_TRAILING_COMMA.enabledIn(this._features)) {
                return this._closeObjectScope();
            }
            return this._handleOddName(ch2);
        }
        if (this._inputPtr + 13 <= this._inputEnd && (n2 = this._fastParseName()) != null) {
            return this._fieldComplete(n2);
        }
        return this._parseEscapedName(0, 0, 0);
    }

    private final JsonToken _startValue(int ch2) throws IOException {
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 12;
            return this._currToken;
        }
        this._updateTokenLocation();
        if (ch2 == 34) {
            return this._startString();
        }
        switch (ch2) {
            case 35: {
                return this._finishHashComment(12);
            }
            case 45: {
                return this._startNegativeNumber();
            }
            case 47: {
                return this._startSlashComment(12);
            }
            case 48: {
                return this._startNumberLeadingZero();
            }
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                return this._startPositiveNumber(ch2);
            }
            case 102: {
                return this._startFalseToken();
            }
            case 110: {
                return this._startNullToken();
            }
            case 116: {
                return this._startTrueToken();
            }
            case 91: {
                return this._startArrayScope();
            }
            case 93: {
                return this._closeArrayScope();
            }
            case 123: {
                return this._startObjectScope();
            }
            case 125: {
                return this._closeObjectScope();
            }
        }
        return this._startUnexpectedValue(false, ch2);
    }

    private final JsonToken _startValueExpectComma(int ch2) throws IOException {
        int ptr;
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 13;
            return this._currToken;
        }
        if (ch2 != 44) {
            if (ch2 == 93) {
                return this._closeArrayScope();
            }
            if (ch2 == 125) {
                return this._closeObjectScope();
            }
            if (ch2 == 47) {
                return this._startSlashComment(13);
            }
            if (ch2 == 35) {
                return this._finishHashComment(13);
            }
            this._reportUnexpectedChar(ch2, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
        }
        if ((ptr = this._inputPtr) >= this._inputEnd) {
            this._minorState = 15;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        ch2 = this._inputBuffer[ptr];
        this._inputPtr = ptr + 1;
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 15;
            return this._currToken;
        }
        this._updateTokenLocation();
        if (ch2 == 34) {
            return this._startString();
        }
        switch (ch2) {
            case 35: {
                return this._finishHashComment(15);
            }
            case 45: {
                return this._startNegativeNumber();
            }
            case 47: {
                return this._startSlashComment(15);
            }
            case 48: {
                return this._startNumberLeadingZero();
            }
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                return this._startPositiveNumber(ch2);
            }
            case 102: {
                return this._startFalseToken();
            }
            case 110: {
                return this._startNullToken();
            }
            case 116: {
                return this._startTrueToken();
            }
            case 91: {
                return this._startArrayScope();
            }
            case 93: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_TRAILING_COMMA)) break;
                return this._closeArrayScope();
            }
            case 123: {
                return this._startObjectScope();
            }
            case 125: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_TRAILING_COMMA)) break;
                return this._closeObjectScope();
            }
        }
        return this._startUnexpectedValue(true, ch2);
    }

    private final JsonToken _startValueExpectColon(int ch2) throws IOException {
        int ptr;
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 14;
            return this._currToken;
        }
        if (ch2 != 58) {
            if (ch2 == 47) {
                return this._startSlashComment(14);
            }
            if (ch2 == 35) {
                return this._finishHashComment(14);
            }
            this._reportUnexpectedChar(ch2, "was expecting a colon to separate field name and value");
        }
        if ((ptr = this._inputPtr) >= this._inputEnd) {
            this._minorState = 12;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        ch2 = this._inputBuffer[ptr];
        this._inputPtr = ptr + 1;
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 12;
            return this._currToken;
        }
        this._updateTokenLocation();
        if (ch2 == 34) {
            return this._startString();
        }
        switch (ch2) {
            case 35: {
                return this._finishHashComment(12);
            }
            case 45: {
                return this._startNegativeNumber();
            }
            case 47: {
                return this._startSlashComment(12);
            }
            case 48: {
                return this._startNumberLeadingZero();
            }
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                return this._startPositiveNumber(ch2);
            }
            case 102: {
                return this._startFalseToken();
            }
            case 110: {
                return this._startNullToken();
            }
            case 116: {
                return this._startTrueToken();
            }
            case 91: {
                return this._startArrayScope();
            }
            case 123: {
                return this._startObjectScope();
            }
        }
        return this._startUnexpectedValue(false, ch2);
    }

    private final JsonToken _startValueAfterComma(int ch2) throws IOException {
        if (ch2 <= 32 && (ch2 = this._skipWS(ch2)) <= 0) {
            this._minorState = 15;
            return this._currToken;
        }
        this._updateTokenLocation();
        if (ch2 == 34) {
            return this._startString();
        }
        switch (ch2) {
            case 35: {
                return this._finishHashComment(15);
            }
            case 45: {
                return this._startNegativeNumber();
            }
            case 47: {
                return this._startSlashComment(15);
            }
            case 48: {
                return this._startNumberLeadingZero();
            }
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                return this._startPositiveNumber(ch2);
            }
            case 102: {
                return this._startFalseToken();
            }
            case 110: {
                return this._startNullToken();
            }
            case 116: {
                return this._startTrueToken();
            }
            case 91: {
                return this._startArrayScope();
            }
            case 93: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_TRAILING_COMMA)) break;
                return this._closeArrayScope();
            }
            case 123: {
                return this._startObjectScope();
            }
            case 125: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_TRAILING_COMMA)) break;
                return this._closeObjectScope();
            }
        }
        return this._startUnexpectedValue(true, ch2);
    }

    protected JsonToken _startUnexpectedValue(boolean leadingComma, int ch2) throws IOException {
        switch (ch2) {
            case 93: {
                if (!this._parsingContext.inArray()) break;
            }
            case 44: {
                if (this.isEnabled(JsonParser.Feature.ALLOW_MISSING_VALUES)) {
                    --this._inputPtr;
                    return this._valueComplete(JsonToken.VALUE_NULL);
                }
            }
            case 125: {
                break;
            }
            case 39: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) break;
                return this._startAposString();
            }
            case 43: {
                return this._finishNonStdToken(2, 1);
            }
            case 78: {
                return this._finishNonStdToken(0, 1);
            }
            case 73: {
                return this._finishNonStdToken(1, 1);
            }
        }
        this._reportUnexpectedChar(ch2, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    private final int _skipWS(int ch2) throws IOException {
        do {
            if (ch2 != 32) {
                if (ch2 == 10) {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                } else if (ch2 == 13) {
                    ++this._currInputRowAlt;
                    this._currInputRowStart = this._inputPtr;
                } else if (ch2 != 9) {
                    this._throwInvalidSpace(ch2);
                }
            }
            if (this._inputPtr < this._inputEnd) continue;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return 0;
        } while ((ch2 = this._inputBuffer[this._inputPtr++] & 0xFF) <= 32);
        return ch2;
    }

    private final JsonToken _startSlashComment(int fromMinorState) throws IOException {
        byte ch2;
        if (!JsonParser.Feature.ALLOW_COMMENTS.enabledIn(this._features)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd) {
            this._pending32 = fromMinorState;
            this._minorState = 51;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        if ((ch2 = this._inputBuffer[this._inputPtr++]) == 42) {
            return this._finishCComment(fromMinorState, false);
        }
        if (ch2 == 47) {
            return this._finishCppComment(fromMinorState);
        }
        this._reportUnexpectedChar(ch2 & 0xFF, "was expecting either '*' or '/' for a comment");
        return null;
    }

    private final JsonToken _finishHashComment(int fromMinorState) throws IOException {
        if (!JsonParser.Feature.ALLOW_YAML_COMMENTS.enabledIn(this._features)) {
            this._reportUnexpectedChar(35, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_YAML_COMMENTS' not enabled for parser)");
        }
        while (true) {
            int ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 55;
                this._pending32 = fromMinorState;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            if ((ch2 = this._inputBuffer[this._inputPtr++] & 0xFF) >= 32) continue;
            if (ch2 == 10) {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                break;
            }
            if (ch2 == 13) {
                ++this._currInputRowAlt;
                this._currInputRowStart = this._inputPtr;
                break;
            }
            if (ch2 == 9) continue;
            this._throwInvalidSpace(ch2);
        }
        return this._startAfterComment(fromMinorState);
    }

    private final JsonToken _finishCppComment(int fromMinorState) throws IOException {
        while (true) {
            int ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 54;
                this._pending32 = fromMinorState;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            if ((ch2 = this._inputBuffer[this._inputPtr++] & 0xFF) >= 32) continue;
            if (ch2 == 10) {
                ++this._currInputRow;
                this._currInputRowStart = this._inputPtr;
                break;
            }
            if (ch2 == 13) {
                ++this._currInputRowAlt;
                this._currInputRowStart = this._inputPtr;
                break;
            }
            if (ch2 == 9) continue;
            this._throwInvalidSpace(ch2);
        }
        return this._startAfterComment(fromMinorState);
    }

    private final JsonToken _finishCComment(int fromMinorState, boolean gotStar) throws IOException {
        while (true) {
            int ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = gotStar ? 52 : 53;
                this._pending32 = fromMinorState;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            if ((ch2 = this._inputBuffer[this._inputPtr++] & 0xFF) < 32) {
                if (ch2 == 10) {
                    ++this._currInputRow;
                    this._currInputRowStart = this._inputPtr;
                } else if (ch2 == 13) {
                    ++this._currInputRowAlt;
                    this._currInputRowStart = this._inputPtr;
                } else if (ch2 != 9) {
                    this._throwInvalidSpace(ch2);
                }
            } else {
                if (ch2 == 42) {
                    gotStar = true;
                    continue;
                }
                if (ch2 == 47 && gotStar) break;
            }
            gotStar = false;
        }
        return this._startAfterComment(fromMinorState);
    }

    private final JsonToken _startAfterComment(int fromMinorState) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = fromMinorState;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        int ch2 = this._inputBuffer[this._inputPtr++] & 0xFF;
        switch (fromMinorState) {
            case 4: {
                return this._startFieldName(ch2);
            }
            case 5: {
                return this._startFieldNameAfterComma(ch2);
            }
            case 12: {
                return this._startValue(ch2);
            }
            case 13: {
                return this._startValueExpectComma(ch2);
            }
            case 14: {
                return this._startValueExpectColon(ch2);
            }
            case 15: {
                return this._startValueAfterComma(ch2);
            }
        }
        VersionUtil.throwInternal();
        return null;
    }

    protected JsonToken _startFalseToken() throws IOException {
        int ch2;
        byte[] buf;
        int ptr = this._inputPtr;
        if (ptr + 4 < this._inputEnd && (buf = this._inputBuffer)[ptr++] == 97 && buf[ptr++] == 108 && buf[ptr++] == 115 && buf[ptr++] == 101 && ((ch2 = buf[ptr] & 0xFF) < 48 || ch2 == 93 || ch2 == 125)) {
            this._inputPtr = ptr;
            return this._valueComplete(JsonToken.VALUE_FALSE);
        }
        this._minorState = 18;
        return this._finishKeywordToken("false", 1, JsonToken.VALUE_FALSE);
    }

    protected JsonToken _startTrueToken() throws IOException {
        int ch2;
        byte[] buf;
        int ptr = this._inputPtr;
        if (ptr + 3 < this._inputEnd && (buf = this._inputBuffer)[ptr++] == 114 && buf[ptr++] == 117 && buf[ptr++] == 101 && ((ch2 = buf[ptr] & 0xFF) < 48 || ch2 == 93 || ch2 == 125)) {
            this._inputPtr = ptr;
            return this._valueComplete(JsonToken.VALUE_TRUE);
        }
        this._minorState = 17;
        return this._finishKeywordToken("true", 1, JsonToken.VALUE_TRUE);
    }

    protected JsonToken _startNullToken() throws IOException {
        int ch2;
        byte[] buf;
        int ptr = this._inputPtr;
        if (ptr + 3 < this._inputEnd && (buf = this._inputBuffer)[ptr++] == 117 && buf[ptr++] == 108 && buf[ptr++] == 108 && ((ch2 = buf[ptr] & 0xFF) < 48 || ch2 == 93 || ch2 == 125)) {
            this._inputPtr = ptr;
            return this._valueComplete(JsonToken.VALUE_NULL);
        }
        this._minorState = 16;
        return this._finishKeywordToken("null", 1, JsonToken.VALUE_NULL);
    }

    protected JsonToken _finishKeywordToken(String expToken, int matched, JsonToken result) throws IOException {
        int end = expToken.length();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                this._pending32 = matched;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            byte ch2 = this._inputBuffer[this._inputPtr];
            if (matched == end) {
                if (ch2 >= 48 && ch2 != 93 && ch2 != 125) break;
                return this._valueComplete(result);
            }
            if (ch2 != expToken.charAt(matched)) break;
            ++matched;
            ++this._inputPtr;
        }
        this._minorState = 50;
        this._textBuffer.resetWithCopy(expToken, 0, matched);
        return this._finishErrorToken();
    }

    protected JsonToken _finishKeywordTokenWithEOF(String expToken, int matched, JsonToken result) throws IOException {
        if (matched == expToken.length()) {
            this._currToken = result;
            return this._currToken;
        }
        this._textBuffer.resetWithCopy(expToken, 0, matched);
        return this._finishErrorTokenWithEOF();
    }

    protected JsonToken _finishNonStdToken(int type, int matched) throws IOException {
        String expToken = this._nonStdToken(type);
        int end = expToken.length();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                this._nonStdTokenType = type;
                this._pending32 = matched;
                this._minorState = 19;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            byte ch2 = this._inputBuffer[this._inputPtr];
            if (matched == end) {
                if (ch2 >= 48 && ch2 != 93 && ch2 != 125) break;
                return this._valueNonStdNumberComplete(type);
            }
            if (ch2 != expToken.charAt(matched)) break;
            ++matched;
            ++this._inputPtr;
        }
        this._minorState = 50;
        this._textBuffer.resetWithCopy(expToken, 0, matched);
        return this._finishErrorToken();
    }

    protected JsonToken _finishNonStdTokenWithEOF(int type, int matched) throws IOException {
        String expToken = this._nonStdToken(type);
        if (matched == expToken.length()) {
            return this._valueNonStdNumberComplete(type);
        }
        this._textBuffer.resetWithCopy(expToken, 0, matched);
        return this._finishErrorTokenWithEOF();
    }

    protected JsonToken _finishErrorToken() throws IOException {
        while (this._inputPtr < this._inputEnd) {
            byte i2;
            char ch2;
            if (Character.isJavaIdentifierPart(ch2 = (char)(i2 = this._inputBuffer[this._inputPtr++]))) {
                this._textBuffer.append(ch2);
                if (this._textBuffer.size() < 256) continue;
            }
            return this._reportErrorToken(this._textBuffer.contentsAsString());
        }
        this._currToken = JsonToken.NOT_AVAILABLE;
        return this._currToken;
    }

    protected JsonToken _finishErrorTokenWithEOF() throws IOException {
        return this._reportErrorToken(this._textBuffer.contentsAsString());
    }

    protected JsonToken _reportErrorToken(String actualToken) throws IOException {
        this._reportError("Unrecognized token '%s': was expecting %s", this._textBuffer.contentsAsString(), "'null', 'true' or 'false'");
        return JsonToken.NOT_AVAILABLE;
    }

    protected JsonToken _startPositiveNumber(int ch2) throws IOException {
        this._numberNegative = false;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        outBuf[0] = (char)ch2;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 26;
            this._textBuffer.setCurrentLength(1);
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        int outPtr = 1;
        ch2 = this._inputBuffer[this._inputPtr] & 0xFF;
        while (true) {
            if (ch2 < 48) {
                if (ch2 != 46) break;
                this._intLength = outPtr;
                ++this._inputPtr;
                return this._startFloat(outBuf, outPtr, ch2);
            }
            if (ch2 > 57) {
                if (ch2 != 101 && ch2 != 69) break;
                this._intLength = outPtr;
                ++this._inputPtr;
                return this._startFloat(outBuf, outPtr, ch2);
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.expandCurrentSegment();
            }
            outBuf[outPtr++] = (char)ch2;
            if (++this._inputPtr >= this._inputEnd) {
                this._minorState = 26;
                this._textBuffer.setCurrentLength(outPtr);
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            ch2 = this._inputBuffer[this._inputPtr] & 0xFF;
        }
        this._intLength = outPtr;
        this._textBuffer.setCurrentLength(outPtr);
        return this._valueComplete(JsonToken.VALUE_NUMBER_INT);
    }

    protected JsonToken _startNegativeNumber() throws IOException {
        int ch2;
        this._numberNegative = true;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 23;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        if ((ch2 = this._inputBuffer[this._inputPtr++] & 0xFF) <= 48) {
            if (ch2 == 48) {
                return this._finishNumberLeadingNegZeroes();
            }
            this.reportUnexpectedNumberChar(ch2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        } else if (ch2 > 57) {
            if (ch2 == 73) {
                return this._finishNonStdToken(3, 2);
            }
            this.reportUnexpectedNumberChar(ch2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        }
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        outBuf[0] = 45;
        outBuf[1] = (char)ch2;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 26;
            this._textBuffer.setCurrentLength(2);
            this._intLength = 1;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        ch2 = this._inputBuffer[this._inputPtr];
        int outPtr = 2;
        while (true) {
            if (ch2 < 48) {
                if (ch2 != 46) break;
                this._intLength = outPtr - 1;
                ++this._inputPtr;
                return this._startFloat(outBuf, outPtr, ch2);
            }
            if (ch2 > 57) {
                if (ch2 != 101 && ch2 != 69) break;
                this._intLength = outPtr - 1;
                ++this._inputPtr;
                return this._startFloat(outBuf, outPtr, ch2);
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.expandCurrentSegment();
            }
            outBuf[outPtr++] = (char)ch2;
            if (++this._inputPtr >= this._inputEnd) {
                this._minorState = 26;
                this._textBuffer.setCurrentLength(outPtr);
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            ch2 = this._inputBuffer[this._inputPtr] & 0xFF;
        }
        this._intLength = outPtr - 1;
        this._textBuffer.setCurrentLength(outPtr);
        return this._valueComplete(JsonToken.VALUE_NUMBER_INT);
    }

    protected JsonToken _startNumberLeadingZero() throws IOException {
        int ch2;
        int ptr = this._inputPtr;
        if (ptr >= this._inputEnd) {
            this._minorState = 24;
            this._currToken = JsonToken.NOT_AVAILABLE;
            return this._currToken;
        }
        if ((ch2 = this._inputBuffer[ptr++] & 0xFF) < 48) {
            if (ch2 == 46) {
                this._inputPtr = ptr;
                this._intLength = 1;
                char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
                outBuf[0] = 48;
                return this._startFloat(outBuf, 1, ch2);
            }
        } else if (ch2 > 57) {
            if (ch2 == 101 || ch2 == 69) {
                this._inputPtr = ptr;
                this._intLength = 1;
                char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
                outBuf[0] = 48;
                return this._startFloat(outBuf, 1, ch2);
            }
            if (ch2 != 93 && ch2 != 125) {
                this.reportUnexpectedNumberChar(ch2, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
            }
        } else {
            return this._finishNumberLeadingZeroes();
        }
        return this._valueCompleteInt(0, "0");
    }

    protected JsonToken _finishNumberMinus(int ch2) throws IOException {
        if (ch2 <= 48) {
            if (ch2 == 48) {
                return this._finishNumberLeadingNegZeroes();
            }
            this.reportUnexpectedNumberChar(ch2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        } else if (ch2 > 57) {
            if (ch2 == 73) {
                return this._finishNonStdToken(3, 2);
            }
            this.reportUnexpectedNumberChar(ch2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        }
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        outBuf[0] = 45;
        outBuf[1] = (char)ch2;
        this._intLength = 1;
        return this._finishNumberIntegralPart(outBuf, 2);
    }

    protected JsonToken _finishNumberLeadingZeroes() throws IOException {
        block7: {
            int ch2;
            do {
                if (this._inputPtr >= this._inputEnd) {
                    this._minorState = 24;
                    this._currToken = JsonToken.NOT_AVAILABLE;
                    return this._currToken;
                }
                if ((ch2 = this._inputBuffer[this._inputPtr++] & 0xFF) < 48) {
                    if (ch2 == 46) {
                        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
                        outBuf[0] = 48;
                        this._intLength = 1;
                        return this._startFloat(outBuf, 1, ch2);
                    }
                    break block7;
                }
                if (ch2 > 57) {
                    if (ch2 == 101 || ch2 == 69) {
                        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
                        outBuf[0] = 48;
                        this._intLength = 1;
                        return this._startFloat(outBuf, 1, ch2);
                    }
                    if (ch2 != 93 && ch2 != 125) {
                        this.reportUnexpectedNumberChar(ch2, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
                    }
                    break block7;
                }
                if (this.isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) continue;
                this.reportInvalidNumber("Leading zeroes not allowed");
            } while (ch2 == 48);
            char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
            outBuf[0] = (char)ch2;
            this._intLength = 1;
            return this._finishNumberIntegralPart(outBuf, 1);
        }
        --this._inputPtr;
        return this._valueCompleteInt(0, "0");
    }

    protected JsonToken _finishNumberLeadingNegZeroes() throws IOException {
        block7: {
            int ch2;
            do {
                if (this._inputPtr >= this._inputEnd) {
                    this._minorState = 25;
                    this._currToken = JsonToken.NOT_AVAILABLE;
                    return this._currToken;
                }
                if ((ch2 = this._inputBuffer[this._inputPtr++] & 0xFF) < 48) {
                    if (ch2 == 46) {
                        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
                        outBuf[0] = 45;
                        outBuf[1] = 48;
                        this._intLength = 1;
                        return this._startFloat(outBuf, 2, ch2);
                    }
                    break block7;
                }
                if (ch2 > 57) {
                    if (ch2 == 101 || ch2 == 69) {
                        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
                        outBuf[0] = 45;
                        outBuf[1] = 48;
                        this._intLength = 1;
                        return this._startFloat(outBuf, 2, ch2);
                    }
                    if (ch2 != 93 && ch2 != 125) {
                        this.reportUnexpectedNumberChar(ch2, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
                    }
                    break block7;
                }
                if (this.isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) continue;
                this.reportInvalidNumber("Leading zeroes not allowed");
            } while (ch2 == 48);
            char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
            outBuf[0] = 45;
            outBuf[1] = (char)ch2;
            this._intLength = 1;
            return this._finishNumberIntegralPart(outBuf, 2);
        }
        --this._inputPtr;
        return this._valueCompleteInt(0, "0");
    }

    protected JsonToken _finishNumberIntegralPart(char[] outBuf, int outPtr) throws IOException {
        int negMod;
        int n2 = negMod = this._numberNegative ? -1 : 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 26;
                this._textBuffer.setCurrentLength(outPtr);
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            int ch2 = this._inputBuffer[this._inputPtr] & 0xFF;
            if (ch2 < 48) {
                if (ch2 != 46) break;
                this._intLength = outPtr + negMod;
                ++this._inputPtr;
                return this._startFloat(outBuf, outPtr, ch2);
            }
            if (ch2 > 57) {
                if (ch2 != 101 && ch2 != 69) break;
                this._intLength = outPtr + negMod;
                ++this._inputPtr;
                return this._startFloat(outBuf, outPtr, ch2);
            }
            ++this._inputPtr;
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.expandCurrentSegment();
            }
            outBuf[outPtr++] = (char)ch2;
        }
        this._intLength = outPtr + negMod;
        this._textBuffer.setCurrentLength(outPtr);
        return this._valueComplete(JsonToken.VALUE_NUMBER_INT);
    }

    protected JsonToken _startFloat(char[] outBuf, int outPtr, int ch2) throws IOException {
        int fractLen = 0;
        if (ch2 == 46) {
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.expandCurrentSegment();
            }
            outBuf[outPtr++] = 46;
            while (true) {
                if (this._inputPtr >= this._inputEnd) {
                    this._textBuffer.setCurrentLength(outPtr);
                    this._minorState = 30;
                    this._fractLength = fractLen;
                    this._currToken = JsonToken.NOT_AVAILABLE;
                    return this._currToken;
                }
                if ((ch2 = this._inputBuffer[this._inputPtr++]) < 48 || ch2 > 57) {
                    ch2 &= 0xFF;
                    if (fractLen != 0) break;
                    this.reportUnexpectedNumberChar(ch2, "Decimal point not followed by a digit");
                    break;
                }
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.expandCurrentSegment();
                }
                outBuf[outPtr++] = (char)ch2;
                ++fractLen;
            }
        }
        this._fractLength = fractLen;
        int expLen = 0;
        if (ch2 == 101 || ch2 == 69) {
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.expandCurrentSegment();
            }
            outBuf[outPtr++] = (char)ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._textBuffer.setCurrentLength(outPtr);
                this._minorState = 31;
                this._expLength = 0;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            if ((ch2 = this._inputBuffer[this._inputPtr++]) == 45 || ch2 == 43) {
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.expandCurrentSegment();
                }
                outBuf[outPtr++] = (char)ch2;
                if (this._inputPtr >= this._inputEnd) {
                    this._textBuffer.setCurrentLength(outPtr);
                    this._minorState = 32;
                    this._expLength = 0;
                    this._currToken = JsonToken.NOT_AVAILABLE;
                    return this._currToken;
                }
                ch2 = this._inputBuffer[this._inputPtr++];
            }
            while (ch2 >= 48 && ch2 <= 57) {
                ++expLen;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.expandCurrentSegment();
                }
                outBuf[outPtr++] = (char)ch2;
                if (this._inputPtr >= this._inputEnd) {
                    this._textBuffer.setCurrentLength(outPtr);
                    this._minorState = 32;
                    this._expLength = expLen;
                    this._currToken = JsonToken.NOT_AVAILABLE;
                    return this._currToken;
                }
                ch2 = this._inputBuffer[this._inputPtr++];
            }
            ch2 &= 0xFF;
            if (expLen == 0) {
                this.reportUnexpectedNumberChar(ch2, "Exponent indicator not followed by a digit");
            }
        }
        --this._inputPtr;
        this._textBuffer.setCurrentLength(outPtr);
        this._expLength = expLen;
        return this._valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
    }

    protected JsonToken _finishFloatFraction() throws IOException {
        byte ch2;
        int fractLen = this._fractLength;
        char[] outBuf = this._textBuffer.getBufferWithoutReset();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        while ((ch2 = this._inputBuffer[this._inputPtr++]) >= 48 && ch2 <= 57) {
            ++fractLen;
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.expandCurrentSegment();
            }
            outBuf[outPtr++] = (char)ch2;
            if (this._inputPtr < this._inputEnd) continue;
            this._textBuffer.setCurrentLength(outPtr);
            this._fractLength = fractLen;
            return JsonToken.NOT_AVAILABLE;
        }
        if (fractLen == 0) {
            this.reportUnexpectedNumberChar(ch2, "Decimal point not followed by a digit");
        }
        this._fractLength = fractLen;
        this._textBuffer.setCurrentLength(outPtr);
        if (ch2 == 101 || ch2 == 69) {
            this._textBuffer.append((char)ch2);
            this._expLength = 0;
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 31;
                return JsonToken.NOT_AVAILABLE;
            }
            this._minorState = 32;
            return this._finishFloatExponent(true, this._inputBuffer[this._inputPtr++] & 0xFF);
        }
        --this._inputPtr;
        this._textBuffer.setCurrentLength(outPtr);
        this._expLength = 0;
        return this._valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
    }

    protected JsonToken _finishFloatExponent(boolean checkSign, int ch2) throws IOException {
        if (checkSign) {
            this._minorState = 32;
            if (ch2 == 45 || ch2 == 43) {
                this._textBuffer.append((char)ch2);
                if (this._inputPtr >= this._inputEnd) {
                    this._minorState = 32;
                    this._expLength = 0;
                    return JsonToken.NOT_AVAILABLE;
                }
                ch2 = this._inputBuffer[this._inputPtr++];
            }
        }
        char[] outBuf = this._textBuffer.getBufferWithoutReset();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        int expLen = this._expLength;
        while (ch2 >= 48 && ch2 <= 57) {
            ++expLen;
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.expandCurrentSegment();
            }
            outBuf[outPtr++] = (char)ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._textBuffer.setCurrentLength(outPtr);
                this._expLength = expLen;
                return JsonToken.NOT_AVAILABLE;
            }
            ch2 = this._inputBuffer[this._inputPtr++];
        }
        ch2 &= 0xFF;
        if (expLen == 0) {
            this.reportUnexpectedNumberChar(ch2, "Exponent indicator not followed by a digit");
        }
        --this._inputPtr;
        this._textBuffer.setCurrentLength(outPtr);
        this._expLength = expLen;
        return this._valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
    }

    private final String _fastParseName() throws IOException {
        int q0;
        byte[] input = this._inputBuffer;
        int[] codes = _icLatin1;
        int ptr = this._inputPtr;
        if (codes[q0 = input[ptr++] & 0xFF] == 0) {
            int i2;
            if (codes[i2 = input[ptr++] & 0xFF] == 0) {
                int q2 = q0 << 8 | i2;
                if (codes[i2 = input[ptr++] & 0xFF] == 0) {
                    q2 = q2 << 8 | i2;
                    if (codes[i2 = input[ptr++] & 0xFF] == 0) {
                        q2 = q2 << 8 | i2;
                        if (codes[i2 = input[ptr++] & 0xFF] == 0) {
                            this._quad1 = q2;
                            return this._parseMediumName(ptr, i2);
                        }
                        if (i2 == 34) {
                            this._inputPtr = ptr;
                            return this._findName(q2, 4);
                        }
                        return null;
                    }
                    if (i2 == 34) {
                        this._inputPtr = ptr;
                        return this._findName(q2, 3);
                    }
                    return null;
                }
                if (i2 == 34) {
                    this._inputPtr = ptr;
                    return this._findName(q2, 2);
                }
                return null;
            }
            if (i2 == 34) {
                this._inputPtr = ptr;
                return this._findName(q0, 1);
            }
            return null;
        }
        if (q0 == 34) {
            this._inputPtr = ptr;
            return "";
        }
        return null;
    }

    private final String _parseMediumName(int ptr, int q2) throws IOException {
        int i2;
        int[] codes = _icLatin1;
        byte[] input = this._inputBuffer;
        if (codes[i2 = input[ptr++] & 0xFF] == 0) {
            q2 = q2 << 8 | i2;
            if (codes[i2 = input[ptr++] & 0xFF] == 0) {
                q2 = q2 << 8 | i2;
                if (codes[i2 = input[ptr++] & 0xFF] == 0) {
                    q2 = q2 << 8 | i2;
                    if (codes[i2 = input[ptr++] & 0xFF] == 0) {
                        return this._parseMediumName2(ptr, i2, q2);
                    }
                    if (i2 == 34) {
                        this._inputPtr = ptr;
                        return this._findName(this._quad1, q2, 4);
                    }
                    return null;
                }
                if (i2 == 34) {
                    this._inputPtr = ptr;
                    return this._findName(this._quad1, q2, 3);
                }
                return null;
            }
            if (i2 == 34) {
                this._inputPtr = ptr;
                return this._findName(this._quad1, q2, 2);
            }
            return null;
        }
        if (i2 == 34) {
            this._inputPtr = ptr;
            return this._findName(this._quad1, q2, 1);
        }
        return null;
    }

    private final String _parseMediumName2(int ptr, int q3, int q2) throws IOException {
        int i2;
        int[] codes = _icLatin1;
        byte[] input = this._inputBuffer;
        if (codes[i2 = input[ptr++] & 0xFF] != 0) {
            if (i2 == 34) {
                this._inputPtr = ptr;
                return this._findName(this._quad1, q2, q3, 1);
            }
            return null;
        }
        q3 = q3 << 8 | i2;
        if (codes[i2 = input[ptr++] & 0xFF] != 0) {
            if (i2 == 34) {
                this._inputPtr = ptr;
                return this._findName(this._quad1, q2, q3, 2);
            }
            return null;
        }
        q3 = q3 << 8 | i2;
        if (codes[i2 = input[ptr++] & 0xFF] != 0) {
            if (i2 == 34) {
                this._inputPtr = ptr;
                return this._findName(this._quad1, q2, q3, 3);
            }
            return null;
        }
        q3 = q3 << 8 | i2;
        if ((i2 = input[ptr++] & 0xFF) == 34) {
            this._inputPtr = ptr;
            return this._findName(this._quad1, q2, q3, 4);
        }
        return null;
    }

    private final JsonToken _parseEscapedName(int qlen, int currQuad, int currQuadBytes) throws IOException {
        int[] quads = this._quadBuffer;
        int[] codes = _icLatin1;
        while (true) {
            int ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._quadLength = qlen;
                this._pending32 = currQuad;
                this._pendingBytes = currQuadBytes;
                this._minorState = 7;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            if (codes[ch2 = this._inputBuffer[this._inputPtr++] & 0xFF] == 0) {
                if (currQuadBytes < 4) {
                    ++currQuadBytes;
                    currQuad = currQuad << 8 | ch2;
                    continue;
                }
                if (qlen >= quads.length) {
                    this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
                }
                quads[qlen++] = currQuad;
                currQuad = ch2;
                currQuadBytes = 1;
                continue;
            }
            if (ch2 == 34) break;
            if (ch2 != 92) {
                this._throwUnquotedSpace(ch2, "name");
            } else {
                ch2 = this._decodeCharEscape();
                if (ch2 < 0) {
                    this._minorState = 8;
                    this._minorStateAfterSplit = 7;
                    this._quadLength = qlen;
                    this._pending32 = currQuad;
                    this._pendingBytes = currQuadBytes;
                    this._currToken = JsonToken.NOT_AVAILABLE;
                    return this._currToken;
                }
            }
            if (qlen >= quads.length) {
                this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
            }
            if (ch2 > 127) {
                if (currQuadBytes >= 4) {
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
                        quads[qlen++] = currQuad;
                        currQuad = 0;
                        currQuadBytes = 0;
                    }
                    currQuad = currQuad << 8 | (0x80 | ch2 >> 6 & 0x3F);
                    ++currQuadBytes;
                }
                ch2 = 0x80 | ch2 & 0x3F;
            }
            if (currQuadBytes < 4) {
                ++currQuadBytes;
                currQuad = currQuad << 8 | ch2;
                continue;
            }
            quads[qlen++] = currQuad;
            currQuad = ch2;
            currQuadBytes = 1;
        }
        if (currQuadBytes > 0) {
            if (qlen >= quads.length) {
                this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
            }
            quads[qlen++] = NonBlockingJsonParser._padLastQuad(currQuad, currQuadBytes);
        } else if (qlen == 0) {
            return this._fieldComplete("");
        }
        String name = this._symbols.findName(quads, qlen);
        if (name == null) {
            name = this._addName(quads, qlen, currQuadBytes);
        }
        return this._fieldComplete(name);
    }

    private JsonToken _handleOddName(int ch2) throws IOException {
        int[] codes;
        switch (ch2) {
            case 35: {
                if (!JsonParser.Feature.ALLOW_YAML_COMMENTS.enabledIn(this._features)) break;
                return this._finishHashComment(4);
            }
            case 47: {
                return this._startSlashComment(4);
            }
            case 39: {
                if (!this.isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) break;
                return this._finishAposName(0, 0, 0);
            }
            case 93: {
                return this._closeArrayScope();
            }
        }
        if (!this.isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            char c10 = (char)ch2;
            this._reportUnexpectedChar(c10, "was expecting double-quote to start field name");
        }
        if ((codes = CharTypes.getInputCodeUtf8JsNames())[ch2] != 0) {
            this._reportUnexpectedChar(ch2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        return this._finishUnquotedName(0, ch2, 1);
    }

    private JsonToken _finishUnquotedName(int qlen, int currQuad, int currQuadBytes) throws IOException {
        String name;
        int[] quads = this._quadBuffer;
        int[] codes = CharTypes.getInputCodeUtf8JsNames();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                this._quadLength = qlen;
                this._pending32 = currQuad;
                this._pendingBytes = currQuadBytes;
                this._minorState = 10;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            int ch2 = this._inputBuffer[this._inputPtr] & 0xFF;
            if (codes[ch2] != 0) break;
            ++this._inputPtr;
            if (currQuadBytes < 4) {
                ++currQuadBytes;
                currQuad = currQuad << 8 | ch2;
                continue;
            }
            if (qlen >= quads.length) {
                this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
            }
            quads[qlen++] = currQuad;
            currQuad = ch2;
            currQuadBytes = 1;
        }
        if (currQuadBytes > 0) {
            if (qlen >= quads.length) {
                this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
            }
            quads[qlen++] = currQuad;
        }
        if ((name = this._symbols.findName(quads, qlen)) == null) {
            name = this._addName(quads, qlen, currQuadBytes);
        }
        return this._fieldComplete(name);
    }

    private JsonToken _finishAposName(int qlen, int currQuad, int currQuadBytes) throws IOException {
        int[] quads = this._quadBuffer;
        int[] codes = _icLatin1;
        while (true) {
            int ch2;
            if (this._inputPtr >= this._inputEnd) {
                this._quadLength = qlen;
                this._pending32 = currQuad;
                this._pendingBytes = currQuadBytes;
                this._minorState = 9;
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            if ((ch2 = this._inputBuffer[this._inputPtr++] & 0xFF) == 39) break;
            if (ch2 != 34 && codes[ch2] != 0) {
                if (ch2 != 92) {
                    this._throwUnquotedSpace(ch2, "name");
                } else {
                    ch2 = this._decodeCharEscape();
                    if (ch2 < 0) {
                        this._minorState = 8;
                        this._minorStateAfterSplit = 9;
                        this._quadLength = qlen;
                        this._pending32 = currQuad;
                        this._pendingBytes = currQuadBytes;
                        this._currToken = JsonToken.NOT_AVAILABLE;
                        return this._currToken;
                    }
                }
                if (ch2 > 127) {
                    if (currQuadBytes >= 4) {
                        if (qlen >= quads.length) {
                            this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
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
                                this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
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
                continue;
            }
            if (qlen >= quads.length) {
                this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
            }
            quads[qlen++] = currQuad;
            currQuad = ch2;
            currQuadBytes = 1;
        }
        if (currQuadBytes > 0) {
            if (qlen >= quads.length) {
                this._quadBuffer = quads = NonBlockingJsonParser.growArrayBy(quads, quads.length);
            }
            quads[qlen++] = NonBlockingJsonParser._padLastQuad(currQuad, currQuadBytes);
        } else if (qlen == 0) {
            return this._fieldComplete("");
        }
        String name = this._symbols.findName(quads, qlen);
        if (name == null) {
            name = this._addName(quads, qlen, currQuadBytes);
        }
        return this._fieldComplete(name);
    }

    protected final JsonToken _finishFieldWithEscape() throws IOException {
        int ch2 = this._decodeSplitEscaped(this._quoted32, this._quotedDigits);
        if (ch2 < 0) {
            this._minorState = 8;
            return JsonToken.NOT_AVAILABLE;
        }
        if (this._quadLength >= this._quadBuffer.length) {
            this._quadBuffer = NonBlockingJsonParser.growArrayBy(this._quadBuffer, 32);
        }
        int currQuad = this._pending32;
        int currQuadBytes = this._pendingBytes;
        if (ch2 > 127) {
            if (currQuadBytes >= 4) {
                this._quadBuffer[this._quadLength++] = currQuad;
                currQuad = 0;
                currQuadBytes = 0;
            }
            if (ch2 < 2048) {
                currQuad = currQuad << 8 | (0xC0 | ch2 >> 6);
                ++currQuadBytes;
            } else {
                currQuad = currQuad << 8 | (0xE0 | ch2 >> 12);
                if (++currQuadBytes >= 4) {
                    this._quadBuffer[this._quadLength++] = currQuad;
                    currQuad = 0;
                    currQuadBytes = 0;
                }
                currQuad = currQuad << 8 | (0x80 | ch2 >> 6 & 0x3F);
                ++currQuadBytes;
            }
            ch2 = 0x80 | ch2 & 0x3F;
        }
        if (currQuadBytes < 4) {
            ++currQuadBytes;
            currQuad = currQuad << 8 | ch2;
        } else {
            this._quadBuffer[this._quadLength++] = currQuad;
            currQuad = ch2;
            currQuadBytes = 1;
        }
        if (this._minorStateAfterSplit == 9) {
            return this._finishAposName(this._quadLength, currQuad, currQuadBytes);
        }
        return this._parseEscapedName(this._quadLength, currQuad, currQuadBytes);
    }

    private int _decodeSplitEscaped(int value, int bytesRead) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            this._quoted32 = value;
            this._quotedDigits = bytesRead;
            return -1;
        }
        int c10 = this._inputBuffer[this._inputPtr++];
        if (bytesRead == -1) {
            switch (c10) {
                case 98: {
                    return 8;
                }
                case 116: {
                    return 9;
                }
                case 110: {
                    return 10;
                }
                case 102: {
                    return 12;
                }
                case 114: {
                    return 13;
                }
                case 34: 
                case 47: 
                case 92: {
                    return c10;
                }
                case 117: {
                    break;
                }
                default: {
                    char ch2 = (char)c10;
                    return this._handleUnrecognizedCharacterEscape(ch2);
                }
            }
            if (this._inputPtr >= this._inputEnd) {
                this._quotedDigits = 0;
                this._quoted32 = 0;
                return -1;
            }
            c10 = this._inputBuffer[this._inputPtr++];
            bytesRead = 0;
        }
        c10 &= 0xFF;
        while (true) {
            int digit;
            if ((digit = CharTypes.charToHex(c10)) < 0) {
                this._reportUnexpectedChar(c10, "expected a hex-digit for character escape sequence");
            }
            value = value << 4 | digit;
            if (++bytesRead == 4) {
                return value;
            }
            if (this._inputPtr >= this._inputEnd) {
                this._quotedDigits = bytesRead;
                this._quoted32 = value;
                return -1;
            }
            c10 = this._inputBuffer[this._inputPtr++] & 0xFF;
        }
    }

    protected JsonToken _startString() throws IOException {
        int ptr;
        int outPtr = 0;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int[] codes = _icUTF8;
        int max = Math.min(this._inputEnd, ptr + outBuf.length);
        byte[] inputBuffer = this._inputBuffer;
        for (ptr = this._inputPtr; ptr < max; ++ptr) {
            int c10 = inputBuffer[ptr] & 0xFF;
            if (codes[c10] != 0) {
                if (c10 != 34) break;
                this._inputPtr = ptr + 1;
                this._textBuffer.setCurrentLength(outPtr);
                return this._valueComplete(JsonToken.VALUE_STRING);
            }
            outBuf[outPtr++] = (char)c10;
        }
        this._textBuffer.setCurrentLength(outPtr);
        this._inputPtr = ptr;
        return this._finishRegularString();
    }

    private final JsonToken _finishRegularString() throws IOException {
        int[] codes = _icUTF8;
        byte[] inputBuffer = this._inputBuffer;
        char[] outBuf = this._textBuffer.getBufferWithoutReset();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        int ptr = this._inputPtr;
        int safeEnd = this._inputEnd - 5;
        block6: while (true) {
            int c10;
            if (ptr >= this._inputEnd) {
                this._inputPtr = ptr;
                this._minorState = 40;
                this._textBuffer.setCurrentLength(outPtr);
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            int max = Math.min(this._inputEnd, ptr + (outBuf.length - outPtr));
            while (true) {
                if (ptr >= max) continue block6;
                if (codes[c10 = inputBuffer[ptr++] & 0xFF] != 0) break;
                outBuf[outPtr++] = (char)c10;
            }
            if (c10 == 34) {
                this._inputPtr = ptr;
                this._textBuffer.setCurrentLength(outPtr);
                return this._valueComplete(JsonToken.VALUE_STRING);
            }
            if (ptr >= safeEnd) {
                this._inputPtr = ptr;
                this._textBuffer.setCurrentLength(outPtr);
                if (!this._decodeSplitMultiByte(c10, codes[c10], ptr < this._inputEnd)) {
                    this._minorStateAfterSplit = 40;
                    this._currToken = JsonToken.NOT_AVAILABLE;
                    return this._currToken;
                }
                outBuf = this._textBuffer.getBufferWithoutReset();
                outPtr = this._textBuffer.getCurrentSegmentSize();
                ptr = this._inputPtr;
                continue;
            }
            switch (codes[c10]) {
                case 1: {
                    this._inputPtr = ptr;
                    c10 = this._decodeFastCharEscape();
                    ptr = this._inputPtr;
                    break;
                }
                case 2: {
                    c10 = this._decodeUTF8_2(c10, this._inputBuffer[ptr++]);
                    break;
                }
                case 3: {
                    c10 = this._decodeUTF8_3(c10, this._inputBuffer[ptr++], this._inputBuffer[ptr++]);
                    break;
                }
                case 4: {
                    c10 = this._decodeUTF8_4(c10, this._inputBuffer[ptr++], this._inputBuffer[ptr++], this._inputBuffer[ptr++]);
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
                        break;
                    }
                    this._reportInvalidChar(c10);
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = (char)c10;
        }
    }

    protected JsonToken _startAposString() throws IOException {
        int ptr;
        int outPtr = 0;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int[] codes = _icUTF8;
        int max = Math.min(this._inputEnd, ptr + outBuf.length);
        byte[] inputBuffer = this._inputBuffer;
        for (ptr = this._inputPtr; ptr < max; ++ptr) {
            int c10 = inputBuffer[ptr] & 0xFF;
            if (c10 == 39) {
                this._inputPtr = ptr + 1;
                this._textBuffer.setCurrentLength(outPtr);
                return this._valueComplete(JsonToken.VALUE_STRING);
            }
            if (codes[c10] != 0) break;
            outBuf[outPtr++] = (char)c10;
        }
        this._textBuffer.setCurrentLength(outPtr);
        this._inputPtr = ptr;
        return this._finishAposString();
    }

    private final JsonToken _finishAposString() throws IOException {
        int[] codes = _icUTF8;
        byte[] inputBuffer = this._inputBuffer;
        char[] outBuf = this._textBuffer.getBufferWithoutReset();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        int ptr = this._inputPtr;
        int safeEnd = this._inputEnd - 5;
        block6: while (true) {
            int c10;
            if (ptr >= this._inputEnd) {
                this._inputPtr = ptr;
                this._minorState = 45;
                this._textBuffer.setCurrentLength(outPtr);
                this._currToken = JsonToken.NOT_AVAILABLE;
                return this._currToken;
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            int max = Math.min(this._inputEnd, ptr + (outBuf.length - outPtr));
            while (true) {
                if (ptr >= max) continue block6;
                if (codes[c10 = inputBuffer[ptr++] & 0xFF] != 0 && c10 != 34) break;
                if (c10 == 39) {
                    this._inputPtr = ptr;
                    this._textBuffer.setCurrentLength(outPtr);
                    return this._valueComplete(JsonToken.VALUE_STRING);
                }
                outBuf[outPtr++] = (char)c10;
            }
            if (ptr >= safeEnd) {
                this._inputPtr = ptr;
                this._textBuffer.setCurrentLength(outPtr);
                if (!this._decodeSplitMultiByte(c10, codes[c10], ptr < this._inputEnd)) {
                    this._minorStateAfterSplit = 45;
                    this._currToken = JsonToken.NOT_AVAILABLE;
                    return this._currToken;
                }
                outBuf = this._textBuffer.getBufferWithoutReset();
                outPtr = this._textBuffer.getCurrentSegmentSize();
                ptr = this._inputPtr;
                continue;
            }
            switch (codes[c10]) {
                case 1: {
                    this._inputPtr = ptr;
                    c10 = this._decodeFastCharEscape();
                    ptr = this._inputPtr;
                    break;
                }
                case 2: {
                    c10 = this._decodeUTF8_2(c10, this._inputBuffer[ptr++]);
                    break;
                }
                case 3: {
                    c10 = this._decodeUTF8_3(c10, this._inputBuffer[ptr++], this._inputBuffer[ptr++]);
                    break;
                }
                case 4: {
                    c10 = this._decodeUTF8_4(c10, this._inputBuffer[ptr++], this._inputBuffer[ptr++], this._inputBuffer[ptr++]);
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
                        break;
                    }
                    this._reportInvalidChar(c10);
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            outBuf[outPtr++] = (char)c10;
        }
    }

    private final boolean _decodeSplitMultiByte(int c10, int type, boolean gotNext) throws IOException {
        switch (type) {
            case 1: {
                c10 = this._decodeSplitEscaped(0, -1);
                if (c10 < 0) {
                    this._minorState = 41;
                    return false;
                }
                this._textBuffer.append((char)c10);
                return true;
            }
            case 2: {
                if (gotNext) {
                    c10 = this._decodeUTF8_2(c10, this._inputBuffer[this._inputPtr++]);
                    this._textBuffer.append((char)c10);
                    return true;
                }
                this._minorState = 42;
                this._pending32 = c10;
                return false;
            }
            case 3: {
                c10 &= 0xF;
                if (gotNext) {
                    return this._decodeSplitUTF8_3(c10, 1, this._inputBuffer[this._inputPtr++]);
                }
                this._minorState = 43;
                this._pending32 = c10;
                this._pendingBytes = 1;
                return false;
            }
            case 4: {
                c10 &= 7;
                if (gotNext) {
                    return this._decodeSplitUTF8_4(c10, 1, this._inputBuffer[this._inputPtr++]);
                }
                this._pending32 = c10;
                this._pendingBytes = 1;
                this._minorState = 44;
                return false;
            }
        }
        if (c10 < 32) {
            this._throwUnquotedSpace(c10, "string value");
        } else {
            this._reportInvalidChar(c10);
        }
        this._textBuffer.append((char)c10);
        return true;
    }

    private final boolean _decodeSplitUTF8_3(int prev, int prevCount, int next) throws IOException {
        if (prevCount == 1) {
            if ((next & 0xC0) != 128) {
                this._reportInvalidOther(next & 0xFF, this._inputPtr);
            }
            prev = prev << 6 | next & 0x3F;
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 43;
                this._pending32 = prev;
                this._pendingBytes = 2;
                return false;
            }
            next = this._inputBuffer[this._inputPtr++];
        }
        if ((next & 0xC0) != 128) {
            this._reportInvalidOther(next & 0xFF, this._inputPtr);
        }
        this._textBuffer.append((char)(prev << 6 | next & 0x3F));
        return true;
    }

    private final boolean _decodeSplitUTF8_4(int prev, int prevCount, int next) throws IOException {
        if (prevCount == 1) {
            if ((next & 0xC0) != 128) {
                this._reportInvalidOther(next & 0xFF, this._inputPtr);
            }
            prev = prev << 6 | next & 0x3F;
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 44;
                this._pending32 = prev;
                this._pendingBytes = 2;
                return false;
            }
            prevCount = 2;
            next = this._inputBuffer[this._inputPtr++];
        }
        if (prevCount == 2) {
            if ((next & 0xC0) != 128) {
                this._reportInvalidOther(next & 0xFF, this._inputPtr);
            }
            prev = prev << 6 | next & 0x3F;
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 44;
                this._pending32 = prev;
                this._pendingBytes = 3;
                return false;
            }
            next = this._inputBuffer[this._inputPtr++];
        }
        if ((next & 0xC0) != 128) {
            this._reportInvalidOther(next & 0xFF, this._inputPtr);
        }
        int c10 = (prev << 6 | next & 0x3F) - 65536;
        this._textBuffer.append((char)(0xD800 | c10 >> 10));
        c10 = 0xDC00 | c10 & 0x3FF;
        this._textBuffer.append((char)c10);
        return true;
    }

    private final int _decodeCharEscape() throws IOException {
        int left = this._inputEnd - this._inputPtr;
        if (left < 5) {
            return this._decodeSplitEscaped(0, -1);
        }
        return this._decodeFastCharEscape();
    }

    private final int _decodeFastCharEscape() throws IOException {
        int digit;
        byte c10 = this._inputBuffer[this._inputPtr++];
        switch (c10) {
            case 98: {
                return 8;
            }
            case 116: {
                return 9;
            }
            case 110: {
                return 10;
            }
            case 102: {
                return 12;
            }
            case 114: {
                return 13;
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
                char ch2 = (char)c10;
                return this._handleUnrecognizedCharacterEscape(ch2);
            }
        }
        byte ch3 = this._inputBuffer[this._inputPtr++];
        int result = digit = CharTypes.charToHex(ch3);
        if (digit >= 0 && (digit = CharTypes.charToHex(ch3 = this._inputBuffer[this._inputPtr++])) >= 0) {
            result = result << 4 | digit;
            if ((digit = CharTypes.charToHex(ch3 = this._inputBuffer[this._inputPtr++])) >= 0) {
                result = result << 4 | digit;
                if ((digit = CharTypes.charToHex(ch3 = this._inputBuffer[this._inputPtr++])) >= 0) {
                    return result << 4 | digit;
                }
            }
        }
        this._reportUnexpectedChar(ch3 & 0xFF, "expected a hex-digit for character escape sequence");
        return -1;
    }

    private final int _decodeUTF8_2(int c10, int d10) throws IOException {
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF, this._inputPtr);
        }
        return (c10 & 0x1F) << 6 | d10 & 0x3F;
    }

    private final int _decodeUTF8_3(int c10, int d10, int e10) throws IOException {
        c10 &= 0xF;
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF, this._inputPtr);
        }
        c10 = c10 << 6 | d10 & 0x3F;
        if ((e10 & 0xC0) != 128) {
            this._reportInvalidOther(e10 & 0xFF, this._inputPtr);
        }
        return c10 << 6 | e10 & 0x3F;
    }

    private final int _decodeUTF8_4(int c10, int d10, int e10, int f10) throws IOException {
        if ((d10 & 0xC0) != 128) {
            this._reportInvalidOther(d10 & 0xFF, this._inputPtr);
        }
        c10 = (c10 & 7) << 6 | d10 & 0x3F;
        if ((e10 & 0xC0) != 128) {
            this._reportInvalidOther(e10 & 0xFF, this._inputPtr);
        }
        c10 = c10 << 6 | e10 & 0x3F;
        if ((f10 & 0xC0) != 128) {
            this._reportInvalidOther(f10 & 0xFF, this._inputPtr);
        }
        return (c10 << 6 | f10 & 0x3F) - 65536;
    }
}

