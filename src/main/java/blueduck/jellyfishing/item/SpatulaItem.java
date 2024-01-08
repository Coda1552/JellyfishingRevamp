package blueduck.jellyfishing.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SpatulaItem extends SwordItem {
    public SpatulaItem(Properties pProperties, Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        double dir = (pTarget.getDeltaMovement().y() > 0) ? .5 : -0.5;
        pTarget.setDeltaMovement(pTarget.getDeltaMovement().x(),pTarget.getDeltaMovement().y() + dir, pTarget.getDeltaMovement().z());
        pTarget.fallDistance += dir < 0 ? 4 : 0;
        return true;
    }

    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return pState.is(BlockTags.SWORD_EFFICIENT) || pState.is(BlockTags.MINEABLE_WITH_SHOVEL) ? 2.0F : 1.0F;
        }
    }
    public boolean isCorrectToolForDrops(BlockState pBlock) {
        return pBlock.is(BlockTags.MINEABLE_WITH_SHOVEL) ||pBlock.is(BlockTags.SWORD_EFFICIENT);
    }

}
