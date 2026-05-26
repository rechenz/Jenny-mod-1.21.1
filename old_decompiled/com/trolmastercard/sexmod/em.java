/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.util.Pair
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWater
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathNavigateGround
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 */
package com.trolmastercard.sexmod;

import com.mojang.realmsclient.util.Pair;
import com.trolmastercard.sexmod.ClientProxy;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.a1;
import com.trolmastercard.sexmod.a8;
import com.trolmastercard.sexmod.b;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.br;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.cv;
import com.trolmastercard.sexmod.cy;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.d_;
import com.trolmastercard.sexmod.dc;
import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.dz;
import com.trolmastercard.sexmod.e1;
import com.trolmastercard.sexmod.e4;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.ew;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.fs;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.g0;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gh;
import com.trolmastercard.sexmod.gj;
import com.trolmastercard.sexmod.gw;
import com.trolmastercard.sexmod.hz;
import com.trolmastercard.sexmod.m;
import com.trolmastercard.sexmod.n;
import com.trolmastercard.sexmod.r;
import com.trolmastercard.sexmod.s;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public abstract class em
extends EntityCreature
implements IAnimatable {
    public static int j = 22;
    protected static final long t = 20L;
    private final AnimationFactory g;
    public EntityAIWanderAvoidWater z;
    public df o;
    public static HashSet<em> k = new HashSet();
    public Vec3d B;
    protected float r;
    protected EntityDataManager m;
    public PathNavigate f;
    public Vec3d l;
    public EntityEnderPearl q;
    public float n;
    public boolean F;
    private boolean i;
    HashMap<String, Vec3d> x;
    public static final DataParameter<String> v = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(110);
    public static final DataParameter<Boolean> G = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(109);
    public static final DataParameter<String> e = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(108);
    public static final DataParameter<Float> w = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187193_c).func_187156_b().func_187161_a(107);
    public static final DataParameter<String> u = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(106);
    public static final DataParameter<Integer> D = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(105);
    public static final DataParameter<String> J = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(104);
    public static final DataParameter<String> h = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(103);
    public static final DataParameter<String> y = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(102);
    public static final DataParameter<String> a = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(101);
    public static final DataParameter<String> b = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(100);
    public static final DataParameter<String> c = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(99);
    protected static final List<Item> I = Arrays.asList(Items.field_151166_bC, Items.field_151045_i, Items.field_151043_k, Items.field_151079_bi);
    public AnimationController C;
    public AnimationController E;
    public AnimationController s;
    HashMap<String, Pair<Integer, Integer>> A;
    AnimationProcessor<?> H;
    public List<String> p;
    protected List<Map.Entry<gw, Map.Entry<List<String>, Integer>>> d;

    public void a(a a10) {
        this.m.func_187227_b(a, (Object)a10.toString());
    }

    public a q() {
        return com.trolmastercard.sexmod.em$a.valueOf((String)this.m.func_187225_a(a));
    }

    @SideOnly(value=Side.CLIENT)
    protected void a(String string, String string2) {
        ge.b.sendToServer((IMessage)new n(this.f(), string, string2));
    }

    public UUID f() {
        try {
            return UUID.fromString((String)this.m.func_187225_a(u));
        }
        catch (Exception exception) {
            UUID uUID = UUID.randomUUID();
            this.m.func_187227_b(u, (Object)uUID.toString());
            return uUID;
        }
    }

    public fp y() {
        return fp.valueOf((String)this.m.func_187225_a(J));
    }

    public void b(fp fp2) {
        fp fp3;
        block12: {
            fp fp4 = this.y();
            try {
                if (fp4 == fp2) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            try {
                try {
                    if (fp2 != fp.ATTACK || fp4 == fp.NULL) break block12;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        try {
            fp3 = fp2 == null ? fp.NULL : fp2;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        fp2 = fp3;
        try {
            if (this.field_70170_p.field_72995_K) {
                this.a("currentAction", fp2.toString());
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        fp4.ticksPlaying = new int[]{0, 0};
        this.m.func_187227_b(J, (Object)fp2.toString());
    }

    public int ah() {
        return (Integer)this.m.func_187225_a(D);
    }

    public void f(int n2) {
        block3: {
            block2: {
                try {
                    if (!this.field_70170_p.field_72995_K) break block2;
                    this.a("currentModel", "0");
                    break block3;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            this.m.func_187227_b(D, (Object)n2);
        }
    }

    public boolean m() {
        return false;
    }

    @Nullable
    public EntityPlayer S() {
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return this.field_70170_p.func_152378_a(uUID);
    }

    public static void a(em em2, String string) {
        for (EntityPlayer entityPlayer : cj.a((Entity)em2)) {
            entityPlayer.func_145747_a((ITextComponent)new TextComponentString(string));
        }
    }

    public static void a(em em2, SoundEvent soundEvent, boolean bl2) {
        Vec3d vec3d = em2.func_174791_d();
        for (EntityPlayer entityPlayer : cj.a((Entity)em2)) {
            Vec3d vec3d2;
            if (!bl2) {
                vec3d2 = vec3d;
            } else {
                Vec3d vec3d3 = entityPlayer.func_174791_d();
                Vec3d vec3d4 = vec3d.func_178788_d(vec3d3).func_72432_b();
                vec3d2 = vec3d3.func_178787_e(vec3d4);
            }
            ((EntityPlayerMP)entityPlayer).field_71135_a.func_147359_a((Packet)new SPacketSoundEffect(soundEvent, SoundCategory.AMBIENT, vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c, 1.0f, 1.0f));
        }
    }

    public static void a(em em2, SoundEvent soundEvent) {
        em.a(em2, soundEvent, false);
    }

    public static void a(em em2, SoundEvent[] soundEventArray) {
        em.a(em2, com.trolmastercard.sexmod.c.a(soundEventArray));
    }

    public static void a(em em2, SoundEvent[] soundEventArray, boolean bl2) {
        em.a(em2, com.trolmastercard.sexmod.c.a(soundEventArray), bl2);
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3d A() {
        Vec3d vec3d = Minecraft.func_71410_x().field_71439_g.func_174791_d();
        Vec3d vec3d2 = this.func_174791_d();
        Vec3d vec3d3 = vec3d2.func_178788_d(vec3d).func_72432_b();
        return vec3d.func_178787_e(vec3d3);
    }

    @Nullable
    public UUID ae() {
        String string = (String)this.m.func_187225_a(y);
        try {
            if (string.equals("null")) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return UUID.fromString(string);
    }

    public void e(UUID uUID) {
        block10: {
            block9: {
                block6: {
                    block8: {
                        block7: {
                            try {
                                try {
                                    if (!this.field_70170_p.field_72995_K) break block6;
                                    if (uUID != null) break block7;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw em.c(concurrentModificationException);
                                }
                                this.a("playerSheHasSexWith", (String)null);
                                break block8;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw em.c(concurrentModificationException);
                            }
                        }
                        this.a("playerSheHasSexWith", uUID.toString());
                    }
                    return;
                }
                try {
                    if (uUID != null) break block9;
                    this.m.func_187227_b(y, (Object)"null");
                    break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            this.m.func_187227_b(y, (Object)uUID.toString());
        }
    }

    public void a(@Nonnull EntityPlayer entityPlayer) {
        this.e(entityPlayer.getPersistentID());
    }

    public Vec3d o() {
        String[] stringArray = ((String)this.m.func_187225_a(e)).split("\\|");
        return new Vec3d(Double.parseDouble(stringArray[0]), Double.parseDouble(stringArray[1]), Double.parseDouble(stringArray[2]));
    }

    public void c(Vec3d vec3d) {
        if (this.field_70170_p.field_72995_K) {
            String string = vec3d.field_72450_a + "f" + vec3d.field_72448_b + "f" + vec3d.field_72449_c + "f";
            this.a("targetPos", string);
            return;
        }
        this.m.func_187227_b(e, (Object)(vec3d.field_72450_a + "|" + vec3d.field_72448_b + "|" + vec3d.field_72449_c));
    }

    public void a(Vec3d vec3d) {
        this.m.func_187227_b(e, (Object)(vec3d.field_72450_a + "|" + vec3d.field_72448_b + "|" + vec3d.field_72449_c));
    }

    public Float I() {
        return (Float)this.m.func_187225_a(w);
    }

    public void b(float f10) {
        this.m.func_187227_b(w, (Object)Float.valueOf(f10));
    }

    public void a(boolean bl2) {
        try {
            if (this.field_70170_p.field_72995_K) {
                this.a("shouldbeattargetpos", String.valueOf(bl2));
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        this.m.func_187227_b(G, (Object)bl2);
    }

    public boolean Q() {
        return (Boolean)this.m.func_187225_a(G);
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected em(World world) {
        block8: {
            super(world);
            this.g = new AnimationFactory(this);
            this.l = Vec3d.field_186680_a;
            this.n = 1.0f;
            this.F = false;
            this.i = false;
            this.x = new HashMap();
            this.A = new HashMap();
            this.H = null;
            this.p = new ArrayList<String>();
            this.d = null;
            if (world.field_72995_K) {
                this.p();
            }
            try {
                try {
                    if (!world.field_72995_K || !(world instanceof gj)) break block8;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        PathNavigate pathNavigate = this.func_70661_as();
        try {
            if (pathNavigate instanceof PathNavigateGround) {
                ((PathNavigateGround)pathNavigate).func_179688_b(true);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    protected void p() {
        this.C = new AnimationController<em>(this, "action", 0.0f, this::a);
        this.E = new AnimationController<em>(this, "movement", 5.0f, this::a);
        this.s = new AnimationController<em>(this, "eyes", 10.0f, this::a);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.f = this.func_70661_as();
        this.m = this.func_184212_Q();
        this.m.func_187214_a(u, (Object)UUID.randomUUID().toString());
        this.m.func_187214_a(D, (Object)1);
        this.m.func_187214_a(J, (Object)fp.NULL.toString());
        this.m.func_187214_a(h, (Object)"");
        this.m.func_187214_a(y, (Object)"null");
        this.m.func_187214_a(G, (Object)false);
        this.m.func_187214_a(w, (Object)Float.valueOf(0.0f));
        this.m.func_187214_a(e, (Object)"0|0|0");
        this.m.func_187214_a(v, (Object)"");
        this.m.func_187214_a(a, (Object)com.trolmastercard.sexmod.em$a.WALK.toString());
        this.m.func_187214_a(b, (Object)"");
        this.m.func_187214_a(c, (Object)"");
    }

    public void b(boolean bl2) {
        block3: {
            block2: {
                try {
                    this.i = bl2;
                    if (!bl2) break block2;
                    fs.b(this);
                    break block3;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            fs.a(this);
        }
    }

    public boolean h() {
        return this.i;
    }

    public static List<em> ad() {
        try {
            if (!g0.a()) {
                return em.Z();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        WorldServer[] worldServerArray = FMLCommonHandler.instance().getMinecraftServerInstance().field_71305_c;
        try {
            if (worldServerArray.length == 0) {
                return new ArrayList<em>();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        ArrayList<em> arrayList = new ArrayList<em>();
        for (WorldServer worldServer : worldServerArray) {
            arrayList.addAll(worldServer.func_175644_a(em.class, em2 -> true));
        }
        return arrayList;
    }

    @SideOnly(value=Side.CLIENT)
    private static List<em> Z() {
        WorldClient worldClient = Minecraft.func_71410_x().field_71441_e;
        try {
            if (worldClient == null) {
                return new ArrayList<em>();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return worldClient.func_175644_a(em.class, em2 -> true);
    }

    public boolean B() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(30.0);
    }

    protected void func_184651_r() {
        this.z = new EntityAIWanderAvoidWater((EntityCreature)this, 0.35);
        this.o = new df((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.4, false, new HashSet<Item>(I)));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new hz((EntityLiving)this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.o);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.z);
    }

    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        nBTTagCompound.func_74780_a("homeX", this.l.field_72450_a);
        nBTTagCompound.func_74780_a("homeY", this.l.field_72448_b);
        nBTTagCompound.func_74780_a("homeZ", this.l.field_72449_c);
        nBTTagCompound.func_74778_a("girlID", (String)this.m.func_187225_a(u));
        String string = this.w();
        try {
            if (!"".equals(string)) {
                nBTTagCompound.func_74778_a("sexmod:customname", string);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (this.X()) {
                nBTTagCompound.func_74778_a("sexmod:customModel", this.C());
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        super.func_70014_b(nBTTagCompound);
    }

    protected boolean X() {
        return em.a((Entity)this);
    }

    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        super.func_70037_a(nBTTagCompound);
        this.l = new Vec3d(nBTTagCompound.func_74769_h("homeX"), nBTTagCompound.func_74769_h("homeY"), nBTTagCompound.func_74769_h("homeZ"));
        String string = nBTTagCompound.func_74779_i("sexmod:customname");
        try {
            if (!"".equals(string)) {
                this.g(string);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        String string2 = nBTTagCompound.func_74779_i("girlID");
        try {
            if ("".equals(string2)) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        UUID uUID = UUID.fromString(string2);
        boolean bl2 = false;
        for (em em2 : em.g(uUID)) {
            try {
                if (em2.field_70170_p.field_72995_K) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            try {
                if (em2 == this) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            try {
                if (em2.field_70128_L) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            try {
                if (!em2.isAddedToWorld()) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            bl2 = true;
            break;
        }
        try {
            if (bl2) {
                Main.LOGGER.log(Level.WARN, String.format("got a duped %s with id '%s'. Deleted her", this.c(), uUID));
                this.field_70170_p.func_72900_e((Entity)this);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            this.m.func_187227_b(u, (Object)uUID.toString());
            if (this.X()) {
                this.f(nBTTagCompound.func_74779_i("sexmod:customModel"));
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    public boolean d() {
        return true;
    }

    public void func_70016_h(double d10, double d11, double d12) {
        this.field_70159_w = d10;
        this.field_70181_x = d11;
        this.field_70179_y = d12;
    }

    public void b(Vec3d vec3d) {
        this.field_70159_w = vec3d.field_72450_a;
        this.field_70181_x = vec3d.field_72448_b;
        this.field_70179_y = vec3d.field_72449_c;
    }

    public Vec3d j() {
        return new Vec3d(this.field_70142_S, this.field_70137_T, this.field_70136_U);
    }

    public void func_70619_bc() {
        try {
            if (((Boolean)this.m.func_187225_a(G)).booleanValue()) {
                this.func_70034_d(this.I().floatValue());
                this.func_70080_a(this.o().field_72450_a, this.o().field_72448_b, this.o().field_72449_c, this.I().floatValue(), 0.0f);
                this.func_70101_b(this.I().floatValue(), this.field_70125_A);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (this.l.equals((Object)Vec3d.field_186680_a)) {
                this.l = new Vec3d((Vec3i)this.func_180425_c());
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        this.G();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.l();
    }

    protected void G() {
        try {
            if (!br.e) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        HashSet<String> hashSet = this.Y();
        fy fy2 = fy.a((Entity)this);
        HashSet<String> hashSet2 = new HashSet<String>();
        String string = br.h();
        for (String string2 : hashSet) {
            try {
                if (!"".equals(br.a(string2, string))) {
                    hashSet2.add(string2);
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            HashSet<fy> hashSet3 = br.a(string2);
            try {
                if (hashSet3 == null) {
                    hashSet2.add(string2);
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            try {
                if (hashSet3.isEmpty()) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            try {
                if (hashSet3.contains((Object)fy2)) continue;
                hashSet2.add(string2);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        try {
            if (hashSet2.isEmpty()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        hashSet.removeAll(hashSet2);
        this.f(em.a(hashSet));
    }

    protected void l() {
        int n2;
        int[] nArray;
        fp fp2 = this.y();
        try {
            nArray = fp2.ticksPlaying;
            n2 = this.field_70170_p.field_72995_K ? 1 : 0;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            nArray[n2] = nArray[n2] + 1;
            if (nArray[n2] < fp2.length) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (fp2.followUp == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (!this.field_70170_p.field_72995_K) {
                this.b(fp2.followUp);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    protected void k() {
        int n2;
        Path path;
        block12: {
            block11: {
                path = this.func_70661_as().func_75505_d();
                try {
                    if (path == null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                try {
                    try {
                        if (!this.field_70122_E && !this.func_70090_H()) break block11;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                    return;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            n2 = path.func_75873_e();
            int n3 = path.func_75874_d();
            try {
                try {
                    if (n3 != n2 && n3 - 1 != n2) break block12;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        PathPoint pathPoint = path.func_75877_a(n2);
        PathPoint pathPoint2 = path.func_75877_a(n2 + 1);
        Vec3d vec3d = new Vec3d((double)(pathPoint2.field_75839_a - pathPoint.field_75839_a), (double)(pathPoint2.field_75837_b - pathPoint.field_75837_b), (double)(pathPoint2.field_75838_c - pathPoint.field_75838_c));
        this.field_70159_w = vec3d.field_72450_a / 7.0;
        this.field_70179_y = vec3d.field_72449_c / 7.0;
    }

    public void g() {
    }

    @SideOnly(value=Side.CLIENT)
    public boolean b(EntityPlayer entityPlayer) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    protected static void a(EntityPlayer entityPlayer, em em2) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(em2, entityPlayer));
    }

    @SideOnly(value=Side.CLIENT)
    protected static void a(EntityPlayer entityPlayer, em em2, String[] stringArray, ItemStack[] itemStackArray, boolean bl2) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(em2, entityPlayer, stringArray, itemStackArray, bl2));
    }

    @SideOnly(value=Side.CLIENT)
    protected static void a(EntityPlayer entityPlayer, em em2, String[] stringArray, boolean bl2) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(em2, entityPlayer, stringArray, null, bl2));
    }

    public void a(ItemStack itemStack) {
        this.field_184627_bm = itemStack;
    }

    public void d(int n2) {
        this.field_184628_bn = n2;
    }

    public Vec3d M() {
        return new Vec3d(this.field_70169_q, this.field_70167_r, this.field_70166_s);
    }

    protected static Vec3d a(em em2) {
        return new Vec3d(em2.field_70169_q, em2.field_70167_r, em2.field_70166_s);
    }

    public em af() {
        return this;
    }

    public void x() {
        block3: {
            block2: {
                try {
                    if (!this.field_70170_p.field_72995_K) break block2;
                    this.a("master", "");
                    this.a("walk speed", com.trolmastercard.sexmod.em$a.WALK.toString());
                    break block3;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            this.m.func_187227_b(v, (Object)"");
            this.m.func_187227_b(a, (Object)com.trolmastercard.sexmod.em$a.WALK.toString());
        }
    }

    protected void a(EntityPlayerMP entityPlayerMP, boolean bl2) {
        entityPlayerMP.field_70159_w = 0.0;
        entityPlayerMP.field_70181_x = 0.0;
        entityPlayerMP.field_70179_y = 0.0;
        if (bl2) {
            Vec3d vec3d = this.a(0.35);
            entityPlayerMP.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        }
    }

    public void j(UUID uUID) {
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        entityPlayer.field_70159_w = 0.0;
        entityPlayer.field_70181_x = 0.0;
        entityPlayer.field_70179_y = 0.0;
        Vec3d vec3d = this.a(0.35);
        entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        this.b(entityPlayer.field_70759_as + 180.0f);
    }

    protected void a(boolean bl2, boolean bl3, UUID uUID) {
        block3: {
            block2: {
                try {
                    if (!this.field_70170_p.field_72995_K) break block2;
                    ge.b.sendToServer((IMessage)new dc(this.f(), uUID, bl2, bl3));
                    break block3;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            dc.a.a(this.f(), uUID, bl2, bl3);
        }
    }

    public static em b(UUID uUID) {
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        for (em em2 : em.g(uUID)) {
            try {
                if (!em2.field_70170_p.field_72995_K) continue;
                return em2;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        return null;
    }

    public static em a(UUID uUID) {
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        for (em em2 : em.g(uUID)) {
            try {
                if (em2.field_70170_p.field_72995_K) continue;
                return em2;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ArrayList<em> g(UUID uUID) {
        ArrayList<em> arrayList = new ArrayList<em>();
        try {
            for (em em2 : em.ad()) {
                try {
                    if (em2 == null) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                try {
                    if (!em2.f().equals(uUID)) continue;
                    arrayList.add(em2);
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                    return arrayList;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            System.out.println("had a ConcurrentModificationException while cycling through the girl list... hopefully nothin borke owo");
            concurrentModificationException.printStackTrace();
        }
        return arrayList;
    }

    protected BlockPos a(BlockPos blockPos) {
        return this.a(blockPos, 1);
    }

    public BlockPos a(BlockPos blockPos, int n2) {
        return this.a(blockPos, n2, Blocks.field_150324_C, 22, 3, null);
    }

    public void W() {
        this.m.func_187227_b(field_184621_as, (Object)Byte.valueOf("1"));
    }

    public void K() {
        this.m.func_187227_b(field_184621_as, (Object)Byte.valueOf("0"));
    }

    public BlockPos a(BlockPos blockPos, int n2, Block block, int n3, int n4, @Nullable HashSet<Biome> hashSet) {
        int n5 = 1;
        int n6 = -1;
        BlockPos blockPos2 = blockPos;
        int n7 = 0;
        while (n5 < n3) {
            for (int i2 = 0; i2 < 2; ++i2) {
                int n8;
                int n9;
                n6 *= -1;
                block22: for (n9 = 0; n9 < n5; ++n9) {
                    blockPos2 = blockPos2.func_177982_a(0, 0, n6);
                    n8 = -n4;
                    while (true) {
                        block26: {
                            try {
                                block27: {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    if (n8 >= n4 + 1) continue block22;
                                                    if (this.field_70170_p.func_180495_p(blockPos2.func_177982_a(0, n8, n6)).func_177230_c() != block) break block26;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw em.c(concurrentModificationException);
                                                }
                                                if (++n7 < n2) break block26;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw em.c(concurrentModificationException);
                                            }
                                            if (hashSet == null) break block27;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw em.c(concurrentModificationException);
                                        }
                                        if (!hashSet.contains(this.field_70170_p.func_180494_b(blockPos2.func_177982_a(n6, n8, 0)))) break block26;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw em.c(concurrentModificationException);
                                    }
                                }
                                return blockPos2.func_177982_a(0, n8, n6);
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw em.c(concurrentModificationException);
                            }
                        }
                        ++n8;
                    }
                }
                block24: for (n9 = 0; n9 < n5; ++n9) {
                    blockPos2 = blockPos2.func_177982_a(n6, 0, 0);
                    n8 = -n4;
                    while (true) {
                        block28: {
                            try {
                                block29: {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    if (n8 >= n4 + 1) continue block24;
                                                    if (this.field_70170_p.func_180495_p(blockPos2.func_177982_a(n6, n8, 0)).func_177230_c() != block) break block28;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw em.c(concurrentModificationException);
                                                }
                                                if (++n7 < n2) break block28;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw em.c(concurrentModificationException);
                                            }
                                            if (hashSet == null) break block29;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw em.c(concurrentModificationException);
                                        }
                                        if (!hashSet.contains(this.field_70170_p.func_180494_b(blockPos2.func_177982_a(n6, n8, 0)))) break block28;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw em.c(concurrentModificationException);
                                    }
                                }
                                return blockPos2.func_177982_a(n6, n8, 0);
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw em.c(concurrentModificationException);
                            }
                        }
                        ++n8;
                    }
                }
                ++n5;
            }
        }
        return null;
    }

    protected List<BlockPos> a(BlockPos blockPos, Class clazz, int n2, int n3, @Nullable HashSet<Biome> hashSet) {
        int n4 = 1;
        int n5 = -1;
        BlockPos blockPos2 = blockPos;
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        while (n4 < n2) {
            for (int i2 = 0; i2 < 2; ++i2) {
                int n6;
                int n7;
                n5 *= -1;
                block18: for (n7 = 0; n7 < n4; ++n7) {
                    blockPos2 = blockPos2.func_177982_a(0, 0, n5);
                    n6 = -n3;
                    while (true) {
                        block22: {
                            try {
                                block23: {
                                    try {
                                        try {
                                            try {
                                                if (n6 >= n3 + 1) continue block18;
                                                if (!clazz.isInstance(this.field_70170_p.func_180495_p(blockPos2.func_177982_a(0, n6, n5)).func_177230_c())) break block22;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw em.c(concurrentModificationException);
                                            }
                                            if (hashSet == null) break block23;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw em.c(concurrentModificationException);
                                        }
                                        if (!hashSet.contains(this.field_70170_p.func_180494_b(blockPos2.func_177982_a(n5, n6, 0)))) break block22;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw em.c(concurrentModificationException);
                                    }
                                }
                                arrayList.add(blockPos2.func_177982_a(0, n6, n5));
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw em.c(concurrentModificationException);
                            }
                        }
                        ++n6;
                    }
                }
                block20: for (n7 = 0; n7 < n4; ++n7) {
                    blockPos2 = blockPos2.func_177982_a(n5, 0, 0);
                    n6 = -n3;
                    while (true) {
                        block24: {
                            try {
                                block25: {
                                    try {
                                        try {
                                            try {
                                                if (n6 >= n3 + 1) continue block20;
                                                if (!clazz.isInstance(this.field_70170_p.func_180495_p(blockPos2.func_177982_a(n5, n6, 0)).func_177230_c())) break block24;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw em.c(concurrentModificationException);
                                            }
                                            if (hashSet == null) break block25;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw em.c(concurrentModificationException);
                                        }
                                        if (!hashSet.contains(this.field_70170_p.func_180494_b(blockPos2.func_177982_a(n5, n6, 0)))) break block24;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw em.c(concurrentModificationException);
                                    }
                                }
                                arrayList.add(blockPos2.func_177982_a(n5, n6, 0));
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw em.c(concurrentModificationException);
                            }
                        }
                        ++n6;
                    }
                }
                ++n4;
            }
        }
        return arrayList;
    }

    public boolean J() {
        boolean bl2;
        try {
            bl2 = !((String)this.m.func_187225_a(v)).equals("");
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return bl2;
    }

    @Nullable
    public UUID O() {
        String string = (String)this.m.func_187225_a(v);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw em.c(illegalArgumentException);
        }
        try {
            return UUID.fromString(string);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    @Nullable
    public EntityPlayer z() {
        UUID uUID = this.O();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return this.field_70170_p.func_152378_a(uUID);
    }

    protected ResourceLocation func_184647_J() {
        return dz.d;
    }

    @SideOnly(value=Side.CLIENT)
    public void a(String string, UUID uUID) {
    }

    @SideOnly(value=Side.CLIENT)
    protected abstract <E extends IAnimatable> PlayState a(AnimationEvent<E> var1);

    @SideOnly(value=Side.CLIENT)
    protected boolean a(fp fp2, String string, boolean bl2, AnimationEvent animationEvent) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    protected void a(String string, boolean bl2, AnimationEvent animationEvent, boolean bl3) {
        ILoopType.EDefaultLoopTypes eDefaultLoopTypes;
        block8: {
            try {
                try {
                    try {
                        if (bl3 || !fp.b(this, animationEvent.getPartialTick())) break block8;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                    if (!this.a(this.y(), string, d3.d, animationEvent)) break block8;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        try {
            eDefaultLoopTypes = bl2 ? ILoopType.EDefaultLoopTypes.LOOP : ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        ILoopType.EDefaultLoopTypes eDefaultLoopTypes2 = eDefaultLoopTypes;
        animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation(string, eDefaultLoopTypes2));
        animationEvent.getController().transitionLengthTicks = 0.0;
    }

    @SideOnly(value=Side.CLIENT)
    protected void a(String string, boolean bl2, AnimationEvent animationEvent) {
        this.a(string, bl2, animationEvent, false);
    }

    @SideOnly(value=Side.CLIENT)
    protected void a(String string, int n2, float f10, AnimationEvent animationEvent, boolean bl2) {
        int n3;
        Integer n4;
        String string2;
        HashMap<String, Pair<Integer, Integer>> hashMap;
        String string3;
        AnimationBuilder animationBuilder;
        AnimationController animationController;
        int n5;
        int n6;
        AnimationController animationController2;
        block16: {
            String string4;
            AnimationBuilder animationBuilder2;
            AnimationController animationController3;
            block18: {
                block17: {
                    block15: {
                        try {
                            try {
                                try {
                                    if (bl2 || !fp.b(this, animationEvent.getPartialTick())) break block15;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw em.c(concurrentModificationException);
                                }
                                if (!this.a(this.y(), string, d3.d, animationEvent)) break block15;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw em.c(concurrentModificationException);
                            }
                            return;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw em.c(concurrentModificationException);
                        }
                    }
                    animationController2 = animationEvent.getController();
                    Pair pair = this.A.get(string);
                    if (pair == null) {
                        pair = Pair.of((Object)0, (Object)0);
                    }
                    n6 = (Integer)pair.first();
                    n5 = (Integer)pair.second();
                    try {
                        try {
                            if (fp.b(this, animationEvent.getPartialTick())) break block16;
                            animationController3 = animationEvent.getController();
                            animationBuilder2 = new AnimationBuilder();
                            if (n6 != 0) break block17;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw em.c(concurrentModificationException);
                        }
                        string4 = string;
                        break block18;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                }
                string4 = string + n6;
            }
            animationController3.setAnimation(animationBuilder2.addAnimation(string4, ILoopType.EDefaultLoopTypes.LOOP));
            animationEvent.getController().transitionLengthTicks = 0.0;
            return;
        }
        int n7 = this.a(n6, n5, n2, f10);
        try {
            animationController = animationController2;
            animationBuilder = new AnimationBuilder();
            string3 = n7 == 0 ? string : string + n7;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            animationController.setAnimation(animationBuilder.addAnimation(string3, ILoopType.EDefaultLoopTypes.LOOP));
            animationController2.transitionLengthTicks = 0.0;
            hashMap = this.A;
            string2 = string;
            n4 = n7;
            n3 = n7 == 0 ? n5 : n7;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        hashMap.put(string2, (Pair<Integer, Integer>)Pair.of((Object)n4, (Object)n3));
    }

    @SideOnly(value=Side.CLIENT)
    protected void a(String string, int n2, float f10, AnimationEvent animationEvent) {
        this.a(string, n2, f10, animationEvent, false);
    }

    /*
     * Exception decompiling
     */
    int a(int var1_1, int var2_2, int var3_3, float var4_4) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [6[DOLOOP]], but top level block is 2[TRYBLOCK]
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
    public abstract void registerControllers(AnimationData var1);

    protected void s() {
        block8: {
            block7: {
                try {
                    try {
                        if (!this.field_70170_p.field_72995_K || !this.n()) break block7;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                    this.B = null;
                    ge.b.sendToServer((IMessage)new s(this.f(), true));
                    break block8;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            try {
                if (!this.field_70170_p.field_72995_K) {
                    s.a.a((EntityPlayerMP)this.field_70170_p.func_152378_a(this.ae()));
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
    }

    public static em c(EntityPlayer entityPlayer) {
        try {
            if (entityPlayer == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return em.i(entityPlayer.getPersistentID());
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3d a(Minecraft minecraft, cy cy2, EntityLivingBase entityLivingBase, float f10) {
        return com.trolmastercard.sexmod.b.a(minecraft, cy2, entityLivingBase, this, f10);
    }

    public static em i(@Nonnull UUID uUID) {
        return em.a(uUID, null);
    }

    public static em a(@Nonnull UUID uUID, Boolean bl2) {
        try {
            for (em em2 : em.ad()) {
                boolean bl3;
                block14: {
                    try {
                        if (em2.field_70128_L) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                    try {
                        if (!uUID.equals(em2.ae())) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                    if (bl2 == null) {
                        return em2;
                    }
                    bl3 = em2.field_70170_p.field_72995_K;
                    try {
                        if (!bl3 || bl2.booleanValue()) break block14;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                    return em2;
                }
                try {
                    if (bl3 || !bl2.booleanValue()) continue;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                return em2;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return null;
    }

    @Nullable
    public static em c(@Nonnull UUID uUID) {
        boolean bl2;
        try {
            bl2 = FMLCommonHandler.instance().getMinecraftServerInstance() == null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        boolean bl3 = bl2;
        try {
            for (em em2 : em.ad()) {
                try {
                    if (em2.field_70128_L) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                boolean bl4 = em2.field_70170_p.field_72995_K;
                try {
                    if (bl4 != bl3) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                if (!uUID.equals(em2.ae())) continue;
                return em2;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return null;
    }

    public static em d(@Nonnull EntityPlayer entityPlayer) {
        return em.c(entityPlayer.getPersistentID());
    }

    @SideOnly(value=Side.CLIENT)
    public void ac() {
    }

    public void r() {
        try {
            this.B = null;
            this.func_189654_d(false);
            this.b((fp)null);
            if (this.field_70170_p.field_72995_K) {
                this.V();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    protected void V() {
        try {
            if (this.n()) {
                d3.a(true);
                Minecraft.func_71410_x().field_71439_g.func_82142_c(false);
                ge.b.sendToServer((IMessage)new s(this.f()));
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void k(UUID uUID) {
        try {
            for (em em2 : em.ad()) {
                UUID uUID2 = em2.ae();
                try {
                    if (uUID2 == null) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                try {
                    if (!uUID2.equals(uUID)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                fp fp2 = em2.c(em2.y());
                if (fp2 == null) {
                    return;
                }
                em2.b(fp2);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void f(UUID uUID) {
        try {
            for (em em2 : em.ad()) {
                try {
                    if (em2.field_70128_L) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                try {
                    if (!em2.field_70170_p.field_72995_K) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                UUID uUID2 = em2.ae();
                try {
                    if (uUID2 == null) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                try {
                    if (!uUID2.equals(uUID)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                fp fp2 = em2.a(em2.y());
                try {
                    if (fp2 == null) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                em2.b(fp2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

    public void N() {
        this.ag();
        ge.b.sendToServer((IMessage)new a1(this.f()));
    }

    @SideOnly(value=Side.CLIENT)
    public void ag() {
        this.C.tickOffset = 0.0;
    }

    @SideOnly(value=Side.CLIENT)
    @Nullable
    protected abstract fp c(fp var1);

    @SideOnly(value=Side.CLIENT)
    protected abstract fp a(fp var1);

    public NetworkRegistry.TargetPoint P() {
        return new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 50.0);
    }

    protected void a(double d10, double d11, double d12, float f10, float f11) {
        try {
            if (this.ae() == null) {
                System.out.println("couldnt move camera because the player isn't set");
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        try {
            if (this.B == null) {
                this.B = entityPlayer.func_174791_d();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        Vec3d vec3d = this.B;
        vec3d = vec3d.func_72441_c(-Math.sin((double)(this.r + 90.0f) * (Math.PI / 180)) * d10, 0.0, Math.cos((double)(this.r + 90.0f) * (Math.PI / 180)) * d10);
        vec3d = vec3d.func_72441_c(0.0, d11, 0.0);
        vec3d = vec3d.func_72441_c(-Math.sin((double)this.r * (Math.PI / 180)) * d12, 0.0, Math.cos((double)this.r * (Math.PI / 180)) * d12);
        try {
            if (this.field_70170_p.field_72995_K) {
                ge.b.sendToServer((IMessage)new a8(entityPlayer.getPersistentID().toString(), vec3d, this.r + f10, f11));
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        entityPlayer.func_70080_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, this.r + f10, f11);
        entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
    }

    @SideOnly(value=Side.CLIENT)
    protected boolean n() {
        boolean bl2;
        block8: {
            block7: {
                try {
                    if (!this.field_70170_p.field_72995_K) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
                try {
                    try {
                        if (!entityPlayerSP.getPersistentID().equals(this.ae()) && !entityPlayerSP.func_110124_au().equals(this.ae())) break block7;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                    bl2 = true;
                    break block8;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    protected void U() {
    }

    public void g(String string) {
        this.m.func_187227_b(c, (Object)string);
    }

    public String w() {
        return (String)this.m.func_187225_a(c);
    }

    public abstract String c();

    public String ab() {
        String string = (String)this.m.func_187225_a(c);
        try {
            if (!"".equals(string)) {
                return string;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return this.c();
    }

    public abstract float i();

    @SideOnly(value=Side.CLIENT)
    public boolean t() {
        return true;
    }

    public void h(String string) {
        block6: {
            block5: {
                try {
                    if (this.field_70170_p.field_72995_K) break block5;
                    ge.b.sendToAllAround((IMessage)new gh(String.format("<%s> %s", this.ab(), string), this.field_71093_bK, this.f()), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 40.0));
                    break block6;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            try {
                if (this.n()) {
                    ge.b.sendToServer((IMessage)new gh(String.format("<%s> %s", this.ab(), string), this.field_71093_bK, this.f()));
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
    }

    protected void b(String string, boolean bl2) {
        try {
            if (!bl2) {
                this.h(string);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (!this.field_70170_p.field_72995_K) {
                ge.b.sendToAllAround((IMessage)new gh(string, this.field_71093_bK, this.f()), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 40.0));
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (this.n()) {
                ge.b.sendToServer((IMessage)new gh(string, this.field_71093_bK, this.f()));
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    protected void a(String string) {
        try {
            if (this.field_70170_p.field_72995_K) {
                Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(String.format("<%s> %s", this.ab(), string)));
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    protected void a(UUID uUID, String string) {
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                System.out.println("Player with UUID " + uUID.toString() + " not found");
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("<" + entityPlayer.func_70005_c_() + "> " + string));
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    public void a(SoundEvent soundEvent, float f10, float f11) {
        this.field_70170_p.func_184134_a((double)this.func_180425_c().func_177958_n(), (double)this.func_180425_c().func_177956_o(), (double)this.func_180425_c().func_177952_p(), soundEvent, SoundCategory.NEUTRAL, f10, f11, false);
    }

    public void a(SoundEvent soundEvent) {
        this.a(soundEvent, 1.0f, 1.0f);
    }

    public void a(SoundEvent[] soundEventArray, int ... nArray) {
        try {
            if (nArray.length == 0) {
                this.a(soundEventArray[this.func_70681_au().nextInt(soundEventArray.length)]);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        this.a(soundEventArray[nArray[this.func_70681_au().nextInt(nArray.length)]], 1.0f, 1.0f);
    }

    public void a(SoundEvent[] soundEventArray, float f10) {
        this.a(soundEventArray[this.func_70681_au().nextInt(soundEventArray.length)], f10, 1.0f);
    }

    public void a(SoundEvent soundEvent, float f10) {
        this.a(soundEvent, f10, 1.0f);
    }

    public static boolean a(Entity entity) {
        boolean bl2;
        try {
            if (entity == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (!(entity instanceof em)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            bl2 = !(entity instanceof ei);
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return bl2;
    }

    @SideOnly(value=Side.CLIENT)
    public em E() {
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean e() {
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 50.0);
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return entityPlayer.getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID());
    }

    public Vec3d aa() {
        return this.a(1.0);
    }

    public Vec3d a(double d10) {
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        float f10 = entityPlayer.field_70177_z;
        return entityPlayer.func_174791_d().func_72441_c(-Math.sin((double)f10 * (Math.PI / 180)) * d10, 0.0, Math.cos((double)f10 * (Math.PI / 180)) * d10);
    }

    public Vec3d a(Vec3d vec3d, float f10) {
        return vec3d;
    }

    public static void a(EnumParticleTypes enumParticleTypes, em em2) {
        double d10 = com.trolmastercard.sexmod.r.f.nextGaussian() * 0.02;
        double d11 = com.trolmastercard.sexmod.r.f.nextGaussian() * 0.02;
        double d12 = com.trolmastercard.sexmod.r.f.nextGaussian() * 0.02;
        em2.field_70170_p.func_175688_a(enumParticleTypes, em2.field_70165_t + (double)(com.trolmastercard.sexmod.r.f.nextFloat() * em2.field_70130_N * 2.0f) - (double)em2.field_70130_N, em2.field_70163_u + 0.5 + (double)(com.trolmastercard.sexmod.r.f.nextFloat() * em2.field_70131_O), em2.field_70161_v + (double)(com.trolmastercard.sexmod.r.f.nextFloat() * em2.field_70130_N * 2.0f) - (double)em2.field_70130_N, d10, d11, d12, new int[0]);
    }

    public static void a(EnumParticleTypes enumParticleTypes, em em2, int n2) {
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                em.a(enumParticleTypes, em2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return this.g;
    }

    public boolean func_70104_M() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    protected SoundEvent func_184639_G() {
        block6: {
            block7: {
                try {
                    try {
                        try {
                            if (this.func_70681_au().nextInt(10000) != 0) break block6;
                            if (!this.field_70170_p.field_72995_K) break block7;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw em.c(concurrentModificationException);
                        }
                        if (!(Minecraft.func_71410_x().field_71439_g.func_174791_d().func_72438_d(this.func_174791_d()) < 10.0)) break block7;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw em.c(concurrentModificationException);
                    }
                    this.a("whopa");
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            return com.trolmastercard.sexmod.c.a(com.trolmastercard.sexmod.c.MISC_FART);
        }
        return null;
    }

    public float T() {
        return 0.0f;
    }

    public float ai() {
        return 0.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public MatrixStack a(String string, boolean bl2) {
        Object object;
        ArrayList<GeoBone> arrayList;
        GeoBone geoBone;
        block17: {
            block16: {
                IBone iBone;
                block14: {
                    block15: {
                        try {
                            if (this.H == null) {
                                this.H = this.b();
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw em.c(concurrentModificationException);
                        }
                        iBone = this.H.getBone(string);
                        try {
                            try {
                                if (iBone != null) break block14;
                                if (cv.e.contains(string)) break block15;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw em.c(concurrentModificationException);
                            }
                            Main.LOGGER.log(Level.WARN, String.format("The bone '%s' does not exist on %s. Bone model matrix couldn't be calculated", string, this.c()));
                            this.p.remove(string);
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw em.c(concurrentModificationException);
                        }
                    }
                    return new MatrixStack();
                }
                geoBone = (GeoBone)iBone;
                arrayList = new ArrayList<GeoBone>();
                object = geoBone;
                while (((GeoBone)object).parent != null) {
                    GeoBone geoBone2 = ((GeoBone)object).parent;
                    arrayList.add(geoBone2);
                    object = geoBone2;
                }
                Collections.reverse(arrayList);
                object = new MatrixStack();
                try {
                    if (!this.Q()) break block16;
                    ((MatrixStack)object).rotateY((float)(-Math.toRadians(this.I().floatValue())));
                    break block17;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
            }
            try {
                if (bl2) {
                    ((MatrixStack)object).rotateY((float)(-Math.toRadians(b6.a(this.field_70760_ar, this.field_70761_aq, Minecraft.func_71410_x().func_184121_ak()))));
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        for (GeoBone geoBone3 : arrayList) {
            ((MatrixStack)object).translate(geoBone3);
            ((MatrixStack)object).moveToPivot(geoBone3);
            ((MatrixStack)object).rotate(geoBone3);
            ((MatrixStack)object).scale(geoBone3);
            ((MatrixStack)object).moveBackFromPivot(geoBone3);
        }
        ((MatrixStack)object).translate(geoBone);
        ((MatrixStack)object).moveToPivot(geoBone);
        ((MatrixStack)object).rotate(geoBone);
        ((MatrixStack)object).scale(geoBone);
        object = this.a((MatrixStack)object);
        return object;
    }

    protected MatrixStack a(MatrixStack matrixStack) {
        return matrixStack;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3d b(String string) {
        Vec3d vec3d = this.x.get(string);
        try {
            if (vec3d != null) {
                return vec3d;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (!this.p.contains(string)) {
                this.p.add(string);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return Vec3d.field_186680_a;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3d d(String string) {
        return this.b(string).func_178787_e(this.func_174791_d());
    }

    public void a(String string, Vec3d vec3d) {
        this.x.put(string, vec3d);
    }

    @SideOnly(value=Side.CLIENT)
    public float R() {
        AnimationProcessor<?> animationProcessor = this.b();
        IBone iBone = animationProcessor.getBone("girlCam");
        try {
            if (iBone == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        float f10 = iBone.getPivotY();
        f10 = this.a(f10);
        return f10 / 16.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public float v() {
        return 1.0f;
    }

    protected float a(float f10) {
        return f10;
    }

    public AnimatedGeoModel<? extends em> a() {
        Minecraft minecraft = Minecraft.func_71410_x();
        Render render = minecraft.func_175598_ae().func_78713_a((Entity)this);
        try {
            if (render == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (!(render instanceof d_)) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        GeoEntityRenderer geoEntityRenderer = (GeoEntityRenderer)render;
        GeoModelProvider geoModelProvider = geoEntityRenderer.getGeoModelProvider();
        try {
            if (geoModelProvider == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (!(geoModelProvider instanceof AnimatedGeoModel)) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return (AnimatedGeoModel)geoModelProvider;
    }

    public AnimationProcessor<?> b() {
        return this.a().getAnimationProcessor();
    }

    public boolean h(int n2) {
        boolean bl2;
        ArrayList<Integer> arrayList = this.D();
        try {
            if (arrayList.size() - 1 < n2) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            bl2 = (Integer)arrayList.get(n2) == 101;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return bl2;
    }

    public e1 g(int n2) {
        return e1.a;
    }

    public void a(List<Integer> list) {
        block5: {
            try {
                try {
                    if (this instanceof e4 || this instanceof ew) break block5;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int n2 : list) {
            e4.c(stringBuilder, n2);
        }
        this.m.func_187227_b(e4.M, (Object)stringBuilder.toString());
    }

    public String F() {
        block4: {
            try {
                try {
                    if (!(this instanceof e4) && !(this instanceof ew)) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                return (String)this.m.func_187225_a(e4.M);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        return "";
    }

    public static String c(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int n2 : list) {
            stringBuilder.append(n2);
            stringBuilder.append("-");
        }
        return stringBuilder.toString();
    }

    public static List<Integer> c(String string) {
        String[] stringArray;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (String string2 : stringArray = string.split("-")) {
            arrayList.add(Integer.parseInt(string2));
        }
        return arrayList;
    }

    public static List<Integer> h(UUID uUID) {
        ArrayList<Integer> arrayList;
        block6: {
            em em2;
            try {
                em2 = Main.proxy instanceof ClientProxy ? em.b(uUID) : em.a(uUID);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            em em3 = em2;
            arrayList = new ArrayList<Integer>(em3.L());
            try {
                try {
                    if (!(em3 instanceof e4) && !(em3 instanceof ew)) break block6;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                arrayList.addAll(em.c((String)em3.func_184212_Q().func_187225_a(e4.M)));
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
        return arrayList;
    }

    public ArrayList<Integer> L() {
        return new ArrayList<Integer>();
    }

    public List<Map.Entry<gw, Map.Entry<List<String>, Integer>>> d(UUID uUID) {
        try {
            if (this.d != null) {
                return this.d;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        ArrayList<Integer> arrayList = this.D();
        try {
            if (arrayList.isEmpty()) {
                this.d = new ArrayList<Map.Entry<gw, Map.Entry<List<String>, Integer>>>();
                return this.d;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        ArrayList<Map.Entry<gw, Map.Entry<List<String>, Integer>>> arrayList2 = new ArrayList<Map.Entry<gw, Map.Entry<List<String>, Integer>>>();
        List<Integer> list = em.h(uUID);
        try {
            for (int i2 = 0; i2 < arrayList.size(); ++i2) {
                arrayList2.add(new AbstractMap.SimpleEntry<gw, AbstractMap.SimpleEntry<List<String>, Integer>>(gw.GIRL_SPECIFIC, new AbstractMap.SimpleEntry<List<String>, Integer>(this.e((Integer)arrayList.get(i2)), list.get(i2))));
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        this.d = arrayList2;
        return arrayList2;
    }

    public void b(List<Map.Entry<gw, Map.Entry<List<String>, Integer>>> list) {
        this.d = list;
    }

    public void a(int n2, int n3) {
        try {
            if (this.d == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (this.d.size() - 1 < n2) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        Map.Entry<gw, Map.Entry<List<String>, Integer>> entry = this.d.get(n2);
        entry.getValue().setValue(n3);
        this.d.set(n2, entry);
    }

    public void e(String string) {
        block4: {
            try {
                try {
                    if (!(this instanceof e4) && !(this instanceof ew)) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw em.c(concurrentModificationException);
                }
                this.m.func_187227_b(e4.M, (Object)string);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
        }
    }

    private List<String> e(int n2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                arrayList.add("");
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        return arrayList;
    }

    public ArrayList<Integer> D() {
        return new ArrayList<Integer>();
    }

    public List<Integer> u() {
        return new ArrayList<Integer>();
    }

    public void f(String string) {
        this.m.func_187227_b(b, (Object)string);
    }

    public String C() {
        return (String)this.m.func_187225_a(b);
    }

    public static String a(HashSet<String> hashSet) {
        try {
            if (hashSet == null) {
                return "";
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        try {
            if (hashSet.isEmpty()) {
                return "";
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw em.c(concurrentModificationException);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : hashSet) {
            stringBuilder.append(string);
            stringBuilder.append("#");
        }
        return stringBuilder.toString();
    }

    public HashSet<String> Y() {
        String string = this.C();
        String[] stringArray = string.split("#");
        HashSet<String> hashSet = new HashSet<String>();
        for (String string2 : stringArray) {
            try {
                if ("".equals(string2)) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            try {
                if ("cross".equals(string2)) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw em.c(concurrentModificationException);
            }
            hashSet.add(string2);
        }
        return hashSet;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean H() {
        return true;
    }

    private static RuntimeException c(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static enum a {
        WALK,
        FAST_WALK,
        RUN;

    }
}

