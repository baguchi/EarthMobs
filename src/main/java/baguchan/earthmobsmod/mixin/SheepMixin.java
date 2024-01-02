package baguchan.earthmobsmod.mixin;

import bagu_chan.bagus_lib.api.IBaguPacket;
import baguchan.earthmobsmod.api.IMoss;
import baguchan.earthmobsmod.message.MossMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

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
        this.resync(this, this.getId());
    }

    @Override
    public void resync(Entity entity, int i) {
        if (!this.level().isClientSide) {
            PacketDistributor.TRACKING_ENTITY_AND_SELF.with(this).send(new MossMessage(this.getId(), this.moss));
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

    @Inject(method = "onSheared", at = @At("RETURN"), cancellable = true)
    public void onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune, CallbackInfoReturnable<List<ItemStack>> callbackInfoReturnable) {
        List<ItemStack> stacks = callbackInfoReturnable.getReturnValue();
        if (this.isMoss()) {
            stacks.add(Items.MOSS_BLOCK.getDefaultInstance());
        }
        callbackInfoReturnable.setReturnValue(stacks);
    }
}
