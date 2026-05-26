/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWater
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.bh;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.co;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fg;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
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
public class ex
extends e2
implements bh,
fg {
    public boolean Z = false;
    public boolean ab = false;
    public boolean af = false;
    public static final DataParameter<Boolean> Y = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(118);
    int ac = 0;
    int ad = 0;
    boolean aa = false;
    int ag = 0;
    boolean ae = false;

    public ex(World world) {
        super(world);
        this.func_70105_a(0.49f, 1.95f);
        this.P = 140;
        this.O = 50;
        this.K = 140;
        this.V = new Vec3d(0.0, -0.029999997854232782, -0.2);
    }

    public static ex a(World world) {
        ex ex2 = new ex(world);
        ex2.F = true;
        return ex2;
    }

    @Override
    public String c() {
        return "Jenny";
    }

    @Override
    public float i() {
        return -0.2f;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.m.func_187214_a(Y, (Object)false);
    }

    @Override
    public void c() {
        this.a("Alright, this is my new Home~");
        this.a(com.trolmastercard.sexmod.c.GIRLS_JENNY_HAPPYOH[1]);
    }

    public float func_70047_e() {
        return 1.64f;
    }

    protected SoundEvent func_184615_bR() {
        return com.trolmastercard.sexmod.c.a(com.trolmastercard.sexmod.c.GIRLS_JENNY_SIGH);
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSource) {
        return null;
    }

    @Override
    public void func_70619_bc() {
        block26: {
            EntityPlayerMP entityPlayerMP;
            block28: {
                block29: {
                    block23: {
                        block25: {
                            block22: {
                                super.func_70619_bc();
                                EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 15.0);
                                try {
                                    try {
                                        if (!this.af || entityPlayer == null) break block22;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ex.a(runtimeException);
                                    }
                                    if (!(entityPlayer.func_174791_d().func_72438_d(this.func_174791_d()) < 0.5)) break block22;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ex.a(runtimeException);
                                }
                                this.af = false;
                                this.m.func_187227_b(em.y, (Object)this.field_70170_p.func_72890_a((Entity)this, 15.0).getPersistentID().toString());
                                entityPlayerMP = this.func_184102_h().func_184103_al().func_177451_a(this.ae());
                                this.m.func_187227_b(em.y, (Object)entityPlayerMP.getPersistentID().toString());
                                entityPlayerMP.func_70634_a(this.func_174791_d().field_72450_a, this.func_174791_d().field_72448_b, this.func_174791_d().field_72449_c);
                                this.a(entityPlayerMP, false);
                                entityPlayerMP.func_191958_b(0.0f, 0.0f, 0.0f, 0.0f);
                                this.a(0.0, 0.0, 0.4, 0.0f, 60.0f);
                                this.B = null;
                                this.b(fp.DOGGYSTART);
                                ge.b.sendTo((IMessage)new gz(false), entityPlayerMP);
                            }
                            try {
                                block24: {
                                    try {
                                        try {
                                            if (!this.Z) break block23;
                                            if (this.func_174791_d().func_72438_d(this.o()) < 0.6) break block24;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw ex.a(runtimeException);
                                        }
                                        if (this.ad <= 200) break block25;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ex.a(runtimeException);
                                    }
                                }
                                this.Z = false;
                                this.m.func_187227_b(em.G, (Object)true);
                                this.ad = 0;
                                this.field_70145_X = true;
                                this.func_189654_d(true);
                                this.field_70159_w = 0.0;
                                this.field_70181_x = 0.0;
                                this.field_70179_y = 0.0;
                                this.b(fp.STARTDOGGY);
                                break block23;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ex.a(runtimeException);
                            }
                        }
                        try {
                            try {
                                ++this.ad;
                                if (this.ad != 60 && this.ad != 120) break block23;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ex.a(runtimeException);
                            }
                            this.func_70661_as().func_75499_g();
                            this.func_70661_as().func_75492_a(this.o().field_72450_a, this.o().field_72448_b, this.o().field_72449_c, 0.35);
                        }
                        catch (RuntimeException runtimeException) {
                            throw ex.a(runtimeException);
                        }
                    }
                    try {
                        try {
                            block27: {
                                try {
                                    try {
                                        if (!this.ab) break block26;
                                        ++this.ac;
                                        if (this.func_174791_d().equals(em.e)) break block27;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ex.a(runtimeException);
                                    }
                                    if (this.ac <= 40) break block28;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ex.a(runtimeException);
                                }
                            }
                            this.ab = false;
                            this.ac = 0;
                            this.b(this.field_70170_p.func_73046_m().func_184103_al().func_177451_a((UUID)this.ae()).field_70177_z + 180.0f);
                            this.m.func_187227_b(em.G, (Object)true);
                            this.func_70661_as().func_75499_g();
                            if (!((Boolean)this.m.func_187225_a(Y)).booleanValue()) break block29;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ex.a(runtimeException);
                        }
                        this.U();
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ex.a(runtimeException);
                    }
                }
                this.b(fp.PAYMENT);
                break block26;
            }
            this.field_70177_z = this.I().floatValue();
            this.c(this.aa());
            this.func_189654_d(false);
            entityPlayerMP = b6.a(this.func_174791_d(), this.o(), 40 - this.ac);
            this.func_70107_b(entityPlayerMP.field_72450_a, entityPlayerMP.field_72448_b, entityPlayerMP.field_72449_c);
        }
    }

    public boolean func_184645_a(EntityPlayer entityPlayer, EnumHand enumHand) {
        block7: {
            try {
                if (super.func_184645_a(entityPlayer, enumHand)) {
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ex.a(runtimeException);
            }
            try {
                try {
                    if (!this.field_70170_p.field_72995_K || this.b(entityPlayer)) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw ex.a(runtimeException);
                }
                this.a(I18n.func_135052_a((String)"jenny.dialogue.busy", (Object[])new Object[0]));
            }
            catch (RuntimeException runtimeException) {
                throw ex.a(runtimeException);
            }
        }
        return true;
    }

    @Override
    public void func_70071_h_() {
        try {
            super.func_70071_h_();
            if (!this.field_70170_p.field_72995_K) {
                this.m.func_187227_b(Y, (Object)this.func_70644_a(co.b));
            }
        }
        catch (RuntimeException runtimeException) {
            throw ex.a(runtimeException);
        }
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        block13: {
            ItemStack itemStack;
            ItemStack[] itemStackArray;
            String[] stringArray;
            ex ex2;
            EntityPlayer entityPlayer2;
            String string;
            String[] stringArray2;
            block16: {
                block15: {
                    try {
                        try {
                            block14: {
                                try {
                                    try {
                                        if (this.ae() != null) break block13;
                                        if (!this.J()) break block14;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ex.a(runtimeException);
                                    }
                                    if (!((String)this.m.func_187225_a(em.v)).equals(Minecraft.func_71410_x().field_71439_g.getPersistentID().toString())) break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ex.a(runtimeException);
                                }
                            }
                            String[] stringArray3 = new String[4];
                            stringArray3[0] = "action.names.blowjob";
                            stringArray3[1] = "action.names.boobjob";
                            stringArray3[2] = "action.names.doggy";
                            String[] stringArray4 = stringArray3;
                            stringArray2 = stringArray3;
                            int n2 = 3;
                            if ((Integer)this.m.func_187225_a(em.D) != 1) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ex.a(runtimeException);
                        }
                        string = "action.names.strip";
                        break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ex.a(runtimeException);
                    }
                }
                string = "action.names.dressup";
            }
            stringArray4[n2] = string;
            String[] stringArray5 = stringArray2;
            try {
                if (((Boolean)this.m.func_187225_a(Y)).booleanValue()) {
                    em.a(entityPlayer, this, stringArray5, true);
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ex.a(runtimeException);
            }
            try {
                entityPlayer2 = entityPlayer;
                ex2 = this;
                stringArray = stringArray5;
                ItemStack[] itemStackArray2 = new ItemStack[4];
                itemStackArray2[0] = new ItemStack(Items.field_151166_bC, 3);
                itemStackArray2[1] = new ItemStack(Items.field_151079_bi, 2);
                itemStackArray2[2] = new ItemStack(Items.field_151045_i, 2);
                ItemStack[] itemStackArray3 = itemStackArray2;
                itemStackArray = itemStackArray2;
                int n3 = 3;
                itemStack = (Integer)this.m.func_187225_a(em.D) == 1 ? new ItemStack(Items.field_151043_k, 1) : new ItemStack(Items.field_190931_a, 0);
            }
            catch (RuntimeException runtimeException) {
                throw ex.a(runtimeException);
            }
            itemStackArray3[n3] = itemStack;
            em.a(entityPlayer2, ex2, stringArray, itemStackArray, true);
            return true;
        }
        return false;
    }

    @Override
    public void a(String string, UUID uUID) {
        block12: {
            block15: {
                block14: {
                    block13: {
                        block11: {
                            try {
                                super.a(string, uUID);
                                if (!"action.names.blowjob".equals(string)) break block11;
                                this.a("animationFollowUp", "blowjob");
                                this.a(true, uUID);
                                break block12;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ex.a(runtimeException);
                            }
                        }
                        try {
                            if (!"action.names.boobjob".equals(string)) break block13;
                            this.a("animationFollowUp", "boobjob");
                            this.a(true, uUID);
                            break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ex.a(runtimeException);
                        }
                    }
                    try {
                        if (!"action.names.doggy".equals(string)) break block14;
                        this.a("animationFollowUp", "doggy");
                        this.a(true, uUID);
                        break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ex.a(runtimeException);
                    }
                }
                try {
                    if (!"action.names.strip".equals(string)) break block15;
                    this.a("animationFollowUp", "strip");
                    this.a(true, uUID);
                    break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw ex.a(runtimeException);
                }
            }
            try {
                if ("action.names.dressup".equals(string)) {
                    this.b(fp.STRIP);
                }
            }
            catch (RuntimeException runtimeException) {
                throw ex.a(runtimeException);
            }
        }
    }

    protected void a(boolean bl2, UUID uUID) {
        super.a(bl2, true, uUID);
        d3.a(false);
    }

    @Override
    public void a() {
        block9: {
            BlockPos blockPos;
            block8: {
                blockPos = this.a(this.func_180425_c());
                try {
                    if (blockPos != null) break block8;
                    this.a(com.trolmastercard.sexmod.c.GIRLS_JENNY_HMPH[2]);
                    this.a(I18n.func_135052_a((String)"jenny.dialogue.nobedinsight", (Object[])new Object[0]));
                    break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw ex.a(runtimeException);
                }
            }
            this.field_70714_bg.func_85156_a((EntityAIBase)this.z);
            this.field_70714_bg.func_85156_a((EntityAIBase)this.o);
            Vec3d vec3d = new Vec3d((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p());
            int[] nArray = new int[]{0, 180, -90, 90};
            Vec3d[][] vec3dArrayArray = new Vec3d[][]{{new Vec3d(0.5, 0.0, -0.5), new Vec3d(0.0, 0.0, -1.0)}, {new Vec3d(0.5, 0.0, 1.5), new Vec3d(0.0, 0.0, 1.0)}, {new Vec3d(-0.5, 0.0, 0.5), new Vec3d(-1.0, 0.0, 0.0)}, {new Vec3d(1.5, 0.0, 0.5), new Vec3d(1.0, 0.0, 0.0)}};
            int n2 = -1;
            for (int i2 = 0; i2 < vec3dArrayArray.length; ++i2) {
                block10: {
                    Vec3d vec3d2 = vec3d.func_178787_e(vec3dArrayArray[i2][1]);
                    try {
                        if (this.field_70170_p.func_180495_p(new BlockPos(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c)).func_177230_c() != Blocks.field_150350_a) continue;
                        if (n2 != -1) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ex.a(runtimeException);
                    }
                    n2 = i2;
                    continue;
                }
                double d10 = this.func_180425_c().func_177954_c(vec3d.func_178787_e((Vec3d)vec3dArrayArray[n2][0]).field_72450_a, vec3d.func_178787_e((Vec3d)vec3dArrayArray[n2][0]).field_72448_b, vec3d.func_178787_e((Vec3d)vec3dArrayArray[n2][0]).field_72449_c);
                double d11 = this.func_180425_c().func_177954_c(vec3d.func_178787_e((Vec3d)vec3dArrayArray[i2][0]).field_72450_a, vec3d.func_178787_e((Vec3d)vec3dArrayArray[i2][0]).field_72448_b, vec3d.func_178787_e((Vec3d)vec3dArrayArray[i2][0]).field_72449_c);
                if (!(d11 < d10)) continue;
                n2 = i2;
            }
            try {
                if (n2 == -1) {
                    this.a(com.trolmastercard.sexmod.c.GIRLS_JENNY_HMPH[2]);
                    this.a(I18n.func_135052_a((String)"jenny.dialogue.bedobscured", (Object[])new Object[0]));
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ex.a(runtimeException);
            }
            Vec3d vec3d3 = vec3d.func_178787_e(vec3dArrayArray[n2][0]);
            this.a(false);
            this.b(nArray[n2]);
            this.c(new Vec3d(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c));
            this.r = this.I().floatValue();
            this.func_70661_as().func_75499_g();
            this.func_70661_as().func_75492_a(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c, 0.35);
            this.Z = true;
            this.ad = 0;
        }
    }

    @Override
    public void b(fp fp2) {
        block34: {
            fp fp3;
            block32: {
                block30: {
                    block28: {
                        fp3 = this.y();
                        try {
                            block29: {
                                try {
                                    try {
                                        if (fp3 != fp.DOGGYCUM) break block28;
                                        if (fp2 == fp.DOGGYSLOW) break block29;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ex.a(runtimeException);
                                    }
                                    if (fp2 != fp.DOGGYFAST) break block28;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ex.a(runtimeException);
                                }
                            }
                            return;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ex.a(runtimeException);
                        }
                    }
                    try {
                        block31: {
                            try {
                                try {
                                    if (fp3 != fp.CUMBLOWJOB) break block30;
                                    if (fp2 == fp.THRUSTBLOWJOB) break block31;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ex.a(runtimeException);
                                }
                                if (fp2 != fp.SUCKBLOWJOB) break block30;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ex.a(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ex.a(runtimeException);
                    }
                }
                try {
                    block33: {
                        try {
                            try {
                                if (fp3 != fp.PAIZURI_CUM) break block32;
                                if (fp2 == fp.PAIZURI_SLOW) break block33;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ex.a(runtimeException);
                            }
                            if (fp2 != fp.PAIZURI_FAST) break block32;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ex.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ex.a(runtimeException);
                }
            }
            try {
                try {
                    super.b(fp2);
                    if (fp3 == fp.STARTBLOWJOB || fp3 == fp.PAIZURI_START) break block34;
                }
                catch (RuntimeException runtimeException) {
                    throw ex.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ex.a(runtimeException);
            }
        }
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ex.a(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ex.a(runtimeException);
        }
        Vec3d vec3d = ck.a(new Vec3d(0.0, 0.0, 0.2), this.I().floatValue() + 180.0f);
        entityPlayer.func_70634_a(entityPlayer.field_70165_t + vec3d.field_72450_a, entityPlayer.field_70163_u, entityPlayer.field_70161_v + vec3d.field_72449_c);
    }

    @Override
    protected fp a(fp fp2) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (fp2 != fp.SUCKBLOWJOB && fp2 != fp.THRUSTBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ex.a(runtimeException);
                        }
                        this.a(0.0, 0.0, 0.0, 0.0f, 70.0f);
                        return fp.CUMBLOWJOB;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ex.a(runtimeException);
                    }
                }
                try {
                    try {
                        if (fp2 != fp.DOGGYSLOW && fp2 != fp.DOGGYFAST) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ex.a(runtimeException);
                    }
                    return fp.DOGGYCUM;
                }
                catch (RuntimeException runtimeException) {
                    throw ex.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.PAIZURI_FAST && fp2 != fp.PAIZURI_SLOW) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw ex.a(runtimeException);
                }
                return fp.PAIZURI_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw ex.a(runtimeException);
            }
        }
        return null;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected fp c(fp var1_1) {
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
    public void b() {
        this.ab = true;
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
    protected void U() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 6[SWITCH]
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

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 12[SWITCH]
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
            throw ex.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 42[SWITCH]
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

