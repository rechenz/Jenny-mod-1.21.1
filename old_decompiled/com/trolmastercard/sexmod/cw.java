/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.BabyEntitySpawnEvent
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class cw
extends EntityAIBase {
    private final EntityVillager c;
    private EntityVillager d;
    private final World a;
    private int b;

    public cw(EntityVillager entityVillager) {
        this.c = entityVillager;
        this.a = entityVillager.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        try {
            if (this.b != 0) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cw.a(runtimeException);
        }
        Entity entity = this.a.func_72857_a(EntityVillager.class, this.c.func_174813_aQ().func_72314_b(8.0, 3.0, 8.0), (Entity)this.c);
        try {
            if (entity == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cw.a(runtimeException);
        }
        this.d = (EntityVillager)entity;
        return true;
    }

    public void func_75249_e() {
        this.b = 300;
        this.c.func_70947_e(true);
    }

    public void func_75251_c() {
    }

    public boolean func_75253_b() {
        return true;
    }

    public void func_75246_d() {
        try {
            --this.b;
            this.c.func_70671_ap().func_75651_a((Entity)this.d, 10.0f, 30.0f);
            if (this.c.func_70068_e((Entity)this.d) > 2.25) {
                this.c.func_70661_as().func_75497_a((Entity)this.d, 0.25);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cw.a(runtimeException);
        }
        try {
            if (this.b <= 0) {
                this.a();
                this.c.field_70714_bg.func_85156_a((EntityAIBase)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cw.a(runtimeException);
        }
        try {
            if (this.c.func_70681_au().nextInt(35) == 0) {
                this.a.func_72960_a((Entity)this.c, (byte)12);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cw.a(runtimeException);
        }
    }

    private void a() {
        BabyEntitySpawnEvent babyEntitySpawnEvent;
        EntityVillager entityVillager;
        block4: {
            entityVillager = this.c.func_90011_a((EntityAgeable)this.d);
            this.d.func_70873_a(6000);
            this.c.func_70873_a(6000);
            this.d.func_175549_o(false);
            this.c.func_175549_o(false);
            babyEntitySpawnEvent = new BabyEntitySpawnEvent((EntityLiving)this.c, (EntityLiving)this.d, (EntityAgeable)entityVillager);
            try {
                try {
                    if (!MinecraftForge.EVENT_BUS.post((Event)babyEntitySpawnEvent) && babyEntitySpawnEvent.getChild() != null) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw cw.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw cw.a(runtimeException);
            }
        }
        entityVillager = babyEntitySpawnEvent.getChild();
        entityVillager.func_70873_a(-24000);
        entityVillager.func_70012_b(this.c.field_70165_t, this.c.field_70163_u, this.c.field_70161_v, 0.0f, 0.0f);
        this.a.func_72838_d((Entity)entityVillager);
        this.a.func_72960_a((Entity)entityVillager, (byte)12);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

