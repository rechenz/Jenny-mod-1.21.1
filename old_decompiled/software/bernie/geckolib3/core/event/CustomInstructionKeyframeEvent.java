/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.event;

import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.KeyframeEvent;

public class CustomInstructionKeyframeEvent<T>
extends KeyframeEvent<T> {
    public final String instructions;

    public CustomInstructionKeyframeEvent(T entity, double animationTick, String instructions, AnimationController controller) {
        super(entity, animationTick, controller);
        this.instructions = instructions;
    }
}

