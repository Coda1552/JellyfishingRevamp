package blueduck.jellyfishing.block;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;
import java.util.function.Supplier;

public class AlgaeGrassBlock extends Block {
    public Supplier<Block> replaceBlock;
    public AlgaeGrassBlock(BlockBehaviour.Properties properties, Supplier<Block> replace) {
        super(properties);
        replaceBlock = replace;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if ((level.getBlockState(pos.above()).canOcclude()  && level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN)) || level.getBlockState(pos.above()).is(Blocks.LAVA)) {
            level.setBlockAndUpdate(pos, replaceBlock.get().defaultBlockState());
        }
        else {

            level.setBlockAndUpdate(pos, state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.tick(state, level, pos, random);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if ((level.getBlockState(pos.above()).canOcclude() && level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN)) || level.getBlockState(pos.above()).is(Blocks.LAVA)) {
            level.setBlockAndUpdate(pos, replaceBlock.get().defaultBlockState());
            level.updateNeighborsAt(pos.below(), this);
        }
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }





}