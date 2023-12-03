package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Jellyfishing.MOD_ID);

    //Food
    public static final RegistryObject<Item> JELLYFISH_JELLY = ITEMS.register("jellyfish_jelly", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));
    public static final RegistryObject<Item> BLUE_JELLYFISH_JELLY = ITEMS.register("blue_jellyfish_jelly", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build())));

    public static final RegistryObject<Item> TRIPLE_GOOBERBERRY_SUNRISE = ITEMS.register("triple_gooberberry_sunrise", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.5F).build())));


}
