package blueduck.jellyfishing.item;

import blueduck.jellyfishing.entity.PattyWagonEntity;
import blueduck.jellyfishing.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Properties;

public class PattyWagonItem extends Item {
    public PattyWagonItem(Properties props) { super(props); }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        if (!level.isClientSide) {
            PattyWagonEntity e = ModEntities.PATTY_WAGON.get().create(level);
            BlockPos pos = ctx.getClickedPos().above();
            e.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            level.addFreshEntity(e);
            ctx.getItemInHand().shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}