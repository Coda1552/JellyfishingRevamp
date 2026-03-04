package blueduck.jellyfishing.enchantments;

import blueduck.jellyfishing.item.JellyfishNetItem;
import blueduck.jellyfishing.item.NetJellyfishItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PlunderingEnchantment extends Enchantment {
    public PlunderingEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }


    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof JellyfishNetItem || pStack.getItem() instanceof NetJellyfishItem ? true : super.canEnchant(pStack);
    }

    public int getMinCost(int pEnchantmentLevel) {
        return 10 + 20 * (pEnchantmentLevel - 1);
    }

    public int getMaxCost(int pEnchantmentLevel) {
        return getMinCost(pEnchantmentLevel) + 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }
}
