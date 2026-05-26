/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  javax.annotation.Nullable
 *  javax.vecmath.Vector4d
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.BlockLog
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumFacing$Axis
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.items.IItemHandler
 *  net.minecraftforge.items.ItemStackHandler
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import com.trolmastercard.sexmod.EyeAndKoboldColor;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.a1;
import com.trolmastercard.sexmod.ax;
import com.trolmastercard.sexmod.b3;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.ba;
import com.trolmastercard.sexmod.bh;
import com.trolmastercard.sexmod.bs;
import com.trolmastercard.sexmod.c7;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.co;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.dj;
import com.trolmastercard.sexmod.dr;
import com.trolmastercard.sexmod.dw;
import com.trolmastercard.sexmod.e1;
import com.trolmastercard.sexmod.e4;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.en;
import com.trolmastercard.sexmod.fm;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.g7;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.h6;
import com.trolmastercard.sexmod.hy;
import com.trolmastercard.sexmod.hz;
import com.trolmastercard.sexmod.m;
import com.trolmastercard.sexmod.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.vecmath.Vector4d;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ff
extends e4
implements bh,
IInventory,
dr {
    public static final EyeAndKoboldColor aJ = EyeAndKoboldColor.PURPLE;
    public static final float Y = 0.25f;
    static final int ar = 20;
    static final int ag = 2;
    static final int aG = 30;
    static final int ah = 84;
    static final int a3 = 32;
    static final int a1 = 5;
    static final float ae = 1.5f;
    static final float aW = 20.0f;
    static final double au = 10.0;
    static final double ay = 2.0;
    static final double al = 3.0;
    static final int aQ = 300;
    static final int aq = 5;
    static final int aO = 100;
    static final int aB = 100;
    static final int ac = 2;
    static final float am = 2.0f;
    static final int aw = 300;
    static final float aj = 0.2f;
    static final double aH = 0.7;
    static final int aa = 142;
    public static final DataParameter<Float> aE = EntityDataManager.func_187226_a(ff.class, (DataSerializer)DataSerializers.field_187193_c).func_187156_b().func_187161_a(122);
    public static final DataParameter<String> T = EntityDataManager.func_187226_a(ff.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(123);
    public static final DataParameter<Boolean> aC = EntityDataManager.func_187226_a(ff.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(124);
    public static final DataParameter<Boolean> aZ = EntityDataManager.func_187226_a(ff.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(125);
    public static final DataParameter<String> aU = EntityDataManager.func_187226_a(ff.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(126);
    public static final DataParameter<Boolean> ak = EntityDataManager.func_187226_a(ff.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(127);
    public static final DataParameter<Boolean> at = EntityDataManager.func_187226_a(ff.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(128);
    public static final DataParameter<Optional<UUID>> aL = EntityDataManager.func_187226_a(ff.class, (DataSerializer)DataSerializers.field_187203_m).func_187156_b().func_187161_a(129);
    public static final int av = 24;
    public static double af = 69.0;
    public static List<Vector4d> aY = new ArrayList<Vector4d>();
    ItemStackHandler X = new ItemStackHandler(27);
    public String as = null;
    boolean az = false;
    int aP = 0;
    int U = 0;
    boolean a2 = false;
    int aD = 0;
    int a5 = 0;
    float S = Float.MAX_VALUE;
    static long aV = Long.MIN_VALUE;
    String[] an = new String[]{"What the fuck did you just fucking say about me, you little bitch? I'll have you know I graduated top of my class in the Navy Seals, and I've been involved in numerous secret raids on Al-Quaeda, and I have over 300 confirmed kills. I am trained in gorilla warfare and I'm the top sniper in the entire US armed forces. You are nothing to me but just another target. I will wipe you the fuck out with precision the likes of which has never been seen before on this Earth, mark my fucking words. You think you can get away with saying that shit to me over the Internet? Think again, fucker. As we speak I am contacting my secret network of spies across the USA and your IP is being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little thing you call your life. You're fucking dead, kid. I can be anywhere, anytime, and I can kill you in over seven hundred ways, and that's just with my bare hands. Not only am I extensively trained in unarmed combat, but I have access to the entire arsenal of the United States Marine Corps and I will use it to its full extent to wipe your miserable ass off the face of the continent, you little shit. If only you could have known what unholy retribution your little \"clever\" comment was about to bring down upon you, maybe you would have held your fucking tongue. But you couldn't, you didn't, and now you're paying the price, you goddamn idiot. I will shit fury all over you and you will drown in it. You're fucking dead, kiddo.", "suck my iron cock you worthless piece of shit!", "you'll die a fucking virgin!", "not even Johnny sins would wanna stick his cock up ur ass", "fuck you with ur borderline illegal fetishes!", "ur cum tastes terrible!", "I've always faked my orgasms when having sex with you!", "Not even Jenny would fuck you for 6 diamonds!", "U look like u'd use a shovel to mine diamonds, fucking idiot!", "Why tf does ur cock smell like my asshole???", "do all of us a favor and hit [ALT]+[F4]!", "I'm about to say the N word!", "you are under attack retard", "Eat my ass!", "my tongue is longer than ur fucking dick bitch!", "Ligma titties!", "touch some grass bitch!"};
    IBlockState R = null;
    IBlockState aX = null;
    BlockPos aF = null;
    boolean ao = true;
    Vec3d aS = Vec3d.field_186680_a;
    BlockPos aM = null;
    BlockPos aI = null;
    int ai = 0;
    int Z = 0;
    int aK = 0;
    int a0 = 0;
    boolean ax = false;
    BlockPos ap = null;
    int ab = 0;
    int aR = 24;
    int W = 0;
    ItemStack ad = null;
    public boolean aA = false;
    int V = -1;
    boolean a4 = true;
    boolean aT = false;
    public boolean Q = false;
    int aN = 0;

    public ff(World world) {
        super(world);
        this.func_70105_a(0.5f, 0.99f);
    }

    ff(World world, UUID uUID, float f10) {
        this(world);
        this.m.func_187227_b(aL, (Object)Optional.of((Object)uUID));
        this.m.func_187227_b(aE, (Object)Float.valueOf(f10));
    }

    public static ff a(World world, UUID uUID) {
        float f10 = ff.j();
        return ff.a(world, uUID, f10);
    }

    public static ff a(World world, UUID uUID, float f10) {
        af = 10.0 - (double)f10 * 25.0;
        return new ff(world, uUID, f10);
    }

    @Override
    protected String a(StringBuilder stringBuilder) {
        ff.b(stringBuilder, 8);
        ff.b(stringBuilder, 3);
        ff.b(stringBuilder);
        ff.b(stringBuilder);
        ff.a(stringBuilder, 2);
        ff.a(stringBuilder, 2);
        ff.a(stringBuilder, 1);
        ff.a(stringBuilder, 1);
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
    public ArrayList<Integer> L() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(Math.round(((Float)this.m.func_187225_a(aE)).floatValue() * 100.0f / 0.25f));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((String)this.m.func_187225_a(N))));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((Vec3i)this.m.func_187225_a(K))));
        return arrayList;
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

    /*
     * Exception decompiling
     */
    void m() {
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

    /*
     * Exception decompiling
     */
    @Override
    public e1 g(int var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 1[SWITCH]
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
    public String c() {
        return (String)this.m.func_187225_a(T);
    }

    @Override
    public float i() {
        return 0.2f - (0.25f - ((Float)this.m.func_187225_a(aE)).floatValue());
    }

    public float func_70047_e() {
        return 0.94f;
    }

    public static float j() {
        return (float)(Math.random() * 0.25);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.values()[this.func_70681_au().nextInt(EyeAndKoboldColor.values().length)];
        this.m.func_187214_a(K, (Object)new BlockPos(eyeAndKoboldColor.getMainColor()));
        this.m.func_187214_a(N, (Object)aJ.name());
        this.m.func_187214_a(aL, (Object)Optional.absent());
        this.m.func_187214_a(aE, (Object)Float.valueOf(0.0f));
        this.m.func_187214_a(T, (Object)ba.values()[this.func_70681_au().nextInt(ba.values().length)].toString());
        this.m.func_187214_a(aC, (Object)false);
        this.m.func_187214_a(aZ, (Object)false);
        this.m.func_187214_a(aU, (Object)"null");
        this.m.func_187214_a(ak, (Object)false);
        this.m.func_187214_a(at, (Object)false);
    }

    @Override
    protected void func_184651_r() {
        this.o = new df((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.4, false, new HashSet(I)));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new hz((EntityLiving)this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.o);
    }

    protected float func_175134_bD() {
        return 0.45f;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(af);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(30.0);
    }

    @Override
    public boolean func_70104_M() {
        return true;
    }

    protected boolean func_184645_a(EntityPlayer entityPlayer, EnumHand enumHand) {
        block45: {
            block43: {
                block44: {
                    block42: {
                        ItemStack itemStack;
                        block40: {
                            block41: {
                                block39: {
                                    try {
                                        if (this.ae() != null) {
                                            return false;
                                        }
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw ff.a(illegalArgumentException);
                                    }
                                    ItemStack itemStack2 = entityPlayer.func_184586_b(EnumHand.MAIN_HAND);
                                    if (!itemStack2.func_77973_b().equals(Items.field_151057_cb)) {
                                        itemStack2 = entityPlayer.func_184586_b(EnumHand.OFF_HAND);
                                    }
                                    try {
                                        try {
                                            if (!itemStack2.func_77973_b().equals(Items.field_151057_cb) || !entityPlayer.getPersistentID().toString().equals(this.m.func_187225_a(v))) break block39;
                                        }
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw ff.a(illegalArgumentException);
                                        }
                                        this.m.func_187227_b(T, (Object)itemStack2.func_82833_r());
                                        itemStack2.func_190918_g(1);
                                        return true;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw ff.a(illegalArgumentException);
                                    }
                                }
                                try {
                                    if (((Boolean)this.m.func_187225_a(aC)).booleanValue()) {
                                        return false;
                                    }
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                try {
                                    if (this.y() == fp.SLEEP) {
                                        return false;
                                    }
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                itemStack = entityPlayer.func_184586_b(EnumHand.MAIN_HAND);
                                if (itemStack.func_77973_b() != hy.b) {
                                    itemStack = entityPlayer.func_184586_b(EnumHand.OFF_HAND);
                                }
                                try {
                                    try {
                                        try {
                                            if (this.J() || itemStack.func_77973_b() != hy.b) break block40;
                                        }
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw ff.a(illegalArgumentException);
                                        }
                                        if (this.field_70170_p.field_72995_K) break block41;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw ff.a(illegalArgumentException);
                                    }
                                    return true;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                            }
                            Optional optional = (Optional)this.m.func_187225_a(aL);
                            try {
                                if (!optional.isPresent()) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            try {
                                if (!aY.isEmpty()) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            this.m((UUID)optional.get());
                            return true;
                        }
                        try {
                            try {
                                try {
                                    if (!this.J() || itemStack.func_77973_b() != hy.b) break block42;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                if (!((String)this.m.func_187225_a(v)).equals(entityPlayer.getPersistentID().toString())) break block42;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            entityPlayer.openGui((Object)Main.instance, 1, this.field_70170_p, this.func_180425_c().func_177958_n(), this.func_180425_c().func_177956_o(), this.func_180425_c().func_177952_p());
                            return true;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (!this.field_70170_p.field_72995_K) break block43;
                                if (!this.J()) break block44;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            if (!((String)this.m.func_187225_a(v)).equals(entityPlayer.getPersistentID().toString())) break block44;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        this.a(com.trolmastercard.sexmod.c.GIRLS_KOBOLD_MASTER);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                this.b(entityPlayer);
                break block45;
            }
            this.e(entityPlayer.getPersistentID());
            this.func_70661_as().func_75499_g();
            this.b((float)(Math.atan2(this.field_70161_v - entityPlayer.field_70161_v, this.field_70165_t - entityPlayer.field_70165_t) * 57.29577951308232 + 90.0));
            this.c(new Vec3d(this.field_70165_t, Math.floor(this.field_70163_u), this.field_70161_v));
            this.m.func_187227_b(G, (Object)true);
            this.b(fp.NULL);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    void m(UUID uUID) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new g7(uUID));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean b(EntityPlayer entityPlayer) {
        block7: {
            try {
                try {
                    if (!this.J() || !entityPlayer.getPersistentID().toString().equals(this.m.func_187225_a(v))) break block7;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(this, entityPlayer, new String[]{"anal", "oral", "mating"}, null, false));
                return true;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        try {
            if (this.func_70660_b(co.b) != null) {
                Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(this, entityPlayer, new String[]{"anal", "oral"}, null, false));
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new m(this, entityPlayer, new String[]{"anal", "oral"}, new ItemStack[]{new ItemStack(Items.field_151043_k, 3), new ItemStack(Items.field_151035_b)}, false));
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ac() {
        try {
            if (this.az) {
                this.az = false;
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        this.e((UUID)null);
        this.a("shouldbeattargetpos", "false");
    }

    @Override
    public void r() {
        this.Q = false;
        super.r();
    }

    protected void a(boolean bl2, UUID uUID) {
        super.a(bl2, true, uUID);
        d3.a(false);
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            this.az = true;
            if ("oral".equals(string)) {
                this.a("animationFollowUp", fp.STARTBLOWJOB.toString());
                this.a(true, uUID);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if ("anal".equals(string)) {
                this.a("animationFollowUp", fp.KOBOLD_ANAL_START.toString());
                this.a(true, uUID);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if ("mating".equals(string)) {
                this.a("animationFollowUp", fp.MATING_PRESS_START.toString());
                this.a(true, uUID);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
    }

    @Override
    public void b() {
        this.a2 = true;
        this.m.func_187227_b(G, (Object)false);
    }

    @Override
    protected void a() {
        dj.c();
    }

    boolean g() {
        try {
            if (!this.a2) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        ++this.aD;
        this.field_70145_X = false;
        this.func_189654_d(false);
        if (this.aD > 40) {
            this.a2 = false;
            this.aD = 0;
            EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
            this.b(entityPlayer.field_70177_z + 180.0f);
            this.m.func_187227_b(G, (Object)true);
            entityPlayer.field_70145_X = true;
            entityPlayer.func_189654_d(true);
            this.field_70145_X = true;
            this.func_189654_d(true);
            this.func_70661_as().func_75499_g();
            this.U();
            return true;
        }
        this.field_70177_z = this.I().floatValue();
        this.func_189654_d(false);
        Vec3d vec3d = b6.a(this.func_174791_d(), this.o(), 40 - this.aD);
        this.func_70107_b(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        this.b(fp.NULL);
        Optional optional = (Optional)this.m.func_187225_a(aL);
        try {
            if (!optional.isPresent()) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        Collection<bs> collection = com.trolmastercard.sexmod.ax.p((UUID)optional.get());
        try {
            if (collection == null) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        for (bs bs2 : collection) {
            bs2.c(this);
        }
        return true;
    }

    void o(UUID uUID) {
        try {
            if (this.V == -1) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (++this.V < 132) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            this.V = -1;
            if (this.y() != fp.MATING_PRESS_CUM) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        UUID uUID2 = this.ae();
        try {
            if (uUID2 == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID2);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        EyeAndKoboldColor eyeAndKoboldColor = com.trolmastercard.sexmod.ax.l(uUID);
        ItemStack itemStack = new ItemStack((Item)c7.a, 1, eyeAndKoboldColor.getWoolMeta());
        NBTTagCompound nBTTagCompound = itemStack.func_77978_p();
        if (nBTTagCompound == null) {
            nBTTagCompound = new NBTTagCompound();
        }
        nBTTagCompound.func_74778_a("tribeID", uUID.toString());
        nBTTagCompound.func_74778_a("tribeColor", eyeAndKoboldColor.toString());
        itemStack.func_77982_d(nBTTagCompound);
        entityPlayer.field_71071_by.func_70441_a(itemStack);
    }

    @Override
    public void func_70619_bc() {
        Optional optional;
        block39: {
            Object object;
            block40: {
                block38: {
                    block37: {
                        super.func_70619_bc();
                        this.ax = false;
                        optional = (Optional)this.m.func_187225_a(aL);
                        if (optional.isPresent()) {
                            this.o((UUID)optional.get());
                            com.trolmastercard.sexmod.ax.k((UUID)optional.get());
                            object = this.z();
                            try {
                                if (object != null) {
                                    com.trolmastercard.sexmod.ax.a((UUID)optional.get(), object.getPersistentID());
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                        }
                        try {
                            if (this.g()) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        try {
                            if (this.ae() != null) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        try {
                            try {
                                try {
                                    if (((Boolean)this.m.func_187225_a(aC)).booleanValue()) break block37;
                                    if (this.func_110143_aJ() == this.func_110138_aP()) break block38;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                if (++this.a5 < 100) break block38;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            this.func_70606_j(this.func_110143_aJ() + 2.0f);
                            this.a5 = 0;
                            ge.b.sendToAllTracking((IMessage)new en(this.f(), EnumParticleTypes.HEART.func_179346_b()), (Entity)this);
                            break block38;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    this.a5 = 0;
                }
                try {
                    if (!((Boolean)this.m.func_187225_a(G)).booleanValue()) {
                        this.func_189654_d(false);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                try {
                    if (!optional.isPresent()) {
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                try {
                    try {
                        --this.aP;
                        if (this.y() != fp.ATTACK) break block39;
                        this.func_70661_as().func_75499_g();
                        this.field_70177_z = this.I().floatValue();
                        this.field_70759_as = this.I().floatValue();
                        ++this.U;
                        if (22 != this.U) break block40;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    this.u();
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            if (32 == this.U) {
                object = com.trolmastercard.sexmod.ax.e((UUID)optional.get());
                HashSet<EntityLivingBase> hashSet = new HashSet<EntityLivingBase>();
                Iterator iterator = ((HashSet)object).iterator();
                while (iterator.hasNext()) {
                    EntityLivingBase entityLivingBase = (EntityLivingBase)iterator.next();
                    try {
                        if (entityLivingBase.func_70032_d((Entity)this) > 2.0f) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    try {
                        entityLivingBase.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 5.0f);
                        if (!entityLivingBase.field_70128_L) continue;
                        hashSet.add(entityLivingBase);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                for (EntityLivingBase entityLivingBase : hashSet) {
                    com.trolmastercard.sexmod.ax.b((UUID)optional.get(), entityLivingBase);
                }
            }
            try {
                if (84 <= this.U) {
                    this.b(fp.NULL);
                    this.m.func_187227_b(G, (Object)false);
                    this.U = 0;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            return;
        }
        this.m.func_187227_b(aC, (Object)this.c((UUID)optional.get(), false));
        this.m.func_187227_b(aZ, (Object)com.trolmastercard.sexmod.ax.e((UUID)optional.get(), this));
        this.m.func_187227_b(ak, (Object)com.trolmastercard.sexmod.ax.c((UUID)optional.get()));
        this.d();
        this.h();
        this.o.a = this.o();
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        this.t();
        this.v();
        this.q();
        this.w();
        this.m();
    }

    void w() {
        float f10;
        block28: {
            try {
                if (!this.field_70170_p.field_72995_K) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (this.field_70170_p.func_82737_E() - 300L < aV) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (!this.J()) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (this.y() != fp.NULL) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (!"".equals(this.m.func_187225_a(h))) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (((Boolean)this.m.func_187225_a(ak)).booleanValue()) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            String string = (String)this.m.func_187225_a(v);
            EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 10.0);
            try {
                if (entityPlayer == null) {
                    this.S = Float.MAX_VALUE;
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (!entityPlayer.getPersistentID().toString().equals(string)) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            f10 = this.func_70032_d((Entity)entityPlayer);
            try {
                try {
                    if (!(f10 < 2.0f) || !(this.S > 2.0f)) break block28;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                this.b(com.trolmastercard.sexmod.c.a(com.trolmastercard.sexmod.c.GIRLS_KOBOLD_HEYMASTER));
                this.a("Hey master!");
                aV = this.field_70170_p.func_82737_E();
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        this.S = f10;
    }

    void q() {
        try {
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (this.y() == fp.SLEEP) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (!((Boolean)this.m.func_187225_a(ak)).booleanValue()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (!this.J()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(UUID.fromString((String)this.m.func_187225_a(v)));
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        this.b(entityPlayer);
    }

    void t() {
        try {
            if (((Boolean)this.m.func_187225_a(aC)).booleanValue()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (this.J()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        Optional optional = (Optional)this.m.func_187225_a(aL);
        try {
            if (!optional.isPresent()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        for (EntityPlayer entityPlayer : this.field_70170_p.field_73010_i) {
            block24: {
                PathNavigate pathNavigate;
                double d10;
                block23: {
                    block22: {
                        double d11 = d10 = entityPlayer.func_174791_d().func_72438_d(this.func_174791_d());
                        if (!this.field_70170_p.field_72995_K) {
                            for (ff ff2 : com.trolmastercard.sexmod.ax.n((UUID)optional.get())) {
                                double d12 = entityPlayer.func_174791_d().func_72438_d(ff2.func_174791_d());
                                if (!(d12 < d11)) continue;
                                d11 = d12;
                            }
                        }
                        try {
                            if (d11 > 10.0) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        try {
                            try {
                                if (entityPlayer.func_184586_b(EnumHand.MAIN_HAND).func_77973_b() == hy.b || entityPlayer.func_184586_b(EnumHand.OFF_HAND).func_77973_b() == hy.b) break block22;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            return;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    pathNavigate = this.func_70661_as();
                    try {
                        pathNavigate.func_75499_g();
                        if (!this.field_70170_p.field_72995_K) break block23;
                        this.b(entityPlayer);
                        break block24;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                if (d10 > 2.0) {
                    ff ff2;
                    ff2 = this.c(entityPlayer.func_180425_c());
                    pathNavigate.func_75492_a((double)ff2.func_177958_n(), (double)ff2.func_177956_o(), (double)ff2.func_177952_p(), (double)0.35f);
                }
            }
            return;
        }
    }

    @Override
    protected void U() {
        String string;
        block27: {
            block29: {
                boolean bl2;
                block24: {
                    block26: {
                        boolean bl3;
                        block23: {
                            block22: {
                                boolean bl4;
                                string = (String)this.m.func_187225_a(em.h);
                                try {
                                    bl4 = this.func_70660_b(co.b) != null;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                boolean bl5 = bl4;
                                boolean bl6 = false;
                                if (this.J()) {
                                    bl6 = ((String)this.m.func_187225_a(v)).equals(this.ae().toString());
                                }
                                try {
                                    try {
                                        if (bl5 || bl6) break block22;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw ff.a(illegalArgumentException);
                                    }
                                    bl3 = true;
                                    break block23;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                            }
                            bl3 = false;
                        }
                        bl2 = bl3;
                        try {
                            block25: {
                                try {
                                    try {
                                        if (!string.equals(fp.STARTBLOWJOB.toString())) break block24;
                                        if (!bl2) break block25;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw ff.a(illegalArgumentException);
                                    }
                                    if (this.y() != fp.PAYMENT) break block26;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                            }
                            this.b(fp.STARTBLOWJOB);
                            break block24;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    this.b(fp.PAYMENT);
                }
                try {
                    block28: {
                        try {
                            try {
                                if (!string.equals(fp.KOBOLD_ANAL_START.toString())) break block27;
                                if (!bl2) break block28;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            if (this.y() != fp.PAYMENT) break block29;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    this.b(fp.KOBOLD_ANAL_START);
                    break block27;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            this.b(fp.PAYMENT);
        }
        try {
            if (string.equals(fp.MATING_PRESS_START.toString())) {
                this.b(fp.MATING_PRESS_START);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
    }

    void v() {
        try {
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (!((Boolean)this.m.func_187225_a(G)).booleanValue()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (this.y() != fp.NULL) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        this.b(entityPlayer);
    }

    void b(EntityPlayer entityPlayer) {
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
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        vec3d2(d11, d10 + (double)f10, entityPlayer.field_70161_v);
        Vec3d vec3d4 = vec3d;
        Vec3d vec3d5 = new Vec3d(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
        double d12 = vec3d5.func_72438_d(vec3d4);
        double d13 = vec3d4.field_72448_b - vec3d5.field_72448_b;
        this.field_70125_A = (float)(-(Math.sin(d13 / d12) * 57.29577951308232));
    }

    void u() {
    }

    boolean o() {
        try {
            if (this.y() != fp.NULL) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (Math.abs(this.field_70159_w) + Math.abs(this.field_70179_y) > 0.01) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (this.a()) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    void d() {
        UUID uUID;
        block23: {
            block21: {
                block22: {
                    Optional optional = (Optional)this.m.func_187225_a(aL);
                    try {
                        if (!optional.isPresent()) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    uUID = (UUID)optional.get();
                    try {
                        try {
                            try {
                                if (((Boolean)this.m.func_187225_a(aC)).booleanValue() || !com.trolmastercard.sexmod.ax.c(uUID)) break block21;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            if (this.J()) break block22;
                            return;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                EntityPlayer entityPlayer = this.z();
                try {
                    if (entityPlayer == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                for (bs bs2 : com.trolmastercard.sexmod.ax.p(uUID)) {
                    try {
                        if (!bs2.b(this)) continue;
                        bs2.c(this);
                        this.b(fp.NULL);
                        this.m.func_187227_b(G, (Object)false);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                this.field_70145_X = false;
                this.func_189654_d(false);
                PathNavigate pathNavigate = this.func_70661_as();
                double d10 = this.func_174791_d().func_72438_d(entityPlayer.func_174791_d());
                try {
                    try {
                        if (!(d10 > 2.0)) return;
                        pathNavigate.func_75497_a((Entity)entityPlayer, this.a(entityPlayer, d10));
                        this.k();
                        if (!(d10 > 15.0)) return;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    this.c(entityPlayer);
                    return;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            try {
                if (!com.trolmastercard.sexmod.ax.e(uUID, this)) break block23;
                this.b(uUID);
                return;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        this.n(uUID);
    }

    protected double a(EntityPlayer entityPlayer, double d10) {
        double d11 = entityPlayer.func_70051_ag() ? 0.7 : 0.35;
        double d12 = Math.floor(d10 / 5.0) * 0.3;
        d11 += d12;
        if (this.func_70090_H()) {
            d11 *= 60.0;
        }
        return d11;
    }

    void s(UUID uUID) {
        BlockPos blockPos = com.trolmastercard.sexmod.ax.m(uUID);
        try {
            if (blockPos == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (this.aX != null) {
                this.field_70170_p.func_175656_a(blockPos, this.aX);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (this.R != null) {
                this.field_70170_p.func_175656_a(blockPos.func_177982_a(0, -1, 0), this.R);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
    }

    /*
     * Exception decompiling
     */
    void b(UUID var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 6[SWITCH]
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

    void p(UUID uUID) {
        Collection<bs> collection = com.trolmastercard.sexmod.ax.p(uUID);
        try {
            if (collection == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        for (bs bs2 : collection) {
            bs2.a();
        }
    }

    void q(UUID uUID) {
        try {
            if (!this.J()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        List<ff> list = com.trolmastercard.sexmod.ax.n(uUID);
        for (ff ff2 : list) {
            try {
                com.trolmastercard.sexmod.ax.b(ff2);
                if (ff2.ae() != null) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            ff2.field_70145_X = false;
            ff2.func_189654_d(false);
            ff2.func_184212_Q().func_187227_b(G, (Object)false);
            ff2.b(fp.NULL);
        }
    }

    void l(UUID uUID) {
        block5: {
            block4: {
                Collection<bs> collection = com.trolmastercard.sexmod.ax.p(uUID);
                if (collection != null) {
                    for (bs bs2 : collection) {
                        bs2.c(this);
                    }
                }
                try {
                    if (!this.J()) break block4;
                    this.i(uUID);
                    break block5;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            this.a(uUID);
        }
    }

    void i(UUID uUID) {
        BlockPos blockPos;
        block27: {
            block29: {
                block28: {
                    BlockPos[] blockPosArray = com.trolmastercard.sexmod.ax.a(this);
                    if (blockPosArray != null) {
                        float f10;
                        ff ff2;
                        boolean bl2;
                        Vec3d vec3d = new Vec3d((double)((float)blockPosArray[0].func_177958_n() + 0.5f), (double)blockPosArray[0].func_177956_o() + 0.5625, (double)((float)blockPosArray[0].func_177952_p() + 0.5f));
                        Vec3d vec3d2 = new Vec3d((double)((float)blockPosArray[1].func_177958_n() + 0.5f), (double)blockPosArray[1].func_177956_o() + 0.5625, (double)((float)blockPosArray[1].func_177952_p() + 0.5f));
                        try {
                            bl2 = vec3d.func_178788_d((Vec3d)vec3d2).field_72450_a == 0.0;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        boolean bl3 = bl2;
                        Vec3d vec3d3 = b6.a(vec3d, vec3d2, 0.5);
                        try {
                            this.m.func_187227_b(G, (Object)true);
                            this.c(vec3d3);
                            ff2 = this;
                            f10 = bl3 ? 0.0f : 90.0f;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        ff2.b(f10);
                        this.field_70145_X = true;
                        this.func_189654_d(true);
                        return;
                    }
                    HashSet<BlockPos> hashSet = com.trolmastercard.sexmod.ax.j(uUID);
                    blockPos = null;
                    try {
                        if (hashSet == null) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    for (BlockPos blockPos2 : hashSet) {
                        IBlockState iBlockState = this.field_70170_p.func_180495_p(blockPos2);
                        boolean bl4 = false;
                        for (Map.Entry entry : iBlockState.func_177228_b().entrySet()) {
                            if (!(entry.getKey() instanceof PropertyBool)) continue;
                            bl4 = (Boolean)entry.getValue();
                            break;
                        }
                        try {
                            if (bl4) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        try {
                            if (com.trolmastercard.sexmod.ax.a(blockPos2)) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        if (blockPos == null) {
                            blockPos = blockPos2;
                            continue;
                        }
                        if (!(this.func_174818_b(blockPos) > this.func_174818_b(blockPos2))) continue;
                        blockPos = blockPos2;
                    }
                    try {
                        if (blockPos == null) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    try {
                        try {
                            if (!(blockPos.func_185332_f((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) > 2.0)) break block27;
                            if (Math.abs(blockPos.func_177973_b((Vec3i)this.func_180425_c()).func_177956_o()) <= 4) break block28;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        this.b(blockPos.func_177982_a(0, 1, 0));
                        break block29;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                BlockPos blockPos3 = this.c(blockPos);
                try {
                    this.func_70661_as().func_75492_a((double)blockPos3.func_177958_n(), (double)blockPos3.func_177956_o(), (double)blockPos3.func_177952_p(), (double)0.35f);
                    if (this.func_70661_as().func_75505_d() == null) {
                        this.b(blockPos.func_177982_a(0, 1, 0));
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            return;
        }
        com.trolmastercard.sexmod.ax.a(this, blockPos);
        this.b(fp.SLEEP);
    }

    void a(UUID uUID) {
        block12: {
            int n2;
            int n3;
            int n4;
            int n5;
            BlockPos blockPos;
            block14: {
                block13: {
                    BlockPos blockPos2;
                    block11: {
                        blockPos2 = com.trolmastercard.sexmod.ax.m(uUID);
                        try {
                            if (blockPos2 != null || !com.trolmastercard.sexmod.ax.e(uUID, this)) break block11;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        BlockPos blockPos3 = this.func_180425_c().func_177982_a(1, 0, 0);
                        this.R = this.field_70170_p.func_180495_p(blockPos3.func_177982_a(0, -1, 0));
                        this.aX = this.field_70170_p.func_180495_p(blockPos3);
                        this.field_70170_p.func_175656_a(blockPos3.func_177982_a(0, -1, 0), Blocks.field_150424_aL.func_176223_P());
                        this.field_70170_p.func_175656_a(blockPos3, dw.a.func_176223_P());
                        com.trolmastercard.sexmod.ax.b(uUID, blockPos3);
                    }
                    try {
                        if (blockPos2 == null) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    try {
                        try {
                            if (this.aF != null) break block12;
                            ff ff2 = this;
                            blockPos = blockPos2;
                            if (!this.func_70681_au().nextBoolean()) break block13;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        n5 = 1;
                        break block14;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                n5 = -1;
            }
            try {
                n4 = n5 * (this.func_70681_au().nextInt(2) + 1);
                n3 = 0;
                n2 = this.func_70681_au().nextBoolean() ? 1 : -1;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            ff2.aF = blockPos.func_177982_a(n4, n3, n2 * (this.func_70681_au().nextInt(2) + 1));
        }
        this.func_70661_as().func_75492_a((double)this.aF.func_177958_n(), (double)this.aF.func_177956_o(), (double)this.aF.func_177952_p(), (double)0.35f);
        this.k();
    }

    void c(UUID uUID) {
        block9: {
            Collection<bs> collection;
            block8: {
                try {
                    if (this.J()) {
                        com.trolmastercard.sexmod.ax.b(uUID, null);
                        this.g(uUID);
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                collection = com.trolmastercard.sexmod.ax.p(uUID);
                try {
                    if (collection == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                try {
                    if (!this.ao) break block8;
                    this.aM = null;
                    this.b(uUID, collection);
                    break block9;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            this.a(uUID, collection);
        }
    }

    void b(UUID uUID, Collection<bs> collection) {
        try {
            if (collection.isEmpty()) {
                this.ao = false;
                this.r(uUID);
                this.h("Lets go somewhere else");
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
    }

    void a(UUID uUID, Collection<bs> collection) {
        BlockPos blockPos;
        block19: {
            block17: {
                block18: {
                    blockPos = com.trolmastercard.sexmod.ax.m(uUID);
                    try {
                        if (blockPos == null) {
                            this.r(uUID);
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    try {
                        try {
                            if (this.field_70173_aa % 40 != 0) break block17;
                            if (!this.aS.equals((Object)this.func_174791_d())) break block18;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        this.r(uUID);
                        this.aM = null;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                this.aS = this.func_174791_d();
            }
            try {
                try {
                    if (this.aM != null && !(this.aM.func_185332_f((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) < 4.0)) break block19;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                this.aM = this.t(uUID);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        try {
            this.func_70661_as().func_75492_a((double)this.aM.func_177958_n(), (double)this.aM.func_177956_o(), (double)this.aM.func_177952_p(), (double)0.35f);
            this.k();
            if (Math.sqrt(this.func_180425_c().func_177951_i((Vec3i)blockPos)) > 5.0) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        this.ao = true;
        this.h("Time to work bitches!");
        int n2 = com.trolmastercard.sexmod.ax.h(uUID);
        try {
            for (int i2 = 1; i2 < n2; ++i2) {
                this.c(uUID, collection);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        com.trolmastercard.sexmod.ax.b(uUID, null);
    }

    protected void c(EntityPlayer entityPlayer) {
        BlockPos blockPos;
        int n2 = 0;
        do {
            blockPos = entityPlayer.func_180425_c().func_177982_a(com.trolmastercard.sexmod.r.f.nextInt(10), 0, com.trolmastercard.sexmod.r.f.nextInt(10));
        } while (++n2 < 20 && !this.func_184595_k(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p()));
        try {
            if (n2 == 20) {
                this.func_70107_b(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
    }

    BlockPos t(UUID uUID) {
        BlockPos blockPos = com.trolmastercard.sexmod.ax.m(uUID);
        try {
            if (blockPos == null) {
                return BlockPos.field_177992_a;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        return this.c(blockPos);
    }

    BlockPos c(BlockPos blockPos) {
        double d10;
        double d11;
        int n2;
        double d12;
        double d13;
        int n3;
        BlockPos blockPos2 = this.func_180425_c();
        BlockPos blockPos3 = blockPos.func_177973_b((Vec3i)blockPos2);
        try {
            if (Math.abs(blockPos3.func_177958_n()) + Math.abs(blockPos3.func_177952_p()) < 20) {
                return blockPos;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        double d14 = Math.min(Math.abs(blockPos3.func_177958_n()), Math.abs(blockPos3.func_177952_p()));
        double d15 = Math.max(Math.abs(blockPos3.func_177958_n()), Math.abs(blockPos3.func_177952_p()));
        double d16 = d14 / (d15 + d14);
        try {
            n3 = blockPos3.func_177958_n() > 0 ? 1 : -1;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            d13 = n3 * 20;
            d12 = d14 == (double)Math.abs(blockPos3.func_177958_n()) ? d16 : 1.0 - d16;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        int n4 = (int)(d13 * d12);
        try {
            n2 = blockPos3.func_177952_p() > 0 ? 1 : -1;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            d11 = n2 * 20;
            d10 = d14 == (double)Math.abs(blockPos3.func_177952_p()) ? d16 : 1.0 - d16;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        int n5 = (int)(d11 * d10);
        BlockPos blockPos4 = this.func_180425_c().func_177982_a(n4, 0, n5);
        blockPos4 = new BlockPos(blockPos4.func_177958_n(), cj.a(this.field_70170_p, blockPos4.func_177958_n(), blockPos4.func_177952_p()) + 1, blockPos4.func_177952_p());
        return blockPos4;
    }

    /*
     * Exception decompiling
     */
    void r(UUID var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [6[DOLOOP]], but top level block is 1[TRYBLOCK]
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

    void c(UUID uUID, Collection<bs> collection) {
        List<BlockPos> list = this.a(this.func_180425_c(), BlockLog.class, 30, 4, null);
        BlockPos blockPos = null;
        for (BlockPos blockPos2 : list) {
            Block block = this.field_70170_p.func_180495_p(blockPos2.func_177977_b()).func_177230_c();
            if (block instanceof BlockLog) continue;
            try {
                if (block == Blocks.field_150350_a) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            boolean bl2 = false;
            for (bs bs2 : collection) {
                if (!bs2.c(blockPos2)) continue;
                bl2 = true;
                break;
            }
            if (bl2) continue;
            blockPos = blockPos2;
            break;
        }
        try {
            if (blockPos == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        bs.a(this.field_70170_p, blockPos, uUID);
        this.h("Someone, go fall this tree!");
    }

    fm p() {
        long l2 = this.field_70170_p.func_72820_D();
        try {
            if (l2 < 12000L) {
                return fm.ACTIVE;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        return fm.REST;
    }

    boolean d(UUID uUID) {
        return this.c(uUID, true);
    }

    boolean c(UUID uUID, boolean bl2) {
        Iterator<Object> iterator;
        Object object2;
        HashSet<EntityLivingBase> hashSet = com.trolmastercard.sexmod.ax.e(uUID);
        ff ff2 = com.trolmastercard.sexmod.ax.f(uUID);
        try {
            if (ff2 == null) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        for (Object object2 : this.field_70170_p.func_72872_a(ff.class, new AxisAlignedBB(ff2.field_70165_t - 30.0, ff2.field_70163_u - 30.0, ff2.field_70161_v - 30.0, ff2.field_70165_t + 30.0, ff2.field_70163_u + 30.0, ff2.field_70161_v + 30.0))) {
            try {
                if (!this.func_70685_l((Entity)object2)) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                try {
                    if (((em)object2).J() && this.J()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            iterator = (Optional)object2.func_184212_Q().func_187225_a(aL);
            try {
                if (!iterator.isPresent()) {
                    hashSet.add((EntityLivingBase)object2);
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (((UUID)iterator.get()).equals(uUID)) continue;
                hashSet.add((EntityLivingBase)object2);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        EntityLivingBase entityLivingBase = null;
        object2 = new ArrayList();
        for (EntityLivingBase entityLivingBase2 : hashSet) {
            try {
                if (entityLivingBase2.field_70128_L) {
                    object2.add(entityLivingBase2);
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (ff2.func_70032_d((Entity)entityLivingBase2) > 30.0f) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (entityLivingBase != null && !(this.func_70032_d((Entity)entityLivingBase) > this.func_70032_d((Entity)entityLivingBase2))) continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            entityLivingBase = entityLivingBase2;
        }
        iterator = object2.iterator();
        while (iterator.hasNext()) {
            EntityLivingBase entityLivingBase2;
            entityLivingBase2 = (EntityLivingBase)iterator.next();
            com.trolmastercard.sexmod.ax.b(uUID, entityLivingBase2);
        }
        try {
            if (entityLivingBase == null) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (!bl2) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (this.y() != fp.ATTACK) {
                this.m.func_187227_b(G, (Object)false);
                this.b(fp.NULL);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        iterator = this.c(entityLivingBase.func_180425_c());
        try {
            this.func_70661_as().func_75492_a((double)iterator.func_177958_n(), (double)iterator.func_177956_o(), (double)iterator.func_177952_p(), 0.7);
            this.k();
            if (this.func_70032_d((Entity)entityLivingBase) > 1.5f) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (this.aP > 0) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        float f10 = (float)(Math.atan2(this.field_70161_v - entityLivingBase.field_70161_v, this.field_70165_t - entityLivingBase.field_70165_t) * 57.29577951308232 + 90.0);
        this.b(f10);
        this.b(fp.ATTACK);
        this.aP = 84;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void n(UUID uUID) {
        try {
            if (this.d(uUID)) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        fm fm2 = com.trolmastercard.sexmod.ax.i(uUID);
        try {
            switch (fm2) {
                case REST: {
                    this.l(uUID);
                    return;
                }
                case ACTIVE: {
                    this.aF = null;
                    this.h(uUID);
                    return;
                }
                default: {
                    return;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
    }

    void h(UUID uUID) {
        block22: {
            block20: {
                block21: {
                    BlockPos blockPos = com.trolmastercard.sexmod.ax.m(uUID);
                    try {
                        if (blockPos == null) {
                            this.aM = null;
                            this.g(uUID);
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    ff ff2 = com.trolmastercard.sexmod.ax.f(uUID);
                    try {
                        if (com.trolmastercard.sexmod.ax.g(uUID)) {
                            this.func_70661_as().func_75499_g();
                            this.aM = null;
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    try {
                        if (ff2 == null) {
                            System.out.println("leader of tribe " + uUID + " is null");
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    try {
                        if (ff2.func_70032_d((Entity)this) > 20.0f) {
                            this.func_70107_b(ff2.field_70165_t, ff2.field_70163_u, ff2.field_70161_v);
                            this.aM = null;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    try {
                        try {
                            if (this.field_70173_aa % 40 != 0) break block20;
                            if (!this.aS.equals((Object)this.func_174791_d())) break block21;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        this.aM = this.t(uUID);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                this.aS = this.func_174791_d();
            }
            try {
                try {
                    if (this.aM != null && !(this.aM.func_185332_f((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) < 4.0)) break block22;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                this.aM = this.t(uUID);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        this.func_70661_as().func_75492_a((double)this.aM.func_177958_n(), (double)this.aM.func_177956_o(), (double)this.aM.func_177952_p(), (double)0.35f);
        this.k();
    }

    void g(UUID uUID) {
        try {
            if (this.ae() != null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        Collection<bs> collection = com.trolmastercard.sexmod.ax.p(uUID);
        try {
            if (collection == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        bs bs2 = null;
        for (bs bs3 : collection) {
            if (!bs3.b(this)) continue;
            bs2 = bs3;
            break;
        }
        if (bs2 == null) {
            for (bs bs3 : collection) {
                try {
                    try {
                        if (this.J() && !this.c(uUID, bs3)) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                try {
                    if (!this.a(bs3)) {
                        this.ax = true;
                        continue;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                if (!bs3.a(this)) continue;
                bs2 = bs3;
                try {
                    this.aI = null;
                    if (bs3.d() == bs.a.FALL_TREE) {
                        this.h("Ima fall this tree owo");
                        break;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                this.h("Ima go mine uwu");
                this.b(bs3.b());
                this.field_70170_p.func_175656_a(bs3.b(), Blocks.field_150350_a.func_176223_P());
                break;
            }
        }
        try {
            if (bs2 == null) {
                this.u(uUID);
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (bs2.d() == bs.a.FALL_TREE) {
                this.a(uUID, bs2.b(), bs2);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (bs2.d() == bs.a.MINE) {
                this.b(uUID, bs2);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
    }

    void b(BlockPos blockPos) {
        ge.b.sendToAllTracking((IMessage)new en(this.f(), EnumParticleTypes.PORTAL.func_179346_b(), 30), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 30.0));
        this.func_70107_b(0.5f + (float)blockPos.func_177958_n(), blockPos.func_177956_o(), 0.5f + (float)blockPos.func_177952_p());
        ge.b.sendToAllTracking((IMessage)new en(this.f(), EnumParticleTypes.PORTAL.func_179346_b(), 30), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 30.0));
    }

    void b(UUID uUID, bs bs2) {
        try {
            if (this.y() != fp.MINE) {
                this.a(uUID, bs2);
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        --this.Z;
        --this.ai;
        if (this.ai == 0) {
            IBlockState iBlockState;
            IBlockState iBlockState2 = this.field_70170_p.func_180495_p(this.aI.func_177984_a());
            if (!(iBlockState2.func_177230_c() instanceof BlockFalling)) {
                bs2.a(this.aI);
                iBlockState = this.z();
                try {
                    if (iBlockState != null) {
                        ge.b.sendTo((IMessage)new h6(this.aI, false), (EntityPlayerMP)iBlockState);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            iBlockState = this.field_70170_p.func_180495_p(this.aI);
            this.b(new ItemStack(iBlockState.func_177230_c().func_180660_a(iBlockState, this.func_70681_au(), 0), 1, iBlockState.func_177230_c().func_180651_a(iBlockState)));
            this.field_70170_p.func_175655_b(this.aI, false);
        }
        try {
            if (this.Z <= 0) {
                this.Z = 100;
                this.ai = 24;
                this.b(fp.NULL);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
    }

    void a(UUID uUID, bs bs2) {
        PathNavigate pathNavigate;
        block28: {
            block27: {
                block25: {
                    BlockPos blockPos;
                    block26: {
                        pathNavigate = this.func_70661_as();
                        try {
                            try {
                                if (this.aI != null && bs2.g().contains(this.aI)) break block25;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            this.aI = this.a(bs2, uUID);
                            if (this.aI != null) break block26;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        boolean bl2 = bs2.g().isEmpty();
                        HashSet<BlockPos> hashSet = com.trolmastercard.sexmod.ax.a(uUID, bs2);
                        UUID uUID2 = com.trolmastercard.sexmod.ax.b(uUID);
                        try {
                            if (uUID2 == null) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID2);
                        try {
                            if (entityPlayer == null) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        try {
                            if (!bl2) {
                                entityPlayer.func_145747_a((ITextComponent)new TextComponentString(String.format("<%s> It's impossible to mine here...", this.c())));
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        ge.b.sendTo((IMessage)new h6(hashSet, false), (EntityPlayerMP)entityPlayer);
                        return;
                    }
                    if (Math.abs(this.func_180425_c().func_177956_o() - bs2.b().func_177956_o()) > 3) {
                        blockPos = bs2.b().func_177971_a(bs2.f().func_176734_d().func_176730_m());
                        this.field_70170_p.func_175656_a(blockPos, Blocks.field_150350_a.func_176223_P());
                        this.b(blockPos);
                    }
                    blockPos = this.aI.func_177971_a(bs2.f().func_176734_d().func_176730_m());
                    pathNavigate.func_75492_a((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p(), (double)0.35f);
                    return;
                }
                IBlockState iBlockState = this.field_70170_p.func_180495_p(this.aI);
                try {
                    if (!this.a(new ItemStack(iBlockState.func_177230_c().func_180660_a(iBlockState, com.trolmastercard.sexmod.r.f, 0)))) {
                        this.ax = true;
                        this.b(uUID, true);
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                try {
                    try {
                        try {
                            try {
                                if (this.field_70159_w != 0.0 || this.field_70179_y != 0.0) break block27;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            if (!this.field_70122_E) break block27;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        if (this.func_70011_f(this.aI.func_177958_n(), this.aI.func_177956_o(), this.aI.func_177952_p()) > 3.0) break block27;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    if (++this.aK >= 10) break block28;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            BlockPos blockPos = this.aI.func_177971_a(bs2.f().func_176734_d().func_176730_m());
            pathNavigate.func_75492_a((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p(), (double)0.35f);
            return;
        }
        pathNavigate.func_75499_g();
        this.aK = 0;
        this.b(fp.MINE);
        this.field_70177_z = this.field_70759_as = (float)(Math.atan2(this.field_70161_v - (double)this.aI.func_177952_p(), this.field_70165_t - (double)this.aI.func_177958_n()) * 57.29577951308232 + 90.0);
        this.m.func_187227_b(at, (Object)false);
    }

    /*
     * Exception decompiling
     */
    BlockPos a(bs var1_1, UUID var2_2) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 23[SWITCH]
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

    @Nullable
    BlockPos a(List<BlockPos> list, int n2, EnumFacing enumFacing, BlockPos blockPos) {
        BlockPos blockPos2;
        int n3;
        ArrayList<BlockPos> arrayList;
        ArrayList<BlockPos> arrayList2;
        ArrayList<BlockPos> arrayList3;
        block33: {
            int n4;
            block32: {
                try {
                    if (list.isEmpty()) {
                        return null;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                arrayList3 = new ArrayList<BlockPos>();
                arrayList2 = new ArrayList<BlockPos>();
                arrayList = new ArrayList<BlockPos>();
                try {
                    try {
                        if (enumFacing != EnumFacing.SOUTH && enumFacing != EnumFacing.WEST) break block32;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    n4 = -1;
                    break block33;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            n4 = n3 = 1;
        }
        if (enumFacing.func_176740_k() == EnumFacing.Axis.Z) {
            blockPos2 = new BlockPos(blockPos.func_177958_n(), blockPos.func_177956_o(), list.get(0).func_177952_p());
            arrayList.add(blockPos2);
            arrayList.add(blockPos2.func_177984_a());
            arrayList.add(blockPos2.func_177984_a().func_177984_a());
            arrayList.add(blockPos2.func_177976_e());
            arrayList.add(blockPos2.func_177976_e().func_177984_a());
            arrayList.add(blockPos2.func_177976_e().func_177984_a().func_177984_a());
            arrayList.add(blockPos2.func_177974_f());
            arrayList.add(blockPos2.func_177974_f().func_177984_a());
            arrayList.add(blockPos2.func_177974_f().func_177984_a().func_177984_a());
            if (n2 == 0) {
                for (BlockPos blockPos3 : arrayList) {
                    arrayList2.add(blockPos3.func_177965_g(2));
                    arrayList2.add(blockPos3.func_177965_g(-2));
                }
                for (BlockPos blockPos3 : list) {
                    try {
                        if (arrayList2.contains(blockPos3)) continue;
                        arrayList3.add(blockPos3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
            } else {
                for (BlockPos blockPos3 : arrayList) {
                    arrayList2.add(blockPos3.func_177965_g(n3 * 2 * n2));
                }
                for (BlockPos blockPos3 : arrayList2) {
                    try {
                        if (!list.contains(blockPos3)) continue;
                        arrayList3.add(blockPos3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
            }
        }
        if (enumFacing.func_176740_k() == EnumFacing.Axis.X) {
            blockPos2 = new BlockPos(list.get(0).func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
            arrayList.add(blockPos2);
            arrayList.add(blockPos2.func_177984_a());
            arrayList.add(blockPos2.func_177984_a().func_177984_a());
            arrayList.add(blockPos2.func_177978_c());
            arrayList.add(blockPos2.func_177978_c().func_177984_a());
            arrayList.add(blockPos2.func_177978_c().func_177984_a().func_177984_a());
            arrayList.add(blockPos2.func_177968_d());
            arrayList.add(blockPos2.func_177968_d().func_177984_a());
            arrayList.add(blockPos2.func_177968_d().func_177984_a().func_177984_a());
            if (n2 == 0) {
                for (BlockPos blockPos3 : arrayList) {
                    arrayList2.add(blockPos3.func_177970_e(2));
                    arrayList2.add(blockPos3.func_177970_e(-2));
                }
                for (BlockPos blockPos3 : list) {
                    try {
                        if (arrayList2.contains(blockPos3)) continue;
                        arrayList3.add(blockPos3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
            } else {
                for (BlockPos blockPos3 : arrayList) {
                    arrayList2.add(blockPos3.func_177970_e(n3 * 2 * n2));
                }
                for (BlockPos blockPos3 : arrayList2) {
                    try {
                        if (!list.contains(blockPos3)) continue;
                        arrayList3.add(blockPos3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
            }
        }
        try {
            if (arrayList3.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        return (BlockPos)arrayList3.get(this.func_70681_au().nextInt(arrayList3.size()));
    }

    void u(UUID uUID) {
        try {
            if (this.b(uUID, false)) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        this.e();
    }

    void e() {
        block26: {
            block25: {
                block22: {
                    int n2;
                    int n3;
                    block24: {
                        block23: {
                            block20: {
                                EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 15.0);
                                try {
                                    try {
                                        try {
                                            try {
                                                if (!this.J() || entityPlayer == null) break block20;
                                            }
                                            catch (IllegalArgumentException illegalArgumentException) {
                                                throw ff.a(illegalArgumentException);
                                            }
                                            if (!(entityPlayer.func_70032_d((Entity)this) < 2.0f)) break block20;
                                        }
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw ff.a(illegalArgumentException);
                                        }
                                        if (!((String)this.m.func_187225_a(v)).equals(entityPlayer.getPersistentID().toString())) break block20;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw ff.a(illegalArgumentException);
                                    }
                                    this.func_70661_as().func_75499_g();
                                    return;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                            }
                            try {
                                try {
                                    block21: {
                                        try {
                                            try {
                                                if (this.ap == null || this.func_70011_f(this.ap.func_177958_n(), this.ap.func_177956_o(), this.ap.func_177952_p()) > this.n()) break block21;
                                            }
                                            catch (IllegalArgumentException illegalArgumentException) {
                                                throw ff.a(illegalArgumentException);
                                            }
                                            if (this.ab <= 100) break block22;
                                        }
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw ff.a(illegalArgumentException);
                                        }
                                    }
                                    if (!this.func_70681_au().nextBoolean()) break block23;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                n3 = 1;
                                break block24;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                        }
                        n3 = -1;
                    }
                    int n4 = n3 * this.func_70681_au().nextInt(5);
                    try {
                        n2 = this.func_70681_au().nextBoolean() ? 1 : -1;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    int n5 = n2 * this.func_70681_au().nextInt(5);
                    int n6 = cj.a(this.field_70170_p, this.func_180425_c().func_177958_n() + n4, this.func_180425_c().func_177952_p() + n5);
                    this.ap = new BlockPos(this.func_180425_c().func_177958_n() + n4, n6, this.func_180425_c().func_177952_p() + n5);
                    this.ab = 0;
                }
                try {
                    if (!(Math.sqrt(this.ap.func_177951_i((Vec3i)this.func_180425_c())) > 2.0)) break block25;
                    this.func_70661_as().func_75492_a((double)this.ap.func_177958_n(), (double)this.ap.func_177956_o(), (double)this.ap.func_177952_p(), (double)0.35f);
                    this.k();
                    break block26;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            ++this.ab;
        }
    }

    double n() {
        return Math.sqrt(800.0);
    }

    boolean b(UUID uUID, boolean bl2) {
        block13: {
            try {
                if (this.f()) {
                    return false;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (this.a(uUID, bl2)) {
                    this.a0 = 0;
                    return true;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                try {
                    if (--this.a0 < 0 && this.ax) break block13;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                return false;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        this.a0 = 300;
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(UUID.fromString((String)this.m.func_187225_a(v)));
        EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.valueOf((String)this.m.func_187225_a(N));
        try {
            if (entityPlayer != null) {
                entityPlayer.func_146105_b((ITextComponent)new TextComponentString(eyeAndKoboldColor.getTextColor() + this.c() + "s " + TextFormatting.WHITE + "inventory is full and there are either no chests to put her items in or said chests are full as well"), false);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        return false;
    }

    boolean a(UUID uUID, boolean bl2) {
        block37: {
            block38: {
                TileEntityChest tileEntityChest;
                BlockPos blockPos3;
                BlockPos blockPos2;
                block35: {
                    block36: {
                        int n2;
                        IItemHandler iItemHandler;
                        HashSet<BlockPos> hashSet = com.trolmastercard.sexmod.ax.q(uUID);
                        try {
                            if (hashSet == null) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        blockPos2 = null;
                        for (BlockPos blockPos3 : hashSet) {
                            TileEntityChest tileEntityChest2 = (TileEntityChest)this.field_70170_p.func_175625_s(blockPos3);
                            iItemHandler = tileEntityChest2.getSingleChestHandler();
                            n2 = 0;
                            for (int i2 = 0; i2 < this.X.getSlots(); ++i2) {
                                ItemStack itemStack = this.X.getStackInSlot(i2);
                                try {
                                    if (itemStack.func_190926_b()) {
                                        continue;
                                    }
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                for (int i3 = 0; i3 < iItemHandler.getSlots(); ++i3) {
                                    ItemStack itemStack2 = iItemHandler.insertItem(i3, itemStack, true);
                                    if (itemStack2.func_190916_E() == itemStack.func_190916_E()) continue;
                                    n2 = 1;
                                    break;
                                }
                                try {
                                    if (n2 == 0) continue;
                                    break;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                            }
                            try {
                                if (n2 == 0) {
                                    continue;
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            if (blockPos2 == null) {
                                blockPos2 = blockPos3;
                                continue;
                            }
                            if (!(this.func_174818_b(blockPos2) > this.func_174818_b(blockPos3))) continue;
                            blockPos2 = blockPos3;
                        }
                        try {
                            if (blockPos2 == null) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        if (this.func_70011_f(blockPos2.func_177958_n(), blockPos2.func_177956_o(), blockPos2.func_177952_p()) < 2.0) {
                            tileEntityChest = (TileEntityChest)this.field_70170_p.func_175625_s(blockPos2);
                            blockPos3 = tileEntityChest.getSingleChestHandler();
                            block25: for (int i4 = 0; i4 < this.X.getSlots(); ++i4) {
                                iItemHandler = this.X.getStackInSlot(i4);
                                try {
                                    if (iItemHandler.func_190926_b()) {
                                        continue;
                                    }
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                for (n2 = 0; n2 < blockPos3.getSlots(); ++n2) {
                                    ItemStack itemStack = blockPos3.insertItem(n2, (ItemStack)iItemHandler, false);
                                    try {
                                        if (itemStack.func_190916_E() <= 0) {
                                            this.X.setStackInSlot(i4, ItemStack.field_190927_a);
                                            continue block25;
                                        }
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw ff.a(illegalArgumentException);
                                    }
                                    this.X.setStackInSlot(i4, itemStack);
                                    iItemHandler = itemStack;
                                }
                            }
                            this.field_70170_p.func_184133_a(null, blockPos2, SoundEvents.field_187654_U, SoundCategory.BLOCKS, 1.0f, 1.0f);
                            return true;
                        }
                        try {
                            try {
                                if (Math.abs(blockPos2.func_177956_o() - this.func_180425_c().func_177956_o()) <= 4) break block35;
                                if (!bl2) break block36;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            this.b(blockPos2);
                            break block37;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    return false;
                }
                tileEntityChest = this.func_70661_as();
                blockPos3 = this.c(blockPos2);
                try {
                    try {
                        tileEntityChest.func_75492_a((double)blockPos3.func_177958_n(), (double)blockPos3.func_177956_o(), (double)blockPos3.func_177952_p(), (double)0.35f);
                        if (tileEntityChest.func_75505_d() != null) break block37;
                        if (!bl2) break block38;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    this.b(blockPos2);
                    break block37;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            return false;
        }
        return true;
    }

    boolean c(UUID uUID, bs bs2) {
        List<ff> list = com.trolmastercard.sexmod.ax.n(uUID);
        Collection<bs> collection = com.trolmastercard.sexmod.ax.p(uUID);
        ff ff2 = null;
        Vec3d vec3d = new Vec3d((double)bs2.b().func_177958_n(), (double)bs2.b().func_177956_o(), (double)bs2.b().func_177952_p());
        for (ff ff3 : list) {
            boolean bl2 = false;
            for (bs bs3 : collection) {
                if (!bs3.b(ff3)) continue;
                bl2 = true;
                break;
            }
            try {
                if (bl2) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (ff3.ae() != null) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            if (ff2 == null) {
                ff2 = ff3;
                continue;
            }
            if (!(ff2.func_174791_d().func_72438_d(vec3d) > ff3.func_174791_d().func_72438_d(vec3d))) continue;
            ff2 = ff3;
        }
        return this.equals(ff2);
    }

    /*
     * Exception decompiling
     */
    void a(UUID var1_1, bs var2_2, BlockPos var3_3) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 5[SWITCH]
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
     * Could not resolve type clashes
     * Loose catch block
     */
    void a(UUID uUID, BlockPos blockPos, bs bs2) {
        block58: {
            Object object2;
            try {
                if (this.y() != fp.MINE) {
                    this.a(blockPos, uUID);
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                --this.W;
                if (this.W > 0) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (this.W == 0) {
                    ge.b.sendToAllAround((IMessage)new a1(this.f()), this.P());
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                if (this.field_70170_p.func_180495_p(blockPos).func_177230_c() == Blocks.field_150350_a) {
                    this.a(uUID, bs2, blockPos);
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            try {
                --this.aR;
                if (this.aR >= 0) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            this.aR = 24;
            this.W = 78;
            HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
            EntityPlayer entityPlayer = this.z();
            for (Object object2 : bs2.g()) {
                block55: {
                    try {
                        if (this.field_70170_p.func_180495_p((BlockPos)object2).func_177230_c() == Blocks.field_150350_a) {
                            hashSet.add((BlockPos)object2);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    if (object2.func_177958_n() != blockPos.func_177958_n()) break block55;
                    try {
                        if (object2.func_177952_p() == blockPos.func_177952_p()) {
                            continue;
                        }
                        break block55;
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                try {
                    ItemStack itemStack = this.field_70170_p.func_180495_p((BlockPos)object2).func_177230_c().func_185473_a(this.field_70170_p, blockPos, this.field_70170_p.func_180495_p(blockPos));
                    try {
                        if (itemStack.func_77973_b() != Items.field_190931_a) {
                            this.b(itemStack);
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    Main.LOGGER.error("Couldn't get an item out of the block that a kobold just destroyed when falling a tree. As a result, the block wasn't added into the kobolds inventory. If you see this message, pls tell trol about it and send her the following stacktrace. Do you maybe remember what block the kobold just removed? Stacktrace follwing:");
                    Main.LOGGER.warn("block in question: " + this.field_70170_p.func_180495_p((BlockPos)object2).func_177230_c().func_149739_a());
                    Main.LOGGER.error(illegalArgumentException.getMessage());
                }
                try {
                    this.ad = this.a((BlockPos)object2);
                    this.field_70170_p.func_175655_b((BlockPos)object2, false);
                    bs2.a((BlockPos)object2);
                    bs2.b(hashSet);
                    hashSet.add((BlockPos)object2);
                    if (entityPlayer != null) {
                        ge.b.sendTo((IMessage)new h6(hashSet, false), (EntityPlayerMP)entityPlayer);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                return;
            }
            try {
                ItemStack itemStack = this.field_70170_p.func_180495_p(blockPos).func_177230_c().func_185473_a(this.field_70170_p, blockPos, this.field_70170_p.func_180495_p(blockPos));
                try {
                    if (itemStack.func_77973_b() != Items.field_190931_a) {
                        this.b(itemStack);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                Main.LOGGER.error("Couldn't get an item out of the block that a kobold just destroyed when falling a tree. As a result, the block wasn't added into the kobolds inventory. If you see this message, pls tell trol about it and send her the following stacktrace. Do you maybe remember what block the kobold just removed? Stacktrace follwing:");
                Main.LOGGER.warn("block in question: " + this.field_70170_p.func_180495_p(blockPos).func_177230_c().func_149739_a());
                Main.LOGGER.error(illegalArgumentException.getMessage());
            }
            this.ad = this.a(blockPos);
            this.field_70170_p.func_175655_b(blockPos, false);
            int n2 = 0;
            for (BlockPos blockPos2 : bs2.g()) {
                try {
                    if (!(this.field_70170_p.func_180495_p(blockPos2).func_177230_c() instanceof BlockLog)) continue;
                    ++n2;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            object2 = new HashSet();
            try {
                for (int i2 = 0; i2 < n2; ++i2) {
                    ((HashSet)object2).add(blockPos.func_177982_a(0, i2, 0));
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            HashSet<BlockPos> hashSet2 = new HashSet<BlockPos>();
            for (BlockPos blockPos3 : bs2.g()) {
                try {
                    if (((HashSet)object2).contains(blockPos3)) continue;
                    hashSet2.add(blockPos3);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            try {
                try {
                    if (hashSet2.isEmpty() || entityPlayer == null) break block58;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                ge.b.sendTo((IMessage)new h6(hashSet2, false), (EntityPlayerMP)entityPlayer);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        int n3 = 1;
        while (true) {
            BlockPos blockPos3;
            blockPos3 = blockPos.func_177982_a(0, n3, 0);
            IBlockState iBlockState = this.field_70170_p.func_180495_p(blockPos3);
            if (this.field_70170_p.func_180495_p(blockPos3).func_177230_c() instanceof BlockLog) {
                this.field_70170_p.func_175655_b(blockPos3, false);
                EntityFallingBlock entityFallingBlock = new EntityFallingBlock(this.field_70170_p, (double)blockPos3.func_177958_n() + 0.5, (double)blockPos3.func_177956_o(), (double)blockPos3.func_177952_p() + 0.5, iBlockState);
                entityFallingBlock.field_145812_b = 1;
                this.field_70170_p.func_72838_d((Entity)entityFallingBlock);
            }
            try {
                if (!bs2.g().contains(blockPos3)) {
                    break;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            ++n3;
        }
    }

    ItemStack a(BlockPos blockPos) {
        block26: {
            int n2;
            int n3;
            block25: {
                block24: {
                    block23: {
                        block22: {
                            ItemStack itemStack;
                            try {
                                itemStack = this.field_70170_p.func_180495_p(blockPos).func_177230_c().func_185473_a(this.field_70170_p, blockPos, this.field_70170_p.func_180495_p(blockPos));
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                Main.LOGGER.error("Couldn't turn a wooden block into an item to get its meta data. As a result the kobold is just gonna plant a oak saplinig instead. If you see this message, pls tell trol about it and send her the following stacktrace. Do you maybe remember what block the kobold just removed? Stacktrace follwing:");
                                Main.LOGGER.warn("block in question: " + this.field_70170_p.func_180495_p(blockPos).func_177230_c().func_149739_a());
                                Main.LOGGER.error(illegalArgumentException.getMessage());
                                return new ItemStack(Blocks.field_150345_g, 1, 0);
                            }
                            n3 = ItemBlock.func_150891_b((Item)itemStack.func_77973_b());
                            n2 = itemStack.func_77973_b().getMetadata(itemStack);
                            try {
                                try {
                                    if (n3 != 17 || n2 != 1) break block22;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                return new ItemStack(Blocks.field_150345_g, 1, 1);
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                        }
                        try {
                            try {
                                if (n3 != 17 || n2 != 2) break block23;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            return new ItemStack(Blocks.field_150345_g, 1, 2);
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    try {
                        try {
                            if (n3 != 17 || n2 != 3) break block24;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        return new ItemStack(Blocks.field_150345_g, 1, 3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                try {
                    try {
                        if (n3 != 162 || n2 != 0) break block25;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    return new ItemStack(Blocks.field_150345_g, 1, 4);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            try {
                try {
                    if (n3 != 162 || n2 != 1) break block26;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                return new ItemStack(Blocks.field_150345_g, 1, 5);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        return new ItemStack(Blocks.field_150345_g, 1, 0);
    }

    void a(BlockPos blockPos, UUID uUID) {
        BlockPos blockPos2;
        block34: {
            EntityPlayer entityPlayer;
            block35: {
                ArrayList<BlockPos> arrayList;
                block33: {
                    block32: {
                        block31: {
                            block30: {
                                blockPos2 = null;
                                arrayList = new ArrayList<BlockPos>();
                                try {
                                    try {
                                        if (!this.field_70170_p.func_180495_p(blockPos.func_177978_c().func_177977_b()).func_185917_h() || this.field_70170_p.func_180495_p(blockPos.func_177978_c()).func_185913_b()) break block30;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw ff.a(illegalArgumentException);
                                    }
                                    arrayList.add(blockPos.func_177978_c());
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                            }
                            try {
                                try {
                                    if (!this.field_70170_p.func_180495_p(blockPos.func_177974_f().func_177977_b()).func_185917_h() || this.field_70170_p.func_180495_p(blockPos.func_177974_f()).func_185913_b()) break block31;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                arrayList.add(blockPos.func_177974_f());
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                        }
                        try {
                            try {
                                if (!this.field_70170_p.func_180495_p(blockPos.func_177968_d().func_177977_b()).func_185917_h() || this.field_70170_p.func_180495_p(blockPos.func_177968_d()).func_185913_b()) break block32;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            arrayList.add(blockPos.func_177968_d());
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    try {
                        try {
                            if (!this.field_70170_p.func_180495_p(blockPos.func_177976_e().func_177977_b()).func_185917_h() || this.field_70170_p.func_180495_p(blockPos.func_177976_e()).func_185913_b()) break block33;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        arrayList.add(blockPos.func_177976_e());
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                for (BlockPos blockPos3 : arrayList) {
                    if (blockPos2 == null) {
                        blockPos2 = blockPos3;
                        continue;
                    }
                    double d10 = new Vec3d((double)((float)blockPos2.func_177958_n() + 0.5f), (double)blockPos2.func_177956_o(), (double)((float)blockPos2.func_177952_p() + 0.5f)).func_72438_d(this.func_174791_d());
                    double d11 = new Vec3d((double)((float)blockPos3.func_177958_n() + 0.5f), (double)blockPos3.func_177956_o(), (double)((float)blockPos3.func_177952_p() + 0.5f)).func_72438_d(this.func_174791_d());
                    if (!(d11 < d10)) continue;
                    blockPos2 = blockPos3;
                }
                if (blockPos2 == null) {
                    com.trolmastercard.sexmod.ax.b(uUID, this);
                    entityPlayer = this.z();
                    try {
                        if (entityPlayer == null) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    entityPlayer.func_146105_b((ITextComponent)new TextComponentString("Your kobolds cannot fall this tree because it starts underground"), true);
                    return;
                }
                try {
                    try {
                        if (!(this.func_180425_c().func_185332_f(blockPos2.func_177958_n(), blockPos2.func_177956_o(), blockPos2.func_177952_p()) > 1.0)) break block34;
                        if (Math.abs(this.func_180425_c().func_177956_o() - blockPos2.func_177956_o()) <= 4) break block35;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    this.b(blockPos2);
                    return;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            entityPlayer = this.c(blockPos2);
            this.func_70661_as().func_75492_a((double)entityPlayer.func_177958_n() + 0.5, (double)entityPlayer.func_177956_o(), (double)entityPlayer.func_177952_p() + 0.5, 0.35);
            this.k();
            return;
        }
        float f10 = 0.0f;
        if (blockPos2.func_177973_b((Vec3i)blockPos).equals((Object)new BlockPos(0, 0, -1))) {
            f10 = 0.0f;
        }
        if (blockPos2.func_177973_b((Vec3i)blockPos).equals((Object)new BlockPos(1, 0, 0))) {
            f10 = 90.0f;
        }
        if (blockPos2.func_177973_b((Vec3i)blockPos).equals((Object)new BlockPos(0, 0, 1))) {
            f10 = 180.0f;
        }
        if (blockPos2.func_177973_b((Vec3i)blockPos).equals((Object)new BlockPos(-1, 0, 0))) {
            f10 = -90.0f;
        }
        this.c(new Vec3d((double)blockPos2.func_177958_n() + 0.5, (double)blockPos2.func_177956_o(), (double)blockPos2.func_177952_p() + 0.5));
        this.b(f10);
        this.m.func_187227_b(G, (Object)true);
        this.m.func_187227_b(at, (Object)true);
        this.b(fp.MINE);
        this.field_70170_p.func_175655_b(blockPos2.func_177984_a(), false);
    }

    void h() {
        try {
            if (this.aA) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        Optional optional = (Optional)this.m.func_187225_a(aL);
        try {
            if (!optional.isPresent()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        this.m.func_187227_b(N, (Object)com.trolmastercard.sexmod.ax.l((UUID)optional.get()).toString());
    }

    @Override
    public void b(fp fp2) {
        block25: {
            block23: {
                block21: {
                    try {
                        block22: {
                            try {
                                try {
                                    if (this.y() != fp.MATING_PRESS_CUM) break block21;
                                    if (fp2 == fp.MATING_PRESS_SOFT) break block22;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw ff.a(illegalArgumentException);
                                }
                                if (fp2 != fp.MATING_PRESS_HARD) break block21;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                        }
                        return;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                try {
                    block24: {
                        try {
                            try {
                                if (this.y() != fp.KOBOLD_ANAL_CUM) break block23;
                                if (fp2 == fp.KOBOLD_ANAL_SLOW) break block24;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw ff.a(illegalArgumentException);
                            }
                            if (fp2 != fp.KOBOLD_ANAL_FAST) break block23;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                    }
                    return;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            try {
                block26: {
                    try {
                        try {
                            if (this.y() != fp.CUMBLOWJOB) break block25;
                            if (fp2 == fp.SUCKBLOWJOB) break block26;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        if (fp2 != fp.THRUSTBLOWJOB) break block25;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                return;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        try {
            if (fp2 == fp.MATING_PRESS_CUM) {
                this.V = 0;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        super.b(fp2);
    }

    public void func_70645_a(DamageSource damageSource) {
        try {
            super.func_70645_a(damageSource);
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        Optional optional = (Optional)this.m.func_187225_a(aL);
        try {
            if (!optional.isPresent()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        UUID uUID = (UUID)optional.get();
        com.trolmastercard.sexmod.ax.a(uUID, this);
        if (this.J()) {
            EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(UUID.fromString((String)this.func_184212_Q().func_187225_a(v)));
            try {
                if (entityPlayer != null) {
                    entityPlayer.func_145747_a((ITextComponent)new TextComponentString(String.format("%s%s%s has perished %suwu", TextFormatting.RED, this.c(), TextFormatting.WHITE, TextFormatting.RED)));
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
    }

    @Override
    protected fp c(fp fp2) {
        try {
            if (fp2 == fp.SUCKBLOWJOB_BLINK) {
                return fp.THRUSTBLOWJOB;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (fp2 == fp.KOBOLD_ANAL_SLOW) {
                return fp.KOBOLD_ANAL_FAST;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
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
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        return fp.CUMBLOWJOB;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                try {
                    try {
                        if (fp2 != fp.KOBOLD_ANAL_SLOW && fp2 != fp.KOBOLD_ANAL_FAST) break block13;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    return fp.KOBOLD_ANAL_CUM;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            try {
                try {
                    if (fp2 != fp.MATING_PRESS_HARD && fp2 != fp.MATING_PRESS_SOFT) break block14;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                return fp.MATING_PRESS_CUM;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        return null;
    }

    @Override
    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        super.func_70014_b(nBTTagCompound);
        nBTTagCompound.func_74776_a("body_size", ((Float)this.m.func_187225_a(aE)).floatValue());
        nBTTagCompound.func_74768_a("eyeColorX", ((BlockPos)this.m.func_187225_a(K)).func_177958_n());
        nBTTagCompound.func_74768_a("eyeColorY", ((BlockPos)this.m.func_187225_a(K)).func_177956_o());
        nBTTagCompound.func_74768_a("eyeColorZ", ((BlockPos)this.m.func_187225_a(K)).func_177952_p());
        nBTTagCompound.func_74778_a("model", (String)this.m.func_187225_a(M));
        nBTTagCompound.func_74778_a("name", (String)this.m.func_187225_a(T));
        nBTTagCompound.func_74778_a("master", (String)this.m.func_187225_a(v));
        nBTTagCompound.func_74782_a("inventory", (NBTBase)this.X.serializeNBT());
        nBTTagCompound.func_74778_a("bodyColor", (String)this.m.func_187225_a(N));
        nBTTagCompound.func_74757_a("editedColorManually", this.aA);
        Optional optional = (Optional)this.m.func_187225_a(aL);
        try {
            if (optional.isPresent()) {
                nBTTagCompound.func_186854_a("tribeId", (UUID)optional.get());
                nBTTagCompound.func_74757_a("isLeader", com.trolmastercard.sexmod.ax.e((UUID)optional.get(), this));
                nBTTagCompound.func_74778_a("tribeName", (String)this.m.func_187225_a(aU));
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
    }

    @Override
    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        block18: {
            UUID uUID;
            block19: {
                super.func_70037_a(nBTTagCompound);
                String string = nBTTagCompound.func_74779_i("model");
                try {
                    if (!"".equals(string)) {
                        this.m.func_187227_b(M, (Object)string);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                BlockPos blockPos = new BlockPos(nBTTagCompound.func_74762_e("eyeColorX"), nBTTagCompound.func_74762_e("eyeColorY"), nBTTagCompound.func_74762_e("eyeColorZ"));
                try {
                    if (!BlockPos.field_177992_a.equals((Object)blockPos)) {
                        this.m.func_187227_b(K, (Object)blockPos);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                this.m.func_187227_b(aE, (Object)Float.valueOf(nBTTagCompound.func_74760_g("body_size")));
                this.m.func_187227_b(T, (Object)nBTTagCompound.func_74779_i("name"));
                this.m.func_187227_b(v, (Object)nBTTagCompound.func_74779_i("master"));
                this.X.deserializeNBT(nBTTagCompound.func_74775_l("inventory"));
                String string2 = nBTTagCompound.func_74779_i("bodyColor");
                try {
                    if (!"".equals(string2)) {
                        this.m.func_187227_b(N, (Object)nBTTagCompound.func_74779_i("bodyColor"));
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
                this.aA = nBTTagCompound.func_74767_n("editedColorManually");
                uUID = nBTTagCompound.func_186857_a("tribeId");
                try {
                    try {
                        try {
                            if (uUID == null || this.field_70128_L) break block18;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        this.m.func_187227_b(aL, (Object)Optional.of((Object)uUID));
                        if (com.trolmastercard.sexmod.ax.o(uUID)) break block19;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    com.trolmastercard.sexmod.ax.a(uUID, EyeAndKoboldColor.valueOf((String)this.m.func_187225_a(N)));
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            try {
                com.trolmastercard.sexmod.ax.c(uUID, this);
                if (nBTTagCompound.func_74767_n("isLeader")) {
                    com.trolmastercard.sexmod.ax.d(uUID, this);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
            this.m.func_187227_b(aU, (Object)nBTTagCompound.func_74779_i("tribeName"));
        }
    }

    @Override
    public boolean a() {
        boolean bl2;
        try {
            if (this.h()) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        Block block = this.field_70170_p.func_180495_p(this.func_180425_c().func_177982_a(0, 1, 0)).func_177230_c();
        try {
            bl2 = !block.func_176205_b((IBlockAccess)this.field_70170_p, this.func_180425_c().func_177982_a(0, 1, 0));
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        return bl2;
    }

    boolean f() {
        int n2 = 0;
        while (true) {
            block5: {
                try {
                    try {
                        if (n2 >= this.X.getSlots()) break;
                        if (this.X.getStackInSlot(n2).func_190926_b()) break block5;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    return false;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            ++n2;
        }
        return true;
    }

    boolean a(bs bs2) {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        for (BlockPos blockPos : bs2.g()) {
            try {
                IBlockState iBlockState = this.field_70170_p.func_180495_p(blockPos);
                ItemStack itemStack = iBlockState.func_177230_c().func_185473_a(this.field_70170_p, blockPos, iBlockState);
                arrayList.add(itemStack);
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
        return this.a((List<ItemStack>)arrayList);
    }

    boolean a(ItemStack itemStack) {
        return this.a(this.X, itemStack, true, false);
    }

    boolean a(List<ItemStack> list) {
        ItemStackHandler itemStackHandler = new ItemStackHandler(this.X.getSlots());
        try {
            for (int i2 = 0; i2 < itemStackHandler.getSlots(); ++i2) {
                itemStackHandler.setStackInSlot(i2, this.X.getStackInSlot(i2));
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        for (ItemStack itemStack : list) {
            try {
                if (this.a(itemStackHandler, itemStack, true, false)) continue;
                return false;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw ff.a(illegalArgumentException);
            }
        }
        return true;
    }

    boolean b(ItemStack itemStack) {
        return this.a(this.X, itemStack, false, true);
    }

    boolean a(ItemStackHandler itemStackHandler, ItemStack itemStack, boolean bl2, boolean bl3) {
        ItemStack itemStack2;
        int n2;
        for (n2 = 0; n2 < itemStackHandler.getSlots(); ++n2) {
            int n3;
            block22: {
                block23: {
                    itemStack2 = itemStackHandler.getStackInSlot(n2);
                    try {
                        if (itemStack2.func_77973_b() != itemStack.func_77973_b()) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    try {
                        if (itemStack2.func_77960_j() != itemStack.func_77960_j()) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    n3 = itemStack2.func_77976_d();
                    try {
                        try {
                            if (n3 <= itemStack.func_190916_E() + itemStack2.func_190916_E()) break block22;
                            if (bl2) break block23;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw ff.a(illegalArgumentException);
                        }
                        itemStack2.func_190920_e(itemStack2.func_190916_E() + itemStack.func_190916_E());
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                }
                return true;
            }
            int n4 = n3 - itemStack2.func_190916_E();
            itemStack2.func_190920_e(n3);
            itemStack.func_190920_e(itemStack.func_190916_E() - n4);
        }
        for (n2 = 0; n2 < itemStackHandler.getSlots(); ++n2) {
            block24: {
                itemStack2 = itemStackHandler.getStackInSlot(n2);
                try {
                    try {
                        if (itemStack2.func_77973_b() != Items.field_190931_a) continue;
                        if (bl2) break block24;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw ff.a(illegalArgumentException);
                    }
                    itemStackHandler.setStackInSlot(n2, itemStack);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw ff.a(illegalArgumentException);
                }
            }
            return true;
        }
        try {
            if (bl2) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        try {
            if (!bl3) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        EntityItem entityItem = new EntityItem(this.field_70170_p);
        entityItem.func_92058_a(itemStack);
        entityItem.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70170_p.func_72838_d((Entity)entityItem);
        return false;
    }

    void b(SoundEvent soundEvent, float f10) {
        float f11 = 0.25f - ((Float)this.m.func_187225_a(aE)).floatValue();
        double d10 = f11 / 0.25f;
        float f12 = (float)b6.b((double)0.9f, (double)1.1f, d10);
        this.a(soundEvent, f10, f12);
    }

    void b(SoundEvent soundEvent) {
        this.b(soundEvent, 1.0f);
    }

    void a(SoundEvent[] soundEventArray) {
        this.b(soundEventArray, 1.0f);
    }

    void b(SoundEvent[] soundEventArray, float f10) {
        this.b(soundEventArray[this.func_70681_au().nextInt(soundEventArray.length)], f10);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 18[SWITCH]
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
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        AnimationController.ISoundListener iSoundListener = var1_1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 33[SWITCH]
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

    public int func_70302_i_() {
        return 27;
    }

    public boolean func_191420_l() {
        return false;
    }

    public ItemStack func_70301_a(int n2) {
        try {
            if (n2 >= this.X.getSlots()) {
                return ItemStack.field_190927_a;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw ff.a(illegalArgumentException);
        }
        return this.X.getStackInSlot(n2);
    }

    public ItemStack func_70298_a(int n2, int n3) {
        return this.X.extractItem(n2, n3, false);
    }

    public ItemStack func_70304_b(int n2) {
        return this.X.extractItem(n2, this.X.getStackInSlot(n2).func_190916_E(), false);
    }

    public void func_70299_a(int n2, ItemStack itemStack) {
        this.X.setStackInSlot(n2, itemStack);
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer entityPlayer) {
        return true;
    }

    public void func_174889_b(EntityPlayer entityPlayer) {
    }

    public void func_174886_c(EntityPlayer entityPlayer) {
    }

    public boolean func_94041_b(int n2, ItemStack itemStack) {
        return true;
    }

    public int func_174887_a_(int n2) {
        return n2;
    }

    public void func_174885_b(int n2, int n3) {
    }

    public int func_174890_g() {
        return 0;
    }

    public void func_174888_l() {
    }

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    public static class c {
        int a = 0;

        @SubscribeEvent
        public void a(LivingDeathEvent livingDeathEvent) {
            if (livingDeathEvent.getEntityLiving() instanceof ff) {
                ff ff2 = (ff)livingDeathEvent.getEntityLiving();
                try {
                    if (ff2.field_70170_p.field_72995_K) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
                }
                for (int i2 = 0; i2 < ff2.X.getSlots(); ++i2) {
                    ItemStack itemStack = ff2.X.getStackInSlot(i2);
                    try {
                        if (itemStack.func_77973_b() == Items.field_190931_a) continue;
                        ff2.func_145779_a(itemStack.func_77973_b(), itemStack.func_190916_E());
                        continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
                    }
                }
            }
        }

        @SubscribeEvent
        public void b(LivingHurtEvent livingHurtEvent) {
            EntityPlayer entityPlayer;
            Entity entity = livingHurtEvent.getEntity();
            World world = entity.func_130014_f_();
            try {
                if (world.field_72995_K) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
            try {
                if (!(entity instanceof ff)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
            ff ff2 = (ff)entity;
            Optional optional = (Optional)ff2.func_184212_Q().func_187225_a(aL);
            try {
                if (!optional.isPresent()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
            Entity entity2 = livingHurtEvent.getSource().func_76346_g();
            try {
                if (entity2 == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
            try {
                if (!(entity2 instanceof EntityLivingBase)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
            if (entity2 instanceof EntityPlayer) {
                entityPlayer = (EntityPlayer)entity2;
                try {
                    if (entityPlayer.field_71075_bZ.field_75098_d) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
                }
                try {
                    if (entityPlayer.equals((Object)ff2.z())) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
                }
            }
            entityPlayer = ff2.z();
            try {
                if (entityPlayer != null) {
                    entityPlayer.func_146105_b((ITextComponent)new TextComponentString(TextFormatting.RED + "Your Tribe is under Attack!"), true);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
            com.trolmastercard.sexmod.ax.a((UUID)optional.get(), (EntityLivingBase)entity2);
        }

        @SubscribeEvent
        public void a(WorldEvent.Unload unload) {
            try {
                for (em em2 : em.ad()) {
                    try {
                        if (!(em2 instanceof ff)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
                    }
                    ff ff2 = (ff)em2;
                    Optional optional = (Optional)ff2.func_184212_Q().func_187225_a(aL);
                    try {
                        if (!optional.isPresent()) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
                    }
                    try {
                        if (!com.trolmastercard.sexmod.ax.e((UUID)optional.get(), ff2)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
                    }
                    ff2.s((UUID)optional.get());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }

        @SubscribeEvent
        public void a(LivingHurtEvent livingHurtEvent) {
            try {
                if (livingHurtEvent.getSource() != DamageSource.field_76368_d) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
            Entity entity = livingHurtEvent.getEntity();
            try {
                if (entity instanceof ff) {
                    entity.func_70107_b(entity.field_70165_t, entity.field_70163_u + 1.0, entity.field_70161_v);
                    livingHurtEvent.setCanceled(true);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(TickEvent.ClientTickEvent clientTickEvent) {
            WorldClient worldClient = Minecraft.func_71410_x().field_71441_e;
            try {
                if (worldClient == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
            try {
                if (++this.a % 20 == 0) {
                    ge.b.sendToServer((IMessage)new b3());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.ff$c.a(concurrentModificationException);
            }
        }

        private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
            return concurrentModificationException;
        }
    }
}

