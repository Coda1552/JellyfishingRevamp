package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Jellyfishing.MOD_ID);

    //Coralstone related blocks
    public static final RegistryObject<Block> CORALSTONE = registerBlock("coralstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRANITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> ALGAE_GRASS = registerBlock("algae_grass", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRANITE).requiresCorrectToolForDrops().strength(1.5F, 5.0F)));
    public static final RegistryObject<Block> ALGAE_BLOCK = registerBlock("algae_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).strength(.75F, 2.0F)));
    public static final RegistryObject<Block> CORALSTONE_STAIRS = registerBlock("coralstone_stairs", () -> new StairBlock(() -> CORALSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.GRANITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> CORALSTONE_SLAB = registerBlock("coralstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> CORALSTONE_WALL = registerBlock("coralstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> POLISHED_CORALSTONE = registerBlock("polished_coralstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRANITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> POLISHED_CORALSTONE_STAIRS = registerBlock("polished_coralstone_stairs", () -> new StairBlock(() -> POLISHED_CORALSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.GRANITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> POLISHED_CORALSTONE_SLAB = registerBlock("polished_coralstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    //Metallic Blockset
    public static final RegistryObject<Block> SCRAP_METAL = registerBlock("scrap_metal", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> SCRAP_METAL_STAIRS = registerBlock("scrap_metal_stairs", () -> new StairBlock(() -> SCRAP_METAL.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> SCRAP_METAL_SLAB = registerBlock("scrap_metal_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> VAULT_DOOR = registerBlock("vault_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).noOcclusion(), BlockSetType.STONE));
    public static final RegistryObject<Block> VAULT_TRAPDOOR = registerBlock("vault_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).noOcclusion(), BlockSetType.STONE));
    public static final RegistryObject<Block> SCRAP_METAL_WINDOW = registerBlock("scrap_metal_window", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).noOcclusion(), BlockSetType.STONE));
    public static final RegistryObject<Block> CHROME_METAL = registerBlock("chrome_metal", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> CHROME_METAL_STAIRS = registerBlock("chrome_metal_stairs", () -> new StairBlock(() -> CHROME_METAL.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> CHROME_METAL_SLAB = registerBlock("chrome_metal_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> CHROME_BRICKS = registerBlock("chrome_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> CHROME_BRICK_STAIRS = registerBlock("chrome_brick_stairs", () -> new StairBlock(() -> CHROME_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> CHROME_BRICK_SLAB = registerBlock("chrome_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> CHROME_DOOR = registerBlock("chrome_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).noOcclusion(), BlockSetType.STONE));
    public static final RegistryObject<Block> CHROME_VENT = registerBlock("chrome_vent", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).noOcclusion(), BlockSetType.STONE));

    //Bamboo Blockset
    public static final RegistryObject<Block> BAMBOO_WALL = registerBlock("bamboo_wall", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_BLOCK)));
    public static final RegistryObject<Block> PINK_BAMBOO_WALL = registerBlock("pink_bamboo_wall", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_BLOCK)));
    public static final RegistryObject<Block> BLUE_BAMBOO_WALL = registerBlock("blue_bamboo_wall", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_BLOCK)));
    public static final RegistryObject<Block> YELLOW_BAMBOO_WALL = registerBlock("yellow_bamboo_wall", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_BLOCK)));

    //Pineapple Blockset
    public static final RegistryObject<Block> PINEAPPLE_BLOCK = registerBlock("pineapple_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WET_GRASS).strength(.5F, 2.0F)));
    public static final RegistryObject<Block> PINEAPPLE_PILLAR = registerBlock("pineapple_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(PINEAPPLE_BLOCK.get())));
    public static final RegistryObject<Block> CHISELED_PINEAPPLE_BLOCK = registerBlock("chiseled_pineapple_block", () -> new Block(BlockBehaviour.Properties.copy(PINEAPPLE_BLOCK.get())));

    //Carpet Tiles
    public static final RegistryObject<Block> WHITE_CARPETED_TILES = registerBlock("white_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> LIGHT_GRAY_CARPETED_TILES = registerBlock("light_gray_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> GRAY_CARPETED_TILES = registerBlock("gray_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> BLACK_CARPETED_TILES = registerBlock("black_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> BROWN_CARPETED_TILES = registerBlock("brown_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> RED_CARPETED_TILES = registerBlock("red_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> ORANGE_CARPETED_TILES = registerBlock("orange_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> YELLOW_CARPETED_TILES = registerBlock("yellow_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> LIME_CARPETED_TILES = registerBlock("lime_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> GREEN_CARPETED_TILES = registerBlock("green_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> CYAN_CARPETED_TILES = registerBlock("cyan_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> LIGHT_BLUE_CARPETED_TILES = registerBlock("light_blue_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> BLUE_CARPETED_TILES = registerBlock("blue_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> PURPLE_CARPETED_TILES = registerBlock("purple_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> MAGENTA_CARPETED_TILES = registerBlock("magenta_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> PINK_CARPETED_TILES = registerBlock("pink_carpeted_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.BAMBOO_WOOD)));

    //Miscellaneous
    public static final RegistryObject<Block> PATTY_TILES = registerBlock("patty_tiles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WET_SPONGE)));

    public static final RegistryObject<Block> JELLY_BLOCK = registerBlock("jelly_block", () -> new SlimeBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).noOcclusion().jumpFactor(1.5f)));
    public static final RegistryObject<Block> BLUE_JELLY_BLOCK = registerBlock("blue_jelly_block", () -> new SlimeBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).noOcclusion().jumpFactor(2)));

    public static final RegistryObject<Block> CORAL_PLANT = registerBlock("coral_plant", () -> new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL).noOcclusion().lightLevel((p_50884_) -> { return 10;})));
    public static final RegistryObject<Block> TUBE_PLANT = registerBlock("tube_plant", () -> new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
