package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityTypes;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
	public EntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, EarthMobsMod.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		this.tag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(ModEntities.BOULDERING_DROWNED.key(), ModEntities.LOBBER_DROWNED.key());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(ModEntities.MELON_GOLEM.key(), ModEntities.MAGMA_COW.key(), ModEntities.UMBRA_COW.key(), ModEntities.WOOLY_COW.key());
		this.tag(EntityTypeTags.FROG_FOOD).add(ModEntities.TROPICAL_SLIME.key());
		this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(ModEntities.BONE_SHARD.key(), ModEntities.MELON_SEED.key());
		this.tag(EntityTypeTags.RAIDERS).add(ModEntities.VILER_WITCH.key());
		this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(ModEntities.HYPER_RABBIT.key(), ModEntities.ZOMBIFIED_RABBIT.key());
		this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModEntities.CLUCK_SHROOM.key(), ModEntities.FANCY_CHICKEN.key(), ModEntities.FURNACE_GOLEM.key(), ModEntities.MELON_GOLEM.key());
		this.tag(EntityTypeTags.ZOMBIES).add(ModEntities.ZOMBIFIED_RABBIT.key(), ModEntities.ZOMBIFIED_PIG.key(), ModEntities.BOULDERING_ZOMBIE.key(), ModEntities.BOULDERING_DROWNED.key(), ModEntities.LOBBER_ZOMBIE.key(), ModEntities.LOBBER_DROWNED.key())
				.add(ModEntities.HUSK_RABBIT.key());
		this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(ModEntities.TROPICAL_SLIME.key());
		this.tag(ModTags.Entities.CAN_MUDDY).add(EntityTypes.PIG.builtInRegistryHolder().key()).add(ModEntities.TEACUP_PIG.key()).add(ModEntities.ZOMBIFIED_PIG.key());
		this.tag(ModTags.Entities.CAN_MOSS).add(EntityTypes.SHEEP.builtInRegistryHolder().key()).add(ModEntities.HORNED_SHEEP.key());
		this.tag(EntityTypeTags.UNDEAD).add(ModEntities.BONE_SPIDER.key())
				.add(ModEntities.BOULDERING_ZOMBIE.key(), ModEntities.LOBBER_ZOMBIE.key())
				.add(ModEntities.BOULDERING_DROWNED.key(), ModEntities.LOBBER_DROWNED.key())
				.add(ModEntities.ZOMBIFIED_RABBIT.key())
				.add(ModEntities.HUSK_RABBIT.key())
				.add(ModEntities.ZOMBIFIED_PIG.key())
				.add(ModEntities.SKELETON_WOLF.key())
				.add(ModEntities.WITHER_SKELETON_WOLF.key());
        this.tag(EntityTypeTags.BURN_IN_DAYLIGHT)
				.add(ModEntities.BOULDERING_ZOMBIE.key(), ModEntities.LOBBER_ZOMBIE.key())
				.add(ModEntities.BOULDERING_DROWNED.key(), ModEntities.LOBBER_DROWNED.key())
				.add(ModEntities.ZOMBIFIED_RABBIT.key());
		this.tag(EntityTypeTags.ARTHROPOD).add(ModEntities.BONE_SPIDER.key());
	}
}