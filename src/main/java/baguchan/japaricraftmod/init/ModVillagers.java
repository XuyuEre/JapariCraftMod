package baguchan.japaricraftmod.init;

import baguchan.japaricraftmod.JapariCraftMod;
import com.google.common.collect.Maps;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;

public class ModVillagers {
    //修正予定
    public static final ModVillagers INSTANCE = new ModVillagers();
    public static VillagerRegistry.VillagerProfession japariProfession = new VillagerRegistry.VillagerProfession(JapariCraftMod.MODID + ":zookeeper", "japaricraftmod:textures/entity/zookeeper.png", "japaricraftmod:textures/entity/zookeeper_zombie.png");
    public Map<Integer, VillagerRegistry.VillagerProfession> professions = Maps.newHashMap();

    public void init(IForgeRegistry<VillagerRegistry.VillagerProfession> registry) {

        registry.register(japariProfession);

        VillagerRegistry.VillagerCareer career_zookeeper = new VillagerRegistry.VillagerCareer(japariProfession, "zookeeper");
        career_zookeeper.addTrade(1,
                new EntityVillager.EmeraldForItems(Items.WHEAT, new EntityVillager.PriceInfo(18, 22)),
                new EntityVillager.EmeraldForItems(Items.APPLE, new EntityVillager.PriceInfo(12, 18)),
                new EntityVillager.EmeraldForItems(Items.SUGAR, new EntityVillager.PriceInfo(14, 19)),
                new EntityVillager.EmeraldForItems(Items.CARROT, new EntityVillager.PriceInfo(15, 19))
        );
        career_zookeeper.addTrade(2,
                new EntityVillager.ListItemForEmeralds(JapariItems.JAPARIMAN, new EntityVillager.PriceInfo(-12, -8)),
                new EntityVillager.ListItemForEmeralds(JapariItems.JAPARIMAN_APPLE, new EntityVillager.PriceInfo(-12, -8))
        );
        career_zookeeper.addTrade(2,
                new EntityVillager.ListItemForEmeralds(JapariItems.JAPARIMAN, new EntityVillager.PriceInfo(-12, -8)),
                new EntityVillager.ListItemForEmeralds(JapariItems.JAPARIMAN_COCOA, new EntityVillager.PriceInfo(-12, -8))
        );
        career_zookeeper.addTrade(3,
                new EntityVillager.EmeraldForItems(Items.MELON_SLICE, new EntityVillager.PriceInfo(26, 32)));
        new EntityVillager.ListItemForEmeralds(JapariItems.JAPARIMAN_SANDSTAR, new EntityVillager.PriceInfo(6, 8));

        //new EntityVillager.ListItemForEmeralds(JapariItems.starjapariman, new EntityVillager.PriceInfo(6, 8)));
    }
}
