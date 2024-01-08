package blueduck.jellyfishing.item;

import blueduck.jellyfishing.entity.AbstractJellyfishEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Supplier;


public class JellyfishItem extends Item {

    Supplier<EntityType<?>> entityType;
    public JellyfishItem(Properties pProperties, Supplier<EntityType<?>> eType) {
        super(pProperties);
        entityType = eType;
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = pContext.getItemInHand();
            BlockPos blockpos = pContext.getClickedPos();
            Direction direction = pContext.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            Entity entity = this.getType(itemstack.getTag()).spawn((ServerLevel)level, itemstack, pContext.getPlayer(), blockpos1, MobSpawnType.BUCKET, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
            if (entity != null) {
                itemstack.shrink(1);
                level.gameEvent(pContext.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
                entity.getPersistentData().putBoolean("PersistenceRequired", true);
            }

            return InteractionResult.CONSUME;
        }

    }
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.SOURCE_ONLY);
        if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else if (!(pLevel instanceof ServerLevel)) {
            return InteractionResultHolder.success(itemstack);
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            if (!(pLevel.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
                return InteractionResultHolder.pass(itemstack);
            } else if (pLevel.mayInteract(pPlayer, blockpos) && pPlayer.mayUseItemAt(blockpos, blockhitresult.getDirection(), itemstack)) {
                EntityType<?> entitytype = this.getType(itemstack.getTag());
                AbstractJellyfishEntity entity = (AbstractJellyfishEntity) entitytype.spawn((ServerLevel)pLevel, itemstack, pPlayer, blockpos, MobSpawnType.BUCKET, false, false);
                if (entity == null) {
                    return InteractionResultHolder.pass(itemstack);
                } else {
                    if (!pPlayer.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, entity.position());
                    entity.getPersistentData().putBoolean("PersistenceRequired", true);
                    return InteractionResultHolder.consume(itemstack);
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }
    public EntityType<?> getType(@Nullable CompoundTag pNbt) {
        return entityType.get();
    }
}
