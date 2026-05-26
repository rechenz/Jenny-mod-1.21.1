/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  javax.vecmath.Vector2f
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
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
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerChangedDimensionEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.ClientProxy;
import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.ai;
import com.trolmastercard.sexmod.by;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.dy;
import com.trolmastercard.sexmod.e1;
import com.trolmastercard.sexmod.e4;
import com.trolmastercard.sexmod.ea;
import com.trolmastercard.sexmod.eh;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fh;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.g5;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import com.trolmastercard.sexmod.hz;
import com.trolmastercard.sexmod.s;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Vector2f;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class e3
extends e4
implements ai {
    public static final by ax = by.DARK_GREEN;
    public static final Vec3i ah = new Vec3i(11, 6, 11);
    public static final Vec3d aB = new Vec3d(5.0, 1.0, 9.0);
    public static final Vec3d af = new Vec3d(3.0, -1.0, 6.0);
    public static final Vec3d ao = new Vec3d(1.0, 1.0, 5.0);
    public static final Vec3d au = new Vec3d(-6.0, -1.0, 3.0);
    public static final Vec3d aM = new Vec3d(5.0, 1.0, 1.0);
    public static final Vec3d W = new Vec3d(-3.0, -1.0, -6.0);
    public static final Vec3d U = new Vec3d(9.0, 1.0, 5.0);
    public static final Vec3d as = new Vec3d(0.0, -1.0, -4.0);
    public static final Vec3d aT = new Vec3d(1.0, -1.0, -3.0);
    public static final Vec3d ap = new Vec3d(-1.0, -1.0, -3.0);
    public static final Vec3d at = new Vec3d(6.0, -1.0, -3.0);
    public static final int aj = 39;
    public static final int ae = 15;
    public static final int aE = 8400;
    static final int aH = 45;
    static final int ad = 32000;
    static final int aw = 26;
    static final int V = 205;
    static final int aL = 100;
    static final int aA = 1200;
    static final int ak = 30;
    static final int aW = 37;
    static final float aU = 2.0f;
    static final int aI = 5;
    static final int S = 100;
    static final int aq = 20;
    static final float aG = 0.825f;
    static final Vector2f aS = new Vector2f(0.5f, 0.99f);
    static final HashSet<Item> ag = new HashSet<Item>(Arrays.asList(Items.field_151013_M, Items.field_151136_bY, Items.field_151043_k, Items.field_151153_ao, Items.field_151006_E, Items.field_151011_C, Items.field_151005_D, Items.field_151010_B, Items.field_151150_bK, Items.field_151169_ag, Items.field_151151_aj, Items.field_151171_ah, Items.field_151149_ai, Items.field_151043_k, Items.field_151074_bl, Item.func_150898_a((Block)Blocks.field_150340_R), Item.func_150898_a((Block)Blocks.field_150352_o)));
    public static final DataParameter<String> Q = EntityDataManager.func_187226_a(e3.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(122);
    public static final DataParameter<String> aK = EntityDataManager.func_187226_a(e3.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(123);
    public static final DataParameter<ItemStack> a0 = EntityDataManager.func_187226_a(e3.class, (DataSerializer)DataSerializers.field_187196_f).func_187156_b().func_187161_a(124);
    public static final DataParameter<Boolean> aC = EntityDataManager.func_187226_a(e3.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(125);
    public static final DataParameter<Boolean> aV = EntityDataManager.func_187226_a(e3.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(126);
    public boolean aX = false;
    public float ac = 0.0f;
    public long av = -1L;
    public Vec3d al = Vec3d.field_186680_a;
    List<UUID> T = new ArrayList<UUID>();
    int aO = 31520;
    int aQ = -1;
    public int aR = -1;
    boolean aZ = false;
    BlockPos R = null;
    int Y = 0;
    int aa = 0;
    int aJ = 0;
    int an = -1;
    int am = 0;
    long ai = 0L;
    List<e3> ab = new ArrayList<e3>();
    int aY = -1;
    int az = -1;
    fp aN = null;
    public float ar = 1.0f;
    int Z = -1;
    boolean aD = true;
    boolean aF = true;
    boolean X = false;
    String aP = "";
    boolean ay = false;

    public e3(World world) {
        super(world);
        this.func_70105_a(e3.aS.x, e3.aS.y);
    }

    public e3(World world, @Nonnull String string, int n2) {
        this(world);
        this.m.func_187227_b(aK, (Object)string);
        this.m.func_187227_b(M, (Object)this.a(new StringBuilder(), n2));
    }

    public e3(World world, boolean bl2, float f10, Vec3d vec3d) {
        this(world);
        if (!bl2) {
            return;
        }
        this.m.func_187227_b(M, (Object)this.b(new StringBuilder()));
        this.ac = f10;
        this.al = vec3d;
        this.aX = true;
        this.c(vec3d);
        this.b(f10);
        this.b(fp.SIT);
        this.a(true);
        this.func_70107_b(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
    }

    @Override
    public void g() {
        super.g();
        this.a((UUID)null);
        this.field_70145_X = false;
        this.func_189654_d(false);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        eh eh2 = eh.values()[this.func_70681_au().nextInt(eh.values().length)];
        this.m.func_187214_a(K, (Object)new BlockPos(eh2.a()));
        this.m.func_187214_a(N, (Object)ax.name());
        this.m.func_187214_a(Q, (Object)"");
        this.m.func_187214_a(aK, (Object)"");
        this.m.func_187214_a(a0, (Object)ItemStack.field_190927_a);
        this.m.func_187214_a(aC, (Object)false);
        this.m.func_187214_a(aV, (Object)false);
    }

    @Override
    protected void a() {
        dy.c();
    }

    public void func_70106_y() {
        try {
            super.func_70106_y();
            this.a((UUID)null);
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        ItemStack itemStack = (ItemStack)this.m.func_187225_a(a0);
        try {
            if (itemStack == ItemStack.field_190927_a) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityItem entityItem = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, itemStack);
        this.field_70170_p.func_72838_d((Entity)entityItem);
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            if ("take ur stuff back".equals(string)) {
                this.b(fp.START_THROWING);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if ("use her".equals(string)) {
                this.c(uUID);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
    }

    public void c(UUID uUID) {
        this.aY = 0;
        fh.b();
        d3.a(false);
        this.e(uUID);
    }

    public void b(UUID uUID) {
        this.az = 0;
        fh.b();
        d3.a(false);
        this.e(uUID);
    }

    @Override
    public String c() {
        return "Goblin";
    }

    public float func_70047_e() {
        return 0.75f;
    }

    @Override
    public float i() {
        return 0.1f;
    }

    @Override
    public void a(UUID uUID) {
        try {
            if (uUID == null) {
                this.m.func_187227_b(Q, (Object)"");
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.m.func_187227_b(Q, (Object)uUID.toString());
    }

    @Override
    @Nullable
    public UUID e() {
        String string = (String)this.m.func_187225_a(Q);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (Exception exception) {
            throw e3.a(exception);
        }
        try {
            return UUID.fromString((String)this.m.func_187225_a(Q));
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public int c() {
        return this.aQ;
    }

    @Override
    public void b(int n2) {
        this.aQ = n2;
    }

    protected String b(StringBuilder stringBuilder) {
        e3.a(stringBuilder, 3);
        e3.a(stringBuilder, 2);
        e3.a(stringBuilder, 2);
        e3.c(stringBuilder, 7);
        e3.c(stringBuilder, 7);
        e3.a(stringBuilder, 5);
        e3.a(stringBuilder, g5.values().length - 1);
        e3.a(stringBuilder, by.values().length - 1);
        e3.a(stringBuilder, eh.values().length - 1);
        e3.c(stringBuilder, 1);
        return stringBuilder.toString();
    }

    @Override
    protected String a(StringBuilder stringBuilder) {
        e3.a(stringBuilder, 3);
        e3.a(stringBuilder, 2);
        e3.a(stringBuilder, 2);
        e3.a(stringBuilder, 8);
        e3.a(stringBuilder, 8);
        e3.a(stringBuilder, 5);
        e3.a(stringBuilder, g5.values().length - 1);
        e3.a(stringBuilder, by.values().length - 1);
        e3.a(stringBuilder, eh.values().length - 1);
        e3.c(stringBuilder, 0);
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> D() {
        return new ArrayList<Integer>(){
            {
                this.add(4);
                this.add(3);
                this.add(3);
                this.add(16);
                this.add(16);
                this.add(6);
                this.add(g5.values().length);
                this.add(by.values().length);
                this.add(eh.values().length);
            }
        };
    }

    @Override
    public List<Integer> u() {
        return Collections.singletonList(2);
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
    public void a(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int n2 : list) {
            e3.c(stringBuilder, n2);
        }
        try {
            e3.c(stringBuilder, Integer.parseInt(e3.a(this)[9]));
            this.m.func_187227_b(M, (Object)stringBuilder.toString());
            if (Main.proxy instanceof ClientProxy) {
                dy.c();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
    }

    void i() {
        try {
            if (this.d == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry entry : this.d) {
            int n2 = (Integer)((Map.Entry)entry.getValue()).getValue();
            e3.c(stringBuilder, n2);
        }
        e3.c(stringBuilder, Integer.parseInt(e3.a(this)[9]));
        this.m.func_187227_b(M, (Object)stringBuilder.toString());
        dy.c();
    }

    protected String a(StringBuilder stringBuilder, int n2) {
        e3.a(stringBuilder, 3);
        e3.a(stringBuilder, 2);
        e3.a(stringBuilder, 2);
        e3.a(stringBuilder, 7);
        e3.a(stringBuilder, 7);
        e3.a(stringBuilder, 5);
        e3.a(stringBuilder, g5.values().length - 1);
        e3.c(stringBuilder, n2);
        e3.a(stringBuilder, eh.values().length - 1);
        e3.c(stringBuilder, 0);
        return stringBuilder.toString();
    }

    @Override
    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        try {
            super.func_70014_b(nBTTagCompound);
            nBTTagCompound.func_74778_a("bodyColor", (String)this.m.func_187225_a(N));
            nBTTagCompound.func_74768_a("eyeColorX", ((BlockPos)this.m.func_187225_a(K)).func_177958_n());
            nBTTagCompound.func_74768_a("eyeColorY", ((BlockPos)this.m.func_187225_a(K)).func_177956_o());
            nBTTagCompound.func_74768_a("eyeColorZ", ((BlockPos)this.m.func_187225_a(K)).func_177952_p());
            nBTTagCompound.func_74778_a("model", (String)this.m.func_187225_a(M));
            nBTTagCompound.func_74778_a("girlID", (String)this.m.func_187225_a(u));
            nBTTagCompound.func_74778_a("queen", (String)this.m.func_187225_a(aK));
            nBTTagCompound.func_74757_a("isQueen", this.aX);
            nBTTagCompound.func_74757_a("isTamed", ((Boolean)this.m.func_187225_a(aC)).booleanValue());
            nBTTagCompound.func_74768_a("robTicks", this.aO);
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        nBTTagCompound.func_74757_a("preggo", ((Boolean)this.m.func_187225_a(aV)).booleanValue());
        nBTTagCompound.func_74776_a("throneRot", this.ac);
        nBTTagCompound.func_74780_a("thronePosX", this.al.field_72450_a);
        nBTTagCompound.func_74780_a("thronePosY", this.al.field_72448_b);
        nBTTagCompound.func_74780_a("thronePosZ", this.al.field_72449_c);
        nBTTagCompound.func_74772_a("impregnationTick", this.av);
        try {
            for (int i2 = 0; i2 < this.T.size(); ++i2) {
                nBTTagCompound.func_74778_a("guard" + i2, this.T.get(i2).toString());
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
    }

    @Override
    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        block10: {
            super.func_70037_a(nBTTagCompound);
            this.aX = nBTTagCompound.func_74767_n("isQueen");
            this.m.func_187227_b(M, (Object)nBTTagCompound.func_74779_i("model"));
            this.m.func_187227_b(N, (Object)nBTTagCompound.func_74779_i("bodyColor"));
            String[] stringArray = e3.a(this);
            try {
                try {
                    if (Integer.parseInt(stringArray[3]) <= 7 && Integer.parseInt(stringArray[4]) <= 7) break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                this.m.func_187227_b(M, (Object)this.a(new StringBuilder(), this.k()));
                Main.LOGGER.log(Level.INFO, "updated an old Goblin");
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        try {
            this.m.func_187227_b(K, (Object)new BlockPos(nBTTagCompound.func_74762_e("eyeColorX"), nBTTagCompound.func_74762_e("eyeColorY"), nBTTagCompound.func_74762_e("eyeColorZ")));
            this.m.func_187227_b(u, (Object)nBTTagCompound.func_74779_i("girlID"));
            this.m.func_187227_b(aK, (Object)nBTTagCompound.func_74779_i("queen"));
            this.m.func_187227_b(aC, (Object)nBTTagCompound.func_74767_n("isTamed"));
            this.aO = nBTTagCompound.func_74762_e("robTicks");
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.ac = nBTTagCompound.func_74760_g("throneRot");
        this.al = new Vec3d(nBTTagCompound.func_74769_h("thronePosX"), nBTTagCompound.func_74769_h("thronePosY"), nBTTagCompound.func_74769_h("thronePosZ"));
        int n2 = 0;
        try {
            while (!"".equals(nBTTagCompound.func_74779_i("guard" + n2))) {
                this.T.add(UUID.fromString(nBTTagCompound.func_74779_i("guard" + n2)));
                ++n2;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.m.func_187227_b(aV, (Object)nBTTagCompound.func_74767_n("preggo"));
        this.av = nBTTagCompound.func_74763_f("impregnationTick");
    }

    protected boolean func_184645_a(EntityPlayer entityPlayer, EnumHand enumHand) {
        block16: {
            block15: {
                block12: {
                    block14: {
                        block13: {
                            try {
                                if (this.field_70170_p.field_72995_K) {
                                    return true;
                                }
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw e3.a(concurrentModificationException);
                            }
                            try {
                                if (this.aX) {
                                    return true;
                                }
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw e3.a(concurrentModificationException);
                            }
                            try {
                                try {
                                    if (this.y() != fp.RUN) break block12;
                                    if (!((double)this.func_70032_d((Entity)entityPlayer) > 3.5)) break block13;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw e3.a(concurrentModificationException);
                                }
                                entityPlayer.func_146105_b((ITextComponent)new TextComponentString("get a bit closer..."), true);
                                break block14;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw e3.a(concurrentModificationException);
                            }
                        }
                        this.c(entityPlayer.func_174791_d());
                        this.b(entityPlayer.field_70177_z);
                        this.b(fp.CATCH);
                        this.m.func_187227_b(h, (Object)"bj");
                        this.a(entityPlayer.getPersistentID());
                        this.e(entityPlayer.getPersistentID());
                        this.func_70661_as().func_75499_g();
                        this.field_70159_w = 0.0;
                        this.field_70181_x = 0.0;
                        this.field_70179_y = 0.0;
                    }
                    return true;
                }
                try {
                    if (!e3.d(entityPlayer.getPersistentID())) break block15;
                    entityPlayer.func_146105_b((ITextComponent)new TextComponentString("you are already carrying a Goblin"), true);
                    break block16;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
            }
            this.a(entityPlayer.getPersistentID());
            this.b(fp.PICK_UP);
            this.aQ = 45;
            this.a(false);
            this.m.func_187227_b(aC, (Object)true);
            this.func_70661_as().func_75499_g();
        }
        return true;
    }

    public static boolean d(UUID uUID) {
        try {
            if (uUID == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            for (em em2 : em.ad()) {
                try {
                    if (!(em2 instanceof ai)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                try {
                    if (em2.field_70170_p.field_72995_K) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                try {
                    if (em2.field_70128_L) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                UUID uUID2 = ((ai)((Object)em2)).e();
                if (!uUID.equals(uUID2)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return false;
    }

    @Override
    protected void func_184651_r() {
        this.o = new df((EntityLiving)this, EntityPlayer.class, 2.0f, 1.0f);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new hz((EntityLiving)this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.o);
    }

    @Override
    public void func_70619_bc() {
        super.func_70619_bc();
        this.f();
        e3.a(this);
        this.m();
        this.B();
        this.J();
        this.E();
        this.t();
        this.w();
        this.b();
        this.d();
        this.h();
        this.o();
        this.u();
        this.n();
    }

    public boolean func_70067_L() {
        fp fp2 = this.y();
        try {
            if (fp2 == fp.THROWN) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (fp2 == fp.RUN) {
                return super.func_70067_L();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (fp2 == fp.AWAIT_PICK_UP) {
                return super.func_70067_L();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.e() != null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (fp2 != fp.NULL) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        return super.func_70067_L();
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
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        vec3d2(d11, d10 + (double)f10, entityPlayer.field_70161_v);
        Vec3d vec3d4 = vec3d;
        Vec3d vec3d5 = new Vec3d(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
        double d12 = vec3d5.func_72438_d(vec3d4);
        double d13 = vec3d4.field_72448_b - vec3d5.field_72448_b;
        this.field_70125_A = (float)(-(Math.sin(d13 / d12) * 57.29577951308232));
    }

    void n() {
        block31: {
            block30: {
                block27: {
                    int n2;
                    int n3;
                    block29: {
                        block28: {
                            block25: {
                                try {
                                    if (!((Boolean)this.m.func_187225_a(aC)).booleanValue()) {
                                        return;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw e3.a(concurrentModificationException);
                                }
                                try {
                                    if (this.ae() != null) {
                                        return;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw e3.a(concurrentModificationException);
                                }
                                try {
                                    if (this.y() != fp.NULL) {
                                        return;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw e3.a(concurrentModificationException);
                                }
                                EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 15.0);
                                try {
                                    try {
                                        if (entityPlayer == null || !(entityPlayer.func_70032_d((Entity)this) < 2.0f)) break block25;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw e3.a(concurrentModificationException);
                                    }
                                    this.b(entityPlayer);
                                    this.func_70661_as().func_75499_g();
                                    return;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw e3.a(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    block26: {
                                        try {
                                            try {
                                                if (this.R == null || this.func_70011_f(this.R.func_177958_n(), this.R.func_177956_o(), this.R.func_177952_p()) > this.l()) break block26;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw e3.a(concurrentModificationException);
                                            }
                                            if (this.Y <= 100) break block27;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw e3.a(concurrentModificationException);
                                        }
                                    }
                                    if (!this.func_70681_au().nextBoolean()) break block28;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw e3.a(concurrentModificationException);
                                }
                                n3 = 1;
                                break block29;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw e3.a(concurrentModificationException);
                            }
                        }
                        n3 = -1;
                    }
                    int n4 = n3 * this.func_70681_au().nextInt(5);
                    try {
                        n2 = this.func_70681_au().nextBoolean() ? 1 : -1;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw e3.a(concurrentModificationException);
                    }
                    int n5 = n2 * this.func_70681_au().nextInt(5);
                    int n6 = cj.a(this.field_70170_p, this.func_180425_c().func_177958_n() + n4, this.func_180425_c().func_177952_p() + n5);
                    this.R = new BlockPos(this.func_180425_c().func_177958_n() + n4, n6, this.func_180425_c().func_177952_p() + n5);
                    this.Y = 0;
                }
                try {
                    if (!(Math.sqrt(this.R.func_177951_i((Vec3i)this.func_180425_c())) > 2.0)) break block30;
                    this.func_70661_as().func_75492_a((double)this.R.func_177958_n(), (double)this.R.func_177956_o(), (double)this.R.func_177952_p(), (double)0.3f);
                    this.k();
                    break block31;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
            }
            ++this.Y;
        }
    }

    double l() {
        return Math.sqrt(800.0);
    }

    void u() {
        try {
            if (this.y() != fp.STAND_UP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (++this.aa < 37) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.aa = 0;
        this.b(fp.NULL);
    }

    @Override
    public void a(int n2) {
        this.aJ = n2;
    }

    @Override
    public int d() {
        return this.aJ;
    }

    void o() {
        try {
            if (this.y() != fp.THROWN) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (!this.field_70122_E) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        int n2 = this.d() + 1;
        try {
            this.a(n2);
            if (n2 < 30) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.a(0);
        this.b(fp.STAND_UP);
    }

    void h() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (!((Boolean)this.m.func_187225_a(aV)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.av + 8400L < this.field_70170_p.func_82737_E()) {
                this.m.func_187227_b(aV, (Object)false);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
    }

    void d() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.ab.isEmpty()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        boolean bl2 = false;
        for (e3 e32 : this.ab) {
            if (!((Boolean)e32.func_184212_Q().func_187225_a(aC)).booleanValue()) continue;
            bl2 = true;
        }
        try {
            if (!bl2) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.h("Farewell my knight. You are welcome once I am breedable again.");
        for (e3 e32 : this.ab) {
            try {
                if (((Boolean)e32.func_184212_Q().func_187225_a(aC)).booleanValue()) continue;
                e32.b(fp.VANISH);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        this.ab.clear();
        this.e((UUID)null);
    }

    void b() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.Z == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (++this.Z < 100) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.Z = -1;
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                this.r();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                this.r();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.e((UUID)null);
        for (e3 e32 : this.ab) {
            e32.e((UUID)null);
        }
        List<e3> list = this.I();
        float f10 = this.ac + 180.0f;
        Vec3d vec3d = this.al.func_178787_e(e3.b(aT, f10));
        Vec3d vec3d2 = this.al.func_178787_e(e3.b(ap, f10));
        Vec3d vec3d3 = this.al.func_178787_e(e3.b(as, f10));
        e3 e33 = (e3)list.get(0);
        e3 e34 = (e3)list.get(1);
        e33.c(vec3d);
        e34.c(vec3d2);
        e33.b(0.0f);
        e34.b(0.0f);
        e33.a(true);
        e34.a(true);
        e33.b(fp.AWAIT_PICK_UP);
        e34.b(fp.AWAIT_PICK_UP);
        e33.func_189654_d(false);
        e34.func_189654_d(false);
        entityPlayer.func_189654_d(false);
        e33.field_70145_X = false;
        e34.field_70145_X = false;
        entityPlayer.field_70145_X = false;
        entityPlayer.field_70177_z = f10;
        entityPlayer.field_70125_A = 30.0f;
        entityPlayer.func_70634_a(vec3d3.field_72450_a, vec3d3.field_72448_b, vec3d3.field_72449_c);
        ge.b.sendTo((IMessage)new gz(true), (EntityPlayerMP)entityPlayer);
        this.h("Thanks to you, my clan is soon going to get a few new members! In return I will bear of one of my guards to serve as your personal Onahole. Choose wisely~");
    }

    void w() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.an == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (++this.an < 205) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.an = -1;
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        Vec3d vec3d = e3.b(new Vec3d(0.0, 0.15625 - (double)entityPlayer.func_70047_e(), -0.8859375), this.ac - 180.0f);
        vec3d = vec3d.func_178787_e(this.o());
        entityPlayer.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
    }

    public static Vec3d b(Vec3d vec3d, float f10) {
        return e3.a(vec3d, 0.0f, f10);
    }

    public static Vec3d a(Vec3d vec3d, float f10, float f11) {
        Vec3d vec3d2 = new Vec3d(vec3d.field_72450_a, vec3d.field_72448_b * Math.cos((double)f10 * (Math.PI / 180)) - vec3d.field_72449_c * Math.sin((double)f10 * (Math.PI / 180)), vec3d.field_72448_b * Math.sin((double)f10 * (Math.PI / 180)) + vec3d.field_72449_c * Math.cos((double)f10 * (Math.PI / 180)));
        Vec3d vec3d3 = new Vec3d(-Math.sin((double)(f11 + 90.0f) * (Math.PI / 180)) * vec3d2.field_72450_a - Math.sin((double)f11 * (Math.PI / 180)) * vec3d2.field_72449_c, vec3d2.field_72448_b, Math.cos((double)(f11 + 90.0f) * (Math.PI / 180)) * vec3d2.field_72450_a + Math.cos((double)f11 * (Math.PI / 180)) * vec3d2.field_72449_c);
        return vec3d3;
    }

    void t() {
        e3 e32;
        Vec3d vec3d;
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.y() != fp.JUMP_0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (++this.am < 26) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.am = 0;
        switch ((int)this.ac) {
            case 90: {
                vec3d = this.al.func_178787_e(au);
                break;
            }
            case 180: {
                vec3d = this.al.func_178787_e(W);
                break;
            }
            case -90: {
                vec3d = this.al.func_178787_e(at);
                break;
            }
            default: {
                vec3d = this.al.func_178787_e(af);
            }
        }
        UUID uUID = this.ae();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.c(vec3d);
        this.b(this.ac);
        this.b(fp.BREEDING_INTRO_0);
        this.field_70145_X = true;
        this.func_189654_d(true);
        Vec3d vec3d2 = e3.b(new Vec3d(0.0, 0.44375 - (double)entityPlayer.eyeHeight, -0.7875), this.ac - 180.0f);
        entityPlayer.field_70145_X = true;
        entityPlayer.func_189654_d(true);
        entityPlayer.func_70634_a(vec3d2.field_72450_a + vec3d.field_72450_a, vec3d2.field_72448_b + vec3d.field_72448_b, vec3d2.field_72449_c + vec3d.field_72449_c);
        List<e3> list = this.I();
        if (list.size() >= 1) {
            e32 = list.get(0);
            e32.c(vec3d);
            e32.b(this.ac);
            e32.b(fp.BREEDING_INTRO_1);
            e32.field_70145_X = true;
            e32.func_189654_d(true);
        }
        if (list.size() >= 2) {
            e32 = list.get(1);
            e32.c(vec3d);
            e32.b(this.ac);
            e32.b(fp.BREEDING_INTRO_2);
            e32.field_70145_X = true;
            e32.func_189654_d(true);
        }
        this.an = 0;
    }

    AxisAlignedBB a(Vec3d vec3d, Vec3d vec3d2) {
        return new AxisAlignedBB(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, vec3d2.field_72450_a, vec3d2.field_72448_b, vec3d2.field_72449_c);
    }

    void E() {
        EntityPlayer entityPlayer;
        block27: {
            block28: {
                try {
                    if (!this.aX) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                try {
                    if (this.ae() != null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                Vec3d vec3d = null;
                switch ((int)this.ac) {
                    case 0: {
                        vec3d = aM;
                        break;
                    }
                    case 90: {
                        vec3d = U;
                        break;
                    }
                    case 180: {
                        vec3d = aB;
                        break;
                    }
                    case -90: {
                        vec3d = ao;
                    }
                }
                try {
                    if (vec3d == null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                Vec3d vec3d2 = this.al.func_178786_a(0.5, 0.0, 0.5).func_178788_d(vec3d);
                AxisAlignedBB axisAlignedBB = this.a(vec3d2, vec3d2.func_72441_c((double)ah.func_177958_n(), (double)ah.func_177956_o(), (double)ah.func_177952_p()));
                List list = this.field_70170_p.func_72872_a(EntityPlayer.class, axisAlignedBB);
                try {
                    if (list.isEmpty()) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                entityPlayer = (EntityPlayer)list.get(0);
                try {
                    if (!entityPlayer.field_70122_E) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                try {
                    try {
                        if (!((Boolean)this.m.func_187225_a(aV)).booleanValue()) break block27;
                        if (this.ai + 1200L >= this.field_70170_p.func_82737_E()) break block28;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw e3.a(concurrentModificationException);
                    }
                    entityPlayer.func_146105_b((ITextComponent)new TextComponentString("The Queen is still pregnant - so no breeding for you uwu"), true);
                    this.ai = this.field_70170_p.func_82737_E();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
            }
            return;
        }
        UUID uUID = entityPlayer.getPersistentID();
        Vec3d vec3d = entityPlayer.func_174791_d();
        float f10 = entityPlayer.field_70177_z + 180.0f;
        ge.b.sendTo((IMessage)new gz(false), (EntityPlayerMP)entityPlayer);
        this.e(uUID);
        this.b(fp.JUMP_0);
        this.c(vec3d);
        this.b(f10);
        this.a(true);
        List<e3> list = this.I();
        if (list.size() > 0) {
            e3 e32 = list.get(0);
            e32.e(uUID);
            e32.b(fp.JUMP_1);
            e32.c(vec3d);
            e32.b(f10);
            e32.a(true);
            if (list.size() > 1) {
                e3 e33 = list.get(1);
                e33.e(uUID);
                e33.b(fp.JUMP_2);
                e33.c(vec3d);
                e33.b(f10);
                e33.a(true);
            }
        }
    }

    List<e3> I() {
        e3 e322;
        try {
            if (this.ab.size() > 1) {
                return this.ab;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        for (e3 e322 : this.ab) {
            this.field_70170_p.func_72900_e((Entity)e322);
        }
        this.ab.clear();
        e3 e33 = new e3(this.field_70170_p, this.f().toString(), this.k());
        e33.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70170_p.func_72838_d((Entity)e33);
        this.ab.add(e33);
        e322 = new e3(this.field_70170_p, this.f().toString(), this.k());
        e322.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70170_p.func_72838_d((Entity)e322);
        this.ab.add(e322);
        return this.ab;
    }

    void f() {
        block11: {
            try {
                if (this.aZ) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                try {
                    try {
                        try {
                            this.field_70145_X = false;
                            this.func_189654_d(false);
                            if (this.aX || ((Boolean)this.m.func_187225_a(aC)).booleanValue()) break block11;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw e3.a(concurrentModificationException);
                        }
                        if (((String)this.m.func_187225_a(aK)).equals("")) break block11;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw e3.a(concurrentModificationException);
                    }
                    if (this.y() != fp.NULL) break block11;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                this.field_70170_p.func_72900_e((Entity)this);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        this.aZ = true;
    }

    void e() {
        e3 e32 = this;
        int n2 = e32.a();
        try {
            if (n2 == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        e32.c(++n2);
        if (n2 == 15) {
            Vec3d vec3d = e3.b(this);
            float f10 = e3.d(this);
            float f11 = e3.c(this);
            this.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
            Vec3d vec3d2 = e3.a(new Vec3d(0.0, 0.0, 1.5), f10, f11);
            try {
                this.field_70159_w = vec3d2.field_72450_a;
                this.field_70181_x = vec3d2.field_72448_b;
                this.field_70179_y = vec3d2.field_72449_c;
                if (!this.field_70170_p.field_72995_K) {
                    this.b(f11);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        try {
            this.field_70145_X = false;
            this.func_189654_d(false);
            if (n2 == 39) {
                this.c(-1);
                this.b(fp.THROWN);
                this.e((UUID)null);
                this.a((UUID)null);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
    }

    public static Vec3d b(em em2) {
        ai ai2 = (ai)((Object)em2);
        UUID uUID = ai2.e();
        try {
            if (uUID == null) {
                return em2.func_174791_d();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = em2.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return em2.func_174791_d();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        return entityPlayer.func_174791_d().func_72441_c(0.0, (double)entityPlayer.func_70047_e(), 0.0).func_178787_e(e3.a(new Vec3d((double)0.4f, 0.0, 0.0), e3.d(em2), e3.c(em2)));
    }

    public static float c(em em2) {
        ai ai2 = (ai)((Object)em2);
        UUID uUID = ai2.e();
        try {
            if (uUID == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = em2.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        return entityPlayer.field_70759_as;
    }

    public static float d(em em2) {
        ai ai2 = (ai)((Object)em2);
        UUID uUID = ai2.e();
        try {
            if (uUID == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = em2.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        return entityPlayer.field_70125_A;
    }

    void J() {
        boolean bl2;
        try {
            if (!this.field_70122_E) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.y() != fp.RUN) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 100.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        double d10 = 20.0;
        do {
            int n2;
            Vec3d vec3d;
            Vec3d vec3d2;
            Vec3d vec3d3;
            if (d10 <= 0.0) {
                return;
            }
            Vec3d vec3d4 = this.func_174791_d().func_178788_d(entityPlayer.func_174791_d());
            Vec3d vec3d5 = new Vec3d(Math.abs(vec3d4.field_72450_a), Math.abs(vec3d4.field_72448_b), Math.abs(vec3d4.field_72449_c));
            double d11 = vec3d5.field_72450_a / (vec3d5.field_72450_a + vec3d5.field_72449_c);
            double d12 = vec3d5.field_72449_c / (vec3d5.field_72450_a + vec3d5.field_72449_c);
            try {
                Vec3d vec3d6;
                vec3d3 = this.func_174791_d();
                vec3d2 = vec3d6;
                vec3d = vec3d6;
                n2 = vec3d4.field_72450_a > 0.0 ? 1 : -1;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            vec3d2((double)n2 * d11 * d10, 0.0, (double)(vec3d4.field_72449_c > 0.0 ? 1 : -1) * d12 * d10);
            Vec3d vec3d7 = vec3d3.func_178787_e(vec3d);
            PathNavigate pathNavigate = this.func_70661_as();
            pathNavigate.func_75499_g();
            bl2 = pathNavigate.func_75492_a(vec3d7.field_72450_a, vec3d7.field_72448_b, vec3d7.field_72449_c, (double)0.825f);
            d10 -= 1.0;
        } while (!bl2);
    }

    protected void func_70664_aZ() {
        block4: {
            try {
                try {
                    if (this.y() != fp.RUN || this.j()) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        super.func_70664_aZ();
    }

    boolean j() {
        boolean bl2;
        int n2;
        Path path;
        block9: {
            PathNavigate pathNavigate = this.func_70661_as();
            path = pathNavigate.func_75505_d();
            try {
                if (path == null) {
                    return true;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            n2 = path.func_75873_e();
            int n3 = path.func_75874_d();
            try {
                try {
                    if (n3 != n2 && n3 - 1 != n2) break block9;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                return true;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        PathPoint pathPoint = path.func_75877_a(n2);
        PathPoint pathPoint2 = path.func_75877_a(n2 + 1);
        try {
            bl2 = pathPoint2.field_75837_b - pathPoint.field_75837_b == 1;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        return bl2;
    }

    void B() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (((Boolean)this.m.func_187225_a(aC)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (((Boolean)this.m.func_187225_a(aV)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.y() != fp.SIT) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (++this.aO < 32000) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 3000.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (!entityPlayer.field_70122_E) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (entityPlayer.field_70160_al) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        Integer n2 = this.c(entityPlayer);
        try {
            if (n2 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        Vec3d vec3d = entityPlayer.func_174791_d();
        Vec3d vec3d2 = this.func_174791_d();
        Vec3d vec3d3 = vec3d.func_178788_d(vec3d2);
        double d10 = Math.sqrt(vec3d3.field_72450_a * vec3d3.field_72450_a + vec3d3.field_72449_c * vec3d3.field_72449_c);
        try {
            if (d10 > 100.0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        ItemStack itemStack = entityPlayer.field_71071_by.func_70301_a(n2.intValue()).func_77946_l();
        e3 e32 = new e3(this.field_70170_p, this.f().toString(), this.k());
        Vec3d vec3d4 = e3.b(new Vec3d(0.0, 0.0, (double)-0.2f), entityPlayer.field_70759_as);
        e32.func_70107_b(entityPlayer.field_70165_t + vec3d4.field_72450_a, entityPlayer.field_70163_u, entityPlayer.field_70161_v + vec3d4.field_72449_c);
        e32.b(fp.RUN);
        this.field_70170_p.func_72838_d((Entity)e32);
        e32.m.func_187227_b(a0, (Object)itemStack);
        entityPlayer.func_145747_a((ITextComponent)new TextComponentString(String.format("<%s> I got your %s hehe~", e32.c(), itemStack.func_82833_r())));
        entityPlayer.field_71071_by.func_70304_b(n2.intValue());
        this.aO = 0;
    }

    int k() {
        return Integer.parseInt(e3.a(this)[7]);
    }

    @Nullable
    Integer c(EntityPlayer entityPlayer) {
        NonNullList nonNullList = entityPlayer.field_71071_by.field_70462_a;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i2 = 0; i2 < nonNullList.size(); ++i2) {
            ItemStack itemStack = (ItemStack)nonNullList.get(i2);
            try {
                if (itemStack == ItemStack.field_190927_a) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                if (!ag.contains(itemStack.func_77973_b())) continue;
                arrayList.add(i2);
                continue;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        try {
            if (arrayList.isEmpty()) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        return (Integer)arrayList.get(this.func_70681_au().nextInt(arrayList.size()));
    }

    void m() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.ae() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.c(this.al);
        this.b(this.ac);
        this.a(true);
        this.func_189654_d(true);
        this.b(fp.SIT);
    }

    @Override
    public void func_70071_h_() {
        block7: {
            try {
                this.i();
                e3.e(this);
                this.e();
                if (this.e() != null) {
                    this.field_71087_bX = false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                try {
                    super.func_70071_h_();
                    this.y();
                    this.H();
                    this.F();
                    if (!this.field_70170_p.field_72995_K) break block7;
                    this.v();
                    this.A();
                    if (this.e() == null) break block7;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                this.field_70145_X = true;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
    }

    @Override
    public fp b() {
        return this.aN;
    }

    @Override
    public void a(fp fp2) {
        this.aN = fp2;
    }

    @Override
    public void c(int n2) {
        this.aR = n2;
    }

    @Override
    public int a() {
        return this.aR;
    }

    public static void e(em em2) {
        ai ai2;
        fp fp2;
        block4: {
            fp2 = em2.y();
            ai2 = (ai)((Object)em2);
            try {
                try {
                    if (ai2.b() == fp.START_THROWING || fp2 != fp.START_THROWING) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                ai2.c(0);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        ai2.a(fp2);
    }

    public void func_70015_d(int n2) {
        try {
            if (this.e() == null) {
                super.func_70015_d(n2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
    }

    void F() {
        try {
            if (this.y() != fp.VANISH) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            this.ar -= 0.05f;
            if (this.ar > 0.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.field_70170_p.func_72900_e((Entity)this);
    }

    void H() {
        block16: {
            try {
                if (((Boolean)this.m.func_187225_a(aC)).booleanValue()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                if (this.y() != fp.THROWN) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                try {
                    if (this.field_70122_E || this.func_70090_H()) break block16;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        try {
            this.ar = (float)((double)this.ar - 0.05);
            if (this.ar > 0.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.b(fp.NULL);
        this.e((UUID)null);
        this.a((UUID)null);
        this.field_70170_p.func_72900_e((Entity)this);
    }

    @SideOnly(value=Side.CLIENT)
    void v() {
        try {
            if (this.aY == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (++this.aY != 15) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.aY = -1;
        this.b(fp.PAIZURI_START);
        Minecraft.func_71410_x().field_71439_g.func_71053_j();
    }

    @SideOnly(value=Side.CLIENT)
    void A() {
        try {
            if (this.az == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            if (++this.az != 15) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.az = -1;
        this.b(fp.NELSON_INTRO);
        Minecraft minecraft = Minecraft.func_71410_x();
        minecraft.field_71439_g.func_71053_j();
        minecraft.field_71474_y.field_74320_O = 2;
    }

    @Override
    public void b(fp fp2) {
        block59: {
            fp fp3;
            block58: {
                block57: {
                    block56: {
                        block55: {
                            block53: {
                                block51: {
                                    block49: {
                                        fp3 = this.y();
                                        try {
                                            block50: {
                                                try {
                                                    try {
                                                        if (fp3 != fp.PAIZURI_CUM) break block49;
                                                        if (fp2 == fp.PAIZURI_SLOW) break block50;
                                                    }
                                                    catch (ConcurrentModificationException concurrentModificationException) {
                                                        throw e3.a(concurrentModificationException);
                                                    }
                                                    if (fp2 != fp.PAIZURI_FAST) break block49;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw e3.a(concurrentModificationException);
                                                }
                                            }
                                            return;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw e3.a(concurrentModificationException);
                                        }
                                    }
                                    try {
                                        block52: {
                                            try {
                                                try {
                                                    if (fp3 != fp.NELSON_CUM) break block51;
                                                    if (fp2 == fp.NELSON_SLOW) break block52;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw e3.a(concurrentModificationException);
                                                }
                                                if (fp2 != fp.NELSON_FAST) break block51;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw e3.a(concurrentModificationException);
                                            }
                                        }
                                        return;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw e3.a(concurrentModificationException);
                                    }
                                }
                                try {
                                    block54: {
                                        try {
                                            try {
                                                if (fp3 != fp.BREEDING_CUM_0) break block53;
                                                if (fp2 == fp.BREEDING_SLOW_0) break block54;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw e3.a(concurrentModificationException);
                                            }
                                            if (fp2 != fp.BREEDING_FAST_0) break block53;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw e3.a(concurrentModificationException);
                                        }
                                    }
                                    return;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw e3.a(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    if (fp2 != fp.START_THROWING || this.field_70170_p.field_72995_K) break block55;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw e3.a(concurrentModificationException);
                                }
                                this.e(this.e());
                                this.L();
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw e3.a(concurrentModificationException);
                            }
                        }
                        try {
                            try {
                                if (fp2 != fp.PAIZURI_START || this.field_70170_p.field_72995_K) break block56;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw e3.a(concurrentModificationException);
                            }
                            this.z();
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw e3.a(concurrentModificationException);
                        }
                    }
                    try {
                        try {
                            if (fp2 != fp.NELSON_INTRO || this.field_70170_p.field_72995_K) break block57;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw e3.a(concurrentModificationException);
                        }
                        this.q();
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw e3.a(concurrentModificationException);
                    }
                }
                try {
                    try {
                        try {
                            if (this.y() != fp.PAIZURI_CUM || fp2 != fp.NULL) break block58;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw e3.a(concurrentModificationException);
                        }
                        if (this.field_70170_p.field_72995_K) break block58;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw e3.a(concurrentModificationException);
                    }
                    this.D();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
            }
            try {
                if (fp2 == fp.BREEDING_CUM_0) {
                    this.m.func_187227_b(aV, (Object)true);
                    this.av = this.field_70170_p.func_82737_E();
                    this.ai = this.field_70170_p.func_82737_E();
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                if (fp2 == fp.BREEDING_CUM_0) {
                    this.Z = 0;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                if (fp2 == fp.NELSON_CUM) {
                    this.m.func_187227_b(aV, (Object)true);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                try {
                    if (fp3 != fp.NELSON_CUM || fp2 == fp.NELSON_CUM) break block59;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                this.m.func_187227_b(aV, (Object)false);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        super.b(fp2);
    }

    void D() {
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        try {
            if (entityPlayer != null) {
                s.a.a((EntityPlayerMP)entityPlayer);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            this.e((UUID)null);
            this.a(false);
            this.field_70145_X = false;
            this.func_189654_d(false);
            this.m.func_187227_b(a0, (Object)ItemStack.field_190927_a);
            if (!((Boolean)this.m.func_187225_a(aC)).booleanValue()) {
                this.func_70634_a(this.l.field_72450_a, this.l.field_72448_b, this.l.field_72449_c);
                this.field_70170_p.func_72900_e((Entity)this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
    }

    void q() {
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.a((UUID)null);
        this.c(entityPlayer.func_174791_d());
        this.b(entityPlayer.field_70177_z);
        this.a(true);
        this.field_70145_X = true;
        this.func_189654_d(true);
        entityPlayer.func_189654_d(true);
        entityPlayer.field_70145_X = true;
        this.e(entityPlayer.getPersistentID());
    }

    void z() {
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.a((UUID)null);
        this.c(entityPlayer.func_174791_d());
        this.b(entityPlayer.field_70177_z + 180.0f);
        this.a(true);
        this.field_70145_X = true;
        this.func_189654_d(true);
        entityPlayer.func_189654_d(true);
        entityPlayer.field_70145_X = true;
        this.e(entityPlayer.getPersistentID());
        entityPlayer.func_70634_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u - 0.5, entityPlayer.field_70161_v);
        entityPlayer.field_70125_A = 70.0f;
        entityPlayer.field_70127_C = 70.0f;
    }

    void L() {
        ItemStack itemStack = (ItemStack)this.m.func_187225_a(a0);
        try {
            if (itemStack == ItemStack.field_190927_a) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.ae());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        entityPlayer.field_71071_by.func_70441_a(itemStack.func_77946_l());
        this.m.func_187227_b(a0, (Object)ItemStack.field_190927_a);
    }

    public static void a(em em2) {
        try {
            if (em2.y() != fp.PICK_UP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        ai ai2 = (ai)((Object)em2);
        UUID uUID = ai2.e();
        try {
            if (uUID == null) {
                ai2.b(-1);
                em2.b(fp.NULL);
                ai2.a((UUID)null);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = em2.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                ai2.b(-1);
                em2.b(fp.NULL);
                ai2.a((UUID)null);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        try {
            em2.func_70107_b(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v);
            if (em2.func_174791_d().func_72438_d(entityPlayer.func_174791_d()) > 10.0) {
                ai2.b(-1);
                em2.b(fp.NULL);
                ai2.a((UUID)null);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        int n2 = ai2.c() - 1;
        try {
            ai2.b(n2);
            if (n2 != 0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        em2.b(fp.SHOULDER_IDLE);
        em2.field_70145_X = true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean t() {
        boolean bl2;
        block12: {
            try {
                if (this.y() != fp.NULL) {
                    return false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                if (this.e() != null) {
                    return false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
            try {
                try {
                    if (((Boolean)this.m.func_187225_a(aC)).booleanValue() || Minecraft.func_71410_x().field_71439_g.func_70685_l((Entity)this)) break block12;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                return false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        try {
            bl2 = this.e() == null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        return bl2;
    }

    void y() {
        try {
            if (this.y() != fp.SHOULDER_IDLE) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        this.func_70107_b(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v);
        this.field_70145_X = true;
        this.func_189654_d(true);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected fp c(fp var1_1) {
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

    /*
     * Exception decompiling
     */
    @Override
    protected fp a(fp var1_1) {
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

    public boolean C() {
        boolean bl2;
        Block block = this.field_70170_p.func_180495_p(this.func_180425_c().func_177982_a(0, 1, 0)).func_177230_c();
        try {
            bl2 = !block.func_176205_b((IBlockAccess)this.field_70170_p, this.func_180425_c().func_177982_a(0, 1, 0));
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
        }
        return bl2;
    }

    public void func_180430_e(float f10, float f11) {
        block4: {
            fp fp2 = this.y();
            try {
                try {
                    if (fp2 != fp.THROWN && fp2 != fp.START_THROWING) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw e3.a(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw e3.a(concurrentModificationException);
            }
        }
        super.func_180430_e(f10, f11);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 19[SWITCH]
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
        catch (ConcurrentModificationException concurrentModificationException) {
            throw e3.a(concurrentModificationException);
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
        this.C.registerSoundListener(iSoundListener);
        this.E.transitionLengthTicks = 10.0;
        animationData.addAnimationController(this.C);
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static class c {
        static Minecraft a = null;

        /*
         * Exception decompiling
         */
        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(TickEvent.ClientTickEvent var1_1) {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        }

        @SubscribeEvent
        public void a(PlayerEvent.PlayerChangedDimensionEvent playerChangedDimensionEvent) {
            EntityPlayer entityPlayer = playerChangedDimensionEvent.player;
            UUID uUID = entityPlayer.getPersistentID();
            int n2 = playerChangedDimensionEvent.toDim;
            World world = entityPlayer.field_70170_p;
            e3 e32 = null;
            try {
                for (em em2 : em.ad()) {
                    try {
                        if (em2.field_70170_p.field_72995_K) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
                    }
                    try {
                        if (!(em2 instanceof e3)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
                    }
                    e3 e33 = (e3)em2;
                    try {
                        if (!uUID.equals(e33.e())) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
                    }
                    String string = e33.C();
                    String string2 = e33.F();
                    e32 = e33;
                    e32.a((UUID)null);
                    e32.e((UUID)null);
                    e32.b(fp.NULL);
                    e3 e34 = new e3(world);
                    e34.field_71093_bK = n2;
                    e34.field_98038_p = true;
                    e34.f(string);
                    e34.e(string2);
                    e34.m.func_187227_b(aC, (Object)true);
                    world.func_72838_d((Entity)e34);
                    e34.func_70634_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v);
                    e34.a(uUID);
                    e34.b(fp.SHOULDER_IDLE);
                    break;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
            try {
                if (e32 == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
            world.func_72900_e(e32);
            em.ad().remove(e32);
        }

        @SubscribeEvent
        public void a(LivingAttackEvent livingAttackEvent) {
            try {
                if (livingAttackEvent.getSource() == DamageSource.field_76380_i) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
            EntityLivingBase entityLivingBase = livingAttackEvent.getEntityLiving();
            try {
                if (!(entityLivingBase instanceof e3)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
            e3 e32 = (e3)entityLivingBase;
            try {
                if (e32.e() != null) {
                    livingAttackEvent.setCanceled(true);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
        }

        @SubscribeEvent
        @SideOnly(value=Side.CLIENT)
        public void a(InputEvent.KeyInputEvent keyInputEvent) {
            try {
                if (a == null) {
                    a = Minecraft.func_71410_x();
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
            try {
                if (com.trolmastercard.sexmod.e3$c.a.field_71462_r instanceof ea) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
            try {
                if (!ClientProxy.keyBindings[0].func_151468_f()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
            em em2 = null;
            UUID uUID = Minecraft.func_71410_x().field_71439_g.getPersistentID();
            try {
                for (em em3 : em.ad()) {
                    if (!em3.field_70170_p.field_72995_K) continue;
                    try {
                        if (!(em3 instanceof ai)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
                    }
                    ai ai2 = (ai)((Object)em3);
                    if (!uUID.equals(ai2.e())) continue;
                    em2 = em3;
                    break;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
            try {
                if (em2 == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
            try {
                if (em2.y() != fp.SHOULDER_IDLE) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw com.trolmastercard.sexmod.e3$c.a(concurrentModificationException);
            }
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new ea(em2));
        }

        private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
            return concurrentModificationException;
        }
    }
}

