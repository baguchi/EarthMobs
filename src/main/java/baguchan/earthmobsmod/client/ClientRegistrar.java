package baguchan.earthmobsmod.client;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.model.*;
import baguchan.earthmobsmod.client.render.*;
import baguchan.earthmobsmod.client.render.layer.MuddyPigFlowerLayer;
import baguchan.earthmobsmod.client.render.layer.MuddyPigMudLayer;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.CLUCK_SHROOM.get(), CluckShroomRender::new);
        event.registerEntityRenderer(ModEntities.FANCY_CHICKEN.get(), FancyChickenRenderer::new);
        event.registerEntityRenderer(ModEntities.WOOLY_COW.get(), WoolyCowRenderer::new);
        event.registerEntityRenderer(ModEntities.UMBRA_COW.get(), UmbraCowRenderer::new);

        event.registerEntityRenderer(ModEntities.ALBINO_COW.get(), (r) -> new RevampedCowRenderer<>(r, new ResourceLocation(EarthMobsMod.MODID, "textures/entity/cow/albino_cow.png")));
        event.registerEntityRenderer(ModEntities.CREAM_COW.get(), (r) -> new RevampedCowRenderer<>(r, new ResourceLocation(EarthMobsMod.MODID, "textures/entity/cow/cream_cow.png")));

        event.registerEntityRenderer(ModEntities.TEACUP_PIG.get(), TeaCupPigRenderer::new);


        event.registerEntityRenderer(ModEntities.HORNED_SHEEP.get(), HornedSheepRenderer::new);
        event.registerEntityRenderer(ModEntities.HYPER_RABBIT.get(), HyperRabbitRenderer::new);
        event.registerEntityRenderer(ModEntities.MOOBLOOM.get(), MoobloomRenderer::new);
        event.registerEntityRenderer(ModEntities.MOOLIP.get(), MoolipRenderer::new);
        event.registerEntityRenderer(ModEntities.JUMBO_RABBIT.get(), JumboRabbitRenderer::new);
        event.registerEntityRenderer(ModEntities.ZOMBIFIED_PIG.get(), ZombifiedPigRenderer::new);
        event.registerEntityRenderer(ModEntities.DUCK.get(), DuckRenderer::new);

        event.registerEntityRenderer(ModEntities.MELON_GOLEM.get(), MelonGolemRenderer::new);

        event.registerEntityRenderer(ModEntities.BONE_SPIDER.get(), BoneSpiderRender::new);
		event.registerEntityRenderer(ModEntities.STRAY_BONE_SPIDER.get(), StrayBoneSpiderRender::new);
		event.registerEntityRenderer(ModEntities.VILER_WITCH.get(), VilerWitchRenderer::new);
		event.registerEntityRenderer(ModEntities.BOULDERING_ZOMBIE.get(), BoulderingZombieRenderer::new);
		event.registerEntityRenderer(ModEntities.LOBBER_ZOMBIE.get(), LobberZombieRenderer::new);
		event.registerEntityRenderer(ModEntities.BOULDERING_DROWNED.get(), BoulderingDrownedRenderer::new);
		event.registerEntityRenderer(ModEntities.LOBBER_DROWNED.get(), LobberDrownedRenderer::new);

		event.registerEntityRenderer(ModEntities.TROPICAL_SLIME.get(), TropicalSlimeRenderer::new);
		event.registerEntityRenderer(ModEntities.SKELETON_WOLF.get(), SkeletonWolfRenderer::new);
		event.registerEntityRenderer(ModEntities.WITHER_SKELETON_WOLF.get(), WitherSkeletonWolfRenderer::new);
		event.registerEntityRenderer(ModEntities.BABY_GHAST.get(), BabyGhastRenderer::new);
        event.registerEntityRenderer(ModEntities.MAGMA_COW.get(), MagmaCowRenderer::new);
        event.registerEntityRenderer(ModEntities.FURNACE_GOLEM.get(), FurnaceGolemRenderer::new);
		event.registerEntityRenderer(ModEntities.JOLLY_LLAMA.get(), JollyLlamaRenderer::new);

		event.registerEntityRenderer(ModEntities.SMELLY_EGG.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.FANCY_EGG.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.DUCK_EGG.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.BONE_SHARD.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.STRAY_BONE_SHARD.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.MELON_SEED.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.ZOMBIE_FLESH.get(), ThrownItemRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.CLUCK_SHROOM, CluckShroomModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FANCY_CHICKEN, FancyChickenModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HORNED_SHEEP, HornedSheepModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HORNED_SHEEP_FUR, HornedSheepModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HYPER_RABBIT, HyperRabbitModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.JUMBO_RABBIT, JumboRabbitModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.TEACUP_PIG, TeaCupPigModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MUDDY_PIG, () -> MuddyPigModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(ModModelLayers.BONE_SPIDER, BoneSpiderModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.STRAY_BONE_SPIDER, BoneSpiderModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.VILER_WITCH, VilerWitchModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.BABY_GHAST, BabyGhastModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.BABY_GHAST_CORE, BabyGhastModel::createCoreLayer);
		event.registerLayerDefinition(ModModelLayers.MAGMA_COW, MagmaCowModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FURNACE_GOLEM, FurnaceGolemModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.JOLLY_LLAMA, JollyLlamaModel::createBodyLayer);

		LayerDefinition layerDefinition = BoulderingZombieModel.createBodyLayer(CubeDeformation.NONE);
		LayerDefinition layerDefinition2 = LobberZombieModel.createBodyLayer(CubeDeformation.NONE);
		LayerDefinition layerDefinition3 = BoulderingDrownedModel.createBodyLayer(CubeDeformation.NONE);
		LayerDefinition layerDefinition4 = LobberDrownedModel.createBodyLayer(CubeDeformation.NONE);
		event.registerLayerDefinition(ModModelLayers.BOULDERING_ZOMBIE, () -> layerDefinition);
		event.registerLayerDefinition(ModModelLayers.LOBBER_ZOMBIE, () -> layerDefinition2);
		event.registerLayerDefinition(ModModelLayers.BOULDERING_DROWNED, () -> layerDefinition3);
		event.registerLayerDefinition(ModModelLayers.LOBBER_DROWNED, () -> layerDefinition4);
		event.registerLayerDefinition(ModModelLayers.BOULDERING_DROWNED_OUTER, () -> BoulderingDrownedModel.createBodyLayer(new CubeDeformation(0.25F)));
		event.registerLayerDefinition(ModModelLayers.LOBBER_DROWNED_OUTER, () -> LobberDrownedModel.createBodyLayer(new CubeDeformation(0.25F)));
	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.AddLayers event) {
		Minecraft.getInstance().getEntityRenderDispatcher().renderers.values().forEach(r -> {
			if (r instanceof PigRenderer) {
				((PigRenderer) r).addLayer(new MuddyPigMudLayer((PigRenderer) r, event.getEntityModels()));
				((PigRenderer) r).addLayer(new MuddyPigFlowerLayer((PigRenderer) r, event.getEntityModels()));
			}
		});
	}

	@SubscribeEvent
	public static void registerItemColorRenders(RegisterColorHandlersEvent.Item event) {
		event.register((p_92693_, p_92694_) -> {
			return PotionUtils.getPotion(p_92693_) != Potions.EMPTY ? PotionUtils.getColor(p_92693_) : 0xFFFFFF;
		}, ModItems.BONE_SHARD.get());
	}

	public static void setup(FMLCommonSetupEvent event) {
	}
}
