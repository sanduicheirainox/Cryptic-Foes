package com.min01.crypticfoes.block;

import com.min01.crypticfoes.CrypticFoes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CrypticBlocks 
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CrypticFoes.MODID);
	
	public static final RegistryObject<Block> FALLEN_LEAVES = BLOCKS.register("fallen_leaves", () -> new FallenLeavesBlock());
	public static final RegistryObject<Block> BLOCK_OF_FRAGILE_BONES = BLOCKS.register("block_of_fragile_bones", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)));
	public static final RegistryObject<Block> PILE_OF_FRAGILE_BONES = BLOCKS.register("pile_of_fragile_bones", () -> new PileofFragileBonesBlock());
	public static final RegistryObject<Block> POLISHED_PILE_OF_FRAGILE_BONES = BLOCKS.register("polished_pile_of_fragile_bones", () -> new PileofFragileBonesBlock());
	public static final RegistryObject<Block> WAXED_PILE_OF_FRAGILE_BONES = BLOCKS.register("waxed_pile_of_fragile_bones", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)));
	public static final RegistryObject<Block> WAXED_POLISHED_PILE_OF_FRAGILE_BONES = BLOCKS.register("waxed_polished_pile_of_fragile_bones", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)));
    public static final RegistryObject<Block> PILE_OF_FRAGILE_BONES_SLAB = BLOCKS.register("pile_of_fragile_bones_slab", () -> new PileofFragileBonesSlabBlock());
    public static final RegistryObject<Block> POLISHED_PILE_OF_FRAGILE_BONES_SLAB = BLOCKS.register("polished_pile_of_fragile_bones_slab", () -> new PileofFragileBonesSlabBlock());
}
