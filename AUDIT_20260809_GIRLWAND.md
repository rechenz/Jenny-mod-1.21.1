# Audit Report — Girl Wand (NPC Editor Wand)

- **Date:** 2026-08-09
- **Project:** Jenny-mod-1.21.1 (1.12.2 SexMod port, Forge 1.21.1-52.0.7)
- **Scope:** New Girl Wand feature (commit 9a96fbf) + re-audit of previous fixes (commit a3c62a3)
- **Verification performed:**
  - ✅ `gradlew compileJava --no-daemon` (JDK 21): **BUILD SUCCESSFUL**
  - ✅ Bytecode scan of compiled classes: GirlWandItem / NpcEditPacket / ModItems / ServerForgeEvents / KoboldEntity / ModEffects / 5 audited items — **zero `net/minecraft/client/` or `sexmod/client/` references** → dedicated-server safe
  - ✅ Vanilla 1.21.1 interaction flow verified by decompiling `Mob` / `Player` / `MultiPlayerGameMode` (official-mapped jar in gradle cache)
  - ✅ `Class.getConstructor` exact-match semantics verified with a live JDK 21 test

---

## 🔴 HIGH (must fix before release)

### H1. GirlWandItem.interactLivingEntity 永远不会被调用 — 法杖完全无效
**File:** `src/main/java/com/schnurritv/sexmod/item/GirlWandItem.java:47-71`

**问题：** 1.21.1 的实体右键流程（已通过字节码逐条验证）：
1. `Player.interactOn` → `Entity.interact(player, hand)`
2. `Mob.interact`（final）：先 `checkAndHandleImportantInteractions`（**只有手持 NAME_TAG 才调 `ItemStack.interactLivingEntity`**，外加 SpawnEgg 逻辑）→ `super.interact`（PASS）→ `mobInteract`（虚分派）
3. `BaseGirlEntity.mobInteract` 客户端**恒返回 SUCCESS**（礼物/任务/开 InteractionScreen），服务端恒 CONSUME —— 永远消耗交互
4. 因此 `Player.interactOn` 里 `entity.interact()` 之后那段 `itemstack.interactLivingEntity(...)` **对任何女孩都是死代码**，`GirlWandItem.interactLivingEntity` 重写永远不会触发

**后果：** 用法杖右键女孩 → 打开的是女孩普通互动菜单（且该菜单还有 H2 的问题），NpcEditorScreen 永远打不开；`interactLivingEntity` 里的服务端归属校验分支也是死代码。P1 收官物品实际不可用（此前 "RCON verified" 只验证了物品注册，未验证客户端交互）。

**修复建议：** 采用项目里 DragonStaff/KoboldEntity 已验证的模式——在 `BaseGirlEntity.mobInteract` 里优先处理法杖：
```java
// BaseGirlEntity.mobInteract 开头：
if (held.getItem() instanceof GirlWandItem) {
    if (this.level().isClientSide) {
        ClientScreenHelper.openNpcEditor(this);
        return InteractionResult.SUCCESS;
    }
    // server: 归属校验 + 提示（把现 interactLivingEntity 服务端分支挪过来）
    return InteractionResult.CONSUME;
}
```
（或另挂 `PlayerInteractEvent.EntityInteract` 处理器，result=SUCCESS 抢先消耗。）

### H2. ClientScreenHelper.openNpcEditor 反射构造函数查找必然失败 — 屏幕打不开
**File:** `src/main/java/com/schnurritv/sexmod/util/ClientScreenHelper.java:100-118`（新增部分）
**同源旧缺陷：** `ClientScreenHelper.openInteraction`（`:24-38`）+ `BaseGirlEntity.java:268`

**问题：** `cls.getConstructor(girlEntity.getClass())` —— `NpcEditorScreen` 构造函数参数是 `BaseGirlEntity`，而 `girlEntity.getClass()` 返回运行时具体类（JennyEntity.class / KoboldEntity.class …）。`Class.getConstructor` 是**严格精确匹配、不沿继承树查找**（已用 JDK 21 实测确认：`Base.class.getConstructor(Sub.class)` → NoSuchMethodException）。因此对全部 14 个具体女孩子类，`openNpcEditor` 都会抛 NoSuchMethodException，被 catch 吞掉只打 error 日志，**屏幕永远不会打开**（即使 H1 修好也一样）。

**注意：** 这是**存量 bug** —— `openInteraction` 用同一模式，意味着整个互动菜单（送礼/场景入口）在真实客户端上可能从来没打开过（此前所有验证都是服务端/代码级）。修复时应一并处理。

**修复建议：** 直接精确匹配：
```java
Class<?> cls = Class.forName("com.schnurritv.sexmod.client.gui.NpcEditorScreen");
java.lang.reflect.Constructor<?> ctor = cls.getConstructor(com.schnurritv.sexmod.entity.BaseGirlEntity.class);
```
（BaseGirlEntity 是 common 类，在 common 字节码里引用完全安全。）`openInteraction` 同样改成 `getConstructor(BaseGirlEntity.class)`。

---

## 🟡 MEDIUM (should fix)

### M1. NpcEditPacket 无"持有法杖"校验 + ADD_AFFECTION 无冷却 — 刷好感/滥用
**File:** `src/main/java/com/schnurritv/sexmod/networking/NpcEditPacket.java:57-64, 66-73`

**问题：** 服务端只校验"女孩归属（或未归属）"，**不校验发送者手里是否真拿着 Girl Wand**，也没有冷却/次数限制：
- 任意客户端可绕过物品直接狂发 `ADD_AFFECTION`（每次 +10，无任何间隔）→ 秒满好感
- 未归属女孩可被任意玩家无成本改名/脱衣/传送/刷好感（共享服上可用来恶心人：把别人刚刷出来的女孩改侮辱名、扒光）
- 注释里 "unowned 可编辑" 是设计意图，但**至少应加"主手持有 GirlWandItem"校验 + 每动作小冷却**（如 10 tick），成本极低

**修复建议：** handle 开头：`if (!(player.getMainHandItem().getItem() instanceof GirlWandItem) && !(player.getOffhandItem().getItem() instanceof GirlWandItem)) return;`；ADD_AFFECTION 加 per-player cooldown（存 player 维度 map 或女孩 NBT）。

### M2. GO_HOME 无安全落点搜索 — 女孩可能被传进方块窒息
**File:** `src/main/java/com/schnurritv/sexmod/networking/NpcEditPacket.java:89-97`

**问题：** `girl.teleportTo(pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5)` 直接传送到 housePos。housePos 是女孩首 tick 的 blockPosition，可能是实心方块（小屋就地生成）；没有像 SummoningWhistle（L5）/MemoryCrystal（L4）那样向上搜空位 → 女孩卡进方块持续窒息，可能死亡。

**修复建议：** 复用 MemoryCrystal 的向上搜空逻辑：从 housePos 向上找两格 air 再传。

### M3. tickPassiveItems 的 BondBracelet 分支漏了归属过滤（上次审计修了一半）
**File:** `src/main/java/com/schnurritv/sexmod/events/ServerForgeEvents.java:126`

**问题：** 同函数 heal 分支（:102-106）有 L2 归属过滤，但 bond 分支对 16 格内**所有**女孩 `setBondActive(true)`（含别人家的）→ 任意玩家拿个手环就能让别人的女孩好感衰减减半。

**修复建议：** `if (hasBond && (owner.isEmpty() || owner.equals(playerId))) girl.setBondActive(true);`

### M4. RENAME 允许 § 格式码/换行 — 视觉滥用
**File:** `src/main/java/com/schnurritv/sexmod/networking/NpcEditPacket.java:80-85`

**问题：** 长度校验 OK（1-32 字符），但 `Component.literal` 里的 `§` 会被字体渲染器当传统格式码解析（本项目到处用 § 上色，效果同理）→ 可把名字改成全屏乱码/闪字（`§k`）/多行。不破坏存档，纯视觉恶心人。

**修复建议：** 服务端剥掉 `§`：`name = name.replace("§", "")`（或限制为字母数字）。

---

## 🟢 LOW (nice to have)

| # | 文件:行 | 问题 | 建议 |
|---|---------|------|------|
| L1 | GirlWandItem.java:17 | `durability(256)` 从不消耗，属性误导 | 在 NpcEditPacket（配合 M1 的持杖校验）里每次动作 `hurtAndBreak(1,…)`，或去掉 durability |
| L2 | NpcEditorScreen.java:48-58 | 按钮文案 emoji（♥👗🏠✏）默认字体渲染成豆腐块 | 换 ASCII/符号（如 "[+] Affection"）或引自定义字体 |
| L3 | NpcEditPacket.java:44 | `readEnum` 越界 ordinal → decode 期 AIOOBE，恶意包可踢自己连接（非服务端崩溃） | decode 里 `Math.abs(ordinal) % values().length` 或 try-catch 后 return null |
| L4 | NpcEditorScreen.java:33-70 | 不是自己女孩时按钮全亮、点了才被服务端拒绝 | 客户端按同步的 owner 禁用按钮（可选） |
| L5 | MemoryCrystalItem.java:88-105 | 绑定无归属校验，任何人可绑到任意女孩并传送去她家（轻微隐私） | bind 时过滤 `owner 为空或 == 自己` |

---

## 上一轮修复复核（commit a3c62a3）— 结论：全部通过 ✅

- **ServerForgeEvents.tickPassiveItems** — 41 槽扫描（36 主 + 4 甲 + 1 副手）正确、不越界；heal 归属过滤 ✅（唯一残留见 M3）
- **KoboldEntity** — paidUntilGameTime NBT 持久化/恢复 ✅；部落重启重建 ✅；horny Holder 判效 ✅；Dragon Staff 非创造模式消耗耐久 ✅
- **ModEffects.hornyHolder()** — `Holder.direct` 按值相等，`hasEffect`/药水施加匹配正确 ✅（注册单例，无泄漏）
- **HornyPotionItem / AlliesLampItem / SummoningWhistleItem / MemoryCrystalItem / DragonStaffItem** — 冷却、安全落点、归属严格匹配均符合上次审计结论 ✅（仅 L5 一条小尾巴）
- **服务端隔离** — 编译产物字节码扫描零 client 引用（ClientScreenHelper 的 Minecraft 引用只存在于 DistExecutor 的 lambda 方法内，惰性解析，测试服已验证可启动）✅

---

## 总结论：🔴 **需修复后再发布**

- 2 个 HIGH 直接导致 **Girl Wand 功能 100% 不可用**（交互钩子死代码 + 反射构造查找必失败），且 H2 同时暴露了存量 `openInteraction` 的同类 bug（互动菜单可能从未在真客户端打开过，建议同批修复并跑一次真实客户端冒烟测试）。
- 修复后建议实测清单：右键女孩开编辑器 → 改名/换装/送回家/好感各自生效、他人女孩被拒、Dedicated Server 启动无异常。

**建议修复顺序：** H2（改 getConstructor）→ H1（mobInteract 接法杖）→ M1/M2 → 客户端冒烟。
