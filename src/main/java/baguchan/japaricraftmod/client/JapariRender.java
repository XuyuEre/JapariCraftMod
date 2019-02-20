package baguchan.japaricraftmod.client;

import baguchan.japaricraftmod.client.render.*;
import baguchan.japaricraftmod.entity.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.client.registry.*;

@OnlyIn(Dist.CLIENT)
public class JapariRender {
    public static void entityRender() {
        RenderingRegistry.registerEntityRenderingHandler(EntityServal.class, RenderServal::new);
    }
}
