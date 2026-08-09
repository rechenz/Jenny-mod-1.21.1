# Jenny Mod — 1.21.1 Port

> ⚠️ **Project Status: On Hold — Looking for Contributors**
> This is a port of the classic Jenny Mod (Fapcraft) to Minecraft 1.21.1 + Forge.
> Core features work, but several issues remain. PRs and forks welcome.

## What Works

Rewrite based on the original 1.12.2 decompiled source + GeckoLib 4 animation system.
All scene animations, interaction system, and affection system are fully ported.

### Ported Characters (14)

Jenny, Ellie, Slime, Bee, Bia, Cat, Allie, Goblin, Kobold, Galath, Manglelie, Lucy, Mika, Momo

### Known Issues

- ~~🔴 **"Diamond Armor" visual glitch**~~ — **Fixed**: `SexEntity.getItemBySlot()` returns EMPTY client-side and `setItemSlot()` forces empty, preventing MC's vanilla armor overlay from ever rendering on girl entities
- ~~🔴 **Goblin interaction incomplete**~~ — **Fixed**: catch dialog (`GoblinCaughtScreen`) now opens on right-click when the goblin has stolen items; `doUseHer` is wired to `SceneManager.startBlowjob` (goblin nelson/paizuri animation chains)
- 🟡 **Combat animations** — SexFighterEntity has combat logic but attack anims depend on model (characters without `attack` anims, e.g. Cat, fall back to idle)
- ~~🟡 **Equipment UI**~~ — **Improved**: EquipmentScreen renders the player inventory grid; click-to-equip/click-to-unequip with **server-authoritative transfers** (no item duping/loss)
- 🟡 **Worldgen houses** — NBT house templates incompatible with current StructureTemplate system (disabled); replaced by `GirlHouseGenerator.generateCottage()` (hardcoded cabin on first spawn)

### Dependencies

- Minecraft 1.21.1
- Forge 52.1.14
- GeckoLib 4.8.4 (forge 1.21.1)
- **Incompatible with OptiFine** (use Iris/Oculus or Sodium/Rubidium)

## Build

```bash
.\gradlew.bat build
```

JAR output: `build/libs/sexmod-1.8.0-1.21.1.jar`

## Credits

- **Original creator** Trolmastercard
- **1.21.1 base port** [Angina830](https://github.com/Angina830) — the 1.21.1 port framework this was rebuilt on
- **Decompiled source** [@Griefed](https://github.com/Griefed)'s `cfr` decompiler for extracting the original jar
- Everyone brave enough to touch this mess

## A Note from the Port Author

I'm a Unity developer, not a Java modder. I have zero familiarity with Minecraft's API or Java ecosystem. Yet somehow I spent 3 days and burned nearly **400 million tokens** of DeepSeek API trying to make this work through my OpenClaw setup.

You can find the official website at [fapcraftx.com](https://fapcraftx.com/) — it claims to be the official Fapcraft site. It offers a legit 1.12.2 Java download, but the "MCPE" version is a suspicious PE beta that has neither mod content nor functional gameplay. I have serious doubts about this site's legitimacy.

The result of my effort? A buggy half-finished port that barely runs. I can't guarantee I'll learn Java and MC modding systematically — it doesn't align with my road map.

So this is an open call: if you're a modder who knows what they're doing, **please take over**. Horny is the ultimate driving force — this project deserves someone who can actually finish it.

---  

## 来自移植者的一段话

这里是真人留言了，首先我要说明一点，我们可以找到[fapcraftx.com](https://fapcraftx.com/)这个官网，这个网站声明是fapcraft模组的官网，但是这里面除了1.12.2的java版mod下载以外就全是假的，它的MCPE下载的是一个诡异的PEbeta版，不仅没有模组内容而且本体都全是bug，我严重怀疑这个网站的真实性。还有我是一个Unity开发者，我并不熟悉Java以及mc的api，所以我自己移植实在困难上天，我用我的openclaw搞了这个项目三天，用deepseek的api烧了我将近4个亿的token，也只是做了一个全是bug的半成品，我很难保证我之后会系统性地学习Java和mc的api，因为这和我的规划不符，所以我由衷感谢可以有大佬来接管这个项目，毕竟色色是第一生产力！

---

# Jenny Mod — 1.21.1 移植版

> ⚠️ **项目状态：停工待大佬**
> 本项目是旧版 Jenny Mod（Fapcraft）到 Minecraft 1.21.1 + Forge 的移植尝试。
> 基础功能可用，但仍有若干遗留问题。欢迎 PR / fork。

## 现状

基于旧版 1.12.2 反编译代码 + GeckoLib 4 动画系统重写。所有场景动画、互动系统、好感度系统已完成移植。

### 已实现的角色（14个）

Jenny, Ellie, Slime, Bee, Bia, Cat, Allie, Goblin, Kobold, Galath, Manglelie, Lucy, Mika, Momo

### 已知问题

- ~~🔴 **「钻石甲」显示 bug**~~ — **已修复**：`SexEntity.getItemBySlot()` 客户端返回 EMPTY + `setItemSlot()` 强制清空，MC 内置 armor overlay 不会在角色实体上渲染
- ~~🔴 **Goblin 交互不完整**~~ — **已修复**：goblin 偷到东西后右键会打开抓捕对话（`GoblinCaughtScreen`）；`doUseHer` 已接入 `SceneManager.startBlowjob`（nelson/paizuri 动画链）
- 🟡 **战斗动画** — 战斗逻辑已实现，攻击动画依赖角色模型是否有 `attack` 动画（没有的如 Cat 回退 idle）
- ~~🟡 **装备 UI**~~ — **已改进**：EquipmentScreen 渲染玩家背包网格，点击拾取/点击装备/点击卸下，交易由**服务端权威执行**（不会复制/丢失物品）
- 🟡 **世界生成房屋** — NBT 模板不兼容 StructureTemplate（已禁用）；由 `GirlHouseGenerator.generateCottage()` 首次生成硬编码小屋替代

### 依赖

- Minecraft 1.21.1
- Forge 52.1.14
- GeckoLib 4.8.4（for forge 1.21.1）
- **不兼容 OptiFine**（推荐 Iris/Oculus 或 Sodium/Rubidium）

## 构建

```bash
.\gradlew.bat build
```

JAR 输出在 `build/libs/sexmod-1.8.0-1.21.1.jar`

## 鸣谢

- **原版作者** Trolmastercard
- **1.21.1 移植基底** [Angina830](https://github.com/Angina830) — 基于其 1.21.1 移植框架重构
- **反编译库来源** [@Griefed](https://github.com/Griefed) 的 `cfr` 反编译器，用于提取旧版 jar 的完整 Java 源码
- 所有愿意碰这堆屎山的勇士

---

*This project is a fan port for preservation and educational purposes.*
