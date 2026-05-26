/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.cv;
import com.trolmastercard.sexmod.e5;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.gj;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class c2
extends cv {
    @Override
    protected ResourceLocation[] a() {
        return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/allie/allie.geo.json"), new ResourceLocation("sexmod", "geo/allie/armored.geo.json"), new ResourceLocation("sexmod", "geo/allie/allie.geo.json")};
    }

    @Override
    public ResourceLocation a(em em2) {
        try {
            if (em2.field_70170_p instanceof gj) {
                return this.c[0];
            }
        }
        catch (RuntimeException runtimeException) {
            throw c2.a(runtimeException);
        }
        try {
            if ((Integer)em2.func_184212_Q().func_187225_a(em.D) > this.c.length) {
                System.out.println("Girl doesn't have an outfit Nr." + em2.func_184212_Q().func_187225_a(em.D) + " so im just making her nude lol");
                return this.c[0];
            }
        }
        catch (RuntimeException runtimeException) {
            throw c2.a(runtimeException);
        }
        try {
            if (em2 instanceof e5) {
                return this.c[(Integer)em2.func_184212_Q().func_187225_a(em.D)];
            }
        }
        catch (RuntimeException runtimeException) {
            throw c2.a(runtimeException);
        }
        try {
            if ((Integer)em2.func_184212_Q().func_187225_a(em.D) == 1) {
                return this.c[2];
            }
        }
        catch (RuntimeException runtimeException) {
            throw c2.a(runtimeException);
        }
        return this.c[0];
    }

    @Override
    public ResourceLocation b() {
        return new ResourceLocation("sexmod", "textures/entity/allie/allie.png");
    }

    @Override
    public ResourceLocation b(em em2) {
        return new ResourceLocation("sexmod", "animations/allie/allie.animation.json");
    }

    @Override
    public String[] c() {
        return new String[]{"armorHelmet"};
    }

    @Override
    public String[] f() {
        return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
    }

    @Override
    public String[] a() {
        return new String[]{"boobsFlesh", "clothes", "clothesR", "clothesL"};
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

