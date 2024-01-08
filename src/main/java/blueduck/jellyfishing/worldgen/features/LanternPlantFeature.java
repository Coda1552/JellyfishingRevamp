package blueduck.jellyfishing.worldgen.features;

import blueduck.jellyfishing.block.DoubleWaterPlant;
import blueduck.jellyfishing.registry.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class LanternPlantFeature extends Feature<ProbabilityFeatureConfiguration> {
    public LanternPlantFeature(Codec<ProbabilityFeatureConfiguration> p_66768_) {
        super(p_66768_);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> p_160318_) {
        boolean flag = false;
        RandomSource randomsource = p_160318_.random();
        WorldGenLevel worldgenlevel = p_160318_.level();
        BlockPos blockpos = p_160318_.origin();
        ProbabilityFeatureConfiguration probabilityfeatureconfiguration = p_160318_.config();
        int i = randomsource.nextInt(8) - randomsource.nextInt(8);
        int j = randomsource.nextInt(8) - randomsource.nextInt(8);
        int k = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX() + i, blockpos.getZ() + j);
        boolean flag2 = false;
        BlockPos blockpos1 = null;
        for (int r = 0; r < 25; r++) {
            k = randomsource.nextIntBetweenInclusive(-56, 48);
            if (k >= -64 && worldgenlevel.getBlockState(new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j)).is(Blocks.WATER) || worldgenlevel.getBlockState(new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j)).canBeReplaced()) {
                for (int l = 0; l < 50; l++) {
                    k-= 1;
                    if (k >= -64) {
                        BlockPos pos = new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j);
                        if (worldgenlevel.getBlockState(pos).isFaceSturdy(worldgenlevel, pos, Direction.UP)) {
                            blockpos1 = pos.above();
                            flag2 = true;
                            break;
                        }
                    }
                }
            }
            if (flag2) {
                break;
            }
        }
        if (blockpos1 != null && (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER) || worldgenlevel.getBlockState(blockpos1).isAir())) {
            boolean flag1 = randomsource.nextDouble() < (double)probabilityfeatureconfiguration.probability;
            BlockState blockstate = flag1 ? ModBlocks.TALL_LANTERN_PLANT.get().defaultBlockState().setValue(BaseCoralPlantBlock.WATERLOGGED, worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER) ? true : false) : ModBlocks.LANTERN_PLANT.get().defaultBlockState().setValue(BaseCoralPlantBlock.WATERLOGGED, worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER) ? true : false);
            if (blockstate.canSurvive(worldgenlevel, blockpos1)) {
                if (flag1) {
                    BlockState blockstate1 = blockstate.setValue(DoubleWaterPlant.HALF, DoubleBlockHalf.UPPER);
                    BlockPos blockpos2 = blockpos1.above();
                    if (worldgenlevel.getBlockState(blockpos2).is(Blocks.WATER) || worldgenlevel.getBlockState(blockpos1).isAir()) {
                        worldgenlevel.setBlock(blockpos1, blockstate, 2);
                        worldgenlevel.setBlock(blockpos2, blockstate1, 2);
                    }
                } else {
                    worldgenlevel.setBlock(blockpos1, blockstate, 2);
                }

                flag = true;
            }
        }

        return flag;
    }
}