/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package software.bernie.geckolib3.util;

import java.util.Objects;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GeckoLibUtil {
    public static int getIDFromStack(ItemStack stack) {
        return Objects.hash(stack.func_77973_b(), stack.func_190916_E(), stack.func_77942_o() ? stack.func_77978_p().toString() : Integer.valueOf(1));
    }

    public static AnimationController getControllerForStack(AnimationFactory factory, ItemStack stack, String controllerName) {
        return factory.getOrCreateAnimationData(GeckoLibUtil.getIDFromStack(stack)).getAnimationControllers().get(controllerName);
    }
}

