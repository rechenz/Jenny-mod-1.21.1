package com.schnurritv.sexmod.client.gui;

import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexEntity;
import com.schnurritv.sexmod.relationship.QuestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.schnurritv.sexmod.Main;

/**
 * HUD overlay showing nearby followed girl's affection + quest status.
 * Hooks into CustomizeGuiOverlayEvent.Chat (renders after chat overlay).
 * Shows friendly quest descriptions and ESCORT destination hints.
 */
@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GirlHUDOverlay {

    @SubscribeEvent
    public static void onRenderChat(CustomizeGuiOverlayEvent.Chat event) {
        var mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        // ── Scene progress bar (if player is locked in scene) ──
        // Find the girl that has this player locked
        BaseGirlEntity scenePartner = null;
        for (var entity : mc.level.entitiesForRendering()) {
            if (entity instanceof BaseGirlEntity girl) {
                if (girl.getEntityData().get(SexEntity.IS_LOCKED) &&
                    girl.getEntityData().get(SexEntity.PARTNER_UUID).equals(mc.player.getUUID().toString())) {
                    scenePartner = girl;
                    break;
                }
            }
        }

        if (scenePartner != null) {
            GuiGraphics g = event.getGuiGraphics();
            int screenW = mc.getWindow().getGuiScaledWidth();
            int screenH = mc.getWindow().getGuiScaledHeight();

            // Scene progress bar at bottom center of screen
            int barW = 120;
            int barH = 6;
            int barX = (screenW - barW) / 2;
            int barY = screenH - 30;

            int ticks = scenePartner.getEntityData().get(SexEntity.ANIMATION_TICKS);
            int length = scenePartner.getAnimationTickLength(scenePartner.getSexModAnimation());
            if (length > 0) {
                float progress = (float) Math.min(ticks, length) / length;
                int fillW = (int)(barW * progress);

                // Background
                g.fill(barX - 1, barY - 1, barX + barW + 1, barY + barH + 1, 0xFF442233);
                g.fill(barX, barY, barX + barW, barY + barH, 0xFF221122);
                // Fill
                if (fillW > 0) {
                    g.fill(barX, barY, barX + fillW, barY + barH, 0xFFCC6688);
                }

                // Phase label
                String phaseName = scenePartner.getSexModAnimation().name().toLowerCase();
                String label;
                if (phaseName.contains("start") || phaseName.contains("intro")) {
                    label = "Starting...";
                } else if (phaseName.contains("cum")) {
                    label = "§d§lFinishing...";
                } else if (phaseName.contains("fast") || phaseName.contains("thrust")) {
                    label = "§c♥ FAST";
                } else if (phaseName.contains("slow") || phaseName.contains("wait") || phaseName.contains("suck")) {
                    label = "§7♥ SLOW";
                } else {
                    label = "§7♥";
                }
                g.drawCenteredString(mc.font, label, screenW / 2, barY - 10, 0xFFDDCCDD);

                // Key hint
                g.drawCenteredString(mc.font, "§7SPACE: Toggle · SNEAK: Exit", screenW / 2, barY + barH + 4, 0xFF776677);
            }
        }

        // ── Ownership HUD (affection + quest) ──
        BaseGirlEntity nearest = null;
        double nearestDist = 256;
        String playerUUID = mc.player.getUUID().toString();

        for (var entity : mc.level.entitiesForRendering()) {
            if (entity instanceof BaseGirlEntity girl) {
                String owner = girl.getEntityData().get(SexEntity.MASTER_UUID);
                if (playerUUID.equals(owner)) {
                    double dist = entity.distanceToSqr(mc.player);
                    if (dist < nearestDist) {
                        nearestDist = dist;
                        nearest = girl;
                    }
                }
            }
        }

        if (nearest == null) return;

        GuiGraphics g = event.getGuiGraphics();
        int screenW = mc.getWindow().getGuiScaledWidth();
        int screenH = mc.getWindow().getGuiScaledHeight();
        int x = 10;
        int baseY = screenH - 55;
        int w = 160;

        // Determine how many extra lines we need
        var qm = nearest.getQuestManager();
        boolean hasQuest = qm.hasActiveQuest();
        var q = hasQuest ? qm.getActiveQuest() : null;
        boolean isEscort = q != null && q.type() == QuestManager.QuestType.ESCORT;
        boolean isKill = q != null && q.type() == QuestManager.QuestType.KILL;
        boolean isDefend = q != null && q.type() == QuestManager.QuestType.DEFEND;

        // We need extra lines for:
        // - base panel line (name + bar + numbers) = 3 lines
        // - quest line (1)
        // - action hint for non-FETCH (1 line)
        // - ESCORT destination hint (1-2 lines)
        int extraLines = 0;
        if (hasQuest) extraLines++; // quest description
        if (hasQuest && !q.type().equals(QuestManager.QuestType.FETCH)) extraLines++; // action hint
        if (isEscort) extraLines += 2; // destination + direction

        // Panel height = 40 base + 9 per extra line
        int h = 40 + extraLines * 9;
        int y = baseY - (extraLines > 1 ? 18 : 0); // shift up if many lines

        // Background
        g.fill(x, y, x + w, y + h, 0xC0221122);
        g.fill(x, y, x + w, y + 1, 0xFF664466);

        // Girl name
        String name = nearest.getGirlName().substring(0, 1).toUpperCase() + nearest.getGirlName().substring(1);
        g.drawString(mc.font, name, x + 6, y + 4, 0xFFEEAAEE, true);

        // Affection bar
        int aff = nearest.getAffection();
        int affMax = com.schnurritv.sexmod.SexModConfig.AFFECTION_MAX.get();
        int barW = w - 20;
        int fillW = Math.max(1, (int)((float)aff / affMax * barW));
        g.fill(x + 8, y + 16, x + 8 + barW, y + 22, 0xFF442233);
        g.fill(x + 8, y + 16, x + 8 + fillW, y + 22, 0xFFEE4466);

        // Numbers
        g.drawString(mc.font, "❤ " + aff + "/" + affMax, x + 10, y + 24, 0xFFFF8888, true);

        int lineY = y + 34;

        // Quest display
        if (hasQuest && q != null) {
            String qStr;
            switch (q.type()) {
                case KILL: {
                    int remaining = q.targetCount() - qm.getProgress();
                    qStr = "⚔ Kill " + remaining + " more " + friendlyMobName(q.targetMob());
                    break;
                }
                case FETCH: {
                    int remaining = q.targetCount() - qm.getProgress();
                    qStr = "📦 " + remaining + "x left to hand in";
                    break;
                }
                case ESCORT: {
                    String dest = switch (q.escortDestination()) {
                        case "BEACH" -> "a Beach/Ocean";
                        case "DEEP_CAVE" -> "Deep Cave (Y<30)";
                        case "FLOWER_FOREST" -> "Flower Forest";
                        default -> q.escortDestination();
                    };
                    qStr = "👣 Escort to: " + dest;
                    break;
                }
                case DEFEND: {
                    int wave = qm.getDefendWave();
                    int total = q.defendWaveCount();
                    if (wave == 0) {
                        qStr = "🛡 Defend: Wave 1/" + total + " (talk to her to start!)";
                    } else {
                        int remaining = qm.getDefendMobsRemaining();
                        qStr = "🛡 Defend: Wave " + wave + "/" + total + " (" + remaining + " mobs left)";
                    }
                    break;
                }
                default:
                    qStr = "📋 " + qm.getProgress() + "/" + q.targetCount();
            }
            // Truncate long strings
            if (mc.font.width(qStr) > w - 12) {
                while (mc.font.width(qStr + "...") > w - 12 && qStr.length() > 3) {
                    qStr = qStr.substring(0, qStr.length() - 1);
                }
                qStr += "...";
            }
            int color = switch (q.type()) {
                case KILL -> 0xFFFF6666;
                case FETCH -> 0xFF88AAFF;
                case ESCORT -> 0xFF66FFAA;
                case DEFEND -> 0xFFFFAA66;
            };
            g.drawString(mc.font, qStr, x + 6, lineY, color, true);
            lineY += 9;

            // Action hint for non-FETCH
            if (q.type() != QuestManager.QuestType.FETCH) {
                String hint = switch (q.type()) {
                    case KILL -> "Use a weapon! Kill " + q.targetMob();
                    case ESCORT -> "Travel with her nearby!";
                    case DEFEND -> "Talk to her to start!";
                    default -> "";
                };
                if (!hint.isEmpty()) {
                    g.drawString(mc.font, "§7" + hint, x + 6, lineY, 0xFF777777, true);
                    lineY += 9;
                }
            }

            // ESCORT destination direction hint
            if (isEscort) {
                String destBiomeKey = switch (q.escortDestination()) {
                    case "BEACH" -> null; // no single biome key
                    case "DEEP_CAVE" -> null; // Y-based, not biome-based
                    case "FLOWER_FOREST" -> "flower_forest";
                    default -> null;
                };
                if ("DEEP_CAVE".equals(q.escortDestination())) {
                    int yLevel = mc.player.blockPosition().getY();
                    int diff = yLevel - 30;
                    if (diff > 0) {
                        g.drawString(mc.font, "⬇ " + diff + " blocks down to Y<30", x + 6, lineY, 0xFF66AAFF, true);
                    } else {
                        g.drawString(mc.font, "✔ Below Y30 — you're deep enough!", x + 6, lineY, 0xFF66FFAA, true);
                    }
                } else if (destBiomeKey != null) {
                    // Check current biome
                    Holder<Biome> currentBiome = mc.level.getBiome(mc.player.blockPosition());
                    String biomeName = currentBiome.getRegisteredName();
                    boolean atDest = biomeName != null && biomeName.toLowerCase().contains(destBiomeKey);
                    if (atDest) {
                        g.drawString(mc.font, "✔ You're at " + destBiomeKey + "!", x + 6, lineY, 0xFF66FFAA, true);
                    } else {
                        g.drawString(mc.font, "§oSearched for: " + destBiomeKey, x + 6, lineY, 0xFF777777, true);
                    }
                } else if ("BEACH".equals(q.escortDestination())) {
                    Holder<Biome> currentBiome = mc.level.getBiome(mc.player.blockPosition());
                    String biomeName = currentBiome.getRegisteredName();
                    boolean atDest = biomeName != null && (biomeName.toLowerCase().contains("beach") || biomeName.toLowerCase().contains("ocean"));
                    if (atDest) {
                        g.drawString(mc.font, "✔ You're near the ocean!", x + 6, lineY, 0xFF66FFAA, true);
                    } else {
                        g.drawString(mc.font, "§oSearch for beach/ocean biome", x + 6, lineY, 0xFF777777, true);
                    }
                }
            }
        }
    }

    private static String friendlyMobName(String mobKey) {
        if (mobKey == null || mobKey.isEmpty()) return "Mobs";
        return switch (mobKey.toUpperCase()) {
            case "ZOMBIE" -> "Zombies";
            case "SKELETON" -> "Skeletons";
            case "SPIDER" -> "Spiders";
            case "ENDERMAN" -> "Endermen";
            case "WITHER_SKELETON" -> "Wither Skeletons";
            case "CREEPER" -> "Creepers";
            default -> mobKey;
        };
    }
}
