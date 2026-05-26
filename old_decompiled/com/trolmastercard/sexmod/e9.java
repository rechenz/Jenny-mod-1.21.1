/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a7;
import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.fp;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class e9
extends ei {
    protected e9(World world) {
        super(world);
    }

    public e9(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public void B() {
        this.c(true);
    }

    @Override
    public void y() {
        this.c(false);
    }

    @Override
    public float i() {
        return 1.4f;
    }

    public float func_70047_e() {
        return 1.3f;
    }

    @Override
    public at a(int n2) {
        return new a7();
    }

    @Override
    public String c(int n2) {
        return "textures/entity/bee/hand.png";
    }

    @Override
    public void b(String string, UUID uUID) {
        this.a(0, fp.CITIZEN_START);
        this.f(0);
        this.b(fp.CITIZEN_START);
        this.b(uUID);
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw e9.a(runtimeException);
        }
        Vec3d vec3d = this.a(-0.2);
        entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        e9.a(entityPlayer, this, new String[]{"action.names.sex"}, false);
        return true;
    }

    @Override
    public void b(fp fp2) {
        block6: {
            try {
                block7: {
                    try {
                        try {
                            if (this.y() != fp.CITIZEN_CUM) break block6;
                            if (fp2 == fp.CITIZEN_FAST) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw e9.a(runtimeException);
                        }
                        if (fp2 != fp.COWGIRLSLOW) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw e9.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw e9.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
    }

    @Override
    public boolean v() {
        return false;
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.CITIZEN_SLOW) {
                return fp.CITIZEN_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw e9.a(runtimeException);
        }
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block4: {
            try {
                try {
                    if (fp2 != fp.CITIZEN_FAST && fp2 != fp.CITIZEN_SLOW) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw e9.a(runtimeException);
                }
                return fp.CITIZEN_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw e9.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void g() {
        super.g();
        this.f(1);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[SWITCH]
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
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.C == null) {
                this.p();
            }
        }
        catch (RuntimeException runtimeException) {
            throw e9.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 15[SWITCH]
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
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

