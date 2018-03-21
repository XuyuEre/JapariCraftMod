package com.japaricraft.japaricraftmod.mob;

import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityDragonFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityEnderCerulean extends EntityMob implements IRangedAttackMob {
    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(JapariCraftMod.MODID, "entitys/endercerulean");
    public int deathTicks;
    private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS));
    private static final DataParameter<Integer> INVULNERABILITY_TIME = EntityDataManager.<Integer>createKey(EntityEnderCerulean.class, DataSerializers.VARINT);

    public EntityEnderCerulean(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.7F);
        this.isImmuneToFire = true;
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityEnderCerulean.AIDoNothing());
        this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 50, 20.0F));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityFriend.class, false));
    }

    protected float getSoundVolume() {
        return 1.1F;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.8F;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(INVULNERABILITY_TIME, 0);
    }

    protected void updateAITasks() {
        if (this.getInvulTime() > 0) {
            int j1 = this.getInvulTime() - 1;

            if (j1 <= 0) {
                this.world.newExplosion(this, this.posX, this.posY + (double) this.getEyeHeight(), this.posZ, 2.0F, false, this.world.getGameRules().getBoolean("mobGriefing"));
            }

            this.setInvulTime(j1);

            if (this.ticksExisted % 10 == 0) {
                this.heal(10.0F);
            }
        } else {
            super.updateAITasks();
            if (this.ticksExisted % 20 == 0) {
                this.heal(0.5F);
            }

            this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        }
    }


    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Invul", this.getInvulTime());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setInvulTime(compound.getInteger("Invul"));

        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    /**
     * Sets the custom name tag for this entity
     */
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30D);
    }

    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    //敵（プレイヤー）の登録解除
    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void onLivingUpdate() {
        if (this.world.isRemote) {
            for (int i = 0; i < 3; ++i) {
                this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        //when get attacktaget
        //もしアタックターゲットを見つけたら
        EntityLivingBase target = this.getAttackTarget();
        if (target instanceof EntityPlayer) {
            if (getInvulTime() == 230) {
                ((EntityPlayer) target).sendStatusMessage(new TextComponentTranslation("entity.cerulean.text1"), false);
            }
            if (getInvulTime() == 170) {
                ((EntityPlayer) target).sendStatusMessage(new TextComponentTranslation("entity.cerulean.text2"), false);
            }
            if (getInvulTime() == 90) {
                ((EntityPlayer) target).sendStatusMessage(new TextComponentTranslation("entity.cerulean.text3"), false);
            }
            if (getInvulTime() == 10) {
                ((EntityPlayer) target).sendStatusMessage(new TextComponentTranslation("entity.cerulean.text4"), false);
            }
            if (!this.onGround && this.motionY < 0.0D) {
                this.motionY *= 0.6D;
            }
            if (!isOnLadder() && this.getAttackTarget() != null && this.getInvulTime() <= 0) {
                double a = this.getAttackTarget().posX - posX;
                double b = this.getAttackTarget().posZ - posZ;
                double d3 = a * a + b * b;
                d3 = (double) MathHelper.sqrt(d3);

                if ((this.getAttackTarget().posY + 3 > posY)) {
                    this.motionY += 0.09F;
                }
                if (!onGround) {
                    double d1 = getAttackTarget().posX - this.posX;
                    double d2 = getAttackTarget().posZ - this.posZ;

                    this.motionX += a / d3 * 0.078D * this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                    this.motionZ += b / d3 * 0.078D * this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                    this.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
                    this.renderYawOffset = this.rotationYaw;
                }
            }
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

        this.setInvulTime(260);
        this.setHealth(this.getMaxHealth() / 3.0F);
        return livingdata;
    }

    @Override
    public void damageEntity(DamageSource source, float amount) {
        super.damageEntity(source, amount);
        this.bossInfo.setPercent(getHealth() / getMaxHealth());
    }

    public int getInvulTime() {
        return this.dataManager.get(INVULNERABILITY_TIME);
    }

    public void setInvulTime(int time) {
        this.dataManager.set(INVULNERABILITY_TIME, time);
    }


    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        if (this.getInvulTime() <= 0) {
            double d1 = target.posX - this.posX;
            double d2 = target.getEntityBoundingBox().minY + (double) (target.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
            double d3 = target.posZ - this.posZ;
            double d0 = this.getDistanceSq(target);
            float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
            if ((this.getHealth() < 100)) {
                if (this.rand.nextInt(6) == 0) {
                    for (int i = 0; i < 3; ++i) {
                        EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.world, this, d1 + this.getRNG().nextGaussian() * (double) f, d2, d3 + this.getRNG().nextGaussian() * (double) f);
                        entitysmallfireball.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                        this.world.spawnEntity(entitysmallfireball);
                    }
                } else {

                    if (this.rand.nextInt(4) == 0) {
                        for (int i = 0; i < 2; ++i) {
                            EntityCeruleanEye eye = new EntityCeruleanEye(world);
                            eye.setPosition(this.posX, this.posY, this.posZ);
                            eye.setHealth(16);
                            this.world.spawnEntity(eye);
                        }
                    } else {
                        for (int i = 0; i < 2; ++i) {
                            EntityWitherSkull entitysmallfireball = new EntityWitherSkull(this.world, this, d1 + this.getRNG().nextGaussian() * (double) f, d2, d3 + this.getRNG().nextGaussian() * (double) f);
                            entitysmallfireball.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                            this.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }
            } else {
                if (this.rand.nextInt(7) == 0) {
                    EntityDragonFireball entitysmallfireball = new EntityDragonFireball(this.world, this, d1 + this.getRNG().nextGaussian() * (double) f, d2, d3 + this.getRNG().nextGaussian() * (double) f);
                    entitysmallfireball.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                    this.world.spawnEntity(entitysmallfireball);
                } else {
                    EntityLargeFireball entityfireball = new EntityLargeFireball(this.world, this, d1 + this.getRNG().nextGaussian() * (double) f, d2, d3 + this.getRNG().nextGaussian() * (double) f);
                    entityfireball.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                    this.world.spawnEntity(entityfireball);
                }
            }
        }
    }

    @Override
    protected void onDeathUpdate() {
        ++this.deathTicks;
        float f = (this.rand.nextFloat() - 0.5F) * 2.0F;
        float f1 = (this.rand.nextFloat() - 0.5F) * 1.0F;
        float f2 = (this.rand.nextFloat() - 0.5F) * 2.0F;

        if (this.deathTicks >= 100 && this.deathTicks <= 160) {
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + (double) f, this.posY + 2.0D + (double) f1, this.posZ + (double) f2, 0.0D, 0.0D, 0.0D);
        }

        boolean flag = this.world.getGameRules().getBoolean("doMobLoot");
        int i = 100;


        if (this.deathTicks >= 40 && this.deathTicks <= 90 && this.ticksExisted % 5 == 0) {

            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + (double) f, this.posY + 2.0D + (double) f1, this.posZ + (double) f2, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isRemote) {

            if (this.deathTicks == 1) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.4F, this.getSoundPitch());
            }
            if (this.deathTicks == 40) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.4F, this.getSoundPitch());
            }
            if (this.deathTicks == 50) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.4F, this.getSoundPitch());
            }
            if (this.deathTicks == 60) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.4F, this.getSoundPitch());
            }
            if (this.deathTicks == 70) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.4F, this.getSoundPitch());
            }
            if (this.deathTicks == 80) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.4F, this.getSoundPitch());
            }
            if (this.deathTicks == 160) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED, 3.0F, this.getSoundPitch());
            }
        }

        this.move(MoverType.SELF, 0.0D, 0.05D, 0.0D);
        this.rotationYaw += 20.0F;
        this.renderYawOffset = this.rotationYaw;

        if (this.deathTicks == 160 && !this.world.isRemote) {
            if (flag) {
                this.dropExperience(MathHelper.floor((float) i));
            }


            this.setDead();
        }
    }

    private void dropExperience(int p_184668_1_) {
        while (p_184668_1_ > 0) {
            int i = EntityXPOrb.getXPSplit(p_184668_1_);
            p_184668_1_ -= i;
            this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, i));
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        }

        if (source == DamageSource.DRAGON_BREATH) {
            amount = 0F;
        }
        if (source == DamageSource.ON_FIRE) {
            amount = 0F;
        }
        if (source == DamageSource.IN_FIRE) {
            amount = 0F;
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }

    public boolean canDespawn() {
        return false;
    }

    public class AIDoNothing extends EntityAIBase {
        public AIDoNothing() {
            this.setMutexBits(7);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            return EntityEnderCerulean.this.getInvulTime() > 0;
        }
    }
}