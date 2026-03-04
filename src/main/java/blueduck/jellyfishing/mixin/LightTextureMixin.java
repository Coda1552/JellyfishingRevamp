package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.client.ClientLightingHandler;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightTexture.class)
public class LightTextureMixin {

    @Shadow @Final private Minecraft minecraft;
    @Shadow @Final private NativeImage lightPixels;

    @Inject(
            method = "updateLightTexture",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/texture/DynamicTexture;upload()V")
    )

    private void jellyfishing_boostUnderwaterLight(float partialTick, CallbackInfo ci) {
        System.out.println("LightTextureMixin fired!");
        if (minecraft.player == null) return;

        float interp = ClientLightingHandler.lightInterp;
        if (interp <= 0.0f) return;

        for (int sky = 0; sky < 16; sky++) {
            for (int block = 0; block < 16; block++) {
                int color = lightPixels.getPixelRGBA(block, sky);

                int r = (color) & 0xFF;
                int g = (color >> 8) & 0xFF;
                int b = (color >> 16) & 0xFF;
                int a = (color >> 24) & 0xFF;

                // Scale boost by interp so it fades in/out with the fog
                float boost = 0.65f * interp;
                r = (int)(r + (255 - r) * boost);
                g = (int)(g + (255 - g) * boost);
                b = (int)(b + (255 - b) * boost);

                // Bioluminescent tint, also scaled
                g = Math.min(255, (int)(g * (1.0f + 0.05f * interp)));
                b = Math.min(255, (int)(b * (1.0f + 0.10f * interp)));

                lightPixels.setPixelRGBA(block, sky, (a << 24) | (b << 16) | (g << 8) | r);
            }
        }
    }
}