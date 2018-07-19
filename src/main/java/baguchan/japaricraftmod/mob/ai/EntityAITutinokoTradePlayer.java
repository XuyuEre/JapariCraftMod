package baguchan.japaricraftmod.mob.ai;

import baguchan.japaricraftmod.mob.EntityTutinoko;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAITutinokoTradePlayer extends EntityAIBase {
    private final EntityTutinoko villager;

    public EntityAITutinokoTradePlayer(EntityTutinoko villagerIn) {
        this.villager = villagerIn;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (!this.villager.isEntityAlive()) {
            return false;
        } else if (this.villager.isInWater()) {
            return false;
        } else if (!this.villager.onGround) {
            return false;
        } else if (this.villager.velocityChanged) {
            return false;
        } else {
            EntityPlayer entityplayer = this.villager.getCustomer();

            if (entityplayer == null) {
                return false;
            } else if (this.villager.getDistanceSq(entityplayer) > 16.0D) {
                return false;
            } else {
                return entityplayer.openContainer != null;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.villager.getNavigator().clearPath();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.villager.setCustomer(null);
    }
}