package com.schnurritv.sexmod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SexModConfig {

    // ── Server config ──
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

    // ── Client config ──
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    // ================================================================
    //  Server settings
    // ================================================================

    // ── Spawn Weights ──
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_JENNY;
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_ELLIE;
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_ALLIE;
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_BIA;
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_BEE;
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_CAT;
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_GOBLIN;
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_KOBOLD;
    public static final ForgeConfigSpec.IntValue SPAWN_WEIGHT_SLIME;

    // ── Spawn min/max group size ──
    public static final ForgeConfigSpec.IntValue SPAWN_MIN_GROUP;
    public static final ForgeConfigSpec.IntValue SPAWN_MAX_GROUP;

    // ── Behavior ──
    public static final ForgeConfigSpec.DoubleValue FOLLOW_DISTANCE;
    public static final ForgeConfigSpec.DoubleValue STAY_RANGE;
    public static final ForgeConfigSpec.BooleanValue GIRLS_CAN_TELEPORT_TO_OWNER;
    public static final ForgeConfigSpec.BooleanValue GIRLS_FIGHT_HOSTILES;
    public static final ForgeConfigSpec.DoubleValue GIRL_ATTACK_DAMAGE;
    public static final ForgeConfigSpec.BooleanValue GIRLS_PICKUP_ITEMS;

    // ── Clothing ──
    public static final ForgeConfigSpec.IntValue CLOTHING_AFFECTION_THRESHOLD;

    // ── Affection ──
    public static final ForgeConfigSpec.IntValue AFFECTION_MAX;
    public static final ForgeConfigSpec.DoubleValue AFFECTION_DECAY_PER_DAY;
    public static final ForgeConfigSpec.IntValue DAILY_GIFT_LIMIT;
    public static final ForgeConfigSpec.IntValue AFFECTION_SCENE_THRESHOLD_LOW;
    public static final ForgeConfigSpec.IntValue AFFECTION_SCENE_THRESHOLD_HIGH;
    public static final ForgeConfigSpec.IntValue AFFECTION_DIALOGUE_THRESHOLD;

    // ── Scenes ──
    public static final ForgeConfigSpec.BooleanValue REQUIRE_BED_FOR_SCENES;
    public static final ForgeConfigSpec.BooleanValue SCENES_REQUIRE_AFFECTION;

    // ── World structures ──
    public static final ForgeConfigSpec.BooleanValue GENERATE_JENNY_HOUSE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_ALLIE_INN;
    public static final ForgeConfigSpec.BooleanValue GENERATE_ELLIE_TAVERN;
    public static final ForgeConfigSpec.BooleanValue GENERATE_BEE_HIVE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_GOBLIN_CAMP;
    public static final ForgeConfigSpec.BooleanValue GENERATE_KOBOLD_LAIR;
    public static final ForgeConfigSpec.BooleanValue GENERATE_CAT_CAFE;

    // ================================================================
    //  Client settings
    // ================================================================

    // ── Camera ──
    public static final ForgeConfigSpec.DoubleValue CAM_DOGGY_Y;
    public static final ForgeConfigSpec.DoubleValue CAM_DOGGY_Z;
    public static final ForgeConfigSpec.DoubleValue CAM_MISSIONARY_Y;
    public static final ForgeConfigSpec.DoubleValue CAM_MISSIONARY_Z;
    public static final ForgeConfigSpec.DoubleValue CAM_BLOWJOB_Y;
    public static final ForgeConfigSpec.DoubleValue CAM_BLOWJOB_Z;
    public static final ForgeConfigSpec.DoubleValue CAM_PAIZURI_Y;
    public static final ForgeConfigSpec.DoubleValue CAM_PAIZURI_Z;
    public static final ForgeConfigSpec.DoubleValue CAM_DEFAULT_Y;
    public static final ForgeConfigSpec.DoubleValue CAM_DEFAULT_Z;

    // ── GUI ──
    public static final ForgeConfigSpec.BooleanValue SHOW_AFFECTION_HUD;
    public static final ForgeConfigSpec.IntValue AFFECTION_HUD_X;
    public static final ForgeConfigSpec.IntValue AFFECTION_HUD_Y;
    public static final ForgeConfigSpec.BooleanValue SHOW_DIALOGUE_BUBBLES;
    public static final ForgeConfigSpec.BooleanValue RADIAL_MENU_DARK_MODE;

    // ── Particles ──
    public static final ForgeConfigSpec.BooleanValue HEART_PARTICLES;
    public static final ForgeConfigSpec.BooleanValue SCENE_FADE;

    // ── Sound ──
    public static final ForgeConfigSpec.DoubleValue VOICE_VOLUME;
    public static final ForgeConfigSpec.BooleanValue IDLE_AMBIENT_SOUNDS;

    static {
        // ===========================================
        //  Server config build
        // ===========================================
        SERVER_BUILDER.push("Natural Spawn Weights");
        SERVER_BUILDER.comment("Set to 0 to disable natural spawning. Vanilla hostile mobs are around 100.");
        SPAWN_WEIGHT_JENNY   = SERVER_BUILDER.defineInRange("jenny_weight", 20, 0, 1000);
        SPAWN_WEIGHT_ELLIE   = SERVER_BUILDER.defineInRange("ellie_weight", 15, 0, 1000);
        SPAWN_WEIGHT_ALLIE   = SERVER_BUILDER.defineInRange("allie_weight", 15, 0, 1000);
        SPAWN_WEIGHT_BIA     = SERVER_BUILDER.defineInRange("bia_weight", 5, 0, 1000);
        SPAWN_WEIGHT_BEE     = SERVER_BUILDER.defineInRange("bee_weight", 10, 0, 1000);
        SPAWN_WEIGHT_CAT     = SERVER_BUILDER.defineInRange("cat_weight", 12, 0, 1000);
        SPAWN_WEIGHT_GOBLIN  = SERVER_BUILDER.defineInRange("goblin_weight", 8, 0, 1000);
        SPAWN_WEIGHT_KOBOLD  = SERVER_BUILDER.defineInRange("kobold_weight", 3, 0, 1000);
        SPAWN_WEIGHT_SLIME   = SERVER_BUILDER.defineInRange("slime_weight", 10, 0, 1000);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("Spawn Group Size");
        SPAWN_MIN_GROUP = SERVER_BUILDER.defineInRange("min_group", 1, 1, 5);
        SPAWN_MAX_GROUP = SERVER_BUILDER.defineInRange("max_group", 2, 1, 8);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("Behavior");
        FOLLOW_DISTANCE            = SERVER_BUILDER.defineInRange("follow_distance", 10.0, 3.0, 64.0);
        STAY_RANGE                 = SERVER_BUILDER.defineInRange("stay_range", 5.0, 2.0, 32.0);
        GIRLS_CAN_TELEPORT_TO_OWNER = SERVER_BUILDER.define("girls_teleport_to_owner", true);
        GIRLS_FIGHT_HOSTILES       = SERVER_BUILDER.define("girls_fight_hostiles", true);
        GIRL_ATTACK_DAMAGE         = SERVER_BUILDER.defineInRange("girl_attack_damage", 4.0, 0.0, 100.0);
        GIRLS_PICKUP_ITEMS         = SERVER_BUILDER.define("girls_pickup_items", true);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("Clothing");
        CLOTHING_AFFECTION_THRESHOLD = SERVER_BUILDER.defineInRange("clothing_affection_threshold", 50, 0, 100);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("Affection System");
        AFFECTION_MAX                = SERVER_BUILDER.defineInRange("affection_max", 100, 10, 1000);
        AFFECTION_DECAY_PER_DAY       = SERVER_BUILDER.defineInRange("affection_decay_per_day", 2.0, 0.0, 20.0);
        DAILY_GIFT_LIMIT             = SERVER_BUILDER.defineInRange("daily_gift_limit", 3, 1, 50);
        AFFECTION_SCENE_THRESHOLD_LOW  = SERVER_BUILDER.defineInRange("scene_threshold_low", 40, 0, 100);
        AFFECTION_SCENE_THRESHOLD_HIGH = SERVER_BUILDER.defineInRange("scene_threshold_high", 70, 0, 100);
        AFFECTION_DIALOGUE_THRESHOLD  = SERVER_BUILDER.defineInRange("dialogue_threshold", 15, 0, 100);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("Scene Rules");
        REQUIRE_BED_FOR_SCENES  = SERVER_BUILDER.define("require_bed", true);
        SCENES_REQUIRE_AFFECTION = SERVER_BUILDER.define("scenes_require_affection", true);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("World Structures");
        GENERATE_JENNY_HOUSE  = SERVER_BUILDER.define("jenny_house", true);
        GENERATE_ALLIE_INN    = SERVER_BUILDER.define("allie_inn", true);
        GENERATE_ELLIE_TAVERN = SERVER_BUILDER.define("ellie_tavern", true);
        GENERATE_BEE_HIVE     = SERVER_BUILDER.define("bee_hive", true);
        GENERATE_GOBLIN_CAMP  = SERVER_BUILDER.define("goblin_camp", true);
        GENERATE_KOBOLD_LAIR  = SERVER_BUILDER.define("kobold_lair", true);
        GENERATE_CAT_CAFE     = SERVER_BUILDER.define("cat_cafe", true);
        SERVER_BUILDER.pop();

        SERVER_SPEC = SERVER_BUILDER.build();

        // ===========================================
        //  Client config build
        // ===========================================
        CLIENT_BUILDER.push("Camera Offsets");
        CLIENT_BUILDER.comment("Vertical (Y) and Distance (Z) camera offsets per scene.");
        CAM_DOGGY_Y      = CLIENT_BUILDER.defineInRange("doggyY", 1.5, 0.0, 10.0);
        CAM_DOGGY_Z      = CLIENT_BUILDER.defineInRange("doggyZ", 0.6, 0.0, 10.0);
        CAM_MISSIONARY_Y = CLIENT_BUILDER.defineInRange("missionaryY", 1.7, 0.0, 10.0);
        CAM_MISSIONARY_Z = CLIENT_BUILDER.defineInRange("missionaryZ", 0.7, 0.0, 10.0);
        CAM_BLOWJOB_Y    = CLIENT_BUILDER.defineInRange("blowjobY", 1.2, 0.0, 10.0);
        CAM_BLOWJOB_Z    = CLIENT_BUILDER.defineInRange("blowjobZ", 0.5, 0.0, 10.0);
        CAM_PAIZURI_Y    = CLIENT_BUILDER.defineInRange("paizuriY", 1.4, 0.0, 10.0);
        CAM_PAIZURI_Z    = CLIENT_BUILDER.defineInRange("paizuriZ", 0.6, 0.0, 10.0);
        CAM_DEFAULT_Y    = CLIENT_BUILDER.defineInRange("defaultY", 1.5, 0.0, 10.0);
        CAM_DEFAULT_Z    = CLIENT_BUILDER.defineInRange("defaultZ", 0.6, 0.0, 10.0);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("GUI");
        SHOW_AFFECTION_HUD     = CLIENT_BUILDER.define("show_affection_hud", true);
        AFFECTION_HUD_X        = CLIENT_BUILDER.defineInRange("affection_hud_x", 10, 0, 1920);
        AFFECTION_HUD_Y        = CLIENT_BUILDER.defineInRange("affection_hud_y", 10, 0, 1080);
        SHOW_DIALOGUE_BUBBLES  = CLIENT_BUILDER.define("show_dialogue_bubbles", true);
        RADIAL_MENU_DARK_MODE  = CLIENT_BUILDER.define("radial_menu_dark_mode", true);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("Visual Effects");
        HEART_PARTICLES = CLIENT_BUILDER.define("heart_particles", true);
        SCENE_FADE      = CLIENT_BUILDER.define("scene_fade", true);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("Audio");
        VOICE_VOLUME        = CLIENT_BUILDER.defineInRange("voice_volume", 1.0, 0.0, 2.0);
        IDLE_AMBIENT_SOUNDS = CLIENT_BUILDER.define("idle_ambient_sounds", true);
        CLIENT_BUILDER.pop();

        CLIENT_SPEC = CLIENT_BUILDER.build();
    }

    // ── Legacy compat aliases (redirect from old CLIENT_SPEC → new client fields) ──
    @Deprecated public static final ForgeConfigSpec SPEC = CLIENT_SPEC;
    @Deprecated public static final ForgeConfigSpec.Builder BUILDER = CLIENT_BUILDER;
    @Deprecated public static final ForgeConfigSpec.DoubleValue DOGGY_Y      = CAM_DOGGY_Y;
    @Deprecated public static final ForgeConfigSpec.DoubleValue DOGGY_Z      = CAM_DOGGY_Z;
    @Deprecated public static final ForgeConfigSpec.DoubleValue MISSIONARY_Y = CAM_MISSIONARY_Y;
    @Deprecated public static final ForgeConfigSpec.DoubleValue MISSIONARY_Z = CAM_MISSIONARY_Z;
    @Deprecated public static final ForgeConfigSpec.DoubleValue BLOWJOB_Y    = CAM_BLOWJOB_Y;
    @Deprecated public static final ForgeConfigSpec.DoubleValue BLOWJOB_Z    = CAM_BLOWJOB_Z;
    @Deprecated public static final ForgeConfigSpec.DoubleValue PAIZURI_Y    = CAM_PAIZURI_Y;
    @Deprecated public static final ForgeConfigSpec.DoubleValue PAIZURI_Z    = CAM_PAIZURI_Z;
    @Deprecated public static final ForgeConfigSpec.DoubleValue DEFAULT_Y    = CAM_DEFAULT_Y;
    @Deprecated public static final ForgeConfigSpec.DoubleValue DEFAULT_Z    = CAM_DEFAULT_Z;
}
