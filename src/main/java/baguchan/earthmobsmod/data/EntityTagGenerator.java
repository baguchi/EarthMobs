package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
	public EntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper exFileHelper) {
		super(output, lookupProvider, EarthMobsMod.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		this.tag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(ModEntities.BOULDERING_DROWNED.get(), ModEntities.LOBBER_DROWNED.get());
        this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(ModEntities.STRAY_BONE_SPIDER.get(), ModEntities.MELON_GOLEM.get(), ModEntities.MAGMA_COW.get());
        this.tag(EntityTypeTags.FROG_FOOD).add(ModEntities.TROPICAL_SLIME.get());
		this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(ModEntities.BONE_SHARD.get(), ModEntities.MELON_SEED.get());
		this.tag(EntityTypeTags.RAIDERS).add(ModEntities.VILER_WITCH.get());
        this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(ModEntities.HYPER_RABBIT.get());
		this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModEntities.CLUCK_SHROOM.get(), ModEntities.FANCY_CHICKEN.get(), ModEntities.DUCK.get(), ModEntities.FURNACE_GOLEM.get(), ModEntities.MELON_GOLEM.get());
	}
}