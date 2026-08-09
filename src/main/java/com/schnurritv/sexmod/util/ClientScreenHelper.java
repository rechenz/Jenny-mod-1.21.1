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
 *
 * <p>Constructor lookup uses {@link #findConstructor(Class, Object...)} which
 * matches assignable parameter types (audit H2): screens take BaseGirlEntity
 * while callers pass concrete subclasses like JennyEntity — a plain
 * getConstructor(runtimeClass) would throw NoSuchMethodException.
 */
public final class ClientScreenHelper {

    private ClientScreenHelper() {}

    /**
     * Find a constructor whose parameter types are assignable FROM the runtime
     * classes of the given args (i.e. each arg's class is a subtype of the
     * declared parameter type). Falls back to exact-match then superclass
     * walk for robustness.
     */
    private static java.lang.reflect.Constructor<?> findConstructor(
            Class<?> cls, Object... args) throws NoSuchMethodException {
        Class<?>[] argTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i] == null ? null : args[i].getClass();
        }
        // Pass 1: exact match
        try {
            return cls.getConstructor(argTypes);
        } catch (NoSuchMethodException ignored) {
            // fall through
        }
        // Pass 2: assignable match (constructor param must accept our arg type)
        for (java.lang.reflect.Constructor<?> ctor : cls.getConstructors()) {
            Class<?>[] params = ctor.getParameterTypes();
            if (params.length != args.length) continue;
            boolean ok = true;
            for (int i = 0; i < params.length; i++) {
                if (argTypes[i] == null) {
                    if (params[i].isPrimitive()) { ok = false; break; }
                } else if (!params[i].isAssignableFrom(argTypes[i])) {
                    ok = false;
                    break;
                }
            }
            if (ok) return ctor;
        }
        throw new NoSuchMethodException(cls.getName());
    }

    private static void open(String screenClass, Object... args) {
        runOnClient(() -> {
            try {
                Class<?> cls = Class.forName(screenClass);
                java.lang.reflect.Constructor<?> ctor = findConstructor(cls, args);
                Object screen = ctor.newInstance(args);
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        (net.minecraft.client.gui.screens.Screen) screen);
            } catch (Exception e) {
                com.schnurritv.sexmod.Main.LOGGER.error("Failed to open " + screenClass, e);
            }
        });
    }

    /** Open InteractionScreen (common entity right-click menu). */
    public static void openInteraction(Object girlEntity) {
        open("com.schnurritv.sexmod.client.gui.InteractionScreen", girlEntity);
    }

    /** Open AllieActionScreen (Allie wish menu). */
    public static void openAllieAction(Object allieEntity) {
        open("com.schnurritv.sexmod.client.gui.AllieActionScreen", allieEntity);
    }

    /** Open GoblinCaughtScreen (goblin catch dialog). */
    public static void openGoblinCaught(Object goblinEntity, Object player) {
        open("com.schnurritv.sexmod.client.gui.GoblinCaughtScreen", goblinEntity, player);
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
        open("com.schnurritv.sexmod.client.gui.GalathGrabScreen", galathEntity, player);
    }

    /** Open NpcEditorScreen (girl wand). */
    public static void openNpcEditor(Object girlEntity) {
        open("com.schnurritv.sexmod.client.gui.NpcEditorScreen", girlEntity);
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
