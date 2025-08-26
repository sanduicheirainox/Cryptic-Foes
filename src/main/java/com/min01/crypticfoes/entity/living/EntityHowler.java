package com.min01.crypticfoes.entity.living;

import com.min01.crypticfoes.entity.AbstractAnimatableMonster;
import com.min01.crypticfoes.entity.ai.goal.HowlerPunchGoal;
import com.min01.crypticfoes.entity.ai.goal.HowlerRoarGoal;
import com.min01.crypticfoes.util.CrypticUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntityHowler extends AbstractAnimatableMonster
{
	public static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(EntityHowler.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_FALLING = SynchedEntityData.defineId(EntityHowler.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<BlockPos> SLEEP_POS = SynchedEntityData.defineId(EntityHowler.class, EntityDataSerializers.BLOCK_POS);
	
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState sleepAnimationState = new AnimationState();
	public final AnimationState awakeAnimationState = new AnimationState();
	public final AnimationState fallAnimationState = new AnimationState();
	public final AnimationState landAnimationState = new AnimationState();
	public final AnimationState roarAnimationState = new AnimationState();
	public final AnimationState blinkAnimationState = new AnimationState();
	public final AnimationState punchAnimationState = new AnimationState();
	
	public int ambientTick;
	public int targetTick;
	
	public EntityHowler(EntityType<? extends Monster> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
		this.posArray = new Vec3[1];
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
    			.add(Attributes.MAX_HEALTH, 30.0F)
    			.add(Attributes.ARMOR, 4.0F)
    			.add(Attributes.ATTACK_DAMAGE, 10.0F)
    			.add(Attributes.KNOCKBACK_RESISTANCE, 100.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.25F)
    			.add(Attributes.FOLLOW_RANGE, 100.0F);
    }
    
    @Override
    protected void registerGoals() 
    {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D)
        {
        	@Override
        	public boolean canUse() 
        	{
        		return super.canUse() && !EntityHowler.this.isHowlerSleeping() && !EntityHowler.this.isUsingSkill();
        	}
        });
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F)
        {
        	@Override
        	public boolean canUse() 
        	{
        		return super.canUse() && !EntityHowler.this.isHowlerSleeping() && !EntityHowler.this.isUsingSkill();
        	}
        });
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this)
        {
        	@Override
        	public boolean canUse() 
        	{
        		return super.canUse() && !EntityHowler.this.isHowlerSleeping() && !EntityHowler.this.isUsingSkill();
        	}
        });
        this.goalSelector.addGoal(0, new HowlerPunchGoal(this));
        this.goalSelector.addGoal(0, new HowlerRoarGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false) 
        {
        	@Override
        	public boolean canUse() 
        	{
        		return super.canUse() && EntityHowler.this.isHowlerSleeping();
        	}
        	
        	@Override
        	protected AABB getTargetSearchArea(double p_26069_) 
        	{
        		return this.mob.getBoundingBox().inflate(3, 64, 3);
        	}
        });
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(IS_SLEEPING, false);
    	this.entityData.define(IS_FALLING, false);
    	this.entityData.define(SLEEP_POS, BlockPos.ZERO);
    }
    
	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_219422_) 
	{
        if(ANIMATION_STATE.equals(p_219422_) && this.level.isClientSide) 
        {
            switch(this.getAnimationState()) 
            {
        		case 0: 
        		{
        			this.stopAllAnimationStates();
        			break;
        		}
        		case 1: 
        		{
        			this.stopAllAnimationStates();
        			this.sleepAnimationState.start(this.tickCount);
        			break;
        		}
        		case 2: 
        		{
        			this.stopAllAnimationStates();
        			this.awakeAnimationState.start(this.tickCount);
        			break;
        		}
        		case 3: 
        		{
        			this.stopAllAnimationStates();
        			this.landAnimationState.start(this.tickCount);
        			break;
        		}
        		case 4: 
        		{
        			this.stopAllAnimationStates();
        			this.roarAnimationState.start(this.tickCount);
        			break;
        		}
        		case 5: 
        		{
        			this.stopAllAnimationStates();
        			this.punchAnimationState.start(this.tickCount);
        			break;
        		}
            }
        }
	}
	
	@Override
	public void stopAllAnimationStates()
	{
		this.sleepAnimationState.stop();
		this.awakeAnimationState.stop();
		this.landAnimationState.stop();
		this.roarAnimationState.stop();
		this.punchAnimationState.stop();
	}
    
    @Override
    public void tick() 
    {
    	super.tick();
    	this.resetFallDistance();
    	
    	if(this.level.isClientSide)
    	{
    		this.idleAnimationState.animateWhen(!this.isHowlerSleeping() && !CrypticUtil.isMoving(this) && this.getAnimationState() == 0, this.tickCount);
    		this.blinkAnimationState.animateWhen(!this.isHowlerSleeping() && this.ambientTick > 0, this.tickCount);
    		this.fallAnimationState.animateWhen(!this.isHowlerSleeping() && this.isFalling(), this.tickCount);
    	}
    	if(this.ambientTick > 0)
    	{
			this.ambientTick--;
    	}
    	if(!this.isHowlerSleeping())
    	{
        	if(!this.level.isClientSide)
        	{
        		if(this.getTarget() == null)
        		{
            		this.targetTick++;
        		}
        		else
        		{
        			this.targetTick = 0;
            		if(this.canLook())
            		{
            			this.getLookControl().setLookAt(this.getTarget(), 30.0F, 30.0F);
            		}
        			if(this.canMove())
        			{
    					this.getNavigation().moveTo(this.getTarget(), 1.0F);
        			}
        		}
        		if(this.targetTick >= 2 && !this.level.canSeeSky(this.blockPosition()) && this.getAnimationState() == 0)
        		{
        			BlockPos ceilingPos = CrypticUtil.getCeilingPos(this.level, this.getX(), this.getY(), this.getZ(), -4);
        			this.setSleepPos(ceilingPos);
        			this.setAnimationState(1);
        			this.setAnimationTick(40);
        			this.setCanMove(false);
        			this.setCanLook(false);
        			this.setHowlerSleeping(true);
        		}
        	}
        	if(this.onGround())
        	{
        		if(this.isFalling())
        		{
            		this.setFalling(false);
            		this.setAnimationState(3);
            		this.setAnimationTick(43);
        		}
        		else if(this.getAnimationState() == 3 && this.getAnimationTick() <= 0)
        		{
        			this.setAnimationState(0);
        			this.setCanMove(true);
        			this.setCanLook(true);
        		}
        	}
    	}
    	else if(!this.getSleepPos().equals(BlockPos.ZERO))
    	{
    		if(!this.isFalling())
    		{
        		BlockPos pos = this.getSleepPos();
        		this.setPos(pos.getX(), pos.getY(), pos.getZ());
        		this.setNoGravity(true);
        		if(this.getAnimationState() == 2 && this.getAnimationTick() <= 0)
        		{
            		this.setHowlerSleeping(false);
        			this.setNoGravity(false);
        			this.setFalling(true);
        			this.setAnimationState(0);
        		}
    		}
    	}
    }
    
    @Override
    public void playAmbientSound() 
    {
    	super.playAmbientSound();
    	if(!this.isHowlerSleeping() && this.ambientTick <= 0)
    	{
			this.ambientTick = 10;
    	}
    }
    
    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) 
    {
    	if(p_21016_.is(DamageTypeTags.IS_FALL))
    	{
    		return false;
    	}
		if(p_21016_.is(DamageTypes.IN_WALL))
		{
			if(this.isHowlerSleeping() || this.isFalling())
			{
				return false;
			}
		}
    	if(this.isHowlerSleeping())
    	{
    		if(p_21016_.getDirectEntity() != null)
    		{
    			this.targetTick = 0;
    			this.setAnimationState(2);
    			this.setAnimationTick(60);
    		}
    	}
    	return super.hurt(p_21016_, p_21017_);
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_)
    {
    	super.readAdditionalSaveData(p_21450_);
    	this.setHowlerSleeping(p_21450_.getBoolean("isHowlerSleeping"));
    	this.setFalling(p_21450_.getBoolean("isFalling"));
    	this.setSleepPos(NbtUtils.readBlockPos(p_21450_.getCompound("SleepPos")));
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) 
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putBoolean("isHowlerSleeping", this.isHowlerSleeping());
    	p_21484_.putBoolean("isFalling", this.isFalling());
    	p_21484_.put("SleepPos", NbtUtils.writeBlockPos(this.getSleepPos()));
    }
    
    public void setHowlerSleeping(boolean value)
    {
    	this.entityData.set(IS_SLEEPING, value);
    }
    
    public boolean isHowlerSleeping()
    {
    	return this.entityData.get(IS_SLEEPING);
    }
    
    public void setFalling(boolean value)
    {
    	this.entityData.set(IS_FALLING, value);
    }
    
    public boolean isFalling()
    {
    	return this.entityData.get(IS_FALLING);
    }
    
    public void setSleepPos(BlockPos value)
    {
    	this.entityData.set(SLEEP_POS, value);
    }
    
    public BlockPos getSleepPos()
    {
    	return this.entityData.get(SLEEP_POS);
    }
}