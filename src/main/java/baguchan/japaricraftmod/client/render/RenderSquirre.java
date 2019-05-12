package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelSquirre;
import baguchan.japaricraftmod.entity.EntitySquirre;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderSquirre extends RenderLiving<EntitySquirre> {
    private static final ResourceLocation SLEEPING_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/squirre_sleep.png");
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/squirre.png");

    public RenderSquirre(RenderManager renderManager) {
        super(renderManager, new ModelSquirre(), 0.5F);
        this.addLayer(new LayerHeldItem(this) {
            @Override
            public void render(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

                GlStateManager.translatef(0.0F, 0.6F, 0.0F);
                super.render(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelSquirre) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0425F);
            }
        });

    }

    //寝るときと寝ない時のテクスチャ
    @Override
    protected ResourceLocation getEntityTexture(EntitySquirre entity) {
        return TEXTURES;
    }
}