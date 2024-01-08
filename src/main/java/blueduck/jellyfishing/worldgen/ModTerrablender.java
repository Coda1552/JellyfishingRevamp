package blueduck.jellyfishing.worldgen;

import blueduck.jellyfishing.Jellyfishing;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(Jellyfishing.MOD_ID, "overworld"), 5));
    }
}
