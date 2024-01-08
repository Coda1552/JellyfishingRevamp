package blueduck.jellyfishing.block;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.extensions.IForgeBlockGetter;

import java.util.Random;

public class PineapplePlant extends SweetBerryBushBlock {


    public PineapplePlant(Properties propert) {
        super(propert);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));

    }


    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(ModItems.PINEAPPLE_SEEDS.get());
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
    }

    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        int i = state.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
            return InteractionResult.PASS;
        } else if (flag) {
            popResource(worldIn, pos, new ItemStack(ModItems.PINEAPPLE.get(), 1));
            worldIn.playSound((Player)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.getRandom().nextFloat() * 0.4F);
            worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(0)), 2);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.SUCCESS;
        }
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int i = pState.getValue(AGE);
        if (i < 3 && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt(5) == 0)) {
            BlockState blockstate = pState.setValue(AGE, Integer.valueOf(i + 1));
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(blockstate));
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

}
