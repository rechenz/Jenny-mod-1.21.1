# PLAYABILITY AUDIT — Jenny Mod 1.21.1

> 审计日期：2026-05-26
> 审计范围：玩法兼容性 + UI 一致性 + 动画路径完整
> 严重级别：🔴 严重 / 🟡 体验 / ⚪ 建议

---

## 总览

| 角色 | UI 暴露场景 | 实际动画完备 | 状态 |
|------|------------|------------|------|
| Jenny | ✅ 4 场景 | ✅ 完整 | 正常 |
| Ellie | ✅ 4 场景 | ✅ 完整 | 正常 |
| Lucy | ✅ 4 场景 | ✅ 完整 | 正常 |
| Mika | ✅ 4 场景 | ✅ 完整 | 正常 |
| Momo | ✅ 4 场景 | ✅ 完整 | 正常 |
| Bia | ✅ 4 场景 | ✅ 完整 | 正常 |
| Allie | ✅ 特殊 UI | ✅ 完整 | 正常 |
| Cat | ✅ 2 场景 (统一场景按钮) | ✅ 完整 | 正常 |
| Bee | ✅ 1 场景 (统一场景按钮) | ✅ 完整 | 正常 |
| Slime | ✅ 1 场景 (统一场景按钮) | 🟡 名称不匹配 | **有问题** |
| Galath | ✅ 3 场景 | ✅ 完整 | 正常 |
| Manglelie | ✅ 1 场景 (只暴露 Missionary) | 🟡 单一动画重复 | **有问题** |
| Goblin | ✅ 4 场景 | ✅ 完整 | 🟡 玩法断链 |
| Kobold | ✅ 4 场景 | ✅ 完整 | 正常 |

---

## 详细报告

### 1. Jenny

| 属性 | 值 |
|------|-----|
| `getAnimationPrefix()` | `jenny` |
| 动画文件 | `jenny.animation.json` |
| Geo(穿) | `jennydressed.geo.json` |
| Geo(裸) | `jennynude.geo.json` |
| UI 暴露 | Missionary, Blowjob, Doggy, Boobjop (全部) |
| 好感度门槛 | Missionary/Blowjob ❤ 30, Doggy/Boobjob ❤ 60 |

**场景动画路径对照：**

| 场景 | 阶段 | 代码路径 | 动画文件存在? |
|------|------|---------|-------------|
| **Missionary** | START | `animation.jenny.doggystart` | ✅ |
| | SLOW | `animation.jenny.doggyslow` | ✅ |
| | FAST | `animation.jenny.doggyfast_soft` | ✅ |
| | CUM | `animation.jenny.doggycum` | ✅ |
| **Blowjob** | INTRO | `animation.jenny.blowjobintro` | ✅ |
| | SUCK | `animation.jenny.blowjobsuck` | ✅ |
| | THRUST | `animation.jenny.blowjobthrust` | ✅ |
| | CUM | `animation.jenny.blowjobcum` | ✅ |
| **Paizuri** | START | `animation.jenny.paizuri_start` | ✅ |
| | SLOW | `animation.jenny.paizuri_slow` | ✅ |
| | FAST | `animation.jenny.paizuri_fast` | ✅ |
| | CUM | `animation.jenny.paizuri_cum` | ✅ |
| **Doggy** | START | `animation.jenny.doggystart` | ✅ |
| | GOONBED | `animation.jenny.doggygoonbed` | ✅ |
| | WAIT | `animation.jenny.doggywait` | ✅ |
| | SLOW | `animation.jenny.doggyslow` | ✅ |
| | FAST | `animation.jenny.doggyfast_hard` | ✅ |
| | CUM | `animation.jenny.doggycum` | ✅ |

#### ⚪ 建议改进：Missionary 复用 Doggy 动画

- Missionary 的 START/SLOW/FAST/CUM 全部指向 doggy 系列动画
- Jenny 的 `.animation.json` 中确实没有 `missionary_*` 开头的动画
- 玩家选 "Missionary" 看到的实际上是 doggy style 视角
- **建议**：要么删除 Missionary 按钮，要么在 UI 中明确标注

---

### 2. Ellie (MILF)

| 属性 | 值 |
|------|-----|
| 动画前缀 | `ellie` |
| 动画文件 | `ellie.animation.json` |
| Geo(穿) | `dressed.geo.json` |
| Geo(裸) | `nude.geo.json` |
| UI 暴露 | Missionary, Blowjob, Doggy, Boobjop (全部) |

**场景动画路径对照：**

| 场景 | 阶段 | 代码路径 | 动画文件存在? |
|------|------|---------|-------------|
| **Missionary** | 全部 | `missionary_*` | ✅ 4条 |
| **Blowjob** | 全部 | `carry_*` (公主抱) | ✅ 4条 |
| **Paizuri** | 全部 | `cowgirl_*` (逆骑) | ✅ 4条 |
| **Doggy** | START/GOONBED/WAIT | `carry_intro` (复用) | ✅ |
| | SLOW | `carry_slow2` | ✅ (注意：Blowjob用 carry_slow1) |
| | FAST | `carry_fast` (复用) | ✅ |
| | CUM | `carry_cum` (复用) | ✅ |

#### 🟡 体验问题：Blowjob/Doggy 共用同一组动画

- Blowjob 和 Doggy 的 FAST 和 CUM 都指向相同的 `carry_fast` / `carry_cum`
- Doggy START/GOONBED/WAIT 都指向 `carry_intro`（Blowjob 的 INTRO）
- 玩家选择 Doggy 时看到的是公主抱动画，和 Doggy 的字面含义完全不匹配
- 同理 Paizuri 显示为逆骑 cowgirl

#### ⚪ 建议改进

- 可以理解为每个角色有自己的特殊体位命名——这个设计可以接受，但 UI 按钮描述建议更准确
- "Blowjob" → "Carry"、"Doggy" → "Carry (fast)" 这种会误导玩家

---

### 3. Lucy / Mika / Momo (共用动画前缀 `default`)

| 属性 | Lucy | Mika | Momo |
|------|------|------|------|
| 动画前缀 | `default` | `default` | `default` |
| 动画文件 | `lucy.animation.json` | `mika.animation.json` | `momo.animation.json` |
| Geo(穿) | `dressed.geo.json` | `dressed.geo.json` | `dressed.geo.json` |
| Geo(裸) | `nude.geo.json` | `nude.geo.json` | `nude.geo.json` |
| UI 暴露 | 全部 4 场景 | 全部 4 场景 | 全部 4 场景 |

三个角色的代码完全相同（除类名外），都使用 `animation.default.*` 前缀。

#### 动画存在性确认

所有路径在 `lucy.animation.json`/`momo.animation.json`/`mika.animation.json` 中**都找到了相同的 `animation.default.*` 定义**：

- ✅ `missionary_start/slow/fast/cum`
- ✅ `blowjob_intro/slow/fast/cum`
- ✅ `paizuri_intro/slow/fast/cum`
- ✅ `doggy_intro/lay_on_bed/bed_idle/slow/fast1/cum`

#### 🟡 体验问题：三角色共用同一套动画

- 三个角色动画路径 100% 相同（`animation.default.*`）
- 每个角色的 `.animation.json` 文件内容完全一致（都是定义 `animation.default.*`）
- **玩家无法区分三个角色的体位差异**
- 建议给每个角色分配独立动画前缀和独立动画

#### 🔴 严重问题：`doggy_fast2` 未映射

- 动画文件中存在 `animation.default.doggy_fast2`，但代码中只用了 `doggy_fast1`
- 不是 bug（代码只引用了一个 fast 版本），但多余的动画资源未使用

---

### 4. Bia

| 属性 | 值 |
|------|-----|
| 动画前缀 | `bia` |
| 动画文件 | `bia.animation.json` |
| 场景映射 | Missionary → prone_doggy_*, Blowjob → anal_*, Paizuri → anal_*, Doggy → prone_doggy_* |

**所有动画路径均验证存在：**
- ✅ `prone_doggy_intro/soft/hard1/cum/hard2/insert`
- ✅ `anal_start/slow/fast/cum/prepare`

#### 🟡 体验问题：Blowjob/Paizuri 都指向 anal

- Blowjob 和 Paizuri 都指向 `anal_*` 动画
- BlowjobINTRO → `anal_start`, BlowjobSUCK → `anal_slow`, BlowjobTHRUST → `anal_fast`, BlowjobCUM → `anal_cum`
- PaizuriSTART → `anal_prepare`, PaizuriSLOW → `anal_slow`, PaizuriFAST → `anal_fast`, PaizuriCUM → `anal_cum`
- **Paizuri 的 START 单独用 `anal_prepare`，然后 SLOW/FAST/CUM 和 Blowjob 完全共用**
- Missionary/Doggy 都指向 `prone_doggy_*`
- Missionary 用 `prone_doggy_hard1`，Doggy 用 `prone_doggy_hard2`——这是唯一区分

---

### 5. Cat

| 属性 | 值 |
|------|-----|
| 动画前缀 | `cat` |
| UI 暴露 | 统一场景按钮 "♥ Play" (实际上是 Missionary 场景) |
| `showStandardBlowjob()` | `false` |
| `showStandardDoggy()` | `false` |
| `showStandardBoobjob()` | `false` |
| `supportsScene()` | 只支持 `"Missionary"` 和 `"Blowjob"` |
| `hasSingleUnifiedScene()` | `false` (注意！) |

#### 🔴 严重问题：`hasSingleUnifiedScene()` 返回 `false`

- **但 Cat 只支持 Missionary 和 Blowjob 两个场景**
- InteractionScreen 中的逻辑是：`if (girl.hasSingleUnifiedScene()) { // 显示统一按钮 } else { 显示标准4按钮 }`
- Cat 在 UI 中会显示 Missionary/Blowjob 两个单独的按钮，但 `showStandardDoggy()` 和 `showStandardBoobjob()` 返回 `false`，所以只显示两个
- 而统一场景逻辑 (`hasSingleUnifiedScene() == false`) 不会触发 "统一按钮" 逻辑

**场景动画路径：**

| 场景 | 阶段 | 代码路径 | 动画存在? |
|------|------|---------|----------|
| **Missionary** | START | `animation.cat.sitting_slow` | ✅ (复用 SLOW) |
| | SLOW | `animation.cat.sitting_slow` | ✅ |
| | FAST | `animation.cat.sitting_fast` | ✅ |
| | CUM | `animation.cat.sitting_cum` | ✅ |
| **Blowjob** | INTRO | `animation.cat.touch_boobs_intro` | ✅ |
| | SUCK | `animation.cat.touch_boobs_slow` | ✅ |
| | THRUST | `animation.cat.touch_boobs_fast` | ✅ |
| | CUM | `animation.cat.touch_boobs_cum` | ✅ |

#### 🟡 体验问题：Missionary START 复用 sitting_slow

- `MISSIONARY_START` → `sitting_slow`（和 SLOW 相同）
- 玩家看不到 "插入" 的独立动画，直接跳到慢速阶段

#### ⚪ 建议改进

- Cat 的 `hasSingleUnifiedScene()` 应该和 Bee 一样返回 `true`，或者如果坚持两个场景，需要清理代码一致性

---

### 6. Bee

| 属性 | 值 |
|------|-----|
| 动画前缀 | `bee` |
| UI 暴露 | 统一场景按钮 "♥ Play" |
| `hasSingleUnifiedScene()` | ✅ `true` |
| `supportsScene(name)` | ✅ 所有场景名→ `false` |
| 特殊 | 所有 4 个场景类型映射到 `sex_*` |

**动画路径验证：**

| 阶段 | 代码路径 | 存在? |
|------|---------|------|
| START (4场景共用) | `animation.bee.sex_start` | ✅ |
| SLOW (4场景共用) | `animation.bee.sex_slow` | ✅ |
| FAST (4场景共用) | `animation.bee.sex_fast` | ✅ |
| CUM (4场景共用) | `animation.bee.sex_cum` | ✅ |

#### 🟡 体验问题：缺少 idle/walk 动画

- 动画文件中没有 `animation.bee.idle` 或 `animation.bee.walk`
- 默认的 idle 路径解析可能失败
- `getIdleAnimationPath()` 默认为 `animation.bee.idle` → **不存在**
- 同样 `walk` 也不存在

---

### 7. Slime

| 属性 | 值 |
|------|-----|
| 动画前缀 | `slime` |
| UI 暴露 | 统一场景按钮 "♥ Play" |
| `hasSingleUnifiedScene()` | ✅ `true` |
| `supportsScene(name)` | ✅ 所有→ `false` |

**场景动画路径：**

| 场景 | 阶段 | 代码路径 | 动画存在? | 备注 |
|------|------|---------|----------|------|
| Missionary/Blowjob | START | `animation.slime.doggystart` | ✅ | |
| | SLOW | `animation.slime.doggyslow` | ✅ | |
| | FAST | `animation.slime.doggyfast` | ✅ | |
| | CUM | `animation.slime.doggycum` | ✅ | |
| **Paizuri** | **START** | **`animation.slime.blowjobstart`** | ❌ **不存在** | 只有 `blowjobsuck`/`blowjobthrust`/`blowjobcum` |
| | SLOW | `animation.slime.blowjobsuck` | ✅ | |
| | FAST | `animation.slime.blowjobthrust` | ✅ | |
| | CUM | `animation.slime.blowjobcum` | ✅ | |
| Doggy | START | `animation.slime.doggygoonbed` | ✅ | 注意：直接用 GOONBED 替代 START |
| | GOONBED | `animation.slime.doggygoonbed` | ✅ | |
| | WAIT | `animation.slime.doggywait` | ✅ | |
| | SLOW | `animation.slime.doggyslow` | ✅ | |
| | FAST | `animation.slime.doggyfast` | ✅ | |
| | CUM | `animation.slime.doggycum` | ✅ | |

#### 🟡 体验问题：`animation.slime.blowjobstart` 不存在

- PaizuriSTART 代码中写的是 `animation.slime.blowjobstart`
- 但实际动画文件中只有 `animation.slime.blowjobsuck`（没有 "start"，只有 "suck"）
- **这个动画路径会导致 GeckoLib 找不到动画，触发优雅降级到空白姿势**
- 可能是笔误——应该是 `animation.slime.blowjobsuck`（和 PAIZURI_SLOW 复用）

#### 🟡 体验问题：BlowjobCUM 后自动切到 Missionary

- `handleAnimationSequencing()` 中，BLOWJOBCUM 结束后 follow-up 自动设为 MISSIONARY_START
- 这是一个跨类型链式玩法，但 UI 是统一按钮，玩家无法控制
- 体验上可能意外——玩家以为吹结束了，结果又自动切到下一场景

#### ⚪ 建议改进：Slime 无 idle/walk

- 和 Bee 一样，动画文件中没有 `animation.slime.idle` 定义
- `getIdleAnimationPath()` 返回的 `animation.slime.idle` 不存在

---

### 8. Galath (Boss 角色)

| 属性 | 值 |
|------|-----|
| 动画前缀 | `galath` |
| UI 暴露 | Missionary, Doggy, Boobjob (Blowjob 隐藏) |
| `showStandardBlowjob()` | `false` |
| 特殊 | Combat Grab 系统；骑乘系统；三飞(和 Manglelie) |

**场景动画路径：**

| 场景 | 阶段 | 代码路径 | 存在? |
|------|------|---------|------|
| **Missionary** | START | `animation.galath.corrupt_intro` | ✅ |
| | SLOW | `animation.galath.corrupt_slow` | ✅ |
| | FAST | `animation.galath.corrupt_hard` | ✅ |
| | CUM | `animation.galath.corrupt_cum` | ✅ |
| **Doggy** | START/WAIT | `animation.shared.bed_soft` | ✅ (shared) |
| | SLOW | `animation.shared.bed_slow` | ✅ |
| | FAST | `animation.shared.bed_fast` | ✅ |
| | CUM | `animation.shared.bed_cum` | ✅ |
| **Paizuri** | START | `animation.galath.pussy_licking` | ✅ |
| | SLOW | `animation.galath.pussy_licking_forward` | ✅ |
| | FAST | `animation.galath.pussy_licking_back` | ✅ |
| | CUM | `animation.galath.masterbating_sitting_cum` | ✅ |
| **Combat Grab** | INTRO | `animation.galath.rape_intro` | ✅ |
| | SUCK | `animation.galath.rape1` | ✅ |
| | THRUST | `animation.galath.rape2` | ✅ |
| | CUM | `animation.galath.rape_cum` | ✅ |

#### ✅ Combat Grab 系统评价

- 完整的抓取→逃脱机制
- 网络包 `GalathGrabPacket` 处理逃脱 tapping（A/D 连按）
- ESCAPE_DURATION = 8 秒后强制 cum + 伤害
- 抓取后动画按时间轴切换 (INTRO→SUCK→THRUST)
- 逃脱/释放后正确回退到 NULL

#### 🟡 体验问题：Blowjob 按钮隐藏但 combat grab 使用其 slot

- Galath 的 combat grab 用 `BLOWJOBINTRO/SUCK/THRUST/CUM` 枚举
- 但由于 `showStandardBlowjob() == false`，UI 不显示这个按钮
- **combat grab 是自动触发的，不在 UI 中操作**——这本身是对的
- 但如果 `supportsScene("Blowjob")` 返回 false（默认 true），玩家无法手动触发

#### ⚪ 建议：Galath 的 Doggy 使用 `shared.bed_*` 共享空间

- Galath 的 doggy 场景路径是 `animation.shared.bed_*` 而非 `animation.galath.*`
- Manglelie 的动画文件中也有 `animation.shared.bed_*` 定义
- 这是有意为之的共享动画系统？还是命名冲突需要确认

---

### 9. Manglelie

| 属性 | 值 |
|------|-----|
| 动画前缀 | `manglelie` |
| UI 暴露 | 只有 Missionary |
| `showStandardBlowjob()` | `false` |
| `showStandardDoggy()` | `false` |
| `showStandardBoobjob()` | `false` |
| `supportsScene(name)` | 只支持 `"Missionary"` |
| 特殊 | 骑乘系统、三飞(Galath)、母女羁绊 |

**场景动画路径：**

| 场景 | 阶段 | 代码路径 | 存在? |
|------|------|---------|------|
| **Missionary** | START | `animation.manglelie.bed_slow` | ✅ |
| | SLOW | `animation.manglelie.bed_slow` | ✅ (复用) |
| | FAST | `animation.manglelie.bed_slow` | ✅ (复用) |
| | CUM | `animation.manglelie.bed_slow` | ✅ (复用) |

#### 🔴 严重问题：Missionary 4 阶段全部指向同一动画

- `MISSIONARY_START/SLOW/FAST/CUM` → `animation.manglelie.bed_slow`
- 玩家选择 Missionary 后，**无论按什么键**（加速、射精）都只播放同一个 slow animation
- 相当于这个角色只有 1 个场景状态，但 UI 显示为有完整交互的 Missionary
- 用 `hasSingleUnifiedScene()` 的设计更适合 Manglelie

#### 🟡 体验问题：Manglelie 没有 solo doggy/paizuri

- 从代码看所有非 Missionary 的场景都设为 false 或不支持
- 这些场景只能通过三飞系统（和 Galath 一起）触发
- 但 Galath 的 startThreesome 只设置了 `PAIZURI_SLOW`，没走标准场景路径
- 三飞退出后的状态恢复逻辑：Manglelie 重置为 NULL，Galath 也重置——**这部分的清理是正确的**

---

### 10. Goblin

| 属性 | 值 |
|------|-----|
| 动画前缀 | `goblin` |
| UI 暴露 | Blowjob, Boobjob (Missionary/Doggy 隐藏) |
| `showStandardMissionary()` | `false` |
| `showStandardDoggy()` | `false` |
| 特殊 | 偷窃系统、接客/女王系统、怀孕产蛋 |

**场景动画路径：**

| 场景 | 阶段 | 代码路径 | 存在? |
|------|------|---------|------|
| **Missionary** | START | `animation.goblin.breeding_intro_1` | ✅ |
| | SLOW | `animation.goblin.breeding_slow_1l` | ✅ |
| | FAST | `animation.goblin.breeding_fast_1s` | ✅ |
| | CUM | `animation.goblin.breeding_cum_1` | ✅ |
| **Blowjob** | INTRO | `animation.goblin.nelson_intro` | ✅ |
| | SUCK | `animation.goblin.nelson_slow` | ✅ |
| | THRUST | `animation.goblin.nelson_fasts` | ✅ |
| | CUM | `animation.goblin.nelson_cum` | ✅ |
| **Paizuri** | 全部 | `animation.goblin.paizuri_*` | ✅ 全部 |
| **Doggy** | START/GOONBED/WAIT | `animation.goblin.catch_1personBj_idle` | ✅ |
| | SLOW | `animation.goblin.catch_1person` | ✅ |
| | FAST | `animation.goblin.catch_1personBj` | ✅ |
| | CUM | `animation.goblin.catch_3personBj` | ✅ |

#### 🟡 体验问题：showStandard 不匹配场景枚举

- Goblin 的 `showStandardMissionary() == false` 且 `showStandardDoggy() == false`
- **但在 InteractionScreen 中，只检查了 `showStandard*` 来决定显示按钮**
- **重点：Goblin 虽然有完整的 missionary/doggy 动画路径**，但因为 UI 隐藏了这些按钮，玩家无法通过标准 UI 触发
- Goblin 的 `supportsScene()` 未重写，所以默认为所有场景 `true`……但 UI 只显示 Blowjob 和 Boobjob

#### 🟡 体验问题：Catch 系统与 UI 冲突

- `doReturnStolenItems()` 和 `doUseHer()` 方法有为 catch 后交互写的逻辑
- 但 UI 中 `RETURN_ITEMS` action 绑定了 "💰 Return Stolen Items" 按钮
- `doUseHer()` 只是一个 stub，打了 log 但**没有实际触发场景**
- 被抓住的哥布林玩家点 "Use Her" 后**没有任何动画播放**

#### ⚪ 建议改进：catch 系统的 `doUseHer()` 未实现

```java
public void doUseHer(ServerPlayer player) {
    player.displayClientMessage(Component.literal("§eThe goblin purrs as you approach..."), false);
    // In the old code this triggered START_THROWING → followed by scene transition
    // For the new system, it should hook into SceneManager
}
```

这个方法只打印了一个消息，没有调用 `SceneManager`。catch 后的场景交互没有实际实现。

---

### 11. Kobold

| 属性 | 值 |
|------|-----|
| 动画前缀 | `kobold` |
| UI 暴露 | 全部 4 场景 |
| 特殊 | 部落系统、工作系统、产蛋系统、睡眠系统 |

**场景动画路径（全部验证存在）：**
- ✅ Missionary → `mating_press_*` (start/soft/hard/cum)
- ✅ Blowjob → `blowjobStart/SlowL/Fast/Cum`
- ✅ Doggy → `analStart/Soft/Hard/Cum`
- ✅ Boobjob → `mating_press_*` (和 Missionary 完全复用)

#### 🟡 体验问题：Paizuri/Booobjob 完全复用 Missionary

- `PAIZURI_START/SLOW/FAST/CUM` 全部映射到 `mating_press_*`
- 和 Missionary 的路径完全一致
- **玩家选择 Boobjob 看到的是和 Missionary 完全相同的体位**
- kobold.animation.json 中也没有独立的 `paizuri_*` 动画

#### 🔴 严重问题：`triggerEggLaying()` 未与场景结束绑定

- `onMatingPressCum()` 设置为产蛋定时器起点
- 但 **`handleAnimationSequencing()` 中 CUM 结束后会自动清理场景**（`IS_LOCKED=false`、`CLOTHING_STATE=0`、`setSexModAnimation=NULL`）
- 产蛋计时器 `matingPressTimer` 在场景结束后继续跑，但此时 Kobold 已经回到普通状态
- 如果玩家此时把 Kobold 带离原来位置，蛋会出现在当前位置而非捕获点
- 正确的做法应该是在 CUM 动画播放完毕后、场景清理之前触发产蛋

---

## 跨角色共性问题

### 🔴 1. `handleAnimationSequencing()` 中的 CUM 后清理

所有角色的 CUM 动画结束后，`handleAnimationSequencing()` 中做的事情：
```java
this.entityData.set(IS_LOCKED, false);
this.entityData.set(PARTNER_UUID, "null");
this.entityData.set(CLOTHING_STATE, 0);
setSexModAnimation(SexModAnimation.NULL);
```

**问题：**
- CUM 结束后玩家衣服自动穿上（CLOTHING_STATE=0）
- 如果玩家在场景中裸体，结束后自动穿好——这是一个 design choice，但值得注意
- 如果 follower 在场景中，`IS_LOCKED=false` 后它会开始自由移动，可能走远

### 🟡 2. 所有角色共享同一个 "Stop" 按钮效果

`SCENE_STOP` action 发送 `"Stop"` 字符串。但场景停止的实现：
- 没有特殊的 "STOP" 动画编码
- 需要确认 NetworkHandler 端的 `handleSceneAction("Stop")` 做了 `IS_LOCKED=false` 的清理

### ⚪ 3. Bee / Cat 缺少 idle/walk 动画

Bee 和 Slime 的动画文件中都没有 `animation.[name].idle` 和 `animation.[name].walk` 定义。`getIdleAnimationPath()` 默认返回 `"animation.bee.idle"` / `"animation.slime.idle"`，这些路径不存在，GeckoLib 会报错。

### ⚪ 4. 好感度解锁的一致性

- 4 场景角色：Missionary/Blowjob 在 ❤30 解锁，Doggy/Boobjob 在 ❤60 解锁
- 1 场景角色：统一按钮在 ❤30 解锁
- 但 `BaseGirlEntity.getAvailableActions()` 与 `InteractionScreen.getActions()` 两处逻辑**重复且可能不一致**
- 特别是 `getAvailableActions()` 中的 `? Missionary` 等锁定标记，在 InteractionScreen 的 `getActions()` 中并未使用——InteractionScreen 有自己的锁定标记逻辑

---

## 汇总

### 🔴 严重问题（玩法不可用）

| # | 角色 | 问题 | 建议修复 |
|---|------|------|---------|
| 1 | **Slime** | `blowjobstart` 动画路径不存在（应为 `blowjobsuck`） | 修改 Paizuri START 路径为 `animation.slime.blowjobsuck` |
| 2 | **Manglelie** | Missionary 4 阶段全部指向同一个 `bed_slow` | 改为 `hasSingleUnifiedScene()=true`，或实现不同阶段的动画 |
| 3 | **Kobold** | `triggerEggLaying()` 在场景清理后才触发 | 在场景清理前/场景中调用产蛋逻辑 |

### 🟡 体验问题（功能残缺/UI混乱）

| # | 角色 | 问题 | 建议修复 |
|---|------|------|---------|
| 4 | **Lucy/Mika/Momo** | 三角色共用同一套 `animation.default.*` 动画，无差异 | 分配独立动画前缀 |
| 5 | **Cat** | `hasSingleUnifiedScene()` 应返回 true | 修复返回值保持一致 |
| 6 | **Bee** | 缺少 idle/walk 动画 | 添加或修改空闲逻辑 |
| 7 | **Slime** | 缺少 idle 动画 | 添加或修改空闲逻辑 |
| 8 | **Goblin** | `doUseHer()` 为 stub，catch 后场景未实现 | 实现 `SceneManager.startBlowjob/Missionary` 调用 |
| 9 | **Goblin** | Missionary/Doggy 有动画但 UI 隐藏 | 统一 showStandard 和 UI 逻辑 |
| 10 | **Bia** | Blowjob/Paizuri 共用 anal 动画 | 考虑给 Paizuri 独立动画 |
| 11 | **Cat** | Missionary START 复用 sitting_slow | 添加独立 start 或调整描述 |
| 12 | **Ellie** | Blowjob/Doggy 共用 carry 动画 | 考虑修改命名或动画指向 |

### ⚪ 建议改进

| # | 角色 | 问题 | 建议 |
|---|------|------|------|
| 13 | **Jenny** | Missionary 按钮实际指向 doggy | 标注 "Missionary (Doggy)" 或删除按钮 |
| 14 | **Galath** | Doggy 使用 `shared.bed_*` 共享空间 | 确认是否为跨角色共享设计方案 |
| 15 | **Kobold** | Paizuri 完全复用 Missionary | 添加独立 paizuri 动画 |
| 16 | **所有** | `handleAnimationSequencing()` CUM 后统一清理 | 确认 clothing_state 重置为有意识的设计 |
| 17 | **所有** | `InteractionScreen` 和 `BaseGirlEntity.getAvailableActions()` 双重逻辑维护 | 统一为一个来源 |

---

## 动画路径逐角色核对完成性

| 角色 | 预期路径数 | 实际存在 | 缺失 |
|------|-----------|---------|------|
| Jenny | 22 | 22 | 0 |
| Ellie | 22 | 22 | 0 |
| Lucy | 22 | 22 | 0 |
| Mika | 22 | 22 | 0 |
| Momo | 22 | 22 | 0 |
| Bia | 22 | 22 | 0 |
| Allie | 20+4 | 24 | 0 |
| Cat | 11 | 11 | 0 |
| Bee | 4 | 4 | 0 |
| Slime | 16 | 15 | **1** (`blowjobstart`) |
| Galath | 20 | 20 | 0 |
| Manglelie | 4 | 4 (全部指向同一个) | 0 (但都指向一个) |
| Goblin | 22 | 22 | 0 |
| Kobold | 22 | 22 | 0 |
