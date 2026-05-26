/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.math.functions.classic;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.mclib.math.functions.Function;

public class ASin
extends Function {
    public ASin(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 1;
    }

    @Override
    public double get() {
        return Math.asin(this.getArg(0));
    }
}

