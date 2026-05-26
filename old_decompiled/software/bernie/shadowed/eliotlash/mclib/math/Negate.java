/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.math;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;

public class Negate
implements IValue {
    public IValue value;

    public Negate(IValue value) {
        this.value = value;
    }

    @Override
    public double get() {
        return this.value.get() == 0.0 ? 1.0 : 0.0;
    }

    public String toString() {
        return "!" + this.value.toString();
    }
}

