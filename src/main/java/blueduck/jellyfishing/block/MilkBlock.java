package blueduck.jellyfishing.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MilkBlock extends HoneyBlock {
    public MilkBlock(Properties pProperties) {
        super(pProperties);
    }

    public boolean canStickTo(BlockState state, BlockState other)
    {
        if (other.getBlock() == Blocks.SLIME_BLOCK) return false;
        if (other.getBlock() == Blocks.HONEY_BLOCK) return false;
        if (state.isStickyBlock() && other.isStickyBlock()) {
            return !state.is(other.getBlock());
         }
        return (state.isStickyBlock() || other.isStickyBlock());
    }

    public boolean isStickyBlock(BlockState state)
    {
        return true;
    }
}
