package com.schnurritv.sexmod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

/**
 * Camera transition helper for smooth transition when leaving a scene.
 * Records the last scene camera position and interpolates back to normal
 * third-person position over 5 ticks (0.25s) using linear interpolation.
 */
public class CameraTransitionHelper {

    private static boolean active = false;
    private static Vec3 startPos = Vec3.ZERO;
    private static Vec3 endPos = Vec3.ZERO;
    private static int elapsedTicks = 0;
    private static final int TRANSITION_DURATION = 5; // ticks (0.25s)

    /**
     * Start a camera transition from scenePosition back to normal player position.
     */
    public static void startTransition(Vec3 scenePosition) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        startPos = scenePosition;
        endPos = player.getEyePosition(1.0f);
        elapsedTicks = 0;
        active = true;
    }

    /**
     * Called each client tick to advance the transition.
     */
    public static void tick() {
        if (!active) return;

        elapsedTicks++;
        if (elapsedTicks >= TRANSITION_DURATION) {
            active = false;
        }
    }

    /**
     * Get the current interpolated camera position during transition.
     * Returns null if transition is not active.
     */
    public static Vec3 getCurrentPosition() {
        if (!active) return null;

        float t = Math.min(1.0f, (float) elapsedTicks / TRANSITION_DURATION);
        return new Vec3(
            startPos.x + (endPos.x - startPos.x) * t,
            startPos.y + (endPos.y - startPos.y) * t,
            startPos.z + (endPos.z - startPos.z) * t
        );
    }

    public static boolean isActive() {
        return active;
    }

    /**
     * Forcefully clear transition (for safety).
     */
    public static void clear() {
        active = false;
    }
}
