# 旧版反编译库移植分析报告

> 分析日期：2026-05-26
> 分析对象：old_ref\src\main\java\com\trolmastercard\sexmod\
> 目标：找出 Allie、Goblin、Kobold 三个角色在新版中没有实现的关键功能

---

## 一、Allie（艾莉）角色分析

### 源文件
- `AllieEntity.java` — 角色逻辑（~575行）
- `PlayerAllie.java` — 玩家控制逻辑（~450行）
- `AllieModel.java` — 模型定义

### 1. 旧版独特玩法机制

**核心机制：许愿系统（Wish System）**
- Allie 被召唤时会询问玩家想要什么（"Make me rich" 或是性互动）
- 许愿系统通过 `boolean_f()` 检查物品 NBT 中的 `sexmodUses`：
  - 第1次使用：触发 `RICH_FIRST_TIME` / `ALLIE_PREPARE_FIRST_TIME`
  - 第2次使用：触发 `RICH_NORMAL`（"2 wishes remaining"）
  - 之后正常（"1 wish remaining"）
- `RICH_FIRST_TIME` / `RICH_NORMAL` 动画完成后会播放 "wish granted" 音效

**无"治愈光环+电击"功能** — 这是误解。旧版 Allie 不是战斗型角色，核心是**召唤-许愿-侍奉**循环。

**召唤仪式感极强：**
- `SUMMON` → 8段台词 + 音效 → `SUMMON_WAIT`
- `SUMMON_NORMAL` → 对话 → `SUMMON_NORMAL_WAIT`
- `SUMMON_SAND`（对沙子点右键时触发）
- `RICH_FIRST_TIME` / `RICH_NORMAL` 动画（洒金币特效）
- 入口粒子效果（portal particles from tails）
- 对话气泡系统（`void_a()` 显示文字 + 音效）

**行为模式（唯一）：**
- 召唤后保持悬浮（`setNoGravity(true)`）或座椅动画
- 8根尾巴随机呼吸动画
- 深喉（Deepthroat）和 Reverse Cowgirl 两种性互动模式
- 动画通过 sound keyframe 触发大量对话和音效

### 2. 渲染器/模型差异

| 方面 | 旧版 | 预期新版状态 |
|------|------|-------------|
| 模型文件 | `geo/allie/allie.geo.json` + `geo/allie/armored.geo.json` | 可能不同 |
| 材质 | `textures/entity/allie/allie.png` | 可能不同 |
| 模型变体 | 支持2个模型：无甲版、装甲版（`armored.geo.json`） | 未知 |
| 玩家版 | `PlayerAllie` 单独模型索引（3个模型包含手部） | 可能简化 |
| 骨骼 | `armorHelmet`、`armorShoulderR/L`、`armorChest`、`armorBoobs` | 可能不同命名 |
| `boobsFlesh`、`clothes`、`clothesR`、`clothesL` | 可动态切换衣物 | 未知 |

### 3. 交互方式差异

| 方面 | 旧版 | 新版 |
|------|------|------|
| 右键菜单 | `"action.names.makemerichallie"` + `"action.names.deepthroat"` + `"Reverse cowgirl"` | 未知 |
| 召唤方式 | 对空气右键（召唤NPC版）/ 物品召唤（Player版） | 未知 |
| 对话系统 | 全音效 + 对话气泡 + 多阶段（8段召唤台词） | 未知 |
| AI行为 | 跟随玩家（`df_class178`）+ 散步 | 未知 |

### 4. ✅ 必须移植的核心功能（不移植就不好玩）

| 优先级 | 功能 | 说明 |
|--------|------|------|
| **P0** | **召唤仪式（对话+动画链）** | 有8段语音/文本的召唤动画链，是Allie最大的差异化 |
| **P0** | **许愿系统（Make me rich）** | 独特的道具交互——从物品 NBT 读取使用次数，触发金币雨特效 |
| **P0** | **许愿物品** | `sexmodUses` NBT 追踪的专用召唤物品（可能已在新版有替代品） |
| **P0** | **8根尾巴+Portal粒子** | 尾巴动画 + PORTAL粒子特效是视觉特征 |
| **P1** | **Reverse Cowgirl + Deepthroat 动作** | 两个独占性姿势 |
| **P1** | **对话气泡 + 音效系统** | Allie 的大量配音台词（召唤、深喉准备等）|

### 5. 🟡 可以延后移植的功能

| 优先级 | 功能 | 说明 |
|--------|------|------|
| P2 | 装甲模型变体（`armored.geo.json`） | 好看但不是核心玩法 |
| P2 | 多次使用物品追踪（2次/1次剩余） | 锦上添花 |
| P3 | 贴地行走 vs 悬浮动画切换 | 视觉效果升级 |
| P3 | `SUMMON_SAND`（沙子召唤变体）| 彩蛋级别 |

### 6. 代码复杂度：3/5
- Allie 代码量 ~1k行（含 PlayerAllie），核心循环清晰
- 许愿系统的 NBT 逻辑独立易提取
- 困难点在：召唤动画链重构、对话/音效系统在新版的位置

---

## 二、Goblin（哥布林）角色分析

### 源文件
- `GoblinEntity.java` — 角色逻辑（~2100行）
- `PlayerGoblin.java` — 玩家控制（~1280行）
- `GoblinRenderer.java`、`GoblinModel.java` — 渲染

### 1. 旧版独特玩法机制

**核心机制：偷东西（Stealing）+ 坐骑系统（Riding/Carrying）+ 女王系统（Queen System）**

**偷东西系统：**
- 野生的 Goblin 潜伏在领地中，当玩家靠近时 `java_lang_Integer_c()` 扫描玩家背包
- 如果玩家有金制品（金剑、金甲、金块等 — `ag` HashSet 定义了大量金制品），Goblin 会窃取
- 偷走 `RUN` → `CATCH` → `CATCH_BJ` / `CATCH_BJ_IDLE` 动作链
- 被偷物品存储在 `m.set(a0, itemStack)`
- 玩家右键选择 `"take ur stuff back"` → `START_THROWING` 动作把物品扔回
- 或选择 `"use her"` → 进入性互动

**坐骑/携带系统（Carrying）：**
- 右键交互 → `PICK_UP` 动作 → 玩家被挂到背上
- 玩家 `setNoGravity(true)` + `noClip = true`
- 携带时 Goblin 旋转跟踪玩家位置（`void_b(entityPlayer)` 计算 pitch）
- 落地的动作链：`STAND_UP` → `NULL`
- 投掷（`THROWN`）：落地后 `STAND_UP` 过渡

**女王系统（Queen System）：**
- `aX = true` 标记为女王
- 王座 `SIT` 动作 + 王座位置追踪（`ac`（旋转角度） + `al`（位置））
- 女王有守卫列表（`T` — `guard` UUID 列表）
- `aV`（`preggo`）：
  - 设置 `isPreggo = true`
  - 8400 ticks 后（约7分钟）自动结束——受孕期
  - 受孕期间守卫会列队保护王座
- 繁殖机制：
  - 坐骑在 throne 上 SIT 32000 ticks（~26分钟）后
  - 自动生成一个新的 GoblinEntity 携带偷来的物品逃走
  - 新 Goblin 会 `RUN` 到玩家处展示物品然后消失
- 女王消失动作链：`VANISH`（透明度渐变 0.05/tick）

**可定制化高：**
- 皮肤颜色：`by_class106`（多种颜色）
- 眼睛颜色：`eh_class250`
- 发型/身体模型变体：编码在 DNA 字符串（`m.set(M, stringBuilder)`）
- 身体数据编码为 DNA 字符串

### 2. 渲染器/模型差异

| 方面 | 旧版 | 预期新版状态 |
|------|------|-------------|
| 身体颜色系统 | `by_class106.DARK_GREEN` 等枚举，每帧渲染时查色 | 可能简化/不同 |
| 眼睛颜色 | `eh_class250` 枚举 | 可能不同 |
| 发型/身体变体 | DNA 编码存储，支持大量组合 | 未知 |
| 王座渲染 | `SIT` 动作下特定角度 | 未知 |
| 透明度动画 | `ar` 字段控制 `VANISH` 渐隐（-0.05/tick） | 未知 |

### 3. 交互方式差异

| 方面 | 旧版 | 新版 |
|------|------|------|
| 右键菜单 | 偷东西后：`"take ur stuff back"` + `"use her"` | 未知 |
| 携带 | `PICK_UP` → 玩家在背上 → `STAND_UP` 落下 | 未知 |
| 女王状态 | 自动 SIT 在王座上，玩家右键触发守卫 | 未知 |
| 战斗AI | `RUN`（逃跑模式）+ 偶尔攻击 | 未知 |

### 4. ✅ 必须移植的核心功能

| 优先级 | 功能 | 说明 |
|--------|------|------|
| **P0** | **偷东西系统** | 扫描玩家金制品 + 盗窃 + 展示/归还，是 Goblin 的核心玩法 |
| **P0** | **携带/背起系统** | 玩家坐到背上，这是 Goblin 的"交通工具"功能，极受欢迎 |
| **P0** | **女王/繁殖系统** | 王座 + 守卫 + 受孕，这是 Goblin 的深度玩法 |
| **P0** | **身体颜色/眼睛颜色** | `by_class106` + `eh_class250` 的可视化，Goblin 的外观辨识度 |
| **P0** | **DNA 身体编码系统** | 发型/身体模型的编码存储 |
| **P1** | **投掷系统（THROWN + STAND_UP）** | 从背上落下来的过渡动画 |
| **P1** | **VANISH 渐隐动画** | 女王消失时的专业效果 |

### 5. 🟡 可以延后移植的功能

| 优先级 | 功能 | 说明 |
|--------|------|------|
| P2 | 女王守卫列表追踪 | +1 但非必要 |
| P2 | 金制品检测集合 `ag` 全部物品 | 可以只支持核心金制品 |
| P2 | `RUN` 逃跑 AI+动作链 | 野生哥布林行为 |
| P3 | NBT 读写完整 DNA | 较长编码的持久化 |
| P3 | `VANISH` 透明度动画 | 纯粹视觉效果 |

### 6. 代码复杂度：4/5
- 代码量大（~3400行含 PlayerGoblin）
- 偷东西系统逻辑自洽但分散
- 女王系统涉及多个状态机（SIT / preggo / guard）
- 坐骑/背起系统涉及玩家实体控制权——在新版中实现最麻烦
- 关键难点：玩家的 `setNoGravity(true)` + `noClip(true)` + 位置同步

---

## 三、Kobold（狗头人）角色分析

### 源文件
- `KoboldEntity.java` — 角色逻辑（~3210行，13万字符级别的文件）
- `PlayerKobold.java` — 玩家控制（~685行）
- `KoboldManager.java` — 部落管理系统（~1200行，5万字符）
- `bs_class97.java` — 任务/工作系统
- `c7_class116.java` — 蛋物品
- `KoboldEggModel.java`、`KoboldEggModel2.java` — 蛋模型
- `KoboldStaffModel.java` — 法杖模型
- `KoboldModel.java`、`KoboldRenderer.java` — 渲染

### 1. 旧版独特玩法机制

**核心机制：部落系统（Tribe System）是最复杂的生态**

#### 部落层级（通过 `KoboldManager` + `b_inner50.a_inner49` 管理）：
- **一个部落 = 1 个 Owner UML + N 个 Kobold**
- 每个部落 UUID 唯一标识
- 部落存储 `WorldSavedData`（`b_inner50`）持久化

#### 部落领导：
- **领袖（Leader）：** `a_inner49.g` — 每个部落只有一个
- **成员列表：** `a_inner49.a` — 所有 Kobold 实体
- **颜色：** `EyeAndKoboldColor_class2` — 整个部落统一颜色
- **姓名：** `ba_class73` 枚举（50个 Kobold 语名字）

#### 部落状态机（`fm_class319`）：
- **ACTIVE：** 成员工作/巡逻
- **REST：** 成员睡觉（回到床上）

#### ⭐ 任务/工作系统（`bs_class97.KoboldTask`）：
1. **`FALL_TREE`（砍树）：** 自动检测原木区块 → 创建 task → 指派 Kobold 执行
2. **`MINE`（采矿）：** 自动检测矿石 → 3个 Kobold 一组执行

#### 自动找床和睡觉：
- `KoboldManager.a(KoboldEntity, BlockPos)`：查找床的两个半块
- REST 模式：Kobold 回到床上位置（`void_i(uUID)` → 查找床 → 定位 → 坐/躺）
- `sleep` 动作

#### 产卵/繁殖：
- **交配（Mating Press）：** 玩家与 Kobold 的独有姿势 `MATING_PRESS_*`
- **产蛋：** `o(UUID)` 方法在 `MATING_PRESS_CUM` 结束后：
  - 132 ticks 延迟 → 生成 `c7_class116.a`（Kobold蛋物品）
  - 蛋物品携带 tribeID + tribeColor（NBT 存储）
- 右键放置蛋 → 生成 `i_class410`（Kobold蛋实体），继承部落颜色

#### 部落防御：
- **攻击系统：** `void_d()` + `UPDATE_AI`
  - 82 ticks 延迟后攻击
  - `attackEntityFrom(DamageSource.causeMobDamage(this), 5.0f)` 
  - 检测敌人（LivingBase）靠近领地主块 → 全体防御
- 防御范围基于部落占领的主块

#### 领地管理：
- `KoboldManager.a(blockPos)` 检查块是否属于领地
- `b`（BlockPos HashSet）— 领地边界
- `i`（BlockPos HashSet）— 内部存储区域
- `k`（HashMap<KoboldUUID, BlockPos>）— 成员位置追踪
- 领地可视化（`h6_class397` 网络包发送领地数据）

#### 
玩家互动：
- **未驯服 + 无药水效果：** 右键显示菜单（"anal" + "oral"），但需要支付（金锭 x3 + 铁镐）
- **未驯服 + 有药水效果：** 右键免费（"anal" + "oral"）
- **驯服（驯服后）：** "anal" + "oral" + "mating"
- **法杖（`hy_class407.b`）：** 
  - 未驯服 Kobold → 显示驯服 GUI（`g7_class352`）
  - 驯服 Kobold（自己的）→ 打开定制化 GUI
- **命名牌：** 改名（只有主人能用）
- **专属姿势：**
  - `KOBOLD_ANAL_START/SLOW/FAST/CUM` — 狗头人专属后入
  - `MATING_PRESS_START/SOFT/HARD/CUM` — 交配压制（产蛋触发）

**自动血量恢复：**
- 未驯服时：100 ticks 恢复 2 HP
- 驯服时：不自动恢复

**自动对话：**
- 主人靠近 <2f 时自动说 "Hey master!"
- 500 个海军陆战队梗台词库（`an` 数组）

### 2. 渲染器/模型差异

| 方面 | 旧版 | 预期新版状态 |
|------|------|-------------|
| 模型文件 | `geo/kobold/kobold.geo.json` | 可能不同 |
| 材质 | 带颜色映射的纹理 | 未知 |
| 颜色系统 | `EyeAndKoboldColor_class2` 枚举（主色+多色映射） | 可能简化 |
| 身体大小 | `aE` DataParameter（0.25 范围），体型可变 | 未知 |
| 蛋模型 | `KoboldEggModel` / `KoboldEggModel2` 两个版本 | 可能无 |
| 法杖模型 | `KoboldStaffModel` | 可能无 |

### 3. 交互方式差异

| 方面 | 旧版 | 新版 |
|------|------|------|
| 右键菜单 | 分三种情况（未驯服/药水/驯服） | 未知 |
| 驯服方式 | 法杖点击 → GUI | 未知 |
| 定制化GUI | 法杖右键领养的 → `g7_class352` GUI | 未知 |
| 部落管理 | 全状态机 + WorldSavedData | 未知 |
| 产卵/孵化 | MATING_PRESS → CUM → 132tick → 蛋物品 → 放置 → 蛋实体 | 未知 |

### 4. ✅ 必须移植的核心功能

| 优先级 | 功能 | 说明 |
|--------|------|------|
| **P0** | **部落系统（KoboldManager）** | 整个 Kobold 的核心——没有部落就是普通 NPC |
| **P0** | **部落 UUID 持久化 + WorldSavedData** | 部落必须跨世界保存/加载 |
| **P0** | **任务系统（FALL_TREE / MINE）** | Kobold 的自动化核心玩法 |
| **P0** | **自动找床 + 睡觉（REST）** | 部落状态的日夜循环 |
| **P0** | **产蛋 + 孵化（KoboldEgg）** | 繁殖系统的核心循环 |
| **P0** | **颜色系统（EyeAndKoboldColor_class2）** | 视觉特征+部落标识 |
| **P0** | **驯服系统（法杖 + GUI）** | 玩家与部落的入口交互 |
| **P0** | **Kobold 姓名系统（ba_class73）** | 50个独特的 Kobold 语名字 |
| **P0** | **领地管理（BlockPos 追踪）** | 防御范围 + 自动工人指派 |
| **P0** | **MATING_PRESS 独占姿势** | 产卵触发点 |
| **P1** | **部落攻击/防御 AI** | 对 Minecraft 怪物的自动防御 |
| **P1** | **领地可视化（h6_class397 网络包）** | 玩家看到自己的领地 |
| **P1** | **自动对话系统（"Hey master!"）** | 沉浸感 |
| **P1** | **支付系统（金锭+铁镐）×3** | 未驯服 Kobold 的互动门槛 |
| **P1** | **多种姿势 + 药水解锁** | 未驯服的额外条件 |

### 5. 🟡 可以延后移植的功能

| 优先级 | 功能 | 说明 |
|--------|------|------|
| P2 | 法杖模型（KoboldStaffModel） | 可以先用简单物品代替 |
| P2 | 海军陆战队梗台词库（500句） | 彩蛋，可以精简 |
| P2 | 体型缩放系统（aE） | 视觉效果 |
| P3 | 两个蛋模型版本切换 | 细节 |
| P3 | 全身定制化 GUI（`g7_class352`） | 可以简化 |

### 6. 代码复杂度：5/5 ⚠️
- 代码总量最大：KoboldEntity (~13万字符) + KoboldManager (~5万字符) + 支撑类
- 部落系统高度耦合（Manager ↔ Entity ↔ Task ↔ Egg ↔ Player）
- WorldSavedData 持久化是最复杂的 Minecraft 数据模式之一
- 多 Kobold 的并发 AI 调度（Leader + Members）
- 领地系统跨越 NBT/网络包/实体追踪三个层面
- 产蛋/孵化系统需要 Item → Entity 完整链路

---

## 四、综合对比

| 维度 | Allie | Goblin | Kobold |
|------|-------|--------|--------|
| **代码总量** | ~1k行 | ~3.4k行 | ~19k行+ |
| **移植难度** | 3/5 | 4/5 | 5/5 |
| **核心卖点** | 召唤+许愿 | 偷窃+骑背+女王 | 部落管理+自动化+繁殖 |
| **P0功能数** | 4 | 6 | 10 |
| **独有姿势数** | 2（DeepThroat+ReverseCowgirl） | 2（Paizuri+Nelson） | 3（Anal+MatingPress+Blowjob） |
| **持久化需求** | 低（物品NBT） | 中（女王/物品状态） | 高（整个部落持久化） |
| **推荐优先级** | **第二移植** | **优先移植** | **最后移植** |

---

## 五、移植建议

### 第一阶段（Goblin — 优先级高）
1. 偷东西系统（金制品检测+盗窃+投掷）
2. 背起/携带系统（PICK_UP + STAND_UP）
3. 女王王座 + 守卫 + 受孕
4. 颜色/外观系统（by_class106 + eh_class250）
5. Paizuri + Nelson 姿势

### 第二阶段（Allie — 优先级中）
1. 许愿物品（sexmodUses NBT）
2. 召唤仪式（完整对话链 + 动画）
3. 8根尾巴 + Portal粒子
4. Deepthroat + Reverse Cowgirl 姿势
5. 许愿金币雨效果

### 第三阶段（Kobold — 优先级低但价值高）
1. KoboldManager 部落核心（Tribe ID + WorldSavedData）
2. 驯服系统（法杖 + GUI）
3. 产蛋/孵化循环
4. 任务系统（FALL_TREE / MINE）
5. 领地 + 防御 AI
6. 睡觉/休息系统
7. 颜色 + 姓名系统

---

## 六、技术要点总结

### 通用架构迁移注意事项

1. **GeckoLib 版本差异**：旧版使用 GeckoLib 3，新版可能升级到 4。动画控制器接口、`IAnimatable`、`AnimationFactory` 构造方式均有变化。

2. **网络包重构**：旧版自建 `ge_class363.b.sendToServer()` 体系，新版需要适配当前网络包系统。

3. **DataParameter 注册**：旧版用 `EntityDataManager.createKey()` 加手动 ID，新版可能需要重新设计 DataParameter 注册方式。

4. **实体继承结构**：
   - 旧版：`GirlEntity extends EntityCreature implements IAnimatable`
   - `PlayerGirl extends Fighter`（注意 `Fighter` 不是 `EntityCreature`！）
   - `GoblinEntity extends e4_class223`（另一个中间类）
   - `KoboldEntity extends e4_class223`
   - 新版需确认继承树

5. **GUI 系统**：旧版用 `displayGuiScreen(new m_class414(...))` 作为右键菜单，新版需重新实现 UI。

6. **对话系统**：旧版对话气泡 + 音效通过 `void_a(String)` + `a(SoundEvent)` 组合实现，在新版中需要重建。

7. **FakeWorld 处理**：多个渲染/动画逻辑检查 `this.world instanceof FakeWorld`——重建时需要特定处理。
