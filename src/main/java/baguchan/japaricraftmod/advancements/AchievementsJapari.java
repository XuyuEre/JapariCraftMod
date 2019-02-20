package baguchan.japaricraftmod.advancements;

import baguchan.japaricraftmod.*;
import net.minecraft.advancements.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class AchievementsJapari {
    //1.12.2のメイドの進捗を参考にした
    public static void grantAdvancement(EntityPlayer player, String advancementName) {
        if (!(player instanceof EntityPlayerMP))
            return;

        AdvancementManager manager = player.world.getServer().getAdvancementManager();
        Advancement advancement = manager.getAdvancement(new ResourceLocation(JapariCraftMod.MODID, "japaricraftmod/" + advancementName));
        if (advancement == null)
            return;

        ((EntityPlayerMP) player).getAdvancements().grantCriterion(advancement, "done");
    }
}
