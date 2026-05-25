package com.schnurritv.sexmod.relationship;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * Per-character affection and relationship data.
 * Stored via NBT on each girl entity.
 */
public class AffectionData {

    // ── NBT keys ──
    private static final String TAG_AFFECTION      = "Affection";
    private static final String TAG_DAILY_GIFTS     = "DailyGifts";
    private static final String TAG_LAST_GIFT_DAY   = "LastGiftDay";
    private static final String TAG_LAST_DECAY_DAY  = "LastDecayDay";
    private static final String TAG_UNLOCKED_SCENES = "UnlockedScenes";
    private static final String TAG_OWNER           = "OwnerUUID";

    // ── Fields ──
    private int affection = 0;            // 0–100
    private int dailyGifts = 0;
    private long lastGiftDay = 0;         // minecraft day
    private long lastDecayDay = 0;
    private int unlockedScenes = 0;        // bitmask
    private String ownerUUID = "";

    public int getAffection() { return affection; }
    public int getDailyGifts() { return dailyGifts; }
    public long getLastGiftDay() { return lastGiftDay; }
    public String getOwnerUUID() { return ownerUUID; }

    public void setOwner(String uuid) { this.ownerUUID = uuid; }

    /** Add affection, clamped to max, returns true if value actually changed */
    public boolean addAffection(int amount, int max) {
        int old = affection;
        affection = Math.min(max, affection + amount);
        return affection != old;
    }

    /** Check if player can give a gift today */
    public boolean canGiveGift(long currentDay, int dailyLimit) {
        if (currentDay != lastGiftDay) {
            dailyGifts = 0;
        }
        return dailyGifts < dailyLimit;
    }

    /** Record a gift given */
    public void recordGift(long currentDay) {
        if (currentDay != lastGiftDay) {
            dailyGifts = 0;
            lastGiftDay = currentDay;
        }
        dailyGifts++;
    }

    /** Apply daily affection decay */
    public void applyDecay(long currentDay, double decayAmount) {
        if (affection <= 0) return;
        if (currentDay <= lastDecayDay) return;
        lastDecayDay = currentDay;
        affection = Math.max(0, affection - (int) Math.round(decayAmount));
    }

    /** Check if a scene is unlocked by bitmask index */
    public boolean isSceneUnlocked(int sceneBit) {
        return (unlockedScenes & (1 << sceneBit)) != 0;
    }

    /** Unlock a scene by bitmask index */
    public void unlockScene(int sceneBit) {
        unlockedScenes |= (1 << sceneBit);
    }

    /** Get affection level as a tier (0-4) */
    public AffectionLevel getLevel() {
        if (affection >= 80) return AffectionLevel.INTIMATE;
        if (affection >= 60) return AffectionLevel.CLOSE;
        if (affection >= 30) return AffectionLevel.FRIENDLY;
        if (affection >= 10) return AffectionLevel.ACQUAINTED;
        return AffectionLevel.STRANGER;
    }

    public enum AffectionLevel {
        STRANGER("Stranger"),
        ACQUAINTED("Acquainted"),
        FRIENDLY("Friendly"),
        CLOSE("Close"),
        INTIMATE("Intimate");

        public final String label;
        AffectionLevel(String label) { this.label = label; }
    }

    // ── NBT serialization ──
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(TAG_AFFECTION, affection);
        tag.putInt(TAG_DAILY_GIFTS, dailyGifts);
        tag.putLong(TAG_LAST_GIFT_DAY, lastGiftDay);
        tag.putLong(TAG_LAST_DECAY_DAY, lastDecayDay);
        tag.putInt(TAG_UNLOCKED_SCENES, unlockedScenes);
        tag.putString(TAG_OWNER, ownerUUID);
        return tag;
    }

    public void fromNBT(CompoundTag tag) {
        if (tag.contains(TAG_AFFECTION)) affection = tag.getInt(TAG_AFFECTION);
        if (tag.contains(TAG_DAILY_GIFTS)) dailyGifts = tag.getInt(TAG_DAILY_GIFTS);
        if (tag.contains(TAG_LAST_GIFT_DAY)) lastGiftDay = tag.getLong(TAG_LAST_GIFT_DAY);
        if (tag.contains(TAG_LAST_DECAY_DAY)) lastDecayDay = tag.getLong(TAG_LAST_DECAY_DAY);
        if (tag.contains(TAG_UNLOCKED_SCENES)) unlockedScenes = tag.getInt(TAG_UNLOCKED_SCENES);
        if (tag.contains(TAG_OWNER)) ownerUUID = tag.getString(TAG_OWNER);
    }

    /** Get level-based greeting */
    public String getGreeting(String girlName) {
        return switch (getLevel()) {
            case INTIMATE   -> DialogueDB.getRandom("%s_intimate_greeting", girlName);
            case CLOSE      -> DialogueDB.getRandom("%s_close_greeting", girlName);
            case FRIENDLY   -> DialogueDB.getRandom("%s_friendly_greeting", girlName);
            case ACQUAINTED -> DialogueDB.getRandom("%s_acquainted_greeting", girlName);
            case STRANGER   -> DialogueDB.getRandom("%s_stranger_greeting", girlName);
        };
    }
}
