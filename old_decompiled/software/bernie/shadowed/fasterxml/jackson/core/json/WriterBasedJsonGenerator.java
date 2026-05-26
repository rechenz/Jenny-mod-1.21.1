/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import software.bernie.shadowed.fasterxml.jackson.core.Base64Variant;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerationException;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonStreamContext;
import software.bernie.shadowed.fasterxml.jackson.core.ObjectCodec;
import software.bernie.shadowed.fasterxml.jackson.core.SerializableString;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharTypes;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharacterEscapes;
import software.bernie.shadowed.fasterxml.jackson.core.io.IOContext;
import software.bernie.shadowed.fasterxml.jackson.core.io.NumberOutput;
import software.bernie.shadowed.fasterxml.jackson.core.json.JsonGeneratorImpl;
import software.bernie.shadowed.fasterxml.jackson.core.json.JsonWriteContext;

public class WriterBasedJsonGenerator
extends JsonGeneratorImpl {
    protected static final int SHORT_WRITE = 32;
    protected static final char[] HEX_CHARS = CharTypes.copyHexChars();
    protected final Writer _writer;
    protected char _quoteChar = (char)34;
    protected char[] _outputBuffer;
    protected int _outputHead;
    protected int _outputTail;
    protected int _outputEnd;
    protected char[] _entityBuffer;
    protected SerializableString _currentEscape;
    protected char[] _charBuffer;

    public WriterBasedJsonGenerator(IOContext ctxt, int features, ObjectCodec codec, Writer w2) {
        super(ctxt, features, codec);
        this._writer = w2;
        this._outputBuffer = ctxt.allocConcatBuffer();
        this._outputEnd = this._outputBuffer.length;
    }

    @Override
    public Object getOutputTarget() {
        return this._writer;
    }

    @Override
    public int getOutputBuffered() {
        int len = this._outputTail - this._outputHead;
        return Math.max(0, len);
    }

    @Override
    public boolean canWriteFormattedNumbers() {
        return true;
    }

    @Override
    public void writeFieldName(String name) throws IOException {
        int status = this._writeContext.writeFieldName(name);
        if (status == 4) {
            this._reportError("Can not write a field name, expecting a value");
        }
        this._writeFieldName(name, status == 1);
    }

    @Override
    public void writeFieldName(SerializableString name) throws IOException {
        int status = this._writeContext.writeFieldName(name.getValue());
        if (status == 4) {
            this._reportError("Can not write a field name, expecting a value");
        }
        this._writeFieldName(name, status == 1);
    }

    protected final void _writeFieldName(String name, boolean commaBefore) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            this._writePPFieldName(name, commaBefore);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            this._flushBuffer();
        }
        if (commaBefore) {
            this._outputBuffer[this._outputTail++] = 44;
        }
        if (this._cfgUnqNames) {
            this._writeString(name);
            return;
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeString(name);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    protected final void _writeFieldName(SerializableString name, boolean commaBefore) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            this._writePPFieldName(name, commaBefore);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            this._flushBuffer();
        }
        if (commaBefore) {
            this._outputBuffer[this._outputTail++] = 44;
        }
        char[] quoted = name.asQuotedChars();
        if (this._cfgUnqNames) {
            this.writeRaw(quoted, 0, quoted.length);
            return;
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        int qlen = quoted.length;
        if (this._outputTail + qlen + 1 >= this._outputEnd) {
            this.writeRaw(quoted, 0, qlen);
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        } else {
            System.arraycopy(quoted, 0, this._outputBuffer, this._outputTail, qlen);
            this._outputTail += qlen;
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
    }

    @Override
    public void writeStartArray() throws IOException {
        this._verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
        } else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 91;
        }
    }

    @Override
    public void writeEndArray() throws IOException {
        if (!this._writeContext.inArray()) {
            this._reportError("Current context not Array but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 93;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    @Override
    public void writeStartObject(Object forValue) throws IOException {
        JsonWriteContext ctxt;
        this._verifyValueWrite("start an object");
        this._writeContext = ctxt = this._writeContext.createChildObjectContext();
        if (forValue != null) {
            ctxt.setCurrentValue(forValue);
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
        } else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 123;
        }
    }

    @Override
    public void writeStartObject() throws IOException {
        this._verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
        } else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 123;
        }
    }

    @Override
    public void writeEndObject() throws IOException {
        if (!this._writeContext.inObject()) {
            this._reportError("Current context not Object but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 125;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    protected final void _writePPFieldName(String name, boolean commaBefore) throws IOException {
        if (commaBefore) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            this._writeString(name);
        } else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
            this._writeString(name);
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
    }

    protected final void _writePPFieldName(SerializableString name, boolean commaBefore) throws IOException {
        if (commaBefore) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        char[] quoted = name.asQuotedChars();
        if (this._cfgUnqNames) {
            this.writeRaw(quoted, 0, quoted.length);
        } else {
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
            this.writeRaw(quoted, 0, quoted.length);
            if (this._outputTail >= this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = this._quoteChar;
        }
    }

    @Override
    public void writeString(String text) throws IOException {
        this._verifyValueWrite("write a string");
        if (text == null) {
            this._writeNull();
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeString(text);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    @Override
    public void writeString(Reader reader, int len) throws IOException {
        int toReadNow;
        int numRead;
        this._verifyValueWrite("write a string");
        if (reader == null) {
            this._reportError("null reader");
        }
        int toRead = len >= 0 ? len : Integer.MAX_VALUE;
        char[] buf = this._allocateCopyBuffer();
        if (this._outputTail + len >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        while (toRead > 0 && (numRead = reader.read(buf, 0, toReadNow = Math.min(toRead, buf.length))) > 0) {
            if (this._outputTail + len >= this._outputEnd) {
                this._flushBuffer();
            }
            this._writeString(buf, 0, numRead);
            toRead -= numRead;
        }
        if (this._outputTail + len >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        if (toRead > 0 && len >= 0) {
            this._reportError("Didn't read enough from reader");
        }
    }

    @Override
    public void writeString(char[] text, int offset, int len) throws IOException {
        this._verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeString(text, offset, len);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    @Override
    public void writeString(SerializableString sstr) throws IOException {
        this._verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        char[] text = sstr.asQuotedChars();
        int len = text.length;
        if (len < 32) {
            int room = this._outputEnd - this._outputTail;
            if (len > room) {
                this._flushBuffer();
            }
            System.arraycopy(text, 0, this._outputBuffer, this._outputTail, len);
            this._outputTail += len;
        } else {
            this._flushBuffer();
            this._writer.write(text, 0, len);
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    @Override
    public void writeRawUTF8String(byte[] text, int offset, int length) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeUTF8String(byte[] text, int offset, int length) throws IOException {
        this._reportUnsupportedOperation();
    }

    @Override
    public void writeRaw(String text) throws IOException {
        int len = text.length();
        int room = this._outputEnd - this._outputTail;
        if (room == 0) {
            this._flushBuffer();
            room = this._outputEnd - this._outputTail;
        }
        if (room >= len) {
            text.getChars(0, len, this._outputBuffer, this._outputTail);
            this._outputTail += len;
        } else {
            this.writeRawLong(text);
        }
    }

    @Override
    public void writeRaw(String text, int start, int len) throws IOException {
        int room = this._outputEnd - this._outputTail;
        if (room < len) {
            this._flushBuffer();
            room = this._outputEnd - this._outputTail;
        }
        if (room >= len) {
            text.getChars(start, start + len, this._outputBuffer, this._outputTail);
            this._outputTail += len;
        } else {
            this.writeRawLong(text.substring(start, start + len));
        }
    }

    @Override
    public void writeRaw(SerializableString text) throws IOException {
        this.writeRaw(text.getValue());
    }

    @Override
    public void writeRaw(char[] text, int offset, int len) throws IOException {
        if (len < 32) {
            int room = this._outputEnd - this._outputTail;
            if (len > room) {
                this._flushBuffer();
            }
            System.arraycopy(text, offset, this._outputBuffer, this._outputTail, len);
            this._outputTail += len;
            return;
        }
        this._flushBuffer();
        this._writer.write(text, offset, len);
    }

    @Override
    public void writeRaw(char c10) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = c10;
    }

    private void writeRawLong(String text) throws IOException {
        int len;
        int amount;
        int room = this._outputEnd - this._outputTail;
        text.getChars(0, room, this._outputBuffer, this._outputTail);
        this._outputTail += room;
        this._flushBuffer();
        int offset = room;
        for (len = text.length() - room; len > this._outputEnd; len -= amount) {
            amount = this._outputEnd;
            text.getChars(offset, offset + amount, this._outputBuffer, 0);
            this._outputHead = 0;
            this._outputTail = amount;
            this._flushBuffer();
            offset += amount;
        }
        text.getChars(offset, offset + len, this._outputBuffer, 0);
        this._outputHead = 0;
        this._outputTail = len;
    }

    @Override
    public void writeBinary(Base64Variant b64variant, byte[] data, int offset, int len) throws IOException, JsonGenerationException {
        this._verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._writeBinary(b64variant, data, offset, offset + len);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws IOException, JsonGenerationException {
        int bytes;
        this._verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        byte[] encodingBuffer = this._ioContext.allocBase64Buffer();
        try {
            if (dataLength < 0) {
                bytes = this._writeBinary(b64variant, data, encodingBuffer);
            } else {
                int missing = this._writeBinary(b64variant, data, encodingBuffer, dataLength);
                if (missing > 0) {
                    this._reportError("Too few bytes available: missing " + missing + " bytes (out of " + dataLength + ")");
                }
                bytes = dataLength;
            }
        }
        finally {
            this._ioContext.releaseBase64Buffer(encodingBuffer);
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        return bytes;
    }

    @Override
    public void writeNumber(short s2) throws IOException {
        this._verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedShort(s2);
            return;
        }
        if (this._outputTail + 6 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt((int)s2, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedShort(short s2) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._outputTail = NumberOutput.outputInt((int)s2, this._outputBuffer, this._outputTail);
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    @Override
    public void writeNumber(int i2) throws IOException {
        this._verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedInt(i2);
            return;
        }
        if (this._outputTail + 11 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt(i2, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedInt(int i2) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._outputTail = NumberOutput.outputInt(i2, this._outputBuffer, this._outputTail);
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    @Override
    public void writeNumber(long l2) throws IOException {
        this._verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedLong(l2);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(l2, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedLong(long l2) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this._outputTail = NumberOutput.outputLong(l2, this._outputBuffer, this._outputTail);
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    @Override
    public void writeNumber(BigInteger value) throws IOException {
        this._verifyValueWrite("write a number");
        if (value == null) {
            this._writeNull();
        } else if (this._cfgNumbersAsStrings) {
            this._writeQuotedRaw(value.toString());
        } else {
            this.writeRaw(value.toString());
        }
    }

    @Override
    public void writeNumber(double d10) throws IOException {
        if (this._cfgNumbersAsStrings || this.isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS) && (Double.isNaN(d10) || Double.isInfinite(d10))) {
            this.writeString(String.valueOf(d10));
            return;
        }
        this._verifyValueWrite("write a number");
        this.writeRaw(String.valueOf(d10));
    }

    @Override
    public void writeNumber(float f10) throws IOException {
        if (this._cfgNumbersAsStrings || this.isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS) && (Float.isNaN(f10) || Float.isInfinite(f10))) {
            this.writeString(String.valueOf(f10));
            return;
        }
        this._verifyValueWrite("write a number");
        this.writeRaw(String.valueOf(f10));
    }

    @Override
    public void writeNumber(BigDecimal value) throws IOException {
        this._verifyValueWrite("write a number");
        if (value == null) {
            this._writeNull();
        } else if (this._cfgNumbersAsStrings) {
            this._writeQuotedRaw(this._asString(value));
        } else {
            this.writeRaw(this._asString(value));
        }
    }

    @Override
    public void writeNumber(String encodedValue) throws IOException {
        this._verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            this._writeQuotedRaw(encodedValue);
        } else {
            this.writeRaw(encodedValue);
        }
    }

    private void _writeQuotedRaw(String value) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
        this.writeRaw(value);
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = this._quoteChar;
    }

    @Override
    public void writeBoolean(boolean state) throws IOException {
        this._verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            this._flushBuffer();
        }
        int ptr = this._outputTail;
        char[] buf = this._outputBuffer;
        if (state) {
            buf[ptr] = 116;
            buf[++ptr] = 114;
            buf[++ptr] = 117;
            buf[++ptr] = 101;
        } else {
            buf[ptr] = 102;
            buf[++ptr] = 97;
            buf[++ptr] = 108;
            buf[++ptr] = 115;
            buf[++ptr] = 101;
        }
        this._outputTail = ptr + 1;
    }

    @Override
    public void writeNull() throws IOException {
        this._verifyValueWrite("write a null");
        this._writeNull();
    }

    @Override
    protected final void _verifyValueWrite(String typeMsg) throws IOException {
        int c10;
        int status = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            this._verifyPrettyValueWrite(typeMsg, status);
            return;
        }
        switch (status) {
            default: {
                return;
            }
            case 1: {
                c10 = 44;
                break;
            }
            case 2: {
                c10 = 58;
                break;
            }
            case 3: {
                if (this._rootValueSeparator != null) {
                    this.writeRaw(this._rootValueSeparator.getValue());
                }
                return;
            }
            case 5: {
                this._reportCantWriteValueExpectName(typeMsg);
                return;
            }
        }
        if (this._outputTail >= this._outputEnd) {
            this._flushBuffer();
        }
        this._outputBuffer[this._outputTail++] = c10;
    }

    @Override
    public void flush() throws IOException {
        this._flushBuffer();
        if (this._writer != null && this.isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._writer.flush();
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
        if (this._outputBuffer != null && this.isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonStreamContext ctxt;
                if ((ctxt = this.getOutputContext()).inArray()) {
                    this.writeEndArray();
                    continue;
                }
                if (!ctxt.inObject()) break;
                this.writeEndObject();
            }
        }
        this._flushBuffer();
        this._outputHead = 0;
        this._outputTail = 0;
        if (this._writer != null) {
            if (this._ioContext.isResourceManaged() || this.isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this._writer.close();
            } else if (this.isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this._writer.flush();
            }
        }
        this._releaseBuffers();
    }

    @Override
    protected void _releaseBuffers() {
        char[] buf = this._outputBuffer;
        if (buf != null) {
            this._outputBuffer = null;
            this._ioContext.releaseConcatBuffer(buf);
        }
        if ((buf = this._charBuffer) != null) {
            this._charBuffer = null;
            this._ioContext.releaseNameCopyBuffer(buf);
        }
    }

    private void _writeString(String text) throws IOException {
        int len = text.length();
        if (len > this._outputEnd) {
            this._writeLongString(text);
            return;
        }
        if (this._outputTail + len > this._outputEnd) {
            this._flushBuffer();
        }
        text.getChars(0, len, this._outputBuffer, this._outputTail);
        if (this._characterEscapes != null) {
            this._writeStringCustom(len);
        } else if (this._maximumNonEscapedChar != 0) {
            this._writeStringASCII(len, this._maximumNonEscapedChar);
        } else {
            this._writeString2(len);
        }
    }

    private void _writeString2(int len) throws IOException {
        int end = this._outputTail + len;
        int[] escCodes = this._outputEscapes;
        int escLen = escCodes.length;
        block0: while (this._outputTail < end) {
            char c10;
            while ((c10 = this._outputBuffer[this._outputTail]) >= escLen || escCodes[c10] == 0) {
                if (++this._outputTail < end) continue;
                break block0;
            }
            int flushLen = this._outputTail - this._outputHead;
            if (flushLen > 0) {
                this._writer.write(this._outputBuffer, this._outputHead, flushLen);
            }
            char c11 = this._outputBuffer[this._outputTail++];
            this._prependOrWriteCharacterEscape(c11, escCodes[c11]);
        }
    }

    private void _writeLongString(String text) throws IOException {
        int segmentLen;
        this._flushBuffer();
        int textLen = text.length();
        int offset = 0;
        do {
            int max;
            segmentLen = offset + (max = this._outputEnd) > textLen ? textLen - offset : max;
            text.getChars(offset, offset + segmentLen, this._outputBuffer, 0);
            if (this._characterEscapes != null) {
                this._writeSegmentCustom(segmentLen);
                continue;
            }
            if (this._maximumNonEscapedChar != 0) {
                this._writeSegmentASCII(segmentLen, this._maximumNonEscapedChar);
                continue;
            }
            this._writeSegment(segmentLen);
        } while ((offset += segmentLen) < textLen);
    }

    private void _writeSegment(int end) throws IOException {
        int ptr;
        int[] escCodes = this._outputEscapes;
        int escLen = escCodes.length;
        int start = ptr = 0;
        while (ptr < end) {
            char c10;
            while (((c10 = this._outputBuffer[ptr]) >= escLen || escCodes[c10] == 0) && ++ptr < end) {
            }
            int flushLen = ptr - start;
            if (flushLen > 0) {
                this._writer.write(this._outputBuffer, start, flushLen);
                if (ptr >= end) break;
            }
            start = this._prependOrWriteCharacterEscape(this._outputBuffer, ++ptr, end, c10, escCodes[c10]);
        }
    }

    private void _writeString(char[] text, int offset, int len) throws IOException {
        if (this._characterEscapes != null) {
            this._writeStringCustom(text, offset, len);
            return;
        }
        if (this._maximumNonEscapedChar != 0) {
            this._writeStringASCII(text, offset, len, this._maximumNonEscapedChar);
            return;
        }
        len += offset;
        int[] escCodes = this._outputEscapes;
        int escLen = escCodes.length;
        while (offset < len) {
            char c10;
            int start = offset;
            while (((c10 = text[offset]) >= escLen || escCodes[c10] == 0) && ++offset < len) {
            }
            int newAmount = offset - start;
            if (newAmount < 32) {
                if (this._outputTail + newAmount > this._outputEnd) {
                    this._flushBuffer();
                }
                if (newAmount > 0) {
                    System.arraycopy(text, start, this._outputBuffer, this._outputTail, newAmount);
                    this._outputTail += newAmount;
                }
            } else {
                this._flushBuffer();
                this._writer.write(text, start, newAmount);
            }
            if (offset >= len) break;
            char c11 = text[offset++];
            this._appendCharacterEscape(c11, escCodes[c11]);
        }
    }

    private void _writeStringASCII(int len, int maxNonEscaped) throws IOException, JsonGenerationException {
        int end = this._outputTail + len;
        int[] escCodes = this._outputEscapes;
        int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
        int escCode = 0;
        while (this._outputTail < end) {
            char c10;
            block5: {
                do {
                    if ((c10 = this._outputBuffer[this._outputTail]) < escLimit) {
                        escCode = escCodes[c10];
                        if (escCode == 0) continue;
                    } else {
                        if (c10 <= maxNonEscaped) continue;
                        escCode = -1;
                    }
                    break block5;
                } while (++this._outputTail < end);
                break;
            }
            int flushLen = this._outputTail - this._outputHead;
            if (flushLen > 0) {
                this._writer.write(this._outputBuffer, this._outputHead, flushLen);
            }
            ++this._outputTail;
            this._prependOrWriteCharacterEscape(c10, escCode);
        }
    }

    private void _writeSegmentASCII(int end, int maxNonEscaped) throws IOException, JsonGenerationException {
        int[] escCodes = this._outputEscapes;
        int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
        int ptr = 0;
        int escCode = 0;
        int start = ptr;
        while (ptr < end) {
            char c10;
            do {
                if ((c10 = this._outputBuffer[ptr]) < escLimit) {
                    escCode = escCodes[c10];
                    if (escCode == 0) continue;
                    break;
                }
                if (c10 <= maxNonEscaped) continue;
                escCode = -1;
                break;
            } while (++ptr < end);
            int flushLen = ptr - start;
            if (flushLen > 0) {
                this._writer.write(this._outputBuffer, start, flushLen);
                if (ptr >= end) break;
            }
            start = this._prependOrWriteCharacterEscape(this._outputBuffer, ++ptr, end, c10, escCode);
        }
    }

    private void _writeStringASCII(char[] text, int offset, int len, int maxNonEscaped) throws IOException, JsonGenerationException {
        len += offset;
        int[] escCodes = this._outputEscapes;
        int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
        int escCode = 0;
        while (offset < len) {
            char c10;
            int start = offset;
            do {
                if ((c10 = text[offset]) < escLimit) {
                    escCode = escCodes[c10];
                    if (escCode == 0) continue;
                    break;
                }
                if (c10 <= maxNonEscaped) continue;
                escCode = -1;
                break;
            } while (++offset < len);
            int newAmount = offset - start;
            if (newAmount < 32) {
                if (this._outputTail + newAmount > this._outputEnd) {
                    this._flushBuffer();
                }
                if (newAmount > 0) {
                    System.arraycopy(text, start, this._outputBuffer, this._outputTail, newAmount);
                    this._outputTail += newAmount;
                }
            } else {
                this._flushBuffer();
                this._writer.write(text, start, newAmount);
            }
            if (offset >= len) break;
            ++offset;
            this._appendCharacterEscape(c10, escCode);
        }
    }

    private void _writeStringCustom(int len) throws IOException, JsonGenerationException {
        int end = this._outputTail + len;
        int[] escCodes = this._outputEscapes;
        int maxNonEscaped = this._maximumNonEscapedChar < 1 ? 65535 : this._maximumNonEscapedChar;
        int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
        int escCode = 0;
        CharacterEscapes customEscapes = this._characterEscapes;
        while (this._outputTail < end) {
            int c10;
            block7: {
                do {
                    if ((c10 = this._outputBuffer[this._outputTail]) < escLimit) {
                        escCode = escCodes[c10];
                        if (escCode == 0) continue;
                    } else if (c10 > maxNonEscaped) {
                        escCode = -1;
                    } else {
                        this._currentEscape = customEscapes.getEscapeSequence(c10);
                        if (this._currentEscape == null) continue;
                        escCode = -2;
                    }
                    break block7;
                } while (++this._outputTail < end);
                break;
            }
            int flushLen = this._outputTail - this._outputHead;
            if (flushLen > 0) {
                this._writer.write(this._outputBuffer, this._outputHead, flushLen);
            }
            ++this._outputTail;
            this._prependOrWriteCharacterEscape((char)c10, escCode);
        }
    }

    private void _writeSegmentCustom(int end) throws IOException, JsonGenerationException {
        int[] escCodes = this._outputEscapes;
        int maxNonEscaped = this._maximumNonEscapedChar < 1 ? 65535 : this._maximumNonEscapedChar;
        int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
        CharacterEscapes customEscapes = this._characterEscapes;
        int ptr = 0;
        int escCode = 0;
        int start = ptr;
        while (ptr < end) {
            int c10;
            do {
                if ((c10 = this._outputBuffer[ptr]) < escLimit) {
                    escCode = escCodes[c10];
                    if (escCode == 0) continue;
                    break;
                }
                if (c10 > maxNonEscaped) {
                    escCode = -1;
                    break;
                }
                this._currentEscape = customEscapes.getEscapeSequence(c10);
                if (this._currentEscape == null) continue;
                escCode = -2;
                break;
            } while (++ptr < end);
            int flushLen = ptr - start;
            if (flushLen > 0) {
                this._writer.write(this._outputBuffer, start, flushLen);
                if (ptr >= end) break;
            }
            start = this._prependOrWriteCharacterEscape(this._outputBuffer, ++ptr, end, (char)c10, escCode);
        }
    }

    private void _writeStringCustom(char[] text, int offset, int len) throws IOException, JsonGenerationException {
        len += offset;
        int[] escCodes = this._outputEscapes;
        int maxNonEscaped = this._maximumNonEscapedChar < 1 ? 65535 : this._maximumNonEscapedChar;
        int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
        CharacterEscapes customEscapes = this._characterEscapes;
        int escCode = 0;
        while (offset < len) {
            int c10;
            int start = offset;
            do {
                if ((c10 = text[offset]) < escLimit) {
                    escCode = escCodes[c10];
                    if (escCode == 0) continue;
                    break;
                }
                if (c10 > maxNonEscaped) {
                    escCode = -1;
                    break;
                }
                this._currentEscape = customEscapes.getEscapeSequence(c10);
                if (this._currentEscape == null) continue;
                escCode = -2;
                break;
            } while (++offset < len);
            int newAmount = offset - start;
            if (newAmount < 32) {
                if (this._outputTail + newAmount > this._outputEnd) {
                    this._flushBuffer();
                }
                if (newAmount > 0) {
                    System.arraycopy(text, start, this._outputBuffer, this._outputTail, newAmount);
                    this._outputTail += newAmount;
                }
            } else {
                this._flushBuffer();
                this._writer.write(text, start, newAmount);
            }
            if (offset >= len) break;
            ++offset;
            this._appendCharacterEscape((char)c10, escCode);
        }
    }

    protected final void _writeBinary(Base64Variant b64variant, byte[] input, int inputPtr, int inputEnd) throws IOException, JsonGenerationException {
        int safeInputEnd = inputEnd - 3;
        int safeOutputEnd = this._outputEnd - 6;
        int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        while (inputPtr <= safeInputEnd) {
            if (this._outputTail > safeOutputEnd) {
                this._flushBuffer();
            }
            int b24 = input[inputPtr++] << 8;
            b24 |= input[inputPtr++] & 0xFF;
            b24 = b24 << 8 | input[inputPtr++] & 0xFF;
            this._outputTail = b64variant.encodeBase64Chunk(b24, this._outputBuffer, this._outputTail);
            if (--chunksBeforeLF > 0) continue;
            this._outputBuffer[this._outputTail++] = 92;
            this._outputBuffer[this._outputTail++] = 110;
            chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        }
        int inputLeft = inputEnd - inputPtr;
        if (inputLeft > 0) {
            if (this._outputTail > safeOutputEnd) {
                this._flushBuffer();
            }
            int b24 = input[inputPtr++] << 16;
            if (inputLeft == 2) {
                b24 |= (input[inputPtr++] & 0xFF) << 8;
            }
            this._outputTail = b64variant.encodeBase64Partial(b24, inputLeft, this._outputBuffer, this._outputTail);
        }
    }

    protected final int _writeBinary(Base64Variant b64variant, InputStream data, byte[] readBuffer, int bytesLeft) throws IOException, JsonGenerationException {
        int b24;
        int inputPtr = 0;
        int inputEnd = 0;
        int lastFullOffset = -3;
        int safeOutputEnd = this._outputEnd - 6;
        int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        while (bytesLeft > 2) {
            if (inputPtr > lastFullOffset) {
                inputEnd = this._readMore(data, readBuffer, inputPtr, inputEnd, bytesLeft);
                inputPtr = 0;
                if (inputEnd < 3) break;
                lastFullOffset = inputEnd - 3;
            }
            if (this._outputTail > safeOutputEnd) {
                this._flushBuffer();
            }
            b24 = readBuffer[inputPtr++] << 8;
            b24 |= readBuffer[inputPtr++] & 0xFF;
            b24 = b24 << 8 | readBuffer[inputPtr++] & 0xFF;
            bytesLeft -= 3;
            this._outputTail = b64variant.encodeBase64Chunk(b24, this._outputBuffer, this._outputTail);
            if (--chunksBeforeLF > 0) continue;
            this._outputBuffer[this._outputTail++] = 92;
            this._outputBuffer[this._outputTail++] = 110;
            chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        }
        if (bytesLeft > 0) {
            inputEnd = this._readMore(data, readBuffer, inputPtr, inputEnd, bytesLeft);
            inputPtr = 0;
            if (inputEnd > 0) {
                int amount;
                if (this._outputTail > safeOutputEnd) {
                    this._flushBuffer();
                }
                b24 = readBuffer[inputPtr++] << 16;
                if (inputPtr < inputEnd) {
                    b24 |= (readBuffer[inputPtr] & 0xFF) << 8;
                    amount = 2;
                } else {
                    amount = 1;
                }
                this._outputTail = b64variant.encodeBase64Partial(b24, amount, this._outputBuffer, this._outputTail);
                bytesLeft -= amount;
            }
        }
        return bytesLeft;
    }

    protected final int _writeBinary(Base64Variant b64variant, InputStream data, byte[] readBuffer) throws IOException, JsonGenerationException {
        int b24;
        int inputPtr = 0;
        int inputEnd = 0;
        int lastFullOffset = -3;
        int bytesDone = 0;
        int safeOutputEnd = this._outputEnd - 6;
        int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        while (true) {
            if (inputPtr > lastFullOffset) {
                inputEnd = this._readMore(data, readBuffer, inputPtr, inputEnd, readBuffer.length);
                inputPtr = 0;
                if (inputEnd < 3) break;
                lastFullOffset = inputEnd - 3;
            }
            if (this._outputTail > safeOutputEnd) {
                this._flushBuffer();
            }
            b24 = readBuffer[inputPtr++] << 8;
            b24 |= readBuffer[inputPtr++] & 0xFF;
            b24 = b24 << 8 | readBuffer[inputPtr++] & 0xFF;
            bytesDone += 3;
            this._outputTail = b64variant.encodeBase64Chunk(b24, this._outputBuffer, this._outputTail);
            if (--chunksBeforeLF > 0) continue;
            this._outputBuffer[this._outputTail++] = 92;
            this._outputBuffer[this._outputTail++] = 110;
            chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        }
        if (inputPtr < inputEnd) {
            if (this._outputTail > safeOutputEnd) {
                this._flushBuffer();
            }
            b24 = readBuffer[inputPtr++] << 16;
            int amount = 1;
            if (inputPtr < inputEnd) {
                b24 |= (readBuffer[inputPtr] & 0xFF) << 8;
                amount = 2;
            }
            bytesDone += amount;
            this._outputTail = b64variant.encodeBase64Partial(b24, amount, this._outputBuffer, this._outputTail);
        }
        return bytesDone;
    }

    private int _readMore(InputStream in, byte[] readBuffer, int inputPtr, int inputEnd, int maxRead) throws IOException {
        int length;
        int i2 = 0;
        while (inputPtr < inputEnd) {
            readBuffer[i2++] = readBuffer[inputPtr++];
        }
        inputPtr = 0;
        inputEnd = i2;
        maxRead = Math.min(maxRead, readBuffer.length);
        while ((length = maxRead - inputEnd) != 0) {
            int count = in.read(readBuffer, inputEnd, length);
            if (count < 0) {
                return inputEnd;
            }
            if ((inputEnd += count) < 3) continue;
        }
        return inputEnd;
    }

    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            this._flushBuffer();
        }
        int ptr = this._outputTail;
        char[] buf = this._outputBuffer;
        buf[ptr] = 110;
        buf[++ptr] = 117;
        buf[++ptr] = 108;
        buf[++ptr] = 108;
        this._outputTail = ptr + 1;
    }

    private void _prependOrWriteCharacterEscape(char ch2, int escCode) throws IOException, JsonGenerationException {
        String escape;
        if (escCode >= 0) {
            if (this._outputTail >= 2) {
                int ptr;
                this._outputHead = ptr = this._outputTail - 2;
                this._outputBuffer[ptr++] = 92;
                this._outputBuffer[ptr] = (char)escCode;
                return;
            }
            char[] buf = this._entityBuffer;
            if (buf == null) {
                buf = this._allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            buf[1] = (char)escCode;
            this._writer.write(buf, 0, 2);
            return;
        }
        if (escCode != -2) {
            if (this._outputTail >= 6) {
                int ptr;
                char[] buf = this._outputBuffer;
                this._outputHead = ptr = this._outputTail - 6;
                buf[ptr] = 92;
                buf[++ptr] = 117;
                if (ch2 > '\u00ff') {
                    int hi = ch2 >> 8 & 0xFF;
                    buf[++ptr] = HEX_CHARS[hi >> 4];
                    buf[++ptr] = HEX_CHARS[hi & 0xF];
                    ch2 = (char)(ch2 & 0xFF);
                } else {
                    buf[++ptr] = 48;
                    buf[++ptr] = 48;
                }
                buf[++ptr] = HEX_CHARS[ch2 >> 4];
                buf[++ptr] = HEX_CHARS[ch2 & 0xF];
                return;
            }
            char[] buf = this._entityBuffer;
            if (buf == null) {
                buf = this._allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            if (ch2 > '\u00ff') {
                int hi = ch2 >> 8 & 0xFF;
                int lo = ch2 & 0xFF;
                buf[10] = HEX_CHARS[hi >> 4];
                buf[11] = HEX_CHARS[hi & 0xF];
                buf[12] = HEX_CHARS[lo >> 4];
                buf[13] = HEX_CHARS[lo & 0xF];
                this._writer.write(buf, 8, 6);
            } else {
                buf[6] = HEX_CHARS[ch2 >> 4];
                buf[7] = HEX_CHARS[ch2 & 0xF];
                this._writer.write(buf, 2, 6);
            }
            return;
        }
        if (this._currentEscape == null) {
            escape = this._characterEscapes.getEscapeSequence(ch2).getValue();
        } else {
            escape = this._currentEscape.getValue();
            this._currentEscape = null;
        }
        int len = escape.length();
        if (this._outputTail >= len) {
            int ptr;
            this._outputHead = ptr = this._outputTail - len;
            escape.getChars(0, len, this._outputBuffer, ptr);
            return;
        }
        this._outputHead = this._outputTail;
        this._writer.write(escape);
    }

    private int _prependOrWriteCharacterEscape(char[] buffer, int ptr, int end, char ch2, int escCode) throws IOException, JsonGenerationException {
        String escape;
        if (escCode >= 0) {
            if (ptr > 1 && ptr < end) {
                buffer[ptr -= 2] = 92;
                buffer[ptr + 1] = (char)escCode;
            } else {
                char[] ent = this._entityBuffer;
                if (ent == null) {
                    ent = this._allocateEntityBuffer();
                }
                ent[1] = (char)escCode;
                this._writer.write(ent, 0, 2);
            }
            return ptr;
        }
        if (escCode != -2) {
            if (ptr > 5 && ptr < end) {
                ptr -= 6;
                buffer[ptr++] = 92;
                buffer[ptr++] = 117;
                if (ch2 > '\u00ff') {
                    int hi = ch2 >> 8 & 0xFF;
                    buffer[ptr++] = HEX_CHARS[hi >> 4];
                    buffer[ptr++] = HEX_CHARS[hi & 0xF];
                    ch2 = (char)(ch2 & 0xFF);
                } else {
                    buffer[ptr++] = 48;
                    buffer[ptr++] = 48;
                }
                buffer[ptr++] = HEX_CHARS[ch2 >> 4];
                buffer[ptr] = HEX_CHARS[ch2 & 0xF];
                ptr -= 5;
            } else {
                char[] ent = this._entityBuffer;
                if (ent == null) {
                    ent = this._allocateEntityBuffer();
                }
                this._outputHead = this._outputTail;
                if (ch2 > '\u00ff') {
                    int hi = ch2 >> 8 & 0xFF;
                    int lo = ch2 & 0xFF;
                    ent[10] = HEX_CHARS[hi >> 4];
                    ent[11] = HEX_CHARS[hi & 0xF];
                    ent[12] = HEX_CHARS[lo >> 4];
                    ent[13] = HEX_CHARS[lo & 0xF];
                    this._writer.write(ent, 8, 6);
                } else {
                    ent[6] = HEX_CHARS[ch2 >> 4];
                    ent[7] = HEX_CHARS[ch2 & 0xF];
                    this._writer.write(ent, 2, 6);
                }
            }
            return ptr;
        }
        if (this._currentEscape == null) {
            escape = this._characterEscapes.getEscapeSequence(ch2).getValue();
        } else {
            escape = this._currentEscape.getValue();
            this._currentEscape = null;
        }
        int len = escape.length();
        if (ptr >= len && ptr < end) {
            escape.getChars(0, len, buffer, ptr -= len);
        } else {
            this._writer.write(escape);
        }
        return ptr;
    }

    private void _appendCharacterEscape(char ch2, int escCode) throws IOException, JsonGenerationException {
        String escape;
        if (escCode >= 0) {
            if (this._outputTail + 2 > this._outputEnd) {
                this._flushBuffer();
            }
            this._outputBuffer[this._outputTail++] = 92;
            this._outputBuffer[this._outputTail++] = (char)escCode;
            return;
        }
        if (escCode != -2) {
            if (this._outputTail + 5 >= this._outputEnd) {
                this._flushBuffer();
            }
            int ptr = this._outputTail;
            char[] buf = this._outputBuffer;
            buf[ptr++] = 92;
            buf[ptr++] = 117;
            if (ch2 > '\u00ff') {
                int hi = ch2 >> 8 & 0xFF;
                buf[ptr++] = HEX_CHARS[hi >> 4];
                buf[ptr++] = HEX_CHARS[hi & 0xF];
                ch2 = (char)(ch2 & 0xFF);
            } else {
                buf[ptr++] = 48;
                buf[ptr++] = 48;
            }
            buf[ptr++] = HEX_CHARS[ch2 >> 4];
            buf[ptr++] = HEX_CHARS[ch2 & 0xF];
            this._outputTail = ptr;
            return;
        }
        if (this._currentEscape == null) {
            escape = this._characterEscapes.getEscapeSequence(ch2).getValue();
        } else {
            escape = this._currentEscape.getValue();
            this._currentEscape = null;
        }
        int len = escape.length();
        if (this._outputTail + len > this._outputEnd) {
            this._flushBuffer();
            if (len > this._outputEnd) {
                this._writer.write(escape);
                return;
            }
        }
        escape.getChars(0, len, this._outputBuffer, this._outputTail);
        this._outputTail += len;
    }

    private char[] _allocateEntityBuffer() {
        char[] buf = new char[14];
        buf[0] = 92;
        buf[2] = 92;
        buf[3] = 117;
        buf[4] = 48;
        buf[5] = 48;
        buf[8] = 92;
        buf[9] = 117;
        this._entityBuffer = buf;
        return buf;
    }

    private char[] _allocateCopyBuffer() {
        if (this._charBuffer == null) {
            this._charBuffer = this._ioContext.allocNameCopyBuffer(2000);
        }
        return this._charBuffer;
    }

    protected void _flushBuffer() throws IOException {
        int len = this._outputTail - this._outputHead;
        if (len > 0) {
            int offset = this._outputHead;
            this._outputHead = 0;
            this._outputTail = 0;
            this._writer.write(this._outputBuffer, offset, len);
        }
    }
}

