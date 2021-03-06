package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.JapariConfig;
import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.advancements.AchievementsJapari;
import baguchan.japaricraftmod.client.JapariParticleTypes;
import baguchan.japaricraftmod.gui.FriendMobNBTs;
import baguchan.japaricraftmod.gui.InventoryFriendEquipment;
import baguchan.japaricraftmod.gui.InventoryFriendMain;
import baguchan.japaricraftmod.handler.JapariItems;
import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityFriend extends EntityTameable {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(EntityFriend.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> LIMITLEVEL = EntityDataManager.createKey(EntityFriend.class, DataSerializers.VARINT);
    private static final Set<Item> Heal_ITEMS = Sets.newHashSet(JapariItems.japariman, JapariItems.japarimanapple, JapariItems.japarimancocoa, JapariItems.japarimanfruit);

    protected static final DataParameter<Float> dataEXPValue = EntityDataManager.createKey(EntityFriend.class, DataSerializers.FLOAT);

    private InventoryFriendMain inventoryFriendMain;
    private InventoryFriendEquipment inventoryFriendEquipment;
    public float friendPoint = 0;
    protected int eattick = 0;

    public EntityFriend(World worldIn) {
        super(worldIn);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20D);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(EntityFriend.dataEXPValue, Float.valueOf(0));
        this.dataManager.register(ATTACKING, Boolean.FALSE);
        this.dataManager.register(LIMITLEVEL, Integer.valueOf(0));
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24D);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        compound.setTag(FriendMobNBTs.ENTITY_FRIEND_INVENTORY, this.getInventoryFriendMain().writeInventoryToNBT());

        compound.setTag(FriendMobNBTs.ENTITY_FRIEND_EQUIPMENT, this.getInventoryFriendEquipment().writeInventoryToNBT());

        compound.setFloat(JapariCraftMod.MODID + ":FRIEND_EXP", friendPoint);

    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        this.getInventoryFriendMain().readInventoryFromNBT(compound.getTagList(FriendMobNBTs.ENTITY_FRIEND_INVENTORY, 10));

        this.getInventoryFriendEquipment().readInventoryFromNBT(compound.getTagList(FriendMobNBTs.ENTITY_FRIEND_EQUIPMENT, 10));

        friendPoint = compound.getFloat(JapariCraftMod.MODID + ":FRIEND_EXP");

        dataManager.set(EntityFriend.dataEXPValue, friendPoint);
    }

    @Override
    public void onUpdate() {
        if (world.isRemote) {
            if (ticksExisted % 10 == 0) {
                friendPoint = dataManager.get(EntityFriend.dataEXPValue);
            }
        }
        super.onUpdate();
    }

    public float getExp() {
        return friendPoint;
    }

    /**
     * フレンズの経験値関係
     */
    public void addExperience(float value) {
        if (this.getLimit() < this.maxLimit()) {
            friendPoint += value;
        }
        dataManager.set(EntityFriend.dataEXPValue, friendPoint);
    }

    public int getLimit() {
        return MathHelper.clamp(this.dataManager.get(LIMITLEVEL).intValue(), 0, 16);
    }

    public void setLimit(int p_191997_1_) {
        this.dataManager.set(LIMITLEVEL, Integer.valueOf(p_191997_1_));
    }

    private int maxLimit() {
        return JapariConfig.levelLimit;
    }

    @Override
    public void damageArmor(float pDamage) {
        pDamage = Math.max(pDamage / 4, 1);

        if (inventoryFriendEquipment.getbootItem() != null && inventoryFriendEquipment.getbootItem().getItem() instanceof ItemArmor) {
            inventoryFriendEquipment.getbootItem().damageItem((int) pDamage, this);
        }
        if (inventoryFriendEquipment.getChestItem() != null && inventoryFriendEquipment.getChestItem().getItem() instanceof ItemArmor) {
            inventoryFriendEquipment.getChestItem().damageItem((int) pDamage, this);
        }
        if (inventoryFriendEquipment.getheadItem() != null && inventoryFriendEquipment.getheadItem().getItem() instanceof ItemArmor) {
            inventoryFriendEquipment.getheadItem().damageItem((int) pDamage, this);
        }
        if (inventoryFriendEquipment.getLegItem() != null && inventoryFriendEquipment.getLegItem().getItem() instanceof ItemArmor) {
            inventoryFriendEquipment.getLegItem().damageItem((int) pDamage, this);
        }
    }

    /*
     * 右クリック時の処理
     */
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {

        ItemStack stack = player.getHeldItem(hand);

        if (this.isTamed()) {
            if (!stack.isEmpty()) {
                //デバッグ用
                if (this.isOwner(player) && stack.getItem() == Items.STICK) {

                    float i = friendPoint;
                    String s = String.valueOf(i);

                    player.sendStatusMessage(new TextComponentTranslation(s + "exp"), true);

                    return true;
                } else if (this.isOwner(player) && isHealItem(stack)) {
                    ItemFood itemfood = (ItemFood) stack.getItem();

                    if (this.getHealth() < this.getMaxHealth()) {
                        if (!player.capabilities.isCreativeMode) {
                            stack.shrink(1);
                        }

                        this.heal((float) itemfood.getHealAmount(stack));
                        this.playSound(SoundEvents.ENTITY_GENERIC_EAT, this.getSoundVolume(), this.getSoundPitch());
                        eattick = 20;

                        for (int i = 0; i < 7; ++i) {

                            double d0 = this.rand.nextGaussian() * 0.02D;
                            double d1 = this.rand.nextGaussian() * 0.02D;
                            double d2 = this.rand.nextGaussian() * 0.02D;

                            this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
                        }

                        return true;
                    }
                } else if (this.isOwner(player) && stack.getItem() == JapariItems.wildliberationpotion) {

                    if (this.getLimit() < this.maxLimit()) {
                        ItemFood itemfood = (ItemFood) stack.getItem();

                        if (!player.capabilities.isCreativeMode) {
                            stack.shrink(1);
                            player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
                        }

                        this.heal((float) itemfood.getHealAmount(stack));
                        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getMaxHealth() + 1.0D + rand.nextInt(2));
                        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() + 0.2D);

                        this.playSound(SoundEvents.ENTITY_GENERIC_DRINK, this.getSoundVolume(), this.getSoundPitch());
                        eattick = 20;

                        this.setLimit(this.getLimit() + 1);

                        for (int i = 0; i < 7; ++i) {

                            double d0 = this.rand.nextGaussian() * 0.02D;
                            double d1 = this.rand.nextGaussian() * 0.02D;
                            double d2 = this.rand.nextGaussian() * 0.02D;

                            JapariCraftMod.proxy.spawnParticle(JapariParticleTypes.SANDSTAR, world, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, d0, d1, d2);
                        }
                    } else {
                        player.sendStatusMessage(new TextComponentTranslation("friends.limit"), true);
                        this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, this.getSoundVolume(), this.getSoundPitch());
                    }


                    return true;

                }
            }

            if (player.isSneaking() && !this.isSitting()) {
                player.openGui(JapariCraftMod.instance, JapariCraftMod.ID_JAPARI_INVENTORY, this.getEntityWorld(), this.getEntityId(), 0, 0);
                if (!this.world.isRemote) {

                    this.aiSit.setSitting(!this.isSitting());
                }

                return true;
            } else if (this.isOwner(player) && !this.world.isRemote) {

                this.aiSit.setSitting(!this.isSitting());

                return true;
            }

        } else if (!this.isTamed() && isHealItem(stack)) {

            if (!player.capabilities.isCreativeMode) {

                stack.setCount(stack.getCount() - 1);

            }

            if (!this.world.isRemote) {

                if (this.rand.nextInt(3) == 0) {

                    this.setTamed(true);
                    this.setOwnerId(player.getUniqueID());

                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte) 7);

                    //ここで実績を解除させる

                    AchievementsJapari.grantAdvancement(player, "tame_friends");

                } else {

                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte) 6);
                }
            }
            return true;
        }
        return super.processInteract(player, hand);
    }


    public void onLivingUpdate() {
        if (this.world.isRemote) {
            this.eattick = Math.max(0, this.eattick - 1);
        }

        super.onLivingUpdate();
        this.updateArmSwingProgress();
        if (!world.isRemote) {
            if (this.isTamed() && this.isEntityAlive()) {
                pickupItem();
            }
        }
        //やばい時はじゃぱりまんを食べる
        if (getHealth() < getMaxHealth() / 1.4 && ticksExisted % 20 == 0) {
            eatJapariman();
        }


        if (friendPoint >= 160) {
            if (this.getLimit() < this.maxLimit()) {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getMaxHealth() + 1.0D + rand.nextInt(3));
                this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() + 0.2D);
                this.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, this.getSoundVolume(), 1.2F);
                friendPoint = 0;
                dataManager.set(EntityFriend.dataEXPValue, friendPoint);

                this.setLimit(this.getLimit() + 1);

                for (int i = 0; i < 7; ++i) {

                    JapariCraftMod.proxy.spawnParticle(JapariParticleTypes.SANDSTAR, world, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width);
                }
            }
        }
    }

    //インベントリにじゃぱりまんがあるか確認する処理
    private void eatJapariman() {
        ItemStack itemstack = findFood();

        if (!itemstack.isEmpty()) {
            //じゃぱりまんがあるか確認
            ItemFood itemfood = (ItemFood) itemstack.getItem();
            this.heal((float) itemfood.getHealAmount(itemstack));
            itemstack.shrink(1);
            this.playSound(SoundEvents.ENTITY_GENERIC_EAT, this.getSoundVolume(), this.getSoundPitch());
            eattick = 20;
        }
    }

    /**
     * インベントリ内の食べれる食べ物を探す.
     */
    private ItemStack findFood() {
        ItemStack friendsstack;

        ItemStack friendsOffHandStack;
        friendsOffHandStack = getInventoryFriendEquipment().getOffhandItem();

        for (int i = 0; i < this.getInventoryFriendMain().getSizeInventory(); ++i) {
            friendsstack = getInventoryFriendMain().getStackInSlot(i);

            if (isHealItem(friendsstack)) {
                return friendsstack;
            } else if (isHealItem(friendsOffHandStack)) {
                return friendsOffHandStack;
            }
        }
        return ItemStack.EMPTY;
    }

    //フレンズを回復できるアイテムをここで指定
    public boolean isHealItem(ItemStack stack) {
        return Heal_ITEMS.contains(stack.getItem());
    }

    private void pickupItem() {
        if (ticksExisted % 10 != 0) {
            return;
        }

        for (EntityItem entityItem : world.getEntitiesWithinAABB(EntityItem.class, getEntityBoundingBox().grow(0.65D))) {
            if (entityItem.isEntityAlive() && entityItem.onGround) {
                ItemStack stack = entityItem.getItem();

                if (!stack.isEmpty()) {
                    stack = onItemStackPickup(stack);

                    if (stack.isEmpty()) {
                        entityItem.setDead();

                        playPickupSound();
                    } else {
                        entityItem.setItem(stack);
                    }
                }
                break;
            }
        }
    }

    public ItemStack onItemStackPickup(ItemStack stack) {
        return getInventoryFriendMain().addItem(stack);
    }

    protected void playPickupSound() {
        playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.25F, 0.85F);
    }


    public InventoryFriendMain getInventoryFriendMain() {
        if (this.inventoryFriendMain == null) {
            this.inventoryFriendMain = new InventoryFriendMain(this);
        }

        return this.inventoryFriendMain;
    }

    public InventoryFriendEquipment getInventoryFriendEquipment() {
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
            case HEAD:

                itemStack = this.getInventoryFriendEquipment().getheadItem();
                break;
            case LEGS:

                itemStack = this.getInventoryFriendEquipment().getLegItem();
                break;
            case OFFHAND:

                itemStack = this.getInventoryFriendEquipment().getOffhandItem();
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
            case HEAD:

                this.getInventoryFriendEquipment().setInventorySlotContents(2, stack);
                break;
            case LEGS:

                this.getInventoryFriendEquipment().setInventorySlotContents(3, stack);
                break;
            case OFFHAND:

                this.getInventoryFriendEquipment().setInventorySlotContents(4, stack);
                break;
        }
    }

    //死んだ時に持ってるアイテム落とす
    @Override
    public void onDeath(DamageSource cause) {
        World world = this.getEntityWorld();

        if (!world.isRemote) {
            InventoryHelper.dropInventoryItems(world, this, this.getInventoryFriendMain());

            InventoryHelper.dropInventoryItems(world, this, this.getInventoryFriendEquipment());
        }

        super.onDeath(cause);
    }

    //ここから下は体力バーの表示方法
    public EntityFriend.Condition getCondition() {
        int health = (int) this.getHealth();
        int healthMax = (int) this.getMaxHealth();

        EntityFriend.Condition condition = EntityFriend.Condition.FINE;

        if (health < (healthMax / 2)) {
            condition = EntityFriend.Condition.HURT;

            if (health < (healthMax / 4)) {
                condition = EntityFriend.Condition.DYING;
            }
        }

        return condition;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);

            if (!entityIn.isNonBoss()) {
                addExperience(4 + this.rand.nextInt(5));
            } else {
                addExperience(1 + this.rand.nextInt(3));
            }
        }

        return flag;
    }

    /**
     * バニラから引用、事故がないように
     */
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
        if (!(target instanceof EntityGhast)) {
            if (target instanceof EntityFriend) {
                //fighting? no way!
                return false;
            }
            if (target instanceof EntityTameable) {
                EntityTameable entityTameable = (EntityTameable) target;
                if (entityTameable.isTamed() && entityTameable.getOwner() == owner) {
                    return false;
                }
            }
            if (target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target)) {
                return false;
            } else {
                return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
            }
        } else {
            return true;
        }
    }

    @Override
    protected void updateAITasks() {
        if (this.ticksExisted % 10 == 0) {
            this.heal(0.08F);
        }
        super.updateAITasks();

        this.setAttacking(this.getAttackTarget() != null);
    }

    @SideOnly(Side.CLIENT)
    protected boolean isAttacking() {
        return this.dataManager.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.dataManager.set(ATTACKING, attacking);
    }

    @SideOnly(Side.CLIENT)
    public int getEatingTick() {
        return eattick;
    }


    /*
     * GUI用、体力がどれくらいあるかで色が変わる
     */
    public enum Condition {

        FINE,
        HURT,
        DYING,

    }

    @SideOnly(Side.CLIENT)
    public EntityFriend.ArmPose getArmPose() {
        if (this.isAttacking()) {
            return EntityFriend.ArmPose.ATTACKING;
        } else {
            return EntityFriend.ArmPose.NORMAL;
        }
    }

    @SideOnly(Side.CLIENT)
    public enum ArmPose {
        NORMAL,
        ATTACKING,
        EATING,
        BOW_AND_ARROW
    }
}
