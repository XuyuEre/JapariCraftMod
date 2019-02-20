package baguchan.japaricraftmod.world.structure;

import baguchan.japaricraftmod.world.*;
import baguchan.japaricraftmod.world.structure.config.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.registries.*;

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
}