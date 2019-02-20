package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.tileentity.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.*;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.*;

import javax.annotation.*;

public class BlockSandStarPortal extends Block implements ITileEntityProvider {
    protected static final AxisAlignedBB Statue_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    protected static final VoxelShape AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    private final TileEntitySandStarPortal sandStarPortal = new TileEntitySandStarPortal();

    public BlockSandStarPortal() {
        super(Block.Properties.create(Material.GLASS).hardnessAndResistance(120.0f, 4000.0f).sound(SoundType.GLASS).lightValue(5));
    }

    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return AABB;
    }


    public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }


    /**
     * @deprecated call via {@link IBlockState#hasCustomBreakingProgress()} whenever possible. Implementing/overriding is
     * fine.
     */
    @OnlyIn(Dist.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntitySandStarPortal();
    }
}
