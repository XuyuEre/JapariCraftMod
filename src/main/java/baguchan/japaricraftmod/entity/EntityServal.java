package baguchan.japaricraftmod.entity;

import baguchan.japaricraftmod.entity.ai.EntityAIAttackDirect;
import baguchan.japaricraftmod.entity.ai.EntityAIAttackSweep;
import baguchan.japaricraftmod.init.JapariEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.AbstractGroupFish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class EntityServal extends EntityFriend {
    private static final DataParameter<Boolean> BEGGING = EntityDataManager.createKey(EntityServal.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean> STRETCHING = EntityDataManager.createKey(EntityServal.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> JUMPATTACK = EntityDataManager.createKey(EntityServal.class, DataSerializers.BOOLEAN);

    private float headRotationCourse;
    private float headRotationCourseOld;
    private boolean isStretching;

    public EntityServal(World p_i48574_2_) {
        super(JapariEntity.SERVAL, p_i48574_2_);
        this.setSize(0.6F, 1.6F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    protected void initEntityAI() {
        super.initEntityAI();
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(2, new EntityAIAttackDirect(this, 0.58F));
        this.tasks.addTask(3, new EntityAIAttackSweep(this, 1.16D, true));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.1D, 10.0F, 2.0F) {
            @Override
            public boolean shouldExecute() {
                return !isPlayerSleeping() && super.shouldExecute();
            }
        });

        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.9D));
        //this.tasks.addTask(7, new EntityAIServalBeg(this, 8.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, AbstractGroupFish.class, false, null));

    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(BEGGING, Boolean.FALSE);
        this.dataManager.register(STRETCHING, Boolean.FALSE);
        this.dataManager.register(JUMPATTACK, Boolean.FALSE);
    }


    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26F);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        double i = 1.0D;
        //when serval doing jampAttack,Increase the damage done
        if (this.isJumpAttack()) {
            i = 1.3D;
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue() * i));

        if (flag) {
            this.applyEnchantments(this, entityIn);

            if (!entityIn.isNonBoss()) {
                addExperience(3 + this.rand.nextInt(5));
            } else {
                addExperience(1 + this.rand.nextInt(3));
            }
        }

        return flag;
    }

    public void applyEntityCollision(Entity entityIn) {
        super.applyEntityCollision(entityIn);
        EntityLivingBase entityLivingBase = this.getAttackTarget();
        if (entityLivingBase != null) {
            if (this.isJumpAttack() && entityLivingBase == entityIn) {

                this.dealDamage((EntityLivingBase) entityIn);
            }
        }
    }

    protected void dealDamage(EntityLivingBase entityIn) {
        if (entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue() * 1.65F))) {
            this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.applyEnchantments(this, entityIn);
        }

    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }


    public void writeAdditional(NBTTagCompound compound) {
        super.writeAdditional(compound);
        compound.setBoolean("Stretching", this.isStretching());
    }


    public void readAdditional(NBTTagCompound compound) {
        super.readAdditional(compound);
        this.setStretching(compound.getBoolean("Stretching"));
    }

    private void updateSize() {
        float f;
        float f1;
        if (this.isPlayerSleeping()) {
            f = 0.2F;
            f1 = 0.2F;
        } else if (this.isJumpAttack()) {
            f = 0.6F;
            f1 = 0.6F;
        } else {
            f = 0.6F;
            f1 = 1.6F;
        }
        if (f != this.width || f1 != this.height) {
            AxisAlignedBB axisalignedbb = this.getBoundingBox();
            axisalignedbb = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) f, axisalignedbb.minY + (double) f1, axisalignedbb.minZ + (double) f);
            if (this.world.isCollisionBoxesEmpty(null, axisalignedbb)) {
                this.setSize(f, f1);
            }
        }

        if (f != this.width || f1 != this.height) {
            AxisAlignedBB axisalignedbb = this.getBoundingBox();
            axisalignedbb = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) f, axisalignedbb.minY + (double) f1, axisalignedbb.minZ + (double) f);
            if (this.world.isCollisionBoxesEmpty(null, axisalignedbb)) {
                this.setSize(f, f1);
            }
        }

    }

    @Override
    public void tick() {
        super.tick();
        this.headRotationCourseOld = this.headRotationCourse;

        this.updateSize();

        if (this.isBegging()) {
            this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
        } else {
            this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
        }

        if (!world.isRemote && !this.isInWater() && !this.isStretching() && this.getRNG().nextInt(600) == 0 && !this.isTamed() && (this.onGround && this.getAttackTarget() == null)) {
            setStretching(true);
        }
        if (!world.isRemote && this.isStretching() && (!this.isSitting() && this.isTamed() || this.isInWater() || this.getAttackTarget() != null || this.getRNG().nextInt(200) == 0 && !this.isTamed() || this.isTamed())) {
            setStretching(false);
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!this.isPlayerSleeping() && this.isStretching()) {
            this.getNavigator().clearPath();
        }
    }

    public boolean isStretching() {
        if (world.isRemote) {
            boolean isStretching = this.dataManager.get(STRETCHING);
            this.isStretching = isStretching;
            return isStretching;
        }
        return isStretching;
    }

    public void setStretching(boolean stretching) {
        this.dataManager.set(STRETCHING, stretching);
        if (!world.isRemote) {
            this.isStretching = stretching;
        }
    }

    public boolean isJumpAttack() {
        return this.dataManager.get(JUMPATTACK);
    }

    public void setJumpAttack(boolean jumping) {
        this.dataManager.set(JUMPATTACK, jumping);
    }

    @OnlyIn(Dist.CLIENT)
    public float getInterestedAngle(float p_70917_1_) {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * (float) Math.PI;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CAT_AMBIENT;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

    public Item getDropItem() {

        return null;//なにも落とさない
    }

    public void fall(float distance, float damageMultiplier) {
        int i = MathHelper.ceil((distance * 0.5F - 4.0F) * damageMultiplier);

        int i2 = MathHelper.ceil((distance * 0.5F - 6.0F) * damageMultiplier);
        if (!this.isJumpAttack()) {
            if (i > 0) {
                this.attackEntityFrom(DamageSource.FALL, (float) i);
            }
        } else {
            if (i2 > 0) {
                this.attackEntityFrom(DamageSource.FALL, (float) i2);
            }
        }
    }


    public boolean isBegging() {
        return this.dataManager.get(BEGGING);
    }

    public void setBegging(boolean beg) {
        this.dataManager.set(BEGGING, beg);
    }


    @Override
    public boolean canDespawn() {
        return false;
    }

}
