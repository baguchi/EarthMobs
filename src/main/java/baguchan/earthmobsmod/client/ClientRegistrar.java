package baguchan.earthmobsmod.client;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMoss;
import baguchan.earthmobsmod.api.IMuddyPig;
import baguchan.earthmobsmod.api.ISheared;
import baguchan.earthmobsmod.client.model.*;
import baguchan.earthmobsmod.client.render.*;
import baguchan.earthmobsmod.client.render.layer.MossSheepLayer;
import baguchan.earthmobsmod.client.render.layer.MuddyPigFlowerLayer;
import baguchan.earthmobsmod.client.render.layer.MuddyPigMudLayer;
import baguchan.earthmobsmod.client.render.zombie.*;
import baguchan.earthmobsmod.fluidtype.MudFluidType;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModFluidTypes;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EarthMobsMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
    public static void setup(FMLClientSetupEvent event) {

    }

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(ModEntities.CLUCK_SHROOM.get(), CluckShroomRender::new);
        event.registerEntityRenderer(ModEntities.FANCY_CHICKEN.get(), FancyChickenRenderer::new);
        event.registerEntityRenderer(ModEntities.WOOLY_COW.get(), WoolyCowRenderer::new);
        event.registerEntityRenderer(ModEntities.UMBRA_COW.get(), UmbraCowRenderer::new);

        event.registerEntityRenderer(ModEntities.TEACUP_PIG.get(), TeaCupPigRenderer::new);


        event.registerEntityRenderer(ModEntities.HORNED_SHEEP.get(), HornedSheepRenderer::new);
        event.registerEntityRenderer(ModEntities.HYPER_RABBIT.get(), HyperRabbitRenderer::new);
        event.registerEntityRenderer(ModEntities.ZOMBIFIED_RABBIT.get(), ZombifiedRabbitRenderer::new);
        event.registerEntityRenderer(ModEntities.MOOBLOOM.get(), MoobloomRenderer::new);
        event.registerEntityRenderer(ModEntities.MOOLIP.get(), MoolipRenderer::new);
        event.registerEntityRenderer(ModEntities.JUMBO_RABBIT.get(), JumboRabbitRenderer::new);
        event.registerEntityRenderer(ModEntities.ZOMBIFIED_PIG.get(), ZombifiedPigRenderer::new);

        event.registerEntityRenderer(ModEntities.MELON_GOLEM.get(), MelonGolemRenderer::new);

        event.registerEntityRenderer(ModEntities.BONE_SPIDER.get(), BoneSpiderRender::new);
		event.registerEntityRenderer(ModEntities.STRAY_BONE_SPIDER.get(), StrayBoneSpiderRender::new);
		event.registerEntityRenderer(ModEntities.VILER_WITCH.get(), VilerWitchRenderer::new);
		event.registerEntityRenderer(ModEntities.BOULDERING_ZOMBIE.get(), BoulderingZombieRenderer::new);
		event.registerEntityRenderer(ModEntities.LOBBER_ZOMBIE.get(), LobberZombieRenderer::new);

		event.registerEntityRenderer(ModEntities.BOULDERING_DROWNED.get(), BoulderingDrownedRenderer::new);
		event.registerEntityRenderer(ModEntities.LOBBER_DROWNED.get(), LobberDrownedRenderer::new);

        event.registerEntityRenderer(ModEntities.BOULDERING_FROZEN_ZOMBIE.get(), BoulderingFrozenZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.LOBBER_HUSK.get(), LobberHuskRenderer::new);

		event.registerEntityRenderer(ModEntities.TROPICAL_SLIME.get(), TropicalSlimeRenderer::new);
		event.registerEntityRenderer(ModEntities.SKELETON_WOLF.get(), SkeletonWolfRenderer::new);
		event.registerEntityRenderer(ModEntities.WITHER_SKELETON_WOLF.get(), WitherSkeletonWolfRenderer::new);
		event.registerEntityRenderer(ModEntities.MAGMA_COW.get(), MagmaCowRenderer::new);
        event.registerEntityRenderer(ModEntities.FURNACE_GOLEM.get(), FurnaceGolemRenderer::new);
		event.registerEntityRenderer(ModEntities.JOLLY_LLAMA.get(), JollyLlamaRenderer::new);

		event.registerEntityRenderer(ModEntities.SMELLY_EGG.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.FANCY_EGG.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.BONE_SHARD.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.STRAY_BONE_SHARD.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.MELON_SEED.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.ZOMBIE_FLESH.get(), ThrownItemRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.CLUCK_SHROOM, CluckShroomModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FANCY_CHICKEN, FancyChickenModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.CLUCK_SHROOM_BABY, () -> CluckShroomModel.createBodyLayer().apply(CluckShroomModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(ModModelLayers.FANCY_CHICKEN_BABY, () -> FancyChickenModel.createBodyLayer().apply(FancyChickenModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(ModModelLayers.HORNED_SHEEP, HornedSheepModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HORNED_SHEEP_FUR, HornedSheepModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HYPER_RABBIT, HyperRabbitModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.JUMBO_RABBIT, JumboRabbitModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.TEACUP_PIG, TeaCupPigModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MUDDY_PIG, () -> MuddyPigModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(ModModelLayers.BONE_SPIDER, BoneSpiderModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.STRAY_BONE_SPIDER, BoneSpiderModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.VILER_WITCH, VilerWitchModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MAGMA_COW, MagmaCowModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MAGMA_COW_GLOW, MagmaCowModel::createAnimateBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FURNACE_GOLEM, FurnaceGolemModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.JOLLY_LLAMA, JollyLlamaModel::createBodyLayer);

		LayerDefinition layerDefinition = BoulderingZombieModel.createBodyLayer();
		LayerDefinition layerDefinition2 = LobberZombieModel.createBodyLayer();
		LayerDefinition layerDefinition3 = BoulderingDrownedModel.createBodyLayer(CubeDeformation.NONE);
		LayerDefinition layerDefinition4 = LobberDrownedModel.createBodyLayer(CubeDeformation.NONE);
		event.registerLayerDefinition(ModModelLayers.BOULDERING_ZOMBIE, () -> layerDefinition);
		event.registerLayerDefinition(ModModelLayers.LOBBER_ZOMBIE, () -> layerDefinition2);
		event.registerLayerDefinition(ModModelLayers.BOULDERING_DROWNED, () -> layerDefinition3);
		event.registerLayerDefinition(ModModelLayers.LOBBER_DROWNED, () -> layerDefinition4);
		event.registerLayerDefinition(ModModelLayers.BOULDERING_ZOMBIE_BABY, () -> layerDefinition.apply(ZombieModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(ModModelLayers.LOBBER_ZOMBIE_BABY, () -> layerDefinition2.apply(ZombieModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(ModModelLayers.BOULDERING_DROWNED_BABY, () -> layerDefinition3.apply(ZombieModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(ModModelLayers.LOBBER_DROWNED_BABY, () -> layerDefinition4.apply(ZombieModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(ModModelLayers.BOULDERING_DROWNED_OUTER, () -> BoulderingDrownedModel.createBodyLayer(new CubeDeformation(0.25F)));
		event.registerLayerDefinition(ModModelLayers.LOBBER_DROWNED_OUTER, () -> LobberDrownedModel.createBodyLayer(new CubeDeformation(0.25F)));
		event.registerLayerDefinition(ModModelLayers.BOULDERING_DROWNED_OUTER_BABY, () -> BoulderingDrownedModel.createBodyLayer(new CubeDeformation(0.25F)).apply(ZombieModel.BABY_TRANSFORMER));
		event.registerLayerDefinition(ModModelLayers.LOBBER_DROWNED_OUTER_BABY, () -> LobberDrownedModel.createBodyLayer(new CubeDeformation(0.25F)).apply(ZombieModel.BABY_TRANSFORMER));
	}

	@SubscribeEvent
	public static void registerClientExtension(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new MudFluidType.MudRender(), ModFluidTypes.MUD.get());
	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.AddLayers event) {

		if (event.getRenderer(EntityType.PIG) instanceof PigRenderer r) {
				((PigRenderer) r).addLayer(new MuddyPigMudLayer((PigRenderer) r, event.getEntityModels()));
				((PigRenderer) r).addLayer(new MuddyPigFlowerLayer((PigRenderer) r, event.getEntityModels()));
			}
		if (event.getRenderer(EntityType.SHEEP) instanceof SheepRenderer r) {
                ((SheepRenderer) r).addLayer(new MossSheepLayer((SheepRenderer) r, event.getEntityModels()));
            }
	}

	@SubscribeEvent
	public static void registerLayerData(RegisterRenderStateModifiersEvent event) {
		event.registerEntityModifier(PigRenderer.class, (pig, renderState) -> {
			if (pig instanceof IMuddyPig muddy) {
				renderState.setRenderData(MuddyPigMudLayer.IS_MUD, muddy.isMuddy());

			}
			if (pig instanceof ISheared sheared) {
				renderState.setRenderData(MuddyPigMudLayer.IS_SHEARED, sheared.isSheared());
				renderState.setRenderData(MuddyPigFlowerLayer.FLOWER_DYE, sheared.getColor());
			}
		});
		event.registerEntityModifier(SheepRenderer.class, (sheep, renderState) -> {
			if (sheep instanceof IMoss moss) {
				renderState.setRenderData(MossSheepLayer.MOSS, moss.isMoss());

			}
		});
	}
}
