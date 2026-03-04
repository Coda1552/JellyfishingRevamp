package blueduck.jellyfishing;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Jellyfishing.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();


    private static final ForgeConfigSpec.BooleanValue JELLYFISH_AMBIENT = BUILDER
            .comment("Should Jellyfish spawn in the ambient water creature pool instead of water creature? (Set to true if having issues with them not spawning)")
            .define("jellyfish_spawns_ambient_creature", false);


    static final ForgeConfigSpec SPEC = BUILDER.build();


    public static boolean jellyfishAmbient;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {

        jellyfishAmbient = JELLYFISH_AMBIENT.get();

    }
}