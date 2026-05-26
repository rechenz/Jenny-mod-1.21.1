/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public interface IAnimatableModel<E> {
    default public double getCurrentTick() {
        return (double)(System.nanoTime() / 1000000L) / 50.0;
    }

    default public void setLivingAnimations(E entity, Integer uniqueID) {
        this.setLivingAnimations(entity, uniqueID, null);
    }

    public void setLivingAnimations(E var1, Integer var2, AnimationEvent var3);

    public AnimationProcessor getAnimationProcessor();

    public Animation getAnimation(String var1, IAnimatable var2);

    default public IBone getBone(String boneName) {
        IBone bone = this.getAnimationProcessor().getBone(boneName);
        if (bone == null) {
            throw new RuntimeException("Could not find bone: " + boneName);
        }
        return bone;
    }

    public void setMolangQueries(IAnimatable var1, double var2);
}

