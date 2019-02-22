package baguchan.japaricraftmod.init;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.item.ItemJapariman;
import baguchan.japaricraftmod.item.ItemSandStarJapariman;
import baguchan.japaricraftmod.item.ItemWildLeberationPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;
import java.util.List;

public class JapariItems {
    private static final NonNullList<Item> ITEMS = NonNullList.create();

    public static final Item JAPARIMAN = new ItemJapariman(4, 0.62F, false, (new Item.Properties()).group(ItemGroup.FOOD));
    public static final Item JAPARIMAN_APPLE = new ItemJapariman(6, 0.74F, false, (new Item.Properties()).group(ItemGroup.FOOD));
    public static final Item JAPARIMAN_COCOA = new ItemJapariman(5, 0.64F, false, (new Item.Properties()).group(ItemGroup.FOOD));
    public static final Item JAPARIMAN_SANDSTAR = new ItemSandStarJapariman((new Item.Properties()).group(ItemGroup.FOOD));
    public static final Item SANDSTAR = new Item((new Item.Properties()).group(ItemGroup.MISC));
    public static final Item WILDLIBERATION_POTION = new ItemWildLeberationPotion((new Item.Properties()).group(ItemGroup.MISC));
    public static final Item SPAWNEGG_SERVAL = new ItemSpawnEgg(JapariEntity.SERVAL, 16703405, 6375001, new Item.Properties().group(ItemGroup.MISC));
    public static final Item SPAWNEGG_KOUTEIPENGUIN = new ItemSpawnEgg(JapariEntity.KOUTEI_PENGUIN, 2243405, 7375001, new Item.Properties().group(ItemGroup.MISC));


    public static List<Item> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }


    public static void register(IForgeRegistry<Item> registry, Item item, String id) {

        if (item instanceof ItemBlock && item.getRegistryName() == null) {
            item.setRegistryName(((ItemBlock) item).getBlock().getRegistryName());
            Item.BLOCK_TO_ITEM.put(((ItemBlock) item).getBlock(), item);
        }

        item.setRegistryName(new ResourceLocation(JapariCraftMod.MODID, id));
        registry.register(item);
    }

    public static void register(IForgeRegistry<Item> registry, Item item) {

        if (item instanceof ItemBlock && item.getRegistryName() == null) {
            item.setRegistryName(((ItemBlock) item).getBlock().getRegistryName());
            Item.BLOCK_TO_ITEM.put(((ItemBlock) item).getBlock(), item);
        }

        registry.register(item);
    }


    @SubscribeEvent
    public static void registerItems(IForgeRegistry<Item> registry) {


        register(registry, JAPARIMAN, "japariman");
        register(registry, JAPARIMAN_APPLE, "japariman_apple");
        register(registry, JAPARIMAN_COCOA, "japariman_cocoa");
        register(registry, JAPARIMAN_SANDSTAR, "japariman_sandstar");
        register(registry, SANDSTAR, "sandstar_fragment");
        register(registry, WILDLIBERATION_POTION, "wildliberation_potion");
        register(registry, SPAWNEGG_SERVAL, "spawnegg_serval");
        register(registry, SPAWNEGG_KOUTEIPENGUIN, "spawnegg_kouteipenguin");
    }

}
