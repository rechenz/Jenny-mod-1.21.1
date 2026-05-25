package com.schnurritv.sexmod.entity.goblin;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.SexModConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import java.util.List;

public class GoblinEntity extends BaseGirlEntity {
    public GoblinEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Override public String getGirlName() { return "goblin"; }

    private int pickupCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (!SexModConfig.GIRLS_PICKUP_ITEMS.get()) return;
        if (this.getEntityData().get(IS_LOCKED)) return;
        if (pickupCooldown > 0) { pickupCooldown--; return; }
        pickupCooldown = 60;

        AABB scanArea = this.getBoundingBox().inflate(3);
        List<ItemEntity> items = this.level().getEntitiesOfClass(ItemEntity.class, scanArea);
        for (ItemEntity item : items) {
            if (item.isAlive() && !item.getItem().isEmpty()) {
                ItemStack stack = item.getItem().copy();
                for (int i = 0; i < 7; i++) {
                    ItemStack existing = inventory.getStackInSlot(i);
                    if (existing.isEmpty()) {
                        inventory.setStackInSlot(i, stack);
                        item.discard();
                        break;
                    } else if (ItemStack.isSameItemSameComponents(existing, stack)) {
                        int maxStack = existing.getMaxStackSize();
                        if (existing.getCount() < maxStack) {
                            int toAdd = Math.min(stack.getCount(), maxStack - existing.getCount());
                            existing.grow(toAdd);
                            stack.shrink(toAdd);
                            if (stack.isEmpty()) { item.discard(); break; }
                        }
                    }
                }
            }
        }
    }
}
