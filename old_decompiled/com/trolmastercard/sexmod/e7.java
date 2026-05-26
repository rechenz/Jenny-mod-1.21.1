/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.de;
import com.trolmastercard.sexmod.dj;
import com.trolmastercard.sexmod.dr;
import com.trolmastercard.sexmod.e4;
import com.trolmastercard.sexmod.ew;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.fv;
import com.trolmastercard.sexmod.m;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.util.MatrixStack;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class e7
extends ew
implements dr {
    public static final EyeAndKoboldColor aw = EyeAndKoboldColor.PURPLE;
    public static final DataParameter<Float> aA = EntityDataManager.func_187226_a(e7.class, (DataSerializer)DataSerializers.field_187193_c).func_187156_b().func_187161_a(122);
    boolean aB = false;
    boolean az = true;
    boolean ay = false;
    int ax = 0;

    protected e7(World world) {
        super(world);
    }

    public e7(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.values()[this.func_70681_au().nextInt(EyeAndKoboldColor.values().length)];
        this.m.func_187214_a(au, (Object)new BlockPos(eyeAndKoboldColor.getMainColor()));
        this.m.func_187214_a(as, (Object)aw.name());
        this.m.func_187214_a(aA, (Object)Float.valueOf(0.0f));
    }

    @Override
    public AxisAlignedBB a(EntityPlayer entityPlayer) {
        float f10 = 0.6f;
        float f11 = 0.9f;
        float f12 = f10 / 2.0f;
        return new AxisAlignedBB(entityPlayer.field_70165_t - (double)f12, entityPlayer.field_70163_u, entityPlayer.field_70161_v - (double)f12, entityPlayer.field_70165_t + (double)f12, entityPlayer.field_70163_u + (double)f11, entityPlayer.field_70161_v + (double)f12);
    }

    /*
     * Exception decompiling
     */
    @Override
    public void a(List<Integer> var1_1) {
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
    public ArrayList<Integer> L() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(Math.round(((Float)this.m.func_187225_a(aA)).floatValue() * 100.0f / 0.25f));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((String)this.m.func_187225_a(as))));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((Vec3i)this.m.func_187225_a(au))));
        return arrayList;
    }

    @Override
    protected String a(StringBuilder stringBuilder) {
        e4.b(stringBuilder, 8);
        e4.b(stringBuilder, 3);
        e4.b(stringBuilder);
        e4.b(stringBuilder);
        e4.a(stringBuilder, 2);
        e4.a(stringBuilder, 2);
        e4.a(stringBuilder, 1);
        e4.a(stringBuilder, 1);
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> D() {
        return new ArrayList<Integer>(){
            {
                this.add(101);
                this.add(EyeAndKoboldColor.values().length);
                this.add(EyeAndKoboldColor.values().length);
                this.add(8);
                this.add(3);
                this.add(101);
                this.add(101);
                this.add(3);
                this.add(3);
                this.add(4);
                this.add(2);
            }
        };
    }

    @Override
    protected void a() {
        de.e();
        dj.c();
    }

    @Override
    public float i() {
        float f10 = 0.25f - ((Float)this.m.func_187225_a(aA)).floatValue();
        return 1.4f - f10;
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("anal".equals(string)) {
                this.b(uUID);
                this.b(fp.KOBOLD_ANAL_START);
                this.a(this.ah(), fp.KOBOLD_ANAL_START);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw e7.a(runtimeException);
        }
        try {
            if ("oral".equals(string)) {
                this.b(uUID);
                this.b(fp.STARTBLOWJOB);
                this.a(this.ah(), fp.STARTBLOWJOB);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw e7.a(runtimeException);
        }
        try {
            if ("mating".equals(string)) {
                this.b(uUID);
                this.b(fp.MATING_PRESS_START);
                this.a(this.ah(), fp.MATING_PRESS_START);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw e7.a(runtimeException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean b(EntityPlayer entityPlayer) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(this, entityPlayer, new String[]{"anal", "oral", "mating"}, null, false));
        return true;
    }

    @Override
    public boolean a() {
        boolean bl2;
        Block block = this.field_70170_p.func_180495_p(this.func_180425_c().func_177982_a(0, 1, 0)).func_177230_c();
        try {
            bl2 = !block.func_176205_b((IBlockAccess)this.field_70170_p, this.func_180425_c().func_177982_a(0, 1, 0));
        }
        catch (RuntimeException runtimeException) {
            throw e7.a(runtimeException);
        }
        return bl2;
    }

    @Override
    protected MatrixStack a(MatrixStack matrixStack) {
        float f10 = 0.25f - ((Float)this.m.func_187225_a(aA)).floatValue();
        matrixStack.scale(1.0f - f10, 1.0f - f10, 1.0f - f10);
        return matrixStack;
    }

    @Override
    protected float a(float f10) {
        float f11 = 1.0f - (0.25f - ((Float)this.m.func_187225_a(aA)).floatValue());
        return f10 * f11;
    }

    @Override
    public at a(int n2) {
        return new fv();
    }

    @Override
    public String c(int n2) {
        return "textures/entity/kobold/hand.png";
    }

    @Override
    public Vec3i b(int n2) {
        try {
            return EyeAndKoboldColor.valueOf((String)this.m.func_187225_a(as)).getMainColor();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return super.b(n2);
        }
    }

    @Override
    @Nullable
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.SUCKBLOWJOB_BLINK) {
                return fp.THRUSTBLOWJOB;
            }
        }
        catch (RuntimeException runtimeException) {
            throw e7.a(runtimeException);
        }
        try {
            if (fp2 == fp.KOBOLD_ANAL_SLOW) {
                return fp.KOBOLD_ANAL_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw e7.a(runtimeException);
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
                            if (fp2 != fp.THRUSTBLOWJOB && fp2 != fp.SUCKBLOWJOB_BLINK) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw e7.a(runtimeException);
                        }
                        return fp.CUMBLOWJOB;
                    }
                    catch (RuntimeException runtimeException) {
                        throw e7.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (fp2 != fp.KOBOLD_ANAL_SLOW && fp2 != fp.KOBOLD_ANAL_FAST) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw e7.a(runtimeException);
                    }
                    return fp.KOBOLD_ANAL_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw e7.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.MATING_PRESS_HARD && fp2 != fp.MATING_PRESS_SOFT) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw e7.a(runtimeException);
                }
                return fp.MATING_PRESS_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw e7.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void b(fp fp2) {
        block22: {
            fp fp3;
            block20: {
                block18: {
                    fp3 = this.y();
                    try {
                        block19: {
                            try {
                                try {
                                    if (fp3 != fp.MATING_PRESS_CUM) break block18;
                                    if (fp2 == fp.MATING_PRESS_SOFT) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw e7.a(runtimeException);
                                }
                                if (fp2 != fp.MATING_PRESS_HARD) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw e7.a(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw e7.a(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                if (fp3 != fp.KOBOLD_ANAL_CUM) break block20;
                                if (fp2 == fp.KOBOLD_ANAL_SLOW) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw e7.a(runtimeException);
                            }
                            if (fp2 != fp.KOBOLD_ANAL_FAST) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw e7.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw e7.a(runtimeException);
                }
            }
            try {
                block23: {
                    try {
                        try {
                            if (fp3 != fp.CUMBLOWJOB) break block22;
                            if (fp2 == fp.SUCKBLOWJOB) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw e7.a(runtimeException);
                        }
                        if (fp2 != fp.THRUSTBLOWJOB) break block22;
                    }
                    catch (RuntimeException runtimeException) {
                        throw e7.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw e7.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 19[SWITCH]
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

    void b(SoundEvent soundEvent) {
        this.b(soundEvent, 1.0f);
    }

    void b(SoundEvent[] soundEventArray) {
        this.b(soundEventArray, 1.0f);
    }

    void b(SoundEvent[] soundEventArray, float f10) {
        this.b(soundEventArray[this.func_70681_au().nextInt(soundEventArray.length)], f10);
    }

    void b(SoundEvent soundEvent, float f10) {
        float f11 = 0.25f - ((Float)this.m.func_187225_a(aA)).floatValue();
        double d10 = f11 / 0.25f;
        float f12 = (float)b6.b((double)0.9f, (double)1.1f, d10);
        this.a(soundEvent, f10, f12);
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
            throw e7.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 34[SWITCH]
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
        this.E.transitionLengthTicks = 3.0;
        this.C.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.C);
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

