/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.trolmastercard.sexmod;

import com.trolmastercard.sexmod.cv;
import com.trolmastercard.sexmod.em;
import com.trolmastercard.sexmod.fp;
import com.trolmastercard.sexmod.gj;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class c0
extends cv {
    @Override
    protected ResourceLocation[] a() {
        return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/bee/bee.geo.json"), new ResourceLocation("sexmod", "geo/bee/armored.geo.json")};
    }

    @Override
    public ResourceLocation b() {
        return new ResourceLocation("sexmod", "textures/entity/bee/bee.png");
    }

    @Override
    public ResourceLocation b(em em2) {
        return new ResourceLocation("sexmod", "animations/bee/bee.animation.json");
    }

    public void a(em em2, Integer n2, AnimationEvent animationEvent) {
        boolean bl2;
        IBone iBone;
        block11: {
            block10: {
                try {
                    super.a(em2, n2, animationEvent);
                    if (em2.field_70170_p instanceof gj) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw c0.a(runtimeException);
                }
                AnimationProcessor animationProcessor = this.getAnimationProcessor();
                IBone iBone2 = animationProcessor.getBone("chest");
                try {
                    if (iBone2 == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw c0.a(runtimeException);
                }
                try {
                    try {
                        iBone = iBone2;
                        if (em2.E.getCurrentAnimation() != null && em2.E.getCurrentAnimation().animationName.contains("chest")) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw c0.a(runtimeException);
                    }
                    bl2 = true;
                    break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw c0.a(runtimeException);
                }
            }
            bl2 = false;
        }
        iBone.setHidden(bl2);
    }

    protected void a(em em2, AnimationProcessor animationProcessor, AnimationEvent animationEvent) {
        block8: {
            IBone iBone;
            block9: {
                try {
                    try {
                        try {
                            if (em2.field_70170_p instanceof gj) break block8;
                            if (em2.y() == fp.NULL) break block9;
                        }
                        catch (RuntimeException runtimeException) {
                            throw c0.a(runtimeException);
                        }
                        if (em2.y() == fp.ATTACK) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw c0.a(runtimeException);
                    }
                    if (em2.y() != fp.BOW) break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw c0.a(runtimeException);
                }
            }
            EntityModelData entityModelData = animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
            IBone iBone2 = animationProcessor.getBone("neck");
            iBone2.setRotationY(entityModelData.netHeadYaw * 0.5f * ((float)Math.PI / 180));
            IBone iBone3 = animationProcessor.getBone("head");
            try {
                iBone3.setRotationY(entityModelData.netHeadYaw * ((float)Math.PI / 180));
                iBone3.setRotationX(1.0f + entityModelData.headPitch * ((float)Math.PI / 180));
                iBone = animationProcessor.getBone("body") == null ? animationProcessor.getBone("dd") : animationProcessor.getBone("body");
            }
            catch (RuntimeException runtimeException) {
                throw c0.a(runtimeException);
            }
            IBone iBone4 = iBone;
            iBone4.setRotationY(0.0f);
        }
    }

    @Override
    public String[] c() {
        return new String[]{"armorHelmet"};
    }

    @Override
    public String[] g() {
        return new String[]{"band", "feeler", "feeler2", "brow", "brow2", "brow3", "brow4"};
    }

    @Override
    public String[] f() {
        return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
    }

    @Override
    public String[] a() {
        return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR"};
    }

    @Override
    public String[] h() {
        return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
    }

    @Override
    public String[] e() {
        return new String[]{"sideL", "sideR", "fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
    }

    @Override
    public String[] b() {
        return new String[]{"armorShoesL", "armorShoesR"};
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

