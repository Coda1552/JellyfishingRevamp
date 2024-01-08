package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;

public class BlueJellyfishEntity extends AbstractJellyfishEntity {

    public BlueJellyfishEntity(EntityType<? extends Squid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ModItems.BLUE_JELLYFISH_JELLY.get(), ModItems.BLUE_JELLYFISH.get(), 0.625);

    }

}
