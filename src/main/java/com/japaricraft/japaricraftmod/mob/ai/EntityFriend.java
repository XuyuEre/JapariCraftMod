package com.japaricraft.japaricraftmod.mob.ai;

import com.japaricraft.japaricraftmod.gui.FriendMobNBTs;
import com.japaricraft.japaricraftmod.gui.InventoryFriendEquipment;
import com.japaricraft.japaricraftmod.gui.InventoryFriendMain;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityFriend extends EntityTameable{
    private InventoryFriendMain inventoryFriendMain;
    private InventoryFriendEquipment inventoryFriendEquipment;

    protected EntityFriend(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        compound.setTag(FriendMobNBTs.ENTITY_FRIEND_INVENTORY, this.getInventoryFriendMain().writeInventoryToNBT());

        compound.setTag(FriendMobNBTs.ENTITY_FRIEND_EQUIPMENT, this.getInventoryFriendEquipment().writeInventoryToNBT());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        this.getInventoryFriendMain().readInventoryFromNBT(compound.getTagList(FriendMobNBTs.ENTITY_FRIEND_INVENTORY, 10));

        this.getInventoryFriendEquipment().readInventoryFromNBT(compound.getTagList(FriendMobNBTs.ENTITY_FRIEND_EQUIPMENT, 10));

    }



    public InventoryFriendMain getInventoryFriendMain()
    {
        if (this.inventoryFriendMain == null) {
            this.inventoryFriendMain = new InventoryFriendMain(this);
        }

        return this.inventoryFriendMain;
    }
    public InventoryFriendEquipment getInventoryFriendEquipment()
    {
        if (this.inventoryFriendEquipment == null) {
            this.inventoryFriendEquipment = new InventoryFriendEquipment(this);
        }

        return this.inventoryFriendEquipment;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
        ItemStack itemStack;

        switch (slotIn) {
            case CHEST:

                itemStack = this.getInventoryFriendEquipment().getChestItem();
                break;
            case FEET:

                itemStack = this.getInventoryFriendEquipment().getbootItem();
                break;

            default:

                itemStack = ItemStack.EMPTY;
                break;
        }

        return itemStack;
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
        switch (slotIn) {
            case CHEST:

                this.getInventoryFriendEquipment().setInventorySlotContents(0, stack);
                break;
            case FEET:

                this.getInventoryFriendEquipment().setInventorySlotContents(1, stack);
                break;

            default:

                // none
                break;
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        World world = this.getEntityWorld();

        if (!world.isRemote) {
            InventoryHelper.dropInventoryItems(world, this, this.getInventoryFriendMain());

            InventoryHelper.dropInventoryItems(world, this, this.getInventoryFriendEquipment());
        }

        super.onDeath(cause);
    }
    public EntityFriend.Condition getCondition()
    {
        int health = (int) this.getHealth();
        int healthMax = (int) this.getMaxHealth();

        EntityFriend.Condition condition = EntityFriend.Condition.FINE;

        if (health < (healthMax / 2))
        {
            condition = EntityFriend.Condition.HURT;

            if (health < (healthMax / 4))
            {
                condition = EntityFriend.Condition.DYING;
            }
        }

        return condition;
    }

    public enum Condition
    {

        FINE,
        HURT,
        DYING,

    }
}
