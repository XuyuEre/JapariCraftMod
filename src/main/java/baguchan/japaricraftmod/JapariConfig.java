package baguchan.japaricraftmod;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class JapariConfig {
    public static Common COMMON;
    public static ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }


    public static class Common {

        public final ForgeConfigSpec.IntValue levelupLimit;

        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("General Settings").push("common");
            levelupLimit = builder.comment("This can change the number of times that friends can level up").translation("config.japaricraftmod.levellimit").defineInRange("levellimit", 20, 0, Integer.MAX_VALUE);
            builder.pop();

        }
    }


}