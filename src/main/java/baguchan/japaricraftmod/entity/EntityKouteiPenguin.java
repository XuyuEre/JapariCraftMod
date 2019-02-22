package baguchan.japaricraftmod.entity;

import baguchan.japaricraftmod.entity.ai.EntityAIFollowSwimOwner;
import baguchan.japaricraftmod.init.JapariEntity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.AbstractGroupFish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityKouteiPenguin extends EntitySwimFriend {
    public EntityKouteiPenguin(World p_i48574_2_) {
        super(JapariEntity.KOUTEI_PENGUIN, p_i48574_2_);
        this.setSize(0.59F, 1.7F);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, true));
        this.tasks.addTask(3, new EntityAIFollowSwimOwner(this, 1.1D, 10.0F, 2.0F));
        this.tasks.addTask(4, new AIGoToAir(this, 1.2D));
        this.tasks.addTask(5, new AITravel(this, 1.2D));
        this.tasks.addTask(6, new AIWander(this, 1.0D));
        //this.tasks.addTask(7, new EntityAIServalBeg(this, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, AbstractGroupFish.class, false, null));
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }


}
