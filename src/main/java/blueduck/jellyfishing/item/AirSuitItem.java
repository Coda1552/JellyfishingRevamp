package blueduck.jellyfishing.item;

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

public class AirSuitItem extends ArmorItem implements IClientItemExtensions {



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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer)
    {
        consumer.accept(Rendering.INSTANCE);
    }

    @OnlyIn(Dist.CLIENT)
    private static final class Rendering implements IClientItemExtensions
    {
        private static final Rendering INSTANCE = new AirSuitItem.Rendering();

        private final NonNullLazy<SuitModel<LivingEntity>> suit = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION)));
//        private final NonNullLazy<SuitModel<LivingEntity>> helmet = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION_HEAD)));
//        private final NonNullLazy<SuitModel<LivingEntity>> chestplate = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION_CHESTPLATE)));
//        private final NonNullLazy<SuitModel<LivingEntity>> leggings = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION_LEGGINGS)));
//        private final NonNullLazy<SuitModel<LivingEntity>> boots = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION_BOOTS)));

        private Rendering()
        {
        }

        @Override
        public net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(LivingEntity wearer, ItemStack stack, EquipmentSlot slot, net.minecraft.client.model.HumanoidModel<?> defaultModel)
        {
//            Item item = stack.getItem();
//            return item == ModItems.AIR_SUIT_CHESTPLATE.get() ? this.chestplate.get() : item == ModItems.AIR_SUIT_LEGGINGS.get() ? this.leggings.get() : item == ModItems.AIR_SUIT_BOOTS.get() ? this.boots.get() : this.helmet.get();
              return suit.get();
        }

        @OnlyIn(Dist.CLIENT)
        private static net.minecraft.client.model.geom.EntityModelSet getModel()
        {
            return net.minecraft.client.Minecraft.getInstance().getEntityModels();
        }

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
//    @OnlyIn(Dist.CLIENT)
//    public HumanoidModel<?> provideArmorModelForSlot(ArmorItem.Type type) {
//        //return new SuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SuitModel.LAYER_LOCATION), type);
//        return type.getSlot() == EquipmentSlot.CHEST ? new SuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(new ModelLayerLocation(SuitModel.TEXTURE_CHESTPLATE, "main")))
//                : type.getSlot() == EquipmentSlot.LEGS ? new SuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(new ModelLayerLocation(SuitModel.TEXTURE_LEGGINGS, "main")))
//                : type.getSlot() == EquipmentSlot.FEET ? new SuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(new ModelLayerLocation(SuitModel.TEXTURE_BOOTS, "main")))
//                : new SuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(new ModelLayerLocation(SuitModel.TEXTURE_HELMET, "main")));
//
//    }

}
