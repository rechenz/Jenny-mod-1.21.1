/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  javax.vecmath.Vector2f
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemElytra
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import com.trolmastercard.sexmod.at;
import com.trolmastercard.sexmod.d3;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.fy;
import com.trolmastercard.sexmod.g4;
import com.trolmastercard.sexmod.gd;
import com.trolmastercard.sexmod.ge;
import com.trolmastercard.sexmod.gz;
import com.trolmastercard.sexmod.s;
import com.trolmastercard.sexmod.w;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Vector2f;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class ei
extends e2 {
    public static final String aa = "sexmod:CustomModel";
    public static final String ae = "sexmod:GirlSpecific";
    public static final float ac = 0.0f;
    public static final int am = 100;
    public static final int Y = 65;
    public static boolean ag = true;
    public Vector2f ao = new Vector2f(0.0f, 0.0f);
    public boolean ad = false;
    public boolean aj = false;
    public boolean ak = false;
    public boolean af = true;
    public boolean ah = false;
    protected static final DataParameter<Optional<UUID>> ai = EntityDataManager.func_187226_a(em.class, (DataSerializer)DataSerializers.field_187203_m).func_187156_b().func_187161_a(118);
    public static Hashtable<UUID, ei> al = new Hashtable();
    public static List<ei> Z = new ArrayList<ei>();
    int an = -1;
    public boolean ab = true;

    protected ei(World world) {
        super(world);
        this.func_70105_a(0.01f, 0.01f);
        Z.add(this);
    }

    protected ei(World world, UUID uUID) {
        this(world);
        this.m.func_187227_b(ai, (Object)Optional.of((Object)uUID));
    }

    @Nullable
    public static ei d(UUID uUID) {
        return al.get(uUID);
    }

    @Nullable
    public static ei g(@Nonnull EntityPlayer entityPlayer) {
        return al.get(entityPlayer.getPersistentID());
    }

    @Nullable
    public static ei a(UUID uUID) {
        try {
            for (em em2 : ei.ad()) {
                try {
                    if (em2.field_70170_p.field_72995_K) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ei.a(concurrentModificationException);
                }
                try {
                    if (!(em2 instanceof ei)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ei.a(concurrentModificationException);
                }
                ei ei2 = (ei)em2;
                if (!uUID.equals(ei2.m())) continue;
                return ei2;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return null;
    }

    @Override
    public NetworkRegistry.TargetPoint P() {
        return new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u - 0.0, this.field_70161_v, 50.0);
    }

    public void a(int n2, fp fp2) {
        ge.b.sendToAllTracking((IMessage)new gd(this.m(), n2, fp2), this.P());
    }

    public EntityPlayer c(EntityPlayer entityPlayer) {
        return entityPlayer;
    }

    public boolean z() {
        return true;
    }

    public Vec3d c(Vec3d vec3d, float f10) {
        return vec3d;
    }

    public boolean func_70067_L() {
        return false;
    }

    public boolean v() {
        return true;
    }

    public boolean q() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void H() {
    }

    public boolean p() {
        return true;
    }

    public boolean a(String string) {
        return false;
    }

    public boolean A() {
        return true;
    }

    @Override
    public String c() {
        if (((Optional)this.m.func_187225_a(ai)).isPresent()) {
            EntityPlayer entityPlayer = this.field_70170_p.func_152378_a((UUID)((Optional)this.m.func_187225_a(ai)).get());
            try {
                if (entityPlayer != null) {
                    return entityPlayer.func_70005_c_();
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
        }
        return "anonymous horny girl";
    }

    public void u() {
    }

    public abstract void b(String var1, UUID var2);

    public abstract at a(int var1);

    public abstract String c(int var1);

    public Vec3i b(int n2) {
        return new Vec3i(255, 255, 255);
    }

    @Override
    public boolean func_70104_M() {
        return false;
    }

    public boolean func_70058_J() {
        return true;
    }

    public boolean F() {
        return false;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.m.func_187214_a(ai, (Object)Optional.absent());
    }

    @SideOnly(value=Side.CLIENT)
    public static void i() {
        ei ei2 = ei.d(Minecraft.func_71410_x().field_71439_g.getPersistentID());
        try {
            if (ei2 == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        ei2.r();
    }

    @Override
    public void r() {
        try {
            this.B = null;
            this.func_189654_d(false);
            if (this.field_70170_p.field_72995_K) {
                this.V();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void V() {
        block2: {
            try {
                if (!this.n() && !this.f()) break block2;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
            d3.a(true);
            EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
            entityPlayerSP.func_82142_c(false);
            entityPlayerSP.func_189654_d(false);
            entityPlayerSP.field_70145_X = false;
            this.m.func_187227_b(G, (Object)false);
            ge.b.sendToServer((IMessage)new s(this.f()));
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean H() {
        boolean bl2;
        block5: {
            block4: {
                Minecraft minecraft = Minecraft.func_71410_x();
                try {
                    try {
                        if (this.f() && minecraft.field_71474_y.field_74320_O == 0) break block4;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw ei.a(concurrentModificationException);
                    }
                    bl2 = true;
                    break block5;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ei.a(concurrentModificationException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    protected void c(boolean bl2) {
        try {
            if (!ag) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        try {
            if (this.m() == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.field_70170_p.func_152378_a(this.m());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        try {
            entityPlayer.field_71075_bZ.field_75101_c = bl2;
            if (!bl2) {
                entityPlayer.field_71075_bZ.field_75100_b = false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        entityPlayer.func_71016_p();
    }

    public static boolean e(UUID uUID) {
        ei.C();
        for (Map.Entry<UUID, ei> entry : al.entrySet()) {
            UUID uUID2 = entry.getKey();
            try {
                if (!uUID.equals(uUID2)) continue;
                return true;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
        }
        return false;
    }

    public static boolean e(EntityPlayer entityPlayer) {
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        return ei.e(entityPlayer.getPersistentID());
    }

    public AxisAlignedBB func_174813_aQ() {
        return super.func_174813_aQ().func_72317_d(0.0, 0.5, 0.0);
    }

    protected EntityPlayer j() {
        List list = this.field_70170_p.field_73010_i;
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : list) {
            try {
                if (entityPlayer2.getPersistentID().equals(((Optional)this.m.func_187225_a(ai)).get())) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            double d10 = entityPlayer.func_70092_e(this.w().field_72450_a, this.w().field_72448_b, this.w().field_72449_c);
            double d11 = entityPlayer2.func_70092_e(this.w().field_72450_a, this.w().field_72448_b, this.w().field_72449_c);
            if (!(d11 < d10)) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean e() {
        EntityPlayer entityPlayer = this.j();
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        return entityPlayer.getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID());
    }

    public Vec3d w() {
        return new Vec3d(this.field_70165_t, this.field_70163_u - 0.0, this.field_70161_v);
    }

    protected void b(UUID uUID) {
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.field_70170_p.func_152378_a(uUID);
        EntityPlayerMP entityPlayerMP2 = (EntityPlayerMP)this.field_70170_p.func_152378_a((UUID)((Optional)this.m.func_187225_a(ai)).get());
        ge.b.sendTo((IMessage)new gz(false), entityPlayerMP);
        ge.b.sendTo((IMessage)new gz(false), entityPlayerMP2);
        this.e(uUID);
        this.field_70177_z = 0.0f;
        this.field_70759_as = 0.0f;
        entityPlayerMP.field_70177_z = 180.0f;
        entityPlayerMP.field_70759_as = 180.0f;
        entityPlayerMP.func_189654_d(true);
        entityPlayerMP.field_70145_X = true;
        Vec3d vec3d = this.func_174791_d();
        entityPlayerMP.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c + 1.0);
        entityPlayerMP.field_71075_bZ.field_75100_b = true;
        entityPlayerMP2.field_71075_bZ.field_75100_b = true;
        this.j(uUID);
        this.m.func_187227_b(G, (Object)true);
        this.c(vec3d);
        this.b(0.0f);
    }

    protected void func_180429_a(BlockPos blockPos, Block block) {
        super.func_180429_a(blockPos, block);
    }

    public AxisAlignedBB a(EntityPlayer entityPlayer) {
        return entityPlayer.func_174813_aQ();
    }

    @Override
    public void func_70071_h_() {
        try {
            this.field_70145_X = true;
            this.func_189654_d(true);
            super.func_70071_h_();
            this.D();
            if (!this.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        try {
            if (this.f()) {
                com.trolmastercard.sexmod.w.a.a();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void h() {
        Minecraft.func_71410_x().field_71439_g.eyeHeight = this.func_70047_e();
    }

    @SideOnly(value=Side.CLIENT)
    public boolean f() {
        try {
            if (!((Optional)this.m.func_187225_a(ai)).isPresent()) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        return ((UUID)((Optional)this.m.func_187225_a(ai)).get()).equals(Minecraft.func_71410_x().field_71439_g.getPersistentID());
    }

    public boolean E() {
        return false;
    }

    void d(EntityPlayer entityPlayer) {
        NBTTagCompound nBTTagCompound = entityPlayer.getEntityData();
        String string = nBTTagCompound.func_74779_i(aa + (Object)((Object)fy.a((Entity)this)));
        this.f(string);
    }

    @Override
    public void func_70619_bc() {
        block17: {
            Object object;
            EntityPlayer entityPlayer;
            block16: {
                ei.C();
                this.l();
                this.G();
                UUID uUID = this.m();
                try {
                    if (uUID == null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ei.a(concurrentModificationException);
                }
                entityPlayer = this.field_70170_p.func_152378_a(uUID);
                try {
                    if (entityPlayer == null) {
                        this.func_70634_a(this.field_70165_t, 0.0, this.field_70161_v);
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ei.a(concurrentModificationException);
                }
                this.d(entityPlayer);
                if (this.Q()) {
                    object = this.o();
                    this.func_70634_a(object.field_72450_a, object.field_72448_b, object.field_72449_c);
                } else {
                    this.func_70634_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u + 0.0, entityPlayer.field_70161_v);
                }
                object = this.y();
                try {
                    try {
                        if (object != fp.NULL || !entityPlayer.field_82175_bq) break block16;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw ei.a(concurrentModificationException);
                    }
                    this.b(fp.ATTACK);
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ei.a(concurrentModificationException);
                }
            }
            try {
                try {
                    if (object != fp.ATTACK || entityPlayer.field_82175_bq) break block17;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ei.a(concurrentModificationException);
                }
                this.b(fp.NULL);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
        }
    }

    void D() {
        block18: {
            int n2;
            ei ei2;
            block20: {
                block19: {
                    try {
                        if (this.an == -1) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw ei.a(concurrentModificationException);
                    }
                    try {
                        try {
                            try {
                                ++this.an;
                                if (this.field_70170_p.field_72995_K || this.an != 65) break block18;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw ei.a(concurrentModificationException);
                            }
                            ei2 = this;
                            if (this.ah() != 0) break block19;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw ei.a(concurrentModificationException);
                        }
                        n2 = 1;
                        break block20;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw ei.a(concurrentModificationException);
                    }
                }
                n2 = 0;
            }
            ei2.f(n2);
        }
        try {
            if (this.an < 100) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        try {
            if (this.y() != fp.STRIP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        try {
            if (this.field_70170_p.field_72995_K) {
                this.n();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        this.b(fp.NULL);
    }

    @SideOnly(value=Side.CLIENT)
    void n() {
        if (this.f()) {
            Minecraft minecraft = Minecraft.func_71410_x();
            minecraft.field_71474_y.field_74320_O = 0;
            minecraft.field_71460_t.func_175066_a(minecraft.func_175606_aa());
            d3.a(true);
        }
    }

    public boolean o() {
        return this.Q();
    }

    public Vec3d b(Vec3d vec3d, float f10) {
        return vec3d;
    }

    public boolean a(fp fp2, EntityPlayer entityPlayer) {
        return false;
    }

    public boolean l() {
        return true;
    }

    public void b(EntityPlayer entityPlayer) {
    }

    @Override
    public void b(fp fp2) {
        block11: {
            int n2;
            block13: {
                block12: {
                    block10: {
                        try {
                            try {
                                try {
                                    if (this.field_70170_p.field_72995_K || fp2 != fp.NULL) break block10;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw ei.a(concurrentModificationException);
                                }
                                if (!this.Q()) break block10;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw ei.a(concurrentModificationException);
                            }
                            System.out.println("prevented a potential animation break");
                            return;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw ei.a(concurrentModificationException);
                        }
                    }
                    try {
                        try {
                            if (fp2 != fp.STRIP) break block11;
                            ei ei2 = this;
                            if (!this.field_70170_p.field_72995_K) break block12;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw ei.a(concurrentModificationException);
                        }
                        n2 = 5;
                        break block13;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw ei.a(concurrentModificationException);
                    }
                }
                n2 = 0;
            }
            ei2.an = n2;
        }
        super.b(fp2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void f(EntityPlayer entityPlayer) {
        this.m.func_187227_b(X, (Object)ItemStack.field_190927_a);
        this.m.func_187227_b(T, (Object)ItemStack.field_190927_a);
        this.m.func_187227_b(U, (Object)ItemStack.field_190927_a);
        this.m.func_187227_b(W, (Object)ItemStack.field_190927_a);
        Iterator iterator = entityPlayer.func_184193_aE().iterator();
        block12: while (iterator.hasNext()) {
            ItemStack itemStack = (ItemStack)iterator.next();
            try {
                if (itemStack.func_77973_b() instanceof ItemElytra) {
                    this.m.func_187227_b(T, (Object)itemStack);
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
            try {
                if (!(itemStack.func_77973_b() instanceof ItemArmor)) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
            ItemArmor itemArmor = (ItemArmor)itemStack.func_77973_b();
            try {
                switch (itemArmor.func_185083_B_()) {
                    case HEAD: {
                        this.m.func_187227_b(X, (Object)itemStack);
                        continue block12;
                    }
                    case CHEST: {
                        this.m.func_187227_b(T, (Object)itemStack);
                        continue block12;
                    }
                    case LEGS: {
                        this.m.func_187227_b(U, (Object)itemStack);
                        continue block12;
                    }
                    case FEET: {
                        this.m.func_187227_b(W, (Object)itemStack);
                        continue block12;
                    }
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
        }
        return;
    }

    public UUID m() {
        try {
            if (((Optional)this.m.func_187225_a(ai)).isPresent()) {
                return (UUID)((Optional)this.m.func_187225_a(ai)).get();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        return null;
    }

    @Nullable
    public EntityPlayer k() {
        UUID uUID = this.m();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        return this.field_70170_p.func_152378_a(uUID);
    }

    public void a(Optional<UUID> optional) {
        this.m.func_187227_b(ai, optional);
    }

    public void y() {
    }

    public void B() {
    }

    /*
     * Exception decompiling
     */
    public static void C() {
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
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    static void t() {
        ArrayList<UUID> arrayList = new ArrayList<UUID>();
        for (Map.Entry<UUID, ei> object : al.entrySet()) {
            try {
                if (!object.getValue().field_70128_L) continue;
                arrayList.add(object.getKey());
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ei.a(concurrentModificationException);
            }
        }
        for (UUID uUID : arrayList) {
            al.remove(uUID);
        }
    }

    protected boolean c(UUID uUID) {
        boolean bl2;
        try {
            if (uUID == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        ei ei2 = ei.d(uUID);
        try {
            bl2 = ei2 != null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        return bl2;
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            if (this.a(string)) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        try {
            if (!((Optional)this.m.func_187225_a(ai)).isPresent()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ei.a(concurrentModificationException);
        }
        ge.b.sendToServer((IMessage)new g4(string, uUID, (UUID)((Optional)this.m.func_187225_a(ai)).get(), this.ab));
        this.ab = true;
    }

    @Override
    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        super.func_70014_b(nBTTagCompound);
        nBTTagCompound.func_74778_a("owner", ((UUID)((Optional)this.m.func_187225_a(ai)).get()).toString());
    }

    @Override
    public void func_70037_a(NBTTagCompound nBTTagCompound) {
        super.func_70037_a(nBTTagCompound);
        this.m.func_187227_b(ai, (Object)Optional.of((Object)UUID.fromString(nBTTagCompound.func_74779_i("owner"))));
        Z.add(this);
    }

    @Override
    public void a(SoundEvent soundEvent, float f10, float f11) {
        block3: {
            Vec3d vec3d;
            block2: {
                vec3d = this.w();
                try {
                    if (!this.field_70170_p.field_72995_K) break block2;
                    this.field_70170_p.func_184134_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, soundEvent, SoundCategory.NEUTRAL, f10, f11, false);
                    break block3;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ei.a(concurrentModificationException);
                }
            }
            this.field_70170_p.func_184133_a(null, new BlockPos(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c), soundEvent, SoundCategory.PLAYERS, f10, f11);
        }
    }

    @Override
    public void a(SoundEvent soundEvent) {
        this.a(soundEvent, 1.0f, 1.0f);
    }

    public void a(SoundEvent[] soundEventArray) {
        this.a(soundEventArray[this.func_70681_au().nextInt(soundEventArray.length)], 1.0f, 1.0f);
    }

    @Override
    public void a(SoundEvent soundEvent, float f10) {
        this.a(soundEvent, f10, 1.0f);
    }

    @Override
    protected void U() {
    }

    private static ConcurrentModificationException a(ConcurrentModificationException concurrentModificationException) {
        return concurrentModificationException;
    }
}

