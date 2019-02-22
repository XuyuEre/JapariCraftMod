package baguchan.japaricraftmod.entity;

import baguchan.japaricraftmod.entity.ai.*;
import baguchan.japaricraftmod.init.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.datasync.*;
import net.minecraft.pathfinding.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.*;


public class EntityServal extends EntityFriend {
    private static final DataParameter<Boolean> BEGGING = EntityDataManager.createKey(EntityServal.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean> STRETCHING = EntityDataManager.createKey(EntityServal.class, DataSerializers.BOOLEAN);

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
        this.tasks.addTask(2, new EntityAIAttackSweep(this, 1.16D, true));
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.1D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.9D));
        //this.tasks.addTask(7, new EntityAIServalBeg(this, 8.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, AbstractGroupFish.class, false, null));

    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(BEGGING, Boolean.FALSE);
        this.dataManager.register(STRETCHING, Boolean.FALSE);
    }


    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.29F);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }


    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeAdditional(compound);
        compound.setBoolean("Stretching", this.isStretching());
    }


    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readAdditional(compound);
        this.setStretching(compound.getBoolean("Stretching"));
    }

    @Override
    public void tick() {
        super.tick();
        this.headRotationCourseOld = this.headRotationCourse;

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
        if (this.isStretching()) {
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

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {

        if (source == DamageSource.FALL) {

            damage *= 0.4F;

        }

        return super.attackEntityFrom(source, damage);
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