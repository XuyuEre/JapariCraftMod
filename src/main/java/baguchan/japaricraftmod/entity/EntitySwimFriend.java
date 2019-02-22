package baguchan.japaricraftmod.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

public class EntitySwimFriend extends EntityFriend {
    public EntitySwimFriend(EntityType<?> type, World p_i48574_2_) {
        super(type, p_i48574_2_);
        this.moveHelper = new EntityKouteiPenguin.MoveHelper(this);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.stepHeight = 1.0F;
    }

    public boolean isPushedByWater() {
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected float determineNextStepDistance() {
        return this.distanceWalkedOnStepModified + 0.15F;
    }

    protected PathNavigate createNavigator(World worldIn) {
        return new EntitySwimFriend.PathNavigater(this, worldIn);
    }


    public void travel(float strafe, float vertical, float forward) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(strafe, vertical, forward, 0.01F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double) 0.9F;
            this.motionY *= (double) 0.9F;
            this.motionZ *= (double) 0.9F;
        } else {
            super.travel(strafe, vertical, forward);
        }

    }

    public void updateSwimming() {
        if (!this.world.isRemote) {
            if (this.isServerWorld() && this.isInWater()) {
                this.setSwimming(true);
            } else {
                this.setSwimming(false);
            }
        }
    }

    public static class AIWander extends EntityAIWander {
        private final EntityFriend friend;

        protected AIWander(EntityFriend entityfriend, double speed) {
            super(entityfriend, speed);
            this.friend = entityfriend;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            return !this.entity.isInWater();
        }
    }

    static class AITravel extends EntityAIBase {
        private final EntityFriend friend;
        private final double field_203138_b;
        private boolean field_203139_c;

        protected AITravel(EntityFriend p_i48811_1_, double p_i48811_2_) {
            this.friend = p_i48811_1_;
            this.field_203138_b = p_i48811_2_;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            return this.friend.isInWater();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.friend.getNavigator().noPath()) {
                Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.friend, 16, 7);

                if (vec3d != null) {
                    int i = MathHelper.floor(vec3d.x);
                    int j = MathHelper.floor(vec3d.z);
                    int k = 34;
                    MutableBoundingBox mutableboundingbox = new MutableBoundingBox(i - 34, 0, j - 34, i + 34, 0, j + 34);
                    if (!this.friend.world.isAreaLoaded(mutableboundingbox)) {
                        vec3d = null;
                    }
                }

                if (vec3d == null) {
                    this.field_203139_c = true;
                    return;
                }

                this.friend.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.field_203138_b);
            }

        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.friend.getNavigator().noPath() && !this.field_203139_c;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            super.resetTask();
        }
    }

    static class AIGoToAir extends EntityAIMoveToBlock {
        private final EntityFriend friend;

        protected AIGoToAir(EntityFriend friend, double speed) {
            super(friend, speed, 24);
            this.friend = friend;
            this.field_203112_e = -1;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.friend.isInWater() && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.friend.world, this.destinationBlock);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (this.friend.isChild() && !this.friend.isInWater()) {
                return super.shouldExecute();
            } else {
                return (this.friend.isInWater()) && super.shouldExecute();
            }
        }

        public int getTargetYOffset() {
            return 1;
        }

        public boolean shouldMove() {
            return this.timeoutCounter % 160 == 0;
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean shouldMoveTo(IWorldReaderBase worldIn, BlockPos pos) {
            Block block = worldIn.getBlockState(pos).getBlock();
            IBlockState air1 = worldIn.getBlockState(pos.up());
            IBlockState air2 = worldIn.getBlockState(pos.up(2));
            if (air1.isAir() && air2.isAir()) {
                return block == Blocks.AIR;
            } else {
                return false;
            }
        }
    }

    static class AIGoToWater extends EntityAIMoveToBlock {
        private final EntityFriend friend;

        protected AIGoToWater(EntityFriend friend, double speed) {
            super(friend, speed, 24);
            this.friend = friend;
            this.field_203112_e = -1;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.friend.isInWater() && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.friend.world, this.destinationBlock);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (this.friend.isChild() && !this.friend.isInWater()) {
                return super.shouldExecute();
            } else {
                return (!this.friend.isInWater() || this.friend.isTamed()) && super.shouldExecute();
            }
        }

        public int getTargetYOffset() {
            return 1;
        }

        public boolean shouldMove() {
            return this.timeoutCounter % 160 == 0;
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean shouldMoveTo(IWorldReaderBase worldIn, BlockPos pos) {
            Block block = worldIn.getBlockState(pos).getBlock();
            return block == Blocks.WATER;
        }
    }

    static class MoveHelper extends EntityMoveHelper {
        private final EntitySwimFriend swimfriend;

        MoveHelper(EntitySwimFriend swimfriendIn) {
            super(swimfriendIn);
            this.swimfriend = swimfriendIn;
        }

        private void updateSpeed() {
            if (this.swimfriend.isInWater()) {
                this.swimfriend.motionY += 0.005D;

            }

        }

        public void tick() {
            this.updateSpeed();
            if (this.swimfriend.isInWater()) {
                double d0 = this.posX - this.swimfriend.posX;
                double d1 = this.posY - this.swimfriend.posY;
                double d2 = this.posZ - this.swimfriend.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.swimfriend.rotationYaw = this.limitAngle(this.swimfriend.rotationYaw, f, 90.0F);
                this.swimfriend.renderYawOffset = this.swimfriend.rotationYaw;
                float f1 = (float) (this.speed * this.swimfriend.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());


                this.swimfriend.setAIMoveSpeed(this.swimfriend.getAIMoveSpeed() + (f1 - this.swimfriend.getAIMoveSpeed()) * 0.19F);
                this.swimfriend.motionX += (double) this.swimfriend.getAIMoveSpeed() * d0 * 0.005D;
                this.swimfriend.motionZ += (double) this.swimfriend.getAIMoveSpeed() * d2 * 0.005D;

                this.swimfriend.motionY += (double) this.swimfriend.getAIMoveSpeed() * d1 * 0.1D;
            } else {
                super.tick();
            }
        }
    }

    static class PathNavigater extends PathNavigateSwimmer {
        PathNavigater(EntitySwimFriend p_i48815_1_, World p_i48815_2_) {
            super(p_i48815_1_, p_i48815_2_);
        }

        /**
         * If on ground or swimming and can swim
         */
        protected boolean canNavigate() {
            return true;
        }

        protected PathFinder getPathFinder() {
            return new PathFinder(new WalkAndSwimNodeProcessor());
        }

        public boolean canEntityStandOnPos(BlockPos pos) {
            if (this.entity instanceof EntitySwimFriend) {
                EntitySwimFriend entitywater = (EntitySwimFriend) this.entity;

                if (this.entity.isInWater()) {
                    return this.world.getBlockState(pos).getMaterial() == Material.WATER;
                }
            }

            return !this.world.getBlockState(pos.down()).isAir();
        }
    }
}
