package baguchan.japaricraftmod.client.model.tileentity;

import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.entity.*;

/**
 * ModelSandStarPortal - bagu
 * Created using Tabula 7.0.0
 */
public class ModelSandStarPortal extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer portal;
    public ModelRenderer bar1;
    public ModelRenderer bar2;
    public ModelRenderer bar3;
    public ModelRenderer bar4;
    public ModelRenderer subBar1;
    public ModelRenderer subBar2;
    public ModelRenderer subBar3;
    public ModelRenderer subBar4;

    public ModelSandStarPortal() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.bar2 = new ModelRenderer(this, 0, 24);
        this.bar2.setRotationPoint(5.0F, 16.0F, -4.8F);
        this.bar2.addBox(-2.0F, -2.7F, -2.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(bar2, -0.36425021489121656F, -0.6829473363053812F, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.base.addBox(-8.0F, 0.0F, -8.0F, 16, 8, 16, 0.0F);
        this.bar3 = new ModelRenderer(this, 0, 24);
        this.bar3.setRotationPoint(-5.0F, 16.0F, 5.2F);
        this.bar3.addBox(-2.0F, -2.7F, -2.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(bar3, -0.36425021489121656F, 2.321986036853256F, 0.0F);
        this.bar4 = new ModelRenderer(this, 0, 24);
        this.bar4.setRotationPoint(5.0F, 16.0F, 5.2F);
        this.bar4.addBox(-2.0F, -2.7F, -2.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(bar4, -0.36425021489121656F, -2.321986036853256F, 0.0F);
        this.subBar4 = new ModelRenderer(this, 16, 24);
        this.subBar4.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.subBar4.addBox(-1.5F, -3.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(subBar4, -0.22759093446006054F, 0.0F, 0.0F);
        this.portal = new ModelRenderer(this, 28, 24);
        this.portal.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.portal.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bar1 = new ModelRenderer(this, 0, 24);
        this.bar1.setRotationPoint(-5.0F, 16.0F, -4.8F);
        this.bar1.addBox(-2.0F, -2.7F, -2.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(bar1, -0.36425021489121656F, 0.6829473363053812F, 0.0F);
        this.subBar2 = new ModelRenderer(this, 16, 24);
        this.subBar2.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.subBar2.addBox(-1.5F, -3.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(subBar2, -0.22759093446006054F, 0.0F, 0.0F);
        this.subBar3 = new ModelRenderer(this, 16, 24);
        this.subBar3.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.subBar3.addBox(-1.5F, -3.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(subBar3, -0.22759093446006054F, 0.0F, 0.0F);
        this.subBar1 = new ModelRenderer(this, 16, 24);
        this.subBar1.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.subBar1.addBox(-1.5F, -3.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(subBar1, -0.22759093446006054F, 0.0F, 0.0F);
        this.bar4.addChild(this.subBar4);
        this.bar2.addChild(this.subBar2);
        this.bar3.addChild(this.subBar3);
        this.bar1.addChild(this.subBar1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bar2.render(f5);
        this.base.render(f5);
        this.bar3.render(f5);
        this.bar4.render(f5);
        this.portal.render(f5);
        this.bar1.render(f5);
    }


    public void renderAll() {

        this.portal.render(0.0625F);

        this.base.render(0.0625F);

        this.bar1.render(0.0625F);

        this.bar2.render(0.0625F);
        this.bar3.render(0.0625F);
        this.bar4.render(0.0625F);

    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
