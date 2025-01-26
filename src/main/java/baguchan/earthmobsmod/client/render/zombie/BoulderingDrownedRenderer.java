package baguchan.earthmobsmod.client.render.zombie;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoulderingDrownedModel;
import baguchan.earthmobsmod.client.render.layer.OuterLayer;
import baguchan.earthmobsmod.client.render.layer.SpinAttackEffectLayer;
import baguchan.earthmobsmod.client.render.state.BoulderingZombieRenderState;
import baguchan.earthmobsmod.entity.BoulderingDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoulderingDrownedRenderer<T extends BoulderingDrowned> extends AgeableMobRenderer<T, BoulderingZombieRenderState, BoulderingDrownedModel<BoulderingZombieRenderState>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bouldering_zombie/bouldering_drowned.png");
	private static final ResourceLocation OUTER_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bouldering_zombie/bouldering_drowned_outer.png");

	public BoulderingDrownedRenderer(EntityRendererProvider.Context p_173964_) {
		super(p_173964_, new BoulderingDrownedModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_DROWNED)), new BoulderingDrownedModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_DROWNED_BABY)), 0.5F);
		this.addLayer(new OuterLayer(this, OUTER_LOCATION, new BoulderingDrownedModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_DROWNED_OUTER)), new BoulderingDrownedModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_DROWNED_OUTER_BABY))));
		this.addLayer(new SpinAttackEffectLayer<>(this, p_173964_.getModelSet()));
		this.addLayer(new baguchi.bagus_lib.client.layer.CustomArmorLayer(this, p_173964_));
	}

	public void extractRenderState(T p_365075_, BoulderingZombieRenderState p_361774_, float p_363123_) {
		super.extractRenderState(p_365075_, p_361774_, p_363123_);
		extractHumanoidRenderState(p_365075_, p_361774_, p_363123_, this.itemModelResolver);
		p_361774_.leftArmPose = this.getArmPose(p_365075_, HumanoidArm.LEFT);
		p_361774_.rightArmPose = this.getArmPose(p_365075_, HumanoidArm.RIGHT);
	}

	public static void extractHumanoidRenderState(LivingEntity p_365104_, HumanoidRenderState p_362998_, float p_363706_, ItemModelResolver p_388651_) {
		ArmedEntityRenderState.extractArmedEntityRenderState(p_365104_, p_362998_, p_388651_);
		p_362998_.isCrouching = p_365104_.isCrouching();
		p_362998_.isFallFlying = p_365104_.isFallFlying();
		p_362998_.isVisuallySwimming = p_365104_.isVisuallySwimming();
		p_362998_.isPassenger = p_365104_.isPassenger() && (p_365104_.getVehicle() != null && p_365104_.getVehicle().shouldRiderSit());
		p_362998_.speedValue = 1.0F;
		if (p_362998_.isFallFlying) {
			p_362998_.speedValue = (float) p_365104_.getDeltaMovement().lengthSqr();
			p_362998_.speedValue /= 0.2F;
			p_362998_.speedValue = p_362998_.speedValue * p_362998_.speedValue * p_362998_.speedValue;
		}

		if (p_362998_.speedValue < 1.0F) {
			p_362998_.speedValue = 1.0F;
		}

		p_362998_.attackTime = p_365104_.getAttackAnim(p_363706_);
		p_362998_.swimAmount = p_365104_.getSwimAmount(p_363706_);
		p_362998_.attackArm = getAttackArm(p_365104_);
		p_362998_.useItemHand = p_365104_.getUsedItemHand();
		p_362998_.maxCrossbowChargeDuration = (float) CrossbowItem.getChargeDuration(p_365104_.getUseItem(), p_365104_);
		p_362998_.ticksUsingItem = p_365104_.getTicksUsingItem();
		p_362998_.isUsingItem = p_365104_.isUsingItem();
		p_362998_.elytraRotX = p_365104_.elytraAnimationState.getRotX(p_363706_);
		p_362998_.elytraRotY = p_365104_.elytraAnimationState.getRotY(p_363706_);
		p_362998_.elytraRotZ = p_365104_.elytraAnimationState.getRotZ(p_363706_);
		p_362998_.headEquipment = getEquipmentIfRenderable(p_365104_, EquipmentSlot.HEAD);
		p_362998_.chestEquipment = getEquipmentIfRenderable(p_365104_, EquipmentSlot.CHEST);
		p_362998_.legsEquipment = getEquipmentIfRenderable(p_365104_, EquipmentSlot.LEGS);
		p_362998_.feetEquipment = getEquipmentIfRenderable(p_365104_, EquipmentSlot.FEET);
	}

	private static ItemStack getEquipmentIfRenderable(LivingEntity p_386637_, EquipmentSlot p_386956_) {
		ItemStack itemstack = p_386637_.getItemBySlot(p_386956_);
		return HumanoidArmorLayer.shouldRender(itemstack, p_386956_) ? itemstack.copy() : ItemStack.EMPTY;
	}

	private static HumanoidArm getAttackArm(LivingEntity p_362737_) {
		HumanoidArm humanoidarm = p_362737_.getMainArm();
		return p_362737_.swingingArm == InteractionHand.MAIN_HAND ? humanoidarm : humanoidarm.getOpposite();
	}

	@Override
	public BoulderingZombieRenderState createRenderState() {
		return new BoulderingZombieRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(BoulderingZombieRenderState p_361561_) {
		return LOCATION;
	}

	@Override
	protected void setupRotations(BoulderingZombieRenderState p_361137_, PoseStack p_114104_, float p_114105_, float p_114106_) {
		super.setupRotations(p_361137_, p_114104_, p_114105_, p_114106_);
		float f = p_361137_.swimAmount;
		if (f > 0.0F) {
			float f1 = -10.0F - p_361137_.xRot;
			float f2 = Mth.lerp(f, 0.0F, f1);
			p_114104_.rotateAround(Axis.XP.rotationDegrees(f2), 0.0F, p_361137_.boundingBoxHeight / 2.0F / p_114106_, 0.0F);
		}
	}

	protected HumanoidModel.ArmPose getArmPose(T p_388016_, HumanoidArm p_386643_) {
		ItemStack itemstack = p_388016_.getItemHeldByArm(p_386643_);
		return p_388016_.getMainArm() == p_386643_ && p_388016_.isAggressive() && itemstack.is(Items.TRIDENT)
				? HumanoidModel.ArmPose.THROW_SPEAR
				: HumanoidModel.ArmPose.EMPTY;
	}
}
