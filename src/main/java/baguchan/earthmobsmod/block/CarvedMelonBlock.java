package baguchan.earthmobsmod.block;

import baguchan.earthmobsmod.entity.MelonGolem;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class CarvedMelonBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	@Nullable
	private BlockPattern snowGolemBase;
	@Nullable
	private BlockPattern snowGolemFull;

	private static final Predicate<BlockState> MELON_PREDICATE = (p_51396_) -> {
		return p_51396_ != null && (p_51396_.is(ModBlocks.CARVED_MELON.get()) || p_51396_.is(ModBlocks.CARVED_MELON_SHOOT.get()));
	};

	public CarvedMelonBlock(Properties properties) {
		super(properties);

		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	public void onPlace(BlockState p_51387_, Level p_51388_, BlockPos p_51389_, BlockState p_51390_, boolean p_51391_) {
		if (!p_51390_.is(p_51387_.getBlock())) {
			this.trySpawnGolem(p_51388_, p_51389_);
		}
	}

	public boolean canSpawnGolem(LevelReader p_51382_, BlockPos p_51383_) {
		return this.getOrCreateSnowGolemBase().find(p_51382_, p_51383_) != null;
	}

	private void trySpawnGolem(Level p_51379_, BlockPos p_51380_) {
		BlockPattern.BlockPatternMatch blockpattern$blockpatternmatch = this.getOrCreateSnowGolemFull().find(p_51379_, p_51380_);
		if (blockpattern$blockpatternmatch != null) {
			for (int i = 0; i < this.getOrCreateSnowGolemFull().getHeight(); ++i) {
				BlockInWorld blockinworld = blockpattern$blockpatternmatch.getBlock(0, i, 0);
				p_51379_.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
				p_51379_.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
			}

			MelonGolem melongolem = ModEntities.MELON_GOLEM.get().create(p_51379_);
			BlockPos blockpos1 = blockpattern$blockpatternmatch.getBlock(0, 2, 0).getPos();
			melongolem.moveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.05D, (double) blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
			p_51379_.addFreshEntity(melongolem);

			for (ServerPlayer serverplayer : p_51379_.getEntitiesOfClass(ServerPlayer.class, melongolem.getBoundingBox().inflate(5.0D))) {
				CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, melongolem);
			}

			for (int l = 0; l < this.getOrCreateSnowGolemFull().getHeight(); ++l) {
				BlockInWorld blockinworld3 = blockpattern$blockpatternmatch.getBlock(0, l, 0);
				p_51379_.blockUpdated(blockinworld3.getPos(), Blocks.AIR);
			}
		}
	}

	public BlockState getStateForPlacement(BlockPlaceContext p_51377_) {
		return this.defaultBlockState().setValue(FACING, p_51377_.getHorizontalDirection().getOpposite());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51385_) {
		p_51385_.add(FACING);
	}

	private BlockPattern getOrCreateSnowGolemBase() {
		if (this.snowGolemBase == null) {
			this.snowGolemBase = BlockPatternBuilder.start().aisle(" ", "#", "#").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
		}

		return this.snowGolemBase;
	}

	private BlockPattern getOrCreateSnowGolemFull() {
		if (this.snowGolemFull == null) {
			this.snowGolemFull = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', BlockInWorld.hasState(MELON_PREDICATE)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
		}

		return this.snowGolemFull;
	}
}
