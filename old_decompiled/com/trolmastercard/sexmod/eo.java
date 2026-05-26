/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.em;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class eo {
    @SubscribeEvent(priority=EventPriority.LOW)
    public void a(LivingDeathEvent livingDeathEvent) {
        if (livingDeathEvent.getEntity() instanceof em) {
            em em2 = (em)livingDeathEvent.getEntity();
            em.ad().remove(em2);
        }
    }
}

