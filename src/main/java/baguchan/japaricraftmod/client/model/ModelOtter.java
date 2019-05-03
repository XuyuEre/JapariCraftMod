package baguchan.japaricraftmod.client.model;

import baguchan.japaricraftmod.entity.EntityOtter;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelOtter extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer handR;
    public ModelRenderer handL;
    public ModelRenderer tail;
    public ModelRenderer hairR;
    public ModelRenderer hairL;
    public ModelRenderer hairR2;
    public ModelRenderer hairL2;
    public ModelRenderer hair_top;
    public ModelRenderer hair_back;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer hair_top2;
    public ModelRenderer hair_top3;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;

    public ModelOtter() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.hairR2 = new ModelRenderer(this, 40, 16);
        this.hairR2.setRotationPoint(-3.0F, -8.0F, -1.0F);
        this.hairR2.addBox(-1.0F, 0.0F, -4.0F, 2, 7, 1, 0.0F);
        this.setRotateAngle(hairR2, 0.0F, 0.0F, 0.136659280431156F);
        this.hair_top = new ModelRenderer(this, 54, 0);
        this.hair_top.setRotationPoint(0.0F, -7.3F, 0.0F);
        this.hair_top.addBox(-4.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F);
        this.hairL2 = new ModelRenderer(this, 40, 16);
        this.hairL2.setRotationPoint(3.0F, -8.0F, -1.0F);
        this.hairL2.addBox(-1.0F, 0.0F, -4.0F, 2, 7, 1, 0.0F);
        this.setRotateAngle(hairL2, 0.0F, 0.0F, -0.136659280431156F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.body.addBox(-3.5F, 0.0F, -2.0F, 7, 11, 4, 0.0F);
        this.hairL = new ModelRenderer(this, 22, 16);
        this.hairL.setRotationPoint(3.4F, -8.0F, 0.0F);
        this.hairL.addBox(0.0F, 0.0F, -4.0F, 1, 7, 8, 0.0F);
        this.setRotateAngle(hairL, 0.0F, 0.0F, -0.136659280431156F);
        this.hairR = new ModelRenderer(this, 22, 16);
        this.hairR.setRotationPoint(-3.4F, -8.0F, 0.0F);
        this.hairR.addBox(-1.0F, 0.0F, -4.0F, 1, 7, 8, 0.0F);
        this.setRotateAngle(hairR, 0.0F, 0.0F, 0.136659280431156F);
        this.earL = new ModelRenderer(this, 46, 0);
        this.earL.setRotationPoint(4.9F, -3.5F, 0.0F);
        this.earL.addBox(-3.0F, -3.0F, -1.0F, 3, 3, 2, 0.0F);
        this.setRotateAngle(earL, 0.0F, 0.0F, 0.5918411493512771F);
        this.legL = new ModelRenderer(this, 0, 15);
        this.legL.setRotationPoint(1.5F, 11.0F, 0.0F);
        this.legL.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
        this.tail4 = new ModelRenderer(this, 0, 32);
        this.tail4.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail4, 0.31869712141416456F, 0.0F, 0.0F);
        this.legR = new ModelRenderer(this, 0, 15);
        this.legR.setRotationPoint(-1.5F, 11.0F, 0.0F);
        this.legR.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
        this.handL = new ModelRenderer(this, 8, 15);
        this.handL.setRotationPoint(4.0F, 1.0F, 0.0F);
        this.handL.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.tail3 = new ModelRenderer(this, 0, 26);
        this.tail3.setRotationPoint(0.0F, 0.0F, 2.5F);
        this.tail3.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(tail3, 0.136659280431156F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 22, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.earR = new ModelRenderer(this, 46, 0);
        this.earR.setRotationPoint(-2.7F, -5.0F, 0.0F);
        this.earR.addBox(-3.0F, -3.0F, -1.0F, 3, 3, 2, 0.0F);
        this.setRotateAngle(earR, 0.0F, 0.0F, -0.5918411493512771F);
        this.hair_top3 = new ModelRenderer(this, 61, 7);
        this.hair_top3.setRotationPoint(0.0F, 0.9F, 0.0F);
        this.hair_top3.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 1, 0.0F);
        this.hair_top2 = new ModelRenderer(this, 61, 7);
        this.hair_top2.setRotationPoint(0.0F, 0.7F, -0.6F);
        this.hair_top2.addBox(-4.0F, -1.0F, -4.0F, 8, 2, 1, 0.0F);
        this.hair_back = new ModelRenderer(this, 54, 10);
        this.hair_back.setRotationPoint(0.0F, -8.0F, 4.0F);
        this.hair_back.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 1, 0.0F);
        this.tail2 = new ModelRenderer(this, 0, 26);
        this.tail2.setRotationPoint(0.0F, 0.0F, 2.5F);
        this.tail2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(tail2, -0.36425021489121656F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 26);
        this.tail.setRotationPoint(0.0F, 8.0F, 1.0F);
        this.tail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(tail, -0.36425021489121656F, 0.0F, 0.0F);
        this.handR = new ModelRenderer(this, 8, 15);
        this.handR.setRotationPoint(-4.0F, 1.0F, 0.0F);
        this.handR.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.head.addChild(this.hairR2);
        this.head.addChild(this.hair_top);
        this.head.addChild(this.hairL2);
        this.head.addChild(this.hairL);
        this.head.addChild(this.hairR);
        this.head.addChild(this.earL);
        this.body.addChild(this.legL);
        this.tail3.addChild(this.tail4);
        this.body.addChild(this.legR);
        this.body.addChild(this.handL);
        this.tail2.addChild(this.tail3);
        this.body.addChild(this.head);
        this.head.addChild(this.earR);
        this.hair_top2.addChild(this.hair_top3);
        this.hair_top.addChild(this.hair_top2);
        this.head.addChild(this.hair_back);
        this.tail.addChild(this.tail2);
        this.body.addChild(this.tail);
        this.body.addChild(this.handR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityOtter)) {
            return;
        }

        EntityOtter entityOtter = (EntityOtter) entityIn;
        boolean flag = ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag) {
            this.head.rotateAngleX = -((float) Math.PI / 4F);
        } else {
            this.head.rotateAngleX = headPitch * 0.017453292F;
        }


        this.body.rotateAngleY = 0.0F;
        this.body.rotateAngleX = 0.0F;
        float f = 1.0F;

        if (flag) {
            f = (float) (entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }


        this.handR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handR.rotateAngleZ = 0.0F;
        this.handL.rotateAngleZ = 0.0F;
        this.handR.rotateAngleY = 0.0F;
        this.handL.rotateAngleY = 0.0F;

        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.legR.rotateAngleY = 0.0F;
        this.legL.rotateAngleY = 0.0F;
        this.legR.rotateAngleZ = 0.0F;
        this.legL.rotateAngleZ = 0.0F;

        if (!entityOtter.isSitting() && entityOtter.getArmPose() == EntityOtter.ArmPose.ATTACKING) {
            this.head.rotateAngleX = -0.2F + (headPitch * 0.017453292F);
            this.body.rotateAngleX = 0.2F;
            this.legR.rotateAngleX = -0.2F + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
            this.legL.rotateAngleX = -0.2F + MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
            this.handR.rotateAngleX = 1.0F;
            this.handL.rotateAngleX = 1.0F;
        } else if (entityOtter.isSitting() || this.isRiding) {
            this.legR.rotateAngleX = -1.4137167F;
            this.legR.rotateAngleY = ((float) Math.PI / 10F);
            this.legR.rotateAngleZ = 0.07853982F;
            this.legL.rotateAngleX = -1.4137167F;
            this.legL.rotateAngleY = -((float) Math.PI / 10F);
            this.legL.rotateAngleZ = -0.07853982F;
            GL11.glTranslatef(0F, 0.4F, 0F);
        }

        if (entityOtter.getEatingTick() > 1) {
            this.handR.rotateAngleZ = -0.6F + MathHelper.cos(ageInTicks * 0.5F) * 0.6F;
            this.handR.rotateAngleX = -0.9F;
        }

        if (this.swingProgress > 0.0F) {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT) {
                this.body.rotateAngleY *= -1.0F;
            }
            this.handR.rotateAngleY += this.body.rotateAngleY;
            this.handL.rotateAngleY += this.body.rotateAngleY;
            float f2 = MathHelper.sin(f1 * (float) Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
            modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }

        this.handR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.handL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        this.tail.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.1F * limbSwingAmount - 0.3F;
        this.tail2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount - 0.3F;
        this.tail3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount + 0.1F;
        this.tail4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount + 0.3F;

    }

    public void postRenderArm(float scale, EnumHandSide side) {
        this.getArmForSide(side).postRender(scale);
    }

    public ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.handL : this.handR;
    }

    protected EnumHandSide getMainHand(Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase) entityIn;
            EnumHandSide enumhandside = entitylivingbase.getPrimaryHand();
            return entitylivingbase.swingingHand == EnumHand.MAIN_HAND ? enumhandside : enumhandside.opposite();
        } else {
            return EnumHandSide.RIGHT;
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}