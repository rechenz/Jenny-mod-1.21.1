#!/usr/bin/env python3
"""
Convert legacy 1.12.2 structure NBT files to 1.21.1 native format.

Why: The DataFixer (1.12.2 → 1.21.1) chokes on old block-entity ids like
`minecraft:flower_pot` (RemoveNoteBlockFlowerPotFix expects the new format).
Instead of fighting the fixer, we rewrite the NBT directly:

1. Remap 1.12.2 block names → 1.21.1 names (flat world names, 1.13+).
2. Remap block state properties where names changed.
3. Clean block-entity NBT: keep harmless ones (chest/furnace/bed), drop
   potted-plant contents (flower_pot block entity no longer exists as such;
   the pot is now a separate block and the plant is encoded in the block state).
4. Set DataVersion to the current 1.21.1 data version (3955).
5. Fix `entities` section: strip entity NBT (we spawn characters ourselves).

Input:  assets/sexmod/structures/*.nbt (gzip-compressed legacy templates)
Output: data/sexmod/structures/*.nbt (gzip-compressed 1.21.1 templates)
"""

import gzip
import io
import os
import sys
import nbtlib

CURRENT_DATA_VERSION = 3955  # 1.21.1

# ── 1.12.2 block name → 1.21.1 block name (flat naming) ──
BLOCK_RENAME = {
    "minecraft:grass": "minecraft:grass_block",
    "minecraft:tallgrass": "minecraft:short_grass",
    "minecraft:double_plant": "minecraft:peony",
    "minecraft:stone": "minecraft:stone",
    "minecraft:cobblestone": "minecraft:cobblestone",
    "minecraft:mossy_cobblestone": "minecraft:mossy_cobblestone",
    "minecraft:stonebrick": "minecraft:stone_bricks",
    "minecraft:planks": None,          # handled by property remap below
    "minecraft:log": None,             # handled by property remap
    "minecraft:log2": None,            # handled by property remap
    "minecraft:leaves": None,          # handled by property remap
    "minecraft:leaves2": None,         # handled by property remap
    "minecraft:wooden_slab": None,     # handled by property remap
    "minecraft:stone_slab": None,      # handled by property remap
    "minecraft:double_stone_slab": None,
    "minecraft:wooden_door": None,     # handled by property remap
    "minecraft:iron_door": "minecraft:iron_door",
    "minecraft:wooden_trapdoor": None, # handled by property remap
    "minecraft:fence": None,           # handled by property remap
    "minecraft:fence_gate": None,      # handled by property remap
    "minecraft:wall": None,            # handled by property remap
    "minecraft:stained_glass": None,   # handled by property remap
    "minecraft:stained_glass_pane": None,
    "minecraft:stained_hardened_clay": None,  # handled by property remap
    "minecraft:concrete": None,        # handled by property remap
    "minecraft:concrete_powder": None, # handled by property remap
    "minecraft:carpet": None,          # handled by property remap
    "minecraft:wool": None,            # handled by property remap
    "minecraft:flower_pot": "minecraft:flower_pot",
    "minecraft:lit_furnace": "minecraft:furnace",
    "minecraft:lit_redstone_lamp": "minecraft:redstone_lamp",
    "minecraft:daylight_detector": "minecraft:daylight_detector",
    "minecraft:daylight_detector_inverted": "minecraft:daylight_detector",
    "minecraft:unpowered_repeater": "minecraft:repeater",
    "minecraft:powered_repeater": "minecraft:repeater",
    "minecraft:unpowered_comparator": "minecraft:comparator",
    "minecraft:powered_comparator": "minecraft:comparator",
    "minecraft:redstone_wire": "minecraft:redstone_wire",
    "minecraft:stone_button": "minecraft:stone_button",
    "minecraft:wooden_button": "minecraft:oak_button",
    "minecraft:stone_pressure_plate": "minecraft:stone_pressure_plate",
    "minecraft:wooden_pressure_plate": "minecraft:oak_pressure_plate",
    "minecraft:heavy_weighted_pressure_plate": "minecraft:heavy_weighted_pressure_plate",
    "minecraft:light_weighted_pressure_plate": "minecraft:light_weighted_pressure_plate",
    "minecraft:standing_sign": "minecraft:oak_sign",
    "minecraft:wall_sign": "minecraft:oak_wall_sign",
    "minecraft:wooden_door_block": None,
    "minecraft:bed": "minecraft:red_bed",  # default color red; property remap may adjust
    "minecraft:chest": "minecraft:chest",
    "minecraft:trapped_chest": "minecraft:trapped_chest",
    "minecraft:furnace": "minecraft:furnace",
    "minecraft:crafting_table": "minecraft:crafting_table",
    "minecraft:bookshelf": "minecraft:bookshelf",
    "minecraft:torch": "minecraft:torch",
    "minecraft:ladder": "minecraft:ladder",
    "minecraft:glass_pane": "minecraft:glass_pane",
    "minecraft:glass": "minecraft:glass",
    "minecraft:air": "minecraft:air",
    "minecraft:stone_slab2": None,
    "minecraft:nether_brick_fence": "minecraft:nether_brick_fence",
    "minecraft:quartz_block": None,     # handled by property remap
    "minecraft:quartz_stairs": "minecraft:quartz_stairs",
    "minecraft:stone_stairs": "minecraft:cobblestone_stairs",
    "minecraft:brick_stairs": "minecraft:brick_stairs",
    "minecraft:nether_brick_stairs": "minecraft:nether_brick_stairs",
    "minecraft:sandstone_stairs": "minecraft:sandstone_stairs",
    "minecraft:red_sandstone_stairs": "minecraft:red_sandstone_stairs",
    "minecraft:oak_stairs": "minecraft:oak_stairs",
    "minecraft:birch_stairs": "minecraft:birch_stairs",
    "minecraft:spruce_stairs": "minecraft:spruce_stairs",
    "minecraft:jungle_stairs": "minecraft:jungle_stairs",
    "minecraft:acacia_stairs": "minecraft:acacia_stairs",
    "minecraft:dark_oak_stairs": "minecraft:dark_oak_stairs",
    "minecraft:stone_slab": None,
    "minecraft:double_stone_slab": None,
    "minecraft:rail": "minecraft:rail",
    "minecraft:golden_rail": "minecraft:powered_rail",
    "minecraft:detector_rail": "minecraft:detector_rail",
    "minecraft:activator_rail": "minecraft:activator_rail",
    "minecraft:water": "minecraft:water",
    "minecraft:lava": "minecraft:lava",
    "minecraft:flowing_water": "minecraft:water",
    "minecraft:flowing_lava": "minecraft:lava",
    "minecraft:snow_layer": "minecraft:snow",
    "minecraft:snow": "minecraft:snow_block",
    "minecraft:ice": "minecraft:ice",
    "minecraft:packed_ice": "minecraft:packed_ice",
    "minecraft:gravel": "minecraft:gravel",
    "minecraft:sand": "minecraft:sand",
    "minecraft:red_sand": "minecraft:red_sand",
    "minecraft:sandstone": "minecraft:sandstone",
    "minecraft:red_sandstone": "minecraft:red_sandstone",
    "minecraft:hardened_clay": "minecraft:terracotta",
    "minecraft:stained_hardened_clay": None,  # handled by property remap
    "minecraft:clay": "minecraft:clay",
    "minecraft:stonebrick": "minecraft:stone_bricks",
    "minecraft:netherrack": "minecraft:netherrack",
    "minecraft:soul_sand": "minecraft:soul_sand",
    "minecraft:glowstone": "minecraft:glowstone",
    "minecraft:end_stone": "minecraft:end_stone",
    "minecraft:obsidian": "minecraft:obsidian",
    "minecraft:bedrock": "minecraft:bedrock",
    "minecraft:portal": "minecraft:nether_portal",
    "minecraft:lit_pumpkin": "minecraft:jack_o_lantern",
    "minecraft:pumpkin": "minecraft:pumpkin",
    "minecraft:melon_block": "minecraft:melon",
    "minecraft:web": "minecraft:cobweb",
    "minecraft:tallgrass": "minecraft:short_grass",
    "minecraft:deadbush": "minecraft:dead_bush",
    "minecraft:yellow_flower": "minecraft:dandelion",
    "minecraft:red_flower": None,       # handled by property remap
    "minecraft:brown_mushroom": "minecraft:brown_mushroom",
    "minecraft:red_mushroom": "minecraft:red_mushroom",
    "minecraft:brown_mushroom_block": "minecraft:brown_mushroom_block",
    "minecraft:red_mushroom_block": "minecraft:red_mushroom_block",
    "minecraft:cactus": "minecraft:cactus",
    "minecraft:reeds": "minecraft:sugar_cane",
    "minecraft:vine": "minecraft:vine",
    "minecraft:waterlily": "minecraft:lily_pad",
    "minecraft:mycelium": "minecraft:mycelium",
    "minecraft:podzol": "minecraft:podzol",
    "minecraft:dirt": "minecraft:dirt",
    "minecraft:cobblestone_wall": "minecraft:cobblestone_wall",
    "minecraft:sponge": "minecraft:sponge",
    "minecraft:prismarine": None,       # handled by property remap
    "minecraft:sea_lantern": "minecraft:sea_lantern",
    "minecraft:slime": "minecraft:slime_block",
    "minecraft:ender_chest": "minecraft:ender_chest",
    "minecraft:skull": None,            # handled by property remap
    "minecraft:anvil": None,            # handled by property remap
    "minecraft:enchanting_table": "minecraft:enchanting_table",
    "minecraft:brewing_stand": "minecraft:brewing_stand",
    "minecraft:cauldron": "minecraft:cauldron",
    "minecraft:hopper": "minecraft:hopper",
    "minecraft:dropper": "minecraft:dropper",
    "minecraft:dispenser": "minecraft:dispenser",
    "minecraft:piston": "minecraft:piston",
    "minecraft:sticky_piston": "minecraft:sticky_piston",
    "minecraft:piston_extension": "minecraft:piston_head",
    "minecraft:piston_head": "minecraft:piston_head",
    "minecraft:command_block": "minecraft:command_block",
    "minecraft:beacon": "minecraft:beacon",
    "minecraft:tnt": "minecraft:tnt",
    "minecraft:lever": "minecraft:lever",
    "minecraft:tripwire_hook": "minecraft:tripwire_hook",
    "minecraft:tripwire": "minecraft:tripwire",
    "minecraft:noteblock": "minecraft:note_block",
    "minecraft:jukebox": "minecraft:jukebox",
    "minecraft:gold_block": "minecraft:gold_block",
    "minecraft:iron_block": "minecraft:iron_block",
    "minecraft:diamond_block": "minecraft:diamond_block",
    "minecraft:emerald_block": "minecraft:emerald_block",
    "minecraft:coal_block": "minecraft:coal_block",
    "minecraft:lapis_block": "minecraft:lapis_block",
    "minecraft:redstone_block": "minecraft:redstone_block",
    "minecraft:quartz_ore": "minecraft:nether_quartz_ore",
    "minecraft:coal_ore": "minecraft:coal_ore",
    "minecraft:iron_ore": "minecraft:iron_ore",
    "minecraft:gold_ore": "minecraft:gold_ore",
    "minecraft:diamond_ore": "minecraft:diamond_ore",
    "minecraft:emerald_ore": "minecraft:emerald_ore",
    "minecraft:lapis_ore": "minecraft:lapis_ore",
    "minecraft:redstone_ore": "minecraft:redstone_ore",
    "minecraft:lit_redstone_ore": "minecraft:redstone_ore",
    "minecraft:grass_path": "minecraft:dirt_path",
    "minecraft:magma": "minecraft:magma_block",
    "minecraft:end_bricks": "minecraft:end_stone_bricks",
    "minecraft:end_rod": "minecraft:end_rod",
    "minecraft:chorus_flower": "minecraft:chorus_flower",
    "minecraft:chorus_plant": "minecraft:chorus_plant",
    "minecraft:purpur_block": "minecraft:purpur_block",
    "minecraft:purpur_pillar": "minecraft:purpur_pillar",
    "minecraft:purpur_stairs": "minecraft:purpur_stairs",
    "minecraft:purpur_slab": "minecraft:purpur_slab",
    "minecraft:double_purpur_slab": "minecraft:purpur_slab",
    "minecraft:bone_block": "minecraft:bone_block",
    "minecraft:observer": "minecraft:observer",
    "minecraft:structure_block": "minecraft:structure_block",
    "minecraft:structure_void": "minecraft:structure_void",
    "minecraft:barrier": "minecraft:barrier",
    "minecraft:iron_bars": "minecraft:iron_bars",
    "minecraft:nether_brick": "minecraft:nether_bricks",
    "minecraft:red_nether_brick": "minecraft:red_nether_bricks",
    "minecraft:quartz_block": None,
    "minecraft:double_wooden_slab": None,
    "minecraft:double_stone_slab2": None,
}

# ── wood-type property → new block name ──
WOOD_MAP = {"oak": "oak", "spruce": "spruce", "birch": "birch", "jungle": "jungle",
            "acacia": "acacia", "dark_oak": "dark_oak"}

# ── 1.12.2 "variant" property → 1.21.1 block name for planks/log/etc ──
VARIANT_BLOCK = {
    "planks": lambda v: f"minecraft:{v}_planks",
    "log": lambda v: f"minecraft:{v}_log",
    "log2": lambda v: f"minecraft:{v}_log",
    "leaves": lambda v: f"minecraft:{v}_leaves",
    "leaves2": lambda v: f"minecraft:{v}_leaves",
    "wooden_slab": lambda v: f"minecraft:{v}_slab",
    "double_wooden_slab": lambda v: f"minecraft:{v}_slab",
    "wooden_trapdoor": lambda v: f"minecraft:{v}_trapdoor",
    "fence": lambda v: f"minecraft:{v}_fence",
    "fence_gate": lambda v: f"minecraft:{v}_fence_gate",
    "wooden_door": lambda v: f"minecraft:{v}_door",
    "wooden_button": lambda v: f"minecraft:{v}_button",
    "wooden_pressure_plate": lambda v: f"minecraft:{v}_pressure_plate",
    "stained_glass": lambda v: f"minecraft:{COLOR_MAP.get(v, v)}_stained_glass",
    "stained_glass_pane": lambda v: f"minecraft:{COLOR_MAP.get(v, v)}_stained_glass_pane",
    "stained_hardened_clay": lambda v: f"minecraft:{COLOR_MAP.get(v, v)}_terracotta",
    "concrete": lambda v: f"minecraft:{COLOR_MAP.get(v, v)}_concrete",
    "concrete_powder": lambda v: f"minecraft:{COLOR_MAP.get(v, v)}_concrete_powder",
    "carpet": lambda v: f"minecraft:{COLOR_MAP.get(v, v)}_carpet",
    "wool": lambda v: f"minecraft:{COLOR_MAP.get(v, v)}_wool",
    "red_flower": lambda v: FLOWER_MAP.get(v, "minecraft:poppy"),
    "stone_slab": lambda v: SLAB_MAP.get(v, "minecraft:stone_slab"),
    "stone_slab2": lambda v: SLAB2_MAP.get(v, "minecraft:red_sandstone_slab"),
    "double_stone_slab": lambda v: SLAB_MAP.get(v, "minecraft:stone_slab"),
    "double_stone_slab2": lambda v: SLAB2_MAP.get(v, "minecraft:red_sandstone_slab"),
    "wall": lambda v: WALL_MAP.get(v, "minecraft:cobblestone_wall"),
    "quartz_block": lambda v: QUARTZ_MAP.get(v, "minecraft:quartz_block"),
    "prismarine": lambda v: PRISMARINE_MAP.get(v, "minecraft:prismarine"),
    "skull": lambda v: SKULL_MAP.get(v, "minecraft:skeleton_skull"),
    "anvil": lambda v: ANVIL_MAP.get(v, "minecraft:anvil"),
    "bed": lambda v: f"minecraft:{COLOR_MAP.get(v, 'red')}_bed",
}

COLOR_MAP = {
    "white": "white", "orange": "orange", "magenta": "magenta",
    "light_blue": "light_blue", "yellow": "yellow", "lime": "lime",
    "pink": "pink", "gray": "gray", "silver": "light_gray",
    "cyan": "cyan", "purple": "purple", "blue": "blue",
    "brown": "brown", "green": "green", "red": "red", "black": "black",
}

FLOWER_MAP = {
    "0": "minecraft:poppy", "1": "minecraft:blue_orchid",
    "2": "minecraft:allium", "3": "minecraft:azure_bluet",
    "4": "minecraft:red_tulip", "5": "minecraft:orange_tulip",
    "6": "minecraft:white_tulip", "7": "minecraft:pink_tulip",
    "8": "minecraft:oxeye_daisy",
}

SLAB_MAP = {
    "0": "minecraft:stone_slab", "1": "minecraft:sandstone_slab",
    "2": "minecraft:petrified_oak_slab", "3": "minecraft:cobblestone_slab",
    "4": "minecraft:brick_slab", "5": "minecraft:stone_brick_slab",
    "6": "minecraft:nether_brick_slab", "7": "minecraft:quartz_slab",
}

SLAB2_MAP = {
    "0": "minecraft:red_sandstone_slab", "1": "minecraft:purpur_slab",
}

WALL_MAP = {
    "0": "minecraft:cobblestone_wall", "1": "minecraft:mossy_cobblestone_wall",
}

QUARTZ_MAP = {
    "0": "minecraft:quartz_block", "1": "minecraft:chiseled_quartz_block",
    "2": "minecraft:quartz_pillar",
}

PRISMARINE_MAP = {
    "0": "minecraft:prismarine", "1": "minecraft:prismarine_bricks",
    "2": "minecraft:dark_prismarine",
}

SKULL_MAP = {
    "0": "minecraft:skeleton_skull", "1": "minecraft:wither_skeleton_skull",
    "2": "minecraft:zombie_head", "3": "minecraft:player_head",
    "4": "minecraft:creeper_head", "5": "minecraft:dragon_head",
}

ANVIL_MAP = {
    "0": "minecraft:anvil", "1": "minecraft:chipped_anvil", "2": "minecraft:damaged_anvil",
}

# block entity ids that survive fine in 1.21.1 (keep their NBT)
KEEP_BLOCK_ENTITY = {"minecraft:chest", "minecraft:trapped_chest", "minecraft:furnace",
                     "minecraft:bed", "minecraft:sign", "minecraft:standing_sign",
                     "minecraft:wall_sign", "minecraft:hopper", "minecraft:brewing_stand",
                     "minecraft:beacon", "minecraft:command_block"}


def remap_palette_entry(entry):
    """Return a new palette entry dict with 1.21.1 names, or None to drop."""
    name = str(entry.get("Name", ""))
    props = {}
    if "Properties" in entry:
        for k, v in entry["Properties"].items():
            props[str(k)] = str(v)

    new_name = BLOCK_RENAME.get(name)
    if new_name is None and name in VARIANT_BLOCK:
        variant = props.get("variant", props.get("type", "0"))
        try:
            new_name = VARIANT_BLOCK[name](variant)
        except Exception:
            new_name = None

    if new_name is None:
        # also try bare name (without minecraft: prefix) in VARIANT_BLOCK
        bare = name.split(":", 1)[-1] if ":" in name else name
        if bare in VARIANT_BLOCK:
            variant = props.get("variant", props.get("type", "0"))
            try:
                new_name = VARIANT_BLOCK[bare](variant)
            except Exception:
                new_name = None

    if new_name is None:
        # last resort: keep original name (may fail on load, better than nothing)
        new_name = name

    # Normalize: strip now-invalid properties
    new_props = {}
    for k, v in props.items():
        # "variant" was the 1.12.2 discriminator; no longer exists in 1.21.1
        if k in ("variant", "type"):
            continue
        # 1.12.2 walls/fences/pistons encoded connection booleans that are
        # either renamed or handled differently in 1.21.1 — drop the noisy
        # "false" values ("true" values are meaningful and kept).
        if k in ("east", "west", "north", "south", "up", "down") and v == "false":
            continue
        new_props[k] = v

    out = {"Name": nbtlib.String(new_name)}
    if new_props:
        out["Properties"] = nbtlib.Compound({k: nbtlib.String(v) for k, v in new_props.items()})
    return out


def convert(input_path, output_path):
    raw = gzip.decompress(open(input_path, "rb").read())
    f = nbtlib.File.parse(io.BytesIO(raw))
    root = f

    # Remap palette
    if "palette" in root:
        new_palette = []
        for entry in root["palette"]:
            np_ = remap_palette_entry(entry)
            if np_ is not None:
                new_palette.append(nbtlib.Compound(np_))
        root["palette"] = nbtlib.List[nbtlib.Compound](new_palette)

    # Drop block-entity NBT for potted plants (flower_pot block entity is gone in 1.21.1)
    # and remap remaining block entity ids.
    if "blocks" in root:
        for b in root["blocks"]:
            if "nbt" in b:
                be_id = str(b["nbt"].get("id", ""))
                if be_id == "minecraft:flower_pot":
                    # Flower pots no longer store their plant in block-entity NBT;
                    # the plant is part of the block state now. Drop the NBT so the
                    # DataFixer/loader doesn't choke. The pot block itself stays.
                    del b["nbt"]
                elif be_id == "minecraft:bed":
                    # Beds keep NBT but id is fine (red_bed etc.)
                    b["nbt"]["id"] = nbtlib.String("minecraft:bed")
                elif be_id and "minecraft:" not in be_id:
                    # bare id like "chest" → qualify it
                    b["nbt"]["id"] = nbtlib.String("minecraft:" + be_id)

    # Strip entity NBT entirely (we spawn characters ourselves; old entity
    # ids like sexmod:jenny won't resolve through the DataFixer cleanly).
    if "entities" in root:
        root["entities"] = nbtlib.List[nbtlib.Compound]([])

    # Set DataVersion to current so the loader skips the legacy fixer path.
    root["DataVersion"] = nbtlib.Int(CURRENT_DATA_VERSION)
    # Remove Forge-specific version marker if present
    if "ForgeDataVersion" in root:
        del root["ForgeDataVersion"]

    # Serialize with gzip
    buf = io.BytesIO()
    f.write(buf)  # writes NBT with gzip magic (nbtlib auto-detects via gzipped flag?)
    data = buf.getvalue()
    # nbtlib's write() writes gzipped data when the file was loaded gzipped;
    # ensure we emit gzip-compressed output regardless.
    if not data.startswith(b"\x1f\x8b"):
        with gzip.open(output_path, "wb") as out:
            out.write(data)
    else:
        with open(output_path, "wb") as out:
            out.write(data)
    print(f"Converted: {os.path.basename(input_path)} -> {os.path.basename(output_path)} "
          f"(palette={len(root.get('palette', []))}, blocks={len(root.get('blocks', []))})")


def main():
    src_dir = sys.argv[1] if len(sys.argv) > 1 else "src/main/resources/data/sexmod/structures"
    dst_dir = src_dir  # in-place
    os.makedirs(dst_dir, exist_ok=True)
    count = 0
    skipped = 0
    for fn in sorted(os.listdir(src_dir)):
        if not fn.endswith(".nbt"):
            continue
        path = os.path.join(src_dir, fn)
        # Only convert legacy (pre-1.13) structures; skip already-modern ones
        try:
            raw = gzip.decompress(open(path, "rb").read())
            f = nbtlib.File.parse(io.BytesIO(raw))
            dv = int(f.get("DataVersion", 1343))
        except Exception as e:
            print(f"SKIP {fn}: unreadable ({e})")
            skipped += 1
            continue
        if dv >= 3000:
            print(f"SKIP {fn}: already modern (DataVersion {dv})")
            skipped += 1
            continue
        convert(path, path)
        count += 1
    print(f"Done. {count} structures converted, {skipped} skipped.")


if __name__ == "__main__":
    main()
