/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector4d
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWater
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.bh;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.ds;
import com.trolmastercard.sexmod.dz;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.fg;
import com.trolmastercard.sexmod.fh;
import com.trolmastercard.sexmod.fp;
import java.util.UUID;
import javax.vecmath.Vector4d;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
public class e0
extends e2
implements bh,
fg {
    static final int ae = 3;
    public boolean Y = false;
    int ag = 0;
    boolean af = false;
    int Z = 0;
    boolean ab = true;
    int ac = -1;
    boolean aa = false;
    final int[] ai = new int[]{0, 180, -90, 90};
    final Vec3d[][] ad = new Vec3d[][]{{new Vec3d(0.5, 0.0, -0.5), new Vec3d(0.0, 0.0, -1.0)}, {new Vec3d(0.5, 0.0, 1.5), new Vec3d(0.0, 0.0, 1.0)}, {new Vec3d(-0.5, 0.0, 0.5), new Vec3d(-1.0, 0.0, 0.0)}, {new Vec3d(1.5, 0.0, 0.5), new Vec3d(1.0, 0.0, 0.0)}};
    int ah = 1;

    public e0(World world) {
        super(world);
        this.func_70105_a(0.49f, 1.65f);
        this.P = 140;
        this.O = 50;
        this.K = 140;
        this.V = new Vec3d(0.0, -0.029999997854232782, -0.2);
    }

    @Override
    public String c() {
        return "Bia";
    }

    @Override
    public float i() {
        return -0.2f;
    }

    @Override
    public void c() {
        this.a("I am living here now nya~");
        this.a(com.trolmastercard.sexmod.c.GIRLS_BIA_BREATH, new int[0]);
    }

    @Override
    public void b() {
        this.Y = true;
    }

    @Override
    public void b(fp fp2) {
        block19: {
            fp fp3;
            block17: {
                block16: {
                    fp3 = this.y();
                    try {
                        try {
                            if (fp3 != fp.ANAL_CUM && fp3 != fp.PRONE_DOGGY_CUM) break block16;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw e0.a(nullPointerException);
                        }
                        this.m.func_187227_b(h, (Object)"");
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                }
                try {
                    block18: {
                        try {
                            try {
                                if (fp3 != fp.ANAL_CUM) break block17;
                                if (fp2 == fp.ANAL_FAST) break block18;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw e0.a(nullPointerException);
                            }
                            if (fp2 != fp.ANAL_SLOW) break block17;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw e0.a(nullPointerException);
                        }
                    }
                    return;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
            }
            try {
                block20: {
                    try {
                        try {
                            if (fp3 != fp.PRONE_DOGGY_CUM) break block19;
                            if (fp2 == fp.PRONE_DOGGY_HARD) break block20;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw e0.a(nullPointerException);
                        }
                        if (fp2 != fp.PRONE_DOGGY_SOFT) break block19;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                }
                return;
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
        }
        super.b(fp2);
    }

    @Override
    protected ResourceLocation func_184647_J() {
        return dz.c;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void func_70619_bc() {
        block25: {
            block27: {
                block28: {
                    block23: {
                        block24: {
                            block29: {
                                try {
                                    super.func_70619_bc();
                                    if (this.ab) {
                                        this.func_189654_d(false);
                                        this.field_70145_X = false;
                                        this.ab = false;
                                    }
                                }
                                catch (NullPointerException v0) {
                                    throw e0.a(v0);
                                }
                                if (!this.Y) break block23;
                                ++this.ag;
                                if (this.func_174791_d().equals((Object)this.o())) ** GOTO lbl24
                                break block29;
                                catch (NullPointerException v1) {
                                    throw e0.a(v1);
                                }
                            }
                            try {
                                block30: {
                                    if (this.ag <= 40) break block24;
                                    break block30;
                                    catch (NullPointerException v2) {
                                        throw e0.a(v2);
                                    }
                                }
                                this.Y = false;
                                this.ag = 0;
                                this.b(this.field_70170_p.func_73046_m().func_184103_al().func_177451_a((UUID)this.ae()).field_70177_z + 180.0f);
                                this.m.func_187227_b(e0.G, (Object)true);
                                this.func_70661_as().func_75499_g();
                                this.U();
                                break block23;
                            }
                            catch (NullPointerException v3) {
                                throw e0.a(v3);
                            }
                        }
                        this.field_70177_z = this.I().floatValue();
                        try {
                            e0.e.equals(null);
                        }
                        catch (NullPointerException var1_1) {
                            this.c(this.aa());
                        }
                        this.func_189654_d(false);
                        var1_2 = b6.a(this.func_174791_d(), this.o(), 40 - this.ag);
                        this.func_70107_b(var1_2.field_72450_a, var1_2.field_72448_b, var1_2.field_72449_c);
                    }
                    try {
                        try {
                            block26: {
                                try {
                                    try {
                                        if (!this.af) break block25;
                                        if (this.func_174791_d().func_72438_d(this.o()) < 0.6) break block26;
                                    }
                                    catch (NullPointerException v4) {
                                        throw e0.a(v4);
                                    }
                                    if (this.Z <= 200) break block27;
                                }
                                catch (NullPointerException v5) {
                                    throw e0.a(v5);
                                }
                            }
                            this.af = false;
                            this.m.func_187227_b(e0.G, (Object)true);
                            this.Z = 0;
                            this.field_70145_X = true;
                            this.func_189654_d(true);
                            this.field_70159_w = 0.0;
                            this.field_70181_x = 0.0;
                            this.field_70179_y = 0.0;
                            if (!"anal".equals(this.m.func_187225_a(e0.h))) break block28;
                        }
                        catch (NullPointerException v6) {
                            throw e0.a(v6);
                        }
                        this.b(fp.ANAL_PREPARE);
                        this.f(0);
                        break block25;
                    }
                    catch (NullPointerException v7) {
                        throw e0.a(v7);
                    }
                }
                this.b(fp.SITDOWN);
                break block25;
            }
            try {
                try {
                    ++this.Z;
                    if (this.Z != 60 && this.Z != 120) break block25;
                }
                catch (NullPointerException v8) {
                    throw e0.a(v8);
                }
                this.func_70661_as().func_75499_g();
                this.func_70661_as().func_75492_a(this.o().field_72450_a, this.o().field_72448_b, this.o().field_72449_c, 0.35);
            }
            catch (NullPointerException v9) {
                throw e0.a(v9);
            }
        }
    }

    public boolean func_184645_a(EntityPlayer entityPlayer, EnumHand enumHand) {
        block15: {
            boolean bl2;
            try {
                if (super.func_184645_a(entityPlayer, enumHand)) {
                    return true;
                }
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
            try {
                if (this.y() == fp.SITDOWNIDLE) {
                    return true;
                }
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
            ItemStack itemStack = entityPlayer.func_184586_b(enumHand);
            try {
                bl2 = itemStack.func_77973_b() == Items.field_151057_cb;
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
            boolean bl3 = bl2;
            try {
                if (bl3) {
                    itemStack.func_111282_a(entityPlayer, (EntityLivingBase)this, enumHand);
                    return true;
                }
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
            try {
                try {
                    if (!this.field_70170_p.field_72995_K || this.b(entityPlayer)) break block15;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
                this.a(I18n.func_135052_a((String)"bia.dialogue.busy", (Object[])new Object[0]));
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
        }
        return true;
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        block8: {
            String string;
            String[] stringArray;
            block11: {
                block10: {
                    try {
                        try {
                            block9: {
                                try {
                                    try {
                                        if (this.ae() != null) break block8;
                                        if (!this.J()) break block9;
                                    }
                                    catch (NullPointerException nullPointerException) {
                                        throw e0.a(nullPointerException);
                                    }
                                    if (!((String)this.m.func_187225_a(v)).equals(Minecraft.func_71410_x().field_71439_g.getPersistentID().toString())) break block8;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw e0.a(nullPointerException);
                                }
                            }
                            String[] stringArray2 = new String[3];
                            String[] stringArray3 = stringArray2;
                            stringArray = stringArray2;
                            int n2 = 0;
                            if ((Integer)this.m.func_187225_a(D) != 1) break block10;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw e0.a(nullPointerException);
                        }
                        string = "action.names.strip";
                        break block11;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                }
                string = "action.names.dressup";
            }
            stringArray3[n2] = string;
            stringArray[1] = "action.names.talk";
            stringArray[2] = "action.names.headpat";
            String[] stringArray4 = stringArray;
            e0.a(entityPlayer, this, stringArray4, true);
            return true;
        }
        return false;
    }

    void b(EntityPlayer entityPlayer) {
        e0.a(entityPlayer, this, new String[]{"action.names.anal", "doggy"}, false);
    }

    @Override
    public void ac() {
        block4: {
            try {
                try {
                    if (!this.Q() || this.aa) break block4;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
                this.r();
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
        }
        this.aa = false;
    }

    @Override
    public void func_70071_h_() {
        block8: {
            try {
                try {
                    try {
                        try {
                            super.func_70071_h_();
                            if (!this.field_70170_p.field_72995_K || !this.n()) break block8;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw e0.a(nullPointerException);
                        }
                        if (this.y() != fp.PRONE_DOGGY_INTRO) break block8;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                    if (fh.a()) break block8;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
                ds.d();
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
        }
        this.d();
    }

    @Override
    protected void V() {
        super.V();
        this.ac = -1;
    }

    void d() {
        float f10;
        EntityPlayer entityPlayer;
        block27: {
            block29: {
                block28: {
                    fp fp2;
                    block24: {
                        block26: {
                            block25: {
                                block23: {
                                    fp2 = this.y();
                                    try {
                                        try {
                                            if (fp2 == fp.ANAL_WAIT || fp2 == fp.SITDOWNIDLE) break block23;
                                        }
                                        catch (NullPointerException nullPointerException) {
                                            throw e0.a(nullPointerException);
                                        }
                                        return;
                                    }
                                    catch (NullPointerException nullPointerException) {
                                        throw e0.a(nullPointerException);
                                    }
                                }
                                entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 10.0);
                                try {
                                    if (entityPlayer == null) {
                                        return;
                                    }
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw e0.a(nullPointerException);
                                }
                                try {
                                    if (entityPlayer.func_70032_d((Entity)this) > 1.0f) {
                                        return;
                                    }
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw e0.a(nullPointerException);
                                }
                                try {
                                    try {
                                        if (this.ac != -1) break block24;
                                        if (!this.field_70170_p.field_72995_K) break block25;
                                    }
                                    catch (NullPointerException nullPointerException) {
                                        throw e0.a(nullPointerException);
                                    }
                                    fh.b();
                                    d3.a(false);
                                    break block26;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw e0.a(nullPointerException);
                                }
                            }
                            this.e(entityPlayer.getPersistentID());
                        }
                        this.ac = j;
                        return;
                    }
                    try {
                        if (--this.ac > 0) {
                            return;
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                    try {
                        this.ac = -1;
                        entityPlayer.field_70145_X = true;
                        entityPlayer.func_189654_d(true);
                        if (fp2 != fp.ANAL_WAIT) break block27;
                        if (this.field_70170_p.field_72995_K) break block28;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                    this.b(fp.ANAL_START);
                    Vec3d vec3d = this.o().func_178787_e(ck.a(-0.3, -1.0, -0.5, this.I().floatValue()));
                    entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
                    break block29;
                }
                try {
                    if (this.n()) {
                        ds.d();
                    }
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
            }
            return;
        }
        entityPlayer.field_70177_z = f10 = this.I().floatValue();
        entityPlayer.field_70125_A = 60.0f;
        if (!this.field_70170_p.field_72995_K) {
            this.f(0);
            this.b(fp.PRONE_DOGGY_INTRO);
            Vec3d vec3d = this.o();
            Vec3d vec3d2 = vec3d.func_178787_e(ck.a(0.0, 0.0, 1.0, f10));
            this.c(vec3d2);
            Vec3d vec3d3 = vec3d.func_178787_e(ck.a(0.0, 1.1875 - (double)entityPlayer.func_70047_e(), 0.5, f10));
            entityPlayer.func_70634_a(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c);
            this.a(true);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ag() {
        try {
            super.ag();
            if (this.y() != fp.PRONE_DOGGY_HARD) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        int n2 = this.ah;
        try {
            do {
                this.ah = this.func_70681_au().nextInt(3) + 1;
            } while (n2 == this.ah);
            return;
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
    }

    @Override
    public void g() {
        this.z = new EntityAIWanderAvoidWater((EntityCreature)this, 0.35);
        this.o = new df((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.o);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.z);
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

    public void func_70645_a(DamageSource damageSource) {
        try {
            super.func_70645_a(damageSource);
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        EntityItem entityItem = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(Blocks.field_150325_L, this.func_70681_au().nextInt(4), 12));
        this.field_70170_p.func_72838_d((Entity)entityItem);
    }

    void a(UUID uUID) {
        this.a(true, true, uUID);
        d3.a(false);
    }

    Vector4d a() {
        BlockPos blockPos;
        block15: {
            blockPos = null;
            int n2 = 0;
            while (!this.a(blockPos)) {
                blockPos = this.a(this.func_180425_c(), n2);
                if (++n2 != 50) continue;
            }
            try {
                try {
                    if (blockPos != null && n2 != 50) break block15;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
                this.a(com.trolmastercard.sexmod.c.GIRLS_BIA_BREATH[2]);
                this.a(I18n.func_135052_a((String)"jenny.dialogue.nobedinsight", (Object[])new Object[0]));
                return null;
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
        }
        this.field_70714_bg.func_85156_a((EntityAIBase)this.z);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.o);
        Vec3d vec3d = new Vec3d((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p());
        int n3 = -1;
        for (int i2 = 0; i2 < this.ad.length; ++i2) {
            Vec3d vec3d2 = vec3d.func_178787_e(this.ad[i2][1]);
            Vec3d vec3d3 = vec3d.func_178788_d(this.ad[i2][1]);
            Block block = this.field_70170_p.func_180495_p(new BlockPos(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c)).func_177230_c();
            try {
                try {
                    if (block != Blocks.field_150350_a || !cj.b(this.field_70170_p, new BlockPos(vec3d3))) {
                        continue;
                    }
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
            if (n3 == -1) {
                n3 = i2;
                continue;
            }
            double d10 = this.func_180425_c().func_177954_c(vec3d.func_178787_e((Vec3d)this.ad[n3][0]).field_72450_a, vec3d.func_178787_e((Vec3d)this.ad[n3][0]).field_72448_b, vec3d.func_178787_e((Vec3d)this.ad[n3][0]).field_72449_c);
            double d11 = this.func_180425_c().func_177954_c(vec3d.func_178787_e((Vec3d)this.ad[i2][0]).field_72450_a, vec3d.func_178787_e((Vec3d)this.ad[i2][0]).field_72448_b, vec3d.func_178787_e((Vec3d)this.ad[i2][0]).field_72449_c);
            if (!(d11 < d10)) continue;
            n3 = i2;
        }
        try {
            if (n3 == -1) {
                this.a(com.trolmastercard.sexmod.c.GIRLS_BIA_BREATH[2]);
                this.a(I18n.func_135052_a((String)"jenny.dialogue.nobedinsight", (Object[])new Object[0]));
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        Vec3d vec3d4 = vec3d.func_178787_e(this.ad[n3][0]);
        return new Vector4d(vec3d4.field_72450_a, vec3d4.field_72448_b, vec3d4.field_72449_c, (double)this.ai[n3]);
    }

    boolean a(BlockPos blockPos) {
        block22: {
            block21: {
                block20: {
                    block19: {
                        try {
                            if (blockPos == null) {
                                return false;
                            }
                        }
                        catch (NullPointerException nullPointerException) {
                            throw e0.a(nullPointerException);
                        }
                        try {
                            try {
                                if (!cj.b(this.field_70170_p, blockPos.func_177978_c()) || !this.field_70170_p.func_175623_d(blockPos.func_177968_d())) break block19;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw e0.a(nullPointerException);
                            }
                            return true;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw e0.a(nullPointerException);
                        }
                    }
                    try {
                        try {
                            if (!cj.b(this.field_70170_p, blockPos.func_177974_f()) || !this.field_70170_p.func_175623_d(blockPos.func_177976_e())) break block20;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw e0.a(nullPointerException);
                        }
                        return true;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                }
                try {
                    try {
                        if (!cj.b(this.field_70170_p, blockPos.func_177968_d()) || !this.field_70170_p.func_175623_d(blockPos.func_177978_c())) break block21;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                    return true;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
            }
            try {
                try {
                    if (!cj.b(this.field_70170_p, blockPos.func_177976_e()) || !this.field_70170_p.func_175623_d(blockPos.func_177974_f())) break block22;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
                return true;
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
        }
        return false;
    }

    Vector4d b() {
        BlockPos blockPos = this.a(this.func_180425_c());
        try {
            if (blockPos == null) {
                this.a(com.trolmastercard.sexmod.c.GIRLS_BIA_BREATH[2]);
                this.a(I18n.func_135052_a((String)"jenny.dialogue.nobedinsight", (Object[])new Object[0]));
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        this.field_70714_bg.func_85156_a((EntityAIBase)this.z);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.o);
        Vec3d vec3d = new Vec3d((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p());
        int n2 = -1;
        for (int i2 = 0; i2 < this.ad.length; ++i2) {
            block9: {
                Vec3d vec3d2 = vec3d.func_178787_e(this.ad[i2][1]);
                try {
                    if (this.field_70170_p.func_180495_p(new BlockPos(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c)).func_177230_c() != Blocks.field_150350_a) continue;
                    if (n2 != -1) break block9;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
                n2 = i2;
                continue;
            }
            double d10 = this.func_180425_c().func_177954_c(vec3d.func_178787_e((Vec3d)this.ad[n2][0]).field_72450_a, vec3d.func_178787_e((Vec3d)this.ad[n2][0]).field_72448_b, vec3d.func_178787_e((Vec3d)this.ad[n2][0]).field_72449_c);
            double d11 = this.func_180425_c().func_177954_c(vec3d.func_178787_e((Vec3d)this.ad[i2][0]).field_72450_a, vec3d.func_178787_e((Vec3d)this.ad[i2][0]).field_72448_b, vec3d.func_178787_e((Vec3d)this.ad[i2][0]).field_72449_c);
            if (!(d11 < d10)) continue;
            n2 = i2;
        }
        try {
            if (n2 == -1) {
                this.a(com.trolmastercard.sexmod.c.GIRLS_BIA_BREATH[2]);
                this.a(I18n.func_135052_a((String)"jenny.dialogue.bedobscured", (Object[])new Object[0]));
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        Vec3d vec3d3 = vec3d.func_178787_e(this.ad[n2][0]);
        return new Vector4d(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c, (double)this.ai[n2]);
    }

    @Override
    public void a() {
        Vector4d vector4d;
        String string = (String)this.m.func_187225_a(h);
        try {
            vector4d = string.equals("anal") ? this.b() : this.a();
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        Vector4d vector4d2 = vector4d;
        try {
            if (vector4d2 == null) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        Vec3d vec3d = new Vec3d(vector4d2.getX(), vector4d2.getY(), vector4d2.getZ());
        this.b((float)vector4d2.getW());
        this.c(vec3d);
        this.r = this.I().floatValue();
        this.func_70661_as().func_75499_g();
        this.func_70661_as().func_75492_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 0.35);
        this.af = true;
        this.Z = 0;
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.ANAL_SLOW) {
                return fp.ANAL_FAST;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        try {
            if (fp2 == fp.PRONE_DOGGY_INTRO) {
                return fp.PRONE_DOGGY_INSERT;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block9: {
            block8: {
                try {
                    try {
                        if (fp2 != fp.ANAL_SLOW && fp2 != fp.ANAL_FAST) break block8;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw e0.a(nullPointerException);
                    }
                    return fp.ANAL_CUM;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
            }
            try {
                try {
                    if (fp2 != fp.PRONE_DOGGY_SOFT && fp2 != fp.PRONE_DOGGY_HARD) break block9;
                }
                catch (NullPointerException nullPointerException) {
                    throw e0.a(nullPointerException);
                }
                return fp.PRONE_DOGGY_CUM;
            }
            catch (NullPointerException nullPointerException) {
                throw e0.a(nullPointerException);
            }
        }
        return null;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void U() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 3[SWITCH]
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
    public float T() {
        return 35.0f;
    }

    @Override
    public float ai() {
        return 140.0f;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 10[SWITCH]
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
        catch (NullPointerException nullPointerException) {
            throw e0.a(nullPointerException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 23[SWITCH]
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

    private static NullPointerException a(NullPointerException nullPointerException) {
        return nullPointerException;
    }
}

