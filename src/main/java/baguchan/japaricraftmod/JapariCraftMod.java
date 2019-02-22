package baguchan.japaricraftmod;

import baguchan.japaricraftmod.client.JapariRender;
import baguchan.japaricraftmod.command.CommandJapariLocate;
import baguchan.japaricraftmod.init.JapariBlocks;
import baguchan.japaricraftmod.init.JapariEntity;
import baguchan.japaricraftmod.init.JapariItems;
import baguchan.japaricraftmod.init.ModVillagers;
import baguchan.japaricraftmod.tileentity.JapariTileEntity;
import baguchan.japaricraftmod.world.structure.JapariStructures;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.block.Block;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static baguchan.japaricraftmod.JapariCraftMod.MODID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MODID)
public class JapariCraftMod {
    public static final String MODID = "japaricraftmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static JapariCraftMod instance;


    public JapariCraftMod() {
        instance = this;

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);

        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, this::onBlocksRegistry);

        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, this::onItemsRegistry);

        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(EntityType.class, this::onEntityRegistry);

        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(TileEntityType.class, this::onTileEntityRegistry);

        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(VillagerRegistry.VillagerProfession.class, this::onVillagerRegistry);

        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        // Register ourselves for server, registry and other game events we are interested in

    }

    private void setup(final FMLCommonSetupEvent event) {
        JapariStructures.register();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        JapariTileEntity.tileModel();
        JapariRender.entityRender();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    public void onLoadComplete(FMLLoadCompleteEvent event) {
        JapariEntity.spawnEntity();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();

        CommandJapariLocate.register(dispatcher);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD event bus
    @SubscribeEvent
    public void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        JapariBlocks.registerBlocks(registry);
    }

    @SubscribeEvent
    public void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        JapariBlocks.registerItemBlocks(registry);
        JapariItems.registerItems(registry);
    }

    @SubscribeEvent
    public void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        JapariEntity.registerEntity(registry);
    }

    @SubscribeEvent
    public void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
        JapariTileEntity.registerEntity(registry);
    }

    @SubscribeEvent
    public void onVillagerRegistry(final RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {
        IForgeRegistry<VillagerRegistry.VillagerProfession> registry = event.getRegistry();
        ModVillagers.INSTANCE.init(registry);
    }

}
