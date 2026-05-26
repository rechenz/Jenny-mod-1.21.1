/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteractSpecific
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class f4 {
    @SubscribeEvent
    public void a(PlayerInteractEvent.EntityInteractSpecific entityInteractSpecific) {
        ItemStack itemStack;
        Entity entity = entityInteractSpecific.getTarget();
        try {
            if (!(entity instanceof em)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f4.a(runtimeException);
        }
        EntityPlayer entityPlayer = entityInteractSpecific.getEntityPlayer();
        if (entityPlayer.func_184614_ca().func_77973_b() == Items.field_151057_cb) {
            itemStack = entityPlayer.func_184614_ca();
        } else if (entityPlayer.func_184592_cb().func_77973_b() == Items.field_151057_cb) {
            itemStack = entityPlayer.func_184592_cb();
        } else {
            return;
        }
        String string = itemStack.func_82833_r();
        try {
            if ("".equals(string)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw f4.a(runtimeException);
        }
        try {
            ((em)entity).g(string);
            if (!entityPlayer.field_71075_bZ.field_75098_d) {
                itemStack.func_190918_g(1);
            }
        }
        catch (RuntimeException runtimeException) {
            throw f4.a(runtimeException);
        }
        entityInteractSpecific.setCanceled(true);
        entityInteractSpecific.setResult(Event.Result.DENY);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

