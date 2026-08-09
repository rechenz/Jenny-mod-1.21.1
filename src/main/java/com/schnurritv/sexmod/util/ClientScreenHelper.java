package com.schnurritv.sexmod.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

/**
 * Opens client-only screens via reflection so that common classes
 * (entities, items, packets) never reference client classes directly.
 *
 * <p>Why reflection? A direct reference (even inside a DistExecutor lambda)
 * puts the client class name in the common class's bytecode constant pool.
 * On a dedicated server, the JVM verifier resolves those references while
 * loading the common class → NoClassDefFoundError for client-only types
 * (e.g. net.minecraft.client.gui.components.Renderable, pulled in by Screen).
 * Reflection defers the lookup until the lambda actually runs on the client.
 */
public final class ClientScreenHelper {

    private ClientScreenHelper() {}

    /** Open InteractionScreen (common entity right-click menu). */
    public static void openInteraction(Object girlEntity) {
        runOnClient(() -> {
            try {
                Class<?> cls = Class.forName("com.schnurritv.sexmod.client.gui.InteractionScreen");
                java.lang.reflect.Constructor<?> ctor = cls.getConstructor(girlEntity.getClass());
                Object screen = ctor.newInstance(girlEntity);
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        (net.minecraft.client.gui.screens.Screen) screen);
            } catch (Exception e) {
                com.schnurritv.sexmod.Main.LOGGER.error("Failed to open InteractionScreen", e);
            }
        });
    }

    /** Open AllieActionScreen (Allie wish menu). */
    public static void openAllieAction(Object allieEntity) {
        runOnClient(() -> {
            try {
                Class<?> cls = Class.forName("com.schnurritv.sexmod.client.gui.AllieActionScreen");
                java.lang.reflect.Constructor<?> ctor = cls.getConstructor(allieEntity.getClass());
                Object screen = ctor.newInstance(allieEntity);
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        (net.minecraft.client.gui.screens.Screen) screen);
            } catch (Exception e) {
                com.schnurritv.sexmod.Main.LOGGER.error("Failed to open AllieActionScreen", e);
            }
        });
    }

    /** Open GoblinCaughtScreen (goblin catch dialog). */
    public static void openGoblinCaught(Object goblinEntity, Object player) {
        runOnClient(() -> {
            try {
                Class<?> cls = Class.forName("com.schnurritv.sexmod.client.gui.GoblinCaughtScreen");
                java.lang.reflect.Constructor<?> ctor = cls.getConstructor(
                        goblinEntity.getClass(), net.minecraft.world.entity.player.Player.class);
                Object screen = ctor.newInstance(goblinEntity, player);
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        (net.minecraft.client.gui.screens.Screen) screen);
            } catch (Exception e) {
                com.schnurritv.sexmod.Main.LOGGER.error("Failed to open GoblinCaughtScreen", e);
            }
        });
    }

    /** Open GuideBookScreen (guide book item). */
    public static void openGuideBook(Object itemStack, String[] pages) {
        runOnClient(() -> {
            try {
                Class<?> cls = Class.forName("com.schnurritv.sexmod.item.GuideBookScreen");
                java.lang.reflect.Constructor<?> ctor = cls.getConstructor(
                        net.minecraft.world.item.ItemStack.class, String[].class);
                Object screen = ctor.newInstance(itemStack, pages);
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        (net.minecraft.client.gui.screens.Screen) screen);
            } catch (Exception e) {
                com.schnurritv.sexmod.Main.LOGGER.error("Failed to open GuideBookScreen", e);
            }
        });
    }

    /** Open GalathGrabScreen (combat grab minigame). */
    public static void openGalathGrab(Object galathEntity, Object player) {
        runOnClient(() -> {
            try {
                Class<?> cls = Class.forName("com.schnurritv.sexmod.client.gui.GalathGrabScreen");
                java.lang.reflect.Constructor<?> ctor = cls.getConstructor(
                        galathEntity.getClass(), net.minecraft.world.entity.player.Player.class);
                Object screen = ctor.newInstance(galathEntity, player);
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        (net.minecraft.client.gui.screens.Screen) screen);
            } catch (Exception e) {
                com.schnurritv.sexmod.Main.LOGGER.error("Failed to open GalathGrabScreen", e);
            }
        });
    }

    /** Open NpcEditorScreen (girl wand). */
    public static void openNpcEditor(Object girlEntity) {
        runOnClient(() -> {
            try {
                Class<?> cls = Class.forName("com.schnurritv.sexmod.client.gui.NpcEditorScreen");
                java.lang.reflect.Constructor<?> ctor = cls.getConstructor(girlEntity.getClass());
                Object screen = ctor.newInstance(girlEntity);
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        (net.minecraft.client.gui.screens.Screen) screen);
            } catch (Exception e) {
                com.schnurritv.sexmod.Main.LOGGER.error("Failed to open NpcEditorScreen", e);
            }
        });
    }

    /** Whether the current client screen is a GalathGrabScreen. */
    public static boolean isGalathGrabScreenOpen() {
        final boolean[] result = {false};
        runOnClient(() -> {
            try {
                net.minecraft.client.gui.screens.Screen s =
                        net.minecraft.client.Minecraft.getInstance().screen;
                if (s != null) {
                    Class<?> cls = Class.forName("com.schnurritv.sexmod.client.gui.GalathGrabScreen");
                    result[0] = cls.isInstance(s);
                }
            } catch (Exception e) {
                result[0] = false;
            }
        });
        return result[0];
    }

    private static void runOnClient(Runnable action) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> action);
    }
}
