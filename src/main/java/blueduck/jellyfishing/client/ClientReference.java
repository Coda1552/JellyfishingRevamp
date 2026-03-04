package blueduck.jellyfishing.client;

import blueduck.jellyfishing.client.models.SuitModel;
import blueduck.jellyfishing.item.AirSuitItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;


public class ClientReference {

    public static final NonNullLazy<SuitModel<LivingEntity>> suit = NonNullLazy.of(() -> new SuitModel<LivingEntity>(net.minecraft.client.Minecraft.getInstance().getEntityModels().bakeLayer(SuitModel.LAYER_LOCATION)));

    public static final Rendering INSTANCE = new Rendering();

    @OnlyIn(Dist.CLIENT)
    public static final class Rendering implements IClientItemExtensions
    {

        //        private final NonNullLazy<SuitModel<LivingEntity>> helmet = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION_HEAD)));
//        private final NonNullLazy<SuitModel<LivingEntity>> chestplate = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION_CHESTPLATE)));
//        private final NonNullLazy<SuitModel<LivingEntity>> leggings = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION_LEGGINGS)));
//        private final NonNullLazy<SuitModel<LivingEntity>> boots = NonNullLazy.of(() -> new SuitModel<LivingEntity>(getModel().bakeLayer(SuitModel.LAYER_LOCATION_BOOTS)));

        public Rendering()
        {
        }

        @Override
        public net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(LivingEntity wearer, ItemStack stack, EquipmentSlot slot, net.minecraft.client.model.HumanoidModel<?> defaultModel)
        {
            return ClientReference.suit.get();
        }

        @OnlyIn(Dist.CLIENT)
        private static net.minecraft.client.model.geom.EntityModelSet getModel()
        {
            return net.minecraft.client.Minecraft.getInstance().getEntityModels();
        }

    }

}
