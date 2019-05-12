package baguchan.japaricraftmod.client;

import baguchan.japaricraftmod.client.render.*;
import baguchan.japaricraftmod.entity.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class JapariRender {
    public static void entityRender() {
        RenderingRegistry.registerEntityRenderingHandler(EntityServal.class, RenderServal::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKouteiPenguin.class, RenderKouteiPenguin::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityOtter.class, RenderOtter::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityShoebill.class, RenderShoebill::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySquirre.class, RenderSquirre::new);
    }
}
