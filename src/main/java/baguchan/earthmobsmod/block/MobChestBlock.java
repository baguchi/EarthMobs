package baguchan.earthmobsmod.block;

import baguchan.earthmobsmod.blockentity.MobChestBlockEntity;
import baguchan.earthmobsmod.registry.ModBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public abstract class MobChestBlock extends AbstractChestBlock<MobChestBlockEntity> implements SimpleWaterloggedBlock {
    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public MobChestBlock(BlockBehaviour.Properties p_53121_) {
        super(p_53121_, ModBlockEntitys.MOB_CHEST);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }


    @Override
    protected MapCodec<? extends AbstractChestBlock<MobChestBlockEntity>> codec() {
        return null;
    }

    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combine(
            BlockState p_53149_, Level p_53150_, BlockPos p_53151_, boolean p_53152_
    ) {
        return DoubleBlockCombiner.Combiner::acceptNone;
    }

    @Override
    public VoxelShape getShape(BlockState p_53171_, BlockGetter p_53172_, BlockPos p_53173_, CollisionContext p_53174_) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_53169_) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_53128_) {
        FluidState fluidstate = p_53128_.getLevel().getFluidState(p_53128_.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, p_53128_.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    public InteractionResult use(BlockState p_53137_, Level p_53138_, BlockPos p_53139_, Player p_53140_, InteractionHand p_53141_, BlockHitResult p_53142_) {
        BlockEntity blockentity = p_53138_.getBlockEntity(p_53139_);
        if (blockentity instanceof MobChestBlockEntity) {
            BlockPos blockpos = p_53139_.above();
            if (p_53138_.getBlockState(blockpos).isRedstoneConductor(p_53138_, blockpos)) {
                return InteractionResult.sidedSuccess(p_53138_.isClientSide);
            } else if (p_53138_.isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                MobChestBlockEntity chestBlockEntity = (MobChestBlockEntity) blockentity;
                p_53140_.openMenu(
                        chestBlockEntity
                );
                p_53140_.awardStat(Stats.OPEN_CHEST);
                PiglinAi.angerNearbyPiglins(p_53140_, true);
                return InteractionResult.CONSUME;
            }
        } else {
            return InteractionResult.sidedSuccess(p_53138_.isClientSide);
        }
    }

    @Override
    public void onRemove(BlockState p_51538_, Level p_51539_, BlockPos p_51540_, BlockState p_51541_, boolean p_51542_) {
        Containers.dropContentsOnDestroy(p_51538_, p_51541_, p_51539_, p_51540_);
        super.onRemove(p_51538_, p_51539_, p_51540_, p_51541_, p_51542_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153208_, BlockState p_153209_) {
        return new MobChestBlockEntity(p_153208_, p_153209_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153199_, BlockState p_153200_, BlockEntityType<T> p_153201_) {
        return p_153199_.isClientSide ? createTickerHelper(p_153201_, ModBlockEntitys.MOB_CHEST.get(), MobChestBlockEntity::lidAnimateTick) : null;
    }

    @Override
    public BlockState rotate(BlockState p_53157_, Rotation p_53158_) {
        return p_53157_.setValue(FACING, p_53158_.rotate(p_53157_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_53154_, Mirror p_53155_) {
        return p_53154_.rotate(p_53155_.getRotation(p_53154_.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_53167_) {
        p_53167_.add(FACING, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState p_53177_) {
        return p_53177_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_53177_);
    }

    @Override
    public BlockState updateShape(BlockState p_53160_, Direction p_53161_, BlockState p_53162_, LevelAccessor p_53163_, BlockPos p_53164_, BlockPos p_53165_) {
        if (p_53160_.getValue(WATERLOGGED)) {
            p_53163_.scheduleTick(p_53164_, Fluids.WATER, Fluids.WATER.getTickDelay(p_53163_));
        }

        return super.updateShape(p_53160_, p_53161_, p_53162_, p_53163_, p_53164_, p_53165_);
    }

    @Override
    public boolean isPathfindable(BlockState p_53132_, BlockGetter p_53133_, BlockPos p_53134_, PathComputationType p_53135_) {
        return false;
    }

    @Override
    public void tick(BlockState p_221112_, ServerLevel p_221113_, BlockPos p_221114_, RandomSource p_221115_) {
        BlockEntity blockentity = p_221113_.getBlockEntity(p_221114_);
        if (blockentity instanceof MobChestBlockEntity) {
            ((MobChestBlockEntity) blockentity).recheckOpen();
        }
    }

    public abstract ResourceLocation getTexture();
}