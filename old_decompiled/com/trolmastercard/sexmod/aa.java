/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Predicate;
import com.trolmastercard.sexmod.ff;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class aa
extends EntityAINearestAttackableTarget<ff> {
    private final int a;
    private final boolean b;

    public aa(EntityCreature entityCreature, boolean bl2, boolean bl3) {
        this(entityCreature, bl2, false, bl3);
    }

    public aa(EntityCreature entityCreature, boolean bl2, boolean bl3, boolean bl4) {
        this(entityCreature, 10, bl2, bl3, null, bl4);
    }

    public aa(EntityCreature entityCreature, int n2, boolean bl2, boolean bl3, @Nullable Predicate predicate, boolean bl4) {
        super(entityCreature, ff.class, n2, bl2, bl3, predicate);
        this.a = n2;
        this.b = bl4;
    }

    public boolean func_75250_a() {
        block17: {
            if (this.b) {
                float f10 = this.field_75299_d.func_70013_c();
                try {
                    if (f10 >= 0.5f) {
                        return false;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw aa.a(runtimeException);
                }
            }
            try {
                try {
                    if (this.a <= 0 || this.field_75299_d.func_70681_au().nextInt(this.a) == 0) break block17;
                }
                catch (RuntimeException runtimeException) {
                    throw aa.a(runtimeException);
                }
                return false;
            }
            catch (RuntimeException runtimeException) {
                throw aa.a(runtimeException);
            }
        }
        List list = this.field_75299_d.field_70170_p.func_175647_a(this.field_75307_b, this.func_188511_a(this.func_111175_f()), this.field_82643_g);
        try {
            if (list.isEmpty()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw aa.a(runtimeException);
        }
        ArrayList<ff> arrayList = new ArrayList<ff>();
        for (ff ff2 : list) {
            try {
                if (!ff2.J()) continue;
                arrayList.add(ff2);
            }
            catch (RuntimeException runtimeException) {
                throw aa.a(runtimeException);
            }
        }
        try {
            if (arrayList.isEmpty()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw aa.a(runtimeException);
        }
        arrayList.sort((Comparator<ff>)this.field_75306_g);
        this.field_75309_a = (EntityLivingBase)arrayList.get(0);
        return true;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

