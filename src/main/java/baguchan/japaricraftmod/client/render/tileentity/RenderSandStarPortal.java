package baguchan.japaricraftmod.client.render.tileentity;

import baguchan.japaricraftmod.client.model.tileentity.*;
import baguchan.japaricraftmod.init.*;
import baguchan.japaricraftmod.tileentity.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class RenderSandStarPortal extends TileEntityRenderer<TileEntitySandStarPortal> {

    protected static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation("japaricraftmod:textures/models/sandstar_portal.png");
    protected final ModelSandStarPortal portalModel = new ModelSandStarPortal();

    public void render(TileEntitySandStarPortal te, double x, double y, double z, float partialTicks,
                       int destroyStage, float alpha) {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        if (te.hasWorld() && te.getWorld().getBlockState(te.getPos()).getBlock() == JapariBlocks.SANDSTAR_PORTAL) {

            ModelBase modelSandStarPortal = portalModel;


            this.bindTexture(TEXTURE_NORMAL);


            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            GlStateManager.color4f(1F, 1F, 1F, alpha);

            GlStateManager.translatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
            GlStateManager.scalef(1.0F, -1.0F, -1.0F);

            int rotation = 0;


            GlStateManager.translatef(0.5F, 0.5F, 0.5F);
            GlStateManager.rotatef((float) rotation, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
            float f = (float) te.tickCount + partialTicks;

            ((ModelSandStarPortal) modelSandStarPortal).setRotateAngle(((ModelSandStarPortal) modelSandStarPortal).portal, 0, f * 0.05F, 0);

            GlStateManager.translatef(0.5F, -0.5F, 0.5F);

            ((ModelSandStarPortal) modelSandStarPortal).renderAll();

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        }
    }

    public static class SandStarTEISR extends TileEntityItemStackRenderer {

        private static ModelSandStarPortal model = new ModelSandStarPortal();

        public void renderByItem(ItemStack stack, float partialTicks) {
            GlStateManager.enableDepthTest();
            GlStateManager.depthFunc(515);
            GlStateManager.depthMask(true);

            ItemStack material = ItemStack.EMPTY;

            boolean useColoredTexture = material.isEmpty();

            Minecraft.getInstance().textureManager.bindTexture(TEXTURE_NORMAL);

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            GlStateManager.color4f(1F, 1F, 1F, 1F);

            GlStateManager.translatef(0F, 2F, 1F);
            GlStateManager.scalef(1.0F, -1.0F, -1.0F);
            GlStateManager.translatef(0.5F, 0.5F, 0.5F);
            model.renderAll();

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

    }

}