/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package software.bernie.shadowed.eliotlash.molang.expressions;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import software.bernie.shadowed.eliotlash.mclib.math.Constant;
import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.molang.MolangParser;
import software.bernie.shadowed.eliotlash.molang.expressions.MolangExpression;

public class MolangValue
extends MolangExpression {
    public IValue value;
    public boolean returns;

    public MolangValue(MolangParser context, IValue value) {
        super(context);
        this.value = value;
    }

    public MolangExpression addReturn() {
        this.returns = true;
        return this;
    }

    @Override
    public double get() {
        return this.value.get();
    }

    public String toString() {
        return (this.returns ? "return " : "") + this.value.toString();
    }

    @Override
    public JsonElement toJson() {
        if (this.value instanceof Constant) {
            return new JsonPrimitive((Number)this.value.get());
        }
        return super.toJson();
    }
}

