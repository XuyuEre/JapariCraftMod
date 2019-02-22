package baguchan.japaricraftmod.init;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.entity.EntityKouteiPenguin;
import baguchan.japaricraftmod.entity.EntityServal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Set;

public class JapariEntity {

    public static final EntityType<EntityServal> SERVAL = EntityType.register(JapariCraftMod.MODID + ":serval", EntityType.Builder.create(EntityServal.class, EntityServal::new));
    public static final EntityType<EntityKouteiPenguin> KOUTEI_PENGUIN = EntityType.register(JapariCraftMod.MODID + ":koutei_penguin", EntityType.Builder.create(EntityKouteiPenguin.class, EntityKouteiPenguin::new));

    @SubscribeEvent
    public static void registerEntity(IForgeRegistry<EntityType<?>> event) {
        event.register(SERVAL);
    }

    public static void spawnEntity() {

        Set<Biome> savannabiomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA);
        for (Biome biome : savannabiomes) {
            biome.addSpawn(EnumCreatureType.CREATURE, new Biome.SpawnListEntry(SERVAL, 6, 2, 3));

        }


        Set<Biome> coldbiomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.COLD);
        for (Biome biome : coldbiomes) {
            biome.addSpawn(EnumCreatureType.CREATURE, new Biome.SpawnListEntry(KOUTEI_PENGUIN, 6, 2, 3));
        }

    }
}