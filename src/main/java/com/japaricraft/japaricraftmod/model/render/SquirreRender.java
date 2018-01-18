package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.Squirre;
import com.japaricraft.japaricraftmod.model.ModelSquirre;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class SquirreRender extends RenderLiving<Squirre> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/squirre.png");

    public SquirreRender(RenderManager renderManager) {
        super(renderManager, new ModelSquirre(), 0.5F);
        this.addLayer(new LayerBipedArmor(this) {
            protected void setModelSlotVisible(ModelBiped p_188359_1_, EntityEquipmentSlot slotIn) {
                this.setModelVisible(p_188359_1_);

                switch (slotIn) {
                    case HEAD:
                        p_188359_1_.bipedHead.showModel = true;
                        p_188359_1_.bipedHeadwear.showModel = true;
                        break;
                    case CHEST:
                        p_188359_1_.bipedBody.showModel = false;
                        p_188359_1_.bipedRightArm.showModel = false;
                        p_188359_1_.bipedLeftArm.showModel = false;
                        break;
                    case LEGS:
                        p_188359_1_.bipedBody.showModel = false;
                        p_188359_1_.bipedRightLeg.showModel = false;
                        p_188359_1_.bipedLeftLeg.showModel = false;
                        break;
                    case FEET:
                        p_188359_1_.bipedRightLeg.showModel = false;
                        p_188359_1_.bipedLeftLeg.showModel = false;
                }
            }

            @Override
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                p_188359_1_();
            }

            void p_188359_1_() {
                GlStateManager.translate(0.0F, 0.01F, 0.0F);
            }
        });

    }

    /**
     * 黄昏の森のコードを参考にしている
     * ここでは装備のメゾットを使って、フレンズの高さに合わせてy軸をいじってる
     */
    @Override
    protected ResourceLocation getEntityTexture(Squirre entity) {
        return TEXTURES;
    }
}