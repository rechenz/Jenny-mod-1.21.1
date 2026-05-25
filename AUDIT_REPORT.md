# Audit Report — Jenny Mod 1.21.1 Forge

**Generated:** 2026-05-25  
**Scope:** `src/main/java/com/schnurritv/sexmod/`  
**Targets:** Geo/Animation paths, NBT sync, null safety, config, networking, entity registration, client/server split  

---

## 1. Geo/Animation Resource Path Mismatches

### 1.1 `GirlModel.getModelResource()` — Geo filename assumes same folder as model name

**File:** `client/model/GirlModel.java:14`  
**Severity:** CRITICAL  

The path is built as:
```java
"geo/" + name + "/" + geoFile + ".geo.json"
```

Where `name = getModelName()` (returns `getGirlName()` = `"ellie"`, `"slime"`, etc.) and `geoFile = getGeoFileName()` (returns `"dressed"`, `"nude"`).

**For Ellie, Slime:** `getGeoFileName()` returns `"dressed"`, so path becomes:
```
geo/ellie/dressed.geo.json → ✅ exists
geo/slime/dressed.geo.json → ✅ exists
```

**For Allie:** `getGeoFileName()` is NOT overridden — defaults to `getModelName()` = `"allie"`, so path becomes:
```
geo/allie/allie.geo.json → ✅ exists
```

But `getNudeGeoFileName()` also defaults to `getGeoFileName()` = `"allie"`. When CLOTHING_STATE > 0, it loads:
```
geo/allie/allie.geo.json → ❌ should be geo/allie/armored.geo.json
```
AllieEntity doesn't override `getNudeGeoFileName()`, so stripping uses `allie.geo.json` instead of `armored.geo.json`. Allie has an `armored.geo.json` but no `nude.geo.json`.

**For Bee:** `getGeoFileName()` is NOT overridden — defaults to `"bee"`:
```
geo/bee/bee.geo.json → ✅ exists
```
But `getNudeGeoFileName()` also defaults to `"bee"`, so nude:
```
geo/bee/bee.geo.json → ❌ should be geo/bee/armored.geo.json
```
Bee has `armored.geo.json` but no `nude.geo.json`. Same bug as Allie.

**For Cat:** `getGeoFileName()` is NOT overridden — defaults to `"cat"`:
```
geo/cat/cat.geo.json → ✅ exists
```
`getNudeGeoFileName()` also defaults to `"cat"`. Cat has no `nude.geo.json` or `armored.geo.json`, so CLOTHING_STATE > 0 will load the dressed model anyway.

**For Goblin:** `getGeoFileName()` is NOT overridden — defaults to `"goblin"`:
```
geo/goblin/goblin.geo.json → ✅ exists
```
`getNudeGeoFileName()` also defaults to `"goblin"`. Goblin has no nude/armored variant. Stripping just reloads the same model.

**For Kobold:** `getGeoFileName()` is NOT overridden — defaults to `"kobold"`:
```
geo/kobold/kobold.geo.json → ✅ exists
```
`getNudeGeoFileName()` also defaults to `"kobold"`. Kobold has no nude/armored variant.

### 1.2 `GirlModel.getAnimationResource()` — Animation path assumes flat file

**File:** `client/model/GirlModel.java:24`  
**Severity:** BUG  

```java
"animations/" + name + ".animation.json"
```

This assumes every entity's animation is a single file at the root of `animations/`. Checking actual files:

| Entity | Path built by code | Actual file | Match |
|--------|-------------------|-------------|-------|
| jenny | `animations/jenny.animation.json` | `animations/jenny/jenny.animation.json` | ❌ **Mismatch!** |
| ellie | `animations/ellie.animation.json` | `animations/ellie/ellie.animation.json` | ❌ **Mismatch!** |
| allie | `animations/allie.animation.json` | `animations/allie/allie.animation.json` | ❌ **Mismatch!** |
| bee   | `animations/bee.animation.json` | `animations/bee/bee.animation.json` | ❌ **Mismatch!** |
| bia   | `animations/bia.animation.json` | `animations/bia/bia.animation.json` | ❌ **Mismatch!** |
| cat   | `animations/cat.animation.json` | `animations/cat/cat.animation.json` | ❌ **Mismatch!** |
| goblin| `animations/goblin.animation.json` | `animations/goblin/goblin.animation.json` | ❌ **Mismatch!** |
| kobold| `animations/kobold.animation.json` | `animations/kobold/kobold.animation.json` | ❌ **Mismatch!** |
| slime | `animations/slime.animation.json` | `animations/slime/slime.animation.json` | ❌ **Mismatch!** |

**ALL 9 entities** will fail to load animations because GeckoLib resolves relative paths. The animation file is at e.g. `animations/jenny/jenny.animation.json` but the code requests `animations/jenny.animation.json`. With GeckoLib's resource loader, this means **no animations will play** — entities will render as T-pose.

**Root cause:** `GirlModel.getAnimationResource()` uses `name` directly but the actual files are in subdirectories named after the entity. The path should be `"animations/" + name + "/" + name + ".animation.json"`.

### 1.3 Inconsistent geo folder naming — "dressed/nude" vs "jennydressed/jennynude" vs "biadressed/bianude"

See 1.1 above for details. Summary:

| Entity | getGeoFileName() | Nude file | Nude exists? |
|--------|------------------|-----------|-------------|
| Jenny | `jennydressed` | `jennynude` | ✅ |
| Ellie | `dressed` | `nude` | ✅ |
| Slime | `dressed` | `nude` | ✅ |
| Bia   | `biadressed` | `bianude` | ✅ |
| Allie | `allie` (default) | `allie` (default, should be `armored`) | ❌ |
| Bee   | `bee` (default) | `bee` (default, should be `armored`) | ❌ |
| Cat   | `cat` (default) | `cat` (default, no nude exists) | ⚠️ |
| Goblin| `goblin` (default) | `goblin` (default, no nude exists) | ⚠️ |
| Kobold| `kobold` (default) | `kobold` (default, no nude exists) | ⚠️ |

---

## 2. NBT / EntityData Sync Issues

### 2.1 AffectionData NOT synced to client after reading from NBT

**File:** `entity/BaseGirlEntity.java:88-94` (`readAdditionalSaveData`)  
**Severity:** BUG  

```java
if (compound.contains("AffectionData")) {
    affectionData.fromNBT(compound.getCompound("AffectionData"));
    syncAffection();  // ← only updates AFFECTION_VALUE entity data
}
```

`syncAffection()` only syncs `affectionData.getAffection()`. The following AffectionData fields are **never synced** to the client:
- `dailyGifts` — affects gift UI display
- `lastGiftDay` — used for daily gift calculation
- `lastDecayDay` — client can't predict decay
- `unlockedScenes` — bitmask for scene unlocking
- `ownerUUID` — affects relationship display

The consequence: `getAffection()` (the entity data version) is available on client, but `affectionData.getOwnerUUID()`, `affectionData.getDailyGifts()` etc. are always their defaults (0, 0, "", etc.) on the client side.

### 2.2 Daily gift reset has potential race condition

**File:** `relationship/AffectionData.java:53-60`  
**Severity:** WARN  

```java
public boolean canGiveGift(long currentDay, int dailyLimit) {
    if (currentDay != lastGiftDay) {
        dailyGifts = 0;  // ← mutates state in a getter
    }
    return dailyGifts < dailyLimit;
}
```

`canGiveGift()` mutates `dailyGifts` state as a side effect. If called multiple times in one tick, it resets `dailyGifts` each time. This is called from `BaseGirlEntity.handleGift()` after the check: `if (!affectionData.canGiveGift(...))`. The actual `canGiveGift` check doesn't mutate state in the calling code's flow since it happens before `recordGift()`, but `canGiveGift` mutates `lastGiftDay` / `dailyGifts` without going through `recordGift()`. The `recordGift()` method also resets dailyGifts independently. This is messy but not crash-causing.

### 2.3 NBT tag name inconsistency with EntityData key

**File:** `entity/SexEntity.java` (defineSynchedData) vs `entity/BaseGirlEntity.java` (readAdditionalSaveData)  
**Severity:** WARN  

The `MOVEMENT_STATE` entity data key is named `"MovementState"` in NBT save (line 73) and NBT load (line 90), which is fine. But the `CURRENT_ACTION` key is named `"CurrentAction"` in NBT — note the case difference: defined as `CURRENT_ACTION` in the code but the NBT key is `"CurrentAction"` — consistent between save/load, so this is fine.

---

## 3. Null Safety Issues

### 3.1 `SexModMoveToTargetGoal.canUse()` — No bounds check on split array

**File:** `entity/ai/SexModMoveToTargetGoal.java:23-28`  
**Severity:** CRITICAL  

```java
String[] parts = targetPosStr.split("\\|");
this.x = Double.parseDouble(parts[0]);
this.y = Double.parseDouble(parts[1]);
this.z = Double.parseDouble(parts[2]);
```

If `targetPosStr` contains fewer than 3 pipe-delimited parts (e.g., it's malformed or something writes `"0|0"`), this throws `ArrayIndexOutOfBoundsException`. The only protection is the `"0|0|0".equals` check above, but any other malformed string crashes the entity tick.

### 3.2 `BaseGirlEntity.handleGift()` — `DialogueDB.getRandom("gift_limit_reached")` can return null

**File:** `entity/BaseGirlEntity.java:119`  
**Severity:** BUG  

```java
player.displayClientMessage(Component.literal(
    "<" + getGirlName() + "> " + DialogueDB.getRandom("gift_limit_reached")), false);
```

`DialogueDB.getRandom()` returns `"..."` only if the key exists but has empty lines, or `null` if `DIALOGUES.get()` returns null. The `getOrDefault` fallback means it returns the same null. However, the static init block includes `put("gift_limit_reached", ...)` so this particular key is fine. But `DialogueDB.getGiftReaction()` calls `getRandom(key, girlName)` which uses `String.format(key, args)` where key is `"%s_gift_love"` etc. If `girlName` is null, this throws `NullPointerException`.

### 3.3 `SexModFollowGoal.maybeTeleport()` — NPE on `this.owner`

**File:** `entity/ai/SexModFollowGoal.java:68`  
**Severity:** BUG  

`owner` is only set in `canUse()` and can be set to `null` (when `getPlayerByUUID` returns null). Although `canUse()` returns false when owner is null, there's a gap: if the player disconnects between `canUse()` and `tick()`, `owner` is still the old reference. The `tick()` method uses `this.owner` without null check. If the player logs out, `canUse()` would have returned false and the goal would stop, but if `owner` becomes null after `start()` is called (player logout during pathfinding), NPE in `tick()`.

### 3.4 `EllieEntity.findOwner()` — NPE from UUID.fromString

**File:** `entity/ellie/EllieEntity.java:35`  
**Severity:** WARN  

```java
String uuid = this.entityData.get(MASTER_UUID);
if (uuid.isEmpty()) return null;
try { return this.level().getPlayerByUUID(java.util.UUID.fromString(uuid)); }
catch (Exception e) { return null; }
```

The empty string check doesn't protect against whitespace-only strings or other invalid formats. The try-catch handles it but silently swallows all exceptions. Silent catch is poor practice for debugging.

### 3.5 `GoblinEntity.tick()` — No bounds check on inventory slot access

**File:** `entity/goblin/GoblinEntity.java:30`  
**Severity:** WARN  

```java
ItemStack existing = inventory.getStackInSlot(i);
```
The loop goes `for (int i = 0; i < 7; i++)` which matches the 7-slot inventory. Fine, but during item stack merging, `stack.shrink(toAdd)` could result in a negative count if `toAdd` calculation overflows (extremely unlikely with vanilla stack sizes, but technically possible with modded items).

### 3.6 `SexModFollowGoal.canTeleportTo()` — Missing `girl` null check

**File:** `entity/ai/SexModFollowGoal.java:84`  
**Severity:** WARN  

Minor — `this.girl` is final and set in the constructor, but `canTeleportTo` calls `this.girl.level().noCollision(this.girl, ...)` without checking if the girl is still alive/loaded.

---

## 4. Config Field Access Issues

### 4.1 `CameraConfigScreen` uses deprecated `SexModConfig.SPEC.save()` — references deprecated field

**File:** `client/gui/CameraConfigScreen.java:52`  
**Severity:** WARN  

```java
SexModConfig.SPEC.save();
```

`SPEC` is `@Deprecated` and aliased to `CLIENT_SPEC`. This works but should use `SexModConfig.CLIENT_SPEC.save()` directly.

### 4.2 `ClientForgeEvents.getCameraOffset()` uses deprecated legacy fields

**File:** `client/ClientForgeEvents.java:103-107`  
**Severity:** WARN  

Uses `SexModConfig.DOGGY_Y.get()` etc. These are correctly the same as `SexModConfig.CAM_DOGGY_Y` via the deprecated aliases, but the codebase is self-inconsistent — new config uses `CAM_*` names but client code relies on deprecated `@Deprecated` aliases.

### 4.3 All config fields properly registered — no orphan fields

**Severity:** ✅ OK  

All `public static final ForgeConfigSpec.*Value` fields in `SexModConfig` are defined in their respective `static` builder blocks. The config file uses the correct data types and ranges for all fields. Server/Client split is correct.

---

## 5. Network Packet Issues

### 5.1 Packet encode/decode uses `readUtf()` with no size limit

**File:** `networking/AnimationSyncPacket.java:22`, `MovementStatePacket.java:20`, `SceneActionPacket.java:20`  
**Severity:** BUG  

```java
buf.writeUtf(msg.animationName);
...
buf.readUtf();  // ← no max length!
```

`FriendlyByteBuf.readUtf()` with no argument defaults to a max of `32767` characters. While animation names are short (enum names), the `SceneActionPacket` and `MovementStatePacket` pass arbitrary strings from the client. This is a **theoretical DoS vector** if a hacked client sends a 32KB string.

### 5.2 `AnimationSyncPacket.handle()` — direct Minecraft.getInstance() call

**File:** `networking/AnimationSyncPacket.java:30`  
**Severity:** WARN  

```java
net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
```

This is inside a packet that's registered as `PLAY_TO_CLIENT` only, so it's fine — this packet never runs on the server. But if the registration direction changes, this would crash the server with a `ClassNotFoundException` or `ClassLoader` issue. Consider adding a `DistExecutor` check or annotating the packet class with `@OnlyIn(Dist.CLIENT)`.

### 5.3 SceneActionPacket handles "Strut" — unknown action silently ignored

**File:** `networking/SceneActionPacket.java:26-60`  
**Severity:** WARN  

The switch statement only handles known actions. If an unknown action string is sent (e.g. "Strut" typo from a hacked client), nothing happens. No warning or error logged. This is stealthy but acceptable for a mod.

### 5.4 NetworkHandler — all 3 packets registered, no orphan handlers

**Severity:** ✅ OK  

Registration at `NetworkHandler.java:17-36` matches all three packet classes. All encoder/decoder/handler method references resolve correctly. No missing packet registrations.

---

## 6. Entity Registration Issues

### 6.1 All 9 entities registered with renderers — all correct

**File:** `entity/EntityRegistry.java`, `events/ModEventBusEvents.java`  
**Severity:** ✅ OK  

All 9 entities (`JENNY`, `ELLIE`, `SLIME`, `BEE`, `BIA`, `ALLIE`, `GOBLIN`, `KOBOLD`, `CAT`) are registered in `EntityRegistry` and have corresponding renderers in `ModEventBusEvents.ClientEvents.registerRenderers()`. All 9 have `EntityAttributeCreationEvent` entries in `entityAttributes()`.

### 6.2 EntityRegistry has duplicate `MOD_ITEMS` reference

**File:** `entity/EntityRegistry.java:15-16`  
**Severity:** WARN  

```java
public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
public static final DeferredRegister<Item> MOD_ITEMS = ModItems.ITEMS;
```

And in `register()`:
```java
ITEMS.register(bus);
MOD_ITEMS.register(bus);
```

Two `DeferredRegister<Item>` instances registered to the same event bus. The `ITEMS` register only contains spawn eggs; `MOD_ITEMS` contains gifts and utility items. This works, but they could have been consolidated into one `DeferredRegister`. It's functionally correct, just slightly redundant.

---

## 7. Client/Server Split Issues

### 7.1 `InteractionScreen.handleAction()` — Direct entity data set on client

**File:** `client/gui/InteractionScreen.java:122`  
**Severity:** BUG  

```java
girl.getEntityData().set(SexEntity.MASTER_UUID, net.minecraft.client.Minecraft.getInstance().player.getUUID().toString());
```

Entity data on the client is **not authoritative** — changes made via `set()` on the client side will be overwritten by the next server sync. This line attempts to set `MASTER_UUID` on the client, but it has no real effect since the server will overwrite it. The actual authoritative set happens in `MovementStatePacket.handle()` on the server side during `"FOLLOW"` processing.

However, this client-side set means the UI might momentarily show the correct owner before the server syncs. Not a crash, but misleading.

### 7.2 `ClientForgeEvents.getCameraOffset()` uses deprecated field aliases — client-only context correct

**File:** `client/ClientForgeEvents.java:103-107`  
**Severity:** ✅ OK (contextually)  

The file is annotated `@Mod.EventBusSubscriber(value = Dist.CLIENT)`, so all event handlers run only on the client. The deprecated field access is fine.

### 7.3 `SexEntity.tick()` — Server-side method `handleAnimationSequencing()` called

**File:** `entity/SexEntity.java:103-107`  
**Severity:** ✅ OK  

```java
if (this.level().isClientSide) {
    // clear equipment on client
} else {
    handleAnimationSequencing(); // ← server only
}
```

Animation sequencing is correctly handled only on the server, which broadcasts the results via `setSexModAnimation()` → `NetworkHandler.broadcastAnimationSync()`.

### 7.4 `SexEntity.onCustomInstruction()` — Sound playing is guarded with `!this.level().isClientSide`

**File:** `entity/SexEntity.java:182`  
**Severity:** ✅ OK  

All `playSound`, `playCoSound`, `playMiscSound` methods check `if (!this.level().isClientSide) return;` which correctly restricts sound playing to the client side. However, note the double-negative: these methods **only** play on the client side. The `onCustomInstruction` method is called from the `SoundKeyframeHandler` which only fires on the client (guarded at line 167). Correct.

### 7.5 `BaseGirlEntity.mobInteract()` — `DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ...)` for screen opening

**File:** `entity/BaseGirlEntity.java:113`  
**Severity:** ✅ OK  

The interaction screen is correctly opened only on the client side.

### 7.6 Camera setup uses reflection — fragile across Minecraft updates

**File:** `client/ClientForgeEvents.java:147-157`  
**Severity:** WARN  

```java
try {
    java.lang.reflect.Method setPosMethod = event.getCamera().getClass().getDeclaredMethod("m_90568_", double.class, double.class, double.class);
    setPosMethod.setAccessible(true);
    setPosMethod.invoke(event.getCamera(), camX, camY, camZ);
} catch (Exception e1) {
    try {
        java.lang.reflect.Method setPosMethod = event.getCamera().getClass().getDeclaredMethod("setPosition", double.class, double.class, double.class);
        setPosMethod.setAccessible(true);
        setPosMethod.invoke(event.getCamera(), camX, camY, camZ);
    } catch (Exception e2) {
        // ignore
    }
}
```

Falls back from obfuscated name `m_90568_` to mapped name `setPosition`. Uses reflection to bypass the `Camera.setPosition()` method which should be public API in 1.21.1. The `EntityRenderState` camera API in 1.21.1 provides `setPosition` publicly. This is fragile.

### 7.7 `GirlRenderer` reflection to clear render layers

**File:** `client/renderer/GirlRenderer.java:27-38`  
**Severity:** WARN  

Uses reflection to access `renderLayers` field from `GeoEntityRenderer` or `GeoRenderer`. This is GeckoLib version-dependent and could break on updates.

---

## 8. Additional Issues

### 8.1 AffectionData.getGreeting() calls DialogueDB.getRandom() with problematic format

**File:** `relationship/AffectionData.java:121-127`  
**Severity:** BUG  

```java
case INTIMATE -> DialogueDB.getRandom("%s_intimate_greeting", girlName);
```

`DialogueDB.getRandom()` does:
```java
String formattedKey = String.format(key, args);
List<String> lines = DIALOGUES.getOrDefault(formattedKey, DIALOGUES.get(key));
```

So `formattedKey` becomes e.g. `"jenny_intimate_greeting"`, which exists. But the fallback to `DIALOGUES.get(key)` where key is `"%s_intimate_greeting"` will return null since there's no such entry. This fallback is never actually reached for greetings since the formatted key always matches, but it's a code smell.

### 8.2 `GirlRenderer.renderRecursively` — pushPose without matching pop on scene exit edge case

**File:** `client/renderer/GirlRenderer.java:83-92`  
**Severity:** BUG  

```java
if (inScene && bone.getParent() == null) {
    String name = bone.getName().toLowerCase();
    poseStack.pushPose();
    // ... translate ...
    shifted = true;
}
// ...
if (shifted) {
    poseStack.popPose();
}
```

If a bone's parent is null but it's not the root bone of the model when NOT in scene, `shifted` could be true when it shouldn't be. However, in practice, GeckoLib models always have a single root bone. This is correct for current models but fragile if the geo.json structure changes.

### 8.3 `JennyEntity.tick()` — `getAffectionData().getLevel()` called every tick on server, but `getLevel()` reads from the field, not the synced data

**File:** `entity/jenny/JennyEntity.java:35`  
**Severity:** WARN  

```java
DialogueDB.getRandom("jenny_" + getAffectionData().getLevel().name().toLowerCase() + "_greeting");
```

This reads from the `affectionData` field directly (server-side), so it's correct. The `getLevel()` method reads `this.affection` which is kept in sync with the synced data. No client confusion here.

### 8.4 `AffectionData` affinity level enums used for dialog keys — inconsistent naming

**File:** `relationship/AffectionData.java:85-103`  
**Severity:** WARN  

`AffectionLevel` enum names: `STRANGER`, `ACQUAINTED`, `FRIENDLY`, `CLOSE`, `INTIMATE`. These are used as `getLevel().name().toLowerCase()` to build dialog keys in `JennyEntity`. The dialog keys in `DialogueDB` match: `jenny_stranger_greeting`, `jenny_acquainted_greeting`, `jenny_friendly_greeting`, `jenny_close_greeting`, `jenny_intimate_greeting`. These are consistent for Jenny, but no other entity uses level-based greeting keys — only Jenny and only in `tick()`. The AffectionData has a `getGreeting()` method that's never called.

### 8.5 DialogueDB static init ordering — static blocks before field declaration

**File:** `relationship/DialogueDB.java`  
**Severity:** WARN  

The `initialized = true` is set in the last `static {}` block, but static blocks in Java execute in order of appearance. The map is populated by static blocks interleaved with comments. This is functionally correct because `put()` writes to the already-initialized `DIALOGUES` map, and `initialized` is only set once at the end.

---

## Summary

### CRITICAL (2)
| # | File:Line | Description |
|---|-----------|-------------|
| 1.2 | `client/model/GirlModel.java:24` | All 9 entity animation paths are wrong — `"animations/%s.animation.json"` instead of `"animations/%s/%s.animation.json"`. **No animations will play.** |
| 3.1 | `entity/ai/SexModMoveToTargetGoal.java:23-28` | No array bounds check on `TARGET_POS` split. Malformed data causes crash. |

### BUG (7)
| # | File:Line | Description |
|---|-----------|-------------|
| 1.1 | `client/model/GirlModel.java:14` | Allie & Bee don't override `getNudeGeoFileName()` — nude/armored model won't load when stripping. |
| 2.1 | `entity/BaseGirlEntity.java:91-94` | AffectionData fields (dailyGifts, ownerUUID, unlockedScenes, etc.) never synced to client. |
| 3.2 | `entity/BaseGirlEntity.java:119` | Potential NPE in `DialogueDB.getRandom()` if girlName is null. |
| 3.3 | `entity/ai/SexModFollowGoal.java:62-66` | `owner` can be null in `tick()` if player disconnects mid-path. |
| 5.1 | `networking/*Packet.java` | `readUtf()` without max length — theoretical DoS vector. |
| 7.1 | `client/gui/InteractionScreen.java:122` | Client-side `entityData.set()` for MASTER_UUID is non-authoritative, will be overwritten by server. |
| 8.1 | `relationship/AffectionData.java:121-127` | `getGreeting()` fallback to unformatted key `"%s_intimate_greeting"` returns null. |

### WARN (10)
| # | File:Line | Description |
|---|-----------|-------------|
| 2.2 | `relationship/AffectionData.java:53-60` | `canGiveGift()` mutates state as side effect. |
| 3.4 | `entity/ellie/EllieEntity.java:35` | Silent catch of all exceptions in `findOwner()`. |
| 3.5 | `entity/goblin/GoblinEntity.java:30` | Item stack shrink could underflow with modded items. |
| 4.1 | `client/gui/CameraConfigScreen.java:52` | Uses deprecated `SexModConfig.SPEC`. |
| 4.2 | `client/ClientForgeEvents.java:103-107` | Uses deprecated config field aliases. |
| 5.2 | `networking/AnimationSyncPacket.java:30` | Direct `Minecraft.getInstance()` without `@OnlyIn(Dist.CLIENT)`. |
| 6.2 | `entity/EntityRegistry.java:15-16` | Two `DeferredRegister<Item>` instances on the same bus. Redundant. |
| 7.6 | `client/ClientForgeEvents.java:147-157` | Reflection on Camera internals — fragile across versions. |
| 7.7 | `client/renderer/GirlRenderer.java:27-38` | Reflection on GeckoLib render layers — fragile on GeckoLib version changes. |
| 8.4 | `relationship/AffectionData.java:85-103` | `AffectionData.getGreeting()` is never called — dead code. |

### ✅ OK
- Entity registration: 9/9 correct
- Renderer registration: 9/9 correct
- Attribute creation: 9/9 correct
- Config fields: all properly registered with correct types/ranges
- Packet registration: 3/3 correct
- Client/Server split in mobInteract: correct (DistExecutor)
- Animation sound keyframe handler: correct (client only guard)
- NBT save/load: all fields consistent between save and load

---

## Recommended Fixes (Priority Order)

1. **Fix animation paths** in `GirlModel.getAnimationResource()` — change to `"animations/%s/%s.animation.json"` (CRITICAL, all 9 entities broken)
2. **Add array bounds check** in `SexModMoveToTargetGoal.canUse()` (CRITICAL, crash risk)
3. **Override `getNudeGeoFileName()`** in AllieEntity and BeeEntity to return `"armored"` (BUG)
4. **Sync more AffectionData fields** to client or add a dedicated sync packet (BUG)
5. **Add readUtf() max length** in all packet decoders (BUG)
6. **Remove deprecated field usage** in camera config screen and client events (WARN)
7. **Fix reflection** in renderer and camera setup to use public API where available (WARN)
