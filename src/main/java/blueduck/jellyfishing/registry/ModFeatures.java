package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.worldgen.features.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>>  FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Jellyfishing.MOD_ID);

    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> CORAL_PLANT_FEATURE = FEATURES.register("coral_plant_feature", () -> new CoralPlantFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> TUBE_PLANT_FEATURE = FEATURES.register("tube_plant_feature", () -> new TubePlantFeature(ProbabilityFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> SEANUT_BUSH_FEATURE = FEATURES.register("seanut_bush_feature", () -> new SeanutBushFeature(ProbabilityFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> DEEP_SPROUT_FEATURE = FEATURES.register("deep_sprout_feature", () -> new DeepSproutFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> LANTERN_PLANT_FEATURE = FEATURES.register("lantern_plant_feature", () -> new LanternPlantFeature(ProbabilityFeatureConfiguration.CODEC));

   public static final RegistryObject<Feature<NoneFeatureConfiguration>> WATER_CAVE_FEATURE = FEATURES.register("water_cave_feature", () -> new FillInBubblesWithWaterFeature(NoneFeatureConfiguration.CODEC));


}
