package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class GreaseJellyfishEntity extends AbstractJellyfishEntity {

    public GreaseJellyfishEntity(EntityType<? extends Squid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ModItems.WAD_OF_GREASE.get(), ModItems.NET_GREASE_JELLYFISH.get(), 0.6);

    }

    public static boolean canSpawnJellyfish(EntityType<GreaseJellyfishEntity> entityType, ServerLevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource rand) {
        return canSpawn(entityType, level, type, pos, rand);
    }

}
