/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.io;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutputAsStream
extends OutputStream {
    protected final DataOutput _output;

    public DataOutputAsStream(DataOutput out) {
        this._output = out;
    }

    @Override
    public void write(int b10) throws IOException {
        this._output.write(b10);
    }

    @Override
    public void write(byte[] b10) throws IOException {
        this._output.write(b10, 0, b10.length);
    }

    @Override
    public void write(byte[] b10, int offset, int length) throws IOException {
        this._output.write(b10, offset, length);
    }
}

