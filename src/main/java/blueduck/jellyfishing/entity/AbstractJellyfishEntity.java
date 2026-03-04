package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.item.JellyfishNetItem;
import blueduck.jellyfishing.registry.ModEnchantments;
import blueduck.jellyfishing.registry.ModItems;
import blueduck.jellyfishing.registry.ModSounds;
import com.ibm.icu.impl.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AbstractJellyfishEntity extends Squid {

    public Item JELLYFISH_NET_ITEM;
    public Item JELLY_ITEM;
    public double catchChance;
    public int jellyTime = this.random.nextInt(5000) + 5000;

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(AbstractJellyfishEntity.class, EntityDataSerializers.BOOLEAN);


    public AbstractJellyfishEntity(EntityType<? extends Squid> pEntityType, Level pLevel, Item jItem, Item netItem, double chance) {
        super(pEntityType, pLevel);

        JELLY_ITEM = jItem;
        JELLYFISH_NET_ITEM = netItem;
        catchChance = chance;
    }


    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && this.isAlive() && --this.jellyTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(JELLY_ITEM);
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.jellyTime = this.random.nextInt(5000) + 5000;
        }

    }


    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).getItem() == (Items.WATER_BUCKET)) {
            return InteractionResult.FAIL;
        }


        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() instanceof JellyfishNetItem && !player.getCooldowns().isOnCooldown(itemstack.getItem())) {
            player.swing(hand);
            if (!player.level().isClientSide() && this.isAlive()) {
                player.getCooldowns().addCooldown(itemstack.getItem(), 20);
                if (catchChance + 0.05*itemstack.getEnchantmentLevel(ModEnchantments.AGILITY.get()) > player.level().getRandom().nextDouble() || this.getPersistentData().getBoolean("PersistenceRequired")) {
                    if (!this.level().isClientSide()) {
                        CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, new ItemStack(JELLYFISH_NET_ITEM));
                    }
//                    if (random.nextDouble() < (~(itemstack.getEnchantmentLevel(Enchantments.UNBREAKING) + 1))) {
//                        itemstack.hurtAndBreak(1, player, (p_43296_) -> {
//                            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
//                        });
//                    }
                    //player.getItemInHand(hand).shrink(1);
                    ItemStack netItem = new ItemStack(JELLYFISH_NET_ITEM);
//                    if (itemstack.hasCustomHoverName()) {
//                        netItem.setHoverName(itemstack.getHoverName());
//                    }
                    if (itemstack.isEnchanted()) {
                        Map<Enchantment, Integer> enchantmentmap = itemstack.getAllEnchantments();
                        Object[] enchantments = enchantmentmap.keySet().toArray();
                        Object[] levels = enchantmentmap.values().toArray();
                        for (int i = 0; i < enchantmentmap.size(); i++) {
                            netItem.enchant((Enchantment) enchantments[i], (Integer) levels[i]);
                        }
                    }
                    netItem.setDamageValue(itemstack.getDamageValue());
                    player.setItemInHand(hand, netItem);
//                    if (player.getItemInHand(hand).isEmpty()) {
//
//                    } else if (!player.getInventory().add( new ItemStack(JELLYFISH_NET_ITEM))) {
//                        player.drop(new ItemStack(JELLYFISH_NET_ITEM), false);
//                    }
                    player.getCooldowns().addCooldown(JELLYFISH_NET_ITEM, 20);
                    if (!this.getPersistentData().getBoolean("PersistenceRequired")) {
                        int i = ((int) (1 - this.catchChance)^2 * 20);
                        while(i > 0) {
                            int j = ExperienceOrb.getExperienceValue(i);
                            i -= j;
                            ExperienceOrb.award((ServerLevel)this.level(), this.position(), j);
                        }
                        if (0.075*itemstack.getEnchantmentLevel(ModEnchantments.PLUNDERING.get()) > player.level().getRandom().nextDouble()) {
                            LootParams lootparams = (new LootParams.Builder((ServerLevel)this.level())).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.TOOL, itemstack).withParameter(LootContextParams.KILLER_ENTITY, player).withLuck(player.getLuck()).create(LootContextParamSets.FISHING);
                            LootTable loottable = this.level().getServer().getLootData().getLootTable(BuiltInLootTables.FISHING);
                            List<ItemStack> list = loottable.getRandomItems(lootparams);
                            for (int e = 0; e < list.size(); e++) {
                                this.spawnAtLocation(list.get(e));
                            }

                            this.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 1, 1);
                        }
                    }

                    this.remove(RemovalReason.UNLOADED_WITH_PLAYER);
                    this.playSound(SoundEvents.BUCKET_EMPTY, 1, 1);
                    return InteractionResult.SUCCESS;
                }
                else {
                    if (this.isInWater() && !player.level().getDifficulty().equals(Difficulty.PEACEFUL)) {
                        player.hurt(player.level().damageSources().sting(this), 2);
                        this.playSound(ModSounds.STING.get(), 1, 1);
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        return super.mobInteract(player, hand);
        }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.getPersistentData().getBoolean("PersistenceRequired");
    }

    @Override
    public void spawnInk() {

    }

    public static boolean canSpawn(EntityType<?> entityType, ServerLevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource rand) {
        if (!level.getBlockState(pos).is(Blocks.WATER)) {
            return false;
        }

        if (level instanceof ServerLevel serverLevel) {
            float moonBrightness = serverLevel.getMoonBrightness();
            if (moonBrightness < 1.0f) {
                return rand.nextFloat() < moonBrightness + 0.1f;
            }
        }
        return true; //checkMobSpawnRules(entityType, level, type, pos, rand);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        if (this.level() instanceof ServerLevel serverLevel) {
            return Math.max(1, Math.round(serverLevel.getMoonBrightness() * 8));
        }
        return 4; // default
    }




}
