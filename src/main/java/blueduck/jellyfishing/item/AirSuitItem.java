package blueduck.jellyfishing.item;

import blueduck.jellyfishing.client.ClientReference;
import blueduck.jellyfishing.client.models.SuitModel;
import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Consumer;

public class AirSuitItem extends ArmorItem {



    public AirSuitItem(ArmorMaterial armorMaterial, ArmorItem.Type equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);

    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (hasAllPieces(player) && player.isInWater()) {
            player.setAirSupply(300);
        }
    }

    private static boolean hasAllPieces(LivingEntity player) {
        return (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.AIR_SUIT_HELMET.get() &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.AIR_SUIT_CHESTPLATE.get() &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.AIR_SUIT_LEGGINGS.get() &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.AIR_SUIT_BOOTS.get());
    }




}
