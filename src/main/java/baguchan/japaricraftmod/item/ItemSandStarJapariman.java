package baguchan.japaricraftmod.item;

import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.world.*;

public class ItemSandStarJapariman extends ItemJapariman {
    public ItemSandStarJapariman(Properties group) {
        super(4, 0.62F, false, group);
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 900, 0));
        }

    }
}
