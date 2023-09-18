package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EntityTagGenerator extends EntityTypeTagsProvider {
    public EntityTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator, EarthMobsMod.MODID, exFileHelper);
	}

	@Override
    protected void addTags() {
		this.tag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(ModEntities.BOULDERING_DROWNED.get(), ModEntities.LOBBER_DROWNED.get());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(ModEntities.STRAY_BONE_SPIDER.get(), ModEntities.MELON_GOLEM.get(), ModEntities.MAGMA_COW.get(), ModEntities.UMBRA_COW.get(), ModEntities.WOOLY_COW.get());
        this.tag(EntityTypeTags.FROG_FOOD).add(ModEntities.TROPICAL_SLIME.get());
		this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(ModEntities.BONE_SHARD.get(), ModEntities.MELON_SEED.get());
		this.tag(EntityTypeTags.RAIDERS).add(ModEntities.VILER_WITCH.get());
        this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(ModEntities.HYPER_RABBIT.get());
	}
}