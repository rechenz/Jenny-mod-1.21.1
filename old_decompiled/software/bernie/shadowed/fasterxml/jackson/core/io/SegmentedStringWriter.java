/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.io;

import java.io.Writer;
import software.bernie.shadowed.fasterxml.jackson.core.util.BufferRecycler;
import software.bernie.shadowed.fasterxml.jackson.core.util.TextBuffer;

public final class SegmentedStringWriter
extends Writer {
    private final TextBuffer _buffer;

    public SegmentedStringWriter(BufferRecycler br2) {
        this._buffer = new TextBuffer(br2);
    }

    @Override
    public Writer append(char c10) {
        this.write(c10);
        return this;
    }

    @Override
    public Writer append(CharSequence csq) {
        String str = csq.toString();
        this._buffer.append(str, 0, str.length());
        return this;
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) {
        String str = csq.subSequence(start, end).toString();
        this._buffer.append(str, 0, str.length());
        return this;
    }

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void write(char[] cbuf) {
        this._buffer.append(cbuf, 0, cbuf.length);
    }

    @Override
    public void write(char[] cbuf, int off, int len) {
        this._buffer.append(cbuf, off, len);
    }

    @Override
    public void write(int c10) {
        this._buffer.append((char)c10);
    }

    @Override
    public void write(String str) {
        this._buffer.append(str, 0, str.length());
    }

    @Override
    public void write(String str, int off, int len) {
        this._buffer.append(str, off, len);
    }

    public String getAndClear() {
        String result = this._buffer.contentsAsString();
        this._buffer.releaseBuffers();
        return result;
    }
}

