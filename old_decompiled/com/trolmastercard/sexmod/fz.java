/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying
 *  net.minecraft.entity.ai.EntityFlyHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.network.play.server.SPacketParticles
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathNavigateFlying
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ch;
import com.trolmastercard.sexmod.co;
import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.fo;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import com.trolmastercard.sexmod.h;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
public class fz
extends fo {
    public float N = 3200.0f;
    int P = 0;
    static final float O = 4800.0f;
    static final float Q = 10.0f;
    public static final DataParameter<Boolean> M = EntityDataManager.func_187226_a(fz.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(112);

    public fz(World world) {
        super(world);
        this.field_70765_h = new EntityFlyHelper((EntityLiving)this);
        this.func_70105_a(0.3f, 1.5f);
    }

    @Override
    public String c() {
        return "Bee";
    }

    @Override
    public float i() {
        return -0.1f;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.m.func_187214_a(M, (Object)false);
    }

    protected PathNavigate func_175447_b(World world) {
        PathNavigateFlying pathNavigateFlying = new PathNavigateFlying((EntityLiving)this, world);
        pathNavigateFlying.func_192879_a(false);
        pathNavigateFlying.func_192877_c(true);
        pathNavigateFlying.func_192878_b(true);
        this.f = pathNavigateFlying;
        return pathNavigateFlying;
    }

    @Override
    protected void func_110147_ax() {
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111267_a);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111266_c);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111263_d);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_188791_g);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_189429_h);
        this.func_110140_aT().func_111150_b(SWIM_SPEED);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_193334_e);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(12.0);
        this.func_110148_a(SharedMonsterAttributes.field_193334_e).func_111128_a((double)0.4f);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.2f);
    }

    @Override
    protected void func_184651_r() {
        this.o = new df((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new h(this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.25));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.o);
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWanderAvoidWaterFlying((EntityCreature)this, 1.0));
    }

    @Override
    public void func_70619_bc() {
        block9: {
            try {
                try {
                    try {
                        super.func_70619_bc();
                        if (!this.func_70644_a(co.b) || !(this.N < 4800.0f)) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fz.b(runtimeException);
                    }
                    if (this.ae() != null) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw fz.b(runtimeException);
                }
                this.func_184589_d(co.b);
                this.N = 6.9420184E7f;
            }
            catch (RuntimeException runtimeException) {
                throw fz.b(runtimeException);
            }
        }
        try {
            this.c();
            if (this.y().equals((Object)fp.CITIZEN_CUM)) {
                this.P = Math.max(1, this.P);
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
        }
        this.a();
        this.b();
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
                            throw fz.b(runtimeException);
                        }
                        if (fp2 != fp.COWGIRLSLOW) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fz.b(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw fz.b(runtimeException);
            }
        }
        super.b(fp2);
    }

    void c() {
        try {
            if (this.ae() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
        }
        try {
            if (this.J()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
        }
        try {
            this.N += 1.0f;
            if (this.N < 4800.0f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 10.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
        }
        try {
            if (fz.d(entityPlayer) != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
        }
        try {
            if (ei.e(entityPlayer)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
        }
        if (entityPlayer.func_70032_d((Entity)this) < 1.5f) {
            this.N = 0.0f;
            this.e(entityPlayer.getPersistentID());
            this.m.func_187227_b(G, (Object)true);
            this.c(this.aa());
            this.b(entityPlayer.field_70177_z - 180.0f);
            this.f.func_75499_g();
            ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
            this.b(fp.CITIZEN_START);
            Vec3d vec3d = this.a(0.2);
            entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        } else {
            this.f.func_75499_g();
            this.f.func_75497_a((Entity)entityPlayer, 1.0);
        }
    }

    void b() {
        block7: {
            RayTraceResult rayTraceResult = this.field_70170_p.func_72933_a(this.func_174791_d(), new Vec3d(this.field_70165_t, 0.0, this.field_70161_v));
            try {
                if (rayTraceResult == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fz.b(runtimeException);
            }
            BlockPos blockPos = rayTraceResult.func_178782_a();
            double d10 = this.field_70163_u - (double)blockPos.func_177956_o();
            try {
                try {
                    if (!(d10 > 3.0) || !(this.field_70181_x > 0.0)) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw fz.b(runtimeException);
                }
                this.field_70181_x = 0.0;
            }
            catch (RuntimeException runtimeException) {
                throw fz.b(runtimeException);
            }
        }
    }

    void a() {
        block28: {
            block25: {
                block26: {
                    try {
                        if (this.P == 0) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw fz.b(runtimeException);
                    }
                    try {
                        ++this.P;
                        if (!((Boolean)this.m.func_187225_a(M)).booleanValue()) break block25;
                        if (this.P >= 40) break block26;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fz.b(runtimeException);
                    }
                    for (EntityPlayer entityPlayer : this.field_70170_p.field_73010_i) {
                        try {
                            if (!(entityPlayer.func_70032_d((Entity)this) < 15.0f)) continue;
                            ((EntityPlayerMP)entityPlayer).field_71135_a.func_147359_a((Packet)new SPacketParticles(EnumParticleTypes.HEART, true, (float)this.field_70165_t, (float)this.field_70163_u + 0.3f, (float)this.field_70161_v, 0.2f, 0.3f, 0.2f, 0.25f, 1, new int[0]));
                        }
                        catch (RuntimeException runtimeException) {
                            throw fz.b(runtimeException);
                        }
                    }
                    break block28;
                }
                this.P = 0;
                break block28;
            }
            if (this.P < 200) {
                for (EntityPlayer entityPlayer : this.field_70170_p.field_73010_i) {
                    try {
                        if (!(entityPlayer.func_70032_d((Entity)this) < 15.0f)) continue;
                        ((EntityPlayerMP)entityPlayer).field_71135_a.func_147359_a((Packet)new SPacketParticles(EnumParticleTypes.SPELL, true, (float)this.field_70165_t, (float)this.field_70163_u + 0.3f, (float)this.field_70161_v, 0.2f, 0.3f, 0.2f, 0.25f, 1, new int[0]));
                    }
                    catch (RuntimeException runtimeException) {
                        throw fz.b(runtimeException);
                    }
                }
            } else {
                block27: {
                    try {
                        if (this.P != 200) break block27;
                        this.m.func_187227_b(M, (Object)this.func_70681_au().nextBoolean());
                        break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fz.b(runtimeException);
                    }
                }
                if (this.P < 250) {
                    for (EntityPlayer entityPlayer : this.field_70170_p.field_73010_i) {
                        EnumParticleTypes enumParticleTypes;
                        SPacketParticles sPacketParticles;
                        SPacketParticles sPacketParticles2;
                        NetHandlerPlayServer netHandlerPlayServer;
                        block30: {
                            block29: {
                                try {
                                    try {
                                        SPacketParticles sPacketParticles3;
                                        if (!(entityPlayer.func_70032_d((Entity)this) < 15.0f)) continue;
                                        netHandlerPlayServer = ((EntityPlayerMP)entityPlayer).field_71135_a;
                                        sPacketParticles2 = sPacketParticles3;
                                        sPacketParticles = sPacketParticles3;
                                        if (!((Boolean)this.m.func_187225_a(M)).booleanValue()) break block29;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw fz.b(runtimeException);
                                    }
                                    enumParticleTypes = EnumParticleTypes.HEART;
                                    break block30;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw fz.b(runtimeException);
                                }
                            }
                            enumParticleTypes = EnumParticleTypes.VILLAGER_ANGRY;
                        }
                        sPacketParticles2(enumParticleTypes, true, (float)this.field_70165_t, (float)this.field_70163_u + 0.3f, (float)this.field_70161_v, 0.2f, 0.3f, 0.2f, 0.25f, 3, new int[0]);
                        netHandlerPlayServer.func_147359_a((Packet)sPacketParticles);
                    }
                } else {
                    this.P = 0;
                }
            }
        }
        for (EntityPlayer entityPlayer : this.field_70170_p.field_73010_i) {
            try {
                if (!(entityPlayer.func_70032_d((Entity)this) < 15.0f)) continue;
                ((EntityPlayerMP)entityPlayer).field_71135_a.func_147359_a((Packet)new SPacketParticles(EnumParticleTypes.SPELL, true, (float)this.field_70165_t, (float)this.field_70163_u + 0.3f, (float)this.field_70161_v, 0.2f, 0.3f, 0.2f, 0.25f, 10, new int[0]));
            }
            catch (RuntimeException runtimeException) {
                throw fz.b(runtimeException);
            }
        }
    }

    @Override
    public void func_70071_h_() {
        block6: {
            try {
                try {
                    try {
                        super.func_70071_h_();
                        if (!(this.N < 4800.0f) || this.field_70122_E) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fz.b(runtimeException);
                    }
                    if (!(this.field_70181_x < 0.0)) break block6;
                }
                catch (RuntimeException runtimeException) {
                    throw fz.b(runtimeException);
                }
                this.field_70181_x *= 0.4;
            }
            catch (RuntimeException runtimeException) {
                throw fz.b(runtimeException);
            }
        }
    }

    public void func_180430_e(float f10, float f11) {
    }

    protected boolean func_184645_a(EntityPlayer entityPlayer, EnumHand enumHand) {
        block11: {
            block10: {
                try {
                    try {
                        try {
                            if (!((Boolean)this.m.func_187225_a(M)).booleanValue() || ((Boolean)this.m.func_187225_a(K)).booleanValue()) break block10;
                        }
                        catch (RuntimeException runtimeException) {
                            throw fz.b(runtimeException);
                        }
                        if (entityPlayer.func_184586_b(enumHand).func_77973_b() != Item.func_150898_a((Block)Blocks.field_150486_ae)) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw fz.b(runtimeException);
                    }
                    this.m.func_187227_b(K, (Object)true);
                    entityPlayer.func_184586_b(enumHand).func_190918_g(1);
                    return super.func_184645_a(entityPlayer, enumHand);
                }
                catch (RuntimeException runtimeException) {
                    throw fz.b(runtimeException);
                }
            }
            try {
                try {
                    if (!this.field_70170_p.field_72995_K || !((Boolean)this.m.func_187225_a(M)).booleanValue()) break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw fz.b(runtimeException);
                }
                this.b(entityPlayer);
            }
            catch (RuntimeException runtimeException) {
                throw fz.b(runtimeException);
            }
        }
        return super.func_184645_a(entityPlayer, enumHand);
    }

    @SideOnly(value=Side.CLIENT)
    void b(EntityPlayer entityPlayer) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new ch(this, entityPlayer));
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public void a(String string, UUID uUID) {
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.CITIZEN_SLOW) {
                return fp.CITIZEN_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
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
                    throw fz.b(runtimeException);
                }
                return fp.CITIZEN_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw fz.b(runtimeException);
            }
        }
        return null;
    }

    @Override
    protected void U() {
    }

    @Override
    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        super.func_70014_b(nBTTagCompound);
        nBTTagCompound.func_74757_a("isTamed", ((Boolean)this.m.func_187225_a(M)).booleanValue());
        nBTTagCompound.func_74757_a("hasChest", ((Boolean)this.m.func_187225_a(K)).booleanValue());
        nBTTagCompound.func_74782_a("inventory", (NBTBase)this.L.serializeNBT());
    }

    public void func_70020_e(NBTTagCompound nBTTagCompound) {
        try {
            super.func_70020_e(nBTTagCompound);
            if (nBTTagCompound.func_74764_b("isTamed")) {
                this.m.func_187227_b(M, (Object)nBTTagCompound.func_74767_n("isTamed"));
            }
        }
        catch (RuntimeException runtimeException) {
            throw fz.b(runtimeException);
        }
        this.m.func_187227_b(K, (Object)nBTTagCompound.func_74767_n("hasChest"));
        this.L.deserializeNBT(nBTTagCompound.func_74775_l("inventory"));
    }

    /*
     * Exception decompiling
     */
    @Override
    @SideOnly(value=Side.CLIENT)
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 6[SWITCH]
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
            throw fz.b(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 14[SWITCH]
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

    private static RuntimeException b(RuntimeException runtimeException) {
        return runtimeException;
    }
}

