/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.cf;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.g4;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class ee
extends ei {
    boolean ar = false;
    boolean aq = false;
    int ap = 1;

    protected ee(World world) {
        super(world);
    }

    public ee(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float i() {
        return 2.05f;
    }

    public float func_70047_e() {
        float f10;
        try {
            f10 = this.a() ? 1.53f : 1.9f;
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        return f10;
    }

    @Override
    public void u() {
        this.b(fp.SITDOWN);
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("Face fuck".equals(string)) {
                this.b(uUID);
                this.b(fp.CARRY_INTRO);
                this.a(this.ah(), fp.CARRY_INTRO);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
    }

    @Override
    public at a(int n2) {
        return new cf();
    }

    @Override
    public String c(int n2) {
        try {
            if (n2 == 0) {
                return "textures/entity/ellie/hand_nude.png";
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        return "textures/entity/ellie/hand.png";
    }

    @Override
    public boolean p() {
        return true;
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            if ("action.names.cowgirl".equals(string)) {
                this.a("animationFollowUp", "Cowgirl");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        try {
            if ("action.names.missionary".equals(string)) {
                this.a("animationFollowUp", "Missionary");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        try {
            if (!((Optional)this.m.func_187225_a(ai)).isPresent()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        ge.b.sendToServer((IMessage)new g4(string, uUID, (UUID)((Optional)this.m.func_187225_a(ai)).get(), this.ab));
        this.ab = true;
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        ee.a(entityPlayer, this, new String[]{"Face fuck"}, false);
        return true;
    }

    void c(EntityPlayer entityPlayer) {
        ee.a(entityPlayer, this, new String[]{"action.names.cowgirl", "action.names.missionary"}, false);
    }

    @Override
    public boolean A() {
        return false;
    }

    @Override
    public void b(fp fp2) {
        block14: {
            fp fp3;
            block12: {
                fp3 = this.y();
                try {
                    block13: {
                        try {
                            try {
                                if (fp3 != fp.MISSIONARY_CUM) break block12;
                                if (fp2 == fp.MISSIONARY_FAST) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ee.a(runtimeException);
                            }
                            if (fp2 != fp.MISSIONARY_SLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ee.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ee.a(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (fp3 != fp.COWGIRLCUM) break block14;
                            if (fp2 == fp.COWGIRLSLOW) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ee.a(runtimeException);
                        }
                        if (fp2 != fp.COWGIRLFAST) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ee.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ee.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.COWGIRLSLOW) {
                return fp.COWGIRLFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        try {
            if (fp2 == fp.MISSIONARY_SLOW) {
                return fp.MISSIONARY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        try {
            if (fp2 == fp.CARRY_SLOW) {
                return fp.CARRY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (fp2 != fp.COWGIRLFAST && fp2 != fp.COWGIRLSLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ee.a(runtimeException);
                        }
                        return fp.COWGIRLCUM;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ee.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (fp2 != fp.MISSIONARY_FAST && fp2 != fp.MISSIONARY_SLOW) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ee.a(runtimeException);
                    }
                    return fp.MISSIONARY_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw ee.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.CARRY_SLOW && fp2 != fp.CARRY_FAST) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw ee.a(runtimeException);
                }
                return fp.CARRY_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw ee.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void func_70619_bc() {
        super.func_70619_bc();
        if (this.y() == fp.SITDOWNIDLE) {
            EntityPlayer entityPlayer;
            String string;
            block12: {
                block11: {
                    string = (String)this.m.func_187225_a(em.h);
                    try {
                        try {
                            if ("Missionary".equals(string) || "Cowgirl".equals(string)) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ee.a(runtimeException);
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ee.a(runtimeException);
                    }
                }
                entityPlayer = this.j();
                try {
                    try {
                        if (entityPlayer != null && !(entityPlayer.func_70011_f(this.w().field_72450_a, this.w().field_72448_b, this.w().field_72449_c) > 1.0)) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ee.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ee.a(runtimeException);
                }
            }
            this.m.func_187227_b(em.h, (Object)"");
            this.m.func_187227_b(em.D, (Object)0);
            this.e(entityPlayer.getPersistentID());
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.field_70170_p.func_152378_a((UUID)((Optional)this.m.func_187225_a(ai)).get());
            ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
            ge.b.sendTo((IMessage)new gz(false), entityPlayerMP);
            entityPlayer.func_191958_b(0.0f, 0.0f, 0.0f, 0.0f);
            entityPlayerMP.field_71075_bZ.field_75100_b = true;
            entityPlayer.field_71075_bZ.field_75100_b = true;
            entityPlayerMP.field_70145_X = true;
            entityPlayer.field_70145_X = true;
            entityPlayerMP.func_189654_d(true);
            entityPlayer.func_189654_d(true);
            if ("Missionary".equals(string)) {
                this.b(fp.MISSIONARY_START);
                Vec3d vec3d = this.w().func_178786_a(0.0, 0.1, 0.0);
                entityPlayer.func_70080_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, this.I().floatValue(), 60.0f);
                entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
            } else {
                this.b(fp.COWGIRLSTART);
                Vec3d vec3d = this.w().func_178787_e(new Vec3d(-Math.sin((double)this.I().floatValue() * (Math.PI / 180)) * 1.8, -0.65, Math.cos((double)this.I().floatValue() * (Math.PI / 180)) * 1.8));
                entityPlayer.func_70080_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 180.0f + this.I().floatValue(), -30.0f);
                entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
            }
        }
    }

    boolean a() {
        boolean bl2;
        EntityPlayer entityPlayer = this.k();
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        try {
            bl2 = this.field_70170_p.func_180495_p(entityPlayer.func_180425_c().func_177984_a().func_177984_a()).func_177230_c() != Blocks.field_150350_a;
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        return bl2;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 20[SWITCH]
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
    @SideOnly(value=Side.CLIENT)
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.C == null) {
                this.p();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ee.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 37[SWITCH]
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        };
        this.C.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.C);
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

