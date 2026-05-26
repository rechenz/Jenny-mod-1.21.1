/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 */
package software.bernie.geckolib3.model;

import java.util.Collections;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;

public abstract class AnimatedTickingGeoModel<T extends IAnimatable & IAnimationTickable>
extends AnimatedGeoModel<T> {
    public boolean isInitialized() {
        return !this.getAnimationProcessor().getModelRendererList().isEmpty();
    }

    @Override
    public void setLivingAnimations(T entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        AnimationData manager = entity.getFactory().getOrCreateAnimationData(uniqueID);
        if (manager.startTick == null) {
            manager.startTick = (float)((IAnimationTickable)entity).tickTimer() + Minecraft.func_71410_x().func_184121_ak();
        }
        if (!Minecraft.func_71410_x().func_147113_T() || manager.shouldPlayWhilePaused) {
            double gameTick = manager.tick = (double)((float)((IAnimationTickable)entity).tickTimer() + Minecraft.func_71410_x().func_184121_ak());
            double deltaTicks = gameTick - this.lastGameTickTime;
            this.seekTime += deltaTicks;
            this.lastGameTickTime = gameTick;
        }
        AnimationEvent<T> predicate = customPredicate == null ? new AnimationEvent<T>(entity, 0.0f, 0.0f, 0.0f, false, Collections.emptyList()) : customPredicate;
        predicate.animationTick = this.seekTime;
        this.getAnimationProcessor().preAnimationSetup((IAnimatable)predicate.getAnimatable(), this.seekTime);
        if (!this.getAnimationProcessor().getModelRendererList().isEmpty()) {
            this.getAnimationProcessor().tickAnimation((IAnimatable)entity, uniqueID, this.seekTime, predicate, GeckoLibCache.getInstance().parser, this.shouldCrashOnMissing);
        }
        if (!Minecraft.func_71410_x().func_147113_T() || manager.shouldPlayWhilePaused) {
            this.codeAnimations(entity, uniqueID, customPredicate);
        }
    }

    public void codeAnimations(T entity, Integer uniqueID, AnimationEvent<?> customPredicate) {
    }
}

