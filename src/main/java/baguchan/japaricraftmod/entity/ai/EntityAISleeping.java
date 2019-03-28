package baguchan.japaricraftmod.entity.ai;

import baguchan.japaricraftmod.entity.EntityFriend;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReaderBase;

public class EntityAISleeping extends EntityAIMoveToBlock {
    private final EntityFriend friends;

    public EntityAISleeping(EntityFriend friendsIn, double p_i45315_2_) {
        super(friendsIn, p_i45315_2_, 8);
        this.friends = friendsIn;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return this.friends.isTamed() && !this.friends.isSitting() && super.shouldExecute();
    }

    public boolean shouldContinueExecuting() {
        return super.shouldContinueExecuting() && this.friends.isPlayerSleeping();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();
        this.friends.getAISit().setSitting(false);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        super.resetTask();
        this.friends.setSitting(false);
        this.friends.setSleeping(true);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        this.friends.getAISit().setSitting(false);
        if (!this.getIsAboveDestination()) {
            this.friends.setSitting(false);
        } else if (!this.friends.isSitting()) {
            this.friends.setSleeping(true);
        }

    }

    /**
     * Return true to set given position as destination
     */
    protected boolean shouldMoveTo(IWorldReaderBase worldIn, BlockPos pos) {
        if (!worldIn.isAirBlock(pos.up())) {
            return false;
        } else {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            return block instanceof BlockBed && iblockstate.get(BlockBed.PART) != BedPart.HEAD;

        }
    }
}