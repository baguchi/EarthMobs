package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.api.IMuddyPig;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.IShearable;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TeaCupPig extends Pig implements IShearable, Bucketable {
    public TeaCupPig(EntityType<? extends Pig> p_29462_, Level p_29463_) {
        super(p_29462_, p_29463_);
    }

    @Override
    public float getVoicePitch() {
        return this.isBaby() ? (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F : (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.35F;
    }


    @Nullable
    @Override
    public TeaCupPig getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
        return ModEntities.TEACUP_PIG.get().create(p_149001_, EntitySpawnReason.BREEDING);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.24D);
    }

    public InteractionResult mobInteract(Player p_149155_, InteractionHand p_149156_) {
        return bucketMobPickup(p_149155_, p_149156_, this).orElse(super.mobInteract(p_149155_, p_149156_));
    }

    static <T extends LivingEntity & Bucketable> Optional<InteractionResult> bucketMobPickup(Player p_148829_, InteractionHand p_148830_, T p_148831_) {
        ItemStack itemstack = p_148829_.getItemInHand(p_148830_);
        if (itemstack.getItem() == Items.FLOWER_POT && p_148831_.isAlive()) {
            p_148831_.playSound(p_148831_.getPickupSound(), 1.0F, 1.0F);
            ItemStack itemstack1 = p_148831_.getBucketItemStack();
            p_148831_.saveToBucketTag(itemstack1);
            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, p_148829_, itemstack1, false);
            p_148829_.setItemInHand(p_148830_, itemstack2);
            Level level = p_148831_.level();
            if (!level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) p_148829_, itemstack1);
            }

            p_148831_.discard();
            return Optional.of(InteractionResult.SUCCESS);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean fromBucket() {
        return false;
    }

    @Override
    public void setFromBucket(boolean p_148834_) {

    }

    public void saveToBucketTag(ItemStack p_149187_) {
        Bucketable.saveDefaultDataToBucketTag(this, p_149187_);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, p_149187_, compoundtag -> {
            compoundtag.putInt("Age", this.getAge());
            if (this instanceof IMuddyPig muddy) {
                compoundtag.putBoolean("Muddy", muddy.isMuddy());
            }
        });
    }

    public void loadFromBucketTag(CompoundTag p_149163_) {
        Bucketable.loadDefaultDataFromBucketTag(this, p_149163_);
        if (p_149163_.contains("Age")) {
            this.setAge(p_149163_.getInt("Age"));
        }
        if (this instanceof IMuddyPig muddy) {
            if (p_149163_.contains("Muddy")) {
                muddy.setMuddy(p_149163_.getBoolean("Muddy"));
            }
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.TEACUP_PIG_POT.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.ARMOR_EQUIP_GENERIC.value();
    }

    @Override
    public boolean isShearable(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
        return false;
    }
}
