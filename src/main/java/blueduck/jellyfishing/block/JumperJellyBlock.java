package blueduck.jellyfishing.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JumperJellyBlock extends HalfTransparentBlock {
    protected static final VoxelShape SHAPE = Block.box(2.0D, 1.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    public JumperJellyBlock(Properties pProperties) {
        super(pProperties);
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public void updateEntityAfterFallOn(BlockGetter pLevel, Entity pEntity) {
        if (pEntity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(pLevel, pEntity);
        } else {
            this.bounceUp(pEntity);
        }

    }

    private void bounceUp(Entity pEntity) {
        Vec3 vec3 = pEntity.getDeltaMovement();
        if (vec3.y < 0.0D) {
            //double d0 = pEntity instanceof LivingEntity ? 1.0D : 0.8D;
            pEntity.setDeltaMovement(vec3.x, -vec3.y, vec3.z);
        }

    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity.getPosition(0f).x > pPos.getX() + 0.75) {
            pEntity.setDeltaMovement(Math.abs(pEntity.getDeltaMovement().x()) > 0.2 ? Math.abs(pEntity.getDeltaMovement().x()) : 0.3, pEntity.getDeltaMovement().y(), pEntity.getDeltaMovement().z());
        }
        if (pEntity.getPosition(0f).x < pPos.getX() + 0.25) {
            pEntity.setDeltaMovement(Math.abs(pEntity.getDeltaMovement().x()) > 0.2 ? Math.abs(pEntity.getDeltaMovement().x()) * -1 : -0.3, pEntity.getDeltaMovement().y(), pEntity.getDeltaMovement().z());
        }
        if (pEntity.getPosition(0f).z > pPos.getZ() + 0.75) {
            pEntity.setDeltaMovement(pEntity.getDeltaMovement().x(), pEntity.getDeltaMovement().y(), Math.abs(pEntity.getDeltaMovement().z()) > 0.2 ? Math.abs(pEntity.getDeltaMovement().z()) : 0.3);
        }
        if (pEntity.getPosition(0f).z < pPos.getZ() + 0.25) {
            pEntity.setDeltaMovement(pEntity.getDeltaMovement().x(), pEntity.getDeltaMovement().y(), Math.abs(pEntity.getDeltaMovement().z()) > 0.2 ? Math.abs(pEntity.getDeltaMovement().z()) * -1 : -0.3);
        }

        super.entityInside(pState, pLevel, pPos, pEntity);
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
