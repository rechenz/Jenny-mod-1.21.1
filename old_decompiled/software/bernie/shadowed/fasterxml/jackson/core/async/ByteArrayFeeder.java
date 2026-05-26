/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.async;

import java.io.IOException;
import software.bernie.shadowed.fasterxml.jackson.core.async.NonBlockingInputFeeder;

public interface ByteArrayFeeder
extends NonBlockingInputFeeder {
    public void feedInput(byte[] var1, int var2, int var3) throws IOException;
}

