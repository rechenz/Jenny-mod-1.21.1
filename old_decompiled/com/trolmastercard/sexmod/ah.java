/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.a3;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ah {
    @SubscribeEvent
    public void b(LivingAttackEvent livingAttackEvent) {
        block11: {
            boolean bl2;
            LivingAttackEvent livingAttackEvent2;
            em em2;
            block10: {
                try {
                    if (livingAttackEvent.getSource() == DamageSource.field_76380_i) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ah.a(runtimeException);
                }
                try {
                    if (!(livingAttackEvent.getEntity() instanceof em)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ah.a(runtimeException);
                }
                em2 = (em)livingAttackEvent.getEntity();
                try {
                    if (!(em2 instanceof ei)) break block10;
                    livingAttackEvent.setCanceled(true);
                    break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw ah.a(runtimeException);
                }
            }
            try {
                livingAttackEvent2 = livingAttackEvent;
                bl2 = em2.ae() != null;
            }
            catch (RuntimeException runtimeException) {
                throw ah.a(runtimeException);
            }
            livingAttackEvent2.setCanceled(bl2);
        }
    }

    @SubscribeEvent
    public void a(LivingAttackEvent livingAttackEvent) {
        block13: {
            DamageSource damageSource = livingAttackEvent.getSource();
            try {
                try {
                    if (damageSource != DamageSource.field_76380_i && !(damageSource instanceof a3)) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw ah.a(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ah.a(runtimeException);
            }
        }
        try {
            if (!(livingAttackEvent.getEntity() instanceof EntityPlayer)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ah.a(runtimeException);
        }
        EntityPlayer entityPlayer = (EntityPlayer)livingAttackEvent.getEntity();
        em em2 = em.i(entityPlayer.getPersistentID());
        try {
            if (em2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ah.a(runtimeException);
        }
        try {
            if (em2.func_70032_d((Entity)entityPlayer) < 1.0f) {
                livingAttackEvent.setCanceled(true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ah.a(runtimeException);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

