package baguchan.japaricraftmod.entity.ai;

import baguchan.japaricraftmod.entity.EntityServal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.Vec3d;

public class EntityAIAttackDirect extends EntityAIBase {
    /**
     * The entity that is leaping.
     */
    private final EntityServal leaper;
    /**
     * The entity that the leaper is leaping towards.
     */
    private EntityLivingBase leapTarget;
    /**
     * The entity's motionY after leaping.
     */
    private final float leapMotionY;

    public EntityAIAttackDirect(EntityServal leapingEntity, float leapMotionYIn) {
        this.leaper = leapingEntity;
        this.leapMotionY = leapMotionYIn;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        this.leapTarget = this.leaper.getAttackTarget();
        if (this.leapTarget == null) {
            return false;
        } else {
            double d0 = this.leaper.getDistanceSq(this.leapTarget);
            if (!(d0 < 20.0D) && !(d0 > 50.0D)) {
                if (!this.leaper.onGround) {
                    return false;
                } else {
                    return this.hasEnoughJump(leapTarget) && this.leaper.getRNG().nextInt(8) == 0;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return !this.leaper.onGround;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        double d0 = this.leapTarget.posX - this.leaper.posX;
        double d1 = this.leapTarget.posZ - this.leaper.posZ;
        this.leaper.setJumpAttack(true);
        float f = MathHelper.sqrt(d0 * d0 + d1 * d1);
        if ((double) f >= 1.0E-4D) {
            this.leaper.motionX += d0 / (double) f * 0.5D * (double) 2.4F + this.leaper.motionX * (double) 0.2F;
            this.leaper.motionZ += d1 / (double) f * 0.5D * (double) 2.4F + this.leaper.motionZ * (double) 0.2F;
        }

        this.leaper.motionY = (double) this.leapMotionY;
    }

    @Override
    public void resetTask() {
        this.leaper.setJumpAttack(false);
    }

    public boolean hasEnoughJump(Entity entityIn) {
        return this.leaper.world.rayTraceBlocks(new Vec3d(this.leaper.posX, this.leaper.posY + (double) this.leaper.getEyeHeight(), this.leaper.posZ), new Vec3d(entityIn.posX, entityIn.posY + (double) entityIn.getEyeHeight(), entityIn.posZ), RayTraceFluidMode.NEVER, true, false) == null;
    }

}
