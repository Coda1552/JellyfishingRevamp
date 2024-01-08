package blueduck.jellyfishing;

import blueduck.jellyfishing.registry.*;
import blueduck.jellyfishing.worldgen.ModTerrablender;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Jellyfishing.MOD_ID)
public class Jellyfishing {
    public static final String MOD_ID = "jellyfishing";

    public Jellyfishing() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModFeatures.FEATURES.register(bus);
        ModSounds.SOUNDS.register(bus);
        ModPaintings.PAINTINGS.register(bus);
        ModEnchantments.ENCHANTMENTS.register(bus);
        ModTerrablender.registerBiomes();
        ModCreativeTabs.CREATIVE_TABS.register(bus);
    }
}
