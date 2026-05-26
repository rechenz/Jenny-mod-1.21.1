/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.core.keyframe;

import java.util.ArrayList;
import java.util.List;
import software.bernie.geckolib3.core.keyframe.KeyFrame;

public class VectorKeyFrameList<T extends KeyFrame> {
    public List<T> xKeyFrames;
    public List<T> yKeyFrames;
    public List<T> zKeyFrames;

    public VectorKeyFrameList(List<T> XKeyFrames, List<T> YKeyFrames, List<T> ZKeyFrames) {
        this.xKeyFrames = XKeyFrames;
        this.yKeyFrames = YKeyFrames;
        this.zKeyFrames = ZKeyFrames;
    }

    public VectorKeyFrameList() {
        this.xKeyFrames = new ArrayList<T>();
        this.yKeyFrames = new ArrayList<T>();
        this.zKeyFrames = new ArrayList<T>();
    }

    public double getLastKeyframeTime() {
        double xTime = 0.0;
        for (KeyFrame frame : this.xKeyFrames) {
            xTime += frame.getLength().doubleValue();
        }
        double yTime = 0.0;
        for (KeyFrame frame : this.yKeyFrames) {
            yTime += frame.getLength().doubleValue();
        }
        double zTime = 0.0;
        for (KeyFrame frame : this.zKeyFrames) {
            zTime += frame.getLength().doubleValue();
        }
        return Math.max(xTime, Math.max(yTime, zTime));
    }
}

