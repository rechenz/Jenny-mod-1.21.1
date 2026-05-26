/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.df;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.r;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class f
extends EntityAIBase {
    public em d;
    public EntityPlayer a;
    public PathNavigate c;
    public EntityDataManager e;
    public a f = com.trolmastercard.sexmod.f$a.IDLE;
    public static final double g = 0.5;
    public static final double h = 0.7;
    public static final int b = 60;

    public f(em em2) {
        this.d = em2;
        this.c = em2.func_70661_as();
        this.e = em2.func_184212_Q();
    }

    protected void c() {
        BlockPos blockPos;
        int n2 = 0;
        do {
            blockPos = this.a.func_180425_c().func_177982_a(r.f.nextInt(10), 0, r.f.nextInt(10));
        } while (++n2 < 20 && !this.d.func_184595_k(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p()));
        try {
            if (n2 >= 20) {
                this.d.func_70107_b(this.a.field_70165_t, this.a.field_70163_u, this.a.field_70161_v);
            }
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.f.b(runtimeException);
        }
        this.d.field_70159_w = 0.0;
        this.d.field_70181_x = 0.0;
        this.d.field_70179_y = 0.0;
    }

    protected double b() {
        em.a a10;
        double d10;
        float f10 = this.d.func_70032_d((Entity)this.a);
        if (this.a.func_70051_ag()) {
            d10 = 0.7;
            a10 = em.a.RUN;
        } else {
            d10 = 0.5;
            a10 = em.a.WALK;
        }
        double d11 = Math.floor(f10 / 5.0f) * 0.2;
        d10 += d11;
        if (this.d.func_70090_H()) {
            d10 *= 60.0;
            a10 = em.a.WALK;
        }
        this.c.func_75489_a(d10);
        this.d.a(a10);
        return d10;
    }

    public void func_75251_c() {
        this.c.func_75499_g();
        this.f = com.trolmastercard.sexmod.f$a.IDLE;
        this.d.b(fp.NULL);
        this.e.func_187227_b(em.v, (Object)"");
        this.c = null;
        this.e = null;
        this.a = null;
    }

    public boolean func_75250_a() {
        boolean bl2;
        try {
            bl2 = !((String)this.d.func_184212_Q().func_187225_a(em.v)).equals("");
        }
        catch (RuntimeException runtimeException) {
            throw com.trolmastercard.sexmod.f.b(runtimeException);
        }
        return bl2;
    }

    public boolean func_75253_b() {
        boolean bl2;
        block5: {
            block4: {
                String string = (String)this.e.func_187225_a(em.v);
                try {
                    try {
                        if (string.equals("") || this.d.field_70170_p.func_152378_a(UUID.fromString(string)) == null) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.f.b(runtimeException);
                    }
                    bl2 = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw com.trolmastercard.sexmod.f.b(runtimeException);
                }
            }
            bl2 = false;
        }
        return bl2;
    }

    public void func_75249_e() {
        this.c = this.d.func_70661_as();
        this.e = this.d.func_184212_Q();
        this.a = this.d.field_70170_p.func_152378_a(UUID.fromString((String)this.e.func_187225_a(em.v)));
    }

    public void func_75246_d() {
        block4: {
            boolean bl2;
            block6: {
                block5: {
                    try {
                        try {
                            this.f = this.a();
                            if (this.d.o == null) break block4;
                            df df2 = this.d.o;
                            if (this.f != com.trolmastercard.sexmod.f$a.IDLE) break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw com.trolmastercard.sexmod.f.b(runtimeException);
                        }
                        bl2 = true;
                        break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw com.trolmastercard.sexmod.f.b(runtimeException);
                    }
                }
                bl2 = false;
            }
            df2.a = bl2;
        }
        this.a(this.f);
    }

    protected abstract a a();

    protected abstract void a(a var1);

    @SubscribeEvent
    public void a(LivingDeathEvent livingDeathEvent) {
        if (livingDeathEvent.getEntityLiving() instanceof em) {
            em em2 = (em)livingDeathEvent.getEntityLiving();
            try {
                if (!((String)em2.func_184212_Q().func_187225_a(em.v)).equals("")) {
                    livingDeathEvent.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw com.trolmastercard.sexmod.f.b(runtimeException);
            }
        }
    }

    private static RuntimeException b(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static enum a {
        ATTACK,
        FOLLOW,
        IDLE,
        RIDE,
        DOWNED;

    }
}

