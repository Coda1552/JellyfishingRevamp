package blueduck.jellyfishing.common;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.entity.AbstractJellyfishEntity;
import blueduck.jellyfishing.entity.BubbleJellyfishEntity;
import blueduck.jellyfishing.registry.ModEntities;
import blueduck.jellyfishing.registry.ModItems;
import blueduck.jellyfishing.worldgen.ModSurfaceRules;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import terrablender.api.SurfaceRuleManager;

import java.util.List;

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

    @SubscribeEvent
    public static void registerPlacement(SpawnPlacementRegisterEvent event) {
        //event.register(ModEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, AbstractJellyfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);

    }

}
