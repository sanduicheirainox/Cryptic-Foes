package com.min01.crypticfoes.entity.ai.goal;

import com.min01.crypticfoes.entity.CrypticEntities;
import com.min01.crypticfoes.entity.living.EntityPetrified;
import com.min01.crypticfoes.entity.projectile.EntityPetrifiedStone;

public class PetrifiedShootStoneGoal extends BasicAnimationSkillGoal<EntityPetrified>
{
	public PetrifiedShootStoneGoal(EntityPetrified mob)
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(1);
	}
	
	@Override
	public boolean additionalStartCondition() 
	{
		return this.mob.distanceTo(this.mob.getTarget()) <= 8.0F && this.mob.hasStone();
	}
	
	@Override
	protected void performSkill() 
	{
		if(this.mob.posArray[0] != null)
		{
			EntityPetrifiedStone stone = new EntityPetrifiedStone(CrypticEntities.PETRIFIED_STONE.get(), this.mob.level);
			stone.setPos(this.mob.posArray[0]);
			stone.setOwner(this.mob);
			stone.shootFromRotation(this.mob, this.mob.getXRot(), this.mob.getYHeadRot(), 0.0F, 1.5F, 1.0F);
			this.mob.level.addFreshEntity(stone);
			this.mob.setHasStone(false);
		}
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setAnimationTick(100);
	}

	@Override
	protected int getSkillUsingTime()
	{
		return 18;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 135;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 6;
	}
}
