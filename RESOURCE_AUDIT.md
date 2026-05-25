# 🕵️ Resource Audit Report — Jenny Mod 1.21.1
## Generated: 2026-05-25 | Characters: 14

---

## ✅ COMPLETE (Geo + Anim + Texture + Sounds + Code)

| # | Character | Geo | Anim | Texture | Sounds | Behavior | Source |
|---|-----------|-----|------|---------|--------|----------|--------|
| 1 | **Jenny** | dressed, nude | ✅ | jenny.png, hand×2 | 15 cats, 100+ files | Gaming idle chatter | 1.12.2 JAR |
| 2 | **Ellie** | dressed, nude | ✅ | ellie.png, hand×2 | 17 cats, 90+ files | Combat zone buff | 1.12.2 JAR |
| 3 | **Allie** | allie, armored, lamp | +lamp | allie.png, hand, lamp | 17 cats, 100+ files | AOE heal + regen | 1.12.2 JAR |
| 4 | **Bia** | dressed, nude | ✅ | bia.png, hand | 7 cats, 20+ files | Night Vision buff | 1.12.2 JAR |
| 5 | **Bee** | armored, bee | ✅ | bee.png, hand | ⚠️ MISSING | Haste buff | 1.12.2 JAR |
| 6 | **Cat** | cat | ✅ | cat.png, hand, robot | ⚠️ MISSING | Speed buff | 1.12.2 JAR |
| 7 | **Goblin** | armored, goblin | ✅ | goblin.png, goblinBK | ⚠️ MISSING | Strength buff | 1.12.2 JAR |
| 8 | **Kobold** | armored, kobold, egg, staff | +egg +staff | full set ×5 | 11 cats, 30+ files | AOE mining buff | 1.12.2 JAR |
| 9 | **Slime** | armored, dressed, nude | ✅ | slime.png, hand | ⚠️ MISSING | Jump boost | 1.12.2 JAR |
| 10 | **Galath** 🆕 | galath, coin, con_mang | +coin | galath.png, energy_ball, hand | 13 cats, 40+ files | Void AOE + weakness | 1.12.2 JAR |
| 11 | **Manglelie** 🆕 | manglelie | ✅ | manglelie.png | ⚠️ MISSING | XP orb spawn + haste | 1.12.2 JAR |
| 12 | **Lucy** 🆕 | dressed, nude | ✅ | lucy.png | 14 cats, 60+ files | Saturation + heal aura | Fabric 1.21.11 JAR |
| 13 | **Mika** 🆕 | dressed, nude | ✅ | mika.png | 16 cats, 55+ files | Luck aura | Fabric 1.21.11 JAR |
| 14 | **Momo** 🆕 | dressed, nude | ✅ | momo.png | 7 cats, 20+ files | Passive XP generation | Fabric 1.21.11 JAR |

---

## 🎵 Sound Library Inventory

### Characters WITH full voice lines:
- **Jenny**: aftersessionmoan, ahh, bjmoan, giggle, happyoh, heavybreathing, hmph, huh, lightbreathing, lipsound, mmm, moan, sadoh, sigh
- **Ellie**: +cometomommy, +goodboy, +mommyhorny
- **Allie**: +scawy (unique)
- **Bia**: +breath, +hey (unique)
- **Kobold**: +haa, +heymaster, +interested, +master, +orgasm, +sad, +yep
- **Galath**: +aaa, +breathing, +dialog, +lightcharge, +strongcharge, +orgasm, +uuh
- **Lucy**: full set from Fabric JAR
- **Mika**: full set from Fabric JAR (+cometomommy, +goodboy, +mommyhorny)
- **Momo**: full set from Fabric JAR (+breath, +hey)

### Characters WITHOUT voice lines (no sounds in any source):
- Bee, Cat, Goblin, Slime, Manglelie
- **Total missing vocals**: 5/14 characters (35.7%)

### Misc Sounds (from 1.12.2 JAR):
bedrustle, beew, belljingle, clap, cuminflation, eat, fart, flap, inserts, jump, plob, pounding, pyro, scream, shatter, slap, slide, smallinserts, touch, weoweo

---

## 🏗️ Structure Templates (NBT) — 7 files
| File | Presumed Owner |
|------|---------------|
| alex.nbt | Alex (unimplemented) |
| bia.nbt | Bia |
| ellie.nbt | Ellie |
| goblin.nbt | Goblin |
| jenny.nbt | Jenny |
| luna.nbt | Luna (no entity yet) |
| ssa.nbt | SSA (unimplemented) |

⚠️ NBT templates are 1.12.2 format — may need conversion for 1.21.1

---

## ⚠️ Known Gaps

### Characters with models in old JAR but no code:
- **Luna**: Has NBT structure + full voice library (15 sound categories) but NO geo/animation/texture models — voice-only
- **Galath/Manglelie/Lucy/Mika/Momo**: ✅ NOW IMPLEMENTED

### Fabric-only features NOT ported:
- Settlement hub block system (building tags, carved pumpkin)
- Housing assignment system
- Player penis texture/model
- Keyframe event system (python scripts in Fabric JAR)
- Bow/Cum button GUI sprites

---

## 📊 JAR Size Evolution
| Version | Size | Delta |
|---------|------|-------|
| Original port | 39 MB | — |
| After UI/Quest/HUD | 40.9 MB | +1.9 MB (code) |
| After asset merge | 53.4 MB | +12.5 MB (sounds!) |

---

## 🎯 What's Next (Priority)

1. **HIGH**: Record or source placeholder sounds for Bee/Cat/Goblin/Slime/Manglelie
2. **HIGH**: Programmatic structure generation (NBT conversion unreliable)
3. **MEDIUM**: Luna entity (has sounds + structure but no model — needs custom geo)
4. **MEDIUM**: Fabric settlement/block features
5. **LOW**: Keyframe event system (Python scripts in Fabric JAR for advanced animation control)
