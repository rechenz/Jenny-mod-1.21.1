/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockBanner
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockButton
 *  net.minecraft.block.BlockCarpet
 *  net.minecraft.block.BlockHorizontal
 *  net.minecraft.block.BlockLadder
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSign
 *  net.minecraft.block.BlockTorch
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityMultiPart
 *  net.minecraft.entity.MultiPartEntityPart
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.EntityWitherSkeleton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.network.play.server.SPacketEntityVelocity
 *  net.minecraft.network.play.server.SPacketParticles
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.BossInfo$Color
 *  net.minecraft.world.BossInfo$Overlay
 *  net.minecraft.world.BossInfoServer
 *  net.minecraft.world.DimensionType
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.event.entity.EntityMountEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingSpawnEvent$CheckSpawn
 *  net.minecraftforge.event.entity.player.PlayerWakeUpEvent
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.a3;
import com.trolmastercard.sexmod.ab;
import com.trolmastercard.sexmod.aq;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.b7;
import com.trolmastercard.sexmod.bb;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.bj;
import com.trolmastercard.sexmod.bk;
import com.trolmastercard.sexmod.bv;
import com.trolmastercard.sexmod.bz;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.c4;
import com.trolmastercard.sexmod.cc;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.cs;
import com.trolmastercard.sexmod.d;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.ez;
import com.trolmastercard.sexmod.f2;
import com.trolmastercard.sexmod.f7;
import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.fh;
import com.trolmastercard.sexmod.fl;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.fq;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.g0;
import com.trolmastercard.sexmod.gc;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gy;
import com.trolmastercard.sexmod.gz;
import com.trolmastercard.sexmod.h8;
import com.trolmastercard.sexmod.hf;
import com.trolmastercard.sexmod.hz;
import com.trolmastercard.sexmod.r;
import com.trolmastercard.sexmod.s;
import com.trolmastercard.sexmod.v;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBanner;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
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
public class f_
extends em
implements IEntityMultiPart,
b7 {
    public static final float a2 = 0.6f;
    public static final float b6 = 0.6f;
    public static final int bj = 10;
    public static final int an = 20;
    public static final float aU = 50.0f;
    public static final float ba = 40.0f;
    public static final int bM = 5;
    public static final int bs = 25;
    public static final float bJ = 30.0f;
    public static final float aA = 3.0f;
    public static final int a3 = 23;
    public static final int X = 45;
    public static final float ca = 0.3f;
    public static final float a8 = 9.0f;
    public static final float aX = 30.0f;
    public static final int bE = 24;
    public static final int aQ = 32;
    public static final int av = 5;
    public static final int bQ = 36;
    public static final int aR = 40;
    public static final int aB = 54;
    public static final int by = 10;
    public static final float b_ = 0.25f;
    public static final double ax = 3.0;
    public static final double bF = 1.0;
    public static final double bv = 1.5;
    public static final double az = (double)0.3f;
    public static final double ag = 40.0;
    public static final double au = 5.0;
    public static final double ae = 0.2;
    public static final double aV = 3.0;
    public static final double ar = (double)0.1f;
    public static final double ai = 6.0;
    public static final double ah = 50.0;
    public static final double bR = 39.0;
    public static final double bV = 58.0;
    public static final double aZ = 2.0;
    public static final double Q = 1.0;
    public static final float aJ = 0.5f;
    public static final f7 aa = new f7(0.83137256f, 0.6862745f, 0.21568628f);
    public static final Vec3d bz = new Vec3d((double)-1.049342f, 2.0547213554382324, -0.05048239231109619);
    public static final Vec3d bC = new Vec3d(1.2522261142730713, 1.435773253440857, 0.23570987582206726);
    public static final int aN = 10;
    public static final float ak = 0.2f;
    public static final int am = 5;
    public static final float T = 15.0f;
    public static final int aM = 48;
    public static final float be = 0.05f;
    public static final float a7 = 0.65f;
    public static final float bh = 0.9f;
    public static final float K = 45.0f;
    public static final float a0 = 1.0f;
    public static final float bn = 1.5f;
    public static final float ao = 110.0f;
    public static final int aj = 15;
    public static final float aw = 6.0f;
    public static final float bp = 0.94f;
    public static final int R = 13;
    public static final int bW = 40;
    public static final int bl = 25;
    public static final int aY = 38;
    public static final int N = 95;
    static final int bB = 10;
    static final int aI = 30;
    static final int bf = 175;
    static final float as = 2.0f;
    public static final float bo = 0.25f;
    public static final float Y = 1000.0f;
    public static final float bX = 15.0f;
    public static final float b9 = 5.0f;
    public static final int aW = 8000;
    public static final float aK = 0.1f;
    public static final float ac = 5.0f;
    public static final float b5 = -10.0f;
    public static final int bk = 16;
    public static final int br = 7;
    public static final int cb = 4;
    public static final float M = 0.5f;
    public static final float bi = 0.55f;
    static final Class<?>[] aS = new Class[]{BlockAir.class, BlockCarpet.class, BlockBush.class, BlockButton.class, BlockLadder.class, BlockTorch.class, BlockSign.class, BlockBanner.class};
    public static final DataParameter<Integer> bq = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(111);
    public static final DataParameter<Integer> aP = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(112);
    public static final DataParameter<Boolean> bN = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(113);
    public static final DataParameter<Boolean> b7 = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(114);
    public static final DataParameter<Boolean> ay = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(115);
    public static final DataParameter<Integer> bH = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(116);
    public static final DataParameter<String> b8 = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(117);
    public static final DataParameter<Boolean> bP = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(118);
    public static final DataParameter<Float> bO = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187193_c).func_187156_b().func_187161_a(119);
    public static final DataParameter<Boolean> L = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(120);
    public static final DataParameter<String> a4 = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(121);
    public static final DataParameter<Boolean> bT = EntityDataManager.func_187226_a(f_.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(122);
    public static final double b0 = 0.2;
    public static final float bS = 5.0f;
    public static final int a1 = 60;
    BossInfoServer aO = new BossInfoServer((ITextComponent)new TextComponentString(this.c()), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS);
    bb b2 = new bb(this, "energyBallHitBox", 0.75f, 0.75f);
    bb V = new bb(this, "energyBallHitBox", 0.75f, 0.75f);
    public h8 bZ = null;
    public Vec3d O = null;
    public Vec3d bL = null;
    public int aF = 0;
    public Vec3d bd = null;
    public List<EntityWitherSkeleton> bI = new ArrayList<EntityWitherSkeleton>();
    public float aE = 0.0f;
    public long af = -1L;
    public long aH = -1L;
    public float bw = 0.0f;
    public float bm = 0.0f;
    boolean bU = false;
    public Vec3d aG = null;
    boolean bA = false;
    Vec3d bD;
    Vec3d W;
    Vec3d Z;
    float al = 0.0f;
    boolean U = false;
    public int ad = 0;
    double a9 = 0.0;
    double bg = 0.0;
    double b4 = 0.0;
    double a_ = 0.0;
    boolean bK = false;
    Path aq = null;
    BlockPos bG = null;
    int aC = 0;
    fp ab = null;
    int at = 0;
    int bY = 0;
    int b3 = 0;
    long bc = 0L;
    boolean S = false;
    boolean P = false;
    int b1 = 0;
    boolean aT = false;
    public boolean bx = false;
    public boolean a5 = false;
    public boolean aD = false;
    public boolean bt = false;
    public boolean ap = false;
    public boolean bu = false;
    public boolean aL = true;
    public boolean bb = false;
    boolean a6 = false;

    public f_(World world) {
        super(world);
    }

    public f_(World world, @Nonnull EntityPlayer entityPlayer, Vec3d vec3d, boolean bl2) {
        this(world);
        UUID uUID = entityPlayer.getPersistentID();
        this.m.func_187227_b(v, (Object)uUID.toString());
        this.aO.func_186758_d(false);
        this.bG = new BlockPos(this.func_174791_d());
        String string = gy.a(uUID, fy.GALATH);
        try {
            if (string != null) {
                super.g(string);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (bl2) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.func_70681_au().nextFloat() > 0.1f) {
                this.b(fp.GALATH_SUMMON);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.b(fp.MASTERBATE);
        this.b(180.0f - (float)gc.b(Math.atan2(vec3d.field_72450_a - entityPlayer.field_70165_t, vec3d.field_72449_c - entityPlayer.field_70161_v)));
        com.trolmastercard.sexmod.be.a(8000, () -> {
            EntityPlayer entityPlayer = this.z();
            try {
                if (entityPlayer == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            try {
                if (entityPlayer.field_70128_L) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            this.c(entityPlayer.func_174791_d());
            this.b(entityPlayer.field_70177_z + 180.0f);
            this.b(fp.RAPE_INTRO);
            this.e(entityPlayer.getPersistentID());
            this.a(true);
        });
    }

    public f_(World world, @Nonnull EntityPlayer entityPlayer, Vec3d vec3d) {
        this(world, entityPlayer, vec3d, false);
    }

    @Override
    public void f(String string) {
        super.f(string);
        com.trolmastercard.sexmod.bj.a(this);
    }

    @Override
    public String c() {
        return "Galath";
    }

    @Override
    public float i() {
        float f10;
        try {
            f10 = this.aF() == null ? 0.5f : 1.35f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return f10;
    }

    public float func_70047_e() {
        return 1.9f;
    }

    public boolean k() {
        return this.J();
    }

    public boolean func_96092_aw() {
        return false;
    }

    protected void func_70629_bd() {
        try {
            if (this.k()) {
                super.func_70629_bd();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    protected float func_189749_co() {
        try {
            if (this.k()) {
                return super.func_189749_co();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return 0.0f;
    }

    public boolean func_70090_H() {
        try {
            if (this.k()) {
                return super.func_70090_H();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return false;
    }

    public boolean func_70072_I() {
        try {
            if (this.k()) {
                return super.func_70072_I();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return false;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.m.func_187214_a(bq, (Object)-1);
        this.m.func_187214_a(aP, (Object)0);
        this.m.func_187214_a(bN, (Object)true);
        this.m.func_187214_a(b7, (Object)true);
        this.m.func_187214_a(ay, (Object)false);
        this.m.func_187214_a(b8, (Object)"null");
        this.m.func_187214_a(bH, (Object)-1);
        this.m.func_187214_a(bP, (Object)false);
        this.m.func_187214_a(bO, (Object)Float.valueOf(0.0f));
        this.m.func_187214_a(L, (Object)false);
        this.m.func_187214_a(a4, (Object)"");
        this.m.func_187214_a(bT, (Object)false);
    }

    @Override
    protected void func_110147_ax() {
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111267_a);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111266_c);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111263_d);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_188791_g);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_189429_h);
        this.func_110140_aT().func_111150_b(SWIM_SPEED);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_193334_e);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(110.0);
        this.func_110148_a(SharedMonsterAttributes.field_193334_e).func_111128_a((double)0.6f);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.6f);
    }

    @Override
    protected void func_184651_r() {
        this.o = new df((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.4, false, new HashSet(I)));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new hz((EntityLiving)this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.o);
    }

    public void func_184178_b(EntityPlayerMP entityPlayerMP) {
        super.func_184178_b(entityPlayerMP);
        this.aO.func_186760_a(entityPlayerMP);
    }

    public void func_184203_c(EntityPlayerMP entityPlayerMP) {
        super.func_184203_c(entityPlayerMP);
        this.aO.func_186761_b(entityPlayerMP);
    }

    @Override
    public Vec3d o() {
        block4: {
            try {
                try {
                    if (!this.field_70170_p.field_72995_K || this.aG == null) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return this.aG;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        return super.o();
    }

    @Nullable
    public UUID aF() {
        String string = (String)this.m.func_187225_a(a4);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (Exception exception) {
            throw f_.a(exception);
        }
        try {
            return UUID.fromString(string);
        }
        catch (Exception exception) {
            return null;
        }
    }

    @Nullable
    public f8 a(boolean bl2) {
        em em2;
        UUID uUID = this.aF();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            em2 = bl2 ? f_.a(uUID) : f_.b(uUID);
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        em em3 = em2;
        try {
            if (em3 instanceof f8) {
                return (f8)em3;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return null;
    }

    @Nullable
    public static f8 a(em em2, boolean bl2) {
        try {
            if (!(em2 instanceof f_)) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return ((f_)em2).a(bl2);
    }

    public void a(@Nullable UUID uUID) {
        String string;
        DataParameter<String> dataParameter;
        EntityDataManager entityDataManager;
        try {
            entityDataManager = this.m;
            dataParameter = a4;
            string = uUID == null ? "" : uUID.toString();
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        entityDataManager.func_187227_b(dataParameter, (Object)string);
    }

    public void aC() {
        this.bA = true;
        f8 f82 = this.a(true);
        try {
            if (f82 != null) {
                f82.q();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    public void w() {
        fp fp2 = this.y();
        try {
            if (fp2 != fp.RAPE_ON_GOING) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.bZ = h8.CHANGE_POSITION;
        this.bZ.b(this);
        this.a(false);
        this.b(fp.FLY);
        EntityPlayer entityPlayer = this.S();
        try {
            this.e((UUID)null);
            if (entityPlayer != null) {
                ge.b.sendTo((IMessage)new gz(true), (EntityPlayerMP)entityPlayer);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        em.a((em)this, com.trolmastercard.sexmod.c.GIRLS_GALATH_DIALOG[0]);
    }

    public Vec3d B() {
        String[] stringArray = ((String)this.m.func_187225_a(b8)).split("\\|");
        return new Vec3d(Double.parseDouble(stringArray[0]), Double.parseDouble(stringArray[1]), Double.parseDouble(stringArray[2]));
    }

    public void e(@Nullable Vec3d vec3d) {
        this.m.func_187227_b(b8, (Object)(vec3d.field_72450_a + "|" + vec3d.field_72448_b + "|" + vec3d.field_72449_c));
    }

    public int az() {
        return (Integer)this.m.func_187225_a(bH);
    }

    public void a(int n2) {
        this.m.func_187227_b(bH, (Object)n2);
    }

    public boolean func_184222_aU() {
        return false;
    }

    @Override
    public boolean b() {
        try {
            switch (this.y()) {
                default: {
                    return false;
                }
                case HUG_MANG: 
                case MORNING_BLOWJOB_SLOW: 
                case MORNING_BLOWJOB_FAST: 
                case MORNING_BLOWJOB_CUM: {
                    return true;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    void aa() {
        this.Z = new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.bD = this.func_174791_d();
        this.W = this.func_174791_d().func_178787_e(this.Z);
        this.Z = this.Z.func_186678_a(0.9);
    }

    @Override
    public void func_70071_h_() {
        block10: {
            block9: {
                boolean bl2;
                block8: {
                    block7: {
                        bl2 = this.k();
                        try {
                            if (!bl2) break block7;
                            this.E();
                            break block8;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                    }
                    this.c();
                }
                try {
                    this.aa();
                    super.func_70071_h_();
                    if (!bl2) break block9;
                    this.au();
                    break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            this.R();
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                this.X();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    @Override
    public boolean B() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    void X() {
        block11: {
            try {
                if (this.y() != fp.GIVE_COIN) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            int n2 = fp.GIVE_COIN.ticksPlaying[1];
            try {
                if (n2 == 95) {
                    cc.a((EntityPlayer)Minecraft.func_71410_x().field_71439_g, this);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            try {
                try {
                    if (n2 > 25 && n2 < 38) break block11;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        Vec3d vec3d = this.func_174791_d();
        Vec3d vec3d2 = this.b("weapon").func_178787_e(vec3d);
        Vec3d vec3d3 = this.b("offhand").func_178787_e(vec3d);
        ez.b = 0.5f;
        for (float f10 = 0.0f; f10 < 1.0f; f10 += 0.2f) {
            Vec3d vec3d4 = com.trolmastercard.sexmod.b6.a(vec3d2, vec3d3, (double)f10);
            Minecraft.func_71410_x().field_71452_i.func_78873_a((Particle)new ez(this.field_70170_p, vec3d4.field_72450_a, vec3d4.field_72448_b, vec3d4.field_72449_c));
        }
    }

    void E() {
        boolean bl2;
        f_ f_2;
        try {
            f_2 = this;
            bl2 = this.ab() != null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        f_2.func_189654_d(bl2);
    }

    void au() {
        block11: {
            try {
                try {
                    try {
                        try {
                            if (this.func_70090_H() || this.func_189652_ae()) break block11;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                        if (!(this.field_70181_x < 0.0)) break block11;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    if (this.y() == fp.MASTERBATE) break block11;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                this.field_70181_x *= (double)0.4f;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        try {
            this.aB();
            this.aj();
            this.aq();
            this.aw();
            this.C();
            this.Y();
            this.o();
            if (this.M() == null) {
                this.ap = false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    void o() {
        try {
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.y() != fp.RAPE_CUM) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (fp.RAPE_CUM.ticksPlaying[0] < 28) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.a(false);
        this.b(fp.NULL);
        EntityPlayer entityPlayer = this.S();
        try {
            this.e((UUID)null);
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        entityPlayer.func_70634_a(entityPlayer.field_70165_t, Math.ceil(entityPlayer.field_70163_u) + 1.0, entityPlayer.field_70161_v);
        ge.b.sendTo((IMessage)new gz(true), (EntityPlayerMP)entityPlayer);
    }

    void Y() {
        try {
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.y() != fp.CORRUPT_CUM) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (fp.CORRUPT_CUM.ticksPlaying[0] < 30) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.a(false);
        this.b(fp.NULL);
        EntityPlayer entityPlayer = this.S();
        try {
            this.e((UUID)null);
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        entityPlayer.func_70634_a(entityPlayer.field_70165_t, Math.ceil(entityPlayer.field_70163_u) + 1.0, entityPlayer.field_70161_v);
        ge.b.sendTo((IMessage)new gz(true), (EntityPlayerMP)entityPlayer);
    }

    static boolean a(BlockPos blockPos, World world) {
        for (BlockPos object : fq.c) {
            try {
                if (!(Math.sqrt(blockPos.func_177951_i((Vec3i)object)) < 1000.0)) continue;
                return false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        try {
            for (em em2 : em.ad()) {
                try {
                    if (em2.field_70170_p.field_72995_K) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    if (!(em2 instanceof f_)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    if (em2.field_70128_L) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                if (!(em2.func_174818_b(blockPos) < 1000000.0)) continue;
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        int n2 = blockPos.func_177956_o();
        while (true) {
            block25: {
                try {
                    try {
                        if (!((float)n2 < 15.0f + (float)blockPos.func_177956_o())) break;
                        if (world.func_180495_p(new BlockPos(blockPos.func_177958_n(), n2, blockPos.func_177952_p())).func_177230_c() == Blocks.field_150350_a) break block25;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    return false;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            ++n2;
        }
        n2 = blockPos.func_177956_o();
        while (true) {
            block26: {
                try {
                    try {
                        if (!((float)n2 > (float)blockPos.func_177956_o() - 5.0f)) break;
                        if (!(world.func_180495_p(new BlockPos(blockPos.func_177958_n(), n2, blockPos.func_177952_p())).func_177230_c() instanceof BlockLiquid)) break block26;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    return false;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            --n2;
        }
        return true;
    }

    void aw() {
        int n2;
        EntityPlayer entityPlayer = this.ab();
        fp fp2 = this.y();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (fp2 != fp.BOOST) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            n2 = g0.a() ? 0 : 1;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        int n3 = n2;
        try {
            if (fp2.ticksPlaying[n3] < 13) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (fp2.ticksPlaying[n3] == 13) {
                this.al = 6.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        Vec3d vec3d = entityPlayer.func_70676_i(0.0f).func_72432_b();
        this.field_70159_w = vec3d.field_72450_a * (double)this.al;
        this.field_70181_x = vec3d.field_72448_b * (double)this.al;
        this.field_70179_y = vec3d.field_72449_c * (double)this.al;
        this.al *= 0.94f;
    }

    void c() {
        this.n();
        this.j();
        this.ah();
    }

    void R() {
        try {
            f_.a(this, 0.0f);
            this.h();
            this.aj();
            this.af();
            this.L();
            this.F();
            this.C();
            this.u();
            if (this.field_70170_p.field_72995_K) {
                this.H();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    void u() {
        try {
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.y() != fp.CORRUPT_CUM) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (fp.CORRUPT_CUM.ticksPlaying[0] >= 30) {
                this.b(fp.GIVE_COIN);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    /*
     * Exception decompiling
     */
    void C() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 2[SWITCH]
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
    public boolean m() {
        try {
            if (this.y() != fp.CORRUPT_INTRO) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return this.U;
    }

    void F() {
        try {
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.y() == fp.KNOCK_OUT_STAND_UP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.aL = true;
    }

    void j() {
        this.aO.func_186735_a(this.func_110143_aJ() / this.func_110138_aP());
    }

    void n() {
        boolean bl2;
        f_ f_2;
        try {
            if (((Boolean)this.m.func_187225_a(bP)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            f_2 = this;
            bl2 = this.M() != null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        f_2.func_189654_d(bl2);
    }

    void L() {
        try {
            if (this.y() != fp.ATTACK_SWORD) {
                this.ap = false;
                this.bu = false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    protected void func_85033_bc() {
    }

    public void func_70690_d(PotionEffect potionEffect) {
    }

    void af() {
        try {
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (!this.bu) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        Vec3d vec3d = this.func_174791_d();
        Vec3d vec3d2 = this.b("weaponStart").func_178787_e(vec3d);
        Vec3d vec3d3 = this.b("weaponEnd").func_178787_e(vec3d);
        float f10 = 0.1f;
        Random random = this.func_70681_au();
        block22: for (float f11 = 0.0f; f11 < 1.0f; f11 += f10) {
            Vec3d vec3d4 = com.trolmastercard.sexmod.b6.a(vec3d2, vec3d3, (double)f11);
            int n2 = 0;
            while (true) {
                int n3;
                double d10;
                double d11;
                double d12;
                int n4;
                double d13;
                double d14;
                double d15;
                int n5;
                double d16;
                double d17;
                EnumParticleTypes enumParticleTypes;
                World world;
                block28: {
                    block27: {
                        try {
                            try {
                                if (n2 >= 3) continue block22;
                                world = this.field_70170_p;
                                enumParticleTypes = EnumParticleTypes.DRAGON_BREATH;
                                d17 = vec3d4.field_72450_a;
                                d16 = random.nextDouble() * 0.25;
                                if (!random.nextBoolean()) break block27;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                            n5 = 1;
                            break block28;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                    }
                    n5 = -1;
                }
                try {
                    d15 = d17 + d16 * (double)n5;
                    d14 = vec3d4.field_72448_b;
                    d13 = random.nextDouble() * 0.25;
                    n4 = random.nextBoolean() ? 1 : -1;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    d12 = d14 + d13 * (double)n4;
                    d11 = vec3d4.field_72449_c;
                    d10 = random.nextDouble() * 0.25;
                    n3 = random.nextBoolean() ? 1 : -1;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                world.func_175688_a(enumParticleTypes, d15, d12, d11 + d10 * (double)n3, 0.0, 0.0, 0.0, new int[0]);
                ++n2;
            }
        }
        int n6 = 0;
        while (true) {
            int n7;
            double d18;
            double d19;
            double d20;
            int n8;
            double d21;
            double d22;
            double d23;
            int n9;
            double d24;
            int n10;
            double d25;
            double d26;
            EnumParticleTypes enumParticleTypes;
            World world;
            block30: {
                block29: {
                    try {
                        try {
                            if (n6 >= 3) break;
                            world = this.field_70170_p;
                            enumParticleTypes = EnumParticleTypes.DRAGON_BREATH;
                            d26 = vec3d3.field_72450_a;
                            d25 = random.nextDouble() * 0.25;
                            if (!random.nextBoolean()) break block29;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                        n10 = 1;
                        break block30;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                }
                n10 = -1;
            }
            try {
                d24 = d25 * (double)n10;
                n9 = random.nextBoolean() ? 1 : -1;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            try {
                d23 = d26 + d24 * (double)n9;
                d22 = vec3d3.field_72448_b;
                d21 = random.nextDouble() * 0.25;
                n8 = random.nextBoolean() ? 1 : -1;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            try {
                d20 = d22 + d21 * (double)n8;
                d19 = vec3d3.field_72449_c;
                d18 = random.nextDouble() * 0.25;
                n7 = random.nextBoolean() ? 1 : -1;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            world.func_175688_a(enumParticleTypes, d23, d20, d19 + d18 * (double)n7, 0.0, 0.0, 0.0, new int[0]);
            ++n6;
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ag() {
        try {
            if (this.y() == fp.GALATH_DE_SUMMON) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.C.tickOffset = 0.0;
    }

    @Override
    public String ab() {
        EntityPlayer entityPlayer = this.z();
        try {
            if (entityPlayer == null) {
                return super.ab();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return String.format("%s %s[%s]", super.ab(), TextFormatting.DARK_PURPLE, entityPlayer.func_70005_c_());
    }

    void h() {
        Vec3d vec3d;
        Vec3d vec3d2;
        Vec3d vec3d3;
        Vec3d vec3d4;
        try {
            this.b2.a = false;
            this.V.a = false;
            if ((float)this.ad < 9.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if ((float)this.ad > 30.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.b2.a = true;
        this.V.a = true;
        boolean bl2 = (Boolean)this.m.func_187225_a(ay);
        try {
            vec3d4 = this.func_174791_d();
            vec3d3 = bl2 ? ck.c(bz) : bz;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        Vec3d vec3d5 = vec3d4.func_178787_e(ck.a(vec3d3, 180.0f + this.field_70761_aq));
        try {
            vec3d2 = this.func_174791_d();
            vec3d = bl2 ? ck.c(bC) : bC;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        Vec3d vec3d6 = vec3d2.func_178787_e(ck.a(vec3d, 180.0f + this.field_70761_aq));
        this.b2.func_70012_b(vec3d5.field_72450_a, vec3d5.field_72448_b, vec3d5.field_72449_c, this.field_70761_aq, 0.0f);
        this.V.func_70012_b(vec3d6.field_72450_a, vec3d6.field_72448_b, vec3d6.field_72449_c, this.field_70761_aq, 0.0f);
        this.b2.func_70071_h_();
        this.V.func_70071_h_();
    }

    void ah() {
        try {
            if (this.y() != fp.SUMMON_SKELETON) {
                this.ad = 0;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.ad++ > 45) {
                this.ad = 0;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    @Override
    public f2 d() {
        return new f2(this.a9, this.bg, this.b4, this.a_);
    }

    void aj() {
        this.b4 = this.a9;
        this.a_ = this.bg;
        Vec3d vec3d = this.W.func_178788_d(this.bD);
        Vec3d vec3d2 = ck.a(vec3d, this.field_70761_aq + 180.0f);
        this.a9 = gc.c(com.trolmastercard.sexmod.be.b(vec3d2.field_72449_c * 40.0, -50.0, 50.0));
        this.bg = gc.c(com.trolmastercard.sexmod.be.b(vec3d2.field_72450_a * 40.0, -50.0, 50.0));
    }

    public void f(Vec3d vec3d) {
        Vec3d vec3d2;
        try {
            if (((Boolean)this.m.func_187225_a(bP)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            this.m.func_187227_b(bP, (Object)true);
            if (this.bZ != null) {
                this.bZ.e(this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.bZ = null;
        Vec3d vec3d3 = this.func_174791_d();
        Random random = this.func_70681_au();
        try {
            vec3d2 = vec3d == null ? new Vec3d(random.nextDouble(), random.nextDouble(), random.nextDouble()).func_72432_b() : vec3d3.func_178788_d(vec3d).func_72432_b();
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        Vec3d vec3d4 = vec3d2;
        this.func_70016_h(vec3d4.field_72450_a * 1.0, 1.0, vec3d4.field_72449_c * 1.0);
        this.b(fp.KNOCK_OUT_FLY);
        this.func_189654_d(false);
        this.field_70145_X = false;
        this.func_70661_as().func_75499_g();
        f_.a((em)this, com.trolmastercard.sexmod.c.GIRLS_GALATH_AAA, true);
    }

    void a(Entity entity) {
        em.a((em)this, TextFormatting.YELLOW + "Galath is paralyzed! Now it's time to corrupt her");
        em.a((em)this, TextFormatting.GRAY + "(Walk to her and right click her)");
        ge.b.sendToAllTracking((IMessage)new bv(this.func_174791_d(), true), (Entity)this);
        this.f((Vec3d)null);
        this.m.func_187227_b(L, (Object)true);
    }

    @Override
    public void func_70619_bc() {
        block6: {
            block5: {
                try {
                    if (this.P) {
                        com.trolmastercard.sexmod.v.a(this);
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    this.P();
                    super.func_70619_bc();
                    this.o.a = this.x();
                    if (!this.k()) break block5;
                    this.ae();
                    break block6;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            this.an();
        }
    }

    void P() {
        try {
            if (this.bK) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.f(com.trolmastercard.sexmod.bj.c(this));
        this.bK = true;
    }

    boolean x() {
        try {
            if (this.y() != fp.NULL) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (Math.abs(this.field_70159_w) + Math.abs(this.field_70179_y) > 0.01) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return true;
    }

    void aq() {
        try {
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.ab() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.z();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.d(entityPlayer);
    }

    void d(EntityPlayer entityPlayer) {
        float f10;
        double d10;
        double d11;
        Vec3d vec3d;
        Vec3d vec3d2;
        ei ei2 = ei.d(entityPlayer.getPersistentID());
        try {
            Vec3d vec3d3;
            vec3d2 = vec3d3;
            vec3d = vec3d3;
            d11 = entityPlayer.field_70165_t;
            d10 = entityPlayer.field_70163_u;
            f10 = ei2 == null ? entityPlayer.eyeHeight : ei2.func_70047_e();
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        vec3d2(d11, d10 + (double)f10, entityPlayer.field_70161_v);
        Vec3d vec3d4 = vec3d;
        Vec3d vec3d5 = new Vec3d(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
        double d12 = vec3d5.func_72438_d(vec3d4);
        double d13 = vec3d4.field_72448_b - vec3d5.field_72448_b;
        this.field_70125_A = (float)(-(Math.sin(d13 / d12) * 57.29577951308232));
    }

    void ae() {
        block9: {
            block8: {
                try {
                    this.aO.func_186758_d(false);
                    if (!com.trolmastercard.sexmod.v.c(this)) {
                        com.trolmastercard.sexmod.v.a(this);
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    if (this.ab() != null) {
                        this.y();
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    this.m();
                    if (this.aF() != null) break block8;
                    this.aJ();
                    break block9;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            this.am();
        }
    }

    void m() {
        try {
            if (!com.trolmastercard.sexmod.v.b(com.trolmastercard.sexmod.v.b(this))) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        boolean bl2 = this.v();
        try {
            if (bl2) {
                Main.LOGGER.warn("mommy thinks she got no daughter but she actually does have one. Failsafe called. Hopefully its fixed");
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    void am() {
        try {
            if (this.ai()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.m.func_187227_b(bT, (Object)false);
        this.ao();
    }

    boolean ai() {
        double d10;
        boolean bl2;
        PathNavigate pathNavigate;
        float f10;
        block40: {
            block39: {
                EntityPlayer entityPlayer;
                block38: {
                    block35: {
                        block37: {
                            BlockPos blockPos;
                            block36: {
                                UUID uUID = com.trolmastercard.sexmod.v.b(this);
                                try {
                                    if (uUID == null) {
                                        return false;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                entityPlayer = this.field_70170_p.func_152378_a(uUID);
                                try {
                                    if (entityPlayer == null) {
                                        return false;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                blockPos = entityPlayer.func_180425_c();
                                try {
                                    if (!this.a(blockPos)) {
                                        return false;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                try {
                                    if (this.bZ != null) {
                                        this.bZ.e(this);
                                        this.bZ = null;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                f10 = this.func_70032_d((Entity)entityPlayer);
                                pathNavigate = this.func_70661_as();
                                try {
                                    if (f10 < 4.0f) {
                                        pathNavigate.func_75499_g();
                                        return false;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                try {
                                    if (f10 > 16.0f) {
                                        pathNavigate.func_75499_g();
                                        this.b(entityPlayer);
                                        return true;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                try {
                                    try {
                                        if (!(fl.a(this.aq).func_177951_i((Vec3i)blockPos) > 16.0)) break block35;
                                        if (this.field_70122_E) break block36;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                    return true;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                            }
                            try {
                                this.aq = this.a(entityPlayer, blockPos);
                                if (this.aq != null) break block37;
                                this.b(entityPlayer);
                                break block35;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                        }
                        pathNavigate.func_75484_a(this.aq, 1.0);
                    }
                    try {
                        try {
                            if (this.aq != null && !this.aq.func_75879_b()) break block38;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                        return false;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                }
                try {
                    try {
                        if (!entityPlayer.func_70051_ag() && !(this.func_70032_d((Entity)entityPlayer) > 7.0f)) break block39;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    bl2 = true;
                    break block40;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            bl2 = false;
        }
        boolean bl3 = bl2;
        try {
            d10 = bl3 ? (double)0.55f : 0.5;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        double d11 = d10;
        double d12 = Math.floor(f10 / 5.0f) * 0.2;
        d11 += d12;
        if (this.func_70090_H()) {
            d11 *= 60.0;
        }
        pathNavigate.func_75489_a(d11);
        this.m.func_187227_b(bT, (Object)bl3);
        this.b((fp)null);
        return true;
    }

    boolean a(BlockPos blockPos) {
        boolean bl2;
        try {
            if (this.bZ == null) {
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        BlockPos blockPos2 = this.func_180425_c();
        int n2 = Math.abs(blockPos.func_177958_n() - blockPos2.func_177958_n()) + Math.abs(blockPos.func_177958_n() - blockPos2.func_177958_n());
        try {
            bl2 = n2 > 16;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return bl2;
    }

    protected void b(EntityPlayer entityPlayer) {
        BlockPos blockPos;
        int n2 = 0;
        do {
            blockPos = entityPlayer.func_180425_c().func_177982_a(com.trolmastercard.sexmod.r.f.nextInt(4), 0, com.trolmastercard.sexmod.r.f.nextInt(4));
        } while (++n2 < 20 && !this.func_184595_k(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p()));
        try {
            if (n2 >= 20) {
                this.func_70107_b(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
    }

    @Nullable
    Path a(EntityPlayer entityPlayer, BlockPos blockPos) {
        PathNavigate pathNavigate = this.func_70661_as();
        return pathNavigate.func_75494_a((Entity)entityPlayer);
    }

    void aJ() {
        this.at();
        this.ay();
    }

    void y() {
        try {
            this.bG = null;
            this.aC = 0;
            if (this.bZ != null) {
                this.bZ.e(this);
                this.bZ = null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    void at() {
        f8 f822;
        f8 f83;
        block26: {
            block27: {
                try {
                    if (!this.field_70122_E) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    if (this.aF() != null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    if (this.y() == fp.HUG_MANG) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                try {
                    if (com.trolmastercard.sexmod.v.b(com.trolmastercard.sexmod.v.f(this.f()))) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                BlockPos blockPos = this.func_180425_c();
                BlockPos blockPos2 = blockPos.func_177963_a(-15.0, -15.0, -15.0);
                BlockPos blockPos3 = blockPos.func_177963_a(15.0, 15.0, 15.0);
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos2, blockPos3);
                List list = this.field_70170_p.func_72872_a(f8.class, axisAlignedBB);
                f83 = null;
                for (f8 f822 : list) {
                    try {
                        if (f822.field_70128_L) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    try {
                        if (f822.a(true) != null) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    f83 = f822;
                    break;
                }
                try {
                    try {
                        if (f83 != null) break block26;
                        if (this.y() != fp.RUN) break block27;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    this.b((fp)null);
                    this.func_70661_as().func_75499_g();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            return;
        }
        try {
            this.f = this.func_70661_as();
            if (f83.func_70032_d((Entity)this) <= 3.65f) {
                this.f.func_75499_g();
                this.b(fp.HUG_MANG);
                this.field_70159_w = 0.0;
                this.field_70181_x = 0.0;
                this.field_70179_y = 0.0;
                this.c(this.func_174791_d());
                this.a(true);
                this.a(f83.f());
                f83.a(this.f());
                f83.b(fp.RIDE_MOMMY_HEAD);
                com.trolmastercard.sexmod.v.e(this.f());
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        Vec3d vec3d = this.func_174791_d();
        f822 = f83.func_174791_d();
        Vec3d vec3d2 = f822.func_178788_d(vec3d);
        float f10 = (float)gc.b(Math.atan2(vec3d2.field_72449_c, vec3d2.field_72450_a)) - 90.0f;
        this.b(f10);
        this.f.func_75499_g();
        this.f.func_75497_a((Entity)f83, (double)0.65f);
        this.b(fp.RUN);
    }

    void ay() {
        block39: {
            block38: {
                block35: {
                    int n2;
                    int n3;
                    int n4;
                    block37: {
                        block36: {
                            block33: {
                                block32: {
                                    fp fp2 = this.y();
                                    try {
                                        if (fp2 == fp.RUN) {
                                            return;
                                        }
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                    try {
                                        if (fp2 == fp.HUG_MANG) {
                                            return;
                                        }
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                    try {
                                        try {
                                            if (!this.Q() && fp2 != fp.MASTERBATE) break block32;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw f_.a(concurrentModificationException);
                                        }
                                        this.func_70661_as().func_75499_g();
                                        return;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                }
                                EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 15.0);
                                try {
                                    try {
                                        try {
                                            try {
                                                if (!this.J() || entityPlayer == null) break block33;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw f_.a(concurrentModificationException);
                                            }
                                            if (!(entityPlayer.func_70032_d((Entity)this) < 2.0f)) break block33;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw f_.a(concurrentModificationException);
                                        }
                                        if (!entityPlayer.getPersistentID().equals(this.O())) break block33;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                    this.func_70661_as().func_75499_g();
                                    return;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    block34: {
                                        try {
                                            try {
                                                if (this.bG == null || this.func_70011_f(this.bG.func_177958_n(), this.bG.func_177956_o(), this.bG.func_177952_p()) > this.i()) break block34;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw f_.a(concurrentModificationException);
                                            }
                                            if (this.aC <= 175) break block35;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw f_.a(concurrentModificationException);
                                        }
                                    }
                                    if (!this.func_70681_au().nextBoolean()) break block36;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                n4 = 1;
                                break block37;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                        }
                        n4 = -1;
                    }
                    int n5 = n4 * this.func_70681_au().nextInt(10);
                    try {
                        n3 = this.func_70681_au().nextBoolean() ? 1 : -1;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    int n6 = n3 * this.func_70681_au().nextInt(10);
                    try {
                        n2 = this.field_70170_p.field_73011_w.func_186058_p() == DimensionType.NETHER ? (int)Math.ceil(this.field_70163_u) : cj.a(this.field_70170_p, this.func_180425_c().func_177958_n() + n5, this.func_180425_c().func_177952_p() + n6);
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    int n7 = n2;
                    this.bG = new BlockPos(this.func_180425_c().func_177958_n() + n5, n7, this.func_180425_c().func_177952_p() + n6);
                    this.aC = 0;
                }
                try {
                    if (!(Math.sqrt(this.bG.func_177951_i((Vec3i)this.func_180425_c())) > 2.0)) break block38;
                    this.func_70661_as().func_75492_a((double)this.bG.func_177958_n(), (double)this.bG.func_177956_o(), (double)this.bG.func_177952_p(), (double)0.35f);
                    this.k();
                    break block39;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            ++this.aC;
        }
    }

    BlockPos av() {
        UUID uUID = com.trolmastercard.sexmod.v.b(this);
        try {
            if (uUID == null) {
                return BlockPos.field_177992_a;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return BlockPos.field_177992_a;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return entityPlayer.func_180425_c();
    }

    double i() {
        return Math.sqrt(1800.0);
    }

    @Nullable
    public EntityPlayer ab() {
        List list = this.func_184188_bt();
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (list.get(0) instanceof EntityPlayer) {
                return (EntityPlayer)list.get(0);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return null;
    }

    @Nullable
    public UUID ax() {
        EntityPlayer entityPlayer = this.ab();
        try {
            if (entityPlayer == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return entityPlayer.getPersistentID();
    }

    @Override
    public void g(String string) {
        super.g(string);
        UUID uUID = this.O();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        gy.a(uUID, fy.GALATH, string);
    }

    public void d(Vec3d vec3d) {
        this.field_70159_w += vec3d.field_72450_a;
        this.field_70179_y += vec3d.field_72449_c;
        this.field_70181_x = vec3d.field_72448_b / 2.0;
    }

    public void t() {
        this.e((UUID)null);
        this.b((fp)null);
    }

    void aB() {
        EntityPlayer entityPlayer = this.ab();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.field_70760_ar = entityPlayer.field_70758_at;
        this.field_70761_aq = entityPlayer.field_70759_as;
    }

    void an() {
        this.aO.func_186758_d(true);
        this.ao();
        this.as();
    }

    void ao() {
        try {
            if (fp.a((em)this, fp.MASTERBATE, fp.HUG_MANG)) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.ae() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.Q();
        this.I();
        this.D();
        this.q();
        this.J();
        this.T();
        this.S();
        this.b();
        this.ad();
        this.aG();
        this.aA();
        this.aD();
        this.O();
        this.Z();
    }

    void Q() {
        try {
            if (!this.k()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.M() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        int n2 = (Integer)this.m.func_187225_a(bq);
        try {
            if (n2 == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.bZ != null) {
                this.bZ.e(this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.bZ = null;
        this.b(fp.NULL);
    }

    void as() {
        try {
            if (this.M() != null) {
                this.bG = null;
                this.aC = 0;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (((Boolean)this.m.func_187225_a(L)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (((Boolean)this.m.func_187225_a(bP)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.ay();
    }

    @Override
    public void b(fp fp2) {
        block55: {
            fp fp3;
            block54: {
                block53: {
                    block51: {
                        block52: {
                            block50: {
                                block48: {
                                    block47: {
                                        block45: {
                                            fp3 = this.y();
                                            try {
                                                if (fp3 == fp.GALATH_DE_SUMMON) {
                                                    return;
                                                }
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw f_.a(concurrentModificationException);
                                            }
                                            try {
                                                block46: {
                                                    try {
                                                        try {
                                                            if (fp3 != fp.CORRUPT_CUM) break block45;
                                                            if (fp2 == fp.CORRUPT_FAST) break block46;
                                                        }
                                                        catch (ConcurrentModificationException concurrentModificationException) {
                                                            throw f_.a(concurrentModificationException);
                                                        }
                                                        if (fp2 != fp.CORRUPT_SLOW) break block45;
                                                    }
                                                    catch (ConcurrentModificationException concurrentModificationException) {
                                                        throw f_.a(concurrentModificationException);
                                                    }
                                                }
                                                return;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw f_.a(concurrentModificationException);
                                            }
                                        }
                                        try {
                                            try {
                                                if (fp3 != fp.RAPE_CUM || fp2 != fp.RAPE_ON_GOING) break block47;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw f_.a(concurrentModificationException);
                                            }
                                            return;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw f_.a(concurrentModificationException);
                                        }
                                    }
                                    try {
                                        block49: {
                                            try {
                                                try {
                                                    if (fp3 != fp.MORNING_BLOWJOB_CUM) break block48;
                                                    if (fp2 == fp.MORNING_BLOWJOB_SLOW) break block49;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw f_.a(concurrentModificationException);
                                                }
                                                if (fp2 != fp.MORNING_BLOWJOB_FAST) break block48;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw f_.a(concurrentModificationException);
                                            }
                                        }
                                        return;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                }
                                try {
                                    try {
                                        if (this.field_70170_p.field_72995_K || !fp.a(fp3, fp.CORRUPT_CUM, fp.RAPE_CUM, fp.MORNING_BLOWJOB_CUM)) break block50;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                    com.trolmastercard.sexmod.v.a(this.ae(), this.field_70170_p.func_82737_E());
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    if (fp2 != fp.CORRUPT_SLOW) break block51;
                                    this.aT = false;
                                    if (fp3 != fp.CORRUPT_INTRO) break block52;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                this.d(false);
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                        }
                        try {
                            try {
                                if (!this.k() || fp3 != fp.NULL) break block51;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                            this.d(true);
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (fp3 != fp.GIVE_COIN || fp2 != fp.NULL) break block53;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                            if (this.field_70170_p.field_72995_K) break block53;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                        this.ap();
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                }
                try {
                    try {
                        if (fp3 != fp.HUG_MANG || fp2 != fp.NULL) break block54;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    this.al();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            try {
                try {
                    if (fp3 != fp.MORNING_BLOWJOB_CUM || fp2 != fp.NULL) break block55;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                this.aE();
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        super.b(fp2);
    }

    void aE() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer != null) {
                s.a.a((EntityPlayerMP)entityPlayer);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        s.a.a(this);
    }

    void al() {
        this.a(false);
        f8 f82 = this.a(true);
        try {
            if (f82 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        f82.c(true);
    }

    void ap() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        ItemStack itemStack = entityPlayer.func_184614_ca();
        try {
            entityPlayer.func_184611_a(EnumHand.MAIN_HAND, new ItemStack((Item)cc.r));
            if (!itemStack.func_190926_b()) {
                entityPlayer.field_71071_by.func_70441_a(itemStack);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        ge.b.sendTo((IMessage)new gz(true), (EntityPlayerMP)entityPlayer);
        this.e((UUID)null);
        this.a((EntityLivingBase)null);
        entityPlayer.func_145747_a((ITextComponent)new TextComponentString(TextFormatting.GRAY + "Defeating a succubus makes her accept the victor as her master, granting him a coin to which her soul is bound. Using the coin summons her, offering services on demand. If her master uses the coin on her or goes too far, she returns to the coin"));
        com.trolmastercard.sexmod.v.a(this);
        entityPlayer.func_70634_a(entityPlayer.field_70165_t, Math.ceil(entityPlayer.field_70163_u) + 1.0, entityPlayer.field_70161_v);
    }

    @SideOnly(value=Side.CLIENT)
    void H() {
        float f10;
        float f11;
        fp fp2;
        block9: {
            fp2 = this.y();
            try {
                try {
                    if (this.ab != fp.CORRUPT_INTRO && fp2 == fp.CORRUPT_INTRO) break block9;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                this.ab = fp2;
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        try {
            if (!entityPlayerSP.getPersistentID().equals(this.ae())) {
                this.ab = fp2;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            f11 = this.k() ? 0.0f : this.I().floatValue() + 180.0f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        entityPlayerSP.field_70177_z = f10 = f11;
        entityPlayerSP.field_70126_B = f10;
        entityPlayerSP.field_70125_A = 80.0f;
        entityPlayerSP.field_70127_C = 80.0f;
        this.ab = fp2;
    }

    void d(boolean bl2) {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        Vec3d vec3d = bl2 ? new Vec3d(-0.5, (double)(0.5f - entityPlayer.func_70047_e()), (double)0.4f).func_178787_e(this.o()) : ck.a(new Vec3d(0.5, (double)(0.5f - entityPlayer.func_70047_e()), (double)0.4f), this.I().floatValue()).func_178787_e(this.o());
        entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
    }

    /*
     * Exception decompiling
     */
    @Override
    @SideOnly(value=Side.CLIENT)
    public float v() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 3[SWITCH]
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
    protected boolean X() {
        return false;
    }

    public boolean v() {
        try {
            if (this.a(true) != null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        f8 f82 = new f8(this.field_70170_p);
        this.a(f82.f());
        f82.a(this.f());
        f82.c(true);
        f82.b(fp.RIDE_MOMMY_HEAD);
        f82.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70170_p.func_72838_d((Entity)f82);
        return true;
    }

    void Z() {
        try {
            if (this.k()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        fp fp2 = this.y();
        try {
            if (fp2 != fp.RAPE_CUM) {
                this.at = 0;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                this.at = 0;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (++this.at != 15) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        entityPlayer.func_70097_a((DamageSource)new a3(this), 2.1474836E9f);
    }

    void O() {
        EntityLivingBase entityLivingBase = this.M();
        try {
            if (entityLivingBase == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        for (EntityWitherSkeleton entityWitherSkeleton : this.bI) {
            try {
                if (entityWitherSkeleton.field_70128_L) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            try {
                if (entityLivingBase.func_70032_d((Entity)entityWitherSkeleton) < 15.0f) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            ge.b.sendToAllTracking((IMessage)new bv(entityWitherSkeleton.func_174791_d(), true), (Entity)this);
            entityWitherSkeleton.func_70106_y();
            this.field_70170_p.func_72900_e((Entity)entityWitherSkeleton);
        }
    }

    void aD() {
        try {
            if (!((Boolean)this.m.func_187225_a(bP)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        for (EntityWitherSkeleton entityWitherSkeleton : this.bI) {
            try {
                if (entityWitherSkeleton.field_70128_L) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            ge.b.sendToAllTracking((IMessage)new bv(entityWitherSkeleton.func_174791_d(), true), (Entity)this);
            entityWitherSkeleton.func_70106_y();
            this.field_70170_p.func_72900_e((Entity)entityWitherSkeleton);
        }
        this.bI.clear();
    }

    public static void c(EntityPlayer entityPlayer) {
        em em2 = em.a(com.trolmastercard.sexmod.v.b(entityPlayer));
        try {
            if (em2 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (em2.equals(entityPlayer.func_184187_bx())) {
                em2.e(entityPlayer.getPersistentID());
                em2.b(fp.CONTROLLED_FLIGHT);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    void aA() {
        for (EntityWitherSkeleton entityWitherSkeleton : this.bI) {
            try {
                if (entityWitherSkeleton.field_70128_L) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            try {
                if (entityWitherSkeleton.field_70173_aa % 10 != 0) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            Set set = ((WorldServer)this.field_70170_p).func_73039_n().getTrackingPlayers((Entity)entityWitherSkeleton);
            for (EntityPlayer entityPlayer : set) {
                ((EntityPlayerMP)entityPlayer).field_71135_a.func_147359_a((Packet)new SPacketParticles(EnumParticleTypes.DRAGON_BREATH, false, (float)entityWitherSkeleton.field_70165_t, (float)entityWitherSkeleton.field_70163_u, (float)entityWitherSkeleton.field_70161_v, 0.2f * (float)com.trolmastercard.sexmod.be.a(), entityWitherSkeleton.func_70047_e() / 2.0f, 0.2f * (float)com.trolmastercard.sexmod.be.a(), 0.0f, 5, new int[0]));
            }
        }
    }

    void aG() {
        ArrayList<EntityWitherSkeleton> arrayList = new ArrayList<EntityWitherSkeleton>();
        for (EntityWitherSkeleton entityWitherSkeleton : this.bI) {
            try {
                if (!entityWitherSkeleton.field_70128_L) continue;
                arrayList.add(entityWitherSkeleton);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        for (EntityWitherSkeleton entityWitherSkeleton : arrayList) {
            this.bI.remove(entityWitherSkeleton);
        }
    }

    void ad() {
        try {
            if (this.y() != fp.KNOCK_OUT_STAND_UP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        ++this.bY;
        if ((double)this.bY == 39.0) {
            this.func_189654_d(true);
            this.func_70016_h(0.0, 0.6f, 0.0);
            Vec3d vec3d = this.func_174791_d();
            Vec3d vec3d2 = vec3d.func_178786_a(2.0, 2.0, 2.0);
            Vec3d vec3d3 = vec3d.func_72441_c(2.0, 2.0, 2.0);
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c, vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c);
            List list = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisAlignedBB);
            for (EntityLivingBase entityLivingBase : list) {
                try {
                    if (entityLivingBase instanceof f_) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                Vec3d vec3d4 = entityLivingBase.func_174791_d();
                Vec3d vec3d5 = vec3d4.func_178788_d(vec3d).func_72432_b();
                try {
                    entityLivingBase.field_70159_w = vec3d5.field_72450_a * 1.0;
                    entityLivingBase.field_70179_y = vec3d5.field_72449_c * 1.0;
                    entityLivingBase.field_70181_x = 1.0;
                    entityLivingBase.func_70097_a((DamageSource)new cs(this), 0.5f);
                    if (!(entityLivingBase instanceof EntityPlayerMP)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entityLivingBase;
                entityPlayerMP.field_71135_a.func_147359_a((Packet)new SPacketEntityVelocity((Entity)entityPlayerMP));
            }
        }
        try {
            if ((double)this.bY < 58.0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.b(Vec3d.field_186680_a);
        this.m.func_187227_b(bP, (Object)false);
        this.bY = 0;
    }

    void b() {
        try {
            if (this.y() != fp.KNOCK_OUT_GROUND) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (((Boolean)this.m.func_187225_a(L)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            int n2;
            ++this.b3;
            if ((double)n2 < 50.0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.b(fp.KNOCK_OUT_STAND_UP);
        this.bY = 0;
        this.b3 = 0;
    }

    void S() {
        block7: {
            fp fp2 = this.y();
            try {
                try {
                    if (fp2 == fp.KNOCK_OUT_GROUND || fp2 == fp.KNOCK_OUT_STAND_UP) break block7;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        try {
            this.field_70159_w = 0.0;
            this.field_70179_y = 0.0;
            if (((Boolean)this.m.func_187225_a(L)).booleanValue()) {
                this.field_70181_x = 0.0;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    void T() {
        try {
            if (this.y() != fp.KNOCK_OUT_FLY) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        BlockPos blockPos = this.func_180425_c();
        if (this.field_70170_p.func_180495_p(blockPos).func_177230_c() instanceof BlockLiquid) {
            BlockPos blockPos2 = blockPos;
            while (this.field_70170_p.func_180495_p(blockPos2.func_177984_a()).func_177230_c() instanceof BlockLiquid) {
                blockPos2 = blockPos2.func_177984_a();
            }
            for (int i2 = -1; i2 < 2; ++i2) {
                try {
                    for (int i3 = -1; i3 < 2; ++i3) {
                        this.field_70170_p.func_175656_a(blockPos2.func_177982_a(i2, 0, i3), Blocks.field_150343_Z.func_176223_P());
                    }
                    continue;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            blockPos2 = blockPos2.func_177984_a();
            this.func_70634_a(blockPos2.func_177958_n(), blockPos2.func_177956_o(), blockPos2.func_177952_p());
            this.c(new Vec3d((Vec3i)blockPos2));
            ge.b.sendToAllTracking((IMessage)new bv(new Vec3d((Vec3i)blockPos2), true), (Entity)this);
            for (EntityPlayer entityPlayer : ((WorldServer)this.field_70170_p).func_73039_n().getTrackingPlayers((Entity)this)) {
                ((EntityPlayerMP)entityPlayer).field_71135_a.func_147359_a((Packet)new SPacketSoundEffect(SoundEvents.field_187659_cY, SoundCategory.AMBIENT, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f, 1.0f));
            }
            this.b(fp.KNOCK_OUT_GROUND);
            return;
        }
        try {
            if (!this.field_70122_E) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.b(fp.KNOCK_OUT_GROUND);
    }

    void J() {
        boolean bl2;
        try {
            if (this.bZ != h8.CHANGE_POSITION) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        int n2 = this.ar();
        try {
            f_ f_2 = this;
            bl2 = n2 == 0;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            f_2.field_70145_X = bl2;
            if (!this.field_70170_p.func_175623_d(this.func_180425_c())) {
                this.field_70145_X = true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    void q() {
        try {
            if (this.bZ == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.bZ.a(this);
    }

    void D() {
        try {
            if (this.M() == null) {
                this.aH();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.bZ == null) {
                this.z();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.bZ.c(this)) {
                this.z();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    /*
     * Exception decompiling
     */
    void z() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [14[DOLOOP]], but top level block is 6[TRYBLOCK]
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

    boolean a(h8 h82) {
        block4: {
            try {
                try {
                    if (!h82.onlyDoThisOnPlayers || this.M() instanceof EntityPlayer) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        return h82.d(this);
    }

    void aH() {
        this.bZ = null;
    }

    void I() {
        Object object;
        float f10;
        try {
            if (this.f()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.ae() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        boolean bl2 = this.k();
        try {
            f10 = bl2 ? 7.0f : 20.0f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        float f11 = f10;
        Vec3d vec3d = new Vec3d((double)f11, (double)f11, (double)f11);
        Vec3d vec3d2 = this.func_174791_d();
        Vec3d vec3d3 = vec3d2.func_178788_d(vec3d);
        Vec3d vec3d4 = vec3d2.func_178787_e(vec3d);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c, vec3d4.field_72450_a, vec3d4.field_72448_b, vec3d4.field_72449_c);
        try {
            object = bl2 ? this.a(axisAlignedBB) : this.b(axisAlignedBB);
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        EntityMob entityMob = object;
        try {
            if (entityMob == null) {
                this.aI();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            this.a((EntityLivingBase)entityMob);
            em.a((em)this, com.trolmastercard.sexmod.c.GIRLS_GALATH_DIALOG[1], true);
            if (this.bZ != null) {
                this.bZ.e(this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.bZ = h8.CHANGE_POSITION;
        this.bZ.b(this);
    }

    EntityPlayer b(AxisAlignedBB axisAlignedBB) {
        List list = this.field_70170_p.func_175647_a(EntityPlayer.class, axisAlignedBB, entityPlayer -> {
            boolean bl2;
            block7: {
                block6: {
                    try {
                        try {
                            try {
                                if (ei.e(entityPlayer) || entityPlayer.func_184812_l_()) break block6;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                            if (entityPlayer.func_175149_v()) break block6;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                        bl2 = true;
                        break block7;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                }
                bl2 = false;
            }
            return bl2;
        });
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return (EntityPlayer)list.get(0);
    }

    EntityMob a(AxisAlignedBB axisAlignedBB) {
        List list = this.field_70170_p.func_72872_a(EntityMob.class, axisAlignedBB);
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        ArrayList<EntityMob> arrayList = new ArrayList<EntityMob>();
        for (Object object : list) {
            try {
                if (!com.trolmastercard.sexmod.d.a((Entity)object)) continue;
                arrayList.add((EntityMob)object);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        Vec3d vec3d = this.func_174791_d().func_72441_c(0.0, (double)this.func_70047_e(), 0.0);
        for (EntityMob entityMob : arrayList) {
            try {
                if (!com.trolmastercard.sexmod.d.a(this.field_70170_p, vec3d, (Entity)entityMob)) continue;
                return entityMob;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        return null;
    }

    void aI() {
        try {
            if (this.M() == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            this.a((EntityLivingBase)null);
            if (this.bZ != null) {
                this.bZ.e(this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            this.bZ = null;
            if (((Boolean)this.m.func_187225_a(bP)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.b(fp.NULL);
    }

    boolean f() {
        float f10;
        EntityLivingBase entityLivingBase = this.M();
        try {
            if (entityLivingBase == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (entityLivingBase.field_70128_L) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (entityLivingBase.field_71093_bK != this.field_71093_bK) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        float f11 = this.func_70032_d((Entity)entityLivingBase);
        try {
            f10 = this.k() ? 16.0f : 30.0f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        float f12 = f10;
        try {
            if (f11 > f12) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (!(entityLivingBase instanceof EntityPlayer)) {
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
        try {
            if (em.c(entityPlayer.getPersistentID()) != null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (entityPlayer.func_184812_l_()) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (entityPlayer.func_175149_v()) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public em E() {
        f8 f82 = this.a(false);
        try {
            if (f82 == null) {
                return super.E();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        try {
            if (entityPlayerSP.func_70093_af()) {
                return f82;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        entityPlayerSP.func_146105_b((ITextComponent)new TextComponentString(TextFormatting.GRAY + "[sneak] + [right click] if you want to edit Manglelie instead"), true);
        return super.E();
    }

    protected boolean func_184645_a(EntityPlayer entityPlayer, EnumHand enumHand) {
        try {
            if (this.k()) {
                return this.a(entityPlayer, enumHand);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return this.b(entityPlayer, enumHand);
    }

    boolean a(EntityPlayer entityPlayer, EnumHand enumHand) {
        block13: {
            try {
                if (!entityPlayer.getPersistentID().equals(this.O())) {
                    return false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            try {
                if (fp.a((em)this, fp.HUG_MANG, fp.RUN, fp.GALATH_SUMMON, fp.GALATH_DE_SUMMON, fp.MASTERBATE)) {
                    return false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            try {
                try {
                    if (!cc.r.equals(entityPlayer.func_184586_b(EnumHand.OFF_HAND).func_77973_b()) && !cc.r.equals(entityPlayer.func_184586_b(EnumHand.MAIN_HAND).func_77973_b())) break block13;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        this.a(com.trolmastercard.sexmod.c.GIRLS_GALATH_HUH, new int[0]);
        String[] stringArray = !entityPlayer.field_70122_E ? new String[]{"ride"} : (this.a(false) == null ? new String[]{"cowgirl", "anal", "ride"} : new String[]{"cowgirl", "anal", "threesome", "ride"});
        try {
            if (this.field_70170_p.field_72995_K) {
                f_.a(entityPlayer, this.af(), stringArray, false);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void a(String string, UUID uUID) {
        try {
            if ("ride".equals(string)) {
                hf.f();
                ge.b.sendToServer((IMessage)new bk());
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if ("anal".equals(string)) {
                fh.b();
                d3.a(false);
                com.trolmastercard.sexmod.be.a(1200, () -> {
                    EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
                    this.c(entityPlayerSP.func_174791_d());
                    this.b(0.0f);
                    this.e(entityPlayerSP.getPersistentID());
                    this.a(true);
                    this.b(fp.CORRUPT_SLOW);
                });
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if ("cowgirl".equals(string)) {
                fh.b();
                d3.a(false);
                com.trolmastercard.sexmod.be.a(1200, () -> {
                    EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
                    this.c(entityPlayerSP.func_174791_d());
                    this.b(entityPlayerSP.field_70177_z + 180.0f);
                    this.b(fp.RAPE_INTRO);
                    this.e(entityPlayerSP.getPersistentID());
                    this.a(true);
                });
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        if ("threesome".equals(string)) {
            f8 f82 = this.a(false);
            try {
                if (f82 == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
            fh.b();
            d3.a(false);
            com.trolmastercard.sexmod.be.a(1200, () -> {
                Minecraft minecraft = Minecraft.func_71410_x();
                EntityPlayerSP entityPlayerSP = minecraft.field_71439_g;
                minecraft.field_71474_y.field_74320_O = 1;
                f82.c(entityPlayerSP.func_174791_d());
                this.c(entityPlayerSP.func_174791_d());
                f82.b(entityPlayerSP.field_70177_z + 180.0f);
                this.b(entityPlayerSP.field_70177_z);
                f82.b(fp.THREESOME_SLOW);
                this.b(fp.PUSSY_LICKING);
                f82.e(entityPlayerSP.getPersistentID());
                this.e(entityPlayerSP.getPersistentID());
                f82.a(true);
                this.a(true);
            });
        }
    }

    boolean b(EntityPlayer entityPlayer, EnumHand enumHand) {
        try {
            if (!((Boolean)this.m.func_187225_a(bP)).booleanValue()) {
                return super.func_184645_a(entityPlayer, enumHand);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.y() != fp.KNOCK_OUT_GROUND) {
                return super.func_184645_a(entityPlayer, enumHand);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                entityPlayer.field_70177_z -= -128.0f;
                entityPlayer.field_70125_A = 19.0f;
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.b(fp.CORRUPT_INTRO);
        this.e(entityPlayer.getPersistentID());
        this.a(true);
        this.c(this.func_174791_d());
        this.b(entityPlayer.field_70177_z);
        ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        return true;
    }

    @Nullable
    public Entity[] func_70021_al() {
        return new Entity[]{this.V, this.b2};
    }

    public void a(@Nullable EntityLivingBase entityLivingBase) {
        try {
            if (entityLivingBase == null) {
                this.m.func_187227_b(bq, (Object)-1);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.m.func_187227_b(bq, (Object)entityLivingBase.func_145782_y());
    }

    public int ar() {
        return (Integer)this.m.func_187225_a(aP);
    }

    public void b(int n2) {
        this.m.func_187227_b(aP, (Object)n2);
    }

    public EntityLivingBase M() {
        int n2 = (Integer)this.m.func_187225_a(bq);
        try {
            if (-1 == n2) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return (EntityLivingBase)this.field_70170_p.func_73045_a(n2);
    }

    public static Float a(f_ f_2, float f10) {
        float f11;
        block9: {
            fp fp2 = f_2.y();
            try {
                try {
                    try {
                        if (fp2 == fp.FLY || fp2 == fp.SUMMON_SKELETON) break block9;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    if (fp2 == fp.RAPE_PREPARE) break block9;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return null;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        EntityLivingBase entityLivingBase = f_2.M();
        try {
            if (entityLivingBase == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        Vec3d vec3d = com.trolmastercard.sexmod.b6.a(new Vec3d(entityLivingBase.field_70142_S, entityLivingBase.field_70137_T, entityLivingBase.field_70136_U), entityLivingBase.func_174791_d(), (double)f10);
        Vec3d vec3d2 = com.trolmastercard.sexmod.b6.a(new Vec3d(f_2.field_70142_S, f_2.field_70137_T, f_2.field_70136_U), f_2.func_174791_d(), (double)f10);
        Vec3d vec3d3 = vec3d.func_178788_d(vec3d2);
        f_2.field_70761_aq = f11 = (float)gc.b(Math.atan2(vec3d3.field_72449_c, vec3d3.field_72450_a)) - 90.0f;
        f_2.field_70760_ar = f11;
        return Float.valueOf(f11);
    }

    void c(float f10) {
        try {
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (this.func_110143_aJ() - f10 <= 0.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        long l2 = System.currentTimeMillis();
        try {
            if (l2 < this.bc + 1000L) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.a(com.trolmastercard.sexmod.c.GIRLS_GALATH_UUH, new int[0]);
        this.bc = l2;
    }

    public boolean func_70097_a(DamageSource damageSource, float f10) {
        try {
            if (damageSource.func_76347_k()) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (DamageSource.field_76369_e.equals(damageSource)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (DamageSource.field_76367_g.equals(damageSource)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (DamageSource.field_76379_h.equals(damageSource)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (DamageSource.field_188406_j.equals(damageSource)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.c(f10);
        return super.func_70097_a(damageSource, f10);
    }

    public boolean func_70965_a(MultiPartEntityPart multiPartEntityPart, DamageSource damageSource, float f10) {
        try {
            if (this.field_70170_p.field_72995_K) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (!(damageSource.func_76346_g() instanceof EntityPlayer)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (multiPartEntityPart == this.V) {
                this.m.func_187227_b(b7, (Object)false);
                ge.b.sendToAllTracking((IMessage)new bv(this.V.func_174791_d(), false), (Entity)this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (multiPartEntityPart == this.b2) {
                this.m.func_187227_b(bN, (Object)false);
                ge.b.sendToAllTracking((IMessage)new bv(this.b2.func_174791_d(), false), (Entity)this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return true;
    }

    @Override
    public void g() {
        this.a((EntityLivingBase)null);
        this.aH();
    }

    public World func_82194_d() {
        return this.field_70170_p;
    }

    public void func_70015_d(int n2) {
    }

    public void func_180430_e(float f10, float f11) {
    }

    @Override
    @Nullable
    protected fp c(fp fp2) {
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block10: {
            try {
                try {
                    if (fp2 != fp.CORRUPT_FAST && fp2 != fp.CORRUPT_SLOW) break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return fp.CORRUPT_CUM;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        try {
            if (fp2 == fp.RAPE_ON_GOING) {
                return fp.RAPE_CUM;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (fp.a(fp2, fp.MORNING_BLOWJOB_SLOW, fp.MORNING_BLOWJOB_FAST)) {
                this.S = true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return null;
    }

    @Override
    public boolean c() {
        return this.bb;
    }

    @Override
    public boolean a() {
        try {
            switch (this.y()) {
                case CORRUPT_SLOW: 
                case CORRUPT_FAST: 
                case CORRUPT_CUM: 
                case COWGIRLCUM: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    public void c(boolean bl2) {
        block16: {
            fp fp2 = this.y();
            try {
                try {
                    if (fp2 == fp.RAPE_ON_GOING || fp2 == fp.RAPE_INTRO) break block16;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (0.0f >= entityPlayer.func_110143_aJ() - 1.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            if (entityPlayer.field_71075_bZ.field_75098_d) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        try {
            entityPlayer.func_70097_a((DamageSource)new a3(this), 1.0f);
            if (bl2) {
                this.func_70691_i(1.5f);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        try {
            super.func_70014_b(nBTTagCompound);
            nBTTagCompound.func_74778_a("sexmod:master", (String)this.m.func_187225_a(v));
            if (this.bA) {
                nBTTagCompound.func_74757_a("sexmod:despawned", true);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
    }

    @Override
    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        try {
            super.func_70037_a(nBTTagCompound);
            this.m.func_187227_b(v, (Object)nBTTagCompound.func_74779_i("sexmod:master"));
            if (nBTTagCompound.func_74767_n("sexmod:despawned")) {
                this.P = true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        UUID uUID = this.O();
        if (uUID != null) {
            String string = gy.a(uUID, fy.GALATH);
            try {
                if (string != null) {
                    this.g(string);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
    }

    public void ak() {
        try {
            if (this.y() == fp.MASTERBATE_SITTING) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        this.bx = true;
        this.b(fp.MASTERBATE_SITTING);
    }

    public void a() {
        this.a5 = true;
        this.b(fp.PUSSY_LICKING);
    }

    @Override
    protected boolean a(fp fp2, String string, boolean bl2, AnimationEvent animationEvent) {
        block40: {
            block39: {
                block37: {
                    block36: {
                        block35: {
                            block34: {
                                block33: {
                                    try {
                                        try {
                                            if (fp2 != fp.MASTERBATE_SITTING || !this.bx) break block33;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw f_.a(concurrentModificationException);
                                        }
                                        this.bx = false;
                                        this.a("animation.galath.masterbating_sitting", true, animationEvent, true);
                                        return true;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                }
                                try {
                                    try {
                                        if (fp2 != fp.MORNING_BLOWJOB_FAST || !this.S) break block34;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw f_.a(concurrentModificationException);
                                    }
                                    this.b(fp.MORNING_BLOWJOB_CUM);
                                    return true;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    if (fp2 != fp.MORNING_BLOWJOB_FAST || !this.aD) break block35;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                this.a("animation.shared.bed_fast", true, animationEvent, true);
                                this.aD = false;
                                return true;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                        }
                        try {
                            if (fp2 == fp.MORNING_BLOWJOB_CUM) {
                                this.b((fp)null);
                                return true;
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                        try {
                            try {
                                if (fp2 != fp.PUSSY_LICKING || !this.a5) break block36;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                            this.a5 = false;
                            this.a("animation.galath.pussy_licking", true, animationEvent, true);
                            return true;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw f_.a(concurrentModificationException);
                        }
                    }
                    try {
                        block38: {
                            try {
                                try {
                                    if (fp2 != fp.MORNING_BLOWJOB_SLOW) break block37;
                                    if (this.S) break block38;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw f_.a(concurrentModificationException);
                                }
                                if (!d3.d) break block37;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw f_.a(concurrentModificationException);
                            }
                        }
                        this.aD = true;
                        this.b(fp.MORNING_BLOWJOB_FAST);
                        this.a("animation.shared.bed_soft", true, animationEvent, true);
                        return true;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                }
                try {
                    try {
                        if (fp2 != fp.MORNING_BLOWJOB_SLOW || !this.bt) break block39;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    this.bt = false;
                    this.a("animation.shared.bed_slow", true, animationEvent, true);
                    return true;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            try {
                try {
                    if (fp2 != fp.MORNING_BLOWJOB_FAST || d3.d) break block40;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                this.b(fp.MORNING_BLOWJOB_SLOW);
                this.bt = true;
                this.a("animation.shared.bed_back", true, animationEvent, true);
                return true;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        return false;
    }

    public float b(float f10) {
        fp fp2;
        block12: {
            block11: {
                fp2 = this.y();
                try {
                    try {
                        if (fp2 != fp.PUSSY_LICKING || this.a5) break block11;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw f_.a(concurrentModificationException);
                    }
                    return 0.0f;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
            }
            try {
                try {
                    if (fp2 != fp.MASTERBATE_SITTING || this.bx) break block12;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw f_.a(concurrentModificationException);
                }
                return 1.0f;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw f_.a(concurrentModificationException);
            }
        }
        float f11 = fp.d(this, f10);
        try {
            if (fp2 == fp.MASTERBATE_SITTING) {
                return f11;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw f_.a(concurrentModificationException);
        }
        return 1.0f - f11;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [9[TRYBLOCK]], but top level block is 17[SWITCH]
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
        this.C = new bz<f_>(this, "action", 0.0f, this::a);
        this.E = new AnimationController<f_>(this, "movement", 5.0f, this::a);
        this.s = new AnimationController<f_>(this, "eyes", 10.0f, this::a);
        this.C.registerSoundListener(var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 36[SWITCH]
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

    private static /* synthetic */ Vec3d lambda$null$10(em em2) {
        return em2.b("futaCockTip").func_178787_e(em2.o());
    }

    private static /* synthetic */ Vec3d lambda$null$9(em em2) {
        Vec3d vec3d = em2.d("futaCockTip");
        Vec3d vec3d2 = em2.d("futaCockTipDirHelp");
        return vec3d.func_178788_d(vec3d2).func_72432_b();
    }

    private static /* synthetic */ Vec3d lambda$null$8(em em2) {
        return em2.b("creampiePos").func_178787_e(em2.o());
    }

    private /* synthetic */ Vec3d lambda$null$7(em em2) {
        return ck.a(new Vec3d(0.0, 0.0, (double)0.6f), this.I().floatValue());
    }

    private static /* synthetic */ Vec3d lambda$null$6(em em2) {
        return em2.b("futaCockTip").func_178787_e(em2.o());
    }

    private static /* synthetic */ Vec3d lambda$null$5(em em2) {
        Vec3d vec3d = em2.d("futaCockTip");
        Vec3d vec3d2 = em2.d("futaCockTipDirHelp");
        return vec3d.func_178788_d(vec3d2).func_72432_b();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static class a {
        boolean a(f_ f_2) {
            boolean bl2;
            try {
                bl2 = f_2.ab() != null;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            return bl2;
        }

        @SubscribeEvent(priority=EventPriority.LOWEST)
        public void a(LivingSpawnEvent.CheckSpawn checkSpawn) {
            Entity entity;
            block13: {
                Event.Result result = checkSpawn.getResult();
                try {
                    if (result == Event.Result.DENY) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (checkSpawn.isSpawner()) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                entity = checkSpawn.getEntity();
                try {
                    try {
                        if (entity instanceof EntityWitherSkeleton || entity instanceof EntityBlaze) break block13;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    return;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
            }
            BlockPos blockPos = entity.func_180425_c();
            World world = entity.field_70170_p;
            try {
                if (!f_.a(blockPos, world)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            checkSpawn.setResult(Event.Result.DENY);
            fq.a(blockPos, fq.c);
            f_ f_2 = new f_(world);
            f_2.func_70634_a(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
            world.func_72838_d((Entity)f_2);
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(InputEvent.KeyInputEvent keyInputEvent) {
            Minecraft minecraft = Minecraft.func_71410_x();
            try {
                if (!minecraft.field_71474_y.field_74314_A.func_151470_d()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            try {
                if (!hf.d()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            try {
                for (em em2 : em.ad()) {
                    try {
                        if (!em2.field_70170_p.field_72995_K) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    try {
                        if (!(em2 instanceof f_)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    try {
                        if (!minecraft.field_71439_g.getPersistentID().equals(((f_)em2).ax())) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    hf.a();
                    em2.b(fp.BOOST);
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }

        @SubscribeEvent
        public void a(EntityMountEvent entityMountEvent) {
            try {
                if (entityMountEvent.isMounting()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            Entity entity = entityMountEvent.getEntityBeingMounted();
            try {
                if (!(entity instanceof f_)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            try {
                if (entity.field_70170_p.field_72995_K) {
                    hf.c();
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            ((f_)entity).t();
        }

        @SubscribeEvent(priority=EventPriority.HIGH)
        public void a(LivingDeathEvent livingDeathEvent) {
            f_ f_2;
            block15: {
                block14: {
                    Entity entity = livingDeathEvent.getEntity();
                    try {
                        if (!(entity instanceof f_)) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    try {
                        if (livingDeathEvent.getSource().equals(DamageSource.field_76380_i)) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    f_2 = (f_)entity;
                    try {
                        if (f_2.bU) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    try {
                        if (entity.field_70170_p.field_72995_K) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    try {
                        if (f_2.k()) break block14;
                        f_2.a((Entity)f_2.func_110142_aN().func_180135_h());
                        break block15;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                }
                cc.a(f_2);
                ge.b.sendToAllTracking((IMessage)new ab(f_2.f(), com.trolmastercard.sexmod.v.b(f_2)), (Entity)f_2);
                com.trolmastercard.sexmod.be.a(900, () -> com.trolmastercard.sexmod.v.a(f_2));
                f_2.bU = true;
            }
            f_2.func_70606_j(1.0f);
            livingDeathEvent.setCanceled(true);
        }

        @SubscribeEvent
        public void a(PlayerEvent.PlayerRespawnEvent playerRespawnEvent) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)playerRespawnEvent.player;
            em em2 = em.a(entityPlayerMP.getPersistentID(), true);
            try {
                if (!(em2 instanceof f_)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            f_ f_2 = (f_)em2;
            try {
                f_2.a((EntityLivingBase)null);
                s.a.a(em2);
                ge.b.sendTo((IMessage)new gz(true), entityPlayerMP);
                em2.b((fp)null);
                if (f_2.bZ == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            f_2.bZ.e(f_2);
            f_2.bZ = null;
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(RenderWorldLastEvent renderWorldLastEvent) {
            Minecraft minecraft = Minecraft.func_71410_x();
            RenderManager renderManager = minecraft.func_175598_ae();
            float f10 = minecraft.func_184121_ak();
            try {
                for (em em2 : em.ad()) {
                    c4 c42;
                    Vec3d vec3d;
                    Vec3d vec3d2;
                    try {
                        if (!(em2 instanceof f_)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    try {
                        if (!em2.field_70170_p.field_72995_K) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    try {
                        if (em2.y() != fp.SUMMON_SKELETON) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    double d10 = ((f_)em2).ad;
                    try {
                        if (d10 < 9.0) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    try {
                        if (d10 > 30.0) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                    }
                    Vec3d vec3d3 = com.trolmastercard.sexmod.b6.a(new Vec3d(em2.field_70142_S, em2.field_70137_T, em2.field_70136_U), em2.func_174791_d(), (double)f10);
                    double d11 = (d10 - 9.0) / 21.0;
                    if (((Boolean)em2.func_184212_Q().func_187225_a(bN)).booleanValue()) {
                        vec3d2 = em2.b("energyBallR");
                        vec3d = vec3d3.func_178787_e(vec3d2);
                        c42 = new c4(em2.field_70170_p, (f_)em2);
                        c42.g = d11;
                        c42.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
                        renderManager.func_188391_a((Entity)c42, 0.0, 0.0, 0.0, 0.0f, f10, true);
                        c42.func_70107_b(0.0, -500.0, 0.0);
                        c42.func_70106_y();
                    }
                    if (!((Boolean)em2.func_184212_Q().func_187225_a(b7)).booleanValue()) continue;
                    vec3d2 = em2.b("energyBallL");
                    vec3d = vec3d3.func_178787_e(vec3d2);
                    c42 = new c4(em2.field_70170_p, (f_)em2);
                    c42.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
                    c42.g = d11;
                    renderManager.func_188391_a((Entity)c42, 0.0, 0.0, 0.0, 0.0f, f10, true);
                    c42.func_70107_b(0.0, -500.0, 0.0);
                    c42.func_70106_y();
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
            GlStateManager.func_179145_e();
            GlStateManager.func_179126_j();
            GlStateManager.func_179141_d();
        }

        boolean a(World world, BlockPos blockPos, EnumFacing enumFacing) {
            if (enumFacing == EnumFacing.NORTH) {
                blockPos = blockPos.func_177976_e();
                try {
                    if (this.a(world, blockPos)) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177984_a())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177968_d())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177968_d().func_177984_a())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                return true;
            }
            if (enumFacing == EnumFacing.WEST) {
                blockPos = blockPos.func_177968_d();
                try {
                    if (this.a(world, blockPos)) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177984_a())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177974_f())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177974_f().func_177984_a())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                return true;
            }
            if (enumFacing == EnumFacing.SOUTH) {
                blockPos = blockPos.func_177974_f();
                try {
                    if (this.a(world, blockPos)) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177984_a())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177978_c())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177978_c().func_177984_a())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                return true;
            }
            if (enumFacing == EnumFacing.EAST) {
                blockPos = blockPos.func_177978_c();
                try {
                    if (this.a(world, blockPos)) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177984_a())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177976_e())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.func_177976_e().func_177984_a())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
                return true;
            }
            Main.LOGGER.error("Weird bed orientation, when checking for space next to bed, on galaths morning blowjob animation: " + enumFacing.func_176610_l());
            return false;
        }

        boolean a(World world, BlockPos blockPos) {
            Block block = world.func_180495_p(blockPos).func_177230_c();
            for (Class<?> clazz : aS) {
                try {
                    if (!clazz.isInstance(block)) continue;
                    return false;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
                }
            }
            return true;
        }

        @SubscribeEvent
        public void a(PlayerWakeUpEvent playerWakeUpEvent) {
            float f10;
            EntityPlayer entityPlayer = playerWakeUpEvent.getEntityPlayer();
            try {
                if (entityPlayer.field_70170_p.field_72995_K) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            try {
                if (!com.trolmastercard.sexmod.v.a(entityPlayer.getPersistentID(), entityPlayer.field_70170_p)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            Vec3d vec3d = entityPlayer.func_174791_d();
            BlockPos blockPos = new BlockPos(vec3d);
            EnumFacing enumFacing = (EnumFacing)entityPlayer.field_70170_p.func_180495_p(blockPos).func_177229_b((IProperty)BlockHorizontal.field_185512_D);
            try {
                if (!this.a(entityPlayer.field_70170_p, blockPos, enumFacing)) {
                    entityPlayer.func_145747_a((ITextComponent)new TextComponentString(String.format("%sFor Galath and Manglelie to %swake you up with a blowjob%s, you have to provide enough space to the %sright side%s of your bed. This includes the %stop and bottom half%s of the bed.", TextFormatting.GRAY, TextFormatting.DARK_RED, TextFormatting.GRAY, TextFormatting.DARK_RED, TextFormatting.GRAY, TextFormatting.DARK_RED, TextFormatting.GRAY)));
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            switch ((EnumFacing)entityPlayer.field_70170_p.func_180495_p(blockPos).func_177229_b((IProperty)BlockHorizontal.field_185512_D)) {
                default: {
                    f10 = 0.0f;
                    break;
                }
                case NORTH: {
                    f10 = 180.0f;
                    break;
                }
                case EAST: {
                    f10 = -90.0f;
                    break;
                }
                case WEST: {
                    f10 = 90.0f;
                }
            }
            Vec3d vec3d2 = new Vec3d((double)blockPos.func_177958_n() + 0.5, (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p() + 0.5);
            UUID uUID = com.trolmastercard.sexmod.v.b(entityPlayer);
            try {
                if (uUID != null) {
                    com.trolmastercard.sexmod.v.a((f_)em.a(uUID));
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.f_$a.a(concurrentModificationException);
            }
            f_ f_2 = new f_(entityPlayer.field_70170_p, entityPlayer, vec3d, true);
            f_2.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
            entityPlayer.field_70170_p.func_72838_d((Entity)f_2);
            com.trolmastercard.sexmod.v.a(entityPlayer, f_2);
            f_2.v();
            f_2.c(vec3d2);
            f_2.b(f10);
            f_2.a(true);
            f_2.e(entityPlayer.getPersistentID());
            f_2.b(fp.MORNING_BLOWJOB_SLOW);
            ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
            com.trolmastercard.sexmod.be.a(500, () -> {
                entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
                ge.b.sendTo((IMessage)new aq(-10.0f, f10 + 180.0f + 5.0f, 0), (EntityPlayerMP)entityPlayer);
            });
        }

        private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
            return concurrentModificationException;
        }
    }
}

