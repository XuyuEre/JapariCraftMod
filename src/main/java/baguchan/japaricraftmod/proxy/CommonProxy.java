package baguchan.japaricraftmod.proxy;

import baguchan.japaricraftmod.gui.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CommonProxy {
    private void doClientStuff(final FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::openGui);
    }

    public EntityPlayer getPlayerEntity() {

        return null;

    }
}
