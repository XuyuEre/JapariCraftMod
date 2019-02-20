package baguchan.japaricraftmod.world;

import baguchan.japaricraftmod.init.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.*;
import net.minecraftforge.fml.common.registry.*;

import java.util.*;


public class ComponentJapariHouse1 extends VillagePieces.Village {

    private int friendsSpawned;

    public ComponentJapariHouse1() {

    }

    public ComponentJapariHouse1(VillagePieces.Start p_i2107_1_, int p_i2107_2_, Random p_i2106_3_, MutableBoundingBox p_i2106_4_, EnumFacing facing) {
        super(p_i2107_1_, p_i2107_2_);
        this.setCoordBaseMode(facing);
        this.boundingBox = p_i2106_4_;
    }

    protected void writeStructureToNBT(NBTTagCompound tagCompound) {
        super.writeStructureToNBT(tagCompound);
        tagCompound.setInt("FCount", this.friendsSpawned);
    }

    /**
     * (abstract) Helper method to read subclass data from NBT
     */
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
        super.readStructureFromNBT(tagCompound, p_143011_2_);
        this.friendsSpawned = tagCompound.getInt("FCount");
    }

    @Override
    public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_) {
        return false;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.averageGroundLvl < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
        }

        IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.ORANGE_CONCRETE_POWDER.getDefaultState());
        IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().with(BlockStairs.FACING, EnumFacing.NORTH));
        IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().with(BlockStairs.FACING, EnumFacing.SOUTH));
        IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().with(BlockStairs.FACING, EnumFacing.WEST));
        IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.OAK_PLANKS.getDefaultState());
        IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.OAK_LOG.getDefaultState());
        IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 6, 8, 0, 10, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
        this.setBlockState(worldIn, iblockstate, 6, 0, 6, structureBoundingBoxIn);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 2, 1, 10, iblockstate6, iblockstate6, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 6, 8, 1, 10, iblockstate6, iblockstate6, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 10, 7, 1, 10, iblockstate6, iblockstate6, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 7, 0, 4, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 3, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 8, 3, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 7, 1, 0, iblockstate, iblockstate, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 7, 1, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 3, 0, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 7, 3, 5, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 8, 4, 1, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 4, 8, 4, 4, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 2, 8, 5, 3, iblockstate4, iblockstate4, false);
        this.setBlockState(worldIn, iblockstate4, 0, 4, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate4, 0, 4, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate4, 8, 4, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate4, 8, 4, 3, structureBoundingBoxIn);

        for (int i = -1; i <= 2; ++i) {
            for (int j = 0; j <= 8; ++j) {
                this.setBlockState(worldIn, iblockstate1, j, 4 + i, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate2, j, 4 + i, 5 - i, structureBoundingBoxIn);
            }
        }

        this.setBlockState(worldIn, iblockstate5, 0, 2, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate5, 0, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate5, 8, 2, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate5, 8, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 3, 2, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 6, 2, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate6, 2, 1, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.OAK_PRESSURE_PLATE.getDefaultState(), 2, 2, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate4, 1, 1, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.BOOKSHELF.getDefaultState(), 1, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.BOOKSHELF.getDefaultState(), 1, 3, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 2, 1, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate3, 1, 1, 3, structureBoundingBoxIn);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 1, 7, 0, 3, Blocks.SMOOTH_STONE.getDefaultState(), Blocks.SMOOTH_STONE.getDefaultState(), false);
        this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(), 6, 1, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.FURNACE.getDefaultState(), 6, 1, 2, structureBoundingBoxIn);
        //this.setBlockState(worldIn, JapariBlocks.Japariman_Bowl.getDefaultState(), 6, 2, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
        this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);
        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH);

        if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR) {
            this.setBlockState(worldIn, iblockstate1, 2, 0, -1, structureBoundingBoxIn);

            if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH) {
                this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
            }
        }

        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 2, 5, structureBoundingBoxIn);
        this.placeTorch(worldIn, EnumFacing.SOUTH, 6, 3, 4, structureBoundingBoxIn);
        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 6, 1, 5, EnumFacing.SOUTH);

        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 9; ++l) {
                this.clearCurrentPositionBlocksUpwards(worldIn, l, 7, k, structureBoundingBoxIn);
                this.replaceAirAndLiquidDownwards(worldIn, iblockstate, l, -1, k, structureBoundingBoxIn);
            }
        }

        this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
        this.spawnFriends(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
        this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 7, 1, 1, JapariTreasure.humanhouse);
        return true;
    }

    private void spawnFriends(World worldIn, MutableBoundingBox structureBoundingBoxIn, int x, int y, int z, int count) {
        if (this.friendsSpawned < count) {
            for (int i = this.friendsSpawned; i < count; ++i) {
                int j = this.getXWithOffset(x + i, z);
                int k = this.getYWithOffset(y);
                int l = this.getZWithOffset(x + i, z);

                if (!structureBoundingBoxIn.isVecInside(new BlockPos(j, k, l))) {
                    break;
                }

                ++this.friendsSpawned;

                /*if (worldIn.rand.nextInt(4) == 0) {
                    EntityWhiteOwl entityWhiteOwl = new EntityWhiteOwl(worldIn);
                    entityWhiteOwl.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
                    entityWhiteOwl.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityWhiteOwl)), null);
                    entityWhiteOwl.enablePersistence();
                    worldIn.spawnEntity(entityWhiteOwl);
                } else if (worldIn.rand.nextInt(4) == 0) {
                    EntityBrownOwl entityBrownOwl = new EntityBrownOwl(worldIn);
                    entityBrownOwl.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
                    entityBrownOwl.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityBrownOwl)), null);
                    entityBrownOwl.enablePersistence();
                    worldIn.spawnEntity(entityBrownOwl);
                } else if (worldIn.rand.nextInt(4) == 0) {
                    EntityShoebill entityfriends = new EntityShoebill(worldIn);
                    entityfriends.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
                    entityfriends.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityfriends)), null);
                    entityfriends.enablePersistence();
                    worldIn.spawnEntity(entityfriends);
                } else {
                    EntitySquirre entityfriends = new EntitySquirre(worldIn);
                    entityfriends.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
                    entityfriends.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityfriends)), null);
                    entityfriends.enablePersistence();
                    worldIn.spawnEntity(entityfriends);
                }*/
            }
        }
    }

    @Override
    protected VillagerRegistry.VillagerProfession chooseForgeProfession(int count, VillagerRegistry.VillagerProfession prof)
    {
        return ModVillagers.japariProfession;
    }

    public static class VillageManager implements VillagerRegistry.IVillageCreationHandler
    {

        @Override
        public VillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {

            return new VillagePieces.PieceWeight(ComponentJapariHouse1.class, 17, i + random.nextInt(1));

        }
        @Override
        public Class<?> getComponentClass()
        {
            return ComponentJapariHouse1.class;
        }

        @Override
        public VillagePieces.Village buildComponent(VillagePieces.PieceWeight villagePiece, VillagePieces.Start startPiece, List<StructurePiece> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
            MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 13, 4, 9, facing);
            return canVillageGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(pieces, mutableboundingbox) == null ? new VillagePieces.Field1(startPiece, p5, random, mutableboundingbox, facing) : null;
        }
    }
}
