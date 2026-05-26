/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.eliotlash.mclib.math;

import java.util.HashSet;
import java.util.Set;

public enum Operation {
    ADD("+", 1){

        @Override
        public double calculate(double a10, double b10) {
            return a10 + b10;
        }
    }
    ,
    SUB("-", 1){

        @Override
        public double calculate(double a10, double b10) {
            return a10 - b10;
        }
    }
    ,
    MUL("*", 2){

        @Override
        public double calculate(double a10, double b10) {
            return a10 * b10;
        }
    }
    ,
    DIV("/", 2){

        @Override
        public double calculate(double a10, double b10) {
            return a10 / (b10 == 0.0 ? 1.0 : b10);
        }
    }
    ,
    MOD("%", 2){

        @Override
        public double calculate(double a10, double b10) {
            return a10 % b10;
        }
    }
    ,
    POW("^", 3){

        @Override
        public double calculate(double a10, double b10) {
            return Math.pow(a10, b10);
        }
    }
    ,
    AND("&&", 5){

        @Override
        public double calculate(double a10, double b10) {
            return a10 != 0.0 && b10 != 0.0 ? 1.0 : 0.0;
        }
    }
    ,
    OR("||", 5){

        @Override
        public double calculate(double a10, double b10) {
            return a10 != 0.0 || b10 != 0.0 ? 1.0 : 0.0;
        }
    }
    ,
    LESS("<", 5){

        @Override
        public double calculate(double a10, double b10) {
            return a10 < b10 ? 1.0 : 0.0;
        }
    }
    ,
    LESS_THAN("<=", 5){

        @Override
        public double calculate(double a10, double b10) {
            return a10 <= b10 ? 1.0 : 0.0;
        }
    }
    ,
    GREATER_THAN(">=", 5){

        @Override
        public double calculate(double a10, double b10) {
            return a10 >= b10 ? 1.0 : 0.0;
        }
    }
    ,
    GREATER(">", 5){

        @Override
        public double calculate(double a10, double b10) {
            return a10 > b10 ? 1.0 : 0.0;
        }
    }
    ,
    EQUALS("==", 5){

        @Override
        public double calculate(double a10, double b10) {
            return 13.equals(a10, b10) ? 1.0 : 0.0;
        }
    }
    ,
    NOT_EQUALS("!=", 5){

        @Override
        public double calculate(double a10, double b10) {
            return !14.equals(a10, b10) ? 1.0 : 0.0;
        }
    };

    public static final Set<String> OPERATORS;
    public final String sign;
    public final int value;

    public static boolean equals(double a10, double b10) {
        return Math.abs(a10 - b10) < 1.0E-5;
    }

    private Operation(String sign, int value) {
        this.sign = sign;
        this.value = value;
    }

    public abstract double calculate(double var1, double var3);

    static {
        OPERATORS = new HashSet<String>();
        for (Operation op : Operation.values()) {
            OPERATORS.add(op.sign);
        }
    }
}

