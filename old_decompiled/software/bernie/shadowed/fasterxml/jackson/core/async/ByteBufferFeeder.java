/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.async;

import java.io.IOException;
import java.nio.ByteBuffer;
import software.bernie.shadowed.fasterxml.jackson.core.async.NonBlockingInputFeeder;

public interface ByteBufferFeeder
extends NonBlockingInputFeeder {
    public void feedInput(ByteBuffer var1) throws IOException;
}

