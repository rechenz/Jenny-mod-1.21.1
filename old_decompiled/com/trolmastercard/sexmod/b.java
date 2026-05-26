/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.culling.ICamera
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.c8;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.cy;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fs;
import com.trolmastercard.sexmod.fw;
import com.trolmastercard.sexmod.gc;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gt;
import com.trolmastercard.sexmod.gw;
import com.trolmastercard.sexmod.gx;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public class b
extends GeoEntityRenderer<cy> {
    public static final float e = 1.876945f;
    public static final float i = 2.876945f;
    Minecraft a;
    cy c = null;
    br.b b = null;
    HashMap<String, String> h = new HashMap();
    HashMap<String, String> f = new HashMap();
    HashMap<String, gt> g = new HashMap();
    public static boolean k = false;
    Vec3d d = new Vec3d(1.0, 1.0, 1.0);
    Vec3d j;

    public b(RenderManager renderManager, AnimatedGeoModel<cy> animatedGeoModel) {
        super(renderManager, animatedGeoModel);
        this.a = Minecraft.func_71410_x();
        this.a();
    }

    void a() {
        this.h.put("customLegL", "legL");
        this.h.put("customShinL", "shinL");
        this.h.put("customLegR", "legR");
        this.h.put("customShinR", "shinR");
        this.f.put("top", "upperBody");
        this.f.put("customArmL", "armL");
        this.f.put("customLowerArmL", "lowerArmL");
        this.f.put("customArmR", "armR");
        this.f.put("customLowerArmR", "lowerArmR");
        this.g.put("lowerArmR", em2 -> gc.c(em2.ai()));
        this.g.put("lowerArmL", em2 -> gc.c(em2.T()));
    }

    boolean d(cy cy2) {
        String string = cy2.a();
        try {
            if (cy2.f) {
                return false;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        try {
            if (br.f(string)) {
                return false;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        try {
            if (br.g() != null) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        UUID uUID = cy2.b();
        em em2 = em.b(uUID);
        try {
            if (em2 == null) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        HashSet<String> hashSet = em2.Y();
        hashSet.remove(string);
        String string2 = em.a(hashSet);
        ge.b.sendToServer((IMessage)new fw(string2, cy2.b()));
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public static void a(em em2, float f10) {
        try {
            if (em2.field_70128_L) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        try {
            if (!em2.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        try {
            if (!em2.H()) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
        for (String string : em2.Y()) {
            cy cy2 = new cy(em2.field_70170_p, em2.f(), string);
            k = true;
            renderManager.func_188391_a((Entity)cy2, 0.0, 0.0, 0.0, 0.0f, f10, false);
        }
    }

    public boolean a(cy cy2, ICamera iCamera, double d10, double d11, double d12) {
        return super.func_177071_a((Entity)cy2, iCamera, d10, d11, d12);
    }

    boolean a(float f10) {
        try {
            if (f10 == 2.876945f) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        try {
            if (f10 == 1.876945f) {
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        try {
            if (k) {
                k = false;
                return true;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        return false;
    }

    void a(br.b b10, cy cy2, float f10) {
        Vec3d vec3d;
        block6: {
            try {
                try {
                    if (b10 != null && b10.i() != c8.DEFAULT) break block6;
                }
                catch (IllegalStateException illegalStateException) {
                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                }
                this.j = null;
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
        }
        try {
            GL11.glDisable((int)2896);
            b b11 = this;
            vec3d = b10.i() == c8.SEXMOD ? cj.a(cy2, f10) : null;
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        b11.j = vec3d;
    }

    public void a(cy cy2, double d10, double d11, double d12, float f10, float f11) {
        EntityPlayer entityPlayer;
        UUID uUID;
        em em2;
        em em3;
        block36: {
            br.b b10;
            block35: {
                try {
                    if (!this.a(f11)) {
                        return;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                }
                try {
                    if (br.d) {
                        return;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                }
                try {
                    if (this.d(cy2)) {
                        return;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                }
                cy2.c = new MatrixStack();
                b10 = br.b(cy2.a());
                try {
                    try {
                        this.c = cy2;
                        this.b = b10;
                        this.a(b10, cy2, f11);
                        if (f11 != 1.876945f && f11 != 2.876945f) break block35;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw com.trolmastercard.sexmod.b.a(illegalStateException);
                    }
                    this.d = new Vec3d(1.0, 1.0, 1.0);
                    super.doRender(cy2, d10, d11, d12, f10, f11);
                    GL11.glEnable((int)2896);
                    return;
                }
                catch (IllegalStateException illegalStateException) {
                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                }
            }
            UUID uUID2 = cy2.b();
            try {
                if (uUID2 == null) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
            em3 = em.b(uUID2);
            try {
                if (em3 == null) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
            try {
                try {
                    try {
                        if (b10 == null || b10.a()) break block36;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw com.trolmastercard.sexmod.b.a(illegalStateException);
                    }
                    if (em3.ah() != 0) break block36;
                }
                catch (IllegalStateException illegalStateException) {
                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                }
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
        }
        if (!(em3 instanceof ei)) {
            em2 = em3;
        } else {
            em em4;
            uUID = ((ei)em3).m();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
            entityPlayer = cy2.field_70170_p.func_152378_a(uUID);
            try {
                em4 = entityPlayer == null ? em3 : entityPlayer;
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
            em2 = em4;
        }
        uUID = em3.a(this.a, cy2, (EntityLivingBase)em2, f11);
        entityPlayer = new BlockPos(Math.floor(((EntityLivingBase)em2).field_70165_t), Math.floor(((EntityLivingBase)em2).field_70163_u), Math.floor(((EntityLivingBase)em2).field_70161_v));
        int n2 = ((EntityLivingBase)em2).field_70170_p.func_175721_c((BlockPos)entityPlayer, true);
        Vec3d vec3d = new Vec3d(1.0, 1.0, 1.0);
        float f12 = be.b(n2, 10.0f, 15.0f) / 15.0f;
        try {
            this.d = new Vec3d(vec3d.field_72450_a * (double)f12, vec3d.field_72448_b * (double)f12, vec3d.field_72449_c * (double)f12);
            GlStateManager.func_179094_E();
            GlStateManager.func_179137_b((double)((Vec3d)uUID).field_72450_a, (double)((Vec3d)uUID).field_72448_b, (double)((Vec3d)uUID).field_72449_c);
            if (em3.Q()) {
                GlStateManager.func_179114_b((float)em3.I().floatValue(), (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        super.doRender(cy2, 0.0, 0.0, 0.0, f10, f11);
        GlStateManager.func_179121_F();
        GL11.glEnable((int)2896);
    }

    public static Vec3d a(Minecraft minecraft, cy cy2, EntityLivingBase entityLivingBase, em em2, float f10) {
        Vec3d vec3d;
        Vec3d vec3d2;
        if (em2.Q()) {
            vec3d2 = em2.o();
            float f11 = em2.I().floatValue();
            cy2.field_70169_q = vec3d2.field_72450_a;
            cy2.field_70167_r = vec3d2.field_72448_b;
            cy2.field_70166_s = vec3d2.field_72449_c;
            cy2.field_70142_S = vec3d2.field_72450_a;
            cy2.field_70137_T = vec3d2.field_72448_b;
            cy2.field_70136_U = vec3d2.field_72449_c;
            cy2.field_70165_t = vec3d2.field_72450_a;
            cy2.field_70163_u = vec3d2.field_72448_b;
            cy2.field_70161_v = vec3d2.field_72449_c;
            cy2.field_70177_z = f11;
            cy2.field_70126_B = f11;
            cy2.field_70759_as = f11;
            cy2.field_70758_at = f11;
            cy2.field_70761_aq = f11;
            cy2.field_70760_ar = f11;
            cy2.field_70125_A = f11;
            cy2.field_70127_C = f11;
            vec3d = vec3d2;
        } else {
            cy2.field_70177_z = entityLivingBase.field_70177_z;
            cy2.field_70126_B = entityLivingBase.field_70126_B;
            cy2.field_70759_as = entityLivingBase.field_70759_as;
            cy2.field_70758_at = entityLivingBase.field_70758_at;
            cy2.field_70761_aq = entityLivingBase.field_70761_aq;
            cy2.field_70760_ar = entityLivingBase.field_70760_ar;
            cy2.field_70125_A = entityLivingBase.field_70125_A;
            cy2.field_70127_C = entityLivingBase.field_70127_C;
            cy2.field_70169_q = entityLivingBase.field_70169_q;
            cy2.field_70167_r = entityLivingBase.field_70167_r;
            cy2.field_70166_s = entityLivingBase.field_70166_s;
            cy2.field_70142_S = entityLivingBase.field_70142_S;
            cy2.field_70137_T = entityLivingBase.field_70137_T;
            cy2.field_70136_U = entityLivingBase.field_70136_U;
            cy2.field_70165_t = entityLivingBase.field_70165_t;
            cy2.field_70163_u = entityLivingBase.field_70163_u;
            cy2.field_70161_v = entityLivingBase.field_70161_v;
            vec3d = b6.a(new Vec3d(entityLivingBase.field_70142_S, entityLivingBase.field_70137_T, entityLivingBase.field_70136_U), entityLivingBase.func_174791_d(), (double)f10);
        }
        vec3d2 = minecraft.field_71439_g;
        Vec3d vec3d3 = b6.a(new Vec3d(vec3d2.field_70142_S, vec3d2.field_70137_T, vec3d2.field_70136_U), vec3d2.func_174791_d(), (double)f10);
        return vec3d.func_178788_d(vec3d3);
    }

    public void a(GeoModel geoModel, cy cy2, float f10, float f11, float f12, float f13, float f14) {
        GlStateManager.func_179129_p();
        GlStateManager.func_179091_B();
        BufferBuilder bufferBuilder = Tessellator.func_178181_a().func_178180_c();
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181712_l);
        for (GeoBone geoBone : geoModel.topLevelBones) {
            try {
                if (f10 != 1.876945f) {
                    this.a(cy2, geoBone, f10);
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
            cy2.c.translate(-geoBone.getPivotX() / 16.0f, -geoBone.getPivotY() / 16.0f, -geoBone.getPivotZ() / 16.0f);
            this.renderRecursively(bufferBuilder, geoBone, f11, f12, f13, f14);
        }
        Tessellator.func_178181_a().func_78381_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179089_o();
    }

    EntityLivingBase c(cy cy2) {
        em em2;
        em em3 = this.b(cy2);
        try {
            if (em3 == null) {
                return null;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        if (!(em3 instanceof ei)) {
            em2 = em3;
        } else {
            em em4;
            EntityPlayer entityPlayer = cy2.field_70170_p.func_152378_a(((ei)em3).m());
            try {
                em4 = entityPlayer == null ? em3 : entityPlayer;
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
            em2 = em4;
        }
        return em2;
    }

    em b(cy cy2) {
        UUID uUID = cy2.b();
        em em2 = fs.a(uUID);
        try {
            if (em2 != null) {
                return em2;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        return em.b(uUID);
    }

    void a(cy cy2, GeoBone geoBone, float f10) {
        String string = this.a(cy2);
        try {
            if (string == null) {
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        this.a(cy2, geoBone, f10, string);
    }

    void a(cy cy2, GeoBone geoBone, float f10, String string) {
        block4: {
            em em2 = this.b(cy2);
            EntityLivingBase entityLivingBase = this.c(cy2);
            try {
                try {
                    cy2.c = em2.a(string, false);
                    if (cy2.f && f10 == 2.876945f) break block4;
                }
                catch (IllegalStateException illegalStateException) {
                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                }
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
        }
        cy2.c.scale(0.5f, 0.5f, 0.5f);
        cy2.c.rotateY((float)Math.toRadians(-com.trolmastercard.sexmod.a.b));
    }

    String a(cy cy2) {
        try {
            if (cy2.f) {
                return cy2.d.boneName;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        br.b b10 = br.b(cy2.a());
        try {
            if (b10 == null) {
                return null;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        try {
            if (gw.CUSTOM_BONE.equals((Object)b10.j())) {
                return b10.b();
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw com.trolmastercard.sexmod.b.a(illegalStateException);
        }
        return b10.j().boneName;
    }

    @Override
    public void renderRecursively(BufferBuilder bufferBuilder, GeoBone geoBone, float f10, float f11, float f12, float f13) {
        this.c.c.push();
        this.c.c.translate(geoBone);
        this.c.c.moveToPivot(geoBone);
        this.c.c.rotate(geoBone);
        this.c.c.scale(geoBone);
        this.c.c.moveBackFromPivot(geoBone);
        if (!geoBone.isHidden()) {
            for (GeoCube object : geoBone.childCubes) {
                this.c.c.push();
                GlStateManager.func_179094_E();
                this.renderCube(bufferBuilder, object, f10, f11, f12, f13);
                GlStateManager.func_179121_F();
                this.c.c.pop();
            }
        }
        if (!geoBone.childBonesAreHiddenToo()) {
            for (GeoBone geoBone2 : geoBone.childBones) {
                this.renderRecursively(bufferBuilder, geoBone2, f10, f11, f12, f13);
            }
        }
        try {
            this.c.c.pop();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
    }

    @Override
    public void renderCube(BufferBuilder bufferBuilder, GeoCube geoCube, float f10, float f11, float f12, float f13) {
        this.c.c.moveToPivot(geoCube);
        this.c.c.rotate(geoCube);
        this.c.c.moveBackFromPivot(geoCube);
        for (GeoQuad geoQuad : geoCube.quads) {
            Vector3f vector3f;
            block28: {
                block27: {
                    block26: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw com.trolmastercard.sexmod.b.a(illegalStateException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.func_177958_n(), (float)geoQuad.normal.func_177956_o(), (float)geoQuad.normal.func_177952_p());
                        try {
                            try {
                                try {
                                    this.c.c.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block26;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block26;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw com.trolmastercard.sexmod.b.a(illegalStateException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw com.trolmastercard.sexmod.b.a(illegalStateException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block27;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw com.trolmastercard.sexmod.b.a(illegalStateException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block27;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw com.trolmastercard.sexmod.b.a(illegalStateException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw com.trolmastercard.sexmod.b.a(illegalStateException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block28;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw com.trolmastercard.sexmod.b.a(illegalStateException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block28;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw com.trolmastercard.sexmod.b.a(illegalStateException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (IllegalStateException illegalStateException) {
                    throw com.trolmastercard.sexmod.b.a(illegalStateException);
                }
            }
            try {
                if (this.j != null) {
                    this.d = gx.a(this.d, vector3f, this.j);
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw com.trolmastercard.sexmod.b.a(illegalStateException);
            }
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                this.c.c.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.func_181662_b((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).func_187315_a((double)geoVertex.textureU, (double)geoVertex.textureV).func_181666_a((float)this.d.field_72450_a, (float)this.d.field_72448_b, (float)this.d.field_72449_c, f13).func_181663_c(vector3f.getX(), vector3f.getY(), vector3f.getZ()).func_181675_d();
            }
        }
    }

    private static IllegalStateException a(IllegalStateException illegalStateException) {
        return illegalStateException;
    }
}

