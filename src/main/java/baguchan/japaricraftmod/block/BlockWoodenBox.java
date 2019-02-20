package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.init.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class BlockWoodenBox extends Block {
    public BlockWoodenBox() {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(0.5f, 1.5f).sound(SoundType.SNOW).lightValue(0));/*硬さ*/
    }

    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
        if (worldIn.rand.nextInt(5) == 0) {
            return Items.SUGAR;
        } else if (worldIn.rand.nextInt(7) == 0) {
            return Items.IRON_NUGGET;
        } else if (worldIn.rand.nextInt(10) == 0) {
            return Items.GOLD_NUGGET;
        } else if (worldIn.rand.nextInt(10) == 0) {
            return JapariItems.JAPARIMAN;
        } else if (worldIn.rand.nextInt(12) == 0) {
            return JapariItems.JAPARIMAN_APPLE;
        } else if (worldIn.rand.nextInt(25) == 0) {
            return JapariItems.SANDSTAR;
        } else {
            return Items.BONE;
        }
    }

    public int getItemsToDropCount(IBlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
        return 1 + random.nextInt(fortune * 2 + 2);
    }

}
