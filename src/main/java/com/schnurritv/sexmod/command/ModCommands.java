package com.schnurritv.sexmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.schnurritv.sexmod.Main;
import com.schnurritv.sexmod.SexModConfig;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("affection")
                .requires(src -> src.hasPermission(2))
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
                .then(Commands.literal("house")
                    .executes(ctx -> locateHouse(ctx.getSource())))
                .then(Commands.literal("teststructure")
                    .then(Commands.argument("name", com.mojang.brigadier.arguments.StringArgumentType.word())
                        .executes(ctx -> testStructure(ctx.getSource(),
                            com.mojang.brigadier.arguments.StringArgumentType.getString(ctx, "name")))))
        );
    }

    private static BaseGirlEntity findNearestGirl(CommandSourceStack src) {
        if (!(src.getEntity() instanceof ServerPlayer player)) {
            src.sendFailure(Component.literal("This command must be run by a player."));
            return null;
        }
        AABB box = player.getBoundingBox().inflate(10);
        return player.level().getEntitiesOfClass(BaseGirlEntity.class, box).stream()
            .min((a, b) -> Double.compare(a.distanceToSqr(player), b.distanceToSqr(player)))
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
            "\u00a7d" + girl.getGirlName() + " \u00a77affection: \u00a7a" + aff + "\u00a77/" + max
            + " \u00a77(" + girl.getAffectionData().getLevel().label + ")"), false);
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
        girl.getEntityData().set(BaseGirlEntity.AFFECTION_VALUE, clamped);
        src.sendSuccess(() -> Component.literal(
            "\u00a7d" + girl.getGirlName() + " \u00a77affection set to: \u00a7a" + clamped + "\u00a77 (was " + old + ")"), true);
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
            girl.getEntityData().set(BaseGirlEntity.AFFECTION_VALUE, current);
        }
        src.sendSuccess(() -> Component.literal(
            "\u00a7d" + girl.getGirlName() + " \u00a77affection: \u00a7a" + current + " \u00a7(" 
            + (delta >= 0 ? "\u00a7a+" + delta : "\u00a7c" + delta) + "\u00a77, was " + old + ")"), true);
        return current;
    }

    private static int reloadConfig(CommandSourceStack src) {
        src.sendSuccess(() -> Component.literal("\u00a7eConfig reloaded. Note: some values require world restart."), false);
        return 1;
    }

    private static int listGirls(CommandSourceStack src) {
        if (!(src.getEntity() instanceof ServerPlayer player)) return 0;
        var girls = player.level().getEntitiesOfClass(BaseGirlEntity.class, 
            player.getBoundingBox().inflate(50));
        if (girls.isEmpty()) {
            src.sendSuccess(() -> Component.literal("\u00a77No girls within 50 blocks."), false);
            return 0;
        }
        src.sendSuccess(() -> Component.literal("\u00a7d=== Girls within 50 blocks ==="), false);
        for (BaseGirlEntity g : girls) {
            int dist = (int) g.distanceTo(player);
            src.sendSuccess(() -> Component.literal(
                "  \u00a7d" + g.getGirlName() + " \u00a77[" + dist + "m] \u00a7a" + g.getAffection() 
                + " \u00a77(" + g.getAffectionData().getLevel().label + ")"), false);
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
            "\u00a7d" + girl.getGirlName() + "\u00a77's quest has been reset."), true);
        return 1;
    }

    private static int locateHouse(CommandSourceStack src) {
        if (!(src.getEntity() instanceof ServerPlayer player)) {
            src.sendFailure(Component.literal("Player-only command."));
            return 0;
        }
        var girls = player.level().getEntitiesOfClass(BaseGirlEntity.class,
            player.getBoundingBox().inflate(300));
        if (girls.isEmpty()) {
            src.sendFailure(Component.literal("No girls found within 300 blocks."));
            return 0;
        }
        BaseGirlEntity best = null;
        double bestDist = Double.MAX_VALUE;
        for (BaseGirlEntity g : girls) {
            if (g.needsHouse() && g.hasHouse() && g.getHousePos() != null) {
                double d = g.distanceToSqr(player);
                if (d < bestDist) { best = g; bestDist = d; }
            }
        }
        if (best == null) {
            src.sendFailure(Component.literal("No girls with houses within 300 blocks. Houses generate on first spawn of human girls (Jenny/Ellie/Bia/Allie/Slime)."));
            return 0;
        }
        BaseGirlEntity target = best;
        BlockPos pos = target.getHousePos();
        String name = target.getGirlName();
        src.sendSuccess(() -> Component.literal(
            "\u00a7d" + name + "\u00a77's house at \u00a7e" 
            + pos.getX() + " " + pos.getY() + " " + pos.getZ()
            + " \u00a77 — \u00a7a/tp " + pos.getX() + " " + (pos.getY() + 1) + " " + pos.getZ()), false);
        return 1;
    }

    /**
     * Debug: load a structure NBT from data/sexmod/structures/ (via StructureTemplateManager,
     * which runs the DataFixer for legacy 1.12.2 templates) and place it at the player.
     * Example: /sexmod teststructure jenny
     */
    private static int testStructure(CommandSourceStack src, String name) {
        net.minecraft.server.level.ServerLevel level;
        if (src.getEntity() instanceof ServerPlayer player) {
            level = player.serverLevel();
        } else {
            net.minecraft.server.MinecraftServer server = src.getServer();
            level = server.overworld();
        }
        net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager manager =
                level.getStructureManager();

        net.minecraft.resources.ResourceLocation loc =
                net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("sexmod", name);
        java.util.Optional<net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate> opt =
                manager.get(loc);
        if (opt.isEmpty()) {
            // Try explicit structures/ prefix (legacy naming)
            loc = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("sexmod", "structures/" + name);
            opt = manager.get(loc);
        }
        if (opt.isEmpty()) {
            // Fallback: manual load via ResourceManager + DataFixer (same path as GirlHouseGenerator)
            opt = com.schnurritv.sexmod.worldgen.GirlHouseGenerator.manualLoadStructure(
                    src.getServer().overworld(), name);
        }
        if (opt.isEmpty()) {
            src.sendFailure(Component.literal("Structure not found: sexmod:" + name
                    + " (manager + manual both failed)"));
            return 0;
        }

        net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate template = opt.get();
        net.minecraft.core.BlockPos origin;
        if (src.getEntity() instanceof ServerPlayer p) {
            origin = p.blockPosition().offset(3, 0, 3);
        } else {
            origin = new net.minecraft.core.BlockPos(0, 0, 0);
        }
        // Find ground
        int groundY = level.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING, origin).getY();
        final net.minecraft.core.BlockPos placePos = new net.minecraft.core.BlockPos(origin.getX(), groundY, origin.getZ());
        final int placedX = placePos.getX();
        final int placedY = placePos.getY();
        final int placedZ = placePos.getZ();

        net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings settings =
                new net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings()
                        .setIgnoreEntities(true);
        template.placeInWorld(level, placePos, placePos, settings, level.random, 3);

        net.minecraft.core.Vec3i size = template.getSize();
        src.sendSuccess(() -> Component.literal(
                "\u00a7aPlaced sexmod:" + name + " (" + size.getX() + "x" + size.getY() + "x" + size.getZ()
                + ") at " + placedX + " " + placedY + " " + placedZ
                + " \u00a77(DataFixer-upgraded from legacy format if applicable)"), true);
        return 1;
    }
}
