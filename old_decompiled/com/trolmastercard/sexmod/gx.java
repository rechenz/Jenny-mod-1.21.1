/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector3f
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.Vec3d
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.c3;
import com.trolmastercard.sexmod.cj;
import com.trolmastercard.sexmod.ck;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.vecmath.Vector3f;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;

public class gx {
    public static final Vec3d c = new Vec3d(0.95, 0.65, 0.85);
    public static final Vec3d e = new Vec3d(0.0, 0.2, 0.3);
    public static final float f = 0.1f;
    public static final HashSet<String> a = new HashSet<String>(){
        {
            this.add("boobs");
            this.add("booty");
            this.add("vagina");
            this.add("fuckhole");
        }
    };
    protected static HashMap<c3, HashMap<String, Boolean>> d = new HashMap();
    public static Vec3d b;

    static boolean a(c3 c32, GeoBone geoBone) {
        HashMap<String, Boolean> hashMap = d.get(c32);
        if (hashMap == null) {
            hashMap = new HashMap();
            boolean bl2 = c32.a(c32.a(), geoBone);
            hashMap.put(geoBone.getName(), bl2);
            d.put(c32, hashMap);
            return bl2;
        }
        Boolean bl3 = hashMap.get(geoBone.getName());
        if (bl3 == null) {
            bl3 = c32.a(c32.a(), geoBone);
            hashMap.put(geoBone.getName(), bl3);
            d.put(c32, hashMap);
            return bl3;
        }
        return bl3;
    }

    public static Vec3d a(c3 c32, GeoBone geoBone, Vec3d vec3d, Vector3f vector3f) {
        try {
            if (!gx.a(c32, geoBone)) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gx.a(runtimeException);
        }
        return gx.a(vec3d, vector3f, b);
    }

    public static Vec3d a(Vec3d vec3d, Vector3f vector3f, Vec3d vec3d2) {
        Vec3d vec3d3;
        Vec3d vec3d4;
        double d10 = ck.a(vector3f, vec3d2);
        double d11 = b6.e(Math.abs(d10));
        d11 *= (double)0.1f;
        try {
            vec3d4 = vec3d;
            vec3d3 = d10 > 0.0 ? c : e;
        }
        catch (RuntimeException runtimeException) {
            throw gx.a(runtimeException);
        }
        return b6.a(vec3d4, vec3d3, d11);
    }

    public static void a(EntityLivingBase entityLivingBase, float f10) {
        b = cj.a(entityLivingBase, f10);
    }

    public static void a(List<IBone> list, HashSet<String> hashSet, c3 c32) {
        try {
            if (d.get(c32) != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw gx.a(runtimeException);
        }
        HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        for (IBone iBone : list) {
            hashMap.put(iBone.getName(), c32.a(hashSet, (GeoBone)iBone));
        }
        d.put(c32, hashMap);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

