package com.min01.crypticfoes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class PileofFragileBonesBlock extends Block
{
	public PileofFragileBonesBlock() 
	{
		super(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK));
	}
	
	@Override
	public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_)
	{
		if(!p_152434_.isSteppingCarefully() && p_152434_ instanceof LivingEntity)
		{
			if(!p_152431_.isClientSide)
			{
				if(p_152433_.is(CrypticBlocks.PILE_OF_FRAGILE_BONES.get()) || p_152433_.is(CrypticBlocks.POLISHED_PILE_OF_FRAGILE_BONES.get()))
				{
					p_152431_.destroyBlock(p_152432_, false);
					p_152431_.gameEvent(GameEvent.BLOCK_DESTROY, p_152432_, GameEvent.Context.of(p_152433_));
					p_152431_.levelEvent(2001, p_152432_, Block.getId(p_152433_));
				}
			}
		}
	}
}
