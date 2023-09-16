package baguchan.earthmobsmod.client.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class BabyGhastAnimation {

	public static final AnimationDefinition SHOOT = AnimationDefinition.Builder.withLength(0.68f)
			.addAnimation("body",
					new AnimationChannel(AnimationChannel.Targets.SCALE,
							new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.52f, KeyframeAnimations.scaleVec(1.25f, 1.25f, 1.25f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.68f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
									AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(2.6f)
			.addAnimation("body",
					new AnimationChannel(AnimationChannel.Targets.POSITION,
							new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.36f, KeyframeAnimations.posVec(0f, 1.5f, 0f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(2.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.CATMULLROM))).build();
}
