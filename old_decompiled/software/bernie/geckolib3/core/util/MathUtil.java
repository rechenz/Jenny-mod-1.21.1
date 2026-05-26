/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.util;

import java.util.function.Function;
import software.bernie.geckolib3.core.easing.EasingManager;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.keyframe.AnimationPoint;

public class MathUtil {
    public static float lerpValues(AnimationPoint animationPoint, EasingType easingType, Function<Double, Double> customEasingMethod) {
        if (animationPoint.currentTick >= animationPoint.animationEndTick) {
            return animationPoint.animationEndValue.floatValue();
        }
        if (animationPoint.currentTick == 0.0 && animationPoint.animationEndTick == 0.0) {
            return animationPoint.animationEndValue.floatValue();
        }
        if (easingType == EasingType.CUSTOM && customEasingMethod != null) {
            return MathUtil.lerpValues(customEasingMethod.apply(animationPoint.currentTick / animationPoint.animationEndTick), animationPoint.animationStartValue, animationPoint.animationEndValue);
        }
        if (easingType == EasingType.NONE && animationPoint.keyframe != null) {
            easingType = animationPoint.keyframe.easingType;
        }
        double ease = EasingManager.ease(animationPoint.currentTick / animationPoint.animationEndTick, easingType, animationPoint.keyframe == null ? null : animationPoint.keyframe.easingArgs);
        return MathUtil.lerpValues(ease, animationPoint.animationStartValue, animationPoint.animationEndValue);
    }

    public static float lerpValues(double percentCompleted, double startValue, double endValue) {
        return (float)MathUtil.lerp(percentCompleted, startValue, endValue);
    }

    public static double lerp(double pct, double start, double end) {
        return start + pct * (end - start);
    }
}

