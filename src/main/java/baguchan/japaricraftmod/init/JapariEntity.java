package baguchan.japaricraftmod.init;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.entity.EntityKouteiPenguin;
import baguchan.japaricraftmod.entity.EntityOtter;
import baguchan.japaricraftmod.entity.EntityServal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Set;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class JapariEntity {

    public static final EntityType<EntityServal> SERVAL = EntityType.register(JapariCraftMod.MODID + ":serval", EntityType.Builder.create(EntityServal.class, EntityServal::new));
    public static final EntityType<EntityKouteiPenguin> KOUTEI_PENGUIN = EntityType.register(JapariCraftMod.MODID + ":koutei_penguin", EntityType.Builder.create(EntityKouteiPenguin.class, EntityKouteiPenguin::new));
    public static final EntityType<EntityOtter> OTTER = EntityType.register(JapariCraftMod.MODID + ":otter", EntityType.Builder.create(EntityOtter.class, EntityOtter::new));

    @SubscribeEvent
    public static void registerEntity(IForgeRegistry<EntityType<?>> event) {
        event.register(SERVAL);
        event.register(KOUTEI_PENGUIN);
    }

    public static void spawnEntity() {

        for (Biome biome : Biome.BIOMES) {

            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);

            if (types.contains(SNOWY) && !types.contains(FOREST) && !types.contains(NETHER) && !biome.getSpawns(EnumCreatureType.CREATURE).isEmpty()) {

                biome.getSpawns(EnumCreatureType.CREATURE).add(new Biome.SpawnListEntry(KOUTEI_PENGUIN, 6, 2, 3));

            }
            if (types.contains(SAVANNA) && !types.contains(NETHER) && !biome.getSpawns(EnumCreatureType.CREATURE).isEmpty()) {

                biome.getSpawns(EnumCreatureType.CREATURE).add(new Biome.SpawnListEntry(SERVAL, 6, 2, 3));

            }

        }

    }
}