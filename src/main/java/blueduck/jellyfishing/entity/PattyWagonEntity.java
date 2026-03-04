package blueduck.jellyfishing.entity;

import blueduck.jellyfishing.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class PattyWagonEntity extends Entity {

    // Synced data keys
    private static final EntityDataAccessor<Integer> HURT_TIME = SynchedEntityData.defineId(PattyWagonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(PattyWagonEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> HURT_DIR = SynchedEntityData.defineId(PattyWagonEntity.class, EntityDataSerializers.INT);

    private float jumpPower = 0.0f;
    private boolean isJumping = false;

    public PattyWagonEntity(EntityType<? extends PattyWagonEntity> type, Level level) {
        super(type, level);
        this.blocksBuilding = true; // prevents players from clipping in
        this.setNoGravity(false);
        this.noPhysics = false;
        this.setMaxUpStep(1.0F);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(HURT_TIME, 0);
        this.entityData.define(DAMAGE, 0.0F);
        this.entityData.define(HURT_DIR, 1);
    }

    public int getHurtTime() {
        return this.entityData.get(HURT_TIME);
    }

    public void setHurtTime(int t) {
        this.entityData.set(HURT_TIME, t);
    }

    public float getDamage() {
        return this.entityData.get(DAMAGE);
    }

    public void setDamage(float d) {
        this.entityData.set(DAMAGE, d);
    }

    public int getHurtDir() {
        return this.entityData.get(HURT_DIR);
    }

    public void setHurtDir(int dir) {
        this.entityData.set(HURT_DIR, dir);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putFloat("WagonDamage", this.getDamage());
        tag.putInt("WagonHurtTime", this.getHurtTime());
    }

    @Override
    public void tick() {
        super.tick();

        // Apply gravity
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, this.isInWater() ? -0.03 : -0.08 , 0));
        }

        // Boat-like "heading persistence"
        float currentYaw = this.getYRot();

        // --- If a player is riding ---
        if (this.getControllingPassenger() instanceof Player player) {

            // Only rotate the Patty Wagon when player rotates their camera
            float playerYaw = player.getYRot();
            float yawDelta = playerYaw - currentYaw;

            this.setYRot(currentYaw);
            this.yRotO = this.getYRot();

            // Movement: use the wagon's rotation, NOT player's yaw
            float forward = player.zza;
            float side = -player.xxa;
            float turnSpeed = (forward > 0 ? 6.7f : 4f);

            if (side != 0) {
                this.setYRot(this.getYRot() + side * turnSpeed);
            }

            if (forward != 0) {
                double speed = 0.56;

                double x = -Math.sin(Math.toRadians(this.getYRot())) * forward * (forward > 0 ? speed : speed * 0.5);
                double z =  Math.cos(Math.toRadians(this.getYRot())) * forward * (forward > 0 ? speed : speed * 0.5);

                this.setDeltaMovement(x, this.getDeltaMovement().y, z);
            }
        }

        if (this.getControllingPassenger() instanceof Player player) {
            // === Jump Charging ===
            if (player.jumping) {
                // Charge up to 1.0
                this.jumpPower += 0.04f;
                if (this.jumpPower > 1.0f) this.jumpPower = 1.0f;
            } else if (this.jumpPower > 0f && !this.isJumping && this.onGround()) {
                // Release the jump
                float power = this.jumpPower;
                this.jumpPower = 0f;
                this.isJumping = true;

                double jumpHeight = 0.42 + (power * 0.5);
                Vec3 motion = this.getDeltaMovement();
                this.setDeltaMovement(motion.x, jumpHeight, motion.z);
            }

            // Reset jumping once we hit the ground
            if (this.onGround()) {
                this.isJumping = false;
            }
        }

        // Move the wagon
        this.move(MoverType.SELF, this.getDeltaMovement());

        // friction
        this.setDeltaMovement(this.getDeltaMovement().multiply(0.9, 1, 0.9));
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        return this.interactAt(player, this.position(), hand);
    }



    @Override
    public InteractionResult interactAt(Player player, Vec3 hitPos, InteractionHand hand) {
        if (level().isClientSide)
            return InteractionResult.SUCCESS; // let server handle mount

        if (!player.isPassenger()) {
            player.startRiding(this, true);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return passenger instanceof Player;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() == null ? null : (LivingEntity) this.getFirstPassenger();
    }

    @Override
    protected void positionRider(Entity passenger, Entity.MoveFunction callback) {
        if (this.hasPassenger(passenger)) {
            float sidewaysOffset = 0.0f;

            // LOWER THE PASSENGER BY 3 PIXELS = 0.1875 BLOCKS
            float yOffset =
                    (float)((this.isRemoved() ? 0.01F : this.getPassengersRidingOffset())
                            + passenger.getMyRidingOffset()
                            - 0.1875F*2); // <--- LOWERING HERE

            // Handle multi-passenger offsets
            if (this.getPassengers().size() > 1) {
                int index = this.getPassengers().indexOf(passenger);
                if (index == 0) {
                    sidewaysOffset = 0.2F;
                } else {
                    sidewaysOffset = -0.6F;
                    yOffset =
                            (float)((this.isRemoved() ? 0.01F : this.getPassengersRidingOffset())
                                    + passenger.getMyRidingOffset()
                                    - 0.1875F);
                }

                if (passenger instanceof Animal) {
                    sidewaysOffset += 0.2F;
                }
            }

            // Calculate rotated seat offset
            Vec3 seatOffset = new Vec3((double)sidewaysOffset, 0.0D, 0.0D)
                    .yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));

            // Apply the new seat position with lowered Y
            callback.accept(
                    passenger,
                    this.getX() + seatOffset.x,
                    this.getY() + (double)yOffset,
                    this.getZ() + seatOffset.z
            );

            // Sync passenger rotation
//            passenger.setYRot(passenger.getYRot() + this.deltaRotation);
//            passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
//            this.clampRotation(passenger);


        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.level().isClientSide || this.isRemoved()) {
            return true;
        }

        // Ignore certain damage sources if you want (like creative players)
        if (source.isCreativePlayer()) {
            // insta-break if creative? or ignore; here: insta-break
            this.destroy(source);
            return true;
        }

        // mark hurt direction based on attacker position
        int hurtDir = 1;
        if (source.getEntity() != null) {
            hurtDir = (int) Math.signum(source.getEntity().getX() - this.getX());
        }
        this.setHurtDir(hurtDir);

        // set hurt timer (visual)
        this.setHurtTime(10);

        // accumulate damage
        float newDamage = this.getDamage() + amount * 10.0F; // scale as you want
        this.setDamage(newDamage);

        // play sound and optionally knockback
        //this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BOAT_HIT, this.getSoundSource(), 1.0F, 1.0F);

        // break threshold (similar to boat: ~40)
        if (newDamage > 40.0F) {
            this.destroy(source);
        }

        return true;
    }

    protected void destroy(DamageSource source) {
        // Drop item and remove entity
        if (!this.level().isClientSide) {
            // Eject passengers nicely
            this.ejectPassengers();

            // Play break sound
            //this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents., this.getSoundSource(), 1.0F, 1.0F);

            // Drop an item - REPLACE Items.MINECART with your mod's wagon item
            ItemStack drop = new ItemStack(ModItems.PATTY_WAGON.get()); // <-- replace with your item
            this.spawnAtLocation(drop);

            // Finally remove the entity
            this.discard();
        }
    }


    @Override
    public boolean isVehicle() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

//    @Override
//    public boolean canBeRiddenInWater() {
//        return true;
//    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        // Candidate offsets (around the wagon)
        Vec3[] offsets = new Vec3[]{
                new Vec3(1, 0, 0),
                new Vec3(-1, 0, 0),
                new Vec3(0, 0, 1),
                new Vec3(0, 0, -1),
                new Vec3(1, 0, 1),
                new Vec3(-1, 0, 1),
                new Vec3(1, 0, -1),
                new Vec3(-1, 0, -1)
        };

        BlockPos base = this.blockPosition();

        for (Vec3 off : offsets) {
            BlockPos pos = base.offset((int) off.x, 0, (int) off.z);

            // Check if the spot is safe
            if (level().getBlockState(pos).getCollisionShape(level(), pos).isEmpty() &&
                    level().getBlockState(pos.above()).getCollisionShape(level(), pos.above()).isEmpty()) {

                return new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            }
        }

        // Fallback — dismount on top
        return new Vec3(this.getX(), this.getY() + 1, this.getZ());
    }

    @Override
    protected AABB makeBoundingBox() {
        return super.makeBoundingBox();
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
}
