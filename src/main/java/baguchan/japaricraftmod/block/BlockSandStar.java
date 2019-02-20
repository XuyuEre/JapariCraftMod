package baguchan.japaricraftmod.block;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class BlockSandStar extends Block {
    public BlockSandStar() {
        super(Block.Properties.create(Material.GLASS).hardnessAndResistance(4.0f, 20.0f).sound(SoundType.GLASS).lightValue(8));/*硬さ*/
    }

    public boolean propagatesSkylightDown(IBlockState state, IBlockReader reader, BlockPos pos) {
        return false;
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
