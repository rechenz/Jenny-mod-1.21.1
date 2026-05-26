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
import software.bernie.shadowed.eliotlash.mclib.math.Operation;
import software.bernie.shadowed.eliotlash.molang.MolangParser;
import software.bernie.shadowed.eliotlash.molang.expressions.MolangValue;

public abstract class MolangExpression
implements IValue {
    public MolangParser context;

    public static boolean isZero(MolangExpression expression) {
        return MolangExpression.isConstant(expression, 0.0);
    }

    public static boolean isOne(MolangExpression expression) {
        return MolangExpression.isConstant(expression, 1.0);
    }

    public static boolean isConstant(MolangExpression expression, double x2) {
        if (expression instanceof MolangValue) {
            MolangValue value = (MolangValue)expression;
            return value.value instanceof Constant && Operation.equals(value.value.get(), x2);
        }
        return false;
    }

    public static boolean isExpressionConstant(MolangExpression expression) {
        if (expression instanceof MolangValue) {
            MolangValue value = (MolangValue)expression;
            return value.value instanceof Constant;
        }
        return false;
    }

    public MolangExpression(MolangParser context) {
        this.context = context;
    }

    public JsonElement toJson() {
        return new JsonPrimitive(this.toString());
    }
}

