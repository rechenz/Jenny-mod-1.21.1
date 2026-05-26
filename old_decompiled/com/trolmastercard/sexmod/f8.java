/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityTippedArrow
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.ProjectileImpactEvent$Arrow
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.Main;
import com.trolmastercard.sexmod.ak;
import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.be;
import com.trolmastercard.sexmod.bj;
import com.trolmastercard.sexmod.bt;
import com.trolmastercard.sexmod.ce;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.cy;
import com.trolmastercard.sexmod.d;
import com.trolmastercard.sexmod.dh;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.fq;
import com.trolmastercard.sexmod.ga;
import com.trolmastercard.sexmod.gc;
import com.trolmastercard.sexmod.v;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class f8
extends em {
    public static final String ac = "sexmod:mommy";
    public static final float am = 60.0f;
    public static final float ag = 4.0f;
    public static final float P = 3.5f;
    public static final float ah = 28.0f;
    public static final float ae = 15.0f;
    public static final float K = 15.0f;
    public static final float L = 0.65f;
    public static final float ao = 3.65f;
    public static final float O = 6.0f;
    public static final float ak = 80.0f;
    public static final float X = 700.0f;
    public static final DataParameter<String> ad = EntityDataManager.func_187226_a(f8.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(111);
    public static final DataParameter<Boolean> ap = EntityDataManager.func_187226_a(f8.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(112);
    public static final DataParameter<Integer> ab = EntityDataManager.func_187226_a(f8.class, (DataSerializer)DataSerializers.field_187192_b).func_187156_b().func_187161_a(113);
    public static final DataParameter<String> al = EntityDataManager.func_187226_a(f8.class, (DataSerializer)DataSerializers.field_187194_d).func_187156_b().func_187161_a(114);
    public static final DataParameter<Boolean> ar = EntityDataManager.func_187226_a(f8.class, (DataSerializer)DataSerializers.field_187198_h).func_187156_b().func_187161_a(115);
    private UUID Q = null;
    public boolean aj = true;
    public Vec3d R = Vec3d.field_186680_a;
    public float V = 0.0f;
    boolean aq = true;
    boolean S = false;
    boolean U = false;
    public float af = 0.0f;
    public float W = 0.0f;
    public float T = 0.0f;
    public float ai = 0.0f;
    boolean aa = false;
    boolean Z = false;
    boolean N = false;
    boolean Y = false;
    boolean M = false;
    public int an = 2;

    public f8(World world) {
        super(world);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.m.func_187214_a(ad, (Object)"");
        this.m.func_187214_a(ap, (Object)false);
        this.m.func_187214_a(ab, (Object)-1);
        this.m.func_187214_a(al, (Object)"");
        this.m.func_187214_a(ar, (Object)false);
    }

    @Override
    public String c() {
        return "Manglelie";
    }

    @Override
    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new bt(this, 20.0f, 1.0, 1.2));
    }

    @Override
    public float i() {
        return 0.0f;
    }

    public void c(boolean bl2) {
        this.m.func_187227_b(ap, (Object)bl2);
    }

    public boolean r() {
        return (Boolean)this.m.func_187225_a(ap);
    }

    @Nullable
    public UUID v() {
        String string = (String)this.m.func_187225_a(ad);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (Exception exception) {
            throw f8.a(exception);
        }
        try {
            return UUID.fromString(string);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean t() {
        boolean bl2;
        try {
            bl2 = !this.r();
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return bl2;
    }

    @Nullable
    public f_ a(boolean bl2) {
        em em2;
        UUID uUID = this.v();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            em2 = bl2 ? em.a(uUID) : em.b(uUID);
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        em em3 = em2;
        try {
            if (!(em3 instanceof f_)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return (f_)em3;
    }

    public void a(UUID uUID) {
        try {
            if (uUID == null) {
                this.m.func_187227_b(ad, (Object)"");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        this.m.func_187227_b(ad, (Object)uUID.toString());
    }

    @Override
    public Float I() {
        float f10 = super.I().floatValue();
        if (ce.c(this)) {
            f10 += 180.0f;
        }
        return Float.valueOf(f10);
    }

    public void q() {
        this.S = true;
    }

    @Override
    public void func_70619_bc() {
        try {
            if (this.aa) {
                this.field_70170_p.func_72900_e((Entity)this);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        this.f();
        this.w();
        super.func_70619_bc();
        this.j();
        this.c();
        this.d();
        this.i();
        this.n();
        this.u();
        this.h();
        this.a();
        this.t();
    }

    void t() {
        try {
            if (this.v() != null) {
                this.aq = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (this.aq) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (this.a(true) == null) {
                System.out.println("removed non-wild mang for lack of mommy");
                this.field_70170_p.func_72900_e((Entity)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
    }

    void a() {
        f_ f_2 = this.a(true);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (f_2.aF() == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (this.f().equals(f_2.aF())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        System.out.println("removed non-wild mang cuz her mommy disowned her and got another mang");
        this.field_70170_p.func_72900_e((Entity)this);
    }

    public static f_ a(em em2, boolean bl2) {
        try {
            if (!(em2 instanceof f8)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return ((f8)em2).a(bl2);
    }

    public long e() {
        String string = (String)this.m.func_187225_a(al);
        try {
            if ("".equals(string)) {
                return -1L;
            }
        }
        catch (Exception exception) {
            throw f8.a(exception);
        }
        try {
            return Long.parseLong(string);
        }
        catch (Exception exception) {
            return -1L;
        }
    }

    public void a(long l2) {
        this.m.func_187227_b(al, (Object)Long.toString(l2));
        this.U = false;
    }

    void h() {
        long l2 = this.e();
        try {
            if (l2 == -1L) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        long l3 = this.field_70170_p.func_82737_E();
        try {
            if ((float)l3 < 28.0f + (float)l2) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (this.U) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        Entity entity = this.b();
        try {
            if (entity == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = this.a(true);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        EntityTippedArrow entityTippedArrow = new EntityTippedArrow(this.field_70170_p, (EntityLivingBase)this);
        Vec3d vec3d = f_2.func_174791_d().func_72441_c(0.0, 3.5, 0.0);
        entityTippedArrow.func_70634_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        Vec3d vec3d2 = entity.func_174791_d();
        Vec3d vec3d3 = vec3d2.func_178788_d(vec3d).func_72432_b();
        entityTippedArrow.field_70159_w = vec3d3.field_72450_a * 4.0;
        entityTippedArrow.field_70181_x = vec3d3.field_72448_b * 4.0;
        entityTippedArrow.field_70179_y = vec3d3.field_72449_c * 4.0;
        em.a((em)f_2, SoundEvents.field_187737_v, true);
        this.field_70170_p.func_72838_d((Entity)entityTippedArrow);
        this.U = true;
    }

    public void func_70690_d(PotionEffect potionEffect) {
    }

    void u() {
        boolean bl2;
        try {
            bl2 = this.v() != null;
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        boolean bl3 = bl2;
        this.func_189654_d(bl3);
        this.field_70145_X = bl3;
    }

    public boolean func_70067_L() {
        boolean bl2;
        try {
            bl2 = this.v() == null;
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return bl2;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public Vec3d a(Minecraft minecraft, cy cy2, EntityLivingBase entityLivingBase, float f10) {
        try {
            if (this.h()) {
                return super.a(minecraft, cy2, entityLivingBase, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (!this.r()) {
                return super.a(minecraft, cy2, entityLivingBase, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = this.a(false);
        try {
            if (f_2 == null) {
                return super.a(minecraft, cy2, entityLivingBase, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        dh.a(f_2, f10, cy2);
        return dh.b(f_2, f10);
    }

    public float b(float f10) {
        long l2 = this.e();
        try {
            if (l2 == -1L) {
                return 0.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        long l3 = this.field_70170_p.func_82737_E();
        float f11 = l3 - l2;
        return (f11 + f10) / 28.0f;
    }

    @Nullable
    public Entity b() {
        int n2 = (Integer)this.m.func_187225_a(ab);
        try {
            if (n2 == -1) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return this.field_70170_p.func_73045_a(n2);
    }

    void a(int n2) {
        long l2;
        f8 f82;
        try {
            this.m.func_187227_b(ab, (Object)n2);
            f82 = this;
            l2 = n2 == -1 ? -1L : this.field_70170_p.func_82737_E();
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f82.a(l2);
    }

    void d() {
        Entity entity = this.b();
        try {
            if (entity == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = this.a(true);
        try {
            if (f_2 == null) {
                this.a(-1);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (!this.r()) {
                this.a(-1);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (f8.a(entity, f_2)) {
                this.a(-1);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
    }

    public static boolean a(Entity entity, f_ f_2) {
        float f10;
        try {
            if (entity.field_70128_L) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (entity.field_71093_bK != f_2.field_71093_bK) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (!com.trolmastercard.sexmod.d.a(entity)) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (!com.trolmastercard.sexmod.d.a(f_2.field_70170_p, f_2.o().func_72441_c(0.0, (double)f_2.func_70047_e(), 0.0), entity)) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        Vec3d vec3d = entity.func_174791_d().func_178788_d(f_2.func_174791_d());
        try {
            if (vec3d.field_72450_a * vec3d.field_72450_a + vec3d.field_72449_c * vec3d.field_72449_c > 225.0) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        Float f11 = f_.a(f_2, 0.0f);
        try {
            f10 = f11 == null ? f_2.field_70759_as : f11.floatValue();
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        float f12 = f10;
        Vec3d vec3d2 = ck.a(vec3d, f12);
        try {
            if (vec3d2.field_72449_c < 0.0) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return false;
    }

    void n() {
        try {
            if (this.b() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (!this.r()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = this.a(true);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (f_2.ae() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (f_2.y() == fp.MASTERBATE) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        BlockPos blockPos = f_2.func_180425_c();
        BlockPos blockPos2 = new BlockPos(15.0, 15.0, 15.0);
        List list = this.field_70170_p.func_72872_a(EntityMob.class, new AxisAlignedBB(blockPos.func_177971_a((Vec3i)blockPos2), blockPos.func_177973_b((Vec3i)blockPos2)));
        for (EntityMob entityMob : list) {
            try {
                if (f8.a((Entity)entityMob, f_2)) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw f8.a(runtimeException);
            }
            this.a(entityMob.func_145782_y());
            return;
        }
    }

    void i() {
        Entity entity = this.b();
        try {
            if (entity == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = this.a(true);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        long l2 = this.e();
        try {
            if (l2 == -1L) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        long l3 = this.field_70170_p.func_82737_E();
        long l4 = l3 - this.e();
        try {
            if ((float)l4 < 60.0f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        this.U = false;
        this.a(-1);
    }

    void j() {
        try {
            if (this.Q == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        em em2 = em.a(this.Q);
        try {
            if (!(em2 instanceof f_)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = (f_)em2;
        try {
            this.a(this.Q);
            f_2.a(this.f());
            this.c(true);
            this.b(fp.RIDE_MOMMY_HEAD);
            this.Q = null;
            if (f_2.y() == fp.HUG_MANG) {
                f_2.a(false);
                f_2.b((fp)null);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
    }

    @Override
    public void b(fp fp2) {
        block9: {
            block8: {
                try {
                    try {
                        if (this.y() != fp.THREESOME_CUM || !fp.a(fp2, fp.THREESOME_FAST, fp.THREESOME_SLOW)) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw f8.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
            }
            try {
                try {
                    if (this.field_70170_p.field_72995_K || fp2 != fp.THREESOME_CUM) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
                com.trolmastercard.sexmod.v.a(this.ae(), this.field_70170_p.func_82737_E());
            }
            catch (RuntimeException runtimeException) {
                throw f8.a(runtimeException);
            }
        }
        super.b(fp2);
    }

    void w() {
        f_ f_2;
        block12: {
            block11: {
                try {
                    try {
                        if (this.r() && !fp.a((em)this, fp.THREESOME_SLOW, fp.THREESOME_CUM, fp.THREESOME_FAST)) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw f8.a(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
            }
            f_2 = this.a(true);
            try {
                if (f_2 == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw f8.a(runtimeException);
            }
            try {
                try {
                    if (!f_2.field_70128_L && this.f().equals(f_2.aF())) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
                Main.LOGGER.warn("A dead mommy has been saved onto a mang. Deleting her and creating a new one");
                this.field_70170_p.func_72900_e((Entity)this);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw f8.a(runtimeException);
            }
        }
        this.b(0.0f);
        this.c(f_2.func_174791_d());
        this.a(true);
    }

    @Override
    public void b(float f10) {
        super.b(f10);
    }

    @Override
    public Vec3d a(Vec3d vec3d, float f10) {
        try {
            if (!this.r()) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (ce.c(this)) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = this.a(false);
        try {
            if (f_2 == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return dh.b(f_2, f10);
    }

    void c() {
        f_ f_22;
        f_ f_3;
        block23: {
            block24: {
                try {
                    if (this.r()) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
                try {
                    if (this.v() != null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
                BlockPos blockPos = this.func_180425_c();
                BlockPos blockPos2 = blockPos.func_177963_a(-15.0, -15.0, -15.0);
                BlockPos blockPos3 = blockPos.func_177963_a(15.0, 15.0, 15.0);
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos2, blockPos3);
                List list = this.field_70170_p.func_72872_a(f_.class, axisAlignedBB);
                f_3 = null;
                for (f_ f_22 : list) {
                    try {
                        if (f_22.field_70128_L) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw f8.a(runtimeException);
                    }
                    try {
                        if (f_22.a(true) != null) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw f8.a(runtimeException);
                    }
                    try {
                        if (!f_22.field_70122_E) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw f8.a(runtimeException);
                    }
                    f_3 = f_22;
                    break;
                }
                try {
                    try {
                        if (f_3 != null) break block23;
                        if (this.y() != fp.RUN) break block24;
                    }
                    catch (RuntimeException runtimeException) {
                        throw f8.a(runtimeException);
                    }
                    this.b((fp)null);
                    this.func_70661_as().func_75499_g();
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
            }
            return;
        }
        try {
            if (this.y() == fp.RIDE_MOMMY_HEAD) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        this.b(fp.RUN);
        Vec3d vec3d = this.func_174791_d();
        f_22 = f_3.func_174791_d();
        Vec3d vec3d2 = f_22.func_178788_d(vec3d);
        float f10 = (float)gc.b(Math.atan2(vec3d2.field_72449_c, vec3d2.field_72450_a)) - 90.0f;
        this.b(f10);
        this.f = this.func_70661_as();
        this.f.func_75499_g();
        this.f.func_75497_a((Entity)f_3, (double)0.65f);
    }

    boolean a(Entity entity, float f10) {
        boolean bl2;
        f8 f82;
        try {
            f82 = this;
            bl2 = f10 == 1.0f;
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = f82.a(bl2);
        try {
            if (f_2 == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        Vec3d vec3d = com.trolmastercard.sexmod.ak.a((Entity)this, f10);
        return this.a(com.trolmastercard.sexmod.ak.a(entity, f10).func_178788_d(vec3d), f_2, f10);
    }

    boolean a(Vec3d vec3d, float f10) {
        boolean bl2;
        f8 f82;
        try {
            f82 = this;
            bl2 = f10 == 1.0f;
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = f82.a(bl2);
        try {
            if (f_2 == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        Vec3d vec3d2 = com.trolmastercard.sexmod.ak.a((Entity)this, f10);
        return this.a(vec3d.func_178788_d(vec3d2), f_2, f10);
    }

    boolean a(Vec3d vec3d, f_ f_2, float f10) {
        boolean bl2;
        Vec3d vec3d2 = ck.a(vec3d, b6.b(f_2.field_70758_at, f_2.field_70759_as, (double)f10));
        try {
            bl2 = vec3d2.field_72450_a > 0.35;
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return bl2;
    }

    @Override
    public void func_70071_h_() {
        try {
            super.func_70071_h_();
            if (this.field_70170_p.field_72995_K) {
                this.m();
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void m() {
        float f10;
        float f11;
        try {
            if ((float)Minecraft.func_71410_x().field_71439_g.field_70173_aa % 7.0f != 0.0f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (!dh.b(this)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = this.a(false);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        Entity entity = this.o();
        try {
            if (entity == null) {
                this.af = 0.0f;
                this.W = 0.0f;
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        Vec3d vec3d = entity.func_174791_d().func_72441_c(0.0, (double)entity.func_70047_e(), 0.0);
        Vec3d vec3d2 = f_2.func_174791_d().func_178787_e(f_2.b("mangPos")).func_178787_e(this.b("head"));
        Vec3d vec3d3 = vec3d2.func_178788_d(vec3d);
        float f12 = (float)(gc.b(Math.atan2(vec3d3.field_72449_c, vec3d3.field_72450_a)) + 90.0);
        Float f13 = f_.a(f_2, 0.0f);
        f12 -= f_2.field_70759_as;
        if (f13 != null) {
            f12 -= f13.floatValue();
        }
        try {
            f8 f82 = this;
            f11 = Math.abs(cj.a(0.0f, f12)) < 80.0f ? -gc.c(f12) : 0.0f;
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            f82.af = f11;
            f8 f83 = this;
            f10 = this.af == 0.0f ? 0.0f : (float)be.b(-vec3d3.field_72448_b / 2.0, -0.75, 0.75);
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f83.W = f10;
    }

    public boolean func_70097_a(DamageSource damageSource, float f10) {
        try {
            if (damageSource == DamageSource.field_76380_i) {
                return super.func_70097_a(damageSource, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_ f_2 = this.a(true);
        try {
            if (f_2 == null) {
                return super.func_70097_a(damageSource, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        f_2.func_70097_a(damageSource, f10);
        return false;
    }

    @Nullable
    Entity o() {
        Entity entity = this.b();
        try {
            if (entity != null) {
                return entity;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        for (EntityPlayer entityPlayer : this.field_70170_p.field_73010_i) {
            float f10 = entityPlayer.func_70032_d((Entity)this);
            try {
                if (f10 > 6.0f) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw f8.a(runtimeException);
            }
            try {
                if (entity != null && !(entity.func_70032_d((Entity)this) > f10)) continue;
            }
            catch (RuntimeException runtimeException) {
                throw f8.a(runtimeException);
            }
            entity = entityPlayer;
        }
        return entity;
    }

    @Override
    public void func_70014_b(NBTTagCompound nBTTagCompound) {
        String string;
        String string2;
        NBTTagCompound nBTTagCompound2;
        super.func_70014_b(nBTTagCompound);
        UUID uUID = this.v();
        try {
            nBTTagCompound2 = nBTTagCompound;
            string2 = ac;
            string = uUID == null ? "" : uUID.toString();
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            nBTTagCompound2.func_74778_a(string2, string);
            nBTTagCompound.func_74757_a("sexmod:iswild", this.aq);
            if (this.S) {
                nBTTagCompound.func_74757_a("sexmod:despawned", true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
    }

    public void func_70020_e(NBTTagCompound nBTTagCompound) {
        super.func_70020_e(nBTTagCompound);
        String string = nBTTagCompound.func_74779_i(ac);
        try {
            if (!"".equals(string)) {
                this.Q = UUID.fromString(string);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        try {
            if (nBTTagCompound.func_74767_n("sexmod:despawned")) {
                this.aa = true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        this.aq = nBTTagCompound.func_74767_n("sexmod:iswild");
    }

    @Override
    protected boolean X() {
        return false;
    }

    @Override
    public void f(String string) {
        super.f(string);
        bj.a(this);
    }

    void f() {
        try {
            if (this.Z) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        this.f(bj.c(this));
        this.Z = true;
    }

    @Override
    @Nullable
    protected fp c(fp fp2) {
        return null;
    }

    @Override
    protected fp a(fp fp2) {
        try {
            if (fp.a(fp2, fp.THREESOME_FAST, fp.THREESOME_SLOW)) {
                this.N = true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        return null;
    }

    @Override
    public void g() {
        try {
            if (this.r()) {
                this.b(fp.RIDE_MOMMY_HEAD);
                this.b(0.0f);
                this.m.func_187217_b(w);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
    }

    public boolean func_70601_bi() {
        try {
            if (!super.func_70601_bi()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f8.a(runtimeException);
        }
        BlockPos blockPos = this.func_180425_c();
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        arrayList.addAll(fq.c);
        arrayList.addAll(fq.b);
        for (BlockPos blockPos2 : arrayList) {
            try {
                if (!(Math.sqrt(blockPos.func_177951_i((Vec3i)blockPos2)) < 700.0)) continue;
                return false;
            }
            catch (RuntimeException runtimeException) {
                throw f8.a(runtimeException);
            }
        }
        fq.a(blockPos, fq.b);
        return true;
    }

    @Override
    protected boolean a(fp fp2, String string, boolean bl2, AnimationEvent animationEvent) {
        block38: {
            block37: {
                block36: {
                    block35: {
                        block34: {
                            if (fp2 == fp.THREESOME_CUM) {
                                this.N = false;
                                this.Y = false;
                                this.M = false;
                                this.an = 2;
                                this.r();
                                f_ f_2 = this.a(false);
                                try {
                                    if (f_2 != null) {
                                        f_2.r();
                                        ga.a(f_2);
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw f8.a(runtimeException);
                                }
                                ga.a(this);
                                return true;
                            }
                            try {
                                if (!this.N || fp2 != fp.THREESOME_FAST) break block34;
                            }
                            catch (RuntimeException runtimeException) {
                                throw f8.a(runtimeException);
                            }
                            this.b(fp.THREESOME_CUM);
                            this.a("animation.shared.double_holding_cum", true, animationEvent, true);
                            f_ f_3 = this.a(false);
                            try {
                                if (f_3 != null) {
                                    f_3.b(fp.MASTERBATE_SITTING_CUM);
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw f8.a(runtimeException);
                            }
                            return true;
                        }
                        try {
                            try {
                                if (!this.N && !bl2) break block35;
                            }
                            catch (RuntimeException runtimeException) {
                                throw f8.a(runtimeException);
                            }
                            if (fp2 != fp.THREESOME_SLOW) break block35;
                        }
                        catch (RuntimeException runtimeException) {
                            throw f8.a(runtimeException);
                        }
                        this.Y = false;
                        this.b(fp.THREESOME_FAST);
                        this.a("animation.shared.double_holding_soft", true, animationEvent, true);
                        f_ f_4 = this.a(false);
                        try {
                            if (f_4 != null) {
                                f_4.ak();
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw f8.a(runtimeException);
                        }
                        return true;
                    }
                    try {
                        if (this.N) {
                            return false;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw f8.a(runtimeException);
                    }
                    try {
                        try {
                            try {
                                if (!bl2 || this.Y) break block36;
                            }
                            catch (RuntimeException runtimeException) {
                                throw f8.a(runtimeException);
                            }
                            if (fp2 != fp.THREESOME_FAST) break block36;
                        }
                        catch (RuntimeException runtimeException) {
                            throw f8.a(runtimeException);
                        }
                        this.Y = true;
                        this.a("animation.shared.double_holding_hard", true, animationEvent, true);
                        return true;
                    }
                    catch (RuntimeException runtimeException) {
                        throw f8.a(runtimeException);
                    }
                }
                try {
                    if (bl2 || fp2 != fp.THREESOME_FAST) break block37;
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
                this.M = true;
                this.b(fp.THREESOME_SLOW);
                this.a("animation.shared.double_holding_back", true, animationEvent, true);
                f_ f_5 = this.a(false);
                try {
                    if (f_5 != null) {
                        f_5.a();
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
                return true;
            }
            try {
                try {
                    if (!this.M || fp2 != fp.THREESOME_SLOW) break block38;
                }
                catch (RuntimeException runtimeException) {
                    throw f8.a(runtimeException);
                }
                this.M = false;
                this.a("animation.shared.double_holding_slow", true, animationEvent, true);
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw f8.a(runtimeException);
            }
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [7[TRYBLOCK]], but top level block is 10[SWITCH]
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
        animationData.addAnimationController(this.E);
        animationData.addAnimationController(this.s);
        this.C.registerSoundListener(var1_1 -> {
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
    }

    private static /* synthetic */ Vec3d lambda$null$1(em em2) {
        return em2.b("semenEmitter").func_178787_e(em2.o());
    }

    private static /* synthetic */ Vec3d lambda$null$0(em em2) {
        Vec3d vec3d = em2.d("semenEmitter");
        Vec3d vec3d2 = em2.d("semenDir");
        return vec3d.func_178788_d(vec3d2).func_72432_b();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static class b {
        @SubscribeEvent
        public void a(ProjectileImpactEvent.Arrow arrow) {
            RayTraceResult rayTraceResult = arrow.getRayTraceResult();
            EntityArrow entityArrow = arrow.getArrow();
            try {
                if (!(entityArrow.field_70250_c instanceof f8)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.f8$b.a(runtimeException);
            }
            try {
                if (rayTraceResult.field_72308_g instanceof em) {
                    arrow.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.f8$b.a(runtimeException);
            }
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

