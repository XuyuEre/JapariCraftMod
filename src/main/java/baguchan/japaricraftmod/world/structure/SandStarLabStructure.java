package baguchan.japaricraftmod.world.structure;

import baguchan.japaricraftmod.world.structure.config.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.*;

import java.util.*;

public class SandStarLabStructure extends Structure<SandStarLabConfig> {
    protected boolean hasStartAt(IChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
        Biome biome = chunkGen.getBiomeProvider().getBiome(new BlockPos((chunkPosX << 4) + 9, 0, (chunkPosZ << 4) + 9), Biomes.DEFAULT);
        if (chunkGen.hasStructure(biome, JapariFeature.SANDSTARLAB_STRUCTURE)) {
            ((SharedSeedRandom) rand).setLargeFeatureSeedWithSalt(chunkGen.getSeed(), chunkPosX, chunkPosZ, 16486320);
            SandStarLabConfig sandStarLabConfig = (SandStarLabConfig) chunkGen.getStructureConfig(biome, JapariFeature.SANDSTARLAB_STRUCTURE);
            return rand.nextFloat() < sandStarLabConfig.chance;
        } else {
            return false;
        }
    }

    protected boolean isEnabledIn(IWorld worldIn) {
        return worldIn.getWorldInfo().isMapFeaturesEnabled();
    }

    protected StructureStart makeStart(IWorld worldIn, IChunkGenerator<?> generator, SharedSeedRandom random, int x, int z) {
        Biome biome = generator.getBiomeProvider().getBiome(new BlockPos((x << 4) + 9, 0, (z << 4) + 9), Biomes.DEFAULT);
        return new Start(worldIn, generator, random, x, z, biome);
    }

    protected String getStructureName() {
        return "SandStarLab";
    }

    public int getSize() {
        return 1;
    }

    public static class Start extends StructureStart {
        public Start() {
        }

        public Start(IWorld p_i48890_1_, IChunkGenerator<?> p_i48890_2_, SharedSeedRandom p_i48890_3_, int p_i48890_4_, int p_i48890_5_, Biome p_i48890_6_) {
            super(p_i48890_4_, p_i48890_5_, p_i48890_6_, p_i48890_3_, p_i48890_1_.getSeed());
            SandStarLabConfig sandstarconfig = (SandStarLabConfig) p_i48890_2_.getStructureConfig(p_i48890_6_, JapariFeature.SANDSTARLAB_STRUCTURE);
            int i = p_i48890_4_ * 16;
            int j = p_i48890_5_ * 16;
            BlockPos blockpos = new BlockPos(i, 90, j);
            Rotation rotation = Rotation.values()[p_i48890_3_.nextInt(Rotation.values().length)];
            TemplateManager templatemanager = p_i48890_1_.getSaveHandler().getStructureTemplateManager();
            SandStarLabPiece.load(templatemanager, blockpos, rotation, this.components, p_i48890_3_, sandstarconfig);
            this.recalculateStructureSize(p_i48890_1_);
        }

        public BlockPos getPos() {
            return new BlockPos((this.chunkPosX << 4) + 9, 0, (this.chunkPosZ << 4) + 9);
        }
    }
}