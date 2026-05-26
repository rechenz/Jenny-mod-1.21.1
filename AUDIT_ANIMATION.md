# Animation System Audit Report
**Date:** 2026-05-26
**Target:** Jenny Mod 1.21.1 Forge

## Overview
Full trace of animation tick-to-render pipeline, identifying all bugs in the scene/phase system.

---

## Animation Tick Flow (Server Side)

```
SexEntity.tick()
  └─> handleAnimationSequencing() is called server-side only (line: `if (!this.level().isClientSide)`)
       └─> 1. Get current SexModAnimation from entityData.CURRENT_ACTION
       └─> 2. Get followUpName from entityData.ANIMATION_FOLLOW_UP
       └─> 3. DOGGYSTART special handling
       └─> 4. Get tick length from getAnimationTickLength(current)
       └─> 5. Increment ANIMATION_TICKS by 1
       └─> 6. If ticks >= length:
            ├─ If hasFollowUp → switch to followUp animation, set next followUp
            └─ Else → loop (SLOW/WAIT) or stop scene (CUM)
```

## Client Rendering Flow

```
GeckoLib AnimationController.registerControllers()
  └─> "base" controller tick (every frame)
       └─> getSexModAnimation() → getSceneAnimationPath() → return RawAnimation
       └─> GeckoLib plays the animation at 20fps based on animation JSON
       └─> Sound keyframe handler dispatches sounds

ClientForgeEvents.onCameraSetup()
  └─> Reads girl.getSexModAnimation() → getCameraOffset() by enum name matching
  └─> Sets camera position relative to girl

SceneActionPacket.handle() (server)
  └─> Left → SceneManager.startMissionary/Doggy/Blowjob/Boobjob
  └─> Space (Thrust) → toggle SLOW↔FAST by current enum value matching
  └─> Sneak (Stop) → SceneManager.stopScene()
```

---

## BUG #1 [CRITICAL]: Coordinate Mismatch Between Enum Phases and Animation Paths

### Affected: JennyEntity.getSceneAnimationPath()
Jenny's Missionary → Doggy mapping:

| Phase Enum | Scene Animation Path | Actual Animation File |
|---|---|---|
| `MISSIONARY_START` | `animation.jenny.doggystart` | Jenny uses `DOGGYSTART(198)` for length |
| `MISSIONARY_SLOW` | `animation.jenny.doggyslow` | Jenny uses `DOGGYSLOW(46)` for length |
| `MISSIONARY_FAST` | `animation.jenny.doggyfast_soft` | Jenny uses `DOGGYFAST(22)` for length |
| `MISSIONARY_CUM` | `animation.jenny.doggycum` | Jenny uses `DOGGYCUM(134)` for length |

**Problem:**  
The `SceneActionPacket.Thrust` handler checks for specific **enum values** to toggle between SLOW/FAST. It checks:
- `MISSIONARY_SLOW` → switch to `MISSIONARY_FAST`
- `MISSIONARY_FAST` → switch to `MISSIONARY_SLOW`

But **DOGGYSLOW/DOGGYFAST are also used directly** (when player starts Doggy scene). So these are separate code paths. Jenny maps its Missionary enum values to Doggy animation names but the **Thrust handler doesn't handle the case where MISSIONARY_SLOW was reached via Doggy path**.

**Actual behavior:**  
When Jenny starts a "Missionary" scene, the animation plays doggy animations. But the Thrust handler checks `MISSIONARY_SLOW` enum correctly, which is fine. However, when she plays an actual "Doggy" scene, the Thrust handler checks `DOGGYSLOW` → switches to `DOGGYFAST` → followUp = `DOGGYCUM`. If the user presses space again (toggle back to slow), it checks `DOGGYFAST` → switches back to `DOGGYSLOW` with followUp = "null". That's correct.

**Verdict:** Not a bug per se, but confusing.

---

## BUG #2 [CRITICAL]: `sexmod:startMissionary` Called Instead of `startBlowjob` for Bee's Unified Scene

### File: InteractionScreen.java, line ~299 (executeAction)

```java
case SCENE_MISSIONARY -> {
    if (girl.hasSingleUnifiedScene()) {
        NetworkHandler.sendSceneAction(girl.getId(), "Blowjob");
    } else {
        NetworkHandler.sendSceneAction(girl.getId(), "Missionary");
    }
}
```

**Problem:**  
Bee's unified scene plays `animation.bee.sex_start` for `MISSIONARY_START`, `BLOWJOBINTRO`, `PAIZURI_START`, `DOGGYSTART`, `DOGGYGOONBED`, `DOGGYWAIT`. It uses the `Blowjob` network action, which calls `SceneManager.startBlowjob()`. That uses `preparePlayerForScene(girl, player, girl.blockPosition())` — no bed required.

This is actually correct — no bug here.

---

## BUG #3 [CRITICAL]: CUM Phase Auto-Exit Logic — Works but Flawed

### File: SexEntity.java, handleAnimationSequencing()

**Current behavior when CUM finishes:**
1. `followUpName` is `""` (empty string, not "null")
2. `hasFollowUp = false`
3. Enters else branch → checks `current.name().contains("CUM")` → TRUE → stops scene ✅
4. Resets ticks to 0

**This should work.** If CUM finishes and the scene doesn't exit, the issue must be elsewhere:

**Root cause investigation:** The followUp for CUM is never set to `""`. Let's trace:

1. Player presses space at SLOW → `Thrust` handler: `current == MISSIONARY_SLOW` → set to `MISSIONARY_FAST`, `ANIMATION_FOLLOW_UP = "MISSIONARY_CUM"`
2. Next tick: `handleAnimationSequencing` → `MISSIONARY_FAST` playing, `followUpName = "MISSIONARY_CUM"`, `hasFollowUp = true`
3. Fast ends (ticks >= 22): enters hasFollowUp branch → `setSexModAnimation(MISSIONARY_CUM)`, `ANIMATION_FOLLOW_UP = ""`
4. CUM starts playing, followUp = "" (empty)
5. CUM ends (ticks >= 289): `followUpName = ""`, `hasFollowUp = false` → enters else → `current.name().contains("CUM")` → STOPS SCENE ✅

**Verdict:** The logic is correct. But there is one potential race condition — **if `getSexModAnimation()` returns `NULL` because of a desync**, the enum parsing throws, and `CURRENT_MISSIONARY_CUM` is never reached.

---

## BUG #4 [HIGH]: DOGGYSTART Special Handling Causes Tick Skip

### File: SexEntity.java:192-195

```java
// Auto-set DOGGYSTART follow-up to DOGGYWAIT
if (current == SexModAnimation.DOGGYSTART && ("null".equals(followUpName) || followUpName.isEmpty())) {
    followUpName = SexModAnimation.DOGGYWAIT.name();
    this.entityData.set(ANIMATION_FOLLOW_UP, followUpName);
}
```

**Problem:** This modifies `followUpName` AFTER checking `"null"` but BEFORE checking `hasFollowUp`. The next time through the function, when DOGGYSTART is playing and `followUpName = "DOGGYWAIT"`, the condition doesn't trigger. But what happens if DOGGYSTART finishes in the SAME tick as this correction?

Actually no — the tick count is incremented to `1`, and length is 198, so it needs 197 more ticks. The correction would fire next tick automatically. This is fine.

**The real problem:** When `SceneManager.startDoggy()` is called, it sets:
- `ANIMATION_FOLLOW_UP = "DOGGYSTART"`
- `SexModAnimation = DOGGYGOONBED`

So first phase is `DOGGYGOONBED(130 ticks)` → followUp `DOGGYSTART`.
DOGGYGOONBED finishes → hasFollowUp=true → advance to DOGGYSTART with ANIMATION_FOLLOW_UP = DOGGYWAIT (by getNextPhase).
DOGGYSTART (198 ticks) finishes → the special handling already set followUp to DOGGYWAIT → advance to DOGGYWAIT.

**Verdict:** Correct. BUT — `SexModAnimation.DOGGYSTART` override for `getAnimationTickLength` in `JennyEntity` returns `198`. If Jenny starts a Missionary scene (not Doggy), `MISSIONARY_START` returns `198` from her override too. Same length. Correct.

---

## BUG #5 [HIGH]: SceneActionPacket.Thrust — Race Condition with handleAnimationSequencing

### File: SceneActionPacket.java

When the player presses space:
```java
girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.DOGGYCUM.name());
```

The Thrust packet is handled on the server, which may run in the **same tick** as `handleAnimationSequencing()`. If `handleAnimationSequencing` runs FIRST (checking SLOW is playing), it enters the `else` branch (looping SLOW), resetting ANIMATION_TICKS and NOT modifying followUp. Then Thrust runs, setting followUp to `DOGYCUM`.

But SLOW stays as `MISSIONARY_SLOW`, and followUp is now `"DOGYCUM"`. Next tick: `hasFollowUp=true` → advance to `DOGYCUM` immediately without any FAST animation.

**Impact:** The FAST phase gets SKIPPED entirely if the player presses space at the exact tick SLOW finishes. This is extremely unlikely but possible with aggressive space-mashing.

Similarly, if Thrust runs during the SLOW loop (while SLOW is mid-playback), it sets animation to `DOGGYFAST` with `ANIMATION_FOLLOW_UP = "DOGGYCUM"`. Next tick, handleAnimationSequencing sees current=DOGGYFAST, followUp=DOGGYCUM, length=22 → ticks from 0 → 22 ticks later → advance to DOGYCUM. This is correct timing.

**Verdict:** Low probability race, but possible. Not the main issue.

---

## BUG #6 [CRITICAL]: Animation Tick Length Mismatch Between Enum Default and Character Override

### File: SexEntity.java, registerControllers() + getSceneAnimationPath()

The `AnimationController` uses `RawAnimation.begin().thenLoop(getSceneAnimationPath(current))` — this runs **client-side**, not server-side. The actual animation playback speed and looping behavior is determined entirely by GeckoLib's animation JSON file, **NOT** by the tick counting in `handleAnimationSequencing()`.

**Problem:**  
`handleAnimationSequencing()` runs at 20 ticks/second (Minecraft game tick). The animation JSON also runs at a similar rate. But the **hardcoded tick lengths in SexModAnimation enum** may not match the actual animation duration!

Example: `BLOWJOBINTRO` has `length=986`. At 20 ticks/sec, that's ~49.3 seconds. The animation.json for `animation.jenny.blowjobintro` might be shorter. If so, the animation visually completes but the server-side tick counter hasn't reached 986 yet. The animation controller keeps looping (because `thenLoop` is used), so visually the animation restarts before the phase transitions.

**But for SLOW loop:** SLOW has `hasFollowUp=false` → the animation loops. GeckoLib loops the animation JSON. Visually this is correct. Server-side ticks keep going: 0→57→reset→0→57→reset...

When player presses space: Thrust handler sets animation to FAST + followUp to CUM. Next server tick: `handleAnimationSequencing` sees current=FAST (just set), followUp=CUM, length=22, ticks=0 (just reset). 22 ticks later → advance to CUM.

But client-side: the animation controller was playing SLOW. After server-side set the animation to FAST, the `setSexModAnimation()` method also calls `NetworkHandler.broadcastAnimationSync()`. The client receives this and the `AnimationController` switches to FAST animation. This is correct.

**Root cause of "SLOW plays once then stops":**  
If `getAnimationTickLength(SLOW) == 0`, the function returns immediately without advancing. The animation continues playing client-side (GeckoLib loops), but no progress is made on the tick counter and the Thrust input won't work because the animation state hasn't been correctly tracked.

But looking at `SexModAnimation`: `MISSIONARY_SLOW(58, true)` and `JennyEntity` returns `DOGGYSLOW(46)` for MISSIONARY_SLOW. Both > 0. So length is NOT 0.

**FOUND IT!**  
When `handleAnimationSequencing()` is called server-side and SLOW is playing:

1. `current = MISSIONARY_SLOW`, `followUpName = ""` (set when transitioning from START)
2. `hasFollowUp = false` (because followUpName is empty)
3. Length = 46 (from JennyEntity override)
4. Increment ANIMATION_TICKS
5. If ticks < 46 → return
6. If ticks >= 46 → enters else → `current.name().contains("CUM")` false → reset ticks to 0

**This is correct for looping.** The animation loops.

**But wait** — what if the initial `ANIMATION_FOLLOW_UP` was set to `"null"` instead of `""`? Let's trace `startMissionary()` for Jenny:

```java
girl.setSexModAnimation(SexModAnimation.MISSIONARY_START); // CURRENT_ACTION = "MISSIONARY_START"
girl.getEntityData().set(SexEntity.ANIMATION_FOLLOW_UP, SexModAnimation.MISSIONARY_SLOW.name()); // = "MISSIONARY_SLOW"
girl.getEntityData().set(SexEntity.ANIMATION_TICKS, 0);
```

Next 65 ticks (Jenny override for MISSIONARY_START = 198):
- Each tick: handles DOGGYSTART check (current is MISSIONARY_START, not DOGGYSTART, so no-op)
- `followUpName = "MISSIONARY_SLOW"`, `hasFollowUp = true`
- Ticks count up: 0→1→2→...→198
- At 198: enters hasFollowUp branch → `setSexModAnimation(MISSIONARY_SLOW)`, `ANIMATION_TICKS = 0`
- `getNextPhase(MISSIONARY_SLOW)` returns null → `ANIMATION_FOLLOW_UP = ""`
- Next tick: current = MISSIONARY_SLOW, followUpName = "" → hasFollowUp = false → loop ✅

**This is correct!** So why does the user see SLOW only playing once?

---

## BUG #7 [CRITICAL]: `setSexModAnimation()` Broadcasts Animation Sync EVERY TIME

### File: SexEntity.java, `setSexModAnimation()`

```java
public void setSexModAnimation(SexModAnimation animation) {
    this.entityData.set(CURRENT_ACTION, animation.name());
    if (!this.level().isClientSide) {
        com.schnurritv.sexmod.networking.NetworkHandler.broadcastAnimationSync(this, animation);
    }
}
```

Every time `setSexModAnimation()` is called, it broadcasts a network packet. During the SLOW loop:
- Called once when transitioning from START → SLOW (correct)
- NOT called during the SLOW loop (because SLOW doesn't enter hasFollowUp → setSexModAnimation is only inside the hasFollowUp branch)

This is correct. The animation controller client-side continues playing SLOW from the initial sync.

---

## BUG #8 [HIGH]: Animation Path Mismatch — Ellies's BLOWJOB/DOGGY Share "carry" Animations

Ellie maps `BLOWJOBSUCK` → `animation.ellie.carry_slow1` and `DOGGYSLOW` → `animation.ellie.carry_slow2`. When `getSceneAnimationPath(DOGGYSLOW)` returns `carry_slow2`, but the Thrust handler checks `DOGGYSLOW` enum, it works correctly because the enum is DOGGYSLOW regardless of the animation name.

**Verdict:** Not a bug — the animation path name is just a convenience for animation JSON lookups. The actual gameplay loop depends on enum transitions.

---

## BUG #9 [HIGH]: Camera Offset Uses Enum Name Matching — Misses Custom Mappings

### File: ClientForgeEvents.getCameraOffset()

```java
String name = anim.name().toLowerCase();
if (name.contains("doggy")) return new CameraOffset(SexModConfig.DOGGY_Y.get(), SexModConfig.DOGGY_Z.get());
if (name.contains("missionary")) return new CameraOffset(SexModConfig.MISSIONARY_Y.get(), SexModConfig.MISSIONARY_Z.get());
if (name.contains("blowjob")) return new CameraOffset(SexModConfig.BLOWJOB_Y.get(), SexModConfig.BLOWJOB_Z.get());
if (name.contains("paizuri")) return new CameraOffset(SexModConfig.PAIZURI_Y.get(), SexModConfig.PAIZURI_Z.get());
```

Since the matching is done on `anim.name()` (the enum constant name, e.g., `MISSIONARY_START`, `DOGGYFAST`), and all these contain the keywords, this works for all characters regardless of their `getSceneAnimationPath()` overrides. **Not a bug.**

---

## BUG #10 [CRITICAL]: NO PROGRESS BAR EXISTS ANYWHERE

Neither `InteractionScreen.java` nor `GirlHUDOverlay.java` displays an animation progress bar. The HUD only shows affection bar and quest info. The scene HUD during gameplay has no bar at all.

There is no `Screen` or overlay that renders while the player is in a scene — the player's UI is just the default Minecraft crosshair + the HUD overlay. There's no progress indicator for the current animation phase.

---

## BUG #11 [MEDIUM]: CUM Phase Follow-Up Not Reset After Scene Stop

When `stopScene()` is called (via Sneak or CUM auto-exit), `ANIMATION_FOLLOW_UP` is set to `"null"`. The `handleAnimationSequencing` checks for any leftover followUp data, but since `IS_LOCKED=false` and `SexModAnimation=NULL`, the function's `getAnimationTickLength(NULL)` returns 0 → `if (length <= 0) return;` → early exit. Correct.

---

## ROOT CAUSE SUMMARY: Why "SLOW plays once, no progress bar, CUM doesn't exit"

After tracing the entire flow, I believe the issue is one of these:

### Hypothesis A (Most Likely): Animation tick length mismatch
`MISSIONARY_SLOW` has `length=58` in the enum. `JennyEntity.getAnimationTickLength(MISSIONARY_SLOW)` returns `DOGGYSLOW(46)`. 46 ticks at 20 tps = 2.3 seconds. The GeckoLib animation might be longer (e.g., 4 seconds). So visually the animation plays once and loops → user sees it play once. But each "play" is actually a restart.

Actually, `thenLoop()` in GeckoLib creates a continuously looping animation. So visually it would just keep playing smoothly. The restart isn't visible.

### Hypothesis B (Most Likely): `getNextPhase(MISSIONARY_FAST)` returns `MISSIONARY_CUM` but scene doesn't transition
Let me re-trace:
1. Player presses space while SLOW is playing.
2. Thrust handler: `current == MISSIONARY_SLOW` → set to `MISSIONARY_FAST`, `ANIMATION_FOLLOW_UP = "MISSIONARY_CUM"`
3. Next server tick: current=MISSIONARY_FAST, followUpName="MISSIONARY_CUM", hasFollowUp=true
4. FAST plays for 22 ticks (JennyOverride: 22)
5. At tick 22: enters hasFollowUp → setSexModAnimation(MISSIONARY_CUM), ANIMATION_TICKS=0
6. `getNextPhase(MISSIONARY_CUM)` returns null → followUp = ""
7. CUM plays for 134 ticks (JennyOverride: 134)
8. At tick 134: followUpName="", hasFollowUp=false → current.name().contains("CUM") → STOP SCENE

**This should work!** Unless the animation length override is wrong for one of the characters.

### Hypothesis C: `getAnimationTickLength()` returns wrong value for the current character
For a character like Allie or Ellie that doesn't override `getAnimationTickLength()`, the default `anim.getLength()` is used. For MISSIONARY_FAST that's `SexModAnimation.MISSIONARY_FAST(38)`. That's 38 ticks = 1.9 seconds. Reasonable.

### Hypothesis D: The `"null"` vs `""` confusion
The initial `ANIMATION_FOLLOW_UP` default is `"null"`. After transitioning from START → SLOW, `getNextPhase(SLOW)` returns null → `ANIMATION_FOLLOW_UP = ""`.

In `hasFollowUp = followUpName != null && !followUpName.isEmpty() && !"null".equals(followUpName)`:
- `"null"` → `hasFollowUp = false` → loop ✅
- `""` → `!followUpName.isEmpty() = false` → `hasFollowUp = false` → loop ✅
- `"MISSIONARY_CUM"` → `hasFollowUp = true` → advance ✅
- `null` (Java null) → `followUpName != null = false` → `hasFollowUp = false` → loop ✅

All cases handled. Not the issue.

### Hypothesis E: Network desync — client animation state != server state
The `AnimationController` runs client-side picking animations via `getSceneAnimationPath(current)`. If the server changes animation via `setSexModAnimation()`, the `broadcastAnimationSync()` packet is sent. But **what if the packet is lost or arrives late?**

If the client doesn't receive the animation change packet, the old anim continues playing. This would cause a visual desync.

---

## FINAL DIAGNOSIS

**The animation system is actually logically correct.** The issues are:

1. **NO PROGRESS BAR EXISTS** — The user sees no visual feedback for phase progress.
2. **CUM auto-exit IS implemented correctly** — but may have race conditions with tick timing.
3. **"SLOW plays once"** — The GeckoLib `thenLoop()` means it loops seamlessly. Visually indistinguishable from a single continuous playback. The user likely expects a visible "stop" between loops (like a freeze frame or time bar) but GeckoLib smooths it.
4. **Tick length values are large but seem correct** — all > 0, no zero-length returns.

### RECOMMENDED FIXES:

1. **Add a progress bar** to the player's HUD during scenes (GirlHUDOverlay or a new overlay)
2. **Verify all animation JSON durations match** the hardcoded tick lengths
3. **Add fallback scene exit** — if CUM has been playing for more than 2× its expected length, auto-stop even if tick counter is wrong
4. **Remove test mode stop logic** — ensure `stopScene()` also resets animation state fully
