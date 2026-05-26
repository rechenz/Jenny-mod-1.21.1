package com.schnurritv.sexmod.entity.kobold;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/**
 * Kobold egg item. Place on a block to spawn a KoboldEggEntity.
 * Stores tribeID and tribeColor in NBT.
 */
public class KoboldEggItem extends Item {

    public static final String TRIBE_ID_TAG = "tribeID";
    public static final String TRIBE_COLOR_TAG = "tribeColor";

    public KoboldEggItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) return InteractionResult.SUCCESS;

        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        // Read NBT from components (1.21.1+ data component system)
        String tribeColorStr = EyeAndKoboldColor.PURPLE.name();
        CompoundTag tag = null;
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null && !customData.isEmpty()) {
            tag = customData.copyTag();
        }

        // Create egg entity
        KoboldEggEntity egg = new KoboldEggEntity(KoboldEntityRegistry.KOBOLD_EGG.get(), level);
        egg.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

        // Set color from item data
        if (tag != null && tag.contains(TRIBE_COLOR_TAG)) {
            tribeColorStr = tag.getString(TRIBE_COLOR_TAG);
        }
        egg.getEntityData().set(KoboldEggEntity.COLOR, tribeColorStr);

        // Set tribe ID from item
        if (tag != null && tag.hasUUID(TRIBE_ID_TAG)) {
            egg.tribeId = tag.getUUID(TRIBE_ID_TAG);
        }

        level.addFreshEntity(egg);

        if (!context.getPlayer().isCreative()) {
            stack.shrink(1);
        }

        return InteractionResult.CONSUME;
    }
}
