package baguchan.japaricraftmod.world.structure;

import baguchan.japaricraftmod.world.ComponentJapariHouse1;
import baguchan.japaricraftmod.world.structure.config.SandStarLabConfig;
import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public class JapariStructures {
    // Register Structure
    public static void register() {
        StructureIO.getStructureStartName(new SandStarLabStructure.Start());

        VillagerRegistry villageRegistry = VillagerRegistry.instance();
        VillagerRegistry.instance().registerVillageCreationHandler(new ComponentJapariHouse1.VillageManager());
        StructureIO.registerStructureComponent(ComponentJapariHouse1.class, "JH1");
        StructureIO.registerStructureComponent(SandStarLabPiece.Piece.class, "sandstarlab_piece");


        ForgeRegistries.BIOMES.forEach(biome -> {

            biome.addStructure(JapariFeature.SANDSTARLAB_STRUCTURE, new SandStarLabConfig(0.0F));

        });
    }

    public static void generate() {
     /*   for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(BiomeDictionary.Type.PLAINS)) {

                if (biome.getStructureConfig(JapariFeature.SANDSTARLAB_STRUCTURE) != null) {

                    biome.addStructure(JapariFeature.SANDSTARLAB_STRUCTURE, new SandStarLabConfig(0.0F));

                }

            }
        }*/
    }
}