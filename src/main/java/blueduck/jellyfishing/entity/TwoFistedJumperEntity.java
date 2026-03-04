package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class TwoFistedJumperEntity extends AbstractJellyfishEntity {

    public TwoFistedJumperEntity(EntityType<? extends Squid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ModItems.JUMPER_JELLY.get(), ModItems.NET_TWO_FISTED_JUMPER.get(), 0.25);
    }

    public void playerTouch(Player pEntity) {
        if (pEntity instanceof ServerPlayer && !this.getPersistentData().getBoolean("PersistenceRequired")) {
            pEntity.hurt(this.damageSources().mobAttack(this), (float)(3));
        }

    }

    public static boolean canSpawnJellyfish(EntityType<TwoFistedJumperEntity> entityType, ServerLevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource rand) {
        return canSpawn(entityType, level, type, pos, rand);
    }

}
