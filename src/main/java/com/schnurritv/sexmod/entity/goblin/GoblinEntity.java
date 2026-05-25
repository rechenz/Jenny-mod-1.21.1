package com.schnurritv.sexmod.entity.goblin;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.network.chat.Component;

/**
 * Goblin — Cave-dwelling thief.
 * Steals items from nearby players. Catch her → choose: retrieve items OR watch scene.
 */
public class GoblinEntity extends BaseGirlEntity {
    public GoblinEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "goblin"; }
    @Override public String getGeoFileName() { return "armored"; }
    @Override public String getNudeGeoFileName() { return "armored"; }

    private int stealCooldown = 0;
    // Track how many times this goblin has been "caught" for loot retrieval
    private int stealCount = 0;
    public int getStealCount() { return stealCount; }

    @Override public boolean requiresBedForMissionary() { return false; }
    @Override public boolean requiresBedForDoggy() { return false; }

    @Override public boolean showStandardMissionary() { return false; }
    @Override public boolean showStandardDoggy() { return false; }
    @Override public boolean showStandardBlowjob() { return true; }
    @Override public boolean showStandardBoobjob() { return true; }

    // Goblin: breeding_*, nelson_*, paizuri_*, catch_*
    @Override
    public String getSceneAnimationPath(SexModAnimation animation) {
        String p = getAnimationPrefix();
        return switch (animation) {
            case MISSIONARY_START   -> "animation." + p + ".breeding_intro_1";
            case MISSIONARY_SLOW    -> "animation." + p + ".breeding_slow_1l";
            case MISSIONARY_FAST    -> "animation." + p + ".breeding_fast_1s";
            case MISSIONARY_CUM     -> "animation." + p + ".breeding_cum_1";
            case BLOWJOBINTRO      -> "animation." + p + ".paizuri_start";
            case BLOWJOBSUCK       -> "animation." + p + ".paizuri_slow";
            case BLOWJOBTHRUST     -> "animation." + p + ".paizuri_fast";
            case BLOWJOBCUM        -> "animation." + p + ".paizuri_cum";
            case PAIZURI_START      -> "animation." + p + ".nelson_intro";
            case PAIZURI_SLOW       -> "animation." + p + ".nelson_slow";
            case PAIZURI_FAST       -> "animation." + p + ".nelson_fastc";
            case PAIZURI_CUM        -> "animation." + p + ".nelson_cum";
            case DOGGYSTART, DOGGYGOONBED, DOGGYWAIT -> "animation." + p + ".catch_1personBj_idle";
            case DOGGYSLOW         -> "animation." + p + ".catch_1personBj";
            case DOGGYFAST         -> "animation." + p + ".catch_3personBj";
            case DOGGYCUM          -> "animation." + p + ".breeding_cum_1";
            default -> "animation." + p + ".idle";
        };
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        // Don't steal while in a scene or following player
        if (this.getEntityData().get(IS_LOCKED)) return;
        if ("FOLLOW".equals(this.getEntityData().get(
            com.schnurritv.sexmod.entity.SexEntity.MOVEMENT_STATE))) return;

        if (stealCooldown > 0) { stealCooldown--; return; }
        stealCooldown = 300; // Every 15 seconds

        String uuid = this.entityData.get(MASTER_UUID);
        // Only steal from non-owner players (wild goblins)
        Player owner = null;
        if (!uuid.isEmpty()) {
            try { owner = this.level().getPlayerByUUID(java.util.UUID.fromString(uuid)); }
            catch (Exception e) {}
        }

        for (Player p : this.level().players()) {
            if (p.distanceToSqr(this) > 16 || p.isCreative() || p.isSpectator()) continue;
            if (p == owner) continue; // Don't steal from owner

            // Find a stealable item in player's hotbar (slots 0-8)
            for (int slot = 0; slot < 9; slot++) {
                ItemStack stack = p.getInventory().getItem(slot);
                if (stack.isEmpty()) continue;
                // Don't steal items considered too valuable
                if (stack.is(net.minecraft.world.item.Items.NETHERITE_INGOT) ||
                    stack.is(net.minecraft.world.item.Items.NETHER_STAR) ||
                    stack.is(net.minecraft.world.item.Items.DEBUG_STICK)) continue;

                ItemStack stolen = stack.split(1); // Take 1 item
                if (!stolen.isEmpty()) {
                    stealCount++;
                    this.setItemSlot(net.minecraft.world.entity.EquipmentSlot.MAINHAND, stolen);
                    p.displayClientMessage(
                        Component.literal("§cA goblin swiped something from you!"), true);
                    break;
                }
            }
            break; // Only steal from one player per cycle
        }
    }

    /** Return stolen items when player catches the goblin (right-click with empty hand). */
    public void returnStolenItems(ServerPlayer player) {
        if (stealCount <= 0) return;
        // Drop items at goblin's position
        for (int i = 0; i < stealCount; i++) {
            // Give a small compensation item
            ItemStack reward = new ItemStack(net.minecraft.world.item.Items.EMERALD, 
                this.random.nextInt(2) + 1);
            player.getInventory().add(reward);
        }
        stealCount = 0;
        this.setItemSlot(net.minecraft.world.entity.EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        player.displayClientMessage(
            Component.literal("§aThe goblin grudgingly returns your things..."), false);
    }

    public void watchScene(ServerPlayer player) {
        // Scene handled via InteractionScreen
    }
}
