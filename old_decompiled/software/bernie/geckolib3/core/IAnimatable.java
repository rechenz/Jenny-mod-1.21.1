/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core;

import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public interface IAnimatable {
    public void registerControllers(AnimationData var1);

    public AnimationFactory getFactory();
}

