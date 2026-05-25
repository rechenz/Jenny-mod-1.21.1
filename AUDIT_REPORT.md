# 🔍 Codebase Audit Report — Jenny Mod 1.21.1

**Date:** 2026-05-25
**Scope:** Full codebase audit (Java source + resources)
**Severity levels:** 🔴 CRITICAL | 🟡 BUG | 🟠 WARN | ✅ OK

---

## 🔴 CRITICAL (3 issues)

### C1. Jenny: Missionary animation path points to non-existent key

| Field | Value |
|-------|-------|
| **File** | `src/main/java/com/schnurritv/sexmod/entity/jenny/JennyEntity.java` |
| **Problem** | `getSceneAnimationPath()` maps `MISSIONARY_START` and `MISSIONARY_SLOW` to `animation.jenny.missionary_slow`. This animation key does NOT exist in `src/main/resources/assets/sexmod/animations/jenny/jenny.animation.json`. The file has no `animation.jenny.missionary_slow` entry. `MISSIONARY_FAST` and `MISSIONARY_CUM` both map to `animation.jenny.idle` (already incorrect fallback). |
| **Impact** | Missionary scenes for Jenny will silently fail — GeckoLib can't resolve the animation key. Falls through to default case → `animation.jenny.idle`. Jenny effectively has no working missionary mode. |
| **Fix** | Either: (a) Add `"animation.jenny.missionary_slow"` animation definition to `jenny.animation.json`, or (b) change the switch cases to point to existing animations. |

---

### C2. Manglelie: Shared bed animations referenced but missing from her `.animation.json`

| Field | Value |
|-------|-------|
| **File** | `src/main/java/com/schnurritv/sexmod/entity/manglelie/ManglelieEntity.java` |
| **Problem** | `getSceneAnimationPath()` maps `DOGGYFAST` → `animation.shared.bed_fast` and `DOGGYCUM` → `animation.shared.bed_cum`. These keys exist ONLY in `galath/galath.animation.json`, NOT in `manglelie/manglelie.animation.json`. GeckoLib loads ONE animation file per entity (based on `getModelName()`). |
| **Impact** | Doggy fast/cum scenes for Manglelie silently fall to default (= idle). |
| **Fix** | Copy `animation.shared.bed_fast` and `animation.shared.bed_cum` from `galath.animation.json` into `manglelie.animation.json`. |

---

### C3. Kobold: `getNudeGeoFileName()` not overridden — nude model == clothed model

| Field | Value |
|-------|-------|
| **File** | `src/main/java/com/schnurritv/sexmod/entity/kobold/KoboldEntity.java` |
| **Problem** | `KoboldEntity` does not override `getNudeGeoFileName()`. Inherits default from `SexEntity`, which returns `getGeoFileName()` = `"kobold"`. When `CLOTHING_STATE > 0` (nude), the model loaded is `geo/kobold/kobold.geo.json` — same as clothed. The actual nude model is at `geo/kobold/armored.geo.json`. |
| **Impact** | Kobold has no visual change when undressed in scenes. |
| **Fix** | Add `@Override public String getNudeGeoFileName() { return "armored"; }` to `KoboldEntity.java`. |

---

## 🟡 BUG (4 issues)

### B1. 6 spawn eggs missing item model JSONs

| Field | Value |
|-------|-------|
| **File** | `src/main/resources/assets/sexmod/models/item/` |
| **Problem** | 6 characters have `_spawn_egg` items registered but their item model JSONs are missing: `bia_spawn_egg.json`, `galath_spawn_egg.json`, `manglelie_spawn_egg.json`, `lucy_spawn_egg.json`, `mika_spawn_egg.json`, `momo_spawn_egg.json`. Existing egg models: `jenny`, `ellie`, `slime`, `bee`, `allie`, `goblin`, `kobold`, `cat`. |
| **Impact** | These spawn eggs will display as purple/black missing texture. |
| **Fix** | Create standard item model JSONs for all 6. Template: `{ "parent": "minecraft:item/generated", "textures": { "layer0": "sexmod:items/spawn_egg" } }`. |

---

### B2. Guide Book missing from creative tab

| Field | Value |
|-------|-------|
| **File** | `src/main/java/com/schnurritv/sexmod/entity/EntityRegistry.java` |
| **Problem** | `ModItems.GUIDE_BOOK` is registered as an item and has a crafting recipe, but is NOT added to the `SEXMOD_TAB` creative tab. |
| **Impact** | Guide Book is unobtainable via creative inventory. |
| **Fix** | Add `output.accept(ModItems.GUIDE_BOOK.get());` to the `displayItems` lambda. |

---

### B3. Duplicate network paths for Goblin actions

| Field | Value |
|-------|-------|
| **File** | `src/main/java/com/schnurritv/sexmod/client/gui/InteractionScreen.java` + `GoblinCaughtScreen.java` |
| **Problem** | `InteractionScreen` sends `SceneActionPacket("ReturnItems")` for goblin loot return, while `GoblinCaughtScreen` sends `GoblinActionPacket("return")`. Both do `goblin.returnStolenItems(player)`. Two separate paths for the same logic = maintenance hazard. |
| **Impact** | Both work but architecturally inconsistent. |
| **Fix** | Standardize to one path (recommend `GoblinActionPacket`). |

---

### B4. Bee & Allie: `getGeoFileName()` not overridden — works by coincidence

| Field | Value |
|-------|-------|
| **File** | `BeeEntity.java`, `AllieEntity.java` |
| **Problem** | These entities rely on the default `getGeoFileName()` returning `getModelName()` (i.e., `"bee"`/`"allie"`). This correctly resolves to `geo/bee/bee.geo.json` and `geo/allie/allie.geo.json`. However, this is fragile: if `getModelName()` ever returns something different, the geo path breaks. |
| **Impact** | Works correctly today. No immediate bug. |
| **Fix** | Not required but recommended for consistency: add explicit `@Override public String getGeoFileName() { return getGirlName(); }`. |

---

## 🟠 WARN (4 issues)

### W1. CatEntity: Paizuri and Doggy (non-start) phases fall through to idle

| Field | Value |
|-------|-------|
| **File** | `src/main/java/com/schnurritv/sexmod/entity/cat/CatEntity.java` |
| **Problem** | Cat's `getSceneAnimationPath()` does not handle `PAIZURI_SLOW`, `PAIZURI_FAST`, `PAIZURI_CUM`, `DOGGYSLOW`, `DOGGYFAST`, or `DOGGYCUM` — all fall to `default` → `animation.cat.idle`. This is intentional by design (Cat only has sitting/touching animations), but it means those scene options have no visual playback for Cat. |
| **Impact** | Some scene types show idle animation instead of unique performance. |
| **Fix** | Design choice. No fix needed unless new animations are created for Cat. |

---

### W2. `AnimationSyncPacket.readUtf(64)` — max length is generous enough

| Field | Value |
|-------|-------|
| **File** | `AnimationSyncPacket.java` |
| **Check** | `readUtf(64)` — the longest animation enum name is `MISSIONARY_START` (16 chars), well within 64. SAFE. |
| **Status** | ✅ OK |

---

### W3. `MovementStatePacket.readUtf(32)` and `SceneActionPacket.readUtf(32)` — safe

Max action strings: "ReturnItems" (11 chars), "QuestTurnin" (11 chars), "Missionary" (10 chars). All well within 32.

**Status:** ✅ OK

---

### W4. `GoblinActionPacket.readUtf(32)` — safe

Action strings: "return" (6), "scene" (5). Within 32.

**Status:** ✅ OK

---

## ✅ OK — Verified Correct (12 items)

1. **MovementStatePacket** — correctly sets `MOVEMENT_STATE` AND auto-sets `MASTER_UUID` on "FOLLOW"
2. **SceneActionPacket** — correctly handles all action strings (Missionary, Doggy, Blowjob, Boobjob, Stop, QuestStart, QuestTurnin, ReturnItems, Thrust)
3. **NetworkHandler** — ALL 4 packet types (AnimationSync, MovementState, SceneAction, GoblinAction) registered
4. **ModEventBusEvents** — ALL 14 entities registered with attributes AND renderers
5. **EntityRegistry** — ALL 14 entities registered with spawn eggs
6. **Creative tab** — contains all 14 spawn eggs + gift items + utility items + galath coin
7. **GoblinEntity** — MOVEMENT_STATE reference via full path `com.schnurritv.sexmod.entity.SexEntity.MOVEMENT_STATE` is valid public static field access
8. **GalathEntity** — `this.jumping` correctly inherited from `LivingEntity`; `PlayerRideableJumping` pattern is standard
9. **Bee/Allie/Kobold geo paths** — default `getGeoFileName()` correctly resolves to existing files (except Kobold nude → see C3)
10. **Lucy/Mika/Momo** — animation files contain `animation.default.*` entries matching their `getAnimationPrefix()` override
11. **CatEntity** — all referenced animation keys exist in `cat.animation.json`; `getIdleAnimationPath()` correctly overrides
12. **zh_cn.json** — complete translation with same keys as en_us.json

---

## Summary

| Severity | Count | Key Issues |
|----------|-------|------------|
| 🔴 CRITICAL | 3 | Jenny missionary broken, Manglelie shared anims missing, Kobold no nude model |
| 🟡 BUG | 4 | 6 missing egg models, GuideBook not in creative tab, duplicate network paths, fragile geo defaults |
| 🟠 WARN | 1 | Cat incomplete scene coverage (design choice) |
| ✅ OK | 12 | Core systems verified working |

**Recommended priority order for fixes:** C1 → C3 → C2 → B1 → B2 → B3
