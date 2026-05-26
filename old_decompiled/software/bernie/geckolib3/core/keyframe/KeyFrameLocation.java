/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.keyframe;

import software.bernie.geckolib3.core.keyframe.KeyFrame;

public class KeyFrameLocation<T extends KeyFrame> {
    public T currentFrame;
    public double currentTick;

    public KeyFrameLocation(T currentFrame, double currentTick) {
        this.currentFrame = currentFrame;
        this.currentTick = currentTick;
    }
}

