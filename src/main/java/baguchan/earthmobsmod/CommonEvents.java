package baguchan.earthmobsmod;

import baguchan.earthmobsmod.block.CarvedMelonBlock;
import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchan.earthmobsmod.entity.*;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID)
public class CommonEvents {
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(ShadowCapability.class);
	}

	@SubscribeEvent
	public void onEntityJoinWorld(MobSpawnEvent.FinalizeSpawn event) {
		if (event.getEntity() instanceof final AbstractVillager villager) {
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, BoulderingDrowned.class, 8.0F, 0.8D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, BoulderingZombie.class, 8.0F, 0.8D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, LobberDrowned.class, 8.0F, 0.8D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, LobberZombie.class, 8.0F, 0.8D, 0.6D));
		}
	}

	@SubscribeEvent
	public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof LivingEntity) {
			event.addCapability(new ResourceLocation(EarthMobsMod.MODID, "shadow"), new ShadowCapability());
		}
	}

	@SubscribeEvent
	public static void onRightClickBlock(BlockEvent.EntityPlaceEvent event) {

	}

	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		InteractionHand hand = event.getHand();
		ItemStack itemStack = event.getEntity().getItemInHand(hand);
		BlockPos pos = event.getPos();

		Level level = event.getEntity().level();
		if (itemStack.getItem() instanceof ShearsItem && event.getEntity().level().getBlockState(pos).getBlock() == Blocks.MELON) {
			Direction direction = event.getHitVec().getDirection();
			if (direction != Direction.DOWN && direction != Direction.UP) {
				itemStack.hurtAndBreak(1, event.getEntity(), (p_29910_) -> {
					p_29910_.broadcastBreakEvent(hand);
				});
				level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);

				level.setBlock(pos, ModBlocks.CARVED_MELON.get().defaultBlockState().setValue(CarvedMelonBlock.FACING, direction), 2);

				event.setUseItem(Event.Result.ALLOW);
			}
		}
		if (itemStack.is(Blocks.CARVED_PUMPKIN.asItem())) {
			BlockPattern.BlockPatternMatch blockpattern$blockpatternmatch1 = getOrCreateFurnaceGolemBase().find(level, pos.relative(event.getFace()));
			if (blockpattern$blockpatternmatch1 != null) {
				FurnaceGolem irongolem = ModEntities.FURNACE_GOLEM.get().create(level);
				if (irongolem != null) {
					spawnGolemInWorld(level, blockpattern$blockpatternmatch1, irongolem, blockpattern$blockpatternmatch1.getBlock(1, 2, 0).getPos());
				}
				if (!event.getEntity().isCreative()) {
					itemStack.shrink(1);
				}
				event.getEntity().swing(hand);
				event.setCanceled(true);
			}

		}
	}

	public static void clearPatternBlocks(Level p_249604_, BlockPattern.BlockPatternMatch p_251190_) {
		for (int i = 0; i < p_251190_.getWidth(); ++i) {
			for (int j = 0; j < p_251190_.getHeight(); ++j) {
				BlockInWorld blockinworld = p_251190_.getBlock(i, j, 0);
				p_249604_.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
				p_249604_.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
			}
		}

	}

	public static void updatePatternBlocks(Level p_248711_, BlockPattern.BlockPatternMatch p_251935_) {
		for (int i = 0; i < p_251935_.getWidth(); ++i) {
			for (int j = 0; j < p_251935_.getHeight(); ++j) {
				BlockInWorld blockinworld = p_251935_.getBlock(i, j, 0);
				p_248711_.blockUpdated(blockinworld.getPos(), Blocks.AIR);
			}
		}

	}

	private static BlockPattern getOrCreateFurnaceGolemBase() {
		return BlockPatternBuilder.start().aisle("   ", "SFS", " # ").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('F', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.BLAST_FURNACE))).where('S', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SMOOTH_STONE))).build();
	}

	@SubscribeEvent
	public static void onUpdate(LivingEvent.LivingTickEvent event) {
		event.getEntity().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			shadowCapability.tick(event.getEntity());
		});
	}

	@SubscribeEvent
	public static void onLightning(EntityStruckByLightningEvent event) {
		if (event.getEntity() instanceof Pig pig) {
			if (event.getEntity().getType() != ModEntities.ZOMBIFIED_PIG.get()) {
				ZombifiedPig zombifiedpig = ModEntities.ZOMBIFIED_PIG.get().create(event.getEntity().level());
				zombifiedpig.moveTo(pig.getX(), pig.getY(), pig.getZ(), pig.getYRot(), pig.getXRot());
				zombifiedpig.setNoAi(pig.isNoAi());
				zombifiedpig.setBaby(pig.isBaby());
				if (pig.hasCustomName()) {
					zombifiedpig.setCustomName(pig.getCustomName());
					zombifiedpig.setCustomNameVisible(pig.isCustomNameVisible());
				}

				zombifiedpig.setPersistenceRequired();
				net.minecraftforge.event.ForgeEventFactory.onLivingConvert(pig, zombifiedpig);
				event.getEntity().level().addFreshEntity(zombifiedpig);
				pig.discard();
				event.setCanceled(true);
			} else {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		event.getEntity().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			if (shadowCapability.getPercentBoost() >= 0.5F && !event.getSource().is(DamageTypeTags.BYPASSES_ARMOR) && !event.getSource().is(DamageTypeTags.IS_EXPLOSION) && !event.getSource().is(DamageTypeTags.IS_FIRE)) {
				event.setAmount(event.getAmount() * (1.0F - shadowCapability.getPercentBoost()));
				if (shadowCapability.getPercentBoost() > 0.9F) {
					event.setCanceled(true);
				}
			}
		});
	}

	@SubscribeEvent
	public static void onLivingKnockback(LivingKnockBackEvent event) {
		event.getEntity().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			if (shadowCapability.getPercentBoost() >= 0.5F) {
				event.setCanceled(true);
			}
		});
	}

	private static void spawnGolemInWorld(Level p_249110_, BlockPattern.BlockPatternMatch p_251293_, Entity p_251251_, BlockPos p_251189_) {
		clearPatternBlocks(p_249110_, p_251293_);
		p_251251_.moveTo((double) p_251189_.getX() + 0.5D, (double) p_251189_.getY() + 0.05D, (double) p_251189_.getZ() + 0.5D, 0.0F, 0.0F);
		p_249110_.addFreshEntity(p_251251_);

		for (ServerPlayer serverplayer : p_249110_.getEntitiesOfClass(ServerPlayer.class, p_251251_.getBoundingBox().inflate(5.0D))) {
			CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, p_251251_);
		}

		updatePatternBlocks(p_249110_, p_251293_);
	}


}
