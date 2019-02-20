package baguchan.japaricraftmod.tileentity;

import baguchan.japaricraftmod.client.render.tileentity.*;
import baguchan.japaricraftmod.init.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.registries.*;

public class JapariTileEntity {
    public static TileEntityType<TileEntitySandStarPortal> tileSandStarPortal;

    public static void registerEntity(IForgeRegistry<TileEntityType<?>> registry) {
        tileSandStarPortal = TileEntityType.register(JapariBlocks.SANDSTAR_PORTAL.getRegistryName().toString(), TileEntityType.Builder.create(TileEntitySandStarPortal::new));
    }

    @OnlyIn(Dist.CLIENT)
    public static void tileModel() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySandStarPortal.class, new RenderSandStarPortal());
        TileEntityRendererDispatcher.instance.renderAsItem(new TileEntitySandStarPortal());

    }
}
