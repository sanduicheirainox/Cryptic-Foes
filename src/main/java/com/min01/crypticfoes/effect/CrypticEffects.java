package com.min01.crypticfoes.effect;

import com.min01.crypticfoes.CrypticFoes;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CrypticEffects 
{
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CrypticFoes.MODID);
	
	public static final RegistryObject<MobEffect> FRAGILITY = EFFECTS.register("fragility", () -> new FragilityEffect());
}
