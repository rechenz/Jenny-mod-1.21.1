# 1.12.2 → 1.21.1 玩法差距分析（2026-08-09 更新）

> 对照物：`Fapcraft 1.12.2 v1.1.jar`（热尘提供）+ `old_decompiled/`（反编译源码）+ `PORTING_PLAN.md` + `PLAYABILITY_AUDIT.md`
> 目标：1.21.1 版完整复刻 1.12.2 全部玩法，且 NBT 结构用 1.21.1 原生格式

---

## ✅ 1.12.2 已有且 1.21.1 已实现

| 功能 | 1.12.2 | 1.21.1 现状 |
|------|--------|-------------|
| 14 角色实体+刷怪蛋 | 12 角色 | ✅ 14 角色全注册 |
| 4 标准场景（Missionary/Doggy/Blowjob/Boobjob） | ✅ | ✅ 动画链全映射 |
| 好感度系统 | 部分 | ✅ AffectionData（0-100，衰减/礼物/嫉妒） |
| 礼物系统 | 部分 | ✅ 14 种礼物+每日上限 |
| 对话系统 | ✅ 全角色配音 | ✅ DialogueDB + 互动屏幕 |
| 任务系统 | ❌ 无 | ✅ 新增（FETCH/KILL/ESCORT/DEFEND） |
| 装备系统 | 部分 | ✅ SexFighterEntity 6 槽装备 |
| 衣柜/换装 | ✅ Strip/Dress up | ✅ CLOTHING_STATE |
| Follow/Stay | ✅ | ✅ |
| Cat 摸头 | ✅ Headpat | ✅ CatEntity.startHeadPat |
| Goblin 偷窃 | ✅ | ✅ 偷金+CaughtScreen+归还 |
| Kobold 部落骨架 | ✅ | ✅ KoboldManager（265 行） |
| Kobold 产蛋 | ✅ | ✅ KoboldEgg |
| Allie 许愿 | ✅ 3 wishes | ✅ RICH_FIRST_TIME/NORMAL |
| Allie 召唤粒子/尾巴 | ✅ | ✅ portal 粒子+8 尾 |
| 成就/进度 | ❌ 无 | ✅ 5 个 advancement |

---

## ❌ 1.12.2 有但 1.21.1 缺失/不完整（要补的）

### P0 — 世界生成（热尘点名 NBT）

| # | 功能 | 1.12.2 实现 | 1.21.1 现状 | 方案 |
|---|------|------------|-------------|------|
| 1 | **角色房屋 NBT 结构** | `assets/sexmod/structures/*.nbt`（7 个：alex/bia/ellie/goblin/jenny/luna/ssa） | WorldGenHandler 禁用，靠 GirlHouseGenerator 硬编码小屋 | 把 7 个 NBT 转 1.21.1 格式放 `data/sexmod/structures/`，启用 chunk 生成 |
| 2 | **自然生成（生物群系）** | 各角色按生物群系生成 | 仅刷怪蛋 | 注册 biome spawn（权重配置化） |
| 3 | **结构内角色+宝箱** | NBT entities 内嵌角色 | 无 | NBT 升级后自动带角色，补宝箱 |

### P1 — 缺失物品

| # | 物品 | 1.12.2 | 1.21.1 | 说明 |
|---|------|--------|--------|------|
| 4 | **Allies Lamp（神灯）** | `item.allies_lamp` | ❌ | 召唤 Allie 的入口，右键召唤 |
| 5 | **Luna's Rod（Luna 法杖）** | `item.luna_rod` | ❌ | Luna 角色相关 |
| 6 | **Dragon Staff（龙法杖）** | `item.dragon_staff` + recipe | ❌ | Kobold 法杖（驯服用） |
| 7 | **Tribe Egg（部落蛋）** | `item.tribe_egg` | ❌ | Kobold 部落蛋 |
| 8 | **Girl Wand（NPC 编辑法杖）** | `item.npc_editor_wand` + recipe | ❌ | 定制化 GUI |
| 9 | **horny potion（发情药水）** | `effect.horny_potion` 4 变体 | ❌ | Kobold 互动门槛（金锭×3+铁镐 / 药水） |
| 10 | **hehe 地图** | `item.item_map_secret` | ❌ | 彩蛋 |

### P2 — 玩法系统

| # | 系统 | 1.12.2 | 1.21.1 | 说明 |
|---|------|--------|--------|------|
| 11 | **回家系统** | `Set new home` / `Go home` | 部分（memory_crystal 物品） | 加进 InteractionScreen 动作 |
| 12 | **Goblin 背起/骑乘** | PICK_UP → 玩家挂背上 | ❌ placeholder | 玩家 setNoGravity+noClip+位置同步 |
| 13 | **Goblin 女王完整链** | SIT 王座+守卫+受孕+VANISH | 部分 | 王座渲染/守卫列队 |
| 14 | **Kobold 任务系统** | FALL_TREE / MINE 自动指派 | 骨架（KoboldTask 125 行） | 补砍树/采矿检测+执行 |
| 15 | **Kobold 驯服 GUI** | 法杖→g7_class352 GUI | ❌ | 法杖右键→驯服界面 |
| 16 | **Kobold 睡眠 REST** | 日夜循环找床睡 | ❌ | REST 模式 |
| 17 | **Kobold 领地防御** | 领地块+全体反击 | 部分（getNearbyHostiles） | 补自动攻击 |
| 18 | **Allie 召唤仪式完整台词** | 8 段台词+音效+SUMMON_SAND | 部分 | 补 8 段对话链+沙子 phobia |
| 19 | **对话气泡** | void_a() 文字+音效 | InteractionScreen 对话框 | 可保留现状 |
| 20 | **genderswap 请求** | 玩家间 sex 请求 | ❌ | 低优先 |

### P3 — 打磨

| # | 项 | 说明 |
|---|-----|------|
| 21 | 多语言真正读取 | 现在硬编码英文，lang 文件没读全 |
| 22 | porn warning 界面 | 1.12.2 启动成人警告 |
| 23 | 角色差异化 UI 文案 | Lucy/Mika/Momo 共用 default 动画，按钮文案误导 |
| 24 | Slime blowjobstart 路径 bug | `animation.slime.blowjobstart` 不存在 |
| 25 | Manglelie 单动画重复 | 4 阶段同一 bed_slow |
| 26 | Bee/Slime 缺 idle/walk | GeckoLib 找不到动画 |

---

## 执行顺序

**本轮（8/9）：**
1. ✅ Goblin 交互链路 + 装备 UI 服务端权威（已完成 commit 1af13c7）
2. 🔨 NBT 结构转换（7 个房屋）+ WorldGenHandler 启用 + biome 自然生成
3. 🔨 P1 缺失物品（神灯/法杖/部落蛋/药水/编辑法杖）
4. 🔨 P2 核心玩法（回家、Goblin 背骑、Kobold 任务/睡眠/驯服、Allie 召唤台词）
5. 🔨 P3 打磨（Slime/Manglelie 动画修复、语言文件）
6. 🧪 runClient 游戏内验证

**后续轮次：** 玩家间请求、porn warning、多语言、性能优化

---

## 技术要点

1. **NBT 升级路径已确认可行**：`StructureTemplateManager` 加载时调用 `DataFixTypes.STRUCTURE` DataFixer，1.12.2 的 NBT（DataVersion 1343）会被自动升级方块名（grass→grass_block、log variant→独立方块等）。文件放 `src/main/resources/data/sexmod/structures/<name>.nbt`，代码里 `level.getStructureManager().get(ResourceLocation.fromNamespaceAndPath("sexmod", name))` 加载。
2. **1.12.2 NBT 内容**：size/blocks/palette/entities（内含 `sexmod:jenny` 等角色实体，DataFixer 会尝试 remap 实体 id；模组实体 id 在新版本已注册同名，需要验证）。
3. **DataFixer 风险点**：1.12.2 方块名如 `minecraft:grass`（1.13 改 grass_block）、`minecraft:log`（拆 oak_log/birch_log）、`minecraft:planks`（拆 oak_planks 等）、`minecraft:stone`（拆 stone/granite 等）。DataFixer 应该能处理，但需实测。
4. **chunk 生成时机**：原 1.12.2 用 IWorldGenerator 在 chunk 生成时放置。1.21.1 用 ChunkEvent.Load 或 Structure 注册。ChunkEvent.Load 可能阻塞加载（原代码注释提到 51% hang），需要测。
