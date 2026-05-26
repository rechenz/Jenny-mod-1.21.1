/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.snapshot;

import software.bernie.geckolib3.core.processor.IBone;

public class BoneSnapshot {
    public String name;
    private IBone modelRenderer;
    public float scaleValueX;
    public float scaleValueY;
    public float scaleValueZ;
    public float positionOffsetX;
    public float positionOffsetY;
    public float positionOffsetZ;
    public float rotationValueX;
    public float rotationValueY;
    public float rotationValueZ;
    public float mostRecentResetRotationTick = 0.0f;
    public float mostRecentResetPositionTick = 0.0f;
    public float mostRecentResetScaleTick = 0.0f;
    public boolean isCurrentlyRunningRotationAnimation = true;
    public boolean isCurrentlyRunningPositionAnimation = true;
    public boolean isCurrentlyRunningScaleAnimation = true;

    public BoneSnapshot(IBone modelRenderer) {
        this.rotationValueX = modelRenderer.getRotationX();
        this.rotationValueY = modelRenderer.getRotationY();
        this.rotationValueZ = modelRenderer.getRotationZ();
        this.positionOffsetX = modelRenderer.getPositionX();
        this.positionOffsetY = modelRenderer.getPositionY();
        this.positionOffsetZ = modelRenderer.getPositionZ();
        this.scaleValueX = modelRenderer.getScaleX();
        this.scaleValueY = modelRenderer.getScaleY();
        this.scaleValueZ = modelRenderer.getScaleZ();
        this.modelRenderer = modelRenderer;
        this.name = modelRenderer.getName();
    }

    public BoneSnapshot(IBone modelRenderer, boolean dontSaveRotations) {
        if (dontSaveRotations) {
            this.rotationValueX = 0.0f;
            this.rotationValueY = 0.0f;
            this.rotationValueZ = 0.0f;
        }
        this.rotationValueX = modelRenderer.getRotationX();
        this.rotationValueY = modelRenderer.getRotationY();
        this.rotationValueZ = modelRenderer.getRotationZ();
        this.positionOffsetX = modelRenderer.getPositionX();
        this.positionOffsetY = modelRenderer.getPositionY();
        this.positionOffsetZ = modelRenderer.getPositionZ();
        this.scaleValueX = modelRenderer.getScaleX();
        this.scaleValueY = modelRenderer.getScaleY();
        this.scaleValueZ = modelRenderer.getScaleZ();
        this.modelRenderer = modelRenderer;
        this.name = modelRenderer.getName();
    }

    public BoneSnapshot(BoneSnapshot snapshot) {
        this.scaleValueX = snapshot.scaleValueX;
        this.scaleValueY = snapshot.scaleValueY;
        this.scaleValueZ = snapshot.scaleValueZ;
        this.positionOffsetX = snapshot.positionOffsetX;
        this.positionOffsetY = snapshot.positionOffsetY;
        this.positionOffsetZ = snapshot.positionOffsetZ;
        this.rotationValueX = snapshot.rotationValueX;
        this.rotationValueY = snapshot.rotationValueY;
        this.rotationValueZ = snapshot.rotationValueZ;
        this.modelRenderer = snapshot.modelRenderer;
        this.name = snapshot.name;
    }

    public boolean equals(Object o2) {
        if (this == o2) {
            return true;
        }
        if (o2 == null || this.getClass() != o2.getClass()) {
            return false;
        }
        BoneSnapshot that = (BoneSnapshot)o2;
        return this.name.equals(that.name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}

