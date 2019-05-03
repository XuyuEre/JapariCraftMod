package baguchan.japaricraftmod.entity;

import baguchan.japaricraftmod.entity.ai.EntityAIFollowSwimOwner;
import baguchan.japaricraftmod.init.JapariEntity;
import baguchan.japaricraftmod.init.JapariItems;
import com.google.common.collect.Sets;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.AbstractGroupFish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Set;

public class EntityOtter extends EntitySwimFriend {
    private static final Set<Item> Heal_ITEMS = Sets.newHashSet(JapariItems.JAPARIMAN, JapariItems.JAPARIMAN_APPLE, JapariItems.JAPARIMAN_COCOA, Items.COD, Items.SALMON, Items.TROPICAL_FISH, JapariItems.JAPARIMAN_KELP);

    public EntityOtter(World p_i48574_2_) {
        super(JapariEntity.OTTER, p_i48574_2_);
        this.setSize(0.59F, 1.7F);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.15D, true));
        this.tasks.addTask(3, new EntityAIFollowSwimOwner(this, 1.15D, 10.0F, 2.0F) {
            @Override
            public boolean shouldExecute() {
                return !isPlayerSleeping() && super.shouldExecute();
            }
        });
        this.tasks.addTask(4, new AIGoToAir(this, 1.2D));
        this.tasks.addTask(6, new AITravel(this, 1.2D));
        this.tasks.addTask(6, new AIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, AbstractGroupFish.class, true, null));
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.245D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public void updateSize() {
        float f;
        float f1;
        if (this.isPlayerSleeping()) {
            f = 0.2F;
            f1 = 0.2F;
        } else if (!this.isSwimming()) {
            f = 0.59F;
            f1 = 1.7F;

        } else {
            f = 0.6F;
            f1 = 0.6F;
        }

        if (f != this.width || f1 != this.height) {
            AxisAlignedBB axisalignedbb = this.getBoundingBox();
            axisalignedbb = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) f, axisalignedbb.minY + (double) f1, axisalignedbb.minZ + (double) f);
            if (this.world.isCollisionBoxesEmpty(null, axisalignedbb)) {
                this.setSize(f, f1);
            }
        }
    }

    public boolean isHealItem(ItemStack stack) {
        return Heal_ITEMS.contains(stack.getItem());
    }
}
