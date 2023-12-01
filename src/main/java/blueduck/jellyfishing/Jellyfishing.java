package blueduck.jellyfishing;

import blueduck.jellyfishing.registry.ModCreativeTabs;
import blueduck.jellyfishing.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Jellyfishing.MOD_ID)
public class Jellyfishing {
    public static final String MOD_ID = "jellyfishing";

    public Jellyfishing() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(bus);
        ModCreativeTabs.CREATIVE_TABS.register(bus);
    }
}
