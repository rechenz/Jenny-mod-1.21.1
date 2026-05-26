/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemShield
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.dm;
import com.trolmastercard.sexmod.gx;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class d9
extends dm {
    protected static final Vec3i z = new Vec3i(255, 255, 255);
    static HashMap<Integer, Vec3i> A = new HashMap();

    public d9(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
    }

    public static void e() {
        A.clear();
    }

    protected Vec3i a(GeoBone geoBone) {
        String string = geoBone.getName();
        int n2 = string.hashCode() + this.j.getPersistentID().hashCode();
        Vec3i vec3i = A.get(n2);
        try {
            if (vec3i != null) {
                return vec3i;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw d9.a(illegalStateException);
        }
        vec3i = this.a(string);
        A.put(n2, vec3i);
        return vec3i;
    }

    protected abstract Vec3i a(String var1);

    protected void b(GeoBone geoBone, int n2) {
        List<GeoBone> list = geoBone.childBones;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            GeoBone geoBone2 = list.get(i2);
            if (n2 != i2) continue;
            GeoBone geoBone3 = geoBone2;
            geoBone3.setHidden(false);
            return;
        }
    }

    protected float a() {
        return 1.0f;
    }

    protected Vec3d a(ItemStack itemStack) {
        return new Vec3d(-90.0, 0.0, 0.0);
    }

    protected GeoBone a(GeoBone geoBone, int n2) {
        List<GeoBone> list = geoBone.childBones;
        GeoBone geoBone2 = null;
        list.sort(Comparator.comparingDouble(GeoBone::getPivotY));
        for (int i2 = 0; i2 < list.size(); ++i2) {
            GeoBone geoBone3 = list.get(i2);
            if (n2 == i2) {
                geoBone2 = geoBone3;
                geoBone2.setHidden(false);
                continue;
            }
            geoBone3.setHidden(true);
        }
        return geoBone2;
    }

    protected Vec3i a(Vec3i vec3i) {
        return vec3i;
    }

    /*
     * Could not resolve type clashes
     * Unable to fully structure code
     */
    @Override
    public void renderRecursively(BufferBuilder var1_1, GeoBone var2_2, float var3_3, float var4_4, float var5_5, float var6_6) {
        block72: {
            block84: {
                block71: {
                    block70: {
                        block69: {
                            block67: {
                                block68: {
                                    block78: {
                                        block65: {
                                            block66: {
                                                block76: {
                                                    block75: {
                                                        block63: {
                                                            block64: {
                                                                var7_7 = var2_2.getName();
                                                                if (!this.r) break block63;
                                                                try {
                                                                    block73: {
                                                                        if (!var7_7.equals("upperBody")) break block64;
                                                                        break block73;
                                                                        catch (IllegalStateException v0) {
                                                                            throw d9.a(v0);
                                                                        }
                                                                    }
                                                                    var2_2.setRotationX(var2_2.getRotationX() - 0.5f);
                                                                }
                                                                catch (IllegalStateException v1) {
                                                                    throw d9.a(v1);
                                                                }
                                                            }
                                                            try {
                                                                if (var7_7.equals("head")) {
                                                                    var2_2.setRotationX(var2_2.getRotationX() + 0.5f);
                                                                }
                                                            }
                                                            catch (IllegalStateException v2) {
                                                                throw d9.a(v2);
                                                            }
                                                            if (var7_7.equals("legL")) ** GOTO lbl28
                                                            try {
                                                                block74: {
                                                                    if (!var7_7.equals("legR")) break block63;
                                                                    break block74;
                                                                    catch (IllegalStateException v3) {
                                                                        throw d9.a(v3);
                                                                    }
                                                                }
                                                                var2_2.setPositionZ(var2_2.getPositionZ() + 1.0f);
                                                            }
                                                            catch (IllegalStateException v4) {
                                                                throw d9.a(v4);
                                                            }
                                                        }
                                                        try {
                                                            if (var7_7.equals("head")) {
                                                                this.a(var1_1, var2_2, Color.ofRGB(var3_3, var4_4, var5_5));
                                                            }
                                                        }
                                                        catch (IllegalStateException v5) {
                                                            throw d9.a(v5);
                                                        }
                                                        this.a(var7_7, var2_2);
                                                        this.a(var7_7, var2_2, this.w, var1_1);
                                                        if (!this.u) break block65;
                                                        if (this.s.func_77973_b() instanceof ItemBow) ** GOTO lbl55
                                                        break block75;
                                                        catch (IllegalStateException v6) {
                                                            throw d9.a(v6);
                                                        }
                                                    }
                                                    if (!(this.x.func_77973_b() instanceof ItemBow)) break block65;
                                                    break block76;
                                                    catch (IllegalStateException v7) {
                                                        throw d9.a(v7);
                                                    }
                                                }
                                                try {
                                                    block77: {
                                                        if (!var7_7.equals("armR")) break block66;
                                                        break block77;
                                                        catch (IllegalStateException v8) {
                                                            throw d9.a(v8);
                                                        }
                                                    }
                                                    var2_2.setRotationX(var2_2.getRotationX() - this.j.field_70125_A / 50.0f);
                                                }
                                                catch (IllegalStateException v9) {
                                                    throw d9.a(v9);
                                                }
                                            }
                                            try {
                                                if (var7_7.equals("armL")) {
                                                    var2_2.setRotationY(var2_2.getRotationY() - this.j.field_70125_A / 50.0f);
                                                }
                                            }
                                            catch (IllegalStateException v10) {
                                                throw d9.a(v10);
                                            }
                                            if (this.x.func_77973_b() instanceof ItemBow) {
                                                var8_8 = this.x;
                                                this.x = this.s;
                                                this.s = var8_8;
                                            }
                                        }
                                        if (!this.u) break block67;
                                        if (!(this.s.func_77973_b() instanceof ItemShield)) break block67;
                                        break block78;
                                        catch (IllegalStateException v11) {
                                            throw d9.a(v11);
                                        }
                                    }
                                    try {
                                        block79: {
                                            if (!var7_7.equals("armR")) break block68;
                                            break block79;
                                            catch (IllegalStateException v12) {
                                                throw d9.a(v12);
                                            }
                                        }
                                        var2_2.setRotationZ(0.0f);
                                        var2_2.setRotationX(0.5f);
                                        break block67;
                                    }
                                    catch (IllegalStateException v13) {
                                        throw d9.a(v13);
                                    }
                                }
                                if (!(this.x.func_77973_b() instanceof ItemShield)) break block67;
                                try {
                                    block80: {
                                        if (!var7_7.equals("armL")) break block67;
                                        break block80;
                                        catch (IllegalStateException v14) {
                                            throw d9.a(v14);
                                        }
                                    }
                                    var2_2.setRotationZ(0.0f);
                                    var2_2.setRotationX(0.5f);
                                }
                                catch (IllegalStateException v15) {
                                    throw d9.a(v15);
                                }
                            }
                            if (!var7_7.equals("weapon")) break block69;
                            try {
                                block81: {
                                    if (this.s.func_190926_b()) break block69;
                                    break block81;
                                    catch (IllegalStateException v16) {
                                        throw d9.a(v16);
                                    }
                                }
                                this.a(var1_1, var2_2, false);
                            }
                            catch (IllegalStateException v17) {
                                throw d9.a(v17);
                            }
                        }
                        if (!var7_7.equals("offhand")) break block70;
                        try {
                            block82: {
                                if (this.x.func_190926_b()) break block70;
                                break block82;
                                catch (IllegalStateException v18) {
                                    throw d9.a(v18);
                                }
                            }
                            this.a(var1_1, var2_2, true);
                        }
                        catch (IllegalStateException v19) {
                            throw d9.a(v19);
                        }
                    }
                    d9.MATRIX_STACK.push();
                    d9.MATRIX_STACK.translate(var2_2);
                    d9.MATRIX_STACK.moveToPivot(var2_2);
                    d9.MATRIX_STACK.rotate(var2_2);
                    d9.MATRIX_STACK.scale(var2_2);
                    d9.MATRIX_STACK.moveBackFromPivot(var2_2);
                    if (!"Head2".equals(var7_7)) break block71;
                    try {
                        block83: {
                            if (this.c()) break block71;
                            break block83;
                            catch (IllegalStateException v20) {
                                throw d9.a(v20);
                            }
                        }
                        d9.MATRIX_STACK.pop();
                        return;
                    }
                    catch (IllegalStateException v21) {
                        throw d9.a(v21);
                    }
                }
                if ("neck".equals(var7_7)) ** GOTO lbl164
                if (!"head".equals(var7_7)) break block72;
                break block84;
                catch (IllegalStateException v22) {
                    throw d9.a(v22);
                }
            }
            try {
                block85: {
                    if (this.a()) break block72;
                    break block85;
                    catch (IllegalStateException v23) {
                        throw d9.a(v23);
                    }
                }
                d9.MATRIX_STACK.pop();
                return;
            }
            catch (IllegalStateException v24) {
                throw d9.a(v24);
            }
        }
        if (!var2_2.isHidden) {
            var8_8 = this.a(var7_7, var3_3, var4_4, var5_5);
            var3_3 = var8_8.x;
            var4_4 = var8_8.y;
            var5_5 = var8_8.z;
            var9_10 = var8_8.w;
            if (!this.p.contains(var7_7)) {
                for (Object var12_12 : var2_2.childCubes) {
                    d9.MATRIX_STACK.push();
                    GlStateManager.func_179094_E();
                    this.q = var2_2;
                    this.a(var1_1, (GeoCube)var12_12, var2_2, var3_3, var4_4, var5_5, var6_6, var9_10);
                    GlStateManager.func_179121_F();
                    d9.MATRIX_STACK.pop();
                }
            }
            for (Object var12_12 : var2_2.childBones) {
                try {
                    if (var9_10 == 0.0) {
                        this.renderRecursively(var1_1, (GeoBone)var12_12, var3_3, var4_4, var5_5, var6_6);
                        continue;
                    }
                }
                catch (IllegalStateException v25) {
                    throw d9.a(v25);
                }
                this.a(var1_1, (GeoBone)var12_12, var3_3, var4_4, var5_5, var6_6, var9_10);
            }
        }
        try {
            d9.MATRIX_STACK.pop();
        }
        catch (IllegalStateException var8_9) {
            // empty catch block
        }
    }

    public void a(BufferBuilder bufferBuilder, GeoCube geoCube, GeoBone geoBone, float f10, float f11, float f12, float f13, double d10) {
        MATRIX_STACK.moveToPivot(geoCube);
        MATRIX_STACK.rotate(geoCube);
        MATRIX_STACK.moveBackFromPivot(geoCube);
        for (GeoQuad geoQuad : geoCube.quads) {
            Vec3d vec3d;
            Vector3f vector3f;
            block27: {
                block26: {
                    block25: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d9.a(illegalStateException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.func_177958_n(), (float)geoQuad.normal.func_177956_o(), (float)geoQuad.normal.func_177952_p());
                        try {
                            try {
                                try {
                                    MATRIX_STACK.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block25;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw d9.a(illegalStateException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block25;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d9.a(illegalStateException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d9.a(illegalStateException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block26;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw d9.a(illegalStateException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block26;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d9.a(illegalStateException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d9.a(illegalStateException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block27;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw d9.a(illegalStateException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block27;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw d9.a(illegalStateException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (IllegalStateException illegalStateException) {
                    throw d9.a(illegalStateException);
                }
            }
            if (this.c(geoBone.getName())) {
                vec3d = new Vec3d((double)f10, (double)f11, (double)f12);
            } else {
                GeoVertex[] geoVertexArray = this.a(geoBone);
                geoVertexArray = this.a((Vec3i)geoVertexArray);
                vec3d = gx.a(this, geoBone, new Vec3d((double)((float)geoVertexArray.func_177958_n() / 255.0f), (double)((float)geoVertexArray.func_177956_o() / 255.0f), (double)((float)geoVertexArray.func_177952_p() / 255.0f)), vector3f);
            }
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.func_181662_b((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).func_187315_a((double)geoVertex.textureU + d10, (double)geoVertex.textureV).func_181666_a((float)vec3d.field_72450_a, (float)vec3d.field_72448_b, (float)vec3d.field_72449_c, f13).func_181663_c(vector3f.getX(), vector3f.getY(), vector3f.getZ()).func_181675_d();
            }
        }
    }

    protected boolean c(String string) {
        return string.startsWith("armor");
    }

    private static IllegalStateException a(IllegalStateException illegalStateException) {
        return illegalStateException;
    }
}

