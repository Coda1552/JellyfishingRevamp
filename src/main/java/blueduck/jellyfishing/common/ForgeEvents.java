package blueduck.jellyfishing.common;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.registry.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid= Jellyfishing.MOD_ID)
public class ForgeEvents {

    @SubscribeEvent
    public static void tradesSetup(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FISHERMAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //Buying Jellyfish
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.JELLYFISH.get(), 1),
                    new ItemStack(Items.EMERALD, 2),
                    8, 8, .2f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BLUE_JELLYFISH.get(), 1),
                    new ItemStack(Items.EMERALD, 3),
                    8, 12, .2f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BUBBLE_JELLYFISH.get(), 1),
                    new ItemStack(Items.EMERALD, 1),
                    8, 6, .2f));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.COW_JELLYFISH.get(), 1),
                    new ItemStack(Items.EMERALD, 4),
                    4, 8, .2f));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.TWO_FISTED_JUMPER.get(), 1),
                    new ItemStack(Items.EMERALD, 9),
                    6, 20, .2f));

            //Selling Stuff
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(ModItems.JELLYFISH_NET.get(), 1),
                    6, 14, .2f));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.JELLYFISH_JELLY.get(), 7),
                    6, 12, .2f));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.BLUE_JELLYFISH_JELLY.get(), 4),
                    6, 12, .2f));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.GELATINOUS_MILK.get(), 8),
                    6, 12, .2f));
        }
        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 9),
                    new ItemStack(ModItems.JELLYFISH_NET.get(), 1),
                    6, 14, .2f));
        }
        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 14),
                    new ItemStack(ModItems.SPATULA.get(), 1),
                    6, 14, .2f));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 14),
                    new ItemStack(ModItems.KARATE_GLOVE.get(), 1),
                    6, 14, .2f));
        }
    }

    @SubscribeEvent
    public static void WandererTradesSetup(WandererTradesEvent event) {

        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 14),
                new ItemStack(ModItems.JELLYFISH_NET.get(), 1),
                6, 14, .2f));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 9),
                new ItemStack(ModItems.KRABBY_PATTY.get(), 1),
                6, 14, .2f));
    }

    @SubscribeEvent
    public static void lootLoad(LootTableLoadEvent event) {
        if (event.getName().equals(new ResourceLocation("minecraft:gameplay/fishing/fish"))) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing", "gameplay/fishing/jellyfish"), 2, 0));

            }
        }
        if (event.getName().equals(new ResourceLocation("minecraft:gameplay/fishing/treasure"))) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing", "gameplay/fishing/treasure_net"), 2, 0));

            }
        }
        if (event.getName().equals(new ResourceLocation("minecraft:gameplay/fishing/junk"))) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing", "gameplay/fishing/junk_plants"), 5, 0));

            }
        }
        if (event.getName().equals(new ResourceLocation("minecraft:chests/end_city_treasure"))) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing", "chests/end_city_treasure"), 8, 0));

            }
        }
        if (event.getName().equals(new ResourceLocation("minecraft:chests/shipwreck_supply"))) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing", "chests/shipwreck_supply"), 25, 0));

            }
        }
        if (event.getName().equals(new ResourceLocation("minecraft:chests/shipwreck_treasure"))) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing", "chests/shipwreck_treasure"), 25, 0));

            }
        }
    }

    private static LootPoolEntryContainer getInjectEntry(ResourceLocation location, int weight, int quality) {
        return LootTableReference.lootTableReference(location).setWeight(weight).setQuality(quality).build();
    }

    private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
        ArrayList<LootPoolEntryContainer> lootPoolEntriesArray = new ArrayList<>(List.of(pool.entries));
        ArrayList<LootPoolEntryContainer> newLootEntries = new ArrayList<>(lootPoolEntriesArray);
        newLootEntries.add(entry);
        pool.entries = newLootEntries.toArray(new LootPoolEntryContainer[]{});
    }

}
