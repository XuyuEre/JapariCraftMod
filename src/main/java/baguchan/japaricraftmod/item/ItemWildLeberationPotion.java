package baguchan.japaricraftmod.item;

import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.world.*;

public class ItemWildLeberationPotion extends Item {
    public ItemWildLeberationPotion(Properties group) {
        super(group);
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.DOLPHINS_GRACE, 600, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1200, 0));
        }

    }
}
