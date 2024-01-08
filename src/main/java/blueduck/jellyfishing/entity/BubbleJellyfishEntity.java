package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BubbleJellyfishEntity extends AbstractJellyfishEntity {

    public BubbleJellyfishEntity(EntityType<? extends Squid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ModItems.BUBBLE_SOAP.get(), ModItems.BUBBLE_JELLYFISH.get(), 0.9);

    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (super.hurt(pSource, pAmount) && this.getLastHurtByMob() != null) {
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 100, 1, true, true));
            this.playSound(SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, 5, 1);

            return true;
        } else {
            return false;
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D);
    }



}
