package blueduck.jellyfishing.client;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.client.models.*;
import blueduck.jellyfishing.client.renderers.*;
import blueduck.jellyfishing.registry.ModEntities;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Jellyfishing.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {

    public ClientModEvents() {

    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(JellyfishModel.LAYER_LOCATION, JellyfishModel::createBodyLayer);
        event.registerLayerDefinition(BlueJellyfishModel.LAYER_LOCATION, BlueJellyfishModel::createBodyLayer);
        event.registerLayerDefinition(BubbleJellyfishModel.LAYER_LOCATION, BubbleJellyfishModel::createBodyLayer);
        event.registerLayerDefinition(CowJellyfishModel.LAYER_LOCATION, CowJellyfishModel::createBodyLayer);
        event.registerLayerDefinition(TwoFistedJumperModel.LAYER_LOCATION, TwoFistedJumperModel::createBodyLayer);


        event.registerLayerDefinition(SuitModel.LAYER_LOCATION, SuitModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.JELLYFISH.get(), JellyfishRenderer::new);
        event.registerEntityRenderer(ModEntities.BLUE_JELLYFISH.get(), BlueJellyfishRenderer::new);
        event.registerEntityRenderer(ModEntities.BUBBLE_JELLYFISH.get(), BubbleJellyfishRenderer::new);
        event.registerEntityRenderer(ModEntities.COW_JELLYFISH.get(), CowJellyfishRenderer::new);
        event.registerEntityRenderer(ModEntities.TWO_FISTED_JUMPER.get(), TwoFistedJumperRenderer::new);
    }
}
