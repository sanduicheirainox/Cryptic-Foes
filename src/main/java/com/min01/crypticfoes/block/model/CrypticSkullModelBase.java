package com.min01.crypticfoes.block.model;

import java.util.Optional;

import org.joml.Vector3f;

import com.min01.crypticfoes.block.animation.KeyframeBlockAnimations;
import com.min01.crypticfoes.blockentity.CrypticSkullBlockEntity;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;

public abstract class CrypticSkullModelBase extends SkullModelBase
{
	private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
	
	public abstract void setupAnim(CrypticSkullBlockEntity blockEntity, float ageInTicks);

	public abstract ModelPart root();

	public Optional<ModelPart> getAnyDescendantWithName(String p_233394_) 
	{
		return this.root().getAllParts().filter((p_233400_) -> 
		{
			return p_233400_.hasChild(p_233394_);
		}).findFirst().map((p_233397_) ->
		{
			return p_233397_.getChild(p_233394_);
		});
	}

	protected void animate(AnimationState p_233382_, AnimationDefinition p_233383_, float p_233384_)
	{
		this.animate(p_233382_, p_233383_, p_233384_, 1.0F);
	}

	protected void animate(AnimationState p_233386_, AnimationDefinition p_233387_, float p_233388_, float p_233389_) 
	{
		p_233386_.updateTime(p_233388_, p_233389_);
		p_233386_.ifStarted((p_233392_) ->
		{
			KeyframeBlockAnimations.animate(this, p_233387_, p_233392_.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE);
		});
	}
}
