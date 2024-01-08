package blueduck.jellyfishing.common;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.entity.AbstractJellyfishEntity;
import blueduck.jellyfishing.entity.BubbleJellyfishEntity;
import blueduck.jellyfishing.registry.ModEntities;
import blueduck.jellyfishing.worldgen.ModSurfaceRules;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import terrablender.api.SurfaceRuleManager;

import static blueduck.jellyfishing.Jellyfishing.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.JELLYFISH.get(), AbstractJellyfishEntity.createAttributes().build());
        event.put(ModEntities.BLUE_JELLYFISH.get(), AbstractJellyfishEntity.createAttributes().build());
        event.put(ModEntities.BUBBLE_JELLYFISH.get(), BubbleJellyfishEntity.createAttributes().build());
        event.put(ModEntities.COW_JELLYFISH.get(), AbstractJellyfishEntity.createAttributes().build());
        event.put(ModEntities.TWO_FISTED_JUMPER.get(), AbstractJellyfishEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());

    }

//    @SubscribeEvent
//    public static void tradesSetup(VillagerTradesEvent event) {
//        if (event.getType() == VillagerProfession.FISHERMAN) {
//
//        }
//    }

}
