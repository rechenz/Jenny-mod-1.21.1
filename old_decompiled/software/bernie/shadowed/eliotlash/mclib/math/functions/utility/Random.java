/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.math.functions.utility;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.mclib.math.functions.Function;

public class Random
extends Function {
    public java.util.Random random = new java.util.Random();

    public Random(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public double get() {
        double random = 0.0;
        if (this.args.length >= 3) {
            this.random.setSeed((long)this.getArg(2));
            random = this.random.nextDouble();
        } else {
            random = Math.random();
        }
        if (this.args.length >= 2) {
            double a10 = this.getArg(0);
            double b10 = this.getArg(1);
            double min = Math.min(a10, b10);
            double max = Math.max(a10, b10);
            random = random * (max - min) + min;
        } else if (this.args.length >= 1) {
            random *= this.getArg(0);
        }
        return random;
    }
}

