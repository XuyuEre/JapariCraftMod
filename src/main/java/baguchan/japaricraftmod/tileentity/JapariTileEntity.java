package baguchan.japaricraftmod.tileentity;

import baguchan.japaricraftmod.client.render.tileentity.RenderSandStarPortal;
import baguchan.japaricraftmod.init.JapariBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class JapariTileEntity {
    public static TileEntityType<TileEntitySandStarPortal> tileSandStarPortal;

    public static void registerEntity(IForgeRegistry<TileEntityType<?>> registry) {
        tileSandStarPortal = TileEntityType.register(JapariBlocks.SANDSTAR_PORTAL.getRegistryName().toString(), TileEntityType.Builder.create(TileEntitySandStarPortal::new));
    }

    @OnlyIn(Dist.CLIENT)
    public static void tileModel() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySandStarPortal.class, new RenderSandStarPortal());
        //TileEntityItemStackRenderer.instance.renderByItem(new TileEntitySandStarPortal());

    }
}
