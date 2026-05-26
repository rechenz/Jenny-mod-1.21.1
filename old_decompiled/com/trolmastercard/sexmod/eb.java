/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWater
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Biomes
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.bh;
import com.trolmastercard.sexmod.c;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fg;
import com.trolmastercard.sexmod.fh;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gh;
import com.trolmastercard.sexmod.gi;
import com.trolmastercard.sexmod.gp;
import com.trolmastercard.sexmod.m;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
public class eb
extends e2
implements bh,
fg {
    public static double ap = 0.01;
    public ItemStack ao = new ItemStack((Item)gp.a);
    public static final DataParameter<Float> Y = EntityDataManager.func_187226_a(eb.class, (DataSerializer)DataSerializers.field_187193_c).func_187156_b().func_187161_a(121);
    public static final DataParameter<ItemStack> az = EntityDataManager.func_187226_a(eb.class, (DataSerializer)DataSerializers.field_187196_f).func_187156_b().func_187161_a(120);
    public static final DataParameter<Boolean> af = EntityDataManager.func_187226_a(eb.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(119);
    public static final DataParameter<ItemStack> ag = EntityDataManager.func_187226_a(eb.class, (DataSerializer)DataSerializers.field_187196_f).func_187156_b().func_187161_a(118);
    static final float ah = 3.0f;
    static final float ax = 1200.0f;
    @Nullable
    public gi av;
    public float aa = 1.0f;
    public float Z = 0.0f;
    int aj = 8000;
    public boolean ac = false;
    int aw = 0;
    boolean ay = false;
    int ak = 0;
    int ab = 0;
    public BlockPos ai;
    int at = 0;
    int as = 0;
    boolean am;
    long al = 0L;
    boolean ar = false;
    Path au = null;
    int aq = 0;
    HashSet<BlockPos> an = new HashSet();
    boolean ae = false;
    boolean ad = false;

    public eb(World world) {
        super(world);
        this.P = 230;
        this.O = 150;
        this.K = 320;
        this.V = new Vec3d(0.0, -0.05999999718368053, 0.10000001192092894);
        if (this.Q.getStackInSlot(0) == ItemStack.field_190927_a) {
            this.Q.setStackInSlot(0, new ItemStack(Items.field_151036_c));
        }
        try {
            if (this.Q.getStackInSlot(6) == ItemStack.field_190927_a) {
                this.Q.setStackInSlot(6, new ItemStack((Item)Items.field_151112_aM));
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
    }

    @Override
    public String c() {
        return "Luna";
    }

    @Override
    public float i() {
        return -0.2f;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.m.func_187214_a(Y, (Object)Float.valueOf(0.0f));
        this.m.func_187214_a(az, (Object)ItemStack.field_190927_a);
        this.m.func_187214_a(af, (Object)false);
        this.m.func_187214_a(ag, (Object)ItemStack.field_190927_a);
    }

    @Override
    public void c() {
        this.a("Love it here owo");
        this.a(com.trolmastercard.sexmod.c.GIRLS_LUNA_OWO, new int[0]);
    }

    @Override
    public void b(fp fp2) {
        block14: {
            block12: {
                try {
                    block13: {
                        try {
                            try {
                                if (this.y() != fp.COWGIRL_SITTING_CUM) break block12;
                                if (fp2 == fp.COWGIRL_SITTING_SLOW) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw eb.a(runtimeException);
                            }
                            if (fp2 != fp.COWGIRL_SITTING_FAST) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.y() != fp.TOUCH_BOOBS_CUM) break block14;
                            if (fp2 == fp.TOUCH_BOOBS_FAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                        if (fp2 != fp.TOUCH_BOOBS_SLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eb.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    @Override
    public void b() {
        this.ac = true;
    }

    public float func_70047_e() {
        return 1.34f;
    }

    public boolean func_184645_a(EntityPlayer entityPlayer, EnumHand enumHand) {
        block12: {
            boolean bl2;
            try {
                if (super.func_184645_a(entityPlayer, enumHand)) {
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            ItemStack itemStack = entityPlayer.func_184586_b(enumHand);
            try {
                bl2 = itemStack.func_77973_b() == Items.field_151057_cb;
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            boolean bl3 = bl2;
            try {
                if (bl3) {
                    itemStack.func_111282_a(entityPlayer, (EntityLivingBase)this, enumHand);
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            try {
                try {
                    if (!this.field_70170_p.field_72995_K || this.b(entityPlayer)) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
                this.a(I18n.func_135052_a((String)"bia.dialogue.busy", (Object[])new Object[0]));
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
        }
        return true;
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        String[] stringArray = new String[]{"action.names.sex", "action.names.touchboobs", "action.names.headpat"};
        ItemStack[] itemStackArray = new ItemStack[]{new ItemStack(Items.field_151115_aP, 3, 0), new ItemStack(Items.field_151115_aP, 2, 1), null};
        eb.a(entityPlayer, (em)this, stringArray, itemStackArray);
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    protected static void a(EntityPlayer entityPlayer, em em2, String[] stringArray, ItemStack[] itemStackArray) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(em2, entityPlayer, stringArray, itemStackArray, true));
    }

    public void b(ItemStack itemStack) {
        this.m.func_187227_b(ag, (Object)itemStack);
    }

    @Override
    public void g() {
        this.z = new EntityAIWanderAvoidWater((EntityCreature)this, 0.35);
        this.o = new df((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.o);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.z);
    }

    @Override
    public void func_70619_bc() {
        block32: {
            block34: {
                block31: {
                    block29: {
                        boolean bl2;
                        DataParameter<Boolean> dataParameter;
                        EntityDataManager entityDataManager;
                        block28: {
                            block27: {
                                block26: {
                                    block25: {
                                        try {
                                            super.func_70619_bc();
                                            if (this.J()) break block25;
                                            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(1.0);
                                            break block26;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw eb.a(runtimeException);
                                        }
                                    }
                                    this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5);
                                }
                                try {
                                    try {
                                        this.m();
                                        this.i();
                                        entityDataManager = this.m;
                                        dataParameter = af;
                                        if (this.av == null || this.m.func_187225_a(ag) != ItemStack.field_190927_a) break block27;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw eb.a(runtimeException);
                                    }
                                    bl2 = true;
                                    break block28;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw eb.a(runtimeException);
                                }
                            }
                            bl2 = false;
                        }
                        try {
                            try {
                                entityDataManager.func_187227_b(dataParameter, (Object)bl2);
                                if (this.al != this.field_70170_p.func_82737_E() || this.av == null) break block29;
                            }
                            catch (RuntimeException runtimeException) {
                                throw eb.a(runtimeException);
                            }
                            this.field_70170_p.func_72900_e((Entity)this.av);
                            this.av = null;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                    }
                    if (this.ay) {
                        block30: {
                            double d10 = this.o().func_72438_d(this.func_174791_d());
                            try {
                                try {
                                    if (!(d10 < 0.5) && this.ak <= 200) break block30;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw eb.a(runtimeException);
                                }
                                this.ay = false;
                                this.ak = 0;
                                this.m.func_187227_b(G, (Object)true);
                                this.field_70145_X = true;
                                this.func_189654_d(true);
                                this.field_70159_w = 0.0;
                                this.field_70181_x = 0.0;
                                this.field_70179_y = 0.0;
                                this.b(fp.WAIT_CAT);
                                break block31;
                            }
                            catch (RuntimeException runtimeException) {
                                throw eb.a(runtimeException);
                            }
                        }
                        try {
                            try {
                                if (++this.ak != 60 && this.ak != 120) break block31;
                            }
                            catch (RuntimeException runtimeException) {
                                throw eb.a(runtimeException);
                            }
                            this.func_70661_as().func_75499_g();
                            this.func_70661_as().func_75492_a(this.o().field_72450_a, this.o().field_72448_b, this.o().field_72449_c, 0.2);
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                    }
                }
                try {
                    block33: {
                        try {
                            try {
                                if (!this.ac) break block32;
                                ++this.aw;
                                if (this.func_174791_d().equals((Object)this.o())) break block33;
                            }
                            catch (RuntimeException runtimeException) {
                                throw eb.a(runtimeException);
                            }
                            if (this.aw <= 40) break block34;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                    }
                    this.ac = false;
                    this.aw = 0;
                    this.b(this.field_70170_p.func_73046_m().func_184103_al().func_177451_a((UUID)this.ae()).field_70177_z + 180.0f);
                    this.m.func_187227_b(G, (Object)true);
                    this.func_70661_as().func_75499_g();
                    this.U();
                    break block32;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
            }
            this.field_70177_z = this.I().floatValue();
            this.func_189654_d(false);
            Vec3d vec3d = b6.a(this.func_174791_d(), this.o(), 40 - this.aw);
            this.func_70107_b(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        }
        this.d();
        this.m.func_187227_b(az, (Object)this.Q.getStackInSlot(6));
    }

    void d() {
        ItemStack itemStack = this.ao;
        ItemStack itemStack2 = (ItemStack)this.m.func_187225_a(az);
        try {
            if (itemStack2.equals(ItemStack.field_190927_a)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        Map map = EnchantmentHelper.func_82781_a((ItemStack)itemStack2);
        EnchantmentHelper.func_82782_a((Map)map, (ItemStack)itemStack);
    }

    @Override
    public void func_70071_h_() {
        block3: {
            block2: {
                try {
                    super.func_70071_h_();
                    if (!fp.WAIT_CAT.equals((Object)this.y())) break block2;
                    this.f();
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
            }
            this.ab = 0;
        }
    }

    void f() {
        block12: {
            EntityPlayer entityPlayer;
            block11: {
                entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 10.0);
                try {
                    if (entityPlayer == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
                try {
                    if (entityPlayer.func_70032_d((Entity)this) > 1.25f) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
                try {
                    if (!this.field_70170_p.field_72995_K) break block11;
                    this.a(entityPlayer, this.ab);
                    break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
            }
            try {
                if (this.ab == 25) {
                    this.e(entityPlayer.getPersistentID());
                    entityPlayer.func_191958_b(0.0f, 0.0f, 0.0f, 0.0f);
                    entityPlayer.func_70634_a(this.func_174791_d().field_72450_a, this.func_174791_d().field_72448_b, this.func_174791_d().field_72449_c);
                    this.b(fp.COWGIRL_SITTING_INTRO);
                    entityPlayer.func_70034_d(this.I().floatValue() + 180.0f);
                    entityPlayer.field_70177_z = this.I().floatValue() + 180.0f;
                    entityPlayer.field_70126_B = this.I().floatValue() + 180.0f;
                    this.r = this.I().floatValue() + 180.0f;
                    this.a(0.0, -0.075f, -0.7109375, 0.0f, 0.0f);
                    this.m.func_187227_b(D, (Object)0);
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
        }
        ++this.ab;
    }

    @SideOnly(value=Side.CLIENT)
    void a(EntityPlayer entityPlayer, int n2) {
        EntityPlayerSP entityPlayerSP;
        if (n2 == 0) {
            entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    fh.b();
                    entityPlayerSP.func_70016_h(0.0, 0.0, 0.0);
                    d3.a(false);
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
        }
        if (n2 == 25) {
            entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    Minecraft.func_71410_x().field_71474_y.field_74320_O = 2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
        }
    }

    @Override
    public void a() {
        block9: {
            BlockPos blockPos;
            block8: {
                this.m.func_187227_b(G, (Object)false);
                this.b(fp.NULL);
                this.ar = true;
                blockPos = this.a(this.func_180425_c());
                try {
                    if (blockPos != null) break block8;
                    this.a(com.trolmastercard.sexmod.c.GIRLS_LUNA_GIGGLE, new int[0]);
                    ge.b.sendToAllAround((IMessage)new gh("<" + this.c() + "> Heh.. there is no bed nearby.. but I already ate the fish so nya~ hehe", this.field_71093_bK, this.f()), this.P());
                    break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
            }
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
                        throw eb.a(runtimeException);
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
                    this.a(com.trolmastercard.sexmod.c.GIRLS_LUNA_GIGGLE, new int[0]);
                    this.a("Heh.. the bed is obscured.. but I already ate the fish so nya~ hehe");
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            Vec3d vec3d3 = vec3d.func_178787_e(vec3dArrayArray[n2][0]);
            this.b(nArray[n2]);
            this.c(new Vec3d(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c));
            this.r = this.I().floatValue();
            this.func_70661_as().func_75499_g();
            this.func_70661_as().func_75492_a(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c, 0.2);
            this.ay = true;
            this.ak = 0;
        }
    }

    public void j() {
        EntityItem entityItem = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, (ItemStack)this.m.func_187225_a(ag));
        Vec3d vec3d = ck.a(new Vec3d(0.0, (double)0.2f + Math.random() * (double)0.1f, (double)-0.2f + Math.random() * (double)-0.1f), this.field_70177_z);
        entityItem.field_70159_w = vec3d.field_72450_a;
        entityItem.field_70181_x = vec3d.field_72448_b;
        entityItem.field_70179_y = vec3d.field_72449_c;
        this.field_70170_p.func_72838_d((Entity)entityItem);
        this.m.func_187227_b(ag, (Object)ItemStack.field_190927_a);
    }

    public void q() {
        try {
            this.ai = null;
            this.at = 0;
            this.as = 0;
            this.am = false;
            this.m.func_187227_b(G, (Object)false);
            this.m.func_187227_b(ag, (Object)ItemStack.field_190927_a);
            this.func_174810_b(false);
            this.b(fp.NULL);
            if (this.av != null) {
                this.field_70170_p.func_72900_e((Entity)this.av);
                this.av = null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        try {
            if (this.ae() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        try {
            this.o = new df((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
            this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.o);
            if (this.J()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        this.z = new EntityAIWanderAvoidWater((EntityCreature)this, 0.35);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.z);
    }

    public void h() {
        try {
            this.q();
            if (++this.aq >= 3) {
                this.aq = 0;
                this.aj = 0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
    }

    void i() {
        block43: {
            ItemStack itemStack;
            block40: {
                block42: {
                    block41: {
                        block38: {
                            block39: {
                                try {
                                    try {
                                        block37: {
                                            try {
                                                try {
                                                    if (this.J() || this.ae() != null) break block37;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw eb.a(runtimeException);
                                                }
                                                if (!this.ar) break block38;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw eb.a(runtimeException);
                                            }
                                        }
                                        if (!((Boolean)this.m.func_187225_a(af)).booleanValue()) break block39;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw eb.a(runtimeException);
                                    }
                                    this.q();
                                }
                                catch (RuntimeException runtimeException) {
                                    throw eb.a(runtimeException);
                                }
                            }
                            return;
                        }
                        try {
                            int n2;
                            ++this.aj;
                            if ((float)n2 < 1200.0f) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                        try {
                            if (this.av == null || this.av.d != 15) break block40;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                        ((gp)this.ao.func_77973_b()).a(this.field_70170_p, this, EnumHand.MAIN_HAND);
                        this.al = this.field_70170_p.func_82737_E() + 20L;
                        itemStack = (ItemStack)this.m.func_187225_a(ag);
                        try {
                            if (itemStack != ItemStack.field_190927_a) break block41;
                            break block40;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                    }
                    try {
                        if (!(itemStack.func_77973_b() instanceof ItemFood)) break block42;
                        this.b(fp.FISHING_EAT);
                        break block40;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eb.a(runtimeException);
                    }
                }
                this.b(fp.FISHING_THROW_AWAY);
            }
            try {
                if (!this.y().toString().toLowerCase().contains("fishing")) {
                    this.n();
                    this.e();
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            try {
                try {
                    try {
                        try {
                            if (this.ai == null || this.au != null) break block43;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                        if (this.func_70661_as().func_75505_d() != null) break block43;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eb.a(runtimeException);
                    }
                    if (this.field_70171_ac) break block43;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
                if (!this.field_70122_E) break block43;
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            itemStack = this.field_70170_p.func_72901_a(this.func_174791_d().func_72441_c(0.0, (double)this.func_70047_e(), 0.0), new Vec3d((double)this.ai.func_177958_n(), (double)this.ai.func_177956_o(), (double)this.ai.func_177952_p()), true);
            try {
                this.func_174810_b(true);
                if (this.z != null) {
                    this.field_70714_bg.func_85156_a((EntityAIBase)this.z);
                    this.z = null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            try {
                if (this.o != null) {
                    this.field_70714_bg.func_85156_a((EntityAIBase)this.o);
                    this.o = null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            try {
                if (this.y() == fp.NULL) {
                    this.b(fp.FISHING_START);
                    this.c(this.func_174791_d());
                    this.m.func_187227_b(G, (Object)true);
                    this.b((float)Math.atan2(this.field_70161_v - (double)this.ai.func_177952_p(), this.field_70165_t - (double)this.ai.func_177958_n()) * 57.29578f + 90.0f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            return;
        }
        this.au = this.func_70661_as().func_75505_d();
    }

    public void o() {
        this.an.add(this.ai);
        this.q();
    }

    void e() {
        try {
            if (this.ai == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        PathNavigate pathNavigate = this.func_70661_as();
        pathNavigate.func_75492_a((double)this.ai.func_177958_n(), (double)this.ai.func_177956_o(), (double)this.ai.func_177952_p(), (double)0.35f);
        Path path = pathNavigate.func_75505_d();
        try {
            if (path == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        if (path.func_75874_d() > path.func_75873_e() + 1) {
            PathPoint pathPoint = path.func_75877_a(path.func_75873_e() + 1);
            PathPoint pathPoint2 = path.func_75877_a(path.func_75874_d() - 1);
            Vec3d vec3d = new Vec3d((double)pathPoint2.field_75839_a, (double)pathPoint2.field_75837_b, (double)pathPoint2.field_75838_c);
            BlockPos blockPos = new BlockPos(pathPoint.field_75839_a, pathPoint.field_75837_b, pathPoint.field_75838_c);
            try {
                if (this.func_174791_d().func_72438_d(vec3d) < 0.75) {
                    pathNavigate.func_75499_g();
                    this.func_70107_b(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            try {
                if (this.field_70170_p.func_180495_p(blockPos.func_177982_a(0, 1, 0)).func_177230_c() == Blocks.field_150355_j) {
                    pathNavigate.func_75499_g();
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            try {
                if (this.field_70170_p.func_180495_p(blockPos).func_177230_c() == Blocks.field_150355_j) {
                    pathNavigate.func_75499_g();
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
            try {
                if (this.field_70170_p.func_180495_p(blockPos.func_177982_a(0, -1, 0)).func_177230_c() == Blocks.field_150355_j) {
                    pathNavigate.func_75499_g();
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
        }
    }

    void n() {
        block26: {
            int n2;
            BlockPos blockPos;
            block25: {
                block24: {
                    int n3 = 0;
                    blockPos = null;
                    n2 = 0;
                    while (++n3 < 50) {
                        BlockPos blockPos2 = this.a(this.func_180425_c(), n3 + 1, (Block)Blocks.field_150355_j, 60, 10, new HashSet<Biome>(Arrays.asList(Biomes.field_76781_i, Biomes.field_76771_b, Biomes.field_150575_M, Biomes.field_76787_r, Biomes.field_150576_N, Biomes.field_76780_h, Biomes.field_150599_m)));
                        try {
                            if (blockPos2 == null) {
                                break;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                        while (this.field_70170_p.func_180495_p(blockPos2.func_177982_a(0, 1, 0)).func_177230_c() == Blocks.field_150355_j) {
                            blockPos2 = blockPos2.func_177982_a(0, 1, 0);
                        }
                        int n4 = 1;
                        BlockPos blockPos3 = blockPos2;
                        while (this.field_70170_p.func_180495_p(blockPos3.func_177982_a(0, -1, 0)).func_177230_c() == Blocks.field_150355_j) {
                            blockPos3 = blockPos3.func_177982_a(0, -1, 0);
                            ++n4;
                        }
                        try {
                            if (this.an.contains(blockPos2)) {
                                continue;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                        if (blockPos == null) {
                            blockPos = blockPos2;
                            n2 = n4;
                            continue;
                        }
                        if (n4 <= n2) continue;
                        blockPos = blockPos2;
                        n2 = n4;
                        try {
                            if (n2 < 6) continue;
                            break;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                    }
                    try {
                        if (blockPos == null) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw eb.a(runtimeException);
                    }
                    try {
                        try {
                            if (this.ai != null && this.at >= n2) break block24;
                        }
                        catch (RuntimeException runtimeException) {
                            throw eb.a(runtimeException);
                        }
                        this.ai = blockPos;
                        this.at = n2;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eb.a(runtimeException);
                    }
                }
                try {
                    if (!this.ai.equals(blockPos)) break block25;
                    this.as = 0;
                    break block26;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
            }
            try {
                if (++this.as > 20) {
                    this.ai = blockPos;
                    this.at = n2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
            }
        }
    }

    void m() {
        Path path = this.func_70661_as().func_75505_d();
        try {
            if (path == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        PathPoint pathPoint = path.func_75870_c();
        PathPoint pathPoint2 = new PathPoint(be.a(this.field_70165_t), be.a(this.field_70163_u), be.a(this.field_70161_v));
        try {
            if (pathPoint == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        this.m.func_187227_b(Y, (Object)Float.valueOf(pathPoint.func_75829_a(pathPoint2)));
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            super.a(string, uUID);
            if ("action.names.touchboobs".equals(string)) {
                this.e(uUID);
                this.a(true, true, uUID);
                this.a("animationFollowUp", "touch_boobs");
                this.a("currentModel", "0");
                d3.a(false);
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        try {
            if ("action.names.sex".equals(string)) {
                this.e(uUID);
                this.a(true, true, uUID);
                this.a("animationFollowUp", "sex");
                d3.a(false);
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        try {
            if ("action.names.headpat".equals(string)) {
                this.e(uUID);
                this.a(true, true, uUID);
                d3.a(false);
                this.a("animationFollowUp", "headpat");
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.TOUCH_BOOBS_SLOW) {
                return fp.TOUCH_BOOBS_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        try {
            if (fp2 == fp.COWGIRL_SITTING_SLOW) {
                return fp.COWGIRL_SITTING_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        block9: {
            block8: {
                try {
                    try {
                        if (fp2 != fp.TOUCH_BOOBS_SLOW && fp2 != fp.TOUCH_BOOBS_FAST) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw eb.a(runtimeException);
                    }
                    return fp.TOUCH_BOOBS_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
            }
            try {
                try {
                    if (fp2 != fp.COWGIRL_SITTING_FAST && fp2 != fp.COWGIRL_SITTING_SLOW) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw eb.a(runtimeException);
                }
                return fp.COWGIRL_SITTING_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw eb.a(runtimeException);
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
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[SWITCH]
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

    protected void func_184581_c(DamageSource damageSource) {
        this.a(com.trolmastercard.sexmod.c.GIRLS_LUNA_OUU, new int[0]);
    }

    @Nullable
    protected SoundEvent func_184615_bR() {
        try {
            if (this.func_70681_au().nextFloat() * 100.0f > 95.0f) {
                return com.trolmastercard.sexmod.c.GIRLS_ALLIE_SCAWY[2];
            }
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        return com.trolmastercard.sexmod.c.GIRLS_LUNA_OUU[12];
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(1.0);
    }

    protected float func_175134_bD() {
        float f10;
        try {
            f10 = this.func_70090_H() ? 1.0f : 0.5f;
        }
        catch (RuntimeException runtimeException) {
            throw eb.a(runtimeException);
        }
        return f10;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 13[SWITCH]
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
            throw eb.a(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 27[SWITCH]
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
        this.E.transitionLengthTicks = 10.0;
        this.C.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.C);
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
    }

    @Override
    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        super.func_70037_a(nBTTagCompound);
        this.func_189654_d(false);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a {
        @SubscribeEvent
        public void a(EntityJoinWorldEvent entityJoinWorldEvent) {
            Entity entity = entityJoinWorldEvent.getEntity();
            if (entity instanceof EntityCreeper) {
                EntityCreeper entityCreeper = (EntityCreeper)entity;
                entityCreeper.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)entityCreeper, eb.class, 6.0f, 1.0, 1.2));
            }
        }
    }
}

