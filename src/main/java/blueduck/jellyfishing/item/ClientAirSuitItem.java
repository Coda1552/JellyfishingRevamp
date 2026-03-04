package blueduck.jellyfishing.item;

import blueduck.jellyfishing.client.ClientReference;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ClientAirSuitItem extends AirSuitItem implements IClientItemExtensions  {
    public ClientAirSuitItem(ArmorMaterial armorMaterial, Type equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer)
    {
        consumer.accept(ClientReference.INSTANCE);
    }


    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (slot.equals(EquipmentSlot.HEAD)) {
            return "jellyfishing:textures/models/armor/air_suit_helmet.png";
        }
        if (slot.equals(EquipmentSlot.CHEST)) {
            return "jellyfishing:textures/models/armor/air_suit_chest.png";
        }
        if (slot.equals(EquipmentSlot.LEGS)) {
            return "jellyfishing:textures/models/armor/air_suit_legs.png";
        }
        if (slot.equals(EquipmentSlot.FEET)) {
            return "jellyfishing:textures/models/armor/air_suit_boots.png";
        }
        return null;
    }
}
