/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Vector3f
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.cv;
import com.trolmastercard.sexmod.ec;
import com.trolmastercard.sexmod.ei;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.gj;
import java.util.Arrays;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class cr
extends cv {
    fp[] f = new fp[]{fp.STARTDOGGY, fp.DOGGYCUM, fp.DOGGYSLOW, fp.DOGGYFAST, fp.DOGGYCUM, fp.DOGGYSTART, fp.WAITDOGGY};

    @Override
    protected ResourceLocation[] a() {
        return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/slime/nude.geo.json"), new ResourceLocation("sexmod", "geo/slime/armored.geo.json"), new ResourceLocation("sexmod", "geo/slime/dressed.geo.json")};
    }

    @Override
    public ResourceLocation a(em em2) {
        try {
            if (em2.field_70170_p instanceof gj) {
                return this.c[0];
            }
        }
        catch (RuntimeException runtimeException) {
            throw cr.a(runtimeException);
        }
        try {
            if ((Integer)em2.func_184212_Q().func_187225_a(em.D) > this.c.length) {
                System.out.println("Girl doesn't have an outfit Nr." + em2.func_184212_Q().func_187225_a(em.D) + " so im just making her nude lol");
                return this.c[0];
            }
        }
        catch (RuntimeException runtimeException) {
            throw cr.a(runtimeException);
        }
        try {
            if (em2 instanceof ec) {
                return this.c[(Integer)em2.func_184212_Q().func_187225_a(em.D)];
            }
        }
        catch (RuntimeException runtimeException) {
            throw cr.a(runtimeException);
        }
        try {
            if ((Integer)em2.func_184212_Q().func_187225_a(em.D) == 1) {
                return this.c[2];
            }
        }
        catch (RuntimeException runtimeException) {
            throw cr.a(runtimeException);
        }
        return this.c[0];
    }

    @Override
    public ResourceLocation b() {
        return new ResourceLocation("sexmod", "textures/entity/slime/slime.png");
    }

    @Override
    public ResourceLocation b(em em2) {
        return new ResourceLocation("sexmod", "animations/slime/slime.animation.json");
    }

    public void a(em em2, Integer n2, AnimationEvent animationEvent) {
        block13: {
            boolean bl2;
            IBone iBone;
            boolean bl3;
            IBone iBone2;
            AnimationProcessor animationProcessor;
            block15: {
                block14: {
                    super.a(em2, n2, animationEvent);
                    animationProcessor = this.getAnimationProcessor();
                    try {
                        try {
                            try {
                                try {
                                    if (em2.field_70170_p instanceof gj || animationProcessor.getBone("bedSlime") == null) break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw cr.a(runtimeException);
                                }
                                if (animationProcessor.getBone("bedSlimeLayer") == null) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw cr.a(runtimeException);
                            }
                            iBone2 = animationProcessor.getBone("bedSlime");
                            if (Arrays.asList(this.f).contains((Object)em2.y())) break block14;
                        }
                        catch (RuntimeException runtimeException) {
                            throw cr.a(runtimeException);
                        }
                        bl3 = true;
                        break block15;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cr.a(runtimeException);
                    }
                }
                bl3 = false;
            }
            try {
                iBone2.setHidden(bl3);
                iBone = animationProcessor.getBone("bedSlimeLayer");
                bl2 = !Arrays.asList(this.f).contains((Object)em2.y());
            }
            catch (RuntimeException runtimeException) {
                throw cr.a(runtimeException);
            }
            iBone.setHidden(bl2);
        }
        try {
            if (em2 instanceof ei) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cr.a(runtimeException);
        }
        this.a(new String[]{"head"}, "hat");
    }

    void a(String[] stringArray, String string) {
        AnimationProcessor animationProcessor = this.getAnimationProcessor();
        IBone iBone = animationProcessor.getBone(string);
        IBone[] iBoneArray = new IBone[stringArray.length];
        try {
            for (int i2 = 0; i2 < iBoneArray.length; ++i2) {
                iBoneArray[i2] = animationProcessor.getBone(stringArray[i2]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cr.a(runtimeException);
        }
        Vector3f vector3f = new Vector3f(0.0f, 0.0f, 0.0f);
        Vector3f vector3f2 = new Vector3f(0.0f, 0.0f, 0.0f);
        for (IBone iBone2 : iBoneArray) {
            vector3f.add((Tuple3f)new Vector3f(iBone2.getRotationX(), iBone2.getRotationY(), iBone2.getRotationZ()));
            vector3f2.add((Tuple3f)new Vector3f(iBone2.getPositionX(), iBone2.getPositionY(), iBone2.getPositionZ()));
        }
        iBone.setRotationX(vector3f.x);
        iBone.setRotationY(vector3f.y);
        iBone.setRotationZ(vector3f.z);
        iBone.setPositionX(vector3f2.x);
        iBone.setPositionY(vector3f2.y);
        iBone.setPositionZ(vector3f2.z);
        iBone.setPositionZ(vector3f2.z);
    }

    @Override
    public String[] c() {
        return new String[]{"armorHelmet"};
    }

    @Override
    public String[] g() {
        return new String[]{"bigblob"};
    }

    @Override
    public String[] f() {
        return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
    }

    @Override
    public String[] a() {
        return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR", "cloth"};
    }

    @Override
    public String[] h() {
        return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
    }

    @Override
    public String[] e() {
        return new String[]{"fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
    }

    @Override
    public String[] b() {
        return new String[]{"armorShoesL", "armorShoesR"};
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

