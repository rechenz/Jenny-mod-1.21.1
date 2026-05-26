/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.math;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;

public class Group
implements IValue {
    private IValue value;

    public Group(IValue value) {
        this.value = value;
    }

    @Override
    public double get() {
        return this.value.get();
    }

    public String toString() {
        return "(" + this.value.toString() + ")";
    }
}

