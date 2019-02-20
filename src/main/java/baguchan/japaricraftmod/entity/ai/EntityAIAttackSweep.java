package baguchan.japaricraftmod.entity.ai;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.pathfinding.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EntityAIAttackSweep extends EntityAIBase {
    protected EntityCreature attacker;
    /**
     * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
     */
    protected int attackTick;
    /**
     * The speed with which the mob will approach the target
     */
    private final double speedTowardsTarget;
    /**
     * When true, the mob will continue chasing its target, even if it can't find a path to them right now.
     */
    private final boolean longMemory;
    /**
     * The PathEntity of our entity.
     */
    private Path path;
    private int delayCounter;
    private double targetX;
    private double targetY;
    private double targetZ;
    protected final int attackInterval = 20;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public EntityAIAttackSweep(EntityCreature creature, double speedIn, boolean useLongMemory) {
        this.attacker = creature;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
        if (entitylivingbase == null) {
            return false;
        } else if (!entitylivingbase.isAlive()) {
            return false;
        } else {
            if (canPenalize) {
                if (--this.delayCounter <= 0) {
                    this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
                    this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
                    return this.path != null;
                } else {
                    return true;
                }
            }
            this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
            if (this.path != null) {
                return true;
            } else {
                return this.getAttackReachSqr(entitylivingbase) >= this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getBoundingBox().minY, entitylivingbase.posZ);
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
        if (entitylivingbase == null) {
            return false;
        } else if (!entitylivingbase.isAlive()) {
            return false;
        } else if (!this.longMemory) {
            return !this.attacker.getNavigator().noPath();
        } else if (!this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(entitylivingbase))) {
            return false;
        } else {
            return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).isSpectator() && !((EntityPlayer) entitylivingbase).isCreative();
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
        this.delayCounter = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
        if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer) entitylivingbase).isSpectator() || ((EntityPlayer) entitylivingbase).isCreative())) {
            this.attacker.setAttackTarget(null);
        }

        this.attacker.getNavigator().clearPath();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
        this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
        double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getBoundingBox().minY, entitylivingbase.posZ);
        --this.delayCounter;
        if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
            this.targetX = entitylivingbase.posX;
            this.targetY = entitylivingbase.getBoundingBox().minY;
            this.targetZ = entitylivingbase.posZ;
            this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
            if (this.canPenalize) {
                this.delayCounter += failedPathFindingPenalty;
                if (this.attacker.getNavigator().getPath() != null) {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                    if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                } else {
                    failedPathFindingPenalty += 10;
                }
            }
            if (d0 > 1024.0D) {
                this.delayCounter += 10;
            } else if (d0 > 256.0D) {
                this.delayCounter += 5;
            }

            if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget)) {
                this.delayCounter += 15;
            }
        }

        this.attackTick = Math.max(this.attackTick - 1, 0);
        this.checkAndPerformAttack(entitylivingbase, d0);
    }


    protected void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.attackTick <= 0) {
            float f3 = 0.6F * (float) attacker.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
            //Range attack on other enemy mobs
            for (EntityMob entitymob : this.attacker.world.getEntitiesWithinAABB(EntityMob.class, attacker.getAttackTarget().getBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
                if (entitymob != attacker && entitymob != attacker.getAttackTarget() && !entitymob.isOnSameTeam(entitymob) && entitymob.getDistanceSq(entitymob) < 9.0D) {
                    entitymob.knockBack(attacker, 0.4F, (double) MathHelper.sin(attacker.rotationYaw * 0.017453292F), (double) (-MathHelper.cos(attacker.rotationYaw * 0.017453292F)));
                    entitymob.attackEntityFrom(DamageSource.causeMobDamage(attacker), f3);
                }
            }
            this.attacker.world.playSound(null, attacker.posX, attacker.posY, attacker.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, attacker.getSoundCategory(), 1.0F, 1.0F);

            this.spawnSweepParticles();
            this.attackTick = 16;
            this.attacker.swingArm(EnumHand.MAIN_HAND);
            this.attacker.attackEntityAsMob(enemy);
        }
    }

    //スイープ攻撃時ののエフェクト
    public void spawnSweepParticles() {
        double d0 = (double) (-MathHelper.sin(attacker.rotationYaw * 0.017453292F));
        double d1 = (double) MathHelper.cos(attacker.rotationYaw * 0.017453292F);

        if (this.attacker.world instanceof WorldServer) {
            ((WorldServer) this.attacker.world).spawnParticle(Particles.SWEEP_ATTACK, attacker.posX + d0, attacker.posY + (double) attacker.height * 0.5D, attacker.posZ + d1, 0, d0, 0.0D, d1, 0.0D);
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double) (this.attacker.width * 2.6F * this.attacker.width * 2.6F + attackTarget.width);
    }
}