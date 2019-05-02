package baguchan.japaricraftmod.gui;


import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.entity.EntityFriend;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

@OnlyIn(Dist.CLIENT)
public class GuiHandler {

    public static GuiScreen openGui(FMLPlayMessages.OpenContainer openContainer) {
        String guiId = openContainer.getId().toString();

        if (guiId.equals("japaricraftmod:friends_inventory")) {
            int entityId = openContainer.getAdditionalData().readInt();
            EntityPlayer clientPlayer = JapariCraftMod.PROXY.getPlayerEntity();

            Entity target = clientPlayer.world.getEntityByID(entityId);
            if (!(target instanceof EntityFriend))
                return null;
            EntityFriend friend = (EntityFriend) target;
            return new FriendInventoryGui(clientPlayer, friend);
        }


        return null;
    }
}