package baguchan.japaricraftmod.world.structure;

import baguchan.japaricraftmod.world.structure.config.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.*;

import java.util.*;

public class SandStarLabPiece {
    private static final ResourceLocation labTemplate = new ResourceLocation("japaricraftmod:sandstarlab");

    public SandStarLabPiece(BlockPos blockpos) {
    }

    public static void load(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> p_204760_3_, Random p_204760_4_, SandStarLabConfig config) {
        ResourceLocation resourcelocation = labTemplate;
        p_204760_3_.add(new SandStarLabPiece.Piece(templateManager, resourcelocation, pos, rotation, config.chance));
    }

    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation labTemplate;
        private Rotation field_207616_e;

        public Piece() {
        }

        public Piece(TemplateManager templateManager, ResourceLocation resourceLocation, BlockPos pos, Rotation rotation, float p_i49313_5_) {
            super(0);
            this.labTemplate = resourceLocation;
            this.templatePosition = pos;
            this.field_207616_e = rotation;
            this.func_207614_a(templateManager);
        }

        private void func_207614_a(TemplateManager p_207614_1_) {
            Template template = p_207614_1_.getTemplateDefaulted(this.labTemplate);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.field_207616_e).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setString("Template", this.labTemplate.toString());
            tagCompound.setString("Rot", this.field_207616_e.name());
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.labTemplate = new ResourceLocation(tagCompound.getString("Template"));
            this.field_207616_e = Rotation.valueOf(tagCompound.getString("Rot"));
            this.func_207614_a(p_143011_2_);
        }

        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
            /*if ("chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                TileEntity tileentity = worldIn.getTileEntity(pos.down());
                if (tileentity instanceof TileEntityChest) {
                    ((TileEntityChest)tileentity).setLootTable(LootTableList.CHESTS_IGLOO_CHEST, rand.nextLong());
                }

            }*/
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
         * the end, it adds Fences...
         */
        public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_) {
            int i = 256;
            int j = 0;
            BlockPos blockpos = this.templatePosition.add(this.template.getSize().getX() - 1, 0, this.template.getSize().getZ() - 1);

            for (BlockPos blockpos1 : BlockPos.getAllInBox(this.templatePosition, blockpos)) {
                int k = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
                j += k;
                i = Math.min(i, k);
            }

            j = j / (this.template.getSize().getX() * this.template.getSize().getZ());
            int l = i - this.template.getSize().getY();
            this.templatePosition = new BlockPos(this.templatePosition.getX(), l, this.templatePosition.getZ());
            return super.addComponentParts(worldIn, randomIn, structureBoundingBoxIn, p_74875_4_);
        }
    }
}