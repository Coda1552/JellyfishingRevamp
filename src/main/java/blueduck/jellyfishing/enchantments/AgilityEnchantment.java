package blueduck.jellyfishing.enchantments;

import blueduck.jellyfishing.item.JellyfishNetItem;
import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AgilityEnchantment extends Enchantment {
    public AgilityEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }


    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof JellyfishNetItem ? true : super.canEnchant(pStack);
    }

    public int getMinCost(int pEnchantmentLevel) {
        return 10;
    }

    public int getMaxCost(int pEnchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }
}
