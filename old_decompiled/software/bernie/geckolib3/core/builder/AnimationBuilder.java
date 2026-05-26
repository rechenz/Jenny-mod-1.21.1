/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.builder;

import java.util.ArrayList;
import java.util.List;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.builder.RawAnimation;

public class AnimationBuilder {
    private List<RawAnimation> animationList = new ArrayList<RawAnimation>();

    public AnimationBuilder addAnimation(String animationName, ILoopType loopType) {
        this.animationList.add(new RawAnimation(animationName, loopType));
        return this;
    }

    @Deprecated
    public AnimationBuilder addAnimation(String animationName, Boolean shouldLoop) {
        this.animationList.add(new RawAnimation(animationName, shouldLoop));
        return this;
    }

    public AnimationBuilder addAnimation(String animationName) {
        this.animationList.add(new RawAnimation(animationName, null));
        return this;
    }

    public AnimationBuilder addRepeatingAnimation(String animationName, int timesToRepeat) {
        assert (timesToRepeat > 0);
        for (int i2 = 0; i2 < timesToRepeat; ++i2) {
            this.addAnimation(animationName, ILoopType.EDefaultLoopTypes.PLAY_ONCE);
        }
        return this;
    }

    public AnimationBuilder playOnce(String animationName) {
        return this.addAnimation(animationName, ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    }

    public AnimationBuilder loop(String animationName) {
        return this.addAnimation(animationName, ILoopType.EDefaultLoopTypes.LOOP);
    }

    public AnimationBuilder playAndHold(String animationName) {
        return this.addAnimation(animationName, ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME);
    }

    public AnimationBuilder delayNext(int waitTimeTicks) {
        throw new UnsupportedOperationException("This isn't implemented yet, sorry!");
    }

    public AnimationBuilder playAndHoldFor(String animationName, int waitTimeTicks) {
        this.playAndHold(animationName);
        return this.delayNext(waitTimeTicks);
    }

    public AnimationBuilder clearAnimations() {
        this.animationList.clear();
        return this;
    }

    public List<RawAnimation> getRawAnimationList() {
        return this.animationList;
    }
}

