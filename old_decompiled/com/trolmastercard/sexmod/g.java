/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityTippedArrow
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingHealEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.google.common.collect.Multimap;
import com.trolmastercard.sexmod.ck;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.f;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.r;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class g
extends f {
    e2 q;
    EntityLivingBase r;
    Entity o;
    double l = 3.4028234663852886E38;
    Vec3d i = Vec3d.field_186680_a;
    int j = 0;
    int n = 0;
    int k = 0;
    int p = 0;
    int m = 0;

    public g(e2 e22) {
        super(e22);
        this.q = e22;
    }

    @Override
    public void func_75246_d() {
        try {
            super.func_75246_d();
            this.l = this.q.func_70032_d((Entity)this.a);
            this.i = this.a.func_174791_d();
            if (this.q.y() == fp.BOW) {
                this.q.b(fp.NULL);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.g.a(runtimeException);
        }
    }

    boolean a(EntityLivingBase entityLivingBase) {
        boolean bl2;
        block19: {
            block18: {
                Vec3d vec3d = this.q.func_174791_d();
                try {
                    try {
                        try {
                            try {
                                try {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    if (entityLivingBase instanceof em || this.n > 0) break block18;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                                                }
                                                if (entityLivingBase == null) break block18;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                                            }
                                            if (entityLivingBase.field_70170_p == null) break block18;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                                        }
                                        if (this.q.equals(entityLivingBase)) break block18;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw com.trolmastercard.sexmod.g.a(runtimeException);
                                    }
                                    if (!entityLivingBase.func_70089_S()) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                                }
                                if (!(vec3d.func_72438_d(this.a.func_174791_d()) < 15.0)) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                            if (!(vec3d.func_72438_d(entityLivingBase.func_174791_d()) < 20.0)) break block18;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                        }
                        if (entityLivingBase.equals((Object)this.a)) break block18;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g.a(runtimeException);
                    }
                    bl2 = true;
                    break block19;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    protected void a(f.a var1_1) {
        switch (com.trolmastercard.sexmod.g$b.a[var1_1.ordinal()]) {
            case 1: {
                this.q.func_70671_ap().func_75651_a((Entity)this.r, 30.0f, 30.0f);
                var2_2 = this.q.func_70032_d((Entity)this.r);
                try {
                    try {
                        this.c.func_75499_g();
                        if (var2_2 < 1.9 && --this.k <= 0) {
                        }
                        ** GOTO lbl17
                    }
                    catch (RuntimeException v0) {
                        throw com.trolmastercard.sexmod.g.a(v0);
                    }
                    this.d();
                    break;
                }
                catch (RuntimeException v1) {
                    throw com.trolmastercard.sexmod.g.a(v1);
                }
lbl17:
                // 1 sources

                try {
                    try {
                        try {
                            try {
                                try {
                                    if (this.q.Q.getStackInSlot(1).func_77973_b() instanceof ItemBow && this.q.func_70635_at().func_75522_a((Entity)this.r)) {
                                    }
                                    ** GOTO lbl53
                                }
                                catch (RuntimeException v2) {
                                    throw com.trolmastercard.sexmod.g.a(v2);
                                }
                                if (++this.p > 0) {
                                }
                                ** GOTO lbl53
                            }
                            catch (RuntimeException v3) {
                                throw com.trolmastercard.sexmod.g.a(v3);
                            }
                            if (var2_2 > 6.0) {
                            }
                            ** GOTO lbl53
                        }
                        catch (RuntimeException v4) {
                            throw com.trolmastercard.sexmod.g.a(v4);
                        }
                        this.e.func_187227_b(e2.M, (Object)2);
                        this.q.b(fp.BOW);
                        if (++this.p >= 32) {
                        }
                        ** GOTO lbl50
                    }
                    catch (RuntimeException v5) {
                        throw com.trolmastercard.sexmod.g.a(v5);
                    }
                    this.p = -20;
                    this.e();
                    this.q.b(fp.NULL);
                }
                catch (RuntimeException v6) {
                    throw com.trolmastercard.sexmod.g.a(v6);
                }
lbl50:
                // 2 sources

                this.l = this.q.func_70032_d((Entity)this.a);
                this.i = this.a.func_174791_d();
                return;
lbl53:
                // 3 sources

                try {
                    if (var2_2 < 2.0) {
                        this.e.func_187227_b(e2.M, (Object)1);
                        this.c.func_75497_a((Entity)this.r, 0.5);
                        this.q.a(em.a.WALK);
                        break;
                    }
                }
                catch (RuntimeException v7) {
                    throw com.trolmastercard.sexmod.g.a(v7);
                }
                this.e.func_187227_b(e2.M, (Object)1);
                this.c.func_75497_a((Entity)this.r, 0.7);
                this.q.a(em.a.RUN);
                break;
            }
            case 2: {
                this.e.func_187227_b(e2.M, (Object)0);
                var2_3 = this.q.func_70032_d((Entity)this.a);
                try {
                    try {
                        if (!((double)this.c.func_111269_d() > var2_3)) ** GOTO lbl86
                        this.c.func_75499_g();
                        if (!this.q.N) {
                        }
                        ** GOTO lbl87
                    }
                    catch (RuntimeException v8) {
                        throw com.trolmastercard.sexmod.g.a(v8);
                    }
                    this.c.func_75497_a((Entity)this.a, 0.5);
                    this.a();
                    ** GOTO lbl87
                }
                catch (RuntimeException v9) {
                    throw com.trolmastercard.sexmod.g.a(v9);
                }
lbl86:
                // 1 sources

                this.c();
lbl87:
                // 3 sources

                this.j = 300;
                this.b();
                break;
            }
            case 3: {
                try {
                    this.e.func_187227_b(e2.M, (Object)0);
                    if (this.q.N) ** GOTO lbl109
                    if (++this.j > 200 + com.trolmastercard.sexmod.r.f.nextInt(100)) {
                    }
                    ** GOTO lbl106
                }
                catch (RuntimeException v10) {
                    throw com.trolmastercard.sexmod.g.a(v10);
                }
                this.j = 0;
                var4_4 = this.a.func_174791_d();
                var5_6 = new Vec3d(var4_4.field_72450_a + 1.0 + (double)(com.trolmastercard.sexmod.r.f.nextFloat() * 3.0f), var4_4.field_72448_b, var4_4.field_72449_c + 1.0 + (double)(com.trolmastercard.sexmod.r.f.nextFloat() * 3.0f));
                this.c.func_75499_g();
                this.c.func_75492_a(var5_6.field_72450_a, var5_6.field_72448_b, var5_6.field_72449_c, 0.5);
lbl106:
                // 2 sources

                this.b();
                break;
lbl109:
                // 1 sources

                try {
                    if (!(this.q.func_70032_d((Entity)this.a) > 10.0f)) break;
                    this.c();
                    break;
                }
                catch (RuntimeException v11) {
                    throw com.trolmastercard.sexmod.g.a(v11);
                }
            }
            case 4: {
                try {
                    if (this.q.func_184218_aH()) {
                        this.q.b(fp.SIT);
                        break;
                    }
                }
                catch (RuntimeException v12) {
                    throw com.trolmastercard.sexmod.g.a(v12);
                }
                this.q.func_189654_d(true);
                this.q.field_70145_X = true;
                var4_5 = this.a.func_174791_d().func_178786_a(this.o.func_70040_Z().field_72450_a * 0.5, 0.0, this.o.func_70040_Z().field_72449_c * 0.5);
                this.q.func_70080_a(var4_5.field_72450_a, var4_5.field_72448_b, var4_5.field_72449_c, 0.0f, 0.0f);
                this.q.field_70159_w = 0.0;
                this.q.field_70181_x = 0.0;
                this.q.field_70179_y = 0.0;
                this.q.b(fp.RIDE);
                break;
            }
            case 5: {
                this.c.func_75499_g();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected f.a a() {
        boolean bl2;
        block62: {
            block60: {
                block61: {
                    boolean bl3;
                    DamageSource damageSource;
                    Entity entity;
                    block59: {
                        block57: {
                            try {
                                try {
                                    --this.n;
                                    if (this.q.N) return f.a.DOWNED;
                                    if (this.q.ae() != null) {
                                        return f.a.DOWNED;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                            if (this.a.func_184218_aH()) {
                                entity = this.a.func_184187_bx();
                                try {
                                    block56: {
                                        try {
                                            try {
                                                try {
                                                    if (this.q.func_184218_aH() || this.q.func_184220_m(entity)) break block56;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                                                }
                                                if (!(entity instanceof EntityHorse)) break block57;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                                            }
                                            if (!((EntityHorse)entity).func_110257_ck()) break block57;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                                        }
                                    }
                                    this.o = entity;
                                    return f.a.RIDE;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                                }
                            }
                            try {
                                block58: {
                                    try {
                                        try {
                                            try {
                                                if (!this.a.func_184218_aH() && this.q.func_184218_aH()) break block58;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                                            }
                                            if (this.f != f.a.RIDE) break block57;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                                        }
                                        if (this.a.func_184218_aH()) break block57;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw com.trolmastercard.sexmod.g.a(runtimeException);
                                    }
                                }
                                this.q.b(fp.NULL);
                                this.q.func_184210_p();
                                this.q.field_70145_X = false;
                                this.q.func_189654_d(false);
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                        }
                        try {
                            if (this.a(this.r)) {
                                return f.a.ATTACK;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                        }
                        damageSource = this.q.func_189748_bU();
                        if (damageSource != null) {
                            entity = (EntityLivingBase)damageSource.func_76346_g();
                            try {
                                if (this.a((EntityLivingBase)entity)) {
                                    this.r = entity;
                                    return f.a.ATTACK;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                        }
                        entity = this.a.func_110144_aD();
                        try {
                            try {
                                if (this.a.field_70173_aa - this.a.func_142013_aG() >= 140 || !this.a((EntityLivingBase)entity)) break block59;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                            this.r = entity;
                            return f.a.ATTACK;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                        }
                    }
                    if (this.f != f.a.FOLLOW) {
                        damageSource = this.a.func_189748_bU();
                        if (damageSource != null) {
                            entity = (EntityLivingBase)damageSource.func_76346_g();
                            try {
                                if (this.a((EntityLivingBase)entity)) {
                                    this.r = entity;
                                    return f.a.ATTACK;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                        }
                        Vec3d vec3d = this.q.func_174791_d();
                        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(vec3d.field_72450_a - 5.0, vec3d.field_72448_b - 2.0, vec3d.field_72449_c - 5.0, vec3d.field_72450_a + 5.0, vec3d.field_72448_b + 2.0, vec3d.field_72449_c + 5.0);
                        List list = this.q.field_70170_p.func_72872_a(EntityMob.class, axisAlignedBB);
                        list.sort((entityMob, entityMob2) -> {
                            int n2;
                            double d10 = entityMob.func_70032_d((Entity)this.q);
                            double d11 = entityMob2.func_70032_d((Entity)this.q);
                            try {
                                if (d10 == d11) {
                                    return 0;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                            try {
                                n2 = d10 < d11 ? -1 : 1;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                            return n2;
                        });
                        for (EntityMob entityMob3 : list) {
                            try {
                                try {
                                    if (!this.a((EntityLivingBase)entityMob3) || entityMob3 instanceof EntityCreeper) continue;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                                }
                                this.r = entityMob3;
                                return f.a.ATTACK;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                        }
                    }
                    float f10 = this.q.func_70032_d((Entity)this.a);
                    try {
                        bl3 = f10 > 5.0f;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g.a(runtimeException);
                    }
                    bl2 = bl3;
                    try {
                        try {
                            if (bl2 || this.f != f.a.FOLLOW) break block60;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                        }
                        if (++this.m <= 60) break block61;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g.a(runtimeException);
                    }
                    bl2 = false;
                    this.m = 0;
                    break block60;
                }
                bl2 = true;
            }
            try {
                try {
                    if (!bl2 || this.f != f.a.ATTACK) break block62;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                }
                this.n = 60;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.g.a(runtimeException);
            }
        }
        try {
            if (!bl2) return f.a.IDLE;
            return f.a.FOLLOW;
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.g.a(runtimeException);
        }
    }

    public void e() {
        EntityArrow entityArrow = this.b();
        double d10 = this.r.field_70165_t - this.q.field_70165_t;
        double d11 = this.r.func_174813_aQ().field_72338_b + (double)(this.r.field_70131_O / 3.0f) - entityArrow.field_70163_u;
        double d12 = this.r.field_70161_v - this.q.field_70161_v;
        double d13 = MathHelper.func_76133_a((double)(d10 * d10 + d12 * d12));
        entityArrow.func_70186_c(d10, d11 + d13 * (double)0.2f, d12, 1.6f, 2.0f);
        this.q.func_184185_a(SoundEvents.field_187866_fi, 1.0f, 1.0f / (this.q.func_70681_au().nextFloat() * 0.4f + 0.8f));
        this.q.field_70170_p.func_72838_d((Entity)entityArrow);
        entityArrow.func_70239_b(4.5);
    }

    protected EntityArrow b() {
        EntityTippedArrow entityTippedArrow = new EntityTippedArrow(this.q.field_70170_p, (EntityLivingBase)this.q);
        ItemStack itemStack = this.q.Q.getStackInSlot(1);
        double d10 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_185309_u, (ItemStack)itemStack);
        int n2 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_185310_v, (ItemStack)itemStack);
        int n3 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_185311_w, (ItemStack)itemStack);
        try {
            if (d10 != 0.0) {
                entityTippedArrow.func_70239_b(entityTippedArrow.func_70242_d() + d10 * 0.5 + 0.5);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.g.a(runtimeException);
        }
        try {
            if (n2 != 0) {
                entityTippedArrow.func_70240_a(n2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.g.a(runtimeException);
        }
        try {
            if (n3 != 0) {
                entityTippedArrow.func_70015_d(100);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.g.a(runtimeException);
        }
        return entityTippedArrow;
    }

    void d() {
        this.q.b(fp.ATTACK);
        this.e.func_187227_b(e2.M, (Object)1);
        ItemStack itemStack = this.q.Q.getStackInSlot(0);
        Multimap multimap = itemStack.func_111283_C(EntityEquipmentSlot.MAINHAND);
        float f10 = 0.0f;
        float f11 = 0.0f;
        for (AttributeModifier attributeModifier : multimap.get((Object)SharedMonsterAttributes.field_111264_e.func_111108_a())) {
            f10 = (float)attributeModifier.func_111164_d();
        }
        for (AttributeModifier attributeModifier : multimap.get((Object)SharedMonsterAttributes.field_188790_f.func_111108_a())) {
            f11 = (float)attributeModifier.func_111164_d();
        }
        f11 = Math.max(f11, 0.5f);
        float f12 = EnchantmentHelper.func_152377_a((ItemStack)itemStack, (EnumCreatureAttribute)this.r.func_70668_bt());
        int n2 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_180313_o, (ItemStack)itemStack);
        int n3 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_77334_n, (ItemStack)itemStack);
        int n4 = EnchantmentHelper.func_77506_a((Enchantment)Enchantments.field_191530_r, (ItemStack)itemStack);
        this.r.func_70653_a((Entity)this.q, (float)n2 * 0.5f, (double)MathHelper.func_76126_a((float)(this.q.field_70177_z * ((float)Math.PI / 180))), (double)(-MathHelper.func_76134_b((float)(this.q.field_70177_z * ((float)Math.PI / 180)))));
        this.r.func_70015_d(n3 * 4);
        if (n4 != 0) {
            float f13 = 0.5f;
            if (n4 == 2) {
                f13 = 0.67f;
            } else if (n4 == 3) {
                f13 = 0.75f;
            }
            for (EntityLivingBase entityLivingBase : this.q.field_70170_p.func_72872_a(EntityLivingBase.class, this.r.func_174813_aQ().func_72314_b(1.0, 0.25, 1.0))) {
                try {
                    try {
                        try {
                            try {
                                try {
                                    if (entityLivingBase == this.q || entityLivingBase == this.a) continue;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                                }
                                if (entityLivingBase == this.r) continue;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                            if (this.q.func_184191_r((Entity)entityLivingBase)) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                        }
                        if (!(this.q.func_70068_e((Entity)entityLivingBase) < 9.0)) continue;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g.a(runtimeException);
                    }
                    entityLivingBase.func_70653_a((Entity)this.q, 0.4f, (double)MathHelper.func_76126_a((float)(this.q.field_70177_z * ((float)Math.PI / 180))), (double)(-MathHelper.func_76134_b((float)(this.q.field_70177_z * ((float)Math.PI / 180)))));
                    entityLivingBase.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this.q), (f10 + f12) * f13);
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.g.a(runtimeException);
                }
            }
        }
        this.r.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this.q), f10 + f12);
        this.k = Math.round(Math.abs(f11) / 3.373494f * 20.0f);
    }

    @Override
    protected double b() {
        double d10 = super.b();
        if (this.q.N) {
            d10 = 0.0;
        }
        this.c.func_75489_a(d10);
        this.q.a(this.q.q());
        return d10;
    }

    @Override
    public void func_75251_c() {
        super.func_75251_c();
        this.q.func_184212_Q().func_187227_b(e2.M, (Object)0);
    }

    void a() {
        block9: {
            try {
                block8: {
                    try {
                        try {
                            try {
                                if (this.q.field_70122_E || this.q.func_70090_H()) break block8;
                            }
                            catch (RuntimeException runtimeException) {
                                throw com.trolmastercard.sexmod.g.a(runtimeException);
                            }
                            if (this.q.field_70159_w + this.q.field_70179_y != 0.0) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g.a(runtimeException);
                        }
                        if (!(this.q.field_70181_x <= 0.0)) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g.a(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.g.a(runtimeException);
            }
        }
        Vec3d vec3d = new Vec3d(0.0, 0.0, (double)0.1f);
        vec3d = ck.a(vec3d, this.q.field_70177_z);
        this.q.field_70159_w = vec3d.field_72450_a;
        this.q.field_70179_y = vec3d.field_72449_c;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a {
        @SubscribeEvent
        public void a(LivingHurtEvent livingHurtEvent) {
            block8: {
                if (livingHurtEvent.getEntityLiving() instanceof e2) {
                    e2 e22;
                    block7: {
                        e22 = (e2)livingHurtEvent.getEntityLiving();
                        try {
                            if (!e22.N) break block7;
                            livingHurtEvent.setCanceled(true);
                            break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g$a.a(runtimeException);
                        }
                    }
                    try {
                        try {
                            if (!(e22.func_110143_aJ() - livingHurtEvent.getAmount() < 0.0f) || ((String)e22.func_184212_Q().func_187225_a(e2.v)).equals("")) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g$a.a(runtimeException);
                        }
                        e22.N = true;
                        e22.b(fp.DOWNED);
                        livingHurtEvent.setAmount(e22.func_110143_aJ() - 1.0f);
                        e22.func_70661_as().func_75499_g();
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g$a.a(runtimeException);
                    }
                }
            }
        }

        @SubscribeEvent
        public void a(LivingHealEvent livingHealEvent) {
            block5: {
                if (livingHealEvent.getEntityLiving() instanceof e2) {
                    e2 e22 = (e2)livingHealEvent.getEntityLiving();
                    try {
                        try {
                            if (!e22.N || !(e22.func_110143_aJ() + livingHealEvent.getAmount() >= e22.func_110138_aP())) break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.g$a.a(runtimeException);
                        }
                        e22.N = false;
                        e22.b(fp.NULL);
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g$a.a(runtimeException);
                    }
                }
            }
        }

        @SubscribeEvent
        public void a(LivingDeathEvent livingDeathEvent) {
            if (livingDeathEvent.getEntityLiving() instanceof e2) {
                e2 e22 = (e2)livingDeathEvent.getEntityLiving();
                try {
                    if (e22.field_70170_p.field_72995_K) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.g$a.a(runtimeException);
                }
                for (int i2 = 0; i2 < 6; ++i2) {
                    Item item = e22.Q.getStackInSlot(i2).func_77973_b();
                    try {
                        if (item == Items.field_190931_a) continue;
                        e22.func_145779_a(item, 1);
                        continue;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.g$a.a(runtimeException);
                    }
                }
            }
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

