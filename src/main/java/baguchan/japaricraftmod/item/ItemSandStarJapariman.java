package baguchan.japaricraftmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSandStarJapariman extends ItemJapariman {
    public ItemSandStarJapariman(Properties group) {
        super(4, 0.44F, false, group);
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 900, 0));
        }

    }
}
