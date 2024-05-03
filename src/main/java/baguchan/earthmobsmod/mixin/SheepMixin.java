package baguchan.earthmobsmod.mixin;

import bagu_chan.bagus_lib.api.IBaguPacket;
import baguchan.earthmobsmod.api.IMoss;
import baguchan.earthmobsmod.message.MossMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Sheep.class)
public abstract class SheepMixin extends Animal implements IMoss, IBaguPacket {
    private boolean moss;

    protected SheepMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }
    @Override
    public boolean isMoss() {
        return this.moss;
    }

    @Override
    public void setMoss(boolean moss) {
        this.moss = moss;
        this.resync(this);
    }

    @Override
    public void resync(Entity entity) {
        if (!this.level().isClientSide) {
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new MossMessage(this.getId(), this.moss));
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundTag p_27587_, CallbackInfo callbackInfo) {
        p_27587_.putBoolean("Moss", this.isMoss());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag p_27576_, CallbackInfo callbackInfo) {
        this.setMoss(p_27576_.getBoolean("Moss"));
    }

    @Inject(method = "shear", at = @At("RETURN"))
    public void onSheared(SoundSource p_29819_, CallbackInfo ci) {
        ItemEntity itementity = this.spawnAtLocation(Items.MOSS_BLOCK.getDefaultInstance(), 1);
        if (itementity != null) {
            itementity.setDeltaMovement(itementity.getDeltaMovement().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
        }
    }
}
