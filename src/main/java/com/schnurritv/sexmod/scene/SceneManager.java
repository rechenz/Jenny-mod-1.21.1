package com.schnurritv.sexmod.scene;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import com.schnurritv.sexmod.entity.SexEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class SceneManager {

    private static BlockPos getBedFoot(BaseGirlEntity girl) {
        Level level = girl.level();
        BlockPos pos = girl.blockPosition();
        for (BlockPos p : BlockPos.betweenClosed(pos.offset(-5, -2, -5), pos.offset(5, 2, 5))) {
            net.minecraft.world.level.block.state.BlockState state = level.getBlockState(p);
            if (state.getBlock() instanceof BedBlock) {
                if (state.getValue(BedBlock.PART) == net.minecraft.world.level.block.state.properties.BedPart.HEAD) {
                    return p.relative(state.getValue(BedBlock.FACING).getOpposite());
                }
                return p;
            }
        }
        return null;
    }

    private static void preparePlayerForScene(BaseGirlEntity girl, Player player, BlockPos bedPos) {
        double x = bedPos.getX() + 0.5;
        double y = bedPos.getY() + 0.5625; // Bed top
        double z = bedPos.getZ() + 0.5;

        net.minecraft.world.level.block.state.BlockState state = girl.level().getBlockState(bedPos);
        float rot = girl.getYRot();
        if (state.getBlock() instanceof BedBlock) {
            rot = state.getValue(BedBlock.FACING).toYRot();
        }
        
        // Girl on bed
        girl.teleportTo(x, y, z);
        girl.setYRot(rot);
        girl.setYBodyRot(rot);
        
        // Player camera: offset backward from bed foot so camera isn't inside geometry
        // Bed facing = the direction the FOOT faces toward. We want player "behind" the foot.
        Direction bedFacing = Direction.fromYRot(rot);
        // Offset player ~1 block behind foot + raise to eye height
        double px = x - bedFacing.getStepX() * 1.2;
        double py = y + 1.0; // eye height above bed
        double pz = z - bedFacing.getStepZ() * 1.2;
        player.teleportTo(px, py, pz);
        player.setYRot(rot + 180); // face toward bed
        player.setXRot(0f);
        
        girl.getEntityData().set(SexEntity.IS_LOCKED, true);
        girl.getEntityData().set(SexEntity.PARTNER_UUID, player.getUUID().toString());
        girl.getEntityData().set(SexEntity.TARGET_ROTATION, rot);
        girl.getEntityData().set(SexEntity.TARGET_POS, x + "|" + (y-0.1) + "|" + z);
        girl.getEntityData().set(SexEntity.CLOTHING_STATE, 1);
    }

    public static void startMissionary(BaseGirlEntity girl, Player player) {
        if (girl.level().isClientSide) return;

        BlockPos bedPos;
        if (girl.requiresBedForMissionary()) {
            bedPos = getBedFoot(girl);
            if (bedPos == null) {
                player.displayClientMessage(Component.literal("This action requires a bed nearby!"), true);
                return;
            }
        } else {
            bedPos = girl.blockPosition();
        }

        preparePlayerForScene(girl, player, bedPos);
        girl.setSexModAnimation(SexModAnimation.MISSIONARY_START);
        girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.MISSIONARY_SLOW.name());
        girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
    }

    public static void startBlowjob(BaseGirlEntity girl, Player player) {
        if (girl.level().isClientSide) return;
        preparePlayerForScene(girl, player, girl.blockPosition());
        girl.setSexModAnimation(SexModAnimation.BLOWJOBINTRO);
        girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.BLOWJOBSUCK.name());
        girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
    }

    public static void startBoobjob(BaseGirlEntity girl, Player player) {
        if (girl.level().isClientSide) return;
        preparePlayerForScene(girl, player, girl.blockPosition());
        girl.setSexModAnimation(SexModAnimation.PAIZURI_START);
        girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.PAIZURI_SLOW.name());
        girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
    }

    public static void startDoggy(BaseGirlEntity girl, Player player) {
        if (girl.level().isClientSide) return;
        
        BlockPos bedPos;
        if (girl.requiresBedForDoggy()) {
            bedPos = getBedFoot(girl);
            if (bedPos == null) {
                player.displayClientMessage(Component.literal("This action requires a bed nearby!"), true);
                return;
            }
        } else {
            bedPos = girl.blockPosition();
        }

        preparePlayerForScene(girl, player, bedPos);
        girl.setSexModAnimation(SexModAnimation.DOGGYGOONBED);
        girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.DOGGYSTART.name());
        girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
    }

    public static void stopScene(BaseGirlEntity girl) {
        if (girl.level().isClientSide) return;
        girl.getEntityData().set(SexEntity.IS_LOCKED, false);
        girl.getEntityData().set(SexEntity.PARTNER_UUID, "null");
        girl.setSexModAnimation(SexModAnimation.NULL);
        girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, "null");
        girl.getEntityData().set(SexEntity.CLOTHING_STATE, 0);
    }
}
