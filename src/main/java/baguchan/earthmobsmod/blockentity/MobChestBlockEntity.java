package baguchan.earthmobsmod.blockentity;

import baguchan.earthmobsmod.registry.ModBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MobChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {

    private final ChestLidController chestLidController = new ChestLidController();
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level p_155531_, BlockPos p_155532_, BlockState p_155533_) {
            p_155531_.playSound(
                    null,
                    (double) p_155532_.getX() + 0.5,
                    (double) p_155532_.getY() + 0.5,
                    (double) p_155532_.getZ() + 0.5,
                    SoundEvents.ENDER_CHEST_OPEN,
                    SoundSource.BLOCKS,
                    0.5F,
                    p_155531_.random.nextFloat() * 0.1F + 0.9F
            );
        }

        @Override
        protected void onClose(Level p_155541_, BlockPos p_155542_, BlockState p_155543_) {
            p_155541_.playSound(
                    null,
                    (double) p_155542_.getX() + 0.5,
                    (double) p_155542_.getY() + 0.5,
                    (double) p_155542_.getZ() + 0.5,
                    SoundEvents.ENDER_CHEST_CLOSE,
                    SoundSource.BLOCKS,
                    0.5F,
                    p_155541_.random.nextFloat() * 0.1F + 0.9F
            );
        }

        @Override
        protected void openerCountChanged(Level p_155535_, BlockPos p_155536_, BlockState p_155537_, int p_155538_, int p_155539_) {
            p_155535_.blockEvent(MobChestBlockEntity.this.worldPosition, p_155537_.getBlock(), 1, p_155539_);
        }

        @Override
        protected boolean isOwnContainer(Player p_155355_) {
            if (!(p_155355_.containerMenu instanceof ChestMenu)) {
                return false;
            } else {
                Container container = ((ChestMenu) p_155355_.containerMenu).getContainer();
                return container == MobChestBlockEntity.this
                        || container instanceof CompoundContainer && ((CompoundContainer) container).contains(MobChestBlockEntity.this);
            }
        }
    };

    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);


    public MobChestBlockEntity(BlockPos p_155513_, BlockState p_155514_) {
        super(ModBlockEntitys.MOB_CHEST.get(), p_155513_, p_155514_);
    }

    public static void lidAnimateTick(Level p_155518_, BlockPos p_155519_, BlockState p_155520_, MobChestBlockEntity p_155521_) {
        p_155521_.chestLidController.tickLid();
    }

    @Override
    protected void saveAdditional(CompoundTag p_187459_) {
        super.saveAdditional(p_187459_);
        if (!this.trySaveLootTable(p_187459_)) {
            ContainerHelper.saveAllItems(p_187459_, this.items);
        }
    }

    @Override
    public void load(CompoundTag p_155055_) {
        super.load(p_155055_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_155055_)) {
            ContainerHelper.loadAllItems(p_155055_, this.items);
        }
    }


    @Override
    public boolean triggerEvent(int p_59285_, int p_59286_) {
        if (p_59285_ == 1) {
            this.chestLidController.shouldBeOpen(p_59286_ > 0);
            return true;
        } else {
            return super.triggerEvent(p_59285_, p_59286_);
        }
    }

    public void startOpen(Player p_155516_) {
        if (!this.remove && !p_155516_.isSpectator()) {
            this.openersCounter.incrementOpeners(p_155516_, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void stopOpen(Player p_155523_) {
        if (!this.remove && !p_155523_.isSpectator()) {
            this.openersCounter.decrementOpeners(p_155523_, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.chest");
    }

    public boolean stillValid(Player p_59283_) {
        return Container.stillValidBlockEntity(this, p_59283_);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> p_59625_) {
        this.items = p_59625_;
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public float getOpenNess(float p_59281_) {
        return this.chestLidController.getOpenness(p_59281_);
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_59082_, Inventory p_59083_) {
        return ChestMenu.threeRows(p_59082_, p_59083_, this);
    }
}
