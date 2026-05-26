/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.math.functions.utility;

import java.util.Random;
import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.mclib.math.functions.Function;

public class DieRoll
extends Function {
    public Random random = new Random();

    public DieRoll(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 3;
    }

    @Override
    public double get() {
        double i2 = 0.0;
        double total = 0.0;
        while (i2 < this.getArg(0)) {
            total += Math.random() * (this.getArg(2) - this.getArg(2));
        }
        return total;
    }
}

