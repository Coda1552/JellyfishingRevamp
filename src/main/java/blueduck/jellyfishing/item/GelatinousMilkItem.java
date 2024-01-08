package blueduck.jellyfishing.item;

import net.minecraft.advancements.CriteriaTriggers;
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

public class GelatinousMilkItem extends Item {

    public static ItemStack MILK = new ItemStack(Items.MILK_BUCKET);
    public GelatinousMilkItem(Properties properties) {
        super(properties.stacksTo(16));
    }


    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        if (!worldIn.isClientSide) entityLiving.curePotionEffects(MILK);
        Player playerentity = entityLiving instanceof Player ? (Player)entityLiving : null;
        if (playerentity instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)playerentity, stack);
        }
        if (playerentity != null) {
            playerentity.awardStat(Stats.ITEM_USED.get(this));
            if (!playerentity.isCreative()) {
                stack.shrink(1);
            }

        }



        return stack;
    }
}
