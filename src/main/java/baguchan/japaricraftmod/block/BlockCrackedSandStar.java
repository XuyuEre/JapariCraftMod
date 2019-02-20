package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.init.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class BlockCrackedSandStar extends Block {
    public BlockCrackedSandStar() {
        super(Block.Properties.create(Material.GLASS).hardnessAndResistance(3.9f, 19.0f).sound(SoundType.GLASS).lightValue(5));/*硬さ*/
    }

    public boolean propagatesSkylightDown(IBlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
        return JapariItems.SANDSTAR;
    }

    public int getItemsToDropCount(IBlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
        return 1 + random.nextInt(fortune * 2 + 4);
    }

    /**
     * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
     * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
     */
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    /**
     * @deprecated call via {@link IBlockState#isFullCube()} whenever possible. Implementing/overriding is fine.
     */
    public boolean isFullCube(IBlockState state) {
        return false;
    }

}
