package baguchan.japaricraftmod.model;

import baguchan.japaricraftmod.mob.EntityOtter;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelOtter extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer handR;
    public ModelRenderer handL;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer tale;
    public ModelRenderer shape15;
    public ModelRenderer shape16;
    public ModelRenderer tale2;

    public ModelOtter() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.tale = new ModelRenderer(this, 64, 0);
        this.tale.setRotationPoint(0.0F, 9.0F, 2.0F);
        this.tale.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tale, -0.6829473363053812F, 0.0F, 0.0F);
        this.handL = new ModelRenderer(this, 8, 19);
        this.handL.setRotationPoint(4.7F, 0.0F, 0.0F);
        this.handL.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.tale2 = new ModelRenderer(this, 64, 7);
        this.tale2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.tale2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(tale2, -0.27314402793711257F, 0.0F, 0.0F);
        this.shape15 = new ModelRenderer(this, 12, 0);
        this.shape15.setRotationPoint(-1.4F, -5.9F, -2.9F);
        this.shape15.addBox(-3.0F, -3.0F, 0.0F, 3, 3, 1, 0.0F);
        this.setRotateAngle(shape15, 0.0F, 0.0F, -0.8651597102135892F);
        this.shape16 = new ModelRenderer(this, 12, 0);
        this.shape16.setRotationPoint(5.6F, -5.9F, -2.9F);
        this.shape16.addBox(-3.0F, -3.0F, 0.0F, 3, 3, 1, 0.0F);
        this.setRotateAngle(shape16, 0.0F, 0.0F, -0.8651597102135892F);
        this.legL = new ModelRenderer(this, 0, 19);
        this.legL.setRotationPoint(1.6F, 12.0F, 0.0F);
        this.legL.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.handR = new ModelRenderer(this, 8, 19);
        this.handR.setRotationPoint(-4.7F, 0.0F, 0.0F);
        this.handR.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.legR = new ModelRenderer(this, 0, 19);
        this.legR.setRotationPoint(-1.6F, 12.0F, 0.0F);
        this.legR.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.body.addBox(-3.5F, 0.0F, -3.0F, 7, 12, 6, 0.0F);
        this.head = new ModelRenderer(this, 28, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.body.addChild(this.tale);
        this.body.addChild(this.handL);
        this.tale.addChild(this.tale2);
        this.head.addChild(this.shape15);
        this.head.addChild(this.shape16);
        this.body.addChild(this.legL);
        this.body.addChild(this.handR);
        this.body.addChild(this.legR);
        this.body.addChild(this.head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityOtter)) {
            return;
        }

        EntityOtter entitySandcat = (EntityOtter) entityIn;
        boolean flag = ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag) {
            this.head.rotateAngleX = -((float) Math.PI / 4F);
        } else {
            this.head.rotateAngleX = headPitch * 0.017453292F;
        }


        this.body.rotateAngleY = 0.0F;
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

        if (entitySandcat.isSitting() || this.isRiding) {
            this.legR.rotateAngleX = -1.4137167F;
            this.legR.rotateAngleY = ((float) Math.PI / 10F);
            this.legR.rotateAngleZ = 0.07853982F;
            this.legL.rotateAngleX = -1.4137167F;
            this.legL.rotateAngleY = -((float) Math.PI / 10F);
            this.legL.rotateAngleZ = -0.07853982F;
            GL11.glTranslatef(0F, 0.4F, 0F);
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

        this.tale.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.tale2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;

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