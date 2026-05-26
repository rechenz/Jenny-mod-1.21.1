# Jenny Mod — 1.21.1 移植版

> ⚠️ **项目状态：停工待大佬**
> 本项目是旧版 Jenny Mod（Fapcraft）到 Minecraft 1.21.1 + Forge 的移植尝试。
> 基础功能可用，但仍有若干遗留问题。欢迎 PR / fork。

## 现状

基于旧版 1.12.2 反编译代码 + GeckoLib 4 动画系统重写。所有场景动画、互动系统、好感度系统已完成移植。

### 已实现的角色（14个）

Jenny, Ellie, Slime, Bee, Bia, Cat, Allie, Goblin, Kobold, Galath, Manglelie, Lucy, Mika, Momo

### 已知问题

- 🔴 **「钻石甲」显示 bug** — 部分角色出生时身上有紫色/白色盔甲纹理（MC 内置 armor overlay 误触发，pending fix）
- 🔴 **Goblin 交互不完整** — 驯服和 catch 后的场景触发未完全实现（`doUseHer` 为 stub）
- 🟡 **战斗动画** — SexFighterEntity 的战斗逻辑已实现，但攻击动画播放依赖角色模型是否存在 `attack` 动画
- 🟡 **装备 UI** — EquipmentScreen 基本可用，但拖拽来自玩家背包的功能受限
- 🟡 **世界生成房屋** — NBT 房屋模板格式不兼容当前 StructureTemplate 系统，暂时禁用

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
- 所有愿意碰这堆屎山的勇士

---

*This project is a fan port for preservation and educational purposes.*
