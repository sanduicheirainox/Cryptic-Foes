package com.min01.crypticfoes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class PileofFragileBonesSlabBlock extends SlabBlock
{
	public PileofFragileBonesSlabBlock() 
	{
		super(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK));
	}
	
	@Override
	public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_)
	{
		if(!p_152434_.isSteppingCarefully() && p_152434_ instanceof LivingEntity)
		{
			int tick = p_152433_.is(CrypticBlocks.POLISHED_PILE_OF_FRAGILE_BONES_SLAB.get()) ? 5 : 2;
			p_152431_.scheduleTick(p_152432_, this, tick);
		}
	}
	
	@Override
	public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) 
	{
		p_222946_.destroyBlock(p_222947_, false);
		p_222946_.gameEvent(GameEvent.BLOCK_DESTROY, p_222947_, GameEvent.Context.of(p_222945_));
		p_222946_.levelEvent(2001, p_222947_, Block.getId(p_222945_));
	}
}
