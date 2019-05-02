package baguchan.japaricraftmod.proxy;

import baguchan.japaricraftmod.client.JapariRender;
import baguchan.japaricraftmod.gui.GuiHandler;
import baguchan.japaricraftmod.tileentity.JapariTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        super();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        JapariTileEntity.tileModel();
        JapariRender.entityRender();
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::openGui);
    }

    @Override
    public EntityPlayer getPlayerEntity() {

        return Minecraft.getInstance().player;

    }
}
