package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;

public class CowJellyfishEntity extends AbstractJellyfishEntity {

    public CowJellyfishEntity(EntityType<? extends Squid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ModItems.GELATINOUS_MILK.get(), ModItems.COW_JELLYFISH.get(), 0.75);

    }

}
