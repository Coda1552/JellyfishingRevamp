package blueduck.jellyfishing.client;

import blueduck.jellyfishing.Jellyfishing;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Jellyfishing.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientLightingHandler {

    public static final ResourceKey<Biome> JELLYFISH_FIELDS_KEY = ResourceKey.create(Registries.BIOME, new ResourceLocation(Jellyfishing.MOD_ID, "jellyfish_fields"));

    public static float lightInterp = 0.0f;

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        Minecraft mc = Minecraft.getInstance();
        boolean inBiome = mc.player != null
                && mc.player.isUnderWater()
                && mc.level.getBiome(mc.player.blockPosition()).is(JELLYFISH_FIELDS_KEY);

        if (inBiome && lightInterp < 1.0f) {
            lightInterp = Math.min(1.0f, lightInterp + 0.01f);
        } else if (!inBiome && lightInterp > 0.0f) {
            lightInterp = Math.max(0.0f, lightInterp - 0.01f);
        }

        if (lightInterp > 0.0f) {
            event.setRed(Math.min(1.0f, event.getRed() + (Math.min(1.0f, event.getRed() * 4.0f) - event.getRed()) * lightInterp));
            event.setGreen(Math.min(1.0f, event.getGreen() + (Math.min(1.0f, event.getGreen() * 4.0f) - event.getGreen()) * lightInterp));
            event.setBlue(Math.min(1.0f, event.getBlue() + (Math.min(1.0f, event.getBlue() * 4.0f) - event.getBlue()) * lightInterp));
        }
    }

    @SubscribeEvent
    public static void onFogDensity(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !mc.player.isUnderWater() || lightInterp <= 0.0f) return;

        float defaultFar = 60f;
        event.setNearPlaneDistance(-8f * lightInterp);
        event.setFarPlaneDistance(defaultFar + (192f - defaultFar) * lightInterp);
        event.setCanceled(true);
    }
}
