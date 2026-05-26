/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.b6;
import com.trolmastercard.sexmod.cm;
import com.trolmastercard.sexmod.e2;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.gc;
import com.trolmastercard.sexmod.gj;
import com.trolmastercard.sexmod.gs;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public abstract class cv<T extends em>
extends cm<T>
implements gs {
    public static final List<String> b = Arrays.asList("braStringMidStartR", "braStringMidMid1R", "braStringMidMid2R", "braStringMidMid3R", "braStringMidEndR", "braStringBackR", "braStringRightEndR", "braStringRightStartR", "braStringRightL", "braStringMidMid1L", "braStringMidMid2L", "braStringMidMid3L", "braStringMidEndL", "braStringBackL", "braStringLeftEndL", "braStringLeftStartL", "braStringMidStartL", "braStringRightR");
    public static final List<String> e = Arrays.asList("boyCam", "girlCam");
    public static boolean d = true;
    protected ResourceLocation[] c = this.a();
    protected Minecraft a = Minecraft.func_71410_x();

    protected cv() {
    }

    protected abstract ResourceLocation[] a();

    public abstract ResourceLocation b();

    public abstract ResourceLocation b(em var1);

    public ResourceLocation c(em em2) {
        return this.b(em2);
    }

    public ResourceLocation a(em em2) {
        try {
            if (em2.field_70170_p instanceof gj) {
                return this.c[0];
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        try {
            if ((Integer)em2.func_184212_Q().func_187225_a(em.D) > this.c.length) {
                System.out.println("Girl doesn't have an outfit Nr." + em2.func_184212_Q().func_187225_a(em.D) + " so im just making her nude lol");
                return this.c[0];
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        return this.c[(Integer)em2.func_184212_Q().func_187225_a(em.D)];
    }

    public ResourceLocation g(em em2) {
        return this.b();
    }

    @Override
    public void setMolangQueries(IAnimatable iAnimatable, double d10) {
        try {
            if (Minecraft.func_71410_x().field_71441_e != null) {
                super.setMolangQueries(iAnimatable, d10);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
    }

    public void a(T t2, Integer n2, AnimationEvent animationEvent) {
        block24: {
            AnimationProcessor animationProcessor;
            block23: {
                block18: {
                    double d10;
                    block21: {
                        block20: {
                            super.setLivingAnimations(t2, n2, animationEvent);
                            animationProcessor = this.getAnimationProcessor();
                            try {
                                this.a(t2, animationProcessor);
                                if (((em)t2).field_70170_p instanceof gj) {
                                    return;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw cv.b(runtimeException);
                            }
                            try {
                                if (((Boolean)t2.func_184212_Q().func_187225_a(em.G)).booleanValue()) {
                                    t2.func_180426_a(((em)t2).o().field_72450_a, ((em)t2).o().field_72448_b, ((em)t2).o().field_72449_c, ((em)t2).I().floatValue(), 0.0f, 3, true);
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw cv.b(runtimeException);
                            }
                            try {
                                block19: {
                                    try {
                                        try {
                                            if (((em)t2).C == null) break block18;
                                            AnimationController animationController = ((em)t2).C;
                                            if (((em)t2).field_70170_p instanceof gj) break block19;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw cv.b(runtimeException);
                                        }
                                        if (((em)t2).y() != null) break block20;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw cv.b(runtimeException);
                                    }
                                }
                                d10 = 5.0;
                                break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw cv.b(runtimeException);
                            }
                        }
                        d10 = ((em)t2).y().transitionTick;
                    }
                    animationController.transitionLengthTicks = d10;
                }
                try {
                    block22: {
                        try {
                            try {
                                this.a(t2, animationProcessor, animationEvent);
                                if (!(t2 instanceof e2) || ((em)t2).h()) break block22;
                            }
                            catch (RuntimeException runtimeException) {
                                throw cv.b(runtimeException);
                            }
                            if (((em)t2).ah() != 0) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw cv.b(runtimeException);
                        }
                    }
                    this.a(animationProcessor);
                    break block24;
                }
                catch (RuntimeException runtimeException) {
                    throw cv.b(runtimeException);
                }
            }
            this.a(animationProcessor, (ItemStack)((em)t2).m.func_187225_a(e2.X), (ItemStack)((em)t2).m.func_187225_a(e2.T), (ItemStack)((em)t2).m.func_187225_a(e2.U), (ItemStack)((em)t2).m.func_187225_a(e2.W));
        }
    }

    public static Vec3d d(em em2) {
        return cv.a(new Vec3d(em2.field_70142_S, em2.field_70137_T, em2.field_70136_U), em2.func_174791_d());
    }

    public static Vec3d a(em em2, Vec3d vec3d) {
        return cv.a(vec3d, em2.func_174791_d());
    }

    public static Vec3d a(Vec3d vec3d, Vec3d vec3d2) {
        float f10;
        float f11;
        int n2;
        Vec3d vec3d3;
        Vec3d vec3d4;
        Vec3d vec3d5 = vec3d2.func_178788_d(vec3d);
        Vec3d vec3d6 = new Vec3d(Math.abs(vec3d5.field_72450_a), Math.abs(vec3d5.field_72448_b), Math.abs(vec3d5.field_72449_c));
        double d10 = vec3d6.field_72450_a / (vec3d6.field_72450_a + vec3d6.field_72448_b + vec3d6.field_72449_c);
        double d11 = vec3d6.field_72448_b / (vec3d6.field_72450_a + vec3d6.field_72448_b + vec3d6.field_72449_c);
        double d12 = vec3d6.field_72449_c / (vec3d6.field_72450_a + vec3d6.field_72448_b + vec3d6.field_72449_c);
        try {
            Vec3d vec3d7;
            vec3d4 = vec3d7;
            vec3d3 = vec3d7;
            n2 = vec3d5.field_72450_a > 0.0 ? 1 : -1;
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        vec3d4((double)n2 * d10, (double)(vec3d5.field_72448_b > 0.0 ? 1 : -1) * d11, (double)(vec3d5.field_72449_c > 0.0 ? 1 : -1) * d12);
        Vec3d vec3d8 = vec3d3;
        double d13 = vec3d8.field_72448_b / 2.0 + 0.5;
        float f12 = (float)b6.b(-180.0, 0.0, d13);
        if (Float.isNaN(f12)) {
            f12 = -90.0f;
        }
        try {
            float f13 = f11 = d13 < 0.5 ? 0.0f : (float)b6.b(0.0, 16.0, -d13);
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        if (Float.isNaN(f11)) {
            f11 = 0.0f;
        }
        if (Float.isNaN(f10 = (float)(4.0 - Math.sin(1.5707963267948966 + d13 * 2.0 * Math.PI) * 4.0))) {
            f10 = 8.0f;
        }
        return new Vec3d((double)gc.c(f12), (double)f11, (double)f10);
    }

    void a(AnimationProcessor<T> animationProcessor, ItemStack itemStack, ItemStack itemStack2, ItemStack itemStack3, ItemStack itemStack4) {
        boolean bl2;
        AnimationProcessor<T> animationProcessor2;
        cv cv2;
        boolean bl3;
        AnimationProcessor<T> animationProcessor3;
        cv cv3;
        boolean bl4;
        AnimationProcessor<T> animationProcessor4;
        cv cv4;
        try {
            cv4 = this;
            animationProcessor4 = animationProcessor;
            bl4 = !itemStack.func_190926_b();
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        try {
            cv4.c(animationProcessor4, bl4);
            this.b(animationProcessor, itemStack2.func_77973_b() instanceof ItemArmor);
            cv3 = this;
            animationProcessor3 = animationProcessor;
            bl3 = !itemStack3.func_190926_b();
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        try {
            cv3.d(animationProcessor3, bl3);
            cv2 = this;
            animationProcessor2 = animationProcessor;
            bl2 = !itemStack4.func_190926_b();
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        cv2.a(animationProcessor2, bl2);
    }

    protected void a(AnimationProcessor<T> animationProcessor) {
        this.c(animationProcessor, false);
        this.b(animationProcessor, false);
        this.d(animationProcessor, false);
        this.a(animationProcessor, false);
    }

    void c(AnimationProcessor animationProcessor, boolean bl2) {
        boolean bl3;
        String[] stringArray;
        cv cv2;
        try {
            this.a(this.c(), bl2, animationProcessor);
            cv2 = this;
            stringArray = this.g();
            bl3 = !bl2;
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        cv2.a(stringArray, bl3, animationProcessor);
    }

    void b(AnimationProcessor<T> animationProcessor, boolean bl2) {
        boolean bl3;
        String[] stringArray;
        cv cv2;
        try {
            this.a(this.f(), bl2, animationProcessor);
            cv2 = this;
            stringArray = this.a();
            bl3 = !bl2;
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        cv2.a(stringArray, bl3, animationProcessor);
    }

    void d(AnimationProcessor<T> animationProcessor, boolean bl2) {
        boolean bl3;
        String[] stringArray;
        cv cv2;
        try {
            this.a(this.h(), bl2, animationProcessor);
            cv2 = this;
            stringArray = this.e();
            bl3 = !bl2;
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        cv2.a(stringArray, bl3, animationProcessor);
    }

    void a(AnimationProcessor<T> animationProcessor, boolean bl2) {
        boolean bl3;
        String[] stringArray;
        cv cv2;
        try {
            this.a(this.b(), bl2, animationProcessor);
            cv2 = this;
            stringArray = this.d();
            bl3 = !bl2;
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        cv2.a(stringArray, bl3, animationProcessor);
    }

    void a(String[] stringArray, boolean bl2, AnimationProcessor<T> animationProcessor) {
        for (String string : stringArray) {
            this.a(string, bl2, animationProcessor);
        }
    }

    void a(String string, boolean bl2, AnimationProcessor<T> animationProcessor) {
        boolean bl3;
        IBone iBone;
        try {
            if (animationProcessor.getBone(string) == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        try {
            iBone = animationProcessor.getBone(string);
            bl3 = !bl2;
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        iBone.setHidden(bl3);
    }

    protected boolean f(T t2) {
        UUID uUID = ((em)t2).ae();
        try {
            if (uUID == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        World world = ((em)t2).field_70170_p;
        AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)world.func_152378_a(uUID);
        try {
            if (abstractClientPlayer == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        return "default".equals(abstractClientPlayer.func_175154_l());
    }

    void a(T t2, AnimationProcessor<T> animationProcessor) {
        block12: {
            boolean bl2;
            IBone iBone;
            block14: {
                block13: {
                    boolean bl3;
                    IBone iBone2;
                    boolean bl4;
                    IBone iBone3;
                    boolean bl5;
                    IBone iBone4;
                    boolean bl6;
                    IBone iBone5;
                    boolean bl7 = this.f(t2);
                    try {
                        animationProcessor.getBone("rightArmAlex").setHidden(bl7);
                        animationProcessor.getBone("rightLowerArmAlex").setHidden(bl7);
                        iBone5 = animationProcessor.getBone("rightArmSteve");
                        bl6 = !bl7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cv.b(runtimeException);
                    }
                    try {
                        iBone5.setHidden(bl6);
                        iBone4 = animationProcessor.getBone("rightLowerArmSteve");
                        bl5 = !bl7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cv.b(runtimeException);
                    }
                    try {
                        iBone4.setHidden(bl5);
                        animationProcessor.getBone("leftArmAlex").setHidden(bl7);
                        animationProcessor.getBone("leftLowerArmAlex").setHidden(bl7);
                        iBone3 = animationProcessor.getBone("leftArmSteve");
                        bl4 = !bl7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cv.b(runtimeException);
                    }
                    try {
                        iBone3.setHidden(bl4);
                        iBone2 = animationProcessor.getBone("leftLowerArmSteve");
                        bl3 = !bl7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cv.b(runtimeException);
                    }
                    iBone2.setHidden(bl3);
                    IBone iBone6 = animationProcessor.getBone("steve");
                    try {
                        try {
                            if (iBone6 == null) break block12;
                            iBone = iBone6;
                            if (((em)t2).y().hasPlayer) break block13;
                        }
                        catch (RuntimeException runtimeException) {
                            throw cv.b(runtimeException);
                        }
                        bl2 = true;
                        break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cv.b(runtimeException);
                    }
                }
                bl2 = false;
            }
            iBone.setHidden(bl2);
        }
    }

    protected boolean e(T t2) {
        return true;
    }

    protected void a(T t2, AnimationProcessor<T> animationProcessor, AnimationEvent animationEvent) {
        IBone iBone;
        block14: {
            try {
                if (((em)t2).field_70170_p instanceof gj) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw cv.b(runtimeException);
            }
            try {
                if (!this.e(t2)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw cv.b(runtimeException);
            }
            try {
                try {
                    try {
                        if (((em)t2).y() == fp.NULL || ((em)t2).y() == fp.ATTACK) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw cv.b(runtimeException);
                    }
                    if (((em)t2).y() == fp.BOW) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw cv.b(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw cv.b(runtimeException);
            }
        }
        EntityModelData entityModelData = animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        IBone iBone2 = animationProcessor.getBone("neck");
        iBone2.setRotationY(entityModelData.netHeadYaw * 0.5f * ((float)Math.PI / 180));
        IBone iBone3 = animationProcessor.getBone("head");
        try {
            iBone3.setRotationY(entityModelData.netHeadYaw * ((float)Math.PI / 180));
            iBone3.setRotationX(entityModelData.headPitch * ((float)Math.PI / 180));
            iBone = animationProcessor.getBone("body") == null ? animationProcessor.getBone("dd") : animationProcessor.getBone("body");
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        IBone iBone4 = iBone;
        iBone4.setRotationY(0.0f);
    }

    public ItemStack a(em em2, String string) {
        try {
            if (Arrays.asList(this.c()).contains(string)) {
                return (ItemStack)em2.m.func_187225_a(e2.X);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        try {
            if (Arrays.asList(this.f()).contains(string)) {
                return (ItemStack)em2.m.func_187225_a(e2.T);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        try {
            if (Arrays.asList(this.h()).contains(string)) {
                return (ItemStack)em2.m.func_187225_a(e2.U);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        try {
            if (Arrays.asList(this.b()).contains(string)) {
                return (ItemStack)em2.m.func_187225_a(e2.W);
            }
        }
        catch (RuntimeException runtimeException) {
            throw cv.b(runtimeException);
        }
        return ItemStack.field_190927_a;
    }

    private static RuntimeException b(RuntimeException runtimeException) {
        return runtimeException;
    }
}

