package baguchan.japaricraftmod.gui;

import baguchan.japaricraftmod.entity.EntityFriend;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.*;

public class ContainerFriendInventory extends Container {
    private EntityFriend entityFriend;
    private EntityPlayer entityPlayer;


    public ContainerFriendInventory(EntityFriend entityFriend, EntityPlayer entityPlayer) {
        int column;
        int row;
        int index;

        EntityEquipmentSlot slotType;

        entityFriend.getInventoryFriendEquipment().openInventory(entityPlayer);

        //フレンズの装備スロットを追加する
        for (index = 0; index < 5; ++index) {


            int x = 0, y = 0;
            switch (index) {
                case 0:
                    slotType = EntityEquipmentSlot.CHEST;
                    x = 8;
                    y = 36;
                    break;
                case 1:
                    slotType = EntityEquipmentSlot.FEET;
                    x = 80;
                    y = 36;
                    break;
                case 2:
                    slotType = EntityEquipmentSlot.HEAD;
                    x = 8;
                    y = 18;
                    break;
                case 3:
                    slotType = EntityEquipmentSlot.LEGS;
                    x = 80;
                    y = 18;
                    break;
                case 4:
                    slotType = EntityEquipmentSlot.OFFHAND;
                    x = 80;
                    y = 54;
                    break;
                default:
                    slotType = null;
            }
            EntityEquipmentSlot finalSlotType = slotType;
            this.addSlot(new Slot(entityFriend.getInventoryFriendEquipment(), index, x, y) {
                public int getSlotStackLimit() {
                    if (this.getSlotIndex() == 4) {
                        return 64;
                    } else {
                        return 1;
                    }
                }

                @Override
                public boolean isItemValid(ItemStack stack) {
                    if (finalSlotType == null)
                        return false;
                    Item item = stack.getItem();
                    if (item instanceof ItemSword) {
                        return false;
                    }
                    if (item instanceof ItemTool) {
                        return false;
                    }
                    if (item instanceof ItemArmor) {
                        if (this.getSlotIndex() < 4) {
                            if (stack.canEquip(finalSlotType, entityFriend)) {
                                return true;
                            }
                        }
                    }
                    return this.getSlotIndex() == 4;
                  /*  if (item instanceof FriendsEquipment) {
                        FriendsEquipment equip = (FriendsEquipment) item;
                        return equip.getEquipmentType() == slotType;
                    }*/

                }
            });
        }

        entityFriend.getInventoryFriendMain().openInventory(entityPlayer);

        for (column = 0; column < 3; ++column) {
            for (row = 0; row < 9; ++row) {
                index = (row + column * 9);

                this.addSlot(new Slot(entityFriend.getInventoryFriendMain(), index, (row * 18) + 8, (column * 18) + 74));
            }
        }


        entityPlayer.inventory.openInventory(entityPlayer);

        for (column = 0; column < 3; ++column) {
            for (row = 0; row < 9; ++row) {
                index = (row + column * 9 + 9);

                this.addSlot(new Slot(entityPlayer.inventory, index, (row * 18) + 8, (column * 18) + 140));
            }
        }

        for (row = 0; row < 9; ++row) {
            index = row;

            this.addSlot(new Slot(entityPlayer.inventory, index, (row * 18) + 8, 198));
        }

        this.entityFriend = entityFriend;
        this.entityPlayer = entityPlayer;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.entityFriend.getInventoryFriendMain().isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stackEmpty = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot == null || !slot.getHasStack()) {
            return stackEmpty;
        }

        ItemStack srcItemStack = slot.getStack();
        ItemStack dstItemStack = srcItemStack.copy();

        // mergeItemStack(移動するItemStack, 移動先の最小スロット番号, 移動先の最大スロット番号, 昇順or降順)

        // int indexEquipment = 5;
        // int indexMain = 27;

        if (index < 32) {
            if (!this.mergeItemStack(dstItemStack, 32, this.inventorySlots.size(), true)) {
                return stackEmpty;
            }
        } else {
            if (!this.mergeItemStack(dstItemStack, 5, 32, false)) {
                return stackEmpty;
            }
        }

        if (dstItemStack.isEmpty()) {
            slot.putStack(stackEmpty);
        } else {
            slot.onSlotChanged();
        }

        if (dstItemStack.getCount() == srcItemStack.getCount()) {
            return stackEmpty;
        }

        slot.onTake(player, dstItemStack);

        return srcItemStack;
    }


    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        this.entityFriend.getInventoryFriendMain().closeInventory(playerIn);
        this.entityFriend.getInventoryFriendEquipment().closeInventory(playerIn);
        this.entityPlayer.inventory.openInventory(playerIn);

        super.onContainerClosed(playerIn);
    }

}