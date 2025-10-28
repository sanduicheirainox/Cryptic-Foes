package com.min01.crypticfoes.block;

import javax.annotation.Nullable;

import com.min01.crypticfoes.blockentity.CrypticSkullBlockEntity;
import com.min01.crypticfoes.misc.CrypticSkullTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock.Type;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrypticWallSkullBlock extends WallSkullBlock
{
	protected static final VoxelShape HOLWER_SHAPE_SOUTH = Block.box(1.5D, 4.0D, 0.0D, 14.5D, 14.0D, 10.0D);
	protected static final VoxelShape HOLWER_SHAPE_NORTH = Block.box(1.5D, 4.0D, 6.0D, 14.5D, 14.0D, 16.0D);
	
	protected static final VoxelShape HOLWER_SHAPE_EAST = Block.box(0.0D, 4.0D, 1.5D, 10.0D, 14.0D, 14.5D);
	protected static final VoxelShape HOLWER_SHAPE_WEST = Block.box(6.0D, 4.0D, 1.5D, 16.0D, 14.0D, 14.5D);
	
	public CrypticWallSkullBlock(Type p_56318_, Properties p_56319_)
	{
		super(p_56318_, p_56319_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_151996_, BlockState p_151997_) 
	{
		return new CrypticSkullBlockEntity(p_151996_, p_151997_);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_151992_, BlockState p_151993_, BlockEntityType<T> p_151994_)
	{
		if(p_151992_.isClientSide) 
		{
			if(p_151993_.is(CrypticBlocks.HOWLER_WALL_HEAD.get())) 
			{
				return createTickerHelper(p_151994_, CrypticBlocks.CRYPTIC_SKULL_BLOCK_ENTITY.get(), CrypticSkullBlockEntity::update);
			}
		}
		return null;
	}
	
	@Override
	public VoxelShape getShape(BlockState p_56331_, BlockGetter p_56332_, BlockPos p_56333_, CollisionContext p_56334_) 
	{
		if(this.getType() == CrypticSkullTypes.HOWLER)
		{
			Direction direction = p_56331_.getValue(FACING);
			switch(direction)
			{
			case EAST:
				return HOLWER_SHAPE_EAST;
			case NORTH:
				return HOLWER_SHAPE_NORTH;
			case SOUTH:
				return HOLWER_SHAPE_SOUTH;
			case WEST:
				return HOLWER_SHAPE_WEST;
			default:
				break;
			}
		}
		return super.getShape(p_56331_, p_56332_, p_56333_, p_56334_);
	}
}
