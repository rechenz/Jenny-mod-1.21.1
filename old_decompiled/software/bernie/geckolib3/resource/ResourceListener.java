/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IReloadableResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 */
package software.bernie.geckolib3.resource;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class ResourceListener {
    public static void registerReloadListener() {
        if (Minecraft.func_71410_x().func_110442_L() == null) {
            throw new RuntimeException("GeckoLib was initialized too early! If you are on fabric, please read the wiki on when to initialize!");
        }
        IReloadableResourceManager reloadable = (IReloadableResourceManager)Minecraft.func_71410_x().func_110442_L();
        reloadable.func_110542_a((IResourceManagerReloadListener)GeckoLibCache.getInstance());
    }
}

