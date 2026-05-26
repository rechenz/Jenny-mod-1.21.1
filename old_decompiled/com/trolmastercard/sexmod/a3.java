/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.f_;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class a3
extends DamageSource {
    f_ a;
    Vec3d b;

    public a3(f_ f_2) {
        super("galath");
        this.a = f_2;
        this.b = f_2.func_174791_d();
    }

    public ITextComponent func_151519_b(EntityLivingBase entityLivingBase) {
        return new TextComponentString(entityLivingBase.func_70005_c_() + " got his cum drained by a Succubus");
    }

    public boolean func_76363_c() {
        return true;
    }

    public boolean func_76357_e() {
        return true;
    }

    @Nullable
    public Entity func_76364_f() {
        return this.a;
    }

    @Nullable
    public Entity func_76346_g() {
        return this.a;
    }

    @Nullable
    public Vec3d func_188404_v() {
        return this.b;
    }
}

