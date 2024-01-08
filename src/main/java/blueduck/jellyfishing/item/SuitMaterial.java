package blueduck.jellyfishing.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class SuitMaterial implements ArmorMaterial {
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private static final int[] ARMOR_ARRAY = new int[]{3, 5, 7, 3};



    @Override
    public int getDurabilityForType(ArmorItem.Type slot) {
        return MAX_DAMAGE_ARRAY[slot.getSlot().getIndex()] * 33;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type slot) {
        return ARMOR_ARRAY[slot.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Items.IRON_INGOT);
    }

    @Override
    public String getName() {
        return "jellyfishing:air_suit";
    }


    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}