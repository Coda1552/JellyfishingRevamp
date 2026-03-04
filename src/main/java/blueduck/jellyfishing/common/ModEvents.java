package blueduck.jellyfishing.common;

import blueduck.jellyfishing.entity.*;
import blueduck.jellyfishing.registry.ModEntities;
import blueduck.jellyfishing.worldgen.ModSurfaceRules;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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
        event.put(ModEntities.GREASE_JELLYFISH.get(), AbstractJellyfishEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        //SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());

    }

    @SubscribeEvent
    public static void registerPlacement(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, JellyfishEntity::canSpawnJellyfish, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.BLUE_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, BlueJellyfishEntity::canSpawnJellyfish, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.BUBBLE_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, BubbleJellyfishEntity::canSpawnJellyfish, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.COW_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, CowJellyfishEntity::canSpawnJellyfish, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.TWO_FISTED_JUMPER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, TwoFistedJumperEntity::canSpawnJellyfish, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.GREASE_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, GreaseJellyfishEntity::canSpawnJellyfish, SpawnPlacementRegisterEvent.Operation.REPLACE);

    }

}
