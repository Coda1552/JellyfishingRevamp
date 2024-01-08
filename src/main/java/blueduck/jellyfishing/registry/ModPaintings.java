package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.item.KelpShakeItem;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Jellyfishing.MOD_ID);

    public static final RegistryObject<PaintingVariant> CAPTAIN = PAINTINGS.register("captain", ()-> new PaintingVariant(64,48));
    public static final RegistryObject<PaintingVariant> BOLD_AND_BRASH = PAINTINGS.register("bold_and_brash", ()-> new PaintingVariant(16,32));
    public static final RegistryObject<PaintingVariant> JELLYFISH = PAINTINGS.register("jellyfish", ()-> new PaintingVariant(16,16));
    public static final RegistryObject<PaintingVariant> JELLYFISH_FIELDS = PAINTINGS.register("jellyfish_fields", ()-> new PaintingVariant(32,16));
    public static final RegistryObject<PaintingVariant> PATTY_WAGON = PAINTINGS.register("patty_wagon", ()-> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> MILLIONTH_DOLLAR = PAINTINGS.register("millionth_dollar", ()-> new PaintingVariant(32,16));


}
