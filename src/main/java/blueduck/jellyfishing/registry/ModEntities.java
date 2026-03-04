package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Config;
import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            Jellyfishing.MOD_ID);

    public static final RegistryObject<EntityType<JellyfishEntity>> JELLYFISH = ENTITIES.register("jellyfish",
            () -> EntityType.Builder.of(JellyfishEntity::new, Config.jellyfishAmbient ? MobCategory.WATER_AMBIENT : MobCategory.WATER_CREATURE).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(Jellyfishing.MOD_ID, "jellyfish").toString()));

    public static final RegistryObject<EntityType<BlueJellyfishEntity>> BLUE_JELLYFISH = ENTITIES.register("blue_jellyfish",
            () -> EntityType.Builder.of(BlueJellyfishEntity::new, Config.jellyfishAmbient ? MobCategory.WATER_AMBIENT : MobCategory.WATER_CREATURE).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(Jellyfishing.MOD_ID, "blue_jellyfish").toString()));

    public static final RegistryObject<EntityType<BubbleJellyfishEntity>> BUBBLE_JELLYFISH = ENTITIES.register("bubble_jellyfish",
            () -> EntityType.Builder.of(BubbleJellyfishEntity::new, Config.jellyfishAmbient ? MobCategory.WATER_AMBIENT : MobCategory.WATER_CREATURE).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(Jellyfishing.MOD_ID, "bubble_jellyfish").toString()));

    public static final RegistryObject<EntityType<CowJellyfishEntity>> COW_JELLYFISH = ENTITIES.register("cow_jellyfish",
            () -> EntityType.Builder.of(CowJellyfishEntity::new, Config.jellyfishAmbient ? MobCategory.WATER_AMBIENT : MobCategory.WATER_CREATURE).sized(0.9f, 0.85f)
                    .build(new ResourceLocation(Jellyfishing.MOD_ID, "cow_jellyfish").toString()));

    public static final RegistryObject<EntityType<TwoFistedJumperEntity>> TWO_FISTED_JUMPER = ENTITIES.register("two_fisted_jumper",
            () -> EntityType.Builder.of(TwoFistedJumperEntity::new, Config.jellyfishAmbient ? MobCategory.WATER_AMBIENT : MobCategory.WATER_CREATURE).sized(0.6f, 0.6f)
                    .build(new ResourceLocation(Jellyfishing.MOD_ID, "two_fisted_jumper").toString()));

    public static final RegistryObject<EntityType<GreaseJellyfishEntity>> GREASE_JELLYFISH = ENTITIES.register("grease_jellyfish",
            () -> EntityType.Builder.of(GreaseJellyfishEntity::new, Config.jellyfishAmbient ? MobCategory.WATER_AMBIENT : MobCategory.WATER_CREATURE).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(Jellyfishing.MOD_ID, "grease_jellyfish").toString()));


    public static final RegistryObject<EntityType<PattyWagonEntity>> PATTY_WAGON =
            ENTITIES.register("patty_wagon",
                    () -> EntityType.Builder.of(PattyWagonEntity::new, MobCategory.MISC)
                            .sized(1.2f, 0.625f)
                            .build("patty_wagon"));

}
