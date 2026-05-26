/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.e0;
import com.trolmastercard.sexmod.e3;
import com.trolmastercard.sexmod.e5;
import com.trolmastercard.sexmod.e7;
import com.trolmastercard.sexmod.e9;
import com.trolmastercard.sexmod.eb;
import com.trolmastercard.sexmod.ec;
import com.trolmastercard.sexmod.ed;
import com.trolmastercard.sexmod.ee;
import com.trolmastercard.sexmod.eg;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.el;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.eq;
import com.trolmastercard.sexmod.er;
import com.trolmastercard.sexmod.es;
import com.trolmastercard.sexmod.ev;
import com.trolmastercard.sexmod.ex;
import com.trolmastercard.sexmod.f8;
import com.trolmastercard.sexmod.f_;
import com.trolmastercard.sexmod.ff;
import com.trolmastercard.sexmod.fn;
import com.trolmastercard.sexmod.fz;
import com.trolmastercard.sexmod.r;
import net.minecraft.entity.Entity;

public enum fy {
    JENNY(ex.class, 177013, es.class, 12388645),
    ELLIE(el.class, 228922, ee.class, 46348348),
    BIA(e0.class, 230053, eg.class, 65456415),
    SLIME(fn.class, 168597, ec.class, 54816432),
    BEE(fz.class, 4663354, e9.class, 48648638),
    ALLIE(ev.class, 5614613, e5.class, 64867483),
    LUNA(eb.class, 6816463, ed.class, 81234824),
    KOBOLD(ff.class, 5648456, e7.class, 62484851, true),
    GOBLIN(e3.class, 4567275, eq.class, 6584344, true),
    GALATH(f_.class, 314351, er.class, 652535516),
    MANGLELIE(f8.class, 618151);

    public final int npcID;
    public final int playerID;
    public final Class<? extends em> npcClass;
    public final Class<? extends ei> playerClass;
    public final boolean isNpcOnly;
    public final int editorID;
    public final boolean hasSpecifics;

    private fy(Class<? extends em> clazz, int n3, Class<? extends ei> clazz2, int n4, boolean bl2) {
        this.npcID = n3;
        this.playerID = n4;
        this.npcClass = clazz;
        this.playerClass = clazz2;
        this.isNpcOnly = false;
        this.hasSpecifics = bl2;
        this.editorID = r.b++;
    }

    private fy(Class<? extends em> clazz, int n3, Class<? extends ei> clazz2, int n4) {
        this.npcID = n3;
        this.playerID = n4;
        this.npcClass = clazz;
        this.playerClass = clazz2;
        this.isNpcOnly = false;
        this.hasSpecifics = false;
        this.editorID = r.b++;
    }

    private fy(Class<? extends em> clazz, int n3) {
        this.npcID = n3;
        this.npcClass = clazz;
        this.isNpcOnly = true;
        this.hasSpecifics = false;
        this.editorID = r.b++;
        this.playerClass = null;
        this.playerID = 0;
    }

    public static fy a(String string) {
        for (fy fy2 : fy.values()) {
            try {
                if (!fy2.toString().equalsIgnoreCase(string)) continue;
                return fy2;
            }
            catch (RuntimeException runtimeException) {
                throw fy.a(runtimeException);
            }
        }
        return JENNY;
    }

    public static fy a(Entity entity) {
        try {
            if (!(entity instanceof em)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw fy.a(runtimeException);
        }
        em em2 = (em)entity;
        Class<?> clazz = em2.getClass();
        for (fy fy2 : fy.values()) {
            try {
                if (clazz.equals(fy2.npcClass)) {
                    return fy2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw fy.a(runtimeException);
            }
            try {
                if (!clazz.equals(fy2.playerClass)) continue;
                return fy2;
            }
            catch (RuntimeException runtimeException) {
                throw fy.a(runtimeException);
            }
        }
        return null;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

