/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.math.functions;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;

public abstract class Function
implements IValue {
    protected IValue[] args;
    protected String name;

    public Function(IValue[] values, String name) throws Exception {
        if (values.length < this.getRequiredArguments()) {
            String message = String.format("Function '%s' requires at least %s arguments. %s are given!", this.getName(), this.getRequiredArguments(), values.length);
            throw new Exception(message);
        }
        this.args = values;
        this.name = name;
    }

    public double getArg(int index) {
        if (index < 0 || index >= this.args.length) {
            return 0.0;
        }
        return this.args[index].get();
    }

    public String toString() {
        String args = "";
        for (int i2 = 0; i2 < this.args.length; ++i2) {
            args = args + this.args[i2].toString();
            if (i2 >= this.args.length - 1) continue;
            args = args + ", ";
        }
        return this.getName() + "(" + args + ")";
    }

    public String getName() {
        return this.name;
    }

    public int getRequiredArguments() {
        return 0;
    }
}

