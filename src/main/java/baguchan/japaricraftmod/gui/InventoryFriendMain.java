package baguchan.japaricraftmod.gui;

import baguchan.japaricraftmod.entity.EntityFriend;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;

public class InventoryFriendMain extends InventoryFriend {

    public InventoryFriendMain(EntityFriend entityFriend) {
        super(entityFriend, (9 * 3));
    }

    @Override
    public void openInventory(EntityPlayer player) {
        super.markDirty();

        this.getContainerEntityFriends().playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5F, this.getContainerEntityFriends().getRNG().nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        super.markDirty();

        this.getContainerEntityFriends().playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5F, this.getContainerEntityFriends().getRNG().nextFloat() * 0.1F + 0.9F);
    }

}