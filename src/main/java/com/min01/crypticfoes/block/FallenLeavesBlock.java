package com.min01.crypticfoes.block;

import java.util.List;

import com.min01.crypticfoes.entity.living.EntityBrancher;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FallenLeavesBlock extends BushBlock
{
	protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.1D, 16.0D);
	
	public FallenLeavesBlock() 
	{
		super(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LEAVES).instabreak().noCollission());
	}
	
	@Override
	public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_)
	{
		return AABB;
	}
	
	//TODO only trigger once
	@Override
	public void entityInside(BlockState p_60495_, Level p_60496_, BlockPos p_60497_, Entity p_60498_)
	{
		if(!p_60498_.isShiftKeyDown() && p_60498_ instanceof LivingEntity living)
		{
			if(!(living instanceof EntityBrancher))
			{
				List<EntityBrancher> list = p_60496_.getEntitiesOfClass(EntityBrancher.class, living.getBoundingBox().inflate(5.0F), t -> !t.isAngry());
				list.forEach(t -> 
				{
					t.setAngerCount(t.getAngerCount() + 1);
					t.setAnimationState(1);
					t.setAnimationTick(20);
				});
			}
		}
	}
}
