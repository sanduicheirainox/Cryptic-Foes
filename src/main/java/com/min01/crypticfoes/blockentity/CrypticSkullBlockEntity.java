package com.min01.crypticfoes.blockentity;

import com.min01.crypticfoes.block.CrypticBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CrypticSkullBlockEntity extends SkullBlockEntity
{
	public CrypticSkullBlockEntity(BlockPos p_155731_, BlockState p_155732_)
	{
		super(p_155731_, p_155732_);
	}

	@Override
	public BlockEntityType<?> getType()
	{
		return CrypticBlocks.CRYPTIC_SKULL_BLOCK_ENTITY.get();
	}
}
