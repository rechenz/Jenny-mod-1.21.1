/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.io;

import java.lang.ref.SoftReference;
import software.bernie.shadowed.fasterxml.jackson.core.io.CharTypes;
import software.bernie.shadowed.fasterxml.jackson.core.io.UTF8Writer;
import software.bernie.shadowed.fasterxml.jackson.core.util.ByteArrayBuilder;
import software.bernie.shadowed.fasterxml.jackson.core.util.TextBuffer;

public final class JsonStringEncoder {
    private static final char[] HC = CharTypes.copyHexChars();
    private static final byte[] HB = CharTypes.copyHexBytes();
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal();
    protected TextBuffer _text;
    protected ByteArrayBuilder _bytes;
    protected final char[] _qbuf = new char[6];

    public JsonStringEncoder() {
        this._qbuf[0] = 92;
        this._qbuf[2] = 48;
        this._qbuf[3] = 48;
    }

    public static JsonStringEncoder getInstance() {
        JsonStringEncoder enc;
        SoftReference<JsonStringEncoder> ref = _threadEncoder.get();
        JsonStringEncoder jsonStringEncoder = enc = ref == null ? null : ref.get();
        if (enc == null) {
            enc = new JsonStringEncoder();
            _threadEncoder.set(new SoftReference<JsonStringEncoder>(enc));
        }
        return enc;
    }

    public char[] quoteAsString(String input) {
        TextBuffer textBuffer = this._text;
        if (textBuffer == null) {
            this._text = textBuffer = new TextBuffer(null);
        }
        char[] outputBuffer = textBuffer.emptyAndGetCurrentSegment();
        int[] escCodes = CharTypes.get7BitOutputEscapes();
        int escCodeCount = escCodes.length;
        int inPtr = 0;
        int inputLen = input.length();
        int outPtr = 0;
        block0: while (inPtr < inputLen) {
            char d10;
            int escCode;
            int length;
            char c10;
            while ((c10 = input.charAt(inPtr)) >= escCodeCount || escCodes[c10] == 0) {
                if (outPtr >= outputBuffer.length) {
                    outputBuffer = textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outputBuffer[outPtr++] = c10;
                if (++inPtr < inputLen) continue;
                break block0;
            }
            int n2 = length = (escCode = escCodes[d10 = input.charAt(inPtr++)]) < 0 ? this._appendNumeric(d10, this._qbuf) : this._appendNamed(escCode, this._qbuf);
            if (outPtr + length > outputBuffer.length) {
                int first = outputBuffer.length - outPtr;
                if (first > 0) {
                    System.arraycopy(this._qbuf, 0, outputBuffer, outPtr, first);
                }
                outputBuffer = textBuffer.finishCurrentSegment();
                int second = length - first;
                System.arraycopy(this._qbuf, first, outputBuffer, 0, second);
                outPtr = second;
                continue;
            }
            System.arraycopy(this._qbuf, 0, outputBuffer, outPtr, length);
            outPtr += length;
        }
        textBuffer.setCurrentLength(outPtr);
        return textBuffer.contentsAsArray();
    }

    public void quoteAsString(CharSequence input, StringBuilder output) {
        int[] escCodes = CharTypes.get7BitOutputEscapes();
        int escCodeCount = escCodes.length;
        int inPtr = 0;
        int inputLen = input.length();
        block0: while (inPtr < inputLen) {
            char c10;
            while ((c10 = input.charAt(inPtr)) >= escCodeCount || escCodes[c10] == 0) {
                output.append(c10);
                if (++inPtr < inputLen) continue;
                break block0;
            }
            char d10 = input.charAt(inPtr++);
            int escCode = escCodes[d10];
            int length = escCode < 0 ? this._appendNumeric(d10, this._qbuf) : this._appendNamed(escCode, this._qbuf);
            output.append(this._qbuf, 0, length);
        }
    }

    public byte[] quoteAsUTF8(String text) {
        ByteArrayBuilder bb2 = this._bytes;
        if (bb2 == null) {
            this._bytes = bb2 = new ByteArrayBuilder(null);
        }
        int inputPtr = 0;
        int inputEnd = text.length();
        int outputPtr = 0;
        byte[] outputBuffer = bb2.resetAndGetFirstSegment();
        block0: while (inputPtr < inputEnd) {
            int ch2;
            int[] escCodes = CharTypes.get7BitOutputEscapes();
            while ((ch2 = text.charAt(inputPtr)) <= 127 && escCodes[ch2] == 0) {
                if (outputPtr >= outputBuffer.length) {
                    outputBuffer = bb2.finishCurrentSegment();
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte)ch2;
                if (++inputPtr < inputEnd) continue;
                break block0;
            }
            if (outputPtr >= outputBuffer.length) {
                outputBuffer = bb2.finishCurrentSegment();
                outputPtr = 0;
            }
            if ((ch2 = text.charAt(inputPtr++)) <= 127) {
                int escape = escCodes[ch2];
                outputPtr = this._appendByte(ch2, escape, bb2, outputPtr);
                outputBuffer = bb2.getCurrentSegment();
                continue;
            }
            if (ch2 <= 2047) {
                outputBuffer[outputPtr++] = (byte)(0xC0 | ch2 >> 6);
                ch2 = 0x80 | ch2 & 0x3F;
            } else if (ch2 < 55296 || ch2 > 57343) {
                outputBuffer[outputPtr++] = (byte)(0xE0 | ch2 >> 12);
                if (outputPtr >= outputBuffer.length) {
                    outputBuffer = bb2.finishCurrentSegment();
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte)(0x80 | ch2 >> 6 & 0x3F);
                ch2 = 0x80 | ch2 & 0x3F;
            } else {
                if (ch2 > 56319) {
                    JsonStringEncoder._illegal(ch2);
                }
                if (inputPtr >= inputEnd) {
                    JsonStringEncoder._illegal(ch2);
                }
                if ((ch2 = JsonStringEncoder._convert(ch2, text.charAt(inputPtr++))) > 0x10FFFF) {
                    JsonStringEncoder._illegal(ch2);
                }
                outputBuffer[outputPtr++] = (byte)(0xF0 | ch2 >> 18);
                if (outputPtr >= outputBuffer.length) {
                    outputBuffer = bb2.finishCurrentSegment();
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte)(0x80 | ch2 >> 12 & 0x3F);
                if (outputPtr >= outputBuffer.length) {
                    outputBuffer = bb2.finishCurrentSegment();
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte)(0x80 | ch2 >> 6 & 0x3F);
                ch2 = 0x80 | ch2 & 0x3F;
            }
            if (outputPtr >= outputBuffer.length) {
                outputBuffer = bb2.finishCurrentSegment();
                outputPtr = 0;
            }
            outputBuffer[outputPtr++] = (byte)ch2;
        }
        return this._bytes.completeAndCoalesce(outputPtr);
    }

    public byte[] encodeAsUTF8(String text) {
        ByteArrayBuilder byteBuilder = this._bytes;
        if (byteBuilder == null) {
            this._bytes = byteBuilder = new ByteArrayBuilder(null);
        }
        int inputPtr = 0;
        int inputEnd = text.length();
        int outputPtr = 0;
        byte[] outputBuffer = byteBuilder.resetAndGetFirstSegment();
        int outputEnd = outputBuffer.length;
        block0: while (inputPtr < inputEnd) {
            int c10 = text.charAt(inputPtr++);
            while (c10 <= 127) {
                if (outputPtr >= outputEnd) {
                    outputBuffer = byteBuilder.finishCurrentSegment();
                    outputEnd = outputBuffer.length;
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte)c10;
                if (inputPtr >= inputEnd) break block0;
                c10 = text.charAt(inputPtr++);
            }
            if (outputPtr >= outputEnd) {
                outputBuffer = byteBuilder.finishCurrentSegment();
                outputEnd = outputBuffer.length;
                outputPtr = 0;
            }
            if (c10 < 2048) {
                outputBuffer[outputPtr++] = (byte)(0xC0 | c10 >> 6);
            } else if (c10 < 55296 || c10 > 57343) {
                outputBuffer[outputPtr++] = (byte)(0xE0 | c10 >> 12);
                if (outputPtr >= outputEnd) {
                    outputBuffer = byteBuilder.finishCurrentSegment();
                    outputEnd = outputBuffer.length;
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte)(0x80 | c10 >> 6 & 0x3F);
            } else {
                if (c10 > 56319) {
                    JsonStringEncoder._illegal(c10);
                }
                if (inputPtr >= inputEnd) {
                    JsonStringEncoder._illegal(c10);
                }
                if ((c10 = JsonStringEncoder._convert(c10, text.charAt(inputPtr++))) > 0x10FFFF) {
                    JsonStringEncoder._illegal(c10);
                }
                outputBuffer[outputPtr++] = (byte)(0xF0 | c10 >> 18);
                if (outputPtr >= outputEnd) {
                    outputBuffer = byteBuilder.finishCurrentSegment();
                    outputEnd = outputBuffer.length;
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte)(0x80 | c10 >> 12 & 0x3F);
                if (outputPtr >= outputEnd) {
                    outputBuffer = byteBuilder.finishCurrentSegment();
                    outputEnd = outputBuffer.length;
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte)(0x80 | c10 >> 6 & 0x3F);
            }
            if (outputPtr >= outputEnd) {
                outputBuffer = byteBuilder.finishCurrentSegment();
                outputEnd = outputBuffer.length;
                outputPtr = 0;
            }
            outputBuffer[outputPtr++] = (byte)(0x80 | c10 & 0x3F);
        }
        return this._bytes.completeAndCoalesce(outputPtr);
    }

    private int _appendNumeric(int value, char[] qbuf) {
        qbuf[1] = 117;
        qbuf[4] = HC[value >> 4];
        qbuf[5] = HC[value & 0xF];
        return 6;
    }

    private int _appendNamed(int esc, char[] qbuf) {
        qbuf[1] = (char)esc;
        return 2;
    }

    private int _appendByte(int ch2, int esc, ByteArrayBuilder bb2, int ptr) {
        bb2.setCurrentSegmentLength(ptr);
        bb2.append(92);
        if (esc < 0) {
            bb2.append(117);
            if (ch2 > 255) {
                int hi = ch2 >> 8;
                bb2.append(HB[hi >> 4]);
                bb2.append(HB[hi & 0xF]);
                ch2 &= 0xFF;
            } else {
                bb2.append(48);
                bb2.append(48);
            }
            bb2.append(HB[ch2 >> 4]);
            bb2.append(HB[ch2 & 0xF]);
        } else {
            bb2.append((byte)esc);
        }
        return bb2.getCurrentSegmentLength();
    }

    private static int _convert(int p1, int p2) {
        if (p2 < 56320 || p2 > 57343) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(p1) + ", second 0x" + Integer.toHexString(p2) + "; illegal combination");
        }
        return 65536 + (p1 - 55296 << 10) + (p2 - 56320);
    }

    private static void _illegal(int c10) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(c10));
    }
}

