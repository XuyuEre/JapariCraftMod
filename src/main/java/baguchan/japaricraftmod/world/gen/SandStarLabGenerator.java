package baguchan.japaricraftmod.world.gen;

import baguchan.japaricraftmod.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import net.minecraft.world.dimension.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.template.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;

import java.util.*;

public class SandStarLabGenerator implements IWorldGenerator {
    public static final ResourceLocation SANDSTARLAB = new ResourceLocation(JapariCraftMod.MODID, "sandstarlab");

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!(world instanceof WorldServer))
            return;
        WorldServer sWorld = (WorldServer) world;

        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);

        BlockPos pos = getHeight(world, new BlockPos(x, 0, z));
        if (world.getWorld().getDimension().getType() == DimensionType.OVERWORLD) {
            if (BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.PLAINS) || BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.WASTELAND)) {
                if (random.nextInt(600) == 0) {

                    IBlockState state = world.getBlockState(pos.down());

                    pos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());

                    generateLabAt(sWorld, random, pos);
                }
            }
        }

    }

    public static BlockPos getHeight(World world, BlockPos pos) {

        for (int y = 0; y < 256; y++) {

            BlockPos pos1 = pos.up(y);

            if (world.getBlockState(pos1.up()).getBlock() == Blocks.AIR && world.getBlockState(pos1.down()).getBlock() != Blocks.AIR) {

                return pos1;

            }

        }

        return pos;

    }

    public static void generateLabAt(WorldServer world, Random random, BlockPos pos) {
        Template template = world.getStructureTemplateManager().getTemplate(SANDSTARLAB);
        PlacementSettings settings = new PlacementSettings();
        settings.setRotation(Rotation.values()[random.nextInt(Rotation.values().length)]);

        BlockPos size = template.getSize();
        for (int x = 0; x < size.getX(); x++)
            for (int y = 0; y < size.getY(); y++)
                for (int z = 0; z < size.getZ(); z++) {

                    template.addBlocksToWorld(world, pos, settings);
                }
    }
}