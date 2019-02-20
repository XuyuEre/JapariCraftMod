package baguchan.japaricraftmod.init;

import baguchan.japaricraftmod.*;
import baguchan.japaricraftmod.block.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraftforge.registries.*;


public class JapariBlocks {

    public static final Block SANDSTAR_BLOCK = new BlockSandStar();
    public static final Block CRACKED_SANDSTAR_BLOCK = new BlockCrackedSandStar();
    public static final Block SANDSTAR_PORTAL = new BlockSandStarPortal();
    //public static final Block SANDSTAR_ORE = new BlockSandStarOre();
    public static final Block SANDSTAR_Lamp = new BlockSandStarLamp();
    public static final Block BLOCK_WOODEN_BOX = new BlockWoodenBox();
    //public static final Block Japariman_Bowl = new BlockJaparimanBowl();
    //public static final Block TREE_APPLE = new BlockSandStarTreeApple();
    //public static final Block REDSTAR_FLOWER = new BlockRedStarFlower();

    public static void register(IForgeRegistry<Block> registry, Block block, String id) {
        block.setRegistryName(new ResourceLocation(JapariCraftMod.MODID, id));
        registry.register(block);
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        //register(registry,SANDSTAR_ORE,"sandstar_ore");
        register(registry, SANDSTAR_BLOCK, "sandstar_block");
        register(registry, CRACKED_SANDSTAR_BLOCK, "cracked_sandstar");
        register(registry, SANDSTAR_PORTAL, "sandstar_portal");
        register(registry, SANDSTAR_Lamp, "sandstar_lamp");
        register(registry, BLOCK_WOODEN_BOX, "woodenbox");
        //register(registry,Japariman_Bowl,"japarimanbowl");
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        //JapariItems.register(registry, new ItemBlock(SANDSTAR_ORE,(new Item.Properties()).group(ItemGroup.DECORATIONS)));
        JapariItems.register(registry, new ItemBlock(SANDSTAR_BLOCK, (new Item.Properties()).group(ItemGroup.DECORATIONS)));
        JapariItems.register(registry, new ItemBlock(CRACKED_SANDSTAR_BLOCK, (new Item.Properties()).group(ItemGroup.DECORATIONS)));
        JapariItems.register(registry, new ItemBlock(SANDSTAR_PORTAL, (new Item.Properties()).group(ItemGroup.DECORATIONS)));
        JapariItems.register(registry, new ItemBlock(SANDSTAR_Lamp, (new Item.Properties()).group(ItemGroup.DECORATIONS)));
        //JapariItems.register(registry, new ItemBlock(Japariman_Bowl,(new Item.Properties()).group(ItemGroup.DECORATIONS)));
        JapariItems.register(registry, new ItemBlock(BLOCK_WOODEN_BOX, (new Item.Properties()).group(ItemGroup.DECORATIONS)));
    }
}
