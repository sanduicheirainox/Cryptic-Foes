package com.min01.crypticfoes.block;

import com.min01.crypticfoes.CrypticFoes;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CrypticBlocks 
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CrypticFoes.MODID);
	
	public static final RegistryObject<Block> FALLEN_LEAVES = BLOCKS.register("fallen_leaves", () -> new FallenLeavesBlock());
}
