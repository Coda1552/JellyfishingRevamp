package blueduck.jellyfishing.worldgen.features;

import blueduck.jellyfishing.registry.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralPlantBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class DeepSproutFeature extends Feature<ProbabilityFeatureConfiguration> {
    public DeepSproutFeature(Codec<ProbabilityFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> p_160318_) {
        boolean flag = false;
        RandomSource randomsource = p_160318_.random();
        WorldGenLevel worldgenlevel = p_160318_.level();
        BlockPos blockpos = p_160318_.origin();
        int i = randomsource.nextInt(8) - randomsource.nextInt(8);
        int j = randomsource.nextInt(8) - randomsource.nextInt(8);
        int k;//randomsource.nextIntBetweenInclusive(-56, 64);
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
            BlockState blockstate = ModBlocks.DEEP_SPROUT.get().defaultBlockState().setValue(BaseCoralPlantBlock.WATERLOGGED, worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER) ? true : false);
            if (blockstate.canSurvive(worldgenlevel, blockpos1)) {
                worldgenlevel.setBlock(blockpos1, blockstate, 2);
                flag = true;
            }
        }

        return flag;
    }
}
