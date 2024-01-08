package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class JellyfishEntity extends AbstractJellyfishEntity {

    public JellyfishEntity(EntityType<? extends Squid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ModItems.JELLYFISH_JELLY.get(), ModItems.JELLYFISH.get(), 0.9);

    }

}
