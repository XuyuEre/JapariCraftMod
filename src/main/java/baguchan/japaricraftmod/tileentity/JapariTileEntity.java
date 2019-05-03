package baguchan.japaricraftmod.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;

public class JapariTileEntity {

    public static void registerEntity(IForgeRegistry<TileEntityType<?>> registry) {
    }

    @OnlyIn(Dist.CLIENT)
    public static void tileModel() {

    }
}
