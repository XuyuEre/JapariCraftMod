package baguchan.japaricraftmod.tileentity;

import net.minecraft.tileentity.*;
import net.minecraft.util.*;

public class TileEntitySandStarPortal extends TileEntity implements ITickable {

    public int tickCount;

    public TileEntitySandStarPortal(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TileEntitySandStarPortal() {
        super(JapariTileEntity.tileSandStarPortal);
    }

    public void tick() {
        ++this.tickCount;
    }
}
