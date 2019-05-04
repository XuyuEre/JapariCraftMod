package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelServal;
import baguchan.japaricraftmod.entity.EntityServal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class RenderServal extends RenderLiving<EntityServal> {
    private static final ResourceLocation SERVAL_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/serval/serval.png");
    private static final ResourceLocation BEG_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/serval/serval_beg.png");

    private float field_205127_a;

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

            @Override
            public void render(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                p_188359_1_();
                super.render(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);

            }

            void p_188359_1_() {
                GlStateManager.translatef(0.0F, 0.6F, 0.0F);
            }
        });
    }

    protected void applyRotations(EntityServal entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        float f = entityLiving.getSwimAnimation(partialTicks);
        if (entityLiving.isElytraFlying()) {
            super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
            float f1 = (float) entityLiving.getTicksElytraFlying() + partialTicks;
            float f2 = MathHelper.clamp(f1 * f1 / 100.0F, 0.0F, 1.0F);
            if (!entityLiving.isSpinAttacking()) {
                GlStateManager.rotatef(f2 * (-90.0F - entityLiving.rotationPitch), 1.0F, 0.0F, 0.0F);
            }

            Vec3d vec3d = entityLiving.getLook(partialTicks);
            double d0 = entityLiving.motionX * entityLiving.motionX + entityLiving.motionZ * entityLiving.motionZ;
            double d1 = vec3d.x * vec3d.x + vec3d.z * vec3d.z;
            if (d0 > 0.0D && d1 > 0.0D) {
                double d2 = (entityLiving.motionX * vec3d.x + entityLiving.motionZ * vec3d.z) / (Math.sqrt(d0) * Math.sqrt(d1));
                double d3 = entityLiving.motionX * vec3d.z - entityLiving.motionZ * vec3d.x;
                GlStateManager.rotatef((float) (Math.signum(d3) * Math.acos(d2)) * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
            }
        } else if (entityLiving.isStretching()) {

            GlStateManager.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);

        } else if (!entityLiving.isElytraFlying() && entityLiving.isJumpAttack()) {
            GlStateManager.rotatef((float) (entityLiving.motionY * 20D + 90F), -1F, 0.0F, 0.0F);

        } else if (f > 0.0F) {
            super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
            float f3 = this.lerp(entityLiving.rotationPitch, -90.0F - entityLiving.rotationPitch, f);
            if (!entityLiving.isSwimming()) {
                f3 = this.interpolateRotation(this.field_205127_a, 0.0F, 1.0F - f);
            }

            GlStateManager.rotatef(f3, 1.0F, 0.0F, 0.0F);
            if (entityLiving.isSwimming()) {
                this.field_205127_a = f3;
                GlStateManager.translatef(0.0F, -1.0F, 0.3F);
            }
        } else if (entityLiving.isPlayerSleeping()) {
            super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
            GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
        } else {
            super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        }

    }

    private float lerp(float p_205126_1_, float p_205126_2_, float p_205126_3_) {
        return p_205126_1_ + (p_205126_2_ - p_205126_1_) * p_205126_3_;
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