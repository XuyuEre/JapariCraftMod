package baguchan.japaricraftmod.init;

import baguchan.japaricraftmod.*;
import baguchan.japaricraftmod.entity.*;
import net.minecraft.entity.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;

public class JapariEntity {

    public static final EntityType<EntityServal> SERVAL = EntityType.register(JapariCraftMod.MODID + ":serval", EntityType.Builder.create(EntityServal.class, EntityServal::new));


    public static void register(EntityType entity, String name, IForgeRegistry<EntityType<?>> event) {
        event.register(entity);

    }

    @SubscribeEvent
    public static void registerEntity(IForgeRegistry<EntityType<?>> event) {
        register(SERVAL, "serval", event);
    }
}