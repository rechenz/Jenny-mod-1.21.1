package com.schnurritv.sexmod.relationship;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import java.util.*;

/**
 * Per-character quest system with differentiated quest types for each character.
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
        // ================================================================
        // JENNY: "Gamer Challenge" — fetch rare tech items
        // ================================================================
        register("jenny", new Quest("jenny_gear", QuestType.FETCH,
            "Gamer Challenge: Bring me a Copper Gear! I want to build a better controller!",
            "sexmod:copper_gear", 1, 25, "sexmod:red_rose", "jenny"));
        register("jenny", new Quest("jenny_redstone", QuestType.FETCH,
            "Gamer Challenge: I need 10 Redstone for my custom keyboard!",
            "minecraft:redstone", 10, 30, "sexmod:enchanted_quill", "jenny"));
        register("jenny", new Quest("jenny_comparator", QuestType.FETCH,
            "Gamer Challenge: Bring me a Redstone Comparator — I'm building a scoreboard!",
            "minecraft:comparator", 1, 20, "", "jenny"));

        // ================================================================
        // ELLIE: "Enchantment Hunt" — fetch enchanted books / XP bottles
        // ================================================================
        register("ellie", new Quest("ellie_xp", QuestType.FETCH,
            "Enchantment Hunt: Bring me 8 Bottles o' Enchanting! Mommy needs power!",
            "minecraft:experience_bottle", 8, 35, "sexmod:enchanted_quill", "ellie"));
        register("ellie", new Quest("ellie_book", QuestType.FETCH,
            "Enchantment Hunt: I need an Enchanted Book! Any enchantment will do.",
            "minecraft:enchanted_book", 1, 30, "", "ellie"));
        register("ellie", new Quest("ellie_lapis", QuestType.FETCH,
            "Enchantment Hunt: Bring me 16 Lapis Lazuli. Fuel the magic!",
            "minecraft:lapis_lazuli", 16, 25, "", "ellie"));

        // ================================================================
        // ALLIE: "Herb Gathering" — fetch flowers and potion ingredients
        // ================================================================
        register("allie", new Quest("allie_flowers", QuestType.FETCH,
            "Herb Gathering: Bring me 10 Poppies! They're good for healing salves.",
            "minecraft:poppy", 10, 20, "sexmod:moonlight_lily", "allie"));
        register("allie", new Quest("allie_dandelion", QuestType.FETCH,
            "Herb Gathering: I need 8 Dandelions for a calming tea.",
            "minecraft:dandelion", 8, 25, "", "allie"));
        register("allie", new Quest("allie_golden_apple", QuestType.FETCH,
            "Herb Gathering: Can you find me a Golden Apple? Medicine for emergencies.",
            "minecraft:golden_apple", 1, 35, "sexmod:healing_charm", "allie"));
        register("allie", new Quest("allie_melon", QuestType.FETCH,
            "Herb Gathering: Bring me 6 Melon Slices! They're hydrating and sweet.",
            "minecraft:melon_slice", 6, 20, "", "allie"));

        // ================================================================
        // BIA: "Relic Hunt" — fetch ancient/rare items
        // ================================================================
        register("bia", new Quest("bia_coins", QuestType.FETCH,
            "Relic Hunt: Bring me an Ancient Coin! The relics call to each other!",
            "sexmod:ancient_coin", 1, 30, "sexmod:ancient_coin", "bia"));
        register("bia", new Quest("bia_echo", QuestType.FETCH,
            "Relic Hunt: Find me 3 Echo Shards! They whisper ancient secrets.",
            "minecraft:echo_shard", 3, 35, "", "bia"));
        register("bia", new Quest("bia_amethyst", QuestType.FETCH,
            "Relic Hunt: Bring me 6 Amethyst Shards! Their glow is hypnotic.",
            "minecraft:amethyst_shard", 6, 25, "", "bia"));

        // ================================================================
        // BEE: "Pollen Run" — fetch honey/flowers/bonemeal
        // ================================================================
        register("bee", new Quest("bee_honeycomb", QuestType.FETCH,
            "Pollen Run: Bring me 8 Honeycombs! The hive will love these!",
            "minecraft:honeycomb", 8, 25, "sexmod:golden_honeycomb", "bee"));
        register("bee", new Quest("bee_flowers", QuestType.FETCH,
            "Pollen Run: I need 12 Sunflowers! They're the tallest and prettiest!",
            "minecraft:sunflower", 12, 30, "", "bee"));
        register("bee", new Quest("bee_bonemeal", QuestType.FETCH,
            "Pollen Run: Bring me 10 Bone Meal! Gotta make more flowers grow!",
            "minecraft:bone_meal", 10, 20, "", "bee"));

        // ================================================================
        // CAT/LUNA: "Fish Feast" — fetch different fish types
        // ================================================================
        register("cat", new Quest("cat_cod", QuestType.FETCH,
            "Fish Feast: Bring me 5 Raw Cod! I'm starving! Nya~",
            "minecraft:cod", 5, 20, "", "cat"));
        register("cat", new Quest("cat_salmon", QuestType.FETCH,
            "Fish Feast: I need 4 Salmon! The pink ones are my favorite!",
            "minecraft:salmon", 4, 25, "", "cat"));
        register("cat", new Quest("cat_tropical", QuestType.FETCH,
            "Fish Feast: Bring me a Tropical Fish! They're so colorful and tasty!",
            "minecraft:tropical_fish", 1, 30, "sexmod:silver_bell", "cat"));

        // ================================================================
        // GOBLIN: "Treasure Snatch" — fetch valuables
        // ================================================================
        register("goblin", new Quest("goblin_gold", QuestType.FETCH,
            "Treasure Snatch: Bring me 4 Gold Ingots! My hoard needs more shine!",
            "minecraft:gold_ingot", 4, 30, "", "goblin"));
        register("goblin", new Quest("goblin_emerald", QuestType.FETCH,
            "Treasure Snatch: I need 3 Emeralds! Green is my lucky color!",
            "minecraft:emerald", 3, 35, "", "goblin"));
        register("goblin", new Quest("goblin_diamond", QuestType.FETCH,
            "Treasure Snatch: ONE Diamond! The biggest, shiniest one you can find!",
            "minecraft:diamond", 1, 40, "sexmod:ancient_coin", "goblin"));

        // ================================================================
        // KOBOLD: "Shiny Collection" — fetch shiny things
        // ================================================================
        register("kobold", new Quest("kobold_nuggets", QuestType.FETCH,
            "Shiny Collection: Kobold needs 16 Gold Nuggets! They're so sparkly!",
            "minecraft:gold_nugget", 16, 20, "", "kobold"));
        register("kobold", new Quest("kobold_glowstone", QuestType.FETCH,
            "Shiny Collection: Bring 8 Glowstone Dust! Makes the cave pretty!",
            "minecraft:glowstone_dust", 8, 25, "", "kobold"));
        register("kobold", new Quest("kobold_amethyst", QuestType.FETCH,
            "Shiny Collection: Kobold wants 4 Amethyst Shards! Purple and sparkly!",
            "minecraft:amethyst_shard", 4, 30, "sexmod:diamond_ring", "kobold"));

        // ================================================================
        // SLIME: "Absorption Test" — fetch organic items
        // ================================================================
        register("slime", new Quest("slime_balls", QuestType.FETCH,
            "Absorption Test: Bring me 6 Slime Balls! For science! For slime!",
            "minecraft:slime_ball", 6, 25, "sexmod:crystal_slime", "slime"));
        register("slime", new Quest("slime_rotten", QuestType.FETCH,
            "Absorption Test: I need 12 Rotten Flesh. What? It's for... experiments.",
            "minecraft:rotten_flesh", 12, 20, "", "slime"));
        register("slime", new Quest("slime_bones", QuestType.FETCH,
            "Absorption Test: Bring me 8 Bones! I want to see if I can absorb minerals!",
            "minecraft:bone", 8, 25, "", "slime"));

        // ================================================================
        // GALATH: "Void Offering" — fetch ender pearls, obsidian, nether star
        // ================================================================
        register("galath", new Quest("galath_pearls", QuestType.FETCH,
            "Void Offering: Bring me 8 Ender Pearls. The void hungers.",
            "minecraft:ender_pearl", 8, 35, "", "galath"));
        register("galath", new Quest("galath_obsidian", QuestType.FETCH,
            "Void Offering: I require 10 Obsidian. To contain the darkness.",
            "minecraft:obsidian", 10, 30, "sexmod:dragon_scale", "galath"));
        register("galath", new Quest("galath_star", QuestType.FETCH,
            "Void Offering: Bring me a Nether Star. True power demands sacrifice.",
            "minecraft:nether_star", 1, 50, "sexmod:galath_coin", "galath"));

        // ================================================================
        // MANGLELIE: "Memory Fragments" — fetch echo shards, amethyst, XP bottles
        // ================================================================
        register("manglelie", new Quest("manglelie_echo", QuestType.FETCH,
            "Memory Fragments: Bring me 4 Echo Shards. I need to remember...",
            "minecraft:echo_shard", 4, 30, "sexmod:memory_crystal", "manglelie"));
        register("manglelie", new Quest("manglelie_amethyst", QuestType.FETCH,
            "Memory Fragments: 8 Amethyst Shards. They spark with forgotten light.",
            "minecraft:amethyst_shard", 8, 25, "", "manglelie"));
        register("manglelie", new Quest("manglelie_xp", QuestType.FETCH,
            "Memory Fragments: Bring me 6 Bottles o' Enchanting. Knowledge heals the past.",
            "minecraft:experience_bottle", 6, 35, "", "manglelie"));

        // ================================================================
        // LUCY: "Comfort Food" — fetch food items
        // ================================================================
        register("lucy", new Quest("lucy_cake", QuestType.FETCH,
            "Comfort Food: Bring me 2 Cakes! Everyone deserves dessert!",
            "minecraft:cake", 2, 25, "", "lucy"));
        register("lucy", new Quest("lucy_cookies", QuestType.FETCH,
            "Comfort Food: I need 16 Cookies! Perfect for a cozy afternoon!",
            "minecraft:cookie", 16, 20, "", "lucy"));
        register("lucy", new Quest("lucy_bread", QuestType.FETCH,
            "Comfort Food: Bring me 6 Bread. Simple, warm, and satisfying.",
            "minecraft:bread", 6, 20, "", "lucy"));
        register("lucy", new Quest("lucy_gapple", QuestType.FETCH,
            "Comfort Food: A Golden Apple! For when you need a little extra care.",
            "minecraft:golden_apple", 1, 35, "sexmod:healing_charm", "lucy"));

        // ================================================================
        // MIKA: "Lucky Charms" — fetch emeralds, rabbit foot, gold nuggets
        // ================================================================
        register("mika", new Quest("mika_emeralds", QuestType.FETCH,
            "Lucky Charms: Bring me 5 Emeralds! They bring good fortune!",
            "minecraft:emerald", 5, 30, "", "mika"));
        register("mika", new Quest("mika_rabbit", QuestType.FETCH,
            "Lucky Charms: A Rabbit's Foot! The luckiest charm of all!",
            "minecraft:rabbit_foot", 1, 35, "sexmod:diamond_ring", "mika"));
        register("mika", new Quest("mika_gold_nuggets", QuestType.FETCH,
            "Lucky Charms: Bring me 12 Gold Nuggets! Tiny treasures all around!",
            "minecraft:gold_nugget", 12, 20, "", "mika"));

        // ================================================================
        // MOMO: "Research Materials" — fetch books, paper, ink sacs, feathers
        // ================================================================
        register("momo", new Quest("momo_books", QuestType.FETCH,
            "Research Materials: Bring me 3 Books! I have so much to document!",
            "minecraft:book", 3, 25, "", "momo"));
        register("momo", new Quest("momo_paper", QuestType.FETCH,
            "Research Materials: I need 12 Paper! Notes pile up faster than expected!",
            "minecraft:paper", 12, 20, "", "momo"));
        register("momo", new Quest("momo_ink", QuestType.FETCH,
            "Research Materials: Bring me 4 Ink Sacs and a Feather! Time to write!",
            "minecraft:ink_sac", 4, 25, "sexmod:enchanted_quill", "momo"));
        register("momo", new Quest("momo_feather", QuestType.FETCH,
            "Research Materials: I need 2 Feathers. For the quill, obviously.",
            "minecraft:feather", 2, 20, "", "momo"));
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
