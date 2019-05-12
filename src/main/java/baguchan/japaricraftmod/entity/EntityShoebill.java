package baguchan.japaricraftmod.entity;

import baguchan.japaricraftmod.entity.ai.EntityAIAttackRangedFriendsBow;
import baguchan.japaricraftmod.init.JapariEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityShoebill extends EntityFriend implements IRangedAttackMob {
    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.createKey(EntityShoebill.class, DataSerializers.BOOLEAN);

    public EntityShoebill(World p_i48574_2_) {
        super(JapariEntity.SHOEBILL, p_i48574_2_);
        this.setSize(0.6F, 1.6F);
        this.moveHelper = new SniperMoveHelper(this);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    protected void initEntityAI() {
        super.initEntityAI();
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(2, new EntityAIAttackRangedFriendsBow<>(this, 1.0D, 20, 17.5F));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, false) {
            @Override
            public boolean shouldExecute() {
                return !(getHeldItemOffhand().getItem() instanceof net.minecraft.item.ItemBow) && super.shouldExecute();
            }
        });
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.1D, 10.0F, 2.0F) {
            @Override
            public boolean shouldExecute() {
                return !isPlayerSleeping() && super.shouldExecute();
            }
        });

        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.9D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SWINGING_ARMS, false);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.255F);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }


    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {

        ItemStack itemstack = this.hasArrow();
        if (!itemstack.isEmpty()) {
            ItemArrow itemarrow = (ItemArrow) (itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW);
            EntityArrow entityarrow = itemarrow.createArrow(world, itemstack, this);
            if (this.getHeldItemOffhand().getItem() instanceof net.minecraft.item.ItemBow)
                entityarrow = ((net.minecraft.item.ItemBow) this.getHeldItemOffhand().getItem()).customizeArrow(entityarrow);
            entityarrow.setEnchantmentEffectsFromEntity(this, distanceFactor);
            double d0 = target.posX - this.posX;
            double d1 = target.getBoundingBox().minY + (double) (target.height / 3.0F) - entityarrow.posY;
            double d2 = target.posZ - this.posZ;
            double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
            entityarrow.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
            this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            this.world.spawnEntity(entityarrow);
            this.getHeldItemOffhand().damageItem(1, this);
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, this.getHeldItemOffhand()) == 0) {
                itemstack.shrink(1);
            }
        }
    }


    private ItemStack hasArrow() {
        ItemStack friendsstack;

        for (int i = 0; i < this.getInventoryFriendMain().getSizeInventory(); ++i) {
            friendsstack = this.getInventoryFriendMain().getStackInSlot(i);

            if (isArrowItem(friendsstack)) {
                return friendsstack;
            } else {
                friendsstack = this.getHeldItemOffhand();

                if (isArrowItem(friendsstack)) {
                    return friendsstack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean isArrowItem(ItemStack stack) {
        return stack.getItem() instanceof ItemArrow;
    }


    @OnlyIn(Dist.CLIENT)
    public boolean isSwingingArms() {
        return this.dataManager.get(SWINGING_ARMS);
    }

    public void setSwingingArms(boolean swingingArms) {
        this.dataManager.set(SWINGING_ARMS, swingingArms);
    }

    private class SniperMoveHelper extends EntityMoveHelper {
        public SniperMoveHelper(EntityLiving entitylivingIn) {
            super(entitylivingIn);
        }

        public void tick() {
            if (this.action == EntityMoveHelper.Action.STRAFE) {
                float f = (float) this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
                float f1 = (float) this.speed * f;
                float f2 = this.moveForward;
                float f3 = this.moveStrafe;
                float f4 = MathHelper.sqrt(f2 * f2 + f3 * f3);
                if (f4 < 1.0F) {
                    f4 = 1.0F;
                }

                f4 = f1 / f4;
                f2 = f2 * f4;
                f3 = f3 * f4;
                float f5 = MathHelper.sin(this.entity.rotationYaw * ((float) Math.PI / 180F));
                float f6 = MathHelper.cos(this.entity.rotationYaw * ((float) Math.PI / 180F));
                float f7 = f2 * f6 - f3 * f5;
                float f8 = f3 * f6 + f2 * f5;
                PathNavigate pathnavigate = this.entity.getNavigator();
                if (pathnavigate != null) {
                    NodeProcessor nodeprocessor = pathnavigate.getNodeProcessor();
                    if (nodeprocessor != null && nodeprocessor.getPathNodeType(this.entity.world, MathHelper.floor(this.entity.posX + (double) f7), MathHelper.floor(this.entity.posY), MathHelper.floor(this.entity.posZ + (double) f8)) != PathNodeType.WALKABLE) {
                        this.moveForward = 0.0F;
                        this.moveStrafe = 0.0F;
                        f1 = f;
                    }
                }

                this.entity.setAIMoveSpeed(f1);
                this.entity.setMoveForward(this.moveForward);
                this.entity.setMoveStrafing(this.moveStrafe);
                this.action = EntityMoveHelper.Action.WAIT;
            } else if (this.action == EntityMoveHelper.Action.MOVE_TO) {
                this.action = EntityMoveHelper.Action.WAIT;
                double d0 = this.posX - this.entity.posX;
                double d1 = this.posZ - this.entity.posZ;
                double d2 = this.posY - this.entity.posY;
                double d3 = d0 * d0 + d2 * d2 + d1 * d1;
                if (d3 < (double) 2.5000003E-7F) {
                    this.entity.setMoveForward(0.0F);
                    return;
                }

                float f9 = (float) (MathHelper.atan2(d1, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, f9, 90.0F);
                this.entity.setAIMoveSpeed((float) (this.speed * this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
                if (d2 > (double) this.entity.stepHeight && d0 * d0 + d1 * d1 < (double) Math.max(1.0F, this.entity.width)) {
                    this.entity.getJumpHelper().setJumping();
                    this.action = EntityMoveHelper.Action.JUMPING;
                }
            } else if (this.action == EntityMoveHelper.Action.JUMPING) {
                this.entity.setAIMoveSpeed((float) (this.speed * this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
                if (this.entity.onGround) {
                    this.action = EntityMoveHelper.Action.WAIT;
                }
            } else {
                this.entity.setMoveForward(0.0F);
            }

        }
    }
}
