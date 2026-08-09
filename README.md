# Jenny Mod — 1.21.1 Port

> ✅ **Project Status: Alive & Shipping (2026-08-09 revival)**
> Full 1.12.2 feature port to Minecraft 1.21.1 + Forge, now with working
> worldgen structures, dedicated-server support, and the complete item set.

## What Works

Full 1.12.2 feature rewrite based on the original decompiled source +
GeckoLib 4 animation system: all scene animations, interaction system,
affection system, quests, equipment, and tribes.

### Ported Characters (14)

Jenny, Ellie, Slime, Bee, Bia, Cat, Allie, Goblin, Kobold, Galath, Manglelie, Lucy, Mika, Momo

### Items (1.12.2 port complete)

| Item | Function |
|------|----------|
| 🧞 Allies Lamp | Summons Allie (8-line dialogue chain, 3 wishes); she has a **sand phobia** |
| 🐲 Dragon Staff | Tames Kobolds into your tribe (128 durability, -1 per tame) |
| 🥚 Tribe Egg | Hatches a kobold into your tribe (random color) |
| 🍷 Potion of Horny | Dedicated `sexmod:horny` effect — untamed kobolds interact FREE while active |
| 🪄 Girl Wand | NPC editor: +10 affection / toggle outfit / rename / send home (server-authoritative, owned girls only) |
| 🧿 Memory Crystal | Right-click bind to nearest owned girl; shift+right-click teleport to her home |
| 📯 Summoning Whistle | Calls your closest owned girl (safe landing, 2s cooldown) |
| 💖 Healing Charm | Passive aura: heals nearby owned girls 1 HP/2s (offhand/armor slots count) |
| 💍 Bond Bracelet | Passive aura: halves affection decay for nearby owned girls (auto-expires when holder leaves) |
| 📖 Guide Book | Tutorial book |

### Systems

- **Worldgen**: 7 role house NBTs converted to 1.21.1-native format (DataVersion 3955), placed via `GirlHouseGenerator` (NBT-first, cottage fallback); `sexmod teststructure <name>` debug command
- **Natural spawns**: 14 biome modifiers (`sexmod:forge/biome_modifier/spawn_*.json`)
- **Kobolds**: tribes (create/join/leader/colors), 3 gold + iron pickaxe payment (or horny potion bypass, 20-min unlock), work (FALL_TREE/MINE), sleep (find bed at night), breeding (mate → egg → 10-min hatch), territory defense, **tribes survive server restarts**
- **Goblins**: steal items → catch dialog, queen + throne guards, **piggyback riding** (shift+right-click)
- **Affection**: 0-100, gifts (14 types + favorites), jealousy, daily limit, decay (halved by Bond Bracelet)
- **Quests**: FETCH / KILL / ESCORT / DEFEND with rewards
- **Equipment**: SexFighterEntity 6-slot armor, server-authoritative UI
- **Advancements**: 5 custom progressions
- **Dedicated server**: fully playable; common classes are client-isolated (reflection-based screen bridge — no NoClassDefFoundError)

## Known Issues (remaining)

- 🟡 **Combat animations** — SexFighterEntity has combat logic but attack anims depend on model (characters without `attack` anims, e.g. Cat, fall back to idle)
- 🟡 **Biome natural spawns** — registered but needs a real player in-world to observe spawn rates
- 🟢 **hehe map** (`item.item_map_secret`) — 1.12.2 easter egg, not ported
- 🟢 **Luna** — 1.12.2 Luna was a half-finished character (sounds + house only, no model/animations); intentionally skipped

## Dependencies

- Minecraft 1.21.1
- Forge 52.0.7 (1.21.1)
- GeckoLib 4.6.6 (forge 1.21.1)
- JDK 21 (Gradle 8.8 rejects Java 24)
- **Incompatible with OptiFine** (use Iris/Oculus or Sodium/Rubidium)

## Build

```bash
$env:JAVA_HOME="C:\Program Files\Java\jdk-21"   # JDK 21 required
.\gradlew.bat build
```

JAR output: `build/libs/sexmod-1.8.0-1.21.1.jar`

## Credits

- **Original creator** Trolmastercard
- **1.21.1 base port** [Angina830](https://github.com/Angina830) — the 1.21.1 port framework this was rebuilt on
- **Decompiled source** [@Griefed](https://github.com/Griefed)'s `cfr` decompiler for extracting the original jar
- **2026-08 revival** — full 1.12.2 feature parity pass: worldgen NBTs, item set, piggyback riding, tribe persistence, dedicated-server support, 2 rounds of sub-agent audits (3+2 HIGH fixes)

---

## 来自移植者的一段话

这里是真人留言了，首先我要说明一点，我们可以找到[fapcraftx.com](https://fapcraftx.com/)这个官网，这个网站声明是fapcraft模组的官网，但是这里面除了1.12.2的java版mod下载以外就全是假的，它的MCPE下载的是一个诡异的PEbeta版，不仅没有模组内容而且本体都全是bug，我严重怀疑这个网站的真实性。还有我是一个Unity开发者，我并不熟悉Java以及mc的api，所以我自己移植实在困难上天，我用我的openclaw搞了这个项目三天，用deepseek的api烧了我将近4个亿的token，也只是做了一个全是bug的半成品，我很难保证我之后会系统性地学习Java和mc的api，因为这和我的规划不符，所以我由衷感谢可以有大佬来接管这个项目，毕竟色色是第一生产力！

> **2026-08-09 更新：** 复活了。1.12.2 全功能补齐 + 正式服务器验证 + 两轮子 agent 审计闭环。项目现在能跑、能玩、能开服。
