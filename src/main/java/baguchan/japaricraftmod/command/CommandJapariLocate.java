package baguchan.japaricraftmod.command;

import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import net.minecraft.command.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.*;

public class CommandJapariLocate {
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TextComponentTranslation("commands.locate.failed"));


    public static void register(CommandDispatcher<CommandSource> dispatcher) {

        dispatcher.register(Commands.literal("japarilocate").requires((p_198533_0_) -> {
            return p_198533_0_.hasPermissionLevel(2);
        }).then(Commands.literal("SandStarLab").executes((p_198530_0_) -> {
            return locateStructure(p_198530_0_.getSource(), "SandStarLab");
        })));

    }

    private static int locateStructure(CommandSource source, String structureName) throws CommandSyntaxException {
        BlockPos blockpos = new BlockPos(source.getPos());
        BlockPos blockpos1 = source.getWorld().findNearestStructure(structureName, blockpos, 100, false);
        if (blockpos1 == null) {
            throw FAILED_EXCEPTION.create();
        } else {
            int i = MathHelper.floor(getDistance(blockpos.getX(), blockpos.getZ(), blockpos1.getX(), blockpos1.getZ()));
            ITextComponent itextcomponent = TextComponentUtils.wrapInSquareBrackets(new TextComponentTranslation("chat.coordinates", blockpos1.getX(), "~", blockpos1.getZ())).applyTextStyle((p_211746_1_) -> {
                p_211746_1_.setColor(TextFormatting.GREEN).setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp @s " + blockpos1.getX() + " ~ " + blockpos1.getZ())).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("chat.coordinates.tooltip")));
            });
            source.sendFeedback(new TextComponentTranslation("commands.locate.success", structureName, itextcomponent, i), false);
            return i;
        }
    }

    private static float getDistance(int x1, int z1, int x2, int z2) {
        int i = x2 - x1;
        int j = z2 - z1;
        return MathHelper.sqrt((float) (i * i + j * j));
    }
}
