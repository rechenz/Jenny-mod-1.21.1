package com.schnurritv.sexmod.effects;

import com.schnurritv.sexmod.Main;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Custom potion effects (1.21.1).
 *
 * HORNY: applied by the Horny Potion. Marks the player as "horny" so
 * untamed kobolds interact for free. A dedicated effect (instead of
 * checking vanilla Regen+Speed) avoids false positives from golden
 * apples / beacons / other mods (audit M2).
 */
public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MODID);

    private static final RegistryObject<MobEffect> HORNY_EFFECT = EFFECTS.register("horny",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xFF6EB4) {
                // No tick logic needed — pure marker effect
            });

    /**
     * Holder for the horny effect (1.21.1 API wants Holder<MobEffect>).
     * Holder.direct() equality is by-value, so every call returns an equal
     * holder for the same registered singleton — potion application and
     * hasEffect() checks match correctly.
     */
    public static Holder<MobEffect> hornyHolder() {
        return Holder.direct(HORNY_EFFECT.get());
    }
}
