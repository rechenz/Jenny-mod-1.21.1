/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix3f
 *  javax.vecmath.Matrix4f
 *  javax.vecmath.Vector3f
 *  org.lwjgl.util.vector.Quaternion
 */
package software.bernie.geckolib3.util;

import java.util.Stack;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import org.lwjgl.util.vector.Quaternion;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;

public class MatrixStack {
    private Stack<Matrix4f> model = new Stack();
    private Stack<Matrix3f> normal = new Stack();
    private Matrix4f tempModelMatrix = new Matrix4f();
    private Matrix3f tempNormalMatrix = new Matrix3f();
    private float[] tempArray = new float[16];

    public MatrixStack() {
        Matrix4f model = new Matrix4f();
        Matrix3f normal = new Matrix3f();
        model.setIdentity();
        normal.setIdentity();
        this.model.add(model);
        this.normal.add(normal);
    }

    public Matrix4f getModelMatrix() {
        return this.model.peek();
    }

    public Matrix3f getNormalMatrix() {
        return this.normal.peek();
    }

    public void push() {
        this.model.add(new Matrix4f(this.model.peek()));
        this.normal.add(new Matrix3f(this.normal.peek()));
    }

    public void pop() {
        if (this.model.size() == 1) {
            throw new IllegalStateException("A one level stack can't be popped!");
        }
        this.model.pop();
        this.normal.pop();
    }

    public void translate(float x2, float y2, float z2) {
        this.translate(new Vector3f(x2, y2, z2));
    }

    public void translate(Vector3f vec) {
        this.tempModelMatrix.setIdentity();
        this.tempModelMatrix.setTranslation(vec);
        this.model.peek().mul(this.tempModelMatrix);
    }

    public void moveToPivot(GeoCube cube) {
        Vector3f pivot = cube.pivot;
        this.translate(pivot.getX() / 16.0f, pivot.getY() / 16.0f, pivot.getZ() / 16.0f);
    }

    public void moveBackFromPivot(GeoCube cube) {
        Vector3f pivot = cube.pivot;
        this.translate(-pivot.getX() / 16.0f, -pivot.getY() / 16.0f, -pivot.getZ() / 16.0f);
    }

    public void moveToPivot(GeoBone bone) {
        this.translate(bone.rotationPointX / 16.0f, bone.rotationPointY / 16.0f, bone.rotationPointZ / 16.0f);
    }

    public void moveBackFromPivot(GeoBone bone) {
        this.translate(-bone.rotationPointX / 16.0f, -bone.rotationPointY / 16.0f, -bone.rotationPointZ / 16.0f);
    }

    public void translate(GeoBone bone) {
        this.translate(-bone.getPositionX() / 16.0f, bone.getPositionY() / 16.0f, bone.getPositionZ() / 16.0f);
    }

    public void scale(float x2, float y2, float z2) {
        this.tempModelMatrix.setIdentity();
        this.tempModelMatrix.setM00(x2);
        this.tempModelMatrix.setM11(y2);
        this.tempModelMatrix.setM22(z2);
        this.model.peek().mul(this.tempModelMatrix);
        if (x2 < 0.0f || y2 < 0.0f || z2 < 0.0f) {
            this.tempNormalMatrix.setIdentity();
            this.tempNormalMatrix.setM00(x2 < 0.0f ? -1.0f : 1.0f);
            this.tempNormalMatrix.setM11(y2 < 0.0f ? -1.0f : 1.0f);
            this.tempNormalMatrix.setM22(z2 < 0.0f ? -1.0f : 1.0f);
            this.normal.peek().mul(this.tempNormalMatrix);
        }
    }

    public void scale(GeoBone bone) {
        this.scale(bone.getScaleX(), bone.getScaleY(), bone.getScaleZ());
    }

    public void rotateX(float radian) {
        this.tempModelMatrix.setIdentity();
        this.tempModelMatrix.rotX(radian);
        this.tempNormalMatrix.setIdentity();
        this.tempNormalMatrix.rotX(radian);
        this.model.peek().mul(this.tempModelMatrix);
        this.normal.peek().mul(this.tempNormalMatrix);
    }

    public void rotateY(float radian) {
        this.tempModelMatrix.setIdentity();
        this.tempModelMatrix.rotY(radian);
        this.tempNormalMatrix.setIdentity();
        this.tempNormalMatrix.rotY(radian);
        this.model.peek().mul(this.tempModelMatrix);
        this.normal.peek().mul(this.tempNormalMatrix);
    }

    public void rotateZ(float radian) {
        this.tempModelMatrix.setIdentity();
        this.tempModelMatrix.rotZ(radian);
        this.tempNormalMatrix.setIdentity();
        this.tempNormalMatrix.rotZ(radian);
        this.model.peek().mul(this.tempModelMatrix);
        this.normal.peek().mul(this.tempNormalMatrix);
    }

    public void rotate(GeoBone bone) {
        if (bone.getRotationZ() != 0.0f) {
            this.rotateZ(bone.getRotationZ());
        }
        if (bone.getRotationY() != 0.0f) {
            this.rotateY(bone.getRotationY());
        }
        if (bone.getRotationX() != 0.0f) {
            this.rotateX(bone.getRotationX());
        }
    }

    public void rotate(GeoCube bone) {
        Vector3f rotation = bone.rotation;
        Matrix4f matrix4f = new Matrix4f();
        Matrix3f matrix3f = new Matrix3f();
        this.tempModelMatrix.setIdentity();
        matrix4f.rotZ(rotation.getZ());
        this.tempModelMatrix.mul(matrix4f);
        matrix4f.rotY(rotation.getY());
        this.tempModelMatrix.mul(matrix4f);
        matrix4f.rotX(rotation.getX());
        this.tempModelMatrix.mul(matrix4f);
        this.tempNormalMatrix.setIdentity();
        matrix3f.rotZ(rotation.getZ());
        this.tempNormalMatrix.mul(matrix3f);
        matrix3f.rotY(rotation.getY());
        this.tempNormalMatrix.mul(matrix3f);
        matrix3f.rotX(rotation.getX());
        this.tempNormalMatrix.mul(matrix3f);
        this.model.peek().mul(this.tempModelMatrix);
        this.normal.peek().mul(this.tempNormalMatrix);
    }

    private Quaternion fromAngles(float x2, float y2, float z2) {
        float sx = (float)Math.sin(0.5f * x2);
        float cx2 = (float)Math.cos(0.5f * x2);
        float sy = (float)Math.sin(0.5f * y2);
        float cy2 = (float)Math.cos(0.5f * y2);
        float sz = (float)Math.sin(0.5f * z2);
        float cz2 = (float)Math.cos(0.5f * z2);
        float ox = sx * cy2 * cz2 + cx2 * sy * sz;
        float oy = cx2 * sy * cz2 - sx * cy2 * sz;
        float oz = sx * sy * cz2 + cx2 * cy2 * sz;
        float ow = cx2 * cy2 * cz2 - sx * sy * sz;
        return new Quaternion(ox, oy, oz, ow);
    }
}

