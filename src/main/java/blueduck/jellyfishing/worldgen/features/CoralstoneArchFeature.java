package blueduck.jellyfishing.worldgen.features;


import blueduck.jellyfishing.registry.ModBlocks;
import blueduck.jellyfishing.worldgen.ShapeUtil;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class CoralstoneArchFeature extends Feature<NoneFeatureConfiguration> {
    public CoralstoneArchFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        List<BlockPos> points = ShapeUtil.generateCircle((float) context.random().nextInt(7) + 5);
        double angle = context.random().nextDouble() * 2 * Math.PI;
        double heightModifier = context.random().nextDouble()*.75 + .5;

        WorldGenLevel level = context.level();


        for (BlockPos point : points) {
            BlockPos pointPos = new BlockPos((int) (context.origin().getX() + Math.sin(angle) * point.getX()), (int) (context.origin().getY() + point.getZ() * heightModifier), (int) (context.origin().getZ() + Math.cos(angle) * point.getX()));

            List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) ((context.random().nextInt(1) + 2)));
            for (BlockPos point2 : points2) {
                BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY(), pointPos.getZ() + point2.getZ());
                level.setBlock(pointPos2, ModBlocks.CORALSTONE.get().defaultBlockState(), Block.UPDATE_NONE);
            }

        }
        for (BlockPos point : points) {
            BlockPos pointPos = new BlockPos((int) (context.origin().getX() + Math.sin(angle) * point.getX()), (int) (context.origin().getY() + point.getZ() * heightModifier), (int) (context.origin().getZ() + Math.cos(angle) * point.getX()));

            List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) ((4)));
            for (BlockPos point2 : points2) {
                BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY(), pointPos.getZ() + point2.getZ());
                if (level.getBlockState(pointPos2).is(ModBlocks.CORALSTONE.get()) && level.getBlockState(pointPos2.above()).is(Blocks.WATER)) {
                    level.setBlock(pointPos2, ModBlocks.ALGAE_GRASS.get().defaultBlockState(), Block.UPDATE_NONE);
                }
            }

        }



        return true;


    }

}
