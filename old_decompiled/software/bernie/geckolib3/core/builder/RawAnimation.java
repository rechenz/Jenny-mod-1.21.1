/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.builder;

import java.util.Objects;
import software.bernie.geckolib3.core.builder.ILoopType;

public class RawAnimation {
    public String animationName;
    public ILoopType loopType;

    public RawAnimation(String animationName, ILoopType loop) {
        this.animationName = animationName;
        this.loopType = loop;
    }

    @Deprecated
    public RawAnimation(String animationName, boolean loop) {
        this(animationName, loop ? ILoopType.EDefaultLoopTypes.LOOP : ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawAnimation)) {
            return false;
        }
        RawAnimation animation = (RawAnimation)obj;
        return animation.loopType == this.loopType && animation.animationName.equals(this.animationName);
    }

    public int hashCode() {
        return Objects.hash(this.animationName, this.loopType);
    }
}

