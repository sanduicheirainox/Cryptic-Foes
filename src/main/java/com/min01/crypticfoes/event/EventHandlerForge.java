package com.min01.crypticfoes.event;

import com.min01.crypticfoes.CrypticFoes;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CrypticFoes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge 
{
	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		
	}
}
