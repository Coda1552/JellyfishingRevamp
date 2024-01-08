package blueduck.jellyfishing.item;

import net.minecraft.world.item.Item;

public class JellyfishNetItem extends Item {
    public JellyfishNetItem(Properties pProperties) {
        super(pProperties);
    }


    public int getEnchantmentValue() {
        return 30;
    }
}
