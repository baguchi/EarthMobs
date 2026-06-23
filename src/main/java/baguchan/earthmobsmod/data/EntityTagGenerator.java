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
		this.tag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(ModEntities.BOULDERING_DROWNED.getKey(), ModEntities.LOBBER_DROWNED.getKey());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(ModEntities.MELON_GOLEM.getKey(), ModEntities.MAGMA_COW.getKey(), ModEntities.UMBRA_COW.getKey(), ModEntities.WOOLY_COW.getKey());
		this.tag(EntityTypeTags.FROG_FOOD).add(ModEntities.TROPICAL_SLIME.getKey());
		this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(ModEntities.BONE_SHARD.getKey(), ModEntities.MELON_SEED.getKey());
		this.tag(EntityTypeTags.RAIDERS).add(ModEntities.VILER_WITCH.getKey());
		this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(ModEntities.HYPER_RABBIT.getKey(), ModEntities.ZOMBIFIED_RABBIT.getKey());
		this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModEntities.CLUCK_SHROOM.getKey(), ModEntities.FANCY_CHICKEN.getKey(), ModEntities.FURNACE_GOLEM.getKey(), ModEntities.MELON_GOLEM.getKey());
		this.tag(EntityTypeTags.ZOMBIES).add(ModEntities.ZOMBIFIED_RABBIT.getKey(), ModEntities.ZOMBIFIED_PIG.getKey(), ModEntities.BOULDERING_ZOMBIE.getKey(), ModEntities.BOULDERING_DROWNED.getKey(), ModEntities.LOBBER_ZOMBIE.getKey(), ModEntities.LOBBER_DROWNED.getKey())
				.add(ModEntities.HUSK_RABBIT.getKey());
		this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(ModEntities.TROPICAL_SLIME.getKey());
		this.tag(ModTags.Entities.CAN_MUDDY).add(EntityTypes.PIG.builtInRegistryHolder().getKey()).add(ModEntities.TEACUP_PIG.getKey()).add(ModEntities.ZOMBIFIED_PIG.getKey());
		this.tag(ModTags.Entities.CAN_MOSS).add(EntityTypes.SHEEP.builtInRegistryHolder().getKey()).add(ModEntities.HORNED_SHEEP.getKey());
		this.tag(EntityTypeTags.UNDEAD).add(ModEntities.BONE_SPIDER.getKey())
				.add(ModEntities.BOULDERING_ZOMBIE.getKey(), ModEntities.LOBBER_ZOMBIE.getKey())
				.add(ModEntities.BOULDERING_DROWNED.getKey(), ModEntities.LOBBER_DROWNED.getKey())
				.add(ModEntities.ZOMBIFIED_RABBIT.getKey())
				.add(ModEntities.HUSK_RABBIT.getKey())
				.add(ModEntities.ZOMBIFIED_PIG.getKey())
				.add(ModEntities.SKELETON_WOLF.getKey())
				.add(ModEntities.WITHER_SKELETON_WOLF.getKey());
        this.tag(EntityTypeTags.BURN_IN_DAYLIGHT)
				.add(ModEntities.BOULDERING_ZOMBIE.getKey(), ModEntities.LOBBER_ZOMBIE.getKey())
				.add(ModEntities.BOULDERING_DROWNED.getKey(), ModEntities.LOBBER_DROWNED.getKey())
				.add(ModEntities.ZOMBIFIED_RABBIT.getKey());
		this.tag(EntityTypeTags.ARTHROPOD).add(ModEntities.BONE_SPIDER.getKey());
	}
}