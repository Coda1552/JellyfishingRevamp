package blueduck.jellyfishing.item;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class KelpMustacheItem extends ArmorItem {

    public KelpMustacheItem(ArmorMaterial materialIn, Properties builder) {
        super(materialIn, Type.HELMET, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (world.getRandom().nextDouble() < 0.025) {
            stack.hurtAndBreak(1, player, (p_213360_0_) -> {
                p_213360_0_.broadcastBreakEvent(EquipmentSlot.HEAD);
            });
        }
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10, 0));
    }

}
