package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.*;
import baguchan.japaricraftmod.client.model.*;
import baguchan.japaricraftmod.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.entity.*;
import net.minecraft.inventory.*;
import net.minecraft.util.*;
import net.minecraftforge.api.distmarker.*;

import javax.annotation.*;

@OnlyIn(Dist.CLIENT)
public class RenderServal extends RenderLiving<EntityServal> {
    private static final ResourceLocation SERVAL_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/serval/serval.png");
    private static final ResourceLocation BEG_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/serval/serval_beg.png");

    public RenderServal(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelServal(), 0.5F);
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
            public void render(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                p_188359_1_();
                super.render(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);

            }

            /**
             * 黄昏の森のコードを参考にしている
             * ここでは装備のメゾットを使って、フレンズの高さに合わせてy軸をいじってる
             */
            void p_188359_1_() {
                GlStateManager.translatef(0.0F, 0.01F, 0.0F);
            }
        });
        this.addLayer(new LayerHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelServal) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityServal entity) {
        if (entity.isBegging()) {
            return BEG_TEXTURES;
        } else {
            return SERVAL_TEXTURES;
        }
    }

    public ModelServal getMainModel() {
        return (ModelServal) super.getMainModel();
    }

}