package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class CowJellyfishEntity extends AbstractJellyfishEntity {

    public CowJellyfishEntity(EntityType<? extends Squid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ModItems.GELATINOUS_MILK.get(), ModItems.NET_COW_JELLYFISH.get(), 0.75);

    }

    public static boolean canSpawnJellyfish(EntityType<CowJellyfishEntity> entityType, ServerLevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource rand) {
        return canSpawn(entityType, level, type, pos, rand);
    }

}
