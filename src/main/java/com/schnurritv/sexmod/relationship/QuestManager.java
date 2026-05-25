package com.schnurritv.sexmod.relationship;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import java.util.*;

/**
 * Per-character quest system: fetch quests, gift quests, escort quests.
 * Stored in entity NBT alongside AffectionData.
 */
public class QuestManager {

    public enum QuestType { FETCH, GIFT_COUNT, VISIT_LOCATION }

    public record Quest(String id, QuestType type, String description, String targetItem,
                        int targetCount, int rewardAffection, String rewardItem, String girlName) {}

    private static final Map<String, List<Quest>> QUESTS_BY_GIRL = new HashMap<>();
    private static final Random RAND = new Random();

    // ── Per-entity state ──
    private String activeQuestId = "";
    private int questProgress = 0;
    private final Set<String> completedQuests = new HashSet<>();

    static {
        // JENNY: gaming-themed quests
        register("jenny", new Quest("jenny_gear", QuestType.FETCH,
            "Find me 5 Iron Ingots — I need to upgrade my gaming rig!",
            "minecraft:iron_ingot", 5, 25, "", "jenny"));
        register("jenny", new Quest("jenny_redstone", QuestType.FETCH,
            "Bring me 10 Redstone! I'm building... something.",
            "minecraft:redstone", 10, 30, "sexmod:enchanted_quill", "jenny"));
        register("jenny", new Quest("jenny_cake", QuestType.FETCH,
            "I'm craving Cake. Get me 3 of them!",
            "minecraft:cake", 3, 20, "", "jenny"));

        // ALLIE: healer-themed quests
        register("allie", new Quest("allie_heal", QuestType.FETCH,
            "Bring me 5 Golden Apples — the village needs medicine.",
            "minecraft:golden_apple", 5, 30, "sexmod:healing_charm", "allie"));
        register("allie", new Quest("allie_herbs", QuestType.FETCH,
            "Can you gather 8 Mystic Herbs from the forest?",
            "sexmod:mystic_herb", 8, 20, "", "allie"));

        // ELLIE: combat quests
        register("ellie", new Quest("ellie_training", QuestType.FETCH,
            "Bring me 16 Rotten Flesh. Consider it... combat training.",
            "minecraft:rotten_flesh", 16, 25, "sexmod:copper_gear", "ellie"));
        register("ellie", new Quest("ellie_gear", QuestType.FETCH,
            "A warrior needs proper weapons. 3 Iron Swords, NOW.",
            "minecraft:iron_sword", 3, 35, "", "ellie"));

        // BIA: mysterious quests
        register("bia", new Quest("bia_stars", QuestType.FETCH,
            "Collect 12 Ender Pearls for my... experiment.",
            "minecraft:ender_pearl", 12, 40, "sexmod:memory_crystal", "bia"));
        register("bia", new Quest("bia_ancient", QuestType.FETCH,
            "The Ancient Coins call to each other. Find 3 more.",
            "sexmod:ancient_coin", 3, 25, "", "bia"));

        // BEE: sweet quests
        register("bee", new Quest("bee_honey", QuestType.FETCH,
            "Bzz! Bring me 10 Honey Bottles! The hive is hungry!",
            "minecraft:honey_bottle", 10, 20, "", "bee"));
        register("bee", new Quest("bee_flowers", QuestType.FETCH,
            "I need 16 different flowers for my collection! Any kind!",
            "minecraft:poppy", 16, 30, "sexmod:golden_honeycomb", "bee"));

        // CAT: lazy quests
        register("cat", new Quest("cat_fish", QuestType.FETCH,
            "Nya... bring me 5 Cooked Salmon. I'm too lazy to fish.",
            "minecraft:cooked_salmon", 5, 20, "", "cat"));
        register("cat", new Quest("cat_wool", QuestType.FETCH,
            "My bed needs more wool. 8 White Wool, human.",
            "minecraft:white_wool", 8, 15, "sexmod:teddy_bear", "cat"));

        // GOBLIN: scavenger quests
        register("goblin", new Quest("goblin_scrap", QuestType.FETCH,
            "Scrap metal. 8 Iron Ingots. Now.",
            "minecraft:iron_ingot", 8, 20, "", "goblin"));
        register("goblin", new Quest("goblin_treasure", QuestType.FETCH,
            "Dungeon loot. 24 Bones. Don't ask why.",
            "minecraft:bone", 24, 30, "sexmod:ancient_coin", "goblin"));

        // KOBOLD: shiny quests
        register("kobold", new Quest("kobold_shiny", QuestType.FETCH,
            "Kobold needs 5 Diamonds! The hoard must grow!",
            "minecraft:diamond", 5, 35, "sexmod:diamond_ring", "kobold"));
        register("kobold", new Quest("kobold_torch", QuestType.FETCH,
            "Cave too dark! Bring 16 Torches please!",
            "minecraft:torch", 16, 15, "", "kobold"));

        // SLIME: curious quests
        register("slime", new Quest("slime_slime", QuestType.FETCH,
            "Blub! I want to meet my cousins! 8 Slime Balls!",
            "minecraft:slime_ball", 8, 20, "", "slime"));
        register("slime", new Quest("slime_glass", QuestType.FETCH,
            "What's this transparent solid? Bring 10 Glass!",
            "minecraft:glass", 10, 25, "sexmod:crystal_slime", "slime"));
    }

    private static void register(String girl, Quest quest) {
        QUESTS_BY_GIRL.computeIfAbsent(girl, k -> new ArrayList<>()).add(quest);
    }

    /** Pick a random uncompleted quest for this girl */
    public Quest getAvailableQuest(String girlName) {
        List<Quest> all = QUESTS_BY_GIRL.getOrDefault(girlName, List.of());
        List<Quest> available = all.stream()
            .filter(q -> !completedQuests.contains(q.id()))
            .toList();
        if (available.isEmpty()) return null;
        return available.get(RAND.nextInt(available.size()));
    }

    public void startQuest(Quest quest) {
        this.activeQuestId = quest.id();
        this.questProgress = 0;
    }

    public Quest getActiveQuest() {
        if (activeQuestId.isEmpty()) return null;
        for (List<Quest> list : QUESTS_BY_GIRL.values()) {
            for (Quest q : list) {
                if (q.id().equals(activeQuestId)) return q;
            }
        }
        return null;
    }

    public boolean hasActiveQuest() { return !activeQuestId.isEmpty(); }

    public int getProgress() { return questProgress; }
    public int getTarget() {
        Quest q = getActiveQuest();
        return q != null ? q.targetCount() : 0;
    }

    /** Add progress, returns true if quest completed */
    public boolean addProgress(String itemName, int amount) {
        Quest q = getActiveQuest();
        if (q == null || q.type() != QuestType.FETCH) return false;
        if (itemName.equals(q.targetItem())) {
            questProgress += amount;
            if (questProgress >= q.targetCount()) {
                completeQuest();
                return true;
            }
        }
        return false;
    }

    private void completeQuest() {
        Quest q = getActiveQuest();
        if (q != null) {
            completedQuests.add(q.id());
        }
        activeQuestId = "";
        questProgress = 0;
    }

    /** Get affection reward for completing current quest */
    public int getCompletionReward() {
        Quest q = getActiveQuest();
        return q != null ? q.rewardAffection() : 0;
    }

    /** Get item reward for completing current quest */
    public String getRewardItem() {
        Quest q = getActiveQuest();
        return q != null ? q.rewardItem() : "";
    }

    // ── NBT ──
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("ActiveQuestId", activeQuestId);
        tag.putInt("QuestProgress", questProgress);
        ListTag completed = new ListTag();
        for (String id : completedQuests) completed.add(StringTag.valueOf(id));
        tag.put("CompletedQuests", completed);
        return tag;
    }

    public void fromNBT(CompoundTag tag) {
        activeQuestId = tag.getString("ActiveQuestId");
        questProgress = tag.getInt("QuestProgress");
        completedQuests.clear();
        ListTag list = tag.getList("CompletedQuests", 8);
        for (int i = 0; i < list.size(); i++) {
            completedQuests.add(list.getString(i));
        }
    }
}
