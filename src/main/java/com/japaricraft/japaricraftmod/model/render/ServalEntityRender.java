package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.Serval;
import com.japaricraft.japaricraftmod.model.ModelServal;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;


@SideOnly(Side.CLIENT)
public class ServalEntityRender extends RenderLiving<Serval>
{
    private static final ResourceLocation SERVAL_TEXTURES = new ResourceLocation(MODID, "textures/entity/serval/serval.png");
    private static final ResourceLocation BEG_TEXTURES = new ResourceLocation(MODID, "textures/entity/serval/serval_beg.png");
    private static final ResourceLocation PLAY_TEXTURES = new ResourceLocation(MODID, "textures/entity/serval/serval_play.png");
    public ServalEntityRender(RenderManager renderManager)
    {
            super(renderManager, new ModelServal(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(Serval entity)
    {
        if (entity.isPlaying()) {
            return PLAY_TEXTURES;
        } else if (entity.isBegging()) {
            return BEG_TEXTURES;
        } else {
            return SERVAL_TEXTURES;
        }
    }
}