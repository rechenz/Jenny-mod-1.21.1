# 审计报告 — 2026-08-09 新增物品 + 被动光环系统

- **审计时间：** 2026-08-09 16:20–17:10
- **审计范围：** git 今日提交 `6ca1077`（P1 物品：神灯/龙法杖/发情药水）→ `be96c70`（部落蛋）→ `c886381`（P2 实用物品：哨子/治疗护符/羁绊手环/记忆水晶）→ `a51689e`（发情药水免交互 bypass）涉及的 14 个 Java 文件
- **编译验证：** `gradlew compileJava --rerun-tasks --offline` → **BUILD SUCCESSFUL**（仅 deprecation/unchecked 警告，无错误）
- **审计方法：** 逐文件阅读 + 与 1.12.2 反编译原版（`old_decompiled/bg.java` 等）对照玩法 + 全项目交叉引用验证

---

## 结论速览

| 级别 | 数量 | 摘要 |
|------|------|------|
| 🔴 HIGH | 3 | 手环效果永久化；部落数据重启丢档；驯服可窃取整个部落 |
| 🟡 MEDIUM | 7 | 未驯服 Kobold 付费黑洞；发情判定脆弱；哨子可拉无主女孩；神灯可无限刷 Allie；Allie 召唤盖房；addMember 抹掉 tribeId |
| 🟢 LOW | 10 | 副手槽不生效、护符无归属过滤、法杖耐久不消耗、全实体线性扫描、传送落点无安全检查等 |

**总结论：需修复后再发布。** 3 个 HIGH 必须修（都不难），MEDIUM 建议本轮一并修完。

---

## 🔴 HIGH（必须修）

### H1. 羁绊手环 `bondActive` 一旦生效永远不重置 → 衰减减半永久化
- **文件：** `events/ServerForgeEvents.java:119`（`girl.setBondActive(true)`）；`entity/BaseGirlEntity.java:475,477,505`（字段/设置器/衰减处）
- **问题：** 全项目唯一调用点是 `setBondActive(true)`，**没有任何地方调用 `setBondActive(false)`**（已全库 grep 确认）。玩家带手环在女孩身边站满 2 秒后，该女孩的好感衰减被永久减半——即使玩家离开、丢掉手环、换人带，效果都持续到服务器重启/实体重载（字段不落盘，重启才消失）。
- **修复建议：** 在 `BaseGirlEntity` 增加 `private long lastBondGameTime = -100;`，tick 里：
  ```java
  if (bondActive && this.level().getGameTime() - lastBondGameTime > 60) bondActive = false;
  ```
  `ServerForgeEvents` 里改为 `girl.setBondActive(true); girl.lastBondGameTime = level.getGameTime();`（或提供 setter）。简单可靠，无需每 2 秒全量清理。

### H2. Kobold 部落数据纯内存 → 服务器重启后驯服的 Kobold 永久残废（数据丢失）
- **文件：** `entity/kobold/KoboldManager.java:24,30-31,166`；`entity/kobold/KoboldEntity.java`（readAdditionalSaveData / tick:362）
- **问题链：**
  1. `KoboldManager.TRIBES` 是 static HashMap，`init()` 是空桩（注释明说 "Future: persist"），**且全项目没有任何地方调用 `init()`** → 部落信息（ownerId/color/beds/领地/任务）从不落盘。
  2. 重启后加载驯服 Kobold：`readAdditionalSaveData` 读到 `TribeId` → 调 `addMember(tribeId, this)` → 部落不在内存中 → **KoboldManager.java:166 把 `kobold.tribeId = null`**，实体上唯一残存的部落 ID 被抹掉。
  3. 后果：`isTame()==true` 但 `tribeId==null` → tick 里 `if (isTame() && tribeId != null)`（KoboldEntity.java:362）全部失效 → 不工作/不睡觉/不打怪/不生蛋；且因为已驯服，龙法杖再点走的是 `super.mobInteract`（开 GUI）而不是驯服分支 → **永久废宠，无法恢复**。
  4. 附带：重启前下的蛋（`KoboldEggEntity.hatch()`）会 `createTribe` 重建部落，但 ownerId 为空 → 孵出的 Kobold 未驯服。
- **修复建议（二选一，推荐都做）：**
  - (a) 用 `SavedData` 持久化 `TRIBES`（tribeId → ownerId/color/beds/claimedBlocks/tasks），在 `KoboldManager.init(ServerLevel)` 里实现并在世界加载时调用；
  - (b) 至少改 `addMember`（KoboldManager.java:166）：部落缺失时**不要置 null**，保留 `tribeId` 并打 warn 日志；再在 `KoboldEntity.tick` 里加恢复逻辑：`isTame() && tribeId != null && !KoboldManager.tribeExists(tribeId)` → 用 `getMasterUUID()` 重建部落并 setOwner。

### H3. 驯服未驯服但带部落 ID 的 Kobold → 整个部落易主
- **文件：** `entity/kobold/KoboldEntity.java:281-282`
- **问题：** 驯服分支无条件 `KoboldManager.addMember(tribeId, this); KoboldManager.setOwner(tribeId, ownerId);`。当一只未驯服 Kobold 携带已有 tribeId 时（典型场景：H2 重启后 ownerless 部落成员、或带 tribeId 孵化的蛋），**任意玩家驯服它就会把整个部落（所有成员、床位、领地、任务）的 owner 改成自己**——部落劫持。
- **修复建议：** setOwner 前校验归属：
  ```java
  TribeData tribe = KoboldManager.getTribe(tribeId);
  if (tribe != null && tribe.ownerId != null && !tribe.ownerId.equals(ownerId)) {
      player.displayClientMessage(Component.literal("This kobold already belongs to another tribe!"), false);
      return InteractionResult.FAIL;
  }
  ```

---

## 🟡 MEDIUM（应该修）

### M1. 未驯服 Kobold 的"3 金 + 铁镐"付费流程是纯金币黑洞 + 服务端/客户端不同步
- **文件：** `entity/kobold/KoboldEntity.java:303-340`
- **问题：**
  1. 客户端分支（308 行）无条件 `super.mobInteract` 打开交互界面，**没有任何付费校验**；服务端同时检查金币：够 → 扣 3 金 + "Payment accepted!"，但**什么都没解锁**；不够 → FAIL。
  2. 付费实际 gate 不住任何东西：场景触发走 `SceneActionPacket`，其服务端 handler 不做驯服/付费校验。
  3. 对未驯服 Kobold 送礼物（玫瑰等）也会先撞进这个分支 → 礼物没送出去，反而白扣 3 金。每次右键都扣。
- **修复建议：** 二选一：① 服务端真正 enforce：付费成功给该玩家一个临时"已解锁"标记（存 girl 侧 `paidUntil` gameTime），mobInteract 里校验，解锁后才放行交互/礼物；② 删掉服务端自动扣钱逻辑，改为交互界面内的一次性付费入口（走 packet）。

### M2. 发情药水免费交互判定条件脆弱（用两个原版效果当"发情"标记）
- **文件：** `entity/kobold/KoboldEntity.java:311-312`（`hasEffect(REGENERATION) && hasEffect(MOVEMENT_SPEED)`）；`item/HornyPotionItem.java:27-31`
- **问题：** 任何来源同时给了 Regen + Speed（金苹果、信标、迷之炖菜、其他 mod）都会触发"发情免交互"；且药水两个效果时长不一致（Regen 10s / Speed 20s），Speed 还剩 10 秒时撞上任何 Regen 来源都能白嫖免交互。语义完全错位。
- **修复建议：** 注册一个专属 `MobEffect`（如 `horny`），药水食物效果改用它；KoboldEntity 检查 `player.hasEffect(ModEffects.HORNY)`。若不想加效果，至少检查药水自带的特定时长/等级组合（仍是 hack）。

### M3. 召唤哨子能把"无主"女孩拉走（含别人的 Allie）
- **文件：** `item/SummoningWhistleItem.java:41`
- **问题：** `if (owner != null && !owner.isEmpty() && !owner.equals(pid)) continue;` —— owner 为空/未设置时**通过过滤**。于是：任何玩家都能把别人刚生成（未送过礼物、未认领）的女孩拉走；Allie 的归属存在 `SUMMON_OWNER`（affectionData.owner 为空）→ 别人召唤的 Allie 也能被哨子拉走。
- **修复建议：** 改为严格匹配 `if (!pid.equals(owner)) continue;`；对 Allie 额外把 `SUMMON_OWNER` 纳入归属判断。

### M4. 神灯重复召唤检测有 64 格半径限制 → 可无限堆 Allie
- **文件：** `item/AlliesLampItem.java:69-73`
- **问题：** "已有 Allie"检测只扫同维度 64 格内。走远 100 格或换维度再右键 → 又召一只；配合 M5 每只都在召唤点盖房。无限刷 Allie/房子。
- **修复建议：** 全局追踪（static `Map<UUID, UUID>` player→allie，Allie 死亡/卸载时清理），或把检测扩展到整个 level（遍历 `level.getAllEntities` 找 SUMMON_OWNER 匹配的 Allie）。

### M5. Allie 未覆写 `needsHouse()` → 每次召唤都在召唤点盖一座小屋（破坏地形）
- **文件：** `entity/allie/AllieEntity.java`（无 `needsHouse` 覆写）；`entity/BaseGirlEntity.java:496-501`
- **问题：** `BaseGirlEntity.needsHouse()` 默认 true；召唤出来的 Allie 首次 tick 就在 `blockPosition()`（= 玩家召唤时的脚下位置）生成小屋——`GirlHouseGenerator.generateCottage` 会先 `clearArea` 清树/清悬空再建房，直接砸在玩家脸上。同一玩家反复召唤 = 反复盖房。其它召唤物类实体（bee/cat/goblin/kobold/lucy/mika/momo/slime/galath/manglelie）都覆写了 `needsHouse()→false`，唯独 Allie 漏了。
- **修复建议：** `AllieEntity` 加 `@Override public boolean needsHouse() { return false; }`。

### M6. `KoboldManager.addMember` 部落缺失时抹掉 `tribeId` —— 数据丢失放大器
- **文件：** `entity/kobold/KoboldManager.java:166`
- **问题：** 部落不存在时 `kobold.tribeId = null` 把实体上最后的恢复线索也销毁了（H2 的成因之一）。即使暂时不做 SavedData，这一行也让"重启后恢复"变成不可能。
- **修复建议：** 改为保留 tribeId + `Main.LOGGER.warn("Tribe {} missing, keeping kobold {} tribeId", tribeId, kobold.getUUID())`，并配合 H2 的恢复逻辑。

---

## 🟢 LOW（建议修）

- **L1. 被动光环扫描不含副手/盔甲槽** — `events/ServerForgeEvents.java:104`：`getContainerSize()` 只返回主物品栏 36 格（不含 offhand/armor）。玩家把护符放手环/副手时不生效。tooltip 写 "in your inventory" 勉强自洽，建议明确文档或改为扫 `items + offhand + armor`。性能：O(玩家×36 + 玩家×附近女孩)/2 秒，可接受，无需改。
- **L2. 治疗护符无归属过滤** — `events/ServerForgeEvents.java:115`：范围内**所有** BaseGirlEntity 都被奶，包括别人的女孩和未驯服 Kobold。建议加 owner 过滤（`owner.isEmpty() || owner.equals(player)` 才奶，无主女孩可奶）。
- **L3. 龙法杖耐久从不消耗；木棍仍可驯服** — `item/DragonStaffItem.java:11`（durability(128) 纯装饰）；`entity/kobold/KoboldEntity.java:261`（`Items.STICK` 兼容分支还在）。与"用龙法杖取代木棍"的意图不符。建议驯服成功 `stack.hurtAndBreak(1, player, ...)`；木棍分支按设计决定去留。
- **L4. 记忆水晶全实体线性扫描 + 传送无安全检查** — `item/MemoryCrystalItem.java:52-53`：`((ServerLevel) level).getEntities().getAll()` 遍历整服实体找一个 UUID，应改用 `getEntities().get(UUID)`（LevelEntityGetter 直接支持）；`:61` 传送到 `housePos.getY()+1` 无方块/虚空/岩浆检查，建议找安全落点。
- **L5. 召唤哨子传送落点无安全检查、无冷却** — `item/SummoningWhistleItem.java:52`：传送到玩家 feet Y（+1/+1 偏移），可能卡进地形；可连点。建议安全落点 + 可选 1-2s 冷却。
- **L6. 部落蛋/神灯出生点无空气检查** — `item/TribeEggItem.java:47`、`item/AlliesLampItem.java:81`：直接在玩家位置生成，可能卡进方块窒息。1.12.2 原版神灯是生成在玩家前方 2 格（`old_decompiled/bg.java`），可参考。
- **L7. 神灯沙恐惧症与原版行为有出入** — 1.12.2 原版：先召唤 Allie，检测她脚下是沙子时播放 SUMMON_SAND 恐慌演出（bg.java:81）；移植版改为**拒绝召唤 + 提示**。功能上"沙恐惧症"达成、更安全，但原版演出缺失。保留现状可接受，属可选优化。
- **L8. `AllieEntity.java:23` 无用 client import** — `import net.minecraft.client.Minecraft;` 未使用。common 类里留 client import 是服务端 NoClassDefFoundError 隐患（当前未使用不会触发类加载，但应删掉，防止以后顺手用上）。
- **L9. `tickDefendQuests` 每 2 秒全实体遍历** — `networking/SceneActionPacket.java:166`：`level.getEntities().getAll()` 与被动光环同周期（ServerForgeEvents.java:86/90），大服 O(全服实体)。非今日新增但耦合在同一 handler，建议改 `getEntitiesOfClass(BaseGirlEntity.class, ...)` 或降频。
- **L10. 已驯服 Kobold + 法杖 → 无归属校验** — `entity/kobold/KoboldEntity.java:289`：任何玩家拿法杖点别人的驯服 Kobold 都能开交互界面，且 `SceneActionPacket` 服务端无 owner 校验。建议服务端场景/任务启动加 owner 校验（与 M1 一并考虑）。

---

## ✅ 通过项（审计确认无问题）

- **编译：** 全量重编译成功（`gradlew compileJava --rerun-tasks --offline` → BUILD SUCCESSFUL）。
- **服务端/客户端隔离：** 审计的 14 个文件均无对 client 类（Screen/Minecraft 等）的可达引用；`GuideBookItem`/`AllieEntity` 走 `ClientScreenHelper` 反射（util 包注释已说明原因）。唯一例外是 AllieEntity 的未使用 import（L8）。
- **NBT/DataComponents 一致性：** `MemoryCrystalItem`（CUSTOM_DATA + "BoundOwner"，`getOrDefault/copyTag` 读、`stack.update` 写）读写一致；`KoboldEntity.layEgg`（写 CUSTOM_DATA）与 `KoboldEggItem.useOn`（读 CUSTOM_DATA，tribeID 用 putUUID/hasUUID）读写一致。
- **事务安全：** 所有物品消耗均在服务端权威处理（TribeEggItem:71 `stack.shrink`、NameTag、付费扣金），客户端无幻影扣除/复制路径。
- **Entity 遍历 API：** `getEntitiesOfClass`、`getEntities().getAll()` 用法均正确（后者仅性能问题，见 L4/L9）。
- **1.12.2 玩法对照：** 神灯有沙恐惧 + 2s 召唤冷却 ✓（细节偏差见 L7）；龙法杖驯服创建/加入部落 ✓（归属缺陷见 H3）；发情药水"免交互"已实现 ✓（判定脆弱见 M2）。
- **被动光环性能：** 2 秒周期、空间索引查询（getEntitiesOfClass 走区块实体段），复杂度 O(玩家×(36+附近女孩))，可接受；唯一注意点是同 handler 里 tickDefendQuests 的全实体遍历（L9）。

---

## 修复优先级建议

1. **发布前必修（HIGH）：** H1（一行）、H2（SavedData 或至少 M6 保底 + tick 恢复）、H3（归属校验）。
2. **本轮建议一并修（MEDIUM）：** M1 付费黑洞、M2 发情判定、M3 哨子无主拉人、M4 无限刷 Allie、M5 Allie 盖房、M6 tribeId 保底。
3. **随手修（LOW）：** L8 删 import、L3 法杖耐久、L4/L5 传送安全检查、L1 副手槽。

**验证建议：** 修复后跑一次「驯服 Kobold → 存档 → 重启服务器 → 确认部落/驯服状态恢复」的冒烟测试，以及「带手环离开 → 确认衰减恢复」的测试。
