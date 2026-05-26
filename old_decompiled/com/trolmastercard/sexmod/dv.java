/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.Vec2f
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.dm;
import com.trolmastercard.sexmod.e5;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec2f;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dv
extends dm {
    static final float E = 8.0f;
    static final float K = 1.68f;
    static final float M = 5.0f;
    static Collection<dv> J = new ArrayList<dv>();
    double C = 0.0;
    double z = 0.0;
    double A = 0.0;
    double D = 0.0;
    float F = 0.0f;
    float B = 0.0f;
    float G;
    float I;
    double H = 0.0;
    double L = 0.0;

    public dv(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
        super(renderManager, animatedGeoModel);
        J.add(this);
    }

    @Override
    protected void c() {
        GlStateManager.func_179109_b((float)0.0f, (float)-1.1f, (float)0.0f);
        GlStateManager.func_179152_a((float)0.7f, (float)0.7f, (float)0.7f);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void a(boolean var1_1, ItemStack var2_2) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    protected void a(boolean bl2) {
        block3: {
            block2: {
                try {
                    super.a(bl2);
                    if (!bl2) break block2;
                    GlStateManager.func_179137_b((double)0.15, (double)0.0, (double)0.0);
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw dv.a(runtimeException);
                }
            }
            GlStateManager.func_179137_b((double)-0.05, (double)0.0, (double)0.0);
        }
    }

    @Override
    protected void a(boolean bl2, boolean bl3) {
        block9: {
            block8: {
                try {
                    try {
                        super.a(bl2, bl3);
                        if (!bl2 || bl3) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw dv.a(runtimeException);
                    }
                    GlStateManager.func_179137_b((double)-0.025, (double)-0.1, (double)-0.1);
                    GlStateManager.func_179114_b((float)10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw dv.a(runtimeException);
                }
            }
            try {
                try {
                    if (bl2 || bl3) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw dv.a(runtimeException);
                }
                GlStateManager.func_179137_b((double)-0.05, (double)-0.125, (double)0.125);
                GlStateManager.func_179114_b((float)50.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw dv.a(runtimeException);
            }
        }
    }

    @Override
    protected void a(String string, GeoBone geoBone) {
        try {
            if (((Boolean)this.w.func_184212_Q().func_187225_a(em.G)).booleanValue()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        try {
            if ("tail".equals(string)) {
                this.a(geoBone, 0.0f, 0.0f, 1.0f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        try {
            if ("body".equals(string)) {
                this.a(geoBone);
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        try {
            if (this.w.y() == fp.BOW) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        try {
            if ("armL".equals(string)) {
                this.a(geoBone, 0.0f, -0.34906584f, 0.15f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        try {
            if (this.w.y() == fp.ATTACK) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        try {
            if ("armR".equals(string)) {
                this.a(geoBone, 0.0f, 0.34906584f, 0.15f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
    }

    void a(GeoBone geoBone, float f10, float f11, float f12) {
        double d10 = this.C - this.A;
        double d11 = this.z - this.D;
        double d12 = Math.PI / 180 * (double)this.w.field_70177_z;
        Vec2f vec2f = new Vec2f((float)(d10 * Math.cos(d12) + d11 * Math.sin(d12)), (float)(-d10 * Math.sin(d12) + d11 * Math.cos(d12)));
        this.G = vec2f.field_189983_j * -8.0f;
        this.I = vec2f.field_189982_i * 8.0f;
        this.G = be.b(this.G, -1.68f, 1.68f);
        this.I = be.b(this.I, -1.68f, 1.68f);
        this.G = b6.a(this.F, this.G, this.y);
        this.I = b6.a(this.B, this.I, this.y);
        geoBone.setRotationX(f10 + this.G * f12);
        geoBone.setRotationZ(f11 + this.I * f12);
    }

    void a(GeoBone geoBone) {
        double d10 = this.C - this.A;
        double d11 = this.z - this.D;
        try {
            this.L = (Math.abs(d10) + Math.abs(d11)) * 5.0;
            this.L = be.b((float)this.L, 0.0f, 1.0f);
            geoBone.setPositionY((float)b6.a(5.0, 0.0, b6.b(this.H, this.L, (double)this.y)));
            if (this.w instanceof e5) {
                ((e5)this.w).aq = (float)b6.a((double)0.3f, 0.0, b6.b(this.H, this.L, (double)this.y));
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
    }

    void a() {
        try {
            if (this.w == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        try {
            this.F = this.G;
            this.B = this.I;
            this.H = this.L;
            if (this.w.m() == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.j.field_70170_p.func_152378_a(this.w.m());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw dv.a(runtimeException);
        }
        this.A = this.C;
        this.D = this.z;
        this.C = entityPlayer.field_70165_t;
        this.z = entityPlayer.field_70161_v;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a {
        @SubscribeEvent
        public void a(TickEvent.ClientTickEvent clientTickEvent) {
            for (dv dv2 : J) {
                dv2.a();
            }
        }
    }
}

