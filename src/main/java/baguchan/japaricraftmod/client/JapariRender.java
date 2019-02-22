package baguchan.japaricraftmod.client;

import baguchan.japaricraftmod.client.render.RenderKouteiPenguin;
import baguchan.japaricraftmod.client.render.RenderServal;
import baguchan.japaricraftmod.entity.EntityKouteiPenguin;
import baguchan.japaricraftmod.entity.EntityServal;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class JapariRender {
    public static void entityRender() {
        RenderingRegistry.registerEntityRenderingHandler(EntityServal.class, RenderServal::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKouteiPenguin.class, RenderKouteiPenguin::new);
    }
}
