package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
	public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, EarthMobsMod.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(Tags.Items.FEATHERS).add(ModItems.FANCY_FEATHER.get());
		tag(Tags.Items.EGGS).add(ModItems.SMELLY_EGG.get(), ModItems.FANCY_EGG.get());
		tag(ItemTags.MEAT).add(ModItems.HARDER_FLESH.get());
        tag(ItemTags.CAMEL_HUSK_FOOD).add(ModItems.ZOMBIFIED_RABBIT_FOOT.get(), ModItems.HUSK_RABBIT_FOOT.get());
	}


    @Override
    protected Appender tag(TagKey<Item> tag) {
        return new Appender(super.tag(tag));
    }

    protected record Appender(TagAppender<Item> app) implements TagAppender<Item> {
        @Override
        public Appender add(ResourceKey<Item> element) {
            app.add(element);
            return this;
        }

        @Override
        public Appender addOptional(ResourceKey<Item> element) {
            app.addOptional(element);
            return this;
        }

        @Override
        public Appender addTag(TagKey<Item> tag) {
            app.addTag(tag);
            return this;
        }

        @Override
        public Appender addOptionalTag(TagKey<Item> tag) {
            app.addOptionalTag(tag);
            return this;
        }

        @Override
        public Appender add(TagEntry entry) {
            app.add(entry);
            return this;
        }

        @Override
        public Appender replace(boolean value) {
            app.replace(value);
            return this;
        }

        @Override
        public Appender remove(ResourceKey<Item> element) {
            app.remove(element);
            return this;
        }

        @Override
        public Appender remove(TagKey<Item> tag) {
            app.remove(tag);
            return this;
        }

        public Appender add(Item... items) {
            for (Item item : items) {
                add(BuiltInRegistries.ITEM.wrapAsHolder(item).getKey());
            }
            return this;
        }
    }
}