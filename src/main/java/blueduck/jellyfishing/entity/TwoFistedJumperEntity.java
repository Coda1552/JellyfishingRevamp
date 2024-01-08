package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TwoFistedJumperEntity extends AbstractJellyfishEntity {

    public TwoFistedJumperEntity(EntityType<? extends Squid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ModItems.JUMPER_JELLY.get(), ModItems.TWO_FISTED_JUMPER.get(), 0.25);
    }

    public void playerTouch(Player pEntity) {
        if (pEntity instanceof ServerPlayer && !this.getPersistentData().getBoolean("PersistenceRequired")) {
            pEntity.hurt(this.damageSources().mobAttack(this), (float)(3));
        }

    }

}
