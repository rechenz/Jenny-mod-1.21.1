/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ai;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.cv;
import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.gc;
import com.trolmastercard.sexmod.gj;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ci
extends cv {
    final float g = 60.0f;
    Minecraft f = Minecraft.func_71410_x();

    @Override
    protected ResourceLocation[] a() {
        return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/goblin/goblin.geo.json"), new ResourceLocation("sexmod", "geo/goblin/armored.geo.json")};
    }

    @Override
    public ResourceLocation b() {
        return new ResourceLocation("sexmod", "textures/entity/goblin/goblin.png");
    }

    @Override
    public ResourceLocation b(em em2) {
        return new ResourceLocation("sexmod", "animations/goblin/goblin.animation.json");
    }

    protected boolean f(em em2) {
        try {
            if (!(em2 instanceof e3)) {
                return super.f(em2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        e3 e32 = (e3)em2;
        UUID uUID = e32.ae();
        if (uUID == null) {
            uUID = e32.e();
        }
        try {
            if (uUID == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        World world = e32.field_70170_p;
        AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)world.func_152378_a(uUID);
        try {
            if (abstractClientPlayer == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        return "default".equals(abstractClientPlayer.func_175154_l());
    }

    public void a(em em2, Integer n2, AnimationEvent animationEvent) {
        boolean bl2;
        AnimationProcessor animationProcessor;
        block48: {
            ai ai2;
            fp fp2;
            block47: {
                IBone iBone;
                block46: {
                    block45: {
                        block43: {
                            block44: {
                                block42: {
                                    IBone iBone2;
                                    block41: {
                                        block39: {
                                            boolean bl3;
                                            IBone iBone3;
                                            try {
                                                super.a(em2, n2, animationEvent);
                                                if (em2.field_70170_p instanceof gj) {
                                                    return;
                                                }
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw ci.a(runtimeException);
                                            }
                                            animationProcessor = this.getAnimationProcessor();
                                            bl2 = em2 instanceof e3;
                                            IBone iBone4 = animationProcessor.getBone("preggy");
                                            try {
                                                iBone3 = iBone4;
                                                bl3 = (Boolean)em2.func_184212_Q().func_187225_a(e3.aV) == false;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw ci.a(runtimeException);
                                            }
                                            iBone3.setHidden(bl3);
                                            iBone = animationProcessor.getBone("body");
                                            iBone2 = animationProcessor.getBone("head");
                                            fp2 = em2.y();
                                            try {
                                                try {
                                                    block38: {
                                                        try {
                                                            try {
                                                                if (fp2 == fp.BREEDING_SLOW_2 || fp2 == fp.BREEDING_FAST_2) break block38;
                                                            }
                                                            catch (RuntimeException runtimeException) {
                                                                throw ci.a(runtimeException);
                                                            }
                                                            if (fp2 != fp.BREEDING_CUM_2) break block39;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw ci.a(runtimeException);
                                                        }
                                                    }
                                                    if (this.f.field_71474_y.field_74320_O != 0) break block39;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw ci.a(runtimeException);
                                                }
                                                iBone.setPositionY(iBone.getPositionY() + 1.5f);
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw ci.a(runtimeException);
                                            }
                                        }
                                        ai2 = (ai)((Object)em2);
                                        try {
                                            block40: {
                                                try {
                                                    try {
                                                        if (bl2 && fp2 == fp.AWAIT_PICK_UP) break block40;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw ci.a(runtimeException);
                                                    }
                                                    if (fp2 != fp.VANISH) break block41;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw ci.a(runtimeException);
                                                }
                                            }
                                            this.a(em2, iBone, iBone2);
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw ci.a(runtimeException);
                                        }
                                    }
                                    try {
                                        try {
                                            if (!bl2 || fp2 != fp.SIT) break block42;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw ci.a(runtimeException);
                                        }
                                        this.a(em2, iBone2);
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ci.a(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (fp2 != fp.START_THROWING) break block43;
                                        if (!this.f.field_71439_g.getPersistentID().equals(ai2.e())) break block44;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ci.a(runtimeException);
                                    }
                                    this.a(iBone, animationProcessor, em2, ai2);
                                    break block45;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ci.a(runtimeException);
                                }
                            }
                            this.a(iBone, animationProcessor, em2);
                            break block45;
                        }
                        iBone.setHidden(false);
                    }
                    try {
                        try {
                            if (!iBone.isHidden() && fp2 == fp.START_THROWING) break block46;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ci.a(runtimeException);
                        }
                        if (fp2 != fp.THROWN) break block47;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ci.a(runtimeException);
                    }
                }
                Vec3d vec3d = ci.d(em2);
                iBone.setRotationX((float)vec3d.field_72450_a);
                iBone.setPositionY((float)vec3d.field_72448_b);
                iBone.setPositionZ((float)vec3d.field_72449_c);
            }
            try {
                try {
                    if (fp2 != fp.START_THROWING && fp2 != fp.PICK_UP) break block48;
                }
                catch (RuntimeException runtimeException) {
                    throw ci.a(runtimeException);
                }
                this.a(animationProcessor, ai2, em2);
            }
            catch (RuntimeException runtimeException) {
                throw ci.a(runtimeException);
            }
        }
        try {
            if (!bl2) {
                this.b(animationProcessor, em2);
                this.a(animationProcessor, em2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
    }

    void a(AnimationProcessor animationProcessor, em em2) {
        block10: {
            try {
                if (em2.y() != fp.START_THROWING) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ci.a(runtimeException);
            }
            try {
                try {
                    if (this.f.field_71474_y.field_74320_O == 0 && this.f.field_71439_g.getPersistentID().equals(((ei)em2).m())) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw ci.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ci.a(runtimeException);
            }
        }
        IBone iBone = animationProcessor.getBone("body");
        try {
            if (iBone == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        iBone.setHidden(true);
    }

    void b(AnimationProcessor animationProcessor, em em2) {
        block13: {
            try {
                if (em2.y() != fp.PICK_UP) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ci.a(runtimeException);
            }
            try {
                try {
                    if (this.f.field_71474_y.field_74320_O != 0 || !this.f.field_71439_g.getPersistentID().equals(((ai)((Object)em2)).e())) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw ci.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ci.a(runtimeException);
            }
        }
        IBone iBone = animationProcessor.getBone("body");
        try {
            if (iBone == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        IBone iBone2 = animationProcessor.getBone("steve");
        try {
            if (iBone2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        iBone.setPositionY(iBone.getPositionY() - 32.0f);
        iBone2.setPositionY(iBone2.getPositionY() - 32.0f);
    }

    void a(AnimationProcessor animationProcessor, ai ai2, em em2) {
        UUID uUID = ai2.e();
        try {
            if (uUID == null) {
                em2.ae();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        EntityPlayer entityPlayer = em2.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        float f10 = b6.a(entityPlayer.field_184618_aE, entityPlayer.field_70721_aZ, this.f.func_184121_ak());
        float f11 = entityPlayer.field_184619_aG;
        float f12 = (float)Math.sin(f11);
        IBone iBone = animationProcessor.getBone("LeftLeg");
        IBone iBone2 = animationProcessor.getBone("RightLeg");
        float f13 = gc.c(60.0f * f12 * f10);
        iBone.setRotationX(f13);
        iBone2.setRotationX(-f13);
    }

    /*
     * Exception decompiling
     */
    void a(em var1_1, IBone var2_2) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 7[SWITCH]
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

    void a(em em2, IBone iBone, IBone iBone2) {
        EntityPlayer entityPlayer = em2.field_70170_p.func_72890_a((Entity)em2, 15.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
        Vec3d vec3d = entityPlayer.func_174791_d();
        Vec3d vec3d2 = em2.func_174791_d();
        Vec3d vec3d3 = vec3d.func_178788_d(vec3d2);
        float f10 = (float)(-(Math.atan2(vec3d3.field_72449_c, vec3d3.field_72450_a) * 57.29577951308232)) + 90.0f;
        float f11 = be.b((float)((double)entityPlayer.func_70047_e() + vec3d.field_72448_b - ((double)em2.func_70047_e() + vec3d2.field_72448_b)), -0.75f, 0.75f);
        iBone.setRotationY(gc.c(f10));
        iBone2.setRotationX(f11);
    }

    void a(IBone iBone, AnimationProcessor animationProcessor, em em2) {
        block3: {
            block2: {
                try {
                    if (!em2.h()) break block2;
                    iBone.setHidden(true);
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw ci.a(runtimeException);
                }
            }
            iBone.setHidden(false);
            animationProcessor.getBone("steve").setHidden(true);
        }
    }

    void a(IBone iBone, AnimationProcessor animationProcessor, em em2, ai ai2) {
        block8: {
            boolean bl2;
            IBone iBone2;
            block7: {
                try {
                    if (!em2.h()) break block7;
                    iBone.setHidden(true);
                    break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw ci.a(runtimeException);
                }
            }
            try {
                iBone2 = iBone;
                bl2 = ai2.a() < 15;
            }
            catch (RuntimeException runtimeException) {
                throw ci.a(runtimeException);
            }
            iBone2.setHidden(bl2);
        }
        try {
            if (!em2.h()) {
                animationProcessor.getBone("steve").setHidden(true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ci.a(runtimeException);
        }
    }

    @Override
    public String[] c() {
        return new String[]{"armorHelmet"};
    }

    @Override
    public String[] f() {
        return new String[]{"armorBoobL", "armorBoobR"};
    }

    @Override
    public String[] a() {
        return new String[]{"nippleL", "nippleR"};
    }

    @Override
    public String[] h() {
        return new String[]{"armorCheekR", "armorCheekL", "armorLegL", "armorLegR", "armorShinL", "armorShinR", "armorTorso"};
    }

    @Override
    public String[] e() {
        return new String[]{"fuckhole", "vagina", "meatCheekR", "meatCheekL", "meatLegL", "meatLegR", "meatShinL", "meatShinR"};
    }

    @Override
    public String[] b() {
        return new String[]{"armorFootL", "armorFootR"};
    }

    @Override
    public String[] d() {
        return new String[]{"meatFootL", "meatFootR"};
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

