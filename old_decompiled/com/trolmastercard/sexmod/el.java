/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.bh;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.co;
import com.trolmastercard.sexmod.ds;
import com.trolmastercard.sexmod.dz;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.fh;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class el
extends e2
implements bh {
    static final float ad = 10.0f;
    static final int ao = 16;
    static final int ap = 79;
    static final int ag = 109;
    static final int as = 150;
    static final int ar = 20;
    static final int ab = 110;
    static final int an = 4;
    int ak = -1;
    boolean aq = false;
    boolean ae = false;
    boolean ac = false;
    int af = -1;
    int Y = -1;
    int al = -1;
    int ai = -1;
    boolean ah = false;
    Object[] am;
    int Z = -1;
    int aa = 1;
    boolean aj = false;

    public el(World world) {
        super(world);
        this.P = -85;
        this.O = -175;
        this.K = -85;
        this.V = new Vec3d(-0.1, 0.05, 0.0);
    }

    @Override
    public void c() {
        this.a("Okay, I will be residing here then..");
        this.a(com.trolmastercard.sexmod.c.GIRLS_ELLIE_HUH[0], 6.0f);
    }

    @Override
    public String c() {
        return "Ellie";
    }

    @Override
    protected ResourceLocation func_184647_J() {
        return dz.a;
    }

    boolean i() {
        boolean bl2;
        try {
            if (this.h()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            bl2 = this.field_70170_p.func_180495_p(this.func_180425_c().func_177982_a(0, 2, 0)).func_177230_c() != Blocks.field_150350_a;
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        return bl2;
    }

    public float func_70047_e() {
        float f10;
        try {
            f10 = this.i() ? 1.53f : 1.9f;
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        return f10;
    }

    @Override
    public float i() {
        return 0.4f;
    }

    @Override
    public void b() {
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                this.f();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                this.f();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        float f10 = entityPlayer.field_70177_z - 180.0f;
        this.b(f10);
        this.b(fp.CARRY_INTRO);
        this.a(true);
    }

    @Override
    public boolean t() {
        try {
            if (this.y() == fp.CARRY_INTRO) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        return true;
    }

    public boolean a(EntityPlayer entityPlayer, boolean bl2) {
        try {
            if (bl2) {
                el.a(entityPlayer, this, new String[]{"action.names.cowgirl", "action.names.missionary"}, false);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if ((Integer)this.m.func_187225_a(D) == 0) {
                el.a(entityPlayer, this, new String[]{"action.names.dressup"}, true);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        el.a(entityPlayer, this, new String[]{"Face fuck"}, true);
        return true;
    }

    @Override
    public void x() {
        super.x();
        this.a("stay safe darling~");
        this.a(com.trolmastercard.sexmod.c.GIRLS_ELLIE_SIGH[1], 6.0f);
    }

    /*
     * Exception decompiling
     */
    @Override
    public void a(String var1_1, UUID var2_2) {
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
    protected void a(EntityPlayerMP entityPlayerMP, boolean bl2) {
    }

    @Override
    public void b(fp fp2) {
        block30: {
            fp fp3;
            block28: {
                block26: {
                    block25: {
                        fp3 = this.y();
                        try {
                            try {
                                if (fp2 != fp.HUGSELECTED || this.field_70170_p.field_72995_K) break block25;
                            }
                            catch (RuntimeException runtimeException) {
                                throw el.a(runtimeException);
                            }
                            this.ai = 79;
                        }
                        catch (RuntimeException runtimeException) {
                            throw el.a(runtimeException);
                        }
                    }
                    try {
                        block27: {
                            try {
                                try {
                                    if (fp3 != fp.MISSIONARY_CUM) break block26;
                                    if (fp2 == fp.MISSIONARY_FAST) break block27;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw el.a(runtimeException);
                                }
                                if (fp2 != fp.MISSIONARY_SLOW) break block26;
                            }
                            catch (RuntimeException runtimeException) {
                                throw el.a(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw el.a(runtimeException);
                    }
                }
                try {
                    block29: {
                        try {
                            try {
                                if (fp3 != fp.COWGIRLCUM) break block28;
                                if (fp2 == fp.COWGIRLSLOW) break block29;
                            }
                            catch (RuntimeException runtimeException) {
                                throw el.a(runtimeException);
                            }
                            if (fp2 != fp.COWGIRLFAST) break block28;
                        }
                        catch (RuntimeException runtimeException) {
                            throw el.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw el.a(runtimeException);
                }
            }
            try {
                block31: {
                    try {
                        try {
                            if (fp3 != fp.CARRY_CUM) break block30;
                            if (fp2 == fp.CARRY_SLOW) break block31;
                        }
                        catch (RuntimeException runtimeException) {
                            throw el.a(runtimeException);
                        }
                        if (fp2 != fp.CARRY_FAST) break block30;
                    }
                    catch (RuntimeException runtimeException) {
                        throw el.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw el.a(runtimeException);
            }
        }
        try {
            if (fp2 == fp.CARRY_INTRO) {
                this.ak = 0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        super.b(fp2);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70071_h_() {
        try {
            super.func_70071_h_();
            if (this.ae) {
                this.a((EntityPlayer)Minecraft.func_71410_x().field_71439_g, true);
                this.ae = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        this.m();
        this.h();
    }

    void h() {
        try {
            if (ds.a()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (this.y() != fp.CARRY_SLOW) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        ds.d();
    }

    void e() {
        try {
            if (this.ak == -1) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (++this.ak < 110) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            this.ak = -1;
            if (this.y() != fp.CARRY_INTRO) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        float f10 = this.I().floatValue();
        Vec3d vec3d = this.o().func_178787_e(ck.a(new Vec3d(0.0, (double)(2.5625f - entityPlayer.func_70047_e()), -0.3125), 180.0f + f10));
        entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
    }

    void m() {
        try {
            if (this.y() != fp.SITDOWNIDLE) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 10.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (this.func_70032_d((Entity)entityPlayer) > 1.5f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (entityPlayer.getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID())) {
                fh.b();
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
    }

    @Override
    public void func_70619_bc() {
        super.func_70619_bc();
        this.o();
        this.d();
        this.n();
        this.q();
        this.j();
        this.a();
        this.t();
        this.u();
    }

    void o() {
        try {
            if (this.ac) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        this.ac = true;
        this.field_70145_X = false;
        this.func_189654_d(false);
    }

    @Override
    protected void U() {
        Vec3d vec3d;
        Vec3d vec3d2;
        EntityPlayer entityPlayer;
        UUID uUID;
        String string = (String)this.m.func_187225_a(h);
        if ("Missionary".equals(string)) {
            this.m.func_187227_b(D, (Object)0);
            this.b(fp.MISSIONARY_START);
            uUID = this.ae();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw el.a(runtimeException);
            }
            entityPlayer = this.field_70170_p.func_152378_a(uUID);
            try {
                if (entityPlayer == null) {
                    this.r();
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw el.a(runtimeException);
            }
            entityPlayer.func_189654_d(true);
            entityPlayer.field_70145_X = true;
            vec3d2 = this.o();
            entityPlayer.field_70177_z = this.I().floatValue();
            vec3d = ck.a(new Vec3d(0.0, 0.0, 0.1), entityPlayer.field_70177_z);
            vec3d2 = vec3d2.func_178787_e(vec3d);
            entityPlayer.func_70634_a(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c);
            ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
        }
        if ("cowgirl".equals(string)) {
            this.m.func_187227_b(D, (Object)0);
            this.b(fp.COWGIRLSTART);
            uUID = this.ae();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw el.a(runtimeException);
            }
            entityPlayer = this.field_70170_p.func_152378_a(uUID);
            try {
                if (entityPlayer == null) {
                    this.r();
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw el.a(runtimeException);
            }
            entityPlayer.func_189654_d(true);
            entityPlayer.field_70145_X = true;
            vec3d2 = this.o();
            entityPlayer.field_70177_z = this.I().floatValue() + 180.0f;
            vec3d = ck.a(new Vec3d(0.0, 1.0 - (double)entityPlayer.eyeHeight, -1.8125), entityPlayer.field_70177_z);
            vec3d2 = vec3d2.func_178787_e(vec3d);
            entityPlayer.func_70634_a(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c);
            ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
        }
    }

    void u() {
        try {
            if (--this.af != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        this.U();
    }

    void t() {
        block10: {
            try {
                try {
                    if (this.y() == fp.SITDOWNIDLE && this.af < 0) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw el.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw el.a(runtimeException);
            }
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 10.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (this.func_70032_d((Entity)entityPlayer) > 1.5f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        this.af = 20;
        this.e(entityPlayer.getPersistentID());
    }

    void a() {
        try {
            if (--this.Y != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        this.b(fp.HUGIDLE);
    }

    void j() {
        try {
            if (--this.al != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        this.b(fp.SITDOWNIDLE);
    }

    void q() {
        block16: {
            try {
                try {
                    if (--this.ai == 0 || this.ah) break block16;
                }
                catch (RuntimeException runtimeException) {
                    throw el.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw el.a(runtimeException);
            }
        }
        try {
            this.ah = true;
            this.m.func_187227_b(G, (Object)false);
            this.b(fp.NULL);
            this.field_70145_X = false;
            this.func_189654_d(false);
            if (this.am == null) {
                this.am = this.g();
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (this.am == null) {
                this.h("no bed in sight...");
                this.field_70170_p.func_184133_a(null, this.func_180425_c(), com.trolmastercard.sexmod.c.GIRLS_ELLIE_SIGH[0], SoundCategory.NEUTRAL, 6.0f, 1.0f);
                this.s();
                this.f();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        try {
            if (entityPlayer != null) {
                entityPlayer.func_189654_d(false);
                entityPlayer.field_70145_X = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        Vec3d vec3d = (Vec3d)this.am[0];
        int n2 = (Integer)this.am[1];
        try {
            if (vec3d.func_72438_d(this.func_174791_d()) > 1.0) {
                this.func_70661_as().func_75492_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, (double)0.35f);
                this.k();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        this.c(vec3d);
        this.b(n2);
        this.b(fp.SITDOWN);
        this.m.func_187227_b(G, (Object)true);
        this.al = 109;
        this.field_70145_X = true;
        this.func_189654_d(true);
        this.ah = false;
        this.am = null;
    }

    @Override
    public void g() {
        super.g();
        this.Y = -1;
    }

    Object[] g() {
        Vec3d vec3d;
        BlockPos blockPos;
        int n2 = -1;
        int n3 = 0;
        Vec3d[][] vec3dArrayArray = new Vec3d[][]{{new Vec3d(0.5, 0.0, -0.18), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0)}, {new Vec3d(0.5, 0.0, 1.18), new Vec3d(0.0, 0.0, 1.0), new Vec3d(0.0, 0.0, -1.0)}, {new Vec3d(-0.18, 0.0, 0.5), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0)}, {new Vec3d(1.18, 0.0, 0.5), new Vec3d(1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 0.0)}};
        int[] nArray = new int[]{0, 180, -90, 90};
        do {
            if ((blockPos = this.a(this.func_180425_c(), ++n3)) == null) {
                return null;
            }
            vec3d = new Vec3d((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p());
            for (int i2 = 0; i2 < vec3dArrayArray.length; ++i2) {
                block7: {
                    Vec3d vec3d2 = vec3d.func_178787_e(vec3dArrayArray[i2][1]);
                    Block block = this.field_70170_p.func_180495_p(new BlockPos(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c)).func_177230_c();
                    Vec3d vec3d3 = vec3d.func_178787_e(vec3dArrayArray[i2][2]);
                    Block block2 = this.field_70170_p.func_180495_p(new BlockPos(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c)).func_177230_c();
                    try {
                        try {
                            if (block != Blocks.field_150350_a || block2 != Blocks.field_150324_C) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw el.a(runtimeException);
                        }
                        if (n2 != -1) break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw el.a(runtimeException);
                    }
                    n2 = i2;
                    continue;
                }
                double d10 = this.func_180425_c().func_177954_c(vec3d.func_178787_e((Vec3d)vec3dArrayArray[n2][0]).field_72450_a, vec3d.func_178787_e((Vec3d)vec3dArrayArray[n2][0]).field_72448_b, vec3d.func_178787_e((Vec3d)vec3dArrayArray[n2][0]).field_72449_c);
                double d11 = this.func_180425_c().func_177954_c(vec3d.func_178787_e((Vec3d)vec3dArrayArray[i2][0]).field_72450_a, vec3d.func_178787_e((Vec3d)vec3dArrayArray[i2][0]).field_72448_b, vec3d.func_178787_e((Vec3d)vec3dArrayArray[i2][0]).field_72449_c);
                if (!(d11 < d10)) continue;
                n2 = i2;
            }
        } while (n2 == -1);
        blockPos = vec3d.func_178787_e(vec3dArrayArray[n2][0]);
        return new Object[]{blockPos, nArray[n2]};
    }

    void d() {
        try {
            if (this.func_70660_b(co.b) == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 10.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        this.func_184596_c(co.b);
        this.e(entityPlayer.getPersistentID());
        float f10 = (float)(Math.atan2(this.field_70161_v - entityPlayer.field_70161_v, this.field_70165_t - entityPlayer.field_70165_t) * 57.29577951308232);
        this.b(f10);
        this.c(this.func_174791_d());
        this.m.func_187227_b(G, (Object)true);
        this.b(fp.DASH);
        this.Z = 16;
        this.func_189654_d(true);
        this.field_70145_X = true;
        ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.z);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.o);
    }

    void n() {
        try {
            if (--this.Z != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                this.f();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                this.f();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        entityPlayer.func_189654_d(true);
        entityPlayer.field_70145_X = true;
        Vec3d vec3d = ck.a(new Vec3d(0.0, 0.0, -0.5), entityPlayer.field_70177_z);
        Vec3d vec3d2 = vec3d.func_178787_e(entityPlayer.func_174791_d());
        this.c(vec3d2);
        this.b(entityPlayer.field_70177_z);
        this.b(fp.HUG);
        this.Y = 150;
    }

    void f() {
        this.m.func_187227_b(G, (Object)false);
        this.b(fp.NULL);
        this.e((UUID)null);
        this.field_70145_X = false;
        this.func_189654_d(false);
        this.ah = false;
        this.Y = -1;
        this.Z = -1;
        this.ai = -1;
        this.am = null;
    }

    protected boolean func_184645_a(EntityPlayer entityPlayer, EnumHand enumHand) {
        try {
            if (el.d(entityPlayer) != null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (this.ae() != null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                this.a(entityPlayer, false);
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        return true;
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
                            throw el.a(runtimeException);
                        }
                        return fp.COWGIRLCUM;
                    }
                    catch (RuntimeException runtimeException) {
                        throw el.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (fp2 != fp.MISSIONARY_FAST && fp2 != fp.MISSIONARY_SLOW) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw el.a(runtimeException);
                    }
                    return fp.MISSIONARY_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw el.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.CARRY_SLOW && fp2 != fp.CARRY_FAST) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw el.a(runtimeException);
                }
                return fp.CARRY_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw el.a(runtimeException);
            }
        }
        return null;
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.COWGIRLSLOW) {
                return fp.COWGIRLFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (fp2 == fp.MISSIONARY_SLOW) {
                return fp.MISSIONARY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        try {
            if (fp2 == fp.CARRY_SLOW) {
                return fp.CARRY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw el.a(runtimeException);
        }
        return null;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 11[SWITCH]
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
            throw el.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 35[SWITCH]
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

