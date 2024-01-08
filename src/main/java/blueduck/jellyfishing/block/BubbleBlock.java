package blueduck.jellyfishing.block;

import blueduck.jellyfishing.registry.ModBlocks;
import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class BubbleBlock extends Block {
    public BubbleBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, 400);
    }



    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        worldIn.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.tick(state, level, pos, random);
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {


        if (pPlayer.getItemInHand(pHand).is(ModItems.BUBBLE_SOAP.get())) {
            int j = 1 + pLevel.random.nextInt(2);

            pLevel.playSound((Player)null, pPos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            BlockState blockstate = ModBlocks.WAXED_BUBBLE_BLOCK.get().defaultBlockState();
            if (!pPlayer.getAbilities().instabuild) {
                pPlayer.getItemInHand(pHand).shrink(1);
            }
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }
}
