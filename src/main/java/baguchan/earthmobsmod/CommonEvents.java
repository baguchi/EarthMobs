package baguchan.earthmobsmod;

import baguchan.earthmobsmod.api.IMoss;
import baguchan.earthmobsmod.api.IMuddyPig;
import baguchan.earthmobsmod.block.CarvedMelonBlock;
import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchan.earthmobsmod.entity.FurnaceGolem;
import baguchan.earthmobsmod.entity.ZombifiedPig;
import baguchan.earthmobsmod.entity.ZombifiedRabbit;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModCapability;
import baguchan.earthmobsmod.registry.ModDamageSource;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.util.DyeUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.ToolActions;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = EarthMobsMod.MODID)
public class CommonEvents {

	@SubscribeEvent
	public static void addSpawn(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Villager abstractVillager) {

            abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, ZombifiedRabbit.class, 10.0F, 0.65F, 0.7F));
		}

        if (event.getEntity() instanceof WanderingTrader wanderingTraderEntity) {

            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, ZombifiedRabbit.class, 10.0F, 0.65F, 0.7F));
		}
	}

	@SubscribeEvent
	public static void initSpawn(MobSpawnEvent.FinalizeSpawn event) {
		Mob entity = event.getEntity();
		ServerLevelAccessor serverLevelAccessor = event.getLevel();
		if (entity instanceof IMoss moss) {
			if (serverLevelAccessor.getBiome(BlockPos.containing(event.getX(), event.getY(), event.getZ())).is(Tags.Biomes.IS_SWAMP)) {
				moss.setMoss(true);
			}
		}
        if (entity instanceof IMuddyPig muddy) {
			if (serverLevelAccessor.getBiome(BlockPos.containing(event.getX(), event.getY(), event.getZ())).is(Tags.Biomes.IS_SWAMP)) {
				muddy.setMuddy(true);
                byte b0 = muddy.getColorData();
                muddy.setColorData((byte) (b0 & 240 | DyeUtil.getRandomColor(serverLevelAccessor.getRandom()).getId() & 15));
			}
		}
	}

	@SubscribeEvent
	public static void onToolUsing(BlockEvent.BlockToolModificationEvent event) {
		if (!event.isSimulated()) {
			if (event.getToolAction() == ToolActions.SHEARS_CARVE && event.getState().getBlock() == Blocks.MELON) {
				Direction direction = event.getContext().getClickedFace();
				if (direction != Direction.DOWN && direction != Direction.UP) {
					event.setFinalState(ModBlocks.CARVED_MELON.get().defaultBlockState().setValue(CarvedMelonBlock.FACING, direction));
				}
			}
		}
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
				itemStack.hurtAndBreak(1, event.getEntity(), LivingEntity.getSlotForHand(hand));
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
		return BlockPatternBuilder.start().aisle("   ", "#F#", " # ").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('F', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.BLAST_FURNACE))).build();
	}

	@SubscribeEvent
	public static void onUpdate(EntityTickEvent.Pre event) {
		ShadowCapability shadowCapability = event.getEntity().getData(ModCapability.SHADOW_ATTACH);
		if (shadowCapability != null && event.getEntity() instanceof LivingEntity livingEntity) {
			shadowCapability.tick(livingEntity);
		}
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
                EventHooks.onLivingConvert(pig, zombifiedpig);
				event.getEntity().level().addFreshEntity(zombifiedpig);
				pig.discard();
				event.setCanceled(true);
			} else {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        LivingEntity living = event.getEntity();
        Level level = event.getEntity().level();
        if (event.getSource().getDirectEntity() instanceof Zombie || event.getSource().getDirectEntity() instanceof ZombifiedRabbit) {
			if (living instanceof Rabbit rabbit && !(living instanceof Enemy)) {
                if (level instanceof ServerLevel serverLevel) {
                    ZombifiedRabbit zombierabbit = rabbit.convertTo(ModEntities.ZOMBIFIED_RABBIT.get(), false);
                    if (zombierabbit != null) {
						zombierabbit.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(zombierabbit.blockPosition()), MobSpawnType.CONVERSION, null);
                        zombierabbit.setVariant(rabbit.getVariant());
                        EventHooks.onLivingConvert(rabbit, zombierabbit);
                        if (!rabbit.isSilent()) {
                            level.levelEvent(null, 1026, rabbit.blockPosition(), 0);
                        }
                    }
                }
            }
        }

		if (event.getSource().is(ModDamageSource.ZOMBIFIED)) {
			if (living instanceof Rabbit rabbit && !(living instanceof Enemy)) {
				ZombifiedRabbit zombifiedRabbit = rabbit.convertTo(ModEntities.ZOMBIFIED_RABBIT.get(), false);
				if (zombifiedRabbit != null) {
					zombifiedRabbit.setVariant(rabbit.getVariant());
					if (!rabbit.isSilent()) {
                        level.levelEvent(null, 1026, rabbit.blockPosition(), 0);
					}
				}
			}

			if (living instanceof Villager villager && !(living instanceof Enemy)) {
				ZombieVillager zombieVillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
				if (zombieVillager != null) {
					zombieVillager.setVariant(villager.getVariant());
					zombieVillager.setVillagerData(villager.getVillagerData());
					zombieVillager.setVillagerXp(villager.getVillagerXp());
					zombieVillager.setGossips(villager.getGossips().store(NbtOps.INSTANCE));
					zombieVillager.setTradeOffers(villager.getOffers().copy());
					if (!villager.isSilent()) {
                        level.levelEvent(null, 1026, villager.blockPosition(), 0);
					}
				}
			}
		}
    }

    @SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		ShadowCapability shadowCapability = event.getEntity().getData(ModCapability.SHADOW_ATTACH);
		if (shadowCapability != null) {
			if (shadowCapability.getPercentBoost() >= 0.5F && !event.getSource().is(DamageTypeTags.BYPASSES_ARMOR) && !event.getSource().is(DamageTypeTags.IS_EXPLOSION) && !event.getSource().is(DamageTypeTags.IS_FIRE)) {
				event.setAmount(event.getAmount() * (1.0F - shadowCapability.getPercentBoost()));
				if (shadowCapability.getPercentBoost() > 0.9F) {
					event.setCanceled(true);
				}
			}
		}
	}


	@SubscribeEvent
	public static void onLivingKnockback(LivingKnockBackEvent event) {
		ShadowCapability shadowCapability = event.getEntity().getData(ModCapability.SHADOW_ATTACH);
		if (shadowCapability != null) {

			if (shadowCapability.getPercentBoost() >= 0.5F) {
				event.setCanceled(true);
			}
		}
	}

    private static void spawnGolemInWorld(Level level, BlockPattern.BlockPatternMatch blockPatternMatch, Entity summoner, BlockPos pos) {
        clearPatternBlocks(level, blockPatternMatch);
        summoner.moveTo((double) pos.getX() + 0.5D, (double) pos.getY() + 0.05D, (double) pos.getZ() + 0.5D, 0.0F, 0.0F);
        level.addFreshEntity(summoner);

        for (ServerPlayer serverplayer : level.getEntitiesOfClass(ServerPlayer.class, summoner.getBoundingBox().inflate(5.0D))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, summoner);
		}

        updatePatternBlocks(level, blockPatternMatch);
	}


}
