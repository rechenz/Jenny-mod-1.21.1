/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.em;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class ak {
    public static Vec3d b(Entity entity, EntityPlayer entityPlayer, float f10) {
        Vec3d vec3d = b6.a(new Vec3d(entity.field_70142_S, entity.field_70137_T + (double)entityPlayer.func_70047_e(), entity.field_70136_U), entity.func_174791_d().func_72441_c(0.0, (double)entityPlayer.func_70047_e(), 0.0), (double)f10);
        Vec3d vec3d2 = b6.a(new Vec3d(entityPlayer.field_70142_S, entityPlayer.field_70137_T, entityPlayer.field_70136_U), entityPlayer.func_174791_d(), (double)f10);
        return vec3d.func_178788_d(vec3d2);
    }

    public static Vec3d a(Entity entity, EntityPlayer entityPlayer, float f10) {
        Vec3d vec3d = ak.a(entity, f10);
        try {
            if (entityPlayer == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ak.a(runtimeException);
        }
        Vec3d vec3d2 = ak.a((Entity)entityPlayer, f10);
        return vec3d.func_178788_d(vec3d2);
    }

    public static Vec3d a(Entity entity, float f10) {
        try {
            if (!(entity instanceof em)) {
                return ak.b(entity, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ak.a(runtimeException);
        }
        em em2 = (em)entity;
        try {
            if (!em2.Q()) {
                return ak.b(entity, f10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ak.a(runtimeException);
        }
        return em2.o();
    }

    static Vec3d b(Entity entity, float f10) {
        return b6.a(new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U), entity.func_174791_d(), (double)f10);
    }

    public static void a() {
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

