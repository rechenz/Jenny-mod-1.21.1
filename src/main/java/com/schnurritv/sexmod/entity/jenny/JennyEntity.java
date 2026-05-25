package com.schnurritv.sexmod.entity.jenny;
import com.schnurritv.sexmod.entity.BaseGirlEntity;
import com.schnurritv.sexmod.entity.SexModAnimation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import com.schnurritv.sexmod.relationship.DialogueDB;

public class JennyEntity extends BaseGirlEntity {
    public JennyEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }

    @Override public String getGirlName() { return "jenny"; }
    @Override public String getGeoFileName() { return "jennydressed"; }
    @Override public String getNudeGeoFileName() { return "jennynude"; }

    // Jenny's personality trait: randomly says game-related things while idle
    private int chatCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;

        // Random idle chatter
        if (chatCooldown > 0) {
            chatCooldown--;
        } else if (this.getEntityData().get(IS_LOCKED)) {
            return; // don't chat during scenes
        } else {
            chatCooldown = 600 + this.random.nextInt(1200); // 30-90 seconds
            if (this.random.nextInt(100) < 15) { // 15% chance
                String msg = DialogueDB.getRandom("jenny_" + getAffectionData().getLevel().name().toLowerCase() + "_greeting");
                for (Player p : this.level().players()) {
                    if (p.distanceToSqr(this) < 144) { // within 12 blocks
                        p.displayClientMessage(Component.literal("<Jenny> " + msg), false);
                    }
                }
            }
        }
    }
}
