package baguchan.japaricraftmod.client.model;

import baguchan.japaricraftmod.mob.EntityBrownOwl;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelBrownOwl extends ModelBase {
    private ModelRenderer handA_L;
    private ModelRenderer handA_R;
    private ModelRenderer body;
    private ModelRenderer handL;
    private ModelRenderer handR;
    private ModelRenderer head;
    private ModelRenderer legR;
    private ModelRenderer legL;
    private ModelRenderer wing2;
    private ModelRenderer wing1;
    private ModelRenderer skirt1;
    ModelRenderer hairA_L;

    public ModelBrownOwl() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.legL = new ModelRenderer(this, 80, 0);
        this.legL.setRotationPoint(1.4F, -1.0F, 0.0F);
        this.legL.addBox(-1.0F, 4.3F, -1.0F, 2, 8, 2, 0.0F);
        this.skirt1 = new ModelRenderer(this, 0, 40);
        this.skirt1.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.skirt1.addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6, 0.0F);
        ModelRenderer neckA = new ModelRenderer(this, 90, 0);
        neckA.setRotationPoint(-0.1F, -0.8F, 0.0F);
        neckA.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6, 0.0F);
        ModelRenderer hairA_R = new ModelRenderer(this, 28, 32);
        hairA_R.setRotationPoint(3.6F, 3.1F, -2.0F);
        hairA_R.addBox(-0.2F, -2.5F, 0.0F, 1, 5, 3, 0.0F);
        this.setRotateAngle(hairA_R, 0.0F, 0.0F, -0.1668534764906579F);
        ModelRenderer neck = new ModelRenderer(this, 0, 58);
        neck.setRotationPoint(0.0F, 1.2F, 0.0F);
        neck.addBox(-1.5F, 0.0F, -1.0F, 3, 2, 2, 0.0F);
        this.head = new ModelRenderer(this, 40, 14);
        this.head.setRotationPoint(0.0F, -2.7F, 0.0F);
        this.head.addBox(-4.0F, -6.6F, -4.0F, 8, 8, 8, 0.0F);
        ModelRenderer hairA_R01_1 = new ModelRenderer(this, 0, 0);
        hairA_R01_1.setRotationPoint(-4.0F, 4.2F, -2.1F);
        hairA_R01_1.addBox(-0.8F, -2.5F, -1.5F, 1, 5, 3, 0.0F);
        this.setRotateAngle(hairA_R01_1, 0.0F, 0.0F, 0.27314402793711257F);
        ModelRenderer hair1 = new ModelRenderer(this, 0, 33);
        hair1.setRotationPoint(0.0F, -5.1F, -3.7F);
        hair1.addBox(-4.0F, -1.5F, 0.0F, 8, 4, 1, 0.0F);
        this.setRotateAngle(hair1, 0.0F, -3.141592653589793F, 0.0F);
        ModelRenderer hairA_R01 = new ModelRenderer(this, 0, 0);
        hairA_R01.setRotationPoint(4.0F, 4.2F, -2.1F);
        hairA_R01.addBox(-0.2F, -2.5F, -1.5F, 1, 5, 3, 0.0F);
        this.setRotateAngle(hairA_R01, 0.0F, 0.0F, -0.27314402793711257F);
        this.handL = new ModelRenderer(this, 65, 0);
        this.handL.setRotationPoint(2.6F, 1.2F, 0.0F);
        this.handL.addBox(0.0F, -0.8F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(handL, 0.0F, 0.0F, -0.15707963267948966F);
        this.handA_R = new ModelRenderer(this, 15, 0);
        this.handA_R.setRotationPoint(-5.1F, 0.6F, -0.5F);
        this.handA_R.addBox(0.0F, -0.8F, -1.0F, 3, 8, 3, 0.0F);
        this.setRotateAngle(handA_R, 0.0F, 0.0F, 0.15707963267948966F);
        wing2 = new ModelRenderer(this, 28, 20);
        wing2.setRotationPoint(-4.0F, -0.98F, -3.6F);
        wing2.addBox(-1.1F, -0.5F, -2.5F, 1, 5, 5, 0.0F);
        this.setRotateAngle(wing2, 0.0F, 0.0F, 0.3186971214141647F);
        this.body = new ModelRenderer(this, 40, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5, 0.0F);
        this.setRotateAngle(body, 0.0F, 3.141592653589793F, 0.0F);
        ModelRenderer hairA1 = new ModelRenderer(this, 0, 28);
        hairA1.setRotationPoint(-2.7F, 0.6F, 0.2F);
        hairA1.addBox(-0.4F, -2.7F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(hairA1, 0.0F, 0.0F, -1.0471975511965976F);
        this.handR = new ModelRenderer(this, 65, 0);
        this.handR.setRotationPoint(-2.6F, 1.0F, 0.0F);
        this.handR.addBox(-2.0F, -0.8F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(handR, 0.0F, 0.0F, 0.15707963267948966F);
        this.legR = new ModelRenderer(this, 80, 0);
        this.legR.setRotationPoint(-1.3F, -1.0F, 0.0F);
        this.legR.addBox(-1.0F, 4.3F, -1.0F, 2, 8, 2, 0.0F);
        ModelRenderer hairA2 = new ModelRenderer(this, 5, 28);
        hairA2.setRotationPoint(2.5F, 0.6F, 0.2F);
        hairA2.addBox(-0.4F, -2.7F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(hairA2, 0.0F, 0.0F, 1.0471975511965976F);
        wing1 = new ModelRenderer(this, 28, 20);
        wing1.setRotationPoint(3.9F, -0.94F, -3.6F);
        wing1.addBox(0.0F, -0.5F, -2.5F, 1, 5, 5, 0.0F);
        this.setRotateAngle(wing1, 0.0F, 0.0F, -0.31869712141416456F);
        this.handA_L = new ModelRenderer(this, 28, 0);
        this.handA_L.setRotationPoint(2.1F, 1.2F, -0.5F);
        this.handA_L.addBox(0.0F, -0.8F, -1.0F, 3, 8, 3, 0.0F);
        this.setRotateAngle(handA_L, 0.0F, 0.0F, -0.15707963267948966F);
        hairA_L = new ModelRenderer(this, 28, 32);
        hairA_L.setRotationPoint(-4.2F, 3.1F, -2.0F);
        hairA_L.addBox(-0.2F, -2.5F, 0.0F, 1, 5, 3, 0.0F);
        this.setRotateAngle(hairA_L, 0.0F, 0.0F, 0.1668534764906579F);
        this.skirt1.addChild(this.legL);
        this.body.addChild(this.skirt1);
        this.body.addChild(neckA);
        hair1.addChild(hairA_R);
        this.head.addChild(neck);
        this.body.addChild(this.head);
        hair1.addChild(hairA_R01_1);
        this.head.addChild(hair1);
        hair1.addChild(hairA_R01);
        this.body.addChild(this.handL);
        this.body.addChild(handA_R);
        hair1.addChild(wing2);
        hair1.addChild(hairA1);
        this.body.addChild(this.handR);
        this.skirt1.addChild(this.legR);
        hair1.addChild(hairA2);
        hair1.addChild(wing1);
        this.body.addChild(handA_L);
        hair1.addChild(hairA_L);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
    }

    //下は特殊なモデルを動かすのに必須
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityBrownOwl)) {
            return;
        }

        EntityBrownOwl entityowl = (EntityBrownOwl) entityIn;
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag || entityowl.isFlying()) {
            this.head.rotateAngleX = -0.413716F;
        } else {
            this.head.rotateAngleX = headPitch * 0.017453292F;
        }

        this.body.rotateAngleX = 0.0F;
        this.body.rotateAngleY = 0.0F;
        float f = 1.0F;

        if (entityowl.isFlying()) {
            this.body.rotateAngleX = 0.413716F;
        }

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
        this.handA_R.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handA_L.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handA_R.rotateAngleZ = 0.0F;
        this.handA_L.rotateAngleZ = 0.0F;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.legR.rotateAngleY = 0.0F;
        this.legL.rotateAngleY = 0.0F;
        this.legR.rotateAngleZ = 0.0F;
        this.legL.rotateAngleZ = 0.0F;
        this.skirt1.rotateAngleX = 0F;

        if (entityowl.isFlying()) {
            this.handR.rotateAngleX = 0.0F;
            this.handL.rotateAngleX = 0.0F;
            this.handA_R.rotateAngleX = 0.0F;
            this.handA_L.rotateAngleX = 0.0F;
            this.legR.rotateAngleX = 0.0F;
            this.legL.rotateAngleX = 0.0F;
        }

        if (entityowl.isSitting() || this.isRiding) {
            this.skirt1.rotateAngleX = -1.4137167F;

            if (entityowl.isReading()) {
                this.handR.rotateAngleX = -0.4F;
                this.handL.rotateAngleX = -0.4F;
                this.handA_R.rotateAngleX = -0.4F;
                this.handA_L.rotateAngleX = -0.4F;
            }
            GL11.glTranslatef(0F, 0.5F, 0F);
        }
        this.handR.rotateAngleY = 0.0F;
        this.handL.rotateAngleY = 0.0F;
        this.handA_R.rotateAngleY = 0.0F;
        this.handA_L.rotateAngleY = 0.0F;
        this.handR.rotateAngleZ = 0.0F;

        if (entityowl.getEatingTick() > 1) {
            this.handR.rotateAngleZ = -0.6F + MathHelper.cos(ageInTicks * 0.5F) * 0.6F;
            this.handR.rotateAngleX = -0.9F;
            this.handA_R.rotateAngleZ = -0.6F + MathHelper.cos(ageInTicks * 0.5F) * 0.6F;
            this.handA_R.rotateAngleX = -0.9F;
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
            this.handA_R.rotateAngleY += this.handR.rotateAngleY;
            this.handA_L.rotateAngleY += this.handL.rotateAngleY;
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
        this.handA_R.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handA_L.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handA_R.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.handA_L.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        GL11.glTranslatef(0F, 0.3F, 0F);
        this.wing1.rotateAngleZ = -ageInTicks;
        this.wing2.rotateAngleZ = ageInTicks;
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
