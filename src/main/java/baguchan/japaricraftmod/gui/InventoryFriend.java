package baguchan.japaricraftmod.gui;

import baguchan.japaricraftmod.entity.EntityFriend;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryFriend extends InventoryBasic {

    private EntityFriend entityfriends;

    public InventoryFriend(EntityFriend entityfriends, int slotCount) {
        super(entityfriends.getName(), slotCount);

        this.entityfriends = entityfriends;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.entityfriends.isAlive() && (player.getDistanceSq(this.entityfriends) < 64.0D);

    }

    // TODO /* ======================================== MOD START =====================================*/

    public EntityFriend getContainerEntityFriends() {
        return this.entityfriends;
    }

    public void readInventoryFromNBT(NBTTagList nbtList) {
        for (int i = 0; i < nbtList.size(); ++i) {
            ItemStack itemstack = ItemStack.read(nbtList.getCompound(i));
            if (!itemstack.isEmpty()) {
                this.addItem(itemstack);
            }
        }

    }

    public NBTTagList writeInventoryToNBT() {
        NBTTagList nbtList = new NBTTagList();

        for (int slot = 0; slot < this.getSizeInventory(); ++slot) {
            ItemStack stackSlot = this.getStackInSlot(slot);

            if (FriendMobHelper.isNotEmptyItemStack(stackSlot)) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("Slot", (byte) slot);
                stackSlot.write(nbt);
                nbtList.add(nbt);
            }
        }

        return nbtList;
    }

}