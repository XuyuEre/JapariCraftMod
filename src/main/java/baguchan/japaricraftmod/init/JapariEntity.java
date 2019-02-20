package baguchan.japaricraftmod.init;

import baguchan.japaricraftmod.*;
import baguchan.japaricraftmod.entity.*;
import net.minecraft.entity.*;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;

import java.util.*;

public class JapariEntity {

    public static final EntityType<EntityServal> SERVAL = EntityType.register(JapariCraftMod.MODID + ":serval", EntityType.Builder.create(EntityServal.class, EntityServal::new));

    @SubscribeEvent
    public static void registerEntity(IForgeRegistry<EntityType<?>> event) {
        event.register(SERVAL);
    }

    public static void spawnEntity() {

        Set<Biome> biomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA);
        if (biomes.size() > 0) {
            for (Biome biome : ForgeRegistries.BIOMES) {
                biome.addSpawn(EnumCreatureType.CREATURE, new Biome.SpawnListEntry(SERVAL, 6, 2, 3));

            }
        }
    }
}