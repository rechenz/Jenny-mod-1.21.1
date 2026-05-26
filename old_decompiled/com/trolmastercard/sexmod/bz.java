/*
 * Decompiled with CFR 0.152.
 */
package com.trolmastercard.sexmod;

import java.util.function.Function;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.easing.EasingType;

public class bz<T extends IAnimatable>
extends AnimationController<T> {
    public bz(T t2, String string, float f10, AnimationController.IAnimationPredicate<T> iAnimationPredicate) {
        super(t2, string, f10, iAnimationPredicate);
    }

    public bz(T t2, String string, float f10, EasingType easingType, AnimationController.IAnimationPredicate<T> iAnimationPredicate) {
        super(t2, string, f10, easingType, iAnimationPredicate);
    }

    public bz(T t2, String string, float f10, Function<Double, Double> function, AnimationController.IAnimationPredicate<T> iAnimationPredicate) {
        super(t2, string, f10, function, iAnimationPredicate);
    }
}

