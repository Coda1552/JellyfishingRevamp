package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Jellyfishing.MOD_ID);

    public static final RegistryObject<SoundEvent> STING = SOUNDS.register("sting", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("jellyfishing", "entity.jellyfish.sting")));


}
