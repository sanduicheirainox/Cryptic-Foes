package com.min01.crypticfoes.event;

import com.min01.crypticfoes.CrypticFoes;
import com.min01.crypticfoes.entity.CrypticEntities;
import com.min01.crypticfoes.entity.living.EntityBrancher;
import com.min01.crypticfoes.entity.living.EntityPetrified;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CrypticFoes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler 
{
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) 
    {
    	event.put(CrypticEntities.PETRIFIED.get(), EntityPetrified.createAttributes().build());
    	event.put(CrypticEntities.BRANCHER.get(), EntityBrancher.createAttributes().build());
    }
    
    @SubscribeEvent
    public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event)
    {
    	
    }
}
