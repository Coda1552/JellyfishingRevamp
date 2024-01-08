package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.enchantments.AgilityEnchantment;
import blueduck.jellyfishing.item.JellyfishNetItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {

    public static EnchantmentCategory NET = EnchantmentCategory.create("jellyfishing:jellyfish_net", (Item p_44771_) -> {
        return p_44771_ instanceof JellyfishNetItem; });
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Jellyfishing.MOD_ID);

    public static final RegistryObject<Enchantment> AGILITY = ENCHANTMENTS.register("agility", () -> new AgilityEnchantment(Enchantment.Rarity.UNCOMMON, NET, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));


}
