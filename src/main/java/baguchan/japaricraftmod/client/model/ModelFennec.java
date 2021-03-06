package baguchan.japaricraftmod.client.model;

import baguchan.japaricraftmod.mob.EntityFennec;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * fennec - Undefined
 * Created using Tabula 5.1.0
 */
public class ModelFennec extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer ear_3;
    public ModelRenderer tail_1;
    public ModelRenderer head;
    public ModelRenderer hand_l;
    public ModelRenderer hand_r;
    public ModelRenderer skirt_1;
    public ModelRenderer leg_l;
    public ModelRenderer leg_r;
    public ModelRenderer tie1;
    public ModelRenderer op_l;
    public ModelRenderer neck;
    public ModelRenderer hair_fh;
    public ModelRenderer hair_fh_2;
    public ModelRenderer hair_fh_3;
    public ModelRenderer hair_fh_4;
    public ModelRenderer hair_fh_5;
    public ModelRenderer main_hair_l;
    public ModelRenderer main_hair_back;
    public ModelRenderer main_hair_r;
    public ModelRenderer hair_fh6;
    public ModelRenderer hair_fh7;
    public ModelRenderer ear_1;
    public ModelRenderer neck_a;
    public ModelRenderer shape50;
    public ModelRenderer shape51;
    public ModelRenderer ear_2;
    public ModelRenderer ear_3_1;
    public ModelRenderer cl_l;
    public ModelRenderer cl_l_2;
    public ModelRenderer cl_r;
    public ModelRenderer cl_r_2;
    public ModelRenderer skirt_2;
    public ModelRenderer tie2;
    public ModelRenderer tie3;
    public ModelRenderer tie1_l1;
    public ModelRenderer tie1_l2;
    public ModelRenderer ear_4;
    public ModelRenderer ear_5;

    public ModelFennec() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.ear_4 = new ModelRenderer(this, 240, 45);
        this.ear_4.setRotationPoint(0.9F, -3.0F, 1.0F);
        this.ear_4.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.cl_r_2 = new ModelRenderer(this, 150, 0);
        this.cl_r_2.setRotationPoint(0.0F, 7.0F, 0.5F);
        this.cl_r_2.addBox(-1.5F, -1.0F, -3.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(cl_r_2, -0.020943951023931952F, 0.0F, 0.0F);
        this.ear_3 = new ModelRenderer(this, 240, 12);
        this.ear_3.setRotationPoint(2.5F, -6.0F, 0.0F);
        this.ear_3.addBox(-2.1F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(ear_3, 0.0F, -3.141592653589793F, 0.6981317007977318F);
        this.shape51 = new ModelRenderer(this, 115, 0);
        this.shape51.setRotationPoint(6.1F, -0.73F, -0.8F);
        this.shape51.addBox(-0.5F, -0.5F, -0.4F, 1, 3, 0, 0.0F);
        this.setRotateAngle(shape51, -0.5235987755982988F, 0.0F, -0.5235987755982988F);
        this.cl_r = new ModelRenderer(this, 40, 0);
        this.cl_r.setRotationPoint(0.0F, 0.0F, 0.5F);
        this.cl_r.addBox(-1.5F, -1.0F, -3.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(cl_r, -0.020943951023931952F, 0.0F, 0.0F);
        this.ear_5 = new ModelRenderer(this, 248, 24);
        this.ear_5.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.ear_5.addBox(-1.0F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.neck = new ModelRenderer(this, 96, 5);
        this.neck.setRotationPoint(-0.09F, 0.0F, 0.0F);
        this.neck.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.hair_fh = new ModelRenderer(this, 0, 61);
        this.hair_fh.setRotationPoint(0.0F, -7.0F, -4.0F);
        this.hair_fh.addBox(-4.0F, -1.0F, -1.0F, 8, 2, 1, 0.0F);
        this.tie1_l2 = new ModelRenderer(this, 60, 29);
        this.tie1_l2.setRotationPoint(-0.3F, -1.89F, -1.0F);
        this.tie1_l2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(tie1_l2, -0.005235987755982988F, 0.45378560551852565F, -0.17453292519943295F);
        this.hair_fh_5 = new ModelRenderer(this, 205, 45);
        this.hair_fh_5.setRotationPoint(3.0F, -5.9F, -3.0F);
        this.hair_fh_5.addBox(-1.0F, -0.5F, -2.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(hair_fh_5, 0.0F, 0.0F, -0.08726646259971647F);
        this.hand_l = new ModelRenderer(this, 28, 20);
        this.hand_l.setRotationPoint(4.0F, -7.4F, -1.0F);
        this.hand_l.addBox(-1.0F, -0.5F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(hand_l, 0.0F, 0.0F, -0.08726646259971647F);
        this.skirt_1 = new ModelRenderer(this, 0, 26);
        this.skirt_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.skirt_1.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6, 0.0F);
        this.head = new ModelRenderer(this, 96, 39);
        this.head.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.cl_l_2 = new ModelRenderer(this, 169, 0);
        this.cl_l_2.setRotationPoint(0.0F, 7.0F, -0.5F);
        this.cl_l_2.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(cl_l_2, -0.020943951023931952F, 0.0F, 0.0F);
        this.neck_a = new ModelRenderer(this, 74, 24);
        this.neck_a.setRotationPoint(0.1F, -1.0F, 0.0F);
        this.neck_a.addBox(-2.5F, 1.0F, -2.5F, 5, 2, 5, 0.0F);
        this.shape50 = new ModelRenderer(this, 122, 0);
        this.shape50.setRotationPoint(-0.2F, 0.3F, -0.8F);
        this.shape50.addBox(-0.5F, -0.5F, -0.4F, 1, 3, 0, 0.0F);
        this.setRotateAngle(shape50, -0.5235987755982988F, 0.0F, 0.3490658503988659F);
        this.tie2 = new ModelRenderer(this, 65, 14);
        this.tie2.setRotationPoint(-0.5F, 1.0F, 0.0F);
        this.tie2.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(tie2, 0.0F, 0.0F, 1.5707963267948966F);
        this.ear_2 = new ModelRenderer(this, 240, 34);
        this.ear_2.setRotationPoint(0.9F, -3.0F, -1.0F);
        this.ear_2.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.leg_r = new ModelRenderer(this, 66, 0);
        this.leg_r.setRotationPoint(-1.5F, 2.0F, 0.0F);
        this.leg_r.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.hair_fh6 = new ModelRenderer(this, 203, 0);
        this.hair_fh6.setRotationPoint(-2.9F, -7.41F, -3.8F);
        this.hair_fh6.addBox(-1.5F, -0.4F, -1.2F, 2, 4, 2, 0.0F);
        this.setRotateAngle(hair_fh6, 0.0F, 0.0F, 0.17453292519943295F);
        this.ear_3_1 = new ModelRenderer(this, 240, 24);
        this.ear_3_1.setRotationPoint(0.0F, -3.0F, -1.0F);
        this.ear_3_1.addBox(-1.0F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.main_hair_r = new ModelRenderer(this, 0, 34);
        this.main_hair_r.setRotationPoint(4.0F, -3.95F, 0.0F);
        this.main_hair_r.addBox(-4.0F, -4.0F, -1.0F, 8, 8, 1, 0.0F);
        this.setRotateAngle(main_hair_r, 0.0F, -1.5707963267948966F, -0.17453292519943295F);
        this.skirt_2 = new ModelRenderer(this, 0, 13);
        this.skirt_2.setRotationPoint(-0.5F, 1.0F, -0.5F);
        this.skirt_2.addBox(-3.0F, 0.0F, -3.0F, 7, 1, 7, 0.0F);
        this.ear_1 = new ModelRenderer(this, 240, 0);
        this.ear_1.setRotationPoint(-2.5F, -6.6F, 0.0F);
        this.ear_1.addBox(-2.1F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(ear_1, 0.0F, -0.017627825445142728F, -0.6981317007977318F);
        this.op_l = new ModelRenderer(this, 60, 33);
        this.op_l.setRotationPoint(0.0F, -8.0F, 1.2F);
        this.op_l.addBox(-2.5F, -1.0F, -5.0F, 5, 3, 3, 0.0F);
        this.setRotateAngle(op_l, 0.5410520681182421F, 0.0F, 0.0F);
        this.hair_fh_3 = new ModelRenderer(this, 0, 55);
        this.hair_fh_3.setRotationPoint(1.0F, -3.1F, -4.0F);
        this.hair_fh_3.addBox(-2.0F, -1.0F, -1.0F, 2, 1, 1, 0.0F);
        this.tail_1 = new ModelRenderer(this, 217, 0);
        this.tail_1.setRotationPoint(-0.2F, 11.5F, 1.1F);
        this.tail_1.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F);
        this.setRotateAngle(tail_1, -0.6373942428283291F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 108, 16);
        this.body.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.body.addBox(-2.5F, -8.0F, -2.5F, 5, 8, 5, 0.0F);
        this.leg_l = new ModelRenderer(this, 57, 0);
        this.leg_l.setRotationPoint(1.5F, 2.0F, 0.0F);
        this.leg_l.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.hair_fh_4 = new ModelRenderer(this, 205, 50);
        this.hair_fh_4.setRotationPoint(-2.0F, -5.9F, -3.0F);
        this.hair_fh_4.addBox(-1.0F, -0.5F, -2.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(hair_fh_4, 0.0F, 0.0F, 0.08726646259971647F);
        this.hair_fh_2 = new ModelRenderer(this, 0, 58);
        this.hair_fh_2.setRotationPoint(0.0F, -5.0F, -4.0F);
        this.hair_fh_2.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 1, 0.0F);
        this.hair_fh7 = new ModelRenderer(this, 188, 0);
        this.hair_fh7.setRotationPoint(3.9F, -7.58F, -3.8F);
        this.hair_fh7.addBox(-1.5F, -0.4F, -1.2F, 2, 4, 2, 0.0F);
        this.setRotateAngle(hair_fh7, 0.0F, 0.0F, -0.17453292519943295F);
        this.main_hair_l = new ModelRenderer(this, 28, 55);
        this.main_hair_l.setRotationPoint(-4.0F, -3.95F, 0.0F);
        this.main_hair_l.addBox(-4.0F, -4.0F, -1.0F, 8, 8, 1, 0.0F);
        this.setRotateAngle(main_hair_l, 0.0F, 1.5707963267948966F, 0.17453292519943295F);
        this.cl_l = new ModelRenderer(this, 28, 0);
        this.cl_l.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.cl_l.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(cl_l, -0.020943951023931952F, 0.0F, 0.0F);
        this.tie3 = new ModelRenderer(this, 60, 19);
        this.tie3.setRotationPoint(-0.5F, -2.0F, 0.0F);
        this.tie3.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(tie3, 0.0F, 0.0F, 1.5707963267948966F);
        this.tie1 = new ModelRenderer(this, 59, 15);
        this.tie1.setRotationPoint(-0.5F, -7.0F, -2.3F);
        this.tie1.addBox(-1.0F, -1.0F, -1.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(tie1, 0.0F, 0.0F, 1.5707963267948966F);
        this.hand_r = new ModelRenderer(this, 38, 20);
        this.hand_r.setRotationPoint(-4.0F, -7.5F, 1.0F);
        this.hand_r.addBox(-1.0F, -0.5F, -2.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(hand_r, 0.0F, 0.0F, 0.08726646259971647F);
        this.main_hair_back = new ModelRenderer(this, 28, 46);
        this.main_hair_back.setRotationPoint(0.0F, -4.0F, 4.0F);
        this.main_hair_back.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 1, 0.0F);
        this.setRotateAngle(main_hair_back, 0.08726646259971647F, 0.0F, 0.0F);
        this.tie1_l1 = new ModelRenderer(this, 60, 24);
        this.tie1_l1.setRotationPoint(-0.1F, -0.1F, -1.0F);
        this.tie1_l1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(tie1_l1, 0.0F, 0.45378560551852565F, 0.17453292519943295F);
        this.ear_3.addChild(this.ear_4);
        this.hand_r.addChild(this.cl_r_2);
        this.hair_fh6.addChild(this.shape51);
        this.hand_r.addChild(this.cl_r);
        this.ear_3.addChild(this.ear_5);
        this.head.addChild(this.ear_3);
        this.head.addChild(this.neck);
        this.head.addChild(this.hair_fh);
        this.tie1.addChild(this.tie1_l2);
        this.head.addChild(this.hair_fh_5);
        this.body.addChild(this.hand_l);
        this.body.addChild(this.skirt_1);
        this.body.addChild(this.head);
        this.hand_l.addChild(this.cl_l_2);
        this.neck.addChild(this.neck_a);
        this.hair_fh6.addChild(this.shape50);
        this.tie1.addChild(this.tie2);
        this.ear_1.addChild(this.ear_2);
        this.body.addChild(this.leg_r);
        this.head.addChild(this.hair_fh6);
        this.ear_1.addChild(this.ear_3_1);
        this.head.addChild(this.main_hair_r);
        this.skirt_1.addChild(this.skirt_2);
        this.head.addChild(this.ear_1);
        this.body.addChild(this.op_l);
        this.head.addChild(this.hair_fh_3);
        this.body.addChild(this.leg_l);
        this.head.addChild(this.hair_fh_4);
        this.head.addChild(this.hair_fh_2);
        this.head.addChild(this.hair_fh7);
        this.head.addChild(this.main_hair_l);
        this.hand_l.addChild(this.cl_l);
        this.tie1.addChild(this.tie3);
        this.body.addChild(this.tie1);
        this.body.addChild(this.hand_r);
        this.head.addChild(this.main_hair_back);
        this.tie1.addChild(this.tie1_l1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.tail_1.render(f5);
        this.body.render(f5);
    }

    //下は特殊なモデルを動かすのに必須
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityFennec)) {
            return;
        }

        EntityFennec entityEntityFennec = (EntityFennec) entityIn;
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
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

        this.hand_r.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.hand_l.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.hand_r.rotateAngleZ = 0.0F;
        this.hand_l.rotateAngleZ = 0.0F;
        this.leg_r.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leg_l.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.leg_r.rotateAngleY = 0.0F;
        this.leg_l.rotateAngleY = 0.0F;
        this.leg_r.rotateAngleZ = 0.0F;
        this.leg_l.rotateAngleZ = 0.0F;

        if (entityEntityFennec.isSitting() || this.isRiding) {
            this.leg_r.rotateAngleX = -1.4137167F;
            this.leg_r.rotateAngleY = ((float) Math.PI / 10F);
            this.leg_r.rotateAngleZ = 0.07853982F;
            this.leg_l.rotateAngleX = -1.4137167F;
            this.leg_l.rotateAngleY = -((float) Math.PI / 10F);
            this.leg_l.rotateAngleZ = -0.07853982F;
            GL11.glTranslatef(0F, 0.3F, 0F);
        }

        this.hand_r.rotateAngleY = 0.0F;
        this.hand_l.rotateAngleY = 0.0F;
        this.hand_r.rotateAngleZ = 0.0F;

        if (entityEntityFennec.getEatingTick() > 1) {
            this.hand_r.rotateAngleZ = -0.6F + MathHelper.cos(ageInTicks * 0.5F) * 0.6F;
            this.hand_r.rotateAngleX = -0.9F;
        }

        if (this.swingProgress > 0.0F) {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT) {
                this.body.rotateAngleY *= -1.0F;
            }
            this.hand_r.rotateAngleY += this.body.rotateAngleY;
            this.hand_l.rotateAngleY += this.body.rotateAngleY;
            float f2 = MathHelper.sin(f1 * (float) Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
            modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }

        this.hand_r.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.hand_l.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.hand_r.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.hand_l.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;


        this.tail_1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount - 0.63F;

    }

    public void postRenderArm(float scale, EnumHandSide side) {
        this.getArmForSide(side).postRender(scale);
    }

    public ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.hand_l : this.hand_r;
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

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}