package com.min01.crypticfoes.advancements.critereon;

import com.google.gson.JsonObject;
import com.min01.crypticfoes.CrypticFoes;

import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class StunningSpeechTrigger extends SimpleCriterionTrigger<StunningSpeechTrigger.TriggerInstance>
{
	public static final ResourceLocation ID = new ResourceLocation(CrypticFoes.MODID, "stunning_speech");
	
	@Override
	public ResourceLocation getId() 
	{
		return ID;
	}
	
	public void trigger(ServerPlayer serverPlayer) 
	{
		this.trigger(serverPlayer, t -> true);
	}

	@Override
	protected TriggerInstance createInstance(JsonObject p_66248_, ContextAwarePredicate p_286603_, DeserializationContext p_66250_) 
	{
		return new TriggerInstance(p_286603_);
	}
	
	public static class TriggerInstance extends AbstractCriterionTriggerInstance 
	{
		public TriggerInstance(ContextAwarePredicate p_286466_) 
		{
			super(ID, p_286466_);
		}
	}
}
