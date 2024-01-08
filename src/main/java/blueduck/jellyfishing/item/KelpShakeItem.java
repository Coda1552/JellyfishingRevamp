package blueduck.jellyfishing.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class KelpShakeItem extends Item {

    public KelpShakeItem(Properties properties) {
        super(properties.stacksTo(16));
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        Player playerentity = entityLiving instanceof Player ? (Player)entityLiving : null;
        if (playerentity instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)playerentity, stack);
        }
        if (playerentity != null) {
            playerentity.awardStat(Stats.ITEM_USED.get(this));
            if (!playerentity.isCreative()) {
                stack.shrink(1);
            }
            playerentity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 4));
            playerentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 160, 0));
        }

        if (playerentity == null || !playerentity.isCreative()) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerentity != null) {
                playerentity.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }
}
