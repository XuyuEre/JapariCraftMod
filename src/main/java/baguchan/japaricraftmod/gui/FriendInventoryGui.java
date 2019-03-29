package baguchan.japaricraftmod.gui;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.entity.EntityFriend;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class FriendInventoryGui extends GuiContainer {

    private static final ResourceLocation RES_CHAST_INVENTORY = new ResourceLocation(JapariCraftMod.MODID + ":" + "textures/gui/friend_inventory.png");


    /**
     * The player inventory bound to this GUI.
     */
    private final EntityPlayer player;
    private EntityFriend entityFriends;

    public FriendInventoryGui(EntityPlayer player, EntityFriend friend) {
        super(new ContainerFriendInventory(friend, player));

        this.ySize = 222;
        this.player = player;
        this.entityFriends = friend;
        this.allowUserInput = false;
    }

    @Override
    public void initGui() {
        super.initGui();

        int buttonPosX = ((this.width - this.xSize) / 2) + 102;
        int buttonPosY = ((this.height - this.ySize) / 2) + 41;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float renderPartialTicks, int xMouse, int yMouse) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(RES_CHAST_INVENTORY);

        int originPosX = (this.width - this.xSize) / 2;
        int originPosY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(originPosX, originPosY, 0, 0, this.xSize, this.ySize);

        this.drawTexturedModalRect((originPosX + 104), (originPosY + 24), 184, this.getHealthTextureY(), this.getHealthBar(), 10);

        int entityPosX = (originPosX + 51);
        int entityPosY = (originPosY + 60);
        GuiInventory.drawEntityOnScreen(entityPosX, entityPosY, 25, (float) (entityPosX - xMouse), (float) ((entityPosY / 2) - yMouse), this.entityFriends);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int xMouse, int yMouse) {
        ITextComponent nameFriend = this.entityFriends.getName();
        this.fontRenderer.drawString(nameFriend.getString(), this.xSize / 2 - this.fontRenderer.getStringWidth(nameFriend.getString()) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.player.inventory.getDisplayName().getUnformattedComponentText(), 8, 128, 4210752);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);

        if (this.isPointInRegion(102, 22, 64, 14, mouseX, mouseY)) {
            this.drawHoveringText(Lists.newArrayList(this.entityFriends.getHealth() + " / " + this.entityFriends.getMaxHealth() + " : " + entityFriends.friendPoint + "exp" + "/160exp"), mouseX, mouseY);
        }
        this.renderHoveredToolTip(mouseX, mouseY);
    }


    private int getHealthTextureY() {
        int healthTextureY;

        switch (this.entityFriends.getCondition()) {
            case HURT:

                healthTextureY = 24;

                break;

            case DYING:

                healthTextureY = 40;

                break;

            default:

                healthTextureY = 8;
        }

        return healthTextureY;
    }

    private int getHealthBar() {
        int health = (int) this.entityFriends.getHealth();
        int healthMax = (int) this.entityFriends.getMaxHealth();

        return Math.min(60, (60 - ((healthMax - health) * 3)));
    }

}