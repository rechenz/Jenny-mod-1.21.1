/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedOutputStream
extends OutputStream {
    protected final ByteBuffer _b;

    public ByteBufferBackedOutputStream(ByteBuffer buf) {
        this._b = buf;
    }

    @Override
    public void write(int b10) throws IOException {
        this._b.put((byte)b10);
    }

    @Override
    public void write(byte[] bytes, int off, int len) throws IOException {
        this._b.put(bytes, off, len);
    }
}

