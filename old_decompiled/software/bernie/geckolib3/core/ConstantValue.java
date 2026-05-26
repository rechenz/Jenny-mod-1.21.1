/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;

public class ConstantValue
implements IValue {
    private final double value;

    public ConstantValue(double value) {
        this.value = value;
    }

    @Override
    public double get() {
        return this.value;
    }

    public static ConstantValue fromDouble(double d10) {
        return new ConstantValue(d10);
    }

    public static ConstantValue fromFloat(float d10) {
        return new ConstantValue(d10);
    }

    public static ConstantValue parseDouble(String s2) {
        return new ConstantValue(Double.parseDouble(s2));
    }

    public static ConstantValue parseFloat(String s2) {
        return new ConstantValue(Float.parseFloat(s2));
    }

    public static ConstantValue subtract(IValue first, IValue second) {
        return ConstantValue.fromDouble(first.get() - second.get());
    }
}

