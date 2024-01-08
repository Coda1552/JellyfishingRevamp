package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModEnchantments;
import blueduck.jellyfishing.registry.ModItems;
import blueduck.jellyfishing.registry.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
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
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

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
        if (itemstack.getItem() == ModItems.JELLYFISH_NET.get() && !player.getCooldowns().isOnCooldown(itemstack.getItem())) {
            player.swing(hand);
            if (!player.level().isClientSide() && this.isAlive()) {
                player.getCooldowns().addCooldown(itemstack.getItem(), 20);
                if (catchChance + 0.05*itemstack.getEnchantmentLevel(ModEnchantments.AGILITY.get()) > player.level().getRandom().nextDouble() || this.getPersistentData().getBoolean("PersistenceRequired")) {
                    if (!this.level().isClientSide()) {
                        CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, new ItemStack(JELLYFISH_NET_ITEM));
                    }
                    if (random.nextDouble() < (~(itemstack.getEnchantmentLevel(Enchantments.UNBREAKING) + 1))) {
                        itemstack.hurtAndBreak(1, player, (p_43296_) -> {
                            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                        });
                    }

                    if (itemstack.isEmpty()) {
                        player.setItemInHand(hand, new ItemStack(JELLYFISH_NET_ITEM));
                    } else if (!player.getInventory().add( new ItemStack(JELLYFISH_NET_ITEM))) {
                        player.drop(new ItemStack(JELLYFISH_NET_ITEM), false);
                    }

                    this.remove(RemovalReason.UNLOADED_WITH_PLAYER);

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




}
