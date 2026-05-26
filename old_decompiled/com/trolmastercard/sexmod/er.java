/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a5;
import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.b7;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.ds;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f2;
import com.trolmastercard.sexmod.fp;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class er
extends ei
implements b7 {
    boolean ap = false;
    int ar = 0;
    boolean as = false;
    boolean aq = false;

    public er(World world) {
        super(world);
    }

    public er(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public at a(int n2) {
        return new a5();
    }

    @Override
    public String c(int n2) {
        return "textures/entity/galath/hand.png";
    }

    @Override
    @Nullable
    protected fp c(fp fp2) {
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block7: {
            try {
                try {
                    if (fp2 != fp.CORRUPT_FAST && fp2 != fp.CORRUPT_SLOW) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw er.a(runtimeException);
                }
                return fp.CORRUPT_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw er.a(runtimeException);
            }
        }
        try {
            if (fp2 == fp.RAPE_ON_GOING) {
                return fp.RAPE_CUM;
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
        return null;
    }

    @Override
    public float i() {
        return 2.3f;
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("cowgirl".equals(string)) {
                this.b(uUID);
                this.b(fp.RAPE_INTRO);
                this.a(this.ah(), fp.RAPE_INTRO);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
        try {
            if ("mating press".equals(string)) {
                this.b(uUID);
                this.b(fp.CORRUPT_SLOW);
                this.a(this.ah(), fp.CORRUPT_SLOW);
                this.a();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
    }

    @Override
    public void b(fp fp2) {
        block20: {
            fp fp3;
            block19: {
                block17: {
                    fp3 = this.y();
                    try {
                        block18: {
                            try {
                                try {
                                    if (fp3 != fp.CORRUPT_CUM) break block17;
                                    if (fp2 == fp.CORRUPT_FAST) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw er.a(runtimeException);
                                }
                                if (fp2 != fp.CORRUPT_SLOW) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw er.a(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw er.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (fp3 != fp.RAPE_CUM || fp2 != fp.RAPE_ON_GOING) break block19;
                    }
                    catch (RuntimeException runtimeException) {
                        throw er.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw er.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp3 != fp.RAPE_CUM || fp2 != fp.RAPE_CUM_IDLE) break block20;
                }
                catch (RuntimeException runtimeException) {
                    throw er.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw er.a(runtimeException);
            }
        }
        try {
            if (fp2 == fp.CORRUPT_SLOW) {
                this.as = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
        super.b(fp2);
    }

    void a() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
        Vec3d vec3d = ck.a(new Vec3d(0.5, (double)(0.5f - entityPlayer.func_70047_e()), (double)0.4f), this.I().floatValue()).func_178787_e(this.o());
        entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        er.a(entityPlayer, this, new String[]{"cowgirl", "mating press", "ride"}, false);
        return true;
    }

    @Override
    public boolean v() {
        return false;
    }

    @Override
    public boolean A() {
        return false;
    }

    @Override
    public f2 d() {
        return new f2(0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean c() {
        boolean bl2;
        block5: {
            block4: {
                try {
                    try {
                        if (this.ah() != 0 && !this.ap) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw er.a(runtimeException);
                    }
                    bl2 = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw er.a(runtimeException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    @Override
    public boolean a() {
        try {
            switch (this.y()) {
                case CORRUPT_CUM: 
                case CORRUPT_FAST: 
                case CORRUPT_SLOW: 
                case COWGIRLCUM: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
    }

    @Override
    public void B() {
        this.c(true);
    }

    @Override
    public void func_70071_h_() {
        try {
            super.func_70071_h_();
            this.b();
            if (this.field_70170_p.field_72995_K) {
                this.d();
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void d() {
        try {
            if (!this.n()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
        try {
            if (this.y() != fp.RAPE_INTRO) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
        ds.a(false);
    }

    /*
     * Exception decompiling
     */
    void b() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous, and can't clone.
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:611)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:94)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:517)
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

    boolean g() {
        boolean bl2;
        EntityPlayer entityPlayer = this.k();
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
        }
        try {
            bl2 = this.field_70170_p.func_180495_p(entityPlayer.func_180425_c().func_177984_a().func_177984_a()).func_177230_c() != Blocks.field_150350_a;
        }
        catch (RuntimeException runtimeException) {
            throw er.a(runtimeException);
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
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 16[SWITCH]
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
        this.p();
        this.C.registerSoundListener(var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 21[SWITCH]
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
        });
        animationData.addAnimationController(this.C);
        animationData.addAnimationController(this.s);
        animationData.addAnimationController(this.E);
    }

    private static /* synthetic */ Vec3d lambda$null$3(em em2) {
        return em2.b("creampiePos").func_178787_e(em2.o());
    }

    private /* synthetic */ Vec3d lambda$null$2(em em2) {
        return ck.a(new Vec3d(0.0, 0.0, (double)0.6f), this.I().floatValue());
    }

    private static /* synthetic */ Vec3d lambda$null$1(em em2) {
        return em2.b("futaCockTip").func_178787_e(em2.o());
    }

    private static /* synthetic */ Vec3d lambda$null$0(em em2) {
        Vec3d vec3d = em2.d("futaCockTip");
        Vec3d vec3d2 = em2.d("futaCockTipDirHelp");
        return vec3d.func_178788_d(vec3d2).func_72432_b();
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

