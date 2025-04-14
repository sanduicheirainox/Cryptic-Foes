package com.min01.crypticfoes.effect;

import java.util.UUID;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FragilityEffect extends MobEffect
{
	public FragilityEffect()
	{
		super(MobEffectCategory.HARMFUL, 10129001);
		this.addAttributeModifier(Attributes.ARMOR, UUID.randomUUID().toString(), -4.0F, Operation.ADDITION);
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) 
	{
		return duration > 0;
	}
}
