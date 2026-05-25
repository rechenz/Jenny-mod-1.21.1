package com.schnurritv.sexmod.entity;

import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.SexModConfig;
import com.schnurritv.sexmod.item.GiftItem;
import com.schnurritv.sexmod.relationship.AffectionData;
import com.schnurritv.sexmod.relationship.DialogueDB;
import com.schnurritv.sexmod.relationship.QuestManager;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.schnurritv.sexmod.client.gui.InteractionScreen;
import com.schnurritv.sexmod.entity.ai.SexModFollowGoal;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseGirlEntity extends SexEntity {

    protected final ItemStackHandler inventory = new ItemStackHandler(7) {
        @Override
        protected void onContentsChanged(int slot) {
            updateSyncedInventory();
        }
    };

    private final LazyOptional<IItemHandler> inventoryHandler = LazyOptional.of(() -> inventory);

    // ── Synced data for inventory display ──
    public static final EntityDataAccessor<ItemStack> ITEM_0 = SynchedEntityData.defineId(BaseGirlEntity.class, EntityDataSerializers.ITEM_STACK);
    public static final EntityDataAccessor<ItemStack> ITEM_1 = SynchedEntityData.defineId(BaseGirlEntity.class, EntityDataSerializers.ITEM_STACK);
    public static final EntityDataAccessor<ItemStack> ITEM_2 = SynchedEntityData.defineId(BaseGirlEntity.class, EntityDataSerializers.ITEM_STACK);
    public static final EntityDataAccessor<ItemStack> ITEM_3 = SynchedEntityData.defineId(BaseGirlEntity.class, EntityDataSerializers.ITEM_STACK);
    public static final EntityDataAccessor<ItemStack> ITEM_4 = SynchedEntityData.defineId(BaseGirlEntity.class, EntityDataSerializers.ITEM_STACK);
    public static final EntityDataAccessor<ItemStack> ITEM_5 = SynchedEntityData.defineId(BaseGirlEntity.class, EntityDataSerializers.ITEM_STACK);

    // ── Affection sync ──
    public static final EntityDataAccessor<Integer> AFFECTION_VALUE = SynchedEntityData.defineId(BaseGirlEntity.class, EntityDataSerializers.INT);

    // ── Affection data (server-authoritative, synced to client) ──
    private final AffectionData affectionData = new AffectionData();
    // ── Quest system ──
    private final QuestManager questManager = new QuestManager();

    protected BaseGirlEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new com.schnurritv.sexmod.entity.ai.SexModMoveToTargetGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new SexModFollowGoal(this, 1.25D, 10.0F, 2.0F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ITEM_0, ItemStack.EMPTY);
        builder.define(ITEM_1, ItemStack.EMPTY);
        builder.define(ITEM_2, ItemStack.EMPTY);
        builder.define(ITEM_3, ItemStack.EMPTY);
        builder.define(ITEM_4, ItemStack.EMPTY);
        builder.define(ITEM_5, ItemStack.EMPTY);
        builder.define(AFFECTION_VALUE, 0);
    }

    // ── NBT save/load ──
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.put("Inventory", inventory.serializeNBT(this.registryAccess()));
        compound.put("AffectionData", affectionData.toNBT());
        compound.put("QuestData", questManager.toNBT());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Inventory")) {
            inventory.deserializeNBT(this.registryAccess(), compound.getCompound("Inventory"));
        }
        if (compound.contains("AffectionData")) {
            affectionData.fromNBT(compound.getCompound("AffectionData"));
            syncAffection();
        }
        if (compound.contains("QuestData")) {
            questManager.fromNBT(compound.getCompound("QuestData"));
        }
    }

    protected void updateSyncedInventory() {
        if (this.getEntityData().get(SexEntity.IS_LOCKED)) {
            this.entityData.set(ITEM_0, ItemStack.EMPTY);
            this.entityData.set(ITEM_1, ItemStack.EMPTY);
            this.entityData.set(ITEM_2, ItemStack.EMPTY);
            this.entityData.set(ITEM_3, ItemStack.EMPTY);
            this.entityData.set(ITEM_4, ItemStack.EMPTY);
            this.entityData.set(ITEM_5, ItemStack.EMPTY);
            return;
        }
        this.entityData.set(ITEM_0, inventory.getStackInSlot(0));
        this.entityData.set(ITEM_1, inventory.getStackInSlot(1));
        this.entityData.set(ITEM_2, inventory.getStackInSlot(2));
        this.entityData.set(ITEM_3, inventory.getStackInSlot(3));
        this.entityData.set(ITEM_4, inventory.getStackInSlot(4));
        this.entityData.set(ITEM_5, inventory.getStackInSlot(5));
    }

    private void syncAffection() {
        if (!this.level().isClientSide) {
            this.entityData.set(AFFECTION_VALUE, affectionData.getAffection());
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (IS_LOCKED.equals(key)) {
            updateSyncedInventory();
        }
    }

    // ── Affection getters ──
    public AffectionData getAffectionData() { return affectionData; }
    public int getAffection() { return this.entityData.get(AFFECTION_VALUE); }
    // ── Quest accessor ──
    public QuestManager getQuestManager() { return questManager; }

    // ── Capability ──
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return inventoryHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    // ── Interaction (gift giving + menu) ──
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);

        // Check if holding a gift item
        if (isGiftItem(held)) {
            if (this.level().isClientSide) {
                return InteractionResult.SUCCESS;
            }
            return handleGift(player, held, hand);
        }

        // Quest turn-in: if has active quest, try to complete it
        if (questManager.hasActiveQuest() && !(held.getItem() instanceof GiftItem)) {
            com.schnurritv.sexmod.relationship.QuestManager.Quest q = questManager.getActiveQuest();
            if (q != null && q.type() == QuestManager.QuestType.FETCH) {
                String itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(held.getItem()).toString();
                if (itemId.equals(q.targetItem())) {
                    int turnIn = Math.min(held.getCount(), q.targetCount() - questManager.getProgress());
                    if (turnIn > 0) {
                        questManager.addProgress(q.targetItem(), turnIn);
                        if (!player.isCreative()) held.shrink(turnIn);
                        if (!questManager.hasActiveQuest()) {
                            // Quest complete!
                            int reward = q.rewardAffection();
                            affectionData.addAffection(reward, SexModConfig.AFFECTION_MAX.get());
                            syncAffection();
                            player.displayClientMessage(Component.literal("<" + getGirlName() + "> " + 
                                DialogueDB.getRandom("quest_complete")), false);
                            player.displayClientMessage(Component.literal("❤ +" + reward + " Quest Reward!"), true);
                            if (!q.rewardItem().isEmpty()) {
                                net.minecraft.world.item.Item rewardItem = net.minecraft.core.registries.BuiltInRegistries.ITEM.get(
                                    net.minecraft.resources.ResourceLocation.parse(q.rewardItem()));
                                if (rewardItem != null && rewardItem != net.minecraft.world.item.Items.AIR) {
                                    player.getInventory().add(new ItemStack(rewardItem, 1));
                                    player.displayClientMessage(Component.literal("§6Received: " + rewardItem.getDescription().getString()), true);
                                }
                            }
                        } else {
                            player.displayClientMessage(Component.literal("<" + getGirlName() + "> " +
                                "Still need " + (questManager.getTarget() - questManager.getProgress()) + " more!"), false);
                        }
                        if (this.level().isClientSide) return InteractionResult.SUCCESS;
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        // Otherwise open interaction screen (client only)
        if (this.level().isClientSide) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                Minecraft.getInstance().setScreen(new InteractionScreen(this));
            });
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }

    private boolean isGiftItem(ItemStack stack) {
        return stack.getItem() instanceof GiftItem || stack.getItem() == Items.DIAMOND
            || stack.getItem() == Items.GOLD_INGOT || stack.getItem() == Items.EMERALD
            || stack.getItem() == Items.CAKE || stack.getItem() == Items.COOKIE
            || stack.getItem() == Items.POPPY || stack.getItem() == Items.DANDELION
            || stack.getItem() == Items.ROSE_BUSH || stack.getItem() == Items.SUNFLOWER
            || stack.getItem() == Items.NETHER_STAR || stack.getItem() == Items.ECHO_SHARD
            || stack.getItem() == Items.BOOK || stack.getItem() == Items.SLIME_BALL
            || stack.getItem() == Items.COD || stack.getItem() == Items.SALMON
            || stack.getItem() == Items.TROPICAL_FISH || stack.getItem() == Items.PUFFERFISH;
    }

    private InteractionResult handleGift(Player player, ItemStack held, InteractionHand hand) {
        long currentDay = this.level().getDayTime() / 24000;

        if (!affectionData.canGiveGift(currentDay, SexModConfig.DAILY_GIFT_LIMIT.get())) {
            player.displayClientMessage(Component.literal(
                "<" + getGirlName() + "> " + DialogueDB.getRandom("gift_limit_reached")), false);
            return InteractionResult.SUCCESS;
        }

        // Calculate affection gain
        int gain;
        if (held.getItem() instanceof GiftItem gift) {
            // Check if it's her favorite: +10 bonus
            if (isFavoriteGift(held)) gain = gift.getAffectionValue() + 10;
            else gain = gift.getAffectionValue();
        } else {
            // Vanilla gift fallback
            gain = switch (net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(held.getItem()).toString()) {
                case "minecraft:diamond"  -> 20;
                case "minecraft:emerald"  -> 12;
                case "minecraft:gold_ingot" -> 8;
                case "minecraft:nether_star" -> 30;
                case "minecraft:echo_shard" -> 15;
                case "minecraft:book" -> 10;
                case "minecraft:slime_ball" -> 10;
                case "minecraft:cod", "minecraft:salmon", "minecraft:tropical_fish", "minecraft:pufferfish" -> 8;
                case "minecraft:cake"     -> 6;
                case "minecraft:cookie"   -> 3;
                case "minecraft:poppy", "minecraft:dandelion", "minecraft:rose_bush", "minecraft:sunflower" -> 4;
                default         -> 2;
            };
        }

        // CAT: fish items give +50% extra affection
        if ("cat".equals(getGirlName().toLowerCase()) && fishItem(held)) {
            gain = (int)(gain * 1.5f);
        }

        // First gift bonus
        boolean firstGift = affectionData.getAffection() == 0;
        if (firstGift) gain += 10;

        affectionData.addAffection(gain, SexModConfig.AFFECTION_MAX.get());
        affectionData.recordGift(currentDay);
        if (affectionData.getOwnerUUID().isEmpty()) {
            affectionData.setOwner(player.getUUID().toString());
        }
        syncAffection();

        // Consume item
        if (!player.isCreative()) {
            held.shrink(1);
        }

        // Feedback
        String reaction = firstGift
            ? DialogueDB.getRandom("gift_first_ever")
            : DialogueDB.getGiftReaction(getGirlName(), gain);
        player.displayClientMessage(Component.literal("<" + getGirlName() + "> " + reaction), false);
        player.displayClientMessage(Component.literal("❤ +" + gain + " (Total: " + affectionData.getAffection() + ")"), true);

        // Spawn heart particles (client)
        if (SexModConfig.HEART_PARTICLES.get()) {
            this.level().broadcastEntityEvent(this, (byte) 7); // heart event
        }

        return InteractionResult.SUCCESS;
    }

    private boolean isFavoriteGift(ItemStack stack) {
        String name = getGirlName().toLowerCase();
        String itemName = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        return switch (name) {
            case "jenny"     -> itemName.contains("copper_gear");
            case "ellie"     -> itemName.contains("enchanted_quill");
            case "allie"     -> itemName.contains("moonlight_lily");
            case "bia"       -> itemName.contains("ancient_coin");
            case "bee"       -> itemName.contains("golden_honeycomb");
            case "cat"       -> fishItem(stack);
            case "goblin"    -> itemName.contains("gold_ingot");
            case "kobold"    -> itemName.contains("diamond");
            case "slime"     -> itemName.contains("slime_ball");
            case "galath"    -> itemName.contains("nether_star");
            case "manglelie" -> itemName.contains("echo_shard");
            case "lucy"      -> itemName.contains("cake");
            case "mika"      -> itemName.contains("emerald");
            case "momo"      -> itemName.contains("book");
            default          -> false;
        };
    }

    private static boolean fishItem(ItemStack stack) {
        net.minecraft.world.item.Item it = stack.getItem();
        return it == net.minecraft.world.item.Items.COD
            || it == net.minecraft.world.item.Items.SALMON
            || it == net.minecraft.world.item.Items.TROPICAL_FISH
            || it == net.minecraft.world.item.Items.PUFFERFISH
            || it == net.minecraft.world.item.Items.COOKED_COD
            || it == net.minecraft.world.item.Items.COOKED_SALMON;
    }

    // ── Scene unlocking ──
    public List<String> getAvailableActions() {
        List<String> actions = new ArrayList<>();
        actions.add("Follow");
        actions.add("Stay");

        if (!SexModConfig.SCENES_REQUIRE_AFFECTION.get()) {
            // If config disables affection gating, show all scenes
            addAllScenes(actions);
            actions.add("Stop");
            return actions;
        }

        int aff = affectionData.getAffection();

        if (aff >= SexModConfig.AFFECTION_SCENE_THRESHOLD_LOW.get()) {
            actions.add("Missionary");
            actions.add("Blowjob");
        }
        if (aff >= SexModConfig.AFFECTION_SCENE_THRESHOLD_HIGH.get()) {
            actions.add("Doggy");
            actions.add("Boobjob");
        }

        // Show locked scenes as greyed out
        if (aff < SexModConfig.AFFECTION_SCENE_THRESHOLD_LOW.get()) {
            actions.add("? Missionary");
            actions.add("? Blowjob");
        }
        if (aff < SexModConfig.AFFECTION_SCENE_THRESHOLD_HIGH.get()) {
            if (!actions.contains("? Doggy")) actions.add("? Doggy");
            if (!actions.contains("? Boobjob")) actions.add("? Boobjob");
        }

        actions.add("Stop");
        return actions;
    }

    private void addAllScenes(List<String> actions) {
        actions.add("Missionary");
        actions.add("Doggy");
        actions.add("Blowjob");
        actions.add("Boobjob");
    }

    public abstract String getGirlName();
    /** Override to limit which scene types appear in interaction UI.
     *  Bee/Cat override to a single unified scene. */
    public boolean supportsScene(String sceneName) { return true; }

    public boolean hasSingleUnifiedScene() { return false; }
    /** For single-scene chars: the unified scene starts with this animation */
    public String getUnifiedSceneStartLabel() { return ""; }

    /** Whether this character needs a bed for missionary scene. Override in subclasses. */
    public boolean requiresBedForMissionary() { return true; }
    /** Whether this character needs a bed for doggy scene. Override in subclasses. */
    public boolean requiresBedForDoggy() { return true; }

    /** Which standard scene types to show in the interaction UI. Override per-character. */
    public boolean showStandardMissionary() { return true; }
    public boolean showStandardBlowjob() { return true; }
    public boolean showStandardDoggy() { return true; }
    public boolean showStandardBoobjob() { return true; }

    @Override
    public String getModelName() {
        return getGirlName();
    }

    // ── Tick: apply affection decay + jealousy ──
    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            long currentDay = this.level().getDayTime() / 24000;
            affectionData.applyDecay(currentDay, SexModConfig.AFFECTION_DECAY_PER_DAY.get());

            // Jealousy check: if player has high affection with another nearby girl,
            // this girl gets jealous and loses affection periodically.
            if (affectionData.getAffection() >= 20 && !affectionData.getOwnerUUID().isEmpty()
                && this.tickCount % 600 == 0 && RAND.nextFloat() < 0.3f) {
                checkJealousy();
            }
        }
    }

    private static final java.util.Random RAND = new java.util.Random();
    private long lastJealousyMessage = 0;

    private void checkJealousy() {
        String myOwner = affectionData.getOwnerUUID();
        for (var entity : this.level().getEntitiesOfClass(BaseGirlEntity.class,
                this.getBoundingBox().inflate(16.0D))) {
            if (entity == this) continue;
            if (entity.getAffectionData().getAffection() >= 20
                && myOwner.equals(entity.getAffectionData().getOwnerUUID())) {
                // Jealousy triggered! Lose 1-3 affection
                int loss = 1 + RAND.nextInt(3);
                affectionData.addAffection(-loss, SexModConfig.AFFECTION_MAX.get());
                syncAffection();

                long now = this.level().getGameTime();
                if (now - lastJealousyMessage > 24000) {
                    lastJealousyMessage = now;
                    var owner = this.level().getPlayerByUUID(java.util.UUID.fromString(myOwner));
                    if (owner != null) {
                        owner.displayClientMessage(Component.literal(
                            "<" + getGirlName() + "> " + DialogueDB.getRandom("jealous_warning")), false);
                    }
                }
                break;
            }
        }
    }
}
