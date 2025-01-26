package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
	public EntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, EarthMobsMod.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		this.tag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(ModEntities.BOULDERING_DROWNED.get(), ModEntities.LOBBER_DROWNED.get());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(ModEntities.STRAY_BONE_SPIDER.get(), ModEntities.MELON_GOLEM.get(), ModEntities.MAGMA_COW.get(), ModEntities.UMBRA_COW.get(), ModEntities.WOOLY_COW.get(), ModEntities.BOULDERING_FROZEN_ZOMBIE.get());
        this.tag(EntityTypeTags.FROG_FOOD).add(ModEntities.TROPICAL_SLIME.get());
		this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(ModEntities.BONE_SHARD.get(), ModEntities.MELON_SEED.get());
		this.tag(EntityTypeTags.RAIDERS).add(ModEntities.VILER_WITCH.get());
        this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(ModEntities.HYPER_RABBIT.get(), ModEntities.ZOMBIFIED_RABBIT.get());
		this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModEntities.CLUCK_SHROOM.get(), ModEntities.FANCY_CHICKEN.get(), ModEntities.FURNACE_GOLEM.get(), ModEntities.MELON_GOLEM.get());
        this.tag(EntityTypeTags.ZOMBIES).add(ModEntities.ZOMBIFIED_RABBIT.get(), ModEntities.ZOMBIFIED_PIG.get(), ModEntities.BOULDERING_ZOMBIE.get(), ModEntities.BOULDERING_DROWNED.get(), ModEntities.LOBBER_ZOMBIE.get(), ModEntities.LOBBER_DROWNED.get())
                .add(ModEntities.BOULDERING_FROZEN_ZOMBIE.get(), ModEntities.LOBBER_HUSK.get());
        this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(ModEntities.TROPICAL_SLIME.get());
		this.tag(ModTags.Entities.CAN_MUDDY).add(EntityType.PIG).add(ModEntities.TEACUP_PIG.get());
        this.tag(ModTags.Entities.CAN_MOSS).add(EntityType.SHEEP).add(ModEntities.HORNED_SHEEP.get());
		this.tag(EntityTypeTags.UNDEAD).add(ModEntities.BONE_SPIDER.get(), ModEntities.STRAY_BONE_SPIDER.get())
				.add(ModEntities.BOULDERING_ZOMBIE.get(), ModEntities.LOBBER_ZOMBIE.get())
				.add(ModEntities.BOULDERING_DROWNED.get(), ModEntities.LOBBER_DROWNED.get())
                .add(ModEntities.BOULDERING_FROZEN_ZOMBIE.get(), ModEntities.LOBBER_HUSK.get())
				.add(ModEntities.ZOMBIFIED_RABBIT.get())
				.add(ModEntities.ZOMBIFIED_PIG.get())
				.add(ModEntities.SKELETON_WOLF.get())
				.add(ModEntities.WITHER_SKELETON_WOLF.get());
		this.tag(EntityTypeTags.ARTHROPOD).add(ModEntities.BONE_SPIDER.get(), ModEntities.STRAY_BONE_SPIDER.get());
	}
}