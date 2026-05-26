/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.PotionTypes
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.potion.PotionHelper
 *  net.minecraft.potion.PotionType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.bd;
import com.trolmastercard.sexmod.cw;
import com.trolmastercard.sexmod.ge;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class co
extends Potion {
    public static final Potion b = new co("horny potion", false, 16736968, 0, 0);
    public static final PotionType a = (PotionType)new PotionType("horny_potion", new PotionEffect[]{new PotionEffect(b, 3600), new PotionEffect(MobEffects.field_76431_k, 200, 1)}).setRegistryName("horny_potion");

    public co() {
        super(false, 0);
    }

    public co(String string, boolean bl2, int n2, int n3, int n4) {
        super(bl2, n2);
        this.func_76390_b(string);
        this.func_76399_b(n3, n4);
        this.setRegistryName(new ResourceLocation("sexmod:" + string));
    }

    public static void a() {
        ForgeRegistries.POTIONS.register((IForgeRegistryEntry)b);
        ForgeRegistries.POTION_TYPES.register((IForgeRegistryEntry)a);
        PotionHelper.func_193357_a((PotionType)PotionTypes.field_185231_c, (Item)Item.func_150898_a((Block)Blocks.field_150328_O), (PotionType)a);
    }

    @SubscribeEvent
    public void a(TickEvent.PlayerTickEvent playerTickEvent) {
        EntityPlayer entityPlayer = playerTickEvent.player;
        PotionEffect potionEffect = entityPlayer.func_70660_b(b);
        try {
            if (entityPlayer.field_70170_p.field_72995_K) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw co.a(runtimeException);
        }
        try {
            if (potionEffect == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw co.a(runtimeException);
        }
        try {
            if (potionEffect.func_76459_b() > 3500) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw co.a(runtimeException);
        }
        entityPlayer.func_184589_d(b);
        ge.b.sendTo((IMessage)new bd(entityPlayer), (EntityPlayerMP)entityPlayer);
    }

    @SubscribeEvent
    public void a(LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        block11: {
            EntityVillager entityVillager;
            block12: {
                if (livingUpdateEvent.getEntity() instanceof EntityVillager) {
                    entityVillager = (EntityVillager)livingUpdateEvent.getEntity();
                    try {
                        if (entityVillager.func_70644_a(b)) {
                            entityVillager.field_70714_bg.func_75776_a(2, (EntityAIBase)new cw(entityVillager));
                            entityVillager.func_184589_d(b);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw co.a(runtimeException);
                    }
                }
                try {
                    if (!(livingUpdateEvent.getEntity() instanceof EntityAnimal)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw co.a(runtimeException);
                }
                entityVillager = (EntityAnimal)livingUpdateEvent.getEntity();
                try {
                    try {
                        if (!entityVillager.func_70644_a(b)) break block11;
                        if (entityVillager.func_70874_b() < 0) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw co.a(runtimeException);
                    }
                    entityVillager.func_70873_a(0);
                    entityVillager.func_70875_t();
                    entityVillager.func_146082_f(entityVillager.field_70170_p.func_72890_a((Entity)entityVillager, 30.0));
                }
                catch (RuntimeException runtimeException) {
                    throw co.a(runtimeException);
                }
            }
            entityVillager.func_184589_d(b);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

