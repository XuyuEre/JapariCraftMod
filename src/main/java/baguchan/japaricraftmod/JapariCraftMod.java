package baguchan.japaricraftmod;

import baguchan.japaricraftmod.event.EntityEventHandler;
import baguchan.japaricraftmod.event.StructureEventHandler;
import baguchan.japaricraftmod.gui.JapariGuiHandler;
import baguchan.japaricraftmod.handler.*;
import baguchan.japaricraftmod.world.ComponentJapariHouse1;
import baguchan.japaricraftmod.world.SandStarOreGenerator;
import baguchan.japaricraftmod.world.biome.JapariBiomes;
import baguchan.japaricraftmod.world.structure.*;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;


@Mod(modid = JapariCraftMod.MODID, name = JapariCraftMod.MODNAME, version = JapariCraftMod.VERSION, useMetadata = true, updateJSON = "https://github.com/pentantan/JapariCraftMod/blob/master/src/main/japaricraftmod.json", dependencies = "required:forge@[14.23.4.2705,);")
public class JapariCraftMod {

    public static final String MODID = "japaricraftmod";
    public static final String VERSION = "4.4.7";
    public static final String MODNAME = "JapariCraftMod";


    //Modの情報を格納する。 mcmod.infoの上位互換
    @Mod.Metadata
    public static ModMetadata metadata;

    @SidedProxy(clientSide = "baguchan.japaricraftmod.ClientProxy", serverSide = "baguchan.japaricraftmod.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static JapariCraftMod instance;
    public static final int ID_JAPARI_INVENTORY = 1;
    public static final CreativeTabs tabJapariCraft = new TabJapariCraft("JapariCraftTab");


    @EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        JapariBlocks.registerBlocks(registry);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        JapariBlocks.registerItemBlocks(registry);
        JapariItems.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        JapariBlocks.registerModels();
        JapariItems.registerModels();
    }

    @SubscribeEvent
    public void registerEntityEntries(RegistryEvent.Register<EntityEntry> event) {
        JapariEntityRegistry.registerEntities();
    }

    @SubscribeEvent
    public void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        JapariBiomes.registerBiomes(registry);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {


        GameRegistry.registerWorldGenerator(new SandStarOreGenerator(), 0);
        if (event.getSide().isClient()) {
            JapariRenderingRegistry.registerRenderers();
        }
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        MinecraftForge.EVENT_BUS.register(new LootTableEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new JapariGuiHandler());
        //メタ情報の登録
        loadMeta();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        JapariBiomes.registerBiomeTypes();
        JapariEntityRegistry.addSpawns();

        MinecraftForge.EVENT_BUS.register(new StructureEventHandler());
        MapGenStructureIO.registerStructure(StructureSandStarRuinStart.class, "SandStarRuin");
        MapGenStructureIO.registerStructureComponent(ComponentSandStarRuin1.class, "SSR");
        MapGenStructureIO.registerStructureComponent(ComponentSandStarRuinHole.class, "SSRH");
        MapGenStructureIO.registerStructureComponent(ComponentSandStarRuinRoof.class, "SSRR");
        MapGenStructureIO.registerStructureComponent(ComponentSandStarRuinUnderRoom.class, "SSRUR");
        MapGenStructureIO.registerStructureComponent(ComponentSandStarRuinCorridor.class, "SSRC");
        MapGenStructureIO.registerStructureComponent(ComponentSandStarRuinMiniHole.class, "SSRMH");
        MapGenStructureIO.registerStructureComponent(ComponentSandStarRuinTreasureRoom.class, "SSRTR");
        MapGenStructureIO.registerStructureComponent(ComponentSandStarRuinBossRoom.class, "SSRBR");
        // チャンク生成時に追加構造物の生成が行われるようにフック
        VillagerRegistry villageRegistry = VillagerRegistry.instance();
        VillagerRegistry.instance().registerVillageCreationHandler(new ComponentJapariHouse1.VillageManager());
        MapGenStructureIO.registerStructureComponent(ComponentJapariHouse1.class, "JH1");

        //Villagerのレンダー
        ModVillagers.INSTANCE.init();
    }

    private void loadMeta() {
        metadata.modId = MODID;
        metadata.name = MODNAME;
        metadata.version = VERSION;
        metadata.description = ("けもフレ関連のアイテムを追加します");
        metadata.credits = ("");
        metadata.logoFile = ("assets/japaricraftmod/textures/logo.png");
        metadata.url = ("https://minecraft.curseforge.com/projects/japaricraftmod");

        metadata.autogenerated = false;
    }
}

