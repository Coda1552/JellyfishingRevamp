package blueduck.jellyfishing.worldgen;

import blueduck.jellyfishing.registry.ModBiomes;
import blueduck.jellyfishing.registry.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.level.levelgen.SurfaceRules.stoneDepthCheck;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource CORALSTONE = makeStateRule(ModBlocks.CORALSTONE.get());
    private static final SurfaceRules.RuleSource ALGAE_GRASS = makeStateRule(ModBlocks.ALGAE_GRASS.get());
    private static final SurfaceRules.RuleSource DEEP_SAND = makeStateRule(ModBlocks.DEEP_SAND.get());
    private static final SurfaceRules.RuleSource DEEP_SOIL = makeStateRule(ModBlocks.DEEP_SOIL.get());

    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    public static final SurfaceRules.ConditionSource WATER_ROCK_BOTTOM = stoneDepthCheck(0, true, 20, CaveSurface.CEILING);
    public static final SurfaceRules.ConditionSource WATER_ROCK_BOTTOM_UP = stoneDepthCheck(0, true, 20, CaveSurface.FLOOR);

    public static final SurfaceRules.ConditionSource HELLA_DEEP_UNDER_FLOOR = stoneDepthCheck(0, true, 60, CaveSurface.FLOOR);
    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, CORALSTONE), ALGAE_GRASS);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.JELLYFISH_FIELDS),
                        SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(-48), 1),
                                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface),
                                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(-24), 1), SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, CORALSTONE)),
                                        SurfaceRules.ifTrue(HELLA_DEEP_UNDER_FLOOR, DEEP_SAND))))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ROCK_BOTTOM),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DEEP_SOIL),
                SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, DEEP_SAND))));


    }

    private static SurfaceRules.RuleSource makeStateRule(@NotNull Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }

}
