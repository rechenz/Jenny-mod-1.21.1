/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 */
package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWatchClosest2;

public class df
extends EntityAIWatchClosest2 {
    public boolean a = true;

    public df(EntityLiving entityLiving, Class<? extends Entity> clazz, float f10, float f11) {
        super(entityLiving, clazz, f10, f11);
    }

    public void func_75246_d() {
        try {
            if (this.a) {
                super.func_75246_d();
            }
        }
        catch (RuntimeException runtimeException) {
            throw df.a(runtimeException);
        }
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

