package com.japaricraft.japaricraftmod.mob;

import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class EntitySandStarHandler extends EntitySpellcasterIllager {
    private EntitySheep wololoTarget;

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(JapariCraftMod.MODID, "entitys/sandstarhandler");
    public EntitySandStarHandler(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.experienceValue = 12;
    }

    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new AICastingSpell());
        this.tasks.addTask(2, new EntityAIAvoidEntity<>(this, EntityPlayer.class, 8.0F, 0.6D, 1.0D));
        this.tasks.addTask(4, new AISummonVexSpell());
        this.tasks.addTask(5, new AIAttackSpell());
        this.tasks.addTask(5, new AIHealingSpell());
        this.tasks.addTask(8, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntitySandStarHandler.class));
        this.targetTasks.addTask(2, (new EntityAINearestAttackableTarget(this, EntityPlayer.class, true)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, (new EntityAINearestAttackableTarget(this, EntityVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, false));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1D);
    }

    protected void entityInit() {
        super.entityInit();
    }

    public static void registerFixesEvoker(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntitySandStarHandler.class);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    protected void updateAITasks() {
        super.updateAITasks();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        super.onUpdate();
    }

    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
    public boolean isOnSameTeam(Entity entityIn) {
        if (entityIn == null) {
            return false;
        } else if (entityIn == this) {
            return true;
        } else if (super.isOnSameTeam(entityIn)) {
            return true;
        } else if (entityIn instanceof EntityVex) {
            return this.isOnSameTeam(((EntityVex) entityIn).getOwner());
        } else
            return entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getCreatureAttribute() == EnumCreatureAttribute.ILLAGER && this.getTeam() == null && entityIn.getTeam() == null;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_EVOCATION_ILLAGER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.EVOCATION_ILLAGER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.ENTITY_EVOCATION_ILLAGER_HURT;
    }

    private void setWololoTarget(@Nullable EntitySheep p_190748_1_) {
        this.wololoTarget = p_190748_1_;
    }

    @Nullable
    private EntitySheep getWololoTarget() {
        return this.wololoTarget;
    }

    protected SoundEvent getSpellSound() {
        return SoundEvents.EVOCATION_ILLAGER_CAST_SPELL;
    }

    protected ResourceLocation getLootTable()
    {
        return LOOT_TABLE;
    }
    class AIAttackSpell extends AIUseSpell {
        private AIAttackSpell() {
            super();
        }

        protected int getCastingTime() {
            return 40;
        }

        protected int getCastingInterval() {
            return 100;
        }

        protected void castSpell() {
            EntityLivingBase entitylivingbase = EntitySandStarHandler.this.getAttackTarget();
            assert entitylivingbase != null;
            double d0 = Math.min(entitylivingbase.posY, EntitySandStarHandler.this.posY);
            double d1 = Math.max(entitylivingbase.posY, EntitySandStarHandler.this.posY) + 1.0D;
            float f = (float) MathHelper.atan2(entitylivingbase.posZ - EntitySandStarHandler.this.posZ, entitylivingbase.posX - EntitySandStarHandler.this.posX);

            if (EntitySandStarHandler.this.getDistanceSq(entitylivingbase) < 9.0D) {
                for (int i = 0; i < 5; ++i) {
                    float f1 = f + (float) i * (float) Math.PI * 0.4F;
                    this.spawnFangs(EntitySandStarHandler.this.posX + (double) MathHelper.cos(f1) * 1.5D, EntitySandStarHandler.this.posZ + (double) MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0);
                }

                for (int k = 0; k < 8; ++k) {
                    float f2 = f + (float) k * (float) Math.PI * 2.0F / 8.0F + ((float) Math.PI * 2F / 5F);
                    this.spawnFangs(EntitySandStarHandler.this.posX + (double) MathHelper.cos(f2) * 2.5D, EntitySandStarHandler.this.posZ + (double) MathHelper.sin(f2) * 2.5D, d0, d1, f2, 3);
                }
            } else {
                for (int l = 0; l < 16; ++l) {
                    double d2 = 1.25D * (double) (l + 1);
                    int j;
                    j = l;
                    this.spawnFangs(EntitySandStarHandler.this.posX + (double) MathHelper.cos(f) * d2, EntitySandStarHandler.this.posZ + (double) MathHelper.sin(f) * d2, d0, d1, f, j);
                }
            }
        }

        private void spawnFangs(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_) {
            BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
            boolean flag = false;
            double d0 = 0.0D;

            while (true) {
                if (!EntitySandStarHandler.this.world.isBlockNormalCube(blockpos, true) && EntitySandStarHandler.this.world.isBlockNormalCube(blockpos.down(), true)) {
                    if (!EntitySandStarHandler.this.world.isAirBlock(blockpos)) {
                        IBlockState iblockstate = EntitySandStarHandler.this.world.getBlockState(blockpos);
                        AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(EntitySandStarHandler.this.world, blockpos);

                        if (axisalignedbb != null) {
                            d0 = axisalignedbb.maxY;
                        }
                    }

                    flag = true;
                    break;
                }

                blockpos = blockpos.down();

                if (blockpos.getY() < MathHelper.floor(p_190876_5_) - 1) {
                    break;
                }
            }

            if (flag) {
                EntityEvokerFangs SandStarHandlerfangs = new EntityEvokerFangs(EntitySandStarHandler.this.world, p_190876_1_, (double) blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, EntitySandStarHandler.this);
                EntitySandStarHandler.this.world.spawnEntity(SandStarHandlerfangs);
            }
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOCATION_ILLAGER_PREPARE_ATTACK;
        }

        protected SpellType getSpellType() {
            return SpellType.FANGS;
        }
    }

    class AICastingSpell extends AICastingApell {
        private AICastingSpell() {
            super();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {
            if (EntitySandStarHandler.this.getAttackTarget() != null) {
                EntitySandStarHandler.this.getLookHelper().setLookPositionWithEntity(EntitySandStarHandler.this.getAttackTarget(), (float) EntitySandStarHandler.this.getHorizontalFaceSpeed(), (float) EntitySandStarHandler.this.getVerticalFaceSpeed());
            } else if (EntitySandStarHandler.this.getWololoTarget() != null) {
                EntitySandStarHandler.this.getLookHelper().setLookPositionWithEntity(EntitySandStarHandler.this.getWololoTarget(), (float) EntitySandStarHandler.this.getHorizontalFaceSpeed(), (float) EntitySandStarHandler.this.getVerticalFaceSpeed());
            }
        }
    }

    class AISummonVexSpell extends AIUseSpell {
        private AISummonVexSpell() {
            super();
        }


        public boolean shouldExecute() {
            if (!super.shouldExecute()) {
                return false;
            } else {
                int i = EntitySandStarHandler.this.world.getEntitiesWithinAABB(EntityVex.class, EntitySandStarHandler.this.getEntityBoundingBox().grow(16.0D)).size();
                return EntitySandStarHandler.this.rand.nextInt(8) + 1 > i;
            }
        }

        protected int getCastingTime() {
            return 90;
        }

        protected int getCastingInterval() {
            return 340;
        }

        protected void castSpell() {
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos = (new BlockPos(EntitySandStarHandler.this)).add(-2 + EntitySandStarHandler.this.rand.nextInt(5), 1, -2 + EntitySandStarHandler.this.rand.nextInt(5));
                EntityVex entityvex = new EntityVex(EntitySandStarHandler.this.world);
                entityvex.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
                entityvex.onInitialSpawn(EntitySandStarHandler.this.world.getDifficultyForLocation(blockpos), null);
                entityvex.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.WOODEN_SWORD));
                entityvex.setOwner(EntitySandStarHandler.this);
                entityvex.setBoundOrigin(blockpos);
                entityvex.setLimitedLife(20 * (30 + EntitySandStarHandler.this.rand.nextInt(90)));
                EntitySandStarHandler.this.world.spawnEntity(entityvex);
            }
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.ENTITY_ILLAGER_PREPARE_MIRROR;
        }

        protected SpellType getSpellType() {
            return SpellType.SUMMON_VEX;
        }
    }

    class AIHealingSpell extends AIUseSpell {
        private AIHealingSpell() {
            super();
        }

        public boolean shouldExecute() {
            if (EntitySandStarHandler.this.isSpellcasting()) {
                return false;
            } else if (EntitySandStarHandler.this.ticksExisted < this.spellCooldown) {
                return false;
            } else if (getHealth() < 40) ;
            {
                return true;
            }
        }

        protected int getCastingTime() {
            return 50;
        }

        protected int getCastingInterval() {
            return 200;
        }

        protected void castSpell() {
            EntitySandStarHandler.this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE;
        }

        protected SpellType getSpellType() {
            return SpellType.DISAPPEAR;
        }
    }

}