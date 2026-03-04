package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.item.*;
import com.teamabnormals.blueprint.common.item.FuelItem;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Jellyfishing.MOD_ID);


    public static final RegistryObject<Item> JELLYFISH_NET = ITEMS.register("jellyfish_net", () -> new JellyfishNetItem(new Item.Properties().stacksTo(1).durability(64)));

    public static final RegistryObject<Item> NET_JELLYFISH = ITEMS.register("net_of_jellyfish", () -> new NetJellyfishItem(new Item.Properties().stacksTo(1).durability(64), () -> ModEntities.JELLYFISH.get()));
    public static final RegistryObject<Item> NET_BLUE_JELLYFISH = ITEMS.register("net_of_blue_jellyfish", () -> new NetJellyfishItem(new Item.Properties().stacksTo(1).durability(64), () -> ModEntities.BLUE_JELLYFISH.get()));
    public static final RegistryObject<Item> NET_BUBBLE_JELLYFISH = ITEMS.register("net_of_bubble_jellyfish", () -> new NetJellyfishItem(new Item.Properties().stacksTo(1).durability(64), () -> ModEntities.BUBBLE_JELLYFISH.get()));
    public static final RegistryObject<Item> NET_COW_JELLYFISH = ITEMS.register("net_of_cow_jellyfish", () -> new NetJellyfishItem(new Item.Properties().stacksTo(1).durability(64), () -> ModEntities.COW_JELLYFISH.get()));
    public static final RegistryObject<Item> NET_TWO_FISTED_JUMPER = ITEMS.register("net_of_two_fisted_jumper", () -> new NetJellyfishItem(new Item.Properties().stacksTo(1).durability(64), () -> ModEntities.TWO_FISTED_JUMPER.get()));
    public static final RegistryObject<Item> NET_GREASE_JELLYFISH = ITEMS.register("net_of_grease_jellyfish", () -> new NetJellyfishItem(new Item.Properties().stacksTo(1).durability(64), () -> ModEntities.GREASE_JELLYFISH.get()));

    public static final RegistryObject<Item> JELLYFISH = ITEMS.register("jellyfish", () -> new JellyfishItem(new Item.Properties(), () -> ModEntities.JELLYFISH.get()));
    public static final RegistryObject<Item> BLUE_JELLYFISH = ITEMS.register("blue_jellyfish", () -> new JellyfishItem(new Item.Properties(), () -> ModEntities.BLUE_JELLYFISH.get()));
    public static final RegistryObject<Item> BUBBLE_JELLYFISH = ITEMS.register("bubble_jellyfish", () -> new JellyfishItem(new Item.Properties(), () -> ModEntities.BUBBLE_JELLYFISH.get()));
    public static final RegistryObject<Item> COW_JELLYFISH = ITEMS.register("cow_jellyfish", () -> new JellyfishItem(new Item.Properties(), () -> ModEntities.COW_JELLYFISH.get()));
    public static final RegistryObject<Item> TWO_FISTED_JUMPER = ITEMS.register("two_fisted_jumper", () -> new JellyfishItem(new Item.Properties(), () -> ModEntities.TWO_FISTED_JUMPER.get()));
    public static final RegistryObject<Item> GREASE_JELLYFISH = ITEMS.register("grease_jellyfish", () -> new JellyfishItem(new Item.Properties(), () -> ModEntities.GREASE_JELLYFISH.get()));


    //Food
    public static final RegistryObject<Item> JELLYFISH_JELLY = ITEMS.register("jellyfish_jelly", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));
    public static final RegistryObject<Item> BLUE_JELLYFISH_JELLY = ITEMS.register("blue_jellyfish_jelly", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build())));
    public static final RegistryObject<Item> BUBBLE_SOAP = ITEMS.register("bubble_soap", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GELATINOUS_MILK = ITEMS.register("gelatinous_milk", () -> new GelatinousMilkItem(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> JUMPER_JELLY = ITEMS.register("jumper_jelly", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0), 1).build())));
    public static final RegistryObject<Item> WAD_OF_GREASE = ITEMS.register("grease_ball", () -> new FuelItem(800, new Item.Properties()));

    public static final RegistryObject<Item> GREASE_UPGRADE_TEMPLATE = ITEMS.register("grease_upgrade_template", () -> JellyfishingSmithingTemplate.createGreaseUpgrade());


    public static final RegistryObject<Item> SEANUT = ITEMS.register("seanut", () -> new ItemNameBlockItem(ModBlocks.SEANUT_BUSH.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ROASTED_SEANUT = ITEMS.register("roasted_seanut", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.5f).build())));
    public static final RegistryObject<Item> SEANUT_BUTTER = ITEMS.register("seanut_butter", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.5f).build())));
    public static final RegistryObject<Item> SEANUT_BRITTLE = ITEMS.register("seanut_brittle", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.3f).build())));

    public static final RegistryObject<Item> JELLY_SANDWICH = ITEMS.register("jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.7F).build())));
    public static final RegistryObject<Item> BLUE_JELLY_SANDWICH = ITEMS.register("blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).build())));
    public static final RegistryObject<Item> JUMPER_JELLY_SANDWICH = ITEMS.register("jumper_jelly_sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1).build())));
    public static final RegistryObject<Item> SEANUT_JELLY_SANDWICH = ITEMS.register("seanut_butter_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(0.8F).build())));
    public static final RegistryObject<Item> SEANUT_BLUE_JELLY_SANDWICH = ITEMS.register("seanut_butter_blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(0.9F).build())));
    public static final RegistryObject<Item> SEANUT_JUMPER_JELLY_SANDWICH = ITEMS.register("seanut_butter_jumper_jelly_sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.7F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1).build())));

    public static final RegistryObject<Item> PINEAPPLE_SEEDS = ITEMS.register("pineapple_seeds", () -> new ItemNameBlockItem(ModBlocks.PINEAPPLE_PLANT.get(), new Item.Properties()));
    public static final RegistryObject<Item> PINEAPPLE = ITEMS.register("pineapple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build())));
    public static final RegistryObject<Item> KELP_SHAKE = ITEMS.register("kelp_shake", () -> new KelpShakeItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build())));
    public static ArmorMaterial KELP = new KelpMaterial();
    public static final RegistryObject<Item> KELP_MUSTACHE = ITEMS.register("kelp_mustache", () -> new KelpMustacheItem(KELP, new Item.Properties().durability(256)));

    public static final RegistryObject<Item> TRIPLE_GOOBERBERRY_SUNRISE = ITEMS.register("triple_gooberberry_sunrise", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.5F).build())));

    public static final RegistryObject<Item> KRABBY_PATTY = ITEMS.register("krabby_patty", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1.0F).build())));


    public static final RegistryObject<Item> BUBBLE_WAND = ITEMS.register("bubble_wand", () -> new BubbleWandItem(ModBlocks.BUBBLE_BLOCK.get(), new Item.Properties().durability(256)));

    public static final RegistryObject<Item> SPATULA = ITEMS.register("spatula", () -> new SpatulaItem(new Item.Properties().durability(781), Tiers.IRON, 3, -1.5f));
    public static final RegistryObject<Item> GOLDEN_SPATULA = ITEMS.register("golden_spatula", () -> new SpatulaItem(new Item.Properties().durability(1562), Tiers.NETHERITE, 5, -1.5f));

    public static final RegistryObject<Item> KARATE_GLOVE = ITEMS.register("karate_glove", () -> new GloveItem(new Item.Properties().durability(502), Tiers.IRON, 2, 0));
    public static final RegistryObject<Item> MASTER_KARATE_GLOVE = ITEMS.register("master_karate_glove", () -> new GloveItem(new Item.Properties().durability(781), Tiers.DIAMOND, 4, 0));
    public static final RegistryObject<Item> POWER_KARATE_GLOVE = ITEMS.register("power_karate_glove", () -> new GloveItem(new Item.Properties().durability(1015), Tiers.NETHERITE, 8, -3));

    public static final RegistryObject<Item> PATTY_WAGON = ITEMS.register("patty_wagon", () -> new PattyWagonItem(new Item.Properties().stacksTo(1)));


    public static ArmorMaterial AIR_SUIT = new SuitMaterial();


    public static final RegistryObject<Item> AIR_SUIT_HELMET = ITEMS.register("air_suit_helmet", () -> FMLEnvironment.dist.equals(Dist.CLIENT) ? new ClientAirSuitItem(AIR_SUIT, ArmorItem.Type.HELMET, new Item.Properties().durability(1261)) : new AirSuitItem(AIR_SUIT, ArmorItem.Type.HELMET, new Item.Properties().durability(1261)));
    public static final RegistryObject<Item> AIR_SUIT_CHESTPLATE = ITEMS.register("air_suit_chestplate", () -> FMLEnvironment.dist.equals(Dist.CLIENT) ? new ClientAirSuitItem(AIR_SUIT, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(1261)) : new AirSuitItem(AIR_SUIT, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(1261)));
    public static final RegistryObject<Item> AIR_SUIT_LEGGINGS = ITEMS.register("air_suit_leggings", () -> FMLEnvironment.dist.equals(Dist.CLIENT) ? new ClientAirSuitItem(AIR_SUIT, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(1261)) : new AirSuitItem(AIR_SUIT, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(1261)));
    public static final RegistryObject<Item> AIR_SUIT_BOOTS = ITEMS.register("air_suit_boots", () -> FMLEnvironment.dist.equals(Dist.CLIENT) ? new ClientAirSuitItem(AIR_SUIT, ArmorItem.Type.BOOTS, new Item.Properties().durability(1261)) : new AirSuitItem(AIR_SUIT, ArmorItem.Type.BOOTS, new Item.Properties().durability(1261)));


    //public static final RegistryObject<Item> CHUM_BUCKET_HAT = ITEMS.register("chum_bucket_hat", () -> new AirSuitItem(AIR_SUIT, ArmorItem.Type.HELMET, new Item.Properties().durability(1261)));

    //Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.JELLYFISH, 12850334, 7538267,
                    new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> BLUE_JELLYFISH_SPAWN_EGG = ITEMS.register("blue_jellyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BLUE_JELLYFISH, 1936112, 678049,
                    new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> BUBBLE_JELLYFISH_SPAWN_EGG = ITEMS.register("bubble_jellyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BUBBLE_JELLYFISH, 757470, 757404,
                    new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> COW_JELLYFISH_SPAWN_EGG = ITEMS.register("cow_jellyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.COW_JELLYFISH, 14017754, 11,
                    new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> TWO_FISTED_JUMPER_SPAWN_EGG = ITEMS.register("two_fisted_jumper_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TWO_FISTED_JUMPER, 12405771, 14856704,
                    new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> GREASE_JELLYFISH_SPAWN_EGG = ITEMS.register("grease_jellyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GREASE_JELLYFISH, 14856704, 12405771,
                    new Item.Properties()));


}
