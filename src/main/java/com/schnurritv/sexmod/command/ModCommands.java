package com.schnurritv.sexmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.SexModConfig;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

/**
 * Debug commands for development and testing.
 * /affection get|set|add [value] — manipulate affection of nearest girl
 */
public class ModCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("affection")
                .requires(src -> src.hasPermission(2)) // op level 2+
                .then(Commands.literal("get")
                    .executes(ctx -> getAffection(ctx.getSource())))
                .then(Commands.literal("set")
                    .then(Commands.argument("value", IntegerArgumentType.integer(0))
                        .executes(ctx -> setAffection(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "value")))))
                .then(Commands.literal("add")
                    .then(Commands.argument("value", IntegerArgumentType.integer(-100, 100))
                        .executes(ctx -> addAffection(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "value")))))
        );

        dispatcher.register(
            Commands.literal("sexmod")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("reload")
                    .executes(ctx -> reloadConfig(ctx.getSource())))
                .then(Commands.literal("girls")
                    .executes(ctx -> listGirls(ctx.getSource())))
                .then(Commands.literal("quest")
                    .then(Commands.literal("reset")
                        .executes(ctx -> resetQuest(ctx.getSource()))))
        );
    }

    private static BaseGirlEntity findNearestGirl(CommandSourceStack src) {
        if (!(src.getEntity() instanceof ServerPlayer player)) {
            src.sendFailure(Component.literal("This command must be run by a player."));
            return null;
        }
        AABB box = player.getBoundingBox().inflate(10);
        return player.level().getEntitiesOfClass(BaseGirlEntity.class, box).stream()
            .min((a, b) -> Double.compare(
                a.distanceToSqr(player),
                b.distanceToSqr(player)))
            .orElse(null);
    }

    private static int getAffection(CommandSourceStack src) {
        BaseGirlEntity girl = findNearestGirl(src);
        if (girl == null) {
            src.sendFailure(Component.literal("No girl found within 10 blocks."));
            return 0;
        }
        int aff = girl.getAffection();
        int max = SexModConfig.AFFECTION_MAX.get();
        src.sendSuccess(() -> Component.literal(
            "§d" + girl.getGirlName() + " §7affection: §a" + aff + "§7/" + max
            + " §7(" + girl.getAffectionData().getLevel().label + ")"), false);
        return aff;
    }

    private static int setAffection(CommandSourceStack src, int value) {
        BaseGirlEntity girl = findNearestGirl(src);
        if (girl == null) {
            src.sendFailure(Component.literal("No girl found within 10 blocks."));
            return 0;
        }
        int max = SexModConfig.AFFECTION_MAX.get();
        int clamped = Math.min(max, Math.max(0, value));
        int old = girl.getAffection();
        girl.getAffectionData().addAffection(clamped - old, max);
        // Sync to client
        girl.getEntityData().set(com.schnurritv.sexmod.entity.BaseGirlEntity.AFFECTION_VALUE, clamped);
        src.sendSuccess(() -> Component.literal(
            "§d" + girl.getGirlName() + " §7affection set to: §a" + clamped + "§7 (was " + old + ")"), true);
        return clamped;
    }

    private static int addAffection(CommandSourceStack src, int delta) {
        BaseGirlEntity girl = findNearestGirl(src);
        if (girl == null) {
            src.sendFailure(Component.literal("No girl found within 10 blocks."));
            return 0;
        }
        int max = SexModConfig.AFFECTION_MAX.get();
        int old = girl.getAffection();
        boolean changed = girl.getAffectionData().addAffection(delta, max);
        int current = girl.getAffection();
        if (changed) {
            girl.getEntityData().set(com.schnurritv.sexmod.entity.BaseGirlEntity.AFFECTION_VALUE, current);
        }
        src.sendSuccess(() -> Component.literal(
            "§d" + girl.getGirlName() + " §7affection: §a" + current + " §7(" 
            + (delta >= 0 ? "§a+" + delta : "§c" + delta) + "§7, was " + old + ")"), true);
        return current;
    }

    private static int reloadConfig(CommandSourceStack src) {
        src.sendSuccess(() -> Component.literal("§eConfig reloaded. Note: some values require world restart."), false);
        return 1;
    }

    private static int listGirls(CommandSourceStack src) {
        if (!(src.getEntity() instanceof ServerPlayer player)) return 0;
        var girls = player.level().getEntitiesOfClass(BaseGirlEntity.class, 
            player.getBoundingBox().inflate(50));
        if (girls.isEmpty()) {
            src.sendSuccess(() -> Component.literal("§7No girls within 50 blocks."), false);
            return 0;
        }
        src.sendSuccess(() -> Component.literal("§d=== Girls within 50 blocks ==="), false);
        for (BaseGirlEntity g : girls) {
            int dist = (int) g.distanceTo(player);
            src.sendSuccess(() -> Component.literal(
                "  §d" + g.getGirlName() + " §7[" + dist + "m] §a❤" + g.getAffection() 
                + " §7(" + g.getAffectionData().getLevel().label + ")"), false);
        }
        return girls.size();
    }

    private static int resetQuest(CommandSourceStack src) {
        BaseGirlEntity girl = findNearestGirl(src);
        if (girl == null) {
            src.sendFailure(Component.literal("No girl found within 10 blocks."));
            return 0;
        }
        girl.resetQuest();
        src.sendSuccess(() -> Component.literal(
            "§d" + girl.getGirlName() + "§7's quest has been reset."), true);
        return 1;
    }
}
