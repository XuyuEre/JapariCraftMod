
package com.japaricraft.japaricraftmod.item;


import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class JaparimanCocoa extends ItemFood {
    public JaparimanCocoa() {
        super(5,3,false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("JaparimanCocoa");
        this.setMaxStackSize(64);
        this.setMaxDamage(1);
        this.setHasSubtypes(true);
    }
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn){
        if (!worldIn.isRemote)
        {
            playerIn.addStat(JapariCraftMod.achievement_japariman);
            playerIn.addStat(JapariCraftMod.achievement_japarimancocoa);
        }
    }
}